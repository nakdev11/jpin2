package com.sample;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface EmpDao {

	@Select("select * from employee")
	@ResultMap("empResult")
	public List<Employee> findAll();

	@ResultMap("empResult")
	@Select("select * from employee where empno = #{empno}")
	public Employee findByPrimaryKey(int empno);
}
