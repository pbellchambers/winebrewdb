package uk.co.pbellchambers.winebrewdb.legacy.brew;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.legacy.InitialiseMenu;
import uk.co.pbellchambers.winebrewdb.legacy.LegacyApp;
import uk.co.pbellchambers.winebrewdb.legacy.util.DBEngine;
import uk.co.pbellchambers.winebrewdb.legacy.util.NumberRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * This is the brew cost panel to be displayed as a tab on the main brew panel tab bar. It shows the cost data for the currently selected brew.
 * 
 * @author Paul.Bellchambers
 *
 */
public class BrewCostPanel extends JPanel {
	
	private static final long serialVersionUID = -1974715040788382066L;
	static JPanel tabbedBrewCostPanel;
	static JScrollPane BrewCostScrollPane;
	static JTable BrewCostTable;
	private static int BrewCostSelectedRow;
	public static JTextField textBrewCostRef;
	public static JTextField textBrewCostLineItem;
	public static JFormattedTextField textBrewCostCost;
	public static JTextField textBrewCostSupplier;
	static JButton btnBrewCostEdit;
	private static JButton btnBrewCostDelete;
	private static JButton btnBrewCostCancel;
	private static JButton btnBrewCostSave;
	private static String isNewCost;
	private static boolean mouseListenerIsActive;
	private static JButton btnBrewCostAdd;
	public static JFormattedTextField textBrewCostTotalCost;
	public static JFormattedTextField textBrewCostCostPerBottle;

	/**
	 * Initialises the brew cost panel so that it can be viewed, including initialising the table.
	 */
	public static void initialisePanel(){
		
		tabbedBrewCostPanel = new JPanel();
		tabbedBrewCostPanel.setBackground(Color.WHITE);
		tabbedBrewCostPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][grow][grow][130px:130px:130px][grow]", "[grow][][10px:10px:10px][][][10px:10px:10px][]"));
		
		
		//Initialise Table
		initialiseTable();
		
		
		//ScrollPane
	    BrewCostScrollPane = new JScrollPane();
	    BrewCostScrollPane.setViewportView(BrewCostTable);
	    tabbedBrewCostPanel.add(BrewCostScrollPane, "cell 0 0 6 1,grow");
		
		JLabel lblBrewCostTotalCost = new JLabel("Total Cost:");
		tabbedBrewCostPanel.add(lblBrewCostTotalCost, "cell 0 1,alignx trailing");
		
		textBrewCostTotalCost = new JFormattedTextField();
		textBrewCostTotalCost.setEditable(false);
		textBrewCostTotalCost.setBackground(new Color(240, 255, 240));
		textBrewCostTotalCost.setMinimumSize(new Dimension(80, 23));
		tabbedBrewCostPanel.add(textBrewCostTotalCost, "cell 1 1 2");
		
		JLabel lblBrewCostCostPerBottle = new JLabel("Cost Per Bottle:");
		tabbedBrewCostPanel.add(lblBrewCostCostPerBottle, "cell 3 1,alignx trailing");
		
		textBrewCostCostPerBottle = new JFormattedTextField();
		textBrewCostCostPerBottle.setEditable(false);
		textBrewCostCostPerBottle.setBackground(new Color(240, 255, 240));
		textBrewCostCostPerBottle.setMinimumSize(new Dimension(80, 23));
		tabbedBrewCostPanel.add(textBrewCostCostPerBottle, "cell 4 1 2");

		textBrewCostRef = new JTextField();
		textBrewCostRef.setEditable(false);
		//tabbedBrewCostPanel.add(textBrewCostRef, "cell 1 1,growx");
		
		JLabel lblBrewCostLineItem = new JLabel("Line Item:");
		tabbedBrewCostPanel.add(lblBrewCostLineItem, "cell 0 3,alignx trailing");
		
		textBrewCostLineItem = new JTextField();
		textBrewCostLineItem.setEditable(false);
		tabbedBrewCostPanel.add(textBrewCostLineItem, "cell 1 3 5,growx");
		
		JLabel lblBrewCostCost = new JLabel("Cost:");
		tabbedBrewCostPanel.add(lblBrewCostCost, "cell 0 4,alignx trailing");
		
		textBrewCostCost = new JFormattedTextField();
		textBrewCostCost.setEditable(false);
		tabbedBrewCostPanel.add(textBrewCostCost, "cell 1 4 2,growx");
		
