package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Color;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JTextField txtGfhhgf;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(new Color(255, 255, 224));
		setLayout(new MigLayout("", "[grow][grow]", "[][100px:100px:100px][100px:100px:100px][grow]"));
		
		JCheckBox chckbxFghgfh = new JCheckBox("fghgfh");
		chckbxFghgfh.setEnabled(false);
		add(chckbxFghgfh, "cell 0 1,alignx center");
		
		txtGfhhgf = new JTextField();
		txtGfhhgf.setEditable(false);
		txtGfhhgf.setText("gfhhgf");
		add(txtGfhhgf, "cell 1 1,growx");
		txtGfhhgf.setColumns(10);

	}

}
