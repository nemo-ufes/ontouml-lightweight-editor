package br.ufes.inf.nemo.move.util.ontoumlview;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.AggregationKind;

public class OntoUMLElemTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	
	//Class and DataType Panel
	private JPanel typePanel;
	private JTextField typeNameField;
	private JTextField typeAliasField;
	@SuppressWarnings("rawtypes")
	private JComboBox typeAbstractCombo;
	private JLabel typelblExtensional;
	@SuppressWarnings("rawtypes")
	private JComboBox typeExtensionalCombo;
	private JLabel typelblType;
	private JTextField typeTypeField;
		
	//Property Panel
	private JPanel propertyPanel;
	
	//Generalization Set Panel
	private JPanel genSetPanel;
	@SuppressWarnings("rawtypes")
	private JComboBox genSetCoveringCombo;
	@SuppressWarnings("rawtypes")
	private JComboBox genSetDisjointCombo;
	
	//Association Panel
	private JPanel assocPanel;
	private JLabel assoclblName;
	private JTextField assocNameField;
	private JLabel assoclblType;
	private JTextField assocTypeField;
	private JLabel assoclblAlias;
	private JTextField assocAliasField;
	private JLabel assoclblAbstract;
	private JLabel assoclblDerived;
	private JLabel assoclblEssential;
	@SuppressWarnings("rawtypes")
	private JComboBox assocAbstractCombo;
	@SuppressWarnings("rawtypes")
	private JComboBox assocDerivedCombo;
	@SuppressWarnings("rawtypes")
	private JComboBox assocEssentialCombo;
	private JLabel assoclblInseparable;
	@SuppressWarnings("rawtypes")
	private JComboBox assocInseparableCombo;
	private JLabel assoclblShareable;
	@SuppressWarnings("rawtypes")
	private JComboBox assocShareableCombo;
	private JLabel assoclblImmutablepart;
	@SuppressWarnings("rawtypes")
	private JComboBox assocImmutablePartCombo;
	private JLabel assoclblImmutablewhole;
	@SuppressWarnings("rawtypes")
	private JComboBox assocImmutableWholeCombo;
	private JTextField genSetNameField;
	private JLabel genSetlblDisjoint;
	@SuppressWarnings("rawtypes")
	private JComboBox genSetGeneralizationsCombo;
	private JLabel genSetlblGeneralizations;
	private JLabel propertylblName;
	private JTextField propertyNameField;
	private JLabel propertylblType;
	private JTextField propertyTypeField;
	private JLabel propertylblUpper;
	private JTextField propertyUpperField;
	private JLabel propertylblLower;
	private JTextField propertyLowerField;
	private JLabel propertylblReadonly;
	@SuppressWarnings("rawtypes")
	private JComboBox propertyReadOnlyCombo;
	private JLabel propertylblAggregation;
	@SuppressWarnings("rawtypes")
	private JComboBox propertyAggregationCombo;
	private JLabel propertylblAlias;
	private JTextField propertyAliasField;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setData (OntoUMLTreeNodeElem elem)
	{		
		// set Class data
		if (elem.getElement() instanceof RefOntoUML.Class)
		{
			RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
			typeNameField.setText(c.getName());
			typeAliasField.setText(elem.getUniqueName());
			if (c.isIsAbstract()) typeAbstractCombo.setSelectedIndex(2); else typeAbstractCombo.setSelectedIndex(1);
			
			if (c instanceof RefOntoUML.Collective)
			{
				RefOntoUML.Collective col = (RefOntoUML.Collective)c;
				if(col.isIsExtensional()) typeExtensionalCombo.setSelectedIndex(2); else typeExtensionalCombo.setSelectedIndex(1);
			}
			typeTypeField.setText("<< "+elem.getTypeName()+" >>");
		}
		
		//set Datatype data
		if (elem.getElement() instanceof RefOntoUML.DataType)
		{
			RefOntoUML.DataType c = (RefOntoUML.DataType)elem.getElement();
			typeNameField.setText(c.getName());
			typeAliasField.setText(elem.getUniqueName());
			if (c.isIsAbstract()) typeAbstractCombo.setSelectedIndex(2); else typeAbstractCombo.setSelectedIndex(1);
			typeTypeField.setText("<< "+elem.getTypeName()+" >>");
		}
		
		// set Association Data
		if (elem.getElement() instanceof RefOntoUML.Association)
		{
			RefOntoUML.Association c = (RefOntoUML.Association)elem.getElement();
			assocNameField.setText(elem.getName());
			assocAliasField.setText(elem.getUniqueName());
			if (c.isIsAbstract()) assocAbstractCombo.setSelectedIndex(2); else assocAbstractCombo.setSelectedIndex(1);			
			if(c.isIsDerived()) assocDerivedCombo.setSelectedIndex(2); else assocDerivedCombo.setSelectedIndex(1);
			
			if (c instanceof RefOntoUML.Meronymic)
			{
				RefOntoUML.Meronymic m = (RefOntoUML.Meronymic)c;
				if(m.isIsEssential()) assocEssentialCombo.setSelectedIndex(2); else assocEssentialCombo.setSelectedIndex(1);
				if(m.isIsInseparable()) assocInseparableCombo.setSelectedIndex(2); else assocInseparableCombo.setSelectedIndex(1);
				if(m.isIsShareable()) assocShareableCombo.setSelectedIndex(2); else assocShareableCombo.setSelectedIndex(1);
				if(m.isIsImmutablePart()) assocImmutablePartCombo.setSelectedIndex(2); else assocImmutablePartCombo.setSelectedIndex(1);
				if(m.isIsImmutableWhole()) assocImmutableWholeCombo.setSelectedIndex(2); else assocImmutableWholeCombo.setSelectedIndex(1);
			}
			assocTypeField.setText("<< "+elem.getTypeName()+" >>");
		}
		
		// set Generalization Set Data
		if (elem.getElement() instanceof RefOntoUML.GeneralizationSet)
		{
			RefOntoUML.GeneralizationSet c = (RefOntoUML.GeneralizationSet)elem.getElement();
			genSetNameField.setText(elem.getName());
			if(c.isIsCovering()) genSetCoveringCombo.setSelectedIndex(2); else genSetCoveringCombo.setSelectedIndex(1);
			if(c.isIsDisjoint()) genSetDisjointCombo.setSelectedIndex(2); else genSetDisjointCombo.setSelectedIndex(1);
			ArrayList<String> generalizations = new ArrayList<String>(); 
			for(RefOntoUML.Generalization g: c.getGeneralization()){
				if (g.getGeneral()!=null && g.getSpecific()!=null)
				{
					generalizations.add(" "+g.getGeneral().getName()+" -> "+g.getSpecific().getName()+" ");
				}
			}
			genSetGeneralizationsCombo.setModel(new DefaultComboBoxModel(generalizations.toArray()));
		}
		
		// set Property Data
		if (elem.getElement() instanceof RefOntoUML.Property)
		{
			RefOntoUML.Property c = (RefOntoUML.Property)elem.getElement();
			propertyNameField.setText(elem.getName());
			propertyAliasField.setText(elem.getUniqueName());
			propertyTypeField.setText(((RefOntoUML.Property)elem.getElement()).getType().getName());
			propertyUpperField.setText((new Integer(c.getUpper())).toString());
			propertyLowerField.setText((new Integer(c.getLower())).toString());
			if(c.isIsReadOnly()) propertyReadOnlyCombo.setSelectedIndex(2); else propertyReadOnlyCombo.setSelectedIndex(1);
			if(c.getAggregation().equals(AggregationKind.COMPOSITE)) propertyAggregationCombo.setSelectedIndex(1);
			else if(c.getAggregation().equals(AggregationKind.SHARED)) propertyAggregationCombo.setSelectedIndex(2);
			else if(c.getAggregation().equals(AggregationKind.NONE)) propertyAggregationCombo.setSelectedIndex(0);
		}
		
		repaint();
		validate();
	}
	
	@SuppressWarnings({ })
	public OntoUMLElemTabbedPane ()
	{
		createClassPanel();
		add(typePanel);
		setTitleAt(0, "Type");
		createAssociationPanel();
		add(assocPanel);
		setTitleAt(1, "Relationship");	
		createGeneralizationSetPanel();
		add(genSetPanel);
		setTitleAt(2, "Generalization Set");
		createPropertyPanel();
		add(propertyPanel);		
		setTitleAt(3, "AssociationEnd / Attribute");
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createPropertyPanel()
	{		
		propertyPanel = new JPanel();
		
		propertylblName = new JLabel("Name");
		
		propertyNameField = new JTextField();
		propertyNameField.setEditable(false);
		propertyNameField.setColumns(10);
		propertyNameField.setBackground(Color.WHITE);
		
		propertylblType = new JLabel("Type");
		
		propertyTypeField = new JTextField();
		propertyTypeField.setEditable(false);
		propertyTypeField.setColumns(10);
		propertyTypeField.setBackground(Color.WHITE);
		
		propertylblUpper = new JLabel("Upper");
		
		propertyUpperField = new JTextField();
		propertyUpperField.setEditable(false);
		propertyUpperField.setColumns(10);
		propertyUpperField.setBackground(Color.WHITE);
		
		propertylblLower = new JLabel("Lower");
		
		propertyLowerField = new JTextField();
		propertyLowerField.setEditable(false);
		propertyLowerField.setColumns(10);
		propertyLowerField.setBackground(Color.WHITE);
		
		propertylblReadonly = new JLabel("ReadOnly");
		
		propertyReadOnlyCombo = new JComboBox();
		propertyReadOnlyCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		propertyReadOnlyCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		propertyReadOnlyCombo.setEnabled(false);
		
		propertylblAggregation = new JLabel("Aggregation");
		
		propertyAggregationCombo = new JComboBox();
		propertyAggregationCombo.setModel(new DefaultComboBoxModel(new String[] {"none", "composite", "shared"}));
		propertyAggregationCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		propertyAggregationCombo.setEnabled(false);
		
		propertylblAlias = new JLabel("Alias");
		
		propertyAliasField = new JTextField();
		propertyAliasField.setEditable(false);
		propertyAliasField.setColumns(10);
		propertyAliasField.setBackground(Color.WHITE);
		GroupLayout gl_propertyPanel = new GroupLayout(propertyPanel);
		gl_propertyPanel.setHorizontalGroup(
			gl_propertyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_propertyPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_propertyPanel.createSequentialGroup()
							.addComponent(propertylblName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(propertyNameField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_propertyPanel.createSequentialGroup()
							.addComponent(propertylblType, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(propertyTypeField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_propertyPanel.createSequentialGroup()
							.addGroup(gl_propertyPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(propertylblUpper, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(propertylblLower, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(propertylblReadonly, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(propertylblAggregation, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_propertyPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_propertyPanel.createSequentialGroup()
									.addComponent(propertyReadOnlyCombo, 0, 245, Short.MAX_VALUE)
									.addGap(2))
								.addGroup(gl_propertyPanel.createSequentialGroup()
									.addComponent(propertyAggregationCombo, 0, 245, Short.MAX_VALUE)
									.addGap(2))
								.addComponent(propertyLowerField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
								.addComponent(propertyUpperField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(gl_propertyPanel.createSequentialGroup()
							.addComponent(propertylblAlias, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(propertyAliasField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addGap(18))))
		);
		gl_propertyPanel.setVerticalGroup(
			gl_propertyPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_propertyPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_propertyPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(propertylblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(propertyNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(propertylblType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertyTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(propertylblAlias, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertyAliasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(propertyUpperField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertylblUpper, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(propertyLowerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertylblLower, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(propertyReadOnlyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertylblReadonly, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_propertyPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(propertyAggregationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(propertylblAggregation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		propertyPanel.setLayout(gl_propertyPanel);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createGeneralizationSetPanel()
	{		
		genSetPanel = new JPanel();
		
		JLabel genSetlblName = new JLabel("Name");
		
		genSetNameField = new JTextField();
		genSetNameField.setEditable(false);
		genSetNameField.setColumns(10);
		genSetNameField.setBackground(Color.WHITE);
		
		JLabel lblCovering = new JLabel("Covering");
		
		genSetlblDisjoint = new JLabel("Disjoint");
		
		genSetCoveringCombo = new JComboBox();
		genSetCoveringCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		genSetCoveringCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		genSetCoveringCombo.setEnabled(false);
		
		genSetDisjointCombo = new JComboBox();
		genSetDisjointCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		genSetDisjointCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		genSetDisjointCombo.setEnabled(false);
		
		genSetGeneralizationsCombo = new JComboBox();
		genSetGeneralizationsCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		genSetlblGeneralizations = new JLabel("Generalizations");
		
		GroupLayout gl_genSetPanel = new GroupLayout(genSetPanel);
		gl_genSetPanel.setHorizontalGroup(
			gl_genSetPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_genSetPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_genSetPanel.createSequentialGroup()
							.addGroup(gl_genSetPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_genSetPanel.createSequentialGroup()
									.addComponent(genSetlblName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(genSetNameField, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
								.addGroup(gl_genSetPanel.createSequentialGroup()
									.addComponent(lblCovering, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(genSetCoveringCombo, 0, 245, Short.MAX_VALUE)
									.addGap(2)))
							.addGap(18))
						.addGroup(gl_genSetPanel.createSequentialGroup()
							.addGroup(gl_genSetPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(genSetlblDisjoint, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(genSetlblGeneralizations, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_genSetPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(genSetGeneralizationsCombo, 0, 245, Short.MAX_VALUE)
								.addComponent(genSetDisjointCombo, 0, 245, Short.MAX_VALUE))
							.addGap(20))))
		);
		gl_genSetPanel.setVerticalGroup(
			gl_genSetPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_genSetPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(genSetlblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(genSetNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCovering, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(genSetCoveringCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(genSetlblDisjoint, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(genSetDisjointCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_genSetPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(genSetGeneralizationsCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(genSetlblGeneralizations, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(179, Short.MAX_VALUE))
		);
		genSetPanel.setLayout(gl_genSetPanel);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createAssociationPanel()
	{		
		assocPanel = new JPanel();
		assoclblName = new JLabel("Name");
		
		assocNameField = new JTextField();
		assocNameField.setEditable(false);
		assocNameField.setColumns(10);
		assocNameField.setBackground(Color.WHITE);
		
		assoclblType = new JLabel("Type");
		
		assocTypeField = new JTextField();
		assocTypeField.setFont(new Font("Arial", Font.PLAIN, 11));
		assocTypeField.setEditable(false);
		assocTypeField.setColumns(10);
		assocTypeField.setBackground(Color.WHITE);
		
		assoclblAlias = new JLabel("Alias");
		
		assocAliasField = new JTextField();
		assocAliasField.setEditable(false);
		assocAliasField.setColumns(10);
		assocAliasField.setBackground(Color.WHITE);
		
		assoclblAbstract = new JLabel("Abstract");
		
		assoclblDerived = new JLabel("Derived");
		
		assoclblEssential = new JLabel("Essential");
		
		assocAbstractCombo = new JComboBox();
		assocAbstractCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocAbstractCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocAbstractCombo.setEnabled(false);
		
		assocDerivedCombo = new JComboBox();
		assocDerivedCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocDerivedCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocDerivedCombo.setEnabled(false);
		
		assocEssentialCombo = new JComboBox();
		assocEssentialCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocEssentialCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocEssentialCombo.setEnabled(false);
		
		assoclblInseparable = new JLabel("Inseparable");
		
		assocInseparableCombo = new JComboBox();
		assocInseparableCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocInseparableCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocInseparableCombo.setEnabled(false);
		
		assoclblShareable = new JLabel("Shareable");
		
		assocShareableCombo = new JComboBox();
		assocShareableCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocShareableCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocShareableCombo.setEnabled(false);
		
		assoclblImmutablepart = new JLabel("ImmutablePart");
		
		assocImmutablePartCombo = new JComboBox();
		assocImmutablePartCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocImmutablePartCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocImmutablePartCombo.setEnabled(false);
		
		assoclblImmutablewhole = new JLabel("ImmutableWhole");
		
		assocImmutableWholeCombo = new JComboBox();
		assocImmutableWholeCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		assocImmutableWholeCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		assocImmutableWholeCombo.setEnabled(false);
		
		GroupLayout gl_assocPanel = new GroupLayout(assocPanel);
		gl_assocPanel.setHorizontalGroup(
			gl_assocPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assocPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(assoclblShareable, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
						.addGroup(gl_assocPanel.createSequentialGroup()
							.addGroup(gl_assocPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_assocPanel.createSequentialGroup()
									.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(assoclblName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblAbstract, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblAlias, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblType, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
										.addComponent(assoclblInseparable, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblEssential, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblDerived, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
									.addGap(6)
									.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(assocInseparableCombo, Alignment.TRAILING, 0, 245, Short.MAX_VALUE)
										.addComponent(assocEssentialCombo, Alignment.TRAILING, 0, 245, Short.MAX_VALUE)
										.addComponent(assocDerivedCombo, Alignment.TRAILING, 0, 245, Short.MAX_VALUE)
										.addComponent(assocAbstractCombo, Alignment.TRAILING, 0, 245, Short.MAX_VALUE)
										.addComponent(assocAliasField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
										.addComponent(assocTypeField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
										.addComponent(assocNameField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
								.addGroup(gl_assocPanel.createSequentialGroup()
									.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(assoclblImmutablewhole, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addComponent(assoclblImmutablepart, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(assocImmutablePartCombo, Alignment.TRAILING, 0, 244, Short.MAX_VALUE)
										.addComponent(assocImmutableWholeCombo, 0, 244, Short.MAX_VALUE)
										.addComponent(assocShareableCombo, Alignment.TRAILING, 0, 244, Short.MAX_VALUE))))
							.addGap(18))))
		);
		gl_assocPanel.setVerticalGroup(
			gl_assocPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assocPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(assoclblAlias, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocAliasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblAbstract, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocAbstractCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblDerived, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocDerivedCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblEssential, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocEssentialCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(assoclblInseparable, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(assocInseparableCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_assocPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(assocShareableCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_assocPanel.createSequentialGroup()
							.addComponent(assoclblShareable, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(assocImmutablePartCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(assoclblImmutablepart, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_assocPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(assoclblImmutablewhole, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(assocImmutableWholeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		assocPanel.setLayout(gl_assocPanel);		
		
	}	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createClassPanel()
	{		
		typePanel = new JPanel();
		
		JLabel typelblName = new JLabel("Name");		
		JLabel typelblAlias = new JLabel("Alias");		
		JLabel typelblAbstract = new JLabel("Abstract");
		
		typeNameField = new JTextField();
		typeNameField.setBackground(Color.WHITE);
		typeNameField.setEditable(false);
		typeNameField.setColumns(10);
		
		typeAliasField = new JTextField();
		typeAliasField.setBackground(Color.WHITE);
		typeAliasField.setEditable(false);
		typeAliasField.setColumns(10);
		
		typeAbstractCombo = new JComboBox();
		typeAbstractCombo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		typeAbstractCombo.setEnabled(false);
		typeAbstractCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		
		typelblExtensional = new JLabel("Extensional");
		
		typeExtensionalCombo = new JComboBox();
		typeExtensionalCombo.setEnabled(false);
		typeExtensionalCombo.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		
		typelblType = new JLabel("Type");
		
		typeTypeField = new JTextField();
		typeTypeField.setFont(new Font("Arial", Font.PLAIN, 11));
		typeTypeField.setBackground(Color.WHITE);
		typeTypeField.setEditable(false);
		typeTypeField.setColumns(10);
		
		GroupLayout gl_typePanel = new GroupLayout(typePanel);
		gl_typePanel.setHorizontalGroup(
			gl_typePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_typePanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(typelblExtensional, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(typelblType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(typelblAlias, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(typelblAbstract, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(typelblName, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(typeExtensionalCombo, 0, 242, Short.MAX_VALUE)
						.addComponent(typeTypeField, 242, 242, Short.MAX_VALUE)
						.addComponent(typeAbstractCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(typeAliasField, 242, 242, Short.MAX_VALUE)
						.addComponent(typeNameField))
					.addGap(20))
		);
		gl_typePanel.setVerticalGroup(
			gl_typePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typePanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typelblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typelblType)
						.addComponent(typeTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typelblAlias, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeAliasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(typelblAbstract, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeAbstractCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_typePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(typelblExtensional, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeExtensionalCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(147, Short.MAX_VALUE))
		);
		typePanel.setLayout(gl_typePanel);		
	}			
}
