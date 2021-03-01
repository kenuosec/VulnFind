package com.leishianquan.vulnfind.mapper;

import com.leishianquan.vulnfind.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author txf
 * @since 2021-02-28
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> findDeptAndCount();
}
