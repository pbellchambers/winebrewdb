package uk.co.pbellchambers.winebrewdb.legacy.calculators;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.LegacyApp;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;

/**
 * This is the main calculators panel that contains all of the sub panels in a tab layout.
 * 
 * @author Paul.Bellchambers
 *
 */
public class CalculatorsPanel extends JPanel {

	private static final long serialVersionUID = 2339618745984753667L;
	public static JPanel CalculatorsPanel;
	private static JLabel CalculatorsHeader;
	private static JLabel CalculatorsSubtitle;
	public static JTabbedPane tabbedCalculatorsPane;
	private static String CalculatorsPanelStatus = "DeInitialised";

	/**
	 * Initialises all the calculators panels so that they are displayed on screen.
	 */
	public static void initialisePanel(){
		
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
		AlcoholPanel.initialisePanel();		
		tabbedCalculatorsPane.addTab("Alcohol %", null, AlcoholPanel.AlcoholSubPanel, null);
		
		
		//Dilution Tab
		DilutionPanel.initialisePanel();		
		tabbedCalculatorsPane.addTab("Dilution", null, DilutionPanel.DilutionSubPanel, null);
		
		
		//Measures Tab
		MeasuresPanel.initialisePanel();
		tabbedCalculatorsPane.addTab("Measures", null, MeasuresPanel.MeasuresSubPanel, null);
		
		
		//Calculators Pictures Tab
		SugarToSGPanel.initialisePanel();
		tabbedCalculatorsPane.addTab("Sugar to SG", null, SugarToSGPanel.SugarToSGSubPanel, null);
		
		
		//Add New Calculators Tab
		TemperatureAdjustedSGPanel.initialisePanel();
		tabbedCalculatorsPane.addTab("Temperature Adjusted SG", null, TemperatureAdjustedSGPanel.TemperatureAdjustedSGSubPanel, null);
		
		
		//Add it all to the main window
		LegacyApp.WineBrewDBFrame.getContentPane().add(CalculatorsPanel, "cell 0 0,grow");
		CalculatorsPanel.setVisible(false);

		
		CalculatorsPanelStatus = "Initialised";
	}

	/**
	 * De-initialises all the calculators panels so that they are no longer displayed on screen.
	 */
	public static void deinitialisePanel(){
		if(CalculatorsPanelStatus.equals("Initialised")) {
			CalculatorsPanel.setVisible(false);
			tabbedCalculatorsPane.removeAll();
			CalculatorsPanel.removeAll();
			LegacyApp.WineBrewDBFrame.getContentPane().remove(CalculatorsPanel);
			CalculatorsPanelStatus = "DeInitialised";
		}
	}
	

}