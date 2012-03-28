package uk.co.pori.WineBrewDB.Calculators;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uk.co.pori.WineBrewDB.MainWindow;


import net.miginfocom.swing.MigLayout;

public class CalculatorsPanel extends JPanel {

	private static final long serialVersionUID = 2339618745984753667L;
	public static JPanel CalculatorsPanel;
	private static JLabel CalculatorsHeader;
	private static JLabel CalculatorsSubtitle;
	public static JTabbedPane tabbedCalculatorsPane;
	private static String CalculatorsPanelStatus = "DeInitialized";

	//public CalculatorsPanel() {
	public static void InitializePanel(){
		
		CalculatorsPanel = new JPanel();
		CalculatorsPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		CalculatorsHeader = new JLabel("Calculators");
		CalculatorsHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		CalculatorsPanel.add(CalculatorsHeader, "cell 0 0,grow");
		
		
		//Subtitle
		CalculatorsSubtitle = new JLabel("Various useful calculators.");
		CalculatorsSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		CalculatorsPanel.add(CalculatorsSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Tabbed Pane
		tabbedCalculatorsPane = new JTabbedPane(JTabbedPane.TOP);
		CalculatorsPanel.add(tabbedCalculatorsPane, "cell 0 2,grow");
				
		
		//Alcohol % Tab
		AlcoholPanel.InitializePanel();		
		tabbedCalculatorsPane.addTab("Alcohol %", null, AlcoholPanel.AlcoholSubPanel, null);
		
		
		//Dilution Tab
		DilutionPanel.InitializePanel();		
		tabbedCalculatorsPane.addTab("Dilution", null, DilutionPanel.DilutionSubPanel, null);
		
		
		//Measures Tab
		MeasuresPanel.InitializePanel();
		tabbedCalculatorsPane.addTab("Measures", null, MeasuresPanel.MeasuresSubPanel, null);
		
		
		//Calculators Pictures Tab
		SugarToSGPanel.InitializePanel();
		tabbedCalculatorsPane.addTab("Sugar to SG", null, SugarToSGPanel.SugarToSGSubPanel, null);
		
		
		//Add New Calculators Tab
		TemperatureAdjustedSGPanel.InitializePanel();
		tabbedCalculatorsPane.addTab("Temperature Adjusted SG", null, TemperatureAdjustedSGPanel.TemperatureAdjustedSGSubPanel, null);
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(CalculatorsPanel, "cell 0 0,grow");
		CalculatorsPanel.setVisible(false);

		
		CalculatorsPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(CalculatorsPanelStatus.equals("Initialized")) {
			CalculatorsPanel.setVisible(false);
			CalculatorsPanel.remove(CalculatorsHeader);
			CalculatorsPanel.remove(CalculatorsSubtitle);
			CalculatorsPanel.remove(tabbedCalculatorsPane);
			tabbedCalculatorsPane.remove(AlcoholPanel.AlcoholSubPanel);
			tabbedCalculatorsPane.remove(DilutionPanel.DilutionSubPanel);
			tabbedCalculatorsPane.remove(MeasuresPanel.MeasuresSubPanel);
			tabbedCalculatorsPane.remove(SugarToSGPanel.SugarToSGSubPanel);
			tabbedCalculatorsPane.remove(TemperatureAdjustedSGPanel.TemperatureAdjustedSGSubPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(CalculatorsPanel);
			CalculatorsPanelStatus = "DeInitialized";
		}
	}
	

}