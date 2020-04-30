package org.apdoer.signature.service.service.impl;

import org.apdoer.signature.service.mapper.MsBrokerApikeyMapper;
import org.apdoer.signature.service.mapper.UserAPIKeyMapper;
import org.apdoer.signature.service.model.po.MsBrokerApikeyPo;
import org.apdoer.signature.service.model.po.UserAPIKeyPo;
import org.apdoer.signature.service.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author apdoer
 */
@Service
public class ApiKeyServiceImpl implements ApiKeyService {

	@Autowired
    private UserAPIKeyMapper userApiKeyMapper;

	@Autowired
    private MsBrokerApikeyMapper msbrokerApikeyMapper;

    @Cacheable(value = "USER", key = "'USER_sign_apikey_'+#apiKey")
    @Override
    public UserAPIKeyPo getUserByApiKey(String apiKey) {
        return userApiKeyMapper.selectByApiKey(apiKey);
    }

    @Cacheable(value = "BROKER", key = "'BROKER_sign_apikey_'+#apiKey")
    @Override
    public MsBrokerApikeyPo getBrokerApiKey(String apiKey) {
        return msbrokerApikeyMapper.selectByApiKey(apiKey);
    }
}
