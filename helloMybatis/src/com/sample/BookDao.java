package com.sample;

public interface BookDao {

	public void insert(Book book);

	public Book findByPrimaryKey(int id);

	public Book findByPrimaryKey2(int id);

}