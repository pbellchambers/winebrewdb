package uk.co.pori.winebrewdb.ledger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import uk.co.pori.winebrewdb.Dates;
import uk.co.pori.winebrewdb.InitialiseMenu;
import uk.co.pori.winebrewdb.MainWindow;
import uk.co.pori.winebrewdb.NumberRenderer;
import uk.co.pori.winebrewdb.sqlite.DBEngine;

import agiletrack.swing.JDateChooser;

import net.miginfocom.swing.MigLayout;

/**
 * This is the ledger equipment panel to be displayed as a tab on the main ledger panel tab bar. It shows the ledger equipment costs data.
 * 
 * @author Paul.Bellchambers
 *
 */
public class LedgerEquipmentPanel extends JPanel {

	private static final long serialVersionUID = -6282891098304905534L;
	static JPanel tabbedLedgerEquipmentCostPanel;
	static JScrollPane LedgerEquipmentCostScrollPane;
	static JTable LedgerEquipmentCostTable;
	private static int LedgerEquipmentCostSelectedRow;
	public static JTextField textLedgerEquipmentCostRef;
	public static JTextField textLedgerEquipmentCostLineItem;
	public static JFormattedTextField textLedgerEquipmentCostCost;
	public static JTextField textLedgerEquipmentCostSupplier;
	static JButton btnLedgerEquipmentCostEdit;
	private static JButton btnLedgerEquipmentCostDelete;
	private static JButton btnLedgerEquipmentCostCancel;
	private static JButton btnLedgerEquipmentCostSave;
	private static String isNewCost;
	private static boolean mouseListenerIsActive;
	private static JButton btnLedgerEquipmentCostAdd;
	public static JFormattedTextField textLedgerEquipmentCostTotalCost;
	public static JFormattedTextField textLedgerEquipmentCostCostPerBottle;
	public static JDateChooser chooserLedgerEquipmentCostDate;
	public static JFormattedTextField textLedgerEquipmentCostCostPerAllBrews;
	public static JTextField textLedgerEquipmentLineItemFilter;
	public static JTextField textLedgerEquipmentSupplierFilter;
	public static JTextField textLedgerEquipmentCostsFilterA;
	public static JTextField textLedgerEquipmentCostsFilterB;
	public static JDateChooser chooserLedgerEquipmentDatesFilterA;
	public static JDateChooser chooserLedgerEquipmentDatesFilterB;
	
	/**
	 * Initialises the ledger equipment panel so that it can be viewed (including populating the table).
	 */
	public static void initialisePanel(){
		tabbedLedgerEquipmentCostPanel = new JPanel();
		tabbedLedgerEquipmentCostPanel.setBackground(Color.WHITE);
		tabbedLedgerEquipmentCostPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][90px:90px:90px][grow][130px:130px:130px][grow]", "[95px:95px:95px][grow][][10px:10px:10px][][][10px:10px:10px][]"));
		
		//Filter Panel
		JPanel LedgerEquipmentFilterPanel = new JPanel();
		LedgerEquipmentFilterPanel.setBackground(UIManager.getColor("Panel.background"));
		LedgerEquipmentFilterPanel.setLayout(new MigLayout("", "[105px:105px:105px][grow][grow][105px:105px:105px][grow][grow]", "[][][5px:5px:5px][]"));
			
		JLabel lblLedgerEquipmentLineItemFilter = new JLabel("Line Item:");
		LedgerEquipmentFilterPanel.add(lblLedgerEquipmentLineItemFilter, "flowx,cell 0 0,alignx right");
			
		textLedgerEquipmentLineItemFilter = new JTextField();
		LedgerEquipmentFilterPanel.add(textLedgerEquipmentLineItemFilter, "cell 1 0 2,growx");
			
		JLabel lblLedgerEquipmentSupplierFilter = new JLabel("Supplier:");
		LedgerEquipmentFilterPanel.add(lblLedgerEquipmentSupplierFilter, "flowx,cell 3 0,alignx right");
			
		textLedgerEquipmentSupplierFilter = new JTextField();
		LedgerEquipmentFilterPanel.add(textLedgerEquipmentSupplierFilter, "cell 4 0 2,growx");
			
