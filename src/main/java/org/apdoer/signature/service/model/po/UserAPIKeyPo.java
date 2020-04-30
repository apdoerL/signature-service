package org.apdoer.signature.service.model.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * web 用户API记录表
 * @author apdoer
 */
@Data
@ToString
@Table(name = "web_apikey")
public class UserAPIKeyPo {

    /**
     *  自增ID，主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * API标识
     */
    private String name;

    /**
     * API访问密钥
     */
    private String accessKey;

    /**
     * 签名的密钥
     */
    private String secretKey;

    /**\
     * 是否可用，0：可用，1：不可用
     */
    private Integer enabled;

    private Date createTime;

    /**
     * 权限类型，1：交易权限，2：提货权限，3：交易提货权限
     */
    private Integer rightType;


}
