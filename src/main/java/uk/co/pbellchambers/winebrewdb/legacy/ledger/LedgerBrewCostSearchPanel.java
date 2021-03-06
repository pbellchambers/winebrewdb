package uk.co.pbellchambers.winebrewdb.legacy.ledger;

import agiletrack.swing.JDateChooser;
import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.legacy.LegacyApp;
import uk.co.pbellchambers.winebrewdb.legacy.util.DBEngine;
import uk.co.pbellchambers.winebrewdb.legacy.util.NumberRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * This is the ledger brew cost search panel to be displayed as a tab on the main ledger panel tab bar. It shows the search filter and table for the ledger brew cost data.
 * 
 * @author Paul.Bellchambers
 *
 */
public class LedgerBrewCostSearchPanel extends JPanel {
	
	private static final long serialVersionUID = -3700951316471175013L;
	static JPanel tabbedLedgerBrewCostSearchPanel;
	static JScrollPane LedgerBrewCostScrollPane;
	static JTable LedgerBrewCostTable;
	public static JTextField textLedgerBrewName;
	static int LedgerBrewCostSearchSelectedRow;
	private static String LedgerBrewCostTableFields;
	public static JTextField textLedgerBrewTotalCostA;
	public static JTextField textLedgerBrewTotalCostB;
	public static JTextField textLedgerBrewCostPerBottleA;
	public static JTextField textLedgerBrewCostPerBottleB;
	public static JDateChooser chooserLedgerBrewDatesFilterA;
	public static JDateChooser chooserLedgerBrewDatesFilterB;
	public static JTextField textLedgerBrewNumberBottlesA;
	public static JTextField textLedgerBrewNumberBottlesB;

	/**
	 * Initialises the ledger brew cost search panel so that it can be viewed (including populating the table).
	 */
	public static void initialisePanel(){
		
		LedgerBrewCostTableFields = "Summary";
		
		//Main Panel
		tabbedLedgerBrewCostSearchPanel = new JPanel();
		tabbedLedgerBrewCostSearchPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedLedgerBrewCostSearchPanel.setLayout(new MigLayout("", "[grow]", "[100px:n:100px][grow]"));
		
		//Filter Panel
		JPanel LedgerBrewCostFilterPanel = new JPanel();
		LedgerBrewCostFilterPanel.setBackground(UIManager.getColor("Panel.background"));
		LedgerBrewCostFilterPanel.setLayout(new MigLayout("", "[140px:140px:140px][grow][grow][135px:135px:135px][75px,grow][grow]", "[][][]"));
			
		JLabel lblLedgerBrewName = new JLabel("Brew Name:");
		LedgerBrewCostFilterPanel.add(lblLedgerBrewName, "flowx,cell 0 0,alignx right");
			
		textLedgerBrewName = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewName, "cell 1 0 2,growx");
		
		JLabel lblYeast = new JLabel("Total Cost Between:");
		LedgerBrewCostFilterPanel.add(lblYeast, "flowx,cell 3 0,alignx right");
			
