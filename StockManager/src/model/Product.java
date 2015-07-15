package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Product {
	
	private String productID;
	private String productName;
	private int productQuantity;
	private int orderThreshold;
	private Random rand;
	private boolean orderRequired;
	private String lastUpdated;
	private DateFormat dateFormat;
	private Date date;
	private ArrayList<Number> stockHistory; 
	
	public Product(){
		stockHistory = new ArrayList<Number>();
		rand = new Random();
		productID = "GN-" + rand.nextInt(1000);
		productName = "Gnome" + rand.nextInt(1000);
		productQuantity = rand.nextInt(100);

	}
	
	public Product(String productID, String productName, int productQuantity){
		this.productID = productID;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.orderThreshold = 20;
		
		rand = new Random();
		stockHistory = new ArrayList<Number>();
		for(int i = 0 ; i < 7 ; i++){
			stockHistory.add(rand.nextInt(100));
		}
	}
		
	public String getProductID(){
		return productID;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public int getProductQuantity(){
		return productQuantity;	
	}
	
	public int getOrderThreshold(){
		return orderThreshold;
	}
	
	public void setProductID(String productID){
		this.productID = productID;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public void setProductQuantity(int productQuantity){
		this.productQuantity = productQuantity;
	}
	
	public void setOrderThreshold(int thres){
		this.orderThreshold = thres;
	}
	
	public void setOrderRequired(boolean orderReq){
		this.orderRequired = orderReq;
	}
	
	public boolean getOrderRequired(){
		return orderRequired;
	}
	
	public void setLastUpdated(){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		date = new Date();
		this.lastUpdated = dateFormat.format(date);	
	}

	public String getLastUpdated(){
		return lastUpdated;
	}
	
	public ArrayList<Number> getStockHistory(){
		return stockHistory;
	}
	
}
