package edu.neu.cs5200.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import edu.neu.cs5200.ds.model.*;

public class CommentManager {
	public Connection getConnection() {
		Connection connection = null;
		
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/test");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createComment (Comment newComment){
		String sql = "insert into comment (id, comment, date, movieId, username) VALUES (?, ?, ?, ?, ?)";
		System.out.println("insertComment");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getId());
			statement.setString(2, newComment.getComment());
			statement.setString(3, newComment.getDate());
			statement.setString(4, newComment.getMovie());
			statement.setString(5, newComment.getUsername());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Comment> readAllComments(){
		String sql2 = "select * from comment";
		List<Comment> allComments = new ArrayList<Comment>();
		System.out.println("readallComments");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("id");
				String comment = results.getString("comment");
				String date = results.getString("date");
				String movieId = results.getString("movieId");
				String username = results.getString("username");
				System.out.println("Query executed " + id);
				Comment comment1 = new Comment(id, comment, date, movieId, username);
				allComments.add(comment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allComments;
		
	}
	public List<Comment> readAllCommentsForUsername(String username){
		String sql2 = "select * from comment where username=?";
		List<Comment> allComments = new ArrayList<Comment>();
		System.out.println("readallComments");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("id");
				String comment = results.getString("comment");
				String date = results.getString("date");
				String movieId = results.getString("movieId");
				String uname = results.getString("username");
				System.out.println("Query executed " + id);
				Comment comment1 = new Comment(id, comment, date, movieId, uname);
				allComments.add(comment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allComments;
		
	}
	
	public List<Comment> readAllCommentsForMovie(String movieId){
		String sql2 = "select * from comment where movieId=?";
		List<Comment> allComments = new ArrayList<Comment>();
		System.out.println("readallComments");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("id");
				String comment = results.getString("comment");
				String date = results.getString("date");
				String movie = results.getString("movieId");
				String uname = results.getString("username");
				System.out.println("Query executed " + id);
				Comment comment1 = new Comment(id, comment, date, movie, uname);
				allComments.add(comment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allComments;
		
	}
	
	public Comment readCommentForId(String commentId) {
		String sql = "select * from comment where id=?";
		System.out.println("select");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				String id = results.getString("id");
				String comment = results.getString("comment");
				String date = results.getString("date");
				String movie = results.getString("movieId");
				String user = results.getString("username");
				System.out.println("Query executed " + id);
				Comment comment1 = new Comment(id, comment, date, movie, user);
				return comment1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public void updateComment(String commentId, String newComment){
		String sql = "update comment set comment=? where id=?";
		System.out.println("update");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(2, commentId);
			statement.setString(1, newComment);
			statement.execute();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteComment(String commentId){
		String sql = "delete from comment where id=?";
		System.out.println("delete");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			statement.execute();
	}catch (SQLException e) {
		e.printStackTrace();
	}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
