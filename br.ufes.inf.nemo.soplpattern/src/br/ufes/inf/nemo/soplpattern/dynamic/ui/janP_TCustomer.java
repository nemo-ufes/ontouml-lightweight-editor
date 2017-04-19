package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class janP_TCustomer {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janP_TCustomer window = new janP_TCustomer();
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
	public janP_TCustomer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 308, 210);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "P-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 277, 118);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 50, 121, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 50, 126, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Organization");
		lblNewLabel.setBounds(27, 25, 68, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Target Customer");
		lblNewLabel_1.setBounds(150, 25, 105, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(10, 140, 77, 14);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblRepresentsPersonsAs = new JLabel("Represents organizations as Target Customers");
		lblRepresentsPersonsAs.setBounds(73, 140, 212, 14);
		frame.getContentPane().add(lblRepresentsPersonsAs);
	}
}
