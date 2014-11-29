package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Scenario;

public abstract class ScenarioPanel<T extends Scenario> extends JPanel {
	
	private static final long serialVersionUID = -5898712697974675901L;
	
	T scenario;
	OntoUMLParser parser;
	
	ArrayList<JComboBox<?>> combos = new ArrayList<JComboBox<?>>();
	ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
	ArrayList<JSpinner> spinners = new ArrayList<JSpinner>();
	
	public ScenarioPanel(OntoUMLParser parser, T scenario) {
		this.scenario = scenario;
		this.parser = parser;
	}
	
	public T getScenario(){
		return scenario;
	}
	
	public void loadUIData(){
		
		if(scenario!=null)
			loadUIData();
		else
			loadDefaultUIData();
	}
	
	public void resetUI(){
		scenario = null;
		loadDefaultUIData();
	}	
	
	public boolean canDelete() {
		return scenario!=null;
	}
	
	public void loadDefaultUIData(boolean clearCurrentScenario){
		scenario=null;
		loadDefaultUIData();
				
	}
	public abstract void loadDefaultUIData();
	public abstract void loadScenarioUIData();
	public abstract T saveScenario();
	public abstract boolean canSave();

	public void addActionListerToCombos(ActionListener al){
		for (JComboBox<?> combo : combos) {
			combo.addActionListener(al);
		}
	}
	
	public void addItemListerToCheckboxes(ItemListener il){
		for (JCheckBox check : checks) {
			check.addItemListener(il);
		}
	}
	
	public void addChangeListenerToSpinners(ChangeListener cl){
		for (JSpinner spinner : spinners) {
			spinner.addChangeListener(cl);
		}
	}

	public void setScenario(T sc) {
			scenario = sc;
		
	}
	
}
