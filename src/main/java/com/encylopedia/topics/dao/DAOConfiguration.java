package com.encylopedia.topics.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig.PoolConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!default")
public class DAOConfiguration extends AbstractCloudConfig{

	private static final Logger logger=LoggerFactory.getLogger(DAOConfiguration.class);
			
			public DataSource createDataSource() throws Exception{
		logger.info("Beginning of createDataSource");
		DataSource serviceDataSource=null;
		PoolConfig poolConfig=new PoolConfig(1,2,2); //minConnectionPool,MaxconnectionPool, MaxWaittime
		DataSourceConfig dbConfig=new DataSourceConfig(poolConfig,null);
		serviceDataSource=cloud().getServiceConnector("TOPIC_DB_DETAILS", DataSource.class, dbConfig);
		
		logger.info("End of createDataSource");
		return serviceDataSource;
	}

}
