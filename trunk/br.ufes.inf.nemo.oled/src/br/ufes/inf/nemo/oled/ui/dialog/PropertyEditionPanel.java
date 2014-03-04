package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.OntoUMLElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

public class PropertyEditionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Component parent;
	
	@SuppressWarnings("unused")
	private DiagramElement ownerDiagramElement;	
	private Classifier ownerElement;
	private Property property;
	private DiagramManager diagramManager;
	
	private JTextField nameField;
	private JLabel lblMultiplicity;
	@SuppressWarnings("rawtypes")
	private JComboBox multCombo;
	private JLabel lblAggregationKind;
	@SuppressWarnings("rawtypes")
	private JComboBox aggregCombo;
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
	JPanel mainPanel;
	JPanel optionsPanel;
	JPanel listPanel;
	JPanel AggregPanel;
	JPanel multPanel;
	private JTextField subsettedText;
	private JTextField redefinedText;
	
	public PropertyEditionPanel(JDialog parent, final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
	{
		this.parent=parent;
		initData(diagramManager,ownerDiagramElement,ownerElem,property);
		initGUI();		
	}
	
	/**
	 * @wbp.parser.constructor 
	 */
	public PropertyEditionPanel(JFrame parent, final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
	{
		this.parent=parent;
		initData(diagramManager,ownerDiagramElement,ownerElem,property);
		initGUI();		
	}
		
	public void initData(final DiagramManager diagramManager, DiagramElement ownerDiagramElement, RefOntoUML.Classifier ownerElem, final Property property)
	{
		this.diagramManager = diagramManager;
		this.property = property;
		this.ownerDiagramElement = ownerDiagramElement;
		this.ownerElement = ownerElem;		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI () 
	{	
		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		listPanel = new JPanel();
		listPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		AggregPanel = new JPanel();
		AggregPanel.setBorder(BorderFactory.createTitledBorder(""));
		
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
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(listPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(mainPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(multPanel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(AggregPanel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(AggregPanel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(multPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(optionsPanel, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listPanel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
		);
		
		lblAggregationKind = new JLabel("(Aggregation Kind)");
		AggregPanel.add(lblAggregationKind);
		lblAggregationKind.setHorizontalAlignment(SwingConstants.CENTER);
		
		aggregCombo = new JComboBox();
		aggregCombo.setModel(new DefaultComboBoxModel(new String[] {"none", "shared", "composite"}));
		AggregPanel.add(aggregCombo);
		aggregCombo.setPreferredSize(new Dimension(80, 20));
		
		btnSubsetted = new JButton("");
		btnSubsetted.setPreferredSize(new Dimension(30, 25));
		btnSubsetted.setIcon(new ImageIcon(PropertyEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnSubsetted.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (parent instanceof JFrame)
					FeatureListDialog.open((JFrame)parent,subsettedText, "Subsetted", property, ProjectBrowser.getParserFor(diagramManager.getCurrentProject()));
				else
					FeatureListDialog.open((JDialog)parent,subsettedText, "Subsetted", property, ProjectBrowser.getParserFor(diagramManager.getCurrentProject()));
			}
		});
		
		subsettedText = new JTextField();
		subsettedText.setEditable(false);
		subsettedText.setColumns(10);
		subsettedText.setPreferredSize(new Dimension(300, 20));
		
		redefinedText = new JTextField();
		redefinedText.setEditable(false);
		redefinedText.setColumns(10);
		redefinedText.setSize(new Dimension(100, 50));
		
		btnRedefined = new JButton("");
		btnRedefined.setPreferredSize(new Dimension(30, 25));
		btnRedefined.setIcon(new ImageIcon(PropertyEditionPanel.class.getResource("/resources/icons/x16/pencil.png")));
		btnRedefined.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (parent instanceof JFrame)
					FeatureListDialog.open((JFrame)parent,redefinedText, "Redefined", property, ProjectBrowser.getParserFor(diagramManager.getCurrentProject()));
				else
					FeatureListDialog.open((JDialog)parent,redefinedText, "Redefined", property, ProjectBrowser.getParserFor(diagramManager.getCurrentProject()));
			}
		});
		
		JLabel lblSubsetted = new JLabel("Subsetted:");
		
		JLabel lblRedefined = new JLabel("Redefined:");
		
		GroupLayout gl_listPanel = new GroupLayout(listPanel);
		gl_listPanel.setHorizontalGroup(
			gl_listPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_listPanel.createSequentialGroup()
							.addComponent(lblSubsetted)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(subsettedText, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSubsetted, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_listPanel.createSequentialGroup()
							.addComponent(lblRedefined)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(redefinedText, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRedefined, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_listPanel.setVerticalGroup(
			gl_listPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_listPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_listPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(subsettedText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSubsetted))
						.addComponent(btnSubsetted, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_listPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_listPanel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_listPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(redefinedText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRedefined)))
						.addGroup(gl_listPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRedefined, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		listPanel.setLayout(gl_listPanel);
		
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
		typeCombo.setFocusable(false);
		
		if (property.getType()!=null){
			typeCombo.setModel(new DefaultComboBoxModel(new String[] {property.getType().getName()}));
		}
		
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
		
		setPreferredSize(new Dimension(450, 246));
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setInitialData()
	{
		nameField.setText(property.getName());
		cbxDerived.setSelected(property.isIsDerived());
		cbxOrdered.setSelected(property.isIsOrdered());
		cbxReadOnly.setSelected(property.isIsReadOnly());
		cbxUnique.setSelected(property.isIsUnique());
		aggregCombo.setSelectedItem(property.getAggregation().getName());
		
		if (ownerElement instanceof Mediation || ownerElement instanceof Characterization){
			if (property.equals(((Association)ownerElement).getMemberEnd().get(0))) cbxReadOnly.setEnabled(true);
			else cbxReadOnly.setEnabled(false);
		}		
		
		if (ownerElement instanceof Meronymic){
			aggregCombo.setEnabled(true);
		}else{
			aggregCombo.setEnabled(false);			
		}
		
		String multiplicity = new String();
		if (property.getLower()==property.getUpper() && property.getUpper()!=-1) multiplicity = Integer.toString(property.getLower());
		else if (property.getLower()==property.getUpper() && property.getUpper()==-1) multiplicity = "*";
		else if (property.getUpper()==-1) multiplicity = property.getLower()+".."+"*";
		else multiplicity = property.getLower()+".."+property.getUpper();
		
		if(multiplicity.compareToIgnoreCase("1")==0) multCombo.setSelectedIndex(0);
		else if(multiplicity.compareToIgnoreCase("0..1")==0)multCombo.setSelectedIndex(1);
		else if(multiplicity.compareToIgnoreCase("0..*")==0)multCombo.setSelectedIndex(2);
		else if(multiplicity.compareToIgnoreCase("1..*")==0) multCombo.setSelectedIndex(3);
		else { multCombo.setSelectedItem(multiplicity); }
		
		String str = new String();
    	int i=0;    	
    	for(Property p: property.getSubsettedProperty())
    	{    		
    		if (i==property.getSubsettedProperty().size()-1) str += "<"+getStereotype(p)+"> "+p.getName()+": "+p.getType().getName()+"";
    		else str += "<"+getStereotype(p)+"> "+p.getName()+": "+p.getType().getName()+", ";    		
    		i++;
    	}	
		subsettedText.setText(str);
		
		str = new String();
    	i=0;    	
    	for(Property p: property.getRedefinedProperty())
    	{    		
    		if (i==property.getRedefinedProperty().size()-1) str += "<"+getStereotype(p)+"> "+p.getName()+": "+p.getType().getName()+"";
    		else str += "<"+getStereotype(p)+"> "+p.getName()+": "+p.getType().getName()+", ";    		
    		i++;
    	}	
		redefinedText.setText(str);		
		
		ArrayList<OntoUMLElement> list = new ArrayList<OntoUMLElement>();
		OntoUMLElement value = null;
		OntoUMLParser refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
		if (property.getType()!=null) value = new OntoUMLElement(property.getType(),"");
		else value = new OntoUMLElement(null,"");			    	
    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
    	{
			if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType || t instanceof RefOntoUML.Association)
			{
				if (((OntoUMLElement) value).getElement()!=null && t.equals(((OntoUMLElement) value).getElement())) list.add((OntoUMLElement)value);				
    			else list.add(new OntoUMLElement(t,""));	    			
    		}	    					
    	}
    	if (((OntoUMLElement) value).getElement()==null) list.add((OntoUMLElement)value);
    	else if (!refparser.getAllInstances(RefOntoUML.Type.class).contains(property.getType())) list.add((OntoUMLElement)value);    	
    	Collections.sort(list,new CustomComparator());	    	
    	typeCombo.setModel(new DefaultComboBoxModel(list.toArray()));
    	typeCombo.setSelectedItem(value);    	
	}
	
	public class CustomComparator implements Comparator<OntoUMLElement> 
    {
        @Override
        public int compare(OntoUMLElement o1, OntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
	 
	public void transferPropertyData() 
	{
		boolean redesign = false;
		
		try{			
			property.setName(nameField.getText());
			property.setIsDerived(cbxDerived.isSelected());
			if(cbxDerived.isSelected()) nameField.setText(nameField.getText().replace("/",""));
			nameField.repaint();
			nameField.validate();
			property.setIsOrdered(cbxOrdered.isSelected());
			property.setIsReadOnly(cbxReadOnly.isSelected());
			property.setIsUnique(cbxUnique.isSelected());
			if(((String)aggregCombo.getSelectedItem()).compareToIgnoreCase("shared")==0) property.setAggregation(AggregationKind.SHARED);
			else if (((String)aggregCombo.getSelectedItem()).compareToIgnoreCase("composite")==0) property.setAggregation(AggregationKind.COMPOSITE);
			else property.setAggregation(AggregationKind.NONE);			
			ModelHelper.setMultiplicityFromString(property, (String)multCombo.getSelectedItem());
			RefOntoUML.Type type = (RefOntoUML.Type)((OntoUMLElement)typeCombo.getSelectedItem()).getElement();			
			if (type!=null && !type.equals(property.getType())) redesign = true;
			property.setType(type);

		}catch(Exception e){
			diagramManager.getFrame().showErrorMessageDialog("Transfering data to property", e.getLocalizedMessage());
		}
					
		diagramManager.updateOLEDFromModification(ownerElement, redesign);
	}
}
