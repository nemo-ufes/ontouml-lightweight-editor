package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.Mode;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class HomoFuncOccurrence extends AntipatternOccurrence {
	
	private Classifier whole;
	
	public Classifier getWhole() {
		return whole;
	}

	public Property getPartEnd() {
		return partEnd;
	}

	private Property partEnd;
	
	public Classifier getWholeIdentityProvider()
	{
		if (whole instanceof SubstanceSortal) return whole;
		if (whole instanceof AntiRigidSortalClass || whole instanceof SubKind){
			for(Classifier c: whole.allParents()){
				if(c instanceof SubstanceSortal) return c;
			}
		}
		return null;		
	}
	
	public HomoFuncOccurrence(Association compOf, HomoFuncAntipattern ap) throws Exception {
		super(ap);
		
		if (compOf==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		if (compOf instanceof componentOf){
			
			//discovers which end represents the part and the whole			
			if(compOf.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE){
				this.whole = (Classifier) compOf.getMemberEnd().get(0).getType();
				this.partEnd = compOf.getMemberEnd().get(1);
			} else{
				this.whole = (Classifier) compOf.getMemberEnd().get(1).getType();
				this.partEnd = compOf.getMemberEnd().get(0);
			}
			
		}else
			throw new Exception("HomoFunc: provided relation must be an instance of componentOf.");
		
		if(whole==null || partEnd==null)
			throw new NullPointerException("HomoFunc: null inputs!");
		
		if(whole instanceof Collective || whole instanceof Quantity || whole instanceof Relator || whole instanceof Mode)
			throw new Exception("HomoFunc: whole type not acceptable. Whole must be a functional complex");
		
		if (!(partEnd.getAssociation() instanceof componentOf))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation.");
		
		if (!partEnd.getOpposite().getType().equals(whole))
			throw new Exception("HomoFunc: partEnd must refer to componentOf relation that is connected to the provided whole");
		
		
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.whole);
		selection.add(this.partEnd.getAssociation());
		selection.add(this.partEnd.getType());
				
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = 
				"Functional Complex: "+super.parser.getStringRepresentation(this.whole) + "\n" +
				"Part: "+super.parser.getStringRepresentation(partEnd);
		return result;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(whole);
	}

	//====================================
	// OUTCOMING FIXES
	//====================================
	
	public void changeToCollective(Element element) 
	{
		if(element instanceof SubstanceSortal)
		{
			fix.addAll(fixer.changeClassStereotypeTo(element, ClassStereotype.COLLECTIVE));
		}
		if(element instanceof AntiRigidSortalClass || element instanceof SubKind)
		{
			for(Generalization gen: ((Classifier)element).getGeneralization()){
				fix.includeDeleted(gen);
			}
			fix.addAll(fixer.changeClassStereotypeTo(element, ClassStereotype.COLLECTIVE));
		}
	}

	public void changeToMemberOf(Element element) {
		fix.addAll(fixer.changeRelationStereotypeTo(element, RelationStereotype.MEMBEROF));		
	}

	public void changeToSubCollectionOf(Element element) {
		fix.addAll(fixer.changeRelationStereotypeTo(element, RelationStereotype.SUBCOLLECTIONOF));		
	}

	public void createNewIdentityProvider(Element element) {
		
		for(Generalization gen: ((Classifier)element).getGeneralization()){
			fix.includeDeleted(gen);
		}
		fix.addAll(fixer.createSuperTypeAs(element, ClassStereotype.COLLECTIVE));
	}

	public void createNewPartToWhole(String partStereotype, String partName, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) 
	{
		fix.addAll(fixer.createPartWithComponentOfTo(whole, fixer.getClassStereotype(partStereotype),partName,componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}
	
	public void createNewSubPartToWhole(String partStereotype, String partName, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole)
	{
		fix.addAll(fixer.createSubPartWithSubComponentOfTo(whole, partEnd, fixer.getClassStereotype(partStereotype),partName,componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}

	public void createSubComponentOfToExistingSubPart(Type subpart, String componentOfName, boolean isEssential, boolean isInseparable, boolean isShareable, boolean isImmutablePart, boolean isImmutableWhole) 
	{
		fix.addAll(fixer.createSubComponentOfToExistingSubPart(whole,partEnd,subpart, componentOfName,isEssential,isInseparable,isShareable,isImmutablePart,isImmutableWhole));
	}
	
}
