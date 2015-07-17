package main;

import controller.StockManagerController;
import view.StockManagerFrame;
import model.StockManager;

/**
 *Main class sets up the view and model, then sends the references to 
 *the controller.
 */
public class ApplicationLoader {

	public static void main(String[] args) {
		System.out.println("Hello");
		StockManagerFrame view = new StockManagerFrame();
		
		StockManager model = new StockManager();
		
		StockManagerController controller = new StockManagerController(model, view);

	}

}