		textLedgerBrewTotalCostA = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewTotalCostA, "cell 4 0,growx");
		
		textLedgerBrewTotalCostB = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewTotalCostB, "cell 5 0,growx");
			
		JLabel lblRecipeFrom = new JLabel("Dates Between:");
		LedgerBrewCostFilterPanel.add(lblRecipeFrom, "flowx,cell 0 1,alignx right");
			
		chooserLedgerBrewDatesFilterA = new JDateChooser();
		LedgerBrewCostFilterPanel.add(chooserLedgerBrewDatesFilterA, "cell 1 1,growx");
		
		chooserLedgerBrewDatesFilterB = new JDateChooser();
		LedgerBrewCostFilterPanel.add(chooserLedgerBrewDatesFilterB, "cell 2 1,growx");
			
		JLabel lblNotes = new JLabel("Cost Per Bottle Between:");
		LedgerBrewCostFilterPanel.add(lblNotes, "flowx,cell 3 1,alignx right");
		
		textLedgerBrewCostPerBottleA = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewCostPerBottleA, "cell 4 1,growx");
		
		textLedgerBrewCostPerBottleB = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewCostPerBottleB, "cell 5 1,growx");
		
		JLabel lblLedgerBrewNumberBottles = new JLabel("Number Bottles Between:");
		LedgerBrewCostFilterPanel.add(lblLedgerBrewNumberBottles, "flowx,cell 0 2,alignx right");
			
		textLedgerBrewNumberBottlesA = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewNumberBottlesA, "cell 1 2,growx");
		
		textLedgerBrewNumberBottlesB = new JTextField();
		LedgerBrewCostFilterPanel.add(textLedgerBrewNumberBottlesB, "cell 2 2,growx");
		
		JButton btnSearch = new JButton("Search");
		LedgerBrewCostFilterPanel.add(btnSearch, "cell 5 2,growx");

		tabbedLedgerBrewCostSearchPanel.add(LedgerBrewCostFilterPanel, "cell 0 0,grow");

		//Initialise Table
		initialiseTable();
		
		//ScrollPane
	    LedgerBrewCostScrollPane = new JScrollPane();
	    LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
	    tabbedLedgerBrewCostSearchPanel.add(LedgerBrewCostScrollPane, "cell 0 1,grow");
	    
	    
		//Add button listeners
	    btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LedgerBrewCostScrollPane.remove(LedgerBrewCostTable);
				LedgerBrewCostScrollPane.setViewportView(null);
				LedgerBrewCostDataPanel.clearLedgerBrewCostData();
				LedgerBrewCostDataPanel.btnLedgerBrewCostEdit.setEnabled(false);
				LedgerBrewCostDataPanel.btnLedgerBrewCostAdd.setEnabled(false);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
				LedgerPanel.tabbedLedgerPane.setEnabledAt(2, false);
				initialiseTable();
				LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostTable);
			}
		});
	    
		
	}
	
	/**
	 * Initialises the ledger brew costs table on the ledger brew costs search tab so that it can be viewed (including getting the data from the database).
	 */
	public static void initialiseTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<Object> header; //used to store data header

	    try {
			data = DBEngine.getLedgerBrews();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + LegacyApp.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<Object>();
	    header.add("Brew Ref");
	    header.add("Brew Name");
	    header.add("Date Started");
	    header.add("Total Cost");
	    header.add("Number Bottles");
	    header.add("Cost Per Bottle");
	    
		//Table
	    TableModel model = new DefaultTableModel(data, header) {

			private static final long serialVersionUID = 1058720365143102151L;

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

		
		if (LedgerBrewCostTableFields.equals("Summary")){
			showDefaultColumns();
		}else {
			showAllColumns();
		}
		
		LedgerBrewCostTable.getTableHeader().setReorderingAllowed(false);
		LedgerBrewCostTable.setAutoCreateRowSorter(true);
		

		//Mouse Listener on JTable:
		LedgerBrewCostTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					LedgerBrewCostSearchSelectedRow = target.getSelectedRow();
					LedgerBrewCostDataPanel.textLedgerBrewRef.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,0));
					LedgerBrewCostDataPanel.textLedgerBrewNumberBottles.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,4));
					LedgerBrewCostDataPanel.initialiseTable();
					LedgerBrewCostDataPanel.clearLedgerBrewCostData();
					LedgerBrewCostDataPanel.textLedgerBrewRef.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,0));
					LedgerBrewCostDataPanel.textLedgerBrewNumberBottles.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,4));
					LedgerBrewCostDataPanel.setBrewTotalCostData();
					LedgerBrewCostDataPanel.btnLedgerBrewCostEdit.setEnabled(false);
					LedgerBrewCostDataPanel.btnLedgerBrewCostAdd.setEnabled(true);
					LedgerBrewCostDataPanel.LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostDataPanel.LedgerBrewCostTable);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
				}
				
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					LedgerBrewCostSearchSelectedRow = target.getSelectedRow();
					LedgerBrewCostDataPanel.textLedgerBrewRef.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,0));
					LedgerBrewCostDataPanel.textLedgerBrewNumberBottles.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,4));
					LedgerBrewCostDataPanel.initialiseTable();
					LedgerBrewCostDataPanel.clearLedgerBrewCostData();
					LedgerBrewCostDataPanel.textLedgerBrewRef.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,0));
					LedgerBrewCostDataPanel.textLedgerBrewNumberBottles.setText((String) LedgerBrewCostTable.getValueAt(LedgerBrewCostSearchSelectedRow,4));
					LedgerBrewCostDataPanel.setBrewTotalCostData();
					LedgerBrewCostDataPanel.btnLedgerBrewCostEdit.setEnabled(false);
					LedgerBrewCostDataPanel.btnLedgerBrewCostAdd.setEnabled(true);
					LedgerBrewCostDataPanel.LedgerBrewCostScrollPane.setViewportView(LedgerBrewCostDataPanel.LedgerBrewCostTable);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(0, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(1, true);
					LedgerPanel.tabbedLedgerPane.setEnabledAt(2, true);
					LedgerPanel.tabbedLedgerPane.setSelectedIndex(2);
				}
				
			   }
			});
		
		
	}
	
	/**
	 * Makes all columns visible on the brew costs search table.
	 */
	private static void showAllColumns(){
		LedgerBrewCostTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setPreferredWidth(260);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		LedgerBrewCostTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		LedgerBrewCostTable.getColumnModel().getColumn(4).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(4).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}
	
	/**
	 * Makes only the default columns visible on the brew costs search table (by setting column width to 0 on the unneeded ones).
	 */
	private static void showDefaultColumns(){
		LedgerBrewCostTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMinWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(0).setMaxWidth(0);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setPreferredWidth(260);
		LedgerBrewCostTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(2).setMaxWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setMaxWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		LedgerBrewCostTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(4).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(4).setMaxWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setMinWidth(5);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setMaxWidth(90);
		LedgerBrewCostTable.getColumnModel().getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
	}
	
}