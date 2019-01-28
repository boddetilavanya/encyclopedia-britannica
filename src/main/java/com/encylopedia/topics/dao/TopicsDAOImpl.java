package com.encylopedia.topics.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.encylopedia.topics.dto.TopicDTO;
import com.encylopedia.topics.exception.TopicsServiceException;

@Repository
@Component
public class TopicsDAOImpl implements TopicsDAO {

	private static final Logger logger = LoggerFactory.getLogger(TopicsDAOImpl.class);
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public TopicDTO getTopic(Integer topicId) throws TopicsServiceException {
		logger.info("Beginning of getTopic");
		TopicDTO topicDTO = null;
		Long startTime = System.currentTimeMillis();
		try {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("topicId", topicId);
			topicDTO = namedParameterJdbcTemplate.queryForObject(
					"select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where TOPIC_ID= :topicId", paramSource,
					new TopicRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("No record found while retrieving Topic details:{} {}", topicId, e);
			throw new TopicsServiceException("URL Not found", "E101", "Topic " + topicId + " not in DataBase");
		} catch (Exception e) {
			logger.error("Error retrieving Topic details {} {}", topicId, e);
			throw new TopicsServiceException("", "E102", e.getMessage());
		}
		logger.info("Time taken for getTopic is {}", (System.currentTimeMillis() - startTime));
		logger.info("End of getTopic");
		return topicDTO;
	}

	@Override
	public List<TopicDTO> getTopicsByClassName(String className) throws TopicsServiceException {
		logger.info("Beginning of getTopicsByClassName");
		List<TopicDTO> topicDTOList = null;
		Long startTime = System.currentTimeMillis();
		try {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("className", className);
			topicDTOList = namedParameterJdbcTemplate.query(
					"select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where CLASS_NAME= :className", paramSource,
					new TopicRowMapper());
		} catch (DataAccessException e) {
			logger.error("No record found while retrieving Topic details:{} {}", className, e);
			throw new TopicsServiceException("URL Not found", "E104", "Topics for the class " + className + " are not in DataBase");
		} catch (Exception e) {
			logger.error("Error retrieving topic details for the given className {} {}", className, e);
			throw new TopicsServiceException("", "E103", e.getMessage());
		}
		logger.info("Time taken for getTopicsByClassName is {}", (System.currentTimeMillis() - startTime));
		logger.info("End of getTopicsByClassName");
		return topicDTOList;
	}

	@Override
	public List<TopicDTO> getAllTopics() throws TopicsServiceException {
		logger.info("Beginning of getAllTopics");
		List<TopicDTO> topicDTOList = null;
		Long startTime = System.currentTimeMillis();
		try {

			topicDTOList = namedParameterJdbcTemplate.query("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC",
					new TopicRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("No record found while retrieving Topic details: {}", e);
			throw new TopicsServiceException("URL Not found", "E105", "Topic details are not in DataBase");
		} catch (Exception e) {
			logger.error("Error retrieving topic details {}", e);
			throw new TopicsServiceException("Error retrieving topic details", "E106", e.getMessage());
		}
		logger.info("Time taken for getAllTopics is {}", (System.currentTimeMillis() - startTime));
		logger.info("End of getAllTopics");
		return topicDTOList;
	}

}
