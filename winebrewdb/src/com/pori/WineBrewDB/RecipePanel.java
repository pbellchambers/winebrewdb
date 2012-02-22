package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecipePanel extends JPanel {
	
	private static final long serialVersionUID = 339367543546800892L;
	public static JTextField txtRecipePanel;
	public static JPanel RecipePanel;
	public static String RecipePanelStatus = "DeInitialized";


	public static void InitializePanel() {
		
		RecipePanel = new JPanel();
		RecipePanel.setBackground(Color.WHITE);
		MainWindow.WineBrewDBFrame.getContentPane().add(RecipePanel, "cell 0 0,grow");
		
		txtRecipePanel = new JTextField();
		txtRecipePanel.setText("RecipePanel");
		RecipePanel.add(txtRecipePanel);
		txtRecipePanel.setColumns(10);
		RecipePanel.setVisible(false);
		
		RecipePanelStatus = "Initialized";
	}
	
	
	public static void DeInitializePanel(){
		if(RecipePanelStatus.equals("Initialized")) {
			RecipePanel.setVisible(false);
			RecipePanel.remove(txtRecipePanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(RecipePanel);
			RecipePanelStatus = "DeInitialized";
		}
	}
	

}