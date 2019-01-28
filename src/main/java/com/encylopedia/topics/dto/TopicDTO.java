package com.encylopedia.topics.dto;

import java.io.Serializable;

public class TopicDTO implements Serializable{

	/**
		 * 
		 */
	private static final long serialVersionUID = -4606151760755110422L;
	private Integer topicId;
	private String urlClass;
	private String urlTitle;
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer i) {
		this.topicId = i;
	}
	public String getUrlClass() {
		return urlClass;
	}
	public void setUrlClass(String urlClass) {
		this.urlClass = urlClass;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
	
	

}
