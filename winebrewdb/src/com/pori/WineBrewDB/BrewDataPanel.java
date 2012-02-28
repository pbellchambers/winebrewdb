package com.pori.WineBrewDB;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
	public static JTextField textBrewThumbsB;
	public static JLabel lblBrewDatePlannedB;
	public static JTextField textBrewDatePlannedB;
	public static JLabel lblBrewDateStartedB;
	public static JTextField textBrewDateStartedB;
	public static JLabel lblBrewDateBottledB;
	public static JTextField textBrewDateBottledB;
	public static JLabel lblBrewYeastB;
	public static JTextField textBrewYeastB;
	public static JLabel lblBrewStartSGB;
	public static JTextField textBrewStartSGB;
	public static JLabel lblBrewStartAdjustedSGB;
	public static JTextField textBrewStartAdjustedSGB;
	public static JLabel lblBrewEndSGB;
	public static JTextField textBrewEndSGB;
	public static JLabel lblBrewAimedABVB;
	public static JTextField textBrewAimedABVB;
	public static JLabel lblBrewFinalABVB;
	public static JTextField textBrewFinalABVB;
	public static JLabel lblBrewFinalAdjustedABVB;
	public static JTextField textBrewFinalAdjustedABVB;
	
	public static void InitializePanel(){
		
		tabbedBrewDataPanel = new JPanel();
		tabbedBrewDataPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewDataPanel.setLayout(new MigLayout("", "[75px:75px:75px][60px:60px, grow][75px:75px:75px][60px:60px, grow][75px:75px:75px][60px:60px, grow][75px:75px:75px][60px:60px, grow]", "[][][20px:20px][][20px:20px][][][20px:20px][][][20px:20px][][20px:20px][][20px:20px][][grow]"));
		
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
		comboBrewColourB.setBackground(Color.WHITE);
		comboBrewColourB.setModel(new DefaultComboBoxModel<String>(new String[] {"Red", "White", "Rosé", "Other"}));
		comboBrewColourB.setEnabled(false);
		tabbedBrewDataPanel.add(comboBrewColourB, "cell 7 0,growx");
		
		lblBrewRecipeB = new JLabel("Recipe From:");
		tabbedBrewDataPanel.add(lblBrewRecipeB, "flowx,cell 0 1,alignx right");
			
		textBrewRecipeB = new JTextField();
		textBrewRecipeB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewRecipeB, "cell 1 1 5,growx");
		
		lblBrewThumbsB = new JLabel("Thumbs Up?");
		tabbedBrewDataPanel.add(lblBrewThumbsB, "flowx,cell 6 1,alignx right");
			
		textBrewThumbsB = new JTextField();
		textBrewThumbsB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewThumbsB, "cell 7 1,growx");
		
		lblBrewDatePlannedB = new JLabel("Date Planned:");
		tabbedBrewDataPanel.add(lblBrewDatePlannedB, "flowx,cell 0 3,alignx right");
			
		textBrewDatePlannedB = new JTextField();
		textBrewDatePlannedB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDatePlannedB, "cell 1 3,growx");
		
		lblBrewDateStartedB = new JLabel("Date Started:");
		tabbedBrewDataPanel.add(lblBrewDateStartedB, "flowx,cell 2 3,alignx right");
			
		textBrewDateStartedB = new JTextField();
		textBrewDateStartedB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDateStartedB, "cell 3 3,growx");
		
		lblBrewDateBottledB = new JLabel("Date Bottled:");
		tabbedBrewDataPanel.add(lblBrewDateBottledB, "flowx,cell 4 3,alignx right");
			
		textBrewDateBottledB = new JTextField();
		textBrewDateBottledB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewDateBottledB, "cell 5 3,growx");
		
		lblBrewYeastB = new JLabel("Yeast:");
		tabbedBrewDataPanel.add(lblBrewYeastB, "flowx,cell 6 3,alignx right");
			
		textBrewYeastB = new JTextField();
		textBrewYeastB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewYeastB, "cell 7 3,growx");
		
		lblBrewStartSGB = new JLabel("Start SG:");
		tabbedBrewDataPanel.add(lblBrewStartSGB, "flowx,cell 0 5,alignx right");
			
		textBrewStartSGB = new JTextField();
		textBrewStartSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartSGB, "cell 1 5,growx");
		
		lblBrewStartAdjustedSGB = new JLabel("Start Adjusted SG:");
		tabbedBrewDataPanel.add(lblBrewStartAdjustedSGB, "flowx,cell 2 5,alignx right");
			
		textBrewStartAdjustedSGB = new JTextField();
		textBrewStartAdjustedSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewStartAdjustedSGB, "cell 3 5,growx");
		
		lblBrewEndSGB = new JLabel("End SG:");
		tabbedBrewDataPanel.add(lblBrewEndSGB, "flowx,cell 4 5,alignx right");
			
		textBrewEndSGB = new JTextField();
		textBrewEndSGB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewEndSGB, "cell 5 5,growx");
		
		lblBrewAimedABVB = new JLabel("AimedABV:");
		tabbedBrewDataPanel.add(lblBrewAimedABVB, "flowx,cell 0 6,alignx right");
			
		textBrewAimedABVB = new JTextField();
		textBrewAimedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewAimedABVB, "cell 1 6,growx");
		
		lblBrewFinalABVB = new JLabel("Final ABV:");
		tabbedBrewDataPanel.add(lblBrewFinalABVB, "flowx,cell 2 6,alignx right");
			
		textBrewFinalABVB = new JTextField();
		textBrewFinalABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalABVB, "cell 3 6,growx");
		
		lblBrewFinalAdjustedABVB = new JLabel("Final Adjusted ABV:");
		tabbedBrewDataPanel.add(lblBrewFinalAdjustedABVB, "flowx,cell 4 6,alignx right");
			
		textBrewFinalAdjustedABVB = new JTextField();
		textBrewFinalAdjustedABVB.setEditable(false);
		tabbedBrewDataPanel.add(textBrewFinalAdjustedABVB, "cell 5 6,growx");
	}

}