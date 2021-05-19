package com.sample;

import java.util.List;

public interface CommentDao {

	public List<Comment> selectByTopicId(int key_topicId);

	public void insert(Comment comment);

}
