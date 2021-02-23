package com.leishianquan.vulnfind.service.impl;

import com.leishianquan.vulnfind.entity.User;
import com.leishianquan.vulnfind.mapper.UserMapper;
import com.leishianquan.vulnfind.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
