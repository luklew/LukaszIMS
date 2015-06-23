package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddProductFrame;
import view.MenuBarGUI;
import view.StockList;
import view.StockManagerFrame;
import model.Product;
import model.StockManager;

public class StockManagerController {
	
	private StockManager model;
	private StockManagerFrame view;
	
	private StockList stockList;
	private MenuBarGUI menuBar;
	private AddProductFrame addProduct;
	
	public StockManagerController(StockManager model, StockManagerFrame view){
		this.model = model;
		this.view = view;
		
		stockList = view.getStockList();
		menuBar = view.getMenuBarGUI();
		
		menuBar.addProductListener(new AddProductHandler());
		menuBar.addSaveToFileListener(new AddSaveToFileHandler());
		
		for(Product products : model.getProducts()){
			addProductToTable(products.getProductID(), products.getProductName(), products.getProductQuantity());
		}
		
		
		
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		stockList.addProductToTable(productID, productName, productQuantity);
	}
	
	public void getLastAndAddProduct(){
		Product product = model.getLastAddedProduct();
		addProductToTable(product.getProductID(), product.getProductName(), product.getProductQuantity());
	}
	
	//MENU BAR
	private class AddProductHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.showAddProductFrame();
			addProduct = view.getProductFrame();
			
			addProduct.getProductPanel().addProductListener(new AddProductPanelHandler());
			addProduct.getProductPanel().addCancelListener(new AddCancelProductHandler());
			
		}
		
	}
	
	private class AddSaveToFileHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	private class AddSimModeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//ADD PRODUCT FRAME
	private class AddProductPanelHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.addProduct(addProduct.getProductPanel().getProductId(), 
					addProduct.getProductPanel().getProductName(), 
					addProduct.getProductPanel().getProductQuantity());
			
			getLastAndAddProduct();
		}
		
	}
	
	private class AddCancelProductHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			addProduct.dispose();
			
		}
		
	}

}