		JLabel lblLedgerEquipmentDatesFilter = new JLabel("Dates Between:");
		LedgerEquipmentFilterPanel.add(lblLedgerEquipmentDatesFilter, "flowx,cell 0 1,alignx right");
			
		chooserLedgerEquipmentDatesFilterA = new JDateChooser();
		LedgerEquipmentFilterPanel.add(chooserLedgerEquipmentDatesFilterA, "cell 1 1,growx");
		
		chooserLedgerEquipmentDatesFilterB = new JDateChooser();
		LedgerEquipmentFilterPanel.add(chooserLedgerEquipmentDatesFilterB, "cell 2 1,growx");
			
		JLabel lblLedgerEquipmentCostsFilter = new JLabel("Costs Betweeen:");
		LedgerEquipmentFilterPanel.add(lblLedgerEquipmentCostsFilter, "flowx,cell 3 1,alignx right");
			
		textLedgerEquipmentCostsFilterA = new JTextField();
		LedgerEquipmentFilterPanel.add(textLedgerEquipmentCostsFilterA, "cell 4 1,growx");
		
		textLedgerEquipmentCostsFilterB = new JTextField();
		LedgerEquipmentFilterPanel.add(textLedgerEquipmentCostsFilterB, "cell 5 1,growx");			
		
		JButton btnLedgerEquipmentCostsSearch = new JButton("Search");
		LedgerEquipmentFilterPanel.add(btnLedgerEquipmentCostsSearch, "cell 4 3 2 1,growx");

		tabbedLedgerEquipmentCostPanel.add(LedgerEquipmentFilterPanel, "cell 0 0 6,grow");
		
		
		//Initialise Table
		initialiseTable();
		
		
		//ScrollPane
	    LedgerEquipmentCostScrollPane = new JScrollPane();
	    LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
	    tabbedLedgerEquipmentCostPanel.add(LedgerEquipmentCostScrollPane, "cell 0 1 6 1,grow");
	    
	    JLabel lblLedgerEquipmentCostTotalCost = new JLabel("Total Cost:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostTotalCost, "cell 0 2,alignx trailing");
		
		textLedgerEquipmentCostTotalCost = new JFormattedTextField();
		textLedgerEquipmentCostTotalCost.setEditable(false);
		textLedgerEquipmentCostTotalCost.setBackground(new Color(240, 255, 240));
		textLedgerEquipmentCostTotalCost.setMinimumSize(new Dimension(80, 23));
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostTotalCost, "cell 1 2");
		
		JLabel lblLedgerEquipmentCostCostPerAllBrews = new JLabel("Cost / All Brews:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostCostPerAllBrews, "cell 2 2,alignx trailing");
		
		textLedgerEquipmentCostCostPerAllBrews = new JFormattedTextField();
		textLedgerEquipmentCostCostPerAllBrews.setEditable(false);
		textLedgerEquipmentCostCostPerAllBrews.setBackground(new Color(240, 255, 240));
		textLedgerEquipmentCostCostPerAllBrews.setMinimumSize(new Dimension(80, 23));
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostCostPerAllBrews, "cell 3 2");
		
		JLabel lblLedgerEquipmentCostCostPerBottle = new JLabel("Cost / All Bottles:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostCostPerBottle, "cell 4 2,alignx trailing");
		
		textLedgerEquipmentCostCostPerBottle = new JFormattedTextField();
		textLedgerEquipmentCostCostPerBottle.setEditable(false);
		textLedgerEquipmentCostCostPerBottle.setBackground(new Color(240, 255, 240));
		textLedgerEquipmentCostCostPerBottle.setMinimumSize(new Dimension(80, 23));
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostCostPerBottle, "cell 5 2");

		textLedgerEquipmentCostRef = new JTextField();
		textLedgerEquipmentCostRef.setEditable(false);
		//tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostRef, "cell 1 1,growx");
		
		JLabel lblEquipmentCostDate = new JLabel("Date:");
		tabbedLedgerEquipmentCostPanel.add(lblEquipmentCostDate, "flowx,cell 0 4,alignx trailing");
			
