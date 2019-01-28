package com.encylopedia.topics.dao;

import java.util.List;

import com.encylopedia.topics.dto.TopicDTO;
import com.encylopedia.topics.exception.TopicsServiceException;

public interface TopicsDAO {

	TopicDTO getTopic(Integer topicId) throws TopicsServiceException;

	List<TopicDTO> getTopicsByClassName(String className) throws TopicsServiceException;

	List<TopicDTO> getAllTopics() throws TopicsServiceException;

}
