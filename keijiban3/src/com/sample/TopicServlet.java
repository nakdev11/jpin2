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
 * トピック一覧
 */
@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * トピック一覧 初期表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		以下の処理をアプリケーションリスナーへ移動
//		String resource = "config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession session = sqlSessionFactory.openSession();

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TopicDao dao = sqlsession.getMapper(TopicDao.class);
		List<Topic> topicList = dao.findAll();

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

		ServletContext application = this.getServletContext();
		SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
		TopicDao dao = sqlsession.getMapper(TopicDao.class);

		dao.insert(topic);
		sqlsession.commit();

//		アプリケーションリスナーへ移動
//		sqlsession.close();

		response.sendRedirect("topic");

		return;

	}

}
