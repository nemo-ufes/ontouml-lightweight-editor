package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;

public class AssociationEditionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private AssociationElement assocElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	private JTextField nameField;
	@SuppressWarnings("rawtypes")
	private JComboBox stereoCombo;
	private JCheckBox cbxAbstract;
	private JCheckBox cbxDerived;
	private JPanel meronymicPanel;
	private JPanel assocPanel;
	private JCheckBox cbxEssential;		
	private JCheckBox cbxInseparable;	
	private JCheckBox cbxImmutablepart;
	private JCheckBox cbxImmutablewhole;
	private JCheckBox cbxShareable;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AssociationEditionPanel(final DiagramManager diagramManager, final AssociationElement assocElement, boolean modal) 
	{
		this.diagramManager = diagramManager;
		this.assocElement = assocElement;
		this.element = (Classifier)assocElement.getRelationship();
		
		meronymicPanel = new JPanel();
		meronymicPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		assocPanel = new JPanel();
		assocPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		cbxEssential = new JCheckBox("Essential");		
		cbxInseparable = new JCheckBox("Inseparable");		
		cbxImmutablepart = new JCheckBox("ImmutablePart");		
		cbxImmutablewhole = new JCheckBox("ImmutableWhole");		
		cbxShareable = new JCheckBox("Shareable");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(assocPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(meronymicPanel, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(assocPanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(meronymicPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);

		// Essential implies in Immutable Part...
		cbxEssential.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbxEssential.isSelected()) cbxImmutablepart.setSelected(true);								
			}
		});
		// Inseparable implies in Immutable Part...
		cbxInseparable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbxInseparable.isSelected()) cbxImmutablewhole.setSelected(true);								
			}
		});
				
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
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_assocPanel.createSequentialGroup()
							.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxAbstract)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxDerived)
							.addGap(0, 0, Short.MAX_VALUE)))
					.addContainerGap())
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
							.addComponent(cbxDerived, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(cbxAbstract, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		assocPanel.setLayout(gl_assocPanel);
		
		GroupLayout gl_meronymicPanel = new GroupLayout(meronymicPanel);
		gl_meronymicPanel.setHorizontalGroup(
			gl_meronymicPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_meronymicPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_meronymicPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxImmutablepart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxEssential, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_meronymicPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxImmutablewhole, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbxInseparable, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxShareable, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_meronymicPanel.setVerticalGroup(
			gl_meronymicPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_meronymicPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_meronymicPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxEssential)
						.addComponent(cbxInseparable)
						.addComponent(cbxShareable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_meronymicPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxImmutablepart)
						.addComponent(cbxImmutablewhole))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		meronymicPanel.setLayout(gl_meronymicPanel);
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
	}	
	
	public void transferAssocData()
	{
		element.setName(nameField.getText());
		element.setIsAbstract(cbxAbstract.isSelected());
		((Association)element).setIsDerived(cbxDerived.isSelected());
				
		diagramManager.updateOLEDFromModification(element);
	}
}
