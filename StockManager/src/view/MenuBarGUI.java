package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarGUI extends JMenuBar{

	private static final long serialVersionUID = 1L;
	private JMenu file;
	private JMenuItem simMode, addProduct;
	

	public MenuBarGUI(){
		
		 file = new JMenu("File");
	     file.setMnemonic(KeyEvent.VK_F);
	     	     
	     addProduct = new JMenuItem("Add Product");
	     file.add(addProduct);
	     
	     file.addSeparator();
	    
	     simMode = new JMenuItem("Simulation Mode");
	     file.add(simMode);
	     
	     this.add(file);
		
	}

	public void addProductListener(ActionListener al){
		addProduct.addActionListener(al);
	}
	
	public void addSimModeListener(ActionListener al){
		simMode.addActionListener(al);
	}
	
}
