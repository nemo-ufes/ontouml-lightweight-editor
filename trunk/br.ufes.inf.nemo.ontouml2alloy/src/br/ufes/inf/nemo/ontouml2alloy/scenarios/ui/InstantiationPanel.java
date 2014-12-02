package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.InstantiationScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.InstantiationType;

public class InstantiationPanel extends ScenarioPanel<InstantiationScenario> {


	private static final long serialVersionUID = 1L;
	private JComboBox<InstantiationType> instantiationTypeCombo;
	private OntoUMLElementList list;
	private CustomQuantificationPanel classQuantificationPanel;
	private CustomQuantificationPanel worldQuantificationPanel;

	/**
	 * @wbp.parser.constructor
	 */
	public InstantiationPanel(OntoUMLParser parser) {
		this(parser,null);
	}
	
	public InstantiationPanel(OntoUMLParser parser, InstantiationScenario scenario) {
		super(parser, scenario);
		
		JLabel lblClass = new JLabel("Classes:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Mode:");
		
		instantiationTypeCombo = new JComboBox<InstantiationType>();
		instantiationTypeCombo.setModel(new DefaultComboBoxModel<InstantiationType>(InstantiationType.values()));
		
		classQuantificationPanel = new CustomQuantificationPanel();
		worldQuantificationPanel = new CustomQuantificationPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(worldQuantificationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(classQuantificationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblClass))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(instantiationTypeCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(lblClass))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(instantiationTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(classQuantificationPanel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(worldQuantificationPanel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblClass, lblNewLabel_1});
		
		list = new OntoUMLElementList(parser, Class.class);
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		
		combos.add(instantiationTypeCombo);
		combos.addAll(classQuantificationPanel.getAllCombos());
		combos.addAll(worldQuantificationPanel.getAllCombos());
		
		spinners.add(classQuantificationPanel.getSpinner());
		spinners.add(worldQuantificationPanel.getSpinner());
	}

	@Override
	public void loadDefaultUIData() {
		list.setSelectedIndex(-1);
		instantiationTypeCombo.setSelectedIndex(-1);
		classQuantificationPanel.loadDefautUIData();
		worldQuantificationPanel.loadDefautUIData();
		
	}

	@Override
	public void loadScenarioUIData() {
		if(scenario==null)
			loadDefaultUIData();
		
		list.setSelectedArray(scenario.getClassifierList());
		instantiationTypeCombo.setSelectedItem(scenario.getType());
		
		classQuantificationPanel.loadQuantificationUIData(scenario.getClassifierQuantification());
		worldQuantificationPanel.loadQuantificationUIData(scenario.getWorldQuantification());
	}

	@Override
	public InstantiationScenario saveScenario() {
		
		if(!(scenario instanceof InstantiationScenario))
			scenario = new InstantiationScenario(parser);
		
		classQuantificationPanel.saveQuantificationData(scenario.getClassifierQuantification());
		worldQuantificationPanel.saveQuantificationData(scenario.getWorldQuantification());
		
		InstantiationType it = (InstantiationType)instantiationTypeCombo.getSelectedItem();
		
		ArrayList<EObject> classifiers = list.getSelectedArray();
		
		switch (it) {
		case EXCL:
			scenario.setAsExclusive(classifiers);
			break;
		case MULT:
			scenario.setAsMultiple(classifiers);
			break;
		}
		
		return scenario;
	}

	@Override
	public boolean canSave() {
		return classQuantificationPanel.canSave() && worldQuantificationPanel.canSave() && 
				instantiationTypeCombo.getSelectedIndex()!=-1 && list.getSelectedArray().size()>1;
	}
}
