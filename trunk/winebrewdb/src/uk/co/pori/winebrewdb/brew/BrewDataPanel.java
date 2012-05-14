package uk.co.pori.winebrewdb.brew;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import uk.co.pori.winebrewdb.Dates;
import uk.co.pori.winebrewdb.InitialiseMenu;
import uk.co.pori.winebrewdb.MainWindow;
import uk.co.pori.winebrewdb.sqlite.DBEngine;

import agiletrack.swing.JDateChooser;

import net.miginfocom.swing.MigLayout;

/**
 * This is the brew data panel to be displayed as a tab on the main brew panel tab bar. It shows the brew data for the currently selected brew.
 * 
 * @author Paul.Bellchambers
 *
 */
public class BrewDataPanel extends JPanel {

	private static final long serialVersionUID = -844109213529915549L;
	static JPanel tabbedBrewDataPanel;
	public static JTextField textBrewRefB;
	public static JTextField textBrewNameB;
	public static JComboBox<String> comboBrewColourB;
	public static JTextField textBrewRecipeB;
	public static JComboBox<String> comboBrewThumbsB;
	public static JDateChooser chooserBrewDatePlannedB;
	public static JDateChooser chooserBrewDateStartedB;
	public static JDateChooser chooserBrewDateBottledB;
	public static JTextField textBrewYeastB;
	public static JFormattedTextField textBrewStartSGB;
	public static JFormattedTextField textBrewStartAdjustedSGB;
	public static JFormattedTextField textBrewEndSGB;
	public static JFormattedTextField textBrewAimedABVB;
	public static JFormattedTextField textBrewFinalABVB;
	public static JFormattedTextField textBrewFinalAdjustedABVB;
	public static JTextField textBrewVolumeMadeB;
	public static JTextField textBrewNumberBottlesB;
	public static JTextField textBrewTastingNotesB;
	public static JTextArea textBrewGeneralNotesB;
	static JButton btnBrewDataEdit;
	private static JButton btnBrewDataSave;
	public static JCheckBox chckbxBrewInPlanningB;
	public static JCheckBox chckbxBrewInFermentingB;
	public static JCheckBox chckbxBrewInFiningB;
	public static JCheckBox chckbxBrewInMaturingB;
	public static JCheckBox chckbxBrewInBottlesB;
	public static JCheckBox chckbxBrewDrunkB;
	static JButton btnBrewDataDelete;
	private static JButton btnBrewDataCancel;
	
