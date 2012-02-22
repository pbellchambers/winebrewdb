package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class AlcoholPanel extends JPanel {

	private static final long serialVersionUID = -8187974481712023349L;
	public static JPanel AlcoholPanel;
	public static JEditorPane AlcoholText;
	public static JLabel AlcoholHeader;
	public static JLabel AlcoholSubtitle;
	public static JTextField txtAlcoholPanel;
	public static String AlcoholPanelStatus = "DeInitialized";

	//public AlcoholPanel() {
	public static void InitializePanel(){
		
		AlcoholPanel = new JPanel();
		AlcoholPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		//Header
		AlcoholHeader = new JLabel("Alcohol");
		AlcoholHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		AlcoholPanel.add(AlcoholHeader, "cell 0 0,grow");
		//Subtitle
		AlcoholSubtitle = new JLabel("Alcohol subtitle");
		AlcoholSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		AlcoholPanel.add(AlcoholSubtitle, "cell 0 1,growx,aligny top");
		
		//Some content
		txtAlcoholPanel = new JTextField();
		txtAlcoholPanel.setBackground(Color.WHITE);
		txtAlcoholPanel.setText("AlcoholPanel");
		txtAlcoholPanel.setColumns(10);
		AlcoholPanel.add(txtAlcoholPanel, "cell 0 2,grow");
		
		
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
			AlcoholPanel.remove(txtAlcoholPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(AlcoholPanel);
			AlcoholPanelStatus = "DeInitialized";
		}
	}
	

}