package com.leishianquan.vulnfind.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.leishianquan.vulnfind.entity.OssEntity;
import com.leishianquan.vulnfind.service.AliOssService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @Author txf
 * @Date 2021/3/1 1:12 下午
 * @Version 1.0
 */
@Service
public class AliOssServiceImpl implements AliOssService, InitializingBean {
    @Autowired
    private OssEntity ossEntity;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 初始化bean进行操作
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        endpoint = ossEntity.getEndpoint();
        accessKeyId = ossEntity.getAccessKeyId();
        accessKeySecret = ossEntity.getAccessKeySecret();
        bucketName = ossEntity.getBucketName();
    }
    /**
     * 创建存储空间
     */
    @Override
    public void createBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //判断Bucket是否存在
        if (ossClient.doesBucketExist(bucketName)){
            throw new RuntimeException(bucketName + "Bucket列表中已经存在该Bucket");
        }
        // 创建存储空间。
        ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();

    }

    /**
     * 上传文件
     *
     * @param file
     */
    @Override
    public String upload(MultipartFile file) {
        String uploadUrl = null;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //判断Bucket是否存在
        if (!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }
        try {
            InputStream inputStream = file.getInputStream();

            //文件夹路径/2021/03/01/文件名
            String dataPath = new DateTime().toString("yyyy/MM/dd");

            //获取文件名称
            String original = file.getOriginalFilename();
            //UUID c994eec1-a194-4b1b-952e-d31ae78f8d0e
            String filename = UUID.randomUUID().toString().replace("-", "");

            //获取文件扩展名
            String fileType = original.substring(original.lastIndexOf("."));

            //拼接
            String newName = filename + fileType;

            //文件夹
            filename = dataPath + "/" + newName;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            //设置权限
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType(getContentType(fileType));

            ossClient.putObject(bucketName, filename, inputStream, objectMetadata);
            //关闭
            ossClient.shutdown();
            //不过期
            Date expiration = new Date(System.currentTimeMillis()+3600L * 1000 * 24 * 365 * 10);
            //url地址
            uploadUrl = ossClient.generatePresignedUrl(bucketName, filename, expiration).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadUrl.substring(0,uploadUrl.indexOf("?"));
    }



    /**
     * 下载文件
     *
     * @param fileName
     */
    @Override
    public void download(String fileName) throws IOException {
        String objectName = "txf.jpg";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 列举文件
     */
    @Override
    public void listFile() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
// objectListing.getObjectSummaries获取所有文件的描述信息。
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }
    /**
     * 删除文件
     *
     * @param fileName
     */
    @Override
    public void deleteFile(String fileName) {
        // <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }

        return "image/jpg";
    }

}
