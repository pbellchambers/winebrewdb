package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

public class AlcoholPanel extends JPanel {

	private static final long serialVersionUID = -8187974481712023349L;
	public static JPanel AlcoholPanel;
	public static JPanel AlcoholSubPanel;
	public static JLabel AlcoholHeader;
	public static JLabel AlcoholSubtitle;
	public static JLabel lblStartingSG;
	public static JLabel lblFinishingSG;
	public static JLabel lblABV;
	public static JLabel lblAlcoholMethod;
	public static JTextPane txtAlcoholInfo;
	public static JFormattedTextField fieldStartingSG;
	public static JFormattedTextField fieldFinishingSG;
	public static JFormattedTextField fieldABV;
	public static JButton btnCalculateABV;
	public static String AlcoholPanelStatus = "DeInitialized";

	//public AlcoholPanel() {
	public static void InitializePanel(){
		
		AlcoholPanel = new JPanel();
		AlcoholPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		//Header
		AlcoholHeader = new JLabel("Alcohol %");
		AlcoholHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		AlcoholPanel.add(AlcoholHeader, "cell 0 0,grow");
		//Subtitle
		AlcoholSubtitle = new JLabel("Calculate ABV % from SG");
		AlcoholSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		AlcoholPanel.add(AlcoholSubtitle, "cell 0 1,growx,aligny top");
		
		//Subpanel
		AlcoholSubPanel = new JPanel();
		AlcoholSubPanel.setBackground(Color.WHITE);
		AlcoholSubPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		AlcoholSubPanel.setLayout(new MigLayout("", "[150px:n:150px,left][150px:n:150px][grow]", "[150px:n,150px][30px:n:30px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		AlcoholPanel.add(AlcoholSubPanel, "cell 0 2,grow");
		
		
		//TESTING FROM HERE ON
		
		txtAlcoholInfo = new JTextPane();
		txtAlcoholInfo.setText("The standard formula for calculating %ABV is: \"(OG - FG) / F = %ABV\". However the factor (F) needed for an accurate result is actually dependant on the amount of alcohol present in the wine. Two differing methods of calculation are provided here as follows:\r\n\r\nThe \"HMRC Method\" extrapolates from HMRC's published factors (HMRC Ref: Notice 226 (November 2011)) used for calculating ABV in beer. This is the most accurate method provided here.\r\nThe \"CJJ Berry Method\" (from \"First Steps in Winemaking\" ISBN: ) uses a constant 7.36 for the factor (F).");
		AlcoholSubPanel.add(txtAlcoholInfo, "cell 0 0 3 1,grow");
		
		lblAlcoholMethod = new JLabel("HMRC Method");
		AlcoholSubPanel.add(lblAlcoholMethod, "cell 0 1");
		
		lblStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		AlcoholSubPanel.add(lblStartingSG, "cell 0 2,alignx trailing");
		
		fieldStartingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldStartingSG.setText("0.000");
		AlcoholSubPanel.add(fieldStartingSG, "cell 1 2,growx");
		
		lblFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		AlcoholSubPanel.add(lblFinishingSG, "cell 0 3,alignx trailing");
		
		fieldFinishingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldFinishingSG.setText("0.000");
		AlcoholSubPanel.add(fieldFinishingSG, "cell 1 3,growx");
		
		btnCalculateABV = new JButton("Submit");
		AlcoholSubPanel.add(btnCalculateABV, "cell 1 4");
		
		lblABV = new JLabel("ABV %");
		AlcoholSubPanel.add(lblABV, "cell 0 5,alignx trailing");
		
		fieldABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldABV.setText("0.00");
		fieldABV.setEditable(false);
		AlcoholSubPanel.add(fieldABV, "cell 1 5,growx");
		
		//DecimalFormat myFormatter = new DecimalFormat("0.000");
		//String output = myFormatter.format(value);
		//System.out.println(value + " " + pattern + " " + output);
		
		//END TESTING
		
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(AlcoholPanel, "cell 0 0,grow");
		AlcoholPanel.setVisible(false);

		AlcoholPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(AlcoholPanelStatus.equals("Initialized")) {
			AlcoholPanel.setVisible(false);
			AlcoholPanel.remove(AlcoholHeader);
			AlcoholPanel.remove(AlcoholSubtitle);
			AlcoholPanel.remove(AlcoholSubPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(AlcoholPanel);
			AlcoholPanelStatus = "DeInitialized";
		}
	}
	

}