package uk.co.pori.winebrewdb.recipe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import uk.co.pori.winebrewdb.MainWindow;
import uk.co.pori.winebrewdb.sqlite.DBEngine;

import net.miginfocom.swing.MigLayout;

/**
 * This is the recipe search panel to be displayed as a tab on the main recipe panel tab bar. It allows the user to search for recipes.
 * 
 * @author Paul.Bellchambers
 *
 */
public class RecipeSearchPanel extends JPanel {
	

	private static final long serialVersionUID = 1271194251562646054L;
	static JPanel tabbedRecipeSearchPanel;
	static JScrollPane RecipeScrollPane;
	static JTable RecipeTable;
	static int RecipeSearchSelectedRow;
	private static String RecipeTableFields;
	public static JTextField textRecipeName;
	public static JTextField textInspiration;
	public static JTextField textIngredients;
	public static JTextField textSuggestedYeast;
	public static JTextField textMethod;
	public static JTextField textNotes;

	/**
	 * Initialises the recipe search panel so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		RecipeTableFields = "Summary";
		
		//Main Panel
		tabbedRecipeSearchPanel = new JPanel();
		tabbedRecipeSearchPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedRecipeSearchPanel.setLayout(new MigLayout("", "[grow]", "[150px:n:150px][grow]"));
		
		//Filter Panel
		JPanel RecipeFilterPanel = new JPanel();
		RecipeFilterPanel.setBackground(UIManager.getColor("Panel.background"));
		RecipeFilterPanel.setLayout(new MigLayout("", "[90px:90px:90px][70px:70px, grow][120px:120px:120px][70px:70px, grow]", "[][][][45px:45px:45px]"));
			
		JLabel lblRecipeName = new JLabel("Recipe Name:");
		RecipeFilterPanel.add(lblRecipeName, "flowx,cell 0 0,alignx right");
			
		textRecipeName = new JTextField();
		RecipeFilterPanel.add(textRecipeName, "cell 1 0,growx");
			
		JLabel lblInspiration = new JLabel("Inspiration:");
		RecipeFilterPanel.add(lblInspiration, "flowx,cell 2 0,alignx right");
			
		textInspiration = new JTextField();
		RecipeFilterPanel.add(textInspiration, "cell 3 0,growx");
			
		JLabel lblIngredients = new JLabel("Ingredients:");
		RecipeFilterPanel.add(lblIngredients, "flowx,cell 0 1,alignx right");
			
		textIngredients = new JTextField();
		RecipeFilterPanel.add(textIngredients, "cell 1 1,growx");
			
		JLabel lblSuggestedYeast = new JLabel("Yeast:");
		RecipeFilterPanel.add(lblSuggestedYeast, "flowx,cell 2 1,alignx right");
			
		textSuggestedYeast = new JTextField();
		RecipeFilterPanel.add(textSuggestedYeast, "cell 3 1,growx");
			
		JLabel lblMethod = new JLabel("Method:");
		RecipeFilterPanel.add(lblMethod, "flowx,cell 0 2,alignx right");
			
		textMethod = new JTextField();
		RecipeFilterPanel.add(textMethod, "cell 1 2,growx");
			
		JLabel lblNotes = new JLabel("Notes:");
		RecipeFilterPanel.add(lblNotes, "flowx,cell 2 2,alignx right");
		
		textNotes = new JTextField();
		RecipeFilterPanel.add(textNotes, "cell 3 2,growx");
			
		JCheckBox chckbxAllFields = new JCheckBox("Show All Fields?");
		RecipeFilterPanel.add(chckbxAllFields, "cell 2 3,alignx right");
		
		JButton btnSearch = new JButton("Search");
		RecipeFilterPanel.add(btnSearch, "cell 3 3,growx");

		tabbedRecipeSearchPanel.add(RecipeFilterPanel, "cell 0 0,grow");

		//Initialise Table
		initialiseTable();
		
		//ScrollPane
	    RecipeScrollPane = new JScrollPane();
	    RecipeScrollPane.setViewportView(RecipeTable);
	    tabbedRecipeSearchPanel.add(RecipeScrollPane, "cell 0 1,grow");
	    
	    
		//Add button listeners
	    btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeScrollPane.remove(RecipeTable);
				RecipeScrollPane.setViewportView(null);
				RecipeDataPanel.clearRecipeData();
				RecipeAddPanel.clearRecipeAddData();
				RecipeDataPanel.btnRecipeDataEdit.setEnabled(false);
				RecipeDataPanel.btnRecipeDataDelete.setEnabled(false);
				RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
				initialiseTable();
				RecipeScrollPane.setViewportView(RecipeTable);
			}
		});
		
	    chckbxAllFields.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					RecipeTableFields = "All";
					RecipeScrollPane.remove(RecipeTable);
					RecipeScrollPane.setViewportView(null);
					RecipeDataPanel.clearRecipeData();
					RecipeAddPanel.clearRecipeAddData();
					RecipeDataPanel.btnRecipeDataEdit.setEnabled(false);
					RecipeDataPanel.btnRecipeDataDelete.setEnabled(false);
					RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
					initialiseTable();
					RecipeScrollPane.setViewportView(RecipeTable);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED){
					RecipeTableFields = "Summary";
					RecipeScrollPane.remove(RecipeTable);
					RecipeScrollPane.setViewportView(null);
					RecipeDataPanel.clearRecipeData();
					RecipeAddPanel.clearRecipeAddData();
					RecipeDataPanel.btnRecipeDataEdit.setEnabled(false);
					RecipeDataPanel.btnRecipeDataDelete.setEnabled(false);
					RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
					initialiseTable();
					RecipeScrollPane.setViewportView(RecipeTable);
				}
			}
		});
	    
		
	}
	
	/**
	 * Initialises the recipes table on the recipe search tab so that it can be viewed (including getting the data from the database).
	 */
	public static void initialiseTable() {
		//Get data for table
	    Vector<Vector<Object>> data = null; //used for data from database
	    Vector<Object> header; //used to store data header

	    try {
			data = DBEngine.getRecipes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occurred getting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	    

	    //Create header for the table
	    header = new Vector<Object>();
	    header.add("Recipe Ref");
	    header.add("Recipe Name");
	    header.add("Inspiration");
	    header.add("Ingredients");
	    header.add("Suggested Yeast");
	    header.add("Method");
	    header.add("Notes");
	    header.add("References");
	    header.add("Volume (gallons)");
	    
		//Table
		RecipeTable = new JTable();
		RecipeTable.setModel(new DefaultTableModel(data,header){
			private static final long serialVersionUID = 6716082729584843250L;
			public boolean isCellEditable(int row, int column) {
			    	return false;
			    }}

			);
		
		if (RecipeTableFields.equals("Summary")){
			showDefaultColumns();
		}else {
			showAllColumns();
		}
		
		RecipeTable.getTableHeader().setReorderingAllowed(false);
		RecipeTable.setAutoCreateRowSorter(true);
		

		//Mouse Listener on JTable:
		RecipeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					RecipeSearchSelectedRow = target.getSelectedRow();
					RecipeDataPanel.btnRecipeDataEdit.setEnabled(true);
					RecipeDataPanel.btnRecipeDataDelete.setEnabled(false);
					RecipeDataPanel.setRecipeData();
					RecipePanel.tabbedRecipePane.setEnabledAt(1, true);
				}
				
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					RecipeSearchSelectedRow = target.getSelectedRow();
					RecipeDataPanel.btnRecipeDataEdit.setEnabled(true);
					RecipeDataPanel.btnRecipeDataDelete.setEnabled(false);
					RecipeDataPanel.setRecipeData();
					RecipePanel.tabbedRecipePane.setEnabledAt(1, true);
					RecipePanel.tabbedRecipePane.setSelectedIndex(1);
				}
				
			   }
			});
		
		
	}
	
	/**
	 * Makes all columns visible on the recipe search table.
	 */
	private static void showAllColumns(){
		RecipeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		RecipeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(0).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(0).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		RecipeTable.getColumnModel().getColumn(1).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(2).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(2).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(3).setPreferredWidth(125);
		RecipeTable.getColumnModel().getColumn(3).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(4).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(4).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(5).setPreferredWidth(200);
		RecipeTable.getColumnModel().getColumn(5).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(5).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(6).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(6).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(6).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(7).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(7).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(7).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(8).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(8).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(8).setMaxWidth(9001);
	}
	
	/**
	 * Makes only the default columns visible on the recipe search table (by setting column width to 0 on the unneeded ones).
	 */
	private static void showDefaultColumns(){
		RecipeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		RecipeTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		RecipeTable.getColumnModel().getColumn(0).setMinWidth(0);
		RecipeTable.getColumnModel().getColumn(0).setMaxWidth(0);
		RecipeTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		RecipeTable.getColumnModel().getColumn(1).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(1).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(2).setPreferredWidth(0);
		RecipeTable.getColumnModel().getColumn(2).setMinWidth(0);
		RecipeTable.getColumnModel().getColumn(2).setMaxWidth(0);
		RecipeTable.getColumnModel().getColumn(3).setPreferredWidth(125);
		RecipeTable.getColumnModel().getColumn(3).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(3).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(4).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(4).setMaxWidth(9001);
		RecipeTable.getColumnModel().getColumn(5).setPreferredWidth(0);
		RecipeTable.getColumnModel().getColumn(5).setMinWidth(0);
		RecipeTable.getColumnModel().getColumn(5).setMaxWidth(0);
		RecipeTable.getColumnModel().getColumn(6).setPreferredWidth(0);
		RecipeTable.getColumnModel().getColumn(6).setMinWidth(0);
		RecipeTable.getColumnModel().getColumn(6).setMaxWidth(0);
		RecipeTable.getColumnModel().getColumn(7).setPreferredWidth(0);
		RecipeTable.getColumnModel().getColumn(7).setMinWidth(0);
		RecipeTable.getColumnModel().getColumn(7).setMaxWidth(0);
		RecipeTable.getColumnModel().getColumn(8).setPreferredWidth(80);
		RecipeTable.getColumnModel().getColumn(8).setMinWidth(5);
		RecipeTable.getColumnModel().getColumn(8).setMaxWidth(9001);
	}
	
}