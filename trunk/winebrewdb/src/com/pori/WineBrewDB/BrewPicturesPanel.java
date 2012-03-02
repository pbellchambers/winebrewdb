package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;


public class BrewPicturesPanel extends JPanel {
	
	private static final long serialVersionUID = -476275486314448039L;
	public static JPanel tabbedBrewPicturesPanel;
	public static JScrollPane BrewPicturesTableScrollPane;
	public static JScrollPane BrewPictureScrollPane;
	public static JTable BrewPicturesTable;
	private static boolean mouseListenerIsActive;
	public static int BrewPicturesSelectedRow;
	public static JLabel lblBrewPictureRef;
	public static JTextField textBrewPictureRef;
	public static JLabel lblBrewPictureDescription;
	public static JTextField textBrewPictureDescription;
	public static JButton btnBrewPictureAdd;
	public static JButton btnBrewPictureEdit;
	public static JButton btnBrewPictureDelete;
	public static JButton btnBrewPictureCancel;
	public static JButton btnBrewPictureSave;
	public static String isNewPicture;
	public static JTextField textBrewPictureFilename;
	public static JLabel labelBrewPicture;
	public static JButton btnBrewPictureLoad;

	
	public static void InitializePanel(){
		

		tabbedBrewPicturesPanel = new JPanel();
		tabbedBrewPicturesPanel.setBackground(Color.WHITE);
		tabbedBrewPicturesPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[100px:100px:100px][grow][][10px:10px:10px][]"));

		
		//Initialize Table
		initializeTable();
		
		
		//ScrollPane
	    BrewPicturesTableScrollPane = new JScrollPane();
	    BrewPicturesTableScrollPane.setViewportView(BrewPicturesTable);
	    tabbedBrewPicturesPanel.add(BrewPicturesTableScrollPane, "cell 0 0 6 1,grow");
	    
	    
	    //Main Fields
	    lblBrewPictureRef = new JLabel("Brew Picture Ref:");
	    //tabbedBrewPicturesPanel.add(lblBrewPictureRef, "cell 0 1,alignx trailing");
		
		textBrewPictureRef = new JTextField();
		textBrewPictureRef.setEditable(false);
		//tabbedBrewPicturesPanel.add(textBrewPictureRef, "cell 1 1,growx");
		
		btnBrewPictureLoad = new JButton("Load File");
		btnBrewPictureLoad.setEnabled(false);
		tabbedBrewPicturesPanel.add(btnBrewPictureLoad, "cell 0 2,growx");
		
		textBrewPictureFilename = new JTextField();
		textBrewPictureFilename.setEditable(false);
		textBrewPictureFilename.setText("C:\\test.jpg");
		
		lblBrewPictureDescription = new JLabel("Description:");
		tabbedBrewPicturesPanel.add(lblBrewPictureDescription, "cell 1 2,alignx trailing");
		
		textBrewPictureDescription = new JTextField();
		textBrewPictureDescription.setEditable(false);
		tabbedBrewPicturesPanel.add(textBrewPictureDescription, "cell 2 2 4,growx");
		
		//Picture ScrollPane
		labelBrewPicture = new JLabel();
		BrewPictureScrollPane = new JScrollPane();
		BrewPictureScrollPane.setViewportView(labelBrewPicture);
	    tabbedBrewPicturesPanel.add(BrewPictureScrollPane, "cell 0 1 6 1,grow");
				
		btnBrewPictureAdd = new JButton("Add");
		tabbedBrewPicturesPanel.add(btnBrewPictureAdd, "cell 0 4,growx");
		
		btnBrewPictureEdit = new JButton("Edit");
		btnBrewPictureEdit.setEnabled(false);
		tabbedBrewPicturesPanel.add(btnBrewPictureEdit, "cell 1 4,growx");
		
		btnBrewPictureDelete = new JButton("Delete Picture");
		btnBrewPictureDelete.setEnabled(false);
		tabbedBrewPicturesPanel.add(btnBrewPictureDelete, "cell 2 4,growx");
		
		btnBrewPictureCancel = new JButton("Cancel");
		btnBrewPictureCancel.setEnabled(false);
		tabbedBrewPicturesPanel.add(btnBrewPictureCancel, "cell 4 4,growx");
		
		btnBrewPictureSave = new JButton("Save / Insert");
		btnBrewPictureSave.setEnabled(false);
		tabbedBrewPicturesPanel.add(btnBrewPictureSave, "cell 5 4,growx");
		
		//Add button listeners
		btnBrewPictureAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				clearBrewPictureData();
				isNewPicture = "true";
				BrewPicturesTable.setEnabled(false);
				BrewPicturesTable.setRowSelectionAllowed(false);
				btnBrewPictureAdd.setEnabled(false);
				btnBrewPictureLoad.setEnabled(true);
				btnBrewPictureEdit.setEnabled(false);
				btnBrewPictureDelete.setEnabled(false);
				btnBrewPictureCancel.setEnabled(true);
				btnBrewPictureSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
				InitializeMenu.DisableAllMenuButtons();
				textBrewPictureDescription.setEditable(true);
		        
			}
		});
		
		//TODO: Add a save picture button
		btnBrewPictureLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showOpenDialog(BrewPictureScrollPane);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  String filename = "\\" + c.getSelectedFile().getName();
			    	  String dir = c.getCurrentDirectory().toString();			    	  
			    	  textBrewPictureFilename.setText(dir + filename);			    	  			    	  
			    	  tabbedBrewPicturesPanel.remove(labelBrewPicture);
			    	  BrewPictureScrollPane.remove(labelBrewPicture);
			    	  BrewPictureScrollPane.setViewportView(null);
			    	  labelBrewPicture = new JLabel(DBEngine.scaledImageIcon(BrewPictureScrollPane.getWidth(),BrewPictureScrollPane.getHeight(), "file"));
			    	  BrewPictureScrollPane.setViewportView(labelBrewPicture);
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }
		        
			}
		});


		btnBrewPictureEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				isNewPicture = "false";
				BrewPicturesTable.setEnabled(false);
				BrewPicturesTable.setRowSelectionAllowed(false);
				btnBrewPictureLoad.setEnabled(false);
				btnBrewPictureAdd.setEnabled(false);
				btnBrewPictureEdit.setEnabled(false);
				btnBrewPictureDelete.setEnabled(true);
				btnBrewPictureCancel.setEnabled(true);
				btnBrewPictureSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
				InitializeMenu.DisableAllMenuButtons();
				textBrewPictureDescription.setEditable(true);
			}
		});

		btnBrewPictureDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the entire picture.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteBrewPicture();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						BrewPicturesTable.setEnabled(true);
						BrewPicturesTable.setRowSelectionAllowed(true);
						btnBrewPictureLoad.setEnabled(false);
					  	btnBrewPictureAdd.setEnabled(true);
						btnBrewPictureEdit.setEnabled(false);
						btnBrewPictureDelete.setEnabled(false);
						btnBrewPictureCancel.setEnabled(false);
						btnBrewPictureSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						InitializeMenu.EnableAllMenuButtons();
						textBrewPictureDescription.setEditable(false);
						BrewPicturesTableScrollPane.remove(BrewPicturesTable);
						BrewPicturesTableScrollPane.setViewportView(null);
						initializeTable();
						BrewPicturesTableScrollPane.setViewportView(BrewPicturesTable);
						clearBrewPictureData();
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnBrewPictureCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewPicture.equals("true")){
					isNewPicture = "false";
					mouseListenerIsActive = true;
					BrewPicturesTableScrollPane.remove(BrewPicturesTable);
					BrewPicturesTableScrollPane.setViewportView(null);
					initializeTable();
					BrewPicturesTableScrollPane.setViewportView(BrewPicturesTable);
					clearBrewPictureData();
					BrewPicturesTable.setEnabled(true);
					BrewPicturesTable.setRowSelectionAllowed(true);
					btnBrewPictureLoad.setEnabled(false);
					btnBrewPictureAdd.setEnabled(true);
					btnBrewPictureEdit.setEnabled(false);
					btnBrewPictureDelete.setEnabled(false);
					btnBrewPictureCancel.setEnabled(false);
					btnBrewPictureSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					InitializeMenu.EnableAllMenuButtons();
					textBrewPictureDescription.setEditable(false);
				}else {
					mouseListenerIsActive = true;
					isNewPicture = "false";
					setBrewPictureData();
					BrewPicturesTable.setEnabled(true);
					BrewPicturesTable.setRowSelectionAllowed(true);
					btnBrewPictureLoad.setEnabled(false);
					btnBrewPictureAdd.setEnabled(true);
					btnBrewPictureEdit.setEnabled(true);
					btnBrewPictureDelete.setEnabled(false);
					btnBrewPictureCancel.setEnabled(false);
					btnBrewPictureSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					InitializeMenu.EnableAllMenuButtons();
					textBrewPictureDescription.setEditable(false);
				}
			}
		});


		btnBrewPictureSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewPicture.equals("true")){
					if (textBrewPictureFilename.getText() == null || textBrewPictureFilename.getText().equals("")){
						JOptionPane.showMessageDialog(null,
							"You must select a picture.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							textBrewPictureRef.setText(DBEngine.getNextBrewPictureRef());
							DBEngine.insertBrewPicture();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						BrewPicturesTable.setEnabled(true);
						BrewPicturesTable.setRowSelectionAllowed(true);
						btnBrewPictureLoad.setEnabled(false);
						btnBrewPictureAdd.setEnabled(true);
						btnBrewPictureEdit.setEnabled(false);
						btnBrewPictureDelete.setEnabled(false);
						btnBrewPictureCancel.setEnabled(false);
						btnBrewPictureSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						InitializeMenu.EnableAllMenuButtons();
						textBrewPictureDescription.setEditable(false);
						BrewPicturesTableScrollPane.remove(BrewPicturesTable);
						BrewPicturesTableScrollPane.setViewportView(null);
						initializeTable();
						BrewPicturesTableScrollPane.setViewportView(BrewPicturesTable);
						clearBrewPictureData();
					}
					
				} else {
					try {
						DBEngine.updateBrewPicture();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					mouseListenerIsActive = true;
					BrewPicturesTable.setEnabled(true);
					BrewPicturesTable.setRowSelectionAllowed(true);
					btnBrewPictureLoad.setEnabled(false);
					btnBrewPictureAdd.setEnabled(true);
					btnBrewPictureEdit.setEnabled(false);
					btnBrewPictureDelete.setEnabled(false);
					btnBrewPictureCancel.setEnabled(false);
					btnBrewPictureSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					InitializeMenu.EnableAllMenuButtons();
					textBrewPictureDescription.setEditable(false);
					BrewPicturesTableScrollPane.remove(BrewPicturesTable);
					BrewPicturesTableScrollPane.setViewportView(null);
					initializeTable();
					BrewPicturesTableScrollPane.setViewportView(BrewPicturesTable);
					clearBrewPictureData();

				}
			}
		});

		
	}
	
	
	public static void initializeTable() {
		//Get data for table
	    Vector<Vector<String>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getBrewPictureTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    //Create header for the table
	    header = new Vector<String>();
	    header.add("Brew Pic Ref");
	    header.add("Description");
	    
		//Table
	    BrewPicturesTable = new JTable();
	    BrewPicturesTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = 6786082729532643250L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
	    BrewPicturesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
	    BrewPicturesTable.getColumnModel().getColumn(0).setMinWidth(5);
	    BrewPicturesTable.getColumnModel().getColumn(0).setMaxWidth(9001);
	    BrewPicturesTable.getColumnModel().getColumn(1).setPreferredWidth(200);
	    BrewPicturesTable.getColumnModel().getColumn(1).setMinWidth(5);
	    BrewPicturesTable.getTableHeader().setReorderingAllowed(false);
	    BrewPicturesTable.setAutoCreateRowSorter(false);
		
		addBrewPicturesMouseListener();
		
	}
	
	public static void addBrewPicturesMouseListener(){
		mouseListenerIsActive = true;
		
		BrewPicturesTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(mouseListenerIsActive){
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						BrewPicturesSelectedRow = target.getSelectedRow();
						if(BrewPicturesSelectedRow != -1){
							btnBrewPictureEdit.setEnabled(true);
							btnBrewPictureDelete.setEnabled(false);
							setBrewPictureData();
						}
					}
				}			
			}
		});
	}
	
	public static void removeBrewPicturesMouseListener(){
		mouseListenerIsActive = false;
	}
	
	//TODO: Add on resize event
	public static void setBrewPictureData(){
		if(BrewPicturesSelectedRow != -1){
			textBrewPictureRef.setText((String) BrewPicturesTable.getValueAt(BrewPicturesSelectedRow,0));
			textBrewPictureDescription.setText((String) BrewPicturesTable.getValueAt(BrewPicturesSelectedRow,1));
			if (textBrewPictureRef.getText() == null || textBrewPictureRef.getText().equals("")){
				BrewPictureScrollPane.remove(labelBrewPicture);
				BrewPictureScrollPane.setViewportView(null);
				BrewPictureScrollPane.repaint();
			}else {
				tabbedBrewPicturesPanel.remove(labelBrewPicture);
				BrewPictureScrollPane.remove(labelBrewPicture);
				BrewPictureScrollPane.setViewportView(null);
				labelBrewPicture = new JLabel(DBEngine.scaledImageIcon(BrewPictureScrollPane.getWidth(),BrewPictureScrollPane.getHeight(), "DB"));
				BrewPictureScrollPane.setViewportView(labelBrewPicture);
				
			}
		}
	}
	
	
	public static void clearBrewPictureData(){
		textBrewPictureRef.setText("");
		textBrewPictureDescription.setText("");
		BrewPictureScrollPane.remove(labelBrewPicture);
		BrewPictureScrollPane.setViewportView(null);
		BrewPictureScrollPane.repaint();
	}
	
}