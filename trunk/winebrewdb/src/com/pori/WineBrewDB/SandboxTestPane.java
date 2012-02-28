package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JTextField textBrewRefB;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(new MigLayout("", "[70px:70px:70px][70px:70px,grow][85px:85px:85px][70px:70px,grow][50px:50px:50px][70px:70px,grow][80px:80px:80px][90px:90px:90px]", "[][][][35px:35px:35px]"));
		
		JLabel lblBrewRefB = new JLabel("Brew Ref");
		add(lblBrewRefB, "cell 0 0,alignx trailing");
		
		textBrewRefB = new JTextField();
		add(textBrewRefB, "cell 1 0,growx");
		textBrewRefB.setColumns(10);

	}

}
