package com.pori.WineBrewDB.Recipe;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pori.WineBrewDB.MainWindow;

import net.miginfocom.swing.MigLayout;

public class RecipePanel extends JPanel {

	private static final long serialVersionUID = 1332318777984759664L;
	public static JPanel RecipePanel;
	private static JLabel RecipeHeader;
	private static JLabel RecipeSubtitle;
	static JTabbedPane tabbedRecipePane;
	private static String RecipePanelStatus = "DeInitialized";

	//public RecipePanel() {
	public static void InitializePanel(){
		
		RecipePanel = new JPanel();
		RecipePanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		RecipeHeader = new JLabel("Recipe");
		RecipeHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		RecipePanel.add(RecipeHeader, "cell 0 0,grow");
		
		
		//Subtitle
		RecipeSubtitle = new JLabel("Browse and edit the Recipe database.");
		RecipeSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		RecipePanel.add(RecipeSubtitle, "cell 0 1,growx,aligny top");
		
		//TODO: Print recipe button
		
		
		//Tabbed Pane
		tabbedRecipePane = new JTabbedPane(JTabbedPane.TOP);
		RecipePanel.add(tabbedRecipePane, "cell 0 2,grow");
				
		
		//Recipe Search Tab
		RecipeSearchPanel.InitializePanel();		
		tabbedRecipePane.addTab("Search", null, RecipeSearchPanel.tabbedRecipeSearchPanel, null);
		
		
		//Recipe Data Tab
		RecipeDataPanel.InitializePanel();		
		tabbedRecipePane.addTab("Recipe Data", null, RecipeDataPanel.tabbedRecipeDataPanel, null);
		
		
		//Add New Recipe Tab
		RecipeAddPanel.InitializePanel();
		tabbedRecipePane.addTab("Add New Recipe", null, RecipeAddPanel.tabbedRecipeAddPanel, null);
		
		
		//Set some tabs disabled initially
		tabbedRecipePane.setEnabledAt(1, false);
		
	   	
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
			RecipePanel.remove(tabbedRecipePane);
			tabbedRecipePane.remove(RecipeSearchPanel.tabbedRecipeSearchPanel);
			tabbedRecipePane.remove(RecipeDataPanel.tabbedRecipeDataPanel);
			tabbedRecipePane.remove(RecipeAddPanel.tabbedRecipeAddPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(RecipePanel);
			RecipePanelStatus = "DeInitialized";
		}
	}
	

}