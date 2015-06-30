package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import view.AddProductFrame;
import view.InfoPanel;
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
	private InfoPanel infoPanel;
	private AddProductFrame addProduct;
	
	public StockManagerController(StockManager model, StockManagerFrame view){
		this.model = model;
		this.view = view;
		
		stockList = view.getStockList();
		menuBar = view.getMenuBarGUI();
		infoPanel = view.getInfoPanel();
		
		menuBar.addProductListener(new AddProductHandler());
		menuBar.addSaveToFileListener(new AddSaveToFileHandler());
		
		for(Product products : model.getProducts()){
			addProductToTable(products.getProductID(), 
					products.getProductName(), 
					products.getProductQuantity());
		}
		
		addListenerToTable();
		addCellRenderer();
			
	}
	
	public void addListenerToTable(){
		stockList.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
	        	
	        	infoPanel.setProductID(productID);
	        	infoPanel.setProductName(stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 1).toString());
	        	infoPanel.setProductQuantity(stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 2).toString());
	        	
	        	Product foundProduct = model.findProductById(productID);
	        	if(foundProduct != null){
	        		infoPanel.setProductThres(Integer.toString(foundProduct.getOrderThreshold()));
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
	
	public void getLastAndAddProduct(){ 	System.out.println("enter");	
		Product product = model.getLastAddedProduct();
		System.out.println("get products");
		addProductToTable(product.getProductID(), product.getProductName(), product.getProductQuantity());
		System.out.println("add to table");
		model.insertNewRow(Integer.parseInt(product.getProductID()), product.getProductName(), product.getProductQuantity());
		System.out.println("test");
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

}
