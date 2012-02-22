package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class BrewPanel extends JPanel {
	
	private static final long serialVersionUID = -6365341164521003049L;
	public static JTextField txtBrewPanel;
	public static JPanel BrewPanel;
	public static String BrewPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		BrewPanel = new JPanel();
		BrewPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(BrewPanel, "cell 0 0,grow");
		
		txtBrewPanel = new JTextField();
		txtBrewPanel.setText("BrewPanel");
		BrewPanel.add(txtBrewPanel);
		txtBrewPanel.setColumns(10);
		BrewPanel.setVisible(false);
		
		BrewPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(BrewPanelStatus.equals("Initialized")) {
			BrewPanel.setVisible(false);
			BrewPanel.remove(txtBrewPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(BrewPanel);
			BrewPanelStatus = "DeInitialized";
		}
	}
	

}