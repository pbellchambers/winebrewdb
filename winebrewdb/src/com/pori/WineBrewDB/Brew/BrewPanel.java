package com.pori.WineBrewDB.Brew;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pori.WineBrewDB.MainWindow;

import net.miginfocom.swing.MigLayout;

public class BrewPanel extends JPanel {

	private static final long serialVersionUID = 6339618777984753664L;
	public static JPanel BrewPanel;
	private static JLabel BrewHeader;
	private static JLabel BrewSubtitle;
	static JTabbedPane tabbedBrewPane;
	private static String BrewPanelStatus = "DeInitialized";
	public static JButton btnPrintBrew;

	//public BrewPanel() {
	public static void InitializePanel(){
		
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
		btnPrintBrew.setIcon(new ImageIcon(BrewPanel.class.getResource("/com/pori/WineBrewDB/Images/print.png")));
		btnPrintBrew.setToolTipText("Save currently selected brew data to printable .pdf");
		BrewPanel.setVisible(true);
		btnPrintBrew.setEnabled(false);
		BrewPanel.add(btnPrintBrew, "cell 1 0");
		
		//Tabbed Pane
		tabbedBrewPane = new JTabbedPane(JTabbedPane.TOP);
		BrewPanel.add(tabbedBrewPane, "cell 0 2 2,grow");
				
		
		//Brew Search Tab
		BrewSearchPanel.InitializePanel();		
		tabbedBrewPane.addTab("Search", null, BrewSearchPanel.tabbedBrewSearchPanel, null);
		
		
		//Brew Data Tab
		BrewDataPanel.InitializePanel();		
		tabbedBrewPane.addTab("Brew Data", null, BrewDataPanel.tabbedBrewDataPanel, null);
		
		
		//Brew Notes Tab
		BrewNotesPanel.InitializePanel();
		tabbedBrewPane.addTab("Brew Notes", null, BrewNotesPanel.tabbedBrewNotesPanel, null);
		
		
		//Brew Pictures Tab
		BrewPicturesPanel.InitializePanel();
		tabbedBrewPane.addTab("Brew Pictures", null, BrewPicturesPanel.tabbedBrewPicturesPanel, null);
		
		
		//Add New Brew Tab
		BrewAddPanel.InitializePanel();
		tabbedBrewPane.addTab("Add New Brew", null, BrewAddPanel.tabbedBrewAddPanel, null);
		
		
		//Set some tabs disabled initially
		tabbedBrewPane.setEnabledAt(1, false);
		tabbedBrewPane.setEnabledAt(2, false);
		tabbedBrewPane.setEnabledAt(3, false);
		
	   	
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(BrewPanel, "cell 0 0,grow");
		BrewPanel.setVisible(false);

		
		BrewPanelStatus = "Initialized";
		
		
		//Add print button listener
		btnPrintBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(MainWindow.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {			    	     	  
					String pdflocation = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".pdf";
					BrewPDF.createPDF(pdflocation);	    	  
			    	  
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }				
				
			}
		});
	}

	
	public static void DeInitializePanel(){
		if(BrewPanelStatus.equals("Initialized")) {
			BrewPanel.setVisible(false);
			BrewPanel.remove(BrewHeader);
			BrewPanel.remove(BrewSubtitle);
			BrewPanel.remove(btnPrintBrew);
			BrewPanel.remove(tabbedBrewPane);
			tabbedBrewPane.remove(BrewSearchPanel.tabbedBrewSearchPanel);
			tabbedBrewPane.remove(BrewDataPanel.tabbedBrewDataPanel);
			tabbedBrewPane.remove(BrewNotesPanel.tabbedBrewNotesPanel);
			tabbedBrewPane.remove(BrewPicturesPanel.tabbedBrewPicturesPanel);
			tabbedBrewPane.remove(BrewAddPanel.tabbedBrewAddPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(BrewPanel);
			BrewPanelStatus = "DeInitialized";
		}
	}
	

}