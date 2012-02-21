package com.pori.WineBrewDB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;

public class MainWindow {

	private JFrame WineBrewDB;
	private JTextField txtSearch;
	private JTextField txtCalculators;
	private JTextField txtInformation;
	private JTextField txtBrewpanel;
	private JTextField txtRecipepanel;
	private JTextField txtAlcoholpanel;
	private JTextField txtDilutionpanel;
	private JTextField txtMeasurespanel;
	private JTextField txtSugartosgpanel;
	private JTextField txtTemperatureadjustedsgpanel;
	private JTextField txtDosagespanel;
	private JTextField txtFruitacidspanel;
	private JTextField txtYeaststrainspanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.WineBrewDB.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		WineBrewDB = new JFrame();
		WineBrewDB.setTitle("WineBrewDB");
		WineBrewDB.setBounds(100, 100, 1024, 720);
		WineBrewDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		WineBrewDB.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		final JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"WineBrewDB - vAlpha0.0.0 \n\n (C) Copyright Paul Bellchambers",
						"About",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		);

		mnHelp.add(mntmAbout);
		WineBrewDB.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Menu Panel
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.LIGHT_GRAY);
		WineBrewDB.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		MenuPanel.setLayout(new MigLayout("", "[200px:n,grow]", "[][][][][][][][][][][][][][][][grow]"));
		
		txtSearch = new JTextField();
		txtSearch.setEditable(false);
		txtSearch.setText("Search");
		MenuPanel.add(txtSearch, "cell 0 0,growx");
		txtSearch.setColumns(10);
		
		JButton btnBrew = new JButton("Brew");
		MenuPanel.add(btnBrew, "cell 0 1,growx");
		
		JButton btnRecipe = new JButton("Recipe");
		MenuPanel.add(btnRecipe, "cell 0 2,growx");
		
		txtCalculators = new JTextField();
		txtCalculators.setEditable(false);
		txtCalculators.setText("Calculators");
		MenuPanel.add(txtCalculators, "cell 0 4,growx");
		txtCalculators.setColumns(10);
		
		JButton btnAlcohol = new JButton("Alcohol %");
		MenuPanel.add(btnAlcohol, "cell 0 5,growx");
		
		JButton btnDilution = new JButton("Dilution");
		MenuPanel.add(btnDilution, "cell 0 6,growx");
		
		JButton btnMeasures = new JButton("Measures");
		MenuPanel.add(btnMeasures, "cell 0 7,growx");
		
		JButton btnSugarToSg = new JButton("Sugar to SG");
		MenuPanel.add(btnSugarToSg, "cell 0 8,growx");
		
		JButton btnTemperatureAdjustedSg = new JButton("Temperature Adjusted SG");
		MenuPanel.add(btnTemperatureAdjustedSg, "cell 0 9,growx");
		
		txtInformation = new JTextField();
		txtInformation.setEditable(false);
		txtInformation.setText("Information");
		MenuPanel.add(txtInformation, "cell 0 11,growx");
		txtInformation.setColumns(10);
		
		JButton btnDosages = new JButton("Dosages");
		MenuPanel.add(btnDosages, "cell 0 12,growx");
		
		JButton btnFruitAcids = new JButton("Fruit Acids");
		MenuPanel.add(btnFruitAcids, "cell 0 13,growx");
		
		JButton btnYeastStrains = new JButton("Yeast Strains");
		MenuPanel.add(btnYeastStrains, "cell 0 14,growx");
		
		JLayeredPane ContentPane = new JLayeredPane();
		WineBrewDB.getContentPane().add(ContentPane, "cell 0 0,grow");
		ContentPane.setLayout(null);
		
		//Brew Panel
		final JPanel BrewPanel = new JPanel();
		BrewPanel.setBackground(Color.WHITE);
		BrewPanel.setVisible(false);
		BrewPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(BrewPanel);
		
		txtBrewpanel = new JTextField();
		txtBrewpanel.setText("BrewPanel");
		BrewPanel.add(txtBrewpanel);
		txtBrewpanel.setColumns(10);
		
		//Recipe Panel
		final JPanel RecipePanel = new JPanel();
		RecipePanel.setVisible(false);
		RecipePanel.setBackground(Color.WHITE);
		RecipePanel.setBounds(0, 0, 776, 647);
		ContentPane.add(RecipePanel);
		
		txtRecipepanel = new JTextField();
		txtRecipepanel.setText("RecipePanel");
		RecipePanel.add(txtRecipepanel);
		txtRecipepanel.setColumns(10);
		
		//Alcohol Panel
		final JPanel AlcoholPanel = new JPanel();
		AlcoholPanel.setBackground(Color.WHITE);
		AlcoholPanel.setVisible(false);
		AlcoholPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(AlcoholPanel);
		
		txtAlcoholpanel = new JTextField();
		txtAlcoholpanel.setText("AlcoholPanel");
		AlcoholPanel.add(txtAlcoholpanel);
		txtAlcoholpanel.setColumns(10);
		
		//Dilution Panel
		final JPanel DilutionPanel = new JPanel();
		DilutionPanel.setBackground(Color.WHITE);
		DilutionPanel.setVisible(false);
		DilutionPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(DilutionPanel);
		
		txtDilutionpanel = new JTextField();
		txtDilutionpanel.setText("DilutionPanel");
		DilutionPanel.add(txtDilutionpanel);
		txtDilutionpanel.setColumns(10);
		
		//Measures Panel
		final JPanel MeasuresPanel = new JPanel();
		MeasuresPanel.setBackground(Color.WHITE);
		MeasuresPanel.setVisible(false);
		MeasuresPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(MeasuresPanel);
		
		txtMeasurespanel = new JTextField();
		txtMeasurespanel.setText("MeasuresPanel");
		MeasuresPanel.add(txtMeasurespanel);
		txtMeasurespanel.setColumns(10);
		
		//SugarToSG Panel
		final JPanel SugarToSGPanel = new JPanel();
		SugarToSGPanel.setBackground(Color.WHITE);
		SugarToSGPanel.setVisible(false);
		SugarToSGPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(SugarToSGPanel);
		
		txtSugartosgpanel = new JTextField();
		txtSugartosgpanel.setText("SugarToSGPanel");
		SugarToSGPanel.add(txtSugartosgpanel);
		txtSugartosgpanel.setColumns(10);
		
		//TemperatureAdjustedSG Panel
		final JPanel TemperatureAdjustedSGPanel = new JPanel();
		TemperatureAdjustedSGPanel.setBackground(Color.WHITE);
		TemperatureAdjustedSGPanel.setVisible(false);
		TemperatureAdjustedSGPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(TemperatureAdjustedSGPanel);
		
		txtTemperatureadjustedsgpanel = new JTextField();
		txtTemperatureadjustedsgpanel.setText("TemperatureAdjustedSGPanel");
		TemperatureAdjustedSGPanel.add(txtTemperatureadjustedsgpanel);
		txtTemperatureadjustedsgpanel.setColumns(10);
		
		//Dosages Panel
		final JPanel DosagesPanel = new JPanel();
		DosagesPanel.setBackground(Color.WHITE);
		DosagesPanel.setVisible(false);
		DosagesPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(DosagesPanel);
		
		txtDosagespanel = new JTextField();
		txtDosagespanel.setText("DosagesPanel");
		DosagesPanel.add(txtDosagespanel);
		txtDosagespanel.setColumns(10);
		
		//FruitAcids Panel
		final JPanel FruitAcidsPanel = new JPanel();
		FruitAcidsPanel.setBackground(Color.WHITE);
		FruitAcidsPanel.setVisible(false);
		FruitAcidsPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(FruitAcidsPanel);
		
		txtFruitacidspanel = new JTextField();
		txtFruitacidspanel.setText("FruitAcidsPanel");
		FruitAcidsPanel.add(txtFruitacidspanel);
		txtFruitacidspanel.setColumns(10);
		
		//YeastStrains Panel
		final JPanel YeastStrainsPanel = new JPanel();
		YeastStrainsPanel.setBackground(Color.WHITE);
		YeastStrainsPanel.setVisible(false);
		YeastStrainsPanel.setBounds(0, 0, 776, 647);
		ContentPane.add(YeastStrainsPanel);
		
		txtYeaststrainspanel = new JTextField();
		txtYeaststrainspanel.setText("YeastStrainsPanel");
		YeastStrainsPanel.add(txtYeaststrainspanel);
		txtYeaststrainspanel.setColumns(10);
		
		//Button Actions
		
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipePanel.setVisible(false);
				AlcoholPanel.setVisible(false);
				DilutionPanel.setVisible(false);
				MeasuresPanel.setVisible(false);
				SugarToSGPanel.setVisible(false);
				TemperatureAdjustedSGPanel.setVisible(false);
				DosagesPanel.setVisible(false);
				FruitAcidsPanel.setVisible(false);
				YeastStrainsPanel.setVisible(false);
				BrewPanel.setVisible(true);
			}
		});
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlcoholPanel.setVisible(false);
				DilutionPanel.setVisible(false);
				MeasuresPanel.setVisible(false);
				SugarToSGPanel.setVisible(false);
				TemperatureAdjustedSGPanel.setVisible(false);
				DosagesPanel.setVisible(false);
				FruitAcidsPanel.setVisible(false);
				YeastStrainsPanel.setVisible(false);
				BrewPanel.setVisible(false);
				RecipePanel.setVisible(true);
			}
		});
	}
}
