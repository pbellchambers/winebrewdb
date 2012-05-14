package uk.co.pori.winebrewdb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.Color;

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
	private SandboxTestFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SandboxTestFrame.class.getResource("/uk/co/pori/winebrewdb/images/winebrewdb64.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("yyyyy");
		contentPane.add(separator, BorderLayout.WEST);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(new Color(220, 220, 220));
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(toolBar, BorderLayout.NORTH);
	}

	
}
