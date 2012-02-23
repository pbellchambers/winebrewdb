package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class SugarToSGPanel extends JPanel {

	private static final long serialVersionUID = -6105676849516060237L;
	public static JPanel SugarToSGPanel;
	public static JEditorPane SugarToSGText;
	public static JLabel SugarToSGHeader;
	public static JLabel SugarToSGSubtitle;
	public static JTextField txtSugarToSGPanel;
	public static String SugarToSGPanelStatus = "DeInitialized";

	//public SugarToSGPanel() {
	public static void InitializePanel(){
		
		SugarToSGPanel = new JPanel();
		SugarToSGPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		SugarToSGHeader = new JLabel("Sugar to SG");
		SugarToSGHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		SugarToSGPanel.add(SugarToSGHeader, "cell 0 0,grow");
		
		
		//Subtitle
		SugarToSGSubtitle = new JLabel("Calculate the sugar needed to reach a required SG.");
		SugarToSGSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		SugarToSGPanel.add(SugarToSGSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Some content
		txtSugarToSGPanel = new JTextField();
		txtSugarToSGPanel.setBackground(Color.WHITE);
		txtSugarToSGPanel.setText("SugarToSGPanel");
		txtSugarToSGPanel.setColumns(10);
		SugarToSGPanel.add(txtSugarToSGPanel, "cell 0 2,grow");
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(SugarToSGPanel, "cell 0 0,grow");
		SugarToSGPanel.setVisible(false);

		SugarToSGPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(SugarToSGPanelStatus.equals("Initialized")) {
			SugarToSGPanel.setVisible(false);
			SugarToSGPanel.remove(SugarToSGHeader);
			SugarToSGPanel.remove(SugarToSGSubtitle);
			SugarToSGPanel.remove(txtSugarToSGPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(SugarToSGPanel);
			SugarToSGPanelStatus = "DeInitialized";
		}
	}
	

}