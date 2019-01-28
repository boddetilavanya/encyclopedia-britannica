package com.encylopedia.topics.dto;

import java.io.Serializable;
import java.util.List;

public class TopicsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8435772555032858575L;
	private List<TopicDTO> topics;

	public List<TopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicDTO> topics) {
		this.topics = topics;
	}

}
