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


public class ActorManager {
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
	public void createActor (Actor newActor){
		String sql = "insert into actor (id, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?)";
		System.out.println("insert");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getId());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setString(4, newActor.getDateOfBirth());
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
	
	public List<Actor> readAllActors(){
		String sql2 = "select * from actor";
		List<Actor> allActors = new ArrayList<Actor>();
		System.out.println("readallActors");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("id");
				String fname = results.getString("firstName");
				String lname = results.getString("lastName");
				String date = results.getString("dateOfBirth");
				System.out.println("Query executed " + id);
				Actor actor = new Actor(id, fname, lname, date);
				allActors.add(actor);
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
		
		return allActors;
		
	}
	
	public Actor readActor(String actorId) {
		String sql = "select * from actor where id=?";
		System.out.println("select");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				String id = results.getString("id");
				String fname = results.getString("firstName");
				String lname = results.getString("lastName");
				String date = results.getString("dateOfBirth");
				System.out.println("Query executed " + id);
				Actor actor = new Actor(id, fname, lname, date);
				return actor;
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
	
	public void updateActor(String actorId, Actor actor){
		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		System.out.println("update");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, actorId);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setString(3, actor.getDateOfBirth());
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
		
	public void deleteActor(String actorId){
		String sql = "delete from actor where id=?;";
		System.out.println("delete");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
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
