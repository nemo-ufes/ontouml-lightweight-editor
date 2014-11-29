package br.ufes.inf.nemo.validator.meronymic.checkers;

import javax.swing.JDialog;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.validator.meronymic.checkers.ui.GeneralizationDialog;

public class GeneralizationError extends MeronymicError<Generalization>{

	enum Action {REVERSE, REMOVE, CHANGE_STEREOTYPE}
	private Action action;
	private ClassStereotype parentStereo;
	private ClassStereotype childStereo;

	public GeneralizationError(OntoUMLParser parser, Generalization g) {
		super(parser,g);
		parentStereo = null;
		childStereo = null;
	}
	
	@Override
	public String getDescription(){
		return "Child: "+OntoUMLNameHelper.getTypeAndName(element.getSpecific(), true, true)+
				", Parent: "+OntoUMLNameHelper.getTypeAndName(element.getGeneral(), true, true);
	}

	@Override
	public String getType() {
		return "Forbidden Generalization";
	}
//TODO
	@Override
	public JDialog createDialog(JDialog parent) {
		return new GeneralizationDialog(parent, this);
	}
	
	@Override
	public Fix fix() {
		
		if(action==Action.REVERSE)
			reverseGeneralization();
		else if (action==Action.REMOVE)
			removeGeneralization();
		else if (action==Action.CHANGE_STEREOTYPE)
			changeStereotypes();
		
		return fix;
	}
	
	@Override
	public boolean hasAction(){
		return action==Action.REVERSE || action==Action.REMOVE || action==Action.CHANGE_STEREOTYPE;
	}
	
	public void setReverse(){
		action = Action.REVERSE;
	}
	
	public void setRemove(){
		action = Action.REMOVE;
	}
	
	public void setChangeParentStereotype(ClassStereotype parentStereo){
		action = Action.CHANGE_STEREOTYPE;
		this.parentStereo = parentStereo;
	}
	
	public void setChangeChildStereotype(ClassStereotype childStereo){
		action = Action.CHANGE_STEREOTYPE;
		this.childStereo = childStereo;
	}
	
	public void reverseGeneralization(){
		fix.addAll(fixer.invertEnds(element));
	}
	
	public void removeGeneralization(){
		fix.addAll(fixer.deleteElement(element));
	}
	
	public void changeStereotypes(){
		if(parentStereo!=null)
			fix.addAll(fixer.changeClassStereotypeTo(element.getGeneral(), parentStereo));
		if(childStereo!=null)
			fix.addAll(fixer.changeClassStereotypeTo(element.getSpecific(), childStereo));
	}

	public Classifier getParent() {
		return element.getGeneral();
	}

	public Classifier getChild() {
		return element.getSpecific();
	}
}
