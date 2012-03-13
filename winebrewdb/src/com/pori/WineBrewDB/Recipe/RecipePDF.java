package com.pori.WineBrewDB.Recipe;

import java.awt.Desktop;
import java.io.File;

import javax.swing.JOptionPane;


public class RecipePDF {
 
	public static void openPDF(String location) {
 
	  try {
 
		File pdfFile = new File(location);
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				JOptionPane.showMessageDialog(null,
						"Automatic file opening is not supported on this system.\n\nYou will need to open the following file yourself:\n" + location,
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
 
		} else {
			JOptionPane.showMessageDialog(null,
					"The file at the following location could not be opened:\n" + location + "\n\nEither the file is missing/corrupt, or could not be accessed.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
 
	}
	
	
	public static void createPDF(String location) {
		
	}
	
	
}