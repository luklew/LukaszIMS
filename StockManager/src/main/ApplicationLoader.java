package main;

import controller.StockManagerController;
import view.StockManagerFrame;
import model.StockManager;

public class ApplicationLoader {

	public static void main(String[] args) {
		System.out.println("Hello");
		StockManagerFrame view = new StockManagerFrame();
		
		StockManager model = new StockManager();
		
		StockManagerController controller = new StockManagerController(model, view);

	}

}
