package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.text.Normalizer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import javax.swing.DefaultComboBoxModel;

import org.eclipse.emf.ecore.EObject;

public class PropertyEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private DiagramElement ownerDiagramElement;	
	@SuppressWarnings("unused")
	private Classifier ownerElement;
	private Property property;
	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	
	private JTextField nameField;
	private JLabel lblMultiplicity;
	@SuppressWarnings("rawtypes")
	private JComboBox multCombo;
	private JLabel lblAggregationKind;
	@SuppressWarnings("rawtypes")
	private JComboBox aggregCombo;
	private JLabel lblSubsetted;
	private JLabel lblRedefined;
	private JButton btnRedefined;
	private JButton btnSubsetted;
	private JCheckBox cbxDerived;
	private JCheckBox cbxUnique;
	private JCheckBox cbxOrdered;
	private JCheckBox cbxReadOnly;
	@SuppressWarnings("rawtypes")
	private JComboBox typeCombo;
	private JLabel lblName;
	private JLabel lblType;
	@SuppressWarnings("rawtypes")
	private JList subsettedList;
	@SuppressWarnings("rawtypes")
	private JList redefinedList;
	private JScrollPane scrollSubsetted;
	private JScrollPane scrollRedefined;
	private JPanel redefinedPanel;
	JPanel mainPanel;
	JPanel optionsPanel;
	JPanel subsettedPanel;
	JPanel AggregPanel;
	JPanel multPanel;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PropertyEditionPanel(DiagramManager diagramManager, DiagramElement ownerDiagramElement, Property property, boolean modal) 
	{
		this.diagramManager = diagramManager;
		this.property = property;
		this.ownerDiagramElement = ownerDiagramElement;
		if (ownerDiagramElement instanceof AssociationElement) this.ownerElement = (Classifier)((AssociationElement)ownerDiagramElement).getRelationship();
		else if (ownerDiagramElement instanceof ClassElement) this.ownerElement = ((ClassElement)ownerDiagramElement).getClassifier();
		
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		subsettedPanel = new JPanel();
		subsettedPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		AggregPanel = new JPanel();
		AggregPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		redefinedPanel = new JPanel();
		redefinedPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		multPanel = new JPanel();
		multPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		lblMultiplicity = new JLabel("(Multiplicity)");
		
		lblMultiplicity.setHorizontalAlignment(SwingConstants.CENTER);
		multPanel.add(lblMultiplicity);
		
		multCombo = new JComboBox();
		multCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "0..1", "0..*", "1..*"}));
		multPanel.add(multCombo);
		multCombo.setPreferredSize(new Dimension(80, 20));
		multCombo.setEditable(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(subsettedPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(optionsPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(multPanel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(AggregPanel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addComponent(redefinedPanel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
					.addGap(364))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(AggregPanel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(multPanel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(redefinedPanel, 0, 0, Short.MAX_VALUE)
						.addComponent(subsettedPanel, GroupLayout.PREFERRED_SIZE, 157, Short.MAX_VALUE))
					.addGap(84))
		);
		
		lblRedefined = new JLabel("Redefined:");
		
		redefinedList = new JList();
		scrollRedefined = new JScrollPane();	
		scrollRedefined.setViewportView(redefinedList);
		
		btnRedefined = new JButton("Browse");
		
		GroupLayout gl_redefinedPanel = new GroupLayout(redefinedPanel);
		gl_redefinedPanel.setHorizontalGroup(
			gl_redefinedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_redefinedPanel.createSequentialGroup()
					.addGroup(gl_redefinedPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_redefinedPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRedefined, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_redefinedPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_redefinedPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRedefined)
								.addComponent(scrollRedefined, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_redefinedPanel.setVerticalGroup(
			gl_redefinedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_redefinedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRedefined)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollRedefined, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRedefined)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		redefinedPanel.setLayout(gl_redefinedPanel);
		
		lblAggregationKind = new JLabel("(Aggregation Kind)");
		AggregPanel.add(lblAggregationKind);
		lblAggregationKind.setHorizontalAlignment(SwingConstants.CENTER);
		
		aggregCombo = new JComboBox();
		aggregCombo.setModel(new DefaultComboBoxModel(new String[] {"none", "shared", "composite"}));
		AggregPanel.add(aggregCombo);
		aggregCombo.setPreferredSize(new Dimension(80, 20));
		
		lblSubsetted = new JLabel("Subsetted:");
					
		subsettedList = new JList();
		scrollSubsetted = new JScrollPane();	
		scrollSubsetted.setViewportView(subsettedList);
		
		btnSubsetted = new JButton("Browse");
		
		GroupLayout gl_subsettedPanel = new GroupLayout(subsettedPanel);
		gl_subsettedPanel.setHorizontalGroup(
			gl_subsettedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subsettedPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_subsettedPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSubsetted)
						.addComponent(btnSubsetted)
						.addComponent(scrollSubsetted, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addGap(224))
		);
		gl_subsettedPanel.setVerticalGroup(
			gl_subsettedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subsettedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSubsetted, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollSubsetted, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubsetted)
					.addContainerGap())
		);
		subsettedPanel.setLayout(gl_subsettedPanel);
		
		cbxDerived = new JCheckBox("Derived");
		cbxDerived.setPreferredSize(new Dimension(70, 20));
		optionsPanel.add(cbxDerived);
		
		cbxUnique = new JCheckBox("Unique");
		cbxUnique.setPreferredSize(new Dimension(80, 20));
		optionsPanel.add(cbxUnique);
		
		cbxOrdered = new JCheckBox("Ordered");
		cbxOrdered.setPreferredSize(new Dimension(70, 20));
		optionsPanel.add(cbxOrdered);
		
		cbxReadOnly = new JCheckBox("Read Only");
		cbxReadOnly.setPreferredSize(new Dimension(80, 20));
		optionsPanel.add(cbxReadOnly);
		
		lblName = new JLabel("Name:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		lblType = new JLabel("Type:");
		
		typeCombo = new JComboBox();
		typeCombo.setEnabled(false);
		typeCombo.setModel(new DefaultComboBoxModel(new String[] {property.getType().getName()}));
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblType, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(nameField)
						.addComponent(typeCombo, 0, 324, Short.MAX_VALUE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(typeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		mainPanel.setLayout(gl_mainPanel);
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	//TODO: Fazer uma aba de comentarios aqui também
	public void setInitialData()
	{
		nameField.setText(property.getName());
		cbxDerived.setSelected(property.isIsDerived());
		cbxOrdered.setSelected(property.isIsOrdered());
		cbxReadOnly.setSelected(property.isIsReadOnly());
		cbxUnique.setSelected(property.isIsUnique());
		aggregCombo.setSelectedItem(property.getAggregation().getName());
	}
}
