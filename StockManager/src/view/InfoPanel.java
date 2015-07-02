package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoPanel extends JPanel{
	
	private JLabel productQuantity, productName, productID, productThreshold, orderRequired;
	private JTextField p_ID, p_Name, p_Quan;
	private JLabel p_Thresh, p_OrderReq, p_Sim;
	private JButton changeThres, orderStock, changeQuan;
	
	public InfoPanel(){
		productQuantity = new JLabel("Quantity : ");
		productName = new JLabel("Name : ");
		productID =  new JLabel("ID : ");
		productThreshold = new JLabel("Warning Threshold : ");
		orderRequired = new JLabel("Order Required : ");
		p_Sim = new JLabel("");
			
		p_ID = new JTextField(12);
		p_Name = new JTextField(20);
		p_Quan = new JTextField(12);
		p_Thresh = new JLabel("");
		p_OrderReq = new JLabel("");
		
		p_ID.setEnabled(false);
		p_ID.setDisabledTextColor(Color.BLACK);
		p_Name.setEnabled(false);
		p_Name.setDisabledTextColor(Color.BLACK);
		p_Quan.setEnabled(false);
		p_Quan.setDisabledTextColor(Color.BLACK);
		
		changeThres = new JButton("Change Threshold");
		orderStock = new JButton("Order Stock");
		changeQuan = new JButton("Change Quantity");
		
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
		c.gridx = 1;
		c.gridy = 3;
		this.add(changeQuan, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 4;
		this.add(productThreshold, c);

		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 4;
		this.add(p_Thresh, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 5;
		//c.weightx = 0.5;
		this.add(changeThres, c);
				
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 6;
		this.add(orderRequired, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 6;
		this.add(p_OrderReq, c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 7;
		this.add(p_Sim, c);
		
		/*c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 7;
		this.add(orderStock, c);*/
		
	}

	public void setProductID(String id){
		p_ID.setText(id);
	}
	
	public void setProductName(String name){
		p_Name.setText(name);
	}
	
	public void setProductQuantity(String quan){
		p_Quan.setText(quan);
	}
	
	public void setProductThres(String thres){
		p_Thresh.setText(thres);
	}
	
	public void setProductOrderReq(String order){
		p_OrderReq.setText(order);
	}
	
	public void addThresholdListener(ActionListener al){
		changeThres.addActionListener(al);
	}
	
	public void addQuantityListener(ActionListener al){
		changeQuan.addActionListener(al);
	}
	
	public void setSimLabel(String msg){
		p_Sim.setText(msg);
	}
	
}
