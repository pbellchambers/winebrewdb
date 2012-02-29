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

public class BrewAddPanel extends JPanel {

	private static final long serialVersionUID = -8501099200598952968L;
	public static JPanel tabbedBrewAddPanel;
	public static JLabel lblBrewRefAdd;
	public static JTextField textBrewRefAdd;
	public static JLabel lblBrewNameAdd;
	public static JTextField textBrewNameAdd;
	public static JLabel lblBrewColourAdd;
	public static JComboBox<String> comboBrewColourAdd;
	public static JLabel lblBrewRecipeAdd;
	public static JTextField textBrewRecipeAdd;
	public static JLabel lblBrewThumbsAdd;
	public static JComboBox<String> comboBrewThumbsAdd;
	public static JLabel lblBrewDatePlannedAdd;
	public static JTextField textBrewDatePlannedAdd;
	public static JLabel lblBrewDateStartedAdd;
	public static JTextField textBrewDateStartedAdd;
	public static JLabel lblBrewDateBottledAdd;
	public static JTextField textBrewDateBottledAdd;
	public static JLabel lblBrewYeastAdd;
	public static JTextField textBrewYeastAdd;
	public static JLabel lblBrewStartSGAdd;
	public static JFormattedTextField textBrewStartSGAdd;
	public static JLabel lblBrewStartAdjustedSGAdd;
	public static JFormattedTextField textBrewStartAdjustedSGAdd;
	public static JLabel lblBrewEndSGAdd;
	public static JFormattedTextField textBrewEndSGAdd;
	public static JLabel lblBrewAimedABVAdd;
	public static JFormattedTextField textBrewAimedABVAdd;
	public static JLabel lblBrewFinalABVAdd;
	public static JFormattedTextField textBrewFinalABVAdd;
	public static JLabel lblBrewFinalAdjustedABVAdd;
	public static JFormattedTextField textBrewFinalAdjustedABVAdd;
	public static JLabel lblBrewVolumeMadeAdd;
	public static JTextField textBrewVolumeMadeAdd;
	public static JLabel lblBrewNumberBottlesAdd;
	public static JTextField textBrewNumberBottlesAdd;
	public static JLabel lblBrewVolumeMade2Add;
	public static JLabel lblBrewTastingNotesAdd;
	public static JTextField textBrewTastingNotesAdd;
	public static JLabel lblBrewGeneralNotesAdd;
	public static JTextArea textBrewGeneralNotesAdd;
	public static JButton btnBrewAddEdit;
	public static JButton btnBrewAddCancel;
	public static JButton btnBrewAddSave;
	public static JScrollPane BrewAddNotesScrollPane;
	public static JCheckBox chckbxBrewInPlanningAdd;
	public static JCheckBox chckbxBrewInFermentingAdd;
	public static JCheckBox chckbxBrewInFiningAdd;
	public static JCheckBox chckbxBrewInMaturingAdd;
	public static JCheckBox chckbxBrewInBottlesAdd;
	public static JCheckBox chckbxBrewDrunkAdd;
	
	
	public static void InitializePanel(){
		
		tabbedBrewAddPanel = new JPanel();
		tabbedBrewAddPanel.setBackground(Color.WHITE);
		tabbedBrewAddPanel.setLayout(new MigLayout("", "[105px:105px:105px][60px:60px, grow][105px:105px:105px][70px:70px, grow][120px:120px:120px][60px:60px, grow][75px:75px:75px][60px:60px, grow]", "[][][][20px:20px][][][][20px:20px][][][20px:20px][][20px:20px][][55px:55,grow][15px:15px][]"));
		
		lblBrewRefAdd = new JLabel("Brew Ref:");
		tabbedBrewAddPanel.add(lblBrewRefAdd, "flowx,cell 0 0,alignx right");
			
		textBrewRefAdd = new JTextField();
		textBrewRefAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewRefAdd, "cell 1 0,growx");
		
		lblBrewNameAdd = new JLabel("Brew Name:");
		tabbedBrewAddPanel.add(lblBrewNameAdd, "flowx,cell 2 0,alignx right");
			
