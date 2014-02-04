package br.ufes.inf.nemo.antipattern.undefformal;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.Mode;
import RefOntoUML.ObjectClass;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

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
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}

	public boolean isBetweenRelatorAndObject()
	{
		return (source instanceof Relator && target instanceof ObjectClass) || (source instanceof ObjectClass && target instanceof Relator);
	}
	
	public boolean isBetweenModeAndAnyOther()
	{
		return (source instanceof Mode && target instanceof RefOntoUML.Class) || (source instanceof RefOntoUML.Class && target instanceof Mode);
	}

	public boolean isBetweenModes()
	{
		return source instanceof Mode && target instanceof Mode;
	}
	
	public boolean isBetweenQuantities()
	{
		return source instanceof Quantity && target instanceof Quantity;
	}

	public boolean isBetweenCollectives()
	{
		return source instanceof Collective && target instanceof Collective;
	}
	
	public boolean isBetweenObjects()
	{
		return source instanceof ObjectClass && target instanceof ObjectClass;
	}
	
	public boolean isBetweenCollectiveAndFunctional()
	{
		return (source instanceof Collective && isFunctionalComplex(target)) || (target instanceof Collective && isFunctionalComplex(source));
	}
	
	public boolean isBetweenFunctionals()
	{
		return isFunctionalComplex(source) && isFunctionalComplex(target);
	}
	
	public boolean isFunctionalComplex(Type type)
	{
		boolean isFunctionalComplex =false;
		if (type instanceof Kind) isFunctionalComplex = true; 
		if (type instanceof SubKind) isFunctionalComplex = true;
		if (type instanceof Phase) isFunctionalComplex = true;
		if (type instanceof Role) isFunctionalComplex = true;
		if (type instanceof Category){
			isFunctionalComplex = true;
			for(Generalization gen: parser.getSpecializations((Classifier)type)){
				if (!(gen.getSpecific() instanceof Kind)) isFunctionalComplex = false;
			}
		}
		return isFunctionalComplex;
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
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MEDIATION, false));
	}

	public void changeToCharacterization(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.CHARACTERIZATION, false));
	}

	public void changeToMaterial(Association assoc, Classifier source, Classifier target) {
		fix.addAll(fixer.changeRelationStereotypeTo(assoc, RelationStereotype.MATERIAL, false));
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

}
