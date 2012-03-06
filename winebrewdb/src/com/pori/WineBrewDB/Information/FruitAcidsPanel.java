package com.pori.WineBrewDB.Information;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

import com.pori.WineBrewDB.MainWindow;

public class FruitAcidsPanel extends JPanel {
	
	private static final long serialVersionUID = -6664688139205966142L;
	static JScrollPane FruitAcidsScrollPane;
	private static JEditorPane FruitAcidsText;
	private static String FruitAcidsPanelStatus = "DeInitialized";

	//public FruitAcidsPanel() {
	public static void InitializePanel(){
		
		
		//Text area that gets text from external html
		FruitAcidsText = new JEditorPane();
		FruitAcidsText.setEditable(false);
		java.net.URL helpURL = FruitAcidsPanel.class.getResource("/com/pori/WineBrewDB/HTMLContent/FruitAcids.html");
		if (helpURL != null) {
			try {FruitAcidsText.setPage(helpURL);
			} catch (IOException e) {
				System.err.println("Attempted to read a bad URL: " + helpURL);
			}
		} else {
			System.err.println("Couldn't find file: " + helpURL);
		}
		
		//Put it in a scrollpane
		FruitAcidsScrollPane = new JScrollPane(FruitAcidsText);
		FruitAcidsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(FruitAcidsScrollPane, "cell 0 0,grow");
		FruitAcidsScrollPane.setVisible(false);

		FruitAcidsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(FruitAcidsPanelStatus.equals("Initialized")) {
			FruitAcidsScrollPane.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(FruitAcidsScrollPane);
			FruitAcidsPanelStatus = "DeInitialized";
		}
	}
	

}