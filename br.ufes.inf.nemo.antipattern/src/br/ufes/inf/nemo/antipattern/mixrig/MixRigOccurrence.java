package br.ufes.inf.nemo.antipattern.mixrig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Mixin;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;

public class MixRigOccurrence extends AntipatternOccurrence {

	Mixin mixin;
	ArrayList<Classifier> subtypes;
	boolean rigidSubtypes;
	boolean antiRigidSubtypes; 
	
	public MixRigOccurrence(Mixin mixin, MixRigAntipattern ap) throws Exception {
		super(ap);
		
		if(mixin==null)
			throw new NullPointerException("MixRig: null mixin provided.");
		
		if(!(mixin instanceof Mixin))
			throw new NullPointerException("MixRig: mixin must be an instance of Mixin.");
		
		this.mixin = mixin;
		
		this.subtypes = new ArrayList<Classifier>();
		this.subtypes.addAll(mixin.children());
		
		if (this.subtypes.size()==0)
			throw new Exception("MixRig: Mixin has no subtypes!");
		
		boolean hasRigid = false;
		boolean hasAntiRigid = false;
		boolean hasSemiRigid = false;
		
		for (Classifier child : subtypes) {	
			if (child instanceof SubstanceSortal || child instanceof SubKind || child instanceof Category)
				hasRigid = true;
			else if( child instanceof Role || child instanceof Phase || child instanceof RoleMixin)
				hasAntiRigid = true;
			else if (child instanceof Mixin)
				hasSemiRigid = true;
		}
		
		if((hasRigid && !hasAntiRigid && !hasSemiRigid) || (hasAntiRigid && !hasRigid && !hasSemiRigid)){
			this.rigidSubtypes=!hasAntiRigid;
			this.antiRigidSubtypes = hasAntiRigid;
		}
		else 
			throw new Exception("MixRig: Mixin has both rigid and anti-rigid subtypes.");
	}

	public Mixin getMixin() {
		return mixin;
	}

	public ArrayList<Classifier> getSubtypes() {
		return subtypes;
	}

	public boolean rigidSubtypes() {
		return rigidSubtypes;
	}
	
	public boolean antiRigidSubtypes() {
		return antiRigidSubtypes;
	}
	
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.mixin);
		selection.addAll(subtypes);
				
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = "Mixin: "+super.parser.getStringRepresentation(this.mixin) + "\n";
		
		if (rigidSubtypes)
			result += "Rigid";
		else
			result+="Anti-Rigid";
		
		result += "\nSubtypes: ";
		
		for (Classifier subtype : this.subtypes) {
			result+="\n\t"+super.parser.getStringRepresentation(subtype);
		}
		return result;
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(mixin);
	}

	public void changeMixinStereotype(){
		if(rigidSubtypes())
			fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.CATEGORY));
		else
			fix.addAll(fixer.changeClassStereotypeTo(mixin, ClassStereotype.ROLEMIXIN));
		
	}
	
	public void addExistingSubtypes(ArrayList<Classifier> existingSubtypes){
		for (Classifier subtype : existingSubtypes) {
			fix.addAll(fixer.createGeneralization(subtype, mixin));
		}
	}
	
	public void addNewSubtypes(HashMap<String, Class<?>> newSubtypes){
		for (String name : newSubtypes.keySet()) {
			fix.addAll(fixer.createSubTypeAs(mixin, OutcomeFixer.getClassStereotype(newSubtypes.get(name)), name));
		}
	}

	public void changeSubtypesStereotype(HashMap<Classifier, Class<?>> modifiedSubtypes) {
		
		Iterator<Classifier> iterator = modifiedSubtypes.keySet().iterator();
		
		while(iterator.hasNext()){
			Classifier subtype = iterator.next();
			fix.addAll(fixer.changeClassStereotypeTo(subtype, OutcomeFixer.getClassStereotype(modifiedSubtypes.get(subtype))));
		}
		
	}
}
