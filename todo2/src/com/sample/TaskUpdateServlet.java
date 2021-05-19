package com.sample;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

/**
 * Servlet implementation class TaskUpdateServlet
 */
@WebServlet("/update")
public class TaskUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * タスク修正画面 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer taskId_key = Integer.parseInt(request.getParameter("taskId"));

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TaskDao dao = sqlsession.getMapper(TaskDao.class);

		Task task = dao.selectByTaskId(taskId_key);

		HttpSession session = request.getSession();
		session.setAttribute("task", task);

		RequestDispatcher rd = request.getRequestDispatcher("task_update.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * タスク修正画面 修正ボタン押下
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Task task = (Task) session.getAttribute("task");

		Integer taskId = task.getTaskId();
		String content = request.getParameter("content");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date deadline = null;
		try {
			deadline = format.parse(request.getParameter("deadline"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String assignee = request.getParameter("assignee");
		String taskStatus = request.getParameter("taskStatus");

		Task updateTask = new Task(taskId, content, deadline, assignee, taskStatus);

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TaskDao dao = sqlsession.getMapper(TaskDao.class);

		dao.update(updateTask);
		sqlsession.commit();

		List<Task> tasks = dao.selectAll();
		session.setAttribute("tasks", tasks);

		response.sendRedirect("list");

		return;

	}

}
