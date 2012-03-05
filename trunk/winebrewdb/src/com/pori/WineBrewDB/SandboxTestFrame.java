package com.pori.WineBrewDB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class SandboxTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8244604801203734746L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SandboxTestFrame frame = new SandboxTestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SandboxTestFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SandboxTestFrame.class.getResource("/com/pori/WineBrewDB/Images/winebrewdb64.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		setJMenuBar(menuBar);
		
		JMenu mnHjjj = new JMenu("hjjj");
		mnHjjj.setEnabled(false);
		menuBar.add(mnHjjj);
		
		JMenuItem mntmCvcv = new JMenuItem("cvcv");
		mnHjjj.add(mntmCvcv);
		
		JRadioButtonMenuItem rdbtnmntmFffffffffffffffff = new JRadioButtonMenuItem("fffffffffffffffff");
		mnHjjj.add(rdbtnmntmFffffffffffffffff);
		
		JMenu mnGftfgfg = new JMenu("gftfgfg");
		mnHjjj.add(mnGftfgfg);
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("1");
		mnGftfgfg.add(radioButtonMenuItem);
		
		JRadioButtonMenuItem radioButtonMenuItem_1 = new JRadioButtonMenuItem("2");
		mnGftfgfg.add(radioButtonMenuItem_1);
		
		JMenuItem mntmGgfg = new JMenuItem("ggfg");
		mnHjjj.add(mntmGgfg);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Search = new JPanel();
		tabbedPane.addTab("Search", null, Search, null);
		
		JPanel BrewDetails = new JPanel();
		tabbedPane.addTab("New tab", null, BrewDetails, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
	}

	
}
