package uk.co.pori.winebrewdb2.calculators2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import uk.co.pori.winebrewdb2.MainWindow;


import net.miginfocom.swing.MigLayout;

public class AlcoholPanel extends JPanel {

	private static final long serialVersionUID = -8187974481712023349L;
	static JPanel AlcoholSubPanel;
	private static JFormattedTextField fieldHStartingSG;
	private static JFormattedTextField fieldHFinishingSG;
	private static JFormattedTextField fieldHABV;
	private static JFormattedTextField fieldCStartingSG;
	private static JFormattedTextField fieldCFinishingSG;
	private static JFormattedTextField fieldCABV;
	private static String AlcoholPanelStatus = "DeInitialized";

	//public AlcoholPanel() {
	public static void InitializePanel(){
		

		//Subpanel
		AlcoholSubPanel = new JPanel();
		AlcoholSubPanel.setBackground(Color.WHITE);
		AlcoholSubPanel.setLayout(new MigLayout("", "[135px:n:135px,left][90px:n:90px][75px:n:75px][135px:n:135px,left][90px:n:90px][grow]", "[150px:n,150px][30px:n:30px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		
		
		//Add Calculators to subpanel		
		JTextPane txtAlcoholInfo = new JTextPane();
		txtAlcoholInfo.setText("The standard formula for calculating %ABV is: \"(OG - FG) / F = %ABV\". However the factor (F) needed for an accurate result is actually dependant on the amount of alcohol present in the wine. Two differing methods of calculation are provided here as follows:\r\n\r\nThe \"HMRC Method\" extrapolates from HMRC's published factors (HMRC Ref: Notice 226 (November 2011)) used for calculating ABV in beer. This is the most accurate method provided here.\r\n\nThe \"CJJ Berry Method\" (from \"First Steps in Winemaking\" ISBN: 978-1854861399) uses a constant 7.36 for the factor (F).");
		txtAlcoholInfo.setEditable(false);
		AlcoholSubPanel.add(txtAlcoholInfo, "cell 0 0 6 1,grow");
		
		JLabel lblHAlcoholMethod = new JLabel("HMRC Method");
		AlcoholSubPanel.add(lblHAlcoholMethod, "cell 0 1");
		
		JLabel lblHStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		AlcoholSubPanel.add(lblHStartingSG, "cell 0 2,alignx trailing");
		
		fieldHStartingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldHStartingSG.setText("0.000");
		AlcoholSubPanel.add(fieldHStartingSG, "cell 1 2,growx");
		
		JLabel lblHFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		AlcoholSubPanel.add(lblHFinishingSG, "cell 0 3,alignx trailing");
		
		fieldHFinishingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldHFinishingSG.setText("0.000");
		AlcoholSubPanel.add(fieldHFinishingSG, "cell 1 3,growx");
		
		JButton btnHCalculateABV = new JButton("Submit");
		AlcoholSubPanel.add(btnHCalculateABV, "cell 1 4");
		
		JLabel lblHABV = new JLabel("ABV %");
		AlcoholSubPanel.add(lblHABV, "cell 0 5,alignx trailing");
		
		fieldHABV = new JFormattedTextField(new DecimalFormat("#0.00"));
		fieldHABV.setText("0.00");
		fieldHABV.setEditable(false);
		AlcoholSubPanel.add(fieldHABV, "cell 1 5,growx");
		
		JLabel lblCAlcoholMethod = new JLabel("CJJ Berry Method");
		AlcoholSubPanel.add(lblCAlcoholMethod, "cell 3 1");
		
		JLabel lblCStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		AlcoholSubPanel.add(lblCStartingSG, "cell 3 2,alignx trailing");
		
		fieldCStartingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCStartingSG.setText("0.000");
		AlcoholSubPanel.add(fieldCStartingSG, "cell 4 2,growx");
		
		JLabel lblCFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		AlcoholSubPanel.add(lblCFinishingSG, "cell 3 3,alignx trailing");
		
		fieldCFinishingSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldCFinishingSG.setText("0.000");
		AlcoholSubPanel.add(fieldCFinishingSG, "cell 4 3,growx");
		
		JButton btnCCalculateABV = new JButton("Submit");
		AlcoholSubPanel.add(btnCCalculateABV, "cell 4 4");
		
		JLabel lblCABV = new JLabel("ABV %");
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
		MainWindow.WineBrewDBFrame.getContentPane().add(AlcoholSubPanel, "cell 0 0,grow");
		AlcoholSubPanel.setVisible(false);

		AlcoholPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(AlcoholPanelStatus.equals("Initialized")) {
			AlcoholSubPanel.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(AlcoholSubPanel);
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