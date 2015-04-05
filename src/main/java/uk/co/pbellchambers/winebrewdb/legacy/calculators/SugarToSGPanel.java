package uk.co.pbellchambers.winebrewdb.legacy.calculators;

import net.miginfocom.swing.MigLayout;
import uk.co.pbellchambers.winebrewdb.legacy.LegacyApp;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * This is the Sugar to SG calculator panel to be displayed as a tab on the main calculator panel tab bar.
 * 
 * @author Paul.Bellchambers
 *
 */
public class SugarToSGPanel extends JPanel {

	private static final long serialVersionUID = -6105676849516060237L;
	static JPanel SugarToSGSubPanel;
	private static JFormattedTextField fieldVolumeSugarToSG;
	private static JFormattedTextField fieldCurrentSG;
	private static JFormattedTextField fieldDesiredSG;
	private static JFormattedTextField fieldResult;	
	private static String SugarToSGPanelStatus = "DeInitialised";

	/**
	 * Initialises the Sugar to SG calculator panel so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		
		//Subpanel
		SugarToSGSubPanel = new JPanel();
		SugarToSGSubPanel.setBackground(Color.WHITE);
		SugarToSGSubPanel.setLayout(new MigLayout("", "[135px:n:135px,left][90px:n:90px][grow]", "[60px:n,60px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));

						
		//Add Calculators to subpanel		
		JTextPane txtSugarToSGInfo = new JTextPane();
		txtSugarToSGInfo.setText("Use this to calculate approximately how much sugar you need to add to a known volume of liquid that has a known SG to reach a desired SG.");
		txtSugarToSGInfo.setEditable(false);
		SugarToSGSubPanel.add(txtSugarToSGInfo, "cell 0 0 6 1,grow");
			
		JLabel lblVolumeSugarToSG = new JLabel("Volume (litres)");
		SugarToSGSubPanel.add(lblVolumeSugarToSG, "cell 0 1,alignx trailing");
						
		fieldVolumeSugarToSG = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldVolumeSugarToSG.setText("0.000");
		SugarToSGSubPanel.add(fieldVolumeSugarToSG, "cell 1 1,growx");
				
		JLabel lblCurrentSG = new JLabel("Current SG");
		SugarToSGSubPanel.add(lblCurrentSG, "cell 0 2,alignx trailing");
						
		fieldCurrentSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCurrentSG.setText("0.000");
		SugarToSGSubPanel.add(fieldCurrentSG, "cell 1 2,growx");
						
		JLabel lblDesiredSG = new JLabel("Desired SG");
		SugarToSGSubPanel.add(lblDesiredSG, "cell 0 3,alignx trailing");
						
		fieldDesiredSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldDesiredSG.setText("0.000");
		SugarToSGSubPanel.add(fieldDesiredSG, "cell 1 3,growx");
						
		JButton btnCalculateSugarToSG = new JButton("Submit");
		SugarToSGSubPanel.add(btnCalculateSugarToSG, "cell 1 4");
						
		JLabel lblResult = new JLabel("Sugar Required (grams)");
		SugarToSGSubPanel.add(lblResult, "cell 0 5,alignx trailing");
						
		fieldResult = new JFormattedTextField(new DecimalFormat("####0"));
		fieldResult.setText("0");
		fieldResult.setEditable(false);
		SugarToSGSubPanel.add(fieldResult, "cell 1 5,growx");
						
							
		//Add button listeners
		btnCalculateSugarToSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateSugarToSG();
			}
		});
		
		
		//Add it all to the main window
		LegacyApp.WineBrewDBFrame.getContentPane().add(SugarToSGSubPanel, "cell 0 0,grow");
		SugarToSGSubPanel.setVisible(false);

		SugarToSGPanelStatus = "Initialised";
	}

	/**
	 * De-initialises the Sugar to SG calculator panel so that it is not visible on screen.
	 */	
	public static void deinitialisePanel(){
		if(SugarToSGPanelStatus.equals("Initialised")) {
			SugarToSGSubPanel.setVisible(false);
			LegacyApp.WineBrewDBFrame.getContentPane().remove(SugarToSGSubPanel);
			SugarToSGPanelStatus = "DeInitialised";
		}
	}
	
	/**
	 * Calculates the approximate Sugar required to increase the SG by the user entered amount.
	 */
	private static void calculateSugarToSG(){
		  
		BigDecimal StartingVolume = new BigDecimal(fieldVolumeSugarToSG.getText());
		BigDecimal CurrentSG = new BigDecimal(fieldCurrentSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal DesiredSG = new BigDecimal(fieldDesiredSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal IncreaseRequired = DesiredSG.subtract(CurrentSG);
		BigDecimal GramsPerLitreSugarRequired = new BigDecimal("2.7").multiply(IncreaseRequired);
		BigDecimal Result = GramsPerLitreSugarRequired.multiply(StartingVolume);

		fieldResult.setText(Result.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
	}

}