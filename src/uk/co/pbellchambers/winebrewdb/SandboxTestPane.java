package uk.co.pbellchambers.winebrewdb;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;

	/**
	 * Create the panel.
	 */
	private SandboxTestPane() {
		setLayout(new MigLayout("", "[400px:n,growprio 50,grow 50]", "[][][]"));
		
		JLabel lblTest = new JLabel("test");
		lblTest.setForeground(new Color(240, 255, 240));
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTest, "cell 0 0");
		
		JButton btnGfgg = new JButton("gfgg");
		btnGfgg.setMinimumSize(new Dimension(80, 23));
		add(btnGfgg, "cell 0 2,growx,alignx right");;
		


	}

}
