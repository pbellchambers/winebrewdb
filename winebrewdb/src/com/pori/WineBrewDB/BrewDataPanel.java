package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.pori.WineBrewDB.SQLite.DBEngine;

import net.miginfocom.swing.MigLayout;

public class BrewDataPanel extends JPanel {

	private static final long serialVersionUID = -844109213529915549L;
	public static JPanel tabbedBrewDataPanel;
	public static JLabel lblBrewRefB;
	public static JTextField textBrewRefB;
	public static JLabel lblBrewNameB;
	public static JTextField textBrewNameB;
	public static JLabel lblBrewColourB;
	public static JComboBox<String> comboBrewColourB;
	public static JLabel lblBrewRecipeB;
	public static JTextField textBrewRecipeB;
	public static JLabel lblBrewThumbsB;
	public static JComboBox<String> comboBrewThumbsB;
	public static JLabel lblBrewDatePlannedB;
	public static JTextField textBrewDatePlannedB;
	public static JLabel lblBrewDateStartedB;
	public static JTextField textBrewDateStartedB;
	public static JLabel lblBrewDateBottledB;
	public static JTextField textBrewDateBottledB;
	public static JLabel lblBrewYeastB;
	public static JTextField textBrewYeastB;
	public static JLabel lblBrewStartSGB;
	public static JFormattedTextField textBrewStartSGB;
	public static JLabel lblBrewStartAdjustedSGB;
	public static JFormattedTextField textBrewStartAdjustedSGB;
	public static JLabel lblBrewEndSGB;
	public static JFormattedTextField textBrewEndSGB;
	public static JLabel lblBrewAimedABVB;
	public static JFormattedTextField textBrewAimedABVB;
	public static JLabel lblBrewFinalABVB;
	public static JFormattedTextField textBrewFinalABVB;
	public static JLabel lblBrewFinalAdjustedABVB;
	public static JFormattedTextField textBrewFinalAdjustedABVB;
	public static JLabel lblBrewVolumeMadeB;
	public static JTextField textBrewVolumeMadeB;
	public static JLabel lblBrewNumberBottlesB;
	public static JTextField textBrewNumberBottlesB;
	public static JLabel lblBrewVolumeMade2B;
	public static JLabel lblBrewTastingNotesB;
	public static JTextField textBrewTastingNotesB;
	public static JLabel lblBrewGeneralNotesB;
	public static JTextArea textBrewGeneralNotesB;
	public static JButton btnBrewDataEdit;
	public static JButton btnBrewDataSave;
	public static JScrollPane BrewDataNotesScrollPane;
	public static JCheckBox chckbxBrewInPlanningB;
	public static JCheckBox chckbxBrewInFermentingB;
	public static JCheckBox chckbxBrewInFiningB;
	public static JCheckBox chckbxBrewInMaturingB;
	public static JCheckBox chckbxBrewInBottlesB;
	public static JCheckBox chckbxBrewDrunkB;
	public static JButton btnBrewDataDelete;
	public static JButton btnBrewDataCancel;
	
	
	public static void InitializePanel(){
		
		tabbedBrewDataPanel = new JPanel();
		tabbedBrewDataPanel.setBackground(Color.WHITE);
		tabbedBrewDataPanel.setLayout(new MigLayout("", "[105px:105px:105px][60px:60px, grow][105px:105px:105px][70px:70px, grow][120px:120px:120px][60px:60px, grow][75px:75px:75px][60px:60px, grow]", "[][][][20px:20px][][][][20px:20px][][][20px:20px][][20px:20px][][55px:55,grow][15px:15px][]"));
		
		lblBrewRefB = new JLabel("Brew Ref:");
		tabbedBrewDataPanel.add(lblBrewRefB, "flowx,cell 0 0,alignx right");
			
		textBrewRefB = new JTextField();
		textBrewRefB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewRefB, "cell 1 0,growx");
		
		lblBrewNameB = new JLabel("Brew Name:");
		tabbedBrewDataPanel.add(lblBrewNameB, "flowx,cell 2 0,alignx right");
			
