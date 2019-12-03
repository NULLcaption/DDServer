package com.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

/**
 * @ClassName: SAPConnectionPool
 * @Description: SAP连接
 * @author b.peng
 * @date 2018年10月16日 上午11:34:01
 */
@Service
public class SAPConnectionPool {
	
	private static Logger logger = LoggerFactory.getLogger(SAPConnectionPool.class);
	
	private String ABAP_AS = "ABAP_AS_WITH_POOL";
    
    @Value("${jco.client.ashost}")
    private String jco_ashost;
    
    @Value("${jco.client.client}")
    private String jco_client;
    
    @Value("${jco.client.user}")
    private String jco_user;
    
    @Value("${jco.client.passwd}")
    private String jco_passwd;
    
    @Value("${jco.client.sysnr:00}")
    private String jco_sysnr;
    
    @Value("${jco.client.lang:en}")
    private String jco_lang;
    
    @Value("${jco.destination.pool_capacity:30}")
    private String jco_pool_capacity;
    
    @Value("${jco.destination.peak_limit:150}")
    private String jco_peak_limit;

    @Bean("sapDestination")
	public JCoDestination sapDestination(){
		try {
	        createDestinationDataFile(ABAP_AS, connectProperties());
			logger.debug("+++++++++++++>:Start connection...");
			System.setProperty("java.library.path", "folder_path_of_.so_file");
			JCoDestination dest = JCoDestinationManager.getDestination(ABAP_AS);
			return dest;
		} catch (JCoException ex) {
			return RegetJCoDestination();
		}
	}
    
    /**
     * @Title: tableToList
     * @Description: JCoTable转list
     * @param table
     * @return
     * List<Map<String,String>>    返回类型
     * @throws
     */
    public static List<Map<String, String>> tableToList(JCoTable table) {
    	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    	do {
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<JCoField> it = table.iterator(); it.hasNext();) {
				JCoField field = it.next();
				map.put(field.getName(), field.getString());
			}
			result.add(map);
		} while (table.nextRow());
    	return result;
    }
    
    /**
     * SAP连接配置
     */
    private Properties connectProperties() {
    	Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, jco_ashost);//IP
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, jco_client);//客户端编号
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, jco_user);//用户名
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, jco_passwd);//密码
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, jco_sysnr);//系统编号
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, jco_lang);//语言
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, jco_pool_capacity);//最大空闲连接数
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, jco_peak_limit);//最大活动连接数
        return connectProperties;
    }
    
    /**
     * 创建配置连接文件
     */
    private void createDestinationDataFile(String destinationName, Properties connectProperties) {
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
	 * 重新获取JCODestination
	 */
    private JCoDestination RegetJCoDestination(){
		try{
			CustomDestinationDataProvider provider = new CustomDestinationDataProvider();
			provider.addDestinationProperties(ABAP_AS, connectProperties());
			Environment.registerDestinationDataProvider(provider);
			try {
				JCoDestination dest = JCoDestinationManager.getDestination(ABAP_AS);
				return dest;
			} catch (JCoException ex) {
				ex.printStackTrace();
				logger.info("重新连接失败");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
