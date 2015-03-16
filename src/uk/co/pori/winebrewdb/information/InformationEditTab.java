package uk.co.pori.winebrewdb.information;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import uk.co.pori.winebrewdb.InitialiseMenu;
import uk.co.pori.winebrewdb.MainWindow;
import uk.co.pori.winebrewdb.sqlite.DBEngine;


import net.miginfocom.swing.MigLayout;

/**
 * This is the information edit panel that allows you to add/edit information tabs.
 * 
 * @author Paul.Bellchambers
 *
 */
public class InformationEditTab extends JPanel {
	
	private static final long serialVersionUID = 417454069851487329L;
	static JPanel tabbedInformationEditTab;
	static JScrollPane InformationEditScrollPane;
	static JTable InformationEditTable;
	private static int InformationEditSelectedRow;
	public static JTextField textInformationTabID;
	public static JTextField textInformationTabName;
	public static JTextArea textInformationTabContent;
	public static JCheckBox checkboxInformationTabHTML;
	static JButton btnInformationTabEdit;
	private static JButton btnInformationTabDelete;
	private static JButton btnInformationTabCancel;
	private static JButton btnInformationTabSave;
	private static String isNewTab;
	private static boolean mouseListenerIsActive;
	private static JButton btnInformationTabAdd;
	public static String textInformationOrigTabID;

