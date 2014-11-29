package br.ufes.inf.nemo.antipattern.relrig;


import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class RelRigOccurrence extends AntipatternOccurrence {
	
	private Relator relator;	
	private ArrayList<Mediation> allMediations = new ArrayList<Mediation>(), rigidMediations = new ArrayList<Mediation>();
	private ArrayList<Property> allMediatedProperties = new ArrayList<Property>(), rigidMediatedProperties = new ArrayList<Property>();
		
	public ArrayList<Mediation> getAllMediations() {
		return allMediations;
	}
	
	public OntoUMLParser getOntoUMLParser(){ return parser; }

	public ArrayList<Mediation> getRigidMediations() {
		return rigidMediations;
	}

	public Mediation getRigidMediation (EObject rigidType){
		for(Mediation med: rigidMediations){
			if (med.getMemberEnd().get(0).getType().equals(rigidType)) return med;
			if (med.getMemberEnd().get(1).getType().equals(rigidType)) return med;
		}
		return null;
	}
	
	public ArrayList<Property> getAllMediatedProperties() {
		return allMediatedProperties;
	}

	public ArrayList<Property> getRigidMediatedProperties() {
		return rigidMediatedProperties;
	}
	
	public RelRigOccurrence(Relator relator, RelRigAntipattern ap, HashSet<Property> allMediatedProperties, HashSet<Property> rigidMediatedProperties){
		super(ap);
		
		this.relator = relator;
		this.allMediatedProperties.addAll(allMediatedProperties);
		this.rigidMediatedProperties.addAll(rigidMediatedProperties);
		
		for (Property p : rigidMediatedProperties) {
			if(p.getAssociation() instanceof Mediation)
				rigidMediations.add((Mediation) p.getAssociation());
		}
		
		for (Property p : allMediatedProperties) {
			if(p.getAssociation() instanceof Mediation)
				allMediations.add((Mediation) p.getAssociation());
		}
	}
	
	public RelRigOccurrence(Relator relator, RelRigAntipattern ap) throws Exception 
	{
		super(ap);
		
		this.setRelator(relator, parser);
			
	}
	
	private void setRelator (Relator relator, OntoUMLParser parser) throws Exception {
		
		if(relator==null)
			throw new NullPointerException("RelRigid: Relator is null");
		
		this.relator = relator;  
		
		for (Mediation med : parser.getMediations(relator)) {
			if(!OntoUMLParser.getRelator(med).equals(relator))
				throw new Exception("RelRigid: Mediation '"+med.getName()+"' does not belong to Relator '"+relator.getName()+"'");
			
			allMediations.add(med);
			allMediatedProperties.add(OntoUMLParser.getMediatedEnd(med));
			
			if (OntoUMLParser.getMediatedType(med) instanceof RigidSortalClass || OntoUMLParser.getMediatedType(med) instanceof RigidMixinClass){
				rigidMediations.add(med);
				rigidMediatedProperties.add(OntoUMLParser.getMediatedEnd(med));
			}
		}
		
		if(rigidMediations.size()==0)
			throw new Exception("Relator '"+relator.getName()+"' does not mediate any rigid type");
			
	}
	
	public Relator getRelator() {
		return relator;
	}

	/**
	 * Select in the OntoUML model only those elements related with this antipattern...
	 */
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(relator);
		selection.addAll(this.rigidMediations);
				
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	
	/**
	 * To String method...
	 * 
	 */
	@Override
	public String toString() {
		
		String result;

		result =OntoUMLNameHelper.getTypeAndName(this.relator, true, false)+
				"\nRigid Mediated Types:";
		
		for (Property p : this.rigidMediatedProperties) {
			result += "\n\t"+OntoUMLNameHelper.getNameTypeAndMultiplicity(p, true, false, true, true, false);
		}
		
		if(allMediatedProperties.size()==rigidMediatedProperties.size())
			return result;
		
		result += "\nOther Mediated Types:";
		
		for (Property p : this.allMediatedProperties){
			if (!this.rigidMediatedProperties.contains(p))
				result += "\n\t"+OntoUMLNameHelper.getNameTypeAndMultiplicity(p, true, false, true, true, false);
		}
		
		return result;
	}
	
	public String explanation() {
	
		String expl = "";
		
		expl += "The relator type "+this.relator.getName()+" mediates "+this.rigidMediatedProperties.size()+" rigid type(s):";
		
		for (int i = 0; i < rigidMediatedProperties.size(); i++) {
			Property p = rigidMediatedProperties.get(i);
			
			if(i>0 && i!= rigidMediatedProperties.size()-1)
				expl += ",";
			
			else if (i == rigidMediatedProperties.size()-1)
				expl += " and";
			
			expl += " "+p.getType().getName();
			
		}
		
		return expl;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(relator);
	}
	
	// ========== OUTCOMING FIXES =========
	
	public void changeToRoleOrRoleMixin(EObject rigid)
	{
		if (rigid instanceof SubKind){			
			this.fix.addAll(fixer.changeClassStereotypeTo(rigid, ClassStereotype.ROLE));			
		}
		if (rigid instanceof SubstanceSortal){
			this.fix.addAll(fixer.changeClassStereotypeSubtyping(rigid, ClassStereotype.ROLE));
		}
		if(rigid instanceof Category){
			if (((Category) rigid).allParents().isEmpty()){
				this.fix.addAll(fixer.changeClassStereotypeSubtyping(rigid, ClassStereotype.ROLEMIXIN));
			}else{
				this.fix.addAll(fixer.changeClassStereotypeTo(rigid, ClassStereotype.ROLEMIXIN));
			}
		}
	}
		
	public void changeToMode(EObject rigid, EObject rigidMediation)
	{				
		this.fix.addAll(fixer.changeRelationStereotypeTo(rigidMediation, RelationStereotype.CHARACTERIZATION));
		this.fix.addAll(fixer.changeClassStereotypeTo(rigid, ClassStereotype.MODE));
	}
	
	public void setBothReadOnly (EObject mediation)
	{		
		if (mediation instanceof Mediation){
			Mediation med = (Mediation)mediation;
			med.getMemberEnd().get(0).setIsReadOnly(true);
			med.getMemberEnd().get(1).setIsReadOnly(true);
		}		
		this.fix.includeModified(mediation);
	}
	
	public void createRoleSubType(EObject rigid, EObject rigidMediation)
	{
		if(rigid instanceof SubKind || rigid instanceof SubstanceSortal){
			this.fix.addAll(fixer.createSubTypeAsInvolvingLink(rigid, ClassStereotype.ROLE,rigidMediation));
		}
		if(rigid instanceof Category){
			this.fix.addAll(fixer.createSubTypeAsInvolvingLink(rigid, ClassStereotype.ROLEMIXIN,rigidMediation));
		}
	}
}


