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
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;

public class TemperatureAdjustedSGPanel extends JPanel {

	private static final long serialVersionUID = -2859114835526520455L;
	public static JPanel TemperatureAdjustedSGPanel;
	public static JEditorPane TemperatureAdjustedSGText;
	public static JLabel TemperatureAdjustedSGHeader;
	public static JLabel TemperatureAdjustedSGSubtitle;
	public static JPanel TemperatureAdjustedSGSubPanel;
	public static JTextPane txtSugarToSGInfo;
	public static JToggleButton btnCelcius;
	public static JToggleButton btnFahrenheit;
	public static JToggleButton btnKelvin;
	public static JLabel lblTemperature;
	public static JFormattedTextField fieldTemperature;
	public static JLabel lblHydrometerTemperature;
	public static JFormattedTextField fieldHydrometerTemperature;
	public static JLabel lblSG;
	public static JFormattedTextField fieldSG;
	public static JButton btnCalculateTemperatureAdjustedSG;
	public static JLabel lblResult;
	public static JFormattedTextField fieldResult;
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
		
		
		//Subpanel
		TemperatureAdjustedSGSubPanel = new JPanel();
		TemperatureAdjustedSGSubPanel.setBackground(Color.WHITE);
		TemperatureAdjustedSGSubPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		TemperatureAdjustedSGSubPanel.setLayout(new MigLayout("", "[205px:n:205px,left][205px:n:205px][205px:n:205px][grow]", "[60px:n,60px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
		TemperatureAdjustedSGPanel.add(TemperatureAdjustedSGSubPanel, "cell 0 2,grow");
							
								
		//Add Calculators to subpanel		
		txtSugarToSGInfo = new JTextPane();
		//TODO Umm...all text panes are still editable
		txtSugarToSGInfo.setText("Hydrometers are calibrated to be read at a specific temperature as temperature affects the density of water (they are usually calibrated to either 15°C or 20°C, it will say on the hydrometer). If you are taking a reading at a temperature your hydrometer is not calibrated for then you will need to use the following calculator to adjust the SG.");
		TemperatureAdjustedSGSubPanel.add(txtSugarToSGInfo, "cell 0 0 6 1,grow");
		
		btnCelcius = new JToggleButton("°C");
		btnCelcius.setSelected(true);
		TemperatureAdjustedSGSubPanel.add(btnCelcius, "cell 0 1,growx");
		
		btnFahrenheit = new JToggleButton("°F");
		TemperatureAdjustedSGSubPanel.add(btnFahrenheit, "cell 1 1,growx");
		
		btnKelvin = new JToggleButton("K");
		TemperatureAdjustedSGSubPanel.add(btnKelvin, "cell 2 1,growx");
		
					
		lblTemperature = new JLabel("Temperature of Liquid");
		TemperatureAdjustedSGSubPanel.add(lblTemperature, "cell 0 2,alignx trailing");
							
		fieldTemperature = new JFormattedTextField(new DecimalFormat("##0.##"));
		fieldTemperature.setText("0");
		TemperatureAdjustedSGSubPanel.add(fieldTemperature, "cell 1 2 2,growx");
		
		lblHydrometerTemperature = new JLabel("Hydrometer Calibrated Temperature");
		TemperatureAdjustedSGSubPanel.add(lblHydrometerTemperature, "cell 0 3,alignx trailing");
							
		fieldHydrometerTemperature = new JFormattedTextField(new DecimalFormat("##0.##"));
		fieldHydrometerTemperature.setText("20");
		TemperatureAdjustedSGSubPanel.add(fieldHydrometerTemperature, "cell 1 3 2,growx");
						
		lblSG = new JLabel("SG");
		TemperatureAdjustedSGSubPanel.add(lblSG, "cell 0 4,alignx trailing");
								
		fieldSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldSG.setText("0.000");
		TemperatureAdjustedSGSubPanel.add(fieldSG, "cell 1 4 2,growx");
						
		btnCalculateTemperatureAdjustedSG = new JButton("Submit");
		TemperatureAdjustedSGSubPanel.add(btnCalculateTemperatureAdjustedSG, "cell 1 5");
								
		lblResult = new JLabel("Temperature Adjusted SG");
		TemperatureAdjustedSGSubPanel.add(lblResult, "cell 0 6,alignx trailing");
								
		fieldResult = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldResult.setText("0.000");
		fieldResult.setEditable(false);
		TemperatureAdjustedSGSubPanel.add(fieldResult, "cell 1 6 2,growx");
								
									
		//Add button listeners
		btnCelcius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFahrenheit.setSelected(false);
				btnKelvin.setSelected(false);
				btnCelcius.setSelected(true);
			}
		});
		btnFahrenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCelcius.setSelected(false);
				btnKelvin.setSelected(false);
				btnFahrenheit.setSelected(true);
			}
		});
		btnKelvin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCelcius.setSelected(false);
				btnFahrenheit.setSelected(false);
				btnKelvin.setSelected(true);
			}
		});
		
		btnCalculateTemperatureAdjustedSG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateTemperatureAdjustedSG();
			}
		});
		
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
			MainWindow.WineBrewDBFrame.getContentPane().remove(TemperatureAdjustedSGPanel);
			TemperatureAdjustedSGPanelStatus = "DeInitialized";
		}
	}
	
	
	public static void CalculateTemperatureAdjustedSG(){
		//TODO http://www.engineeringtoolbox.com/water-temperature-specific-gravity-d_1179.html
		//http://hbd.org/brewery/library/HydromCorr0992.html
		if(btnCelcius.isSelected()){
			fieldResult.setText(new BigDecimal("1").toString());
		}
		if(btnFahrenheit.isSelected()){
			fieldResult.setText(new BigDecimal("2").toString());
		}
		if(btnKelvin.isSelected()){
			fieldResult.setText(new BigDecimal("3").toString());
		}

	}

}