	/**
	 * Initialises the brew data panel so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		tabbedBrewDataPanel = new JPanel();
		tabbedBrewDataPanel.setBackground(Color.WHITE);
		tabbedBrewDataPanel.setLayout(new MigLayout("", "[105px:105px:105px][70px:70px, grow][grow][105px:105px:105px][30px:30px, grow][grow][120px:120px:120px][30px:30px, grow][grow]", "[][][30px:30px][10px:10px][][][][10px:10px][][][][10px:10px][][10px:10px][][55px:55,grow][15px:15px][]"));
		
		JLabel lblBrewRefB = new JLabel("Brew Ref:");
		tabbedBrewDataPanel.add(lblBrewRefB, "flowx,cell 0 0,alignx right");
			
		textBrewRefB = new JTextField();
		textBrewRefB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewRefB, "cell 1 0,growx");
		
		JLabel lblBrewNameB = new JLabel("Brew Name:");
		tabbedBrewDataPanel.add(lblBrewNameB, "flowx,cell 2 0,alignx right");
			
		textBrewNameB = new JTextField();
		textBrewNameB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewNameB, "cell 3 0 6,growx");
		
		JLabel lblBrewRecipeB = new JLabel("Recipe From:");
		tabbedBrewDataPanel.add(lblBrewRecipeB, "flowx,cell 0 1,alignx right");
			
		textBrewRecipeB = new JTextField();
		textBrewRecipeB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewRecipeB, "cell 1 1 8,growx");
		
		JLabel lblBrewDatePlannedB = new JLabel("Date Planned:");
		tabbedBrewDataPanel.add(lblBrewDatePlannedB, "flowx,cell 0 2,alignx right");
			
		chooserBrewDatePlannedB = new JDateChooser();
		chooserBrewDatePlannedB.setDisabled();
		tabbedBrewDataPanel.add(chooserBrewDatePlannedB, "cell 1 2 2,growx");
		
		JLabel lblBrewDateStartedB = new JLabel("Date Started:");
		tabbedBrewDataPanel.add(lblBrewDateStartedB, "flowx,cell 3 2,alignx right");
			
		chooserBrewDateStartedB = new JDateChooser();
		chooserBrewDateStartedB.setDisabled();
		tabbedBrewDataPanel.add(chooserBrewDateStartedB, "cell 4 2 2,growx");
		
		JLabel lblBrewDateBottledB = new JLabel("Date Bottled:");
		tabbedBrewDataPanel.add(lblBrewDateBottledB, "flowx,cell 6 2,alignx right");
			
		chooserBrewDateBottledB = new JDateChooser();
		chooserBrewDateBottledB.setDisabled();
		tabbedBrewDataPanel.add(chooserBrewDateBottledB, "cell 7 2 2,growx");
			
		JLabel lblBrewStartSGB = new JLabel("Start SG:");
		tabbedBrewDataPanel.add(lblBrewStartSGB, "flowx,cell 0 4,alignx right");
		
		textBrewStartSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartSGB, "cell 1 4 2,growx");
		
		JLabel lblBrewStartAdjustedSGB = new JLabel("Start Adjusted SG:");
		tabbedBrewDataPanel.add(lblBrewStartAdjustedSGB, "flowx,cell 3 4,alignx right");
			
		textBrewStartAdjustedSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewStartAdjustedSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartAdjustedSGB, "cell 4 4 2,growx");
		
		JLabel lblBrewEndSGB = new JLabel("End SG:");
		tabbedBrewDataPanel.add(lblBrewEndSGB, "flowx,cell 6 4,alignx right");
			
		textBrewEndSGB = new JFormattedTextField(new DecimalFormat("0.000"));
		textBrewEndSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewEndSGB, "cell 7 4 2,growx");
		
		JLabel lblBrewAimedABVB = new JLabel("Aimed ABV%:");
		tabbedBrewDataPanel.add(lblBrewAimedABVB, "flowx,cell 0 5,alignx right");
			
		textBrewAimedABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewAimedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewAimedABVB, "cell 1 5 2,growx");
		
		JLabel lblBrewFinalABVB = new JLabel("Final ABV%:");
		tabbedBrewDataPanel.add(lblBrewFinalABVB, "flowx,cell 3 5,alignx right");
			
		textBrewFinalABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalABVB, "cell 4 5 2,growx");
		
		JLabel lblBrewFinalAdjustedABVB = new JLabel("Final Adjusted ABV%:");
		tabbedBrewDataPanel.add(lblBrewFinalAdjustedABVB, "flowx,cell 6 5,alignx right");
			
		textBrewFinalAdjustedABVB = new JFormattedTextField(new DecimalFormat("##0.00"));
		textBrewFinalAdjustedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalAdjustedABVB, "cell 7 5 2,growx");
		
		JLabel lblBrewYeastB = new JLabel("Yeast:");
		tabbedBrewDataPanel.add(lblBrewYeastB, "flowx,cell 0 6,alignx right");
			
		textBrewYeastB = new JTextField();
		textBrewYeastB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewYeastB, "cell 1 6 8,growx");
		
		JLabel lblBrewVolumeMadeB = new JLabel("Volume Made:");
		tabbedBrewDataPanel.add(lblBrewVolumeMadeB, "flowx,cell 0 8,alignx right");
			
		textBrewVolumeMadeB = new JTextField();
		textBrewVolumeMadeB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewVolumeMadeB, "cell 1 8,growx");
		
		JLabel lblBrewVolumeMade2B = new JLabel("(gallons)");
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
		
		JLabel lblBrewColourB = new JLabel("Colour:");
		tabbedBrewDataPanel.add(lblBrewColourB, "flowx,cell 6 8,alignx right");
			
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
		tabbedBrewDataPanel.add(comboBrewColourB, "cell 7 8 2,growx");
		
		JLabel lblBrewNumberBottlesB = new JLabel("Number of Bottles:");
		tabbedBrewDataPanel.add(lblBrewNumberBottlesB, "flowx,cell 0 9,alignx right");
			
		textBrewNumberBottlesB = new JTextField();
		textBrewNumberBottlesB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewNumberBottlesB, "cell 1 9,growx");
		
		chckbxBrewInFiningB = new JCheckBox("In Fining");
		chckbxBrewInFiningB.setSelected(false);
		chckbxBrewInFiningB.setEnabled(false);
		chckbxBrewInFiningB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInFiningB, "cell 3 9,growx");
		
		chckbxBrewInMaturingB = new JCheckBox("In Maturing");
		chckbxBrewInMaturingB.setSelected(false);
		chckbxBrewInMaturingB.setEnabled(false);
		chckbxBrewInMaturingB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInMaturingB, "cell 4 9,growx");
		
		JLabel lblBrewThumbsB = new JLabel("Thumbs Up?");
		tabbedBrewDataPanel.add(lblBrewThumbsB, "flowx,cell 6 9,alignx right");
			
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
		tabbedBrewDataPanel.add(comboBrewThumbsB, "cell 7 9 2,growx");		
		
		chckbxBrewInBottlesB = new JCheckBox("In Bottles");
		chckbxBrewInBottlesB.setSelected(false);
		chckbxBrewInBottlesB.setEnabled(false);
		chckbxBrewInBottlesB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewInBottlesB, "cell 3 10,growx");
		
		chckbxBrewDrunkB = new JCheckBox("Drunk");
		chckbxBrewDrunkB.setSelected(false);
		chckbxBrewDrunkB.setEnabled(false);
		chckbxBrewDrunkB.setBackground(Color.WHITE);
		tabbedBrewDataPanel.add(chckbxBrewDrunkB, "cell 4 10,growx");
		
		JLabel lblBrewTastingNotesB = new JLabel("Tasting Notes:");
		tabbedBrewDataPanel.add(lblBrewTastingNotesB, "flowx,cell 0 12,alignx right");
			
		textBrewTastingNotesB = new JTextField();
		textBrewTastingNotesB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewTastingNotesB, "cell 1 12 8,growx");
		
		JLabel lblBrewGeneralNotesB = new JLabel("General Notes:");
		tabbedBrewDataPanel.add(lblBrewGeneralNotesB, "flowx,cell 0 14,alignx right");
		
		textBrewGeneralNotesB = new JTextArea();
		textBrewGeneralNotesB.setEditable(false);
		textBrewGeneralNotesB.setLineWrap(true);
		textBrewGeneralNotesB.setBackground(UIManager.getColor("Panel.background"));
		
		//ScrollPane for textBrewGeneralNotesB
	    JScrollPane BrewDataNotesScrollPane = new JScrollPane();
	    BrewDataNotesScrollPane.setViewportView(textBrewGeneralNotesB);
	    tabbedBrewDataPanel.add(BrewDataNotesScrollPane, "cell 1 14 8 2,grow");
		
		btnBrewDataEdit = new JButton("Edit");
		btnBrewDataEdit.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataEdit, "cell 0 17 2 1,growx");
		
		btnBrewDataDelete = new JButton("Delete Brew");
		btnBrewDataDelete.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataDelete, "cell 2 17,growx");
		
		btnBrewDataCancel = new JButton("Cancel");
		btnBrewDataCancel.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataCancel, "cell 6 17,growx");
			
		btnBrewDataSave = new JButton("Save / Update");
		btnBrewDataSave.setEnabled(false);
		tabbedBrewDataPanel.add(btnBrewDataSave, "cell 7 17 2 1,growx");
		
		//Add decimal format document listener
		textBrewStartSGB.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  String d = textBrewStartSGB.getText();
				  DecimalFormat df = new DecimalFormat("0.000");
				  textBrewStartSGB.setText(df.format(d));
			  }
			  public void removeUpdate(DocumentEvent e) {
			  
			  }
			  public void insertUpdate(DocumentEvent e) {
				  
			  }
		});
		
		textBrewStartAdjustedSGB.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  String d = textBrewStartAdjustedSGB.getText();
				  DecimalFormat df = new DecimalFormat("0.000");
				  textBrewStartAdjustedSGB.setText(df.format(d));
			  }
			  public void removeUpdate(DocumentEvent e) {
		  
			  }
			  public void insertUpdate(DocumentEvent e) {

			  }
		});
		
		textBrewEndSGB.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  String d = textBrewEndSGB.getText();
				  DecimalFormat df = new DecimalFormat("0.000");
				  textBrewEndSGB.setText(df.format(d));
			  }
			  public void removeUpdate(DocumentEvent e) {
			  
			  }
			  public void insertUpdate(DocumentEvent e) {

			  }
		});
		
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
				BrewPanel.tabbedBrewPane.setEnabledAt(5, false);
				InitialiseMenu.disableAllMenuButtons();
				textBrewNameB.setEditable(true);
				comboBrewColourB.setEnabled(true);
				textBrewRecipeB.setEditable(true);
				comboBrewThumbsB.setEnabled(true);
				chooserBrewDatePlannedB.setEnabled();
				chooserBrewDateStartedB.setEnabled();
				chooserBrewDateBottledB.setEnabled();
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
				BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
				InitialiseMenu.enableAllMenuButtons();
				textBrewNameB.setEditable(false);
				comboBrewColourB.setEnabled(false);
				textBrewRecipeB.setEditable(false);
				comboBrewThumbsB.setEnabled(false);
				chooserBrewDatePlannedB.setDisabled();
				chooserBrewDateStartedB.setDisabled();
				chooserBrewDateBottledB.setDisabled();
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
				if (chckbxBrewInPlanningB.isSelected() == false && chckbxBrewInFermentingB.isSelected() == false && chckbxBrewInFiningB.isSelected() == false && chckbxBrewInMaturingB.isSelected() == false && chckbxBrewInBottlesB.isSelected() == false && chckbxBrewDrunkB.isSelected() == false){
					JOptionPane.showMessageDialog(null,
						"You must select at least one state for the brew to be in.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
				} else {
					try {
						if(textBrewNumberBottlesB.getText().equals("") || textBrewNumberBottlesB == null){textBrewNumberBottlesB.setText("0");}
						textBrewNumberBottlesB.setText(textBrewNumberBottlesB.getText().replaceAll("[^0-9\\.]", ""));
						DBEngine.updateBrew();
						DBEngine.setTotalBrewCost(BrewDataPanel.textBrewRefB.getText(), BrewDataPanel.textBrewNumberBottlesB.getText());
						BrewCostPanel.setBrewTotalCostData();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"An error occurred updating data in the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
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
					BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
					InitialiseMenu.enableAllMenuButtons();
					textBrewNameB.setEditable(false);
					comboBrewColourB.setEnabled(false);
					textBrewRecipeB.setEditable(false);
					comboBrewThumbsB.setEnabled(false);
					chooserBrewDatePlannedB.setDisabled();
					chooserBrewDateStartedB.setDisabled();
					chooserBrewDateBottledB.setDisabled();
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
					BrewSearchPanel.initialiseTable();
					BrewSearchPanel.BrewScrollPane.setViewportView(BrewSearchPanel.BrewTable);
				}
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
							JOptionPane.showMessageDialog(null,
									"An error occurred deleting data from the database.\n" + MainWindow.DatabaseLocationFromIni + "\n\nEither:\n- The database doesn't exist.\n- You don't have permission to write to this location.\n- The database is invalid or corrupt.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
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
						BrewPanel.tabbedBrewPane.setEnabledAt(4, false);
						BrewPanel.tabbedBrewPane.setEnabledAt(5, true);
						InitialiseMenu.enableAllMenuButtons();
						textBrewNameB.setEditable(false);
						comboBrewColourB.setEnabled(false);
						textBrewRecipeB.setEditable(false);
						comboBrewThumbsB.setEnabled(false);
						chooserBrewDatePlannedB.setDisabled();
						chooserBrewDateStartedB.setDisabled();
						chooserBrewDateBottledB.setDisabled();
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
						BrewSearchPanel.initialiseTable();
						BrewSearchPanel.BrewScrollPane.setViewportView(BrewSearchPanel.BrewTable);
						clearBrewData();
						BrewPanel.tabbedBrewPane.setSelectedIndex(0);
				      				      
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    	//Nothing Happens
				      
				    }
				
			}
		});
		
	}
	
	/**
	 * Sets the data in the fields on the Brew Data tab to the currently selected brew.
	 */
	public static void setBrewData(){
		BrewPanel.btnPrintBrew.setEnabled(true);
		textBrewRefB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,0));
		textBrewNameB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,1));
		textBrewRecipeB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,5));
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,2).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,2).equals("")){
			chooserBrewDatePlannedB.setDate(null);
		} else {
			chooserBrewDatePlannedB.setDate(Dates.stringToDate((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,2)));
		};		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,3).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,3).equals("")){
			chooserBrewDateStartedB.setDate(null);
		} else{
			chooserBrewDateStartedB.setDate(Dates.stringToDate((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,3)));
		};
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,4).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,4).equals("")){
			chooserBrewDateBottledB.setDate(null);
		} else{
			chooserBrewDateBottledB.setDate(Dates.stringToDate((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,4)));
		};
		textBrewYeastB.setText((String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,6));
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,7).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,7).equals("")){
			textBrewStartSGB.setText("");
		}else {
			String unformattedtextBrewStartSGB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,7);
			Float unformattedfloatBrewStartSGB = Float.valueOf(unformattedtextBrewStartSGB);
			DecimalFormat SG = new DecimalFormat("0.000");
			textBrewStartSGB.setText(SG.format(unformattedfloatBrewStartSGB));
		};
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,8).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,8).equals("")){
			textBrewStartAdjustedSGB.setText("");
		}else {
			String unformattedtextBrewStartAdjustedSGB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,8);
			Float unformattedfloatBrewStartAdjustedSGB = Float.valueOf(unformattedtextBrewStartAdjustedSGB);
			DecimalFormat SG = new DecimalFormat("0.000");
			textBrewStartAdjustedSGB.setText(SG.format(unformattedfloatBrewStartAdjustedSGB));
		};
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,9).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,9).equals("")){
			textBrewEndSGB.setText("");
		}else {
			String unformattedtextBrewEndSGB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,9);
			Float unformattedfloatBrewEndSGB = Float.valueOf(unformattedtextBrewEndSGB);
			DecimalFormat SG = new DecimalFormat("0.000");
			textBrewEndSGB.setText(SG.format(unformattedfloatBrewEndSGB));
		};
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,10).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,10).equals("")){
			textBrewAimedABVB.setText("");
		}else {
			String unformattedtextBrewAimedABVB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,10);
			Float unformattedfloatBrewAimedABVB = Float.valueOf(unformattedtextBrewAimedABVB);
			DecimalFormat ABV = new DecimalFormat("##0.00");
			textBrewAimedABVB.setText(ABV.format(unformattedfloatBrewAimedABVB));
		};
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,11).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,11).equals("")){
			textBrewFinalABVB.setText("");
		}else {
			String unformattedtextBrewFinalABVB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,11);
			Float unformattedfloatBrewFinalABVB = Float.valueOf(unformattedtextBrewFinalABVB);
			DecimalFormat ABV = new DecimalFormat("##0.00");
			textBrewFinalABVB.setText(ABV.format(unformattedfloatBrewFinalABVB));
		};
		
		if(BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,12).equals(null) || BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,12).equals("")){
			textBrewFinalAdjustedABVB.setText("");
		}else {
			String unformattedtextBrewFinalAdjustedABVB = (String) BrewSearchPanel.BrewTable.getValueAt(BrewSearchPanel.BrewSearchSelectedRow,12);
			Float unformattedfloatBrewFinalAdjustedABVB = Float.valueOf(unformattedtextBrewFinalAdjustedABVB);
			DecimalFormat ABV = new DecimalFormat("##0.00");
			textBrewFinalAdjustedABVB.setText(ABV.format(unformattedfloatBrewFinalAdjustedABVB));
		};
		
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
	
	/**
	 * Clears all data from all fields on the brew data tab.
	 */
	public static void clearBrewData(){
		BrewPanel.btnPrintBrew.setEnabled(false);
		textBrewRefB.setText("");
		textBrewNameB.setText("");
		textBrewRecipeB.setText("");
		chooserBrewDatePlannedB.setDate(null);
		chooserBrewDateStartedB.setDate(null);
		chooserBrewDateBottledB.setDate(null);
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
	
	/**
	 * Converts a string to a boolean.
	 * 
	 * @param s String value (either 1 or 0).
	 * @return Returns the boolean value of the string.
	 */
	public static boolean stringToBool(String s) {
		  if (s.equals("1"))
		    return true;
		  if (s.equals("0"))
		    return false;
		  throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
		}


}