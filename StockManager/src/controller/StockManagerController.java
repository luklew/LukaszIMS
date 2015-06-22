package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public StockManagerController(StockManager model, StockManagerFrame view){
		this.model = model;
		this.view = view;
		
		stockList = view.getStockList();
		
		
		
		for(Product products : model.getProducts()){
			stockList.addProductToTable(products.getProductID(), products.getProductName(), products.getProductQuantity());
		}
		
	}
	
	private class AddProductHandler implements ActionListener{

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

}
