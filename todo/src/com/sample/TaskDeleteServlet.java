package com.sample;

import java.io.IOException;
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
@WebServlet("/delete")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * タスク削除画面 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<Integer, Task> tasks = (Map<Integer, Task>) session.getAttribute("tasks");

		Integer taskId_key = Integer.parseInt(request.getParameter("taskId"));

		Task task = tasks.get(taskId_key);
		session.setAttribute("task", task);

		RequestDispatcher rd = request.getRequestDispatcher("task_delete.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * タスク削除画面 削除ボタン押下
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Task task = (Task) session.getAttribute("task");

		Integer taskId = task.getTaskId();

		Map<Integer, Task> tasks = (Map<Integer, Task>) session.getAttribute("tasks");
		tasks.remove(taskId);

		session.setAttribute("tasks", tasks);

		response.sendRedirect("list");

		return;

	}

}
