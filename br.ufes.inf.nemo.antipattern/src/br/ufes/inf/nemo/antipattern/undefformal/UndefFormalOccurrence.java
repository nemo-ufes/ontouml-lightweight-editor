package br.ufes.inf.nemo.antipattern.undefformal;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.FormalAssociation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class UndefFormalOccurrence extends AntipatternOccurrence {

	
	FormalAssociation formal;
	Classifier source, target;
	
	public FormalAssociation getFormal() {
		return formal;
	}

	public Classifier getSource() {
		return source;
	}

	public Classifier getTarget() {
		return target;
	}

	public UndefFormalOccurrence(FormalAssociation formal, UndefFormalAntipattern ap) throws Exception {
		super(ap);

		if (formal==null)
			throw new NullPointerException("UndefFormal: null formal association provided.");
		if (!(formal instanceof FormalAssociation))
			throw new Exception("UndefFormal: a formal association instance is required.");
		if (formal.getMemberEnd().size()!=2 )
			throw new Exception("UndefFormal: a formal association must own exactly 2 ends.");
		if (formal.getMemberEnd().get(0).getType()==null || formal.getMemberEnd().get(1).getType()==null)
			throw new Exception("UndefFormal: one of the end types is null");
		
		this.formal = formal;
		this.source = (Classifier) formal.getMemberEnd().get(0).getType();
		this.target = (Classifier) formal.getMemberEnd().get(1).getType();
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(formal);
		selection.add(source);
		selection.add(target);
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}

		
	@Override
	public String toString(){
		String result = "Source: "+super.parser.getStringRepresentation(this.source)+"\n"+
						"Target: "+super.parser.getStringRepresentation(this.target)+"\n"+
						"Formal: "+super.parser.getStringRepresentation(this.formal);
		
		return result;
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(formal);
	}

	// OUTCOMING FIXES ======================================================================
	
	public void changeToMediation(Association assoc, Classifier source, Classifier target) {
		Fix fixes = fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MEDIATION, false);
		
		Mediation mediation = fixes.getAddedByType(Mediation.class).get(0);
		
		Property mediatedEnd = mediation.getMemberEnd().get(1);
		Property relatorEnd = mediatedEnd.getOpposite();
		
		mediatedEnd.setIsReadOnly(true);
		
		if(mediatedEnd.getLower()==0)
			fixer.changePropertyMultiplicity(mediatedEnd, 1, mediatedEnd.getUpper(), false);
		if(relatorEnd.getLower()==0)
			fixer.changePropertyMultiplicity(relatorEnd, 1, relatorEnd.getUpper(), false);
		
		fix.addAll(fixes);
	}

	public void changeToCharacterization(Association assoc, Classifier source, Classifier target) {
		Fix fixes = fixer.changeRelationStereotypeTo(assoc, RelationStereotype.CHARACTERIZATION, false);
		Characterization characterization = fixes.getAddedByType(Characterization.class).get(0);
		
		Property characterizedEnd = characterization.getMemberEnd().get(1);
		characterizedEnd.setIsReadOnly(true);
		fixer.changePropertyMultiplicity(characterizedEnd, 1, 1, false);
		
		Property modeEnd = characterizedEnd.getOpposite();
		
		if(modeEnd.getLower()==0)
			fixer.changePropertyMultiplicity(modeEnd, 1, modeEnd.getUpper(), false);
		
		fix.addAll(fixes);
		
	}

	public void changeToMaterial(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MATERIAL, false));
	}
	
	public void changeToMaterial(Association assoc, Relator relator, String relatorName, 
			Mediation mediation1, String mediation1Name, String mediation1SourceMult, String mediation1TargetMult, 
			Mediation mediation2, String mediation2Name, String mediation2SourceMult, String mediation2TargetMult) {
		
		Fix currentFix = fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MATERIAL, false);
		MaterialAssociation material = currentFix.getAddedByType(MaterialAssociation.class).get(0);
		fix.addAll(currentFix);
		
		if(relator==null){
			relator = (Relator) fixer.createClass(ClassStereotype.RELATOR);
			relator.setName(relatorName);
			fixer.copyContainer(material, relator);
			fix.includeAdded(relator);
		}
		
		if(mediation1==null){
			currentFix = fixer.createAssociationBetween(RelationStereotype.MEDIATION, mediation1Name, relator, source);
			mediation1 = currentFix.getAddedByType(Mediation.class).get(0);
			fix.addAll(currentFix);
			fixer.changePropertyMultiplicity(mediation1.getMemberEnd().get(0), mediation1SourceMult);
			fixer.changePropertyMultiplicity(mediation1.getMemberEnd().get(1), mediation1TargetMult);
		}
		else{
			fix.addAll(fixer.changePropertyMultiplicity(mediation1.getMemberEnd().get(0), mediation1SourceMult));
			fix.addAll(fixer.changePropertyMultiplicity(mediation1.getMemberEnd().get(1), mediation1TargetMult));
		}
		
		if(mediation2==null){
			currentFix = fixer.createAssociationBetween(RelationStereotype.MEDIATION, mediation2Name, relator, target);
			mediation2 = currentFix.getAddedByType(Mediation.class).get(0);
			fix.addAll(currentFix);
			fixer.changePropertyMultiplicity(mediation2.getMemberEnd().get(0), mediation2SourceMult);
			fixer.changePropertyMultiplicity(mediation2.getMemberEnd().get(1), mediation2TargetMult);
		}
		else{
			fix.addAll(fixer.changePropertyMultiplicity(mediation2.getMemberEnd().get(0), mediation2SourceMult));
			fix.addAll(fixer.changePropertyMultiplicity(mediation2.getMemberEnd().get(1), mediation2TargetMult));
		}
		
		fix.addAll(fixer.createAssociationBetween(RelationStereotype.DERIVATION, "", relator, material));
		fixer.deriveMaterialMultiplicities(material,mediation1,mediation2);
	}

	public void changeToSubCollectionOfSrcWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.SUBCOLLECTIONOF, false));
	}

	public void changeToSubCollectionOfTgtWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.SUBCOLLECTIONOF, true));
	}

	public void changeToMemberOfSrcWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MEMBEROF, false));
	}

	public void changeToMemberOfTgtWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MEMBEROF, true));
	}

	public void changeToSubQuantityOfSrcWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.SUBQUANTITYOF, false));
	}

	public void changeToSubQuantityOfTgtWhole(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.SUBQUANTITYOF, true));
	}

	public void createDatatypesAttributesAndConstraint(Association assoc, Classifier source, Classifier target, 
	HashMap<String,String> sourceMapType, HashMap<String,String> targetMapType,	HashMap<String,String> sourceMapStereo, HashMap<String,String> targetMapStereo,	String constraints)	 
	{
		//create attributes and datatypes/primitivetypes
		for(String name: sourceMapType.keySet()){
			if (sourceMapStereo.get(name).equals("PrimitiveType")) fix.addAll(fixer.createAttribute(source, name, ClassStereotype.PRIMITIVETYPE, sourceMapType.get(name)));
			if (sourceMapStereo.get(name).equals("DataType")) fix.addAll(fixer.createAttribute(source, name, ClassStereotype.DATATYPE, sourceMapType.get(name)));
		}
		for(String name: targetMapType.keySet()){
			if (targetMapStereo.get(name).equals("PrimitiveType")) fix.addAll(fixer.createAttribute(target, name, ClassStereotype.PRIMITIVETYPE, targetMapType.get(name)));
			if (targetMapStereo.get(name).equals("DataType")) fix.addAll(fixer.createAttribute(target, name, ClassStereotype.DATATYPE, targetMapType.get(name)));
		}
		//set formal is derived
		assoc.setIsDerived(true);
		//include constraint
		fix.includeRule(constraints);
	}

	public void setUpperMult(Association assoc, Classifier source,	Classifier target, int upper) 
	{
		if (assoc instanceof Mediation){
			fix.addAll(fixer.setUpperCardinalityOnMediatedSide((Mediation)assoc, upper));
		}		
	}

	public void createNewMediatedTypes(HashMap<String, String> newMediatedMap) 
	{		
		Mediation med=null;
		for(Object obj: fix.getAdded()){
			if (obj instanceof Mediation){
				med = (Mediation)obj;
			}
		}
		if (med != null){
			fix.addAll(fixer.createNewMediatedTypes((Relator)med.relator(), newMediatedMap));
		}
	}
	
	public String getFormalName(){
		String formalName = getFormal().getName();
		if(formalName==null || formalName.trim().isEmpty())
			formalName = "unnamed";
		
		return formalName;
	}

	public void removeFormal() {
		fix.addAll(fixer.deleteElement(getFormal()));
	}

	public void changeToComponentOf(Classifier newSource, Classifier newTarget) {
		
		if(this.source.equals(newSource) && this.target.equals(newTarget))
			fix.addAll(fixer.changeRelationStereotypeTo(formal, RelationStereotype.COMPONENTOF, false));
		else if (this.source.equals(newTarget) && this.target.equals(newSource)){
			fix.addAll(fixer.changeRelationStereotypeTo(formal, RelationStereotype.COMPONENTOF, true));
		}
	}

}
