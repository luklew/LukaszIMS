package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import view.AddProductFrame;
import view.InfoPanel;
import view.MenuBarGUI;
import view.StockList;
import view.StockManagerFrame;
import view.StockOrderTable;
import model.Product;
import model.StockManager;

public class StockManagerController {
	
	private StockManager model;
	private StockManagerFrame view;
	
	private StockList stockList;
	private MenuBarGUI menuBar;
	private InfoPanel infoPanel;
	private AddProductFrame addProduct;
	private StockOrderTable orderTable;
	
	public StockManagerController(StockManager model, StockManagerFrame view){
		this.model = model;
		this.view = view;
		
		stockList = view.getStockList();
		menuBar = view.getMenuBarGUI();
		infoPanel = view.getInfoPanel();
		orderTable = view.getOrderTable();
		
		menuBar.addProductListener(new AddProductHandler());
		menuBar.addSaveToFileListener(new AddSaveToFileHandler());
		
		infoPanel.addThresholdListener(new AddChangeThresholdHandler());
		infoPanel.addQuantityListener(new AddChangeQuantityHandler());
		
		addCellRenderer();
		addListenerToTable();
		addToLowStockTable();	
		
		for(Product products : model.getProducts()){
			addProductToTable(products.getProductID(), 
					products.getProductName(), 
					products.getProductQuantity());
		}
		
	}
	
	public void addProductToLowTable(String productID){
		boolean exists = false;
		for(Product pr : model.getProducts()){
			
			for(int i = 0; i < orderTable.getOrderTable().getRowCount() ; i++){
				if(productID.equals(orderTable.getOrderTable().getValueAt(i, 0))){
					exists = true;
					System.out.println(exists);
					System.out.println(orderTable.getOrderTable().getValueAt(i, 0));
				}

			}
			if(!exists){
				if(pr.getOrderThreshold() > pr.getProductQuantity()){
					orderTable.addProductToTable(pr.getProductID(), pr.getProductName(), pr.getProductQuantity());
				}	
			}
			else if(exists){
				if(pr.getOrderThreshold() < pr.getProductQuantity()){
					orderTable.deleteProductFromTable(pr.getProductID());
				}
			}
				
		}
	}
	
	public void addToLowStockTable(){
		boolean exists = false;
		for(Product pr : model.getProducts()){
			
			for(int i = 0; i < orderTable.getOrderTable().getRowCount() ; i++){
				if(pr.getProductID().equals(orderTable.getOrderTable().getValueAt(i, 0))){
					exists = true;
					System.out.println(exists);
					System.out.println(orderTable.getOrderTable().getValueAt(i, 0));
				}

			}
			if(!exists){
				if(pr.getOrderThreshold() > pr.getProductQuantity()){
					orderTable.addProductToTable(pr.getProductID(), pr.getProductName(), pr.getProductQuantity());
				}	
			}
			else if(exists){
				if(pr.getOrderThreshold() < pr.getProductQuantity()){
					orderTable.deleteProductFromTable(pr.getProductID());
				}
			}
				
		}
	}
	
	public void deleteFromLowStockTable(){
		
	}
	
	public void addListenerToTable(){
		stockList.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
	        	String productName = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 1).toString();
	        	String quantity = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 2).toString();
	        	
	        	infoPanel.setProductID(productID);
	        	infoPanel.setProductName(productName);
	        	infoPanel.setProductQuantity(quantity);
	        	
	        	Product foundProduct = model.findProductById(productID);
	        	if(foundProduct != null){
	        		infoPanel.setProductThres(Integer.toString(foundProduct.getOrderThreshold()));
	        		if(foundProduct.getOrderThreshold() > Integer.parseInt(quantity)){
	        			infoPanel.setProductOrderReq("Yes");
	        		}
	        		else 
	        			infoPanel.setProductOrderReq("No");
	        	}
	        	
	            System.out.println(stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString());
	        }
	    });
	}
	
	public void addCellRenderer(){
		stockList.getTable().setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		        
		        String prID = (String)table.getModel().getValueAt(row, 0);
		        String quantity = (String)table.getModel().getValueAt(row, 2);
		        Product foundPr = model.findProductById(prID);
		        
		        if(foundPr != null){
		        	if (Integer.parseInt(quantity) < foundPr.getOrderThreshold()) {
			            setBackground(Color.RED);
			            setForeground(Color.WHITE);
			        } else {
			            setBackground(table.getBackground());
			            setForeground(table.getForeground()); 
			        }       
		        }
		        
		        return this;
		    }   
		});
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		stockList.addProductToTable(productID, productName, productQuantity);
	}
	
	public void getLastAndAddProduct(){ 		
		Product product = model.getLastAddedProduct();
		addProductToTable(product.getProductID(), product.getProductName(), product.getProductQuantity());
		model.insertNewRow(Integer.parseInt(product.getProductID()), product.getProductName(), product.getProductQuantity());
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
			model.saveReportToFile();
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
	
	//InfoPanel
	
	private class AddChangeThresholdHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 String newThresh = JOptionPane.showInputDialog(view, "Enter new warning threshold:");
			 String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
			 
			 Product pr = model.findProductById(productID);
			 pr.setOrderThreshold(Integer.parseInt(newThresh));
			 
			 addToLowStockTable(); 
			 
		}
		
	}
	
	private class AddChangeQuantityHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String newQuan = JOptionPane.showInputDialog(view, "Enter new quantity:");
			
			String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
			 
			Product pr = model.findProductById(productID);
			pr.setProductQuantity(Integer.parseInt(newQuan));
			model.updateQuantity(productID, Integer.parseInt(newQuan));
			
			for(int i = 0; i < stockList.getTable().getRowCount() ; i++){
				if(productID.equals(stockList.getTable().getValueAt(i, 0))){
					System.out.println(stockList.getTable().getValueAt(i, 0));
					stockList.getTable().setValueAt(newQuan, i, 2);
				}

			}

		}
		
	}

}
