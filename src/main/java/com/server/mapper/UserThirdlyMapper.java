package com.server.mapper;

import com.core.util.Query;
import com.server.pojo.KunnrDo;
import com.server.pojo.ProductInfoDo;
import com.server.pojo.QualityCheckingDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description EXP数据实体类
 * @Author xg.chen
 * @Date 11:28 2020/3/19
 **/
@Repository
@Mapper
public interface UserThirdlyMapper {

    QualityCheckingDo thirdly_getQualityChecking(String batchNumber);

    Long thirdly_saveProductInfo(ProductInfoDo productInfoDo1);

    void thirdly_saveProductKunnrs(KunnrDo kunnrDo);

    List<ProductInfoDo> thirdly_getProductInfoDataList(Query query);

    int thirdly_getProductInfoData(Query query);
}
