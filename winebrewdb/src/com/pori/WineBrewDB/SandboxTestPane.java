package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JTextField txtTestttt;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(Color.ORANGE);
		setLayout(new MigLayout("", "[grow]", "[80px:n:80px][grow]"));
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setBackground(Color.PINK);
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblTest, "cell 0 0,growx,aligny top");
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 1,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("New button");
		scrollPane.setViewportView(btnNewButton);
		
		txtTestttt = new JTextField();
		txtTestttt.setText("testttt");
		panel.add(txtTestttt);
		txtTestttt.setColumns(10);
		

	}

}
