package com.encylopedia.topics.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Profile("!default")
public class DAOTemplateConfig extends AbstractCloudConfig {

	@Autowired
	DAOConfiguration daoConfig;
	private static final Logger logger = LoggerFactory.getLogger(DAOTemplateConfig.class);
@Bean
@Qualifier("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws Exception {
		logger.info("Beginning of namedParameterJdbcTemplate");
		
		return new NamedParameterJdbcTemplate(daoConfig.createDataSource());
	}

}
