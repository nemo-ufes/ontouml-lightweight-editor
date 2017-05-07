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

public class JanSODescription {

	private static JFrame frame;
	private JTextField txtServiceProvider;
	private ImageIcon icon;
	private JanSOffering janAnterior;
	private JanSOCommitment janSOCommitment;
	
	public JanSODescription(JanSOffering jan){
		initialize();
		frame.setVisible(true);
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
		
		JButton btnContinue = new JButton("Continue ->");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(janSOCommitment == null)
					janSOCommitment = new JanSOCommitment(JanSODescription.this);
				janSOCommitment.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnContinue.setBackground(Color.WHITE);
		btnContinue.setBounds(405, 317, 139, 32);
		frame.getContentPane().add(btnContinue);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janAnterior.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGoBack.setBounds(405, 274, 139, 32);
		frame.getContentPane().add(btnGoBack);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancel.setBounds(405, 375, 139, 32);
		frame.getContentPane().add(btnCancel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SODescription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		txtServiceProvider.setBounds(205, 257, 166, 20);
		panel_2.add(txtServiceProvider);
		txtServiceProvider.setColumns(10);
				
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.LIGHT_GRAY);
		panelImg.setBounds(10, 27, 361, 194);
		panel_2.add(panelImg);
		
		JLabel lblNewLabel = new JLabel("Service Offering Description");
		lblNewLabel.setBounds(20, 257, 140, 14);
		panel_2.add(lblNewLabel);
		
		//icon = new ImageIcon(getClass().getResource("resource/SOFFERING.png"));
	}
	
	public static void setVisible(boolean b){
		frame.setVisible(b);
	}
}
