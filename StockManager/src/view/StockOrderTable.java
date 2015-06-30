package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StockOrderTable extends JPanel{
	
	private JTable orderTable;
	private DefaultTableModel tableModel;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity" };
	
	public StockOrderTable (){
		
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L ;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
				
		orderTable = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setPreferredSize(new Dimension(350,430));
		
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				
		this.add(scrollPane);		
		
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
		model.addRow(new Object[]{productID, productName, Integer.toString(productQuantity)});
	}

}
