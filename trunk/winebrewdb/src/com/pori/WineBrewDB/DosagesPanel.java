package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DosagesPanel extends JPanel {
	
	public static JTextField txtDosagesPanel;
	public static JPanel DosagesPanel;

	public static void InitializePanel() {
		
		DosagesPanel = new JPanel();
		DosagesPanel.setBackground(Color.WHITE);
		DosagesPanel.setVisible(false);
		DosagesPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(DosagesPanel);
		
		txtDosagesPanel = new JTextField();
		txtDosagesPanel.setText("DosagesPanel");
		DosagesPanel.add(txtDosagesPanel);
		txtDosagesPanel.setColumns(10);

	}

}