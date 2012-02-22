package com.pori.WineBrewDB;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class YeastStrainsPanel extends JPanel {

	private static final long serialVersionUID = 4458265931731794998L;
	public static JPanel YeastStrainsPanel;
	public static JScrollPane YeastStrainsScrollPane;
	public static JEditorPane YeastStrainsText;
	public static String YeastStrainsPanelStatus = "DeInitialized";

	//public YeastStrainsPanel() {
	public static void InitializePanel(){
		
		YeastStrainsPanel = new JPanel();
		YeastStrainsPanel.setBackground(Color.WHITE);
		YeastStrainsPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
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
		YeastStrainsPanel.add(YeastStrainsScrollPane, "cell 0 0,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(YeastStrainsPanel, "cell 0 0,grow");
		YeastStrainsPanel.setVisible(false);

		YeastStrainsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(YeastStrainsPanelStatus.equals("Initialized")) {
			YeastStrainsPanel.setVisible(false);
			YeastStrainsPanel.remove(YeastStrainsScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(YeastStrainsPanel);
			YeastStrainsPanelStatus = "DeInitialized";
		}
	}
	

}