	/**
	 * Initialises the information edit panel (including getting the table of data) so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		tabbedInformationEditTab = new JPanel();
		tabbedInformationEditTab.setBackground(Color.WHITE);
		tabbedInformationEditTab.setLayout(new MigLayout("", "[90px:90px:90px][grow][90px:90px:90px][grow][130px:130px:130px][grow]", "[100px:100px:100px][][][grow][10px:10px:10px][]"));
		
		
		//Initialise Table
		initialiseTable();
		
		
		//ScrollPane
	    InformationEditScrollPane = new JScrollPane();
	    InformationEditScrollPane.setViewportView(InformationEditTable);
	    tabbedInformationEditTab.add(InformationEditScrollPane, "cell 0 0 6 1,grow");

	    JLabel lblInformationTabID = new JLabel("Tab Number:");
		tabbedInformationEditTab.add(lblInformationTabID, "cell 0 1,alignx trailing");
	    
	    textInformationTabID = new JTextField();
	    textInformationTabID.setEditable(false);
		tabbedInformationEditTab.add(textInformationTabID, "cell 1 1,growx");
		
		JLabel lblInformationTabName = new JLabel("Tab Name:");
		tabbedInformationEditTab.add(lblInformationTabName, "cell 2 1,alignx trailing");
		
		textInformationTabName = new JTextField();
		textInformationTabName.setEditable(false);
		tabbedInformationEditTab.add(textInformationTabName, "cell 3 1 2,growx");
		
		checkboxInformationTabHTML = new JCheckBox("Is HTML?");
		checkboxInformationTabHTML.setSelected(false);
		checkboxInformationTabHTML.setEnabled(false);
		checkboxInformationTabHTML.setBackground(Color.WHITE);
		tabbedInformationEditTab.add(checkboxInformationTabHTML, "cell 5 1,alignx right");
		
		JLabel lblInformationTabContent = new JLabel("Content:");
		tabbedInformationEditTab.add(lblInformationTabContent, "cell 0 2,alignx trailing");
		
		textInformationTabContent = new JTextArea();
		textInformationTabContent.setEditable(false);
		textInformationTabContent.setLineWrap(false);
		textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));

		//ScrollPane for textInformationTabContent
	    JScrollPane InformationEditContentScrollPane = new JScrollPane();
	    InformationEditContentScrollPane.setViewportView(textInformationTabContent);
	    tabbedInformationEditTab.add(InformationEditContentScrollPane, "cell 1 2 5 2,grow");
		
		btnInformationTabAdd = new JButton("Add");
		tabbedInformationEditTab.add(btnInformationTabAdd, "cell 0 6,growx");
		
		btnInformationTabEdit = new JButton("Edit");
		btnInformationTabEdit.setEnabled(false);
		tabbedInformationEditTab.add(btnInformationTabEdit, "cell 1 6,growx");
		
		btnInformationTabDelete = new JButton("Delete Tab");
		btnInformationTabDelete.setEnabled(false);
		tabbedInformationEditTab.add(btnInformationTabDelete, "cell 2 6,growx");
		
		btnInformationTabCancel = new JButton("Cancel");
		btnInformationTabCancel.setEnabled(false);
		tabbedInformationEditTab.add(btnInformationTabCancel, "cell 4 6,growx");
		
		btnInformationTabSave = new JButton("Save / Insert");
		btnInformationTabSave.setEnabled(false);
		tabbedInformationEditTab.add(btnInformationTabSave, "cell 5 6,growx");
		
		//Add button listeners
		btnInformationTabAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				clearInformationTabData();
				isNewTab = "true";
				InformationEditTable.setEnabled(false);
				InformationEditTable.setRowSelectionAllowed(false);
				btnInformationTabAdd.setEnabled(false);
				btnInformationTabEdit.setEnabled(false);
				btnInformationTabDelete.setEnabled(false);
				btnInformationTabCancel.setEnabled(true);
				btnInformationTabSave.setEnabled(true);
				int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
				int currenttab = 0;
				while(currenttab < totaltabs){
					InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, false);
					currenttab = currenttab + 1;
				}				
				InitialiseMenu.disableAllMenuButtons();
				textInformationTabID.setEditable(true);
				textInformationTabName.setEditable(true);
				textInformationTabContent.setEditable(true);
				textInformationTabContent.setBackground(Color.WHITE);
				checkboxInformationTabHTML.setEnabled(true);

		        
			}
		});
		
		btnInformationTabEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				isNewTab = "false";
				InformationEditTable.setEnabled(false);
				InformationEditTable.setRowSelectionAllowed(false);
				btnInformationTabAdd.setEnabled(false);
				btnInformationTabEdit.setEnabled(false);
				btnInformationTabDelete.setEnabled(true);
				btnInformationTabCancel.setEnabled(true);
				btnInformationTabSave.setEnabled(true);
				int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
				int currenttab = 0;
				while(currenttab < totaltabs){
					InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, false);
					currenttab = currenttab + 1;
				}		
				InitialiseMenu.disableAllMenuButtons();
				textInformationTabID.setEditable(true);
				textInformationTabName.setEditable(true);
				textInformationTabContent.setEditable(true);
				textInformationTabContent.setBackground(Color.WHITE);
				checkboxInformationTabHTML.setEnabled(true);
			}
		});
		
		btnInformationTabDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the entire note.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteInformationTabData();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred deleting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						InformationEditTable.setEnabled(true);
						InformationEditTable.setRowSelectionAllowed(true);
					  	btnInformationTabAdd.setEnabled(true);
						btnInformationTabEdit.setEnabled(false);
						btnInformationTabDelete.setEnabled(false);
						btnInformationTabCancel.setEnabled(false);
						btnInformationTabSave.setEnabled(false);
						int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
						int currenttab = 0;
						while(currenttab < totaltabs){
							InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, true);
							currenttab = currenttab + 1;
						}		
						InitialiseMenu.enableAllMenuButtons();
						textInformationTabID.setEditable(false);
						textInformationTabName.setEditable(false);
						textInformationTabContent.setEditable(false);
						textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));
						checkboxInformationTabHTML.setEnabled(false);
						InformationEditScrollPane.remove(InformationEditTable);
						InformationEditScrollPane.setViewportView(null);
						initialiseTable();
						InformationEditScrollPane.setViewportView(InformationEditTable);
						clearInformationTabData();
						refreshInformationTabs();
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnInformationTabCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewTab.equals("true")){
					isNewTab = "false";
					mouseListenerIsActive = true;
					InformationEditScrollPane.remove(InformationEditTable);
					InformationEditScrollPane.setViewportView(null);
					initialiseTable();
					InformationEditScrollPane.setViewportView(InformationEditTable);
					clearInformationTabData();
					InformationEditTable.setEnabled(true);
					InformationEditTable.setRowSelectionAllowed(true);
					btnInformationTabAdd.setEnabled(true);
					btnInformationTabEdit.setEnabled(false);
					btnInformationTabDelete.setEnabled(false);
					btnInformationTabCancel.setEnabled(false);
					btnInformationTabSave.setEnabled(false);
					int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
					int currenttab = 0;
					while(currenttab < totaltabs){
						InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, true);
						currenttab = currenttab + 1;
					}		
					InitialiseMenu.enableAllMenuButtons();
					textInformationTabID.setEditable(false);
					textInformationTabName.setEditable(false);
					textInformationTabContent.setEditable(false);
					textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));
					checkboxInformationTabHTML.setEnabled(false);
				}else {
					mouseListenerIsActive = true;
					isNewTab = "false";
					setInformationTabData();
					InformationEditTable.setEnabled(true);
					InformationEditTable.setRowSelectionAllowed(true);
					btnInformationTabAdd.setEnabled(true);
					btnInformationTabEdit.setEnabled(true);
					btnInformationTabDelete.setEnabled(false);
					btnInformationTabCancel.setEnabled(false);
					btnInformationTabSave.setEnabled(false);
					int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
					int currenttab = 0;
					while(currenttab < totaltabs){
						InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, true);
						currenttab = currenttab + 1;
					}		
					InitialiseMenu.enableAllMenuButtons();
					textInformationTabID.setEditable(false);
					textInformationTabName.setEditable(false);
					textInformationTabContent.setEditable(false);
					textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));
					checkboxInformationTabHTML.setEnabled(false);
				}
			}
		});
		
		
		btnInformationTabSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewTab.equals("true")){
					if (textInformationTabID.getText() == null || textInformationTabID.getText().equals("")){
						JOptionPane.showMessageDialog(null,
							"You must enter valid unique Tab Number.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.addInformationTabData();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred inserting data into the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.\n- The Tab Number you entered is not unique.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						InformationEditTable.setEnabled(true);
						InformationEditTable.setRowSelectionAllowed(true);
						btnInformationTabAdd.setEnabled(true);
						btnInformationTabEdit.setEnabled(false);
						btnInformationTabDelete.setEnabled(false);
						btnInformationTabCancel.setEnabled(false);
						btnInformationTabSave.setEnabled(false);
						int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
						int currenttab = 0;
						while(currenttab < totaltabs){
							InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, true);
							currenttab = currenttab + 1;
						}		
						InitialiseMenu.enableAllMenuButtons();
						textInformationTabID.setEditable(false);
						textInformationTabName.setEditable(false);
						textInformationTabContent.setEditable(false);
						textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));
						checkboxInformationTabHTML.setEnabled(false);
						InformationEditScrollPane.remove(InformationEditTable);
						InformationEditScrollPane.setViewportView(null);
						initialiseTable();
						InformationEditScrollPane.setViewportView(InformationEditTable);
						clearInformationTabData();
						refreshInformationTabs();
					}
					
				} else {
					if (textInformationTabID.getText() == null || textInformationTabID.getText().equals("")){
						JOptionPane.showMessageDialog(null,
								"You must enter valid unique Tab Number.",
								"Error",
								JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.updateInformationTabData();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred updating data in the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.\n- The Tab Number you entered is not unique.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						InformationEditTable.setEnabled(true);
						InformationEditTable.setRowSelectionAllowed(true);
						btnInformationTabAdd.setEnabled(true);
						btnInformationTabEdit.setEnabled(false);
						btnInformationTabDelete.setEnabled(false);
						btnInformationTabCancel.setEnabled(false);
						btnInformationTabSave.setEnabled(false);
						int totaltabs = InformationPanel.tabbedInformationPane.getTabCount() - 1;
						int currenttab = 0;
						while(currenttab < totaltabs){
							InformationPanel.tabbedInformationPane.setEnabledAt(currenttab, true);
							currenttab = currenttab + 1;
						}		
						InitialiseMenu.enableAllMenuButtons();
						textInformationTabID.setEditable(false);
						textInformationTabName.setEditable(false);
						textInformationTabContent.setEditable(false);
						textInformationTabContent.setBackground(UIManager.getColor("Panel.background"));
						checkboxInformationTabHTML.setEnabled(false);
						InformationEditScrollPane.remove(InformationEditTable);
						InformationEditScrollPane.setViewportView(null);
						initialiseTable();
						InformationEditScrollPane.setViewportView(InformationEditTable);
						clearInformationTabData();
						refreshInformationTabs();
					}
				}
			}
		});
    
		
	}
	
	/**
	 * Initialises the information tab data table so that it is visible (including getting the information from the database).
	 */
	private static void initialiseTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<Object> header; //used to store data header

