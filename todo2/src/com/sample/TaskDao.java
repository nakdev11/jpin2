package com.sample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TaskDao {

	public Task selectByTaskId(int taskId);

	public List<Task> selectByTaskStatus(String taskStatus);

	public List<Task> selectAll();

	public void insert(Task task);

	public void update(Task task);

	// 一括完了
	// ### Error updating database.
	// Cause: org.apache.ibatis.binding.BindingException:
	// Parameter 'taskStatus' not found. Available parameters are [arg1, arg0, param1, param2]
	// 上記Exception発生。下記サイトを参考にし、メソッドの引数に@Paramを追記して解決
	// 参照 https://teratail.com/questions/289538
	// 参照 https://qiita.com/5zm/items/0864d6641c65f976d415
	public void updateTaskStatus(@Param("taskId") int taskId, @Param("taskStatus") String taskStatus);

	public void delete(int taskId);


}
