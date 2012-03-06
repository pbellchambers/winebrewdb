package com.pori.WineBrewDB.Calculators;

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

import com.pori.WineBrewDB.MainWindow;

import net.miginfocom.swing.MigLayout;

public class MeasuresPanel extends JPanel {

	private static final long serialVersionUID = -7839159138359581270L;
	public static String MeasuresPanelStatus = "DeInitialized";
	public static JPanel MeasuresSubPanel;
	public static JTextPane txtMeasuresInfo;
	public static JLabel lblWeight;
	public static JLabel lbllbs;
	public static JFormattedTextField fieldlbs;
	public static JLabel lbloz;
	public static JFormattedTextField fieldoz;
	public static JButton btnWeightToGrams;
	public static JButton btnWeightToOz;
	public static JLabel lblgrams;
	public static JFormattedTextField fieldgrams;
	public static JLabel lblSpoons;
	public static JLabel lbltsp;
	public static JFormattedTextField fieldtsp;
	public static JButton btnTspToTbsp;
	public static JButton btnTbspToTsp;
	public static JLabel lbltbsp;
	public static JFormattedTextField fieldtbsp;
	public static JLabel lblUKUSGallons;
	public static JLabel lblUKGallons;
	public static JFormattedTextField fieldUKGallons;
	public static JButton btnUKToUSGallons;
	public static JButton btnUSToUKGallons;
	public static JLabel lblUSGallons;
	public static JFormattedTextField fieldUSGallons;
	public static JLabel lblUKGallonsLitres;
	public static JLabel lblUKGallonsB;
	public static JFormattedTextField fieldUKGallonsB;
	public static JButton btnUKGallonsToLitres;
	public static JButton btnLitresToUKGallons;
	public static JLabel lblLitres;
	public static JFormattedTextField fieldLitres;
	public static JLabel lblCToF;
	public static JLabel lblC;
	public static JFormattedTextField fieldC;
	public static JButton btnCToF;
	public static JButton btnFToC;
	public static JLabel lblF;
	public static JFormattedTextField fieldF;
	
