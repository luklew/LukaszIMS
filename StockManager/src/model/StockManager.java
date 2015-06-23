package model;

import java.util.ArrayList;

public class StockManager {

	private ArrayList<Product> product = new ArrayList<Product>();
	private DatabaseConnection db;
	

	public StockManager(){
		/*for(int i = 0; i <= 50; i++ ){
			product.add(new Product());
		}*/
			
		db = new DatabaseConnection();
		db.accessDB();
		
		//db.createEntry();
		//db.updateDB();
		db.readDB();
		db.closeDB();
		
		for(int i = 0; i <= db.getProductIDs().size() -1 ; i++){
			addProduct(Integer.toString(db.getProductIDs().get(i)),
					db.getProductNames().get(i), 
					db.getProductQuantities().get(i));
		}
	}
	
	public ArrayList<Product> getProducts(){
		return product;
	}
	
	public void addProduct(String productID, String productName, int productQuantity){
		product.add(new Product(productID, productName, productQuantity));
	}
	
	public Product getLastAddedProduct(){
		return product.get(product.size() - 1);
	}
	
	
	
	
}
