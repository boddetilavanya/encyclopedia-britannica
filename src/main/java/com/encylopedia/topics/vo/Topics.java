package com.encylopedia.topics.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Topics", description = " Holds multiple topic details.")
@XmlRootElement
public class Topics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8435772555032858575L;
	@ApiModelProperty(value = "List of topics", required = false)
	private List<UrlPublish> topics;

	public List<UrlPublish> getTopics() {
		return topics;
	}

	public void setTopics(List<UrlPublish> topics) {
		this.topics = topics;
	}

}
