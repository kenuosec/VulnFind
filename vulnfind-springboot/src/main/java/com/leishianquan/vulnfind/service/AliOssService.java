package com.leishianquan.vulnfind.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author txf
 * @Date 2021/2/28 11:50 下午
 * @Version 1.0
 */

public interface AliOssService {

    /**
     * 创建存储空间
     */
    void createBucket();


    /**
     * 上传文件
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     */
    void download(String fileName) throws IOException;

    /**
     * 列举文件
     */
    void listFile();

    /**
     * 删除文件
     */
    void deleteFile(String filename);



}
