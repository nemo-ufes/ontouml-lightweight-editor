package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class janProviderSubgroup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janProviderSubgroup window = new janProviderSubgroup();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janProviderSubgroup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 647, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 34, 291, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("P-Provider");
		rdbtnNewRadioButton.setBounds(6, 22, 109, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("O-Provider");
		rdbtnNewRadioButton_1.setBounds(6, 48, 109, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("OU-Provider");
		rdbtnNewRadioButton_2.setBounds(6, 72, 109, 23);
		panel.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("O-OU-Provider");
		rdbtnNewRadioButton_3.setBounds(131, 22, 109, 23);
		panel.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("P-O-Provider");
		rdbtnNewRadioButton_4.setBounds(131, 48, 109, 23);
		panel.add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("P-OU-Provider");
		rdbtnNewRadioButton_5.setBounds(131, 72, 109, 23);
		panel.add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("P-O-OU-Provider");
		rdbtnNewRadioButton_6.setBounds(6, 98, 109, 23);
		panel.add(rdbtnNewRadioButton_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Target Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(330, 34, 291, 146);
		frame.getContentPane().add(panel_1);
		
		JRadioButton radioButton = new JRadioButton("P-TCustomer");
		radioButton.setBounds(6, 22, 109, 23);
		panel_1.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("O-TCustomer");
		radioButton_1.setBounds(6, 48, 109, 23);
		panel_1.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("OU-TCustomer");
		radioButton_2.setBounds(6, 72, 109, 23);
		panel_1.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("O-OU-TCustomer");
		radioButton_3.setBounds(131, 22, 109, 23);
		panel_1.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("P-O-TCustomer");
		radioButton_4.setBounds(131, 48, 109, 23);
		panel_1.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("P-OU-TCustomer");
		radioButton_5.setBounds(131, 72, 109, 23);
		panel_1.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("P-O-OU-TCustomer");
		radioButton_6.setBounds(6, 98, 125, 23);
		panel_1.add(radioButton_6);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(532, 348, 89, 32);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(10, 191, 291, 146);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(330, 191, 291, 146);
		frame.getContentPane().add(panel_3);
	}
}
