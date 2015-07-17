package view;

import java.awt.Dimension;

import javax.swing.JFrame;

/*
 * This class creates a new window which displays 
 * a table which contains products that need to be ordered.
 */

public class OrderStockFrame extends JFrame{
	
	private OrderStockPanel orderStock;
	
	public OrderStockFrame(){
		this.setTitle("Order Stock");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(450,600));
		//this.setResizable(false);
	
		orderStock = new OrderStockPanel();
		this.add(orderStock);
	
		this.pack();
		this.setVisible(true);
	}

	public OrderStockPanel getOrderPanel(){
		return orderStock;
	}
}
