package com;

import com.core.config.Constant;
import com.core.util.AccessTokenUtil;
import com.server.pojo.PropertyInfo;
import com.server.pojo.UserInfoDo;
import com.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void getUserInfoList() {
//		String deptId = "71";
//		List<UserInfoDo> userInfoDoList = userService.primary_getUserInfoList(deptId);
        String loginId = "cf.xiong";
        UserInfoDo userInfoDo = userService.primary_getUserInfoByLoginId(loginId);
        if (userInfoDo != null) {
//				UserInfoDo userInfoDo1 = userService.secondary_getUserInfoByLoginId(userInfoDo.getUserid());
            String string = AccessTokenUtil.sendDDMessage(Constant.AGENT_ID, userInfoDo.getUserid(), Constant.MESSAGE_URL);
            System.out.println("[sendDDMessage]:" + string);
        }
    }

    @Test
    public void getUserInfo() {
        //获取数据
        String loginId = "xg.chen";
        UserInfoDo userInfoDo = userService.primary_getUserInfoByLoginId(loginId);

        String id = userInfoDo.getUserid();
        String name = userInfoDo.getName();

        System.out.println("[primary user info data] :userId=" + id + " ; name=" + name);

    }

    @Test
    public void getUserInfoS() {
        //获取数据
        String loginId = "xg.chen";
        UserInfoDo userInfoDo = userService.secondary_getUserInfoByLoginId(loginId);

        String id = userInfoDo.getUserid();
        String name = userInfoDo.getName();
        String outsysuserid = userInfoDo.getOutsysuserid();

        System.out.println("[primary user info data] :userId=" + id + " ; name=" + name + "; outsysuserid=" + outsysuserid);

    }

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

}
