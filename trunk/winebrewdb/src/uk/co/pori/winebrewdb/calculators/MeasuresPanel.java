package uk.co.pori.winebrewdb.calculators;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import uk.co.pori.winebrewdb.MainWindow;

import net.miginfocom.swing.MigLayout;

/**
 * This is the measures calculator panel to be displayed as a tab on the main calculator panel tab bar.
 * 
 * @author Paul.Bellchambers
 *
 */
public class MeasuresPanel extends JPanel {

	private static final long serialVersionUID = -7839159138359581270L;
	private static String MeasuresPanelStatus = "DeInitialised";
	static JPanel MeasuresSubPanel;
	private static JFormattedTextField fieldlbs;
	private static JFormattedTextField fieldoz;
	private static JFormattedTextField fieldgrams;
	private static JFormattedTextField fieldtsp;
	private static JFormattedTextField fieldtbsp;
	private static JFormattedTextField fieldUKGallons;
	private static JFormattedTextField fieldUSGallons;
	private static JFormattedTextField fieldUKGallonsB;
	private static JFormattedTextField fieldLitres;
	private static JFormattedTextField fieldC;
	private static JFormattedTextField fieldF;
	
	public static String AlcoholPanelStatus = "DeInitialised";
	
	
	/**
	 * Initialises the measures calculator panel so that it can be viewed.
	 */
	public static void initialisePanel(){
			
		
		//Subpanel
		MeasuresSubPanel = new JPanel();
		MeasuresSubPanel.setBackground(Color.WHITE);
		MeasuresSubPanel.setLayout(new MigLayout("", "[20px:n:20px,left][15px:n:15px][20px:n:20px,left][60px:n:60px][50px:n:50px][50px:n:50px][60px:n:60px,left][60px:n:60px][60px:n:60px][60px:n:60px,left][60px:n:60px][50px:n:50px][50px:n:50px][60px:n:60px,left][60px:n:60px][grow]", "[5px:n,5px][15px:n:15px][20px:n:20px][40px:n:40px][40px:n:40px][20px:n:20px][40px:n:40px][40px:n:40px][20px:n:20px][40px:n:40px]"));
				
				
		//Add Calculators to subpanel		
		JTextPane txtMeasuresInfo = new JTextPane();
		txtMeasuresInfo.setText("");
		txtMeasuresInfo.setEditable(false);
		MeasuresSubPanel.add(txtMeasuresInfo, "cell 0 0 16,grow");
				
		JLabel lblWeight = new JLabel("Weight");
		MeasuresSubPanel.add(lblWeight, "cell 0 1 3");
		
		JLabel lbllbs = new JLabel("Lbs");
		MeasuresSubPanel.add(lbllbs, "cell 2 2,alignx trailing");
				
		fieldlbs = new JFormattedTextField(new DecimalFormat("####0"));
		fieldlbs.setText("1");
		MeasuresSubPanel.add(fieldlbs, "cell 3 2,growx");
				
		JLabel lbloz = new JLabel("Oz");
		MeasuresSubPanel.add(lbloz, "cell 2 3,alignx trailing");
				
		fieldoz = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldoz.setText("8.00");
		MeasuresSubPanel.add(fieldoz, "cell 3 3,growx");
				
		JButton btnWeightToGrams = new JButton();
		btnWeightToGrams.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/right.png")));
		MeasuresSubPanel.add(btnWeightToGrams, "cell 4 3");
		
		JButton btnWeightToOz = new JButton();
		btnWeightToOz.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/left.png")));
		MeasuresSubPanel.add(btnWeightToOz, "cell 5 3");
				
		JLabel lblgrams = new JLabel("Grams");
		MeasuresSubPanel.add(lblgrams, "cell 7 3,alignx left");
				
		fieldgrams = new JFormattedTextField(new DecimalFormat("#######0.0"));
		fieldgrams.setText("680.4");
		MeasuresSubPanel.add(fieldgrams, "cell 6 3,growx");
				
		JLabel lblSpoons = new JLabel("Spoons");
		MeasuresSubPanel.add(lblSpoons, "cell 9 1");

		JLabel lbltsp = new JLabel("Tsp");
		MeasuresSubPanel.add(lbltsp, "cell 9 3,alignx trailing");
				
		fieldtsp = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldtsp.setText("3.00");
		MeasuresSubPanel.add(fieldtsp, "cell 10 3,growx");
				
		JButton btnTspToTbsp = new JButton();
		btnTspToTbsp.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/right.png")));
		MeasuresSubPanel.add(btnTspToTbsp, "cell 11 3");

		JButton btnTbspToTsp = new JButton();
		btnTbspToTsp.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/left.png")));
		MeasuresSubPanel.add(btnTbspToTsp, "cell 12 3");
				
		JLabel lbltbsp = new JLabel("Tbsp");
		MeasuresSubPanel.add(lbltbsp, "cell 14 3,alignx left");
				
		fieldtbsp = new JFormattedTextField(new DecimalFormat("####0.00"));
		fieldtbsp.setText("1.00");
		MeasuresSubPanel.add(fieldtbsp, "cell 13 3,growx");
		
		JLabel lblUKUSGallons = new JLabel("UK/US Gallons");
		MeasuresSubPanel.add(lblUKUSGallons, "cell 0 5 3");

		JLabel lblUKGallons = new JLabel("UK Gallons");
		MeasuresSubPanel.add(lblUKGallons, "cell 0 6 3,alignx trailing");
				
		fieldUKGallons = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUKGallons.setText("1.000");
		MeasuresSubPanel.add(fieldUKGallons, "cell 3 6,growx");
				
		JButton btnUKToUSGallons = new JButton();
		btnUKToUSGallons.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/right.png")));
		MeasuresSubPanel.add(btnUKToUSGallons, "cell 4 6");

		JButton btnUSToUKGallons = new JButton();
		btnUSToUKGallons.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/left.png")));
		MeasuresSubPanel.add(btnUSToUKGallons, "cell 5 6");
				
		JLabel lblUSGallons = new JLabel("US Gallons");
		MeasuresSubPanel.add(lblUSGallons, "cell 7 6,alignx left");
				
		fieldUSGallons = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUSGallons.setText("1.201");
		MeasuresSubPanel.add(fieldUSGallons, "cell 6 6,growx");
		
		JLabel lblUKGallonsLitres = new JLabel("Gallons/Litres");
		MeasuresSubPanel.add(lblUKGallonsLitres, "cell 9 5");

		JLabel lblUKGallonsB = new JLabel("UK Gallons");
		MeasuresSubPanel.add(lblUKGallonsB, "cell 9 6,alignx trailing");
				
		fieldUKGallonsB = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldUKGallonsB.setText("1.000");
		MeasuresSubPanel.add(fieldUKGallonsB, "cell 10 6,growx");
				
		JButton btnUKGallonsToLitres = new JButton();
		btnUKGallonsToLitres.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/right.png")));
		MeasuresSubPanel.add(btnUKGallonsToLitres, "cell 11 6");

		JButton btnLitresToUKGallons = new JButton();
		btnLitresToUKGallons.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/left.png")));
		MeasuresSubPanel.add(btnLitresToUKGallons, "cell 12 6");
				
		JLabel lblLitres = new JLabel("Litres");
		MeasuresSubPanel.add(lblLitres, "cell 14 6,alignx left");
				
		fieldLitres = new JFormattedTextField(new DecimalFormat("####0.000"));
		fieldLitres.setText("4.546");
		MeasuresSubPanel.add(fieldLitres, "cell 13 6,growx");
		
		JLabel lblCToF = new JLabel("Temperature");
		MeasuresSubPanel.add(lblCToF, "cell 0 8 3");

		JLabel lblC = new JLabel("°C");
		MeasuresSubPanel.add(lblC, "cell 0 9 3,alignx trailing");
				
		fieldC = new JFormattedTextField(new DecimalFormat("####0.0"));
		fieldC.setText("0.0");
		MeasuresSubPanel.add(fieldC, "cell 3 9,growx");
				
		JButton btnCToF = new JButton();
		btnCToF.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/right.png")));
		MeasuresSubPanel.add(btnCToF, "cell 4 9");

		JButton btnFToC = new JButton();
		btnFToC.setIcon(new ImageIcon(MeasuresPanel.class.getResource("/uk/co/pori/winebrewdb/images/left.png")));
		MeasuresSubPanel.add(btnFToC, "cell 5 9");
				
		JLabel lblF = new JLabel("°F");
		MeasuresSubPanel.add(lblF, "cell 7 9,alignx left");
				
		fieldF = new JFormattedTextField(new DecimalFormat("####0.0"));
		fieldF.setText("32.0");
		MeasuresSubPanel.add(fieldF, "cell 6 9,growx");

				
		//Add button listeners
		btnWeightToGrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateWeightToGrams();
			}
		});
				
		btnWeightToOz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateWeightToOz();
			}
		});
		
		btnTspToTbsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateTspToTbsp();
			}
		});
				
		btnTbspToTsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateTbspToTsp();
			}
		});
		
		btnUKToUSGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateUKToUSGallons();
			}
		});
				
		btnUSToUKGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateUSToUKGallons();
			}
		});
		
		btnUKGallonsToLitres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateUKGallonsToLitres();
			}
		});
				
		btnLitresToUKGallons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateLitresToUKGallons();
			}
		});
		
		btnCToF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateCToF();
			}
		});
				
		btnFToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateFToC();
			}
		});
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(MeasuresSubPanel, "cell 0 0,grow");
		MeasuresSubPanel.setVisible(false);

		MeasuresPanelStatus = "Initialised";
	}

	/**
	 * De-initialises the measures calculator panel so that it is not visible on screen.
	 */	
	public static void deinitialisePanel(){
		if(MeasuresPanelStatus.equals("Initialised")) {
			MeasuresSubPanel.setVisible(false);
			MainWindow.WineBrewDBFrame.getContentPane().remove(MeasuresSubPanel);
			MeasuresPanelStatus = "DeInitialised";
		}
	}
	
	/**
	 * Calculates the weight in grams from the user entered values.
	 */
	public static void calculateWeightToGrams(){
		BigDecimal lbs = new BigDecimal(fieldlbs.getText());
		BigDecimal oz = new BigDecimal(fieldoz.getText());
		BigDecimal totaloz = oz.add(lbs.multiply(new BigDecimal("16")));
		BigDecimal result = totaloz.multiply(new BigDecimal("28.3495231"));
		
		fieldgrams.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}
	
	/**
	 * Calculates the weight in Oz from the user entered values.
	 */
	public static void calculateWeightToOz(){
		BigDecimal grams = new BigDecimal(fieldgrams.getText());
		BigDecimal totaloz = grams.divide(new BigDecimal("28.3495231"), 5, BigDecimal.ROUND_HALF_UP);
		BigDecimal lbs = totaloz.divide(new BigDecimal("16"), 0, BigDecimal.ROUND_DOWN);
		BigDecimal oz = totaloz.subtract(lbs.multiply(new BigDecimal("16")));

		fieldlbs.setText(lbs.toString());
		fieldoz.setText(oz.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the number of Tbsp from the user entered Tsp value.
	 */
	public static void calculateTspToTbsp(){
		BigDecimal tsp = new BigDecimal(fieldtsp.getText());
		BigDecimal result = tsp.divide(new BigDecimal("3"), 3, BigDecimal.ROUND_HALF_UP);
		
		fieldtbsp.setText(result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the number of Tsp from the user entered Tbsp value.
	 */
	public static void calculateTbspToTsp(){
		BigDecimal tbsp = new BigDecimal(fieldtbsp.getText());
		BigDecimal result = tbsp.multiply(new BigDecimal("3"));
		
		fieldtsp.setText(result.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the US Gallon value from the user entered UK Gallon value.
	 */
	public static void calculateUKToUSGallons(){
		BigDecimal UKGallons = new BigDecimal(fieldUKGallons.getText());
		BigDecimal result = UKGallons.multiply(new BigDecimal("1.20095042"));
		
		fieldUSGallons.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the UK Gallon value from the user entered US Gallon value.
	 */
	public static void calculateUSToUKGallons(){
		BigDecimal USGallons = new BigDecimal(fieldUSGallons.getText());
		BigDecimal result = USGallons.divide(new BigDecimal("1.20095042"), 10, BigDecimal.ROUND_HALF_UP);
		
		fieldUKGallons.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the litres value from the user entered UK Gallon value.
	 */
	public static void calculateUKGallonsToLitres(){
		BigDecimal UKGallonsB = new BigDecimal(fieldUKGallonsB.getText());
		BigDecimal result = UKGallonsB.multiply(new BigDecimal("4.54609188"));
		
		fieldLitres.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the UK Gallon value from the user entered litres value.
	 */
	public static void calculateLitresToUKGallons(){
		BigDecimal Litres = new BigDecimal(fieldLitres.getText());
		BigDecimal result = Litres.divide(new BigDecimal("4.54609188"), 10, BigDecimal.ROUND_HALF_UP);
		
		fieldUKGallonsB.setText(result.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the temperature in °F from the user entered °C value.
	 */
	public static void calculateCToF(){
		BigDecimal C = new BigDecimal(fieldC.getText());
		BigDecimal result = new BigDecimal("32").add(C.multiply(new BigDecimal("1.8")));
		
		fieldF.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}

	/**
	 * Calculates the temperature in °C from the user entered °F value.
	 */
	public static void calculateFToC(){
		BigDecimal F = new BigDecimal(fieldF.getText());
		BigDecimal Finterim = F.subtract(new BigDecimal("32"));
		BigDecimal result = Finterim.divide(new BigDecimal("1.8"), 3, BigDecimal.ROUND_HALF_UP);
		
		fieldC.setText(result.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
	}
	

}