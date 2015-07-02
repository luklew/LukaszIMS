package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProductPanel extends JPanel{
	
	private JTextField productID, productName, productQuantity;
	private JLabel id, name, quantity;
	private JButton addProduct, cancel;
	
	public AddProductPanel(){
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		id = new JLabel("Product ID : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		this.add(id, c);
		
		productID = new JTextField(8);
		c.gridx = 1;
		c.gridy = 0;
		this.add(productID, c);
		
		name = new JLabel("Product Name : ");
		c.gridx = 0;
		c.gridy = 1;
		this.add(name, c);
		
		productName = new JTextField(8);
		c.gridx = 1;
		c.gridy = 1;
		this.add(productName, c);
		
		quantity = new JLabel("Product Quantity : ");
		c.gridx = 0;
		c.gridy = 2;
		this.add(quantity, c);
		
		productQuantity = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		this.add(productQuantity, c);
		
		addProduct = new JButton("Add Product");
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 3;
		this.add(addProduct, c);
		
		cancel = new JButton("Close");
		c.gridx = 1;
		c.gridy = 3;
		this.add(cancel, c);
	}
	
	public void addProductListener(ActionListener al){
		addProduct.addActionListener(al);
	}
	
	public void addCancelListener(ActionListener al){
		cancel.addActionListener(al);
	}
	
	public String getProductId(){
		return productID.getText();
	}
	
	public String getProductName(){
		return productName.getText();
	}
	
	public int getProductQuantity(){
		return Integer.parseInt(productQuantity.getText());
	}

}
