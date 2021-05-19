package com.sample.listener;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Application Lifecycle Listener implementation class ListenerApplication
 *
 */
@WebListener
public class ListenerApplication implements ServletContextListener {

    /**
     * Default constructor.
     */
    public ListenerApplication() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {

    	ServletContext application = sce.getServletContext();
    	SqlSession sqlsession = (SqlSession) application.getAttribute("sqlsession");
    	sqlsession.close();

    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {

		String resource = "config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession sqlsession = sqlSessionFactory.openSession();

		ServletContext application = sce.getServletContext();
		application.setAttribute("sqlsession", sqlsession);
    }

}
