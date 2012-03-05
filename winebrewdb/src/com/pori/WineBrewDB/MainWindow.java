package com.pori.WineBrewDB;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow {

	public static JFrame WineBrewDBFrame;
	public static MainWindow window;
	public static URL img16;
	public static URL img32;
	public static URL img64;
	public static URL img128;
	public static URL img256;

	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Font defaultFont = new Font("Arial",Font.PLAIN,12);
				
				//TODO: Add graphical buttons, maybe get rid of side menu bar, expand top menu bar, more like a proper application.
				try{ 
					   UIManager.setLookAndFeel(
					        UIManager.getSystemLookAndFeelClassName());
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
					window = new MainWindow();
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
		WineBrewDBFrame.setTitle("WineBrewDB");
		img16 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb16.png");
		img32 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb32.png");
		img64 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb64.png");
		img128 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb128.png");
		img256 = MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb256.png");

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
