package model;

import java.util.ArrayList;

public class StockManager {

	private ArrayList<Product> product = new ArrayList<Product>();
	
	public StockManager(){
		for(int i = 0; i <= 50; i++ ){
			product.add(new Product());
		}
		
	}
	
	public ArrayList<Product> getProducts(){
		return product;
	}
	
	public void addProduct(String productID, String productName, int productQuantity){
		
	}
	
	
	
}
