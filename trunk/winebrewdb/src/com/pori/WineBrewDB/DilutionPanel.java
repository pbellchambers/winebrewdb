package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

public class DilutionPanel extends JPanel {

	private static final long serialVersionUID = -5260637755405545904L;
	public static JPanel DilutionPanel;
	public static JPanel DilutionSubPanel;
	public static JEditorPane DilutionText;
	public static JLabel DilutionHeader;
	public static JLabel DilutionSubtitle;
	public static JTextField txtDilutionPanel;
	public static JTextPane txtDilutionInfo;
	public static JLabel lblDilutionMethodABV;
	public static JLabel lblStartingVolumeABV;
	public static JFormattedTextField fieldStartingVolumeABV;
	public static JLabel lblVolumeAddedABV;
	public static JFormattedTextField fieldVolumeAddedABV;
	public static JLabel lblCurrentABV;
	public static JFormattedTextField fieldCurrentABV;
	public static JLabel lblResultABV;
	public static JFormattedTextField fieldResultABV;
	public static JButton btnCalculateDilutionABV;
	public static JLabel lblDilutionMethodSG;
	public static JLabel lblStartingVolumeSG;
	public static JFormattedTextField fieldStartingVolumeSG;
	public static JLabel lblVolumeAddedSG;
	public static JFormattedTextField fieldVolumeAddedSG;
	public static JLabel lblCurrentSG;
	public static JFormattedTextField fieldCurrentSG;
	public static JLabel lblResultSG;
	public static JFormattedTextField fieldResultSG;
	public static JButton btnCalculateDilutionSG;
	public static String DilutionPanelStatus = "DeInitialized";

