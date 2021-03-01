package com.leishianquan.vulnfind.shiro;

import lombok.Data;

import java.io.Serializable;
//登录成功之后返回用户信息载体
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

//    private String avatar;

    private String email;

}
