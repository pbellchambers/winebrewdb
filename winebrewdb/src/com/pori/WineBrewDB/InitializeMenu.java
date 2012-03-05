package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class InitializeMenu extends MainWindow {
	
	public static JTextField txtDatabase;
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
	public static String WineBrewDBVersion;
	private static JMenu mnLAF;
	private static JRadioButtonMenuItem radioSystemLAF;
	private static JRadioButtonMenuItem radioJavaLAF;
	private static JPanel MenuPanel;
	private static JMenuBar menuBar;
	private static JMenu mnFile;
	private static JMenu mnDatabase;
	private static JMenu mnCalculators;
	private static JMenu mnInformation;
	private static JMenu mnHelp;
	
	public static void InitializeTopMenuMethod(){
		
		WineBrewDBVersion = "v0.7.2";
		
		//Top Menu Bar
		menuBar = new JMenuBar();
		MainWindow.WineBrewDBFrame.setJMenuBar(menuBar);
		
		//TODO: Finish menu bar
		//File Menu
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		final JMenuItem mntmNew = new JMenuItem("New Database");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				}
			}
			
		);
		mnFile.add(mntmNew);
		
		final JMenuItem mntmLoad = new JMenuItem("Load Database");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				}
			}
			
		);
		mnFile.add(mntmLoad);
		
		final JMenuItem mntmSaveAs = new JMenuItem("Save Database As");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				}
			}
			
		);
		mnFile.add(mntmSaveAs);
		
		mnFile.addSeparator();
		
		mnLAF = new JMenu("Look and Feel");
		mnFile.add(mnLAF);
		
		radioSystemLAF = new JRadioButtonMenuItem("System");
		radioSystemLAF.setSelected(true);
		radioSystemLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
					   UIManager.setLookAndFeel(
					        UIManager.getSystemLookAndFeelClassName());
					}
					catch(Exception ex){
					 ex.printStackTrace();
					}
				SwingUtilities.updateComponentTreeUI(MainWindow.WineBrewDBFrame);
				radioSystemLAF.setSelected(true);
				radioJavaLAF.setSelected(false);
				}
			}
			
		);
		mnLAF.add(radioSystemLAF);
		
		radioJavaLAF = new JRadioButtonMenuItem("Java");
		radioJavaLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
						UIManager.setLookAndFeel(
				            UIManager.getCrossPlatformLookAndFeelClassName());
					}
					catch(Exception ex){
					 ex.printStackTrace();
					}
				SwingUtilities.updateComponentTreeUI(MainWindow.WineBrewDBFrame);
				radioSystemLAF.setSelected(false);
				radioJavaLAF.setSelected(true);
				}
			}	
		);
		mnLAF.add(radioJavaLAF);
		
		mnFile.addSeparator();
		
		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.WineBrewDBFrame.dispose();
				}
			}
			
		);
		mnFile.add(mntmExit);
		
				
				
		//Database Menu
		mnDatabase = new JMenu("Data");
		menuBar.add(mnDatabase);
		
		final JMenuItem mntmBrew = new JMenuItem("Brew");
		mntmBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnBrew.setSelected(true);
				BrewPanel.InitializePanel();
				BrewPanel.BrewPanel.setVisible(true);
				}
			}
			
		);
		mnDatabase.add(mntmBrew);
		
		final JMenuItem mntmRecipe = new JMenuItem("Recipe");
		mntmRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnRecipe.setSelected(true);
				RecipePanel.InitializePanel();
				RecipePanel.RecipePanel.setVisible(true);				
				}
			}
			
		);
		mnDatabase.add(mntmRecipe);
				
				
		//Calculators Menu
		mnCalculators = new JMenu("Calculators");
		menuBar.add(mnCalculators);
		
		final JMenuItem mntmAlcohol = new JMenuItem("Alcohol %");
		mntmAlcohol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnAlcohol.setSelected(true);
				AlcoholPanel.InitializePanel();
				AlcoholPanel.AlcoholPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmAlcohol);
		
		final JMenuItem mntmDilution = new JMenuItem("Dilution");
		mntmDilution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnDilution.setSelected(true);
				DilutionPanel.InitializePanel();
				DilutionPanel.DilutionPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmDilution);
		
		final JMenuItem mntmMeasures = new JMenuItem("Measures");
		mntmMeasures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnMeasures.setSelected(true);
				MeasuresPanel.InitializePanel();
				MeasuresPanel.MeasuresPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmMeasures);
		
		final JMenuItem mntmSugarToSG = new JMenuItem("Sugar to SG");
		mntmSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnSugarToSG.setSelected(true);
				SugarToSGPanel.InitializePanel();
				SugarToSGPanel.SugarToSGPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmSugarToSG);
		
		final JMenuItem mntmTemperatureAdjustedSG = new JMenuItem("Temperature Adjusted SG");
		mntmTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnTemperatureAdjustedSG.setSelected(true);
				TemperatureAdjustedSGPanel.InitializePanel();
				TemperatureAdjustedSGPanel.TemperatureAdjustedSGPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmTemperatureAdjustedSG);
				
				
		//Information Menu
		mnInformation = new JMenu("Information");
		menuBar.add(mnInformation);
		
		final JMenuItem mntmDosages = new JMenuItem("Dosages");
		mntmDosages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnDosages.setSelected(true);
				DosagesPanel.InitializePanel();
				DosagesPanel.DosagesPanel.setVisible(true);				
				}
			}
			
		);
		mnInformation.add(mntmDosages);
		
		final JMenuItem mntmFruitAcids = new JMenuItem("Fruit Acids");
		mntmFruitAcids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnFruitAcids.setSelected(true);
				FruitAcidsPanel.InitializePanel();
				FruitAcidsPanel.FruitAcidsPanel.setVisible(true);				
				}
			}
			
		);
		mnInformation.add(mntmFruitAcids);
		
		final JMenuItem mntmYeastStrains = new JMenuItem("Yeast Strains");
		mntmYeastStrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				btnYeastStrains.setSelected(true);
				YeastStrainsPanel.InitializePanel();
				YeastStrainsPanel.YeastStrainsPanel.setVisible(true);				
				}
			}
			
		);
		mnInformation.add(mntmYeastStrains);
		
		
		//Help Menu
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		final JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"WineBrewDB - " + WineBrewDBVersion + "\nhttp://code.google.com/p/winebrewdb/\n\nLicensed under The MIT License\nCopyright (c) 2012 Paul Bellchambers\n\nPermission is hereby granted, free of charge, to any person obtaining a copy of this \nsoftware and associated documentation files (the \"Software\"), to deal in the Software \nwithout restriction, including without limitation the rights to use, copy, modify, merge, \npublish, distribute, sublicense, and/or sell copies of the Software, and to permit persons \nto whom the Software is furnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all copies \nor substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, \nINCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR \nPURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE \nFOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR \nOTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER \nDEALINGS IN THE SOFTWARE.",
						"About",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		);
		mnHelp.add(mntmAbout);
		
		final JMenuItem mntmAck = new JMenuItem("Credits");
		mntmAck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"The following were used in the making of this program:\n\n- Wine icon modified from \"Wine\" symbol by thenounproject.com, CC BY.\n- MiGLayout from MiG InfoCom AB, licensed under the BSD License.\n- SQLite from sqlite.org, public domain.\n- SQLiteJDBC from David Crawshaw / zentus.com, licensed under the BSD License.\n- JDateChooser from Adam Lee / gimlisys.com, licensed under the Academic Free License.\n- Joda Time from Joda.org, licensed under the Apache License.\n- Yeast Strains data compiled from information posted by \"goldseal\" and other members\n   of the winesathome.co.uk / homewinemaking.co.uk communities.",
						"Credits",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		);
		mnHelp.add(mntmAck);
		
	}
	
	public static void InitializeMenuMethod(){
		
		//TODO: Welcome pane
		
		//Main Content Pane
		MainWindow.WineBrewDBFrame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Menu Panel
		MenuPanel = new JPanel();
		MenuPanel.setBackground(Color.LIGHT_GRAY);
		MainWindow.WineBrewDBFrame.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		MenuPanel.setLayout(new MigLayout("", "[200px:n,grow]", "[][][][][][][][][][][][][][][][grow]"));
		
		txtDatabase = new JTextField();
		txtDatabase.setEditable(false);
		txtDatabase.setText("Database");
		MenuPanel.add(txtDatabase, "cell 0 0,growx");
		txtDatabase.setColumns(10);
		
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
	
	public static void DisableAllMenuButtons(){
		btnBrew.setEnabled(false);
		btnRecipe.setEnabled(false);
		btnAlcohol.setEnabled(false);
		btnDilution.setEnabled(false);
		btnMeasures.setEnabled(false);
		btnSugarToSG.setEnabled(false);
		btnTemperatureAdjustedSG.setEnabled(false);
		btnDosages.setEnabled(false);
		btnFruitAcids.setEnabled(false);
		btnYeastStrains.setEnabled(false);
		mnFile.setEnabled(false);
		mnDatabase.setEnabled(false);
		mnCalculators.setEnabled(false);
		mnInformation.setEnabled(false);
		mnHelp.setEnabled(false);
	};
	
	public static void EnableAllMenuButtons(){
		btnBrew.setEnabled(true);
		btnRecipe.setEnabled(true);
		btnAlcohol.setEnabled(true);
		btnDilution.setEnabled(true);
		btnMeasures.setEnabled(true);
		btnSugarToSG.setEnabled(true);
		btnTemperatureAdjustedSG.setEnabled(true);
		btnDosages.setEnabled(true);
		btnFruitAcids.setEnabled(true);
		btnYeastStrains.setEnabled(true);
		mnFile.setEnabled(true);
		mnDatabase.setEnabled(true);
		mnCalculators.setEnabled(true);
		mnInformation.setEnabled(true);
		mnHelp.setEnabled(true);
	};
	
}