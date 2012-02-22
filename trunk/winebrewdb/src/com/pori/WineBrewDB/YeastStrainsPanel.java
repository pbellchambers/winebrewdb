package com.pori.WineBrewDB;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class YeastStrainsPanel extends JPanel {

	private static final long serialVersionUID = 2117258858258100582L;
	public static JPanel YeastStrainsPanel;
	public static JScrollPane YeastStrainsScrollPane;
	public static JEditorPane YeastStrainsText;
	public static JLabel YeastStrainsHeader;
	public static JLabel YeastStrainsSubtitle;
	public static String YeastStrainsPanelStatus = "DeInitialized";

	//public YeastStrainsPanel() {
	public static void InitializePanel(){
		
		YeastStrainsPanel = new JPanel();
		YeastStrainsPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		//Header
		YeastStrainsHeader = new JLabel("Yeast Strains");
		YeastStrainsHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		YeastStrainsPanel.add(YeastStrainsHeader, "cell 0 0,grow");
		//Subtitle
		YeastStrainsSubtitle = new JLabel("Commonly used homebrew yeast strains, their equivalents and suggested uses.");
		YeastStrainsSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		YeastStrainsPanel.add(YeastStrainsSubtitle, "cell 0 1,growx,aligny top");
		
		//Text area that gets text from external html
		YeastStrainsText = new JEditorPane();
		YeastStrainsText.setEditable(false);
		java.net.URL helpURL = YeastStrainsPanel.class.getResource("/com/pori/WineBrewDB/HTMLContent/YeastStrains.html");
		if (helpURL != null) {
			try {YeastStrainsText.setPage(helpURL);
			} catch (IOException e) {
				System.err.println("Attempted to read a bad URL: " + helpURL);
			}
		} else {
			System.err.println("Couldn't find file: " + helpURL);
		}
		
		//Put it in a scrollpane
		YeastStrainsScrollPane = new JScrollPane(YeastStrainsText);
		YeastStrainsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		//Add it all to the main panel
		YeastStrainsPanel.add(YeastStrainsScrollPane, "cell 0 2,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(YeastStrainsPanel, "cell 0 0,grow");
		YeastStrainsPanel.setVisible(false);

		YeastStrainsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(YeastStrainsPanelStatus.equals("Initialized")) {
			YeastStrainsPanel.setVisible(false);
			YeastStrainsPanel.remove(YeastStrainsHeader);
			YeastStrainsPanel.remove(YeastStrainsSubtitle);
			YeastStrainsPanel.remove(YeastStrainsScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(YeastStrainsPanel);
			YeastStrainsPanelStatus = "DeInitialized";
		}
	}
	

}