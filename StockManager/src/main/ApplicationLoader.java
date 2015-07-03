package main;

import javafx.application.Application;
import controller.StockManagerController;
import view.StockManagerFrame;
import model.StockManager;

public class ApplicationLoader {

	public static void main(String[] args) {
		System.out.println("Hello");
		StockManagerFrame view = new StockManagerFrame();
		Application.launch(StockManagerFrame.class);//.launch(StockManagerFrame.class);
		//StockManagerFrame view = new StockManagerFrame(args);
		
		StockManager model = new StockManager();
		
		StockManagerController controller = new StockManagerController(model, view);

	}

}
