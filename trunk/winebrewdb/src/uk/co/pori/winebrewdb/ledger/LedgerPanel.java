package uk.co.pori.winebrewdb.ledger;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.pori.winebrewdb.MainWindow;


import net.miginfocom.swing.MigLayout;

public class LedgerPanel extends JPanel {
	
	private static final long serialVersionUID = 4792318776984759664L;
	public static JPanel LedgerPanel;
	private static JLabel LedgerHeader;
	private static JLabel LedgerSubtitle;
	static JTabbedPane tabbedLedgerPane;
	private static String LedgerPanelStatus = "DeInitialized";
	public static JButton btnPrintLedger;

	//public LedgerPanel() {
	public static void InitializePanel(){
		
		LedgerPanel = new JPanel();
		LedgerPanel.setLayout(new MigLayout("", "[grow][65px:n:65px]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		LedgerHeader = new JLabel("Ledger");
		LedgerHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		LedgerPanel.add(LedgerHeader, "cell 0 0,grow");
		
		
		//Subtitle
		LedgerSubtitle = new JLabel("Browse and edit the Ledger data.");
		LedgerSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		LedgerPanel.add(LedgerSubtitle, "cell 0 1,growx,aligny top");
		
		//Print ledger button
		btnPrintLedger = new JButton();
		btnPrintLedger.setIcon(new ImageIcon(LedgerPanel.class.getResource("/uk/co/pori/winebrewdb/images/print.png")));
		btnPrintLedger.setToolTipText("Save ledger data to printable .pdf");
		btnPrintLedger.setEnabled(true);
		LedgerPanel.add(btnPrintLedger, "cell 1 0");
		
		//Tabbed Pane
		tabbedLedgerPane = new JTabbedPane(JTabbedPane.TOP);
		LedgerPanel.add(tabbedLedgerPane, "cell 0 2 2,grow");
				
		
		//Ledger Search Tab
		LedgerEquipmentPanel.InitializePanel();		
		tabbedLedgerPane.addTab("Equipment & Non-brew-specific Costs", null, LedgerEquipmentPanel.tabbedLedgerEquipmentCostPanel, null);
		
		
		//Ledger Data Tab
		LedgerBrewCostSearchPanel.InitializePanel();		
		tabbedLedgerPane.addTab("Brew Cost Search", null, LedgerBrewCostSearchPanel.tabbedLedgerBrewCostSearchPanel, null);
		
		
		//Add New Ledger Tab
		LedgerBrewCostDataPanel.InitializePanel();
		tabbedLedgerPane.addTab("Brew Cost Data", null, LedgerBrewCostDataPanel.tabbedLedgerBrewCostPanel, null);
		
		
		//Set some tabs disabled initially
		tabbedLedgerPane.setEnabledAt(2, false);
		
	   	
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(LedgerPanel, "cell 0 0,grow");
		LedgerPanel.setVisible(false);

		
		LedgerPanelStatus = "Initialized";
		
		
		//Add print button listener
		btnPrintLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(MainWindow.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {			    	     	  
					String pdflocation = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".pdf";
					LedgerPDF.createPDF(pdflocation);	    	  
			    	  
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }				
				
			}
		});
		
		tabbedLedgerPane.addChangeListener(new ChangeListener() {
		    // This method is called whenever the selected tab changes
		    public void stateChanged(ChangeEvent evt) {
		        JTabbedPane pane = (JTabbedPane)evt.getSource();

		        // Get current tab
		        int sel = pane.getSelectedIndex();
		        if(sel == 2){
		        	btnPrintLedger.setVisible(false);
		        	btnPrintLedger.setEnabled(false);
		        }else{
		        	btnPrintLedger.setVisible(true);
		        	btnPrintLedger.setEnabled(true);
		        }
		    }
		});
		
	}

	
	public static void DeInitializePanel(){
		if(LedgerPanelStatus.equals("Initialized")) {
			LedgerPanel.setVisible(false);
			LedgerPanel.remove(LedgerHeader);
			LedgerPanel.remove(LedgerSubtitle);
			LedgerPanel.remove(tabbedLedgerPane);
			LedgerPanel.remove(btnPrintLedger);
			tabbedLedgerPane.remove(LedgerEquipmentPanel.tabbedLedgerEquipmentCostPanel);
			tabbedLedgerPane.remove(LedgerBrewCostSearchPanel.tabbedLedgerBrewCostSearchPanel);
			tabbedLedgerPane.remove(LedgerBrewCostDataPanel.tabbedLedgerBrewCostPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(LedgerPanel);
			LedgerPanelStatus = "DeInitialized";
		}
	}
	

}