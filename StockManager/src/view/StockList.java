package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
		stockListTable = new JTable(tableModel){
	        @Override
	        public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
	                int columnIndex) {
	            JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  

	            if(getValueAt(rowIndex, 0).toString().equalsIgnoreCase("java") && columnIndex == 0) {
	                component.setBackground(Color.RED);
	            } else if(getValueAt(rowIndex, 1).toString().equalsIgnoreCase("j2ee") && columnIndex == 1){
	                component.setBackground(Color.GREEN);
	            }

	            return component;
	        }
	    };
	    
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
