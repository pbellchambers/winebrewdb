package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JToggleButton tglbtnNewToggleButton;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {;
		setLayout(new MigLayout("", "[100px:100px:100px][grow][100px:100px:100px][grow][100px:100px:100px][grow]", "[300px:300px,grow][][][][grow][][][]"));
		
		tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setIcon(new ImageIcon(SandboxTestPane.class.getResource("/com/pori/WineBrewDB/Images/brew.png")));
		add(tglbtnNewToggleButton, "cell 0 0");
		


	}

}
