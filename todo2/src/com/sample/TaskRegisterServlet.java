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

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TaskDao dao = sqlsession.getMapper(TaskDao.class);

		dao.insert(new Task(0, content, deadline, assignee, taskStatus));
		sqlsession.commit();

		List<Task> tasks = dao.selectAll();

		session.setAttribute("tasks", tasks);

		response.sendRedirect("list");

		sqlsession.clearCache();

		return;

	}

}
