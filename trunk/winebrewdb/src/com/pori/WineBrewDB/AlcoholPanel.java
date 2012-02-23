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
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

public class AlcoholPanel extends JPanel {

	private static final long serialVersionUID = -8187974481712023349L;
	public static JPanel AlcoholPanel;
	public static JPanel AlcoholSubPanel;
	public static JLabel AlcoholHeader;
	public static JLabel AlcoholSubtitle;
	public static JLabel lblHStartingSG;
	public static JLabel lblHFinishingSG;
	public static JLabel lblHABV;
	public static JLabel lblHAlcoholMethod;
	public static JTextPane txtAlcoholInfo;
	public static JFormattedTextField fieldHStartingSG;
	public static JFormattedTextField fieldHFinishingSG;
	public static JFormattedTextField fieldHABV;
	public static JButton btnHCalculateABV;
	public static JLabel lblCStartingSG;
	public static JLabel lblCFinishingSG;
	public static JLabel lblCABV;
	public static JLabel lblCAlcoholMethod;
	public static JFormattedTextField fieldCStartingSG;
	public static JFormattedTextField fieldCFinishingSG;
	public static JFormattedTextField fieldCABV;
	public static JButton btnCCalculateABV;
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
		AlcoholSubtitle = new JLabel("Calculate ABV % from SG.");
		AlcoholSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		AlcoholPanel.add(AlcoholSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Subpanel
		AlcoholSubPanel = new JPanel();
		AlcoholSubPanel.setBackground(Color.WHITE);
		AlcoholSubPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		AlcoholSubPanel.setLayout(new MigLayout("", "[135px:n:135px,left][90px:n:90px][75px:n:75px][135px:n:135px,left][90px:n:90px][grow]", "[150px:n,150px][30px:n:30px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		AlcoholPanel.add(AlcoholSubPanel, "cell 0 2,grow");
		
		
		//Add Calculators to subpanel		
		txtAlcoholInfo = new JTextPane();
		txtAlcoholInfo.setText("The standard formula for calculating %ABV is: \"(OG - FG) / F = %ABV\". However the factor (F) needed for an accurate result is actually dependant on the amount of alcohol present in the wine. Two differing methods of calculation are provided here as follows:\r\n\r\nThe \"HMRC Method\" extrapolates from HMRC's published factors (HMRC Ref: Notice 226 (November 2011)) used for calculating ABV in beer. This is the most accurate method provided here.\r\n\nThe \"CJJ Berry Method\" (from \"First Steps in Winemaking\" ISBN: 978-1854861399) uses a constant 7.36 for the factor (F).");
		AlcoholSubPanel.add(txtAlcoholInfo, "cell 0 0 6 1,grow");
		
		lblHAlcoholMethod = new JLabel("HMRC Method");
		AlcoholSubPanel.add(lblHAlcoholMethod, "cell 0 1");
		
		lblHStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		AlcoholSubPanel.add(lblHStartingSG, "cell 0 2,alignx trailing");
		
		fieldHStartingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldHStartingSG.setText("0.000");
		AlcoholSubPanel.add(fieldHStartingSG, "cell 1 2,growx");
		
		lblHFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		AlcoholSubPanel.add(lblHFinishingSG, "cell 0 3,alignx trailing");
		
		fieldHFinishingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldHFinishingSG.setText("0.000");
		AlcoholSubPanel.add(fieldHFinishingSG, "cell 1 3,growx");
		
		btnHCalculateABV = new JButton("Submit");
		AlcoholSubPanel.add(btnHCalculateABV, "cell 1 4");
		
		lblHABV = new JLabel("ABV %");
		AlcoholSubPanel.add(lblHABV, "cell 0 5,alignx trailing");
		
		fieldHABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldHABV.setText("0.00");
		fieldHABV.setEditable(false);
		AlcoholSubPanel.add(fieldHABV, "cell 1 5,growx");
		
		lblCAlcoholMethod = new JLabel("CJJ Berry Method");
		AlcoholSubPanel.add(lblCAlcoholMethod, "cell 3 1");
		
		lblCStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		AlcoholSubPanel.add(lblCStartingSG, "cell 3 2,alignx trailing");
		
		fieldCStartingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCStartingSG.setText("0.000");
		AlcoholSubPanel.add(fieldCStartingSG, "cell 4 2,growx");
		
		lblCFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		AlcoholSubPanel.add(lblCFinishingSG, "cell 3 3,alignx trailing");
		
		fieldCFinishingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCFinishingSG.setText("0.000");
		AlcoholSubPanel.add(fieldCFinishingSG, "cell 4 3,growx");
		
		btnCCalculateABV = new JButton("Submit");
		AlcoholSubPanel.add(btnCCalculateABV, "cell 4 4");
		
		lblCABV = new JLabel("ABV %");
		AlcoholSubPanel.add(lblCABV, "cell 3 5,alignx trailing");
		
		fieldCABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldCABV.setText("0.00");
		fieldCABV.setEditable(false);
		AlcoholSubPanel.add(fieldCABV, "cell 4 5,growx");

		
		//Add button listeners
		btnHCalculateABV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateAlcoholHMRC();
			}
		});
		
		btnCCalculateABV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateAlcoholCJJBerry();
			}
		});
		
		
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
	
	public static void CalculateAlcoholHMRC(){
		BigDecimal OG = new BigDecimal(fieldHStartingSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal FG = new BigDecimal(fieldHFinishingSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal Drop = OG.subtract(FG);
		BigDecimal interim = Drop.multiply(new BigDecimal("-0.0054"));
		BigDecimal Factor = interim.add(new BigDecimal("7.914"));
		BigDecimal Result = Drop.divide(Factor, 2, BigDecimal.ROUND_HALF_UP);
		
		fieldHABV.setText(Result.toString());		
	}
	
	public static void CalculateAlcoholCJJBerry(){
		BigDecimal OG = new BigDecimal(fieldCStartingSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal FG = new BigDecimal(fieldCFinishingSG.getText()).multiply(new BigDecimal("1000"));
		BigDecimal Drop = OG.subtract(FG);
		BigDecimal Result = Drop.divide(new BigDecimal("7.36"), 2, BigDecimal.ROUND_HALF_UP);
		
		fieldCABV.setText(Result.toString());	
	}

}