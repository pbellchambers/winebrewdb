package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class TemperatureAdjustedSGPanel extends JPanel {

	private static final long serialVersionUID = -2859114835526520455L;
	public static JPanel TemperatureAdjustedSGPanel;
	public static JEditorPane TemperatureAdjustedSGText;
	public static JLabel TemperatureAdjustedSGHeader;
	public static JLabel TemperatureAdjustedSGSubtitle;
	public static JTextField txtTemperatureAdjustedSGPanel;
	public static String TemperatureAdjustedSGPanelStatus = "DeInitialized";

	//public TemperatureAdjustedSGPanel() {
	public static void InitializePanel(){
		
		TemperatureAdjustedSGPanel = new JPanel();
		TemperatureAdjustedSGPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		TemperatureAdjustedSGHeader = new JLabel("Temperature Adjusted SG");
		TemperatureAdjustedSGHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		TemperatureAdjustedSGPanel.add(TemperatureAdjustedSGHeader, "cell 0 0,grow");
		
		
		//Subtitle
		TemperatureAdjustedSGSubtitle = new JLabel("Calculate the Temperature Adjusted SG.");
		TemperatureAdjustedSGSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		TemperatureAdjustedSGPanel.add(TemperatureAdjustedSGSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Some content
		txtTemperatureAdjustedSGPanel = new JTextField();
		txtTemperatureAdjustedSGPanel.setBackground(Color.WHITE);
		txtTemperatureAdjustedSGPanel.setText("TemperatureAdjustedSGPanel");
		txtTemperatureAdjustedSGPanel.setColumns(10);
		TemperatureAdjustedSGPanel.add(txtTemperatureAdjustedSGPanel, "cell 0 2,grow");
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(TemperatureAdjustedSGPanel, "cell 0 0,grow");
		TemperatureAdjustedSGPanel.setVisible(false);

		TemperatureAdjustedSGPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(TemperatureAdjustedSGPanelStatus.equals("Initialized")) {
			TemperatureAdjustedSGPanel.setVisible(false);
			TemperatureAdjustedSGPanel.remove(TemperatureAdjustedSGHeader);
			TemperatureAdjustedSGPanel.remove(TemperatureAdjustedSGSubtitle);
			TemperatureAdjustedSGPanel.remove(txtTemperatureAdjustedSGPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(TemperatureAdjustedSGPanel);
			TemperatureAdjustedSGPanelStatus = "DeInitialized";
		}
	}
	

}