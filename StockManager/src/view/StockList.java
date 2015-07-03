package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.Product;

public class StockList extends Scene{


	private static final long serialVersionUID = 1L;
	protected static final int STATUS_COL = 0;
	private JTable stockListTable;
	private Object columnNames[] = { "Product ID", "Product Name", "Quantity" };
	private DefaultTableModel tableModel;
	private TableView<Product> table = new TableView<Product>();

	@SuppressWarnings("unchecked")
	public StockList(Parent parent){
		super(parent);
 
        final Label label = new Label("Stock List");
        label.setFont(new Font("Arial", 12));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Product ID");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Product Name");
        lastNameCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("Product Quantity");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("email"));
 
       // table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) this.getRoot()).getChildren().addAll(vbox);
		/*
		tableModel = new DefaultTableModel(columnNames, 0){ 
			private static final long serialVersionUID = 1L ;
			public boolean isCellEditable(int row, int column)
	    	{
				return false;
	    	}
		};
		stockListTable = new JTable(tableModel);
		
		stockListTable.getTableHeader().setReorderingAllowed(false);
					
			    
		JScrollPane scrollPane = new JScrollPane(stockListTable);
		scrollPane.setPreferredSize(new Dimension(500,700));
		
		stockListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		stockListTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		stockListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				
		this.add(scrollPane);*/
	}
	
	public void addProductToTable(String productID, String productName, int productQuantity){
		
		DefaultTableModel model = (DefaultTableModel) stockListTable.getModel();
		model.addRow(new Object[]{productID, productName, Integer.toString(productQuantity)});
	}
	 
	public JTable getTable(){
		return stockListTable;
	}
}
