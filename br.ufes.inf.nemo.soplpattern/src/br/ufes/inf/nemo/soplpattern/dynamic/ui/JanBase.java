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

	public JTextField getTextField_35() {
		return textField_35;
	}

	public void setTextField_35(JTextField textField_35) {
		this.textField_35 = textField_35;
	}

	public JTextField getTextField_36() {
		return textField_36;
	}

	public void setTextField_36(JTextField textField_36) {
		this.textField_36 = textField_36;
	}

	public JTextField getTextField_37() {
		return textField_37;
	}

	public void setTextField_37(JTextField textField_37) {
		this.textField_37 = textField_37;
	}

	public JTextField getTextField_38() {
		return textField_38;
	}

	public void setTextField_38(JTextField textField_38) {
		this.textField_38 = textField_38;
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

	public static JPanel getPanelSOClaim() {
		return panelSOClaim;
	}

	public static void setPanelSOClaim(JPanel panelSOClaim) {
		JanBase.panelSOClaim = panelSOClaim;
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

	public ImageIcon getIconSOClaim() {
		return iconSOClaim;
	}

	public void setIconSOClaim(ImageIcon iconSOClaim) {
		this.iconSOClaim = iconSOClaim;
	}

	public JLabel getImagemSOClaim() {
		return imagemSOClaim;
	}

	public void setImagemSOClaim(JLabel imagemSOClaim) {
		this.imagemSOClaim = imagemSOClaim;
	}

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
	
	//PANEL SOFFERING
	private static JPanel panelSOffering;
	private static JPanel panelSODescription;
	private static JPanel panelSOCommitment;
	private static JPanel panelSOClaim;
	private JTextField txtServiceOffering;
	private JTextField txtTargetCC;
	private JTextField txtServiceOfferingDescription;
	private JTextField txtServiceProvider;
	private JTextField txtServiceOfferingCommitment;
	private JTextField txtServiceOfferingClaim;
	
	//JRadioButtons Provider and Customer Subgroup
	
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
	
	//Button Groups
	
	ButtonGroup rdCustomerGroup;
	ButtonGroup rdProviderGroup;
	
	//Imagens
	
	 //Provider Subgroup
	
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
	
	 //Customer Subgroup
	
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
	
	//
	private ImageIcon iconSOffering;
	private JLabel imagemSOffering;
	private ImageIcon iconSODescription;
	private JLabel imagemSODescription;
	private ImageIcon iconSOCommitment;
	private JLabel imagemSOCommitment;
	private ImageIcon iconSOClaim;
	private JLabel imagemSOClaim;


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
		btnNext.setBounds(678, 522, 89, 37);
		frame.getContentPane().add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trocaPainelPrincipal(-1);
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
		//panelPCSubgroup.setVisible(false);
		
		panelPatternProvider = new JPanel();
		panelPatternProvider.setBorder(new TitledBorder(null, "Provider", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatternProvider.setBounds(10,11, 291, 152);
		panelPCSubgroup.add(panelPatternProvider);
		panelPatternProvider.setLayout(null);		
						
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
		
		panelPatternCustomer = new JPanel();
		panelPatternCustomer.setLayout(null);
		panelPatternCustomer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Target Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPatternCustomer.setBounds(325,11, 286, 152);
		panelPCSubgroup.add(panelPatternCustomer);
		
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
		
		//Group the radio buttons Provider.
		rdProviderGroup = new ButtonGroup();
		rdProviderGroup.add(rdbtnP_Provider); rdProviderGroup.add(rdbtnP_OU_Provider);
		rdProviderGroup.add(rdbtnO_Provider); rdProviderGroup.add(rdbtnP_O_OU_Provider);
		rdProviderGroup.add(rdbtnOU_Provider); 
		rdProviderGroup.add(rdbtnO_OU_Provider); 
		rdProviderGroup.add(rdbtnP_O_Provider); 
		
		//Group the radio buttons Target Customer.
		rdCustomerGroup = new ButtonGroup();
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
		
		//SOClaim
		
		panelSOClaim = new JPanel();
		panelSOClaim.setBounds(157, 11, 621, 511);
		frame.getContentPane().add(panelSOClaim);
		panelSOClaim.setLayout(null);	
		panelSOClaim.setVisible(false);
		
		JPanel panelSOClaimInterno = new JPanel();
		panelSOClaimInterno.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SOClaim", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSOClaimInterno.setBounds(10, 11, 601, 489);
		panelSOClaim.add(panelSOClaimInterno);
		panelSOClaimInterno.setLayout(null);
		
		JLabel lblClass_SOClaim = new JLabel("Class");
		lblClass_SOClaim.setBounds(143, 327, 68, 14);
		panelSOClaimInterno.add(lblClass_SOClaim);
		
		JLabel lblName_SOClaim = new JLabel("Name");
		lblName_SOClaim.setBounds(328, 327, 68, 14);
		panelSOClaimInterno.add(lblName_SOClaim);
		
		JLabel lblServiceOfferingClaim = new JLabel("Service Offering Claim");
		lblServiceOfferingClaim.setBounds(143, 352, 151, 14);
		panelSOClaimInterno.add(lblServiceOfferingClaim);
		
		txtServiceOfferingClaim = new JTextField();
		txtServiceOfferingClaim.setBounds(328, 352, 166, 20);
		panelSOClaimInterno.add(txtServiceOfferingClaim);
		txtServiceOfferingClaim.setColumns(10);
			
		JPanel panelImg_SOClaimm = new JPanel();
		panelImg_SOClaimm.setBackground(Color.LIGHT_GRAY);
		panelImg_SOClaimm.setBounds(10, 27, 581, 275);
		iconSOClaim = new  ImageIcon(getClass().getResource("/resource/SOClaim.PNG"));
		imagemSOClaim = new JLabel(iconSOClaim);
		imagemSOClaim.setBounds(10, 27, 581, 275);
		imagemSOClaim.setSize(70, 96); // 70 96
		imagemSOClaim.setVisible(true);
		panelImg_SOClaimm.add(imagemSOClaim);
		panelSOClaimInterno.add(panelImg_SOClaimm);	
		
		JButton btnCreateConcepts = new JButton("Create Concepts");
		btnCreateConcepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				//Verifica os radios selecionados
				int rdProviderSubgroupSelected = JanBase.getRadioProviderSubgroupSelected();
				int rdCustomerSubgroupSelected = JanBase.getRadioCustomerSubgroupSelected();
										
				soplPattern.getSpecificFix(rdProviderSubgroupSelected, rdCustomerSubgroupSelected);	
				frame.dispose();
			}
		});
		btnCreateConcepts.setBounds(167, 522, 113, 37);
		frame.getContentPane().add(btnCreateConcepts);

		
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	//Esse metodo verifica qual pattern subgroup foi selecionado e troca a imagem correspondente no JPanel
	public static void trocaPainel(JPanel panel ){		
		panel.setVisible(true);
		imgPatternCustomer.removeAll();
		imgPatternProvider.removeAll();
				
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
	
	public void desabilitaPaineisPrincipais(){
		panelPCSubgroup.setVisible(false);
		panelSOffering.setVisible(false);
		panelSODescription.setVisible(false);
		panelSOCommitment.setVisible(false);
		panelSOClaim.setVisible(false);
	}
	
	public void trocaPainelPrincipal(int n){
		painelSelecionado += n;
		
		//Escolhe qual painel sera exibido !
		desabilitaPaineisPrincipais();
		
		if(painelSelecionado == 0){			
			panelPCSubgroup.setVisible(true);
		}
		else if(painelSelecionado == 1){			
			panelSOffering.setVisible(true);
		

		}
		else if(painelSelecionado == 2){			
			panelSODescription.setVisible(true);
		}
		else if(painelSelecionado == 3){			
			panelSOCommitment.setVisible(true);
		}else if(painelSelecionado == 4){			
			panelSOClaim.setVisible(true);
		}
	}	
	
//	public static String getRadioProviderSubgroupSelected(){
//		
//		if(rdbtnP_Provider.isSelected()){
//			return rdbtnP_Provider.getName();
//		}else if(rdbtnP_OU_Provider.isSelected()){
//			return rdbtnP_OU_Provider.getName();
//		}else if(rdbtnO_Provider.isSelected()){
//			return rdbtnO_Provider.getName();
//		}else if(rdbtnP_O_OU_Provider.isSelected()){
//			return rdbtnP_O_OU_Provider.getName();
//		}else if(rdbtnOU_Provider.isSelected()){
//			return rdbtnOU_Provider.getName();
//		}else if(rdbtnO_OU_Provider.isSelected()){
//			return rdbtnO_OU_Provider.getName();
//		}else if(rdbtnP_O_Provider.isSelected()){
//			return rdbtnP_O_Provider.getName();
//		}
//		
//		return null;		
//	}
//	
//	public static String getRadioCustomerSubgroupSelected(){		
//	
//		if(rdbtnP_TCustomer.isSelected()){
//			return rdbtnP_TCustomer.getName();
//		}else if(rdbtnP_O_TCustomer.isSelected()){
//			return rdbtnP_O_TCustomer.getName();
//		}else if(rdbtnO_TCustomer.isSelected()){
//			return rdbtnO_TCustomer.getName();
//		}else if(rdbtnP_OU_TCustomer.isSelected()){
//			return rdbtnP_OU_TCustomer.getName();
//		}else if(rdbtnP_OU_TCustomer.isSelected()){
//			return rdbtnP_OU_TCustomer.getName();
//		}else if(rdbtnOU_TCustomer.isSelected()){
//			return rdbtnOU_TCustomer.getName();
//		}else if(rdbtnP_O_OU_TCustomer.isSelected()){
//			return rdbtnP_O_OU_TCustomer.getName();
//		}else if(rdbtnO_OU_TCustomer.isSelected()){
//			return rdbtnO_OU_TCustomer.getName();
//		}
//		return null;
//		
//	}
	
	public static int getRadioProviderSubgroupSelected(){
		
		if(rdbtnP_Provider.isSelected()){
			return 1;
		}else if(rdbtnO_Provider.isSelected()){
			return 2;
		}else if(rdbtnOU_Provider.isSelected()){
			return 3;
		}else if(rdbtnP_O_OU_Provider.isSelected()){
			return 4;
		}else if(rdbtnO_OU_Provider.isSelected()){
			return 5;
		}else if(rdbtnP_O_Provider.isSelected()){
			return 6;
		}else if(rdbtnP_OU_Provider.isSelected()){
			return 7;
		}
		
		return 0;		
	}
	
	public static int getRadioCustomerSubgroupSelected(){		
	
		if(rdbtnP_TCustomer.isSelected()){
			return 1;
		}else if(rdbtnO_TCustomer.isSelected()){
			return 2;
		}else if(rdbtnOU_TCustomer.isSelected()){
			return 3;
		}else if(rdbtnP_O_OU_TCustomer.isSelected()){
			return 4;
		}else if(rdbtnO_OU_TCustomer.isSelected()){
			return 5;
		}else if(rdbtnP_O_TCustomer.isSelected()){
			return 6;
		}else if(rdbtnP_OU_TCustomer.isSelected()){
			return 7;
		}				
		return 0;
		
	}
	
//	public String radioCustomerSubgroupSelected(){
//		ButtonModel rdCustomerSelected = rdCustomerGroup.getSelection();
//		
//		return rdCustomerSelected.getName();
//	}
}
