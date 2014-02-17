package br.ufes.inf.nemo.assistant.manager;

import java.text.Normalizer;
import java.util.ArrayList;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.assistant.util.GeneralizationClass;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;

public class PatternOperator {
	//elements
	private ArrayList<RefOntoUML.Kind> setKind = new ArrayList<>();
	private ArrayList<RefOntoUML.Quantity> setQuantity = new ArrayList<>();
	private ArrayList<RefOntoUML.Collective> setCollective = new ArrayList<>();
	private ArrayList<RefOntoUML.SubKind> setSubkind = new ArrayList<>();
	private ArrayList<RefOntoUML.Role> setRole = new ArrayList<>();
	private ArrayList<RefOntoUML.Phase> setPhase = new ArrayList<>();
	private ArrayList<RefOntoUML.Category> setCategory = new ArrayList<>();
	private ArrayList<RefOntoUML.RoleMixin> setRoleMixin = new ArrayList<>();
	private ArrayList<RefOntoUML.Mixin> setMixin = new ArrayList<>();
	private ArrayList<RefOntoUML.Relator> setRelator = new ArrayList<>();
	private ArrayList<RefOntoUML.GeneralizationSet> setGeneralizationSet = new ArrayList<>();

	public ArrayList<RefOntoUML.Kind> getSetKind() {
		return setKind;
	}

	public ArrayList<RefOntoUML.Quantity> getSetQuantity() {
		return setQuantity;
	}

	public ArrayList<RefOntoUML.Collective> getSetCollective() {
		return setCollective;
	}

	public ArrayList<RefOntoUML.SubKind> getSetSubkind() {
		return setSubkind;
	}

	public ArrayList<RefOntoUML.Role> getSetRole() {
		return setRole;
	}

	public ArrayList<RefOntoUML.Phase> getSetPhase() {
		return setPhase;
	}

	public ArrayList<RefOntoUML.Category> getSetCategory() {
		return setCategory;
	}

	public ArrayList<RefOntoUML.RoleMixin> getSetRoleMixin() {
		return setRoleMixin;
	}

	public ArrayList<RefOntoUML.Mixin> getSetMixin() {
		return setMixin;
	}

	public ArrayList<RefOntoUML.Relator> getSetRelator() {
		return setRelator;
	}

	public ArrayList<RefOntoUML.GeneralizationSet> getSetGeneralizationSet() {
		return setGeneralizationSet;
	}

	/**
	 * Initializing all kind of lists
	 * */
	public void updateLists(RefOntoUML.Package root) {
		for (PackageableElement p: root.getPackagedElement()) {
			if(p instanceof Kind){
				setKind.add((Kind)p);
			}else if(p instanceof Quantity){
				setQuantity.add((Quantity)p);
			}else if(p instanceof Collective){
				setCollective.add((Collective)p);
			}else if(p instanceof SubKind){
				setSubkind.add((SubKind)p);
			}else if(p instanceof Role){
				setRole.add((Role)p);
			}else if(p instanceof Phase){
				setPhase.add((Phase)p);
			}else if(p instanceof Category){
				setCategory.add((Category)p);
			}else if(p instanceof RoleMixin){
				setRoleMixin.add((RoleMixin)p);
			}else if(p instanceof Mixin){
				setMixin.add((Mixin)p);
			}else if(p instanceof Relator){
				setRelator.add((Relator)p);
			}else if(p instanceof GeneralizationSet){
				setGeneralizationSet.add((GeneralizationSet)p);
			}else if(p instanceof Package){
				updateLists((Package) p);
			}
		}
	}

	/**
	 *	Return a hash with <string with class and stereotype, <a String with the generalizationSet name, the meta property of this class in a array ([0]: isDisjoint.
	 *  [1]: isCovering)>> for each class with it stereotype.
	 * */	
	public ArrayList<GeneralizationClass> getMetaPropertiesForAll(StereotypeOntoUMLEnum stereotype){
		ArrayList<GeneralizationClass> genClassList = new ArrayList<>();
		for (GeneralizationSet genSet : setGeneralizationSet) {
			Classifier general = genSet.getGeneralization().get(0).getGeneral(); 
			if(UtilAssistant.getStereotypeFromClassifier(general) == stereotype){
				GeneralizationClass genClass = new GeneralizationClass(getStringRepresentationClass(general), genSet.getName(), genSet.isIsDisjoint(), genSet.isIsCovering());
				//Victor
				//Esta colocando repetido
				genClassList.add(genClass);	
			}
		}
		return genClassList;
	}

