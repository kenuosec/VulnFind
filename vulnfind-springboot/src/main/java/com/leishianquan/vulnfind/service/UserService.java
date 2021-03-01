package com.leishianquan.vulnfind.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leishianquan.vulnfind.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author txf
 * @since 2021-02-23
 */
public interface UserService extends IService<User> {
    IPage<User> findUserPage(Page<User> page, QueryWrapper<User> wrapper);
}
