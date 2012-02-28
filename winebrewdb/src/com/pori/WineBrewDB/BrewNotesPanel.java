package com.pori.WineBrewDB;

import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;


public class BrewNotesPanel extends JPanel {
	
	private static final long serialVersionUID = -1974511040788382066L;
	public static JPanel tabbedBrewNotesPanel;

	
	public static void InitializePanel(){
		
		tabbedBrewNotesPanel = new JPanel();
		tabbedBrewNotesPanel.setBackground(UIManager.getColor("Panel.background"));
		tabbedBrewNotesPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
	}
	
}