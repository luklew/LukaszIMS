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
		
		//db.closeDB();
		
		for(int i = 0; i <= db.getProductIDs().size() - 1 ; i++){
			addProduct(Integer.toString(db.getProductIDs().get(i)),
					db.getProductNames().get(i), 
					db.getProductQuantities().get(i));
		}
	}
	
	public Product findProductById(String productID){
		for(Product pr: product){
			if(pr.getProductID().equals(productID)){
				return pr;
			}
		}
		
		return null;
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
	
	public void insertNewRow(int productID, String productName, int productQuantity){
		db.insertToDb(productID, productName, productQuantity);
	}
	
	public void saveReportToFile(){
		try{
			int numOfChars;
			int totalSpacesLeft;
			int productID;
			int productQuantity;
			
			String report = "Stock Report \r\n";
			report += "\r\n";
			report += "|----ID----|------Product Name------|-Quantity-|\r\n";
			report += "\r\n";
			File reportFile = new File("Report.txt");
			for(int i = 0; i <= product.size() -1; i++){
				totalSpacesLeft = 20 ;
				productID = Integer.parseInt(product.get(i).getProductID());
				productQuantity = product.get(i).getProductQuantity();

				if(productID < 10){
					report += "|  " + product.get(i).getProductID() + "       |";
				}
				else if(productID  > 9 && productID < 100){
					report += "|  " + product.get(i).getProductID() + "      |";
				}
				else if(productID > 99 && productID < 1000){
					report += "|  " + product.get(i).getProductID() + "     |";
				}
				else if(productID > 999 && productID < 10000){
					report += "|  " + product.get(i).getProductID() + "    |";
				}
				

				
				report += "   " + product.get(i).getProductName() ;
				
				numOfChars = product.get(i).getProductName().length();			
				totalSpacesLeft = totalSpacesLeft - numOfChars;
				
				for(int whiteSpace = 0; whiteSpace <= totalSpacesLeft; whiteSpace++){
					report += " ";
				}
				
				report += "|  ";
				
				if(productQuantity < 9){
					report +=  productQuantity + "       |";
				}
				else if(productQuantity > 8 && productQuantity < 100){
					report +=  product.get(i).getProductQuantity() + "      |" ;
				}
				else if(product.get(i).getProductQuantity() > 99 && product.get(i).getProductQuantity() < 1000){
					report +=  product.get(i).getProductQuantity() + "     |" ;
				}
				else if(product.get(i).getProductQuantity() > 999 && product.get(i).getProductQuantity() < 10000){
					report +=  product.get(i).getProductQuantity() + "     |" ;
				}
				
				report += "\r\n";
				
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
