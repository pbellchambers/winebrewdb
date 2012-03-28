package uk.co.pori.winebrewdb.ledger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import uk.co.pori.winebrewdb.InitializeMenu;
import uk.co.pori.winebrewdb.MainWindow;
import uk.co.pori.winebrewdb.NumberRenderer;
import uk.co.pori.winebrewdb.sqlite.DBEngine;


import net.miginfocom.swing.MigLayout;


public class LedgerBrewCostDataPanel extends JPanel {
	
	private static final long serialVersionUID = -1660532837353771865L;
	static JPanel tabbedLedgerBrewCostPanel;
	static JScrollPane LedgerBrewCostScrollPane;
	static JTable LedgerBrewCostTable;
	private static int LedgerBrewCostSelectedRow;
	public static JTextField textLedgerBrewCostRef;
	public static JTextField textLedgerBrewCostLineItem;
	public static JFormattedTextField textLedgerBrewCostCost;
	public static JTextField textLedgerBrewCostSupplier;
	static JButton btnLedgerBrewCostEdit;
	private static JButton btnLedgerBrewCostDelete;
	private static JButton btnLedgerBrewCostCancel;
	private static JButton btnLedgerBrewCostSave;
	private static String isNewCost;
	private static boolean mouseListenerIsActive;
	static JButton btnLedgerBrewCostAdd;
	public static JFormattedTextField textLedgerBrewCostTotalCost;
	public static JFormattedTextField textLedgerBrewCostCostPerBottle;
	public static JTextField textLedgerBrewRef;
	public static JTextField textLedgerBrewNumberBottles;

	
	public static void InitializePanel(){
		
		tabbedLedgerBrewCostPanel = new JPanel();
		tabbedLedgerBrewCostPanel.setBackground(Color.WHITE);
		tabbedLedgerBrewCostPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][grow][grow][130px:130px:130px][grow]", "[grow][][10px:10px:10px][][][10px:10px:10px][]"));
		
		
		//ScrollPane
	    LedgerBrewCostScrollPane = new JScrollPane();
	    LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
	    tabbedLedgerBrewCostPanel.add(LedgerBrewCostScrollPane, "cell 0 0 6 1,grow");
		
		JLabel lblLedgerBrewCostTotalCost = new JLabel("Total Cost:");
		tabbedLedgerBrewCostPanel.add(lblLedgerBrewCostTotalCost, "cell 0 1,alignx trailing");
		
		textLedgerBrewCostTotalCost = new JFormattedTextField();
		textLedgerBrewCostTotalCost.setEditable(false);
		textLedgerBrewCostTotalCost.setBackground(new Color(240, 255, 240));
		textLedgerBrewCostTotalCost.setMinimumSize(new Dimension(80, 23));
		tabbedLedgerBrewCostPanel.add(textLedgerBrewCostTotalCost, "cell 1 1 2");
		
		JLabel lblLedgerBrewCostCostPerBottle = new JLabel("Cost Per Bottle:");
		tabbedLedgerBrewCostPanel.add(lblLedgerBrewCostCostPerBottle, "cell 3 1,alignx trailing");
		
		textLedgerBrewCostCostPerBottle = new JFormattedTextField();
		textLedgerBrewCostCostPerBottle.setEditable(false);
		textLedgerBrewCostCostPerBottle.setBackground(new Color(240, 255, 240));
		textLedgerBrewCostCostPerBottle.setMinimumSize(new Dimension(80, 23));
		tabbedLedgerBrewCostPanel.add(textLedgerBrewCostCostPerBottle, "cell 4 1 2");

		textLedgerBrewRef = new JTextField();
		textLedgerBrewRef.setEditable(false);
		//tabbedLedgerBrewCostPanel.add(textLedgerBrewRef, "cell 1 1,growx");

		textLedgerBrewCostRef = new JTextField();
		textLedgerBrewCostRef.setEditable(false);
		//tabbedLedgerBrewCostPanel.add(textLedgerBrewCostRef, "cell 1 1,growx");
		
		textLedgerBrewNumberBottles = new JTextField();
		textLedgerBrewNumberBottles.setEditable(false);
		//tabbedLedgerBrewCostPanel.add(textLedgerBrewNumberBottles, "cell 1 1,growx");
		
