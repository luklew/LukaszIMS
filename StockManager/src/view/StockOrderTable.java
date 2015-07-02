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
		scrollPane.setPreferredSize(new Dimension(350,400));
		
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				
		this.add(scrollPane);
		
		
		
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
	
}
