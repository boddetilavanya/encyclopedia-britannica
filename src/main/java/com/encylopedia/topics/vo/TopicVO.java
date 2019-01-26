package com.encylopedia.topics.vo;

import java.io.Serializable;

public class TopicVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8764450758612709167L;
	private String topicId;
	private String urlClass;
	private String urlTitle;
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
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
