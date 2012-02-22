package com.pori.WineBrewDB;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class FruitAcidsPanel extends JPanel {

	private static final long serialVersionUID = 4458265931731794998L;
	public static JPanel FruitAcidsPanel;
	public static JScrollPane FruitAcidsScrollPane;
	public static JEditorPane FruitAcidsText;
	public static String FruitAcidsPanelStatus = "DeInitialized";

	//public FruitAcidsPanel() {
	public static void InitializePanel(){
		
		FruitAcidsPanel = new JPanel();
		FruitAcidsPanel.setBackground(Color.WHITE);
		FruitAcidsPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
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

		
		//Add it all to the main panel
		FruitAcidsPanel.add(FruitAcidsScrollPane, "cell 0 0,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(FruitAcidsPanel, "cell 0 0,grow");
		FruitAcidsPanel.setVisible(false);

		FruitAcidsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(FruitAcidsPanelStatus.equals("Initialized")) {
			FruitAcidsPanel.setVisible(false);
			FruitAcidsPanel.remove(FruitAcidsScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(FruitAcidsPanel);
			FruitAcidsPanelStatus = "DeInitialized";
		}
	}
	

}