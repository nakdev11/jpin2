package com.sample;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * トピック一覧
 */
@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * トピック一覧 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TopicDao topicDao = new TopicDao();
		List<Topic> topicList = topicDao.findAll();

		HttpSession session = request.getSession();
		session.setAttribute("topicList", topicList);

		RequestDispatcher rd = request.getRequestDispatcher("/topic_list.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * トピック一覧 トピック登録
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String content = request.getParameter("content");
		String name = request.getParameter("name");
		Topic topic = new Topic(0, content, name, new Date());

		TopicDao topicDao = new TopicDao();
		topicDao.insert(topic);

		response.sendRedirect("topic");

		return;

	}

}
