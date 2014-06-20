package br.ufes.inf.nemo.meronymic_validation.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;

public class ClassStereotypeCombo extends JComboBox<ClassStereotype> {
	
	private static final long serialVersionUID = -6081802431795454015L;
	
	private DefaultComboBoxModel<ClassStereotype> model;
	
	public ClassStereotypeCombo(){
		super();
		
		model = new DefaultComboBoxModel<>(ClassStereotype.values());
		setModel(model);
		setSelectedItem(null);
	}
	
	public ClassStereotypeCombo(Classifier classifier){
		super();
		
		model = new DefaultComboBoxModel<>(ClassStereotype.values());
		setModel(model);
		setSelectedItem(OutcomeFixer.getClassStereotype(classifier));
	}
}
