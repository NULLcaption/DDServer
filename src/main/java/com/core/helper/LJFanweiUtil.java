package com.core.helper;

import java.net.URL;
import org.codehaus.xfire.client.Client;

/**
 * @Description 请求泛微生成的webservice接口工具
 * @Author xg.chen
 * @Date 15:43 2020/4/17
**/

public class LJFanweiUtil {

    /**
     * @Description 插入OA数据后数据重构
     * @Author xg.chen
     * @Date 15:45 2020/4/17
    **/
    public static String HttpFWWebService(int in2){
        try {
            Client client = new Client(new URL("http://10.100.0.6:8101/services/ModeRefactorService?wsdl"));
            Object[] result = client.invoke("init", new Object[]{1, 1241, in2});
            result[0].toString();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
