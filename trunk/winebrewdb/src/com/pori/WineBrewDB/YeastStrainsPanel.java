package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class YeastStrainsPanel extends JPanel {
	
	public static JTextField txtYeastStrainsPanel;
	public static JPanel YeastStrainsPanel;

	public static void InitializePanel() {
		
		YeastStrainsPanel = new JPanel();
		YeastStrainsPanel.setBackground(Color.WHITE);
		YeastStrainsPanel.setVisible(false);
		YeastStrainsPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(YeastStrainsPanel);
		
		txtYeastStrainsPanel = new JTextField();
		txtYeastStrainsPanel.setText("YeastStrainsPanel");
		YeastStrainsPanel.add(txtYeastStrainsPanel);
		txtYeastStrainsPanel.setColumns(10);

	}

}