	//public DilutionPanel() {
	public static void InitializePanel(){
		
		DilutionPanel = new JPanel();
		DilutionPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		DilutionHeader = new JLabel("Dilution");
		DilutionHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		DilutionPanel.add(DilutionHeader, "cell 0 0,grow");
		
		
		//Subtitle
		DilutionSubtitle = new JLabel("Calculate the adjusted ABV % / SG when diluting a brew.");
		DilutionSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		DilutionPanel.add(DilutionSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Subpanel
		DilutionSubPanel = new JPanel();
		DilutionSubPanel.setBackground(Color.WHITE);
		DilutionSubPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		DilutionSubPanel.setLayout(new MigLayout("", "[135px:n:135px,left][90px:n:90px][100px:n:100px][135px:n:135px,left][90px:n:90px][grow]", "[150px:n,150px][30px:n:30px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		DilutionPanel.add(DilutionSubPanel, "cell 0 2,grow");
			
				
		//Add Calculators to subpanel		
		txtDilutionInfo = new JTextPane();
		txtDilutionInfo.setText("The following calculators are to be used when you are diluting your brew. The first is for when you are topping up with water after fermentation is complete and you know the ABV %, the second is for calculating the adjusted starting SG when you are topping up with water during fermentation. \n\nNote: If you are topping up during fermentation with sugar containing liquid that is the same as the Starting SG then you do not need to calculate any adjustments. If you are topping up with sugar containing liquid that is different to the starting SG then that is currently out of the scope of this calculator.");
		DilutionSubPanel.add(txtDilutionInfo, "cell 0 0 6 1,grow");
		
		lblDilutionMethodABV = new JLabel("Diluted ABV %");
		DilutionSubPanel.add(lblDilutionMethodABV, "cell 0 1");
				
		lblStartingVolumeABV = new JLabel("Starting Volume (litres)");
		DilutionSubPanel.add(lblStartingVolumeABV, "cell 0 2,alignx trailing");
				
		fieldStartingVolumeABV = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldStartingVolumeABV.setText("0.000");
		DilutionSubPanel.add(fieldStartingVolumeABV, "cell 1 2,growx");
		
		lblCurrentABV = new JLabel("Current ABV %");
		DilutionSubPanel.add(lblCurrentABV, "cell 0 3,alignx trailing");
				
		fieldCurrentABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldCurrentABV.setText("0.00");
		DilutionSubPanel.add(fieldCurrentABV, "cell 1 3,growx");
				
		lblVolumeAddedABV = new JLabel("Volume Added (litres)");
		DilutionSubPanel.add(lblVolumeAddedABV, "cell 0 4,alignx trailing");
				
		fieldVolumeAddedABV = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldVolumeAddedABV.setText("0.000");
		DilutionSubPanel.add(fieldVolumeAddedABV, "cell 1 4,growx");
				
		btnCalculateDilutionABV = new JButton("Submit");
		DilutionSubPanel.add(btnCalculateDilutionABV, "cell 1 5");
				
		lblResultABV = new JLabel("Diluted ABV %");
		DilutionSubPanel.add(lblResultABV, "cell 0 6,alignx trailing");
				
		fieldResultABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldResultABV.setText("0.00");
		fieldResultABV.setEditable(false);
		DilutionSubPanel.add(fieldResultABV, "cell 1 6,growx");
				
		lblDilutionMethodSG = new JLabel("Diluted SG");
		DilutionSubPanel.add(lblDilutionMethodSG, "cell 3 1");
				
		lblStartingVolumeSG = new JLabel("Starting Volume (litres)");
		DilutionSubPanel.add(lblStartingVolumeSG, "cell 3 2,alignx trailing");
				
		fieldStartingVolumeSG = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldStartingVolumeSG.setText("0.000");
		DilutionSubPanel.add(fieldStartingVolumeSG, "cell 4 2,growx");
		
		lblCurrentSG = new JLabel("Starting SG (e.g. 1.085)");
		DilutionSubPanel.add(lblCurrentSG, "cell 3 3,alignx trailing");
				
		fieldCurrentSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCurrentSG.setText("0.000");
		DilutionSubPanel.add(fieldCurrentSG, "cell 4 3,growx");
				
		lblVolumeAddedSG = new JLabel("Volume Added (litres)");
		DilutionSubPanel.add(lblVolumeAddedSG, "cell 3 4,alignx trailing");
				
		fieldVolumeAddedSG = new JFormattedTextField(new DecimalFormat("#0.###"));
		fieldVolumeAddedSG.setText("0.000");
		DilutionSubPanel.add(fieldVolumeAddedSG, "cell 4 4,growx");	
		
		btnCalculateDilutionSG = new JButton("Submit");
		DilutionSubPanel.add(btnCalculateDilutionSG, "cell 4 5");
				
		lblResultSG = new JLabel("Diluted Starting SG");
		DilutionSubPanel.add(lblResultSG, "cell 3 6,alignx trailing");
				
		fieldResultSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldResultSG.setText("0.000");
		fieldResultSG.setEditable(false);
		DilutionSubPanel.add(fieldResultSG, "cell 4 6,growx");

				
		//Add button listeners
		btnCalculateDilutionABV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateDilutionABV();
			}
		});
				
		btnCalculateDilutionSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateDilutionSG();
			}
		});
				
				
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(DilutionPanel, "cell 0 0,grow");
		DilutionPanel.setVisible(false);

		DilutionPanelStatus = "Initialized";
	}

			
	public static void DeInitializePanel(){
		if(DilutionPanelStatus.equals("Initialized")) {
			DilutionPanel.setVisible(false);
			DilutionPanel.remove(DilutionHeader);
			DilutionPanel.remove(DilutionSubtitle);
			DilutionPanel.remove(DilutionSubPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DilutionPanel);
			DilutionPanelStatus = "DeInitialized";
		}
	}
			
	public static void CalculateDilutionABV(){
		BigDecimal StartingVolume = new BigDecimal(fieldStartingVolumeABV.getText());
		BigDecimal VolumeAdded = new BigDecimal(fieldVolumeAddedABV.getText());
		BigDecimal TotalNewVolume = StartingVolume.add(VolumeAdded);
		BigDecimal CurrentABV = new BigDecimal(fieldCurrentABV.getText());
		BigDecimal interim = CurrentABV.divide(TotalNewVolume, 5, BigDecimal.ROUND_HALF_UP);
		BigDecimal Result = interim.multiply(StartingVolume);
		
		fieldResultABV.setText(Result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}
			
	public static void CalculateDilutionSG(){
		BigDecimal StartingVolume = new BigDecimal(fieldStartingVolumeSG.getText());
		BigDecimal VolumeAdded = new BigDecimal(fieldVolumeAddedSG.getText());
		BigDecimal TotalNewVolume = StartingVolume.add(VolumeAdded);
		BigDecimal CurrentSGMinus1 = new BigDecimal(fieldCurrentSG.getText()).subtract(new BigDecimal("1"));
		BigDecimal CurrentSGPoints = CurrentSGMinus1.multiply(new BigDecimal("1000"));
		BigDecimal interim = CurrentSGPoints.multiply(StartingVolume);
		BigDecimal interimpoints = interim.divide(TotalNewVolume, 5, BigDecimal.ROUND_HALF_UP);
		BigDecimal interimminus1 = interimpoints.divide(new BigDecimal("1000"), 3, BigDecimal.ROUND_HALF_UP);
		BigDecimal Result = interimminus1.add(new BigDecimal("1"));
		
		fieldResultSG.setText(Result.toString());
	}
	

}