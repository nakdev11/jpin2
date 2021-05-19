package com.sample;

import java.io.IOException;

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

		Integer taskId_key = Integer.parseInt(request.getParameter("taskId"));

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TaskDao dao = sqlsession.getMapper(TaskDao.class);

		Task task = dao.selectByTaskId(taskId_key);

		HttpSession session = request.getSession();
		session.setAttribute("task", task);
		session.setAttribute("taskId_key", taskId_key);

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
		Integer taskId_key = (Integer) session.getAttribute("taskId_key");

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TaskDao dao = sqlsession.getMapper(TaskDao.class);

		dao.delete(taskId_key);
		sqlsession.commit();

		response.sendRedirect("list");

		return;

	}

}
