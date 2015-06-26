package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StockList extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected static final int STATUS_COL = 0;
	private JTable stockListTable;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity" };
	private DefaultTableModel tableModel;

	public StockList(){
		
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L ;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
		stockListTable = new JTable(tableModel);
		
		stockListTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        String quantity = (String)table.getModel().getValueAt(row, 2);
		        
		        if (Integer.parseInt(quantity) < 25) {
		            setBackground(Color.RED);
		            setForeground(Color.WHITE);
		        } else {
		            setBackground(table.getBackground());
		            setForeground(table.getForeground()); 
		        }       
		        return this;
		    }   
		});
	    
		JScrollPane scrollPane = new JScrollPane(stockListTable);
		scrollPane.setPreferredSize(new Dimension(500,700));
		
		stockListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		stockListTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		stockListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				
		this.add(scrollPane);
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		
		DefaultTableModel model = (DefaultTableModel) stockListTable.getModel();
		model.addRow(new Object[]{productID, productName, Integer.toString(productQuantity)});
	}
	 
	
}
