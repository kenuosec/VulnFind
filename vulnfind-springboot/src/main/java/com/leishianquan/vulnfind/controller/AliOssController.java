package com.leishianquan.vulnfind.controller;

import com.leishianquan.vulnfind.common.lang.Result;
import com.leishianquan.vulnfind.entity.User;
import com.leishianquan.vulnfind.service.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author txf
 * @Date 2021/3/1 3:10 下午
 * @Version 1.0
 */
@Api
@RestController
@CrossOrigin
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @PostMapping("/uploadImgFile")
    @ApiOperation(value = "上传头像")
    public Result upload(MultipartFile file){
        String s = aliOssService.upload(file);
        return Result.succ(s);
    }

    @PostMapping("/deleteImgFile")
    @ApiOperation(value = "删除旧头像")
    public Result deleteImgFile(String file){
        try {
            String[] split = file.split(".com/");
            System.out.println(split[1]);

            aliOssService.deleteFile(split[1]);
            return Result.succ(null);
        }catch (Exception e){
            return Result.fail(String.valueOf(e));
        }
    }



}
