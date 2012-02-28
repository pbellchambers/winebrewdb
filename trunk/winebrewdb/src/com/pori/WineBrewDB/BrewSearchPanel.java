package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;


public class BrewSearchPanel extends JPanel {
	

	private static final long serialVersionUID = 1271175051561946054L;
	public static JPanel tabbedBrewSearchPanel;
	public static JPanel BrewFilterPanel;
	public static JScrollPane BrewScrollPane;
	public static JTable BrewTable;
	public static String SelectedBrewRef;
	public static String SelectedBrewName;
	public static String SelectedDatePlanned;
	public static String SelectedDateStarted;
	public static String SelectedDateBottled;
	public static String SelectedRecipeFrom;
	public static String SelectedYeast;
	public static String SelectedStartSG;
	public static String SelectedStartAdjustedSG;
	public static String SelectedEndSG;
	public static String SelectedAimedABV;
	public static String SelectedFinalABV;
	public static String SelectedFinalAdjustedABV;
	public static String SelectedNotes;
	public static String SelectedTastingNotes;
	public static String SelectedThumbsUp;
	public static String SelectedInPlanning;
	public static String SelectedInFermenting;
	public static String SelectedInFining;
	public static String SelectedInMaturing;
	public static String SelectedInBottles;
	public static String SelectedDrunk;
	public static String SelectedVolumeMade;
	public static String SelectedNumberBottles;
	public static String SelectedColour;
	public static JLabel lblBrewName;
	public static JLabel lblRecipeFrom;
	public static JLabel lblYeast;
	public static JLabel lblNotes;
	public static JLabel lblTastingNotes;
	public static JLabel lblColour;
	public static JTextField textBrewName;
	public static JTextField textRecipeFrom;
	public static JTextField textNotes;
	public static JTextField textTastingNotes;
	public static JTextField textYeast;
	public static JComboBox<String> comboColour;
	public static JLabel lblThumbs;
	public static JComboBox<String> comboThumbs;
	public static JCheckBox chckbxInPlanning;
	public static JCheckBox chckbxInFining;
	public static JCheckBox chckbxInFermenting;
	public static JCheckBox chckbxInBottles;
	public static JCheckBox chckbxInMaturing;
	public static JCheckBox chckbxDrunk;
	public static JButton btnShowEntireTable;
	public static JButton btnSearch;
	public static int BrewSearchSelectedRow;

	
	public static void InitializePanel(){
		
		//Main Panel
		tabbedBrewSearchPanel = new JPanel();
		tabbedBrewSearchPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewSearchPanel.setLayout(new MigLayout("", "[grow]", "[150px:n:150px][grow]"));
		
		//Filter Panel
		BrewFilterPanel = new JPanel();
		BrewFilterPanel.setBackground(UIManager.getColor("Panel.background"));
		BrewFilterPanel.setLayout(new MigLayout("", "[70px:70px:70px][70px:70px, grow][85px:85px:85px][70px:70px, grow][50px:50px:50px][70px:70px, grow][80px:80px:80px][90px:90px:90px]", "[][][][45px:45px:45px]"));
			
		lblBrewName = new JLabel("Brew Name:");
		BrewFilterPanel.add(lblBrewName, "flowx,cell 0 0,alignx right");
			
		textBrewName = new JTextField();
		BrewFilterPanel.add(textBrewName, "cell 1 0,growx");
			
		lblRecipeFrom = new JLabel("Recipe From:");
		BrewFilterPanel.add(lblRecipeFrom, "flowx,cell 2 0,alignx right");
			
		textRecipeFrom = new JTextField();
		BrewFilterPanel.add(textRecipeFrom, "cell 3 0,growx");
			
		lblYeast = new JLabel("Yeast:");
		BrewFilterPanel.add(lblYeast, "flowx,cell 4 0,alignx right");
			
		textYeast = new JTextField();
		BrewFilterPanel.add(textYeast, "cell 5 0,growx");
			
		lblNotes = new JLabel("Notes:");
		BrewFilterPanel.add(lblNotes, "flowx,cell 0 1,alignx right");
			
		textNotes = new JTextField();
		BrewFilterPanel.add(textNotes, "cell 1 1,growx");
			
		lblTastingNotes = new JLabel("Tasting Notes:");
		BrewFilterPanel.add(lblTastingNotes, "flowx,cell 2 1,alignx right");
			
		textTastingNotes = new JTextField();
		BrewFilterPanel.add(textTastingNotes, "cell 3 1,growx");
			
		lblColour = new JLabel("Colour:");
		BrewFilterPanel.add(lblColour, "flowx,cell 4 1,alignx right");
			
		comboColour = new JComboBox<String>();
		comboColour.setBackground(Color.WHITE);
		comboColour.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "Red", "White", "Ros\u00E9", "Other"}));
		BrewFilterPanel.add(comboColour, "cell 5 1,growx");
			
		lblThumbs = new JLabel("Thumbs:");
		BrewFilterPanel.add(lblThumbs, "flowx,cell 0 2,alignx right");
			
		chckbxInPlanning = new JCheckBox("In Planning");
		chckbxInPlanning.setSelected(true);
		BrewFilterPanel.add(chckbxInPlanning, "cell 6 0,growx");
			
		chckbxInFining = new JCheckBox("In Fining");
		chckbxInFining.setSelected(true);
		BrewFilterPanel.add(chckbxInFining, "cell 6 1,growx");
			
		chckbxInFermenting = new JCheckBox("In Fermenting");
		chckbxInFermenting.setSelected(true);
		BrewFilterPanel.add(chckbxInFermenting, "cell 7 0,growx");
			
		chckbxInBottles = new JCheckBox("In Bottles");
		chckbxInBottles.setSelected(true);
		BrewFilterPanel.add(chckbxInBottles, "cell 7 1,growx");
			
		comboThumbs = new JComboBox<String>();
		comboThumbs.setBackground(Color.WHITE);
		comboThumbs.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "Up", "Middle", "Down"}));
		BrewFilterPanel.add(comboThumbs, "cell 1 2,growx");

		chckbxInMaturing = new JCheckBox("In Maturing");
		chckbxInMaturing.setSelected(true);
		BrewFilterPanel.add(chckbxInMaturing, "cell 6 2,growx");

		chckbxDrunk = new JCheckBox("Drunk");
		chckbxDrunk.setSelected(true);
		BrewFilterPanel.add(chckbxDrunk, "cell 7 2,growx");
			
		btnShowEntireTable = new JButton("Show Entire Table");
		BrewFilterPanel.add(btnShowEntireTable, "cell 0 3 2 1,growx");
			
		btnSearch = new JButton("Search");
		BrewFilterPanel.add(btnSearch, "cell 6 3 2 1,growx");

		tabbedBrewSearchPanel.add(BrewFilterPanel, "cell 0 0,grow");

		//Initialize Table
		initializeTable();
		
		//TODO: Some form of colouring on table rows
		
		//ScrollPane
	    BrewScrollPane = new JScrollPane();
	    BrewScrollPane.setViewportView(BrewTable);
	    tabbedBrewSearchPanel.add(BrewScrollPane, "cell 0 1,grow");
	    
	    
		//Add button listeners
	    btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrewScrollPane.remove(BrewTable);
				BrewScrollPane.setViewportView(null);
				BrewDataPanel.clearBrewData();
				BrewAddPanel.clearBrewAddData();
				BrewDataPanel.btnBrewDataEdit.setEnabled(false);
				initializeTable();
				BrewScrollPane.setViewportView(BrewTable);
			}
		});
		
	    //TODO: ShowEntireTable button popup window entire table
	    btnShowEntireTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
	private static void initializeTable() {
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
	    header.add("Brew Ref");
	    header.add("Brew Name");
	    header.add("Date Planned");
	    header.add("Date Started");
	    header.add("Date Bottled");
	    header.add("Recipe From");
	    header.add("Yeast");
	    header.add("Start SG");
	    header.add("Start Adjusted SG");
	    header.add("End SG");
	    header.add("Aimed ABV");
	    header.add("Final ABV");
	    header.add("Final Adjusted ABV");
	    header.add("Notes");
	    header.add("Tasting Notes");
	    header.add("Thumbs Up");
	    header.add("In Planning");
	    header.add("In Fermenting");
	    header.add("In Fining");
	    header.add("In Maturing");
	    header.add("In Bottles");
	    header.add("Drunk");
	    header.add("Volume Made");
	    header.add("Number Bottles");
	    header.add("Colour");
	    
		//Table
		BrewTable = new JTable();
		BrewTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = 6716082729584843250L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
		BrewTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(0).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(0).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(1).setPreferredWidth(260);
		BrewTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		BrewTable.getColumnModel().getColumn(2).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(2).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(2).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		BrewTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		BrewTable.getColumnModel().getColumn(4).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(4).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(4).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(5).setPreferredWidth(260);
		BrewTable.getColumnModel().getColumn(5).setMaxWidth(9001);
		BrewTable.getColumnModel().getColumn(6).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(6).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(6).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(7).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(7).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(7).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(8).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(8).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(8).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(9).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(9).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(9).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(10).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(10).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(10).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(11).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(11).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(11).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(12).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(12).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(12).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(13).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(13).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(13).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(14).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(14).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(14).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(15).setPreferredWidth(80);
		BrewTable.getColumnModel().getColumn(15).setMinWidth(5);
		BrewTable.getColumnModel().getColumn(15).setMaxWidth(9001);
		BrewTable.getColumnModel().getColumn(16).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(16).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(16).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(17).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(17).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(17).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(18).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(18).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(18).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(19).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(19).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(19).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(20).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(20).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(20).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(21).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(21).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(21).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(22).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(22).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(22).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(23).setPreferredWidth(0);
		BrewTable.getColumnModel().getColumn(23).setMinWidth(0);
		BrewTable.getColumnModel().getColumn(23).setMaxWidth(0);
		BrewTable.getColumnModel().getColumn(24).setPreferredWidth(79);
		BrewTable.getColumnModel().getColumn(24).setMinWidth(5);
		BrewTable.getColumnModel().getColumn(24).setMaxWidth(9001);
		BrewTable.getTableHeader().setReorderingAllowed(false);
		BrewTable.setAutoCreateRowSorter(true);
		

		//Mouse Listener on JTable:
		BrewTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					BrewSearchSelectedRow = target.getSelectedRow();
					BrewDataPanel.btnBrewDataEdit.setEnabled(true);
					BrewDataPanel.setBrewData();
				}
				
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					BrewSearchSelectedRow = target.getSelectedRow();
					BrewDataPanel.btnBrewDataEdit.setEnabled(true);
					BrewDataPanel.setBrewData();
					BrewPanel.tabbedBrewPane.setSelectedIndex(1);
				}
				
			   }
			});
		
		
	}
	
}