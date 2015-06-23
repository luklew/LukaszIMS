package view;

import java.awt.Dimension;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StockManagerFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private MenuBarGUI menuBar;
	private StockList stockList;
	private AddProductFrame addProduct;

	public StockManagerFrame(){
		this.setTitle("Stock Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(550,800));
		
		
		menuBar = new MenuBarGUI();
		this.setJMenuBar(menuBar);
		
		JPanel stockListPnl = new JPanel();

		stockList = new StockList();
		stockListPnl.add(stockList);
		this.add(stockList);	
		
		this.pack();
		this.setVisible(true);
				
	}
	
	public void showAddProductFrame(){
		addProduct = new AddProductFrame();
	}
	
	public AddProductFrame getProductFrame(){
		return addProduct;
	}
	
	public StockList getStockList(){
		return stockList;
	}
	
	public MenuBarGUI getMenuBarGUI(){
		return menuBar;
	}

}
