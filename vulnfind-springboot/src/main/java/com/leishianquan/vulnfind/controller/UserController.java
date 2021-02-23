package com.leishianquan.vulnfind.controller;


import com.leishianquan.vulnfind.common.lang.Result;
import com.leishianquan.vulnfind.entity.User;
import com.leishianquan.vulnfind.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author txf
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/{id}")
//    public Object test(@PathVariable("id") Long id) {
//        return userService.getById(id);
//    }
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }
}
