package com.leishianquan.vulnfind.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.leishianquan.vulnfind.mapper")
public class MybatisPlusConfig {

    //PaginationInterceptor分页插件，提示弃用，但是新版本setUseDeprecatedExecutor也提示弃用，就不改了
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}

