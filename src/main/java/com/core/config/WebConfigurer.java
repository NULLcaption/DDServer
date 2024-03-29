package com.core.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description 虚拟化本地文件或者图片访问路径
 * @Author xg.chen
 * @Date 9:11 2019/12/3
 **/
@Component
@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    DdFileConfig ddFileConfig;
    /**
     * 虚拟化本地文件或者图片访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mImagesPath = ddFileConfig.getImagePath();
        if (mImagesPath.equals("") || mImagesPath.equals("${imagePath.imagesPath}")) {
            String imagesPath = WebConfigurer.class.getClassLoader().getResource("").getPath();
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
        }
        LoggerFactory.getLogger(WebConfigurer.class).info("imagesPath=" + mImagesPath);
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        super.addResourceHandlers(registry);
    }
}
