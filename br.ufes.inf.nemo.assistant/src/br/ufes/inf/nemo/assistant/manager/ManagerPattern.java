package br.ufes.inf.nemo.assistant.manager;

import java.util.Set;

import org.eclipse.jface.wizard.IWizardPage;

import JP.co.esm.caddies.jomt.jcontrol.SetRelationModeCommand;
import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.assistant.ModellingAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewClass;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGenericRelation;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewPhase;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewRelator;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ManagerPattern {
	private RefOntoUML.Package root;
	private OutcomeFixer of;
	private Fix fix = new Fix();

	private Classifier source;

	public Fix getFix(){
		return fix;
	}

	public void setClassifierOrigem(Classifier src){
		source = src;
		//		String[] s = getStereotypesFor(ModellingAssistant.getStereotypeFromClassifier(src));
		//		for (String string : s) {
		//			System.out.println(string);
		//		}
	}


	public void name(RefOntoUML.Package root) {
		for (PackageableElement p: root.getPackagedElement()) {
			if(p instanceof Kind){
				//addtoList
			}
			//...

			if(p instanceof Package){
				name((Package) p);
			}
		}
	}

	public void setRefOntoUML(RefOntoUML.Package root) {
		this.root = root;
		of = new OutcomeFixer(root);

		//Initializing all sets
		updateSets();
	}

	//Sets
	private Set<RefOntoUML.Kind> setKind;
	private Set<RefOntoUML.Quantity> setQuantity;
	private Set<RefOntoUML.Collective> setCollective;
	private Set<RefOntoUML.SubKind> setSubkind;
	private Set<RefOntoUML.Role> setRole;
	private Set<RefOntoUML.Phase> setPhase;
	private Set<RefOntoUML.Category> setCategory;
	private Set<RefOntoUML.RoleMixin> setRoleMixin;
	private Set<RefOntoUML.Mixin> setMixin;
	private Set<RefOntoUML.Relator> setRelator;


	private void updateSets(){
		//RigidSortal
//		setKind = ontoParser.getAllInstances(RefOntoUML.Kind.class);
//		setQuantity = ontoParser.getAllInstances(RefOntoUML.Quantity.class);
//		setCollective = ontoParser.getAllInstances(RefOntoUML.Collective.class);
//		setSubkind = ontoParser.getAllInstances(RefOntoUML.SubKind.class);
//
//		//AntiRigidSortal
//		setRole = ontoParser.getAllInstances(RefOntoUML.Role.class);
//		setPhase = ontoParser.getAllInstances(RefOntoUML.Phase.class);
//
//		//RigidMixin
//		setCategory = ontoParser.getAllInstances(RefOntoUML.Category.class);
//
//		//AntiRigidMixin
//		setRoleMixin = ontoParser.getAllInstances(RefOntoUML.RoleMixin.class);
//
//		//SemiRigidMixin
//		setMixin = ontoParser.getAllInstances(RefOntoUML.Mixin.class);
//
//		//Moment
//		setRelator = ontoParser.getAllInstances(RefOntoUML.Relator.class);
	}


	/* Actions */

	/*
	 * Substance Sortals are classes that can provide identity principal
	 * They are: Kind, Quantity and Collective;
	 * */
	public boolean existSomeSubstanceSortal() {
		if(!setKind.isEmpty() || !setQuantity.isEmpty() || !setCollective.isEmpty()){
			return true;
		}
		return true;
	}

	/* Pages callback */

	public void run(IWizardPage page) {
		if(page instanceof NewRelator){
			process((NewRelator)page);
		}else if(page instanceof NewClass){
			process((NewClass)page);
		}else if(page instanceof NewPhase){
			process((NewPhase)page);
		}else if(page instanceof NewGenericRelation){
			process((NewGenericRelation)page);
		}else{
			System.out.println("Not treated page: "+page.getName());
		}
	}

	private void process(NewGenericRelation page) {
		System.out.println(page.toString());
	}

	private void process(NewPhase page) {
		System.out.println(page.toString());
	}

	private void process(NewClass page) {
		//Set the new name
		source.setName(page.getClassName());
		fix.includeModified(source);
	}

	private void process(NewRelator page){
		System.out.println(page.toString());

	}

	/* Operations with RefOntoUML from pages */

//	public String[] getStereotypesFor(StereotypeOntoUMLEnum stereotype) {
//		switch (stereotype) {
//		case CATEGORY:
//			return getStringRepresentationStereotype(setCategory);
//		case COLLECTIVE:
//			break;
//		case KIND:
//			return getStringRepresentationStereotype(setKind);
//		case MIXIN:
//			break;
//		case PHASE:
//			break;
//		case RELATOR:
//			return getStringRepresentationStereotype(setRelator);
//		case ROLE:
//			break;
//		case ROLEMIXIN:
//			break;
//		case SUBKIND:
//			break;
//		default:
//			break;
//
//		}
//		return null;
//	}
//
//	private String getStringRepresentationClassStereotype(Classifier c){
//		return ontoParser.getStringRepresentation(c);
//	}
//
//	private String[] getStringRepresentationClassStereotype(Set<Classifier> set){
//		String[] s = new String[set.size()];
//		int i = 0;
//		for (Classifier cls : set) {
//			s[i] = getStringRepresentationClassStereotype(cls);
//		}
//		return s;
//	}
//
//	private String getStringRepresentationClass(Classifier c){
//		String s = ontoParser.getStringRepresentation(c);
//		s = s.substring(0,s.indexOf(" "));
//		return s;
//	}
//
//	private String[] getStringRepresentationClass(Set<? extends Classifier> set){
//		String[] s = new String[set.size()];
//		int i = 0;
//		for (Classifier cls : set) {
//			s[i] = getStringRepresentationClass(cls);
//		}
//		return s;
//	}
//
//	private String getStringRepresentationStereotype(Classifier c){
//		String s = ontoParser.getStringRepresentation(c);
//		s = s.substring(s.indexOf(" ")+1);
//		return s;
//	}
//
//	private String[] getStringRepresentationStereotype(Set<? extends Classifier> set){
//		String[] s = new String[set.size()];
//		int i = 0;
//		for (Classifier cls : set) {
//			s[i] = getStringRepresentationStereotype(cls);
//		}
//		return s;
//	}
}