package uk.co.pbellchambers.winebrewdb.legacy;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is the welcome panel that is shown on first opening the application.
 * 
 * @author paul.bellchambers
 *
 */
public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 3333498745984753687L;
	public static JPanel WelcomePanel;
	private static JLabel WelcomeText2;
	private static JLabel WelcomeText3;
	private static String WelcomePanelStatus = "DeInitialised";

	/**
	 * Initialises the welcome panel so that it is displayed on screen.
	 */
	public static void initialisePanel(){		
		
		InitialiseMenu.disableAllMenuButtons();
		
		WelcomePanel = new JPanel();
		WelcomePanel.setLayout(new MigLayout("", "[300px,grow][300px,grow]", "[30px:n:30px][30px:n:30px][30px:n:30px][50px:n:50px][100px:n:100px][grow]"));
		
		JLabel WelcomeText1 = new JLabel();
		WelcomeText1.setText("Welcome to WineBrewDB!");
		WelcomeText1.setFont(new Font("Arial", Font.BOLD, 18));
		WelcomeText1.setHorizontalAlignment(SwingConstants.CENTER);
		WelcomePanel.add(WelcomeText1, "cell 0 0 2,grow");
		
		JButton btnNewDatabase = new JButton();
		btnNewDatabase.setIcon(new ImageIcon(WelcomePanel.class.getResource("/images/newbig.png")));
		btnNewDatabase.setText("New WineBrewDB Database");
		btnNewDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewDatabase.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewDatabase.setHorizontalTextPosition(SwingConstants.CENTER);
		WelcomePanel.add(btnNewDatabase, "cell 0 4,alignx center");;
		
		JButton btnLoadDatabase = new JButton();
		btnLoadDatabase.setIcon(new ImageIcon(WelcomePanel.class.getResource("/images/loadbig.png")));
		btnLoadDatabase.setText("Load WineBrewDB Database");
		btnLoadDatabase.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLoadDatabase.setHorizontalTextPosition(SwingConstants.CENTER);
		WelcomePanel.add(btnLoadDatabase, "cell 1 4,alignx center");;
		
		
		if(LegacyApp.DatabaseLocationFromIni.equals("") || LegacyApp.DatabaseLocationFromIni == null){
			WelcomeText2 = new JLabel();
			WelcomeText2.setText("Your current database is not set!");
			WelcomeText2.setFont(new Font("Arial", Font.BOLD, 14));
			WelcomeText2.setForeground(Color.RED);
			WelcomeText2.setHorizontalAlignment(SwingConstants.CENTER);
			WelcomePanel.add(WelcomeText2, "cell 0 1 2,grow");
			
			WelcomeText3 = new JLabel();
			WelcomeText3.setText("Please create a new database below to begin.");
			WelcomeText3.setFont(new Font("Arial", Font.PLAIN, 14));
			WelcomeText3.setHorizontalAlignment(SwingConstants.CENTER);
			WelcomePanel.add(WelcomeText3, "cell 0 2 2,grow");
			
		}else{			
			InitialiseMenu.enableAllMenuButtons();
			
			WelcomeText2 = new JLabel();
			WelcomeText2.setText("Your current database is set to: " + LegacyApp.DatabaseLocationFromIni);
			WelcomeText2.setFont(new Font("Arial", Font.PLAIN, 14));
			WelcomeText2.setHorizontalAlignment(SwingConstants.CENTER);
			WelcomePanel.add(WelcomeText2, "cell 0 1 2,grow");
			
			WelcomeText3 = new JLabel();
			WelcomeText3.setText("Select an option from the left to begin, and remember to ensure your database is backed up.");
			WelcomeText3.setFont(new Font("Arial", Font.PLAIN, 14));
			WelcomeText3.setHorizontalAlignment(SwingConstants.CENTER);
			WelcomePanel.add(WelcomeText3, "cell 0 2 2,grow");
		}
	
		
		//Add it all to the main window
		LegacyApp.WineBrewDBFrame.getContentPane().add(WelcomePanel, "cell 0 0,grow");
		WelcomePanel.setVisible(true);
		
		btnNewDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showSaveDialog(LegacyApp.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  
			    	InputStream content = LegacyApp.class.getResourceAsStream("/sqlite/BlankWineBrewDBData.sqlite");
					File filename = new File(c.getCurrentDirectory().toString() + LegacyApp.OSSlash + c.getSelectedFile().getName() + ".sqlite");
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
			    	  
			    	  LegacyApp.DatabaseLocationFromIni = c.getCurrentDirectory().toString() + LegacyApp.OSSlash + c.getSelectedFile().getName() + ".sqlite";
			    	  LegacyApp.brewIni.put("WineBrewDB", "DatabaseLocation", LegacyApp.DatabaseLocationFromIni);
			    	  try {
			    		  LegacyApp.brewIni.store();
			    		  InitialiseMenu.enableAllMenuButtons();
				    	  WelcomeText2.setText("Your current database is set to: " + LegacyApp.DatabaseLocationFromIni);
				    	  WelcomeText2.setFont(new Font("Arial", Font.PLAIN, 14));
				    	  WelcomeText2.setForeground(Color.BLACK);
				    	  WelcomeText3.setText("Select an option from the left to begin, and remember to ensure your database is backed up.");
				    	  WelcomeText3.setFont(new Font("Arial", Font.PLAIN, 14));
				    	  LegacyApp.WineBrewDBFrame.setTitle("WineBrewDB " + LegacyApp.WineBrewDBVersion + " - Current Database: " + LegacyApp.DatabaseLocationFromIni);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Failed to save:\n" + LegacyApp.WineBrewDBConfigFile + "\n\nPlease check you have permission.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }
			}
		});
		
		btnLoadDatabase.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
			      int rVal = c.showOpenDialog(LegacyApp.WineBrewDBFrame);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  LegacyApp.DatabaseLocationFromIni = c.getCurrentDirectory().toString() + LegacyApp.OSSlash + c.getSelectedFile().getName();
			    	  LegacyApp.brewIni.put("WineBrewDB", "DatabaseLocation", LegacyApp.DatabaseLocationFromIni);
			    	  try {
			    		  LegacyApp.brewIni.store();
			    		  InitialiseMenu.enableAllMenuButtons();
				    	  WelcomeText2.setText("Your current database is set to: " + LegacyApp.DatabaseLocationFromIni);
				    	  WelcomeText2.setFont(new Font("Arial", Font.PLAIN, 14));
				    	  WelcomeText2.setForeground(Color.BLACK);
				    	  WelcomeText3.setText("Select an option from the left to begin, and remember to ensure your database is backed up.");
				    	  WelcomeText3.setFont(new Font("Arial", Font.PLAIN, 14));
				    	  LegacyApp.WineBrewDBFrame.setTitle("WineBrewDB " + LegacyApp.WineBrewDBVersion + " - Current Database: " + LegacyApp.DatabaseLocationFromIni);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null,
								"Failed to save:\n" + LegacyApp.WineBrewDBConfigFile + "\n\nPlease check you have permission.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			    	  
			      }
			}
		});

		
		WelcomePanelStatus = "Initialised";
	}

	/**
	 * De-initialises the welcome panel so that it is no longer displayed on screen.
	 */
	public static void deinitialisePanel(){
		if(WelcomePanelStatus.equals("Initialised")) {
			WelcomePanel.remove(WelcomeText2);
			WelcomePanel.remove(WelcomeText3);
			LegacyApp.WineBrewDBFrame.getContentPane().remove(WelcomePanel);
			WelcomePanelStatus = "DeInitialised";
		}
	}
	

}