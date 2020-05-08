package com.server.pojo;

import java.io.Serializable;

/**
 * @Description 经销商实体类
 * @Author xg.chen
 * @Date 15:29 2020/5/6
 **/

public class KunnrDo implements Serializable{

    public Long pId;
    public String kunnrId;
    public String kunnrName;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getKunnrId() {
        return kunnrId;
    }

    public void setKunnrId(String kunnrId) {
        this.kunnrId = kunnrId;
    }

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
    }
}
