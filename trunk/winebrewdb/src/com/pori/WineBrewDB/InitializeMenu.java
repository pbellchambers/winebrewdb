package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class InitializeMenu extends MainWindow {
	
	public static JTextField txtSearch;
	public static JTextField txtCalculators;
	public static JTextField txtInformation;
	public static JLayeredPane ContentPane;
	public static JToggleButton btnBrew;
	public static JToggleButton btnRecipe;
	public static JToggleButton btnAlcohol;
	public static JToggleButton btnDilution;
	public static JToggleButton btnMeasures;
	public static JToggleButton btnSugarToSG;
	public static JToggleButton btnTemperatureAdjustedSG;
	public static JToggleButton btnDosages;
	public static JToggleButton btnFruitAcids;
	public static JToggleButton btnYeastStrains;
	
	public static void InitializeMenuMethod(){
		
		JMenuBar menuBar = new JMenuBar();
		MainWindow.WineBrewDBFrame.setJMenuBar(menuBar);
		
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
		MainWindow.WineBrewDBFrame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Menu Panel
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.LIGHT_GRAY);
		MainWindow.WineBrewDBFrame.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		MenuPanel.setLayout(new MigLayout("", "[200px:n,grow]", "[][][][][][][][][][][][][][][][grow]"));
		
		txtSearch = new JTextField();
		txtSearch.setEditable(false);
		txtSearch.setText("Search");
		MenuPanel.add(txtSearch, "cell 0 0,growx");
		txtSearch.setColumns(10);
		
		btnBrew = new JToggleButton("Brew");
		MenuPanel.add(btnBrew, "cell 0 1,growx");
		
		btnRecipe = new JToggleButton("Recipe");
		MenuPanel.add(btnRecipe, "cell 0 2,growx");
		
		txtCalculators = new JTextField();
		txtCalculators.setEditable(false);
		txtCalculators.setText("Calculators");
		MenuPanel.add(txtCalculators, "cell 0 4,growx");
		txtCalculators.setColumns(10);
		
		btnAlcohol = new JToggleButton("Alcohol %");
		MenuPanel.add(btnAlcohol, "cell 0 5,growx");
		
		btnDilution = new JToggleButton("Dilution");
		MenuPanel.add(btnDilution, "cell 0 6,growx");
		
		btnMeasures = new JToggleButton("Measures");
		MenuPanel.add(btnMeasures, "cell 0 7,growx");
		
		btnSugarToSG = new JToggleButton("Sugar to SG");
		MenuPanel.add(btnSugarToSG, "cell 0 8,growx");
		
		btnTemperatureAdjustedSG = new JToggleButton("Temperature Adjusted SG");
		MenuPanel.add(btnTemperatureAdjustedSG, "cell 0 9,growx");
		
		txtInformation = new JTextField();
		txtInformation.setEditable(false);
		txtInformation.setText("Information");
		MenuPanel.add(txtInformation, "cell 0 11,growx");
		txtInformation.setColumns(10);
		
		btnDosages = new JToggleButton("Dosages");
		MenuPanel.add(btnDosages, "cell 0 12,growx");
		
		btnFruitAcids = new JToggleButton("Fruit Acids");
		MenuPanel.add(btnFruitAcids, "cell 0 13,growx");
		
		btnYeastStrains = new JToggleButton("Yeast Strains");
		MenuPanel.add(btnYeastStrains, "cell 0 14,growx");
		

		//Menu Button Actions
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnBrew.setSelected(true);
				BrewPanel.InitializePanel();
				BrewPanel.BrewPanel.setVisible(true);
			}
		});
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnRecipe.setSelected(true);
				RecipePanel.InitializePanel();
				RecipePanel.RecipePanel.setVisible(true);
			}
		});
		btnAlcohol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnAlcohol.setSelected(true);
				AlcoholPanel.InitializePanel();
				AlcoholPanel.AlcoholPanel.setVisible(true);
			}
		});
		btnDilution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnDilution.setSelected(true);
				DilutionPanel.InitializePanel();
				DilutionPanel.DilutionPanel.setVisible(true);
			}
		});
		btnMeasures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnMeasures.setSelected(true);
				MeasuresPanel.InitializePanel();
				MeasuresPanel.MeasuresPanel.setVisible(true);
			}
		});
		btnSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnSugarToSG.setSelected(true);
				SugarToSGPanel.InitializePanel();
				SugarToSGPanel.SugarToSGPanel.setVisible(true);
			}
		});
		btnTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnTemperatureAdjustedSG.setSelected(true);
				TemperatureAdjustedSGPanel.InitializePanel();
				TemperatureAdjustedSGPanel.TemperatureAdjustedSGPanel.setVisible(true);
			}
		});
		btnDosages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnDosages.setSelected(true);
				DosagesPanel.InitializePanel();
				DosagesPanel.DosagesPanel.setVisible(true);
			}
		});
		btnFruitAcids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnFruitAcids.setSelected(true);
				FruitAcidsPanel.InitializePanel();
				FruitAcidsPanel.FruitAcidsPanel.setVisible(true);
			}
		});
		btnYeastStrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnYeastStrains.setSelected(true);
				YeastStrainsPanel.InitializePanel();
				YeastStrainsPanel.YeastStrainsPanel.setVisible(true);
			}
		});
		
	}
	
	public static void DeinitializeAllPanels(){
		BrewPanel.DeInitializePanel();
		RecipePanel.DeInitializePanel();
		AlcoholPanel.DeInitializePanel();
		DilutionPanel.DeInitializePanel();
		MeasuresPanel.DeInitializePanel();
		SugarToSGPanel.DeInitializePanel();
		TemperatureAdjustedSGPanel.DeInitializePanel();
		DosagesPanel.DeInitializePanel();
		FruitAcidsPanel.DeInitializePanel();
		YeastStrainsPanel.DeInitializePanel();
		
		btnBrew.setSelected(false);
		btnRecipe.setSelected(false);
		btnAlcohol.setSelected(false);
		btnDilution.setSelected(false);
		btnMeasures.setSelected(false);
		btnSugarToSG.setSelected(false);
		btnTemperatureAdjustedSG.setSelected(false);
		btnDosages.setSelected(false);
		btnFruitAcids.setSelected(false);
		btnYeastStrains.setSelected(false);
	};
	
}