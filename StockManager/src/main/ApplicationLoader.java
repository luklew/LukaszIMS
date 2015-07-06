package main;

import javafx.application.Application;
import controller.StockManagerController;
import view.StockManagerFrame;
import model.StockManager;

public class ApplicationLoader {
	
	static Thread ui;

	public static void main(String[] args) {
		System.out.println("Hello");
		StockManagerFrame view = new StockManagerFrame();
	
		
		ui = new Thread(new Runnable() {
            @Override
            public void run() {
                	Application.launch(StockManagerFrame.class);
            }
        });
		
		
		ui.start();
		
		StockManager model = new StockManager();
		
		StockManagerController controller = new StockManagerController(model, view);

	}

}
