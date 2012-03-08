package com.pori.WineBrewDB;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class MainWindow {

	public static JFrame WineBrewDBFrame;
	public static String WineBrewDBVersion = "v1.0.2";
	public static String DatabaseLocationFromIni;
	public static String LookAndFeel;
	private static File WineBrewDBConfigDirectory;
	public static File WineBrewDBConfigFile;
	public static String OSSlash;
	static Wini brewIni;
	
	//TODO: Mac launcher somehow.
	//Use "Jar Bundler" on a mac
	//Also see: http://java.sun.com/developer/technicalArticles/JavaLP/JavaToMac/
	
	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	   		

			public void run() {
				//Set look and feel to system temporarily
				try{ 
					   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					}
					catch(Exception e){
					 e.printStackTrace();
					}
				
				//Get app data location for every OS
			    String OS = System.getProperty("os.name").toUpperCase();
			    if (OS.contains("WIN")){
			    	WineBrewDBConfigDirectory = new File(System.getenv("APPDATA") + "\\WineBrewDB");
			    	WineBrewDBConfigFile = new File(WineBrewDBConfigDirectory, "\\WineBrewDBConfig.ini");
			    	OSSlash = "\\";
			    }
			    else if (OS.contains("MAC")){
			    	WineBrewDBConfigDirectory = new File(System.getProperty("user.home") + "/Library/Application/WineBrewDB");
			    	WineBrewDBConfigFile = new File(WineBrewDBConfigDirectory, "/WineBrewDBConfig.ini");
			    	OSSlash = "/";
			    }
			    else if (OS.contains("NUX")){
			    	WineBrewDBConfigDirectory = new File(System.getProperty("user.home") + "/.WineBrewDB");
			    	WineBrewDBConfigFile = new File(WineBrewDBConfigDirectory, "/WineBrewDBConfig.ini");
			    	OSSlash = "/";
			    }
			    else {
			    	WineBrewDBConfigDirectory = new File(System.getProperty("user.home"));
			    	WineBrewDBConfigFile = new File(WineBrewDBConfigDirectory, "/WineBrewDBConfig.ini");
			    	OSSlash = "/";
			    }

				//Check config exists and create it if not	
				if(!WineBrewDBConfigFile.exists()){
			    	InputStream content = MainWindow.class.getResourceAsStream("/com/pori/WineBrewDB/WineBrewDBConfig.ini");
			  		FileOutputStream fop;	
			  		
		  			WineBrewDBConfigDirectory.mkdirs();
			  		
					try {
						fop = new FileOutputStream(WineBrewDBConfigFile);

						WineBrewDBConfigFile.createNewFile();
							
						byte buf[]=new byte[1024];
						int len;
						while((len=content.read(buf))>0)
						fop.write(buf,0,len);
						fop.close();
						content.close();
			 
					} catch (IOException exx) {
						JOptionPane.showMessageDialog(null,
								"An error occurred creating:\n" + WineBrewDBConfigFile + "\n\nCheck you have permission to save to this location.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
						exx.printStackTrace();
					} 
				}	
				
				//Get config settings
				try {
					brewIni = new Wini(WineBrewDBConfigFile);
					DatabaseLocationFromIni = brewIni.get("WineBrewDB", "DatabaseLocation");
					LookAndFeel = brewIni.get("WineBrewDB", "LookAndFeel");						
					} catch (InvalidFileFormatException e1) {
						JOptionPane.showMessageDialog(null,
								WineBrewDBConfigFile + "file format is invalid.",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Error accessing:\n" + WineBrewDBConfigFile + "\n\nPlease check the file exists and you have permission.",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				try{ 
					   if(LookAndFeel.equals("system")){
						   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					   }
					   if(LookAndFeel.equals("java")){
						   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					   }
					}
					catch(Exception e){
					 e.printStackTrace();
					}
				
				Font defaultFont = new Font("Arial",Font.PLAIN,12);
				UIManager.put("Button.font", defaultFont);				
				UIManager.put("ToggleButton.font", defaultFont);
				UIManager.put("RadioButton.font", defaultFont);
				UIManager.put("CheckBox.font", defaultFont);
				UIManager.put("ColorChooser.font", defaultFont);
				UIManager.put("ComboBox.font", defaultFont);
				UIManager.put("Label.font", defaultFont);
				UIManager.put("List.font", defaultFont);
				UIManager.put("MenuBar.font", defaultFont);
				UIManager.put("MenuItem.font", defaultFont);
				UIManager.put("RadioButtonMenuItem.font", defaultFont);
				UIManager.put("CheckBoxMenuItem.font", defaultFont);
				UIManager.put("Menu.font", defaultFont);
				UIManager.put("PopupMenu.font", defaultFont);
				UIManager.put("OptionPane.font", defaultFont);
				UIManager.put("Panel.font", defaultFont);
				UIManager.put("ProgressBar.font", defaultFont);
				UIManager.put("ScrollPane.font", defaultFont);
				UIManager.put("Viewport.font", defaultFont);
				UIManager.put("TabbedPane.font", defaultFont);
				UIManager.put("Table.font", defaultFont);
				UIManager.put("TableHeader.font", defaultFont);
				UIManager.put("TextField.font", defaultFont);
				UIManager.put("PasswordField.font", defaultFont);
				UIManager.put("TextArea.font", defaultFont);
				UIManager.put("TextPane.font", defaultFont);
				UIManager.put("EditorPane.font", defaultFont);
				UIManager.put("TitledBorder.font", defaultFont);
				UIManager.put("ToolBar.font", defaultFont);
				UIManager.put("ToolTip.font", defaultFont);
				UIManager.put("Tree.font", defaultFont);
				

				
				try {
					new MainWindow();
					MainWindow.WineBrewDBFrame.setVisible(true);
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
	public void initialize() {
		WineBrewDBFrame = new JFrame();
		WineBrewDBFrame.setTitle("WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
		URL img16 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb16.png");
		URL img32 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb32.png");
		URL img64 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb64.png");
		URL img128 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb128.png");
		URL img256 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb256.png");

        final ArrayList<Image> icons = new ArrayList<Image>();
        try {
			icons.add(ImageIO.read(img16));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			icons.add(ImageIO.read(img32));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			icons.add(ImageIO.read(img64));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			icons.add(ImageIO.read(img128));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			icons.add(ImageIO.read(img256));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WineBrewDBFrame.setIconImages(icons);
		WineBrewDBFrame.setBounds(100, 100, 1024, 640);
		WineBrewDBFrame.setLocation(50, 50);
		WineBrewDBFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		InitializeMenu.InitializeTopMenuMethod();
		InitializeMenu.InitializeMenuMethod();


	}


}
