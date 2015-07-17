package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class contains methods to connect, add, update, delete
 * in the database.
 */

public class DatabaseConnection {
	
	private String[] gnomeNames =  {"Hippy Gnome", "King Gnome", "Queen Gnome", "Nuclear Gnome", "Biohazard Gnome", "Obama Gnome", "Redneck Gnome", "Business Gnome", 
			"Chav Gnome", "Beiber Gnome", "Potter Gnome", "Wolverine Gnome", "Iron Man Gnome", "Voldemort Gnome", "Jedi Gnome", "Sith Gnome", "Picard Gnome", "Angel Gnome", "Gun Gnome", 
			"Big Gun Gnome",  "Bazooka Gnome", "Tank Gnome", "Police Gnome",
			"Other Gnome", "Greek Gnome", "Roman Gnome", "Chewbacca Gnome", "Time Lord Gnome"
			,"Samurai Gnome" , "Demon Gnome", "Insane Gnome", "Australian Gnome", "French Gnome"};
	
	private Random rnd;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.50.15.29:3306/IMS";//"jdbc:mysql://10.50.15.12/IMS";
	static final String USER = "LL";
	static final String PASS = "root";
	
	private ArrayList<Integer> productID;
	private ArrayList<String> productName;
	private ArrayList<Integer> productQuantity;
	
	private Statement stmt;
	private Connection conn;
	
	public DatabaseConnection(){
		productID = new ArrayList<Integer>();
		productName = new ArrayList<String>();
		productQuantity = new ArrayList<Integer>();
		
		rnd = new Random();
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
			for(int i = 0; i < 33; i++){
			String sql = "INSERT INTO Product" + " VALUES (" + (i+1) + ", '" + gnomeNames[i] +"', " + rnd.nextInt(100) +")";
			stmt.executeUpdate(sql);
			System.out.println("Inserted into tables");
			}
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
			int i = 0;
			while(rs.next()){
				productID.add(rs.getInt("ProductID"));
				productName.add(rs.getString("ProductName"));
				productQuantity.add(rs.getInt("ProductQuantity"));
				System.out.println(productID.get(i) + " " + 
				productName.get(i) + " " + productQuantity.get(i));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDB(int productID, int productQuantity){
		System.out.println("Updating quantity...");
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE Product " + "SET ProductQuantity = " + productQuantity + "  WHERE ProductID = " + productID ;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertToDb(int productID, String productName, int productQuantity){
		System.out.println("Inserting tables...");
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO Product VALUES (" + productID +", '" + productName + "', " + productQuantity + ")";
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
	
	public ArrayList<Integer> getProductIDs(){
		return productID;
	}
	
	public ArrayList<String> getProductNames(){
		return productName;
	}
	
	public ArrayList<Integer> getProductQuantities(){
		return productQuantity;
	}
	
}
