package com.encylopedia.topics.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UrlPublish", description = "Topic Details")
@XmlRootElement
public class UrlPublish implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8764450758612709167L;
	@ApiModelProperty(value = "Holds the id of a topic", required = false)
	private Integer topicId;
	@ApiModelProperty(value = "Holds the className of the topic, Example: Science, Social", required = false)
	private String urlClass;
	@ApiModelProperty(value = "Holds the title of a topic", required = false)
	private String urlTitle;
	@ApiModelProperty(value = "Holds the error details of a topic", required = false)
	private String error;
	@ApiModelProperty(value = "Holds the error cause of a topic", required = false)
	private String cause;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
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
