package uk.co.pori.winebrewdb2.recipe2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.pori.winebrewdb2.MainWindow;


import net.miginfocom.swing.MigLayout;

public class RecipePanel extends JPanel {

	private static final long serialVersionUID = 1332318777984759664L;
	public static JPanel RecipePanel;
	private static JLabel RecipeHeader;
	private static JLabel RecipeSubtitle;
	static JTabbedPane tabbedRecipePane;
	private static String RecipePanelStatus = "DeInitialized";
	public static JButton btnPrintRecipe;

	//public RecipePanel() {
	public static void InitializePanel(){
		
		RecipePanel = new JPanel();
		RecipePanel.setLayout(new MigLayout("", "[grow][65px:n:65px]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		RecipeHeader = new JLabel("Recipe");
		RecipeHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		RecipePanel.add(RecipeHeader, "cell 0 0,grow");
		
		
		//Subtitle
		RecipeSubtitle = new JLabel("Browse and edit the Recipe database.");
		RecipeSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		RecipePanel.add(RecipeSubtitle, "cell 0 1,growx,aligny top");
		
		//Print recipe button
		btnPrintRecipe = new JButton();
		btnPrintRecipe.setIcon(new ImageIcon(RecipePanel.class.getResource("/uk/co/pori/WineBrewDB/Images/print.png")));
		btnPrintRecipe.setToolTipText("Save currently selected recipe data to printable .pdf");
    	btnPrintRecipe.setVisible(false);
		btnPrintRecipe.setEnabled(false);
		RecipePanel.add(btnPrintRecipe, "cell 1 0");
		
		//Tabbed Pane
		tabbedRecipePane = new JTabbedPane(JTabbedPane.TOP);
		RecipePanel.add(tabbedRecipePane, "cell 0 2 2,grow");
				
		
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
		
		
		//Add print button listener
		btnPrintRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(MainWindow.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {			    	     	  
					String pdflocation = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".pdf";
					RecipePDF.createPDF(pdflocation);	    	  
			    	  
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }				
				
			}
		});
		
		
		tabbedRecipePane.addChangeListener(new ChangeListener() {
		    // This method is called whenever the selected tab changes
		    public void stateChanged(ChangeEvent evt) {
		        JTabbedPane pane = (JTabbedPane)evt.getSource();

		        // Get current tab
		        int sel = pane.getSelectedIndex();
		        if(sel == 0 || sel == 2){
		        	btnPrintRecipe.setVisible(false);
		        	btnPrintRecipe.setEnabled(false);
		        }else{
		        	btnPrintRecipe.setVisible(true);
		        	btnPrintRecipe.setEnabled(true);
		        }
		    }
		});
		
	}

	
	public static void DeInitializePanel(){
		if(RecipePanelStatus.equals("Initialized")) {
			RecipePanel.setVisible(false);
			RecipePanel.remove(RecipeHeader);
			RecipePanel.remove(RecipeSubtitle);
			RecipePanel.remove(tabbedRecipePane);
			RecipePanel.remove(btnPrintRecipe);
			tabbedRecipePane.remove(RecipeSearchPanel.tabbedRecipeSearchPanel);
			tabbedRecipePane.remove(RecipeDataPanel.tabbedRecipeDataPanel);
			tabbedRecipePane.remove(RecipeAddPanel.tabbedRecipeAddPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(RecipePanel);
			RecipePanelStatus = "DeInitialized";
		}
	}
	

}