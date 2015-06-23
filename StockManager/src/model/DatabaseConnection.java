package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/IMS";
	static final String USER = "LL";
	static final String PASS = "root";
	
	private Statement stmt;
	private Connection conn;
	
	public DatabaseConnection(){

	}
	
	public void accessDB(){
		conn = null;
		stmt = null;
		
		try{
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to db");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void closeDB(){
		try{
			if(stmt != null)
				conn.close();
		}catch(SQLException se){
				se.printStackTrace();
			}
		try{
			if (conn != null)
				conn.close();		
			System.out.println("DB connection closed");
		}catch(SQLException se){
			se.printStackTrace();			
			}
		
		
	}

	public void createEntry(){
		System.out.println("Inserting records into table");
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO Product" + " VALUES (30, 'Gnome', 20)";
			stmt.executeUpdate(sql);
			System.out.println("Inserted into tables");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readDB(){
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			String sql = "SELECT ProductID, ProductName, ProductQuantity FROM Product";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("ProductID");
				String name = rs.getString("ProductName");
				int quantity = rs.getInt("ProductQuantity");
				System.out.println(id + " " + name + " " + quantity);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDB(){
		System.out.println("Updating tables...");
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE Product " + "SET ProductQuantity = 50 WHERE ProductID = 25";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteRow(){
		System.out.println("Deleting from db...");
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM Product " + "WHERE ProductID = 30";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
