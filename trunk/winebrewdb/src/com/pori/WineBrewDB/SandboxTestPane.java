package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column2"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(76);
		table.getColumnModel().getColumn(0).setMinWidth(16);
		table.getColumnModel().getColumn(0).setMaxWidth(999999);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(76);
		table.getColumnModel().getColumn(1).setMinWidth(16);
		table.getColumnModel().getColumn(1).setMaxWidth(99999);
		add(table, "cell 0 0,grow");

	}

}
