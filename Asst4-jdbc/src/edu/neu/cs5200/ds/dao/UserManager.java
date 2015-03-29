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

public class UserManager {

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
	
	public void createUser (User newUser){
		String sql = "insert into user (username, password, firstname, lastname, email, date) VALUES (?, ?, ?, ?, ?, ?)";
		System.out.println("insert");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newUser.getUserName());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setString(6, newUser.getDateOfBirth());
			statement.execute();
		} catch (Exception e) {
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
	
	
	
	public List<User> readAllUsers(){
		String sql2 = "select * from user";
		List<User> allUsers = new ArrayList<User>();
		System.out.println("readall");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String userName = results.getString("username");
				String password = results.getString("password");
				String fname = results.getString("firstName");
				String lname = results.getString("lastName");
				String email = results.getString("email");
				String dob = results.getString("date");
				System.out.println("Query executed " + userName);
				User user = new User(userName, password, fname, lname, email, dob);
				allUsers.add(user);
			}
		} catch (SQLException e) {
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
		
		return allUsers;
		
	}
	
	public User readUser(String username) {
		String sql = "select * from user where username=?";
		System.out.println("select");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				String userName = results.getString("username");
				String fname = results.getString("firstname");
				String lname = results.getString("lastname");
				String email = results.getString("email");
				String dob = results.getString("date");
				System.out.println("Query executed");
				User user = new User(userName, fname, lname, email, dob, dob);
				return user;
			}
		} catch (SQLException e) {
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
		
		return null;
	}
	
	public void updateUser(String username, User user){
		String sql = "update user set password=?, firstname=?, lastname=?, email=?, date=? where username=?";
		System.out.println("update");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(6, username);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getDateOfBirth());
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
		
	public void deleteUser(String username){
		String sql = "delete from user where username=?";
		System.out.println("delete");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
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