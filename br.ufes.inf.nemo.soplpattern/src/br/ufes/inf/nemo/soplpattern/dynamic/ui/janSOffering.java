package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class janSOffering {

	private JFrame frame;
	private JTextField txtServiceProvider;
	private JTextField txtTargetCC;
	private ImageIcon icon;
	
	
//	public void inicializar(final SODescription janDescription) {	
//		this.janAnterior = janDescription;		
//		this.main(null);
//	}
	
	public void inicializar() {	
		//this.janAnterior = janDescription;		
		this.main(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janSOffering window = new janSOffering();
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
	public janSOffering() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 584, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Continue ->");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(405, 317, 139, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Go Back");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(405, 274, 139, 32);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(405, 375, 139, 32);
		frame.getContentPane().add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Service Offering", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 383, 396);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Class");
		label.setBounds(20, 232, 68, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(205, 232, 68, 14);
		panel_2.add(label_1);
		
		txtServiceProvider = new JTextField();
		txtServiceProvider.setText("Service Provider");
		txtServiceProvider.setBounds(205, 257, 166, 20);
		panel_2.add(txtServiceProvider);
		txtServiceProvider.setColumns(10);
		
		txtTargetCC = new JTextField();
		txtTargetCC.setText("Target Customer Community");
		txtTargetCC.setColumns(10);
		txtTargetCC.setBounds(205, 288, 166, 20);
		panel_2.add(txtTargetCC);
				
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.LIGHT_GRAY);
		panelImg.setBounds(10, 27, 361, 194);
		panel_2.add(panelImg);
		
		JLabel lblNewLabel = new JLabel("Service Offering");
		lblNewLabel.setBounds(20, 257, 78, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Target Customer Community");
		lblNewLabel_1.setBounds(20, 295, 151, 14);
		panel_2.add(lblNewLabel_1);
		
		icon = new ImageIcon(getClass().getResource("resource/SOFFERING.png"));
		
		JButton button_7 = new JButton("Create Concepts");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(405, 159, 139, 32);
		frame.getContentPane().add(button_7);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Paths", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(399, 11, 145, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("SOCommitments");
		rdbtnNewRadioButton_1.setBounds(6, 17, 103, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("SOClaims");
		rdbtnNewRadioButton.setBounds(6, 43, 69, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnSodescription = new JRadioButton("SODescription");
		rdbtnSodescription.setBounds(6, 69, 109, 23);
		panel.add(rdbtnSodescription);
	}
}
