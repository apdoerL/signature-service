package org.apdoer.signature.service.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author apdoer
 */
@Data
@Table(name = "ms_broker_apikey")
public class MsBrokerApikeyPo {
    /**
     * 自增长id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Id
    private Integer userId;

    /**
     * API标示
     */
    private String keyName;

    /**
     * API 访问密钥
     */
    private String accessKey;

    /**
     * 签名的密钥
     */
    private String secretKey;

    /**
     * 是否可用(默认0可用，1不可用)
     */
    private Integer enabled;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 权限值list
     */
    @Transient
    private List<Integer> permList;

}