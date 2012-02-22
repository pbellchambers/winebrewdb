package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DilutionPanel extends JPanel {
	
	public static JTextField txtDilutionPanel;
	public static JPanel DilutionPanel;

	public static void InitializePanel() {
		
		DilutionPanel = new JPanel();
		DilutionPanel.setBackground(Color.WHITE);
		DilutionPanel.setVisible(false);
		DilutionPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(DilutionPanel);
		
		txtDilutionPanel = new JTextField();
		txtDilutionPanel.setText("DilutionPanel");
		DilutionPanel.add(txtDilutionPanel);
		txtDilutionPanel.setColumns(10);

	}

}