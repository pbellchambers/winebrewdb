package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setLayout(new MigLayout("", "[400px:n,growprio 50,grow 50]", "[][][]"));
		
		JLabel lblTest = new JLabel("test");
		lblTest.setForeground(Color.RED);
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTest, "cell 0 0");
		
		JButton btnGfgg = new JButton("gfgg");
		add(btnGfgg, "cell 0 2,alignx center");;
		


	}

}
