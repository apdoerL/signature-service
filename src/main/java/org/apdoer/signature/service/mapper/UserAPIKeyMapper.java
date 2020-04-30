package org.apdoer.signature.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.signature.service.model.po.UserAPIKeyPo;

public interface UserAPIKeyMapper {

    UserAPIKeyPo selectByApiKey(@Param("apiKey") String apiKey);

}
