package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
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

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.MixinClass;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class ClassEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private ClassElement classElement;
	private Classifier element;
	private DiagramManager diagramManager;
	
	private JCheckBox btnAbstract;		
	private JCheckBox btnExtensional;
	private JTextField nameField;
	private JPanel classPropPanel;
	private JLabel lblStereo;
	@SuppressWarnings("rawtypes")
	private JComboBox stereoCombo;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClassEditionPanel(final DiagramManager diagramManager, final ClassElement classElement, RefOntoUML.Classifier element) 
	{	
		this.diagramManager = diagramManager;
		this.classElement = classElement;
		this.element = element;
						
		classPropPanel = new JPanel();
		classPropPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(classPropPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(classPropPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		btnAbstract = new JCheckBox("Abstract");
		btnExtensional = new JCheckBox("Extensional");
		
		lblStereo = new JLabel("Stereo:");
		
		stereoCombo = new JComboBox();
		stereoCombo.setModel(new DefaultComboBoxModel(new String[] {"Kind", "SubKind", "Collective", "Quantity", "Role", "Phase", "Category", "Mixin", "RoleMixin", "Relator", "Mode", "DataType"}));
		
		GroupLayout gl_classPropPanel = new GroupLayout(classPropPanel);
		gl_classPropPanel.setHorizontalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_classPropPanel.createSequentialGroup()
							.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAbstract)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExtensional))
						.addComponent(nameField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_classPropPanel.setVerticalGroup(
			gl_classPropPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_classPropPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_classPropPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExtensional)
						.addComponent(btnAbstract)
						.addComponent(lblStereo)
						.addComponent(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		classPropPanel.setLayout(gl_classPropPanel);
		this.setLayout(groupLayout);		
		
		setInitialData();
		
		setSize(new Dimension(450, 103));
	}
	
	public void setInitialData()
	{		
		if (element instanceof MixinClass) btnAbstract.setEnabled(false);
		else btnAbstract.setEnabled(true);
		if (element instanceof Collective) btnExtensional.setEnabled(true);
		else btnExtensional.setEnabled(false);
		nameField.setText(element.getName());		
		btnAbstract.setSelected(element.isIsAbstract());
		stereoCombo.setSelectedItem(getStereotype(element).trim());
		stereoCombo.setEnabled(false);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void selectNameText()
	{
		nameField.selectAll();
	}
	
	public void transferClassData()
	{
		element.setName(nameField.getText());
		if (element instanceof Collective) ((Collective) element).setIsExtensional(btnExtensional.isSelected());
		element.setIsAbstract(btnAbstract.isSelected());
				
		diagramManager.updateOLEDFromModification(element,false);		
	}
}
