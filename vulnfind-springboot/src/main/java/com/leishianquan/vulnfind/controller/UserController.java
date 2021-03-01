package com.leishianquan.vulnfind.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leishianquan.vulnfind.common.lang.Result;
import com.leishianquan.vulnfind.entity.User;
import com.leishianquan.vulnfind.entity.VO.UserVO;
import com.leishianquan.vulnfind.service.AliOssService;
import com.leishianquan.vulnfind.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@Api(value = "系统用户模版")
public class UserController {
    @Autowired
    UserService userService;



//    @RequiresAuthentication
    @GetMapping("/select")
    @ApiOperation(value = "查询所有用户信息")
    public Result selectUsers() {
        List<User> list = userService.list();
        return Result.succ(list);
    }

//    @RequiresAuthentication
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个用户信息")
    public Result getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return Result.succ(user);
        }else {
            return Result.fail("用户不存在");
        }
    }

//    @RequiresAuthentication
    @GetMapping("/findUserList")
    @ApiOperation(value = "分页查询")
    public Result findUserList(@RequestParam(defaultValue = "1")Integer current,
                               @RequestParam(defaultValue = "3")Integer size){
        Page page = new Page(current,size);
        IPage userPage = userService.page(page);
        return Result.succ(userPage);
    }

//    @RequiresAuthentication
    @PostMapping("/findUserPage")
    @ApiOperation(value = "分页查询用户")
    public Result findUserList(@RequestParam(defaultValue = "1")Integer current,
                               @RequestParam(defaultValue = "3")Integer size,
                               @RequestBody UserVO userVO){
        Page page = new Page(current,size);
        QueryWrapper<User> wrapper = getWrapper(userVO);
        IPage userPage = userService.findUserPage(page,wrapper);
        return Result.succ(userPage);
    }

    private QueryWrapper<User> getWrapper(UserVO userVO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userVO!=null){
            if(StringUtils.hasText(String.valueOf(userVO.getDepartmentId()))){
                queryWrapper.eq("department_id",userVO.getDepartmentId());
            }
            if(StringUtils.hasText(userVO.getUsername())){
                queryWrapper.eq("username",userVO.getUsername());
            }
            if(StringUtils.hasText(userVO.getEmail())){
                queryWrapper.eq("email",userVO.getEmail());
            }
        }
        return queryWrapper;
    }


    @PostMapping("/save")
    @ApiOperation(value = "保存")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }


    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户")
    public Result addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return Result.succ(null);
        }catch (Exception e){
            return Result.fail(null);
        }
    }
}


