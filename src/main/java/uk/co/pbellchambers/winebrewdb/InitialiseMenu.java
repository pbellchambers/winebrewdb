package uk.co.pbellchambers.winebrewdb;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.brew.BrewPanel;
import uk.co.pbellchambers.winebrewdb.calculators.*;
import uk.co.pbellchambers.winebrewdb.information.InformationPanel;
import uk.co.pbellchambers.winebrewdb.ledger.LedgerPanel;
import uk.co.pbellchambers.winebrewdb.recipe.RecipePanel;

import javax.swing.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This handles all the menus (top and sidebar) for the application.
 * 
 * @author paul.bellchambers
 *
 */
public class InitialiseMenu extends MainWindow {
	
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
	
	/**
	 * Initialises the top menu so that it is visible on screen, and sets what all of the buttons do.
	 */
	public static void initialiseTopMenuMethod(){
		
		//Top Menu Bar
		JMenuBar menuBar = new JMenuBar();
		WineBrewDBFrame.setJMenuBar(menuBar);
	
		
		//File Menu
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		

		final JMenuItem mntmNew = new JMenuItem("New Database");
		mntmNew.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/new.png")));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  
			    	InputStream content = MainWindow.class.getResourceAsStream("/sqlite/BlankWineBrewDBData.sqlite");
					File filename = new File(c.getCurrentDirectory().toString() + OSSlash + c.getSelectedFile().getName() + ".sqlite");
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
			    	  
			    	  DatabaseLocationFromIni = c.getCurrentDirectory().toString() + OSSlash + c.getSelectedFile().getName() + ".sqlite";
			    	  brewIni.put("WineBrewDB", "DatabaseLocation", DatabaseLocationFromIni);
			    	  try {
			    		  brewIni.store();
				    	  WineBrewDBFrame.setTitle(
                              "WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
				    	  deinitialiseAllPanels();
				    	  WelcomePanel.initialisePanel();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Failed to save:\n" + WineBrewDBConfigFile + "\n\nPlease check you have permission.",
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
		mntmLoad.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/load.png")));
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser c = new JFileChooser();
					  int rVal = c.showOpenDialog(WineBrewDBFrame);
					  if (rVal == JFileChooser.APPROVE_OPTION) {
						  DatabaseLocationFromIni = c.getCurrentDirectory().toString() + OSSlash + c.getSelectedFile().getName();
						  brewIni.put("WineBrewDB", "DatabaseLocation", DatabaseLocationFromIni);
						  try {
							  brewIni.store();
					    	  WineBrewDBFrame.setTitle(
                                  "WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
					    	  deinitialiseAllPanels();
					    	  WelcomePanel.initialisePanel();
					    	  
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,
									"Failed to save:\n" + WineBrewDBConfigFile + "\n\nPlease check you have permission.",
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
		mntmSaveAs.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/save2.png")));
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	File currentdb = new File(DatabaseLocationFromIni);
			    	FileInputStream content;
					try {
						content = new FileInputStream(currentdb);
						File filename = new File(c.getCurrentDirectory().toString() + OSSlash + c.getSelectedFile().getName() + ".sqlite");
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
				    	  
				    	  DatabaseLocationFromIni = c.getCurrentDirectory().toString() + OSSlash + c.getSelectedFile().getName() + ".sqlite";
				    	  brewIni.put("WineBrewDB", "DatabaseLocation", DatabaseLocationFromIni);
				    	  try {
				    		  brewIni.store();
					    	  WineBrewDBFrame.setTitle(
                                  "WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
					    	  deinitialiseAllPanels();
					    	  WelcomePanel.initialisePanel();
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null,
									"Failed to save:\n" + WineBrewDBConfigFile + "\n\nPlease check you have permission.",
									"Error",
									JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null,
								DatabaseLocationFromIni + "\n\nThe current database file no longer seems to exist.",
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
		if(LookAndFeel.equals("system")){radioSystemLAF.setSelected(true);}
		radioSystemLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
					   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					   brewIni.put("WineBrewDB", "LookAndFeel", "system");
					   brewIni.store();
					}
					catch(Exception ex){
					 ex.printStackTrace();
					}
				SwingUtilities.updateComponentTreeUI(WineBrewDBFrame);
				radioSystemLAF.setSelected(true);
				radioJavaLAF.setSelected(false);
				}
			}
			
		);
		mnLAF.add(radioSystemLAF);
		
		radioJavaLAF = new JRadioButtonMenuItem("Java");
		if(LookAndFeel.equals("java")){radioJavaLAF.setSelected(true);}
		radioJavaLAF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{ 
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
						brewIni.put("WineBrewDB", "LookAndFeel", "java");
						brewIni.store();
					}
					catch(Exception ex){
					 ex.printStackTrace();
					}
				SwingUtilities.updateComponentTreeUI(WineBrewDBFrame);
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
				WineBrewDBFrame.dispose();
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
				deinitialiseAllPanels();
				BrewPanel.initialisePanel();
				BrewPanel.BrewPanel.setVisible(true);
				}
			}
			
		);
		mnDatabase.add(mntmBrew);
		
		final JMenuItem mntmLedger = new JMenuItem("Ledger");
		mntmLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				LedgerPanel.initialisePanel();
				LedgerPanel.LedgerPanel.setVisible(true);				
				}
			}
			
		);
		mnDatabase.add(mntmLedger);
		
		final JMenuItem mntmRecipe = new JMenuItem("Recipe");
		mntmRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				RecipePanel.initialisePanel();
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
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(0);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmAlcohol);
		
		final JMenuItem mntmDilution = new JMenuItem("Dilution");
		mntmDilution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(1);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
				}
			}
			
		);
		mnCalculators.add(mntmDilution);
		
		final JMenuItem mntmMeasures = new JMenuItem("Measures");
		mntmMeasures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(2);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmMeasures);
		
		final JMenuItem mntmSugarToSG = new JMenuItem("Sugar to SG");
		mntmSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(3);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
				}
			}
			
		);
		mnCalculators.add(mntmSugarToSG);
		
		final JMenuItem mntmTemperatureAdjustedSG = new JMenuItem("Temperature Adjusted SG");
		mntmTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(4);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);				
				}
			}
			
		);
		mnCalculators.add(mntmTemperatureAdjustedSG);
				
				
		//Information Menu
		mnInformation = new JMenu("Information");
		menuBar.add(mnInformation);
		
		final JMenuItem mntmInformationAddEdit = new JMenuItem("Add/Edit");
		mntmInformationAddEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				InformationPanel.initialisePanel();
				int finaltab = InformationPanel.tabbedInformationPane.getTabCount() - 1;
				InformationPanel.tabbedInformationPane.setSelectedIndex(finaltab);
				InformationPanel.InformationPanel.setVisible(true);					
				}
			}
			
		);
		mnInformation.add(mntmInformationAddEdit);
		
		
		//Help Menu
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		final JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"WineBrewDB - " + WineBrewDBVersion + 
						"\nhttps://github.com/pbellchambers/winebrewdb/" +
						"\n\nLicensed under the Apache v2.0 License" +
						"\nCopyright (c) 2012-2015 Paul Bellchambers",
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
						"- Wine icon modified from \"Wine\" symbol by thenounproject.com, CC BY\n" +
                        "- Icons from \"GO Green! Web\" icon set by Dawghouse Design Studio\n" +
						"- JDateChooser from Adam Lee / gimlisys.com, licensed under the Academic Free License\n" +
						"- Yeast Strains data compiled from information posted by \"goldseal\" and other members\nof the winesathome.co.uk community",
						"Credits",
						JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		);
		mnHelp.add(mntmAck);
		
		final JMenuItem mntmReportBug = new JMenuItem("Report a bug");
		mntmReportBug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"You will now be directed to the WineBrewDB GitHub page to report any bugs/feature requests.","",JOptionPane.PLAIN_MESSAGE);
				if (Desktop.isDesktopSupported()) {
					try {
						java.net.URI uri = new java.net.URI("https://github.com/pbellchambers/winebrewdb/issues");
						java.awt.Desktop.getDesktop().browse(uri);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Automatic URL opening is not supported on this system.\n\nYou will need to open the following URL yourself:\n\n" + "https://github.com/pbellchambers/winebrewdb/issues",
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
			        URL url = new URL("https://raw.githubusercontent.com/pbellchambers/winebrewdb/master/VERSION");
			        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			        String str;
			        while ((str = in.readLine()) != null) {
			        	if(str.equals("") || str == null){
				        	JOptionPane.showMessageDialog(null,
									"There was a problem accessing the URL, please check the website manually:\n\n" + "https://github.com/pbellchambers/winebrewdb/releases",
									"Error",
									JOptionPane.ERROR_MESSAGE);			        		
			        	}
			        	if(str.equals(WineBrewDBVersion)){
			        		JOptionPane.showMessageDialog(null,"You have the latest version!","",JOptionPane.PLAIN_MESSAGE);
			        	}else{
			        		JOptionPane.showMessageDialog(null,"The latest version is:  " + str + "\n\nYou will now be directed to the WineBrewDB website where you can download the latest version.","Out of Date",JOptionPane.PLAIN_MESSAGE);
							if (Desktop.isDesktopSupported()) {
								try {
									java.net.URI uri = new java.net.URI("https://github.com/pbellchambers/winebrewdb/releases");
									java.awt.Desktop.getDesktop().browse(uri);
								} catch (URISyntaxException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Automatic URL opening is not supported on this system.\n\nYou will need to open the following URL yourself:\n\n" + "https://github.com/pbellchambers/winebrewdb/releases",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
			        	}
			        }
			        in.close();
			        } catch (MalformedURLException e1) {
			        	JOptionPane.showMessageDialog(null,
								"There was a problem accessing the URL, please check the website manually:\n\n" + "hhttps://github.com/pbellchambers/winebrewdb/",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
			        } catch (IOException e1) {
			        	JOptionPane.showMessageDialog(null,
			        			"There was a problem accessing the URL, please check the website manually:\n\n" + "https://github.com/pbellchambers/winebrewdb/",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
			        }
			}
		});		
		mnHelp.add(mntmCheckUpdates);
		
	}
	
	/**
	 * Initialises the sidebar menu so that it is visible on screen, and sets what all the buttons do.
	 */
	public static void initialiseMenuMethod(){
		
		//Main Content Pane
		WineBrewDBFrame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//Menu Panel
		JToolBar MenuPanel = new JToolBar();
		MenuPanel.setFloatable(false);
		MenuPanel.setOrientation(SwingConstants.VERTICAL);
		WineBrewDBFrame.getContentPane().add(MenuPanel, "flowx,cell 0 0,alignx left,growy");
		
		btnBrew = new JButton();
		btnBrew.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/brew.png")));
		btnBrew.setToolTipText("Add/Edit Brews");
		MenuPanel.add(btnBrew);
		
		btnLedger = new JButton();
		btnLedger.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/ledger.png")));
		btnLedger.setToolTipText("Add To/Edit Ledger");
		MenuPanel.add(btnLedger);
		
		btnRecipe = new JButton();
		btnRecipe.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/recipe.png")));
		btnRecipe.setToolTipText("Add/Edit Recipes");
		MenuPanel.add(btnRecipe);
		
		MenuPanel.addSeparator();
		
		btnCalculators = new JButton();
		btnCalculators.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/calculator.png")));
		btnCalculators.setToolTipText("Various Calculators");
		MenuPanel.add(btnCalculators);
		
		MenuPanel.addSeparator();

		btnInformation = new JButton();
		btnInformation.setIcon(new ImageIcon(InitialiseMenu.class.getResource("/images/information.png")));
		btnInformation.setToolTipText("Add your own notes and information to be displayed in tabs here");
		MenuPanel.add(btnInformation);
		
		//Initialise welcome panel
		WelcomePanel.initialisePanel();
		

		//Menu Button Actions
		btnBrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				BrewPanel.initialisePanel();
				BrewPanel.BrewPanel.setVisible(true);
			}
		});
		btnLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				LedgerPanel.initialisePanel();
				LedgerPanel.LedgerPanel.setVisible(true);
			}
		});
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				RecipePanel.initialisePanel();
				RecipePanel.RecipePanel.setVisible(true);
			}
		});
		btnCalculators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				CalculatorsPanel.initialisePanel();
				CalculatorsPanel.tabbedCalculatorsPane.setSelectedIndex(0);
				CalculatorsPanel.CalculatorsPanel.setVisible(true);			
			}
		});
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deinitialiseAllPanels();
				InformationPanel.initialisePanel();
				InformationPanel.tabbedInformationPane.setSelectedIndex(0);
				InformationPanel.InformationPanel.setVisible(true);		
			}
		});
		
	}
	
	/**
	 * Calls the De-initialise panel method for all panels so that none are visible on screen.
	 */
	private static void deinitialiseAllPanels(){
		WelcomePanel.deinitialisePanel();
		BrewPanel.deinitialisePanel();
		LedgerPanel.deinitialisePanel();
		RecipePanel.deinitialisePanel();
		CalculatorsPanel.deinitialisePanel();
		InformationPanel.deinitialisePanel();
		AlcoholPanel.deinitialisePanel();
		DilutionPanel.deinitialisePanel();
		MeasuresPanel.deinitialisePanel();
		SugarToSGPanel.deinitialisePanel();
		TemperatureAdjustedSGPanel.deinitialisePanel();
	};
	
	/**
	 * Disables all menu buttons (both top and sidebar).
	 */
	public static void disableAllMenuButtons(){
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
	
	/**
	 * Enables all menu buttons (both top and sidebar).
	 */
	public static void enableAllMenuButtons(){
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