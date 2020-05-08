package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 扫码后截取的信息
 * @Author xg.chen
 * @Date 10:30 2020/5/6
 **/

public class CodeInfoDo implements Serializable {

    private String status;//类别: A/B/C
    private String date;//年月日
    private String factory;//工厂
    private String time;//字母（A/B/C…Z）+MMDD
    private String code;//流水码
    private String lineCode;//线别

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }
}