		JLabel lblBrewCostSupplier = new JLabel("Supplier:");
		tabbedBrewCostPanel.add(lblBrewCostSupplier, "cell 3 4,alignx trailing");
		
		textBrewCostSupplier = new JTextField();
		textBrewCostSupplier.setEditable(false);
		tabbedBrewCostPanel.add(textBrewCostSupplier, "cell 4 4 2,growx");
				
		btnBrewCostAdd = new JButton("Add");
		tabbedBrewCostPanel.add(btnBrewCostAdd, "cell 0 6,growx");
		
		btnBrewCostEdit = new JButton("Edit");
		btnBrewCostEdit.setEnabled(false);
		tabbedBrewCostPanel.add(btnBrewCostEdit, "cell 1 6,growx");
		
		btnBrewCostDelete = new JButton("Delete Line Item");
		btnBrewCostDelete.setEnabled(false);
		tabbedBrewCostPanel.add(btnBrewCostDelete, "cell 2 6,growx");
		
		btnBrewCostCancel = new JButton("Cancel");
		btnBrewCostCancel.setEnabled(false);
		tabbedBrewCostPanel.add(btnBrewCostCancel, "cell 4 6,growx");
		
		btnBrewCostSave = new JButton("Save / Insert");
		btnBrewCostSave.setEnabled(false);
		tabbedBrewCostPanel.add(btnBrewCostSave, "cell 5 6,growx");
		
