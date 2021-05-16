package com.sample;

import java.io.IOException;
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
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/list")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * タスク一覧 初期表示/検索
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<Integer, Task> tasks = (Map<Integer, Task>)session.getAttribute("tasks");
		if (tasks == null) {
			tasks = new TreeMap<>();
		}

		// debug
		System.out.println(tasks);

		// 検索条件がある場合、タスクを抽出する。
		String searchCondition = request.getParameter("searchCondition");
		if (searchCondition == null) {
			searchCondition = "全て";
		}
		session.setAttribute("searchCondition", searchCondition);

		RequestDispatcher rd = request.getRequestDispatcher("task_list.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * タスク一覧 一括完了押下
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<Integer, Task> tasks = (Map<Integer, Task>)session.getAttribute("tasks");
		if (tasks == null) {
			tasks = new TreeMap<>();
		}

//		Integer taskId_max = (Integer)session.getAttribute("taskId_max");

		String[] taskIds = request.getParameterValues("taskIds_finished");

		for (String i : taskIds) {
			Integer taskId = Integer.parseInt(i);
			Task task = tasks.get(taskId);
			task.setTaskStatus("済み");
		}

		session.setAttribute("tasks", tasks);

		response.sendRedirect("list");

		return;

	}

}
