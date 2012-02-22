package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	public static JButton btnBrew;
	public static JButton btnRecipe;
	public static JButton btnAlcohol;
	public static JButton btnDilution;
	public static JButton btnMeasures;
	public static JButton btnSugarToSG;
	public static JButton btnTemperatureAdjustedSG;
	public static JButton btnDosages;
	public static JButton btnFruitAcids;
	public static JButton btnYeastStrains;
	
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
		
		btnBrew = new JButton("Brew");
		MenuPanel.add(btnBrew, "cell 0 1,growx");
		
		btnRecipe = new JButton("Recipe");
		MenuPanel.add(btnRecipe, "cell 0 2,growx");
		
		txtCalculators = new JTextField();
		txtCalculators.setEditable(false);
		txtCalculators.setText("Calculators");
		MenuPanel.add(txtCalculators, "cell 0 4,growx");
		txtCalculators.setColumns(10);
		
		btnAlcohol = new JButton("Alcohol %");
		MenuPanel.add(btnAlcohol, "cell 0 5,growx");
		
		btnDilution = new JButton("Dilution");
		MenuPanel.add(btnDilution, "cell 0 6,growx");
		
		btnMeasures = new JButton("Measures");
		MenuPanel.add(btnMeasures, "cell 0 7,growx");
		
		btnSugarToSG = new JButton("Sugar to SG");
		MenuPanel.add(btnSugarToSG, "cell 0 8,growx");
		
		btnTemperatureAdjustedSG = new JButton("Temperature Adjusted SG");
		MenuPanel.add(btnTemperatureAdjustedSG, "cell 0 9,growx");
		
		txtInformation = new JTextField();
		txtInformation.setEditable(false);
		txtInformation.setText("Information");
		MenuPanel.add(txtInformation, "cell 0 11,growx");
		txtInformation.setColumns(10);
		
		btnDosages = new JButton("Dosages");
		MenuPanel.add(btnDosages, "cell 0 12,growx");
		
		btnFruitAcids = new JButton("Fruit Acids");
		MenuPanel.add(btnFruitAcids, "cell 0 13,growx");
		
		btnYeastStrains = new JButton("Yeast Strains");
		MenuPanel.add(btnYeastStrains, "cell 0 14,growx");
		
		ContentPane = new JLayeredPane();
		MainWindow.WineBrewDBFrame.getContentPane().add(ContentPane, "cell 0 0,grow");
		ContentPane.setLayout(null);
		
		
		//Menu Button Actions
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				BrewPanel.BrewPanel.setVisible(true);
			}
		});
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				RecipePanel.RecipePanel.setVisible(true);
			}
		});
		btnAlcohol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				AlcoholPanel.AlcoholPanel.setVisible(true);
			}
		});
		btnDilution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				DilutionPanel.DilutionPanel.setVisible(true);
			}
		});
		btnMeasures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				MeasuresPanel.MeasuresPanel.setVisible(true);
			}
		});
		btnSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				SugarToSGPanel.SugarToSGPanel.setVisible(true);
			}
		});
		btnTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				TemperatureAdjustedSGPanel.TemperatureAdjustedSGPanel.setVisible(true);
			}
		});
		btnDosages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				DosagesPanel.DosagesPanel.setVisible(true);
			}
		});
		btnFruitAcids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				FruitAcidsPanel.FruitAcidsPanel.setVisible(true);
			}
		});
		btnYeastStrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelsInvisible();
				YeastStrainsPanel.YeastStrainsPanel.setVisible(true);
			}
		});
		
	}
	
	public static void setAllPanelsInvisible(){
		BrewPanel.BrewPanel.setVisible(false);
		RecipePanel.RecipePanel.setVisible(false);
		AlcoholPanel.AlcoholPanel.setVisible(false);
		DilutionPanel.DilutionPanel.setVisible(false);
		MeasuresPanel.MeasuresPanel.setVisible(false);
		SugarToSGPanel.SugarToSGPanel.setVisible(false);
		TemperatureAdjustedSGPanel.TemperatureAdjustedSGPanel.setVisible(false);
		DosagesPanel.DosagesPanel.setVisible(false);
		FruitAcidsPanel.FruitAcidsPanel.setVisible(false);
		YeastStrainsPanel.YeastStrainsPanel.setVisible(false);
	};
	
}