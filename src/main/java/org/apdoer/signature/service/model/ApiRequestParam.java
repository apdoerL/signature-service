package org.apdoer.signature.service.model;

import lombok.Data;

import java.util.Map;

/**
 * @author apdoer
 */
@Data
public class ApiRequestParam {
    /**
     * api请求域名
     */
    private String host;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求参数
     */
    private Map<String,Object> map;

    /**
     * 请求签名
     */
    private String Signature;

    /**
     * 根据AccessKey获取的secretKey
     */
    private String secretKey;


}
