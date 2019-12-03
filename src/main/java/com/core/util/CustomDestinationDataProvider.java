package com.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * @ClassName: CustomDestinationDataProvider
 * @Description: 连接提供商
 * @author b.peng
 * @date 2018年10月16日 上午11:39:11
 */
public class CustomDestinationDataProvider implements DestinationDataProvider {

	private Map<String, Object> providers = new HashMap<String, Object>();

	@Override
	public Properties getDestinationProperties(String destName) {
		if (destName == null)
			throw new NullPointerException("请指定目的名称");
		if (providers.size() == 0)
			throw new IllegalStateException("请加入一个目的连接参数属性给提供者");
		return (Properties) providers.get(destName);
	}

	@Override
	public void setDestinationDataEventListener(
			DestinationDataEventListener arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}
	
	public void addDestinationProperties(String destName, Properties provider) {
		providers.put(destName, provider);
	}
}
