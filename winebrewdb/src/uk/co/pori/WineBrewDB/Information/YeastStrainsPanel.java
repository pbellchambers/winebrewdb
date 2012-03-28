package uk.co.pori.WineBrewDB.Information;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

import uk.co.pori.WineBrewDB.MainWindow;


public class YeastStrainsPanel extends JPanel {

	private static final long serialVersionUID = 2117258858258100582L;
	static JScrollPane YeastStrainsScrollPane;
	private static String YeastStrainsPanelStatus = "DeInitialized";

	//public YeastStrainsPanel() {
	public static void InitializePanel(){
		
	
		//Text area that gets text from external html
		JEditorPane YeastStrainsText = new JEditorPane();
		YeastStrainsText.setEditable(false);
		java.net.URL helpURL = YeastStrainsPanel.class.getResource("/uk/co/pori/WineBrewDB/HTMLContent/YeastStrains.html");
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
		YeastStrainsScrollPane.setBorder(null);
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