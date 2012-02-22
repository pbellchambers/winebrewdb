package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlcoholPanel extends JPanel {
	
	public static JTextField txtAlcoholPanel;
	public static JPanel AlcoholPanel;

	public static void InitializePanel() {
		
		AlcoholPanel = new JPanel();
		AlcoholPanel.setBackground(Color.WHITE);
		AlcoholPanel.setVisible(false);
		AlcoholPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(AlcoholPanel);
		
		txtAlcoholPanel = new JTextField();
		txtAlcoholPanel.setText("AlcoholPanel");
		AlcoholPanel.add(txtAlcoholPanel);
		txtAlcoholPanel.setColumns(10);

	}

}