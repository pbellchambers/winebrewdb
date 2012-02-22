package com.pori.WineBrewDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class DilutionPanel extends JPanel {

	private static final long serialVersionUID = -5260637755405545904L;
	public static JPanel DilutionPanel;
	public static JEditorPane DilutionText;
	public static JLabel DilutionHeader;
	public static JLabel DilutionSubtitle;
	public static JTextField txtDilutionPanel;
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
		DilutionSubtitle = new JLabel("Dilution subtitle");
		DilutionSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		DilutionPanel.add(DilutionSubtitle, "cell 0 1,growx,aligny top");
		
		//Some content
		txtDilutionPanel = new JTextField();
		txtDilutionPanel.setBackground(Color.WHITE);
		txtDilutionPanel.setText("DilutionPanel");
		txtDilutionPanel.setColumns(10);
		DilutionPanel.add(txtDilutionPanel, "cell 0 2,grow");
		
		
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
			DilutionPanel.remove(txtDilutionPanel);
			MainWindow.WineBrewDBFrame.getContentPane().remove(DilutionPanel);
			DilutionPanelStatus = "DeInitialized";
		}
	}
	

}