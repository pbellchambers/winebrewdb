package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecipePanel extends JPanel {
	
	public static JTextField txtRecipePanel;
	public static JPanel RecipePanel;

	public static void InitializePanel() {
		
		RecipePanel = new JPanel();
		RecipePanel.setBackground(Color.WHITE);
		RecipePanel.setVisible(false);
		RecipePanel.setBounds(0, 0, 776, 647);
		InitializeMenu.ContentPane.add(RecipePanel);
		
		txtRecipePanel = new JTextField();
		txtRecipePanel.setText("RecipePanel");
		RecipePanel.add(txtRecipePanel);
		txtRecipePanel.setColumns(10);

	}

}