		textBrewNameAdd = new JTextField();
		textBrewNameAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewNameAdd, "cell 3 0 3,growx");
		
		lblBrewColourAdd = new JLabel("Colour:");
		tabbedBrewAddPanel.add(lblBrewColourAdd, "flowx,cell 6 0,alignx right");
			
		comboBrewColourAdd = new JComboBox<String>();
		comboBrewColourAdd.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 6334568977984753664L;
			@Override
	        public void paint(Graphics g) {
	            setForeground(Color.BLACK);
	            super.paint(g);
	            if(comboBrewColourAdd.getSelectedItem().equals(" ")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	            if(comboBrewColourAdd.getSelectedItem().equals("Red")){
	            	setBackground(new Color(255, 192, 203));
		            super.paint(g);
				}
	            if(comboBrewColourAdd.getSelectedItem().equals("White")){
	            	setBackground(new Color(255, 255, 224));
		            super.paint(g);
				}
	            if(comboBrewColourAdd.getSelectedItem().equals("Ros�")){
	            	setBackground(new Color(255, 240, 245));
		            super.paint(g);
				}
	            if(comboBrewColourAdd.getSelectedItem().equals("Other")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	        }
	    });
		comboBrewColourAdd.setBackground(Color.WHITE);
		comboBrewColourAdd.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Red", "White", "Ros�", "Other"}));
		comboBrewColourAdd.setEnabled(false);
		tabbedBrewAddPanel.add(comboBrewColourAdd, "cell 7 0,growx");
		
		lblBrewRecipeAdd = new JLabel("Recipe From:");
		tabbedBrewAddPanel.add(lblBrewRecipeAdd, "flowx,cell 0 1,alignx right");
			
		textBrewRecipeAdd = new JTextField();
		textBrewRecipeAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewRecipeAdd, "cell 1 1 5,growx");
		
		lblBrewThumbsAdd = new JLabel("Thumbs Up?");
		tabbedBrewAddPanel.add(lblBrewThumbsAdd, "flowx,cell 6 1,alignx right");
			
		comboBrewThumbsAdd = new JComboBox<String>();
		comboBrewThumbsAdd.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 6334568777984753664L;
			@Override
	        public void paint(Graphics g) {
	            setForeground(Color.BLACK);
	            super.paint(g);
	            if(comboBrewThumbsAdd.getSelectedItem().equals(" ")){
	            	setBackground(Color.WHITE);
		            super.paint(g);
				}
	            if(comboBrewThumbsAdd.getSelectedItem().equals("Up")){
	            	setBackground(new Color(152, 251, 152));
		            super.paint(g);
				}
	            if(comboBrewThumbsAdd.getSelectedItem().equals("Middle")){
	            	setBackground(new Color(255, 255, 224));
		            super.paint(g);
				}
	            if(comboBrewThumbsAdd.getSelectedItem().equals("Down")){
	            	setBackground(new Color(210, 180, 140));
		            super.paint(g);
				}
	        }
	    });
		comboBrewThumbsAdd.setBackground(Color.WHITE);
		comboBrewThumbsAdd.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Up", "Middle", "Down"}));
		comboBrewThumbsAdd.setEnabled(false);
		tabbedBrewAddPanel.add(comboBrewThumbsAdd, "cell 7 1,growx");
		
		//TODO: Make these some form of date picker
		lblBrewDatePlannedAdd = new JLabel("Date Planned:");
		tabbedBrewAddPanel.add(lblBrewDatePlannedAdd, "flowx,cell 0 2,alignx right");
			
		textBrewDatePlannedAdd = new JTextField();
		textBrewDatePlannedAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewDatePlannedAdd, "cell 1 2,growx");
		
		lblBrewDateStartedAdd = new JLabel("Date Started:");
		tabbedBrewAddPanel.add(lblBrewDateStartedAdd, "flowx,cell 2 2,alignx right");
			
		textBrewDateStartedAdd = new JTextField();
		textBrewDateStartedAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewDateStartedAdd, "cell 3 2,growx");
		
		lblBrewDateBottledAdd = new JLabel("Date Bottled:");
		tabbedBrewAddPanel.add(lblBrewDateBottledAdd, "flowx,cell 4 2,alignx right");
			
		textBrewDateBottledAdd = new JTextField();
		textBrewDateBottledAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewDateBottledAdd, "cell 5 2,growx");
			
		lblBrewStartSGAdd = new JLabel("Start SG:");
		tabbedBrewAddPanel.add(lblBrewStartSGAdd, "flowx,cell 0 4,alignx right");
		
		//TODO: Format these number fields properly
		textBrewStartSGAdd = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartSGAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewStartSGAdd, "cell 1 4,growx");
		
		lblBrewStartAdjustedSGAdd = new JLabel("Start Adjusted SG:");
		tabbedBrewAddPanel.add(lblBrewStartAdjustedSGAdd, "flowx,cell 2 4,alignx right");
			
		textBrewStartAdjustedSGAdd = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartAdjustedSGAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewStartAdjustedSGAdd, "cell 3 4,growx");
		
		lblBrewEndSGAdd = new JLabel("End SG:");
		tabbedBrewAddPanel.add(lblBrewEndSGAdd, "flowx,cell 4 4,alignx right");
			
		textBrewEndSGAdd = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewEndSGAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewEndSGAdd, "cell 5 4,growx");
		
		lblBrewAimedABVAdd = new JLabel("Aimed ABV%:");
		tabbedBrewAddPanel.add(lblBrewAimedABVAdd, "flowx,cell 0 5,alignx right");
			
		textBrewAimedABVAdd = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewAimedABVAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewAimedABVAdd, "cell 1 5,growx");
		
		lblBrewFinalABVAdd = new JLabel("Final ABV%:");
		tabbedBrewAddPanel.add(lblBrewFinalABVAdd, "flowx,cell 2 5,alignx right");
			
		textBrewFinalABVAdd = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalABVAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewFinalABVAdd, "cell 3 5,growx");
		
		lblBrewFinalAdjustedABVAdd = new JLabel("Final Adjusted ABV%:");
		tabbedBrewAddPanel.add(lblBrewFinalAdjustedABVAdd, "flowx,cell 4 5,alignx right");
			
		textBrewFinalAdjustedABVAdd = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalAdjustedABVAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewFinalAdjustedABVAdd, "cell 5 5,growx");
		
		lblBrewYeastAdd = new JLabel("Yeast:");
		tabbedBrewAddPanel.add(lblBrewYeastAdd, "flowx,cell 0 6,alignx right");
			
		textBrewYeastAdd = new JTextField();
		textBrewYeastAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewYeastAdd, "cell 1 6 5,growx");
		
		lblBrewVolumeMadeAdd = new JLabel("Volume Made:");
		tabbedBrewAddPanel.add(lblBrewVolumeMadeAdd, "flowx,cell 0 8,alignx right");
			
		textBrewVolumeMadeAdd = new JTextField();
		textBrewVolumeMadeAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewVolumeMadeAdd, "cell 1 8,growx");
		
		lblBrewVolumeMade2Add = new JLabel("(gallons)");
		tabbedBrewAddPanel.add(lblBrewVolumeMade2Add, "flowx,cell 2 8,alignx left");
		
		chckbxBrewInPlanningAdd = new JCheckBox("In Planning");
		chckbxBrewInPlanningAdd.setSelected(false);
		chckbxBrewInPlanningAdd.setEnabled(false);
		chckbxBrewInPlanningAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewInPlanningAdd, "cell 3 8,growx");
		
		chckbxBrewInFermentingAdd = new JCheckBox("In Fermenting");
		chckbxBrewInFermentingAdd.setSelected(false);
		chckbxBrewInFermentingAdd.setEnabled(false);
		chckbxBrewInFermentingAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewInFermentingAdd, "cell 4 8,growx");
			
		chckbxBrewInFiningAdd = new JCheckBox("In Fining");
		chckbxBrewInFiningAdd.setSelected(false);
		chckbxBrewInFiningAdd.setEnabled(false);
		chckbxBrewInFiningAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewInFiningAdd, "cell 5 8,growx");
		
		lblBrewNumberBottlesAdd = new JLabel("Number of Bottles:");
		tabbedBrewAddPanel.add(lblBrewNumberBottlesAdd, "flowx,cell 0 9,alignx right");
			
		textBrewNumberBottlesAdd = new JTextField();
		textBrewNumberBottlesAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewNumberBottlesAdd, "cell 1 9,growx");
		
		chckbxBrewInMaturingAdd = new JCheckBox("In Maturing");
		chckbxBrewInMaturingAdd.setSelected(false);
		chckbxBrewInMaturingAdd.setEnabled(false);
		chckbxBrewInMaturingAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewInMaturingAdd, "cell 3 9,growx");	
			
		chckbxBrewInBottlesAdd = new JCheckBox("In Bottles");
		chckbxBrewInBottlesAdd.setSelected(false);
		chckbxBrewInBottlesAdd.setEnabled(false);
		chckbxBrewInBottlesAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewInBottlesAdd, "cell 4 9,growx");
		
		chckbxBrewDrunkAdd = new JCheckBox("Drunk");
		chckbxBrewDrunkAdd.setSelected(false);
		chckbxBrewDrunkAdd.setEnabled(false);
		chckbxBrewDrunkAdd.setBackground(Color.WHITE);
		tabbedBrewAddPanel.add(chckbxBrewDrunkAdd, "cell 5 9,growx");	
		
		lblBrewTastingNotesAdd = new JLabel("Tasting Notes:");
		tabbedBrewAddPanel.add(lblBrewTastingNotesAdd, "flowx,cell 0 11,alignx right");
			
		textBrewTastingNotesAdd = new JTextField();
		textBrewTastingNotesAdd.setEditable(false);
		tabbedBrewAddPanel.add(textBrewTastingNotesAdd, "cell 1 11 7,growx");
		
		lblBrewGeneralNotesAdd = new JLabel("General Notes:");
		tabbedBrewAddPanel.add(lblBrewGeneralNotesAdd, "flowx,cell 0 13,alignx right");
		
		textBrewGeneralNotesAdd = new JTextArea();
		textBrewGeneralNotesAdd.setEditable(false);
		textBrewGeneralNotesAdd.setLineWrap(true);
		textBrewGeneralNotesAdd.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textBrewGeneralNotesAdd
	    BrewAddNotesScrollPane = new JScrollPane();
	    BrewAddNotesScrollPane.setViewportView(textBrewGeneralNotesAdd);
	    tabbedBrewAddPanel.add(BrewAddNotesScrollPane, "cell 1 13 7 2,grow");
		
		btnBrewAddEdit = new JButton("Add");
		btnBrewAddEdit.setEnabled(true);
		tabbedBrewAddPanel.add(btnBrewAddEdit, "cell 0 16 2 1,growx");
		
		btnBrewAddCancel = new JButton("Cancel");
		btnBrewAddCancel.setEnabled(false);
		tabbedBrewAddPanel.add(btnBrewAddCancel, "cell 5 16,growx");
			
		btnBrewAddSave = new JButton("Save / Insert");
		btnBrewAddSave.setEnabled(false);
		tabbedBrewAddPanel.add(btnBrewAddSave, "cell 6 16 2 1,growx");
		
		//Add button listeners
		btnBrewAddEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBrewAddEdit.setEnabled(false);
				btnBrewAddCancel.setEnabled(true);
				btnBrewAddSave.setEnabled(true);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				textBrewNameAdd.setEditable(true);
				comboBrewColourAdd.setEnabled(true);
				textBrewRecipeAdd.setEditable(true);
				comboBrewThumbsAdd.setEnabled(true);
				textBrewDatePlannedAdd.setEditable(true);
				textBrewDateStartedAdd.setEditable(true);
				textBrewDateBottledAdd.setEditable(true);
				textBrewStartSGAdd.setEditable(true);
				textBrewStartAdjustedSGAdd.setEditable(true);
				textBrewEndSGAdd.setEditable(true);
				textBrewAimedABVAdd.setEditable(true);
				textBrewFinalABVAdd.setEditable(true);
				textBrewFinalAdjustedABVAdd.setEditable(true);
				textBrewYeastAdd.setEditable(true);
				textBrewVolumeMadeAdd.setEditable(true);
				chckbxBrewInPlanningAdd.setEnabled(true);
				chckbxBrewInFermentingAdd.setEnabled(true);
				chckbxBrewInFiningAdd.setEnabled(true);
				textBrewNumberBottlesAdd.setEditable(true);
				chckbxBrewInMaturingAdd.setEnabled(true);
				chckbxBrewInBottlesAdd.setEnabled(true);
				chckbxBrewDrunkAdd.setEnabled(true);
				textBrewTastingNotesAdd.setEditable(true);
				textBrewGeneralNotesAdd.setEditable(true);
				textBrewGeneralNotesAdd.setBackground(Color.WHITE);
			}
		});
		
		btnBrewAddCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBrewAddEdit.setEnabled(true);
				btnBrewAddCancel.setEnabled(false);
				btnBrewAddSave.setEnabled(false);
				BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
				BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
				BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
				textBrewNameAdd.setEditable(false);
				comboBrewColourAdd.setEnabled(false);
				textBrewRecipeAdd.setEditable(false);
				comboBrewThumbsAdd.setEnabled(false);
				textBrewDatePlannedAdd.setEditable(false);
				textBrewDateStartedAdd.setEditable(false);
				textBrewDateBottledAdd.setEditable(false);
				textBrewStartSGAdd.setEditable(false);
				textBrewStartAdjustedSGAdd.setEditable(false);
				textBrewEndSGAdd.setEditable(false);
				textBrewAimedABVAdd.setEditable(false);
				textBrewFinalABVAdd.setEditable(false);
				textBrewFinalAdjustedABVAdd.setEditable(false);
				textBrewYeastAdd.setEditable(false);
				textBrewVolumeMadeAdd.setEditable(false);
				chckbxBrewInPlanningAdd.setEnabled(false);
				chckbxBrewInFermentingAdd.setEnabled(false);
				chckbxBrewInFiningAdd.setEnabled(false);
				textBrewNumberBottlesAdd.setEditable(false);
				chckbxBrewInMaturingAdd.setEnabled(false);
				chckbxBrewInBottlesAdd.setEnabled(false);
				chckbxBrewDrunkAdd.setEnabled(false);
				textBrewTastingNotesAdd.setEditable(false);
				textBrewGeneralNotesAdd.setEditable(false);
				textBrewGeneralNotesAdd.setBackground(UIManager.getColor("Panel.background"));
				clearBrewAddData();
			}
		});
		
		btnBrewAddSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxBrewInPlanningAdd.isSelected() == false && chckbxBrewInFermentingAdd.isSelected() == false && chckbxBrewInFiningAdd.isSelected() == false && chckbxBrewInMaturingAdd.isSelected() == false && chckbxBrewInBottlesAdd.isSelected() == false && chckbxBrewDrunkAdd.isSelected() == false){
					JOptionPane.showMessageDialog(null,
						"You must select at least one state for the brew to be in.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
				} else {
					try {
						DBEngine.addBrew();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					btnBrewAddEdit.setEnabled(true);
					btnBrewAddCancel.setEnabled(false);
					btnBrewAddSave.setEnabled(false);
					BrewPanel.tabbedBrewPane.setEnabledAt(0, true);
					BrewPanel.tabbedBrewPane.setEnabledAt(1, false);
					BrewPanel.tabbedBrewPane.setEnabledAt(2, false);
					BrewPanel.tabbedBrewPane.setEnabledAt(3, false);
					BrewPanel.tabbedBrewPane.setEnabledAt(4, true);
					textBrewNameAdd.setEditable(false);
					comboBrewColourAdd.setEnabled(false);
					textBrewRecipeAdd.setEditable(false);
					comboBrewThumbsAdd.setEnabled(false);
					textBrewDatePlannedAdd.setEditable(false);
					textBrewDateStartedAdd.setEditable(false);
					textBrewDateBottledAdd.setEditable(false);
					textBrewStartSGAdd.setEditable(false);
					textBrewStartAdjustedSGAdd.setEditable(false);
					textBrewEndSGAdd.setEditable(false);
					textBrewAimedABVAdd.setEditable(false);
					textBrewFinalABVAdd.setEditable(false);
					textBrewFinalAdjustedABVAdd.setEditable(false);
					textBrewYeastAdd.setEditable(false);
					textBrewVolumeMadeAdd.setEditable(false);
					chckbxBrewInPlanningAdd.setEnabled(false);
					chckbxBrewInFermentingAdd.setEnabled(false);
					chckbxBrewInFiningAdd.setEnabled(false);
					textBrewNumberBottlesAdd.setEditable(false);
					chckbxBrewInMaturingAdd.setEnabled(false);
					chckbxBrewInBottlesAdd.setEnabled(false);
					chckbxBrewDrunkAdd.setEnabled(false);
					textBrewTastingNotesAdd.setEditable(false);
					textBrewGeneralNotesAdd.setEditable(false);
					textBrewGeneralNotesAdd.setBackground(UIManager.getColor("Panel.background"));
					clearBrewAddData();
				}
			}
		});
	}
	
	public static void clearBrewAddData(){
		textBrewRefAdd.setText("");
		textBrewNameAdd.setText("");
		textBrewRecipeAdd.setText("");
		textBrewDatePlannedAdd.setText("");
		textBrewDateStartedAdd.setText("");
		textBrewDateBottledAdd.setText("");
		textBrewYeastAdd.setText("");
		textBrewStartSGAdd.setText("");
		textBrewStartAdjustedSGAdd.setText("");
		textBrewEndSGAdd.setText("");
		textBrewAimedABVAdd.setText("");
		textBrewFinalABVAdd.setText("");
		textBrewFinalAdjustedABVAdd.setText("");
		textBrewVolumeMadeAdd.setText("");
		textBrewNumberBottlesAdd.setText("");
		textBrewTastingNotesAdd.setText("");
		textBrewGeneralNotesAdd.setText("");
		comboBrewThumbsAdd.setSelectedItem(" ");
		comboBrewColourAdd.setSelectedItem(" ");
		chckbxBrewInPlanningAdd.setSelected(false);
		chckbxBrewInFermentingAdd.setSelected(false);
		chckbxBrewInFiningAdd.setSelected(false);
		chckbxBrewInMaturingAdd.setSelected(false);
		chckbxBrewInBottlesAdd.setSelected(false);
		chckbxBrewDrunkAdd.setSelected(false);
	}
	
	public static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}


}