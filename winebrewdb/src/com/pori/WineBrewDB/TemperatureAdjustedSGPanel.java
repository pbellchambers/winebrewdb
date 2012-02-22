package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperatureAdjustedSGPanel extends JPanel {
	
	public static JTextField txtTemperatureAdjustedSGPanel;
	public static JPanel TemperatureAdjustedSGPanel;

	public static void InitializePanel() {
		
		TemperatureAdjustedSGPanel = new JPanel();
		TemperatureAdjustedSGPanel.setBackground(Color.WHITE);
		TemperatureAdjustedSGPanel.setVisible(false);
		TemperatureAdjustedSGPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(TemperatureAdjustedSGPanel);
		
		txtTemperatureAdjustedSGPanel = new JTextField();
		txtTemperatureAdjustedSGPanel.setText("TemperatureAdjustedSGPanel");
		TemperatureAdjustedSGPanel.add(txtTemperatureAdjustedSGPanel);
		txtTemperatureAdjustedSGPanel.setColumns(10);

	}

}