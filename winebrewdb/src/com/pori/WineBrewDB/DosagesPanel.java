package com.pori.WineBrewDB;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

public class DosagesPanel extends JPanel {

	private static final long serialVersionUID = -7871618682975760889L;
	public static JPanel DosagesPanel;
	public static JScrollPane DosagesScrollPane;
	public static JEditorPane DosagesText;
	public static JLabel DosagesHeader;
	public static JLabel DosagesSubtitle;
	public static String DosagesPanelStatus = "DeInitialized";

	//public DosagesPanel() {
	public static void InitializePanel(){
		
		DosagesPanel = new JPanel();
		DosagesPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		//Header
		DosagesHeader = new JLabel("Dosages");
		DosagesHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		DosagesPanel.add(DosagesHeader, "cell 0 0,grow");
		//Subtitle
		DosagesSubtitle = new JLabel("Recommended dosages for various chemicals/additives.");
		DosagesSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		DosagesPanel.add(DosagesSubtitle, "cell 0 1,growx,aligny top");
		
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
		DosagesPanel.add(DosagesScrollPane, "cell 0 2,grow");

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(DosagesPanel, "cell 0 0,grow");
		DosagesPanel.setVisible(false);

		DosagesPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(DosagesPanelStatus.equals("Initialized")) {
			DosagesPanel.setVisible(false);
			DosagesPanel.remove(DosagesHeader);
			DosagesPanel.remove(DosagesSubtitle);
			DosagesPanel.remove(DosagesScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DosagesPanel);
			DosagesPanelStatus = "DeInitialized";
		}
	}
	

}