		chooserLedgerEquipmentCostDate = new JDateChooser();
		chooserLedgerEquipmentCostDate.setDisabled();
		chooserLedgerEquipmentCostDate.setMinimumSize(new Dimension(181, 23));
		tabbedLedgerEquipmentCostPanel.add(chooserLedgerEquipmentCostDate, "cell 1 4");
		
		JLabel lblLedgerEquipmentCostLineItem = new JLabel("Line Item:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostLineItem, "cell 2 4,alignx trailing");
		
		textLedgerEquipmentCostLineItem = new JTextField();
		textLedgerEquipmentCostLineItem.setEditable(false);
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostLineItem, "cell 3 4 3,growx");
		
		JLabel lblLedgerEquipmentCostCost = new JLabel("Cost:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostCost, "cell 0 5,alignx trailing");
		
		textLedgerEquipmentCostCost = new JFormattedTextField();
		textLedgerEquipmentCostCost.setEditable(false);
		textLedgerEquipmentCostCost.setMinimumSize(new Dimension(181, 23));
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostCost, "cell 1 5");
		
		JLabel lblLedgerEquipmentCostSupplier = new JLabel("Supplier:");
		tabbedLedgerEquipmentCostPanel.add(lblLedgerEquipmentCostSupplier, "cell 2 5,alignx trailing");
		
		textLedgerEquipmentCostSupplier = new JTextField();
		textLedgerEquipmentCostSupplier.setEditable(false);
		tabbedLedgerEquipmentCostPanel.add(textLedgerEquipmentCostSupplier, "cell 3 5 3,growx");
				
		btnLedgerEquipmentCostAdd = new JButton("Add");
		tabbedLedgerEquipmentCostPanel.add(btnLedgerEquipmentCostAdd, "cell 0 7,growx");
		
		btnLedgerEquipmentCostEdit = new JButton("Edit");
		btnLedgerEquipmentCostEdit.setEnabled(false);
		tabbedLedgerEquipmentCostPanel.add(btnLedgerEquipmentCostEdit, "cell 1 7,growx");
		
		btnLedgerEquipmentCostDelete = new JButton("Delete Line Item");
		btnLedgerEquipmentCostDelete.setEnabled(false);
		tabbedLedgerEquipmentCostPanel.add(btnLedgerEquipmentCostDelete, "cell 2 7,growx");
		
		btnLedgerEquipmentCostCancel = new JButton("Cancel");
		btnLedgerEquipmentCostCancel.setEnabled(false);
		tabbedLedgerEquipmentCostPanel.add(btnLedgerEquipmentCostCancel, "cell 4 7,growx");
		
		btnLedgerEquipmentCostSave = new JButton("Save / Insert");
		btnLedgerEquipmentCostSave.setEnabled(false);
		tabbedLedgerEquipmentCostPanel.add(btnLedgerEquipmentCostSave, "cell 5 7,growx");
		
