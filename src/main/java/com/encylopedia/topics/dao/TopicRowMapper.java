package com.encylopedia.topics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.encylopedia.topics.dto.TopicDTO;

public class TopicRowMapper implements RowMapper<TopicDTO> {

	@Override
	public TopicDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopicDTO topicDTORowMapper = new TopicDTO();
		topicDTORowMapper.setTopicId(rs.getInt("TOPIC_ID"));
		topicDTORowMapper.setUrlClass(rs.getString("CLASS_NAME"));
		topicDTORowMapper.setUrlTitle(rs.getString("TITLE"));
		return topicDTORowMapper;
	}

}
