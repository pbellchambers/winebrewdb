package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	public static JTextField textBrewName;
	public static JTextField textRecipeFrom;
	public static JTextField textNotes;
	public static JTextField textField_3;
	public static JTextField textYeast;
	public static JComboBox<String> comboColour;
	public static JLabel lblThumbs;
	public static JComboBox<String> comboThumbs;
	public static JCheckBox chckbxInPlanning;
	public static JCheckBox chckbxInFining;
	public static JCheckBox chckbxInFermenting;
	public static JCheckBox chckbxInBottles;
	public static JCheckBox chckbxDrunk;
	public static JButton btnShowEntireTable;
	public static JButton btnSearch;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(new MigLayout("", "[75px:75px,grow][][][][][][][]", "[][][][35px:35px:35px]"));
		
		JLabel lblBrewName = new JLabel("Brew Name:");
		add(lblBrewName, "flowx,cell 0 0,alignx right");
		
		textBrewName = new JTextField();
		add(textBrewName, "cell 1 0,growx");
		textBrewName.setColumns(10);
		
		JLabel lblRecipeFrom = new JLabel("Recipe From:");
		add(lblRecipeFrom, "flowx,cell 2 0");
		
		textRecipeFrom = new JTextField();
		add(textRecipeFrom, "cell 3 0,growx");
		textRecipeFrom.setColumns(10);
		
		JLabel lblYeast = new JLabel("Yeast:");
		add(lblYeast, "flowx,cell 4 0");
		
		textYeast = new JTextField();
		add(textYeast, "cell 5 0,growx");
		textYeast.setColumns(10);
		
		JLabel lblNotes = new JLabel("Notes:");
		add(lblNotes, "flowx,cell 0 1");
		
		textNotes = new JTextField();
		add(textNotes, "cell 1 1,growx");
		textNotes.setColumns(10);
		
		JLabel lblTastingNotes = new JLabel("Tasting Notes:");
		add(lblTastingNotes, "flowx,cell 2 1");
		
		textField_3 = new JTextField();
		add(textField_3, "cell 3 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblColour = new JLabel("Colour:");
		add(lblColour, "flowx,cell 4 1");
		
		comboColour = new JComboBox<String>();
		comboColour.setBackground(UIManager.getColor("Panel.background"));
		comboColour.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "Red", "White", "Ros\u00E9", "Other"}));
		add(comboColour, "cell 5 1,growx");
		
		lblThumbs = new JLabel("Thumbs:");
		add(lblThumbs, "flowx,cell 0 2");
		
		chckbxInPlanning = new JCheckBox("In Planning");
		chckbxInPlanning.setSelected(true);
		add(chckbxInPlanning, "cell 6 0,growx");
		
		chckbxInFining = new JCheckBox("In Fining");
		chckbxInFining.setSelected(true);
		add(chckbxInFining, "cell 6 1,growx");
		
		chckbxInFermenting = new JCheckBox("In Fermenting");
		chckbxInFermenting.setSelected(true);
		add(chckbxInFermenting, "cell 7 0,growx");
		
		chckbxInBottles = new JCheckBox("In Bottles");
		chckbxInBottles.setSelected(true);
		add(chckbxInBottles, "cell 7 1,growx");
		
		comboThumbs = new JComboBox<String>();
		comboThumbs.setBackground(Color.WHITE);
		comboThumbs.setModel(new DefaultComboBoxModel<String>(new String[] {"Any", "Up", "Middle", "Down"}));
		add(comboThumbs, "cell 1 2,growx");
		
		chckbxDrunk = new JCheckBox("Drunk");
		chckbxDrunk.setSelected(true);
		add(chckbxDrunk, "cell 7 2,growx");
		
		btnShowEntireTable = new JButton("Show Entire Table");
		add(btnShowEntireTable, "cell 0 3 2 1,growx,aligny bottom");
		
		btnSearch = new JButton("Search");
		add(btnSearch, "cell 6 3 2 1,growx,aligny bottom");

	}

}
