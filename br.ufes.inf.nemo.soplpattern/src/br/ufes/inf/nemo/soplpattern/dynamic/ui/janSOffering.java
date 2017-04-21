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
		frame.setBounds(100, 100, 783, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Continue ->");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(405, 366, 107, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Go Back");
		button.setFont(new Font("Verdana", Font.PLAIN, 12));
		button.setBounds(307, 366, 91, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_1.setBounds(556, 366, 91, 25);
		frame.getContentPane().add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Paths", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 115, 344);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("SODescription");
		chckbxNewCheckBox.setBounds(6, 44, 97, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("SOClaims");
		chckbxNewCheckBox_1.setBounds(6, 70, 97, 23);
		panel_1.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("SOCommitments");
		chckbxNewCheckBox_2.setBounds(6, 96, 97, 23);
		panel_1.add(chckbxNewCheckBox_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(135, 11, 536, 344);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Class");
		label.setBounds(119, 232, 68, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(353, 232, 68, 14);
		panel_2.add(label_1);
		
		txtServiceProvider = new JTextField();
		txtServiceProvider.setText("Service Provider");
		txtServiceProvider.setBounds(293, 261, 166, 20);
		panel_2.add(txtServiceProvider);
		txtServiceProvider.setColumns(10);
		
		txtTargetCC = new JTextField();
		txtTargetCC.setText("Target Customer Community");
		txtTargetCC.setColumns(10);
		txtTargetCC.setBounds(293, 292, 166, 20);
		panel_2.add(txtTargetCC);
				
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.LIGHT_GRAY);
		panelImg.setBounds(31, 27, 466, 194);
		panel_2.add(panelImg);
		
		JLabel lblNewLabel = new JLabel("Service Offering");
		lblNewLabel.setBounds(97, 264, 78, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Target Customer Community");
		lblNewLabel_1.setBounds(74, 295, 151, 14);
		panel_2.add(lblNewLabel_1);
		
		icon = new ImageIcon(getClass().getResource("resource/SOFFERING.png"));
		
		JButton button_7 = new JButton("Create Concepts");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		button_7.setFont(new Font("Verdana", Font.PLAIN, 12));
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(56, 366, 143, 25);
		frame.getContentPane().add(button_7);
	}
}
