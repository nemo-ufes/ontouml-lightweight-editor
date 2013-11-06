package br.ufes.inf.nemo.oled.ui.diagram;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.impl.CharacterizationImpl;
import RefOntoUML.impl.MediationImpl;
import RefOntoUML.impl.MeronymicImpl;
import RefOntoUML.impl.subQuantityOfImpl;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDirection;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class EditAssociationDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 7921212911820841003L;
	private JPanel buttonPanel;
	private JButton okButton;
	private JPanel sourcePanel;
	private JCheckBox targetNavigableCheck;
	private JCheckBox sourceNavigableCheck;
	private JTextField targetRoleText;
	private JLabel targetRoleLabel;
	private JTextField sourceRoleText;
	private JLabel sourceRoleLabel;
	private JCheckBox readonlyTargetCheck;
	@SuppressWarnings("rawtypes")
	private JComboBox multiplicityTargetCombo;
	private JLabel multiplicityTargetLabel;
	private JPanel targetPanel;
	private JRadioButton l2rRadio;
	private JRadioButton noneRadio;
	private JCheckBox immutablePartCheck;
	private JCheckBox immutableWholeCheck;
	private JCheckBox inseparableCheck;
	private JPanel meronymicAssociationPanel;
	private JCheckBox essentialCheck;
	private JCheckBox shareableCheck;
	private JPanel partPanel;
	private JPanel wholePanel;
	private JLabel multiplicitySourceLabel;
	private ButtonGroup nameDirectionGroup;
	private JButton assocAnttButton;
	private JButton trgPropAnttButton;
	private JButton srcPropAnttButton;
	private JCheckBox showRolesCheck;
	private JCheckBox showMultiplicityCheck;
	private JCheckBox readonlySourceCheck;
	@SuppressWarnings("rawtypes")
	private JComboBox multiplicitySourceCombo;
	private JRadioButton r2lRadio;
	private JPanel nameDirectionPanel;
	private JCheckBox nameVisibilityCheck;
	private JTextField associationNameText;
	private JLabel associatioNameLabel;
	private JPanel meronymicPropertiesPanel;
	private JPanel associationPropertiesPanel;
	private JTabbedPane contentTabbedPane;
	private JButton cancelButton;
	private AssociationElement associationElement;
	private JFrame parent;
	private DiagramManager diagramManager;

	public EditAssociationDialog(JFrame parent, DiagramManager diagramManager, AssociationElement associationElement, boolean modal) {
		super(parent, modal);
		
		this.diagramManager = diagramManager;
		this.associationElement = associationElement;
		this.parent = parent;
		
		initGUI();
		myPostInit();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		try {
			{
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				this.setResizable(false);
				setTitle(ApplicationResources.getInstance().getString("dialog.assocproperties.title"));
			}
			{
				buttonPanel = new JPanel();
				GroupLayout buttonPanelLayout = new GroupLayout((JComponent)buttonPanel);
				getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				buttonPanel.setLayout(buttonPanelLayout);
				buttonPanel.setPreferredSize(new java.awt.Dimension(584, 42));
				{
					okButton = new JButton();
					okButton.setText(ApplicationResources.getInstance().getString("stdcaption.ok"));
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (validateInput()) {
								transferDataToAssociation();
								//update model tree
								ProjectBrowser.updateModelTree(diagramManager.getCurrentProject());
								dispose();
							}
						}
					});
				}
				{
					cancelButton = new JButton();
					cancelButton.setText(ApplicationResources.getInstance().getString("stdcaption.cancel"));
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							dispose();
						}
					});
				}
				buttonPanelLayout.setHorizontalGroup(buttonPanelLayout.createSequentialGroup()
					.addContainerGap(402, Short.MAX_VALUE)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap());
				buttonPanelLayout.setVerticalGroup(buttonPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, 27));
			}
			{
				contentTabbedPane = new JTabbedPane();
				getContentPane().add(contentTabbedPane, BorderLayout.CENTER);
				{
					associationPropertiesPanel = new JPanel();
					GroupLayout associationPropertiesPanelLayout = new GroupLayout((JComponent)associationPropertiesPanel);
					associationPropertiesPanel.setLayout(associationPropertiesPanelLayout);
					contentTabbedPane.addTab(ApplicationResources.getInstance().getString("dialog.assoc.association"), null, associationPropertiesPanel, null);
					associationPropertiesPanel.setPreferredSize(new java.awt.Dimension(579, 244));
					{
						associatioNameLabel = new JLabel();
						associatioNameLabel.setText(ApplicationResources.getInstance().getString("stdcaption.name"));
					}
					{
						associationNameText = new JTextField();
					}
					{
						nameVisibilityCheck = new JCheckBox();
						nameVisibilityCheck.setText(ApplicationResources.getInstance().getString("stdcaption.visible"));
					}
					{
						nameDirectionPanel = new JPanel();
						GroupLayout nameDirectionPanelLayout = new GroupLayout((JComponent)nameDirectionPanel);
						nameDirectionPanel.setLayout(nameDirectionPanelLayout);
						nameDirectionPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.assocdirection.title")));
						{
							r2lRadio = new JRadioButton();
							r2lRadio.setText(ApplicationResources.getInstance().getString("dialog.assocdirection.rightleft"));
							getNameDirectionGroup().add(r2lRadio);
						}
						{
							noneRadio = new JRadioButton();
							noneRadio.setText(ApplicationResources.getInstance().getString("dialog.assocdirection.none"));
							getNameDirectionGroup().add(noneRadio);
						}
						{
							l2rRadio = new JRadioButton();
							l2rRadio.setText(ApplicationResources.getInstance().getString("dialog.assocdirection.leftright"));
							getNameDirectionGroup().add(l2rRadio);
						}
						nameDirectionPanelLayout.setHorizontalGroup(nameDirectionPanelLayout.createSequentialGroup()
							.addContainerGap(33, 33)
							.addComponent(noneRadio, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(l2rRadio, 0, 155, Short.MAX_VALUE)
							.addGap(33)
							.addComponent(r2lRadio, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
						nameDirectionPanelLayout.setVerticalGroup(nameDirectionPanelLayout.createSequentialGroup()
							.addGroup(nameDirectionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(noneRadio, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(l2rRadio, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(r2lRadio, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED));
					}
					{
						showMultiplicityCheck = new JCheckBox();
						showMultiplicityCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.showmultiplicities"));
					}
					{
						sourcePanel = new JPanel();
						GroupLayout sourcePanelLayout = new GroupLayout((JComponent)sourcePanel);
						sourcePanel.setLayout(sourcePanelLayout);
						sourcePanel.setBorder(BorderFactory.createTitledBorder(
								ApplicationResources.getInstance().getString("dialog.assoc.source.title") + 
								" (" + getAssociation().getOwnedEnd().get(0).getType().getName() + ")"));
						{
							multiplicitySourceLabel = new JLabel();
							multiplicitySourceLabel.setText(ApplicationResources.getInstance().getString("dialog.assoc.multiplicity"));
						}
						{
							multiplicitySourceCombo = new JComboBox();
							multiplicitySourceCombo.setModel(getDefaultMultiplicitiesModel(null));
							multiplicitySourceCombo.setEditable(true);
						}
						{
							readonlySourceCheck = new JCheckBox();
							readonlySourceCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.readonly"));
						}
						{
							sourceRoleLabel = new JLabel();
							sourceRoleLabel.setText("Role");
						}
						{
							sourceRoleText = new JTextField();
						}
						{
							sourceNavigableCheck = new JCheckBox();
							sourceNavigableCheck.setText("Navigable");
						}
						{
							srcPropAnttButton = new JButton();
							srcPropAnttButton.setToolTipText("Annotations for this element");
							srcPropAnttButton.setIcon(IconLoader.getInstance().getIcon(IconType.ANNOTATION));
							srcPropAnttButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									EditAnnotationDialog antDialog = new EditAnnotationDialog(parent, diagramManager, associationElement.getAssociation().getOwnedEnd().get(0), true);
									antDialog.setVisible(true);
								}
							});
						}
						{
							trgPropAnttButton = new JButton();
							trgPropAnttButton.setToolTipText("Annotations for this element");
							trgPropAnttButton.setIcon(IconLoader.getInstance().getIcon(IconType.ANNOTATION));
							trgPropAnttButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									EditAnnotationDialog antDialog = new EditAnnotationDialog(parent, diagramManager, associationElement.getAssociation().getOwnedEnd().get(1), true);
									antDialog.setVisible(true);
								}
							});
						}
						{
							assocAnttButton = new JButton();
							assocAnttButton.setToolTipText("Annotations for this element");
							assocAnttButton.setIcon(IconLoader.getInstance().getIcon(IconType.ANNOTATION));
							assocAnttButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									EditAnnotationDialog antDialog = new EditAnnotationDialog(parent, diagramManager, associationElement.getAssociation(), true);
									antDialog.setVisible(true);
								}
							});
						}
						sourcePanelLayout.setHorizontalGroup(sourcePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(sourcePanelLayout.createParallelGroup()
							    .addGroup(sourcePanelLayout.createSequentialGroup()
							        .addGroup(sourcePanelLayout.createParallelGroup()
							            .addComponent(multiplicitySourceLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							            .addGroup(GroupLayout.Alignment.LEADING, sourcePanelLayout.createSequentialGroup()
							                .addComponent(srcPropAnttButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							                .addGap(51))
							            .addComponent(sourceRoleLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
							        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							        .addGroup(sourcePanelLayout.createParallelGroup()
							            .addComponent(multiplicitySourceCombo, GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE)
							            .addComponent(sourceRoleText, GroupLayout.Alignment.LEADING, 0, 143, Short.MAX_VALUE)))
							    .addGroup(GroupLayout.Alignment.LEADING, sourcePanelLayout.createSequentialGroup()
							        .addComponent(readonlySourceCheck, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							        .addComponent(sourceNavigableCheck, 0, 108, Short.MAX_VALUE)))
							.addContainerGap());
						sourcePanelLayout.setVerticalGroup(sourcePanelLayout.createSequentialGroup()
							.addGroup(sourcePanelLayout.createParallelGroup()
							    .addComponent(multiplicitySourceCombo, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addGroup(GroupLayout.Alignment.LEADING, sourcePanelLayout.createSequentialGroup()
							        .addComponent(multiplicitySourceLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							        .addGap(7)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(sourcePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(sourceRoleText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(sourceRoleLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(sourcePanelLayout.createParallelGroup()
							    .addComponent(readonlySourceCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(sourceNavigableCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 15, Short.MAX_VALUE)
							.addComponent(srcPropAnttButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
					}
					{
						targetPanel = new JPanel();
						GroupLayout jPanel1Layout1 = new GroupLayout((JComponent)targetPanel);
						targetPanel.setBorder(BorderFactory.createTitledBorder(
								ApplicationResources.getInstance().getString("dialog.assoc.target.title")+ 
								" (" + getAssociation().getOwnedEnd().get(1).getType().getName() + ")"));
						targetPanel.setLayout(jPanel1Layout1);
						{
							multiplicityTargetLabel = new JLabel();
							multiplicityTargetLabel.setText(ApplicationResources.getInstance().getString("dialog.assoc.multiplicity"));
						}
						{
							multiplicityTargetCombo = new JComboBox();
							multiplicityTargetCombo.setModel(getDefaultMultiplicitiesModel(null));
							multiplicityTargetCombo.setEditable(true);
						}
						{
							readonlyTargetCheck = new JCheckBox();
							readonlyTargetCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.readonly"));
						}
						{
							targetRoleLabel = new JLabel();
							targetRoleLabel.setText("Role");
						}
						{
							targetRoleText = new JTextField();
						}
						{
							targetNavigableCheck = new JCheckBox();
							targetNavigableCheck.setText("Navigable");
						}
						{
							showRolesCheck = new JCheckBox();
							showRolesCheck.setText("Show roles");
						}
						jPanel1Layout1.setHorizontalGroup(jPanel1Layout1.createSequentialGroup()
							.addContainerGap()
							.addGroup(jPanel1Layout1.createParallelGroup()
							    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout1.createSequentialGroup()
							        .addComponent(readonlyTargetCheck, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							        .addGap(19)
							        .addComponent(targetNavigableCheck, 0, 111, Short.MAX_VALUE))
							    .addGroup(jPanel1Layout1.createSequentialGroup()
							        .addGroup(jPanel1Layout1.createParallelGroup()
							            .addComponent(multiplicityTargetLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							            .addComponent(targetRoleLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout1.createSequentialGroup()
							                .addComponent(trgPropAnttButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							                .addGap(51)))
							        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							        .addGroup(jPanel1Layout1.createParallelGroup()
							            .addComponent(multiplicityTargetCombo, GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE)
							            .addComponent(targetRoleText, GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE))))
							.addContainerGap());
						jPanel1Layout1.setVerticalGroup(jPanel1Layout1.createSequentialGroup()
							.addGroup(jPanel1Layout1.createParallelGroup()
							    .addComponent(multiplicityTargetCombo, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout1.createSequentialGroup()
							        .addComponent(multiplicityTargetLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							        .addGap(7)))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(jPanel1Layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(targetRoleText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(targetRoleLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(jPanel1Layout1.createParallelGroup()
							    .addComponent(readonlyTargetCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(targetNavigableCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 0, Short.MAX_VALUE)
							.addComponent(trgPropAnttButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
					}
					associationPropertiesPanelLayout.setHorizontalGroup(associationPropertiesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(associationPropertiesPanelLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, associationPropertiesPanelLayout.createSequentialGroup()
						        .addComponent(associatioNameLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(associationNameText, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(assocAnttButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(nameVisibilityCheck, 0, 114, Short.MAX_VALUE)
						        .addGap(6))
						    .addGroup(associationPropertiesPanelLayout.createSequentialGroup()
						        .addGroup(associationPropertiesPanelLayout.createParallelGroup()
						            .addComponent(sourcePanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						            .addComponent(showMultiplicityCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addGroup(associationPropertiesPanelLayout.createParallelGroup()
						            .addGroup(associationPropertiesPanelLayout.createSequentialGroup()
						                .addComponent(targetPanel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
						            .addGroup(associationPropertiesPanelLayout.createSequentialGroup()
						                .addComponent(showRolesCheck, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)))
						        .addGap(0, 10, Short.MAX_VALUE))
						    .addComponent(nameDirectionPanel, GroupLayout.Alignment.LEADING, 0, 565, Short.MAX_VALUE))
						.addContainerGap());
					associationPropertiesPanelLayout.setVerticalGroup(associationPropertiesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(associationPropertiesPanelLayout.createParallelGroup()
						    .addComponent(associationNameText, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(assocAnttButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, associationPropertiesPanelLayout.createSequentialGroup()
						        .addComponent(nameVisibilityCheck, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						        .addGap(6))
						    .addGroup(GroupLayout.Alignment.LEADING, associationPropertiesPanelLayout.createSequentialGroup()
						        .addGap(8)
						        .addComponent(associatioNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addGap(15)))
						.addGap(0, 17, Short.MAX_VALUE)
						.addComponent(nameDirectionPanel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(associationPropertiesPanelLayout.createParallelGroup()
						    .addComponent(showRolesCheck, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addGroup(associationPropertiesPanelLayout.createSequentialGroup()
						        .addComponent(showMultiplicityCheck, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(associationPropertiesPanelLayout.createParallelGroup()
						    .addComponent(targetPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						    .addComponent(sourcePanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addContainerGap());
				}
				{
					meronymicPropertiesPanel = new JPanel();
					GroupLayout meronymicPropertiesPanelLayout = new GroupLayout((JComponent)meronymicPropertiesPanel);
					meronymicPropertiesPanel.setLayout(meronymicPropertiesPanelLayout);
					contentTabbedPane.addTab(ApplicationResources.getInstance().getString("dialog.assoc.meronymic"), null, meronymicPropertiesPanel, null);
					{
						wholePanel = new JPanel();
						GroupLayout wholePanelLayout = new GroupLayout((JComponent)wholePanel);
						wholePanel.setLayout(wholePanelLayout);
						wholePanel.setBorder(BorderFactory.createTitledBorder(
								ApplicationResources.getInstance().getString("dialog.assoc.whole") + 
								" (" + getAssociation().getOwnedEnd().get(0).getType().getName() + ")"));
						{
							immutableWholeCheck = new JCheckBox();
							immutableWholeCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.immutablewhole"));
						}
							wholePanelLayout.setHorizontalGroup(wholePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(immutableWholeCheck, 0, 233, Short.MAX_VALUE)
							.addContainerGap());
							wholePanelLayout.setVerticalGroup(wholePanelLayout.createSequentialGroup()
							.addComponent(immutableWholeCheck, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE));
					}
					{
						partPanel = new JPanel();
						GroupLayout partPanelLayout = new GroupLayout((JComponent)partPanel);
						partPanel.setLayout(partPanelLayout);
						partPanel.setBorder(BorderFactory.createTitledBorder(
								ApplicationResources.getInstance().getString("dialog.assoc.part") + 
								" (" + getAssociation().getOwnedEnd().get(1).getType().getName() + ")"));
						{
							immutablePartCheck = new JCheckBox();
							immutablePartCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.immutablepart"));
						}
							partPanelLayout.setHorizontalGroup(partPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(immutablePartCheck, 0, 250, Short.MAX_VALUE)
							.addContainerGap());
							partPanelLayout.setVerticalGroup(partPanelLayout.createSequentialGroup()
							.addComponent(immutablePartCheck, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE));
					}
					{
						meronymicAssociationPanel = new JPanel();
						GroupLayout jPanel1Layout = new GroupLayout((JComponent)meronymicAssociationPanel);
						meronymicAssociationPanel.setLayout(jPanel1Layout);
						meronymicAssociationPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.assoc.association")));
						{
							shareableCheck = new JCheckBox();
							shareableCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.shareable"));
						}
						{
							essentialCheck = new JCheckBox();
							essentialCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.essential"));
						}
						{
							inseparableCheck = new JCheckBox();
							inseparableCheck.setText(ApplicationResources.getInstance().getString("dialog.assoc.inseparable"));
						}
						jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap(39, 39)
							.addComponent(shareableCheck, 0, 158, Short.MAX_VALUE)
							.addGap(23)
							.addComponent(essentialCheck, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(inseparableCheck, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
						jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
							.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(shareableCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(essentialCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							    .addComponent(inseparableCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap());
					}
					meronymicPropertiesPanelLayout.setHorizontalGroup(meronymicPropertiesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(meronymicPropertiesPanelLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, meronymicPropertiesPanelLayout.createSequentialGroup()
						        .addComponent(wholePanel, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(partPanel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE))
						    .addComponent(meronymicAssociationPanel, GroupLayout.Alignment.LEADING, 0, 555, Short.MAX_VALUE))
						.addContainerGap());
					meronymicPropertiesPanelLayout.setVerticalGroup(meronymicPropertiesPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(meronymicAssociationPanel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(meronymicPropertiesPanelLayout.createParallelGroup()
						    .addComponent(wholePanel, GroupLayout.Alignment.LEADING, 0, 55, Short.MAX_VALUE)
						    .addComponent(partPanel, GroupLayout.Alignment.LEADING, 0, 55, Short.MAX_VALUE))
						.addContainerGap(126, 126));
				}
			}
			this.setSize(600, 398);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Post-Initialization code.
	 */
	@SuppressWarnings("unchecked")
	private void myPostInit() {
		
		associationNameText.setText(getAssociation().getName());
		showMultiplicityCheck.setSelected(associationElement.showMultiplicities());
		showRolesCheck.setSelected(associationElement.showRoles());
		nameVisibilityCheck.setSelected(associationElement.showName());

		switch (associationElement.getNameReadingDirection()) {
		case LEFT_RIGHT:
			l2rRadio.setSelected(true);
			break;
		case RIGHT_LEFT:
			r2lRadio.setSelected(true);
			break;
		default:
			noneRadio.setSelected(true);
			break;
		}
		
		if(getAssociation() instanceof MeronymicImpl == false)
		{
			contentTabbedPane.setEnabledAt(1, false);
		}
		else
		{
			Meronymic meronymic = (Meronymic) getAssociation(); 
			shareableCheck.setSelected(meronymic.isIsShareable());
			essentialCheck.setSelected(meronymic.isIsEssential());
			inseparableCheck.setSelected(meronymic.isIsInseparable());
			immutableWholeCheck.setSelected(meronymic.isIsImmutableWhole());
			immutablePartCheck.setSelected(meronymic.isIsImmutablePart());
			
			if(meronymic instanceof subQuantityOfImpl)
			{
				//If the association is a SubQuantityOf, it is always non-shareable
				shareableCheck.setEnabled(false);
			}
		}
		
		if(getAssociation() instanceof CharacterizationImpl || getAssociation() instanceof MediationImpl)
		{
			readonlySourceCheck.setEnabled(false);
			readonlyTargetCheck.setEnabled(false);
		}
		
		Property sourceEnd = getAssociation().getOwnedEnd().get(0);
		Property targetEnd = getAssociation().getOwnedEnd().get(1);
		
		String sourceMultiplicity = ModelHelper.getMultiplicityString(sourceEnd);
		multiplicitySourceCombo.setModel(getDefaultMultiplicitiesModel(sourceMultiplicity));
		
		String targetMultiplicity = ModelHelper.getMultiplicityString(targetEnd);
		multiplicityTargetCombo.setModel(getDefaultMultiplicitiesModel(targetMultiplicity));
		
		readonlySourceCheck.setSelected(sourceEnd.isIsReadOnly());
		readonlyTargetCheck.setSelected(targetEnd.isIsReadOnly());
		
		boolean sourceNavigable = getAssociation().getNavigableOwnedEnd().contains(sourceEnd);
		sourceNavigableCheck.setSelected(sourceNavigable);
		
		boolean targetNavigable = getAssociation().getNavigableOwnedEnd().contains(targetEnd);
		targetNavigableCheck.setSelected(targetNavigable);
		
		sourceRoleText.setText(sourceEnd.getName());
		targetRoleText.setText(targetEnd.getName());
		
		getRootPane().setDefaultButton(okButton);
		
		setLocationRelativeTo(null);
	}
	
	private ButtonGroup getNameDirectionGroup() {
		if(nameDirectionGroup == null) {
			nameDirectionGroup = new ButtonGroup();
		}
		return nameDirectionGroup;
	}
	
	private void setReadingDirection(AssociationElement association) {
		if (l2rRadio.isSelected()) {
			association.setNameReadingDirection(ReadingDirection.LEFT_RIGHT);
		} else if (r2lRadio.isSelected()) {
			association.setNameReadingDirection(ReadingDirection.RIGHT_LEFT);
		} else {
			association.setNameReadingDirection(ReadingDirection.UNDEFINED);
		}
	}
	
	/**
	 * Validates the input fields.
	 * 
	 * @return true if everything was ok, false otherwise
	 */
	private boolean validateInput() {
		if (!verifyMultiplicity(multiplicitySourceCombo)) {
			multiplicitySourceCombo.getEditor().selectAll();
			multiplicitySourceCombo.requestFocusInWindow();
			return false;
		}
		if (!verifyMultiplicity(multiplicityTargetCombo)) {
			multiplicityTargetCombo.getEditor().selectAll();
			multiplicityTargetCombo.requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	/**
	 * Verifies the specified combobox if the selected item contains a valid
	 * Multiplicity string.
	 * 
	 * @param combobox
	 *            the JComboBox
	 * @return true if valid, false otherwise
	 */
	private boolean verifyMultiplicity(@SuppressWarnings("rawtypes") JComboBox combobox) {
		try {
			ModelHelper.setMultiplicityFromString(null, combobox.getSelectedItem().toString());
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}
		
	/**
	 * Transfers the data to the associationElement object.
	 */
	private void transferDataToAssociation() {
		
		if(associationNameText.getText().trim().length() > 0)
			getAssociation().setName(associationNameText.getText().trim());
		else
			getAssociation().setName(null);
		associationElement.setShowName(nameVisibilityCheck.isSelected());
		setReadingDirection(associationElement);
		associationElement.setShowMultiplicities(showMultiplicityCheck.isSelected());
		associationElement.setShowRoles(showRolesCheck.isSelected());

		Property sourceEnd = getAssociation().getOwnedEnd().get(0);
		Property targetEnd = getAssociation().getOwnedEnd().get(1);
		
		try {
			ModelHelper.setMultiplicityFromString(sourceEnd, multiplicitySourceCombo.getSelectedItem().toString());
			ModelHelper.setMultiplicityFromString(targetEnd, multiplicityTargetCombo.getSelectedItem().toString());
		} catch (ParseException ex) {
			// do not catch it here, but in the verification
			ex.printStackTrace();
		}
		
		if(getAssociation() instanceof MeronymicImpl)
		{
			Meronymic meronymic = (Meronymic) getAssociation(); 
			meronymic.setIsShareable(shareableCheck.isSelected());
			meronymic.setIsEssential(essentialCheck.isSelected());
			meronymic.setIsInseparable(inseparableCheck.isSelected());
			meronymic.setIsImmutableWhole(immutableWholeCheck.isSelected());
			meronymic.setIsImmutablePart(immutablePartCheck.isSelected());
		}
		
		sourceEnd.setIsReadOnly(readonlySourceCheck.isSelected());
		targetEnd.setIsReadOnly(readonlyTargetCheck.isSelected());
		
		if(sourceNavigableCheck.isSelected())
		{
			getAssociation().getNavigableOwnedEnd().add(sourceEnd);	
		}
		else
		{
			getAssociation().getNavigableOwnedEnd().remove(sourceEnd);
		}
		
		if(targetNavigableCheck.isSelected())
		{
			getAssociation().getNavigableOwnedEnd().add(targetEnd);
		}
		else
		{
			getAssociation().getNavigableOwnedEnd().remove(targetEnd);
		}
		
		if(sourceRoleText.getText().trim().length() > 0)
			sourceEnd.setName(sourceRoleText.getText().trim());
		else
			sourceEnd.setName(null);
		
		if(targetRoleText.getText().trim().length() > 0)
			targetEnd.setName(targetRoleText.getText().trim());
		else
			targetEnd.setName(null);
	}

	private Association getAssociation() {
		return (Association) associationElement.getAssociation();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ComboBoxModel getDefaultMultiplicitiesModel(String multiplicity) {
		ComboBoxModel model = new DefaultComboBoxModel(new String[] { "0..1", "1", "*", "1..*" });
		if(multiplicity != null)
		{
			if(!multiplicity.equals("0..1") && !multiplicity.equals("1") && !multiplicity.equals("*") && !multiplicity.equals("1..*"))
			{
				model = new DefaultComboBoxModel(new String[] { "0..1", "1", "*", "1..*", multiplicity });
			}
			model.setSelectedItem(multiplicity);
		}
		return model; 
	}
}