	    try {
			data = DBEngine.getInformationTabData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<Object>();
	    header.add("ID");
	    header.add("TabName");
	    header.add("TabContent");
	    header.add("HTML");
	    
		//Table
		InformationEditTable = new JTable();
		InformationEditTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = -420962074252897158L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
		InformationEditTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		InformationEditTable.getColumnModel().getColumn(0).setMinWidth(80);
		InformationEditTable.getColumnModel().getColumn(0).setMaxWidth(80);
		InformationEditTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		InformationEditTable.getColumnModel().getColumn(1).setMinWidth(80);
		InformationEditTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		InformationEditTable.getColumnModel().getColumn(2).setPreferredWidth(0);
		InformationEditTable.getColumnModel().getColumn(2).setMinWidth(0);
		InformationEditTable.getColumnModel().getColumn(2).setMaxWidth(0);
		InformationEditTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		InformationEditTable.getColumnModel().getColumn(3).setMinWidth(80);
		InformationEditTable.getColumnModel().getColumn(3).setMaxWidth(80);
		InformationEditTable.getTableHeader().setReorderingAllowed(false);
		InformationEditTable.setAutoCreateRowSorter(false);
		
		addInformationEditMouseListener();
		
	}
	
	/**
	 * Adds the mouse listener to the information tab table that listens for mouse clicks.
	 */
	private static void addInformationEditMouseListener(){
		mouseListenerIsActive = true;
		
		InformationEditTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(mouseListenerIsActive){
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						InformationEditSelectedRow = target.getSelectedRow();
						if(InformationEditSelectedRow != -1){
							btnInformationTabEdit.setEnabled(true);
							btnInformationTabDelete.setEnabled(false);
							setInformationTabData();
						}
					}
				}			
			}
		});
	}
	
	/**
	 * Sets the fields on the information edit tab to contain the data for the selected information tab.
	 */
	private static void setInformationTabData(){
		if(InformationEditSelectedRow != -1){
			textInformationOrigTabID = (String) InformationEditTable.getValueAt(InformationEditSelectedRow,0);
			textInformationTabID.setText((String) InformationEditTable.getValueAt(InformationEditSelectedRow,0));
			textInformationTabName.setText((String) InformationEditTable.getValueAt(InformationEditSelectedRow,1));
			textInformationTabContent.setText((String) InformationEditTable.getValueAt(InformationEditSelectedRow,2));
			textInformationTabContent.setCaretPosition(0);
			checkboxInformationTabHTML.setSelected((Boolean) stringToBool((String) InformationEditTable.getValueAt(InformationEditSelectedRow,3)));
		}
	}
	
	/**
	 * Clears all data from all fields on the information edit tab.
	 */
	private static void clearInformationTabData(){
		textInformationOrigTabID = "";
		textInformationTabID.setText("");
		textInformationTabName.setText("");
		textInformationTabContent.setText("");
		checkboxInformationTabHTML.setSelected(false);
	}
	
	/**
	 * Converts a string to a boolean.
	 * 
	 * @param s String value (either 1 or 0).
	 * @return Returns the boolean value of the string.
	 */
	private static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}
	
	/**
	 * Refreshes all of the information tabs so that newly added/removed/edited tabs are instantly visible.
	 */
	private static void refreshInformationTabs(){
		InformationPanel.tabbedInformationPane.removeAll();
		InformationPanel.dynamicallyAddTabs();
		InformationPanel.InformationPanel.repaint();
		InformationPanel.tabbedInformationPane.repaint();
		int finaltab = InformationPanel.tabbedInformationPane.getTabCount() - 1;
		InformationPanel.tabbedInformationPane.setSelectedIndex(finaltab);
	}
	
}