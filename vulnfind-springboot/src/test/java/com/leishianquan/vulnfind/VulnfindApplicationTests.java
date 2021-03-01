package com.leishianquan.vulnfind;

import com.leishianquan.vulnfind.entity.OssEntity;
import com.leishianquan.vulnfind.service.AliOssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class VulnfindApplicationTests {

    @Autowired
    private OssEntity ossEntity;

    @Autowired
    private AliOssService aliOssService;

    @Test
    void contextLoads() {
        System.out.println(UUID.randomUUID());
    }

}