		//Add button listeners
		btnBrewCostAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				clearBrewCostData();
				setBrewTotalCostData();
				isNewCost = "true";
				BrewCostTable.setEnabled(false);
				BrewCostTable.setRowSelectionAllowed(false);
				btnBrewCostAdd.setEnabled(false);
				btnBrewCostEdit.setEnabled(false);
				btnBrewCostDelete.setEnabled(false);
				btnBrewCostCancel.setEnabled(true);
				btnBrewCostSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(5, false);
				InitialiseMenu.disableAllMenuButtons();
				textBrewCostLineItem.setEditable(true);
				textBrewCostCost.setEditable(true);
				textBrewCostSupplier.setEditable(true);

		        
			}
		});
		
		btnBrewCostEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				isNewCost = "false";
				BrewCostTable.setEnabled(false);
				BrewCostTable.setRowSelectionAllowed(false);
				btnBrewCostAdd.setEnabled(false);
				btnBrewCostEdit.setEnabled(false);
				btnBrewCostDelete.setEnabled(true);
				btnBrewCostCancel.setEnabled(true);
				btnBrewCostSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(5, false);
				InitialiseMenu.disableAllMenuButtons();
				textBrewCostLineItem.setEditable(true);
				textBrewCostCost.setEditable(true);
				textBrewCostSupplier.setEditable(true);
			}
		});
		
		btnBrewCostDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the selected line item.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteBrewCost(BrewDataPanel.textBrewRefB.getText(), textBrewCostRef.getText());
							DBEngine.setTotalBrewCost(BrewDataPanel.textBrewRefB.getText(), BrewDataPanel.textBrewNumberBottlesB.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred deleting data from the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						BrewCostTable.setEnabled(true);
						BrewCostTable.setRowSelectionAllowed(true);
					  	btnBrewCostAdd.setEnabled(true);
						btnBrewCostEdit.setEnabled(false);
						btnBrewCostDelete.setEnabled(false);
						btnBrewCostCancel.setEnabled(false);
						btnBrewCostSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
						InitialiseMenu.enableAllMenuButtons();
						textBrewCostLineItem.setEditable(false);
						textBrewCostCost.setEditable(false);
						textBrewCostSupplier.setEditable(false);
						BrewCostScrollPane.remove(BrewCostTable);
						BrewCostScrollPane.setViewportView(null);
						initialiseTable();
						BrewCostScrollPane.setViewportView(BrewCostTable);
						clearBrewCostData();
						setBrewTotalCostData();
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnBrewCostCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					isNewCost = "false";
					mouseListenerIsActive = true;
					BrewCostScrollPane.remove(BrewCostTable);
					BrewCostScrollPane.setViewportView(null);
					initialiseTable();
					BrewCostScrollPane.setViewportView(BrewCostTable);
					clearBrewCostData();
					setBrewTotalCostData();
					BrewCostTable.setEnabled(true);
					BrewCostTable.setRowSelectionAllowed(true);
					btnBrewCostAdd.setEnabled(true);
					btnBrewCostEdit.setEnabled(false);
					btnBrewCostDelete.setEnabled(false);
					btnBrewCostCancel.setEnabled(false);
					btnBrewCostSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
					InitialiseMenu.enableAllMenuButtons();
					textBrewCostLineItem.setEditable(false);
					textBrewCostCost.setEditable(false);
					textBrewCostSupplier.setEditable(false);
				}else {
					mouseListenerIsActive = true;
					isNewCost = "false";
					setBrewCostData();
					BrewCostTable.setEnabled(true);
					BrewCostTable.setRowSelectionAllowed(true);
					btnBrewCostAdd.setEnabled(true);
					btnBrewCostEdit.setEnabled(true);
					btnBrewCostDelete.setEnabled(false);
					btnBrewCostCancel.setEnabled(false);
					btnBrewCostSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
					InitialiseMenu.enableAllMenuButtons();
					textBrewCostLineItem.setEditable(false);
					textBrewCostCost.setEditable(false);
					textBrewCostSupplier.setEditable(false);
				}
			}
		});
		
		
		btnBrewCostSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					if (textBrewCostCost.getText() == null || textBrewCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
								"You must enter a cost value.",
								"Error",
								JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							textBrewCostRef.setText(DBEngine.getNextBrewCostRef(BrewDataPanel.textBrewRefB.getText()));
							DBEngine.addBrewCost(BrewDataPanel.textBrewRefB.getText(), textBrewCostLineItem.getText().replaceAll("'", "''"), textBrewCostCost.getText().replaceAll("[^0-9\\.]", ""), textBrewCostSupplier.getText().replaceAll("'", "''"));
							DBEngine.setTotalBrewCost(BrewDataPanel.textBrewRefB.getText(), BrewDataPanel.textBrewNumberBottlesB.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred inserting data into the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						BrewCostTable.setEnabled(true);
						BrewCostTable.setRowSelectionAllowed(true);
						btnBrewCostAdd.setEnabled(true);
						btnBrewCostEdit.setEnabled(false);
						btnBrewCostDelete.setEnabled(false);
						btnBrewCostCancel.setEnabled(false);
						btnBrewCostSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
						InitialiseMenu.enableAllMenuButtons();
						textBrewCostLineItem.setEditable(false);
						textBrewCostCost.setEditable(false);
						textBrewCostSupplier.setEditable(false);
						BrewCostScrollPane.remove(BrewCostTable);
						BrewCostScrollPane.setViewportView(null);
						initialiseTable();
						BrewCostScrollPane.setViewportView(BrewCostTable);
						clearBrewCostData();
						setBrewTotalCostData();
						BrewSearchPanel.BrewScrollPane.remove(BrewSearchPanel.BrewTable);
						BrewSearchPanel.BrewScrollPane.setViewportView(null);
						BrewSearchPanel.initialiseTable();
						BrewSearchPanel.BrewScrollPane.setViewportView(BrewSearchPanel.BrewTable);
					}
					
				} else {
					if (textBrewCostCost.getText() == null || textBrewCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
							"You must enter a cost value.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.updateBrewCost(BrewDataPanel.textBrewRefB.getText(), textBrewCostLineItem.getText().replaceAll("'", "''"), textBrewCostCost.getText().replaceAll("[^0-9\\.]", ""), textBrewCostSupplier.getText().replaceAll("'", "''"), textBrewCostRef.getText());
							DBEngine.setTotalBrewCost(BrewDataPanel.textBrewRefB.getText(), BrewDataPanel.textBrewNumberBottlesB.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred updating data in the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						BrewCostTable.setEnabled(true);
						BrewCostTable.setRowSelectionAllowed(true);
						btnBrewCostAdd.setEnabled(true);
						btnBrewCostEdit.setEnabled(false);
						btnBrewCostDelete.setEnabled(false);
						btnBrewCostCancel.setEnabled(false);
						btnBrewCostSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
						InitialiseMenu.enableAllMenuButtons();
						textBrewCostLineItem.setEditable(false);
						textBrewCostCost.setEditable(false);
						textBrewCostSupplier.setEditable(false);
						BrewCostScrollPane.remove(BrewCostTable);
						BrewCostScrollPane.setViewportView(null);
						initialiseTable();
						BrewCostScrollPane.setViewportView(BrewCostTable);
						clearBrewCostData();
						setBrewTotalCostData();
						BrewSearchPanel.BrewScrollPane.remove(BrewSearchPanel.BrewTable);
						BrewSearchPanel.BrewScrollPane.setViewportView(null);
						BrewSearchPanel.initialiseTable();
						BrewSearchPanel.BrewScrollPane.setViewportView(BrewSearchPanel.BrewTable);
					}
				}
			}
		});
    
		
	}
	
	/**
	 * Initialises the brew costs table on the brew cost tab so that it can be viewed (including getting the data from the database).
	 */
	public static void initialiseTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getBrewCosts(BrewDataPanel.textBrewRefB.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<String>();
	    header.add("Brew Cost Ref");
	    header.add("Line Item");
	    header.add("Cost");
	    header.add("Supplier");
	    
		//Table	    
	    TableModel model = new DefaultTableModel(data, header) {

			private static final long serialVersionUID = -8427294766395472858L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
	          Class returnValue;
	          if ((column >= 0) && (column < getColumnCount())) {
	            returnValue = getValueAt(0, column).getClass();
	          } else {
	            returnValue = Object.class;
	          }
	          return returnValue;
	        }
			
			public boolean isCellEditable(int row, int column) {
		    	return false;
		    }
	      };

		BrewCostTable = new JTable(model);

		BrewCostTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		BrewCostTable.getColumnModel().getColumn(0).setMinWidth(0);
		BrewCostTable.getColumnModel().getColumn(0).setMaxWidth(0);
		BrewCostTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		BrewCostTable.getColumnModel().getColumn(1).setMinWidth(5);
		BrewCostTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		BrewCostTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		BrewCostTable.getColumnModel().getColumn(2).setMinWidth(5);
		BrewCostTable.getColumnModel().getColumn(2).setMaxWidth(90);
		BrewCostTable.getColumnModel().getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		BrewCostTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		BrewCostTable.getColumnModel().getColumn(3).setMinWidth(5);
		BrewCostTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		BrewCostTable.getTableHeader().setReorderingAllowed(false);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		BrewCostTable.setRowSorter(sorter);

		
		addBrewCostMouseListener();
		
	}
	
	/**
	 * Adds the mouse listener to the brew costs table that listens for mouse clicks.
	 */
	private static void addBrewCostMouseListener(){
		mouseListenerIsActive = true;
		
		BrewCostTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(mouseListenerIsActive){
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						BrewCostSelectedRow = target.getSelectedRow();
						if(BrewCostSelectedRow != -1){
							btnBrewCostEdit.setEnabled(true);
							btnBrewCostDelete.setEnabled(false);
							setBrewCostData();
						}
					}
				}			
			}
		});
	}
	
	/**
	 * Sets the data in the fields on the Brew Costs tab to the currently selected brew cost.
	 */
	private static void setBrewCostData(){
		if(BrewCostSelectedRow != -1){
			textBrewCostRef.setText((String) BrewCostTable.getValueAt(BrewCostSelectedRow,0));
			textBrewCostLineItem.setText((String) BrewCostTable.getValueAt(BrewCostSelectedRow,1));
			textBrewCostCost.setText(BrewCostTable.getValueAt(BrewCostSelectedRow,2).toString());
			textBrewCostSupplier.setText((String) BrewCostTable.getValueAt(BrewCostSelectedRow,3));
		}
	}
	
	/**
	 * Calculates and sets the brew total cost and cost per bottle.
	 */
	public static void setBrewTotalCostData(){
		try {
			textBrewCostTotalCost.setText(DBEngine.getTotalBrewCost(BrewDataPanel.textBrewRefB.getText()));
			textBrewCostCostPerBottle.setText(DBEngine.getBrewCostPerBottle(BrewDataPanel.textBrewRefB.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}


	/**
	 * Clears all data from all fields on the brew costs tab.
	 */
	public static void clearBrewCostData(){
		textBrewCostRef.setText("");
		textBrewCostLineItem.setText("");
		textBrewCostCost.setText("");
		textBrewCostSupplier.setText("");
		textBrewCostTotalCost.setText("");
	}

	
}