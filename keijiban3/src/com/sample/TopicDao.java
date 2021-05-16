package com.sample;

import java.util.List;

public interface TopicDao {

	public List<Topic> findAll();

	public void insert(Topic topic);
}
