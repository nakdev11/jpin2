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
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Integer key_topicId = Integer.parseInt(request.getParameter("topicId"));

		HttpSession session = request.getSession();
		List<Comment> comments = (List<Comment>) session.getAttribute("comments");
		List<Comment> filteredComments = new ArrayList<>();

		for (Comment c : comments) {
			if (c.getTopicId() == key_topicId) {
				filteredComments.add(c);
			}
		}

		session.setAttribute("filtered_comments", filteredComments);
		session.setAttribute("key_topicId", key_topicId);
		session.setAttribute("key_topic", filteredComments.get(0).getTopic());

		RequestDispatcher rd = request.getRequestDispatcher("comment_list.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<Comment> comments = (List<Comment>) session.getAttribute("comments");
		List<Comment> filteredComments = new ArrayList<>();

		Integer key_topicId = (Integer)session.getAttribute("key_topicId");
		String key_topic = (String)session.getAttribute("key_topic");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		Comment cmt = new Comment(key_topicId, key_topic, name, new Date(), comment, false);

		comments.add(cmt);

		for (Comment c : comments) {
			if (c.getTopicId() == key_topicId) {
				filteredComments.add(c);
			}
		}

		session.setAttribute("filtered_comments", filteredComments);
		session.setAttribute("key_topicId", key_topicId);
		session.setAttribute("key_topic", key_topic);

		RequestDispatcher rd = request.getRequestDispatcher("comment_list.jsp");
		rd.forward(request, response);

		return;

	}

}