	public static String AlcoholPanelStatus = "DeInitialized";
	
	
	//public MeasuresPanel() {
	public static void InitializePanel(){
			
		
		//Subpanel
		MeasuresSubPanel = new JPanel();
		MeasuresSubPanel.setBackground(Color.WHITE);
		MeasuresSubPanel.setLayout(new MigLayout("", "[20px:n:20px,left][15px:n:15px][20px:n:20px,left][60px:n:60px][40px:n:40px][40px:n:40px][60px:n:60px,left][60px:n:60px][60px:n:60px][60px:n:60px,left][60px:n:60px][40px:n:40px][40px:n:40px][60px:n:60px,left][60px:n:60px][grow]", "[5px:n,5px][15px:n:15px][20px:n:20px][40px:n:40px][40px:n:40px][20px:n:20px][40px:n:40px][40px:n:40px][20px:n:20px][40px:n:40px]"));
				
				
		//Add Calculators to subpanel		
		txtMeasuresInfo = new JTextPane();
		txtMeasuresInfo.setText("");
		txtMeasuresInfo.setEditable(false);
		MeasuresSubPanel.add(txtMeasuresInfo, "cell 0 0 16,grow");
				
		lblWeight = new JLabel("Weight");
		MeasuresSubPanel.add(lblWeight, "cell 0 1 3");
		
		lbllbs = new JLabel("Lbs");
		MeasuresSubPanel.add(lbllbs, "cell 2 2,alignx trailing");
				
		fieldlbs = new JFormattedTextField(new DecimalFormat("####0"));
		fieldlbs.setText("1");
		MeasuresSubPanel.add(fieldlbs, "cell 3 2,growx");
				
		lbloz = new JLabel("Oz");
		MeasuresSubPanel.add(lbloz, "cell 2 3,alignx trailing");
				
		fieldoz = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldoz.setText("8.00");
		MeasuresSubPanel.add(fieldoz, "cell 3 3,growx");
				
		btnWeightToGrams = new JButton(">");
		MeasuresSubPanel.add(btnWeightToGrams, "cell 4 3");
		
		btnWeightToOz = new JButton("<");
		MeasuresSubPanel.add(btnWeightToOz, "cell 5 3");
				
		lblgrams = new JLabel("Grams");
		MeasuresSubPanel.add(lblgrams, "cell 7 3,alignx left");
				
		fieldgrams = new JFormattedTextField(new DecimalFormat("#######0.0"));
		fieldgrams.setText("680.4");
		MeasuresSubPanel.add(fieldgrams, "cell 6 3,growx");
				
		lblSpoons = new JLabel("Spoons");
		MeasuresSubPanel.add(lblSpoons, "cell 9 1");

		lbltsp = new JLabel("Tsp");
		MeasuresSubPanel.add(lbltsp, "cell 9 3,alignx trailing");
				
		fieldtsp = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldtsp.setText("3.00");
		MeasuresSubPanel.add(fieldtsp, "cell 10 3,growx");
				
		btnTspToTbsp = new JButton(">");
		MeasuresSubPanel.add(btnTspToTbsp, "cell 11 3");

		btnTbspToTsp = new JButton("<");
		MeasuresSubPanel.add(btnTbspToTsp, "cell 12 3");
				
		lbltbsp = new JLabel("Tbsp");
		MeasuresSubPanel.add(lbltbsp, "cell 14 3,alignx left");
				
		fieldtbsp = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldtbsp.setText("1.00");
		MeasuresSubPanel.add(fieldtbsp, "cell 13 3,growx");
		
		lblUKUSGallons = new JLabel("UK/US Gallons");
		MeasuresSubPanel.add(lblUKUSGallons, "cell 0 5 3");

		lblUKGallons = new JLabel("UK Gallons");
		MeasuresSubPanel.add(lblUKGallons, "cell 0 6 3,alignx trailing");
				
		fieldUKGallons = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUKGallons.setText("1.000");
		MeasuresSubPanel.add(fieldUKGallons, "cell 3 6,growx");
				
		btnUKToUSGallons = new JButton(">");
		MeasuresSubPanel.add(btnUKToUSGallons, "cell 4 6");

		btnUSToUKGallons = new JButton("<");
		MeasuresSubPanel.add(btnUSToUKGallons, "cell 5 6");
				
		lblUSGallons = new JLabel("US Gallons");
		MeasuresSubPanel.add(lblUSGallons, "cell 7 6,alignx left");
				
		fieldUSGallons = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUSGallons.setText("1.201");
		MeasuresSubPanel.add(fieldUSGallons, "cell 6 6,growx");
		
		lblUKGallonsLitres = new JLabel("Gallons/Litres");
		MeasuresSubPanel.add(lblUKGallonsLitres, "cell 9 5");

		lblUKGallonsB = new JLabel("UK Gallons");
		MeasuresSubPanel.add(lblUKGallonsB, "cell 9 6,alignx trailing");
				
		fieldUKGallonsB = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUKGallonsB.setText("1.000");
		MeasuresSubPanel.add(fieldUKGallonsB, "cell 10 6,growx");
				
		btnUKGallonsToLitres = new JButton(">");
		MeasuresSubPanel.add(btnUKGallonsToLitres, "cell 11 6");

		btnLitresToUKGallons = new JButton("<");
		MeasuresSubPanel.add(btnLitresToUKGallons, "cell 12 6");
				
		lblLitres = new JLabel("Litres");
		MeasuresSubPanel.add(lblLitres, "cell 14 6,alignx left");
				
		fieldLitres = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldLitres.setText("4.546");
		MeasuresSubPanel.add(fieldLitres, "cell 13 6,growx");
		
		lblCToF = new JLabel("Temperature");
		MeasuresSubPanel.add(lblCToF, "cell 0 8 3");

		lblC = new JLabel("°C");
		MeasuresSubPanel.add(lblC, "cell 0 9 3,alignx trailing");
				
		fieldC = new JFormattedTextField(new DecimalFormat("####0.0"));
		fieldC.setText("0.0");
		MeasuresSubPanel.add(fieldC, "cell 3 9,growx");
				
		btnCToF = new JButton(">");
		MeasuresSubPanel.add(btnCToF, "cell 4 9");

		btnFToC = new JButton("<");
		MeasuresSubPanel.add(btnFToC, "cell 5 9");
				
		lblF = new JLabel("°F");
		MeasuresSubPanel.add(lblF, "cell 7 9,alignx left");
				
		fieldF = new JFormattedTextField(new DecimalFormat("####0.0"));
		fieldF.setText("32.0");
		MeasuresSubPanel.add(fieldF, "cell 6 9,growx");

				
		//Add button listeners
		btnWeightToGrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateWeightToGrams();
			}
		});
				
		btnWeightToOz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateWeightToOz();
			}
		});
		
		btnTspToTbsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateTspToTbsp();
			}
		});
				
		btnTbspToTsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateTbspToTsp();
			}
		});
		
		btnUKToUSGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateUKToUSGallons();
			}
		});
				
		btnUSToUKGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateUSToUKGallons();
			}
		});
		
		btnUKGallonsToLitres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateUKGallonsToLitres();
			}
		});
				
		btnLitresToUKGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateLitresToUKGallons();
			}
		});
		
		btnCToF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateCToF();
			}
		});
				
		btnFToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateFToC();
			}
		});
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(MeasuresSubPanel, "cell 0 0,grow");
		MeasuresSubPanel.setVisible(false);

		MeasuresPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(MeasuresPanelStatus.equals("Initialized")) {
			MeasuresSubPanel.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(MeasuresSubPanel);
			MeasuresPanelStatus = "DeInitialized";
		}
	}
	
	public static void CalculateWeightToGrams(){
		BigDecimal lbs = new BigDecimal(fieldlbs.getText());
		BigDecimal oz = new BigDecimal(fieldoz.getText());
		BigDecimal totaloz = oz.add(lbs.multiply(new BigDecimal("16")));
		BigDecimal result = totaloz.multiply(new BigDecimal("28.3495231"));
		
		fieldgrams.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateWeightToOz(){
		BigDecimal grams = new BigDecimal(fieldgrams.getText());
		BigDecimal totaloz = grams.divide(new BigDecimal("28.3495231"), 5, BigDecimal.ROUND_HALF_UP);
		BigDecimal lbs = totaloz.divide(new BigDecimal("16"), 0, BigDecimal.ROUND_DOWN);
		BigDecimal oz = totaloz.subtract(lbs.multiply(new BigDecimal("16")));

		fieldlbs.setText(lbs.toString());
		fieldoz.setText(oz.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateTspToTbsp(){
		BigDecimal tsp = new BigDecimal(fieldtsp.getText());
		BigDecimal result = tsp.divide(new BigDecimal("3"), 3, BigDecimal.ROUND_HALF_UP);
		
		fieldtbsp.setText(result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateTbspToTsp(){
		BigDecimal tbsp = new BigDecimal(fieldtbsp.getText());
		BigDecimal result = tbsp.multiply(new BigDecimal("3"));
		
		fieldtsp.setText(result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateUKToUSGallons(){
		BigDecimal UKGallons = new BigDecimal(fieldUKGallons.getText());
		BigDecimal result = UKGallons.multiply(new BigDecimal("1.20095042"));
		
		fieldUSGallons.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateUSToUKGallons(){
		BigDecimal USGallons = new BigDecimal(fieldUSGallons.getText());
		BigDecimal result = USGallons.divide(new BigDecimal("1.20095042"), 10, BigDecimal.ROUND_HALF_UP);
		
		fieldUKGallons.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateUKGallonsToLitres(){
		BigDecimal UKGallonsB = new BigDecimal(fieldUKGallonsB.getText());
		BigDecimal result = UKGallonsB.multiply(new BigDecimal("4.54609188"));
		
		fieldLitres.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateLitresToUKGallons(){
		BigDecimal Litres = new BigDecimal(fieldLitres.getText());
		BigDecimal result = Litres.divide(new BigDecimal("4.54609188"), 10, BigDecimal.ROUND_HALF_UP);
		
		fieldUKGallonsB.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateCToF(){
		BigDecimal C = new BigDecimal(fieldC.getText());
		BigDecimal result = new BigDecimal("32").add(C.multiply(new BigDecimal("1.8")));
		
		fieldF.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static void CalculateFToC(){
		BigDecimal F = new BigDecimal(fieldF.getText());
		BigDecimal Finterim = F.subtract(new BigDecimal("32"));
		BigDecimal result = Finterim.divide(new BigDecimal("1.8"), 3, BigDecimal.ROUND_HALF_UP);
		
		fieldC.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}
	

}