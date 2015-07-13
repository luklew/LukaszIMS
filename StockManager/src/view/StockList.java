package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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

    final static ObservableList<Product> data = FXCollections.observableArrayList();

	
	
	@SuppressWarnings("unchecked")
	public StockList(Parent parent){
		super(parent);
 
        final Label label = new Label("Stock List");
        label.setFont(new Font("Arial", 12));
 
        table.setEditable(true);
 
        TableColumn productID = new TableColumn("Product ID");
        productID.setMinWidth(150);
        productID.setCellValueFactory(
                new PropertyValueFactory<Product, String>("productID"));
 
        TableColumn productName = new TableColumn("Product Name");
        productName.setMinWidth(200);
        productName.setCellValueFactory(
                new PropertyValueFactory<Product, String>("productName"));
 
        TableColumn<Product, String> productQuantity = new TableColumn("Product Quantity");
        productQuantity.setMinWidth(150);
        
        productQuantity.setCellValueFactory(
        		cellData -> cellData.getValue().getProductQuantity());
        
        productQuantity.setCellFactory(column -> {
            return new TableCell<Product, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                        System.out.println("NULL");
                    } else {

                        // Style all dates in March with a different color.
                        if (item.equals(10)) {
                            System.out.println("10");
                        } else {
                            System.out.println("non 10");
                        }
                    }
                }
            };
        });

 
        table.setPrefSize(500, 700);
        table.setItems(data);
        table.getColumns().addAll(productID, productName, productQuantity);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) this.getRoot()).getChildren().addAll(vbox);

	}
	
	public static void addProductToTable(String productID, String productName, int productQuantity){
        data.add(new Product(productID, productName, productQuantity ));
    }

	 
	public JTable getTable(){
		return stockListTable;
	}
}
