package com.sample;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {

	public static void main(String[] args) throws Exception {

		String resource = "config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		AuthorDao dao = session.getMapper(AuthorDao.class);

		Author author = dao.findByPrimaryKey2(1);
		for (Book b : author.getBookList()) {
			System.out.print(author.getName() + ": ");
			System.out.println(b.getTitle());
		}

		session.commit();
		session.close();

	}

}
