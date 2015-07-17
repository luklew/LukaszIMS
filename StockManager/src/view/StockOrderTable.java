package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * This class creates a new table and inserts products with quantity
 * below their thershold.
 */

public class StockOrderTable extends JPanel{
	
	private JTable orderTable;
	private DefaultTableModel tableModel;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity" };
	private JButton orderStock;
	
	public StockOrderTable (){
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		orderStock = new JButton("Order Stock");
		
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L ;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
				
		orderTable = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setPreferredSize(new Dimension(350,370));
		
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1 ; 
		this.add(scrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1 ; 
		this.add(orderStock, c);
		
		
		
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

	public JTable getOrderTable(){
		return orderTable;
	}
	
	public void addOrderStockListener(ActionListener al){
		orderStock.addActionListener(al);
	}
	
}
