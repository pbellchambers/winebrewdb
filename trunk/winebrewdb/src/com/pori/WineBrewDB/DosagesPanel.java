package com.pori.WineBrewDB;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class DosagesPanel extends JPanel {

	private static final long serialVersionUID = 4458265931731794998L;
	public static JPanel DosagesPanel;
	public static JScrollPane DosagesScrollPane;
	public static JEditorPane DosagesText;
	public static String DosagesPanelStatus = "DeInitialized";

	//public DosagesPanel() {
	public static void InitializePanel(){
		
		DosagesPanel = new JPanel();
		DosagesPanel.setBackground(Color.WHITE);
		DosagesPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Text area that gets text from external html
		DosagesText = new JEditorPane();
		DosagesText.setEditable(false);
		java.net.URL helpURL = DosagesPanel.class.getResource("/com/pori/WineBrewDB/HTMLContent/Dosages.html");
		if (helpURL != null) {
			try {DosagesText.setPage(helpURL);
			} catch (IOException e) {
				System.err.println("Attempted to read a bad URL: " + helpURL);
			}
		} else {
			System.err.println("Couldn't find file: " + helpURL);
		}
		
		//Put it in a scrollpane
		DosagesScrollPane = new JScrollPane(DosagesText);
		DosagesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		//Add it all to the main panel
		DosagesPanel.add(DosagesScrollPane, "cell 0 0,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(DosagesPanel, "cell 0 0,grow");
		DosagesPanel.setVisible(false);

		DosagesPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(DosagesPanelStatus.equals("Initialized")) {
			DosagesPanel.setVisible(false);
			DosagesPanel.remove(DosagesScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DosagesPanel);
			DosagesPanelStatus = "DeInitialized";
		}
	}
	

}