		JLabel lblLedgerBrewCostLineItem = new JLabel("Line Item:");
		tabbedLedgerBrewCostPanel.add(lblLedgerBrewCostLineItem, "cell 0 3,alignx trailing");
		
		textLedgerBrewCostLineItem = new JTextField();
		textLedgerBrewCostLineItem.setEditable(false);
		tabbedLedgerBrewCostPanel.add(textLedgerBrewCostLineItem, "cell 1 3 5,growx");
		
		JLabel lblLedgerBrewCostCost = new JLabel("Cost:");
		tabbedLedgerBrewCostPanel.add(lblLedgerBrewCostCost, "cell 0 4,alignx trailing");
		
		textLedgerBrewCostCost = new JFormattedTextField();
		textLedgerBrewCostCost.setEditable(false);
		tabbedLedgerBrewCostPanel.add(textLedgerBrewCostCost, "cell 1 4 2,growx");
		
		JLabel lblLedgerBrewCostSupplier = new JLabel("Supplier:");
		tabbedLedgerBrewCostPanel.add(lblLedgerBrewCostSupplier, "cell 3 4,alignx trailing");
		
		textLedgerBrewCostSupplier = new JTextField();
		textLedgerBrewCostSupplier.setEditable(false);
		tabbedLedgerBrewCostPanel.add(textLedgerBrewCostSupplier, "cell 4 4 2,growx");
				
		btnLedgerBrewCostAdd = new JButton("Add");
		tabbedLedgerBrewCostPanel.add(btnLedgerBrewCostAdd, "cell 0 6,growx");
		
		btnLedgerBrewCostEdit = new JButton("Edit");
		btnLedgerBrewCostEdit.setEnabled(false);
		tabbedLedgerBrewCostPanel.add(btnLedgerBrewCostEdit, "cell 1 6,growx");
		
		btnLedgerBrewCostDelete = new JButton("Delete Line Item");
		btnLedgerBrewCostDelete.setEnabled(false);
		tabbedLedgerBrewCostPanel.add(btnLedgerBrewCostDelete, "cell 2 6,growx");
		
		btnLedgerBrewCostCancel = new JButton("Cancel");
		btnLedgerBrewCostCancel.setEnabled(false);
		tabbedLedgerBrewCostPanel.add(btnLedgerBrewCostCancel, "cell 4 6,growx");
		
		btnLedgerBrewCostSave = new JButton("Save / Insert");
		btnLedgerBrewCostSave.setEnabled(false);
		tabbedLedgerBrewCostPanel.add(btnLedgerBrewCostSave, "cell 5 6,growx");
		
