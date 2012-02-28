package com.pori.WineBrewDB;

import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;


public class BrewPicturesPanel extends JPanel {
	
	private static final long serialVersionUID = -476275486314448039L;
	public static JPanel tabbedBrewPicturesPanel;

	
	public static void InitializePanel(){
		
		tabbedBrewPicturesPanel = new JPanel();
		tabbedBrewPicturesPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewPicturesPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
	}
	
}