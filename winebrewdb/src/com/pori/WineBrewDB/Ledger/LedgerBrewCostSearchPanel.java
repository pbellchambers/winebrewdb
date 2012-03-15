package com.pori.WineBrewDB.Ledger;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LedgerBrewCostSearchPanel extends JPanel {

	private static final long serialVersionUID = -6289098098304905534L;
	static JPanel tabbedLedgerBrewCostSearchPanel;
	
	
	public static void InitializePanel(){
		tabbedLedgerBrewCostSearchPanel = new JPanel();
		tabbedLedgerBrewCostSearchPanel.setBackground(Color.WHITE);
		tabbedLedgerBrewCostSearchPanel.setLayout(new MigLayout("", "[90px:90px:90px][grow][90px:90px:90px][grow][130px:130px:130px][grow]", "[130px:130px:130px][grow][][][][10px:10px:10px][]"));
		
		
	}
	
}