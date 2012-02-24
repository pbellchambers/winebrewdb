package com.pori.WineBrewDB;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainWindow {

	public static JFrame WineBrewDBFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.WineBrewDBFrame.setVisible(true);
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
		WineBrewDBFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb64.jpg")));
		WineBrewDBFrame.setBounds(100, 100, 1024, 640);
		WineBrewDBFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		InitializeMenu.InitializeMenuMethod();	



	}


}
