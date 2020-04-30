package org.apdoer.signature.service.service;


import org.apdoer.signature.service.model.po.MsBrokerApikeyPo;
import org.apdoer.signature.service.model.po.UserAPIKeyPo;

public interface ApiKeyService {


    UserAPIKeyPo getUserByApiKey(String apiKey);


    MsBrokerApikeyPo getBrokerApiKey(String apiKey);

}
