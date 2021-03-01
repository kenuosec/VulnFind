package com.leishianquan.vulnfind.service.impl;

import com.leishianquan.vulnfind.entity.Department;
import com.leishianquan.vulnfind.mapper.DepartmentMapper;
import com.leishianquan.vulnfind.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author txf
 * @since 2021-02-28
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {


    @Override
    public List<Department> findDeptAndCount() {
        return this.baseMapper.findDeptAndCount();
    }
}
