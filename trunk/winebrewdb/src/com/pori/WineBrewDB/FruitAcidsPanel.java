package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class FruitAcidsPanel extends JPanel {
	
	public static JTextField txtFruitAcidsPanel;
	public static JPanel FruitAcidsPanel;

	public static void InitializePanel() {
		
		FruitAcidsPanel = new JPanel();
		FruitAcidsPanel.setBackground(Color.WHITE);
		FruitAcidsPanel.setVisible(false);
		FruitAcidsPanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(FruitAcidsPanel);
		
		txtFruitAcidsPanel = new JTextField();
		txtFruitAcidsPanel.setText("FruitAcidsPanel");
		FruitAcidsPanel.add(txtFruitAcidsPanel);
		txtFruitAcidsPanel.setColumns(10);

	}

}