		//Add button listeners
		btnLedgerBrewCostAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				clearLedgerBrewCostData();
				setBrewTotalCostData();
				isNewCost = "true";
				LedgerBrewCostTable.setEnabled(false);
				LedgerBrewCostTable.setRowSelectionAllowed(false);
				btnLedgerBrewCostAdd.setEnabled(false);
				btnLedgerBrewCostEdit.setEnabled(false);
				btnLedgerBrewCostDelete.setEnabled(false);
				btnLedgerBrewCostCancel.setEnabled(true);
				btnLedgerBrewCostSave.setEnabled(true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
				InitializeMenu.DisableAllMenuButtons();
				textLedgerBrewCostLineItem.setEditable(true);
				textLedgerBrewCostCost.setEditable(true);
				textLedgerBrewCostSupplier.setEditable(true);

		        
			}
		});
		
		btnLedgerBrewCostEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				isNewCost = "false";
				LedgerBrewCostTable.setEnabled(false);
				LedgerBrewCostTable.setRowSelectionAllowed(false);
				btnLedgerBrewCostAdd.setEnabled(false);
				btnLedgerBrewCostEdit.setEnabled(false);
				btnLedgerBrewCostDelete.setEnabled(true);
				btnLedgerBrewCostCancel.setEnabled(true);
				btnLedgerBrewCostSave.setEnabled(true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
				InitializeMenu.DisableAllMenuButtons();
				textLedgerBrewCostLineItem.setEditable(true);
				textLedgerBrewCostCost.setEditable(true);
				textLedgerBrewCostSupplier.setEditable(true);
			}
		});
		
		btnLedgerBrewCostDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the selected line item.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteBrewCost(textLedgerBrewRef.getText(), textLedgerBrewCostRef.getText());
							DBEngine.setTotalBrewCost(textLedgerBrewRef.getText(), textLedgerBrewNumberBottles.getText());							
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred deleting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerBrewCostTable.setEnabled(true);
						LedgerBrewCostTable.setRowSelectionAllowed(true);
					  	btnLedgerBrewCostAdd.setEnabled(true);
						btnLedgerBrewCostEdit.setEnabled(false);
						btnLedgerBrewCostDelete.setEnabled(false);
						btnLedgerBrewCostCancel.setEnabled(false);
						btnLedgerBrewCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitializeMenu.EnableAllMenuButtons();
						textLedgerBrewCostLineItem.setEditable(false);
						textLedgerBrewCostCost.setEditable(false);
						textLedgerBrewCostSupplier.setEditable(false);
						LedgerBrewCostScrollPane.remove(LedgerBrewCostTable);
						LedgerBrewCostScrollPane.setViewportView(null);
						initializeTable();
						LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
						clearLedgerBrewCostData();
						setBrewTotalCostData();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.remove(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(null);
						LedgerBrewCostSearchPanel.initializeTable();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnLedgerBrewCostCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					isNewCost = "false";
					mouseListenerIsActive = true;
					LedgerBrewCostScrollPane.remove(LedgerBrewCostTable);
					LedgerBrewCostScrollPane.setViewportView(null);
					initializeTable();
					LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
					clearLedgerBrewCostData();
					setBrewTotalCostData();
					LedgerBrewCostTable.setEnabled(true);
					LedgerBrewCostTable.setRowSelectionAllowed(true);
					btnLedgerBrewCostAdd.setEnabled(true);
					btnLedgerBrewCostEdit.setEnabled(false);
					btnLedgerBrewCostDelete.setEnabled(false);
					btnLedgerBrewCostCancel.setEnabled(false);
					btnLedgerBrewCostSave.setEnabled(false);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
					InitializeMenu.EnableAllMenuButtons();
					textLedgerBrewCostLineItem.setEditable(false);
					textLedgerBrewCostCost.setEditable(false);
					textLedgerBrewCostSupplier.setEditable(false);
				}else {
					mouseListenerIsActive = true;
					isNewCost = "false";
					setLedgerBrewCostData();
					LedgerBrewCostTable.setEnabled(true);
					LedgerBrewCostTable.setRowSelectionAllowed(true);
					btnLedgerBrewCostAdd.setEnabled(true);
					btnLedgerBrewCostEdit.setEnabled(true);
					btnLedgerBrewCostDelete.setEnabled(false);
					btnLedgerBrewCostCancel.setEnabled(false);
					btnLedgerBrewCostSave.setEnabled(false);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
					InitializeMenu.EnableAllMenuButtons();
					textLedgerBrewCostLineItem.setEditable(false);
					textLedgerBrewCostCost.setEditable(false);
					textLedgerBrewCostSupplier.setEditable(false);
				}
			}
		});
		
		
		btnLedgerBrewCostSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					if (textLedgerBrewCostCost.getText() == null || textLedgerBrewCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
								"You must enter a cost value.",
								"Error",
								JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							textLedgerBrewCostRef.setText(DBEngine.getNextBrewCostRef(textLedgerBrewRef.getText()));
							DBEngine.addBrewCost(textLedgerBrewRef.getText(), textLedgerBrewCostLineItem.getText().replaceAll("'", "''"), textLedgerBrewCostCost.getText().replaceAll("[^0-9\\.]", ""), textLedgerBrewCostSupplier.getText().replaceAll("'", "''"));
							DBEngine.setTotalBrewCost(textLedgerBrewRef.getText(), textLedgerBrewNumberBottles.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred inserting data into the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerBrewCostTable.setEnabled(true);
						LedgerBrewCostTable.setRowSelectionAllowed(true);
						btnLedgerBrewCostAdd.setEnabled(true);
						btnLedgerBrewCostEdit.setEnabled(false);
						btnLedgerBrewCostDelete.setEnabled(false);
						btnLedgerBrewCostCancel.setEnabled(false);
						btnLedgerBrewCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitializeMenu.EnableAllMenuButtons();
						textLedgerBrewCostLineItem.setEditable(false);
						textLedgerBrewCostCost.setEditable(false);
						textLedgerBrewCostSupplier.setEditable(false);
						LedgerBrewCostScrollPane.remove(LedgerBrewCostTable);
						LedgerBrewCostScrollPane.setViewportView(null);
						initializeTable();
						LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
						clearLedgerBrewCostData();
						setBrewTotalCostData();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.remove(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(null);
						LedgerBrewCostSearchPanel.initializeTable();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
					}
					
				} else {
					if (textLedgerBrewCostCost.getText() == null || textLedgerBrewCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
							"You must enter a cost value.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.updateBrewCost(textLedgerBrewRef.getText(), textLedgerBrewCostLineItem.getText().replaceAll("'", "''"), textLedgerBrewCostCost.getText().replaceAll("[^0-9\\.]", ""), textLedgerBrewCostSupplier.getText().replaceAll("'", "''"), textLedgerBrewCostRef.getText());
							DBEngine.setTotalBrewCost(textLedgerBrewRef.getText(), textLedgerBrewNumberBottles.getText());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred updating data in the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerBrewCostTable.setEnabled(true);
						LedgerBrewCostTable.setRowSelectionAllowed(true);
						btnLedgerBrewCostAdd.setEnabled(true);
						btnLedgerBrewCostEdit.setEnabled(false);
						btnLedgerBrewCostDelete.setEnabled(false);
						btnLedgerBrewCostCancel.setEnabled(false);
						btnLedgerBrewCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitializeMenu.EnableAllMenuButtons();
						textLedgerBrewCostLineItem.setEditable(false);
						textLedgerBrewCostCost.setEditable(false);
						textLedgerBrewCostSupplier.setEditable(false);
						LedgerBrewCostScrollPane.remove(LedgerBrewCostTable);
						LedgerBrewCostScrollPane.setViewportView(null);
						initializeTable();
						LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
						clearLedgerBrewCostData();
						setBrewTotalCostData();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.remove(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(null);
						LedgerBrewCostSearchPanel.initializeTable();
						LedgerBrewCostSearchPanel.LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostSearchPanel.LedgerBrewCostTable);
					}
				}
			}
		});
    
		
	}
	
	public static void initializeTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getBrewCosts(textLedgerBrewRef.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
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

		LedgerBrewCostTable = new JTable(model);

		LedgerBrewCostTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMinWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMaxWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMaxWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		LedgerBrewCostTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		LedgerBrewCostTable.getTableHeader().setReorderingAllowed(false);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		LedgerBrewCostTable.setRowSorter(sorter);

		
		addLedgerBrewCostMouseListener();
		
	}
	
	public static void addLedgerBrewCostMouseListener(){
		mouseListenerIsActive = true;
		
		LedgerBrewCostTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(mouseListenerIsActive){
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						LedgerBrewCostSelectedRow = target.getSelectedRow();
						if(LedgerBrewCostSelectedRow != -1){
							btnLedgerBrewCostEdit.setEnabled(true);
							btnLedgerBrewCostDelete.setEnabled(false);
							setLedgerBrewCostData();
						}
					}
				}			
			}
		});
	}
	
	public static void removeLedgerBrewCostMouseListener(){
		mouseListenerIsActive = false;
	}
	
	public static void setLedgerBrewCostData(){
		if(LedgerBrewCostSelectedRow != -1){
			textLedgerBrewCostRef.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSelectedRow,0));
			textLedgerBrewCostLineItem.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSelectedRow,1));
			textLedgerBrewCostCost.setText(LedgerBrewCostTable.getValueAt(LedgerBrewCostSelectedRow,2).toString());
			textLedgerBrewCostSupplier.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSelectedRow,3));
		}
	}
	
	public static void setBrewTotalCostData(){
		try {
			textLedgerBrewCostTotalCost.setText(DBEngine.getTotalBrewCost(textLedgerBrewRef.getText()));
			textLedgerBrewCostCostPerBottle.setText(DBEngine.getBrewCostPerBottle(textLedgerBrewRef.getText()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}


	
	public static void clearLedgerBrewCostData(){
		textLedgerBrewCostRef.setText("");
		textLedgerBrewCostLineItem.setText("");
		textLedgerBrewCostCost.setText("");
		textLedgerBrewCostSupplier.setText("");
	}

	
}