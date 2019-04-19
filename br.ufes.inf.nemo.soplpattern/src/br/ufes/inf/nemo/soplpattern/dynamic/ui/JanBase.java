package br.ufes.inf.nemo.soplpattern.dynamic.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.soplpattern.impl.EntryPoint;
import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
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
	private JTextField txtPersonTC_P_O_OU_TCustomer;
	private JTextField txtTargetCustomer_P_O_OU_TCustomer;
	private JTextField txtOrgUnitTC_P_O_OU_TCustomer;
	private JTextField txtOrgTC_P_O_OU_TCustomer;
	private SOPLPattern soplPattern;
	
	private JTextField txtServiceProvider_P_HProvider;
	private JTextField txtPerson_P_HProvider;
	private JTextField txtTargetCustomer_P_Customer;
	private JTextField txtPerson_P_Customer;
	private JTextField txtServiceProvider_P_O_OU_HProvider;
	private JTextField txtPersonProvider_P_O_OU_HProvider;
	private JTextField txtOrgProvider_P_O_OU_HProvider;
	private JTextField txtPersonTC_P_O_OU_Customer;
	private JTextField txtTargetCustomer_P_O_OU_Customer;
	private JTextField txtOrgUnitTC_P_O_OU_Customer;
	private JTextField txtOrgTC_P_O_OU_Customer;
	private JTextField txtTargetCustomerTC_P_OU_Customer;
	private JTextField txtPersonTC_P_OU_Customer;
	private JTextField txtOrgUnitTC_P_OU_Customer;
	private JTextField txtOrgProvider_P_OU_Customer;
	private JTextField txtTargetCustomer_P_O_Customer;
	private JTextField txtPersonTC_P_O_Customer;
	private JTextField txtOrgTC_P_O_Customer;
	private JTextField txtTargetCustomer_O_OU_Customer;
	private JTextField txtOrgUnitTC_O_OU_Customer;
	private JTextField txtOrgTC_O_OU_Customer;
	private JTextField txtTargetCustomer_OU_Customer;
	private JTextField txtOrgUnit_OU_Customer;
	private JTextField txtTargetCustomer_O_Customer;
	private JTextField txtOrgUnitProvider_P_O_OU_HProvider;
	
	private JTextField txtOrganization_O_Customer;
	private JTextField txtServiceProvider_P_OU_HProvider;
	private JTextField txtPersonProvider_P_OU_HProvider;
	private JTextField txtOrgUnitProvider_P_OU_HProvider;
	private JTextField txtServiceProvider_P_O_HProvider;
	private JTextField txtPersonProvider_P_O_HProvider;
	private JTextField txtOrganizationProvider_P_O_HProvider;
	private JTextField txtServiceProvider_O_OU_HProvider;
	private JTextField txtOrgUnitProvider_O_OU_HProvider;
	private JTextField txtOrganizationProvider_O_OU_HProvider;
	private JTextField txtServiceProvider_OU_HProvider;
	private JTextField txtOrgUnit_OU_HProvider;
	private JTextField txtServiceProvider_O_HProvider;
	private JTextField txtOrganization_O_HProvider;

	
	//PANEL PROVIDER AND TARGET CUSTOMER (VARIANT PATTERNS)	
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
	
	//PANEL HIRED PROVIDER AND SERVICE CUSTOMER (VARIANT PATTERNS) - !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!FALTA IMPLEMENTAR!!!!!!!!!!!	
	private static JPanel imgPatternHiredProvider;
	private static JPanel imgPatternServiceCustomer;
	private static JPanel panelHiredP_ServiceC_Subgroup; //Panel que contem todos os paines de cada variant patter que pode ser escolhido.
	private static JPanel panelP_HProvider;
	private static JPanel panelO_OU_HProvider;
	private static JPanel panelPatternHiredProvider;
	private static JPanel panelPatternServiceCustomer;
	private static JPanel panelO_HProvider;
	private static JPanel panelP_O_HProvider;
	private static JPanel panelOU_HProvider;
	private static JPanel panelP_OU_HProvider;
	private static JPanel panelP_O_OU_HProvider;
	private static JPanel panelP_Customer;
	private static JPanel panelO_OU_Customer;
	private static JPanel panelO_Customer;
	private static JPanel panelP_O_Customer;
	private static JPanel panelOU_Customer;
	private static JPanel panelP_OU_Customer;
	private static JPanel panelP_O_OU_Customer;
	
	//PANEL SOFFERING
	private static JPanel panelSOffering;
	private static JPanel panelSODescription;
	private static JPanel panelSOCommitment;
	private static JPanel panelSNegAgree;
	private JTextField txtServiceOffering;
	private JTextField txtTargetCC;
	private JTextField txtServiceOfferingDescription;
	private JTextField txtServiceProvider;
	private JTextField txtServiceOfferingCommitment;
	private JTextField txtServiceOfferingClaim;
	
	//JRadioButtons Provider and Customer - Variant Patterns
	
	private static JRadioButton rdbtnP_Provider;
	private static JRadioButton rdbtnP_OU_Provider;
	private static JRadioButton rdbtnO_Provider;
	private static JRadioButton rdbtnP_O_OU_Provider;
	private static JRadioButton rdbtnOU_Provider;
	private static JRadioButton rdbtnO_OU_Provider;
	private static JRadioButton rdbtnP_O_Provider;

	private static JRadioButton rdbtnP_TCustomer;
	private static JRadioButton rdbtnP_O_TCustomer;
	private static JRadioButton rdbtnO_TCustomer;
	private static JRadioButton rdbtnP_OU_TCustomer;
	private static JRadioButton rdbtnOU_TCustomer ;
	private static JRadioButton rdbtnP_O_OU_TCustomer;
	private static JRadioButton rdbtnO_OU_TCustomer;
	
	//JRadioButtons Hired Provider and Service Customer - Variant Patterns
	
	private static JRadioButton rdbtnP_HProvider;
	private static JRadioButton rdbtnO_OU_HProvider;
	private static JRadioButton rdbtnO_HProvider;
	private static JRadioButton rdbtnP_O_HProvider;
	private static JRadioButton rdbtnOU_HProvider;
	private static JRadioButton rdbtnP_OU_HProvider;
	private static JRadioButton rdbtnP_O_OU_HProvider;

	private static JRadioButton rdbtnP_Customer;
	private static JRadioButton rdbtnO_OU_Customer;
	private static JRadioButton rdbtnO_Customer;
	private static JRadioButton rdbtnP_O_Customer;
	private static JRadioButton rdbtnOU_Customer ;
	private static JRadioButton rdbtnP_OU_Customer;
	private static JRadioButton rdbtnP_O_OU_Customer;
	
	//Button Groups
	
	ButtonGroup rdCustomerGroup;
	ButtonGroup rdProviderGroup;
	ButtonGroup rdServiceCustomerGroup;
	ButtonGroup rdHiredProviderGroup;
	
	//Imagens
	
	 //Provider - Variant Patterns
	
	private static ImageIcon icon_P_Provider;
	private static JLabel imagem_P_Provider;
	private static ImageIcon icon_O_Provider;
	private static JLabel imagem_O_Provider;
	private static ImageIcon icon_OU_Provider;
	private static JLabel imagem_OU_Provider;
	private static ImageIcon icon_P_O_OU_Provider;
	private static JLabel imagem_P_O_OU_Provider;
	private static ImageIcon icon_O_OU_Provider;
	private static JLabel imagem_O_OU_Provider;
	private static ImageIcon icon_P_O_Provider;
	private static JLabel imagem_P_O_Provider;
	private static ImageIcon icon_P_OU_Provider;
	private static JLabel imagem_P_OU_Provider;
	
	 //Target Customer - Variant Patterns
	
	private static ImageIcon icon_P_TCustomer;
	private static JLabel imagem_P_TCustomer;
	private static ImageIcon icon_O_TCustomer;
	private static JLabel imagem_O_TCustomer;
	private static ImageIcon icon_OU_TCustomer;
	private static JLabel imagem_OU_TCustomer;
	private static ImageIcon icon_P_O_OU_TCustomer;
	private static JLabel imagem_P_O_OU_TCustomer;
	private static ImageIcon icon_O_OU_TCustomer;
	private static JLabel imagem_O_OU_TCustomer;
	private static ImageIcon icon_P_O_TCustomer;
	private static JLabel imagem_P_O_TCustomer;
	private static ImageIcon icon_P_OU_TCustomer;
	private static JLabel imagem_P_OU_TCustomer;
	
	 //Hired Provider - Variant Patterns
	
	private static ImageIcon icon_P_HProvider;
	private static JLabel imagem_P_HProvider;
	private static ImageIcon icon_O_HProvider;
	private static JLabel imagem_O_HProvider;
	private static ImageIcon icon_OU_HProvider;
	private static JLabel imagem_OU_HProvider;
	private static ImageIcon icon_P_O_OU_HProvider;
	private static JLabel imagem_P_O_OU_HProvider;
	private static ImageIcon icon_O_OU_HProvider;
	private static JLabel imagem_O_OU_HProvider;
	private static ImageIcon icon_P_O_HProvider;
	private static JLabel imagem_P_O_HProvider;
	private static ImageIcon icon_P_OU_HProvider;
	private static JLabel imagem_P_OU_HProvider;
	
	 //Service Customer - Variant Patterns
	
	private static ImageIcon icon_P_Customer;
	private static JLabel imagem_P_Customer;
	private static ImageIcon icon_O_Customer;
	private static JLabel imagem_O_Customer;
	private static ImageIcon icon_OU_Customer;
	private static JLabel imagem_OU_Customer;
	private static ImageIcon icon_P_O_OU_Customer;
	private static JLabel imagem_P_O_OU_Customer;
	private static ImageIcon icon_O_OU_Customer;
	private static JLabel imagem_O_OU_Customer;
	private static ImageIcon icon_P_O_Customer;
	private static JLabel imagem_P_O_Customer;
	private static ImageIcon icon_P_OU_Customer;
	private static JLabel imagem_P_OU_Customer;
	
	
	//Imagens para o restante dos padrões
	private ImageIcon iconSOffering;
	private JLabel imagemSOffering;
	private ImageIcon iconSODescription;
	private JLabel imagemSODescription;
	private ImageIcon iconSOCommitment;
	private JLabel imagemSOCommitment;
	private ImageIcon iconSNegAgree;
	private JLabel imagemSNegAgree;

	public int entrypoint = 1; //Por padrao o entrypoint é a SOffering

	/**
	 * Create the application.
	 */
	public JanBase(final SOPLPattern pattern, int entry) {
		initialize();
		frame.setVisible(true);
		soplPattern = pattern;	
		this.entrypoint = entry;
		EntryPoint(entry);
	}
	
	public void EntryPoint(int entrypoint)
	{
		desabilitaPaineisPrincipais();
		if(entrypoint == 1) {// 1 = Entry Point SOffering
			trocaPainelPrincipalSOffering(0); //Comeca do Panel 0 - SOffering
		}else{ // 2 = Entry Point SAgreement
			trocaPainelPrincipalSAgreement(0);  //Comeca do Panel 0 - SAgreement
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 609);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(entrypoint == 1) {
					trocaPainelPrincipalSOffering(1);
				}else {
					trocaPainelPrincipalSAgreement(1);
				}
				
			}
		});
		btnNext.setBounds(678, 522, 89, 37);
		frame.getContentPane().add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(entrypoint == 1) {
					trocaPainelPrincipalSOffering(-1);
				}else {
					trocaPainelPrincipalSAgreement(-1);
				}
			}
		});
		btnBack.setBounds(579, 522, 89, 37);
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
		
		panelHiredP_ServiceC_Subgroup = new JPanel();
		panelHiredP_ServiceC_Subgroup.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelHiredP_ServiceC_Subgroup);
		panelHiredP_ServiceC_Subgroup.setLayout(null);	
		
		panelPatternProvider = new JPanel();
		panelPatternProvider.setBorder(new TitledBorder(null, "Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatternProvider.setBounds(10,11, 291, 152);
		panelPCSubgroup.add(panelPatternProvider);
		panelPatternProvider.setLayout(null);	
		
		panelPatternHiredProvider = new JPanel();
		panelPatternHiredProvider.setBorder(new TitledBorder(null, "Hired Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatternHiredProvider.setBounds(10,11, 291, 152);
		panelHiredP_ServiceC_Subgroup.add(panelPatternHiredProvider);
		panelPatternHiredProvider.setLayout(null);
						
		rdbtnP_Provider = new JRadioButton("P-Provider");
		rdbtnP_Provider.setBounds(6, 22, 109, 23);
		rdbtnP_Provider.setName("P_Provider");
		panelPatternProvider.add(rdbtnP_Provider);
		
		rdbtnO_Provider = new JRadioButton("O-Provider");
		rdbtnO_Provider.setBounds(6, 48, 109, 23);
		rdbtnO_Provider.setName("O_Provider");
		panelPatternProvider.add(rdbtnO_Provider);
		
		rdbtnOU_Provider = new JRadioButton("OU-Provider");
		rdbtnOU_Provider.setBounds(6, 72, 109, 23);
		rdbtnOU_Provider.setName("OU_Provider");
		panelPatternProvider.add(rdbtnOU_Provider);
		
		rdbtnO_OU_Provider = new JRadioButton("O-OU-Provider");
		rdbtnO_OU_Provider.setBounds(131, 22, 109, 23);
		rdbtnO_OU_Provider.setName("O_OU_Provider");
		panelPatternProvider.add(rdbtnO_OU_Provider);
		
		rdbtnP_O_Provider = new JRadioButton("P-O-Provider");
		rdbtnP_O_Provider.setBounds(131, 48, 109, 23);
		rdbtnP_O_Provider.setName("P_O_Provider");
		panelPatternProvider.add(rdbtnP_O_Provider);
		
		rdbtnP_OU_Provider = new JRadioButton("P-OU-Provider");
		rdbtnP_OU_Provider.setBounds(131, 72, 109, 23);
		rdbtnP_OU_Provider.setName("P_OU_Provider");
		panelPatternProvider.add(rdbtnP_OU_Provider);
		
		rdbtnP_O_OU_Provider = new JRadioButton("P-O-OU-Provider");
		rdbtnP_O_OU_Provider.setBounds(6, 98, 109, 23);
		rdbtnP_O_OU_Provider.setName("P_O_OU_Provider");
		panelPatternProvider.add(rdbtnP_O_OU_Provider);
		
		//--------------//----------------------------------------//
		rdbtnP_HProvider = new JRadioButton("P-HProvider");
		rdbtnP_HProvider.setBounds(6, 22, 109, 23);
		rdbtnP_HProvider.setName("P_HProvider");
		panelPatternHiredProvider.add(rdbtnP_HProvider);
		
		rdbtnO_HProvider = new JRadioButton("O-HProvider");
		rdbtnO_HProvider.setBounds(6, 48, 109, 23);
		rdbtnO_HProvider.setName("O_HProvider");
		panelPatternHiredProvider.add(rdbtnO_HProvider);
		
		rdbtnOU_HProvider = new JRadioButton("OU-HProvider");
		rdbtnOU_HProvider.setBounds(6, 72, 109, 23);
		rdbtnOU_HProvider.setName("OU_HProvider");
		panelPatternHiredProvider.add(rdbtnOU_HProvider);
		
		rdbtnO_OU_HProvider = new JRadioButton("O-OU-HProvider");
		rdbtnO_OU_HProvider.setBounds(131, 22, 109, 23);
		rdbtnO_OU_HProvider.setName("O_OU_HProvider");
		panelPatternHiredProvider.add(rdbtnO_OU_HProvider);
		
		rdbtnP_O_HProvider = new JRadioButton("P-O-HProvider");
		rdbtnP_O_HProvider.setBounds(131, 48, 109, 23);
		rdbtnP_O_HProvider.setName("P_O_HProvider");
		panelPatternHiredProvider.add(rdbtnP_O_HProvider);
		
		rdbtnP_OU_HProvider = new JRadioButton("P-OU-HProvider");
		rdbtnP_OU_HProvider.setBounds(131, 72, 109, 23);
		rdbtnP_OU_HProvider.setName("P_OU_HProvider");
		panelPatternHiredProvider.add(rdbtnP_OU_HProvider);
		
		rdbtnP_O_OU_HProvider = new JRadioButton("P-O-OU-HProvider");
		rdbtnP_O_OU_HProvider.setBounds(6, 98, 109, 23);
		rdbtnP_O_OU_HProvider.setName("P_O_OU_HProvider");
		panelPatternHiredProvider.add(rdbtnP_O_OU_HProvider);
		//-------------//------------------------------------------//
		
		panelPatternCustomer = new JPanel();
		panelPatternCustomer.setLayout(null);
		panelPatternCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Target Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPatternCustomer.setBounds(325,11, 286, 152);
		panelPCSubgroup.add(panelPatternCustomer);
		
		panelPatternServiceCustomer = new JPanel();
		panelPatternServiceCustomer.setLayout(null);
		panelPatternServiceCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Service Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPatternServiceCustomer.setBounds(325,11, 286, 152);
		panelHiredP_ServiceC_Subgroup.add(panelPatternServiceCustomer);
		
		rdbtnP_TCustomer = new JRadioButton("P-TCustomer");
		rdbtnP_TCustomer.setBounds(6, 22, 109, 23);
		rdbtnP_TCustomer.setName("P_TCustomer");
		panelPatternCustomer.add(rdbtnP_TCustomer);
		
		rdbtnO_TCustomer = new JRadioButton("O-TCustomer");
		rdbtnO_TCustomer.setBounds(6, 48, 109, 23);
		rdbtnO_TCustomer.setName("O_TCustomer");
		panelPatternCustomer.add(rdbtnO_TCustomer);
		
		rdbtnOU_TCustomer = new JRadioButton("OU-TCustomer");
		rdbtnOU_TCustomer.setBounds(6, 72, 109, 23);
		rdbtnOU_TCustomer.setName("OU_TCustomer");
		panelPatternCustomer.add(rdbtnOU_TCustomer);
		
		rdbtnO_OU_TCustomer = new JRadioButton("O-OU-TCustomer");
		rdbtnO_OU_TCustomer.setBounds(131, 22, 109, 23);
		rdbtnO_OU_TCustomer.setName("O_OU_TCustomer");
		panelPatternCustomer.add(rdbtnO_OU_TCustomer);
		
		rdbtnP_O_TCustomer = new JRadioButton("P-O-TCustomer");
		rdbtnP_O_TCustomer.setBounds(131, 48, 109, 23);
		rdbtnP_O_TCustomer.setName("P_O_TCustomer");
		panelPatternCustomer.add(rdbtnP_O_TCustomer);
		
		rdbtnP_OU_TCustomer = new JRadioButton("P-OU-TCustomer");
		rdbtnP_OU_TCustomer.setBounds(131, 72, 109, 23);
		rdbtnP_OU_TCustomer.setName("P_OU_TCustomer");
		panelPatternCustomer.add(rdbtnP_OU_TCustomer);
		
		rdbtnP_O_OU_TCustomer = new JRadioButton("P-O-OU-TCustomer");
		rdbtnP_O_OU_TCustomer.setBounds(6, 98, 125, 23);
		rdbtnP_O_OU_TCustomer.setName("P_O_OU_TCustomer");
		panelPatternCustomer.add(rdbtnP_O_OU_TCustomer);
		
		//-----------------------//----------------------//-------------
		
		rdbtnP_Customer = new JRadioButton("P-Customer");
		rdbtnP_Customer.setBounds(6, 22, 109, 23);
		rdbtnP_Customer.setName("P_Customer");
		panelPatternServiceCustomer.add(rdbtnP_Customer);
		
		rdbtnO_Customer = new JRadioButton("O-Customer");
		rdbtnO_Customer.setBounds(6, 48, 109, 23);
		rdbtnO_Customer.setName("O_Customer");
		panelPatternServiceCustomer.add(rdbtnO_Customer);
		
		rdbtnOU_Customer = new JRadioButton("OU-Customer");
		rdbtnOU_Customer.setBounds(6, 72, 109, 23);
		rdbtnOU_Customer.setName("OU_Customer");
		panelPatternServiceCustomer.add(rdbtnOU_Customer);
		
		rdbtnO_OU_Customer = new JRadioButton("O-OU-Customer");
		rdbtnO_OU_Customer.setBounds(131, 22, 109, 23);
		rdbtnO_OU_Customer.setName("O_OU_Customer");
		panelPatternServiceCustomer.add(rdbtnO_OU_Customer);
		
		rdbtnP_O_Customer = new JRadioButton("P-O-Customer");
		rdbtnP_O_Customer.setBounds(131, 48, 109, 23);
		rdbtnP_O_Customer.setName("P_O_Customer");
		panelPatternServiceCustomer.add(rdbtnP_O_Customer);
		
		rdbtnP_OU_Customer = new JRadioButton("P-OU-Customer");
		rdbtnP_OU_Customer.setBounds(131, 72, 109, 23);
		rdbtnP_OU_Customer.setName("P_OU_Customer");
		panelPatternServiceCustomer.add(rdbtnP_OU_Customer);
		
		rdbtnP_O_OU_Customer = new JRadioButton("P-O-OU-Customer");
		rdbtnP_O_OU_Customer.setBounds(6, 98, 125, 23);
		rdbtnP_O_OU_Customer.setName("P_O_OU_Customer");
		panelPatternServiceCustomer.add(rdbtnP_O_OU_Customer);
		
		//Group the radio buttons Provider.
		rdProviderGroup = new ButtonGroup();
		rdProviderGroup.add(rdbtnP_Provider); rdProviderGroup.add(rdbtnP_OU_Provider);
		rdProviderGroup.add(rdbtnO_Provider); rdProviderGroup.add(rdbtnP_O_OU_Provider);
		rdProviderGroup.add(rdbtnOU_Provider); 
		rdProviderGroup.add(rdbtnO_OU_Provider); 
		rdProviderGroup.add(rdbtnP_O_Provider);
		
		//Group the radio buttons Hired Provider.
		rdHiredProviderGroup = new ButtonGroup();
		rdHiredProviderGroup.add(rdbtnP_HProvider); rdHiredProviderGroup.add(rdbtnP_OU_HProvider);
		rdHiredProviderGroup.add(rdbtnO_HProvider); rdHiredProviderGroup.add(rdbtnP_O_OU_HProvider);
		rdHiredProviderGroup.add(rdbtnOU_HProvider); 
		rdHiredProviderGroup.add(rdbtnO_OU_HProvider); 
		rdHiredProviderGroup.add(rdbtnP_O_HProvider); 
		
		//Group the radio buttons Target Customer.
		rdCustomerGroup = new ButtonGroup();
		rdCustomerGroup.add(rdbtnP_TCustomer); rdCustomerGroup.add(rdbtnP_O_TCustomer);
		rdCustomerGroup.add(rdbtnO_TCustomer); rdCustomerGroup.add(rdbtnP_OU_TCustomer);
		rdCustomerGroup.add(rdbtnOU_TCustomer); rdCustomerGroup.add(rdbtnP_O_OU_TCustomer);
		rdCustomerGroup.add(rdbtnO_OU_TCustomer);
		
		//Group the radio buttons Target Customer.
		rdServiceCustomerGroup = new ButtonGroup();
		rdServiceCustomerGroup.add(rdbtnP_Customer); rdServiceCustomerGroup.add(rdbtnP_O_Customer);
		rdServiceCustomerGroup.add(rdbtnO_Customer); rdServiceCustomerGroup.add(rdbtnP_OU_Customer);
		rdServiceCustomerGroup.add(rdbtnOU_Customer); rdServiceCustomerGroup.add(rdbtnP_O_OU_Customer);
		rdServiceCustomerGroup.add(rdbtnO_OU_Customer);	
		
		imgPatternProvider = new JPanel();
		imgPatternProvider.setBackground(Color.GRAY);
		imgPatternProvider.setBounds(10, 168, 291, 146);	
		panelPCSubgroup.add(imgPatternProvider);
		
		imgPatternHiredProvider = new JPanel();
		imgPatternHiredProvider.setBackground(Color.GRAY);
		imgPatternHiredProvider.setBounds(10, 168, 291, 146);
		panelHiredP_ServiceC_Subgroup.add(imgPatternHiredProvider);
		
		imgPatternCustomer = new JPanel();
		imgPatternCustomer.setBackground(Color.GRAY);
		imgPatternCustomer.setBounds(325, 168, 291, 146);
		panelPCSubgroup.add(imgPatternCustomer);
		
		imgPatternServiceCustomer = new JPanel();
		imgPatternServiceCustomer.setBackground(Color.GRAY);
		imgPatternServiceCustomer.setBounds(325, 168, 291, 146);
		panelHiredP_ServiceC_Subgroup.add(imgPatternServiceCustomer);
		
		//Possiveis paineis de escolha
		
		 //PANEL P_PROVIDER
		panelP_Provider = new JPanel();
		panelP_Provider.setBorder(new TitledBorder(null, "P-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelP_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_Provider);
		panelP_Provider.setLayout(null);
		panelP_Provider.setName("panelP_Provider");
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
		
		 //PANEL P_HPROVIDER
		panelP_HProvider = new JPanel();
		panelP_HProvider.setBorder(new TitledBorder(null, "P-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelP_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_HProvider);
		panelP_HProvider.setLayout(null);
		panelP_HProvider.setName("panelP_HProvider");
		panelP_HProvider.setVisible(false);
		
		JLabel lblPerson_P_HProvider = new JLabel("Person");
		lblPerson_P_HProvider.setBounds(10, 24, 46, 14);
		panelP_HProvider.add(lblPerson_P_HProvider);
		
		txtServiceProvider_P_HProvider = new JTextField();
		txtServiceProvider_P_HProvider.setBounds(97, 59, 184, 20);
		panelP_HProvider.add(txtServiceProvider_P_HProvider);
		txtServiceProvider_P_HProvider.setColumns(10);
		
		JLabel lblServiceProvider_P_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_P_HProvider.setBounds(10, 62, 86, 14);
		panelP_HProvider.add(lblServiceProvider_P_HProvider);
		
		txtPerson_P_HProvider = new JTextField();
		txtPerson_P_HProvider.setColumns(10);
		txtPerson_P_HProvider.setBounds(97, 21, 184, 20);
		panelP_HProvider.add(txtPerson_P_HProvider);	
		
		//PANEL P_TCustomer
		panelP_TCustomer = new JPanel();
		panelP_TCustomer.setLayout(null);
		panelP_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_TCustomer);
		panelP_TCustomer.setVisible(false);
		panelP_TCustomer.setName("panelP_TCustomer");
		
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
		
		//PANEL P_Customer
		panelP_Customer = new JPanel();
		panelP_Customer.setLayout(null);
		panelP_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_Customer);
		panelP_Customer.setVisible(false);
		panelP_Customer.setName("panelP_Customer");
		
		JLabel lblPerson_P_Customer = new JLabel("Person");
		lblPerson_P_Customer.setBounds(10, 24, 76, 14);
		panelP_Customer.add(lblPerson_P_Customer);
		
		txtTargetCustomer_P_Customer = new JTextField();
		txtTargetCustomer_P_Customer.setColumns(10);
		txtTargetCustomer_P_Customer.setBounds(97, 59, 184, 20);
		panelP_Customer.add(txtTargetCustomer_P_Customer);
		
		JLabel lblTargetCustomer_P_Customer = new JLabel("Service Customer");
		lblTargetCustomer_P_Customer.setBounds(10, 62, 86, 14);
		panelP_Customer.add(lblTargetCustomer_P_Customer);
		
		txtPerson_P_Customer = new JTextField();
		txtPerson_P_Customer.setColumns(10);
		txtPerson_P_Customer.setBounds(97, 21, 184, 20);
		panelP_Customer.add(txtPerson_P_Customer);
		
		//panel P_O_OU_Provider
		panelP_O_OU_Provider = new JPanel();
		panelP_O_OU_Provider.setLayout(null);
		panelP_O_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_OU_Provider);
		panelP_O_OU_Provider.setVisible(false);
		panelP_O_OU_Provider.setName("panelP_O_OU_Provider");
		
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
		
		//panel P_O_OU_HProvider
		panelP_O_OU_HProvider = new JPanel();
		panelP_O_OU_HProvider.setLayout(null);
		panelP_O_OU_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_O_OU_HProvider);
		panelP_O_OU_HProvider.setVisible(false);
		panelP_O_OU_HProvider.setName("panelP_O_OU_HProvider");
		
		JLabel lblPersonProvider_P_O_OU_HProvider = new JLabel("Person Hired Service Provider");
		lblPersonProvider_P_O_OU_HProvider.setBounds(10, 24, 76, 14);
		panelP_O_OU_HProvider.add(lblPersonProvider_P_O_OU_HProvider);
		
		txtServiceProvider_P_O_OU_HProvider = new JTextField();
		txtServiceProvider_P_O_OU_HProvider.setColumns(10);
		txtServiceProvider_P_O_OU_HProvider.setBounds(137, 59, 144, 20);
		panelP_O_OU_HProvider.add(txtServiceProvider_P_O_OU_HProvider);
		
		JLabel lblServiceProvider_P_O_OU_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_P_O_OU_HProvider.setBounds(10, 62, 86, 14);
		panelP_O_OU_HProvider.add(lblServiceProvider_P_O_OU_HProvider);
		
		txtPersonProvider_P_O_OU_HProvider = new JTextField();
		txtPersonProvider_P_O_OU_HProvider.setColumns(10);
		txtPersonProvider_P_O_OU_HProvider.setBounds(137, 21, 144, 20);
		panelP_O_OU_HProvider.add(txtPersonProvider_P_O_OU_HProvider);
		
		JLabel lblOrgUnitProvider_P_O_OU_HProvider = new JLabel("Organization Unit Hired Service Provider");
		lblOrgUnitProvider_P_O_OU_HProvider.setBounds(10, 93, 126, 14);
		panelP_O_OU_HProvider.add(lblOrgUnitProvider_P_O_OU_HProvider);
		
		txtOrgUnitProvider_P_O_OU_HProvider = new JTextField();
		txtOrgUnitProvider_P_O_OU_HProvider.setColumns(10);
		txtOrgUnitProvider_P_O_OU_HProvider.setBounds(137, 90, 144, 20);
		panelP_O_OU_HProvider.add(txtOrgUnitProvider_P_O_OU_HProvider);
		
		JLabel lblOrgProvider_P_O_OU_HProvider = new JLabel("Organization Hired Service Provider");
		lblOrgProvider_P_O_OU_HProvider.setBounds(10, 129, 113, 14);
		panelP_O_OU_HProvider.add(lblOrgProvider_P_O_OU_HProvider);
		
		txtOrgProvider_P_O_OU_HProvider = new JTextField();
		txtOrgProvider_P_O_OU_HProvider.setColumns(10);
		txtOrgProvider_P_O_OU_HProvider.setBounds(137, 126, 144, 20);
		panelP_O_OU_HProvider.add(txtOrgProvider_P_O_OU_HProvider);
		
		//PANEL P_O_OU_TCustomer
		panelP_O_OU_TCustomer = new JPanel();
		panelP_O_OU_TCustomer.setLayout(null);
		panelP_O_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_OU_TCustomer);
		panelP_O_OU_TCustomer.setVisible(false);
		panelP_O_OU_TCustomer.setName("panelP_O_OU_TCustomer");
		
		JLabel lblPersonTC_P_0_OU_TCustomer = new JLabel("Person Target Customer");
		lblPersonTC_P_0_OU_TCustomer.setBounds(10, 24, 117, 14);
		panelP_O_OU_TCustomer.add(lblPersonTC_P_0_OU_TCustomer);
		
		txtPersonTC_P_O_OU_TCustomer = new JTextField();
		txtPersonTC_P_O_OU_TCustomer.setColumns(10);
		txtPersonTC_P_O_OU_TCustomer.setBounds(178, 59, 103, 20);
		panelP_O_OU_TCustomer.add(txtPersonTC_P_O_OU_TCustomer);
		
		JLabel lblTargetCustomer_P_0_OU_TCustomer = new JLabel("Target Customer");
		lblPersonTC_P_0_OU_TCustomer.setBounds(10, 62, 86, 14);
		panelP_O_OU_TCustomer.add(lblPersonTC_P_0_OU_TCustomer);
		
		txtTargetCustomer_P_O_OU_TCustomer = new JTextField();
		txtTargetCustomer_P_O_OU_TCustomer.setColumns(10);
		txtTargetCustomer_P_O_OU_TCustomer.setBounds(178, 21, 103, 20);
		panelP_O_OU_TCustomer.add(txtTargetCustomer_P_O_OU_TCustomer);
		
		JLabel lblOrgUnitTC_P_0_OU_TCustomer = new JLabel("Organization Unit Target Customer");
		lblOrgUnitTC_P_0_OU_TCustomer.setBounds(10, 93, 167, 14);
		panelP_O_OU_TCustomer.add(lblOrgUnitTC_P_0_OU_TCustomer);
		
		txtOrgUnitTC_P_O_OU_TCustomer = new JTextField();
		txtOrgUnitTC_P_O_OU_TCustomer.setColumns(10);
		txtOrgUnitTC_P_O_OU_TCustomer.setBounds(178, 90, 103, 20);
		panelP_O_OU_TCustomer.add(txtOrgUnitTC_P_O_OU_TCustomer);
		
		JLabel lblOrgTC_P_0_OU_TCustomer = new JLabel("Organization Target Customer");
		lblOrgTC_P_0_OU_TCustomer.setBounds(10, 129, 145, 14);
		panelP_O_OU_TCustomer.add(lblOrgTC_P_0_OU_TCustomer);
		
		txtOrgTC_P_O_OU_TCustomer = new JTextField();
		txtOrgTC_P_O_OU_TCustomer.setColumns(10);
		txtOrgTC_P_O_OU_TCustomer.setBounds(178, 126, 103, 20);
		panelP_O_OU_TCustomer.add(txtOrgTC_P_O_OU_TCustomer);
		
		//PANEL P_O_OU_Customer
		panelP_O_OU_Customer = new JPanel();
		panelP_O_OU_Customer.setLayout(null);
		panelP_O_OU_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-OU-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_OU_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_O_OU_Customer);
		panelP_O_OU_Customer.setVisible(false);
		panelP_O_OU_Customer.setName("panelP_O_OU_Customer");
		
		JLabel lblPersonTC_P_0_OU_Customer = new JLabel("Person Service Customer");
		lblPersonTC_P_0_OU_Customer.setBounds(10, 24, 117, 14);
		panelP_O_OU_Customer.add(lblPersonTC_P_0_OU_Customer);
		
		txtPersonTC_P_O_OU_Customer = new JTextField();
		txtPersonTC_P_O_OU_Customer.setColumns(10);
		txtPersonTC_P_O_OU_Customer.setBounds(178, 59, 103, 20);
		panelP_O_OU_Customer.add(txtPersonTC_P_O_OU_Customer);
		
		JLabel lblTargetCustomer_P_0_OU_Customer = new JLabel("Service Customer");
		lblPersonTC_P_0_OU_Customer.setBounds(10, 62, 86, 14);
		panelP_O_OU_Customer.add(lblPersonTC_P_0_OU_Customer);
		
		txtTargetCustomer_P_O_OU_Customer = new JTextField();
		txtTargetCustomer_P_O_OU_Customer.setColumns(10);
		txtTargetCustomer_P_O_OU_Customer.setBounds(178, 21, 103, 20);
		panelP_O_OU_Customer.add(txtTargetCustomer_P_O_OU_Customer);
		
		JLabel lblOrgUnitTC_P_0_OU_Customer = new JLabel("Organization Unit Service Customer");
		lblOrgUnitTC_P_0_OU_Customer.setBounds(10, 93, 167, 14);
		panelP_O_OU_Customer.add(lblOrgUnitTC_P_0_OU_Customer);
		
		txtOrgUnitTC_P_O_OU_Customer = new JTextField();
		txtOrgUnitTC_P_O_OU_Customer.setColumns(10);
		txtOrgUnitTC_P_O_OU_Customer.setBounds(178, 90, 103, 20);
		panelP_O_OU_Customer.add(txtOrgUnitTC_P_O_OU_Customer);
		
		JLabel lblOrgTC_P_0_OU_Customer = new JLabel("Organization Service Customer");
		lblOrgTC_P_0_OU_Customer.setBounds(10, 129, 145, 14);
		panelP_O_OU_Customer.add(lblOrgTC_P_0_OU_Customer);
		
		txtOrgTC_P_O_OU_Customer = new JTextField();
		txtOrgTC_P_O_OU_Customer.setColumns(10);
		txtOrgTC_P_O_OU_Customer.setBounds(178, 126, 103, 20);
		panelP_O_OU_Customer.add(txtOrgTC_P_O_OU_Customer);
		
		//PANEL P_OU_TCustomer
		panelP_OU_TCustomer = new JPanel();
		panelP_OU_TCustomer.setLayout(null);
		panelP_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_OU_TCustomer);
		panelP_OU_TCustomer.setVisible(false);
		panelP_OU_TCustomer.setName("panelP_OU_TCustomer");
		
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
		
		JLabel lblOrgProvider_P_OU_TCustomer = new JLabel("Organization Unit");
		lblOrgProvider_P_OU_TCustomer.setBounds(10, 129, 113, 14);
		panelP_OU_TCustomer.add(lblOrgProvider_P_OU_TCustomer);
		
		txtOrgProvider_P_OU_TCustomer = new JTextField();
		txtOrgProvider_P_OU_TCustomer.setColumns(10);
		txtOrgProvider_P_OU_TCustomer.setBounds(178, 126, 103, 20);
		panelP_OU_TCustomer.add(txtOrgProvider_P_OU_TCustomer);
		
		//PANEL P_OU_Customer
		panelP_OU_Customer = new JPanel();
		panelP_OU_Customer.setLayout(null);
		panelP_OU_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_OU_Customer);
		panelP_OU_Customer.setVisible(false);
		panelP_OU_Customer.setName("panelP_OU_Customer");
		
		JLabel lblPersonTC_P_OU_Customer = new JLabel("Person Service Customer");
		lblPersonTC_P_OU_Customer.setBounds(10, 24, 117, 14);
		panelP_OU_Customer.add(lblPersonTC_P_OU_Customer);
		
		txtTargetCustomerTC_P_OU_Customer = new JTextField();
		txtTargetCustomerTC_P_OU_Customer.setColumns(10);
		txtTargetCustomerTC_P_OU_Customer.setBounds(178, 59, 103, 20);
		panelP_OU_Customer.add(txtTargetCustomerTC_P_OU_Customer);
		
		JLabel lblTargetCustomer_P_OU_Customer = new JLabel("Service Customer");
		lblTargetCustomer_P_OU_Customer.setBounds(10, 62, 86, 14);
		panelP_OU_Customer.add(lblTargetCustomer_P_OU_Customer);
		
		txtPersonTC_P_OU_Customer = new JTextField();
		txtPersonTC_P_OU_Customer.setColumns(10);
		txtPersonTC_P_OU_Customer.setBounds(178, 21, 103, 20);
		panelP_OU_Customer.add(txtPersonTC_P_OU_Customer);
		
		JLabel lblOrgUnitTC_P_OU_Customer = new JLabel("Organization Unit Service Customer");
		lblOrgUnitTC_P_OU_Customer.setBounds(10, 93, 172, 14);
		panelP_OU_Customer.add(lblOrgUnitTC_P_OU_Customer);
		
		txtOrgUnitTC_P_OU_Customer = new JTextField();
		txtOrgUnitTC_P_OU_Customer.setColumns(10);
		txtOrgUnitTC_P_OU_Customer.setBounds(178, 90, 103, 20);
		panelP_OU_Customer.add(txtOrgUnitTC_P_OU_Customer);
		
		JLabel lblOrgProvider_P_OU_Customer = new JLabel("Organization Unit");
		lblOrgProvider_P_OU_Customer.setBounds(10, 129, 113, 14);
		panelP_OU_Customer.add(lblOrgProvider_P_OU_Customer);
		
		txtOrgProvider_P_OU_Customer = new JTextField();
		txtOrgProvider_P_OU_Customer.setColumns(10);
		txtOrgProvider_P_OU_Customer.setBounds(178, 126, 103, 20);
		panelP_OU_Customer.add(txtOrgProvider_P_OU_Customer);
		
		//PANEL P_O_TCustomer
		panelP_O_TCustomer = new JPanel();
		panelP_O_TCustomer.setLayout(null);
		panelP_O_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_TCustomer);
		panelP_O_TCustomer.setVisible(false);
		panelP_O_TCustomer.setName("panelP_O_TCustomer");
		
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
		
		//PANEL P_O_Customer
		panelP_O_Customer = new JPanel();
		panelP_O_Customer.setLayout(null);
		panelP_O_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_O_Customer);
		panelP_O_Customer.setVisible(false);
		panelP_O_Customer.setName("panelP_O_Customer");
		
		JLabel lblPersonTC_P_O_Customer = new JLabel("Person Service Customer");
		lblPersonTC_P_O_Customer.setBounds(10, 24, 117, 14);
		panelP_O_Customer.add(lblPersonTC_P_O_Customer);
		
		txtTargetCustomer_P_O_Customer = new JTextField();
		txtTargetCustomer_P_O_Customer.setColumns(10);
		txtTargetCustomer_P_O_Customer.setBounds(157, 59, 124, 20);
		panelP_O_Customer.add(txtTargetCustomer_P_O_Customer);
		
		JLabel lblTargetCustomer_P_O_Customer = new JLabel("Service Customer");
		lblTargetCustomer_P_O_Customer.setBounds(10, 62, 86, 14);
		panelP_O_Customer.add(lblTargetCustomer_P_O_Customer);
		
		txtPersonTC_P_O_Customer = new JTextField();
		txtPersonTC_P_O_Customer.setColumns(10);
		txtPersonTC_P_O_Customer.setBounds(157, 21, 124, 20);
		panelP_O_Customer.add(txtPersonTC_P_O_Customer);
		
		JLabel lblOrgTC_P_O_Customer = new JLabel("Organization Service Customer");
		lblOrgTC_P_O_Customer.setBounds(10, 93, 154, 14);
		panelP_O_Customer.add(lblOrgTC_P_O_Customer);
		
		txtOrgTC_P_O_Customer = new JTextField();
		txtOrgTC_P_O_Customer.setColumns(10);
		txtOrgTC_P_O_Customer.setBounds(157, 90, 124, 20);
		panelP_O_Customer.add(txtOrgTC_P_O_Customer);
		
		//PANEL P_OU_TCustomer
		panelO_OU_TCustomer = new JPanel();
		panelO_OU_TCustomer.setLayout(null);
		panelO_OU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelO_OU_TCustomer);
		panelO_OU_TCustomer.setVisible(false);
		panelO_OU_TCustomer.setName("panelO_OU_TCustomer");
		
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
		
		//PANEL P_OU_Customer
		panelO_OU_Customer = new JPanel();
		panelO_OU_Customer.setLayout(null);
		panelO_OU_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelO_OU_Customer);
		panelO_OU_Customer.setVisible(false);
		panelO_OU_Customer.setName("panelO_OU_Customer");
		
		JLabel lblOrgUnitTC_O_OU_Customer = new JLabel("Organizational Unit Service Customer");
		lblOrgUnitTC_O_OU_Customer.setBounds(61, 21, 197, 14);
		panelO_OU_Customer.add(lblOrgUnitTC_O_OU_Customer);
		
		txtTargetCustomer_O_OU_Customer = new JTextField();
		txtTargetCustomer_O_OU_Customer.setColumns(10);
		txtTargetCustomer_O_OU_Customer.setBounds(61, 83, 170, 20);
		panelO_OU_Customer.add(txtTargetCustomer_O_OU_Customer);
		
		JLabel lblTargetCustomer_O_OU_Customer = new JLabel("Service Customer");
		lblTargetCustomer_O_OU_Customer.setBounds(104, 69, 86, 14);
		panelO_OU_Customer.add(lblTargetCustomer_O_OU_Customer);
		
		txtOrgUnitTC_O_OU_Customer = new JTextField();
		txtOrgUnitTC_O_OU_Customer.setColumns(10);
		txtOrgUnitTC_O_OU_Customer.setBounds(61, 38, 170, 20);
		panelO_OU_Customer.add(txtOrgUnitTC_O_OU_Customer);
		
		JLabel lblOrgTC_O_OU_Customer = new JLabel("Organization Service Customer");
		lblOrgTC_O_OU_Customer.setBounds(75, 123, 156, 14);
		panelO_OU_Customer.add(lblOrgTC_O_OU_Customer);
		
		txtOrgTC_O_OU_Customer = new JTextField();
		txtOrgTC_O_OU_Customer.setColumns(10);
		txtOrgTC_O_OU_Customer.setBounds(61, 142, 170, 20);
		panelO_OU_Customer.add(txtOrgTC_O_OU_Customer);
		
		//PANEL OU_TCustomer
		panelOU_TCustomer = new JPanel();
		panelOU_TCustomer.setLayout(null);
		panelOU_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelOU_TCustomer);
		panelOU_TCustomer.setVisible(false);
		panelOU_TCustomer.setName("panelOU_TCustomer");
		
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
		
		//PANEL OU_Customer
		panelOU_Customer = new JPanel();
		panelOU_Customer.setLayout(null);
		panelOU_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelOU_Customer);
		panelOU_Customer.setVisible(false);
		panelOU_Customer.setName("panelOU_Customer");
		
		JLabel lblOrgUnit_OU_Customer = new JLabel("Organizational Unit");
		lblOrgUnit_OU_Customer.setBounds(10, 24, 117, 14);
		panelOU_Customer.add(lblOrgUnit_OU_Customer);
		
		txtTargetCustomer_OU_Customer = new JTextField();
		txtTargetCustomer_OU_Customer.setColumns(10);
		txtTargetCustomer_OU_Customer.setBounds(119, 59, 162, 20);
		panelOU_Customer.add(txtTargetCustomer_OU_Customer);
		
		JLabel lblTargetCustomer_OU_Customer = new JLabel("Service Customer");
		lblTargetCustomer_OU_Customer.setBounds(10, 62, 86, 14);
		panelOU_Customer.add(lblTargetCustomer_OU_Customer);
		
		txtOrgUnit_OU_Customer = new JTextField();
		txtOrgUnit_OU_Customer.setColumns(10);
		txtOrgUnit_OU_Customer.setBounds(119, 21, 162, 20);
		panelOU_Customer.add(txtOrgUnit_OU_Customer);
		
		//PANEL O_TCustomer
		panelO_TCustomer = new JPanel();
		panelO_TCustomer.setLayout(null);
		panelO_TCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-TCustomer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_TCustomer.setBounds(325, 325, 291, 184);
		panelPCSubgroup.add(panelO_TCustomer);
		panelO_TCustomer.setVisible(false);
		panelO_TCustomer.setName("panelO_TCustomer");
		
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
		
		//PANEL O_Customer
		panelO_Customer = new JPanel();
		panelO_Customer.setLayout(null);
		panelO_Customer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_Customer.setBounds(325, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelO_Customer);
		panelO_Customer.setVisible(false);
		panelO_Customer.setName("panelO_Customer");
		
		JLabel lblOrganization_O_Customer = new JLabel("Organization");
		lblOrganization_O_Customer.setBounds(10, 24, 76, 14);
		panelO_Customer.add(lblOrganization_O_Customer);
		
		txtTargetCustomer_O_Customer = new JTextField();
		txtTargetCustomer_O_Customer.setColumns(10);
		txtTargetCustomer_O_Customer.setBounds(137, 59, 144, 20);
		panelO_Customer.add(txtTargetCustomer_O_Customer);
		
		JLabel lblTargetCustomer_O_Customer = new JLabel("Service Customer");
		lblTargetCustomer_O_Customer.setBounds(10, 62, 86, 14);
		panelO_Customer.add(lblTargetCustomer_O_Customer);
		
		txtOrganization_O_Customer = new JTextField();
		txtOrganization_O_Customer.setColumns(10);
		txtOrganization_O_Customer.setBounds(137, 21, 144, 20);
		panelO_Customer.add(txtOrganization_O_Customer);
		
		//PANEL P_OU_Provider
		panelP_OU_Provider = new JPanel();
		panelP_OU_Provider.setLayout(null);
		panelP_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_OU_Provider);
		panelP_OU_Provider.setVisible(false);
		panelP_OU_Provider.setName("panelP_OU_Provider");
		
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
		
		//PANEL P_OU_HProvider
		panelP_OU_HProvider = new JPanel();
		panelP_OU_HProvider.setLayout(null);
		panelP_OU_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-OU-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_OU_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_OU_HProvider);
		panelP_OU_HProvider.setVisible(false);
		panelP_OU_HProvider.setName("panelP_OU_HProvider");
		
		JLabel lblPersonProvider_P_OU_HProvider = new JLabel("Person Hired Service Provider");
		lblPersonProvider_P_OU_HProvider.setBounds(10, 24, 76, 14);
		panelP_OU_HProvider.add(lblPersonProvider_P_OU_HProvider);
		
		txtServiceProvider_P_OU_HProvider = new JTextField();
		txtServiceProvider_P_OU_HProvider.setColumns(10);
		txtServiceProvider_P_OU_HProvider.setBounds(143, 59, 138, 20);
		panelP_OU_HProvider.add(txtServiceProvider_P_OU_HProvider);
		
		JLabel lblServiceProvider_P_OU_hProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_P_OU_Provider.setBounds(10, 62, 86, 14);
		panelP_OU_Provider.add(lblServiceProvider_P_OU_Provider);
		
		txtPersonProvider_P_OU_HProvider = new JTextField();
		txtPersonProvider_P_OU_HProvider.setColumns(10);
		txtPersonProvider_P_OU_HProvider.setBounds(143, 21, 138, 20);
		panelP_OU_HProvider.add(txtPersonProvider_P_OU_HProvider);
		
		JLabel lblOrgUnitProvider_P_OU_HProvider = new JLabel("Organization Unit Hired Service Provider");
		lblOrgUnitProvider_P_OU_HProvider.setBounds(10, 93, 126, 14);
		panelP_OU_HProvider.add(lblOrgUnitProvider_P_OU_HProvider);
		
		txtOrgUnitProvider_P_OU_HProvider = new JTextField();
		txtOrgUnitProvider_P_OU_HProvider.setColumns(10);
		txtOrgUnitProvider_P_OU_HProvider.setBounds(143, 90, 138, 20);
		panelP_OU_HProvider.add(txtOrgUnitProvider_P_OU_HProvider);
		
		//PANEL P_O_Provider
		panelP_O_Provider = new JPanel();
		panelP_O_Provider.setLayout(null);
		panelP_O_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelP_O_Provider);
		panelP_O_Provider.setVisible(false);
		panelP_O_Provider.setName("panelP_O_Provider");
		
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
		
		//PANEL P_O_HProvider
		panelP_O_HProvider = new JPanel();
		panelP_O_HProvider.setLayout(null);
		panelP_O_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "P-O-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelP_O_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelP_O_HProvider);
		panelP_O_HProvider.setVisible(false);
		panelP_O_HProvider.setName("panelP_O_HProvider");
		
		JLabel lblPersonProvider_P_O_HProvider = new JLabel("Person Hired Service Provider");
		lblPersonProvider_P_O_HProvider.setBounds(10, 24, 76, 14);
		panelP_O_HProvider.add(lblPersonProvider_P_O_HProvider);
		
		txtServiceProvider_P_O_HProvider = new JTextField();
		txtServiceProvider_P_O_HProvider.setColumns(10);
		txtServiceProvider_P_O_HProvider.setBounds(117, 59, 164, 20);
		panelP_O_HProvider.add(txtServiceProvider_P_O_HProvider);
		
		JLabel lblServiceProvider_P_O_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_P_O_HProvider.setBounds(10, 62, 86, 14);
		panelP_O_HProvider.add(lblServiceProvider_P_O_HProvider);
		
		txtPersonProvider_P_O_HProvider = new JTextField();
		txtPersonProvider_P_O_HProvider.setColumns(10);
		txtPersonProvider_P_O_HProvider.setBounds(117, 21, 164, 20);
		panelP_O_HProvider.add(txtPersonProvider_P_O_HProvider);
		
		txtOrganizationProvider_P_O_HProvider = new JTextField();
		txtOrganizationProvider_P_O_HProvider.setColumns(10);
		txtOrganizationProvider_P_O_HProvider.setBounds(117, 90, 164, 20);
		panelP_O_HProvider.add(txtOrganizationProvider_P_O_HProvider);
		
		JLabel lblOrganizationProvider_P_O_HProvider = new JLabel("Organization Hired Service Provider");
		lblOrganizationProvider_P_O_HProvider.setBounds(10, 93, 109, 14);
		panelP_O_HProvider.add(lblOrganizationProvider_P_O_HProvider);
		
		//PANEL O_OU_Provider
		panelO_OU_Provider = new JPanel();
		panelO_OU_Provider.setLayout(null);
		panelO_OU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelO_OU_Provider);
		panelO_OU_Provider.setVisible(false);
		panelO_OU_Provider.setName("panelO_OU_Provider");
		
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
		
		//PANEL O_OU_HProvider
		panelO_OU_HProvider = new JPanel();
		panelO_OU_HProvider.setLayout(null);
		panelO_OU_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-OU-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_OU_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelO_OU_HProvider);
		panelO_OU_HProvider.setVisible(false);
		panelO_OU_HProvider.setName("panelO_OU_HProvider");
		
		JLabel lblOrgUnitProvider_O_OU_HProvider = new JLabel("Organization Unit Hired Service Provider");
		lblOrgUnitProvider_O_OU_HProvider.setBounds(10, 24, 126, 14);
		panelO_OU_HProvider.add(lblOrgUnitProvider_O_OU_HProvider);
		
		txtServiceProvider_O_OU_HProvider = new JTextField();
		txtServiceProvider_O_OU_HProvider.setColumns(10);
		txtServiceProvider_O_OU_HProvider.setBounds(146, 59, 135, 20);
		panelO_OU_HProvider.add(txtServiceProvider_O_OU_HProvider);
		
		JLabel lblServiceProvider_O_OU_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_O_OU_HProvider.setBounds(10, 62, 86, 14);
		panelO_OU_HProvider.add(lblServiceProvider_O_OU_HProvider);
		
		txtOrgUnitProvider_O_OU_HProvider = new JTextField();
		txtOrgUnitProvider_O_OU_HProvider.setColumns(10);
		txtOrgUnitProvider_O_OU_HProvider.setBounds(146, 21, 135, 20);
		panelO_OU_HProvider.add(txtOrgUnitProvider_O_OU_HProvider);
		
		JLabel lblOrganizationProvider_O_OU_HProvider = new JLabel("Organization Hired Service Provider");
		lblOrganizationProvider_O_OU_HProvider.setBounds(10, 93, 111, 14);
		panelO_OU_HProvider.add(lblOrganizationProvider_O_OU_HProvider);
		
		txtOrganizationProvider_O_OU_HProvider = new JTextField();
		txtOrganizationProvider_O_OU_HProvider.setColumns(10);
		txtOrganizationProvider_O_OU_HProvider.setBounds(146, 90, 135, 20);
		panelO_OU_HProvider.add(txtOrganizationProvider_O_OU_HProvider);
		
		//PANEL OU_Provider
		panelOU_Provider = new JPanel();
		panelOU_Provider.setLayout(null);
		panelOU_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelOU_Provider);
		panelOU_Provider.setVisible(false);
		panelOU_Provider.setName("panelOU_Provider");
		
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
		
		//PANEL OU_HProvider
		panelOU_HProvider = new JPanel();
		panelOU_HProvider.setLayout(null);
		panelOU_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OU-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOU_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelOU_HProvider);
		panelOU_HProvider.setVisible(false);
		panelOU_HProvider.setName("panelOU_HProvider");
		
		JLabel lblOrgUnit_OU_HProvider = new JLabel("Organization Unit");
		lblOrgUnit_OU_HProvider.setBounds(10, 24, 107, 14);
		panelOU_HProvider.add(lblOrgUnit_OU_HProvider);
		
		txtServiceProvider_OU_HProvider = new JTextField();
		txtServiceProvider_OU_HProvider.setColumns(10);
		txtServiceProvider_OU_HProvider.setBounds(102, 59, 179, 20);
		panelOU_HProvider.add(txtServiceProvider_OU_HProvider);
		
		JLabel lblServiceProvider_OU_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_OU_HProvider.setBounds(10, 62, 86, 14);
		panelOU_HProvider.add(lblServiceProvider_OU_HProvider);
		
		txtOrgUnit_OU_HProvider = new JTextField();
		txtOrgUnit_OU_HProvider.setColumns(10);
		txtOrgUnit_OU_HProvider.setBounds(102, 21, 179, 20);
		panelOU_HProvider.add(txtOrgUnit_OU_HProvider);
		
		//PANEL O_Provider
		panelO_Provider = new JPanel();
		panelO_Provider.setLayout(null);
		panelO_Provider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-Provider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_Provider.setBounds(10, 325, 291, 184);
		panelPCSubgroup.add(panelO_Provider);
		panelO_Provider.setVisible(false);
		panelO_Provider.setName("panelO_Provider");
		
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
		
		//PANEL O_HProvider
		panelO_HProvider = new JPanel();
		panelO_HProvider.setLayout(null);
		panelO_HProvider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "O-HProvider", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelO_HProvider.setBounds(10, 325, 291, 184);
		panelHiredP_ServiceC_Subgroup.add(panelO_HProvider);
		panelO_HProvider.setVisible(false);
		panelO_HProvider.setName("panelO_HProvider");
		
		JLabel lblOrganization_O_HProvider = new JLabel("Organization");
		lblOrganization_O_HProvider.setBounds(10, 24, 76, 14);
		panelO_HProvider.add(lblOrganization_O_HProvider);
		
		txtServiceProvider_O_HProvider = new JTextField();
		txtServiceProvider_O_HProvider.setColumns(10);
		txtServiceProvider_O_HProvider.setBounds(97, 59, 184, 20);
		panelO_HProvider.add(txtServiceProvider_O_HProvider);
		
		JLabel lblServiceProvider_O_HProvider = new JLabel("Hired Service Provider");
		lblServiceProvider_O_HProvider.setBounds(10, 62, 86, 14);
		panelO_HProvider.add(lblServiceProvider_O_HProvider);
		
		txtOrganization_O_HProvider = new JTextField();
		txtOrganization_O_HProvider.setColumns(10);
		txtOrganization_O_HProvider.setBounds(97, 21, 184, 20);
		panelO_HProvider.add(txtOrganization_O_HProvider);
		
		//Register a listener for the radio buttons	Provider	
		
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
		
		//Register a listener for the radio buttons	Hired Provider	
		
		rdbtnP_HProvider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				desabilitaPaineisHiredProvider();
				trocaPainel(panelP_HProvider);	
			}	
		});	
		
		rdbtnO_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisHiredProvider();
				trocaPainel(panelO_HProvider);			
			}
		});
		rdbtnOU_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				desabilitaPaineisHiredProvider();
				trocaPainel(panelOU_HProvider);				
			}
		});
		rdbtnO_OU_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisHiredProvider();
				trocaPainel(panelO_OU_HProvider);				
			}
		});
		rdbtnP_O_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisHiredProvider();
				trocaPainel(panelP_O_HProvider);				
			}
		});
		rdbtnP_OU_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desabilitaPaineisHiredProvider();
				trocaPainel(panelP_OU_HProvider);				
			}
		});
		rdbtnP_O_OU_HProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				desabilitaPaineisHiredProvider();
				trocaPainel(panelP_O_OU_HProvider);				
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
		
		//Register a listener for the radio buttons Service Customer
		rdbtnP_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelP_Customer);			
			}
		});
		rdbtnO_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelO_Customer);			
			}
		});
		rdbtnOU_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();		
				trocaPainel(panelOU_Customer);				
			}
		});
		rdbtnO_OU_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelO_OU_Customer);				
			}
		});
		rdbtnP_O_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelP_O_Customer);				
			}
		});
		rdbtnP_OU_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelP_OU_Customer);				
			}
		});
		rdbtnP_O_OU_Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				desabilitaPaineisServiceCustomer();
				trocaPainel(panelP_O_OU_Customer);				
			}
		});					
		
		//TUDO REFERENTE AO PAINEL SOFFFERING
		
		panelSOffering = new JPanel();
		panelSOffering.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelSOffering);
		panelSOffering.setLayout(null);
		panelSOffering.setVisible(false);
		
		JPanel panelSOfferingInterno = new JPanel();
		panelSOfferingInterno.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SOffering", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSOfferingInterno.setBounds(10, 11, 601, 489);
		panelSOffering.add(panelSOfferingInterno);
		panelSOfferingInterno.setLayout(null);
		
		JLabel label = new JLabel("Class");
		label.setBounds(143, 327, 68, 14);
		panelSOfferingInterno.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(328, 327, 68, 14);
		panelSOfferingInterno.add(label_1);
		
		txtServiceOffering = new JTextField();
		txtServiceOffering.setBounds(328, 352, 166, 20);
		panelSOfferingInterno.add(txtServiceOffering);
		txtServiceOffering.setColumns(10);
		
		txtTargetCC = new JTextField();
		txtTargetCC.setColumns(10);
		txtTargetCC.setBounds(328, 383, 166, 20);
		panelSOfferingInterno.add(txtTargetCC);
				
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.LIGHT_GRAY);
		panelImg.setBounds(10, 27, 581, 275);
		iconSOffering = new  ImageIcon(getClass().getResource("/resource/SOffering.PNG"));
		imagemSOffering = new JLabel(iconSOffering);
		imagemSOffering.setBounds(10, 27, 581, 275);
		imagemSOffering.setSize(70, 96); // 70 96
		imagemSOffering.setVisible(true);
		panelImg.add(imagemSOffering);
		panelSOfferingInterno.add(panelImg);		

		
		JLabel lblServiceOffering = new JLabel("Service Offering");
		lblServiceOffering.setBounds(143, 352, 78, 14);
		panelSOfferingInterno.add(lblServiceOffering);
		
		JLabel lblTargetCC = new JLabel("Target Customer Community");
		lblTargetCC.setBounds(143, 390, 151, 14);
		panelSOfferingInterno.add(lblTargetCC);
		
		//SODescription		
		panelSODescription = new JPanel();
		panelSODescription.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelSODescription);
		panelSODescription.setLayout(null);
		panelSODescription.setVisible(false);
		
		JPanel panelSODescriptionInterno = new JPanel();
		panelSODescriptionInterno.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SODescription", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSODescriptionInterno.setBounds(10, 11, 601, 489);
		panelSODescription.add(panelSODescriptionInterno);
		panelSODescriptionInterno.setLayout(null);
		
		JLabel lblClass_SODescription = new JLabel("Class");
		lblClass_SODescription.setBounds(143, 327, 68, 14);
		panelSODescriptionInterno.add(lblClass_SODescription);
		
		JLabel lblName_SODescription = new JLabel("Name");
		lblName_SODescription.setBounds(328, 327, 68, 14);
		panelSODescriptionInterno.add(lblName_SODescription);
		
		JLabel lblServiceOfferingDescription = new JLabel("Service Offering Description");
		lblServiceOfferingDescription.setBounds(143, 352, 139, 14);
		panelSODescriptionInterno.add(lblServiceOfferingDescription);
		
		txtServiceOfferingDescription = new JTextField();
		txtServiceOfferingDescription.setBounds(328, 352, 166, 20);
		panelSODescriptionInterno.add(txtServiceOfferingDescription);
		txtServiceOfferingDescription.setColumns(10);
			
		JPanel panelImg_SODescription = new JPanel();
		panelImg_SODescription.setBackground(Color.LIGHT_GRAY);
		panelImg_SODescription.setBounds(10, 27, 581, 275);
		iconSODescription = new  ImageIcon(getClass().getResource("/resource/SODescription.PNG"));
		imagemSODescription = new JLabel(iconSODescription);
		imagemSODescription.setBounds(10, 27, 581, 275);
		imagemSODescription.setSize(70, 96); // 70 96
		imagemSODescription.setVisible(true);
		panelImg_SODescription.add(imagemSODescription);
		panelSODescriptionInterno.add(panelImg_SODescription);	
		
		//SOCommitment
		
		panelSOCommitment = new JPanel();
		panelSOCommitment.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelSOCommitment);
		panelSOCommitment.setLayout(null);	
		panelSOCommitment.setVisible(false);
		
		JPanel panelSOCommitmentInterno = new JPanel();
		panelSOCommitmentInterno.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SOCommitment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSOCommitmentInterno.setBounds(10, 11, 601, 489);
		panelSOCommitment.add(panelSOCommitmentInterno);
		panelSOCommitmentInterno.setLayout(null);
		
		JLabel lblClass_SOCommitment = new JLabel("Class");
		lblClass_SOCommitment.setBounds(143, 327, 68, 14);
		panelSOCommitmentInterno.add(lblClass_SOCommitment);
		
		JLabel lblName_SOCommitment = new JLabel("Name");
		lblName_SOCommitment.setBounds(328, 327, 68, 14);
		panelSOCommitmentInterno.add(lblName_SOCommitment);
		
		JLabel lblServiceOfferingCommitment = new JLabel("Service Offering Commitment");
		lblServiceOfferingCommitment.setBounds(143, 352, 151, 14);
		panelSOCommitmentInterno.add(lblServiceOfferingCommitment);
		
		txtServiceOfferingCommitment = new JTextField();
		txtServiceOfferingCommitment.setBounds(328, 352, 166, 20);
		panelSOCommitmentInterno.add(txtServiceOfferingCommitment);
		txtServiceOfferingCommitment.setColumns(10);
			
		JPanel panelImg_SOCommitment = new JPanel();
		panelImg_SOCommitment.setBackground(Color.LIGHT_GRAY);
		panelImg_SOCommitment.setBounds(10, 27, 581, 275);
		iconSOCommitment = new  ImageIcon(getClass().getResource("/resource/SOCommitment.PNG"));
		imagemSOCommitment = new JLabel(iconSOCommitment);
		imagemSOCommitment.setBounds(10, 27, 581, 275);
		imagemSOCommitment.setSize(70, 96); // 70 96
		imagemSOCommitment.setVisible(true);
		panelImg_SOCommitment.add(imagemSOCommitment);
		panelSOCommitmentInterno.add(panelImg_SOCommitment);	
		
		//SNegAgree
		
		panelSNegAgree = new JPanel();
		panelSNegAgree.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelSNegAgree);
		panelSNegAgree.setLayout(null);	
		panelSNegAgree.setVisible(false);
		
		JPanel panelSNegAgreeInterno = new JPanel();
		panelSNegAgreeInterno.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SNegAgree", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSNegAgreeInterno.setBounds(10, 11, 601, 489);
		panelSNegAgree.add(panelSNegAgreeInterno);
		panelSNegAgreeInterno.setLayout(null);
		
		JLabel lblClass_SNegAgree = new JLabel("Class");
		lblClass_SNegAgree.setBounds(143, 327, 68, 14);
		panelSNegAgreeInterno.add(lblClass_SNegAgree);
		
		JLabel lblName_SNegAgree = new JLabel("Name");
		lblName_SNegAgree.setBounds(328, 327, 68, 14);
		panelSNegAgreeInterno.add(lblName_SNegAgree);
		
		JLabel lblServiceOfferingClaim = new JLabel("Service Offering Claim");
		lblServiceOfferingClaim.setBounds(143, 352, 151, 14);
		panelSNegAgreeInterno.add(lblServiceOfferingClaim);
		
		txtServiceOfferingClaim = new JTextField();
		txtServiceOfferingClaim.setBounds(328, 352, 166, 20);
		panelSNegAgreeInterno.add(txtServiceOfferingClaim);
		txtServiceOfferingClaim.setColumns(10);
			
		JPanel panelImg_SNegAgreem = new JPanel();
		panelImg_SNegAgreem.setBackground(Color.LIGHT_GRAY);
		panelImg_SNegAgreem.setBounds(10, 27, 581, 275);
		iconSNegAgree = new  ImageIcon(getClass().getResource("/resource/SNegAgree.PNG"));
		imagemSNegAgree = new JLabel(iconSNegAgree);
		imagemSNegAgree.setBounds(10, 27, 581, 275);
		imagemSNegAgree.setSize(70, 96); // 70 96
		imagemSNegAgree.setVisible(true);
		panelImg_SNegAgreem.add(imagemSNegAgree);
		panelSNegAgreeInterno.add(panelImg_SNegAgreem);	
		
		JButton btnCreateConcepts = new JButton("Create S-OPL Diagram");
		btnCreateConcepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				//Verifica os radios selecionados
				int rdProviderSubgroupSelected = JanBase.getRadioProviderSubgroupSelected();
				int rdCustomerSubgroupSelected = JanBase.getRadioCustomerSubgroupSelected();
										
				soplPattern.getSpecificFix(rdProviderSubgroupSelected, rdCustomerSubgroupSelected);	
				frame.dispose();
			}
		});
		btnCreateConcepts.setBounds(167, 522, 150, 37);
		frame.getContentPane().add(btnCreateConcepts);

		
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	//Esse metodo verifica qual Variant Pattern foi selecionado e troca a imagem correspondente no JPanel
	public static void trocaPainel(JPanel panel ){		
		panel.setVisible(true);
		imgPatternCustomer.removeAll();
		imgPatternProvider.removeAll();
		imgPatternServiceCustomer.removeAll();
		imgPatternHiredProvider.removeAll();
				
		if(panel.getName().equals("panelP_Provider")){
			icon_P_Provider = new  ImageIcon(JanBase.class.getResource("/resource/P-Provider.PNG"));
			imagem_P_Provider = new JLabel(icon_P_Provider);
			imagem_P_Provider.setBounds(10, 27, 581, 275);
			imagem_P_Provider.setSize(70, 96); // 70 96		
			imagem_P_Provider.setVisible(true);
			imgPatternProvider.add(imagem_P_Provider);
		}else if(panel.getName().equals("panelO_Provider")){
			icon_O_Provider = new  ImageIcon(JanBase.class.getResource("/resource/O-Provider.PNG"));
			imagem_O_Provider = new JLabel(icon_O_Provider);
			imagem_O_Provider.setBounds(10, 27, 581, 275);
			imagem_O_Provider.setSize(70, 96); // 70 96
			imagem_O_Provider.setVisible(true);
			imgPatternProvider.add(imagem_O_Provider);
		}else if(panel.getName().equals("panelP_TCustomer")){
			icon_P_TCustomer = new  ImageIcon(JanBase.class.getResource("/resource/P-TCustomer.PNG"));
			imagem_P_TCustomer = new JLabel(icon_P_TCustomer);
			imagem_P_TCustomer.setBounds(10, 27, 581, 275);
			imagem_P_TCustomer.setSize(70, 96); // 70 96
			imagem_P_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_P_TCustomer);
		}else if(panel.getName().equals("panelP_O_OU_Provider")){
			icon_P_O_OU_Provider = new  ImageIcon(JanBase.class.getResource("/resource/P-O-OU-Provider.PNG"));
			imagem_P_O_OU_Provider = new JLabel(icon_P_O_OU_Provider);
			imagem_P_O_OU_Provider.setBounds(10, 27, 581, 275);
			imagem_P_O_OU_Provider.setSize(70, 96); // 70 96
			imagem_P_O_OU_Provider.setVisible(true);
			imgPatternProvider.add(imagem_P_O_OU_Provider);
		}else if(panel.getName().equals("panelP_O_OU_TCustomer")){
			icon_P_O_OU_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/P-O-OU-TCustomer.PNG"));
			imagem_P_O_OU_TCustomer = new JLabel(icon_P_O_OU_TCustomer);
			imagem_P_O_OU_TCustomer.setBounds(10, 27, 581, 275);
			imagem_P_O_OU_TCustomer.setSize(70, 96); // 70 96
			imagem_P_O_OU_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_P_O_OU_TCustomer);
		}else if(panel.getName().equals("panelP_OU_TCustomer")){
			icon_P_OU_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/P-OU-TCustomer.PNG"));
			imagem_P_OU_TCustomer = new JLabel(icon_P_OU_TCustomer);
			imagem_P_OU_TCustomer.setBounds(10, 27, 581, 275);
			imagem_P_OU_TCustomer.setSize(70, 96); // 70 96
			imagem_P_OU_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_P_OU_TCustomer);
		}else if(panel.getName().equals("panelP_O_TCustomer")){
			icon_P_O_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/P-O-TCustomer.PNG"));
			imagem_P_O_TCustomer = new JLabel(icon_P_O_TCustomer);
			imagem_P_O_TCustomer.setBounds(10, 27, 581, 275);
			imagem_P_O_TCustomer.setSize(70, 96); // 70 96
			imagem_P_O_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_P_O_TCustomer);
		}else if(panel.getName().equals("panelO_OU_TCustomer")){
			icon_O_OU_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/O-OU-TCustomer.PNG"));
			imagem_O_OU_TCustomer = new JLabel(icon_O_OU_TCustomer);
			imagem_O_OU_TCustomer.setBounds(10, 27, 581, 275);
			imagem_O_OU_TCustomer.setSize(70, 96); // 70 96
			imagem_O_OU_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_O_OU_TCustomer);
		}else if(panel.getName().equals("panelOU_TCustomer")){
			icon_OU_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/OU-TCustomer.PNG"));
			imagem_OU_TCustomer = new JLabel(icon_OU_TCustomer);
			imagem_OU_TCustomer.setBounds(10, 27, 581, 275);
			imagem_OU_TCustomer.setSize(70, 96); // 70 96
			imagem_OU_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_OU_TCustomer);
		}else if(panel.getName().equals("panelO_TCustomer")){
			icon_O_TCustomer= new  ImageIcon(JanBase.class.getResource("/resource/O-TCustomer.PNG"));
			imagem_O_TCustomer = new JLabel(icon_O_TCustomer);
			imagem_O_TCustomer.setBounds(10, 27, 581, 275);
			imagem_O_TCustomer.setSize(70, 96); // 70 96
			imagem_O_TCustomer.setVisible(true);
			imgPatternCustomer.add(imagem_O_TCustomer);
		}else if(panel.getName().equals("panelP_OU_Provider")){
			icon_P_OU_Provider= new  ImageIcon(JanBase.class.getResource("/resource/P-OU-Provider.PNG"));
			imagem_P_OU_Provider = new JLabel(icon_P_OU_Provider);
			imagem_P_OU_Provider.setBounds(10, 27, 581, 275);
			imagem_P_OU_Provider.setSize(70, 96); // 70 96
			imagem_P_OU_Provider.setVisible(true);
			imgPatternProvider.add(imagem_P_OU_Provider);
		}else if(panel.getName().equals("panelP_O_Provider")){
			icon_P_O_Provider= new  ImageIcon(JanBase.class.getResource("/resource/P-O-Provider.PNG"));
			imagem_P_O_Provider = new JLabel(icon_P_O_Provider);
			imagem_P_O_Provider.setBounds(10, 27, 581, 275);
			imagem_P_O_Provider.setSize(70, 96); // 70 96
			imagem_P_O_Provider.setVisible(true);
			imgPatternProvider.add(imagem_P_O_Provider);
		}else if(panel.getName().equals("panelO_OU_Provider")){
			icon_O_OU_Provider= new  ImageIcon(JanBase.class.getResource("/resource/O-OU-Provider.PNG"));
			imagem_O_OU_Provider = new JLabel(icon_O_OU_Provider);
			imagem_O_OU_Provider.setBounds(10, 27, 581, 275);
			imagem_O_OU_Provider.setSize(70, 96); // 70 96
			imagem_O_OU_Provider.setVisible(true);
			imgPatternProvider.add(imagem_O_OU_Provider);
		}else if(panel.getName().equals("panelOU_Provider")){
			icon_OU_Provider= new  ImageIcon(JanBase.class.getResource("/resource/OU-Provider.PNG"));
			imagem_OU_Provider = new JLabel(icon_OU_Provider);
			imagem_OU_Provider.setBounds(10, 27, 581, 275);
			imagem_OU_Provider.setSize(70, 96); // 70 96
			imagem_OU_Provider.setVisible(true);
			imgPatternProvider.add(imagem_OU_Provider);
		}
		
		//Hired Provider and Service Customer
		
		if(panel.getName().equals("panelP_HProvider")){
			icon_P_HProvider = new  ImageIcon(JanBase.class.getResource("/resource/P-HProvider.PNG"));
			imagem_P_HProvider = new JLabel(icon_P_HProvider);
			imagem_P_HProvider.setBounds(10, 27, 581, 275);
			imagem_P_HProvider.setSize(70, 96); // 70 96
			imagem_P_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_P_HProvider);
		}else if(panel.getName().equals("panelO_HProvider")){
			icon_O_HProvider = new  ImageIcon(JanBase.class.getResource("/resource/O-HProvider.PNG"));
			imagem_O_HProvider = new JLabel(icon_O_HProvider);
			imagem_O_HProvider.setBounds(10, 27, 581, 275);
			imagem_O_HProvider.setSize(70, 96); // 70 96
			imagem_O_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_O_HProvider);
		}else if(panel.getName().equals("panelP_Customer")){
			icon_P_Customer = new  ImageIcon(JanBase.class.getResource("/resource/P-Customer.PNG"));
			imagem_P_Customer = new JLabel(icon_P_Customer);
			imagem_P_Customer.setBounds(10, 27, 581, 275);
			imagem_P_Customer.setSize(70, 96); // 70 96
			imagem_P_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_P_Customer);
		}else if(panel.getName().equals("panelP_O_OU_HProvider")){
			icon_P_O_OU_HProvider = new  ImageIcon(JanBase.class.getResource("/resource/P-O-OU-HProvider.PNG"));
			imagem_P_O_OU_HProvider = new JLabel(icon_P_O_OU_HProvider);
			imagem_P_O_OU_HProvider.setBounds(10, 27, 581, 275);
			imagem_P_O_OU_HProvider.setSize(70, 96); // 70 96
			imagem_P_O_OU_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_P_O_OU_HProvider);
		}else if(panel.getName().equals("panelP_O_OU_Customer")){
			icon_P_O_OU_Customer= new  ImageIcon(JanBase.class.getResource("/resource/P-O-OU-Customer.PNG"));
			imagem_P_O_OU_Customer = new JLabel(icon_P_O_OU_Customer);
			imagem_P_O_OU_Customer.setBounds(10, 27, 581, 275);
			imagem_P_O_OU_Customer.setSize(70, 96); // 70 96
			imagem_P_O_OU_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_P_O_OU_Customer);
		}else if(panel.getName().equals("panelP_OU_Customer")){
			icon_P_OU_Customer= new  ImageIcon(JanBase.class.getResource("/resource/P-OU-Customer.PNG"));
			imagem_P_OU_Customer = new JLabel(icon_P_OU_Customer);
			imagem_P_OU_Customer.setBounds(10, 27, 581, 275);
			imagem_P_OU_Customer.setSize(70, 96); // 70 96
			imagem_P_OU_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_P_OU_Customer);
		}else if(panel.getName().equals("panelP_O_Customer")){
			icon_P_O_Customer= new  ImageIcon(JanBase.class.getResource("/resource/P-O-Customer.PNG"));
			imagem_P_O_Customer = new JLabel(icon_P_O_Customer);
			imagem_P_O_Customer.setBounds(10, 27, 581, 275);
			imagem_P_O_Customer.setSize(70, 96); // 70 96
			imagem_P_O_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_P_O_Customer);
		}else if(panel.getName().equals("panelO_OU_Customer")){
			icon_O_OU_Customer= new  ImageIcon(JanBase.class.getResource("/resource/O-OU-Customer.PNG"));
			imagem_O_OU_Customer = new JLabel(icon_O_OU_Customer);
			imagem_O_OU_Customer.setBounds(10, 27, 581, 275);
			imagem_O_OU_Customer.setSize(70, 96); // 70 96
			imagem_O_OU_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_O_OU_Customer);
		}else if(panel.getName().equals("panelOU_Customer")){
			icon_OU_Customer= new  ImageIcon(JanBase.class.getResource("/resource/OU-Customer.PNG"));
			imagem_OU_Customer = new JLabel(icon_OU_Customer);
			imagem_OU_Customer.setBounds(10, 27, 581, 275);
			imagem_OU_Customer.setSize(70, 96); // 70 96
			imagem_OU_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_OU_Customer);
		}else if(panel.getName().equals("panelO_Customer")){
			icon_O_Customer= new  ImageIcon(JanBase.class.getResource("/resource/O-Customer.PNG"));
			imagem_O_Customer = new JLabel(icon_O_Customer);
			imagem_O_Customer.setBounds(10, 168, 291, 146);
			imagem_O_Customer.setSize(70, 96); // 70 96
			imagem_O_Customer.setVisible(true);
			imgPatternServiceCustomer.add(imagem_O_Customer);
		}else if(panel.getName().equals("panelP_OU_HProvider")){
			icon_P_OU_HProvider= new  ImageIcon(JanBase.class.getResource("/resource/P-OU-HProvider.PNG"));
			imagem_P_OU_HProvider = new JLabel(icon_P_OU_HProvider);
			imagem_P_OU_HProvider.setBounds(10, 27, 581, 275);
			imagem_P_OU_HProvider.setSize(70, 96); // 70 96
			imagem_P_OU_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_P_OU_HProvider);
		}else if(panel.getName().equals("panelP_O_HProvider")){
			icon_P_O_HProvider= new  ImageIcon(JanBase.class.getResource("/resource/P-O-HProvider.PNG"));
			imagem_P_O_HProvider = new JLabel(icon_P_O_HProvider);
			imagem_P_O_HProvider.setBounds(10, 27, 581, 275);
			imagem_P_O_HProvider.setSize(70, 96); // 70 96
			imagem_P_O_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_P_O_HProvider);
		}else if(panel.getName().equals("panelO_OU_HProvider")){
			icon_O_OU_HProvider= new  ImageIcon(JanBase.class.getResource("/resource/O-OU-HProvider.PNG"));
			imagem_O_OU_HProvider = new JLabel(icon_O_OU_HProvider);
			imagem_O_OU_HProvider.setBounds(10, 27, 581, 275);
			imagem_O_OU_HProvider.setSize(70, 96); // 70 96
			imagem_O_OU_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_O_OU_HProvider);
		}else if(panel.getName().equals("panelOU_HProvider")){
			icon_OU_HProvider= new  ImageIcon(JanBase.class.getResource("/resource/OU-HProvider.PNG"));
			imagem_OU_HProvider = new JLabel(icon_OU_HProvider);
			imagem_OU_HProvider.setBounds(10, 27, 581, 275);
			imagem_OU_HProvider.setSize(70, 96); // 70 96
			imagem_OU_HProvider.setVisible(true);
			imgPatternHiredProvider.add(imagem_OU_HProvider);
		}
		
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
	
	public void desabilitaPaineisHiredProvider(){
		panelP_HProvider.setVisible(false);	
		panelP_O_OU_HProvider.setVisible(false);
		panelP_OU_HProvider.setVisible(false);
		panelP_O_HProvider.setVisible(false);
		panelO_OU_HProvider.setVisible(false);
		panelOU_HProvider.setVisible(false);
		panelO_HProvider.setVisible(false);
	}
	
	public void desabilitaPaineisServiceCustomer(){
		panelP_O_OU_Customer.setVisible(false);
		panelP_OU_Customer.setVisible(false);
		panelP_O_Customer.setVisible(false);
		panelO_OU_Customer.setVisible(false);
		panelOU_Customer.setVisible(false);
		panelO_Customer.setVisible(false);
		panelP_Customer.setVisible(false);
	}	
	
	
	public void desabilitaPaineisPrincipais(){
		panelHiredP_ServiceC_Subgroup.setVisible(false);
		panelPCSubgroup.setVisible(false);
		panelSOffering.setVisible(false);
		panelSODescription.setVisible(false);
		panelSOCommitment.setVisible(false);
		panelSNegAgree.setVisible(false);
	}
	
	/**
	 * @param n
	 */
	public void trocaPainelPrincipalSOffering(int n){  //Esse metodo controla todo o fluxo partindo do Entry Point SOffering
		painelSelecionado += n;
		//Escolhe qual painel sera exibido !
		desabilitaPaineisPrincipais();
		
		if(painelSelecionado == 0){	panelSOffering.setVisible(true);}
		if(painelSelecionado == 1){	panelPCSubgroup.setVisible(true);}
		if(painelSelecionado == 2){ panelSODescription.setVisible(true);}
		if(painelSelecionado == 3){	panelSOCommitment.setVisible(true);}
		if(painelSelecionado == 4){	panelSNegAgree.setVisible(true);} //Mudar para SNegAgree
		/*
		 * if(painelSelecionado == 5){ panelSOfferAgree.setVisible(true);}
		 * if(painelSelecionado == 6){ panelSNegotiation.setVisible(true);}
		 * if(painelSelecionado == 7){ panelSADescription.setVisible(true);}
		 * if(painelSelecionado == 8){ panelSCCommitments.setVisible(true);}
		 * if(painelSelecionado == 9){ panelHPCommitments.setVisible(true);}
		 * if(painelSelecionado == 10){panelSDelivery.setVisible(true);}
		 * if(painelSelecionado == 11){panelHPActions.setVisible(true);}
		 * if(painelSelecionado == 12){panelHPActionMotivation.setVisible(true);}
		 * if(painelSelecionado == 13){panelSCActions.setVisible(true);}
		 * if(painelSelecionado == 14){panelSCActionMotivation.setVisible(true);}
		 * if(painelSelecionado == 15){panelInterations.setVisible(true);}
		 * if(painelSelecionado == 16){panelInteractionMotivation.setVisible(true);}
		 */
	}	
	
	public void trocaPainelPrincipalSAgreement(int n){ //Esse metodo controla todo o fluxo partindo do Entry Point SAgreement
		painelSelecionado += n;
		//Escolhe qual painel sera exibido !
		desabilitaPaineisPrincipais();
		
		/* if(painelSelecionado == 0){panelSAgreement.setVisible(true);} */
		if(painelSelecionado == 1){panelHiredP_ServiceC_Subgroup.setVisible(true);}
		/*
		 * if(painelSelecionado == 2){panelSADescription.setVisible(true);}
		 * if(painelSelecionado == 3){panelSCCommitments.setVisible(true);}
		 * if(painelSelecionado == 4){panelHPCommitments.setVisible(true);}
		 * if(painelSelecionado == 5){panelSDelivery.setVisible(true);}
		 * if(painelSelecionado == 6){panelHPActions.setVisible(true);}
		 * if(painelSelecionado == 7){panelHPActionMotivation.setVisible(true);}
		 * if(painelSelecionado == 8){panelSCActions.setVisible(true);}
		 * if(painelSelecionado == 9){panelSCActionMotivation.setVisible(true);}
		 * if(painelSelecionado == 10){panelInterations.setVisible(true);}
		 * if(painelSelecionado == 11){panelInteractionMotivation.setVisible(true);}
		 */
	}	
		
	public static int getRadioProviderSubgroupSelected(){
		if(rdbtnP_Provider.isSelected()){return 1;}
		else if(rdbtnO_Provider.isSelected()){return 2;}
		else if(rdbtnOU_Provider.isSelected()){return 3;}
		else if(rdbtnP_O_OU_Provider.isSelected()){return 4;}
		else if(rdbtnO_OU_Provider.isSelected()){return 5;}
		else if(rdbtnP_O_Provider.isSelected()){return 6;}
		else if(rdbtnP_OU_Provider.isSelected()){return 7;}		
		return 0;		
	}
	public static int getRadioCustomerSubgroupSelected(){		
		if(rdbtnP_TCustomer.isSelected()){return 1;}
		else if(rdbtnO_TCustomer.isSelected()){return 2;}
		else if(rdbtnOU_TCustomer.isSelected()){return 3;}
		else if(rdbtnP_O_OU_TCustomer.isSelected()){return 4;}
		else if(rdbtnO_OU_TCustomer.isSelected()){return 5;}
		else if(rdbtnP_O_TCustomer.isSelected()){return 6;}
		else if(rdbtnP_OU_TCustomer.isSelected()){return 7;}				
		return 0;	
	}
	
	public static int getRadioHiredProviderSubgroupSelected(){
		if(rdbtnP_HProvider.isSelected()){return 1;}
		else if(rdbtnO_HProvider.isSelected()){return 2;}
		else if(rdbtnOU_HProvider.isSelected()){return 3;}
		else if(rdbtnP_O_OU_HProvider.isSelected()){return 4;}
		else if(rdbtnO_OU_HProvider.isSelected()){return 5;}
		else if(rdbtnP_O_HProvider.isSelected()){return 6;}
		else if(rdbtnP_OU_HProvider.isSelected()){return 7;}		
		return 0;		
	}
	
	public static int getRadioServiceCustomerSubgroupSelected(){		
		if(rdbtnP_Customer.isSelected()){return 1;}
		else if(rdbtnO_Customer.isSelected()){return 2;}
		else if(rdbtnOU_Customer.isSelected()){return 3;}
		else if(rdbtnP_O_OU_Customer.isSelected()){return 4;}
		else if(rdbtnO_OU_Customer.isSelected()){return 5;}
		else if(rdbtnP_O_Customer.isSelected()){return 6;}
		else if(rdbtnP_OU_Customer.isSelected()){return 7;}				
		return 0;	
	}
	
	//GETS AND SETS

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public int getPainelSelecionado() {
		return painelSelecionado;
	}
	public void setPainelSelecionado(int painelSelecionado) {
		this.painelSelecionado = painelSelecionado;
	}
	public JTextField getTxtServiceProvider_P_Provider() {
		return txtServiceProvider_P_Provider;
	}
	public void setTxtServiceProvider_P_Provider(JTextField txtServiceProvider_P_Provider) {
		this.txtServiceProvider_P_Provider = txtServiceProvider_P_Provider;
	}
	public JTextField getTxtPerson_P_Provider() {
		return txtPerson_P_Provider;
	}
	public void setTxtPerson_P_Provider(JTextField txtPerson_P_Provider) {
		this.txtPerson_P_Provider = txtPerson_P_Provider;
	}
	public JTextField getTxtTargetCustomer_P_TCustomer() {
		return txtTargetCustomer_P_TCustomer;
	}
	public void setTxtTargetCustomer_P_TCustomer(JTextField txtTargetCustomer_P_TCustomer) {
		this.txtTargetCustomer_P_TCustomer = txtTargetCustomer_P_TCustomer;
	}
	public JTextField getTxtPerson_P_TCustomer() {
		return txtPerson_P_TCustomer;
	}
	public void setTxtPerson_P_TCustomer(JTextField txtPerson_P_TCustomer) {
		this.txtPerson_P_TCustomer = txtPerson_P_TCustomer;
	}
	public JTextField getTxtServiceProvider_O_Provider() {
		return txtServiceProvider_O_Provider;
	}
	public void setTxtServiceProvider_O_Provider(JTextField txtServiceProvider_O_Provider) {
		this.txtServiceProvider_O_Provider = txtServiceProvider_O_Provider;
	}
	public JTextField getTxtOrganization_O_Provider() {
		return txtOrganization_O_Provider;
	}
	public void setTxtOrganization_O_Provider(JTextField txtOrganization_O_Provider) {
		this.txtOrganization_O_Provider = txtOrganization_O_Provider;
	}
	public JTextField getTxtServiceProvider_OU_Provider() {
		return txtServiceProvider_OU_Provider;
	}
	public void setTxtServiceProvider_OU_Provider(JTextField txtServiceProvider_OU_Provider) {
		this.txtServiceProvider_OU_Provider = txtServiceProvider_OU_Provider;
	}
	public JTextField getTxtOrgUnit_OU_Provider() {
		return txtOrgUnit_OU_Provider;
	}
	public void setTxtOrgUnit_OU_Provider(JTextField txtOrgUnit_OU_Provider) {
		this.txtOrgUnit_OU_Provider = txtOrgUnit_OU_Provider;
	}
	public JTextField getTxtServiceProvider_O_OU_Provider() {
		return txtServiceProvider_O_OU_Provider;
	}
	public void setTxtServiceProvider_O_OU_Provider(JTextField txtServiceProvider_O_OU_Provider) {
		this.txtServiceProvider_O_OU_Provider = txtServiceProvider_O_OU_Provider;
	}
	public JTextField getTxtOrgUnitProvider_O_OU_Provider() {
		return txtOrgUnitProvider_O_OU_Provider;
	}
	public void setTxtOrgUnitProvider_O_OU_Provider(JTextField txtOrgUnitProvider_O_OU_Provider) {
		this.txtOrgUnitProvider_O_OU_Provider = txtOrgUnitProvider_O_OU_Provider;
	}
	public JTextField getTxtOrganizationProvider_O_OU_Provider() {
		return txtOrganizationProvider_O_OU_Provider;
	}
	public void setTxtOrganizationProvider_O_OU_Provider(JTextField txtOrganizationProvider_O_OU_Provider) {
		this.txtOrganizationProvider_O_OU_Provider = txtOrganizationProvider_O_OU_Provider;
	}
	public JTextField getTxtServiceProvider_P_O_Provider() {
		return txtServiceProvider_P_O_Provider;
	}
	public void setTxtServiceProvider_P_O_Provider(JTextField txtServiceProvider_P_O_Provider) {
		this.txtServiceProvider_P_O_Provider = txtServiceProvider_P_O_Provider;
	}
	public JTextField getTxtPersonProvider_P_O_Provider() {
		return txtPersonProvider_P_O_Provider;
	}
	public void setTxtPersonProvider_P_O_Provider(JTextField txtPersonProvider_P_O_Provider) {
		this.txtPersonProvider_P_O_Provider = txtPersonProvider_P_O_Provider;
	}
	public JTextField getTxtOrganizationProvider_P_O_Provider() {
		return txtOrganizationProvider_P_O_Provider;
	}
	public void setTxtOrganizationProvider_P_O_Provider(JTextField txtOrganizationProvider_P_O_Provider) {
		this.txtOrganizationProvider_P_O_Provider = txtOrganizationProvider_P_O_Provider;
	}
	public JTextField getTxtServiceProvider_P_OU_Provider() {
		return txtServiceProvider_P_OU_Provider;
	}
	public void setTxtServiceProvider_P_OU_Provider(JTextField txtServiceProvider_P_OU_Provider) {
		this.txtServiceProvider_P_OU_Provider = txtServiceProvider_P_OU_Provider;
	}
	public JTextField getTxtPersonProvider_P_OU_Provider() {
		return txtPersonProvider_P_OU_Provider;
	}
	public void setTxtPersonProvider_P_OU_Provider(JTextField txtPersonProvider_P_OU_Provider) {
		this.txtPersonProvider_P_OU_Provider = txtPersonProvider_P_OU_Provider;
	}
	public JTextField getTxtOrgUnitProvider_P_OU_Provider() {
		return txtOrgUnitProvider_P_OU_Provider;
	}
	public void setTxtOrgUnitProvider_P_OU_Provider(JTextField txtOrgUnitProvider_P_OU_Provider) {
		this.txtOrgUnitProvider_P_OU_Provider = txtOrgUnitProvider_P_OU_Provider;
	}
	public JTextField getTxtServiceProvider_P_O_OU_Provider() {
		return txtServiceProvider_P_O_OU_Provider;
	}
	public void setTxtServiceProvider_P_O_OU_Provider(JTextField txtServiceProvider_P_O_OU_Provider) {
		this.txtServiceProvider_P_O_OU_Provider = txtServiceProvider_P_O_OU_Provider;
	}
	public JTextField getTxtPersonProvider_P_O_OU_Provider() {
		return txtPersonProvider_P_O_OU_Provider;
	}
	public void setTxtPersonProvider_P_O_OU_Provider(JTextField txtPersonProvider_P_O_OU_Provider) {
		this.txtPersonProvider_P_O_OU_Provider = txtPersonProvider_P_O_OU_Provider;
	}
	public JTextField getTxtOrgUnitProvider_P_O_OU_Provider() {
		return txtOrgUnitProvider_P_O_OU_Provider;
	}
	public void setTxtOrgUnitProvider_P_O_OU_Provider(JTextField txtOrgUnitProvider_P_O_OU_Provider) {
		this.txtOrgUnitProvider_P_O_OU_Provider = txtOrgUnitProvider_P_O_OU_Provider;
	}
	public JTextField getTxtOrgProvider_P_O_OU_Provider() {
		return txtOrgProvider_P_O_OU_Provider;
	}
	public void setTxtOrgProvider_P_O_OU_Provider(JTextField txtOrgProvider_P_O_OU_Provider) {
		this.txtOrgProvider_P_O_OU_Provider = txtOrgProvider_P_O_OU_Provider;
	}
	public JTextField getTxtTargetCustomer_O_TCustomer() {
		return txtTargetCustomer_O_TCustomer;
	}
	public void setTxtTargetCustomer_O_TCustomer(JTextField txtTargetCustomer_O_TCustomer) {
		this.txtTargetCustomer_O_TCustomer = txtTargetCustomer_O_TCustomer;
	}
	public JTextField getTxtOrganization_O_TCustomer() {
		return txtOrganization_O_TCustomer;
	}
	public void setTxtOrganization_O_TCustomer(JTextField txtOrganization_O_TCustomer) {
		this.txtOrganization_O_TCustomer = txtOrganization_O_TCustomer;
	}
	public JTextField getTxtTargetCustomer_OU_TCustomer() {
		return txtTargetCustomer_OU_TCustomer;
	}
	public void setTxtTargetCustomer_OU_TCustomer(JTextField txtTargetCustomer_OU_TCustomer) {
		this.txtTargetCustomer_OU_TCustomer = txtTargetCustomer_OU_TCustomer;
	}
	public JTextField getTxtOrgUnit_OU_TCustomer() {
		return txtOrgUnit_OU_TCustomer;
	}
	public void setTxtOrgUnit_OU_TCustomer(JTextField txtOrgUnit_OU_TCustomer) {
		this.txtOrgUnit_OU_TCustomer = txtOrgUnit_OU_TCustomer;
	}
	public JTextField getTxtTargetCustomer_O_OU_TCustomer() {
		return txtTargetCustomer_O_OU_TCustomer;
	}
	public void setTxtTargetCustomer_O_OU_TCustomer(JTextField txtTargetCustomer_O_OU_TCustomer) {
		this.txtTargetCustomer_O_OU_TCustomer = txtTargetCustomer_O_OU_TCustomer;
	}
	public JTextField getTxtOrgUnitTC_O_OU_TCustomer() {
		return txtOrgUnitTC_O_OU_TCustomer;
	}
	public void setTxtOrgUnitTC_O_OU_TCustomer(JTextField txtOrgUnitTC_O_OU_TCustomer) {
		this.txtOrgUnitTC_O_OU_TCustomer = txtOrgUnitTC_O_OU_TCustomer;
	}
	public JTextField getTxtOrgTC_O_OU_TCustomer() {
		return txtOrgTC_O_OU_TCustomer;
	}
	public void setTxtOrgTC_O_OU_TCustomer(JTextField txtOrgTC_O_OU_TCustomer) {
		this.txtOrgTC_O_OU_TCustomer = txtOrgTC_O_OU_TCustomer;
	}
	public JTextField getTxtTargetCustomer_P_O_TCustomer() {
		return txtTargetCustomer_P_O_TCustomer;
	}
	public void setTxtTargetCustomer_P_O_TCustomer(JTextField txtTargetCustomer_P_O_TCustomer) {
		this.txtTargetCustomer_P_O_TCustomer = txtTargetCustomer_P_O_TCustomer;
	}
	public JTextField getTxtPersonTC_P_O_TCustomer() {
		return txtPersonTC_P_O_TCustomer;
	}
	public void setTxtPersonTC_P_O_TCustomer(JTextField txtPersonTC_P_O_TCustomer) {
		this.txtPersonTC_P_O_TCustomer = txtPersonTC_P_O_TCustomer;
	}
	public JTextField getTxtOrgTC_P_O_TCustomer() {
		return txtOrgTC_P_O_TCustomer;
	}
	public void setTxtOrgTC_P_O_TCustomer(JTextField txtOrgTC_P_O_TCustomer) {
		this.txtOrgTC_P_O_TCustomer = txtOrgTC_P_O_TCustomer;
	}
	public JTextField getTxtTargetCustomerTC_P_OU_TCustomer() {
		return txtTargetCustomerTC_P_OU_TCustomer;
	}
	public void setTxtTargetCustomerTC_P_OU_TCustomer(JTextField txtTargetCustomerTC_P_OU_TCustomer) {
		this.txtTargetCustomerTC_P_OU_TCustomer = txtTargetCustomerTC_P_OU_TCustomer;
	}
	public JTextField getTxtPersonTC_P_OU_TCustomer() {
		return txtPersonTC_P_OU_TCustomer;
	}
	public void setTxtPersonTC_P_OU_TCustomer(JTextField txtPersonTC_P_OU_TCustomer) {
		this.txtPersonTC_P_OU_TCustomer = txtPersonTC_P_OU_TCustomer;
	}
	public JTextField getTxtOrgUnitTC_P_OU_TCustomer() {
		return txtOrgUnitTC_P_OU_TCustomer;
	}
	public void setTxtOrgUnitTC_P_OU_TCustomer(JTextField txtOrgUnitTC_P_OU_TCustomer) {
		this.txtOrgUnitTC_P_OU_TCustomer = txtOrgUnitTC_P_OU_TCustomer;
	}
	public JTextField getTxtOrgProvider_P_OU_TCustomer() {
		return txtOrgProvider_P_OU_TCustomer;
	}
	public void setTxtOrgProvider_P_OU_TCustomer(JTextField txtOrgProvider_P_OU_TCustomer) {
		this.txtOrgProvider_P_OU_TCustomer = txtOrgProvider_P_OU_TCustomer;
	}
	public JTextField gettxtPersonTC_P_O_OU_TCustomer() {
		return txtPersonTC_P_O_OU_TCustomer;
	}
	public void settxtPersonTC_P_O_OU_TCustomer(JTextField txtPersonTC_P_O_OU_TCustomer) {
		this.txtPersonTC_P_O_OU_TCustomer = txtPersonTC_P_O_OU_TCustomer;
	}
	public JTextField gettxtTargetCustomer_P_O_OU_TCustomer() {
		return txtTargetCustomer_P_O_OU_TCustomer;
	}
	public void settxtTargetCustomer_P_O_OU_TCustomer(JTextField txtTargetCustomer_P_O_OU_TCustomer) {
		this.txtTargetCustomer_P_O_OU_TCustomer = txtTargetCustomer_P_O_OU_TCustomer;
	}
	public JTextField gettxtOrgUnitTC_P_O_OU_TCustomer() {
		return txtOrgUnitTC_P_O_OU_TCustomer;
	}
	public void settxtOrgUnitTC_P_O_OU_TCustomer(JTextField txtOrgUnitTC_P_O_OU_TCustomer) {
		this.txtOrgUnitTC_P_O_OU_TCustomer = txtOrgUnitTC_P_O_OU_TCustomer;
	}
	public JTextField gettxtOrgTC_P_O_OU_TCustomer() {
		return txtOrgTC_P_O_OU_TCustomer;
	}
	public void settxtOrgTC_P_O_OU_TCustomer(JTextField txtOrgTC_P_O_OU_TCustomer) {
		this.txtOrgTC_P_O_OU_TCustomer = txtOrgTC_P_O_OU_TCustomer;
	}
	public SOPLPattern getSoplPattern() {
		return soplPattern;
	}
	public void setSoplPattern(SOPLPattern soplPattern) {
		this.soplPattern = soplPattern;
	}
	public static JPanel getImgPatternProvider() {
		return imgPatternProvider;
	}
	public static void setImgPatternProvider(JPanel imgPatternProvider) {
		JanBase.imgPatternProvider = imgPatternProvider;
	}
	public static JPanel getImgPatternCustomer() {
		return imgPatternCustomer;
	}
	public static void setImgPatternCustomer(JPanel imgPatternCustomer) {
		JanBase.imgPatternCustomer = imgPatternCustomer;
	}
	public static JPanel getPanelPCSubgroup() {
		return panelPCSubgroup;
	}
	public static void setPanelPCSubgroup(JPanel panelPCSubgroup) {
		JanBase.panelPCSubgroup = panelPCSubgroup;
	}
	public static JPanel getPanelP_TCustomer() {
		return panelP_TCustomer;
	}
	public static void setPanelP_TCustomer(JPanel panelP_TCustomer) {
		JanBase.panelP_TCustomer = panelP_TCustomer;
	}
	public static JPanel getPanelP_Provider() {
		return panelP_Provider;
	}
	public static void setPanelP_Provider(JPanel panelP_Provider) {
		JanBase.panelP_Provider = panelP_Provider;
	}
	public static JPanel getPanelPatternProvider() {
		return panelPatternProvider;
	}
	public static void setPanelPatternProvider(JPanel panelPatternProvider) {
		JanBase.panelPatternProvider = panelPatternProvider;
	}
	public static JPanel getPanelPatternCustomer() {
		return panelPatternCustomer;
	}
	public static void setPanelPatternCustomer(JPanel panelPatternCustomer) {
		JanBase.panelPatternCustomer = panelPatternCustomer;
	}
	public static JPanel getPanelP_O_OU_Provider() {
		return panelP_O_OU_Provider;
	}
	public static void setPanelP_O_OU_Provider(JPanel panelP_O_OU_Provider) {
		JanBase.panelP_O_OU_Provider = panelP_O_OU_Provider;
	}
	public static JPanel getPanelP_O_OU_TCustomer() {
		return panelP_O_OU_TCustomer;
	}
	public static void setPanelP_O_OU_TCustomer(JPanel panelP_O_OU_TCustomer) {
		JanBase.panelP_O_OU_TCustomer = panelP_O_OU_TCustomer;
	}
	public static JPanel getPanelP_OU_TCustomer() {
		return panelP_OU_TCustomer;
	}
	public static void setPanelP_OU_TCustomer(JPanel panelP_OU_TCustomer) {
		JanBase.panelP_OU_TCustomer = panelP_OU_TCustomer;
	}
	public static JPanel getPanelP_O_TCustomer() {
		return panelP_O_TCustomer;
	}
	public static void setPanelP_O_TCustomer(JPanel panelP_O_TCustomer) {
		JanBase.panelP_O_TCustomer = panelP_O_TCustomer;
	}
	public static JPanel getPanelO_OU_TCustomer() {
		return panelO_OU_TCustomer;
	}
	public static void setPanelO_OU_TCustomer(JPanel panelO_OU_TCustomer) {
		JanBase.panelO_OU_TCustomer = panelO_OU_TCustomer;
	}
	public static JPanel getPanelOU_TCustomer() {
		return panelOU_TCustomer;
	}
	public static void setPanelOU_TCustomer(JPanel panelOU_TCustomer) {
		JanBase.panelOU_TCustomer = panelOU_TCustomer;
	}
	public static JPanel getPanelO_TCustomer() {
		return panelO_TCustomer;
	}
	public static void setPanelO_TCustomer(JPanel panelO_TCustomer) {
		JanBase.panelO_TCustomer = panelO_TCustomer;
	}
	public static JPanel getPanelP_OU_Provider() {
		return panelP_OU_Provider;
	}
	public static void setPanelP_OU_Provider(JPanel panelP_OU_Provider) {
		JanBase.panelP_OU_Provider = panelP_OU_Provider;
	}
	public static JPanel getPanelP_O_Provider() {
		return panelP_O_Provider;
	}
	public static void setPanelP_O_Provider(JPanel panelP_O_Provider) {
		JanBase.panelP_O_Provider = panelP_O_Provider;
	}
	public static JPanel getPanelO_OU_Provider() {
		return panelO_OU_Provider;
	}
	public static void setPanelO_OU_Provider(JPanel panelO_OU_Provider) {
		JanBase.panelO_OU_Provider = panelO_OU_Provider;
	}
	public static JPanel getPanelOU_Provider() {
		return panelOU_Provider;
	}
	public static void setPanelOU_Provider(JPanel panelOU_Provider) {
		JanBase.panelOU_Provider = panelOU_Provider;
	}
	public static JPanel getPanelO_Provider() {
		return panelO_Provider;
	}
	public static void setPanelO_Provider(JPanel panelO_Provider) {
		JanBase.panelO_Provider = panelO_Provider;
	}
	public static JPanel getPanelSOffering() {
		return panelSOffering;
	}
	public static void setPanelSOffering(JPanel panelSOffering) {
		JanBase.panelSOffering = panelSOffering;
	}
	public static JPanel getPanelSODescription() {
		return panelSODescription;
	}
	public static void setPanelSODescription(JPanel panelSODescription) {
		JanBase.panelSODescription = panelSODescription;
	}
	public static JPanel getPanelSOCommitment() {
		return panelSOCommitment;
	}
	public static void setPanelSOCommitment(JPanel panelSOCommitment) {
		JanBase.panelSOCommitment = panelSOCommitment;
	}
	public static JPanel getpanelSNegAgree() {
		return panelSNegAgree;
	}
	public static void setpanelSNegAgree(JPanel panelSNegAgree) {
		JanBase.panelSNegAgree = panelSNegAgree;
	}
	public JTextField getTxtServiceOffering() {
		return txtServiceOffering;
	}
	public void setTxtServiceOffering(JTextField txtServiceOffering) {
		this.txtServiceOffering = txtServiceOffering;
	}
	public JTextField getTxtTargetCC() {
		return txtTargetCC;
	}
	public void setTxtTargetCC(JTextField txtTargetCC) {
		this.txtTargetCC = txtTargetCC;
	}
	public JTextField getTxtServiceOfferingDescription() {
		return txtServiceOfferingDescription;
	}
	public void setTxtServiceOfferingDescription(JTextField txtServiceOfferingDescription) {
		this.txtServiceOfferingDescription = txtServiceOfferingDescription;
	}
	public JTextField getTxtServiceProvider() {
		return txtServiceProvider;
	}
	public void setTxtServiceProvider(JTextField txtServiceProvider) {
		this.txtServiceProvider = txtServiceProvider;
	}
	public JTextField getTxtServiceOfferingCommitment() {
		return txtServiceOfferingCommitment;
	}
	public void setTxtServiceOfferingCommitment(JTextField txtServiceOfferingCommitment) {
		this.txtServiceOfferingCommitment = txtServiceOfferingCommitment;
	}
	public JTextField getTxtServiceOfferingClaim() {
		return txtServiceOfferingClaim;
	}
	public void setTxtServiceOfferingClaim(JTextField txtServiceOfferingClaim) {
		this.txtServiceOfferingClaim = txtServiceOfferingClaim;
	}
	public static JRadioButton getRdbtnP_Provider() {
		return rdbtnP_Provider;
	}
	public static void setRdbtnP_Provider(JRadioButton rdbtnP_Provider) {
		JanBase.rdbtnP_Provider = rdbtnP_Provider;
	}
	public static JRadioButton getRdbtnP_OU_Provider() {
		return rdbtnP_OU_Provider;
	}
	public static void setRdbtnP_OU_Provider(JRadioButton rdbtnP_OU_Provider) {
		JanBase.rdbtnP_OU_Provider = rdbtnP_OU_Provider;
	}
	public static JRadioButton getRdbtnO_Provider() {
		return rdbtnO_Provider;
	}
	public static void setRdbtnO_Provider(JRadioButton rdbtnO_Provider) {
		JanBase.rdbtnO_Provider = rdbtnO_Provider;
	}
	public static JRadioButton getRdbtnP_O_OU_Provider() {
		return rdbtnP_O_OU_Provider;
	}
	public static void setRdbtnP_O_OU_Provider(JRadioButton rdbtnP_O_OU_Provider) {
		JanBase.rdbtnP_O_OU_Provider = rdbtnP_O_OU_Provider;
	}
	public static JRadioButton getRdbtnOU_Provider() {
		return rdbtnOU_Provider;
	}
	public static void setRdbtnOU_Provider(JRadioButton rdbtnOU_Provider) {
		JanBase.rdbtnOU_Provider = rdbtnOU_Provider;
	}
	public static JRadioButton getRdbtnO_OU_Provider() {
		return rdbtnO_OU_Provider;
	}
	public static void setRdbtnO_OU_Provider(JRadioButton rdbtnO_OU_Provider) {
		JanBase.rdbtnO_OU_Provider = rdbtnO_OU_Provider;
	}
	public static JRadioButton getRdbtnP_O_Provider() {
		return rdbtnP_O_Provider;
	}
	public static void setRdbtnP_O_Provider(JRadioButton rdbtnP_O_Provider) {
		JanBase.rdbtnP_O_Provider = rdbtnP_O_Provider;
	}
	public static JRadioButton getRdbtnP_TCustomer() {
		return rdbtnP_TCustomer;
	}
	public static void setRdbtnP_TCustomer(JRadioButton rdbtnP_TCustomer) {
		JanBase.rdbtnP_TCustomer = rdbtnP_TCustomer;
	}
	public static JRadioButton getRdbtnP_O_TCustomer() {
		return rdbtnP_O_TCustomer;
	}
	public static void setRdbtnP_O_TCustomer(JRadioButton rdbtnP_O_TCustomer) {
		JanBase.rdbtnP_O_TCustomer = rdbtnP_O_TCustomer;
	}
	public static JRadioButton getRdbtnO_TCustomer() {
		return rdbtnO_TCustomer;
	}
	public static void setRdbtnO_TCustomer(JRadioButton rdbtnO_TCustomer) {
		JanBase.rdbtnO_TCustomer = rdbtnO_TCustomer;
	}
	public static JRadioButton getRdbtnP_OU_TCustomer() {
		return rdbtnP_OU_TCustomer;
	}
	public static void setRdbtnP_OU_TCustomer(JRadioButton rdbtnP_OU_TCustomer) {
		JanBase.rdbtnP_OU_TCustomer = rdbtnP_OU_TCustomer;
	}
	public static JRadioButton getRdbtnOU_TCustomer() {
		return rdbtnOU_TCustomer;
	}
	public static void setRdbtnOU_TCustomer(JRadioButton rdbtnOU_TCustomer) {
		JanBase.rdbtnOU_TCustomer = rdbtnOU_TCustomer;
	}
	public static JRadioButton getRdbtnP_O_OU_TCustomer() {
		return rdbtnP_O_OU_TCustomer;
	}
	public static void setRdbtnP_O_OU_TCustomer(JRadioButton rdbtnP_O_OU_TCustomer) {
		JanBase.rdbtnP_O_OU_TCustomer = rdbtnP_O_OU_TCustomer;
	}
	public static JRadioButton getRdbtnO_OU_TCustomer() {
		return rdbtnO_OU_TCustomer;
	}
	public static void setRdbtnO_OU_TCustomer(JRadioButton rdbtnO_OU_TCustomer) {
		JanBase.rdbtnO_OU_TCustomer = rdbtnO_OU_TCustomer;
	}
	public ButtonGroup getRdCustomerGroup() {
		return rdCustomerGroup;
	}
	public void setRdCustomerGroup(ButtonGroup rdCustomerGroup) {
		this.rdCustomerGroup = rdCustomerGroup;
	}
	public ButtonGroup getRdProviderGroup() {
		return rdProviderGroup;
	}
	public void setRdProviderGroup(ButtonGroup rdProviderGroup) {
		this.rdProviderGroup = rdProviderGroup;
	}
	public static ImageIcon getIcon_P_Provider() {
		return icon_P_Provider;
	}
	public static void setIcon_P_Provider(ImageIcon icon_P_Provider) {
		JanBase.icon_P_Provider = icon_P_Provider;
	}
	public static JLabel getImagem_P_Provider() {
		return imagem_P_Provider;
	}
	public static void setImagem_P_Provider(JLabel imagem_P_Provider) {
		JanBase.imagem_P_Provider = imagem_P_Provider;
	}
	public static ImageIcon getIcon_O_Provider() {
		return icon_O_Provider;
	}
	public static void setIcon_O_Provider(ImageIcon icon_O_Provider) {
		JanBase.icon_O_Provider = icon_O_Provider;
	}
	public static JLabel getImagem_O_Provider() {
		return imagem_O_Provider;
	}
	public static void setImagem_O_Provider(JLabel imagem_O_Provider) {
		JanBase.imagem_O_Provider = imagem_O_Provider;
	}
	public static ImageIcon getIcon_OU_Provider() {
		return icon_OU_Provider;
	}
	public static void setIcon_OU_Provider(ImageIcon icon_OU_Provider) {
		JanBase.icon_OU_Provider = icon_OU_Provider;
	}
	public static JLabel getImagem_OU_Provider() {
		return imagem_OU_Provider;
	}
	public static void setImagem_OU_Provider(JLabel imagem_OU_Provider) {
		JanBase.imagem_OU_Provider = imagem_OU_Provider;
	}
	public static ImageIcon getIcon_P_O_OU_Provider() {
		return icon_P_O_OU_Provider;
	}
	public static void setIcon_P_O_OU_Provider(ImageIcon icon_P_O_OU_Provider) {
		JanBase.icon_P_O_OU_Provider = icon_P_O_OU_Provider;
	}
	public static JLabel getImagem_P_O_OU_Provider() {
		return imagem_P_O_OU_Provider;
	}
	public static void setImagem_P_O_OU_Provider(JLabel imagem_P_O_OU_Provider) {
		JanBase.imagem_P_O_OU_Provider = imagem_P_O_OU_Provider;
	}
	public static ImageIcon getIcon_O_OU_Provider() {
		return icon_O_OU_Provider;
	}
	public static void setIcon_O_OU_Provider(ImageIcon icon_O_OU_Provider) {
		JanBase.icon_O_OU_Provider = icon_O_OU_Provider;
	}
	public static JLabel getImagem_O_OU_Provider() {
		return imagem_O_OU_Provider;
	}
	public static void setImagem_O_OU_Provider(JLabel imagem_O_OU_Provider) {
		JanBase.imagem_O_OU_Provider = imagem_O_OU_Provider;
	}
	public static ImageIcon getIcon_P_O_Provider() {
		return icon_P_O_Provider;
	}
	public static void setIcon_P_O_Provider(ImageIcon icon_P_O_Provider) {
		JanBase.icon_P_O_Provider = icon_P_O_Provider;
	}
	public static JLabel getImagem_P_O_Provider() {
		return imagem_P_O_Provider;
	}
	public static void setImagem_P_O_Provider(JLabel imagem_P_O_Provider) {
		JanBase.imagem_P_O_Provider = imagem_P_O_Provider;
	}
	public static ImageIcon getIcon_P_OU_Provider() {
		return icon_P_OU_Provider;
	}
	public static void setIcon_P_OU_Provider(ImageIcon icon_P_OU_Provider) {
		JanBase.icon_P_OU_Provider = icon_P_OU_Provider;
	}
	public static JLabel getImagem_P_OU_Provider() {
		return imagem_P_OU_Provider;
	}
	public static void setImagem_P_OU_Provider(JLabel imagem_P_OU_Provider) {
		JanBase.imagem_P_OU_Provider = imagem_P_OU_Provider;
	}
	public static ImageIcon getIcon_P_TCustomer() {
		return icon_P_TCustomer;
	}
	public static void setIcon_P_TCustomer(ImageIcon icon_P_TCustomer) {
		JanBase.icon_P_TCustomer = icon_P_TCustomer;
	}
	public static JLabel getImagem_P_TCustomer() {
		return imagem_P_TCustomer;
	}
	public static void setImagem_P_TCustomer(JLabel imagem_P_TCustomer) {
		JanBase.imagem_P_TCustomer = imagem_P_TCustomer;
	}
	public static ImageIcon getIcon_O_TCustomer() {
		return icon_O_TCustomer;
	}
	public static void setIcon_O_TCustomer(ImageIcon icon_O_TCustomer) {
		JanBase.icon_O_TCustomer = icon_O_TCustomer;
	}
	public static JLabel getImagem_O_TCustomer() {
		return imagem_O_TCustomer;
	}
	public static void setImagem_O_TCustomer(JLabel imagem_O_TCustomer) {
		JanBase.imagem_O_TCustomer = imagem_O_TCustomer;
	}
	public static ImageIcon getIcon_OU_TCustomer() {
		return icon_OU_TCustomer;
	}
	public static void setIcon_OU_TCustomer(ImageIcon icon_OU_TCustomer) {
		JanBase.icon_OU_TCustomer = icon_OU_TCustomer;
	}
	public static JLabel getImagem_OU_TCustomer() {
		return imagem_OU_TCustomer;
	}
	public static void setImagem_OU_TCustomer(JLabel imagem_OU_TCustomer) {
		JanBase.imagem_OU_TCustomer = imagem_OU_TCustomer;
	}
	public static ImageIcon getIcon_P_O_OU_TCustomer() {
		return icon_P_O_OU_TCustomer;
	}
	public static void setIcon_P_O_OU_TCustomer(ImageIcon icon_P_O_OU_TCustomer) {
		JanBase.icon_P_O_OU_TCustomer = icon_P_O_OU_TCustomer;
	}
	public static JLabel getImagem_P_O_OU_TCustomer() {
		return imagem_P_O_OU_TCustomer;
	}
	public static void setImagem_P_O_OU_TCustomer(JLabel imagem_P_O_OU_TCustomer) {
		JanBase.imagem_P_O_OU_TCustomer = imagem_P_O_OU_TCustomer;
	}
	public static ImageIcon getIcon_O_OU_TCustomer() {
		return icon_O_OU_TCustomer;
	}
	public static void setIcon_O_OU_TCustomer(ImageIcon icon_O_OU_TCustomer) {
		JanBase.icon_O_OU_TCustomer = icon_O_OU_TCustomer;
	}
	public static JLabel getImagem_O_OU_TCustomer() {
		return imagem_O_OU_TCustomer;
	}
	public static void setImagem_O_OU_TCustomer(JLabel imagem_O_OU_TCustomer) {
		JanBase.imagem_O_OU_TCustomer = imagem_O_OU_TCustomer;
	}
	public static ImageIcon getIcon_P_O_TCustomer() {
		return icon_P_O_TCustomer;
	}
	public static void setIcon_P_O_TCustomer(ImageIcon icon_P_O_TCustomer) {
		JanBase.icon_P_O_TCustomer = icon_P_O_TCustomer;
	}
	public static JLabel getImagem_P_O_TCustomer() {
		return imagem_P_O_TCustomer;
	}
	public static void setImagem_P_O_TCustomer(JLabel imagem_P_O_TCustomer) {
		JanBase.imagem_P_O_TCustomer = imagem_P_O_TCustomer;
	}
	public static ImageIcon getIcon_P_OU_TCustomer() {
		return icon_P_OU_TCustomer;
	}
	public static void setIcon_P_OU_TCustomer(ImageIcon icon_P_OU_TCustomer) {
		JanBase.icon_P_OU_TCustomer = icon_P_OU_TCustomer;
	}
	public static JLabel getImagem_P_OU_TCustomer() {
		return imagem_P_OU_TCustomer;
	}
	public static void setImagem_P_OU_TCustomer(JLabel imagem_P_OU_TCustomer) {
		JanBase.imagem_P_OU_TCustomer = imagem_P_OU_TCustomer;
	}
	public ImageIcon getIconSOffering() {
		return iconSOffering;
	}
	public void setIconSOffering(ImageIcon iconSOffering) {
		this.iconSOffering = iconSOffering;
	}
	public JLabel getImagemSOffering() {
		return imagemSOffering;
	}
	public void setImagemSOffering(JLabel imagemSOffering) {
		this.imagemSOffering = imagemSOffering;
	}
	public ImageIcon getIconSODescription() {
		return iconSODescription;
	}
	public void setIconSODescription(ImageIcon iconSODescription) {
		this.iconSODescription = iconSODescription;
	}
	public JLabel getImagemSODescription() {
		return imagemSODescription;
	}
	public void setImagemSODescription(JLabel imagemSODescription) {
		this.imagemSODescription = imagemSODescription;
	}
	public ImageIcon getIconSOCommitment() {
		return iconSOCommitment;
	}
	public void setIconSOCommitment(ImageIcon iconSOCommitment) {
		this.iconSOCommitment = iconSOCommitment;
	}
	public JLabel getImagemSOCommitment() {
		return imagemSOCommitment;
	}
	public void setImagemSOCommitment(JLabel imagemSOCommitment) {
		this.imagemSOCommitment = imagemSOCommitment;
	}
	public ImageIcon getIconSNegAgree() {
		return iconSNegAgree;
	}
	public void setIconSNegAgree(ImageIcon iconSNegAgree) {
		this.iconSNegAgree = iconSNegAgree;
	}
	public JLabel getImagemSNegAgree() {
		return imagemSNegAgree;
	}
	public void setImagemSNegAgree(JLabel imagemSNegAgree) {
		this.imagemSNegAgree = imagemSNegAgree;
	}
}
