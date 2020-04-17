package com.server.mapper;

import com.server.pojo.QualityCheckingDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description EXP数据实体类
 * @Author xg.chen
 * @Date 11:28 2020/3/19
 **/
@Repository
@Mapper
public interface UserThirdlyMapper {

    QualityCheckingDo thirdly_getQualityChecking(String batchNumber);
}
