package uk.co.pbellchambers.winebrewdb.information;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.MainWindow;
import uk.co.pbellchambers.winebrewdb.sqlite.DBEngine;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.Font;
import java.util.Vector;

/**
 * This is the main information panel that contains all of the sub panels in a tab layout.
 * 
 * @author Paul.Bellchambers
 *
 */
public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 3331794777984753664L;
	public static JPanel InformationPanel;
	private static JLabel InformationHeader;
	private static JLabel InformationSubtitle;
	public static JTabbedPane tabbedInformationPane;
	private static Vector<Vector<Object>> InformationTabData;
	private static String InformationPanelStatus = "DeInitialised";

	/**
	 * Initialises all the information panels (including dynamically adding tabs and getting all data) so that they are displayed on screen.
	 */
	public static void initialisePanel(){
		
		InformationPanel = new JPanel();
		InformationPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		InformationHeader = new JLabel("Information");
		InformationHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		InformationPanel.add(InformationHeader, "cell 0 0,grow");
		
		
		//Subtitle
		InformationSubtitle = new JLabel("Add your own notes and information to be displayed in tabs here.");
		InformationSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		InformationPanel.add(InformationSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Tabbed Pane
		tabbedInformationPane = new JTabbedPane(JTabbedPane.TOP);
		InformationPanel.add(tabbedInformationPane, "cell 0 2,grow");
		
		
		dynamicallyAddTabs();
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(InformationPanel, "cell 0 0,grow");
		InformationPanel.setVisible(false);

		
		InformationPanelStatus = "Initialised";
	}

	/**
	 * De-initialises all the information panels so that they are no longer displayed on screen.
	 */
	public static void deinitialisePanel(){
		if(InformationPanelStatus.equals("Initialised")) {
			InformationPanel.setVisible(false);
			tabbedInformationPane.removeAll();
			InformationPanel.removeAll();
			MainWindow.WineBrewDBFrame.getContentPane().remove(InformationPanel);
			InformationPanelStatus = "DeInitialised";
		}
	}
	
	/**
	 * Gets all of the information tab data from the database and dynamically creates and initialises a tab for each one.
	 */
	public static void dynamicallyAddTabs(){
		//Dynamically add tabs based on DB content
		try {
			InformationTabData = DBEngine.getInformationTabData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}		
		
		int InformationTabCount = InformationTabData.size();
		int InformationTabCurrentRow = 0;
		
		while(InformationTabCurrentRow < InformationTabCount){
			Vector<Object> InformationTabCurrentData = InformationTabData.get(InformationTabCurrentRow);
			
			JEditorPane TempEditor = new JEditorPane();
			TempEditor.setEditable(false);
			if(Integer.parseInt((String) InformationTabCurrentData.get(3)) == 1){
				HTMLEditorKit htmlkit = new HTMLEditorKit();
				TempEditor.setEditorKit(htmlkit);
			}
			TempEditor.setText((String) InformationTabCurrentData.get(2));			

			JScrollPane TempScrollPane = new JScrollPane(TempEditor);
			TempScrollPane.setBorder(null);
			TempScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			TempEditor.setCaretPosition(0);
			
			
			String TabName = (String) InformationTabCurrentData.get(1);
			tabbedInformationPane.addTab(TabName, null, TempScrollPane, null);
			
			InformationTabCurrentRow = InformationTabCurrentRow + 1;
		}
		
		//Add/Edit tab gets added at end
		InformationEditTab.initialisePanel();
		tabbedInformationPane.addTab("Add/Edit Tabs", new ImageIcon(InformationPanel.class.getResource("/uk/co/pbellchambers/winebrewdb/images/new.png")), InformationEditTab.tabbedInformationEditTab, null);
	}
	

}