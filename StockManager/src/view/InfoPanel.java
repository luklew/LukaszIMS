package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoPanel extends JPanel{
	
	private JLabel productQuantity, productName, productID, productThreshold, orderRequired;
	private JTextField p_ID, p_Name, p_Quan, p_Thresh, p_OrderReq;
	
	public InfoPanel(){
		productQuantity = new JLabel("Quantity : ");
		productName = new JLabel("Name : ");
		productID =  new JLabel("ID : ");
		productThreshold = new JLabel("Warning Threshold : ");
		orderRequired = new JLabel("Order Required");
		
		p_ID = new JTextField(12);
		p_Name = new JTextField(20);
		p_Quan = new JTextField(12);
		p_Thresh = new JTextField(8);
		p_OrderReq = new JTextField(8);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		this.add(productID, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 0;
		this.add(p_ID, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 1;
		this.add(productName, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 1;
		this.add(p_Name, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 2;
		this.add(productQuantity, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 2;
		this.add(p_Quan, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 3;
		this.add(productThreshold, c);

		c.insets = new Insets(10,5,5,10);
		c.gridx = 1;
		c.gridy = 3;
		this.add(p_Thresh, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 4;
		this.add(orderRequired, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 4;
		this.add(p_OrderReq, c);
		
		
		
	}

}
