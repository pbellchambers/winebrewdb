package uk.co.pori.winebrewdb.calculators;

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
import javax.swing.JToggleButton;

import uk.co.pori.winebrewdb.MainWindow;

import net.miginfocom.swing.MigLayout;

/**
 * This is the temperature adjusted SG calculator panel to be displayed as a tab on the main calculator panel tab bar.
 * 
 * @author Paul.Bellchambers
 *
 */
public class TemperatureAdjustedSGPanel extends JPanel {

	private static final long serialVersionUID = -2859114835526520455L;
	static JPanel TemperatureAdjustedSGSubPanel;
	private static JToggleButton btnCelcius;
	private static JToggleButton btnFahrenheit;
	private static JToggleButton btnKelvin;
	private static JFormattedTextField fieldTemperature;
	private static JFormattedTextField fieldHydrometerTemperature;
	private static JFormattedTextField fieldSG;
	private static JFormattedTextField fieldResult;
	private static String TemperatureAdjustedSGPanelStatus = "DeInitialised";

	/**
	 * Initialises the temperature adjusted SG calculator panel so that it can be viewed.
	 */
	public static void initialisePanel(){
		
		
		//Subpanel
		TemperatureAdjustedSGSubPanel = new JPanel();
		TemperatureAdjustedSGSubPanel.setBackground(Color.WHITE);
		TemperatureAdjustedSGSubPanel.setLayout(new MigLayout("", "[205px:n:205px,left][205px:n:205px][205px:n:205px][grow]", "[60px:n,60px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px][40px:n:40px]"));
							
								
		//Add Calculators to subpanel		
		JTextPane txtTemperatureAdjustedSG = new JTextPane();
		txtTemperatureAdjustedSG.setText("Hydrometers are calibrated to be read at a specific temperature as temperature affects the density of water (they are usually calibrated to either 15°C or 20°C, it will say on the hydrometer). If you are taking a reading at a temperature your hydrometer is not calibrated for then you will need to use the following calculator to adjust the SG.");
		txtTemperatureAdjustedSG.setEditable(false);
		TemperatureAdjustedSGSubPanel.add(txtTemperatureAdjustedSG, "cell 0 0 6 1,grow");
		
		btnCelcius = new JToggleButton("°C");
		btnCelcius.setSelected(true);
		TemperatureAdjustedSGSubPanel.add(btnCelcius, "cell 0 1,growx");
		
		btnFahrenheit = new JToggleButton("°F");
		TemperatureAdjustedSGSubPanel.add(btnFahrenheit, "cell 1 1,growx");
		
		btnKelvin = new JToggleButton("K");
		TemperatureAdjustedSGSubPanel.add(btnKelvin, "cell 2 1,growx");
		
					
		JLabel lblTemperature = new JLabel("Temperature of Liquid");
		TemperatureAdjustedSGSubPanel.add(lblTemperature, "cell 0 2,alignx trailing");
							
		fieldTemperature = new JFormattedTextField(new DecimalFormat("##0.##"));
		fieldTemperature.setText("20.0");
		TemperatureAdjustedSGSubPanel.add(fieldTemperature, "cell 1 2 2,growx");
		
		JLabel lblHydrometerTemperature = new JLabel("Hydrometer Calibrated Temperature");
		TemperatureAdjustedSGSubPanel.add(lblHydrometerTemperature, "cell 0 3,alignx trailing");
							
		fieldHydrometerTemperature = new JFormattedTextField(new DecimalFormat("##0.##"));
		fieldHydrometerTemperature.setText("20");
		TemperatureAdjustedSGSubPanel.add(fieldHydrometerTemperature, "cell 1 3 2,growx");
						
		JLabel lblSG = new JLabel("SG");
		TemperatureAdjustedSGSubPanel.add(lblSG, "cell 0 4,alignx trailing");
								
		fieldSG = new JFormattedTextField(new DecimalFormat("0.000"));
		fieldSG.setText("0.000");
		TemperatureAdjustedSGSubPanel.add(fieldSG, "cell 1 4 2,growx");
						
		JButton btnCalculateTemperatureAdjustedSG = new JButton("Submit");
		TemperatureAdjustedSGSubPanel.add(btnCalculateTemperatureAdjustedSG, "cell 1 5");
								
		JLabel lblResult = new JLabel("Temperature Adjusted SG");
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
				calculateTemperatureAdjustedSG();
			}
		});
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(TemperatureAdjustedSGSubPanel, "cell 0 0,grow");
		TemperatureAdjustedSGSubPanel.setVisible(false);

		TemperatureAdjustedSGPanelStatus = "Initialised";
	}

	/**
	 * De-initialises the temperature adjusted SG calculator panel so that it is not visible on screen.
	 */	
	public static void deinitialisePanel(){
		if(TemperatureAdjustedSGPanelStatus.equals("Initialised")) {
			TemperatureAdjustedSGSubPanel.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(TemperatureAdjustedSGSubPanel);
			TemperatureAdjustedSGPanelStatus = "DeInitialised";
		}
	}
	
	/**
	 * Calculates the temperature adjusted SG based on the user entered values.
	 * 
	 * Formula used as follows:
	 * TemperatureAdjustedSG = r * ((1.00130346 - (0.000134722124 * t) + (0.00000204052596 * t^2) - (0.00000000232820948 * t^3)) / (1.00130346 - (0.000134722124 * c) + (0.00000204052596 * c^2) - (0.00000000232820948 * c^3)))
	 * 
	 * Where:
	 * r = SG Reading
	 * t = Current Temperature
	 * c = Hydrometer Calibration Temperature
	 * 
	 * Note: Calculation uses °F, so temperature is converted first if needed.
	 * 
	 */
	private static void calculateTemperatureAdjustedSG(){
		if(btnCelcius.isSelected()){
			BigDecimal SpecificGravity = new BigDecimal(fieldSG.getText());
			BigDecimal TemperatureCelcius = new BigDecimal(fieldTemperature.getText());
			BigDecimal TemperatureFahrenheit = new BigDecimal("32").add(TemperatureCelcius.multiply(new BigDecimal("1.8")));
			BigDecimal HydrometerTemperatureCelcius = new BigDecimal(fieldHydrometerTemperature.getText());
			BigDecimal HydrometerTemperatureFahrenheit = new BigDecimal("32").add(HydrometerTemperatureCelcius.multiply(new BigDecimal("1.8")));
			BigDecimal TemperatureFahrenheitP2 = TemperatureFahrenheit.pow(2);
			BigDecimal TemperatureFahrenheitP3 = TemperatureFahrenheit.pow(3);
			BigDecimal HydrometerTemperatureFahrenheitP2 = HydrometerTemperatureFahrenheit.pow(2);
			BigDecimal HydrometerTemperatureFahrenheitP3 = HydrometerTemperatureFahrenheit.pow(3);
			BigDecimal a1 =	new BigDecimal("0.000134722124").multiply(TemperatureFahrenheit);	
			BigDecimal a2 = new BigDecimal("0.00000204052596").multiply(TemperatureFahrenheitP2);
			BigDecimal a3 = new BigDecimal("0.00000000232820948").multiply(TemperatureFahrenheitP3);
			BigDecimal a = new BigDecimal("1.00130346").subtract(a1).add(a2).subtract(a3);
			BigDecimal b1 =	new BigDecimal("0.000134722124").multiply(HydrometerTemperatureFahrenheit);	
			BigDecimal b2 = new BigDecimal("0.00000204052596").multiply(HydrometerTemperatureFahrenheitP2);
			BigDecimal b3 = new BigDecimal("0.00000000232820948").multiply(HydrometerTemperatureFahrenheitP3);
			BigDecimal b = new BigDecimal("1.00130346").subtract(b1).add(b2).subtract(b3);
			BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
			BigDecimal result = SpecificGravity.multiply(c);
			
			fieldResult.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(btnFahrenheit.isSelected()){
			BigDecimal SpecificGravity = new BigDecimal(fieldSG.getText());
			BigDecimal TemperatureFahrenheit = new BigDecimal(fieldTemperature.getText());
			BigDecimal HydrometerTemperatureFahrenheit = new BigDecimal(fieldHydrometerTemperature.getText());
			BigDecimal TemperatureFahrenheitP2 = TemperatureFahrenheit.pow(2);
			BigDecimal TemperatureFahrenheitP3 = TemperatureFahrenheit.pow(3);
			BigDecimal HydrometerTemperatureFahrenheitP2 = HydrometerTemperatureFahrenheit.pow(2);
			BigDecimal HydrometerTemperatureFahrenheitP3 = HydrometerTemperatureFahrenheit.pow(3);
			BigDecimal a1 =	new BigDecimal("0.000134722124").multiply(TemperatureFahrenheit);	
			BigDecimal a2 = new BigDecimal("0.00000204052596").multiply(TemperatureFahrenheitP2);
			BigDecimal a3 = new BigDecimal("0.00000000232820948").multiply(TemperatureFahrenheitP3);
			BigDecimal a = new BigDecimal("1.00130346").subtract(a1).add(a2).subtract(a3);
			BigDecimal b1 =	new BigDecimal("0.000134722124").multiply(HydrometerTemperatureFahrenheit);	
			BigDecimal b2 = new BigDecimal("0.00000204052596").multiply(HydrometerTemperatureFahrenheitP2);
			BigDecimal b3 = new BigDecimal("0.00000000232820948").multiply(HydrometerTemperatureFahrenheitP3);
			BigDecimal b = new BigDecimal("1.00130346").subtract(b1).add(b2).subtract(b3);
			BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
			BigDecimal result = SpecificGravity.multiply(c);
			
			fieldResult.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(btnKelvin.isSelected()){
			BigDecimal SpecificGravity = new BigDecimal(fieldSG.getText());
			BigDecimal TemperatureKelvin = new BigDecimal(fieldTemperature.getText());
			BigDecimal TemperatureFahrenheit = new BigDecimal("32").add(new BigDecimal("1.8").multiply(TemperatureKelvin.subtract(new BigDecimal("273"))));
			BigDecimal HydrometerTemperatureKelvin = new BigDecimal(fieldHydrometerTemperature.getText());			
			BigDecimal HydrometerTemperatureFahrenheit = new BigDecimal("32").add(new BigDecimal("1.8").multiply(HydrometerTemperatureKelvin.subtract(new BigDecimal("273"))));
			BigDecimal TemperatureFahrenheitP2 = TemperatureFahrenheit.pow(2);
			BigDecimal TemperatureFahrenheitP3 = TemperatureFahrenheit.pow(3);
			BigDecimal HydrometerTemperatureFahrenheitP2 = HydrometerTemperatureFahrenheit.pow(2);
			BigDecimal HydrometerTemperatureFahrenheitP3 = HydrometerTemperatureFahrenheit.pow(3);
			BigDecimal a1 =	new BigDecimal("0.000134722124").multiply(TemperatureFahrenheit);	
			BigDecimal a2 = new BigDecimal("0.00000204052596").multiply(TemperatureFahrenheitP2);
			BigDecimal a3 = new BigDecimal("0.00000000232820948").multiply(TemperatureFahrenheitP3);
			BigDecimal a = new BigDecimal("1.00130346").subtract(a1).add(a2).subtract(a3);
			BigDecimal b1 =	new BigDecimal("0.000134722124").multiply(HydrometerTemperatureFahrenheit);	
			BigDecimal b2 = new BigDecimal("0.00000204052596").multiply(HydrometerTemperatureFahrenheitP2);
			BigDecimal b3 = new BigDecimal("0.00000000232820948").multiply(HydrometerTemperatureFahrenheitP3);
			BigDecimal b = new BigDecimal("1.00130346").subtract(b1).add(b2).subtract(b3);
			BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
			BigDecimal result = SpecificGravity.multiply(c);
			
			fieldResult.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
		}

	}

}