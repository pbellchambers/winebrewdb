package com.pori.WineBrewDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.pori.WineBrewDB.Brew.BrewPanel;
import com.pori.WineBrewDB.Calculators.AlcoholPanel;
import com.pori.WineBrewDB.Calculators.CalculatorsPanel;
import com.pori.WineBrewDB.Calculators.DilutionPanel;
import com.pori.WineBrewDB.Calculators.MeasuresPanel;
import com.pori.WineBrewDB.Calculators.SugarToSGPanel;
import com.pori.WineBrewDB.Calculators.TemperatureAdjustedSGPanel;
import com.pori.WineBrewDB.Information.DosagesPanel;
import com.pori.WineBrewDB.Information.FruitAcidsPanel;
import com.pori.WineBrewDB.Information.InformationPanel;
import com.pori.WineBrewDB.Information.YeastStrainsPanel;
import com.pori.WineBrewDB.Recipe.RecipePanel;

import net.miginfocom.swing.MigLayout;

public class InitializeMenu extends MainWindow {
	
	public static JTextField txtDatabase;
	public static JTextField txtCalculators;
	public static JTextField txtInformation;
	public static JLayeredPane ContentPane;
	public static JButton btnBrew;
	public static JButton btnRecipe;
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
	private static JToolBar MenuPanel;
	private static JMenuBar menuBar;
	private static JMenu mnFile;
	private static JMenu mnDatabase;
	private static JMenu mnCalculators;
	private static JMenu mnInformation;
	private static JMenu mnHelp;
	public static JButton btnCalculators;
	public static JButton btnInformation;
	
	public static void InitializeTopMenuMethod(){
		
		WineBrewDBVersion = "v0.8.2";
		
		//Top Menu Bar
		menuBar = new JMenuBar();
		MainWindow.WineBrewDBFrame.setJMenuBar(menuBar);
		
		//TODO: Finish menu bar (load and save)
		//TODO: First run database save location popup (use .ini file)
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
		mntmLoad.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/load.png")));
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				}
			}
			
		);
		mnFile.add(mntmLoad);
		
		final JMenuItem mntmSaveAs = new JMenuItem("Save Database As");
		mntmSaveAs.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/save.png")));
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
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(0);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmAlcohol);
		
		final JMenuItem mntmDilution = new JMenuItem("Dilution");
		mntmDilution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(1);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
				}
			}
			
		);
		mnCalculators.add(mntmDilution);
		
		final JMenuItem mntmMeasures = new JMenuItem("Measures");
		mntmMeasures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(2);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmMeasures);
		
		final JMenuItem mntmSugarToSG = new JMenuItem("Sugar to SG");
		mntmSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(3);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
				}
			}
			
		);
		mnCalculators.add(mntmSugarToSG);
		
		final JMenuItem mntmTemperatureAdjustedSG = new JMenuItem("Temperature Adjusted SG");
		mntmTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(4);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
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
				InformationPanel.InitializePanel();
				InformationPanel.tabbedInformationPane.setSelectedIndex(0);
				InformationPanel.InformationPanel.setVisible(true);					
				}
			}
			
		);
		mnInformation.add(mntmDosages);
		
		final JMenuItem mntmFruitAcids = new JMenuItem("Fruit Acids");
		mntmFruitAcids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				InformationPanel.InitializePanel();
				InformationPanel.tabbedInformationPane.setSelectedIndex(1);
				InformationPanel.InformationPanel.setVisible(true);				
				}
			}
			
		);
		mnInformation.add(mntmFruitAcids);
		
		final JMenuItem mntmYeastStrains = new JMenuItem("Yeast Strains");
		mntmYeastStrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				InformationPanel.InitializePanel();
				InformationPanel.tabbedInformationPane.setSelectedIndex(2);
				InformationPanel.InformationPanel.setVisible(true);				
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
		MenuPanel = new JToolBar();
		MenuPanel.setFloatable(false);
		MenuPanel.setOrientation(SwingConstants.VERTICAL);
		MainWindow.WineBrewDBFrame.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		
		btnBrew = new JButton();
		btnBrew.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/brew.png")));
		btnBrew.setToolTipText("Add/Edit Brews");
		MenuPanel.add(btnBrew);
		
		btnRecipe = new JButton();
		btnRecipe.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/recipe.png")));
		btnRecipe.setToolTipText("Add/Edit Recipes");
		MenuPanel.add(btnRecipe);
		
		MenuPanel.addSeparator();
		
		btnCalculators = new JButton();
		btnCalculators.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/calculator.png")));
		btnCalculators.setToolTipText("Various Calculators");
		MenuPanel.add(btnCalculators);
		
		MenuPanel.addSeparator();

		btnInformation = new JButton();
		btnInformation.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/information.png")));
		btnInformation.setToolTipText("Useful Information");
		MenuPanel.add(btnInformation);
		

		//Menu Button Actions
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				BrewPanel.InitializePanel();
				BrewPanel.BrewPanel.setVisible(true);
			}
		});
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				RecipePanel.InitializePanel();
				RecipePanel.RecipePanel.setVisible(true);
			}
		});
		btnCalculators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				CalculatorsPanel.InitializePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(0);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
			}
		});
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				InformationPanel.InitializePanel();
				InformationPanel.tabbedInformationPane.setSelectedIndex(0);
				InformationPanel.InformationPanel.setVisible(true);		
			}
		});
		
	}
	
	public static void DeinitializeAllPanels(){
		BrewPanel.DeInitializePanel();
		RecipePanel.DeInitializePanel();
		CalculatorsPanel.DeInitializePanel();
		InformationPanel.DeInitializePanel();
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
		btnCalculators.setSelected(false);
		btnInformation.setSelected(false);
	};
	
	public static void DisableAllMenuButtons(){
		btnBrew.setEnabled(false);
		btnRecipe.setEnabled(false);
		btnCalculators.setEnabled(false);
		btnInformation.setEnabled(false);
		mnFile.setEnabled(false);
		mnDatabase.setEnabled(false);
		mnCalculators.setEnabled(false);
		mnInformation.setEnabled(false);
		mnHelp.setEnabled(false);
	};
	
	public static void EnableAllMenuButtons(){
		btnBrew.setEnabled(true);
		btnRecipe.setEnabled(true);
		btnCalculators.setEnabled(true);
		btnInformation.setEnabled(true);
		mnFile.setEnabled(true);
		mnDatabase.setEnabled(true);
		mnCalculators.setEnabled(true);
		mnInformation.setEnabled(true);
		mnHelp.setEnabled(true);
	};
	
}