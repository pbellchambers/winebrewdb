package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeasuresPanel extends JPanel {
	
	private static final long serialVersionUID = -4135137590381773734L;
	public static JTextField txtMeasuresPanel;
	public static JPanel MeasuresPanel;
	public static String MeasuresPanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		MeasuresPanel = new JPanel();
		MeasuresPanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(MeasuresPanel, "cell 0 0,grow");
		
		txtMeasuresPanel = new JTextField();
		txtMeasuresPanel.setText("MeasuresPanel");
		MeasuresPanel.add(txtMeasuresPanel);
		txtMeasuresPanel.setColumns(10);
		MeasuresPanel.setVisible(false);
		
		MeasuresPanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(MeasuresPanelStatus.equals("Initialized")) {
			MeasuresPanel.setVisible(false);
			MeasuresPanel.remove(txtMeasuresPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(MeasuresPanel);
			MeasuresPanelStatus = "DeInitialized";
		}
	}
	

}