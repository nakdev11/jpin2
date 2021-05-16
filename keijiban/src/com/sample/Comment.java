package com.sample;

import java.util.Date;

public class Comment {

	private int topicId;
	private String topic;
	private String name;
	private Date date;
	private String comment;
	private boolean rootFlg;

	public Comment(int topicId, String topic, String name, Date date, String comment, boolean rootFlg) {
		super();
		this.topicId = topicId;
		this.topic = topic;
		this.name = name;
		this.date = date;
		this.comment = comment;
		this.rootFlg = rootFlg;
	}

	public int getTopicId() {
		return topicId;
	}

	public String getTopic() {
		return topic;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public boolean isRootFlg() {
		return rootFlg;
	}

	@Override
	public String toString() {
		return "Comment [topicId=" + topicId + ", topic=" + topic + ", name=" + name + ", date=" + date + ", comment="
				+ comment + ", rootFlg=" + rootFlg + "]";
	}


}
