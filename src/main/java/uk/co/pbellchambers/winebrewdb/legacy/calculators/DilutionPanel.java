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
 * This is the dilution calculator panel to be displayed as a tab on the main calculator panel tab bar.
 * 
 * @author Paul.Bellchambers
 *
 */
public class DilutionPanel extends JPanel {

	private static final long serialVersionUID = -5260637755405545904L;
	static JPanel DilutionSubPanel;
	private static JFormattedTextField fieldStartingVolumeABV;
	private static JFormattedTextField fieldVolumeAddedABV;
	private static JFormattedTextField fieldCurrentABV;
	private static JFormattedTextField fieldResultABV;
	private static JFormattedTextField fieldStartingVolumeSG;
	private static JFormattedTextField fieldVolumeAddedSG;
	private static JFormattedTextField fieldCurrentSG;
	private static JFormattedTextField fieldResultSG;
	private static String DilutionPanelStatus = "DeInitialised";

	/**
	 * Initialises the dilution calculator panel so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		
		//Subpanel
		DilutionSubPanel = new JPanel();
		DilutionSubPanel.setBackground(Color.WHITE);
		DilutionSubPanel.setLayout(new MigLayout("", "[135px:n:135px,left][90px:n:90px][100px:n:100px][135px:n:135px,left][90px:n:90px][grow]", "[150px:n,150px][30px:n:30px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
			
				
		//Add Calculators to subpanel		
		JTextPane txtDilutionInfo = new JTextPane();
		txtDilutionInfo.setText("The following calculators are to be used when you are diluting your brew. The first is for when you are topping up with water after fermentation is complete and you know the ABV %, the second is for calculating the adjusted starting SG when you are topping up with water during fermentation. \n\nNote: If you are topping up during fermentation with sugar containing liquid that is the same as the Starting SG then you do not need to calculate any adjustments. If you are topping up with sugar containing liquid that is different to the starting SG then that is currently out of the scope of this calculator.");
		txtDilutionInfo.setEditable(false);
		DilutionSubPanel.add(txtDilutionInfo, "cell 0 0 6 1,grow");
		
		JLabel lblDilutionMethodABV = new JLabel("Diluted ABV %");
		DilutionSubPanel.add(lblDilutionMethodABV, "cell 0 1");
				
		JLabel lblStartingVolumeABV = new JLabel("Starting Volume (litres)");
		DilutionSubPanel.add(lblStartingVolumeABV, "cell 0 2,alignx trailing");
				
		fieldStartingVolumeABV = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldStartingVolumeABV.setText("0.000");
		DilutionSubPanel.add(fieldStartingVolumeABV, "cell 1 2,growx");
		
		JLabel lblCurrentABV = new JLabel("Current ABV %");
		DilutionSubPanel.add(lblCurrentABV, "cell 0 3,alignx trailing");
				
		fieldCurrentABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldCurrentABV.setText("0.00");
		DilutionSubPanel.add(fieldCurrentABV, "cell 1 3,growx");
				
		JLabel lblVolumeAddedABV = new JLabel("Volume Added (litres)");
		DilutionSubPanel.add(lblVolumeAddedABV, "cell 0 4,alignx trailing");
				
		fieldVolumeAddedABV = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldVolumeAddedABV.setText("0.000");
		DilutionSubPanel.add(fieldVolumeAddedABV, "cell 1 4,growx");
				
		JButton btnCalculateDilutionABV = new JButton("Submit");
		DilutionSubPanel.add(btnCalculateDilutionABV, "cell 1 5");
				
		JLabel lblResultABV = new JLabel("Diluted ABV %");
		DilutionSubPanel.add(lblResultABV, "cell 0 6,alignx trailing");
				
		fieldResultABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldResultABV.setText("0.00");
		fieldResultABV.setEditable(false);
		DilutionSubPanel.add(fieldResultABV, "cell 1 6,growx");
				
		JLabel lblDilutionMethodSG = new JLabel("Diluted SG");
		DilutionSubPanel.add(lblDilutionMethodSG, "cell 3 1");
				
		JLabel lblStartingVolumeSG = new JLabel("Starting Volume (litres)");
		DilutionSubPanel.add(lblStartingVolumeSG, "cell 3 2,alignx trailing");
				
		fieldStartingVolumeSG = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldStartingVolumeSG.setText("0.000");
		DilutionSubPanel.add(fieldStartingVolumeSG, "cell 4 2,growx");
		
		JLabel lblCurrentSG = new JLabel("Starting SG (e.g. 1.085)");
		DilutionSubPanel.add(lblCurrentSG, "cell 3 3,alignx trailing");
				
		fieldCurrentSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCurrentSG.setText("0.000");
		DilutionSubPanel.add(fieldCurrentSG, "cell 4 3,growx");
				
		JLabel lblVolumeAddedSG = new JLabel("Volume Added (litres)");
		DilutionSubPanel.add(lblVolumeAddedSG, "cell 3 4,alignx trailing");
				
		fieldVolumeAddedSG = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldVolumeAddedSG.setText("0.000");
		DilutionSubPanel.add(fieldVolumeAddedSG, "cell 4 4,growx");	
		
		JButton btnCalculateDilutionSG = new JButton("Submit");
		DilutionSubPanel.add(btnCalculateDilutionSG, "cell 4 5");
				
		JLabel lblResultSG = new JLabel("Diluted Starting SG");
		DilutionSubPanel.add(lblResultSG, "cell 3 6,alignx trailing");
				
		fieldResultSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldResultSG.setText("0.000");
		fieldResultSG.setEditable(false);
		DilutionSubPanel.add(fieldResultSG, "cell 4 6,growx");

				
		//Add button listeners
		btnCalculateDilutionABV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateDilutionABV();
			}
		});
				
		btnCalculateDilutionSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateDilutionSG();
			}
		});
				
				
		//Add it all to the main window
		LegacyApp.WineBrewDBFrame.getContentPane().add(DilutionSubPanel, "cell 0 0,grow");
		DilutionSubPanel.setVisible(false);

		DilutionPanelStatus = "Initialised";
	}

	/**
	 * De-initialises the dilution calculator panel so that it is not visible on screen.
	 */		
	public static void deinitialisePanel(){
		if(DilutionPanelStatus.equals("Initialised")) {
			DilutionSubPanel.setVisible(false);
			LegacyApp.WineBrewDBFrame.getContentPane().remove(DilutionSubPanel);
			DilutionPanelStatus = "DeInitialised";
		}
	}
	