		//Add button listeners
		btnLedgerEquipmentCostsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = true;
				LedgerEquipmentCostScrollPane.remove(LedgerEquipmentCostTable);
				LedgerEquipmentCostScrollPane.setViewportView(null);
				initialiseTable();
				LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
				clearLedgerEquipmentCostData();
				setBrewTotalCostData();
				LedgerEquipmentCostTable.setEnabled(true);
				LedgerEquipmentCostTable.setRowSelectionAllowed(true);
				btnLedgerEquipmentCostAdd.setEnabled(true);
				btnLedgerEquipmentCostEdit.setEnabled(false);
				btnLedgerEquipmentCostDelete.setEnabled(false);
				btnLedgerEquipmentCostCancel.setEnabled(false);
				btnLedgerEquipmentCostSave.setEnabled(false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, false);
			}
		});
	    
	    
		btnLedgerEquipmentCostAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				clearLedgerEquipmentCostData();
				setBrewTotalCostData();
				isNewCost = "true";
				LedgerEquipmentCostTable.setEnabled(false);
				LedgerEquipmentCostTable.setRowSelectionAllowed(false);
				btnLedgerEquipmentCostAdd.setEnabled(false);
				btnLedgerEquipmentCostEdit.setEnabled(false);
				btnLedgerEquipmentCostDelete.setEnabled(false);
				btnLedgerEquipmentCostCancel.setEnabled(true);
				btnLedgerEquipmentCostSave.setEnabled(true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, false);
				InitialiseMenu.disableAllMenuButtons();
				textLedgerEquipmentCostLineItem.setEditable(true);
				textLedgerEquipmentCostCost.setEditable(true);
				textLedgerEquipmentCostSupplier.setEditable(true);
				chooserLedgerEquipmentCostDate.setEnabled();

		        
			}
		});
		
		btnLedgerEquipmentCostEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseListenerIsActive = false;
				isNewCost = "false";
				LedgerEquipmentCostTable.setEnabled(false);
				LedgerEquipmentCostTable.setRowSelectionAllowed(false);
				btnLedgerEquipmentCostAdd.setEnabled(false);
				btnLedgerEquipmentCostEdit.setEnabled(false);
				btnLedgerEquipmentCostDelete.setEnabled(true);
				btnLedgerEquipmentCostCancel.setEnabled(true);
				btnLedgerEquipmentCostSave.setEnabled(true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, false);
				InitialiseMenu.disableAllMenuButtons();
				textLedgerEquipmentCostLineItem.setEditable(true);
				textLedgerEquipmentCostCost.setEditable(true);
				textLedgerEquipmentCostSupplier.setEditable(true);
				chooserLedgerEquipmentCostDate.setEnabled();
			}
		});
		
		btnLedgerEquipmentCostDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the selected line item.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteEquipmentCost();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred deleting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerEquipmentCostTable.setEnabled(true);
						LedgerEquipmentCostTable.setRowSelectionAllowed(true);
					  	btnLedgerEquipmentCostAdd.setEnabled(true);
						btnLedgerEquipmentCostEdit.setEnabled(false);
						btnLedgerEquipmentCostDelete.setEnabled(false);
						btnLedgerEquipmentCostCancel.setEnabled(false);
						btnLedgerEquipmentCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitialiseMenu.enableAllMenuButtons();
						textLedgerEquipmentCostLineItem.setEditable(false);
						textLedgerEquipmentCostCost.setEditable(false);
						textLedgerEquipmentCostSupplier.setEditable(false);
						chooserLedgerEquipmentCostDate.setDisabled();
						LedgerEquipmentCostScrollPane.remove(LedgerEquipmentCostTable);
						LedgerEquipmentCostScrollPane.setViewportView(null);
						initialiseTable();
						LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
						clearLedgerEquipmentCostData();
						setBrewTotalCostData();
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});

		btnLedgerEquipmentCostCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					isNewCost = "false";
					mouseListenerIsActive = true;
					LedgerEquipmentCostScrollPane.remove(LedgerEquipmentCostTable);
					LedgerEquipmentCostScrollPane.setViewportView(null);
					initialiseTable();
					LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
					clearLedgerEquipmentCostData();
					setBrewTotalCostData();
					LedgerEquipmentCostTable.setEnabled(true);
					LedgerEquipmentCostTable.setRowSelectionAllowed(true);
					btnLedgerEquipmentCostAdd.setEnabled(true);
					btnLedgerEquipmentCostEdit.setEnabled(false);
					btnLedgerEquipmentCostDelete.setEnabled(false);
					btnLedgerEquipmentCostCancel.setEnabled(false);
					btnLedgerEquipmentCostSave.setEnabled(false);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
					InitialiseMenu.enableAllMenuButtons();
					textLedgerEquipmentCostLineItem.setEditable(false);
					textLedgerEquipmentCostCost.setEditable(false);
					textLedgerEquipmentCostSupplier.setEditable(false);
					chooserLedgerEquipmentCostDate.setDisabled();
				}else {
					mouseListenerIsActive = true;
					isNewCost = "false";
					setLedgerEquipmentCostData();
					LedgerEquipmentCostTable.setEnabled(true);
					LedgerEquipmentCostTable.setRowSelectionAllowed(true);
					btnLedgerEquipmentCostAdd.setEnabled(true);
					btnLedgerEquipmentCostEdit.setEnabled(true);
					btnLedgerEquipmentCostDelete.setEnabled(false);
					btnLedgerEquipmentCostCancel.setEnabled(false);
					btnLedgerEquipmentCostSave.setEnabled(false);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
					InitialiseMenu.enableAllMenuButtons();
					textLedgerEquipmentCostLineItem.setEditable(false);
					textLedgerEquipmentCostCost.setEditable(false);
					textLedgerEquipmentCostSupplier.setEditable(false);
					chooserLedgerEquipmentCostDate.setDisabled();
				}
			}
		});
		
		
		btnLedgerEquipmentCostSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNewCost.equals("true")){
					if (chooserLedgerEquipmentCostDate.getDate() == null || textLedgerEquipmentCostCost.getText() == null || textLedgerEquipmentCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
								"You must enter both a cost and date value.",
								"Error",
								JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.addEquipmentCost();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred inserting data into the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerEquipmentCostTable.setEnabled(true);
						LedgerEquipmentCostTable.setRowSelectionAllowed(true);
						btnLedgerEquipmentCostAdd.setEnabled(true);
						btnLedgerEquipmentCostEdit.setEnabled(false);
						btnLedgerEquipmentCostDelete.setEnabled(false);
						btnLedgerEquipmentCostCancel.setEnabled(false);
						btnLedgerEquipmentCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitialiseMenu.enableAllMenuButtons();
						textLedgerEquipmentCostLineItem.setEditable(false);
						textLedgerEquipmentCostCost.setEditable(false);
						textLedgerEquipmentCostSupplier.setEditable(false);
						chooserLedgerEquipmentCostDate.setDisabled();
						LedgerEquipmentCostScrollPane.remove(LedgerEquipmentCostTable);
						LedgerEquipmentCostScrollPane.setViewportView(null);
						initialiseTable();
						LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
						clearLedgerEquipmentCostData();
						setBrewTotalCostData();
					}
					
				} else {
					if (chooserLedgerEquipmentCostDate.getDate() == null || textLedgerEquipmentCostCost.getText() == null || textLedgerEquipmentCostCost.getText().equals("")){
						JOptionPane.showMessageDialog(null,
							"You must enter both a cost and date value.",
							"Error",
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						try {
							DBEngine.updateEquipmentCost();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"An error occurred updating data in the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						mouseListenerIsActive = true;
						LedgerEquipmentCostTable.setEnabled(true);
						LedgerEquipmentCostTable.setRowSelectionAllowed(true);
						btnLedgerEquipmentCostAdd.setEnabled(true);
						btnLedgerEquipmentCostEdit.setEnabled(false);
						btnLedgerEquipmentCostDelete.setEnabled(false);
						btnLedgerEquipmentCostCancel.setEnabled(false);
						btnLedgerEquipmentCostSave.setEnabled(false);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
						LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
						InitialiseMenu.enableAllMenuButtons();
						textLedgerEquipmentCostLineItem.setEditable(false);
						textLedgerEquipmentCostCost.setEditable(false);
						textLedgerEquipmentCostSupplier.setEditable(false);
						chooserLedgerEquipmentCostDate.setDisabled();
						LedgerEquipmentCostScrollPane.remove(LedgerEquipmentCostTable);
						LedgerEquipmentCostScrollPane.setViewportView(null);
						initialiseTable();
						LedgerEquipmentCostScrollPane.setViewportView(LedgerEquipmentCostTable);
						clearLedgerEquipmentCostData();
						setBrewTotalCostData();
					}
				}
			}
		});
		

		setBrewTotalCostData();
    
		
	}
	
	/**
	 * Initialises the ledger equipment costs table on the ledger equipment tab so that it can be viewed (including getting the data from the database).
	 */
	private static void initialiseTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<String> header; //used to store data header

	    try {
			data = DBEngine.getEquipmentCosts();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<String>();
	    header.add("Equipment Cost Ref");
	    header.add("Date");
	    header.add("Line Item");
	    header.add("Cost");
	    header.add("Supplier");
	    
		//Table	    
	    TableModel model = new DefaultTableModel(data, header) {

			private static final long serialVersionUID = -8421234566395472858L;

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

		LedgerEquipmentCostTable = new JTable(model);

		LedgerEquipmentCostTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		LedgerEquipmentCostTable.getColumnModel().getColumn(0).setMinWidth(0);
		LedgerEquipmentCostTable.getColumnModel().getColumn(0).setMaxWidth(0);
		LedgerEquipmentCostTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		LedgerEquipmentCostTable.getColumnModel().getColumn(1).setMinWidth(5);
		LedgerEquipmentCostTable.getColumnModel().getColumn(1).setMaxWidth(90);
		LedgerEquipmentCostTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		LedgerEquipmentCostTable.getColumnModel().getColumn(2).setMinWidth(5);
		LedgerEquipmentCostTable.getColumnModel().getColumn(2).setMaxWidth(9001);
		LedgerEquipmentCostTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		LedgerEquipmentCostTable.getColumnModel().getColumn(3).setMinWidth(5);
		LedgerEquipmentCostTable.getColumnModel().getColumn(3).setMaxWidth(90);
		LedgerEquipmentCostTable.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		LedgerEquipmentCostTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		LedgerEquipmentCostTable.getColumnModel().getColumn(4).setMinWidth(5);
		LedgerEquipmentCostTable.getColumnModel().getColumn(4).setMaxWidth(9001);
		LedgerEquipmentCostTable.getTableHeader().setReorderingAllowed(false);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		LedgerEquipmentCostTable.setRowSorter(sorter);

		
		addLedgerEquipmentCostMouseListener();
		
	}
	
	/**
	 * Adds the mouse listener to the ledger equipment costs table that listens for mouse clicks.
	 */
	private static void addLedgerEquipmentCostMouseListener(){
		mouseListenerIsActive = true;
		
		LedgerEquipmentCostTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(mouseListenerIsActive){
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						LedgerEquipmentCostSelectedRow = target.getSelectedRow();
						if(LedgerEquipmentCostSelectedRow != -1){
							btnLedgerEquipmentCostEdit.setEnabled(true);
							btnLedgerEquipmentCostDelete.setEnabled(false);
							setLedgerEquipmentCostData();
						}
					}
				}			
			}
		});
	}
	
	/**
	 * Gets the data from the database and sets the fields for the current ledger equipment cost.
	 */
	private static void setLedgerEquipmentCostData(){
		if(LedgerEquipmentCostSelectedRow != -1){
			textLedgerEquipmentCostRef.setText((String) LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,0));
			if(LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,1).equals(null) || LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,1).equals("")){
				chooserLedgerEquipmentCostDate.setDate(null);
			} else{
				chooserLedgerEquipmentCostDate.setDate(Dates.stringToDate((String) LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,1)));
			};
			textLedgerEquipmentCostLineItem.setText((String) LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,2));
			textLedgerEquipmentCostCost.setText(LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,3).toString());
			textLedgerEquipmentCostSupplier.setText((String) LedgerEquipmentCostTable.getValueAt(LedgerEquipmentCostSelectedRow,4));
		}
	}
	
	/**
	 * Calculates and sets the total, total per brew, and total per bottle ledger equipment costs.
	 */
	private static void setBrewTotalCostData(){
	    BigDecimal total = new BigDecimal("0");
	    for (int i = 0; i < (LedgerEquipmentCostTable.getRowCount()); i++ ) {
	    total = total.add(new BigDecimal((Float) LedgerEquipmentCostTable.getValueAt(i, 3)));
	    }
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    
	    textLedgerEquipmentCostTotalCost.setText(nf.format(total.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
	    try {
			textLedgerEquipmentCostCostPerAllBrews.setText(nf.format(total.divide(DBEngine.getTotalNumberBrews(), 2, BigDecimal.ROUND_HALF_UP)).toString());
			textLedgerEquipmentCostCostPerBottle.setText(nf.format(total.divide(DBEngine.getTotalNumberBottles(), 2, BigDecimal.ROUND_HALF_UP)).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Clears all data from all fields on the ledger equipment cost tab.
	 */
	private static void clearLedgerEquipmentCostData(){
		textLedgerEquipmentCostRef.setText("");
		chooserLedgerEquipmentCostDate.setDate(null);
		textLedgerEquipmentCostLineItem.setText("");
		textLedgerEquipmentCostCost.setText("");
		textLedgerEquipmentCostSupplier.setText("");
		textLedgerEquipmentCostTotalCost.setText("");
		textLedgerEquipmentCostCostPerAllBrews.setText("");
		textLedgerEquipmentCostCostPerBottle.setText("");
	}

	
	
}