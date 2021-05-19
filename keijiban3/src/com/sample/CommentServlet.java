package com.sample;

import java.io.IOException;
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
	 * コメント一覧画面 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// topic一覧から当画面へ遷移してきた場合、URLパラメータからtopicIdを取得し、
		// comment登録から当画面へ遷移してきた場合、sessionからtopicIdを取得する。
		Integer key_topicId = null;
		if (request.getParameter("topicId") != null) {
			key_topicId = Integer.parseInt(request.getParameter("topicId"));
		} else {
			key_topicId = (Integer)session.getAttribute("key_topicId");
		}

		String topicContent = null;
		if (request.getParameter("topicContent") != null) {
			topicContent = request.getParameter("topicContent");
		} else {
			topicContent = (String)session.getAttribute("topicContent");
		}

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		CommentDao dao = sqlsession.getMapper(CommentDao.class);

		List<Comment> commentList = dao.selectByTopicId(key_topicId);

		session.setAttribute("key_topicId", key_topicId);
		session.setAttribute("topicContent", topicContent);
		session.setAttribute("commentList", commentList);

		RequestDispatcher rd = request.getRequestDispatcher("comment_list.jsp");
		rd.forward(request, response);

		return;

	}

	/**
	 * コメント登録画面　登録ボタン押下
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Integer key_topicId = (Integer)session.getAttribute("key_topicId");
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");

		Comment cmt = new Comment(0, key_topicId, comment, name, new Date());

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		CommentDao dao = sqlsession.getMapper(CommentDao.class);

		dao.insert(cmt);
		sqlsession.commit();

		response.sendRedirect("comment");

		return;

	}

}
