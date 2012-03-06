package com.pori.WineBrewDB.Information;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

import com.pori.WineBrewDB.MainWindow;

public class YeastStrainsPanel extends JPanel {

	private static final long serialVersionUID = 2117258858258100582L;
	public static JScrollPane YeastStrainsScrollPane;
	public static JEditorPane YeastStrainsText;
	public static JLabel YeastStrainsHeader;
	public static JLabel YeastStrainsSubtitle;
	public static String YeastStrainsPanelStatus = "DeInitialized";

	//public YeastStrainsPanel() {
	public static void InitializePanel(){
		
	
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

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(YeastStrainsScrollPane, "cell 0 0,grow");
		YeastStrainsScrollPane.setVisible(false);

		YeastStrainsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(YeastStrainsPanelStatus.equals("Initialized")) {
			YeastStrainsScrollPane.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(YeastStrainsScrollPane);
			YeastStrainsPanelStatus = "DeInitialized";
		}
	}
	

}