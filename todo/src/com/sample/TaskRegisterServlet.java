package com.sample;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TaskRegisterServlet
 */
@WebServlet("/register")
public class TaskRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * タスク登録画面 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("task_register.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * タスク登録画面 登録ボタン押下
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Integer taskId_max = (Integer) session.getAttribute("taskId_max");
		if (taskId_max == null) {
			taskId_max = 0;
		}

		Integer taskId = taskId_max + 1;
		String content = request.getParameter("content");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date deadline = null;
		try {
			deadline = format.parse(request.getParameter("deadline"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String assignee = request.getParameter("assignee");
		String taskStatus = request.getParameter("taskStatus");

		Map<Integer, Task> tasks = (Map<Integer, Task>)session.getAttribute("tasks");
		if (tasks == null) {
			tasks = new TreeMap<>();
		}

		Task task = new Task(taskId, content, deadline, assignee, taskStatus);
		tasks.put(taskId, task);

		session.setAttribute("taskId_max", taskId);
		session.setAttribute("tasks", tasks);

		// forwardだと遷移先（タスク一覧）のURLがregisterのまま変わらず分かりづらいため、redirectにする。
//		RequestDispatcher rd = request.getRequestDispatcher("taskList");
//		rd.forward(request, response);

		response.sendRedirect("list");

		return;

	}

}
