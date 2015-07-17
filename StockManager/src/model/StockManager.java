package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * This is the main model class, it handles adding/updating rows in the db,
 * creates an Array List of Products, saves report to file. The controller
 * communicates with this class.
 */

public class StockManager {

	private ArrayList<Product> product = new ArrayList<Product>();
	private DatabaseConnection db;
	private DateFormat dateFormat;
	private Date date;
 
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
	
	public void updateQuantity(String productID, int quantity){
		db.updateDB(Integer.parseInt(productID), quantity);
	}
	
	public void updateTableQuantity(int quantity){
		
	}
	
	public void saveReportToFile(File saveLoc){
		try{
			int numOfChars;
			int totalSpacesLeft;
			int productID;
			int productQuantity;
			
			String report = "Stock Report Generated at " + getCurrentTime() + "\r\n";
			report += "\r\n";
			report += "|----ID----|------Product Name------|-Quantity-|-OrderReq?-|------Last Updated-----|\r\n";
			report += "\r\n";
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
				
				if(productQuantity < 9 && productQuantity >= 0){
					report +=  productQuantity + "       |";
				}
				else if(productQuantity < 0 && productQuantity > -10){
					report +=  productQuantity + "      |";
				}
				else if(productQuantity < -9 && productQuantity > -100){
					report +=  productQuantity + "     |";
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
								
				if(product.get(i).getOrderRequired())
					report += "    Yes    |";
				else
					report += "    No     |";
				
				report += "  " + product.get(i).getLastUpdated() + "  |";
					
				
				report += "\r\n";
				
				report += "\r\n";

			}
			
			System.out.println(report);
			FileWriter fw = new FileWriter(saveLoc + ".txt");
			fw.write(report);
			fw.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	public String getCurrentTime(){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		date = new Date();
		return dateFormat.format(date);
	}
	
	public boolean isNumber(String val){
		try{
			Integer.parseInt(val);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
		
	}
}
