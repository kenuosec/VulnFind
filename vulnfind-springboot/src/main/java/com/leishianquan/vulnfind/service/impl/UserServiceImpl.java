package com.leishianquan.vulnfind.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leishianquan.vulnfind.common.lang.Result;
import com.leishianquan.vulnfind.entity.Department;
import com.leishianquan.vulnfind.entity.User;
import com.leishianquan.vulnfind.mapper.DepartmentMapper;
import com.leishianquan.vulnfind.mapper.UserMapper;
import com.leishianquan.vulnfind.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author txf
 * @since 2021-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public IPage<User> findUserPage(Page<User> page, QueryWrapper<User> wrapper) {
        return this.baseMapper.findUserPage(page,wrapper);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        //判断是否增加同一个用户
        String username = user.getUsername();
        //获取部门
        Long departmentId = user.getDepartmentId();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count!=0){
            throw new RuntimeException(String.valueOf(Result.fail("用户已存在")));
        }
        Department department = departmentMapper.selectById(departmentId);
        if (department==null){
            throw new RuntimeException(String.valueOf(Result.fail("部门不存在")));
        }
        //设置用户密码
        user.setPassword(SecureUtil.md5(user.getPassword()));
        //设置用户状态
        user.setStatus(1);
        //设置用户邮箱
        user.setEmail(user.getEmail());

        //user.setCreated(new Date());


        this.baseMapper.insert(user);
    }
}
