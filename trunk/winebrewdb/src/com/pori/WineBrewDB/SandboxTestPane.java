package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[150px:n:150px,grow,left][150px:n:150px]", "[175px:n:175px,grow][][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		
		JTextPane txtpnTheStandardFormula = new JTextPane();
		txtpnTheStandardFormula.setText("The standard formula for calculating %ABV is: \"(OG - FG) / F = %ABV\". However the factor (F) needed for an accurate result is actually dependant on the amount of alcohol present in the wine. Two differing methods of calculation are provided here as follows:\r\n\r\nThe \"HMRC Method\" extrapolates from HMRC's published factors (HMRC Ref: Notice 226 (November 2011)) used for calculating ABV in beer so that it can be applied to wine. This is the most accurate method provided here.\r\nThe \"CJJ Berry Method\" (from \"First Steps in Winemaking\" ISBN: ) uses a constant 7.36 for the factor (F).");
		add(txtpnTheStandardFormula, "cell 0 0 2 1,growx,aligny top");
		
		JLabel lblThisUsesThe = new JLabel("HMRC Method");
		add(lblThisUsesThe, "cell 0 1");
		
		JLabel lblStartingSG = new JLabel("Starting SG (e.g. 1.080)");
		add(lblStartingSG, "cell 0 2,alignx trailing");
		
		JFormattedTextField fieldStartingSG = new JFormattedTextField();
		fieldStartingSG.setText("0.000");
		add(fieldStartingSG, "cell 1 2,growx");
		
		JLabel lblFinishingSG = new JLabel("Finishing SG (e.g. 0.994)");
		add(lblFinishingSG, "cell 0 3,alignx trailing");
		
		JFormattedTextField fieldFinishingSG = new JFormattedTextField();
		fieldFinishingSG.setText("0.000");
		add(fieldFinishingSG, "cell 1 3,growx");
		
		JButton btnCalculateABV = new JButton("Submit");
		add(btnCalculateABV, "cell 1 4");
		
		JLabel lblABV = new JLabel("ABV %");
		add(lblABV, "cell 0 5,alignx trailing");
		
		JFormattedTextField fieldABV = new JFormattedTextField();
		fieldABV.setText("0");
		fieldABV.setEditable(false);
		add(fieldABV, "cell 1 5,growx");
		

	}

}
