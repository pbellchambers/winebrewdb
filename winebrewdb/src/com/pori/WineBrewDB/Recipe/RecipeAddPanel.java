package com.pori.WineBrewDB.Recipe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.pori.WineBrewDB.InitializeMenu;
import com.pori.WineBrewDB.MainWindow;
import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;

public class RecipeAddPanel extends JPanel {

	private static final long serialVersionUID = -4501679200598952188L;
	static JPanel tabbedRecipeAddPanel;public static JLabel lblRecipeNameAdd;
	public static JTextField textRecipeNameAdd;
	public static JTextField textInspirationAdd;
	public static JTextArea textIngredientsAdd;
	public static JTextField textSuggestedYeastAdd;
	public static JTextArea textMethodAdd;
	public static JTextArea textNotesAdd;
	public static JTextField textReferencesAdd;
	public static JTextField textVolumeAdd;
	private static JTextField textRecipeRefAdd;
	private static JButton btnRecipeAddEdit;
	private static JButton btnRecipeAddCancel;
	private static JButton btnRecipeAddSave;
	
	
	public static void InitializePanel(){
		
		tabbedRecipeAddPanel = new JPanel();
		tabbedRecipeAddPanel.setBackground(Color.WHITE);
		tabbedRecipeAddPanel.setLayout(new MigLayout("", "[110px:110px:110px][70px:70px, grow][70px:70px, grow][120px:120px:120px][70px:70px][70px:70px, grow]", "[][][100px:100px, grow][][130px:130px, grow][70px:70px, grow][][15px:15px][]"));
	
		textRecipeRefAdd = new JTextField();
		textRecipeRefAdd.setEditable(false);

		lblRecipeNameAdd = new JLabel("Recipe Name:");
		tabbedRecipeAddPanel.add(lblRecipeNameAdd, "flowx,cell 0 0,alignx right");
			
		textRecipeNameAdd = new JTextField();
		textRecipeNameAdd.setEditable(false);
		tabbedRecipeAddPanel.add(textRecipeNameAdd, "cell 1 0 5,growx");

		JLabel lblInspirationAdd = new JLabel("Inspiration:");
		tabbedRecipeAddPanel.add(lblInspirationAdd, "flowx,cell 0 1,alignx right");
			
		textInspirationAdd = new JTextField();
		textInspirationAdd.setEditable(false);
		tabbedRecipeAddPanel.add(textInspirationAdd, "cell 1 1 5,growx");

		JLabel lblIngredientsAdd = new JLabel("Ingredients:");
		tabbedRecipeAddPanel.add(lblIngredientsAdd, "flowx,cell 0 2,alignx right");

		textIngredientsAdd = new JTextArea();
		textIngredientsAdd.setEditable(false);
		textIngredientsAdd.setLineWrap(true);
		textIngredientsAdd.setBackground(UIManager.getColor("Panel.background"));

		//ScrollPane for textIngredientsB
		JScrollPane RecipeAddIngredientsScrollPane = new JScrollPane();
		RecipeAddIngredientsScrollPane.setViewportView(textIngredientsAdd);
		tabbedRecipeAddPanel.add(RecipeAddIngredientsScrollPane, "cell 1 2 5,grow");

		JLabel lblSuggestedYeastAdd = new JLabel("Suggested Yeast:");
		tabbedRecipeAddPanel.add(lblSuggestedYeastAdd, "flowx,cell 0 3,alignx right");

		textSuggestedYeastAdd = new JTextField();
		textSuggestedYeastAdd.setEditable(false);
		tabbedRecipeAddPanel.add(textSuggestedYeastAdd, "cell 1 3 2,growx");

		JLabel lblVolumeAdd = new JLabel("Volume:");
		tabbedRecipeAddPanel.add(lblVolumeAdd, "flowx,cell 3 3,alignx right");
			
		textVolumeAdd = new JTextField();
		textVolumeAdd.setEditable(false);
		tabbedRecipeAddPanel.add(textVolumeAdd, "cell 4 3,growx");
		
		JLabel lblVolumeUnitAdd = new JLabel("(gallons)");
		tabbedRecipeAddPanel.add(lblVolumeUnitAdd, "flowx,cell 5 3,alignx left");

		JLabel lblMethodAdd = new JLabel("Method:");
		tabbedRecipeAddPanel.add(lblMethodAdd, "flowx,cell 0 4,alignx right");

		textMethodAdd = new JTextArea();
		textMethodAdd.setEditable(false);
		textMethodAdd.setLineWrap(true);
		textMethodAdd.setBackground(UIManager.getColor("Panel.background"));

		//ScrollPane for textMethodB
		JScrollPane RecipeAddMethodScrollPane = new JScrollPane();
		RecipeAddMethodScrollPane.setViewportView(textMethodAdd);
		tabbedRecipeAddPanel.add(RecipeAddMethodScrollPane, "cell 1 4 5,grow");

		JLabel lblNotesAdd = new JLabel("Notes:");
		tabbedRecipeAddPanel.add(lblNotesAdd, "flowx,cell 0 5,alignx right");

		textNotesAdd = new JTextArea();
		textNotesAdd.setEditable(false);
		textNotesAdd.setLineWrap(true);
		textNotesAdd.setBackground(UIManager.getColor("Panel.background"));

		//ScrollPane for textNotesB
		JScrollPane RecipeAddNotesScrollPane = new JScrollPane();
		RecipeAddNotesScrollPane.setViewportView(textNotesAdd);
		tabbedRecipeAddPanel.add(RecipeAddNotesScrollPane, "cell 1 5 5,grow");

		JLabel lblReferencesAdd = new JLabel("References:");
		tabbedRecipeAddPanel.add(lblReferencesAdd, "flowx,cell 0 6,alignx right");
			
		textReferencesAdd = new JTextField();
		textReferencesAdd.setEditable(false);
		tabbedRecipeAddPanel.add(textReferencesAdd, "cell 1 6 5,growx");

		btnRecipeAddEdit = new JButton("Add");
		btnRecipeAddEdit.setEnabled(true);
		tabbedRecipeAddPanel.add(btnRecipeAddEdit, "cell 0 8,growx");

		btnRecipeAddCancel = new JButton("Cancel");
		btnRecipeAddCancel.setEnabled(false);
		tabbedRecipeAddPanel.add(btnRecipeAddCancel, "cell 4 8,growx");
			
		btnRecipeAddSave = new JButton("Save / Insert");
		btnRecipeAddSave.setEnabled(false);
		tabbedRecipeAddPanel.add(btnRecipeAddSave, "cell 5 8,growx");
		
		//Add button listeners
		btnRecipeAddEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRecipeAddEdit.setEnabled(false);
				btnRecipeAddCancel.setEnabled(true);
				btnRecipeAddSave.setEnabled(true);
				RecipePanel.tabbedRecipePane.setEnabledAt(0, false);
				RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
				RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
				InitializeMenu.DisableAllMenuButtons();
				textRecipeNameAdd.setEditable(true);
				textInspirationAdd.setEditable(true);
				textIngredientsAdd.setEditable(true);
				textSuggestedYeastAdd.setEditable(true);
				textMethodAdd.setEditable(true);
				textNotesAdd.setEditable(true);
				textReferencesAdd.setEditable(true);
				textVolumeAdd.setEditable(true);
				textIngredientsAdd.setBackground(Color.WHITE);
				textMethodAdd.setBackground(Color.WHITE);
				textNotesAdd.setBackground(Color.WHITE);
			}
		});
		
		btnRecipeAddCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRecipeAddEdit.setEnabled(true);
				btnRecipeAddCancel.setEnabled(false);
				btnRecipeAddSave.setEnabled(false);
				RecipePanel.tabbedRecipePane.setEnabledAt(0, true);
				RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
				RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
				InitializeMenu.EnableAllMenuButtons();
				textRecipeNameAdd.setEditable(false);
				textInspirationAdd.setEditable(false);
				textIngredientsAdd.setEditable(false);
				textSuggestedYeastAdd.setEditable(false);
				textMethodAdd.setEditable(false);
				textNotesAdd.setEditable(false);
				textReferencesAdd.setEditable(false);
				textVolumeAdd.setEditable(false);
				textIngredientsAdd.setBackground(UIManager.getColor("Panel.background"));
				textMethodAdd.setBackground(UIManager.getColor("Panel.background"));
				textNotesAdd.setBackground(UIManager.getColor("Panel.background"));
				clearRecipeAddData();
			}
		});
		
		btnRecipeAddSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textRecipeNameAdd.getText().equals("") || textRecipeNameAdd.getText() == null){
					JOptionPane.showMessageDialog(null,
						"You must enter a recipe name.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
				} else {
					try {
						DBEngine.addRecipe();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"An error occurred inserting data into the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					btnRecipeAddEdit.setEnabled(true);
					btnRecipeAddCancel.setEnabled(false);
					btnRecipeAddSave.setEnabled(false);
					RecipePanel.tabbedRecipePane.setEnabledAt(0, true);
					RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
					RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
					InitializeMenu.EnableAllMenuButtons();
					textRecipeNameAdd.setEditable(false);
					textInspirationAdd.setEditable(false);
					textIngredientsAdd.setEditable(false);
					textSuggestedYeastAdd.setEditable(false);
					textMethodAdd.setEditable(false);
					textNotesAdd.setEditable(false);
					textReferencesAdd.setEditable(false);
					textVolumeAdd.setEditable(false);
					textIngredientsAdd.setBackground(UIManager.getColor("Panel.background"));
					textMethodAdd.setBackground(UIManager.getColor("Panel.background"));
					textNotesAdd.setBackground(UIManager.getColor("Panel.background"));
					clearRecipeAddData();
					RecipeSearchPanel.RecipeScrollPane.remove(RecipeSearchPanel.RecipeTable);
					RecipeSearchPanel.RecipeScrollPane.setViewportView(null);
					RecipeAddPanel.clearRecipeAddData();
					RecipeSearchPanel.initializeTable();
					RecipeSearchPanel.RecipeScrollPane.setViewportView(RecipeSearchPanel.RecipeTable);
				}
			}
		});
	}
	
	public static void clearRecipeAddData(){
		textRecipeRefAdd.setText("");
		textRecipeNameAdd.setText("");
		textInspirationAdd.setText("");
		textIngredientsAdd.setText("");
		textSuggestedYeastAdd.setText("");
		textMethodAdd.setText("");
		textNotesAdd.setText("");
		textReferencesAdd.setText("");
		textVolumeAdd.setText("");
	}
	
	public static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}


}