package com.pori.WineBrewDB;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
		
		InitializeMenu.InitializeMenuMethod();	



	}


}
