package br.ufes.inf.nemo.validator.meronymic;

import java.util.ArrayList;

import javax.swing.JDialog;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.ui.FixDialog;

public abstract class MeronymicItem {

	protected Fix fix;
	protected OutcomeFixer fixer;
	protected OntoUMLParser parser;

	/**Properties of MeronymicPath*/
	protected ArrayList<Property> path;
	protected ArrayList<Meronymic> meronymicPath;
	protected Classifier whole;
	protected Classifier part;
	
	/**Properties for actions*/
	protected Enum<?> action;
	protected RelationStereotype newStereotype;
	protected Meronymic relationToRemove;
	protected Meronymic relationToReverse;
	protected Meronymic relationToChange;
	
	public abstract FixDialog<?> createDialog(JDialog parent);
	public abstract Fix fix();
	
	
	public MeronymicItem(OntoUMLParser parser) {
		this.parser = parser;
		
		fix = new Fix();
		fixer = new OutcomeFixer(parser.getModel());
		path = new ArrayList<Property>();
		meronymicPath = new ArrayList<Meronymic>();
	}
	
	public Fix getFix() {
		return fix;
	}
	
	public static String getPathText(ArrayList<Property> path){
		
		String result = path.get(0).getOpposite().getType().getName();
		
		for (int i = 0; i < path.size(); i++) {
			Classifier part = (Classifier) path.get(i).getType();
			Classifier nextWhole;
			
			if(i<path.size()-1){
				result += ", ";
				nextWhole = (Classifier) path.get(i+1).getOpposite().getType();
			}
			else{
				result += " and ";
				nextWhole = (Classifier) path.get(0).getOpposite().getType();
			}
			
			result += part.getName();
			
			if(nextWhole.allParents().contains(part))
				result += " (Sub: "+nextWhole.getName()+")";
			if(nextWhole.allChildren().contains(part))
				result += " (Super: "+nextWhole.getName()+")";
		}

		return result;
	}
	
	public static String getFullPathText(ArrayList<Property> path){
		String text = "";

		int i = 0;
		for (Property p : path) {
			if(i!=0)
				text += "\n";
			text += OntoUMLNameHelper.getCompleteName(p.getAssociation());
			i++;
		}
		
		return text;
		
	}
	
	public String getPathString() {
		String result = OntoUMLNameHelper.getName(getWhole());
		
		for (Property p : path) {
			result += " -> "+OntoUMLNameHelper.getName(p.getType());
		}
		
		return result;
	}

	public void setPath(ArrayList<Property> path) {
		this.path.clear();
		this.path.addAll(path);
		
		this.meronymicPath.clear();
		for (Property p : path) {
			meronymicPath.add((Meronymic) p.getAssociation());
		}
		
	}

	public ArrayList<Property> getPath() {
		return path;
	}
	
	public ArrayList<Meronymic> getMeronymicPath() {
		return meronymicPath;
	}
	
	public Classifier getWhole() {
		return whole;
	}

	public Classifier getPart() {
		return part;
	}
	
	
	/**ACTIONS*/
	public boolean hasAction() {
		return action!=null;
	}

	public Meronymic getRelationToReverse() {
		return relationToReverse;
	}
	
	public Meronymic getRelationToRemove() {
		return relationToRemove;
	}
	
	public void changeStereotype() {
		fix.addAll(fixer.changeRelationStereotypeTo(relationToChange, newStereotype));
	}

	public void reverse() {
		fix.addAll(fixer.invertEnds(relationToReverse));
	}
	
	public void remove() {
		fix.addAll(fixer.deleteElement(relationToRemove));
	}
	
	private void changeAllRelationsInPathTo(RelationStereotype stereotype){
		
		for (Property p : path) {
			Association a = p.getAssociation();
			if(fixer.getRelationshipStereotype(a)!=stereotype)
				fix.addAll(fixer.changeRelationStereotypeTo(a, stereotype));
		}
		
		return;
	}
	
