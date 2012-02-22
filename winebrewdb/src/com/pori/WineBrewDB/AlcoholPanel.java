package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlcoholPanel extends JPanel {
	
	private static final long serialVersionUID = 4564836491676829876L;
	public static JTextField txtAlcoholPanel;
	public static JPanel AlcoholPanel;
	public static String AlcoholPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		AlcoholPanel = new JPanel();
		AlcoholPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(AlcoholPanel, "cell 0 0,grow");
		
		txtAlcoholPanel = new JTextField();
		txtAlcoholPanel.setText("AlcoholPanel");
		AlcoholPanel.add(txtAlcoholPanel);
		txtAlcoholPanel.setColumns(10);
		AlcoholPanel.setVisible(false);
		
		AlcoholPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(AlcoholPanelStatus.equals("Initialized")) {
			AlcoholPanel.setVisible(false);
			AlcoholPanel.remove(txtAlcoholPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(AlcoholPanel);
			AlcoholPanelStatus = "DeInitialized";
		}
	}
	

}