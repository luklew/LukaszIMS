package view;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StockList extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTable stockListTable;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity" };
	private DefaultTableModel tableModel;

	public StockList(){
		
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
		stockListTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(stockListTable);
		scrollPane.setPreferredSize(new Dimension(500,700));
		
		stockListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		stockListTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		stockListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				
		this.add(scrollPane);
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		
		DefaultTableModel model = (DefaultTableModel) stockListTable.getModel();
		model.addRow(new Object[]{productID, productName, productQuantity});
		
	}
	 
	
}