	//ALWASYS CALL changeAllClassesInPathTo BEFORE changeAllRelationsInPathTo;
	public void changeAllClassesInPathTo(ClassStereotype newNature){
		ArrayList<Classifier> visited = new ArrayList<Classifier>();
		Classifier firstWhole = (Classifier) path.get(0).getOpposite().getType();
		
		fix.addAll(fixer.changeNature(firstWhole, visited, newNature));
		
		for (Property p : path) {
			visited = new ArrayList<Classifier>();
			fix.addAll(fixer.changeNature((Classifier)p.getType(), visited, newNature));
		}
		
		return;
	}
	
	//A SC WILL BE DERIVED. SC->SC->SC
	public void makeSubCollectionDerivationPath(){
		changeAllClassesInPathTo(ClassStereotype.COLLECTIVE);
		changeAllRelationsInPathTo(RelationStereotype.SUBCOLLECTIONOF);
	}
	
	//A CP WILL BE DERIVED. CP->CP->CP
	public void makeFunctionalDerivationPath(){
		changeAllClassesInPathTo(ClassStereotype.KIND);
		changeAllRelationsInPathTo(RelationStereotype.COMPONENTOF);
	}
	
	//A SQ WILL BE DERIVED. SQ->SQ->SQ
	public void makeSubQuantityDerivationPath(){
		changeAllClassesInPathTo(ClassStereotype.QUANTITY);
		changeAllRelationsInPathTo(RelationStereotype.SUBQUANTITYOF);
	}
	
	//NO RELATION WILL BE DERIVED. MB->MB->MB
	public void makeMembershipPath(){
		ArrayList<Classifier> visited = new ArrayList<Classifier>();
		Classifier firstWhole = (Classifier) path.get(0).getOpposite().getType();
		fix.addAll(fixer.changeNature(firstWhole, visited, ClassStereotype.COLLECTIVE));
		
		for (int i = 0; i < path.size()-1; i++) {
			visited = new ArrayList<Classifier>();
			fix.addAll(fixer.changeNature((Classifier)path.get(i).getType(), visited, ClassStereotype.COLLECTIVE));
		}
		
		Property lastProperty = path.get(path.size()-1);
		Classifier lastPart = (Classifier) lastProperty.getType();
		visited = new ArrayList<Classifier>();
		
		if(!parser.isFunctionalComplex(lastPart) & !parser.isCollective(lastPart))
			fix.addAll(fixer.changeNature(lastPart, visited, ClassStereotype.KIND));
		
		changeAllRelationsInPathTo(RelationStereotype.MEMBEROF);
		
	}
	
	//A MB WILL BE DERIVED. SC->SC->MB
	public Fix makeMembershipDerivationPath(){
		
		ArrayList<Classifier> visited = new ArrayList<Classifier>();
		Classifier firstWhole = (Classifier) path.get(0).getOpposite().getType();
		fix.addAll(fixer.changeNature(firstWhole, visited, ClassStereotype.COLLECTIVE));
		
		for (int i = 0; i < path.size()-1; i++) {
			visited = new ArrayList<Classifier>();
			fix.addAll(fixer.changeNature((Classifier)path.get(i).getType(), visited, ClassStereotype.COLLECTIVE));
		}
		
		for (int i = 0; i < path.size()-1; i++) {
			Association a = path.get(i).getAssociation();
			if(!(a instanceof subCollectionOf))
				fix.addAll(fixer.changeRelationStereotypeTo(a, RelationStereotype.SUBCOLLECTIONOF));
		}
		
		Property lastProperty = path.get(path.size()-1);
		Classifier lastPart = (Classifier) lastProperty.getType();
		visited = new ArrayList<Classifier>();
		
		if(!parser.isFunctionalComplex(lastPart) & !parser.isCollective(lastPart))
			fix.addAll(fixer.changeNature(lastPart, visited, ClassStereotype.KIND));
		
		if(!(lastProperty.getAssociation() instanceof memberOf))
			fix.addAll(fixer.changeRelationStereotypeTo(lastProperty.getAssociation(), RelationStereotype.MEMBEROF));
		
		return fix;
	}
}
