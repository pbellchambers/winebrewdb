package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class BrewPanel extends JPanel {
	
	public static JTextField txtBrewpanel;
	public static JPanel BrewPanel;

	public static void InitializePanel() {
		
		BrewPanel = new JPanel();
		BrewPanel.setBackground(Color.WHITE);
		BrewPanel.setVisible(false);
		BrewPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(BrewPanel);
		
		txtBrewpanel = new JTextField();
		txtBrewpanel.setText("BrewPanel");
		BrewPanel.add(txtBrewpanel);
		txtBrewpanel.setColumns(10);

	}

}
