package com.yangtao.bxbserver.config;


import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuOSSConfig {

    private String accessKey = "4zTOff-GiRg1ADHfHf3i5bOfxIUAGbG3jNuP-HdF";
    private String secretKey = "ApNdEAL-Kni8tCE8T2c5-b5H7VuHvUhwGSZUhALg";



    private com.qiniu.storage.Configuration qnConfig() {
        return new com.qiniu.storage.Configuration(Region.huanan());// 华南机房
    }

    /*
     构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qnConfig());
    }

    /*
     认证信息实例
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /*
     构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qnConfig());
    }

}
