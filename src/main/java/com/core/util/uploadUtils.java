package com.core.util;

import com.core.config.Constant;
import com.core.config.DdFileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Description 上传文件工具类
 * @Author xg.chen
 * @Date 10:26 2019/4/26
 **/

public class uploadUtils {

    //图片上传路径
    private static String uploadPath = Constant.U_PRO;

    /**
     * 上传文件
     * @param request
     * @param id
     * @param index
     * @return
     */
    public static String uploadImageFiles(HttpServletRequest request, String id, String index) {
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //图片处理
        if (multipartFile == null || id == null ) {
            return "error_1";
        }
        if (multipartFile.getSize() > 10 * 1024 * 1024) {
            return "error_2";
        }
        String realPath = uploadPath;
        String imageUrl;
        try {
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            long time = System.currentTimeMillis();
            imageUrl  = "/files/"+id+"_"+index+"_"+ String.valueOf(time)+".jpg";
            File file  =  new File(realPath,id+"_"+index+"_"+ String.valueOf(time)+".jpg");
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "error";
        }
        return imageUrl;
    }
}
