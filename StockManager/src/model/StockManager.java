package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StockManager {

	private ArrayList<Product> product = new ArrayList<Product>();
	private DatabaseConnection db;
	

	public StockManager(){
			
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
			int numOfChars;
			int totalSpacesLeft;
			
			String report = "Stock Report \r\n";
			report += "\r\n";
			report += "|---ID---|------Product Name------|-Quantity-|\r\n";
			report += "\r\n";
			File reportFile = new File("Report.txt");
			for(int i = 0; i <= product.size() -1; i++){
				totalSpacesLeft = 20 ;
				
				if(i < 9){
					report += "|   " + product.get(i).getProductID() + "    |";
				}
				else if(i > 8 && i < 100){
					report += "|   " + product.get(i).getProductID() + "   |";
				}
				
				report += "   " + product.get(i).getProductName() ;
				
				numOfChars = product.get(i).getProductName().length();			
				totalSpacesLeft = totalSpacesLeft - numOfChars;
				
				for(int whiteSpace = 0; whiteSpace <= totalSpacesLeft; whiteSpace++){
					report += " ";
				}
				
				report += "|    ";
				
				if(product.get(i).getProductQuantity() < 9){
					report +=  product.get(i).getProductQuantity() + "     |";
				}
				else if(product.get(i).getProductQuantity() > 8 && product.get(i).getProductQuantity() < 100){
					report +=  product.get(i).getProductQuantity() + "    |" ;
				}
				else if(product.get(i).getProductQuantity() > 99 && product.get(i).getProductQuantity() < 1000){
					report +=  product.get(i).getProductQuantity() + "   |" ;
				}
				
				report += "\r\n";
				
				report += "_____________________________________________";
				report += "\r\n";

			}
			
			System.out.println(report);
			FileWriter fw = new FileWriter(reportFile);
			fw.write(report);
			fw.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
}
