package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class MeasuresPanel extends JPanel {

	private static final long serialVersionUID = -7839159138359581270L;
	public static JPanel MeasuresPanel;
	public static JEditorPane MeasuresText;
	public static JLabel MeasuresHeader;
	public static JLabel MeasuresSubtitle;
	public static JTextField txtMeasuresPanel;
	public static String MeasuresPanelStatus = "DeInitialized";

	//public MeasuresPanel() {
	public static void InitializePanel(){
		
		MeasuresPanel = new JPanel();
		MeasuresPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		MeasuresHeader = new JLabel("Measures");
		MeasuresHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		MeasuresPanel.add(MeasuresHeader, "cell 0 0,grow");
		
		
		//Subtitle
		MeasuresSubtitle = new JLabel("Convert between various measures.");
		MeasuresSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		MeasuresPanel.add(MeasuresSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Some content
		txtMeasuresPanel = new JTextField();
		txtMeasuresPanel.setBackground(Color.WHITE);
		txtMeasuresPanel.setEditable(false);
		txtMeasuresPanel.setText("TODO: lbs oz <--> grams, tsp <--> tbsp, uk gallons <--> us gallons, uk gallons <--> litres, c <--> f,");
		txtMeasuresPanel.setColumns(10);
		MeasuresPanel.add(txtMeasuresPanel, "cell 0 2,grow");
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(MeasuresPanel, "cell 0 0,grow");
		MeasuresPanel.setVisible(false);

		MeasuresPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(MeasuresPanelStatus.equals("Initialized")) {
			MeasuresPanel.setVisible(false);
			MeasuresPanel.remove(MeasuresHeader);
			MeasuresPanel.remove(MeasuresSubtitle);
			MeasuresPanel.remove(txtMeasuresPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(MeasuresPanel);
			MeasuresPanelStatus = "DeInitialized";
		}
	}
	

}