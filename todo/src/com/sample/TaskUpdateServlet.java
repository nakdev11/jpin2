package com.sample;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		HttpSession session = request.getSession();
		Map<Integer, Task> tasks = (Map<Integer, Task>) session.getAttribute("tasks");

		Integer taskId_key = Integer.parseInt(request.getParameter("taskId"));

		Task task = tasks.get(taskId_key);
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

		Map<Integer, Task> tasks = (Map<Integer, Task>) session.getAttribute("tasks");
		tasks.replace(taskId, updateTask);

		session.setAttribute("tasks", tasks);

		response.sendRedirect("list");

		return;

	}

}
