package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarGUI extends JMenuBar{

	private static final long serialVersionUID = 1L;
	private JMenu file, charts;
	private JMenuItem simMode, addProduct, saveToFile, showLineChart;
	

	public MenuBarGUI(){
		
		 file = new JMenu("File");
	     file.setMnemonic(KeyEvent.VK_F);
	     	     
	     addProduct = new JMenuItem("Add Product");
	     file.add(addProduct);
	     
	     saveToFile = new JMenuItem("Save Report");
	     file.add(saveToFile);
	     
	     file.addSeparator();
	    
	     simMode = new JMenuItem("Simulation Mode");
	     file.add(simMode);
	     
	     this.add(file);
	     
	     charts = new JMenu("Charts");
	     showLineChart = new JMenuItem("Stock Line Chart");
	     charts.add(showLineChart);
	     
	     this.add(charts);
		
	}

	public void addProductListener(ActionListener al){
		addProduct.addActionListener(al);
	}
	
	public void addSaveToFileListener(ActionListener al){
		saveToFile.addActionListener(al);
	}
	
	public void addSimModeListener(ActionListener al){
		simMode.addActionListener(al);
	}
	
	public void addStockLineChartListener(ActionListener al){
		showLineChart.addActionListener(al);
	}
	
}
