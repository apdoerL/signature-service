package org.apdoer.signature.service.service.impl;


import org.apdoer.common.service.model.po.UserPo;
import org.apdoer.common.service.model.vo.ResultVo;
import org.apdoer.common.service.service.UserService;
import org.apdoer.common.service.util.EncryptUtil;
import org.apdoer.common.service.util.ResultVoBuildUtils;
import org.apdoer.encrypt.client.EncryptClient;
import org.apdoer.signature.service.cache.ApikeyCache;
import org.apdoer.signature.service.code.ExceptionCode;
import org.apdoer.signature.service.enums.VerbEnum;
import org.apdoer.signature.service.model.po.MsBrokerApikeyPo;
import org.apdoer.signature.service.model.po.UserAPIKeyPo;
import org.apdoer.signature.service.service.ApiKeyService;
import org.apdoer.signature.service.service.SignatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 签名服务
 *
 * @author hang
 * @date 2019-01-24
 */
@Service
public class SignatureServiceImpl implements SignatureService {

    private Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);

    /**
     * 加解密服务
     */
    @Autowired
    EncryptClient encryptClient;

    @Autowired
    private org.apdoer.common.service.service.UserService UserService;

    @Autowired
    private ApiKeyService apiKeyService;

    /**
     * 检查签名，如合法，则返回userApiKeyPo
     *
     * @param apiKey    apikey
     * @param expires   过期时间戳
     * @param path      路径
     * @param signature 签名
     * @param data      "{'s': 123}"
     * @param verb      GET|POST|DELETE|PUT
     */
    @Override
    public ResultVo check(String apiKey, String signature, String verb, String path, long expires, String data) {

        // 获取用户api信息
        UserAPIKeyPo userAPIKeyPo = apiKeyService.getUserByApiKey(apiKey);
        if (userAPIKeyPo == null || !this.checkExpires(expires) || !this.checkVerb(verb)) {
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }
        // 签名
        String newSignature = null;
        try {
            newSignature = EncryptUtil.getHmacHashForSignature(this.decodeSecretKey(userAPIKeyPo.getSecretKey()), verb + path + expires + data);
        } catch (Exception e) {
            logger.error("签名错误：" + e.getMessage());
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }

        if (!newSignature.equals(signature)) {
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }
        UserPo userPo = this.UserService.getUserById(userAPIKeyPo.getUserId());
        return ResultVoBuildUtils.buildResultVo(ExceptionCode.SUCCESS.getCodeNo(),ExceptionCode.SUCCESS.getCodeName(),userPo);
    }

    @Override
    public ResultVo brokerCheck(String apiKey, String signature, String verb, String path, long expires, String data) {
        // 获取用户api信息
        MsBrokerApikeyPo brokerApikeyPo = apiKeyService.getBrokerApiKey(apiKey);
        if (brokerApikeyPo == null || !this.checkExpires(expires) || !this.checkVerb(verb)) {
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }
        // 签名
        String newSignature = null;
        try {
            newSignature = EncryptUtil.getHmacHashForSignature(this.decodeSecretKey(brokerApikeyPo.getSecretKey()), verb + path + expires + data);
        } catch (Exception e) {
            logger.error("签名错误：" + e.getMessage());
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }

        if (!newSignature.equals(signature)) {
            return ResultVoBuildUtils.buildResultVo(ExceptionCode.SIGNATURE_INVALID.getCodeNo(),ExceptionCode.SIGNATURE_INVALID.getCodeName());
        }
        UserPo userPo = this.UserService.getUserById(brokerApikeyPo.getUserId());
        return ResultVoBuildUtils.buildResultVo(ExceptionCode.SUCCESS.getCodeNo(),ExceptionCode.SUCCESS.getCodeName(),userPo);
    }

    /**
     * 检查时间是否满足要求，与服务器时间超过1分钟表示请求不合法
     *
     * @param expires 请求时间戳
     * @return boolean
     */
    @Override
    public boolean checkExpires(long expires) {
//        return true;
        long nowTime = System.currentTimeMillis();
        return nowTime - expires * 1000 <= 60 * 1000;
    }


    /**
     * 检查资源名称
     *
     * @param verb GET|POST|DELETE|PUT
     * @return bool
     */
    @Override
    public boolean checkVerb(String verb) {
    	return VerbEnum.isValid(verb);
        //String[] arr = {"GET", "POST", "DELETE", "PUT"};
        //return Arrays.asList(arr).contains(verb);
    }

    /**
     * 解密secretKey
     *
     * @param encryptSecretKey 加密过的secretKey
     * @return String
     */
    @Override
    public String decodeSecretKey(String encryptSecretKey) {
    	if (ApikeyCache.getInstant().containsKey(encryptSecretKey)) {
    		return ApikeyCache.getInstant().get(encryptSecretKey);
    	} else {
    		String decodekey = encryptClient.decrypt(encryptSecretKey);
    		ApikeyCache.getInstant().put(encryptSecretKey, decodekey);
    		return decodekey;
    	}
    }

}
