package com.pori.WineBrewDB.Ledger;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LedgerBrewCostDataPanel extends JPanel {

	private static final long serialVersionUID = -2902891098304905534L;
	static JPanel tabbedLedgerBrewCostDataPanel;
	
	
	public static void InitializePanel(){
		tabbedLedgerBrewCostDataPanel = new JPanel();
		tabbedLedgerBrewCostDataPanel.setBackground(Color.WHITE);
		tabbedLedgerBrewCostDataPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][90px:90px:90px][grow][130px:130px:130px][grow]", "[130px:130px:130px][grow][][][][10px:10px:10px][]"));
		
		
	}
	
}