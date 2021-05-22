package com.sample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AuthorDao {

	@Insert("insert into author values (#{id}, #{name})")
	public void insert(Author author);

	@Update("update author set name = #{name} where id = #{id}")
	public void update(Author author);

	@Delete("delete from author where id = #{id}")
	public void delete(int id);

	@ResultMap("authorResult")
	@Select("select * from author")
	public List<Author> findAll();

	@ResultMap("authorResult")
	@Select("select * from author where id = #{id}")
	public Author findByPrimaryKey(int id);

	public Author findByPrimaryKey2(int id);
}
