package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDirection;

public class AssociationEditionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private AssociationElement assocElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	private JTextField nameField;
	@SuppressWarnings("rawtypes")
	private JComboBox stereoCombo;
	private JCheckBox cbxAbstract;
	private JCheckBox cbxDerived;
	private JPanel assocPanel;
	private JCheckBox cbxEssential;		
	private JCheckBox cbxInseparable;	
	private JCheckBox cbxImmutablepart;
	private JCheckBox cbxImmutablewhole;
	private JCheckBox cbxShareable;
	private JPanel meronymicPanel;
	private JPanel directionPanel;
	private JRadioButton btnUndefined;	
	private JRadioButton btnToSource;
	private JRadioButton btnToDestination;
	private JPanel panel;
	private JCheckBox btnStereotype;
	private JCheckBox btnEndNames;
	private JCheckBox btnMultiplicity;
	private JCheckBox btnName;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public AssociationEditionPanel(final DiagramManager diagramManager, final AssociationElement assocElement, boolean modal) 
	{
		this.diagramManager = diagramManager;
		this.assocElement = assocElement;
		this.element = (Classifier)assocElement.getRelationship();
		
		assocPanel = new JPanel();
		assocPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		meronymicPanel = new JPanel();
		meronymicPanel.setBorder(BorderFactory.createTitledBorder("Meronymic"));
		
		directionPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) directionPanel.getLayout();
		directionPanel.setBorder(BorderFactory.createTitledBorder("Reading Direction"));
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBorder(BorderFactory.createTitledBorder("Visibility"));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(meronymicPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(directionPanel, 0, 0, Short.MAX_VALUE))
							.addComponent(assocPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(assocPanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(directionPanel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(meronymicPanel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		
		btnName = new JCheckBox("Name");
		panel.add(btnName);
		btnName.setPreferredSize(new Dimension(80, 25));
		
		btnStereotype = new JCheckBox("Stereotype");
		panel.add(btnStereotype);
		btnStereotype.setPreferredSize(new Dimension(80, 25));
		
		btnEndNames = new JCheckBox("End names");
		panel.add(btnEndNames);
		btnEndNames.setPreferredSize(new Dimension(80, 25));
		
		btnMultiplicity = new JCheckBox("Multiplicity");
		panel.add(btnMultiplicity);
		btnMultiplicity.setPreferredSize(new Dimension(80, 25));
		
		ButtonGroup group = new ButtonGroup();
		
		btnToSource = new JRadioButton("To source");
		directionPanel.add(btnToSource);
		group.add(btnToSource);
		btnToSource.setPreferredSize(new Dimension(80, 25));
		
		btnToDestination = new JRadioButton("To destination");
		directionPanel.add(btnToDestination);
		group.add(btnToDestination);
		btnToDestination.setPreferredSize(new Dimension(100, 25));
		
		btnUndefined = new JRadioButton("Undefined");
		directionPanel.add(btnUndefined);
		group.add(btnUndefined);
		btnUndefined.setPreferredSize(new Dimension(182, 25));
		
		cbxEssential = new JCheckBox("Essential");		
		cbxEssential.setPreferredSize(new Dimension(85, 20));
		meronymicPanel.add(cbxEssential);
		cbxImmutablepart = new JCheckBox("ImmutablePart");
		cbxImmutablepart.setPreferredSize(new Dimension(310, 20));
		meronymicPanel.add(cbxImmutablepart);
		
		cbxInseparable = new JCheckBox("Inseparable");
		meronymicPanel.add(cbxInseparable);
		cbxInseparable.setPreferredSize(new Dimension(85, 20));
		cbxImmutablewhole = new JCheckBox("ImmutableWhole");
		meronymicPanel.add(cbxImmutablewhole);
		cbxImmutablewhole.setPreferredSize(new Dimension(310, 20));
		cbxShareable = new JCheckBox("Shareable");
		cbxShareable.setPreferredSize(new Dimension(398, 20));
		
		meronymicPanel.add(cbxShareable);
		// Inseparable implies in Immutable Part...
		cbxInseparable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbxInseparable.isSelected()) cbxImmutablewhole.setSelected(true);								
			}
		});
		
		// Essential implies in Immutable Part...
		cbxEssential.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbxEssential.isSelected()) cbxImmutablepart.setSelected(true);								
			}
		});
		
		JLabel lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
				
		// If whole is anti-rigid then Essential is not a possible choice
		// If part is anti-rigid then Inseparable is not a possible choice
		if (element instanceof Meronymic){
			Classifier whole = ((Meronymic)element).whole();
			Classifier part = ((Meronymic)element).part();
			if (whole instanceof AntiRigidSortalClass || whole instanceof AntiRigidMixinClass) {
				cbxEssential.setSelected(false);
				cbxEssential.setEnabled(false);
			}
			if (part instanceof AntiRigidSortalClass || whole instanceof AntiRigidMixinClass) {
				cbxInseparable.setSelected(false);
				cbxInseparable.setEnabled(false);
			}
		}
		
		JLabel lblStereo = new JLabel("Stereo:");
		
		stereoCombo = new JComboBox();
		stereoCombo.setModel(new DefaultComboBoxModel(new String[] {"Mediation", "componentOf", "memberOf", "subCollectionOf", "subQuantityOf", "Material", "Characterization", "Formal", "Association", "Derivation"}));
		stereoCombo.setEnabled(false);
		
		cbxAbstract = new JCheckBox("Abstract");		
		cbxDerived = new JCheckBox("Derived");
				
		GroupLayout gl_assocPanel = new GroupLayout(assocPanel);
		gl_assocPanel.setHorizontalGroup(
			gl_assocPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assocPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblStereo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_assocPanel.createSequentialGroup()
							.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxAbstract)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxDerived))
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
					.addGap(12))
		);
		gl_assocPanel.setVerticalGroup(
			gl_assocPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assocPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbxAbstract, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cbxDerived, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		assocPanel.setLayout(gl_assocPanel);
		setLayout(groupLayout);
		
		setInitialData();
			
	}
	
	public void setMeronymicPanelVisible(boolean value)
	{		
		meronymicPanel.setVisible(value);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void setInitialData()
	{		
		nameField.setText(element.getName());		
		cbxAbstract.setSelected(element.isIsAbstract());
		if (element instanceof Association) { cbxDerived.setSelected(((Association)element).isIsDerived()); cbxDerived.setEnabled(true); }
		else { cbxDerived.setSelected(false); cbxDerived.setEnabled(false); }
		stereoCombo.setSelectedItem(getStereotype(element).trim());
		stereoCombo.setEnabled(false);
		
		if (element instanceof Meronymic){
			Meronymic m = (Meronymic)element;
			cbxEssential.setSelected(m.isIsEssential());
			cbxInseparable.setSelected(m.isIsInseparable());
			cbxImmutablepart.setSelected(m.isIsImmutablePart());
			cbxImmutablewhole.setSelected(m.isIsImmutableWhole());
			cbxShareable.setSelected(m.isIsShareable());
			setMeronymicPanelVisible(true);		
			repaint();
			validate();
		}else{
			cbxEssential.setSelected(false);
			cbxInseparable.setSelected(false);
			cbxImmutablepart.setSelected(false);
			cbxImmutablewhole.setSelected(false);
			cbxShareable.setSelected(false);
			setMeronymicPanelVisible(false);
			repaint();
			validate();
		}
		
		if (element instanceof subQuantityOf){
			cbxShareable.setEnabled(false);
		}
		ReadingDirection direction = assocElement.getNameReadingDirection();
		if (direction.equals(ReadingDirection.LEFT_RIGHT)) btnToDestination.setSelected(true);
		else if (direction.equals(ReadingDirection.RIGHT_LEFT)) btnToSource.setSelected(true);
		else btnUndefined.setSelected(true);
		
		btnName.setSelected(assocElement.showName());
		btnMultiplicity.setSelected(assocElement.showMultiplicities());
		btnEndNames.setSelected(assocElement.showRoles());
		btnStereotype.setSelected(assocElement.showOntoUmlStereotype());		
	}	
	
	public void transferAssocData()
	{
		element.setName(nameField.getText());
		element.setIsAbstract(cbxAbstract.isSelected());
		((Association)element).setIsDerived(cbxDerived.isSelected());
				
		assocElement.setShowMultiplicities(btnMultiplicity.isSelected());
		assocElement.setShowRoles(btnEndNames.isSelected());
		assocElement.setShowOntoUmlStereotype(btnStereotype.isSelected());
		assocElement.setShowName(btnName.isSelected());
		
		if (btnToDestination.isSelected()) assocElement.setNameReadingDirection(ReadingDirection.LEFT_RIGHT);
		else if (btnToSource.isSelected()) assocElement.setNameReadingDirection(ReadingDirection.RIGHT_LEFT);
		else assocElement.setNameReadingDirection(ReadingDirection.UNDEFINED);
		
		diagramManager.updateOLEDFromModification(element,false);
	}
}

