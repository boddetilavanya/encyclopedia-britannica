package com.encylopedia.topics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.encylopedia.topics.dao.TopicsDAO;
import com.encylopedia.topics.dto.TopicDTO;
import com.encylopedia.topics.exception.TopicsServiceException;
import com.encylopedia.topics.vo.UrlPublish;
import com.encylopedia.topics.vo.Topics;

@Component
public class TopicsServiceImpl implements TopicsService {

	@Autowired
	TopicsDAO topicsDAO;

	@Override
	public UrlPublish getTopic(Integer topicId) throws TopicsServiceException {
		TopicDTO topicDTO = new TopicDTO();
		UrlPublish topicVO = new UrlPublish();
		try {
			topicDTO = topicsDAO.getTopic(topicId);

			if (topicDTO != null) {
				topicVO.setTopicId(topicId);
				topicVO.setUrlClass(topicDTO.getUrlClass());
				topicVO.setUrlTitle(topicDTO.getUrlTitle());

			}
		} catch (TopicsServiceException e) {
			if (e.getExceptionCode().equalsIgnoreCase("E101")) {
				topicVO.setTopicId(topicId);
				topicVO.setError(e.getExceptionType());
				topicVO.setCause(e.getMessage());
			} else
				throw e;
		}
		return topicVO;
	}

	@Override
	public Topics getTopicsByClassName(String className) throws TopicsServiceException {
		Topics topics = new Topics();
		List<UrlPublish> urlPublishList=new ArrayList<>();
		List<TopicDTO> topicDTOList = new ArrayList<>();
		topicDTOList = topicsDAO.getTopicsByClassName(className);
		UrlPublish topicVO = null;
		if (topicDTOList != null && !topicDTOList.isEmpty()) {

			for (TopicDTO topic : topicDTOList) {
				topicVO = new UrlPublish();
				topicVO.setTopicId(topic.getTopicId());
				topicVO.setUrlClass(topic.getUrlClass());
				topicVO.setUrlTitle(topic.getUrlTitle());
				urlPublishList.add(topicVO);
			}
			topics.setTopics(urlPublishList);
		}
		
		return topics;
	}

	@Override
	public Topics getAllTopics() throws TopicsServiceException {
		Topics topics = new Topics();
		List<TopicDTO> topicDTOList = new ArrayList<>();
		topicDTOList = topicsDAO.getAllTopics();
		List<UrlPublish> urlPublishList=new ArrayList<>();
		UrlPublish topicVO = null;
		if (topicDTOList != null && !topicDTOList.isEmpty()) {

			for (TopicDTO topic : topicDTOList) {
				topicVO = new UrlPublish();
				topicVO.setTopicId(topic.getTopicId());
				topicVO.setUrlClass(topic.getUrlClass());
				topicVO.setUrlTitle(topic.getUrlTitle());
				urlPublishList.add(topicVO);
			}
			topics.setTopics(urlPublishList);

		}
		return topics;
	}

}
