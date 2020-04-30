package org.apdoer.signature.service.service;


import org.apdoer.common.service.model.vo.ResultVo;

/**
 * Copyright: Copyright (c) 2018 Chainext
 *
 * @author hang
 */

public interface SignatureService {

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
    ResultVo check(String apiKey, String signature, String verb, String path, long expires, String data);

    /**
     * 券商签名验证
     * 检查签名，如合法，则返回userApiKeyPo
     *
     * @param apiKey    apikey
     * @param expires   过期时间戳
     * @param path      路径
     * @param signature 签名
     * @param data      "{'s': 123}"
     * @param verb      GET|POST|DELETE|PUT
     */
    ResultVo brokerCheck(String apiKey, String signature, String verb, String path, long expires, String data);

    /**
     * 检查时间是否满足要求，与服务器时间超过5分钟表示请求不合法
     *
     * @param expires 请求时间戳
     * @return boolean
     */
    boolean checkExpires(long expires);

    /**
     * 检查资源名称
     *
     * @param verb GET|POST
     * @return bool
     */
    boolean checkVerb(String verb);

    /**
     * 解密secretKey
     *
     * @param encryptSecretKey 加密过的secretKey
     * @return String
     */
    String decodeSecretKey(String encryptSecretKey);


}
