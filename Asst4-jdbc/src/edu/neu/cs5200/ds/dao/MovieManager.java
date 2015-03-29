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

public class MovieManager {
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
	
	public void createMovie (Movie newMovie){
		String sql = "insert into movie (id, title, posterImage, releaseDate) VALUES (?, ?, ?, ?)";
		System.out.println("insert");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getId());
			statement.setString(2, newMovie.getTitle());
			statement.setString(3, newMovie.getPosterImage());
			statement.setString(4, newMovie.getReleaseDate());
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
	
	public List<Movie> readAllMovies(){
		String sql2 = "select * from movie";
		List<Movie> allMovies = new ArrayList<Movie>();
		System.out.println("readallMovies");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql2);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				String id = results.getString("id");
				String title = results.getString("title");
				String image = results.getString("posterImage");
				String date = results.getString("releaseDate");
				System.out.println("Query executed " + id);
				Movie movie = new Movie(id, title, image, date);
				allMovies.add(movie);
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
		
		return allMovies;
		
	}
	
	public Movie readMovie(String movieId) {
		String sql = "select * from movie where id=?";
		System.out.println("select");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				String id = results.getString("id");
				String title = results.getString("title");
				String image = results.getString("posterImage");
				String date = results.getString("releaseDate");
				System.out.println("Query executed " + id);
				Movie movie = new Movie(id, title, image, date);
				return movie;
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
	
	public void updateMovie(String movieId, Movie movie){
		String sql = "update movie set title=?, posterImage=?, releaseDate=? where id=?";
		System.out.println("update");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, movieId);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setString(3, movie.getReleaseDate());
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
		
	public void deleteMovie(String movieId){
		String sql = "delete from movie where id=?";
		System.out.println("delete");
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
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
