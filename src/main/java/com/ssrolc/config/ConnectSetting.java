package com.ssrolc.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;


public class ConnectSetting {
	private static final Logger logger = LoggerFactory.getLogger(ConnectSetting.class);
	
	static private ConnectSetting connectSetting = new ConnectSetting();
	
	static public String mysqlDriverClassName;
	static public String mysqlUrl;
	static public String mysqlUsername;
	static public String mysqlPassword;
	static public String mssqlDriverClassName;
	static public String mssqlUrl;
	static public String mssqlUsername;
	static public String mssqlPassword;
	
	private ConnectSetting() {
		super();
		ConfigurableApplicationContext context = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = context.getEnvironment();
		String [] aa = env.getActiveProfiles();
		for (String dd : aa) {
			logger.debug("==========="+dd);
		}
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("application.yml"));
		Properties proper = yaml.getObject();
		mysqlDriverClassName = proper.getProperty("db.mysqlDriverClassName");
		mysqlUrl = proper.getProperty("db.mysqlUrl");
		mysqlUsername = proper.getProperty("db.mysqlUsername");
		mysqlPassword = proper.getProperty("db.mysqlPassword");
		
		mssqlDriverClassName= proper.getProperty("db.mssqlDriverClassName");
		mssqlUrl= proper.getProperty("db.mssqlUrl");
		mssqlUsername= proper.getProperty("db.mssqlUsername");
		mssqlPassword= proper.getProperty("db.mssqlPassword");
	}
	
	public static ConnectSetting getInstance(){
        return connectSetting;
    }
}