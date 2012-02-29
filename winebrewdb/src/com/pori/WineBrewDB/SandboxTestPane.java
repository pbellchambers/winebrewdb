package com.pori.WineBrewDB;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class SandboxTestPane extends JPanel {

	private static final long serialVersionUID = 6400490130216379374L;
	private JTextField textBrewNoteRef;
	private JTextField textBrewNoteDate;
	private JTextField textBrewNoteDaysSinceStart;
	private JTextField textBrewNoteIncident;
	private JLabel lblBrewNoteNote;
	private JLabel lblBrewNoteRef;
	private JTextArea textBrewNoteNote;
	private JButton btnBrewNoteAdd;
	private JButton btnBrewNoteEdit;
	private JButton btnBrewNoteDelete;
	private JButton btnBrewNoteCancel;
	private JButton btnBrewNoteSave;

	/**
	 * Create the panel.
	 */
	public SandboxTestPane() {;
		setLayout(new MigLayout("", "[100px:100px:100px][grow][100px:100px:100px][grow][100px:100px:100px][grow]", "[300px:300px,grow][][][][grow][][][]"));
		
		lblBrewNoteRef = new JLabel("Brew Note Ref:");
		add(lblBrewNoteRef, "cell 0 0,alignx trailing");
		
		textBrewNoteRef = new JTextField();
		add(textBrewNoteRef, "cell 1 0,growx");
		textBrewNoteRef.setColumns(10);
		
		JLabel lblBrewNoteDate = new JLabel("Date:");
		add(lblBrewNoteDate, "cell 2 0,alignx trailing");
		
		textBrewNoteDate = new JTextField();
		add(textBrewNoteDate, "cell 3 0,growx");
		textBrewNoteDate.setColumns(10);
		
		JLabel lblBrewNoteDaysSinceStart = new JLabel("Days Since Start:");
		add(lblBrewNoteDaysSinceStart, "cell 4 0,alignx trailing");
		
		textBrewNoteDaysSinceStart = new JTextField();
		add(textBrewNoteDaysSinceStart, "cell 5 0,growx");
		textBrewNoteDaysSinceStart.setColumns(10);
		
		JLabel lblBrewNoteIncident = new JLabel("Incident:");
		add(lblBrewNoteIncident, "cell 0 2,alignx trailing");
		
		textBrewNoteIncident = new JTextField();
		add(textBrewNoteIncident, "cell 1 2 5,growx");
		textBrewNoteIncident.setColumns(10);
		
		lblBrewNoteNote = new JLabel("Note:");
		add(lblBrewNoteNote, "cell 0 3,alignx trailing");
		
		textBrewNoteNote = new JTextArea();
		add(textBrewNoteNote, "cell 1 3 5 2,grow");
		
		btnBrewNoteAdd = new JButton("Add New Note");
		add(btnBrewNoteAdd, "cell 0 6,growx");
		
		btnBrewNoteEdit = new JButton("Edit");
		add(btnBrewNoteEdit, "cell 1 6,growx");
		
		btnBrewNoteDelete = new JButton("Delete");
		add(btnBrewNoteDelete, "cell 2 6,growx");
		
		btnBrewNoteCancel = new JButton("Cancel");
		add(btnBrewNoteCancel, "cell 4 6,growx");
		
		btnBrewNoteSave = new JButton("Save / Insert");
		add(btnBrewNoteSave, "cell 5 6,growx");
		


	}

}
