package com.pori.WineBrewDB;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import com.pori.WineBrewDB.Ledger.LedgerPanel;
import com.pori.WineBrewDB.Recipe.RecipePanel;

import net.miginfocom.swing.MigLayout;

public class InitializeMenu extends MainWindow {
	
	private static JRadioButtonMenuItem radioSystemLAF;
	private static JRadioButtonMenuItem radioJavaLAF;
	private static JMenu mnFile;
	private static JMenu mnDatabase;
	private static JMenu mnCalculators;
	private static JMenu mnInformation;
	private static JMenu mnHelp;
	private static JButton btnBrew;
	private static JButton btnRecipe;
	private static JButton btnCalculators;
	private static JButton btnInformation;
	private static JButton btnLedger;
	
	public static void InitializeTopMenuMethod(){
		
		//Top Menu Bar
		JMenuBar menuBar = new JMenuBar();
		MainWindow.WineBrewDBFrame.setJMenuBar(menuBar);
	
		
		//File Menu
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		

		final JMenuItem mntmNew = new JMenuItem("New Database");
		mntmNew.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/new.png")));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(MainWindow.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  
			    	InputStream content = MainWindow.class.getResourceAsStream("/com/pori/WineBrewDB/SQLite/BlankWineBrewDBData.sqlite");		    	  
					File filename = new File(c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".sqlite");
			  		FileOutputStream fop;	
			  		
					try {
						fop = new FileOutputStream(filename);
						filename.createNewFile();
							
						byte buf[]=new byte[1024];
						int len;
						while((len=content.read(buf))>0)
						fop.write(buf,0,len);
						fop.close();
						content.close();
			 
					} catch (IOException exx) {
						JOptionPane.showMessageDialog(null,
								"An error occurred saving the new database, check you have permission to this location.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						exx.printStackTrace();
					} 			    	  
			    	  
			    	  MainWindow.DatabaseLocationFromIni = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".sqlite";
			    	  MainWindow.brewIni.put("WineBrewDB", "DatabaseLocation", MainWindow.DatabaseLocationFromIni);
			    	  try {
			    		  MainWindow.brewIni.store();
				    	  MainWindow.WineBrewDBFrame.setTitle("WineBrewDB " + MainWindow.WineBrewDBVersion + " - Current Database: " + MainWindow.DatabaseLocationFromIni);
				    	  DeinitializeAllPanels();
				    	  WelcomePanel.InitializePanel();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Failed to save:\n" + MainWindow.WineBrewDBConfigFile + "\n\nPlease check you have permission.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }
				}
			}
			
		);
		mnFile.add(mntmNew);
		
		final JMenuItem mntmLoad = new JMenuItem("Load Database");
		mntmLoad.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/load.png")));
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser c = new JFileChooser();
					  int rVal = c.showOpenDialog(MainWindow.WineBrewDBFrame);
					  if (rVal == JFileChooser.APPROVE_OPTION) {
						  MainWindow.DatabaseLocationFromIni = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName();
						  MainWindow.brewIni.put("WineBrewDB", "DatabaseLocation", MainWindow.DatabaseLocationFromIni);
						  try {
							  MainWindow.brewIni.store();
					    	  MainWindow.WineBrewDBFrame.setTitle("WineBrewDB " + MainWindow.WineBrewDBVersion + " - Current Database: " + MainWindow.DatabaseLocationFromIni);
					    	  DeinitializeAllPanels();
					    	  WelcomePanel.InitializePanel();
					    	  
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,
									"Failed to save:\n" + MainWindow.WineBrewDBConfigFile + "\n\nPlease check you have permission.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					  }
					  if (rVal == JFileChooser.CANCEL_OPTION) {
						  
					  }
				}
			}
			
		);
		mnFile.add(mntmLoad);
		

		final JMenuItem mntmSaveAs = new JMenuItem("Save Database As");
		mntmSaveAs.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/save2.png")));
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(MainWindow.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	File currentdb = new File(MainWindow.DatabaseLocationFromIni);  
			    	FileInputStream content;
					try {
						content = new FileInputStream(currentdb);
						File filename = new File(c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".sqlite");
				  		FileOutputStream fop;	
				  		
						try {
							fop = new FileOutputStream(filename);
							filename.createNewFile();
								
							byte buf[]=new byte[1024];
							int len;
							while((len=content.read(buf))>0)
							fop.write(buf,0,len);
							fop.close();
							content.close();
				 
						} catch (IOException exx) {
							JOptionPane.showMessageDialog(null,
									"An error occurred saving the new database, check you have permission to this location.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							exx.printStackTrace();
						} 			    	  
				    	  
				    	  MainWindow.DatabaseLocationFromIni = c.getCurrentDirectory().toString() + MainWindow.OSSlash + c.getSelectedFile().getName() + ".sqlite";
				    	  MainWindow.brewIni.put("WineBrewDB", "DatabaseLocation", MainWindow.DatabaseLocationFromIni);
				    	  try {
				    		  MainWindow.brewIni.store();
					    	  MainWindow.WineBrewDBFrame.setTitle("WineBrewDB " + MainWindow.WineBrewDBVersion + " - Current Database: " + MainWindow.DatabaseLocationFromIni);
					    	  DeinitializeAllPanels();
					    	  WelcomePanel.InitializePanel();
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,
									"Failed to save:\n" + MainWindow.WineBrewDBConfigFile + "\n\nPlease check you have permission.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null,
								MainWindow.DatabaseLocationFromIni + "\n\nThe current database file no longer seems to exist.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}		    	  

			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }
				}
			}			
		);
		mnFile.add(mntmSaveAs);
		
		mnFile.addSeparator();
		
		JMenu mnLAF = new JMenu("Look and Feel");
		mnFile.add(mnLAF);
		
		radioSystemLAF = new JRadioButtonMenuItem("System");
		if(MainWindow.LookAndFeel.equals("system")){radioSystemLAF.setSelected(true);}
		radioSystemLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
					   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					   MainWindow.brewIni.put("WineBrewDB", "LookAndFeel", "system");
					   MainWindow.brewIni.store();
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
		if(MainWindow.LookAndFeel.equals("java")){radioJavaLAF.setSelected(true);}
		radioJavaLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
						MainWindow.brewIni.put("WineBrewDB", "LookAndFeel", "java");
						MainWindow.brewIni.store();
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
		
		final JMenuItem mntmLedger = new JMenuItem("Ledger");
		mntmLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				LedgerPanel.InitializePanel();
				LedgerPanel.LedgerPanel.setVisible(true);				
				}
			}
			
		);
		mnDatabase.add(mntmLedger);
		
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
						"WineBrewDB - " + WineBrewDBVersion + 
						"\nhttp://code.google.com/p/winebrewdb/" +
						"\n\nLicensed under The MIT License" +
						"\nCopyright (c) 2012 Paul Bellchambers" +
						"\n\nPermission is hereby granted, free of charge, to any person obtaining a copy of this " +
						"\nsoftware and associated documentation files (the \"Software\"), to deal in the Software " +
						"\nwithout restriction, including without limitation the rights to use, copy, modify, merge, " +
						"\npublish, distribute, sublicense, and/or sell copies of the Software, and to permit persons " +
						"\nto whom the Software is furnished to do so, subject to the following conditions:" +
						"\n\nThe above copyright notice and this permission notice shall be included in all copies " +
						"\nor substantial portions of the Software." +
						"\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, " +
						"\nINCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR " +
						"\nPURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE " +
						"\nFOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR " +
						"\nOTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER " +
						"\nDEALINGS IN THE SOFTWARE.",
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
						"The following were used in the making of this program:\n\n" +
						"- Wine icon modified from \"Wine\" symbol by thenounproject.com, CC BY.\n" +
						"- MiGLayout from MiG InfoCom AB, licensed under the BSD License.\n" +
						"- SQLite from sqlite.org, public domain.\n" +
						"- SQLiteJDBC from David Crawshaw / zentus.com, licensed under the BSD License.\n" +
						"- JDateChooser from Adam Lee / gimlisys.com, licensed under the Academic Free License.\n" +
						"- Joda Time from Joda.org, licensed under the Apache License.\n" +
						"- ini4j from Ivan SZKIBA, licensed under the Apache License.\n" +
						"- iText from 1T3XT BVBA / itextpdf.com, licensed under the AGPLv3 License.\n" +
						"- Yeast Strains data compiled from information posted by \"goldseal\" and other members\nof the winesathome.co.uk community.",
						"Credits",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		);
		mnHelp.add(mntmAck);
		
		final JMenuItem mntmReportBug = new JMenuItem("Report a bug");
		mntmReportBug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"You will now be directed to the WineBrewDB Google Code page to report any bugs/feature requests.","",JOptionPane.PLAIN_MESSAGE);
				if (Desktop.isDesktopSupported()) {
					try {
						java.net.URI uri = new java.net.URI("http://code.google.com/p/winebrewdb/issues/list");
						java.awt.Desktop.getDesktop().browse(uri);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Automatic URL opening is not supported on this system.\n\nYou will need to open the following URL yourself:\n\n" + "http://code.google.com/p/winebrewdb/issues/list",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
					
				}
			}
			
		);
		mnHelp.add(mntmReportBug);
		

		final JMenuItem mntmCheckUpdates = new JMenuItem("Check for updates");
		mntmCheckUpdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        URL url = new URL("http://winebrewdb.googlecode.com/svn/trunk/winebrewdb/VERSION.txt");
			        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			        String str;
			        while ((str = in.readLine()) != null) {
			        	if(str.equals("") || str == null){
				        	JOptionPane.showMessageDialog(null,
									"There was a problem accessing the URL, please check the website manually:\n\n" + "http://code.google.com/p/winebrewdb/",
									"Error",
									JOptionPane.ERROR_MESSAGE);			        		
			        	}
			        	if(str.equals(MainWindow.WineBrewDBVersion)){
			        		JOptionPane.showMessageDialog(null,"You have the latest version!","",JOptionPane.PLAIN_MESSAGE);
			        	}else{
			        		JOptionPane.showMessageDialog(null,"The latest version is:  " + str + "\n\nYou will now be directed to the WineBrewDB website where you can download the latest version.","Out of Date",JOptionPane.PLAIN_MESSAGE);
							if (Desktop.isDesktopSupported()) {
								try {
									java.net.URI uri = new java.net.URI("http://code.google.com/p/winebrewdb/downloads/list");
									java.awt.Desktop.getDesktop().browse(uri);
								} catch (URISyntaxException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Automatic URL opening is not supported on this system.\n\nYou will need to open the following URL yourself:\n\n" + "http://code.google.com/p/winebrewdb/downloads/list",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
			        	}
			        }
			        in.close();
			        } catch (MalformedURLException e1) {
			        	JOptionPane.showMessageDialog(null,
								"There was a problem accessing the URL, please check the website manually:\n\n" + "http://code.google.com/p/winebrewdb/",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
			        } catch (IOException e1) {
			        	JOptionPane.showMessageDialog(null,
			        			"There was a problem accessing the URL, please check the website manually:\n\n" + "http://code.google.com/p/winebrewdb/",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
			        }
			}
		});		
		mnHelp.add(mntmCheckUpdates);
		
	}
	
	public static void InitializeMenuMethod(){
		
		//Main Content Pane
		MainWindow.WineBrewDBFrame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Menu Panel
		JToolBar MenuPanel = new JToolBar();
		MenuPanel.setFloatable(false);
		MenuPanel.setOrientation(SwingConstants.VERTICAL);
		MainWindow.WineBrewDBFrame.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		
		btnBrew = new JButton();
		btnBrew.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/brew.png")));
		btnBrew.setToolTipText("Add/Edit Brews");
		MenuPanel.add(btnBrew);
		
		btnLedger = new JButton();
		btnLedger.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/ledger.png")));
		btnLedger.setToolTipText("Add To/Edit Ledger");
		MenuPanel.add(btnLedger);
		
		btnRecipe = new JButton();
		btnRecipe.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/recipe.png")));
		btnRecipe.setToolTipText("Add/Edit Recipes");
		MenuPanel.add(btnRecipe);
		
		MenuPanel.addSeparator();
		
		btnCalculators = new JButton();
		btnCalculators.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/calculator.png")));
		btnCalculators.setToolTipText("Various Calculators");
		MenuPanel.add(btnCalculators);
		
		MenuPanel.addSeparator();

		btnInformation = new JButton();
		btnInformation.setIcon(new ImageIcon(InitializeMenu.class.getResource("/com/pori/WineBrewDB/Images/information.png")));
		btnInformation.setToolTipText("Useful Information");
		MenuPanel.add(btnInformation);
		
		//Initialise welcome panel
		WelcomePanel.InitializePanel();
		

		//Menu Button Actions
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				BrewPanel.InitializePanel();
				BrewPanel.BrewPanel.setVisible(true);
			}
		});
		btnLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeinitializeAllPanels();
				LedgerPanel.InitializePanel();
				LedgerPanel.LedgerPanel.setVisible(true);
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
		WelcomePanel.DeInitializePanel();
		BrewPanel.DeInitializePanel();
		LedgerPanel.DeInitializePanel();
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
	};
	
	public static void DisableAllMenuButtons(){
		btnBrew.setEnabled(false);
		btnLedger.setEnabled(false);
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
		btnLedger.setEnabled(true);
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