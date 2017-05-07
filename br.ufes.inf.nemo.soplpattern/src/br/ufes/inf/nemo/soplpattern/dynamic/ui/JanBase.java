package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanBase {

	private JFrame frame;
	private int painelSelecionado = 0; //Utilizado no botao Next . Sempre que passamos para outro painel(Next) ou voltamos(Go Back) essa variavel incrementa ou decrementa
	private JTextField txtServiceProvider_P_Provider;
	private JTextField txtPerson_P_Provider;
	private JTextField txtTargetCustomer_P_TCustomer;
	private JTextField txtPerson_P_TCustomer;
	private JTextField txtServiceProvider_O_Provider;
	private JTextField txtOrganization_O_Provider;
	private JTextField txtServiceProvider_OU_Provider;
	private JTextField txtOrgUnit_OU_Provider;
	private JTextField txtServiceProvider_O_OU_Provider;
	private JTextField txtOrgUnitProvider_O_OU_Provider;
	private JTextField txtOrganizationProvider_O_OU_Provider;
	private JTextField txtServiceProvider_P_O_Provider;
	private JTextField txtPersonProvider_P_O_Provider;
	private JTextField txtOrganizationProvider_P_O_Provider;
	private JTextField txtServiceProvider_P_OU_Provider;
	private JTextField txtPersonProvider_P_OU_Provider;
	private JTextField txtOrgUnitProvider_P_OU_Provider;
	private JTextField txtServiceProvider_P_O_OU_Provider;
	private JTextField txtPersonProvider_P_O_OU_Provider;
	private JTextField txtOrgUnitProvider_P_O_OU_Provider;
	private JTextField txtOrgProvider_P_O_OU_Provider;
	private JTextField txtTargetCustomer_O_TCustomer;
	private JTextField txtOrganization_O_TCustomer;
	private JTextField txtTargetCustomer_OU_TCustomer;
	private JTextField txtOrgUnit_OU_TCustomer;
	private JTextField txtTargetCustomer_O_OU_TCustomer;
	private JTextField txtOrgUnitTC_O_OU_TCustomer;
	private JTextField txtOrgTC_O_OU_TCustomer;
	private JTextField txtTargetCustomer_P_O_TCustomer;
	private JTextField txtPersonTC_P_O_TCustomer;
	private JTextField txtOrgTC_P_O_TCustomer;
	private JTextField txtTargetCustomerTC_P_OU_TCustomer;
	private JTextField txtPersonTC_P_OU_TCustomer;
	private JTextField txtOrgUnitTC_P_OU_TCustomer;
	private JTextField txtOrgProvider_P_OU_TCustomer;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_38;
	private SOPLPattern soplPattern;
	private static JPanel imgPatternProvider;
	private static JPanel imgPatternCustomer;
	private static JPanel panelPCSubgroup;
	private static JPanel panelP_TCustomer;
	private static JPanel panelP_Provider;
	private static JPanel panelPatternProvider;
	private static JPanel panelPatternCustomer;
	private static JPanel panelP_O_OU_Provider;
	private static JPanel panelP_O_OU_TCustomer;
	private static JPanel panelP_OU_TCustomer;
	private static JPanel panelP_O_TCustomer;
	private static JPanel panelO_OU_TCustomer;
	private static JPanel panelOU_TCustomer;
	private static JPanel panelO_TCustomer;
	private static JPanel panelP_OU_Provider;
	private static JPanel panelP_O_Provider;
	private static JPanel panelO_OU_Provider;
	private static JPanel panelOU_Provider;
	private static JPanel panelO_Provider;


	/**
	 * Create the application.
	 */
	public JanBase(final SOPLPattern pattern) {
		initialize();
		frame.setVisible(true);
		soplPattern = pattern;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocaPainelPrincipal(1);
			}
		});
		btnNext.setBounds(689, 533, 89, 37);
		frame.getContentPane().add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocaPainelPrincipal(-1);
			}
		});
		btnBack.setBounds(576, 533, 89, 37);
		frame.getContentPane().add(btnBack);
		
		JPanel panelSteps = new JPanel();
		panelSteps.setBorder(new TitledBorder(null, "Steps", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSteps.setBounds(10, 11, 139, 511);
		frame.getContentPane().add(panelSteps);
		panelSteps.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("SOffering");
		lblNewLabel_2.setBounds(38, 31, 46, 14);
		panelSteps.add(lblNewLabel_2);
		
		panelPCSubgroup = new JPanel();
		panelPCSubgroup.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelPCSubgroup);
		panelPCSubgroup.setLayout(null);
		
		panelPatternProvider = new JPanel();
		panelPatternProvider.setBorder(new TitledBorder(null, "Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatternProvider.setBounds(10,11, 291, 152);
		panelPCSubgroup.add(panelPatternProvider);
		panelPatternProvider.setLayout(null);		
						
		JRadioButton rdbtnP_Provider = new JRadioButton("P-Provider");
		rdbtnP_Provider.setBounds(6, 22, 109, 23);
		panelPatternProvider.add(rdbtnP_Provider);
		
		JRadioButton rdbtnO_Provider = new JRadioButton("O-Provider");
		rdbtnO_Provider.setBounds(6, 48, 109, 23);
		panelPatternProvider.add(rdbtnO_Provider);
		
		JRadioButton rdbtnOU_Provider = new JRadioButton("OU-Provider");
		rdbtnOU_Provider.setBounds(6, 72, 109, 23);
		panelPatternProvider.add(rdbtnOU_Provider);
		
		JRadioButton rdbtnO_OU_Provider = new JRadioButton("O-OU-Provider");
		rdbtnO_OU_Provider.setBounds(131, 22, 109, 23);
		panelPatternProvider.add(rdbtnO_OU_Provider);
		
		JRadioButton rdbtnP_O_Provider = new JRadioButton("P-O-Provider");
		rdbtnP_O_Provider.setBounds(131, 48, 109, 23);
		panelPatternProvider.add(rdbtnP_O_Provider);
		
		JRadioButton rdbtnP_OU_Provider = new JRadioButton("P-OU-Provider");
		rdbtnP_OU_Provider.setBounds(131, 72, 109, 23);
		panelPatternProvider.add(rdbtnP_OU_Provider);
		
		JRadioButton rdbtnP_O_OU_Provider = new JRadioButton("P-O-OU-Provider");
		rdbtnP_O_OU_Provider.setBounds(6, 98, 109, 23);
		panelPatternProvider.add(rdbtnP_O_OU_Provider);
		
		panelPatternCustomer = new JPanel();
		panelPatternCustomer.setLayout(null);
		panelPatternCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Target Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPatternCustomer.setBounds(325,11, 286, 152);
		panelPCSubgroup.add(panelPatternCustomer);
		
		JRadioButton rdbtnP_TCustomer = new JRadioButton("P-TCustomer");
		rdbtnP_TCustomer.setBounds(6, 22, 109, 23);
		panelPatternCustomer.add(rdbtnP_TCustomer);
		
		JRadioButton rdbtnO_TCustomer = new JRadioButton("O-TCustomer");
		rdbtnO_TCustomer.setBounds(6, 48, 109, 23);
		panelPatternCustomer.add(rdbtnO_TCustomer);
		
		JRadioButton rdbtnOU_TCustomer = new JRadioButton("OU-TCustomer");
		rdbtnOU_TCustomer.setBounds(6, 72, 109, 23);
		panelPatternCustomer.add(rdbtnOU_TCustomer);
		
		JRadioButton rdbtnO_OU_TCustomer = new JRadioButton("O-OU-TCustomer");
		rdbtnO_OU_TCustomer.setBounds(131, 22, 109, 23);
		panelPatternCustomer.add(rdbtnO_OU_TCustomer);
		
		JRadioButton rdbtnP_O_TCustomer = new JRadioButton("P-O-TCustomer");
		rdbtnP_O_TCustomer.setBounds(131, 48, 109, 23);
		panelPatternCustomer.add(rdbtnP_O_TCustomer);
		
		JRadioButton rdbtnP_OU_TCustomer = new JRadioButton("P-OU-TCustomer");
		rdbtnP_OU_TCustomer.setBounds(131, 72, 109, 23);
		panelPatternCustomer.add(rdbtnP_OU_TCustomer);
		
		JRadioButton rdbtnP_O_OU_TCustomer = new JRadioButton("P-O-OU-TCustomer");
		rdbtnP_O_OU_TCustomer.setBounds(6, 98, 125, 23);
		panelPatternCustomer.add(rdbtnP_O_OU_TCustomer);
		
		//Group the radio buttons Provider.
		ButtonGroup rdProviderGroup = new ButtonGroup();
		rdProviderGroup.add(rdbtnP_Provider); rdProviderGroup.add(rdbtnP_OU_Provider);
		rdProviderGroup.add(rdbtnO_Provider); rdProviderGroup.add(rdbtnP_O_OU_Provider);
		rdProviderGroup.add(rdbtnOU_Provider); 
		rdProviderGroup.add(rdbtnO_OU_Provider); 
		rdProviderGroup.add(rdbtnP_O_Provider); 
		
		//Group the radio buttons Target Customer.
		ButtonGroup rdCustomerGroup = new ButtonGroup();
		rdCustomerGroup.add(rdbtnP_TCustomer); rdCustomerGroup.add(rdbtnP_O_TCustomer);
		rdCustomerGroup.add(rdbtnO_TCustomer); rdCustomerGroup.add(rdbtnP_OU_TCustomer);
		rdCustomerGroup.add(rdbtnOU_TCustomer); rdCustomerGroup.add(rdbtnP_O_OU_TCustomer);
		rdCustomerGroup.add(rdbtnO_OU_TCustomer);		
		
		imgPatternProvider = new JPanel();
		imgPatternProvider.setBackground(Color.GRAY);
		imgPatternProvider.setBounds(10, 168, 291, 146);
		panelPCSubgroup.add(imgPatternProvider);
		
		imgPatternCustomer = new JPanel();
		imgPatternCustomer.setBackground(Color.GRAY);
		imgPatternCustomer.setBounds(325, 168, 291, 146);
		panelPCSubgroup.add(imgPatternCustomer);
		
		//Possiveis paineis de escolha
		
		panelP_Provider = new JPanel();
		panelP_Provider.setBorder(new TitledBorder(null, "P-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelP_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_Provider);
		panelP_Provider.setLayout(null);
		panelP_Provider.setVisible(false);
		
		JLabel lblPerson_P_Provider = new JLabel("Person");
		lblPerson_P_Provider.setBounds(10, 24, 46, 14);
		panelP_Provider.add(lblPerson_P_Provider);
		
		txtServiceProvider_P_Provider = new JTextField();
		txtServiceProvider_P_Provider.setBounds(97, 59, 184, 20);
		panelP_Provider.add(txtServiceProvider_P_Provider);
		txtServiceProvider_P_Provider.setColumns(10);
		
		JLabel lblServiceProvider_P_Provider = new JLabel("Service Provider");
		lblServiceProvider_P_Provider.setBounds(10, 62, 86, 14);
		panelP_Provider.add(lblServiceProvider_P_Provider);
		
		txtPerson_P_Provider = new JTextField();
		txtPerson_P_Provider.setColumns(10);
		txtPerson_P_Provider.setBounds(97, 21, 184, 20);
		panelP_Provider.add(txtPerson_P_Provider);	
				
		panelP_TCustomer = new JPanel();
		panelP_TCustomer.setLayout(null);
		panelP_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_TCustomer);
		panelP_TCustomer.setVisible(false);
		
		JLabel lblPerson_P_TCustomer = new JLabel("Person");
		lblPerson_P_TCustomer.setBounds(10, 24, 76, 14);
		panelP_TCustomer.add(lblPerson_P_TCustomer);
		
		txtTargetCustomer_P_TCustomer = new JTextField();
		txtTargetCustomer_P_TCustomer.setColumns(10);
		txtTargetCustomer_P_TCustomer.setBounds(97, 59, 184, 20);
		panelP_TCustomer.add(txtTargetCustomer_P_TCustomer);
		
		JLabel lblTargetCustomer_P_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_P_TCustomer.setBounds(10, 62, 86, 14);
		panelP_TCustomer.add(lblTargetCustomer_P_TCustomer);
		
		txtPerson_P_TCustomer = new JTextField();
		txtPerson_P_TCustomer.setColumns(10);
		txtPerson_P_TCustomer.setBounds(97, 21, 184, 20);
		panelP_TCustomer.add(txtPerson_P_TCustomer);
				
		panelP_O_OU_Provider = new JPanel();
		panelP_O_OU_Provider.setLayout(null);
		panelP_O_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_OU_Provider);
		panelP_O_OU_Provider.setVisible(false);
		
		JLabel lblPersonProvider_P_O_OU_Provider = new JLabel("Person Provider");
		lblPersonProvider_P_O_OU_Provider.setBounds(10, 24, 76, 14);
		panelP_O_OU_Provider.add(lblPersonProvider_P_O_OU_Provider);
		
		txtServiceProvider_P_O_OU_Provider = new JTextField();
		txtServiceProvider_P_O_OU_Provider.setColumns(10);
		txtServiceProvider_P_O_OU_Provider.setBounds(137, 59, 144, 20);
		panelP_O_OU_Provider.add(txtServiceProvider_P_O_OU_Provider);
		
		JLabel lblServiceProvider_P_O_OU_Provider = new JLabel("Service Provider");
		lblServiceProvider_P_O_OU_Provider.setBounds(10, 62, 86, 14);
		panelP_O_OU_Provider.add(lblServiceProvider_P_O_OU_Provider);
		
		txtPersonProvider_P_O_OU_Provider = new JTextField();
		txtPersonProvider_P_O_OU_Provider.setColumns(10);
		txtPersonProvider_P_O_OU_Provider.setBounds(137, 21, 144, 20);
		panelP_O_OU_Provider.add(txtPersonProvider_P_O_OU_Provider);
		
		JLabel lblOrgUnitProvider_P_O_OU_Provider = new JLabel("Organization Unit Provider");
		lblOrgUnitProvider_P_O_OU_Provider.setBounds(10, 93, 126, 14);
		panelP_O_OU_Provider.add(lblOrgUnitProvider_P_O_OU_Provider);
		
		txtOrgUnitProvider_P_O_OU_Provider = new JTextField();
		txtOrgUnitProvider_P_O_OU_Provider.setColumns(10);
		txtOrgUnitProvider_P_O_OU_Provider.setBounds(137, 90, 144, 20);
		panelP_O_OU_Provider.add(txtOrgUnitProvider_P_O_OU_Provider);
		
		JLabel lblOrgProvider_P_O_OU_Provider = new JLabel("Organization Provider");
		lblOrgProvider_P_O_OU_Provider.setBounds(10, 129, 113, 14);
		panelP_O_OU_Provider.add(lblOrgProvider_P_O_OU_Provider);
		
		txtOrgProvider_P_O_OU_Provider = new JTextField();
		txtOrgProvider_P_O_OU_Provider.setColumns(10);
		txtOrgProvider_P_O_OU_Provider.setBounds(137, 126, 144, 20);
		panelP_O_OU_Provider.add(txtOrgProvider_P_O_OU_Provider);
		
		panelP_O_OU_TCustomer = new JPanel();
		panelP_O_OU_TCustomer.setLayout(null);
		panelP_O_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_OU_TCustomer);
		panelP_O_OU_TCustomer.setVisible(false);
		
		JLabel lblPersonTC_P_0_OU_TCustomer = new JLabel("Person Target Customer");
		lblPersonTC_P_0_OU_TCustomer.setBounds(10, 24, 117, 14);
		panelP_O_OU_TCustomer.add(lblPersonTC_P_0_OU_TCustomer);
		
		textField_35 = new JTextField();
		textField_35.setColumns(10);
		textField_35.setBounds(178, 59, 103, 20);
		panelP_O_OU_TCustomer.add(textField_35);
		
		JLabel lblTargetCustomer_P_0_OU_TCustomer = new JLabel("Target Customer");
		lblPersonTC_P_0_OU_TCustomer.setBounds(10, 62, 86, 14);
		panelP_O_OU_TCustomer.add(lblPersonTC_P_0_OU_TCustomer);
		
		textField_36 = new JTextField();
		textField_36.setColumns(10);
		textField_36.setBounds(178, 21, 103, 20);
		panelP_O_OU_TCustomer.add(textField_36);
		
		JLabel lblOrgUnitTC_P_0_OU_TCustomer = new JLabel("Organization Unit Target Customer");
		lblOrgUnitTC_P_0_OU_TCustomer.setBounds(10, 93, 167, 14);
		panelP_O_OU_TCustomer.add(lblOrgUnitTC_P_0_OU_TCustomer);
		
		textField_37 = new JTextField();
		textField_37.setColumns(10);
		textField_37.setBounds(178, 90, 103, 20);
		panelP_O_OU_TCustomer.add(textField_37);
		
		JLabel lblOrgTC_P_0_OU_TCustomer = new JLabel("Organization Target Customer");
		lblOrgTC_P_0_OU_TCustomer.setBounds(10, 129, 145, 14);
		panelP_O_OU_TCustomer.add(lblOrgTC_P_0_OU_TCustomer);
		
		textField_38 = new JTextField();
		textField_38.setColumns(10);
		textField_38.setBounds(178, 126, 103, 20);
		panelP_O_OU_TCustomer.add(textField_38);
		
		panelP_OU_TCustomer = new JPanel();
		panelP_OU_TCustomer.setLayout(null);
		panelP_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_OU_TCustomer);
		panelP_OU_TCustomer.setVisible(false);
		
		JLabel lblPersonTC_P_OU_TCustomer = new JLabel("Person Target Customer");
		lblPersonTC_P_OU_TCustomer.setBounds(10, 24, 117, 14);
		panelP_OU_TCustomer.add(lblPersonTC_P_OU_TCustomer);
		
		txtTargetCustomerTC_P_OU_TCustomer = new JTextField();
		txtTargetCustomerTC_P_OU_TCustomer.setColumns(10);
		txtTargetCustomerTC_P_OU_TCustomer.setBounds(178, 59, 103, 20);
		panelP_OU_TCustomer.add(txtTargetCustomerTC_P_OU_TCustomer);
		
		JLabel lblTargetCustomer_P_OU_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_P_OU_TCustomer.setBounds(10, 62, 86, 14);
		panelP_OU_TCustomer.add(lblTargetCustomer_P_OU_TCustomer);
		
		txtPersonTC_P_OU_TCustomer = new JTextField();
		txtPersonTC_P_OU_TCustomer.setColumns(10);
		txtPersonTC_P_OU_TCustomer.setBounds(178, 21, 103, 20);
		panelP_OU_TCustomer.add(txtPersonTC_P_OU_TCustomer);
		
		JLabel lblOrgUnitTC_P_OU_TCustomer = new JLabel("Organization Unit Target Customer");
		lblOrgUnitTC_P_OU_TCustomer.setBounds(10, 93, 172, 14);
		panelP_OU_TCustomer.add(lblOrgUnitTC_P_OU_TCustomer);
		
		txtOrgUnitTC_P_OU_TCustomer = new JTextField();
		txtOrgUnitTC_P_OU_TCustomer.setColumns(10);
		txtOrgUnitTC_P_OU_TCustomer.setBounds(178, 90, 103, 20);
		panelP_OU_TCustomer.add(txtOrgUnitTC_P_OU_TCustomer);
		
		JLabel lblOrgProvider_P_OU_TCustomer = new JLabel("Organization Provider");
		lblOrgProvider_P_OU_TCustomer.setBounds(10, 129, 113, 14);
		panelP_OU_TCustomer.add(lblOrgProvider_P_OU_TCustomer);
		
		txtOrgProvider_P_OU_TCustomer = new JTextField();
		txtOrgProvider_P_OU_TCustomer.setColumns(10);
		txtOrgProvider_P_OU_TCustomer.setBounds(178, 126, 103, 20);
		panelP_OU_TCustomer.add(txtOrgProvider_P_OU_TCustomer);
		
		panelP_O_TCustomer = new JPanel();
		panelP_O_TCustomer.setLayout(null);
		panelP_O_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_TCustomer);
		panelP_O_TCustomer.setVisible(false);
		
		JLabel lblPersonTC_P_O_TCustomer = new JLabel("Person Target Customer");
		lblPersonTC_P_O_TCustomer.setBounds(10, 24, 117, 14);
		panelP_O_TCustomer.add(lblPersonTC_P_O_TCustomer);
		
		txtTargetCustomer_P_O_TCustomer = new JTextField();
		txtTargetCustomer_P_O_TCustomer.setColumns(10);
		txtTargetCustomer_P_O_TCustomer.setBounds(157, 59, 124, 20);
		panelP_O_TCustomer.add(txtTargetCustomer_P_O_TCustomer);
		
		JLabel lblTargetCustomer_P_O_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_P_O_TCustomer.setBounds(10, 62, 86, 14);
		panelP_O_TCustomer.add(lblTargetCustomer_P_O_TCustomer);
		
		txtPersonTC_P_O_TCustomer = new JTextField();
		txtPersonTC_P_O_TCustomer.setColumns(10);
		txtPersonTC_P_O_TCustomer.setBounds(157, 21, 124, 20);
		panelP_O_TCustomer.add(txtPersonTC_P_O_TCustomer);
		
		JLabel lblOrgTC_P_O_TCustomer = new JLabel("Organization Target Customer");
		lblOrgTC_P_O_TCustomer.setBounds(10, 93, 154, 14);
		panelP_O_TCustomer.add(lblOrgTC_P_O_TCustomer);
		
		txtOrgTC_P_O_TCustomer = new JTextField();
		txtOrgTC_P_O_TCustomer.setColumns(10);
		txtOrgTC_P_O_TCustomer.setBounds(157, 90, 124, 20);
		panelP_O_TCustomer.add(txtOrgTC_P_O_TCustomer);
		
		panelO_OU_TCustomer = new JPanel();
		panelO_OU_TCustomer.setLayout(null);
		panelO_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelO_OU_TCustomer);
		panelO_OU_TCustomer.setVisible(false);
		
		JLabel lblOrgUnitTC_O_OU_TCustomer = new JLabel("Organizational Unit Target Customer");
		lblOrgUnitTC_O_OU_TCustomer.setBounds(61, 21, 197, 14);
		panelO_OU_TCustomer.add(lblOrgUnitTC_O_OU_TCustomer);
		
		txtTargetCustomer_O_OU_TCustomer = new JTextField();
		txtTargetCustomer_O_OU_TCustomer.setColumns(10);
		txtTargetCustomer_O_OU_TCustomer.setBounds(61, 83, 170, 20);
		panelO_OU_TCustomer.add(txtTargetCustomer_O_OU_TCustomer);
		
		JLabel lblTargetCustomer_O_OU_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_O_OU_TCustomer.setBounds(104, 69, 86, 14);
		panelO_OU_TCustomer.add(lblTargetCustomer_O_OU_TCustomer);
		
		txtOrgUnitTC_O_OU_TCustomer = new JTextField();
		txtOrgUnitTC_O_OU_TCustomer.setColumns(10);
		txtOrgUnitTC_O_OU_TCustomer.setBounds(61, 38, 170, 20);
		panelO_OU_TCustomer.add(txtOrgUnitTC_O_OU_TCustomer);
		
		JLabel lblOrgTC_O_OU_TCustomer = new JLabel("Organization Target Customer");
		lblOrgTC_O_OU_TCustomer.setBounds(75, 123, 156, 14);
		panelO_OU_TCustomer.add(lblOrgTC_O_OU_TCustomer);
		
		txtOrgTC_O_OU_TCustomer = new JTextField();
		txtOrgTC_O_OU_TCustomer.setColumns(10);
		txtOrgTC_O_OU_TCustomer.setBounds(61, 142, 170, 20);
		panelO_OU_TCustomer.add(txtOrgTC_O_OU_TCustomer);
		
		panelOU_TCustomer = new JPanel();
		panelOU_TCustomer.setLayout(null);
		panelOU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelOU_TCustomer);
		panelOU_TCustomer.setVisible(false);
		
		JLabel lblOrgUnit_OU_TCustomer = new JLabel("Organizational Unit");
		lblOrgUnit_OU_TCustomer.setBounds(10, 24, 117, 14);
		panelOU_TCustomer.add(lblOrgUnit_OU_TCustomer);
		
		txtTargetCustomer_OU_TCustomer = new JTextField();
		txtTargetCustomer_OU_TCustomer.setColumns(10);
		txtTargetCustomer_OU_TCustomer.setBounds(119, 59, 162, 20);
		panelOU_TCustomer.add(txtTargetCustomer_OU_TCustomer);
		
		JLabel lblTargetCustomer_OU_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_OU_TCustomer.setBounds(10, 62, 86, 14);
		panelOU_TCustomer.add(lblTargetCustomer_OU_TCustomer);
		
		txtOrgUnit_OU_TCustomer = new JTextField();
		txtOrgUnit_OU_TCustomer.setColumns(10);
		txtOrgUnit_OU_TCustomer.setBounds(119, 21, 162, 20);
		panelOU_TCustomer.add(txtOrgUnit_OU_TCustomer);
		
		panelO_TCustomer = new JPanel();
		panelO_TCustomer.setLayout(null);
		panelO_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelO_TCustomer);
		panelO_TCustomer.setVisible(false);
		
		JLabel lblOrganization_O_TCustomer = new JLabel("Organization");
		lblOrganization_O_TCustomer.setBounds(10, 24, 76, 14);
		panelO_TCustomer.add(lblOrganization_O_TCustomer);
		
		txtTargetCustomer_O_TCustomer = new JTextField();
		txtTargetCustomer_O_TCustomer.setColumns(10);
		txtTargetCustomer_O_TCustomer.setBounds(137, 59, 144, 20);
		panelO_TCustomer.add(txtTargetCustomer_O_TCustomer);
		
		JLabel lblTargetCustomer_O_TCustomer = new JLabel("Target Customer");
		lblTargetCustomer_O_TCustomer.setBounds(10, 62, 86, 14);
		panelO_TCustomer.add(lblTargetCustomer_O_TCustomer);
		
		txtOrganization_O_TCustomer = new JTextField();
		txtOrganization_O_TCustomer.setColumns(10);
		txtOrganization_O_TCustomer.setBounds(137, 21, 144, 20);
		panelO_TCustomer.add(txtOrganization_O_TCustomer);
		
		panelP_OU_Provider = new JPanel();
		panelP_OU_Provider.setLayout(null);
		panelP_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_OU_Provider);
		panelP_OU_Provider.setVisible(false);
		
		JLabel lblPersonProvider_P_OU_Provider = new JLabel("Person Provider");
		lblPersonProvider_P_OU_Provider.setBounds(10, 24, 76, 14);
		panelP_OU_Provider.add(lblPersonProvider_P_OU_Provider);
		
		txtServiceProvider_P_OU_Provider = new JTextField();
		txtServiceProvider_P_OU_Provider.setColumns(10);
		txtServiceProvider_P_OU_Provider.setBounds(143, 59, 138, 20);
		panelP_OU_Provider.add(txtServiceProvider_P_OU_Provider);
		
		JLabel lblServiceProvider_P_OU_Provider = new JLabel("Service Provider");
		lblServiceProvider_P_OU_Provider.setBounds(10, 62, 86, 14);
		panelP_OU_Provider.add(lblServiceProvider_P_OU_Provider);
		
		txtPersonProvider_P_OU_Provider = new JTextField();
		txtPersonProvider_P_OU_Provider.setColumns(10);
		txtPersonProvider_P_OU_Provider.setBounds(143, 21, 138, 20);
		panelP_OU_Provider.add(txtPersonProvider_P_OU_Provider);
		
		JLabel lblOrgUnitProvider_P_OU_Provider = new JLabel("Organization Unit Provider");
		lblOrgUnitProvider_P_OU_Provider.setBounds(10, 93, 126, 14);
		panelP_OU_Provider.add(lblOrgUnitProvider_P_OU_Provider);
		
		txtOrgUnitProvider_P_OU_Provider = new JTextField();
		txtOrgUnitProvider_P_OU_Provider.setColumns(10);
		txtOrgUnitProvider_P_OU_Provider.setBounds(143, 90, 138, 20);
		panelP_OU_Provider.add(txtOrgUnitProvider_P_OU_Provider);
		
		panelP_O_Provider = new JPanel();
		panelP_O_Provider.setLayout(null);
		panelP_O_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_Provider);
		panelP_O_Provider.setVisible(false);
		
		JLabel lblPersonProvider_P_O_Provider = new JLabel("Person Provider");
		lblPersonProvider_P_O_Provider.setBounds(10, 24, 76, 14);
		panelP_O_Provider.add(lblPersonProvider_P_O_Provider);
		
		txtServiceProvider_P_O_Provider = new JTextField();
		txtServiceProvider_P_O_Provider.setColumns(10);
		txtServiceProvider_P_O_Provider.setBounds(117, 59, 164, 20);
		panelP_O_Provider.add(txtServiceProvider_P_O_Provider);
		
		JLabel lblServiceProvider_P_O_Provider = new JLabel("Service Provider");
		lblServiceProvider_P_O_Provider.setBounds(10, 62, 86, 14);
		panelP_O_Provider.add(lblServiceProvider_P_O_Provider);
		
		txtPersonProvider_P_O_Provider = new JTextField();
		txtPersonProvider_P_O_Provider.setColumns(10);
		txtPersonProvider_P_O_Provider.setBounds(117, 21, 164, 20);
		panelP_O_Provider.add(txtPersonProvider_P_O_Provider);
		
		txtOrganizationProvider_P_O_Provider = new JTextField();
		txtOrganizationProvider_P_O_Provider.setColumns(10);
		txtOrganizationProvider_P_O_Provider.setBounds(117, 90, 164, 20);
		panelP_O_Provider.add(txtOrganizationProvider_P_O_Provider);
		
		JLabel lblOrganizationProvider_P_O_Provider = new JLabel("Organization Provider");
		lblOrganizationProvider_P_O_Provider.setBounds(10, 93, 109, 14);
		panelP_O_Provider.add(lblOrganizationProvider_P_O_Provider);
		
		panelO_OU_Provider = new JPanel();
		panelO_OU_Provider.setLayout(null);
		panelO_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelO_OU_Provider);
		panelO_OU_Provider.setVisible(false);
		
		JLabel lblOrgUnitProvider_O_OU_Provider = new JLabel("Organization Unit Provider");
		lblOrgUnitProvider_O_OU_Provider.setBounds(10, 24, 126, 14);
		panelO_OU_Provider.add(lblOrgUnitProvider_O_OU_Provider);
		
		txtServiceProvider_O_OU_Provider = new JTextField();
		txtServiceProvider_O_OU_Provider.setColumns(10);
		txtServiceProvider_O_OU_Provider.setBounds(146, 59, 135, 20);
		panelO_OU_Provider.add(txtServiceProvider_O_OU_Provider);
		
		JLabel lblServiceProvider_O_OU_Provider = new JLabel("Service Provider");
		lblServiceProvider_O_OU_Provider.setBounds(10, 62, 86, 14);
		panelO_OU_Provider.add(lblServiceProvider_O_OU_Provider);
		
		txtOrgUnitProvider_O_OU_Provider = new JTextField();
		txtOrgUnitProvider_O_OU_Provider.setColumns(10);
		txtOrgUnitProvider_O_OU_Provider.setBounds(146, 21, 135, 20);
		panelO_OU_Provider.add(txtOrgUnitProvider_O_OU_Provider);
		
		JLabel lblOrganizationProvider_O_OU_Provider = new JLabel("Organization Provider");
		lblOrganizationProvider_O_OU_Provider.setBounds(10, 93, 111, 14);
		panelO_OU_Provider.add(lblOrganizationProvider_O_OU_Provider);
		
		txtOrganizationProvider_O_OU_Provider = new JTextField();
		txtOrganizationProvider_O_OU_Provider.setColumns(10);
		txtOrganizationProvider_O_OU_Provider.setBounds(146, 90, 135, 20);
		panelO_OU_Provider.add(txtOrganizationProvider_O_OU_Provider);
		
		panelOU_Provider = new JPanel();
		panelOU_Provider.setLayout(null);
		panelOU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelOU_Provider);
		panelOU_Provider.setVisible(false);
		
		JLabel lblOrgUnit_OU_Provider = new JLabel("Organization Unit");
		lblOrgUnit_OU_Provider.setBounds(10, 24, 107, 14);
		panelOU_Provider.add(lblOrgUnit_OU_Provider);
		
		txtServiceProvider_OU_Provider = new JTextField();
		txtServiceProvider_OU_Provider.setColumns(10);
		txtServiceProvider_OU_Provider.setBounds(102, 59, 179, 20);
		panelOU_Provider.add(txtServiceProvider_OU_Provider);
		
		JLabel lblServiceProvider_OU_Provider = new JLabel("Service Provider");
		lblServiceProvider_OU_Provider.setBounds(10, 62, 86, 14);
		panelOU_Provider.add(lblServiceProvider_OU_Provider);
		
		txtOrgUnit_OU_Provider = new JTextField();
		txtOrgUnit_OU_Provider.setColumns(10);
		txtOrgUnit_OU_Provider.setBounds(102, 21, 179, 20);
		panelOU_Provider.add(txtOrgUnit_OU_Provider);
		
		panelO_Provider = new JPanel();
		panelO_Provider.setLayout(null);
		panelO_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelO_Provider);
		panelO_Provider.setVisible(false);
		
		JLabel lblOrganization_O_Provider = new JLabel("Organization");
		lblOrganization_O_Provider.setBounds(10, 24, 76, 14);
		panelO_Provider.add(lblOrganization_O_Provider);
		
		txtServiceProvider_O_Provider = new JTextField();
		txtServiceProvider_O_Provider.setColumns(10);
		txtServiceProvider_O_Provider.setBounds(97, 59, 184, 20);
		panelO_Provider.add(txtServiceProvider_O_Provider);
		
		JLabel lblServiceProvider_O_Provider = new JLabel("Service Provider");
		lblServiceProvider_O_Provider.setBounds(10, 62, 86, 14);
		panelO_Provider.add(lblServiceProvider_O_Provider);
		
		txtOrganization_O_Provider = new JTextField();
		txtOrganization_O_Provider.setColumns(10);
		txtOrganization_O_Provider.setBounds(97, 21, 184, 20);
		panelO_Provider.add(txtOrganization_O_Provider);
		
		//Register a listener for the radio buttons		
		
		rdbtnP_Provider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				desabilitaPaineisProvider();
				trocaPainel(panelP_Provider);	
			}	
		});	
		
		rdbtnO_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisProvider();
				trocaPainel(panelO_Provider);			
			}
		});
		rdbtnOU_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				desabilitaPaineisProvider();
				trocaPainel(panelOU_Provider);				
			}
		});
		rdbtnO_OU_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisProvider();
				trocaPainel(panelO_OU_Provider);				
			}
		});
		rdbtnP_O_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisProvider();
				trocaPainel(panelP_O_Provider);				
			}
		});
		rdbtnP_OU_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desabilitaPaineisProvider();
				trocaPainel(panelP_OU_Provider);				
			}
		});
		rdbtnP_O_OU_Provider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisProvider();
				trocaPainel(panelP_O_OU_Provider);				
			}
		});
		
				
		//Register a listener for the radio buttons Target Customer
		rdbtnP_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();
				trocaPainel(panelP_TCustomer);			
			}
		});
		rdbtnO_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();
				trocaPainel(panelO_TCustomer);			
			}
		});
		rdbtnOU_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();		
				trocaPainel(panelOU_TCustomer);				
			}
		});
		rdbtnO_OU_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						
				desabilitaPaineisCustomer();
				trocaPainel(panelO_OU_TCustomer);				
			}
		});
		rdbtnP_O_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();
				trocaPainel(panelP_O_TCustomer);				
			}
		});
		rdbtnP_OU_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();
				trocaPainel(panelP_OU_TCustomer);				
			}
		});
		rdbtnP_O_OU_TCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisCustomer();
				trocaPainel(panelP_O_OU_TCustomer);				
			}
		});		
		
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	public static void trocaPainel(JPanel panel ){		
		panel.setVisible(true);
	}
	
	public void desabilitaPaineisProvider(){
		panelP_Provider.setVisible(false);	
		panelP_O_OU_Provider.setVisible(false);
		panelP_OU_Provider.setVisible(false);
		panelP_O_Provider.setVisible(false);
		panelO_OU_Provider.setVisible(false);
		panelOU_Provider.setVisible(false);
		panelO_Provider.setVisible(false);
	}
	
	public void desabilitaPaineisCustomer(){
		panelP_O_OU_TCustomer.setVisible(false);
		panelP_OU_TCustomer.setVisible(false);
		panelP_O_TCustomer.setVisible(false);
		panelO_OU_TCustomer.setVisible(false);
		panelOU_TCustomer.setVisible(false);
		panelO_TCustomer.setVisible(false);
		panelP_TCustomer.setVisible(false);
	}	
	
	public void trocaPainelPrincipal(int n){
		painelSelecionado += n;
		
		//Escolhe qual painel sera exibido !
		
		if(painelSelecionado == 0){
			panelPCSubgroup.setVisible(true);
		}
		else if(painelSelecionado == 1){
			
		}
	}
}
