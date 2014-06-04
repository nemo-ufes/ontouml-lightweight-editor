package br.ufes.inf.nemo.meronymic_validation.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;

public class StereotypeCombo extends JComboBox<RelationStereotype> {
	
	private static final long serialVersionUID = -6081802431795454015L;
	
	private DefaultComboBoxModel<RelationStereotype> model;
	
	public StereotypeCombo(){
		super();
		
		model = new DefaultComboBoxModel<>(RelationStereotype.values());
		setModel(model);
		setSelectedItem(null);
	}
}
