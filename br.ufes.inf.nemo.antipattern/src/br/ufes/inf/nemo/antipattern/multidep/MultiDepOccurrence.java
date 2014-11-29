package br.ufes.inf.nemo.antipattern.multidep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.ObjectClass;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RoleMixin;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

//Multiple Relational Dependency
public class MultiDepOccurrence extends AntipatternOccurrence{

	Classifier type;
	ArrayList<Property> relatorEnds;

	public Classifier getType() {
		return type;
	}

	public ArrayList<Property> getRelatorEnds() {
		return relatorEnds;
	}

	public MultiDepOccurrence (Classifier type, MultiDepAntipattern ap) throws Exception{
		super(ap);
		
		if(type==null)
			throw new NullPointerException("MultiDep: type is null");
		
		if(!(type instanceof ObjectClass))
			throw new Exception("MultiDep: type must be an ObjectClass");
		
		this.type = type;
		this.relatorEnds = new ArrayList<Property>();
		
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			
			Property source = m.getMemberEnd().get(0);
			Property target = m.getMemberEnd().get(1);
			
			if(source.getType().equals(type) && target.getType() instanceof Relator)
				relatorEnds.add(target);
			if(target.getType().equals(type) && source.getType() instanceof Relator)
				relatorEnds.add(source);	
		}
		
		if (relatorEnds.size()<2){
			throw new Exception("MultiDep: Classifier doesn't depend on multiple types.");
			
		}
	}
	
	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(type);
		selection.addAll(relatorEnds);
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
		
	}
	
	@Override
	public String toString(){
		String result = "Type: "+OntoUMLNameHelper.getTypeAndName(type, true, false)+"\n"+
						"Relators: ";
		
		for (Property p : relatorEnds){
			result += "\n\t"+OntoUMLNameHelper.getNameTypeAndMultiplicity(p, true, false, true, false, false);
		}
		
		return result;
		
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(this.type);
	}

	
	// OUTCOMING FIXER =====================================================
	//======================================================================
		
	public void createFormalAssociations(ArrayList<ArrayList<Property>> matrix) 
	{
		for(ArrayList<Property> list: matrix){
			if (list.size()==2){
				fix.addAll(fixer.createAssociationBetween(RelationStereotype.FORMAL, "", list.get(0).getType(), list.get(1).getType()) );
			}
		}		
	}

	public void addSubtypesPerProperty(ArrayList<Property> properties) 
	{
		ArrayList<Generalization> genList = new ArrayList<Generalization>();
		for(Property p : properties)
		{
			Fix partial = fixer.createSubTypeAsInvolvingLink(getType(), getSubtypeStereotype(type), p.getAssociation());
			Class subtype = partial.getAddedByType(Class.class).get(0);
			subtype.setName("MediatedBy_"+OntoUMLNameHelper.getName(p.getType()));
			for(Object obj: fix.getAdded()) { if (obj instanceof Generalization) { genList.add((Generalization)obj); }}
			
			fix.addAll(partial);
		}
		if (genList.size()>1) fix.addAll(fixer.createGeneralizationSet(genList, false, true, ""));
	}

	public void addOrderedSubtypes(HashMap<Property, Integer> order) {
		
		ArrayList<Integer> valuesList = new ArrayList<Integer>(new HashSet<Integer>(order.values()));
		Collections.sort(valuesList);
		
		Classifier currentParent = type;
		Fix currentFix;
		while(valuesList.size()>0){
			ArrayList<Property> samePositionList = new ArrayList<Property>();
			
			for (Property p : order.keySet()) {
				if(order.get(p)==valuesList.get(0)){
					samePositionList.add(p);
				}
			}
			
			if(samePositionList.size()==1){
				Property p = samePositionList.get(0);
				currentFix = fixer.createSubTypeAs(currentParent, getSubtypeStereotype(currentParent), "MediatedBy_"+OntoUMLNameHelper.getName(p.getType()));
				currentParent = currentFix.getAddedByType(RefOntoUML.Class.class).get(0);
				p.getOpposite().setType(currentParent);
				currentFix.includeModified(p.getAssociation());
				
				fix.addAll(currentFix);
			}
			else{
				if(valuesList.size()>1){
					currentFix = fixer.createSubTypeAs(currentParent, getSubtypeStereotype(currentParent), "SubtypeOf_"+OntoUMLNameHelper.getName(currentParent));
					currentParent = currentFix.getAddedByType(RefOntoUML.Class.class).get(0);
					fix.addAll(currentFix);
				}
				
				ArrayList<Classifier> subtypes = new ArrayList<Classifier>();
				Class subtype;
				for (Property p : samePositionList) {
					currentFix = fixer.createSubTypeAs(currentParent, getSubtypeStereotype(currentParent), "MediatedBy_"+OntoUMLNameHelper.getName(p.getType()));
					subtype = currentFix.getAddedByType(RefOntoUML.Class.class).get(0);
					subtypes.add(subtype);
					p.getOpposite().setType(subtype);
					currentFix.includeModified(p.getAssociation());
					fix.addAll(currentFix);
				}
				
				fix.addAll(fixer.createGeneralizationSet(currentParent, subtypes, false, true));
			}
			valuesList.remove(0);	
		}
	}
	
	public ClassStereotype getSubtypeStereotype(Classifier c){
		
		if(c instanceof Category || c instanceof RoleMixin)
			return ClassStereotype.ROLEMIXIN;
		if(c instanceof Mixin)
			return ClassStereotype.MIXIN;
		
		return ClassStereotype.ROLE;
	}
}
