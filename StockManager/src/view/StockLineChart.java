package view;

import java.util.ArrayList;
import java.util.Random;

import model.Product;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StockLineChart extends Application {

	XYChart.Series series1;
	XYChart.Series series2;
	CategoryAxis xAxis;
	NumberAxis yAxis;
	LineChart<String, Number> lineChart;
	private ArrayList<Number> values;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Line Chart Sample");
		Platform.setImplicitExit(false);

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Stage is closing");
				// stage.hide();
			}
		});

		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		xAxis.setLabel("Days");
		lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Stock Monitoring");

		series1 = new XYChart.Series();
		series1.setName("Stock Levels");

		/*
		 * series1.getData().add(new XYChart.Data("Thur", 36));
		 * series1.getData().add(new XYChart.Data("Fri", 22));
		 * series1.getData().add(new XYChart.Data("Sat", 45));
		 * series1.getData().add(new XYChart.Data("Sun", 43));
		 * series1.getData().add(new XYChart.Data("Mon", 17));
		 * series1.getData().add(new XYChart.Data("Tue", 29));
		 * series1.getData().add(new XYChart.Data("Wed", 25));
		 */

		series2 = new XYChart.Series();
		series2.setName("Warning Threshold");

		/*
		 * series2.getData().add(new XYChart.Data("Thur", 10));
		 * series2.getData().add(new XYChart.Data("Fri", 10));
		 * series2.getData().add(new XYChart.Data("Sat", 10));
		 * series2.getData().add(new XYChart.Data("Sun", 10));
		 * series2.getData().add(new XYChart.Data("Mon", 10));
		 * series2.getData().add(new XYChart.Data("Tue", 10));
		 * series2.getData().add(new XYChart.Data("Wed", 10));
		 */


		lineChart.getData().addAll(series1, series2);
		Scene scene = new Scene(lineChart, 800, 600);

		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
					/*	Random rnd = new Random();
						XYChart.Series<String, Number> s = lineChart.getData()
								.get(0);
						s.getData().remove(0);
								s.getData().add(
										new LineChart.Data<String, Number>(Integer.toString(rnd.nextInt(100)), rnd.nextInt(100) ));*/
										addValsToChart();
					}
				});
		//addValsToChart2();
		stage.setScene(scene);
		stage.show();
	}

	public void addValsToChart() {
		System.out.println(values);
		System.out.println(values.get(0));
		int valueTest = (int)values.get(0);
		
		Random rnd = new Random();
		//series1.getData().add(new XYChart.Data("Thur", 50));
		series1.getData().add(new XYChart.Data("Fri", rnd.nextInt(50)));
		series1.getData().add(new XYChart.Data("Sat", rnd.nextInt(50)));
		series1.getData().add(new XYChart.Data("Sun", rnd.nextInt(50)));
		series1.getData().add(new XYChart.Data("Mon", rnd.nextInt(50)));
		series1.getData().add(new XYChart.Data("Tue", rnd.nextInt(50)));
		series1.getData().add(new XYChart.Data("Wed", rnd.nextInt(50)));

		series2.getData().add(new XYChart.Data("Thur", 10));
		series2.getData().add(new XYChart.Data("Fri", 10));
		series2.getData().add(new XYChart.Data("Sat", 10));
		series2.getData().add(new XYChart.Data("Sun", 10));
		series2.getData().add(new XYChart.Data("Mon", 10));
		series2.getData().add(new XYChart.Data("Tue", 10));
		series2.getData().add(new XYChart.Data("Wed", 10));
	

	}

	public void setChartValues(ArrayList<Number> vals) {
		System.out.println(values);
		this.values = vals;
		System.out.println(values);
	}

	public void addValues() {
		 addValsToChart();
	}
	

}
