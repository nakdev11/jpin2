package com.sample;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class CommentServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<Comment> comments = (List<Comment>) session.getAttribute("comments");
		if (comments == null) {
			comments = new ArrayList<>();
		}

		session.setAttribute("comments", comments);

		RequestDispatcher rd = request.getRequestDispatcher("/topic_list.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Integer topicId = (Integer)session.getAttribute("topicId");

		if (topicId == null) {
			topicId = 0;
		} else {
			topicId++;
		}

		String topic = request.getParameter("topic");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		Comment cmt = new Comment(topicId, topic, name, new Date(), comment, true);

		List<Comment> comments = (List<Comment>) session.getAttribute("comments");
		if (comments == null) {
			comments = new ArrayList<>();
		}

		comments.add(cmt);
		session.setAttribute("comments", comments);
		session.setAttribute("topicId", topicId);

		System.out.println("register: " + session.getAttribute("comments"));

		RequestDispatcher rd = request.getRequestDispatcher("/topic_list.jsp");
		rd.forward(request, response);

		return;

	}

}
