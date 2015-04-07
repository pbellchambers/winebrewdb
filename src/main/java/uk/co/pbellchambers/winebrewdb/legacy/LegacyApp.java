package uk.co.pbellchambers.winebrewdb.legacy;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Contains everything to start the application, and sets up the main window.
 * 
 * @author paul.bellchambers
 *
 */
public class LegacyApp {

	public static JFrame WineBrewDBFrame;
	public static String WineBrewDBVersion = LegacyApp.class.getPackage().getImplementationVersion();
	public static String DatabaseLocationFromIni;
	public static String LookAndFeel;
	private static File WineBrewDBConfigDirectory;
	public static File WineBrewDBConfigFile;
	public static String OSSlash;
	public static Wini brewIni;
	
	/**
	 * Starts the application. Including getting the users specified preferences or creating the default ones if they don't exist, and setting the application as appropriate. 
	 * 
	 * @param args ?? No idea what any args do here.
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
			    	InputStream content = LegacyApp.class.getResourceAsStream("/config/WineBrewDBConfig.ini");
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
					new LegacyApp();
					LegacyApp.WineBrewDBFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Creates the main window of the application.
	 */
	public LegacyApp() {
		initialise();
	}

	/**
	 * Initialise the contents of the main frame so that it is visible.
	 */
	private void initialise() {
		WineBrewDBFrame = new JFrame();
		WineBrewDBFrame.setTitle("WineBrewDB " + WineBrewDBVersion + " - Current Database: " + DatabaseLocationFromIni);
		URL img16 = LegacyApp.class.getResource("/images/winebrewdb16.png");
		URL img32 = LegacyApp.class.getResource("/images/winebrewdb32.png");
		URL img64 = LegacyApp.class.getResource("/images/winebrewdb64.png");
		URL img128 = LegacyApp.class.getResource("/images/winebrewdb128.png");
		URL img256 = LegacyApp.class.getResource("/images/winebrewdb256.png");

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
		
		InitialiseMenu.initialiseTopMenuMethod();
		InitialiseMenu.initialiseMenuMethod();


	}


}
