package com.leishianquan.vulnfind.mapper;

import com.leishianquan.vulnfind.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author txf
 * @since 2021-02-28
 */
@Service
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> findDeptAndCount();
}
