package com.pori.WineBrewDB;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.table.DefaultTableModel;

import com.pori.WineBrewDB.SQLite.DBEngine;

public class BrewPanel extends JPanel {

	private static final long serialVersionUID = 6339618777984753664L;
	public static JPanel BrewPanel;
	public static JEditorPane BrewText;
	public static JLabel BrewHeader;
	public static JLabel BrewSubtitle;
	public static JTextField txtBrewPanel;
	public static JTable BrewTable;
	public static JScrollPane BrewScrollPane;
	public static String BrewPanelStatus = "DeInitialized";

	//public BrewPanel() {
	public static void InitializePanel(){
		
		BrewPanel = new JPanel();
		BrewPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		BrewHeader = new JLabel("Brew");
		BrewHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		BrewPanel.add(BrewHeader, "cell 0 0,grow");
		
		
		//Subtitle
		BrewSubtitle = new JLabel("Search for brews.");
		BrewSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		BrewPanel.add(BrewSubtitle, "cell 0 1,growx,aligny top");
		
		//Get data for table
	    Vector<Vector<String>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getBrews();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    //Create header for the table
	    header = new Vector<String>();
	    header.add("BrewRef");
	    header.add("BrewName");
	    header.add("DatePlanned");
	    header.add("DateStarted");
	    header.add("DateBottled");
	    header.add("RecipeFrom");
	    header.add("Yeast");
	    header.add("StartSG");
	    header.add("StartAdjustedSG");
	    header.add("EndSG");
	    header.add("AimedABV");
	    header.add("FinalABV");
	    header.add("FinalAdjustedABV");
	    header.add("Notes");
	    header.add("TastingNotes");
	    header.add("ThumbsUp");
	    header.add("InPlanning");
	    header.add("InFermenting");
	    header.add("InFining");
	    header.add("InMaturing");
	    header.add("InBottles");
	    header.add("Drunk");
	    header.add("VolumeMade");
	    header.add("NumberBottles");
	    header.add("Colour");
	    
		//Table
		BrewTable = new JTable();
		BrewTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = 6716082729584843250L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
		//TODO: Size columns, pick which ones visible in initial table too.
		//BrewTable.getColumnModel().getColumn(0).setPreferredWidth(760);
		//BrewTable.getColumnModel().getColumn(0).setMinWidth(16);
		//BrewTable.getColumnModel().getColumn(0).setMaxWidth(999999);
		//BrewTable.getColumnModel().getColumn(1).setPreferredWidth(760);
		//BrewTable.getColumnModel().getColumn(1).setMinWidth(16);
		//BrewTable.getColumnModel().getColumn(1).setMaxWidth(99999);
		BrewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//TODO: Double click table
		//Mouse Listener on JTable:
		//  myJTable.addMouseListener(new MouseAdapter() {
		//	   public void mouseClicked(MouseEvent e) {
		//	      if (e.getClickCount() == 2) {
		//	         JTable target = (JTable)e.getSource();
		//	         int row = target.getSelectedRow();
		//	         int column = target.getSelectedColumn();
		//	         // do some action
		//	         }
		//	   }
		//	});
		
		//TODO: Bottom panel with more info on selected cell
	    
	    //ScrollPane
	    BrewScrollPane = new JScrollPane();
	    BrewScrollPane.setViewportView(BrewTable);
	    BrewPanel.add(BrewScrollPane, "cell 0 2,grow");
	    
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(BrewPanel, "cell 0 0,grow");
		BrewPanel.setVisible(false);

		BrewPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(BrewPanelStatus.equals("Initialized")) {
			BrewPanel.setVisible(false);
			BrewPanel.remove(BrewHeader);
			BrewPanel.remove(BrewSubtitle);
			BrewPanel.remove(BrewTable);
			MainWindow.WineBrewDBFrame.getContentPane().remove(BrewPanel);
			BrewPanelStatus = "DeInitialized";
		}
	}
	

}