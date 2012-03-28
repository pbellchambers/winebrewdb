package uk.co.pori.WineBrewDB.Information;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uk.co.pori.WineBrewDB.MainWindow;


import net.miginfocom.swing.MigLayout;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 3331794777984753664L;
	public static JPanel InformationPanel;
	private static JLabel InformationHeader;
	private static JLabel InformationSubtitle;
	public static JTabbedPane tabbedInformationPane;
	private static String InformationPanelStatus = "DeInitialized";

	//public InformationPanel() {
	public static void InitializePanel(){
		
		InformationPanel = new JPanel();
		InformationPanel.setLayout(new MigLayout("", "[grow]", "[20px:n:20px][25px:n:25px][grow]"));
		
		
		//Header
		InformationHeader = new JLabel("Information");
		InformationHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		InformationPanel.add(InformationHeader, "cell 0 0,grow");
		
		
		//Subtitle
		InformationSubtitle = new JLabel("Various useful information.");
		InformationSubtitle.setFont(new Font("Tahoma", Font.ITALIC, 13));
		InformationPanel.add(InformationSubtitle, "cell 0 1,growx,aligny top");
		
		
		//Tabbed Pane
		tabbedInformationPane = new JTabbedPane(JTabbedPane.TOP);
		InformationPanel.add(tabbedInformationPane, "cell 0 2,grow");
				
		
		//Dosages Tab
		DosagesPanel.InitializePanel();		
		tabbedInformationPane.addTab("Dosages", null, DosagesPanel.DosagesScrollPane, null);
		
		
		//Fruit Acids Tab
		FruitAcidsPanel.InitializePanel();		
		tabbedInformationPane.addTab("Fruit Acids", null, FruitAcidsPanel.FruitAcidsScrollPane, null);
		
		
		//Yeast Strains Tab
		YeastStrainsPanel.InitializePanel();
		tabbedInformationPane.addTab("Yeast Strains", null, YeastStrainsPanel.YeastStrainsScrollPane, null);
		
		
		//Add it all to the main window
		MainWindow.WineBrewDBFrame.getContentPane().add(InformationPanel, "cell 0 0,grow");
		InformationPanel.setVisible(false);

		
		InformationPanelStatus = "Initialized";
	}

	
	public static void DeInitializePanel(){
		if(InformationPanelStatus.equals("Initialized")) {
			InformationPanel.setVisible(false);
			InformationPanel.remove(InformationHeader);
			InformationPanel.remove(InformationSubtitle);
			InformationPanel.remove(tabbedInformationPane);
			tabbedInformationPane.remove(DosagesPanel.DosagesScrollPane);
			tabbedInformationPane.remove(FruitAcidsPanel.FruitAcidsScrollPane);
			tabbedInformationPane.remove(YeastStrainsPanel.YeastStrainsScrollPane);
			MainWindow.WineBrewDBFrame.getContentPane().remove(InformationPanel);
			InformationPanelStatus = "DeInitialized";
		}
	}
	

}