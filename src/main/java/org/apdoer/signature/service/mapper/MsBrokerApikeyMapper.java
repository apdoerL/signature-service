package org.apdoer.signature.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.signature.service.model.po.MsBrokerApikeyPo;

public interface MsBrokerApikeyMapper {

	MsBrokerApikeyPo selectByApiKey(@Param("accessKey") String accessKey);
}