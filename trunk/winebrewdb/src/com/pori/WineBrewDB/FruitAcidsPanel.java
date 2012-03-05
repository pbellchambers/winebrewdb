package com.pori.WineBrewDB;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class FruitAcidsPanel extends JPanel {
	
	private static final long serialVersionUID = -6664688139205966142L;
	public static JPanel FruitAcidsPanel;
	public static JScrollPane FruitAcidsScrollPane;
	public static JEditorPane FruitAcidsText;
	public static JLabel FruitAcidsHeader;
	public static JLabel FruitAcidsSubtitle;
	public static String FruitAcidsPanelStatus = "DeInitialized";

	//public FruitAcidsPanel() {
	public static void InitializePanel(){
		
		FruitAcidsPanel = new JPanel();
		FruitAcidsPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		//Header
		FruitAcidsHeader = new JLabel("Fruit Acids");
		FruitAcidsHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		FruitAcidsPanel.add(FruitAcidsHeader, "cell 0 0,grow");
		//Subtitle
		FruitAcidsSubtitle = new JLabel("A list of the predominant acid in most common fruits/vegetables.");
		FruitAcidsSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		FruitAcidsPanel.add(FruitAcidsSubtitle, "cell 0 1,growx,aligny top");
		
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
		FruitAcidsPanel.add(FruitAcidsScrollPane, "cell 0 2,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(FruitAcidsPanel, "cell 0 0,grow");
		FruitAcidsPanel.setVisible(false);

		FruitAcidsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(FruitAcidsPanelStatus.equals("Initialized")) {
			FruitAcidsPanel.setVisible(false);
			FruitAcidsPanel.remove(FruitAcidsHeader);
			FruitAcidsPanel.remove(FruitAcidsSubtitle);
			FruitAcidsPanel.remove(FruitAcidsScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(FruitAcidsPanel);
			FruitAcidsPanelStatus = "DeInitialized";
		}
	}
	

}