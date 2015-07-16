package view;

import java.awt.Dimension;

import javax.swing.JFrame;

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
