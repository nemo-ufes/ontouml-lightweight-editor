package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationDepthScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationDepthScenario.BoundType;

public class AssociationDepthPanel extends ScenarioPanel<AssociationDepthScenario> {
	
	private static final long serialVersionUID = -270595094656326794L;
	
	private OntoUMLElementCombo associationCombo;
	private JTextField targetField;
	private JTextField sourceField;
	private JComboBox<BoundType> limitCombo;
	private JSpinner limitSpinner;
	private CustomQuantificationPanel classQuantificationPanel;
	private CustomQuantificationPanel worldQuantificationPanel;

	/**
	 * @wbp.parser.constructor
	 */
	public AssociationDepthPanel(OntoUMLParser parser) {
		this(parser,null);
	}
	
	public AssociationDepthPanel(OntoUMLParser parser, AssociationDepthScenario scenario) {
		super(parser,scenario);
		
		JLabel lblNewLabel_1 = new JLabel("Source:");
		
		sourceField = new JTextField();
		sourceField.setEditable(false);
		
		JLabel lblStereotype = new JLabel("Association:");
		
		associationCombo = new OntoUMLElementCombo(parser);
		associationCombo.setObjects(getBinOverAssociations());
		associationCombo.addActionListener(associationListener);
		
		targetField = new JTextField();
		targetField.setEditable(false);
		
		JLabel lblTarget = new JLabel("Target:");
		
		JLabel lblMultiplicity = new JLabel("Limit:");
		
		limitCombo = new JComboBox<BoundType>();
		limitCombo.setModel(new DefaultComboBoxModel<BoundType>(BoundType.values()));
		
		JLabel lblX = new JLabel("x =");
		lblX.setHorizontalAlignment(SwingConstants.LEFT);
		
		limitSpinner = new JSpinner();
		
		classQuantificationPanel = new CustomQuantificationPanel();
		classQuantificationPanel.setBorder(new TitledBorder(null, "Base Class Quantification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		worldQuantificationPanel = new CustomQuantificationPanel();
		worldQuantificationPanel.setBorder(new TitledBorder(null, "World Quantification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(worldQuantificationPanel, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
						.addComponent(classQuantificationPanel, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblTarget, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblStereotype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
								.addComponent(lblMultiplicity, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(targetField, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
								.addComponent(sourceField, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
								.addComponent(associationCombo, 0, 378, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(limitCombo, 0, 252, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(limitSpinner, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(associationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStereotype))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sourceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(targetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTarget))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(limitCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMultiplicity)
						.addComponent(limitSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(classQuantificationPanel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(worldQuantificationPanel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		combos.add(associationCombo);
		combos.add(limitCombo);
		combos.addAll(classQuantificationPanel.getAllCombos());
		combos.addAll(worldQuantificationPanel.getAllCombos());
		
		spinners.add(classQuantificationPanel.getSpinner());
		spinners.add(worldQuantificationPanel.getSpinner());
		spinners.add(limitSpinner);

	}

	private Set<EObject> getBinOverAssociations() {
		Set<EObject> items = new HashSet<EObject>();
		
		for (Association a : parser.getAllInstances(Association.class)) {
			
			if(a.getMemberEnd().size()!=2)
				continue;
			
			Type source = a.getMemberEnd().get(0).getType();
			Type target = a.getMemberEnd().get(1).getType();
			
			if(source!=null && target!=null && source.equals(target))
				items.add(a);
		}
		
		return items;
	}

	private ActionListener associationListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateTextFields();
		}		
	};
	
	private void updateTextFields() {
		if(associationCombo.getSelectedIndex()==-1){
			sourceField.setText("");
			targetField.setText("");
			return;
		}
		
		Association a = (Association) associationCombo.getElement();
		
		if(a.getMemberEnd().size()!=2)
			return;
		
		sourceField.setText(OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(0), true));
		targetField.setText(OntoUMLNameHelper.getNameAndType(a.getMemberEnd().get(1), true));
	}

	@Override
	public void loadDefaultUIData() {
		associationCombo.setSelectedItem(null);
		sourceField.setText("");
		targetField.setText("");
		
		limitCombo.setSelectedItem(null);
		limitSpinner.setValue(1);
		
		classQuantificationPanel.loadDefautUIData();
		worldQuantificationPanel.loadDefautUIData();
	}

	@Override
	public void loadScenarioUIData() {
		if(scenario==null)
			loadDefaultUIData();
		
		associationCombo.assignElement(scenario.getAssociation());
		updateTextFields();
		
		limitCombo.setSelectedItem(scenario.getBound());
		limitSpinner.setValue(scenario.getDepth());
		
		classQuantificationPanel.loadQuantificationUIData(scenario.getClassQuantification());
		worldQuantificationPanel.loadQuantificationUIData(scenario.getWorldQuantification());
	}

	@Override
	public AssociationDepthScenario saveScenario() {
		if(scenario==null)
			scenario = new AssociationDepthScenario(parser);
		
		scenario.setAssociation((Association) associationCombo.getElement());
					
		scenario.setBound((BoundType)limitCombo.getSelectedItem());
		scenario.setDepth((int) limitSpinner.getValue());
		
		classQuantificationPanel.saveQuantificationData(scenario.getClassQuantification());
		worldQuantificationPanel.saveQuantificationData(scenario.getWorldQuantification());
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return associationCombo.getSelectedIndex()!=-1 && limitCombo.getSelectedIndex()!=-1 && ((int)limitSpinner.getValue())>=0 &&
				classQuantificationPanel.canSave() && worldQuantificationPanel.canSave();
	}
}
