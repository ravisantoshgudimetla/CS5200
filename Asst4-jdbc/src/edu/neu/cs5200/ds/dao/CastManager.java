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

public class CastManager {
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
	public void createCast(Cast newCast){
		String sql = "insert into cast (castId, characterName, actorId, movieId) VALUES (?, ?, ?, ?)";
		System.out.println("insertCast");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getId());
			statement.setString(2, newCast.getCharacterName());
			statement.setString(3, newCast.getActorId());
			statement.setString(4, newCast.getMovieId());
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
	
	public List<Cast> readAllCasts(){
		String sql2 = "select * from cast;;
		List<Cast> allCasts = new ArrayList<Cast>();
		System.out.println("readallCasts");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("castId");
				String cname = results.getString("characterName");
				String actorId = results.getString("actorId");
				String movieId = results.getString("movieId");
				System.out.println("Query executed " + id);
				Cast cast = new Cast(id, cname, actorId, movieId);
				allCasts.add(cast);
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
		
		return allCasts;
		
	}
	
	public List<Cast> readAllCastForActor(String castId){
		String sql2 = "select * from cast where castId=?";
		List<Cast> allCasts = new ArrayList<Cast>();
		System.out.println("readallCasts");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("castId");
				String cname = results.getString("characterName");
				String actorId = results.getString("actorId");
				String movieId = results.getString("movieId");
				System.out.println("Query executed " + id);
				Cast cast = new Cast(id, cname, actorId, movieId);
				allCasts.add(cast);
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
		
		return allCasts;
	}
	
	public List<Cast> readAllCastForMovie(String movieId){
		String sql2 = "select * from cast where movieId=?";
		List<Cast> allCasts = new ArrayList<Cast>();
		System.out.println("readallCastsForMovie");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("castId");
				String cname = results.getString("characterName");
				String actorId = results.getString("actorId");
				String movie = results.getString("movieId");
				System.out.println("Query executed " + id);
				Cast cast = new Cast(id, cname, actorId, movie);
				allCasts.add(cast);
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
		
		return allCasts;
	}

	public Cast readCastForId(String castId){
		String sql = "select * from cast where castId=?";
		System.out.println("selectReadCastForId");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				String id = results.getString("castId");
				String cname = results.getString("characterName");
				String actorId = results.getString("actorId");
				String movie = results.getString("movieId");
				System.out.println("Query executed " + id);
				System.out.println("Query executed " + id);
				Cast cast = new Cast(id, cname, actorId, movie);
				return cast;
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
	
	public void updateCast(String castId, Cast newCast){
		String sql = "update cast set characterName=?, actorId=?, movieId=? where castId=?;";
		System.out.println("updateCast");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, castId);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getActorId());
			statement.setString(3, newCast.getMovieId());
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
	
	public void deleteCast(String castId){
		String sql = "delete from cast where castId=?;";
		System.out.println("deleteCast");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
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
}