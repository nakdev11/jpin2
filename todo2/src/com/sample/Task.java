package com.sample;

import java.util.Date;

public class Task {

	private int taskId;
	private String content;
	private Date deadline; // Date型にする
	private String assignee;
	private String taskStatus; // TaskStatus型にしたい

	public Task(int taskId, String content, Date deadline, String assignee, String taskStatus) {
		super();
		this.taskId = taskId;
		this.content = content;
		this.deadline = deadline;
		this.assignee = assignee;
		this.taskStatus = taskStatus;
	}

	public int getTaskId() {
		return taskId;
	}

	public String getContent() {
		return content;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", content=" + content + ", deadline=" + deadline + ", assignee=" + assignee
				+ ", taskStatus=" + taskStatus + "]";
	}

}