		textBrewNameB = new JTextField();
		textBrewNameB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewNameB, "cell 3 0 3,growx");
		
		lblBrewColourB = new JLabel("Colour:");
		tabbedBrewDataPanel.add(lblBrewColourB, "flowx,cell 6 0,alignx right");
			
		comboBrewColourB = new JComboBox<String>();
		comboBrewColourB.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 6334568977984753664L;
			@Override
	        public void paint(Graphics g) {
	            setForeground(Color.BLACK);
	            super.paint(g);
	            if(comboBrewColourB.getSelectedItem().equals(" ")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	            if(comboBrewColourB.getSelectedItem().equals("Red")){
	            	setBackground(new Color(255, 192, 203));
		            super.paint(g);
				}
	            if(comboBrewColourB.getSelectedItem().equals("White")){
	            	setBackground(new Color(255, 255, 224));
		            super.paint(g);
				}
	            if(comboBrewColourB.getSelectedItem().equals("Rosé")){
	            	setBackground(new Color(255, 240, 245));
		            super.paint(g);
				}
	            if(comboBrewColourB.getSelectedItem().equals("Other")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	        }
	    });
		comboBrewColourB.setBackground(Color.WHITE);
		comboBrewColourB.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Red", "White", "Rosé", "Other"}));
		comboBrewColourB.setEnabled(false);
		tabbedBrewDataPanel.add(comboBrewColourB, "cell 7 0,growx");
		
		lblBrewRecipeB = new JLabel("Recipe From:");
		tabbedBrewDataPanel.add(lblBrewRecipeB, "flowx,cell 0 1,alignx right");
			
		textBrewRecipeB = new JTextField();
		textBrewRecipeB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewRecipeB, "cell 1 1 5,growx");
		
		lblBrewThumbsB = new JLabel("Thumbs Up?");
		tabbedBrewDataPanel.add(lblBrewThumbsB, "flowx,cell 6 1,alignx right");
			
		comboBrewThumbsB = new JComboBox<String>();
		comboBrewThumbsB.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 6334568777984753664L;
			@Override
	        public void paint(Graphics g) {
	            setForeground(Color.BLACK);
	            super.paint(g);
	            if(comboBrewThumbsB.getSelectedItem().equals(" ")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	            if(comboBrewThumbsB.getSelectedItem().equals("Up")){
	            	setBackground(new Color(152, 251, 152));
		            super.paint(g);
				}
	            if(comboBrewThumbsB.getSelectedItem().equals("Middle")){
	            	setBackground(new Color(255, 255, 224));
		            super.paint(g);
				}
	            if(comboBrewThumbsB.getSelectedItem().equals("Down")){
	            	setBackground(new Color(210, 180, 140));
		            super.paint(g);
				}
	        }
	    });
		comboBrewThumbsB.setBackground(Color.WHITE);
		comboBrewThumbsB.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Up", "Middle", "Down"}));
		comboBrewThumbsB.setEnabled(false);
		tabbedBrewDataPanel.add(comboBrewThumbsB, "cell 7 1,growx");
		
		//TODO: Make these some form of date picker
		lblBrewDatePlannedB = new JLabel("Date Planned:");
		tabbedBrewDataPanel.add(lblBrewDatePlannedB, "flowx,cell 0 2,alignx right");
			
		textBrewDatePlannedB = new JTextField();
		textBrewDatePlannedB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDatePlannedB, "cell 1 2,growx");
		
		lblBrewDateStartedB = new JLabel("Date Started:");
		tabbedBrewDataPanel.add(lblBrewDateStartedB, "flowx,cell 2 2,alignx right");
			
		textBrewDateStartedB = new JTextField();
		textBrewDateStartedB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDateStartedB, "cell 3 2,growx");
		
		lblBrewDateBottledB = new JLabel("Date Bottled:");
		tabbedBrewDataPanel.add(lblBrewDateBottledB, "flowx,cell 4 2,alignx right");
			
		textBrewDateBottledB = new JTextField();
		textBrewDateBottledB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDateBottledB, "cell 5 2,growx");
			
		lblBrewStartSGB = new JLabel("Start SG:");
		tabbedBrewDataPanel.add(lblBrewStartSGB, "flowx,cell 0 4,alignx right");
		
		//TODO: Format these number fields properly
		textBrewStartSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartSGB, "cell 1 4,growx");
		
		lblBrewStartAdjustedSGB = new JLabel("Start Adjusted SG:");
		tabbedBrewDataPanel.add(lblBrewStartAdjustedSGB, "flowx,cell 2 4,alignx right");
			
		textBrewStartAdjustedSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartAdjustedSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartAdjustedSGB, "cell 3 4,growx");
		
		lblBrewEndSGB = new JLabel("End SG:");
		tabbedBrewDataPanel.add(lblBrewEndSGB, "flowx,cell 4 4,alignx right");
			
		textBrewEndSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewEndSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewEndSGB, "cell 5 4,growx");
		
		lblBrewAimedABVB = new JLabel("Aimed ABV%:");
		tabbedBrewDataPanel.add(lblBrewAimedABVB, "flowx,cell 0 5,alignx right");
			
		textBrewAimedABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewAimedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewAimedABVB, "cell 1 5,growx");
		
		lblBrewFinalABVB = new JLabel("Final ABV%:");
		tabbedBrewDataPanel.add(lblBrewFinalABVB, "flowx,cell 2 5,alignx right");
			
		textBrewFinalABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalABVB, "cell 3 5,growx");
		
		lblBrewFinalAdjustedABVB = new JLabel("Final Adjusted ABV%:");
		tabbedBrewDataPanel.add(lblBrewFinalAdjustedABVB, "flowx,cell 4 5,alignx right");
			
		textBrewFinalAdjustedABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalAdjustedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalAdjustedABVB, "cell 5 5,growx");
		
		lblBrewYeastB = new JLabel("Yeast:");
		tabbedBrewDataPanel.add(lblBrewYeastB, "flowx,cell 0 6,alignx right");
			
		textBrewYeastB = new JTextField();
		textBrewYeastB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewYeastB, "cell 1 6 5,growx");
		
		lblBrewVolumeMadeB = new JLabel("Volume Made:");
		tabbedBrewDataPanel.add(lblBrewVolumeMadeB, "flowx,cell 0 8,alignx right");
			
		textBrewVolumeMadeB = new JTextField();
		textBrewVolumeMadeB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewVolumeMadeB, "cell 1 8,growx");
		
		lblBrewVolumeMade2B = new JLabel("(gallons)");
		tabbedBrewDataPanel.add(lblBrewVolumeMade2B, "flowx,cell 2 8,alignx left");
		
		chckbxBrewInPlanningB = new JCheckBox("In Planning");
		chckbxBrewInPlanningB.setSelected(false);
		chckbxBrewInPlanningB.setEnabled(false);
		chckbxBrewInPlanningB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInPlanningB, "cell 3 8,growx");
		
		chckbxBrewInFermentingB = new JCheckBox("In Fermenting");
		chckbxBrewInFermentingB.setSelected(false);
		chckbxBrewInFermentingB.setEnabled(false);
		chckbxBrewInFermentingB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInFermentingB, "cell 4 8,growx");
			
		chckbxBrewInFiningB = new JCheckBox("In Fining");
		chckbxBrewInFiningB.setSelected(false);
		chckbxBrewInFiningB.setEnabled(false);
		chckbxBrewInFiningB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInFiningB, "cell 5 8,growx");
		
		lblBrewNumberBottlesB = new JLabel("Number of Bottles:");
		tabbedBrewDataPanel.add(lblBrewNumberBottlesB, "flowx,cell 0 9,alignx right");
			
		textBrewNumberBottlesB = new JTextField();
		textBrewNumberBottlesB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewNumberBottlesB, "cell 1 9,growx");
		
		chckbxBrewInMaturingB = new JCheckBox("In Maturing");
		chckbxBrewInMaturingB.setSelected(false);
		chckbxBrewInMaturingB.setEnabled(false);
		chckbxBrewInMaturingB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInMaturingB, "cell 3 9,growx");	
			
		chckbxBrewInBottlesB = new JCheckBox("In Bottles");
		chckbxBrewInBottlesB.setSelected(false);
		chckbxBrewInBottlesB.setEnabled(false);
		chckbxBrewInBottlesB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInBottlesB, "cell 4 9,growx");
		
		chckbxBrewDrunkB = new JCheckBox("Drunk");
		chckbxBrewDrunkB.setSelected(false);
		chckbxBrewDrunkB.setEnabled(false);
		chckbxBrewDrunkB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewDrunkB, "cell 5 9,growx");	
		
		lblBrewTastingNotesB = new JLabel("Tasting Notes:");
		tabbedBrewDataPanel.add(lblBrewTastingNotesB, "flowx,cell 0 11,alignx right");
			
		textBrewTastingNotesB = new JTextField();
		textBrewTastingNotesB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewTastingNotesB, "cell 1 11 7,growx");
		
		lblBrewGeneralNotesB = new JLabel("General Notes:");
		tabbedBrewDataPanel.add(lblBrewGeneralNotesB, "flowx,cell 0 13,alignx right");
		
		textBrewGeneralNotesB = new JTextArea();
		textBrewGeneralNotesB.setEditable(false);
		textBrewGeneralNotesB.setLineWrap(true);
		textBrewGeneralNotesB.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textBrewGeneralNotesB
	    BrewDataNotesScrollPane = new JScrollPane();
	    BrewDataNotesScrollPane.setViewportView(textBrewGeneralNotesB);
	    tabbedBrewDataPanel.add(BrewDataNotesScrollPane, "cell 1 13 7 2,grow");
		
		btnBrewDataEdit = new JButton("Edit");
		btnBrewDataEdit.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataEdit, "cell 0 16 2 1,growx");
		
		btnBrewDataDelete = new JButton("Delete Brew");
		btnBrewDataDelete.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataDelete, "cell 2 16,growx");
		
		btnBrewDataCancel = new JButton("Cancel");
		btnBrewDataCancel.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataCancel, "cell 5 16,growx");
			
		btnBrewDataSave = new JButton("Save / Update");
		btnBrewDataSave.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataSave, "cell 6 16 2 1,growx");
		
		//Add button listeners
		btnBrewDataEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBrewDataEdit.setEnabled(false);
				btnBrewDataDelete.setEnabled(true);
				btnBrewDataCancel.setEnabled(true);
				btnBrewDataSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
				textBrewNameB.setEditable(true);
				comboBrewColourB.setEnabled(true);
				textBrewRecipeB.setEditable(true);
				comboBrewThumbsB.setEnabled(true);
				textBrewDatePlannedB.setEditable(true);
				textBrewDateStartedB.setEditable(true);
				textBrewDateBottledB.setEditable(true);
				textBrewStartSGB.setEditable(true);
				textBrewStartAdjustedSGB.setEditable(true);
				textBrewEndSGB.setEditable(true);
				textBrewAimedABVB.setEditable(true);
				textBrewFinalABVB.setEditable(true);
				textBrewFinalAdjustedABVB.setEditable(true);
				textBrewYeastB.setEditable(true);
				textBrewVolumeMadeB.setEditable(true);
				chckbxBrewInPlanningB.setEnabled(true);
				chckbxBrewInFermentingB.setEnabled(true);
				chckbxBrewInFiningB.setEnabled(true);
				textBrewNumberBottlesB.setEditable(true);
				chckbxBrewInMaturingB.setEnabled(true);
				chckbxBrewInBottlesB.setEnabled(true);
				chckbxBrewDrunkB.setEnabled(true);
				textBrewTastingNotesB.setEditable(true);
				textBrewGeneralNotesB.setEditable(true);
				textBrewGeneralNotesB.setBackground(Color.WHITE);
			}
		});
		
		btnBrewDataCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBrewData();
				btnBrewDataEdit.setEnabled(true);
				btnBrewDataDelete.setEnabled(false);
				btnBrewDataCancel.setEnabled(false);
				btnBrewDataSave.setEnabled(false);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				textBrewNameB.setEditable(false);
				comboBrewColourB.setEnabled(false);
				textBrewRecipeB.setEditable(false);
				comboBrewThumbsB.setEnabled(false);
				textBrewDatePlannedB.setEditable(false);
				textBrewDateStartedB.setEditable(false);
				textBrewDateBottledB.setEditable(false);
				textBrewStartSGB.setEditable(false);
				textBrewStartAdjustedSGB.setEditable(false);
				textBrewEndSGB.setEditable(false);
				textBrewAimedABVB.setEditable(false);
				textBrewFinalABVB.setEditable(false);
				textBrewFinalAdjustedABVB.setEditable(false);
				textBrewYeastB.setEditable(false);
				textBrewVolumeMadeB.setEditable(false);
				chckbxBrewInPlanningB.setEnabled(false);
				chckbxBrewInFermentingB.setEnabled(false);
				chckbxBrewInFiningB.setEnabled(false);
				textBrewNumberBottlesB.setEditable(false);
				chckbxBrewInMaturingB.setEnabled(false);
				chckbxBrewInBottlesB.setEnabled(false);
				chckbxBrewDrunkB.setEnabled(false);
				textBrewTastingNotesB.setEditable(false);
				textBrewGeneralNotesB.setEditable(false);
				textBrewGeneralNotesB.setBackground(UIManager.getColor("Panel.background"));
			}
		});
		
		btnBrewDataSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBEngine.updateBrew();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				btnBrewDataEdit.setEnabled(true);
				btnBrewDataDelete.setEnabled(false);
				btnBrewDataCancel.setEnabled(false);
				btnBrewDataSave.setEnabled(false);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				textBrewNameB.setEditable(false);
				comboBrewColourB.setEnabled(false);
				textBrewRecipeB.setEditable(false);
				comboBrewThumbsB.setEnabled(false);
				textBrewDatePlannedB.setEditable(false);
				textBrewDateStartedB.setEditable(false);
				textBrewDateBottledB.setEditable(false);
				textBrewStartSGB.setEditable(false);
				textBrewStartAdjustedSGB.setEditable(false);
				textBrewEndSGB.setEditable(false);
				textBrewAimedABVB.setEditable(false);
				textBrewFinalABVB.setEditable(false);
				textBrewFinalAdjustedABVB.setEditable(false);
				textBrewYeastB.setEditable(false);
				textBrewVolumeMadeB.setEditable(false);
				chckbxBrewInPlanningB.setEnabled(false);
				chckbxBrewInFermentingB.setEnabled(false);
				chckbxBrewInFiningB.setEnabled(false);
				textBrewNumberBottlesB.setEditable(false);
				chckbxBrewInMaturingB.setEnabled(false);
				chckbxBrewInBottlesB.setEnabled(false);
				chckbxBrewDrunkB.setEnabled(false);
				textBrewTastingNotesB.setEditable(false);
				textBrewGeneralNotesB.setEditable(false);
				textBrewGeneralNotesB.setBackground(UIManager.getColor("Panel.background"));
			}
		});
		
		btnBrewDataDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int response = JOptionPane.showConfirmDialog(null, "This will irreversibly delete the entire brew, brew notes, and brew pictures.\n\nAre you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      //Nothing Happens
				    	
				    } else if (response == JOptionPane.YES_OPTION) {  
					  	try {
							DBEngine.deleteBrew();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						btnBrewDataEdit.setEnabled(false);
						btnBrewDataDelete.setEnabled(false);
						btnBrewDataCancel.setEnabled(false);
						btnBrewDataSave.setEnabled(false);
						BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
						BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
						BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
						BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
						BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
						textBrewNameB.setEditable(false);
						comboBrewColourB.setEnabled(false);
						textBrewRecipeB.setEditable(false);
						comboBrewThumbsB.setEnabled(false);
						textBrewDatePlannedB.setEditable(false);
						textBrewDateStartedB.setEditable(false);
						textBrewDateBottledB.setEditable(false);
						textBrewStartSGB.setEditable(false);
						textBrewStartAdjustedSGB.setEditable(false);
						textBrewEndSGB.setEditable(false);
						textBrewAimedABVB.setEditable(false);
						textBrewFinalABVB.setEditable(false);
						textBrewFinalAdjustedABVB.setEditable(false);
						textBrewYeastB.setEditable(false);
						textBrewVolumeMadeB.setEditable(false);
						chckbxBrewInPlanningB.setEnabled(false);
						chckbxBrewInFermentingB.setEnabled(false);
						chckbxBrewInFiningB.setEnabled(false);
						textBrewNumberBottlesB.setEditable(false);
						chckbxBrewInMaturingB.setEnabled(false);
						chckbxBrewInBottlesB.setEnabled(false);
						chckbxBrewDrunkB.setEnabled(false);
						textBrewTastingNotesB.setEditable(false);
						textBrewGeneralNotesB.setEditable(false);
						textBrewGeneralNotesB.setBackground(UIManager.getColor("Panel.background"));
						BrewSearchPanel.BrewScrollPane.remove(BrewSearchPanel.BrewTable);
						BrewSearchPanel.BrewScrollPane.setViewportView(null);
						BrewAddPanel.clearBrewAddData();
						BrewSearchPanel.initializeTable();
						BrewSearchPanel.BrewScrollPane.setViewportView(BrewSearchPanel.BrewTable);
						clearBrewData();
						BrewPanel.tabbedBrewPane.setSelectedIndex(0);
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});
		
	}
	
	public static void setBrewData(){
		textBrewRefB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,0));
		textBrewNameB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,1));
		textBrewRecipeB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,5));
		textBrewDatePlannedB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,2));
		textBrewDateStartedB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,3));
		textBrewDateBottledB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,4));
		textBrewYeastB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,6));
		textBrewStartSGB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,7));
		textBrewStartAdjustedSGB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,8));
		textBrewEndSGB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,9));
		textBrewAimedABVB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,10));
		textBrewFinalABVB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,11));
		textBrewFinalAdjustedABVB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,12));
		textBrewVolumeMadeB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,22));
		textBrewNumberBottlesB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,23));
		textBrewTastingNotesB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,14));
		textBrewGeneralNotesB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,13));
		comboBrewThumbsB.setSelectedItem((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,15));
		comboBrewColourB.setSelectedItem((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,24));
		chckbxBrewInPlanningB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,16)));
		chckbxBrewInFermentingB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,17)));
		chckbxBrewInFiningB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,18)));
		chckbxBrewInMaturingB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,19)));
		chckbxBrewInBottlesB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,20)));
		chckbxBrewDrunkB.setSelected((Boolean) stringToBool((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,21)));
	}
	
	public static void clearBrewData(){
		textBrewRefB.setText("");
		textBrewNameB.setText("");
		textBrewRecipeB.setText("");
		textBrewDatePlannedB.setText("");
		textBrewDateStartedB.setText("");
		textBrewDateBottledB.setText("");
		textBrewYeastB.setText("");
		textBrewStartSGB.setText("");
		textBrewStartAdjustedSGB.setText("");
		textBrewEndSGB.setText("");
		textBrewAimedABVB.setText("");
		textBrewFinalABVB.setText("");
		textBrewFinalAdjustedABVB.setText("");
		textBrewVolumeMadeB.setText("");
		textBrewNumberBottlesB.setText("");
		textBrewTastingNotesB.setText("");
		textBrewGeneralNotesB.setText("");
		comboBrewThumbsB.setSelectedItem(" ");
		comboBrewColourB.setSelectedItem(" ");
		chckbxBrewInPlanningB.setSelected(false);
		chckbxBrewInFermentingB.setSelected(false);
		chckbxBrewInFiningB.setSelected(false);
		chckbxBrewInMaturingB.setSelected(false);
		chckbxBrewInBottlesB.setSelected(false);
		chckbxBrewDrunkB.setSelected(false);
	}
	
	public static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}


}