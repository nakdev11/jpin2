package com.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicDao {

	// DB接続情報
	private final String JDBC_URL = "jdbc:mysql://localhost/keijiban";
	private final String DB_USER = "root";
	private final String DB_PASS = "rootroot";

	public List<Topic> findAll() {
		List<Topic> topicList = new ArrayList<>();

		// No suitable driver found for jdbc:mysql://localhost/keijiban の対処
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// sql作成
			String sql = "select topicId, content, name, date from topic order by topicId";
			PreparedStatement ps = conn.prepareStatement(sql);

			// sql実行
			ResultSet rs = ps.executeQuery();

			// sql結果をListへ詰める
			while(rs.next()) {
				int topicId = rs.getInt("topicId");
				String content = rs.getString("content");
				String name = rs.getString("name");

				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = fmt.parse(rs.getString("date"));
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				Topic topic = new Topic(topicId, content, name, date);
				topicList.add(topic);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return topicList;

	}

	public boolean insert(Topic topic) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// sql作成
			String sql = "insert into topic (content, name, date) values (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, topic.getContent());
			ps.setString(2, topic.getName());
			// MYSQLのDATETIME型は、'YYYY-MM-DD HH:MM:SS'が基本フォーマット。
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = fmt.format(topic.getDate());
			ps.setString(3, strDate);
			int result = ps.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
