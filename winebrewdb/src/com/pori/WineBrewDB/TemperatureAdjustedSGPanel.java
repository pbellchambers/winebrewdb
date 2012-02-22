package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperatureAdjustedSGPanel extends JPanel {
	
	private static final long serialVersionUID = -5846436969526655095L;
	public static JTextField txtTemperatureAdjustedSGPanel;
	public static JPanel TemperatureAdjustedSGPanel;
	public static String TemperatureAdjustedSGPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		TemperatureAdjustedSGPanel = new JPanel();
		TemperatureAdjustedSGPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(TemperatureAdjustedSGPanel, "cell 0 0,grow");
		
		txtTemperatureAdjustedSGPanel = new JTextField();
		txtTemperatureAdjustedSGPanel.setText("TemperatureAdjustedSGPanel");
		TemperatureAdjustedSGPanel.add(txtTemperatureAdjustedSGPanel);
		txtTemperatureAdjustedSGPanel.setColumns(10);
		TemperatureAdjustedSGPanel.setVisible(false);
		
		TemperatureAdjustedSGPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(TemperatureAdjustedSGPanelStatus.equals("Initialized")) {
			TemperatureAdjustedSGPanel.setVisible(false);
			TemperatureAdjustedSGPanel.remove(txtTemperatureAdjustedSGPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(TemperatureAdjustedSGPanel);
			TemperatureAdjustedSGPanelStatus = "DeInitialized";
		}
	}
	

}