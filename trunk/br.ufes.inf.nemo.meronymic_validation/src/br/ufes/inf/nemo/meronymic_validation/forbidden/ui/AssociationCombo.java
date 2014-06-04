package br.ufes.inf.nemo.meronymic_validation.forbidden.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import RefOntoUML.Property;
import RefOntoUML.memberOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenMemberOf;

public class AssociationCombo extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
	private ForbiddenMemberOf forbidden;
	private DefaultComboBoxModel<String> model;
	
	public AssociationCombo(ForbiddenMemberOf forbidden){
		super();
		this.forbidden = forbidden;
		
		model = new DefaultComboBoxModel<String>();
		model.addElement(OntoUMLNameHelper.getName(forbidden.getMeronymic(), false, false));
		
		for (Property p : forbidden.getPath()) {
			model.addElement(OntoUMLNameHelper.getName(p.getAssociation()));
		}
		
		setModel(model);
	}
	
	public memberOf getSelectedAssociation(){
		if(getSelectedIndex()==-1)
			return null;
		else
			return (memberOf) forbidden.getPath().get(getSelectedIndex()).getAssociation();
	}

}
