package com.cobble.takeaway.util;

import java.io.File;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationUtil {
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtil.class);
	private static PropertiesConfiguration propertiesConfig = null;
	public static synchronized PropertiesConfiguration getPropertiesConfig() {
		if (propertiesConfig == null) {
			Configurations configs = new Configurations();
			String resource = "takeaway.properties";
			File file = null;
			try {
				file = Resources.getResourceAsFile(resource);
				propertiesConfig = configs.properties(file);
			} catch (Exception e) {
				logger.error("Read resource: {} exception", resource, e);
			}
		}
		return propertiesConfig;
	}
}
