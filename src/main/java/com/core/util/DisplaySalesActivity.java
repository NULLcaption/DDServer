package com.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * @ClassName: DisplaySalesActivity
 * @Description: 用于SAP测试类
 * @author b.peng
 * @date 2018年10月16日 下午3:37:42
 */
public class DisplaySalesActivity {

	static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";
    static String DESTINATION_NAME2 = "ABAP_AS_WITH_POOL";
    
	//配置连接
    static {
    	Properties connectProperties = new Properties();
    	connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.0.16");//IP
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "700");//客户端编号
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, "RFC");//用户名
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "poiuyt");//密码
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");//系统编号
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "en");//语言
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "30");//最大空闲连接数
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "150");//最大活动连接数
        createDestinationDataFile(DESTINATION_NAME2, connectProperties);
    }
    
    /**
     * 创建配置连接文件
     */
    static void createDestinationDataFile(String destinationName, Properties connectProperties) {
    	File destCfg = new File(destinationName + ".jcoDestination");
    	try {
    		FileOutputStream fos = new FileOutputStream(destCfg, false);
    		connectProperties.store(fos, "For tests only !");
    		fos.close();
    	} catch (Exception e) {
    		throw new RuntimeException("Unable to create the destination files", e);
    	}
    }
    
    /**
     * 获取连接
     */
    public static JCoDestination getSAPDestination() throws JCoException {
    	JCoDestination destination = JCoDestinationManager.getDestination(DESTINATION_NAME2);
    	return destination;
    }
}
