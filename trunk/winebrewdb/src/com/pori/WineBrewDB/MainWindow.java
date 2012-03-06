package com.pori.WineBrewDB;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class MainWindow {

	public static JFrame WineBrewDBFrame;
	public static String WineBrewDBVersion = "v0.8.3";
	public static String DatabaseLocationFromIni;
	public static String LookAndFeel;
	private static Wini brewIni;
	
	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				Font defaultFont = new Font("Arial",Font.PLAIN,12);
				
				try {
					brewIni = new Wini(new File("WineBrewDBConfig.ini"));
					DatabaseLocationFromIni = brewIni.get("WineBrewDB", "DatabaseLocation");
					LookAndFeel = brewIni.get("WineBrewDB", "LookAndFeel");						
					} catch (InvalidFileFormatException e1) {
					// TODO Invalid .ini file error
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO .ini IO error
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
		CheckAndSetCurrentDB();


	}

	private void CheckAndSetCurrentDB() {
		if(DatabaseLocationFromIni.equals("") || DatabaseLocationFromIni == null){
			JFileChooser c = new JFileChooser();
		      int rVal = c.showSaveDialog(WineBrewDBFrame);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		    	  DatabaseLocationFromIni = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName() + ".sqlite";
		    	  System.out.println(DatabaseLocationFromIni);
		    	  brewIni.put("WineBrewDB", "DatabaseLocation", DatabaseLocationFromIni);
		    	  try {
					brewIni.store();
				} catch (IOException e) {
					//TODO: Failed saving ini
					e.printStackTrace();
				}
		    	  System.out.println(brewIni.get("WineBrewDB", "DatabaseLocation"));
		    	  JOptionPane.showMessageDialog(null,"Database location set successfully.","Complete",JOptionPane.INFORMATION_MESSAGE);
		    	  WineBrewDBFrame.setTitle("WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
					//TODO: Disable cancel		    	  
		      }
			
		}
		
	}


}
