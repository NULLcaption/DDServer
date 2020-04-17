package com.server.mapper;

import com.server.pojo.UserInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description 辅数据源数据持久层
 * @Author xg.chen
 * @Date 9:55 2019/5/16
 **/
@Repository
@Mapper
public interface UserSecondMapper {
    UserInfoDo secondary_getUserInfoByLoginId(String loginId);

    UserInfoDo secondary_getUserInfoByUserId(String userid);
}