	public Classifier getClassifierForStringRepresentationClassStereotype(String stringRepresentation){
		String stereotype = stringRepresentation.substring(0,stringRepresentation.indexOf(" "));
		String className  = stringRepresentation.substring(stringRepresentation.indexOf(" ")+1,stringRepresentation.length());

		StereotypeOntoUMLEnum stereotypeEnum = StereotypeOntoUMLEnum.valueOf(stereotype.toUpperCase());
		ArrayList<? extends Classifier> list = getAllInstancesOf(stereotypeEnum);
		for (Classifier classifier : list) {
			if(((NamedElement)classifier).getName().equals(className)){
				return classifier;
			}
		}
		return null;
	}

	public GeneralizationSet getGeneralizationSetByName(String genSetName){
		for (GeneralizationSet genSet : setGeneralizationSet) {
			if(genSet.getName().equals(genSetName)){
				return genSet;
			}
		}
		return null;
	}

	public ArrayList<? extends Classifier> getAllInstancesOf(StereotypeOntoUMLEnum stereotype) {
		switch (stereotype) {
		case CATEGORY:
			return setCategory;
		case COLLECTIVE:
			return setCollective;
		case KIND:
			return setKind;
		case MIXIN:
			return setMixin;
		case PHASE:
			return setPhase;
		case RELATOR:
			return setRelator;
		case ROLE:
			return setRole;
		case ROLEMIXIN:
			return setRoleMixin;
		case SUBKIND:
			return setSubkind;
		}
		return null;
	}


	/**
	 * Return a string representation for all class with its stereotype
	 * */
	public String[] getStringRepresentationClassStereotypesFor(StereotypeOntoUMLEnum stereotype) {
		switch (stereotype) {
		case CATEGORY:
			return getStringRepresentationClassStereotype(setCategory);
		case COLLECTIVE:
			return getStringRepresentationClassStereotype(setCollective);
		case KIND:
			return getStringRepresentationStereotype(setKind);
		case MIXIN:
			return getStringRepresentationStereotype(setMixin);
		case PHASE:
			return getStringRepresentationStereotype(setPhase);
		case RELATOR:
			return getStringRepresentationStereotype(setRelator);
		case ROLE:
			return getStringRepresentationStereotype(setRole);
		case ROLEMIXIN:
			return getStringRepresentationStereotype(setRoleMixin);
		case SUBKIND:
			return getStringRepresentationStereotype(setSubkind);
		}
		return null;
	}

	/**
	 * Return a String with the Class and its Stereotype.
	 * e.g.: "Kind Person"
	 * */
	private String getStringRepresentationClassStereotype(Classifier c){
		String type = c.getClass().toString().replaceAll("class RefOntoUML.impl.","");
		type = type.replaceAll("Impl","");
		type = Normalizer.normalize(type, Normalizer.Form.NFD);	

		String name = ((NamedElement)c).getName();

		return type +" "+ name;
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: {"Kind Person", "Collective Students"}
	 * */
	private String[] getStringRepresentationClassStereotype(ArrayList<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationClassStereotype(cls);
		}
		return s;
	}

	/**
	 * Return a String with the Class
	 * e.g.: From Kind Person, returns "Person"
	 * */
	private String getStringRepresentationClass(Classifier c){
		return ((NamedElement)c).getName();
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From {"Kind Person", "Collective Students"} returns {"Person", "Students"}
	 * */
	private String[] getStringRepresentationClass(ArrayList<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationClass(cls);
		}
		return s;
	}

	/**
	 * Return a String with the Class
	 * e.g.: From Kind Person, returns "Kind"
	 * */
	private String getStringRepresentationStereotype(Classifier c){
		String type = c.getClass().toString().replaceAll("class RefOntoUML.impl.","");
		type = type.replaceAll("Impl","");
		type = Normalizer.normalize(type, Normalizer.Form.NFD);

		return type;
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From {"Kind Person", "Collective Students"} returns {"Kind", "Collective"}
	 * */
	private String[] getStringRepresentationStereotype(ArrayList<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationStereotype(cls);
		}
		return s;
	}
}
