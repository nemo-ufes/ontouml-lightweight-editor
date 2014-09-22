package br.ufes.inf.nemo.common.ontoumlparser;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public class OntoUMLUtil {

	private static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
	
	public static Generalization createGeneralization (Classifier specific, Classifier general){
		
		Generalization g = factory.createGeneralization();
		
		g.setSpecific(specific);
		g.setGeneral(general);
		
		return g;
	}
	
	public static Association createAssociation (Classifier source, Classifier target){
		Association assoc = factory.createAssociation();
		addAssociationEnds(assoc, source, target);
		return assoc;
	}
	
	public static MaterialAssociation createMaterialAssociation (Classifier source, Classifier target){
		MaterialAssociation material = factory.createMaterialAssociation();
		addAssociationEnds(material, source, target);
		return material;
	}
	
	public static FormalAssociation createFormalAssociation (Classifier source, Classifier target){
		FormalAssociation formal = factory.createFormalAssociation();
		addAssociationEnds(formal, source, target);
		return formal;
	}
	
	public static Mediation createMediation (Classifier source, Classifier target){
		Mediation mediation = factory.createMediation();
		
		if(target instanceof Relator && !(source instanceof Relator))
			addAssociationEnds(mediation, target, source);
		else
			addAssociationEnds(mediation, source, target);
		
		mediation.targetEnd().setIsReadOnly(true);
		
		return mediation;
	}
	
	public static Characterization createCharacterization (Classifier source, Classifier target){
		Characterization characterization = factory.createCharacterization();
		
		if(target instanceof Mode && !(source instanceof Mode))
			addAssociationEnds(characterization, target, source);
		else
			addAssociationEnds(characterization, source, target);
		
		characterization.targetEnd().setIsReadOnly(true);
		
		return characterization;
	}
	
	public static componentOf createComponentOf (Classifier source, Classifier target){
		componentOf compOf = factory.createcomponentOf();
		addAssociationEnds(compOf, source, target);
		
		compOf.sourceEnd().setAggregation(AggregationKind.COMPOSITE);
		return compOf;
	}
	
	public static memberOf createMemberOf (Classifier source, Classifier target){
		memberOf membOf = factory.creatememberOf();
		addAssociationEnds(membOf, source, target);
		
		membOf.sourceEnd().setAggregation(AggregationKind.COMPOSITE);
		return membOf;
	}
	
	public static subCollectionOf createSubCollectionOf (Classifier source, Classifier target){
		subCollectionOf subColl = factory.createsubCollectionOf();
		addAssociationEnds(subColl, source, target);
		
		subColl.sourceEnd().setAggregation(AggregationKind.COMPOSITE);
		return subColl;
	}
	
	public static subQuantityOf createSubQuantityOf (Classifier source, Classifier target){
		subQuantityOf subQuan = factory.createsubQuantityOf();
		addAssociationEnds(subQuan, source, target);
		
		subQuan.sourceEnd().setAggregation(AggregationKind.COMPOSITE);
		
		if(target instanceof Role || target instanceof Phase || target instanceof RoleMixin)
			subQuan.setIsEssential(false);
		else
			subQuan.setIsEssential(true);
		
		subQuan.setIsImmutablePart(true);
		subQuan.setIsInseparable(false);
		
		return subQuan;
	}
	
	public static Derivation createDerivation (Classifier source, Association target){
		Derivation derivation = factory.createDerivation();
		addAssociationEnds(derivation,source,target);
		return derivation;
	}
	
	
	public static void addAttribute (Classifier classifier, Type type){
		addAttribute(classifier, type, 1, 1, null, false, false, AggregationKind.NONE);
	}
	
	public static void addAttribute (Classifier classifier, Type type, int lower, int upper, String name,
			boolean isDerived, boolean isReadOnly, AggregationKind aggreggation){
		
		Property attribute = createProperty(type, lower, upper, name, isDerived, isReadOnly, aggreggation);
		
		if (classifier instanceof Class){
			Class c = (Class) classifier; 
			c.getOwnedAttribute().add(attribute);
		}
		
		else if (classifier instanceof DataType){
			DataType dt = (DataType) classifier;
			dt.getOwnedAttribute().add(attribute);
		}
	}
	
	public static void addAssociationEnds (Association a, Type source, Type target){
		addAssociationEnd(a, source);
		addAssociationEnd(a, target);
	}
	
	public static void addAssociationEnd (Association a, Type type){
		
		addAssociationEnd(a, type, 1, 1, null, false, false, AggregationKind.NONE);
		
	}
	
	public static void addAssociationEnd (Association a, Type type, int lower, int upper, String name,
			boolean isDerived, boolean isReadOnly, AggregationKind aggreggation){
		
		Property associationEnd = createProperty(type, lower, upper, name, isDerived, isReadOnly, aggreggation);
		
		a.getMemberEnd().add(associationEnd);
		a.getOwnedEnd().add(associationEnd);
		
	}
	
	public static Property createProperty(Type type){
		return createProperty(type, 1, 1, null, false, false, AggregationKind.NONE);
	}
	
	public static Property createProperty (Type type, int lower, int upper, String name,
			boolean isDerived, boolean isReadOnly, AggregationKind aggreggation){
		
		Property p = factory.createProperty();
		LiteralInteger lowerValue = factory.createLiteralInteger();
        LiteralUnlimitedNatural upperValue = factory.createLiteralUnlimitedNatural();
		
        lowerValue.setValue(lower);
		upperValue.setValue(upper);
		
        if(name==null || name.trim().isEmpty()){
        	
        	if (type.getName()==null || type.getName().trim().isEmpty())
        		name = "property";
        	else
        		name = type.getName();
        }
        	
        name = name.toLowerCase();
        
        p.setName(name);
        p.setType(type);
		p.setLowerValue(lowerValue);
		p.setUpperValue(upperValue);
		p.setIsDerived(isDerived);
		p.setIsReadOnly(isReadOnly);
        
		if(aggreggation==null)
			aggreggation = AggregationKind.NONE;
		
		p.setAggregation(aggreggation);
		
        return p;
	}
	
}
