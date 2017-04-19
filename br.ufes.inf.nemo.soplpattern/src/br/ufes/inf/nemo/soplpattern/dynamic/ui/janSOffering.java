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

public class janSOffering {

	private JFrame frame;
	private JTextField txtServiceProvider;
	private JTextField txtTargetCC;
	private JTextField txtServiceOffering;
	private JTextField txtTargetCustomer;
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
		frame.setBounds(100, 100, 822, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Continue ->");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(553, 406, 107, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Go Back");
		button.setFont(new Font("Verdana", Font.PLAIN, 12));
		button.setBounds(455, 406, 91, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_1.setBounds(705, 406, 91, 25);
		frame.getContentPane().add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Steps", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 60, 143, 344);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("SOffering *");
		btnNewButton_1.setBounds(10, 37, 123, 36);
		panel_1.add(btnNewButton_1);
		
		JButton btnSodescription = new JButton("SODescription");
		btnSodescription.setBounds(10, 84, 123, 36);
		panel_1.add(btnSodescription);
		
		JButton button_2 = new JButton("SOCommitments");
		button_2.setBounds(10, 131, 123, 36);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("SOClaims");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_3.setBounds(10, 177, 123, 36);
		panel_1.add(button_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(158, 60, 638, 344);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDisableClass = new JLabel("Disable class ?");
		lblDisableClass.setBounds(10, 177, 68, 14);
		panel_2.add(lblDisableClass);
		
		JLabel label = new JLabel("Stereotype");
		label.setBounds(160, 177, 68, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Class Name");
		label_1.setBounds(357, 177, 68, 14);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Pick up from model ?");
		label_2.setBounds(514, 177, 97, 14);
		panel_2.add(label_2);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(31, 200, 21, 23);
		panel_2.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setBounds(31, 240, 21, 23);
		panel_2.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setBounds(31, 278, 21, 23);
		panel_2.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setBounds(31, 314, 21, 23);
		panel_2.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setBounds(554, 200, 21, 23);
		panel_2.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setBounds(554, 240, 21, 23);
		panel_2.add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setBounds(554, 278, 21, 23);
		panel_2.add(checkBox_6);
		
		JCheckBox checkBox_7 = new JCheckBox("");
		checkBox_7.setBounds(554, 314, 21, 23);
		panel_2.add(checkBox_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"P-Provider", "O-Provider", "OU-Provider", "O-OU-Provider", "P-O-Provider", "P-OU-Provider", "P-O-OU-Provider"}));
		comboBox.setBounds(126, 202, 113, 20);
		panel_2.add(comboBox);
		
		txtServiceProvider = new JTextField();
		txtServiceProvider.setText("Service Provider");
		txtServiceProvider.setBounds(310, 203, 166, 20);
		panel_2.add(txtServiceProvider);
		txtServiceProvider.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Collective"}));
		comboBox_1.setBounds(125, 240, 113, 20);
		panel_2.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Relator"}));
		comboBox_2.setBounds(125, 279, 113, 20);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"P-TCustomer", "O-TCustomer", "OU-TCustomer", "O-OU-TCustomer", "P-O-TCustomer", "P-OU-TCustomer", "P-O-OU-TCustomer"}));
		comboBox_3.setBounds(126, 316, 113, 20);
		panel_2.add(comboBox_3);
		
		txtTargetCC = new JTextField();
		txtTargetCC.setText("Target Customer Community");
		txtTargetCC.setColumns(10);
		txtTargetCC.setBounds(310, 236, 166, 20);
		panel_2.add(txtTargetCC);
		
		txtServiceOffering = new JTextField();
		txtServiceOffering.setText("Service Offering");
		txtServiceOffering.setColumns(10);
		txtServiceOffering.setBounds(310, 277, 166, 20);
		panel_2.add(txtServiceOffering);
		
		txtTargetCustomer = new JTextField();
		txtTargetCustomer.setText("Target Customer");
		txtTargetCustomer.setColumns(10);
		txtTargetCustomer.setBounds(309, 314, 166, 20);
		panel_2.add(txtTargetCustomer);
				
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.LIGHT_GRAY);
		panelImg.setBounds(175, 27, 301, 139);
		panel_2.add(panelImg);
		
		icon = new ImageIcon(getClass().getResource("resource/SOFFERING.png"));
		
		JButton button_4 = new JButton("Service Offering");
		button_4.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_4.setBounds(64, 11, 143, 36);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("Service Agreement");
		button_5.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_5.setBounds(304, 11, 151, 36);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("Service Delivery");
		button_6.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_6.setBounds(570, 11, 138, 36);
		frame.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("Create Concepts");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		button_7.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(158, 406, 143, 25);
		frame.getContentPane().add(button_7);
	}
}
