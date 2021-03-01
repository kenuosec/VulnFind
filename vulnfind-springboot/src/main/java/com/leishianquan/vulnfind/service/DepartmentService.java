package com.leishianquan.vulnfind.service;

import com.leishianquan.vulnfind.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author txf
 * @since 2021-02-28
 */
public interface DepartmentService extends IService<Department> {
    List<Department> findDeptAndCount();
}
