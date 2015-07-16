package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import view.AddProductFrame;
import view.InfoPanel;
import view.MenuBarGUI;
import view.OrderStockFrame;
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
	private Product foundProduct;
	private OrderStockFrame orderFrame;
	
	public StockManagerController(StockManager model, StockManagerFrame view){
		this.model = model;
		this.view = view;
		
		stockList = view.getStockList();
		menuBar = view.getMenuBarGUI();
		infoPanel = view.getInfoPanel();
		orderTable = view.getOrderTable();
		orderFrame = view.getOrderFrame();
		
		menuBar.addProductListener(new AddProductHandler());
		menuBar.addSaveToFileListener(new AddSaveToFileHandler());
		menuBar.addSimModeListener(new AddSimModeHandler());
		
		infoPanel.addThresholdListener(new AddChangeThresholdHandler());
		infoPanel.addQuantityListener(new AddChangeQuantityHandler());
		
		orderTable.addOrderStockListener(new AddOrderStockHanlder());
		
		addCellRenderer();
		addListenerToTable();
		addToLowStockTable();	
		
		for(Product products : model.getProducts()){
			addProductToTable(products.getProductID(), 
					products.getProductName(), 
					products.getProductQuantity());
			
        	if(products.getOrderThreshold() > products.getProductQuantity())
        			products.setOrderRequired(true);
        	else 
        			products.setOrderRequired(false);
        	
        	products.setLastUpdated();
        	
		}
		
	}
	
	public void addProductToLowTable(String productID){
		boolean exists = false;
			
		Product pr = model.findProductById(productID);
		for(int i = 0; i < orderTable.getOrderTable().getRowCount() ; i++){
			if(productID.equals(orderTable.getOrderTable().getValueAt(i, 0))){
				exists = true;
				System.out.println(exists);
				System.out.println(orderTable.getOrderTable().getValueAt(i, 0));
			}
		}
		
		System.out.println(exists);
		
		
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
	        	
	        	foundProduct = model.findProductById(productID);
	        	if(foundProduct != null){
	        		infoPanel.setProductThres(Integer.toString(foundProduct.getOrderThreshold()));
	        		if(foundProduct.getOrderThreshold() > Integer.parseInt(quantity)){
	        			System.out.println("True");
	        			foundProduct.setOrderRequired(true);
	        			infoPanel.setProductOrderReq("Yes");
	        		}
	        		else {
	        			System.out.println("False");
	        			foundProduct.setOrderRequired(false);
	        			infoPanel.setProductOrderReq("No");
	        		}
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
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(orderFrame) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  model.saveReportToFile(file);
			}
		}
		
	}
	
	private class AddSimModeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Thread thread = new Thread() {
		        @Override
		        public void run() {
		        	int rowCount;
					int randRow ; 
					String currentVal;
					int randQuan;
					String id;
					Product pr;
					Random rnd = new Random();
					
					for(int i = 25 ; i >= 0 ; i--){
						infoPanel.setSimLabel("Simulation..." + i );
						rowCount = stockList.getTable().getRowCount();
						randRow = rnd.nextInt(rowCount);
						randQuan = rnd.nextInt(100);
						currentVal = (String) stockList.getTable().getValueAt(randRow, 2);
						id = (String) stockList.getTable().getValueAt(randRow, 0);
						pr = model.findProductById(id);
						
					if((pr.getProductQuantity() - randQuan) > 0 ){
							stockList.getTable().setValueAt(Integer.toString(Integer.parseInt(currentVal) - randQuan) , randRow, 2);
							System.out.println((pr.getProductQuantity() - randQuan));
							pr.setProductQuantity(Integer.parseInt(currentVal) - randQuan);
							pr.setLastUpdated();
					}
					else{
						stockList.getTable().setValueAt("0" , randRow, 2);
						System.out.println(pr.getProductName() + " Quantity :  0" );
						pr.setProductQuantity(0);
						pr.setLastUpdated();
					}					
						addProductToLowTable(id);
						stockList.getTable().repaint();
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					infoPanel.setSimLabel("");
		        }
		    };
		    thread.start();
			
		}
		
	}
	
	//ADD PRODUCT FRAME
	private class AddProductPanelHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int exists = 0;
			
			for(Product products : model.getProducts()){
				if(addProduct.getProductPanel().getProductId().equals(products.getProductID())){
					exists++;
					JOptionPane.showMessageDialog(view, "Product ID already exsists in table");
				}
				else{
				}
			}
			try{
				  	Integer.parseInt(addProduct.getProductPanel().getProductId());
			} catch (NumberFormatException e1) {
					exists++;
					JOptionPane.showMessageDialog(view, "Product ID must be a number");
			}
						
			if(exists == 0){
				model.addProduct(addProduct.getProductPanel().getProductId(), 
						addProduct.getProductPanel().getProductName(), 
						addProduct.getProductPanel().getProductQuantity());
				getLastAndAddProduct();
			}
  
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
			 
			 if(newThresh != null){
				 if(model.isNumber(newThresh)){
					 String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
			 
					 Product pr = model.findProductById(productID);
					 pr.setOrderThreshold(Integer.parseInt(newThresh));
			 
					 addProductToLowTable(productID);
					 infoPanel.setProductThres(newThresh);
			 
					 stockList.getTable().repaint();
				 }else {
					 JOptionPane.showMessageDialog(view, "Threshold can only be a number");
				 }
			 }
			 
		}
		
	}
	
	private class AddChangeQuantityHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String newQuan = JOptionPane.showInputDialog(view, "Enter new quantity:");
			if(newQuan != null){
				if(model.isNumber(newQuan)){
						
					String productID = stockList.getTable().getValueAt(stockList.getTable().getSelectedRow(), 0).toString();
			 
					Product pr = model.findProductById(productID);
					pr.setProductQuantity(Integer.parseInt(newQuan));
					model.updateQuantity(productID, Integer.parseInt(newQuan));
			
					for(int i = 0; i < stockList.getTable().getRowCount() ; i++){
						if(productID.equals(stockList.getTable().getValueAt(i, 0))){
							System.out.println(stockList.getTable().getValueAt(i, 0));
							stockList.getTable().setValueAt(newQuan, i, 2);
							addProductToLowTable(productID);
							pr.setLastUpdated();
							infoPanel.setProductQuantity(newQuan);
						}

					}
			
					stockList.getTable().repaint();
				
				}else {
					JOptionPane.showMessageDialog(view, "Quantity can only be a number");
				}
			}
		}
		
	}
	
	private class AddOrderStockHanlder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderFrame =  new OrderStockFrame();
			
			orderFrame.getOrderPanel().addBrowseListener(new AddBrowseHandler());
			orderFrame.getOrderPanel().addOrderListener(new AddOrderHandler());
			orderFrame.getOrderPanel().addCancelListener(new OrderCancelHandler());
			
			
			boolean exists = false;
			for(Product pr : model.getProducts()){
				
				for(int i = 0; i < orderFrame.getOrderPanel().getTable().getRowCount() ; i++){
					if(pr.getProductID().equals(orderFrame.getOrderPanel().getTable().getValueAt(i, 0))){
						exists = true;
						System.out.println(exists);
						System.out.println(orderFrame.getOrderPanel().getTable().getValueAt(i, 0));
					}

				}
				if(!exists){
					if(pr.getOrderThreshold() > pr.getProductQuantity()){
						orderFrame.getOrderPanel().addProductToTable(pr.getProductID(), pr.getProductName(),(pr.getProductQuantity() + (pr.getOrderThreshold() - pr.getProductQuantity()) * 2));
					}	
				}
				else if(exists){
					if(pr.getOrderThreshold() < pr.getProductQuantity()){
						orderFrame.getOrderPanel().deleteProductFromTable(pr.getProductID());
					}
				}
					
			}
			
		}
		
	}
	
	private class AddBrowseHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(orderFrame) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  // save to file
			}
		}
		
	}
	
	private class AddOrderHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < orderFrame.getOrderPanel().getTable().getRowCount(); i++){
				
				String productID = orderFrame.getOrderPanel().getTable().getValueAt(i, 0).toString();
				System.out.println(productID);
				Product pr = model.findProductById(productID);
				String newQuan = Integer.toString(pr.getProductQuantity() + ((pr.getOrderThreshold() - pr.getProductQuantity()) * 2));
				pr.setProductQuantity(Integer.parseInt(newQuan));
				model.updateQuantity(productID, Integer.parseInt(newQuan));
			
				for(int index = 0; index < stockList.getTable().getRowCount() ; index++){
					System.out.println(index);
					if(productID.equals(stockList.getTable().getValueAt(index, 0))){
						stockList.getTable().setValueAt(newQuan, index, 2);
						addProductToLowTable(productID);
						pr.setLastUpdated();
					}

				}
				
				stockList.getTable().repaint();
			}
			
			orderFrame.dispose();

			
		}
		
	}
	
	private class OrderCancelHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			orderFrame.dispose();
			
		}
		
	}
}
