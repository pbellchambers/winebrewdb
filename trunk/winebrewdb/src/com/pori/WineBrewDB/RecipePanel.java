package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class RecipePanel extends JPanel {

	private static final long serialVersionUID = -7290586613655558447L;
	public static JPanel RecipePanel;
	public static JEditorPane RecipeText;
	public static JLabel RecipeHeader;
	public static JLabel RecipeSubtitle;
	public static JTextField txtRecipePanel;
	public static String RecipePanelStatus = "DeInitialized";

	//public RecipePanel() {
	public static void InitializePanel(){
		
		RecipePanel = new JPanel();
		RecipePanel.setLayout(new MigLayout("", "[grow][100px:n:100px]", "[20px:n:20px][25px:n:25px][grow][50px:n:50px]"));
		
		
		//Header
		RecipeHeader = new JLabel("Recipe");
		RecipeHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		RecipePanel.add(RecipeHeader, "cell 0 0,grow");
		
		
		//Subtitle
		RecipeSubtitle = new JLabel("Search for recipes.");
		RecipeSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		RecipePanel.add(RecipeSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Some content
		txtRecipePanel = new JTextField();
		txtRecipePanel.setBackground(Color.WHITE);
		txtRecipePanel.setText("RecipePanel - Something will go here to search for/filter recipes entered in the database, and view their details.");
		txtRecipePanel.setColumns(10);
		RecipePanel.add(txtRecipePanel, "cell 0 2,grow");
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(RecipePanel, "cell 0 0,grow");
		RecipePanel.setVisible(false);

		RecipePanelStatus = "Initialized";
		
	}
	

	public static void DeInitializePanel(){
		if(RecipePanelStatus.equals("Initialized")) {
			RecipePanel.setVisible(false);
			RecipePanel.remove(RecipeHeader);
			RecipePanel.remove(RecipeSubtitle);
			RecipePanel.remove(txtRecipePanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(RecipePanel);
			RecipePanelStatus = "DeInitialized";
		}
	}
	

}