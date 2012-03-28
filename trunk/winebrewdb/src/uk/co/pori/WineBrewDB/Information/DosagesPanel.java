package uk.co.pori.WineBrewDB.Information;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

import uk.co.pori.WineBrewDB.MainWindow;


public class DosagesPanel extends JPanel {

	private static final long serialVersionUID = -7871618682975760889L;
	static JScrollPane DosagesScrollPane;
	private static String DosagesPanelStatus = "DeInitialized";

	//public DosagesPanel() {
	public static void InitializePanel(){
		
		
		//Text area that gets text from external html
		JEditorPane DosagesText = new JEditorPane();
		DosagesText.setEditable(false);
		java.net.URL helpURL = DosagesPanel.class.getResource("/uk/co/pori/WineBrewDB/HTMLContent/Dosages.html");
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
		DosagesScrollPane.setBorder(null);
		DosagesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(DosagesScrollPane, "cell 0 0,grow");
		DosagesScrollPane.setVisible(false);

		DosagesPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(DosagesPanelStatus.equals("Initialized")) {
			DosagesScrollPane.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DosagesScrollPane);
			DosagesPanelStatus = "DeInitialized";
		}
	}
	

}