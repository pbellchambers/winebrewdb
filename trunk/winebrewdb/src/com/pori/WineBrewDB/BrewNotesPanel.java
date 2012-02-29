package com.pori.WineBrewDB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;


public class BrewNotesPanel extends JPanel {
	
	private static final long serialVersionUID = -1974511040788382066L;
	public static JPanel tabbedBrewNotesPanel;
	public static JScrollPane BrewNotesScrollPane;
	public static JTable BrewNotesTable;
	public static int BrewNotesSelectedRow;
	public static JLabel lblBrewNoteRef;
	public static JTextField textBrewNoteRef;
	public static JLabel lblBrewNoteDate;
	public static JTextField textBrewNoteDate;
	public static JLabel lblBrewNoteDaysSinceStart;
	public static JTextField textBrewNoteDaysSinceStart;
	public static JLabel lblBrewNoteIncident;
	public static JTextField textBrewNoteIncident;
	public static JLabel lblBrewNoteNote;
	public static JTextArea textBrewNoteNote;
	public static JButton btnBrewNoteAdd;
	public static JButton btnBrewNoteEdit;
	public static JButton btnBrewNoteDelete;
	public static JButton btnBrewNoteCancel;
	public static JButton btnBrewNoteSave;

	
	public static void InitializePanel(){
		
		tabbedBrewNotesPanel = new JPanel();
		tabbedBrewNotesPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewNotesPanel.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[grow][][][][grow][][][]"));
		
		
		//Initialize Table
		initializeTable();
		
		
		//ScrollPane
	    BrewNotesScrollPane = new JScrollPane();
	    BrewNotesScrollPane.setViewportView(BrewNotesTable);
	    tabbedBrewNotesPanel.add(BrewNotesScrollPane, "cell 0 0 6 1,grow");
	    
	    
	    //Main Fields
	    lblBrewNoteRef = new JLabel("Brew Note Ref:");
	    tabbedBrewNotesPanel.add(lblBrewNoteRef, "cell 0 1,alignx trailing");
		
		textBrewNoteRef = new JTextField();
		tabbedBrewNotesPanel.add(textBrewNoteRef, "cell 1 1,growx");
		
		lblBrewNoteDate = new JLabel("Date:");
		tabbedBrewNotesPanel.add(lblBrewNoteDate, "cell 2 1,alignx trailing");
		
		textBrewNoteDate = new JTextField();
		tabbedBrewNotesPanel.add(textBrewNoteDate, "cell 3 1,growx");
		
		lblBrewNoteDaysSinceStart = new JLabel("Days Since Start:");
		tabbedBrewNotesPanel.add(lblBrewNoteDaysSinceStart, "cell 4 1,alignx trailing");
		
		textBrewNoteDaysSinceStart = new JTextField();
		tabbedBrewNotesPanel.add(textBrewNoteDaysSinceStart, "cell 5 1,growx");
		
		lblBrewNoteIncident = new JLabel("Incident:");
		tabbedBrewNotesPanel.add(lblBrewNoteIncident, "cell 0 3,alignx trailing");
		
		textBrewNoteIncident = new JTextField();
		tabbedBrewNotesPanel.add(textBrewNoteIncident, "cell 1 3 5,growx");
		
		lblBrewNoteNote = new JLabel("Note:");
		tabbedBrewNotesPanel.add(lblBrewNoteNote, "cell 0 4,alignx trailing");
		
		textBrewNoteNote = new JTextArea();
		tabbedBrewNotesPanel.add(textBrewNoteNote, "cell 1 4 5 2,grow");
		
		btnBrewNoteAdd = new JButton("Add New Note");
		tabbedBrewNotesPanel.add(btnBrewNoteAdd, "cell 0 7,growx");
		
		btnBrewNoteEdit = new JButton("Edit");
		tabbedBrewNotesPanel.add(btnBrewNoteEdit, "cell 1 7,growx");
		
		btnBrewNoteDelete = new JButton("Delete");
		tabbedBrewNotesPanel.add(btnBrewNoteDelete, "cell 2 7,growx");
		
		btnBrewNoteCancel = new JButton("Cancel");
		tabbedBrewNotesPanel.add(btnBrewNoteCancel, "cell 4 7,growx");
		
		btnBrewNoteSave = new JButton("Save / Insert");
		tabbedBrewNotesPanel.add(btnBrewNoteSave, "cell 5 7,growx");
	    
		
	}
	
	public static void initializeTable() {
		//Get data for table
	    Vector<Vector<String>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getBrewNotes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<String>();
	    header.add("Brew Note Ref");
	    header.add("Date");
	    header.add("Days Since Start");
	    header.add("Incident");
	    header.add("Notes");
	    
		//Table
		BrewNotesTable = new JTable();
		BrewNotesTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = 6616082729514643250L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
		BrewNotesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		BrewNotesTable.getColumnModel().getColumn(0).setMinWidth(0);
		BrewNotesTable.getColumnModel().getColumn(0).setMaxWidth(9001);
		BrewNotesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		BrewNotesTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		BrewNotesTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		BrewNotesTable.getColumnModel().getColumn(2).setMinWidth(0);
		BrewNotesTable.getColumnModel().getColumn(2).setMaxWidth(9001);
		BrewNotesTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		BrewNotesTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		BrewNotesTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		BrewNotesTable.getColumnModel().getColumn(4).setMinWidth(0);
		BrewNotesTable.getColumnModel().getColumn(4).setMaxWidth(9001);
		BrewNotesTable.getTableHeader().setReorderingAllowed(false);
		BrewNotesTable.setAutoCreateRowSorter(false);
		

		//Mouse Listener on JTable:
		BrewNotesTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					BrewNotesSelectedRow = target.getSelectedRow();
					btnBrewNoteEdit.setEnabled(true);
					btnBrewNoteDelete.setEnabled(false);
					setBrewNoteData();
				}
				
			   }
			});
				
		
	}
	
	public static void setBrewNoteData(){
		
	}

	
}