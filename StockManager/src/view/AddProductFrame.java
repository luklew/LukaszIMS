package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

/*
 * This class creates a new window where user can add
 * a new product to the table and database.
 */

public class AddProductFrame extends JFrame{
	
	private AddProductPanel addProductPanel;
	
	public AddProductFrame(){
		this.setTitle("Add Product");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400,200));
		
		addProductPanel = new AddProductPanel();
		this.add(addProductPanel);
		
		this.pack();
		this.setVisible(true);
				
	}
	
	public AddProductPanel getProductPanel(){
		return addProductPanel;
	}
	
	public void disposeFrame(){
		this.dispose();
	}

}