	/**
	 * Calculates the diluted ABV amount based on the user entered values.
	 */
	private static void calculateDilutionABV(){
		BigDecimal StartingVolume = new BigDecimal(fieldStartingVolumeABV.getText());
		BigDecimal VolumeAdded = new BigDecimal(fieldVolumeAddedABV.getText());
		BigDecimal TotalNewVolume = StartingVolume.add(VolumeAdded);
		BigDecimal CurrentABV = new BigDecimal(fieldCurrentABV.getText());
		BigDecimal Result = new BigDecimal("0");
		try{
			BigDecimal interim = CurrentABV.divide(TotalNewVolume, 5, BigDecimal.ROUND_HALF_UP);
			Result = interim.multiply(StartingVolume);
		}catch(ArithmeticException ex){
			JOptionPane.showMessageDialog(null,
			"You seem to have tried to divide by zero.",
			"Error",
			JOptionPane.ERROR_MESSAGE
			);
		}
		
		fieldResultABV.setText(Result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}
			
	/**
	 * Calculates the diluted SG amount based on the user entered values.
	 */
	private static void calculateDilutionSG(){
		BigDecimal StartingVolume = new BigDecimal(fieldStartingVolumeSG.getText());
		BigDecimal VolumeAdded = new BigDecimal(fieldVolumeAddedSG.getText());
		BigDecimal TotalNewVolume = StartingVolume.add(VolumeAdded);
		BigDecimal CurrentSGMinus1 = new BigDecimal(fieldCurrentSG.getText()).subtract(new BigDecimal("1"));
		BigDecimal CurrentSGPoints = CurrentSGMinus1.multiply(new BigDecimal("1000"));
		BigDecimal Result = new BigDecimal("0");
		try{
			BigDecimal interim = CurrentSGPoints.multiply(StartingVolume);
			BigDecimal interimpoints = interim.divide(TotalNewVolume, 5, BigDecimal.ROUND_HALF_UP);
			BigDecimal interimminus1 = interimpoints.divide(new BigDecimal("1000"), 3, BigDecimal.ROUND_HALF_UP);
			Result = interimminus1.add(new BigDecimal("1"));
		}catch(ArithmeticException ex){
			JOptionPane.showMessageDialog(null,
			"You seem to have tried to divide by zero.",
			"Error",
			JOptionPane.ERROR_MESSAGE
			);			
		}		
		
		fieldResultSG.setText(Result.toString());
	}
	

}