package com.encylopedia.topics.service;

import com.encylopedia.topics.exception.TopicsServiceException;
import com.encylopedia.topics.vo.UrlPublish;
import com.encylopedia.topics.vo.Topics;

public interface TopicsService {

	UrlPublish getTopic(Integer topicId) throws TopicsServiceException;

	Topics getTopicsByClassName(String className) throws TopicsServiceException;

	Topics getAllTopics() throws TopicsServiceException;

}
