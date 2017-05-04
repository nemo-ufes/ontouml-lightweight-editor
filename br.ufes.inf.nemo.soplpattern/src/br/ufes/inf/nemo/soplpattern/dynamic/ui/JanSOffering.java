package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

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

public class JanSOffering {

	private static JFrame frame;
	private JTextField txtServiceOffering;
	private JTextField txtTargetCC;
	private ImageIcon icon;
	private SOPLPattern soplPattern;
	private JanProviderCustomerSubgroup janAnterior;
	//private static JanSOffering window;
		
	/**
	 * Launch the application.
	 */
	
	public JanSOffering(final SOPLPattern pm, final JanProviderCustomerSubgroup jan){
		initialize();
		frame.setVisible(true);
		soplPattern = pm;
		janAnterior = jan;
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
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janAnterior.setVisible(true);				
				setVisible(false);
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGoBack.setBounds(405, 274, 139, 32);
		frame.getContentPane().add(btnGoBack);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(405, 375, 139, 32);
		frame.getContentPane().add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SOffering", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 383, 396);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Class");
		label.setBounds(20, 232, 68, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(205, 232, 68, 14);
		panel_2.add(label_1);
		
		txtServiceOffering = new JTextField();
		txtServiceOffering.setBounds(205, 257, 166, 20);
		panel_2.add(txtServiceOffering);
		txtServiceOffering.setColumns(10);
		
		txtTargetCC = new JTextField();
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
		
		//icon = new ImageIcon(getClass().getResource("resource/SOFFERING.png"));
		
		JButton btnCreateConcepts = new JButton("Create Concepts");
		btnCreateConcepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				String tabela[][] = new String[4][2];
				
				//tabela[0][1] = txtServiceOfferDesc.getText();
				tabela[1][1] = txtTargetCC.getText();
				tabela[2][1] = txtServiceOffering.getText();
				//tabela[3][1] = txtTargetCustomer.getText();
				soplPattern.criarTabela(tabela);		
				soplPattern.getSpecificFix();
				setVisible(false);
			}
		});
		btnCreateConcepts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateConcepts.setBackground(Color.WHITE);
		btnCreateConcepts.setBounds(405, 159, 139, 32);
		frame.getContentPane().add(btnCreateConcepts);
		
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
	
	public static void setVisible(boolean b){
		frame.setVisible(b);
	}
	
}
