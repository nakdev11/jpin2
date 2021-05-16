package com.sample;

import java.util.Date;

public class Comment {

	private int commentId;
	private int topicId;
	private String comment;
	private String name;
	private Date date;

	public Comment(int commentId, int topicId, String comment, String name, Date date) {
		this.commentId = commentId;
		this.topicId = topicId;
		this.comment = comment;
		this.name = name;
		this.date = date;
	}

	public int getTopicId() {
		return topicId;
	}

	public int getCommentId() {
		return commentId;
	}

	public String getComment() {
		return comment;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Comment [topicId=" + topicId + ", commentId=" + commentId + ", comment=" + comment + ", name=" + name
				+ ", date=" + date + "]";
	}

}
