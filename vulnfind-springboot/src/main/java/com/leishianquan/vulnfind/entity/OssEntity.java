package com.leishianquan.vulnfind.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author txf
 * @Date 2021/2/28 11:43 下午
 * @Version 1.0
 */

@ConfigurationProperties(prefix = "alioss")
@Component
@Data
public class OssEntity {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

}
