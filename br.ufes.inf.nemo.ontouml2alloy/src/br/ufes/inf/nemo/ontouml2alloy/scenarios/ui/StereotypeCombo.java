package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Stereotype;

public class StereotypeCombo extends JComboBox<Stereotype>{
	
	private static final long serialVersionUID = 2721908823135720575L;

	public StereotypeCombo(){
		ArrayList<Stereotype> list = new ArrayList<Stereotype>();
	
		for (Stereotype s : ClassStereotype.getClassStereotypes()) {
			list.add(s);
		}
		for (Stereotype s : RelationStereotype.getAssociationStereotypes()) {
			list.add(s);
		}
		
		Stereotype[] array = new Stereotype[list.size()];
		array = list.toArray(array);
		
		setModel(new DefaultComboBoxModel<Stereotype>(array));
	}
	
	public Class<? extends EObject> getSelectedMetaClass(){
		if(getSelectedItem()==null)
			return null;
		
		return ((Stereotype)getSelectedItem()).getMetaclass();
	}
	
	

}
