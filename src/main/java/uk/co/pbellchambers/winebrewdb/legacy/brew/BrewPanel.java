package uk.co.pbellchambers.winebrewdb.legacy.brew;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.legacy.LegacyApp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the main brew panel that contains all of the sub panels in a tab layout.
 * 
 * @author Paul.Bellchambers
 *
 */
public class BrewPanel extends JPanel {

	private static final long serialVersionUID = 6339618777984753664L;
	public static JPanel BrewPanel;
	private static JLabel BrewHeader;
	private static JLabel BrewSubtitle;
	static JTabbedPane tabbedBrewPane;
	private static String BrewPanelStatus = "DeInitialised";
	public static JButton btnPrintBrew;

	/**
	 * Initialises all the brew panels (including getting all data) so that they are displayed on screen.
	 */
	public static void initialisePanel(){
		
		BrewPanel = new JPanel();
		BrewPanel.setLayout(new MigLayout("", "[grow][65px:n:65px]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		BrewHeader = new JLabel("Brew");
		BrewHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		BrewPanel.add(BrewHeader, "cell 0 0,grow");
		
		
		//Subtitle
		BrewSubtitle = new JLabel("Browse and edit the brew database.");
		BrewSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		BrewPanel.add(BrewSubtitle, "cell 0 1,growx,aligny top");
		
		btnPrintBrew = new JButton();
		btnPrintBrew.setIcon(new ImageIcon(BrewPanel.class.getResource("/images/print.png")));
		btnPrintBrew.setToolTipText("Save currently selected brew data to printable .pdf");
		BrewPanel.setVisible(true);
    	btnPrintBrew.setVisible(false);
		btnPrintBrew.setEnabled(false);
		BrewPanel.add(btnPrintBrew, "cell 1 0");
		
		//Tabbed Pane
		tabbedBrewPane = new JTabbedPane(JTabbedPane.TOP);
		BrewPanel.add(tabbedBrewPane, "cell 0 2 2,grow");
				
		
		//Brew Search Tab
		BrewSearchPanel.initialisePanel();		
		tabbedBrewPane.addTab("Search", null, BrewSearchPanel.tabbedBrewSearchPanel, null);
		
		
		//Brew Data Tab
		BrewDataPanel.initialisePanel();		
		tabbedBrewPane.addTab("Brew Data", null, BrewDataPanel.tabbedBrewDataPanel, null);
		
		
		//Brew Notes Tab
		BrewNotesPanel.initialisePanel();
		tabbedBrewPane.addTab("Brew Notes", null, BrewNotesPanel.tabbedBrewNotesPanel, null);
		
		
		//Brew Pictures Tab
		BrewPicturesPanel.initialisePanel();
		tabbedBrewPane.addTab("Brew Pictures", null, BrewPicturesPanel.tabbedBrewPicturesPanel, null);
		
		
		//Brew Pictures Tab
		BrewCostPanel.initialisePanel();
		tabbedBrewPane.addTab("Brew Costs", null, BrewCostPanel.tabbedBrewCostPanel, null);
				
		
		//Add New Brew Tab
		BrewAddPanel.initialisePanel();
		tabbedBrewPane.addTab("Add New Brew", new ImageIcon(BrewPanel.class.getResource("/images/new.png")), BrewAddPanel.tabbedBrewAddPanel, null);
		
		
		//Set some tabs disabled initially
		tabbedBrewPane.setEnabledAt(1, false);
		tabbedBrewPane.setEnabledAt(2, false);
		tabbedBrewPane.setEnabledAt(3, false);
		tabbedBrewPane.setEnabledAt(4, false);
		
	   	
		//Add it all to the main window
		LegacyApp.WineBrewDBFrame.getContentPane().add(BrewPanel, "cell 0 0,grow");
		BrewPanel.setVisible(false);

		
		BrewPanelStatus = "Initialised";
		
		
		//Add print button listener
		btnPrintBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(LegacyApp.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {			    	     	  
					String pdflocation = c.getCurrentDirectory().toString() + LegacyApp.OSSlash + c.getSelectedFile().getName() + ".pdf";
					BrewPDF.createPDF(pdflocation);	    	  
			    	  
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }				
				
			}
		});
		
		tabbedBrewPane.addChangeListener(new ChangeListener() {
		    // This method is called whenever the selected tab changes
		    public void stateChanged(ChangeEvent evt) {
		        JTabbedPane pane = (JTabbedPane)evt.getSource();

		        // Get current tab
		        int sel = pane.getSelectedIndex();
		        if(sel == 0 || sel == 5){
		        	btnPrintBrew.setVisible(false);
		    		btnPrintBrew.setEnabled(false);
		        }else{
		        	btnPrintBrew.setVisible(true);
		    		btnPrintBrew.setEnabled(true);
		        }
		    }
		});
	}

	/**
	 * De-initialises all the brew panels so that they are no longer displayed on screen.
	 */
	public static void deinitialisePanel(){
		if(BrewPanelStatus.equals("Initialised")) {
			BrewPanel.setVisible(false);
			tabbedBrewPane.removeAll();
			BrewPanel.removeAll();
			LegacyApp.WineBrewDBFrame.getContentPane().remove(BrewPanel);
			BrewPanelStatus = "DeInitialised";
		}
	}
	

}