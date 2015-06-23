package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		
		for(int i = 0; i <= db.getProductIDs().size() - 1 ; i++){
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
	
	public void saveReportToFile(){
		try{
			String report = "";
			File reportFile = new File("Report.txt");
			for(int i = 0; i <= product.size() -1; i++){
				report += product.get(i).getProductID() + " " + 
						product.get(i).getProductName() + " " + 
						product.get(i).getProductQuantity() + " \n";
			}
			FileWriter fw = new FileWriter(reportFile);
			fw.write(report);
			fw.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
}
