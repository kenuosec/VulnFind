package com.leishianquan.vulnfind.controller;


import com.leishianquan.vulnfind.common.lang.Result;
import com.leishianquan.vulnfind.entity.Department;
import com.leishianquan.vulnfind.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author txf
 * @since 2021-02-28
 */
@Api(value = "部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数")
    @GetMapping("/findDeptAndCount")
    public Result findDeptAndCount(){
        List<Department> departments = departmentService.findDeptAndCount();
//        System.out.println(departments);
        if (departments.size()==0){
            return Result.fail("部门不存在");
        }
        return Result.succ(departments);
    }
}
