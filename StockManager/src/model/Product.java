package model;

import java.util.Random;

public class Product {
	
	private String productID;
	private String productName;
	private int productQuantity;
	private Random rand;
	
	public Product(){
		rand = new Random();
		productID = "GN-" + rand.nextInt(1000);
		productName = "Gnome" + rand.nextInt(1000);
		productQuantity = rand.nextInt(100);

	}
	
	public Product(String productID, String productName, int productQuantity){
		this.productID = productID;
		this.productName = productName;
		this.productQuantity = productQuantity;

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
	
	public void setProductID(String productID){
		this.productID = productID;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public void setProductQuantity(int productQuantity){
		this.productQuantity = productQuantity;
	}
	

}
