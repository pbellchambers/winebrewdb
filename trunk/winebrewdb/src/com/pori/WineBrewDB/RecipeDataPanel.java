package com.pori.WineBrewDB;

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

import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;

public class RecipeDataPanel extends JPanel {

	private static final long serialVersionUID = -744103763529915749L;
	public static JPanel tabbedRecipeDataPanel;
	public static JLabel lblRecipeNameB;
	public static JTextField textRecipeNameB;
	public static JLabel lblInspirationB;
	public static JTextField textInspirationB;
	public static JLabel lblIngredientsB;
	public static JTextArea textIngredientsB;
	public static JLabel lblSuggestedYeastB;
	public static JTextField textSuggestedYeastB;
	public static JLabel lblMethodB;
	public static JTextArea textMethodB;
	public static JLabel lblNotesB;
	public static JTextArea textNotesB;
	public static JLabel lblReferencesB;
	public static JTextField textReferencesB;
	public static JLabel lblVolumeB;
	public static JTextField textVolumeB;
	public static JLabel lblRecipeRefB;
	public static JTextField textRecipeRefB;
	public static JScrollPane RecipeDataIngredientsScrollPane;
	public static JScrollPane RecipeDataMethodScrollPane;
	public static JScrollPane RecipeDataNotesScrollPane;
	public static JButton btnRecipeDataEdit;
	public static JButton btnRecipeDataDelete;
	public static JButton btnRecipeDataCancel;
	public static JButton btnRecipeDataSave;
	
	
	public static void InitializePanel(){
		
		tabbedRecipeDataPanel = new JPanel();
		tabbedRecipeDataPanel.setBackground(Color.WHITE);
		tabbedRecipeDataPanel.setLayout(new MigLayout("", "[110px:110px:110px][70px:70px, grow][70px:70px, grow][120px:120px:120px][70px:70px, grow]", "[][][100px:100px, grow][][130px:130px, grow][70px:70px, grow][][15px:15px][]"));
		
		lblRecipeRefB = new JLabel("Recipe Ref:");
			
		textRecipeRefB = new JTextField();
		textRecipeRefB.setEditable(false);
		
		lblRecipeNameB = new JLabel("Recipe Name:");
		tabbedRecipeDataPanel.add(lblRecipeNameB, "flowx,cell 0 0,alignx right");
			
		textRecipeNameB = new JTextField();
		textRecipeNameB.setEditable(false);
		tabbedRecipeDataPanel.add(textRecipeNameB, "cell 1 0 4,growx");
		
		lblInspirationB = new JLabel("Inspiration:");
		tabbedRecipeDataPanel.add(lblInspirationB, "flowx,cell 0 1,alignx right");
			
		textInspirationB = new JTextField();
		textInspirationB.setEditable(false);
		tabbedRecipeDataPanel.add(textInspirationB, "cell 1 1 4,growx");
		
		lblIngredientsB = new JLabel("Ingredients:");
		tabbedRecipeDataPanel.add(lblIngredientsB, "flowx,cell 0 2,alignx right");
		
		textIngredientsB = new JTextArea();
		textIngredientsB.setEditable(false);
		textIngredientsB.setLineWrap(true);
		textIngredientsB.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textIngredientsB
	    RecipeDataIngredientsScrollPane = new JScrollPane();
	    RecipeDataIngredientsScrollPane.setViewportView(textIngredientsB);
	    tabbedRecipeDataPanel.add(RecipeDataIngredientsScrollPane, "cell 1 2 4,grow");
		
		lblSuggestedYeastB = new JLabel("Suggested Yeast:");
		tabbedRecipeDataPanel.add(lblSuggestedYeastB, "flowx,cell 0 3,alignx right");
		
		textSuggestedYeastB = new JTextField();
		textSuggestedYeastB.setEditable(false);
		tabbedRecipeDataPanel.add(textSuggestedYeastB, "cell 1 3 2,growx");
		
		lblVolumeB = new JLabel("Volume:");
		tabbedRecipeDataPanel.add(lblVolumeB, "flowx,cell 3 3,alignx right");
			
		textVolumeB = new JTextField();
		textVolumeB.setEditable(false);
		tabbedRecipeDataPanel.add(textVolumeB, "cell 4 3,growx");
		
		lblMethodB = new JLabel("Method:");
		tabbedRecipeDataPanel.add(lblMethodB, "flowx,cell 0 4,alignx right");
		
		textMethodB = new JTextArea();
		textMethodB.setEditable(false);
		textMethodB.setLineWrap(true);
		textMethodB.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textMethodB
	    RecipeDataMethodScrollPane = new JScrollPane();
	    RecipeDataMethodScrollPane.setViewportView(textMethodB);
	    tabbedRecipeDataPanel.add(RecipeDataMethodScrollPane, "cell 1 4 4,grow");
		
		lblNotesB = new JLabel("Notes:");
		tabbedRecipeDataPanel.add(lblNotesB, "flowx,cell 0 5,alignx right");
		
		textNotesB = new JTextArea();
		textNotesB.setEditable(false);
		textNotesB.setLineWrap(true);
		textNotesB.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textNotesB
	    RecipeDataNotesScrollPane = new JScrollPane();
	    RecipeDataNotesScrollPane.setViewportView(textNotesB);
	    tabbedRecipeDataPanel.add(RecipeDataNotesScrollPane, "cell 1 5 4,grow");
		
		lblReferencesB = new JLabel("References:");
		tabbedRecipeDataPanel.add(lblReferencesB, "flowx,cell 0 6,alignx right");
			
		textReferencesB = new JTextField();
		textReferencesB.setEditable(false);
		tabbedRecipeDataPanel.add(textReferencesB, "cell 1 6 4,growx");
		
		btnRecipeDataEdit = new JButton("Edit");
		btnRecipeDataEdit.setEnabled(false);
		tabbedRecipeDataPanel.add(btnRecipeDataEdit, "cell 0 8,growx");
		
		btnRecipeDataDelete = new JButton("Delete Recipe");
		btnRecipeDataDelete.setEnabled(false);
		tabbedRecipeDataPanel.add(btnRecipeDataDelete, "cell 1 8,growx");
		
		btnRecipeDataCancel = new JButton("Cancel");
		btnRecipeDataCancel.setEnabled(false);
		tabbedRecipeDataPanel.add(btnRecipeDataCancel, "cell 3 8,growx");
			
		btnRecipeDataSave = new JButton("Save / Update");
		btnRecipeDataSave.setEnabled(false);
		tabbedRecipeDataPanel.add(btnRecipeDataSave, "cell 4 8,growx");
		
		//Add button listeners
		btnRecipeDataEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRecipeDataEdit.setEnabled(false);
				btnRecipeDataDelete.setEnabled(true);
				btnRecipeDataCancel.setEnabled(true);
				btnRecipeDataSave.setEnabled(true);
				RecipePanel.tabbedRecipePane.setEnabledAt(0, false);
				RecipePanel.tabbedRecipePane.setEnabledAt(1, true);
				RecipePanel.tabbedRecipePane.setEnabledAt(2, false);
				InitializeMenu.DisableAllMenuButtons();
				textRecipeNameB.setEditable(true);
				textInspirationB.setEditable(true);
				textIngredientsB.setEditable(true);
				textSuggestedYeastB.setEditable(true);
				textMethodB.setEditable(true);
				textNotesB.setEditable(true);
				textReferencesB.setEditable(true);
				textVolumeB.setEditable(true);
				textIngredientsB.setBackground(Color.WHITE);
				textMethodB.setBackground(Color.WHITE);
				textNotesB.setBackground(Color.WHITE);
			}
		});
		
		btnRecipeDataCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRecipeData();
				btnRecipeDataEdit.setEnabled(true);
				btnRecipeDataDelete.setEnabled(false);
				btnRecipeDataCancel.setEnabled(false);
				btnRecipeDataSave.setEnabled(false);
				RecipePanel.tabbedRecipePane.setEnabledAt(0, true);
				RecipePanel.tabbedRecipePane.setEnabledAt(1, true);
				RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
				InitializeMenu.EnableAllMenuButtons();
				textRecipeNameB.setEditable(false);
				textInspirationB.setEditable(false);
				textIngredientsB.setEditable(false);
				textSuggestedYeastB.setEditable(false);
				textMethodB.setEditable(false);
				textNotesB.setEditable(false);
				textReferencesB.setEditable(false);
				textVolumeB.setEditable(false);
				textIngredientsB.setBackground(UIManager.getColor("Panel.background"));
				textMethodB.setBackground(UIManager.getColor("Panel.background"));
				textNotesB.setBackground(UIManager.getColor("Panel.background"));
			}
		});
		
		btnRecipeDataSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textRecipeNameB.equals("") || textRecipeNameB == null){
					JOptionPane.showMessageDialog(null,
						"You must enter a recipe name.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
				} else {
					try {
						DBEngine.updateRecipe();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					btnRecipeDataEdit.setEnabled(true);
					btnRecipeDataDelete.setEnabled(false);
					btnRecipeDataCancel.setEnabled(false);
					btnRecipeDataSave.setEnabled(false);
					RecipePanel.tabbedRecipePane.setEnabledAt(0, true);
					RecipePanel.tabbedRecipePane.setEnabledAt(1, true);
					RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
					InitializeMenu.EnableAllMenuButtons();
					textRecipeNameB.setEditable(false);
					textInspirationB.setEditable(false);
					textIngredientsB.setEditable(false);
					textSuggestedYeastB.setEditable(false);
					textMethodB.setEditable(false);
					textNotesB.setEditable(false);
					textReferencesB.setEditable(false);
					textVolumeB.setEditable(false);
					textIngredientsB.setBackground(UIManager.getColor("Panel.background"));
					textMethodB.setBackground(UIManager.getColor("Panel.background"));
					textNotesB.setBackground(UIManager.getColor("Panel.background"));
				}
			}
		});
		
		btnRecipeDataDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the entire Recipe.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteRecipe();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						btnRecipeDataEdit.setEnabled(false);
						btnRecipeDataDelete.setEnabled(false);
						btnRecipeDataCancel.setEnabled(false);
						btnRecipeDataSave.setEnabled(false);
						RecipePanel.tabbedRecipePane.setEnabledAt(0, true);
						RecipePanel.tabbedRecipePane.setEnabledAt(1, false);
						RecipePanel.tabbedRecipePane.setEnabledAt(2, true);
						InitializeMenu.EnableAllMenuButtons();
						textRecipeNameB.setEditable(false);
						textInspirationB.setEditable(false);
						textIngredientsB.setEditable(false);
						textSuggestedYeastB.setEditable(false);
						textMethodB.setEditable(false);
						textNotesB.setEditable(false);
						textReferencesB.setEditable(false);
						textVolumeB.setEditable(false);
						textIngredientsB.setBackground(UIManager.getColor("Panel.background"));
						textMethodB.setBackground(UIManager.getColor("Panel.background"));
						textNotesB.setBackground(UIManager.getColor("Panel.background"));
						RecipeSearchPanel.RecipeScrollPane.remove(RecipeSearchPanel.RecipeTable);
						RecipeSearchPanel.RecipeScrollPane.setViewportView(null);
						RecipeAddPanel.clearRecipeAddData();
						RecipeSearchPanel.initializeTable();
						RecipeSearchPanel.RecipeScrollPane.setViewportView(RecipeSearchPanel.RecipeTable);
						clearRecipeData();
						RecipePanel.tabbedRecipePane.setSelectedIndex(0);
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});
		
	}
	
	public static void setRecipeData(){
		textRecipeRefB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,0));
		textRecipeNameB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,1));
		textInspirationB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,2));
		textIngredientsB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,3));
		textSuggestedYeastB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,4));
		textMethodB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,5));
		textNotesB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,6));
		textReferencesB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,7));
		textVolumeB.setText((String) RecipeSearchPanel.RecipeTable.getValueAt(RecipeSearchPanel.RecipeSearchSelectedRow,8));
	}
	
	public static void clearRecipeData(){
		textRecipeRefB.setText("");
		textRecipeNameB.setText("");
		textInspirationB.setText("");
		textIngredientsB.setText("");
		textSuggestedYeastB.setText("");
		textMethodB.setText("");
		textNotesB.setText("");
		textReferencesB.setText("");
		textVolumeB.setText("");
	}
	
	public static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}


}