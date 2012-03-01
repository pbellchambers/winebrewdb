package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import agiletrack.swing.JDateChooser;

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
	public static JDateChooser chooserBrewNoteDate;
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
	public static JScrollPane BrewNotesNoteScrollPane;
	public static String isNewNote;

	
	public static void InitializePanel(){
		
		tabbedBrewNotesPanel = new JPanel();
		tabbedBrewNotesPanel.setBackground(Color.WHITE);
		tabbedBrewNotesPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][90px:90px:90px][grow][130px:130px:130px][grow]", "[200px:200px:200px][][][][][grow][][10px:10px:10px][]"));
		
		
		//Initialize Table
		initializeTable();
		
		
		//ScrollPane
	    BrewNotesScrollPane = new JScrollPane();
	    BrewNotesScrollPane.setViewportView(BrewNotesTable);
	    tabbedBrewNotesPanel.add(BrewNotesScrollPane, "cell 0 0 6 1,grow");
	    
	    
	    //Main Fields
	    lblBrewNoteRef = new JLabel("Brew Note Ref:");
	    //tabbedBrewNotesPanel.add(lblBrewNoteRef, "cell 0 1,alignx trailing");
		
		textBrewNoteRef = new JTextField();
		textBrewNoteRef.setEditable(false);
		//tabbedBrewNotesPanel.add(textBrewNoteRef, "cell 1 1,growx");
		
		lblBrewNoteDate = new JLabel("Date:");
		tabbedBrewNotesPanel.add(lblBrewNoteDate, "cell 0 1,alignx trailing");
		
		chooserBrewNoteDate = new JDateChooser();
		chooserBrewNoteDate.setDisabled();
		tabbedBrewNotesPanel.add(chooserBrewNoteDate, "cell 1 1,growx");
		
		lblBrewNoteDaysSinceStart = new JLabel("Days Since Start:");
		//tabbedBrewNotesPanel.add(lblBrewNoteDaysSinceStart, "cell 4 1,alignx trailing");
		
		textBrewNoteDaysSinceStart = new JTextField();
		textBrewNoteDaysSinceStart.setEditable(false);
		//tabbedBrewNotesPanel.add(textBrewNoteDaysSinceStart, "cell 5 1,growx");
		
		lblBrewNoteIncident = new JLabel("Incident:");
		tabbedBrewNotesPanel.add(lblBrewNoteIncident, "cell 0 3,alignx trailing");
		
		textBrewNoteIncident = new JTextField();
		textBrewNoteIncident.setEditable(false);
		tabbedBrewNotesPanel.add(textBrewNoteIncident, "cell 1 3 5,growx");
		
		lblBrewNoteNote = new JLabel("Note:");
		tabbedBrewNotesPanel.add(lblBrewNoteNote, "cell 0 5,alignx trailing");
		
		textBrewNoteNote = new JTextArea();
		textBrewNoteNote.setEditable(false);
		textBrewNoteNote.setLineWrap(true);
		textBrewNoteNote.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textBrewNoteNote
	    BrewNotesNoteScrollPane = new JScrollPane();
	    BrewNotesNoteScrollPane.setViewportView(textBrewNoteNote);
	    tabbedBrewNotesPanel.add(BrewNotesNoteScrollPane, "cell 1 5 5 2,grow");
		
		btnBrewNoteAdd = new JButton("Add");
		tabbedBrewNotesPanel.add(btnBrewNoteAdd, "cell 0 8,growx");
		
		btnBrewNoteEdit = new JButton("Edit");
		btnBrewNoteEdit.setEnabled(false);
		tabbedBrewNotesPanel.add(btnBrewNoteEdit, "cell 1 8,growx");
		
		btnBrewNoteDelete = new JButton("Delete Note");
		btnBrewNoteDelete.setEnabled(false);
		tabbedBrewNotesPanel.add(btnBrewNoteDelete, "cell 2 8,growx");
		
		btnBrewNoteCancel = new JButton("Cancel");
		btnBrewNoteCancel.setEnabled(false);
		tabbedBrewNotesPanel.add(btnBrewNoteCancel, "cell 4 8,growx");
		
		btnBrewNoteSave = new JButton("Save / Insert");
		btnBrewNoteSave.setEnabled(false);
		tabbedBrewNotesPanel.add(btnBrewNoteSave, "cell 5 8,growx");
		
		//Add button listeners
		//TODO: If Add then click table
		btnBrewNoteAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearBrewNoteData();
				isNewNote = "true";
				BrewNotesTable.setEnabled(false);
				btnBrewNoteAdd.setEnabled(false);
				btnBrewNoteEdit.setEnabled(false);
				btnBrewNoteDelete.setEnabled(false);
				btnBrewNoteCancel.setEnabled(true);
				btnBrewNoteSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
				InitializeMenu.DisableAllMenuButtons();
				chooserBrewNoteDate.setEnabled();
				textBrewNoteIncident.setEditable(true);
				textBrewNoteNote.setEditable(true);
				textBrewNoteNote.setBackground(Color.WHITE);

		        
			}
		});
		
		btnBrewNoteEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isNewNote = "false";
				BrewNotesTable.setEnabled(false);
				btnBrewNoteAdd.setEnabled(false);
				btnBrewNoteEdit.setEnabled(false);
				btnBrewNoteDelete.setEnabled(true);
				btnBrewNoteCancel.setEnabled(true);
				btnBrewNoteSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
				InitializeMenu.DisableAllMenuButtons();
				chooserBrewNoteDate.setEnabled();
				textBrewNoteIncident.setEditable(true);
				textBrewNoteNote.setEditable(true);
				textBrewNoteNote.setBackground(Color.WHITE);
			}
		});
		
		btnBrewNoteDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the entire note.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteBrewNote();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					  	BrewNotesTable.setEnabled(true);
					  	btnBrewNoteAdd.setEnabled(true);
						btnBrewNoteEdit.setEnabled(false);
						btnBrewNoteDelete.setEnabled(false);
						btnBrewNoteCancel.setEnabled(false);
						btnBrewNoteSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						InitializeMenu.EnableAllMenuButtons();
						chooserBrewNoteDate.setDisabled();
						textBrewNoteIncident.setEditable(false);
						textBrewNoteNote.setEditable(false);
						textBrewNoteNote.setBackground(UIManager.getColor("Panel.background"));
						BrewNotesScrollPane.remove(BrewNotesTable);
						BrewNotesScrollPane.setViewportView(null);
						initializeTable();
						BrewNotesScrollPane.setViewportView(BrewNotesTable);
						clearBrewNoteData();
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnBrewNoteCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isNewNote = "false";
				setBrewNoteData();
				BrewNotesTable.setEnabled(true);
				btnBrewNoteAdd.setEnabled(true);
				btnBrewNoteEdit.setEnabled(true);
				btnBrewNoteDelete.setEnabled(false);
				btnBrewNoteCancel.setEnabled(false);
				btnBrewNoteSave.setEnabled(false);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				InitializeMenu.EnableAllMenuButtons();
				chooserBrewNoteDate.setDisabled();
				textBrewNoteIncident.setEditable(false);
				textBrewNoteNote.setEditable(false);
				textBrewNoteNote.setBackground(UIManager.getColor("Panel.background"));
			}
		});
		
		
		btnBrewNoteSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewNote.equals("true")){
					if (chooserBrewNoteDate.getDate() == null){
						JOptionPane.showMessageDialog(null,
							"You must enter a date.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							textBrewNoteDaysSinceStart.setText(Dates.daysBetween(BrewDataPanel.chooserBrewDateStartedB.getDate(), chooserBrewNoteDate.getDate()));
							textBrewNoteRef.setText(DBEngine.getNextBrewNoteRef());
							DBEngine.addBrewNote();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						BrewNotesTable.setEnabled(true);
						btnBrewNoteAdd.setEnabled(true);
						btnBrewNoteEdit.setEnabled(false);
						btnBrewNoteDelete.setEnabled(false);
						btnBrewNoteCancel.setEnabled(false);
						btnBrewNoteSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						InitializeMenu.EnableAllMenuButtons();
						chooserBrewNoteDate.setDisabled();
						textBrewNoteIncident.setEditable(false);
						textBrewNoteNote.setEditable(false);
						textBrewNoteNote.setBackground(UIManager.getColor("Panel.background"));
						BrewNotesScrollPane.remove(BrewNotesTable);
						BrewNotesScrollPane.setViewportView(null);
						initializeTable();
						BrewNotesScrollPane.setViewportView(BrewNotesTable);
						clearBrewNoteData();
					}
					
				} else {
					if (chooserBrewNoteDate.getDate() == null){
						JOptionPane.showMessageDialog(null,
							"You must enter a date.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.updateBrewNote();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						BrewNotesTable.setEnabled(true);
						btnBrewNoteAdd.setEnabled(true);
						btnBrewNoteEdit.setEnabled(false);
						btnBrewNoteDelete.setEnabled(false);
						btnBrewNoteCancel.setEnabled(false);
						btnBrewNoteSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						InitializeMenu.EnableAllMenuButtons();
						chooserBrewNoteDate.setDisabled();
						textBrewNoteIncident.setEditable(false);
						textBrewNoteNote.setEditable(false);
						textBrewNoteNote.setBackground(UIManager.getColor("Panel.background"));
						BrewNotesScrollPane.remove(BrewNotesTable);
						BrewNotesScrollPane.setViewportView(null);
						initializeTable();
						BrewNotesScrollPane.setViewportView(BrewNotesTable);
						clearBrewNoteData();
					}
				}
			}
		});
    
		
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
		BrewNotesTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		BrewNotesTable.getColumnModel().getColumn(0).setMinWidth(0);
		BrewNotesTable.getColumnModel().getColumn(0).setMaxWidth(0);
		BrewNotesTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		BrewNotesTable.getColumnModel().getColumn(1).setMinWidth(80);
		BrewNotesTable.getColumnModel().getColumn(1).setMaxWidth(80);
		BrewNotesTable.getColumnModel().getColumn(2).setPreferredWidth(105);
		BrewNotesTable.getColumnModel().getColumn(2).setMinWidth(105);
		BrewNotesTable.getColumnModel().getColumn(2).setMaxWidth(105);
		BrewNotesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		BrewNotesTable.getColumnModel().getColumn(3).setMinWidth(5);
		BrewNotesTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		BrewNotesTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		BrewNotesTable.getColumnModel().getColumn(4).setMinWidth(5);
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
	
	//TODO: If add then cancel with no selected row.
	public static void setBrewNoteData(){
		textBrewNoteRef.setText((String) BrewNotesTable.getValueAt(BrewNotesSelectedRow,0));
		chooserBrewNoteDate.setDate(Dates.stringToDate((String) BrewNotesTable.getValueAt(BrewNotesSelectedRow,1)));
		textBrewNoteDaysSinceStart.setText((String) BrewNotesTable.getValueAt(BrewNotesSelectedRow,2));
		textBrewNoteIncident.setText((String) BrewNotesTable.getValueAt(BrewNotesSelectedRow,3));
		textBrewNoteNote.setText((String) BrewNotesTable.getValueAt(BrewNotesSelectedRow,4));
		chooserBrewNoteDate.setDisabled();
	}
	
	public static void clearBrewNoteData(){
		textBrewNoteRef.setText("");
		chooserBrewNoteDate.setDate(null);
		textBrewNoteDaysSinceStart.setText("");
		textBrewNoteIncident.setText("");
		textBrewNoteNote.setText("");
	}

	
}