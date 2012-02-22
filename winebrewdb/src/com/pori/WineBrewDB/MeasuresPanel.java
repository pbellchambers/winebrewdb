package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MeasuresPanel extends JPanel {
	
	public static JTextField txtMeasuresPanel;
	public static JPanel MeasuresPanel;

	public static void InitializePanel() {
		
		MeasuresPanel = new JPanel();
		MeasuresPanel.setBackground(Color.WHITE);
		MeasuresPanel.setVisible(false);
		MeasuresPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(MeasuresPanel);
		
		txtMeasuresPanel = new JTextField();
		txtMeasuresPanel.setText("MeasuresPanel");
		MeasuresPanel.add(txtMeasuresPanel);
		txtMeasuresPanel.setColumns(10);

	}

}