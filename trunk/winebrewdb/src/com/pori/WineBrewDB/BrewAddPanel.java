package com.pori.WineBrewDB;

import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;


public class BrewAddPanel extends JPanel {
	
	private static final long serialVersionUID = 1783367101321663851L;
	public static JPanel tabbedBrewAddPanel;

	
	public static void InitializePanel(){
		
		tabbedBrewAddPanel = new JPanel();
		tabbedBrewAddPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewAddPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
	}
	
}