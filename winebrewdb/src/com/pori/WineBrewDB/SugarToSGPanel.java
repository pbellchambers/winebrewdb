package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SugarToSGPanel extends JPanel {
	
	public static JTextField txtSugarToSGPanel;
	public static JPanel SugarToSGPanel;

	public static void InitializePanel() {
		
		SugarToSGPanel = new JPanel();
		SugarToSGPanel.setBackground(Color.WHITE);
		SugarToSGPanel.setVisible(false);
		SugarToSGPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(SugarToSGPanel);
		
		txtSugarToSGPanel = new JTextField();
		txtSugarToSGPanel.setText("SugarToSGPanel");
		SugarToSGPanel.add(txtSugarToSGPanel);
		txtSugarToSGPanel.setColumns(10);

	}

}