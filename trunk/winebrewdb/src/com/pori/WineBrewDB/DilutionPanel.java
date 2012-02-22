package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DilutionPanel extends JPanel {
	
	private static final long serialVersionUID = -7364180897299028471L;
	public static JTextField txtDilutionPanel;
	public static JPanel DilutionPanel;
	public static String DilutionPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		DilutionPanel = new JPanel();
		DilutionPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(DilutionPanel, "cell 0 0,grow");
		
		txtDilutionPanel = new JTextField();
		txtDilutionPanel.setText("DilutionPanel");
		DilutionPanel.add(txtDilutionPanel);
		txtDilutionPanel.setColumns(10);
		DilutionPanel.setVisible(false);
		
		DilutionPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(DilutionPanelStatus.equals("Initialized")) {
			DilutionPanel.setVisible(false);
			DilutionPanel.remove(txtDilutionPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DilutionPanel);
			DilutionPanelStatus = "DeInitialized";
		}
	}
	

}