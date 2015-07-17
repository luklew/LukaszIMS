package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * This class sets the layout of the Order Stock window. Adds 
 * a new table, and contains methods to add new Products to that
 * table.
 */

public class OrderStockPanel extends JPanel{
	
	private JTable orderTable;
	private DefaultTableModel tableModel;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity to Order" };
	private JCheckBox saveReport;
	private JTextField savePath;
	private JButton browse, order, cancel;
	
	public OrderStockPanel(){
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L ;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
				
		orderTable = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setPreferredSize(new Dimension(400,500));
		
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1 ; 
		c.gridwidth = 2 ; 
		this.add(scrollPane, c);
		
		order = new JButton("Order");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0 ;
		c.gridy = 1;
		c.gridheight = 1 ; 
		c.gridwidth = 1; 
		this.add(order, c);
		
		cancel = new JButton("Cancel");
		
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1 ; 
		c.gridwidth = 1; 
		this.add(cancel, c);
		
		
		
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		model.addRow(new Object[]{productID, productName, Integer.toString(productQuantity)});
	}
	
	public void deleteProductFromTable(String productID){
		for(int i = 0; i < orderTable.getRowCount() ; i++){
			if(productID.equals(orderTable.getValueAt(i, 0))){
				DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
				model.removeRow(i);
			}
		}
	}

	public JTable getTable(){
		return orderTable;
	}
	
	public void addOrderListener(ActionListener al){
		order.addActionListener(al);
	}

	public void addCancelListener(ActionListener al){
		cancel.addActionListener(al);
	}
}
