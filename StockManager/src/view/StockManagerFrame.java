package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StockManagerFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private MenuBarGUI menuBar;
	private StockList stockList;
	private InfoPanel infoPanel;
	private AddProductFrame addProduct;
	private StockOrderTable orderTable;

	public StockManagerFrame(){
		this.setTitle("Stock Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(950,800));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		menuBar = new MenuBarGUI();
		this.setJMenuBar(menuBar);
		
		JPanel stockListPnl = new JPanel();

		stockList = new StockList();
		stockListPnl.add(stockList);
		//this.add(stockList);	
		
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2 ; 
		this.add(stockList, c);
		
		
		infoPanel = new InfoPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1 ; 
		this.add(infoPanel, c);
				
		orderTable = new StockOrderTable();
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("Low Stock", orderTable);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 1;
		this.add(tabPane, c);
		
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
	
	public InfoPanel getInfoPanel(){
		return infoPanel;
	}

}
