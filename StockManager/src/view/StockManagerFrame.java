package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuBar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StockManagerFrame extends Application{

	private static final long serialVersionUID = 1L;
	
	private MenuBarGUI menuBar;
	private StockList stockList;
	private InfoPanel infoPanel;
	private AddProductFrame addProduct;
	private StockOrderTable orderTable;

	
	@Override
	public void start(Stage stage) {
			Scene scene = new Scene(new Group());
			stage.setTitle("Inventory Managment System");
			stage.setWidth(1000);
			stage.setHeight(800);
	        stage.setScene(new StockList(new Group()));
	        stage.show();
	 }

	
	public void showAddProductFrame(){
		addProduct = new AddProductFrame();
	}
	
	public AddProductFrame getProductFrame(){
		return addProduct;
	}
	
	public StockList getStockList(){
		return stockList;
	}
	
	public MenuBarGUI getMenuBarGUI(){
		return menuBar;
	}
	
	public InfoPanel getInfoPanel(){
		return infoPanel;
	}
	
	public StockOrderTable getOrderTable(){
		return orderTable;
	}
}
