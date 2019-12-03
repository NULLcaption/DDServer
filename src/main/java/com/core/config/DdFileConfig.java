package com.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 文件路径设置
 * @Author xg.chen
 * @Date 13:27 2019/11/29
 **/
@Component
public class DdFileConfig {
    //上传路径
    @Value("${dd.uploadPath}")
    private String uploadPath;
    //图片路径
    @Value("${dd.imagePath}")
    private String imagePath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "DdFileConfig{" +
                "uploadPath='" + uploadPath + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}