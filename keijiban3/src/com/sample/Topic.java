package com.sample;

import java.util.Date;

public class Topic {

	private int topicId;
	private String content;
	private String name;
	private Date date;

	public Topic(int topicId, String content, String name, Date date) {
		super();
		this.topicId = topicId;
		this.content = content;
		this.name = name;
		this.date = date;
	}

	public int getTopicId() {
		return topicId;
	}

	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", content=" + content + ", name=" + name + ", date=" + date + "]";
	}



}
