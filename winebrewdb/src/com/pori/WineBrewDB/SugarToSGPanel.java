package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SugarToSGPanel extends JPanel {
	
	private static final long serialVersionUID = 1908691761559070965L;
	public static JTextField txtSugarToSGPanel;
	public static JPanel SugarToSGPanel;
	public static String SugarToSGPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		SugarToSGPanel = new JPanel();
		SugarToSGPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(SugarToSGPanel, "cell 0 0,grow");
		
		txtSugarToSGPanel = new JTextField();
		txtSugarToSGPanel.setText("SugarToSGPanel");
		SugarToSGPanel.add(txtSugarToSGPanel);
		txtSugarToSGPanel.setColumns(10);
		SugarToSGPanel.setVisible(false);
		
		SugarToSGPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(SugarToSGPanelStatus.equals("Initialized")) {
			SugarToSGPanel.setVisible(false);
			SugarToSGPanel.remove(txtSugarToSGPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(SugarToSGPanel);
			SugarToSGPanelStatus = "DeInitialized";
		}
	}
	

}