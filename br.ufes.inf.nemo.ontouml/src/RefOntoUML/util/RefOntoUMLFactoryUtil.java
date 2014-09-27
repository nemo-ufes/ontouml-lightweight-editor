package RefOntoUML.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public class RefOntoUMLFactoryUtil {

	public static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;

	public static RefOntoUML.Package createPackage(String name)
	{
		RefOntoUML.Package p = factory.createPackage();
		if(name!=null)p.setName(name);
		else p.setName("");
		return p;
	}
	
	public static GeneralizationSet createGeneralizationSet(boolean isDisjoint, boolean isCovering, RefOntoUML.Package container) 
	{
		GeneralizationSet gs = factory.createGeneralizationSet();
		gs.setIsCovering(isCovering);
		gs.setIsDisjoint(isDisjoint);
		gs.setName("");
		if(container!=null)container.getPackagedElement().add(gs);		
		return gs;
	}
	
	public static GeneralizationSet createGeneralizationSet(List<Generalization> generalizations, boolean isDisjoint, boolean isCovering, RefOntoUML.Package container) 
	{
		GeneralizationSet gs = factory.createGeneralizationSet();
		gs.setIsCovering(isCovering);
		gs.setIsDisjoint(isDisjoint);
		gs.setName("");
		if(generalizations!=null) gs.getGeneralization().addAll(generalizations);
		for(Generalization gen: generalizations) gen.getGeneralizationSet().add(gs);
		if(container!=null)container.getPackagedElement().add(gs);
		return gs;
	}
	
	public static GeneralizationSet createGeneralizationSet(List<Generalization> generalizations, String name, boolean isDisjoint, boolean isCovering, RefOntoUML.Package container) 
	{
		GeneralizationSet gs = factory.createGeneralizationSet();
		gs.setIsCovering(isCovering);
		gs.setIsDisjoint(isDisjoint);
		if(name!=null) gs.setName(name);
		else gs.setName("");
		if(generalizations!=null) gs.getGeneralization().addAll(generalizations);
		for(Generalization gen: generalizations) gen.getGeneralizationSet().add(gs);
		if(container!=null)container.getPackagedElement().add(gs);
		return gs;
	}
	
	/** Create a generalization between specific and general types */
	public static Generalization createGeneralization (Classifier specific, Classifier general)
	{		
		Generalization g = factory.createGeneralization();		
		g.setSpecific(specific);
		g.setGeneral(general);		
		return g;
	}
	
	/** Create an association between source and target types */
	public static Association createAssociation (Classifier source, Classifier target, RefOntoUML.Package container)
	{
		Association assoc = factory.createAssociation();
		addAssociationEnds(assoc, source, target);
		if(container!=null)container.getPackagedElement().add(assoc);
		return assoc;
	}
	
	/** Create an association between source and target types */
	public static Association createAssociation (Classifier source, int srcLower, int srcUpper, String name, Classifier target, int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		Association assoc = factory.createAssociation();
		List<Property> ends = addAssociationEnds(assoc, source, target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);
		if(name!=null) assoc.setName(name);
		else assoc.setName("");
		if(container!=null)container.getPackagedElement().add(assoc);
		return assoc;
	}
	
	/** Create a material association between source and target types */
	public static MaterialAssociation createMaterialAssociation (Classifier source, Classifier target, RefOntoUML.Package container){
		MaterialAssociation material = factory.createMaterialAssociation();
		addAssociationEnds(material, source, target);
		if(container!=null)container.getPackagedElement().add(material);
		return material;
	}
	
	/** Create a material association between source and target types */
	public static MaterialAssociation createMaterialAssociation (Classifier source, int srcLower, int srcUpper, String name, Classifier target, int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		MaterialAssociation material = factory.createMaterialAssociation();
		List<Property> ends = addAssociationEnds(material, source, target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);
		if(name!=null) material.setName(name);
		else material.setName("");
		if(container!=null)container.getPackagedElement().add(material);
		return material;
	}
	
	/** Create a formal association between source and target types */
	public static FormalAssociation createFormalAssociation (Classifier source, Classifier target, RefOntoUML.Package container)
	{
		FormalAssociation formal = factory.createFormalAssociation();
		addAssociationEnds(formal, source, target);
		if(container!=null)container.getPackagedElement().add(formal);
		return formal;
	}

	/** Create a formal association between source and target types */
	public static FormalAssociation createFormalAssociation (Classifier source, int srcLower, int srcUpper, String name, Classifier target, int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		FormalAssociation formal = factory.createFormalAssociation();
		List<Property> ends = addAssociationEnds(formal, source, target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);
		if(name!=null) formal.setName(name);
		else formal.setName("");
		if(container!=null)container.getPackagedElement().add(formal);
		return formal;
	}
		
	/** Create a mediation between source and target types */
	public static Mediation createMediation (Classifier source, Classifier target, RefOntoUML.Package container)
	{
		Mediation mediation = factory.createMediation();		
		if(target instanceof Relator && !(source instanceof Relator)) addAssociationEnds(mediation, target, source);
		else addAssociationEnds(mediation, source, target);		
		if(container!=null)container.getPackagedElement().add(mediation);
		return mediation;
	}

	/** Create a mediation between source and target types */
	public static Mediation createMediation (Classifier source, int srcLower, int srcUpper, String name, Classifier target, int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		Mediation mediation = factory.createMediation();
		List<Property> ends = null;		
		if(target instanceof Relator && !(source instanceof Relator)) ends = addAssociationEnds(mediation, target, source);
		else ends = addAssociationEnds(mediation, source, target);		
		setMultiplicity(ends.get(0), srcLower, srcUpper);		
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);		
		if(name!=null) mediation.setName(name);
		else mediation.setName("");
		if(container!=null)container.getPackagedElement().add(mediation);
		return mediation;
	}
	
	/** Create a characterization between source and target types */
	public static Characterization createCharacterization (Classifier source, Classifier target, RefOntoUML.Package container)
	{
		Characterization characterization = factory.createCharacterization();		
		if(target instanceof Mode && !(source instanceof Mode)) addAssociationEnds(characterization, target, source);
		else addAssociationEnds(characterization, source, target);	
		if(container!=null)container.getPackagedElement().add(characterization);
		return characterization;
	}
	
	/** Create a characterization between source and target types */
	public static Characterization createCharacterization (Classifier source, int srcLower, int srcUpper, String name, Classifier target, int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		Characterization characterization = factory.createCharacterization();
		List<Property> ends = null;				
		if(target instanceof Mode && !(source instanceof Mode)) ends = addAssociationEnds(characterization, target, source);
		else ends = addAssociationEnds(characterization, source, target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);	
		if(name!=null) characterization.setName(name);
		else characterization.setName("");
		if(container!=null)container.getPackagedElement().add(characterization);
		return characterization;
	}
	
	/** Create a componentOf between whole and part types */
	public static componentOf createComponentOf (Classifier whole, Classifier part, RefOntoUML.Package container)
	{
		componentOf compOf = factory.createcomponentOf();
		addAssociationEnds(compOf, whole, part);		
		if(container!=null)container.getPackagedElement().add(compOf);
		return compOf;
	}
	
	/** Create a componentOf between whole and part types */
	public static componentOf createComponentOf (Classifier whole, int wholeLower, int wholeUpper, String name, Classifier part, int partLower, int partUpper, RefOntoUML.Package container)
	{
		componentOf compOf = factory.createcomponentOf();
		List<Property> ends = addAssociationEnds(compOf, whole, part);		
		setMultiplicity(ends.get(0), wholeLower, wholeUpper);
		setMultiplicity(ends.get(1), partLower, partUpper);
		if(name!=null) compOf.setName(name);
		else compOf.setName("");
		if(container!=null)container.getPackagedElement().add(compOf);
		return compOf;
	}
	
	/** Create a memberOf between whole and part types */
	public static memberOf createMemberOf (Classifier whole, Classifier part, RefOntoUML.Package container)
	{
		memberOf membOf = factory.creatememberOf();
		addAssociationEnds(membOf, whole, part);		
		if(container!=null)container.getPackagedElement().add(membOf);
		return membOf;
	}
	
	/** Create a memberOf between whole and part types */
	public static memberOf createMemberOf (Classifier whole, int wholeLower, int wholeUpper, String name, Classifier part, int partLower, int partUpper, RefOntoUML.Package container)
	{
		memberOf membOf = factory.creatememberOf();
		List<Property> ends = addAssociationEnds(membOf, whole, part);		
		setMultiplicity(ends.get(0), wholeLower, wholeUpper);
		setMultiplicity(ends.get(1), partLower, partUpper);	
		if(name!=null) membOf.setName(name);
		else membOf.setName("");
		if(container!=null)container.getPackagedElement().add(membOf);
		return membOf;
	}
	
	/** Create a subCollectionOf between whole and part types */
	public static subCollectionOf createSubCollectionOf (Classifier whole, Classifier part, RefOntoUML.Package container)
	{
		subCollectionOf subColl = factory.createsubCollectionOf();
		addAssociationEnds(subColl, whole, part);
		if(container!=null)container.getPackagedElement().add(subColl);
		return subColl;
	}
			
	/** Create a subCollectionOf between whole and part types */
	public static subCollectionOf createSubCollectionOf (Classifier whole, int wholeLower, int wholeUpper, String name, Classifier part, int partLower, int partUpper, RefOntoUML.Package container)
	{
		subCollectionOf subColl = factory.createsubCollectionOf();
		List<Property> ends = addAssociationEnds(subColl, whole, part);		
		setMultiplicity(ends.get(0), wholeLower, wholeUpper);
		setMultiplicity(ends.get(1), partLower, partUpper);
		if(name!=null) subColl.setName(name);
		else subColl.setName("");
		if(container!=null)container.getPackagedElement().add(subColl);
		return subColl;
	}
	
	/** Create a subQuantityOf between whole and part types */
	public static subQuantityOf createSubQuantityOf (Classifier whole, Classifier part, RefOntoUML.Package container)
	{
		subQuantityOf subQuan = factory.createsubQuantityOf();
		addAssociationEnds(subQuan, whole, part);		
		if(part instanceof Role || part instanceof Phase || part instanceof RoleMixin) subQuan.setIsEssential(false);
		else subQuan.setIsEssential(true);		
		subQuan.setIsImmutablePart(true);
		subQuan.setIsInseparable(false);		
		if(container!=null)container.getPackagedElement().add(subQuan);
		return subQuan;
	}
	
	/** Create a subQuantityOf between whole and part types */
	public static subQuantityOf createSubQuantityOf (Classifier whole, int wholeLower, int wholeUpper, String name, Classifier part, int partLower, int partUpper, RefOntoUML.Package container)
	{
		subQuantityOf subQuan = factory.createsubQuantityOf();
		List<Property> ends = addAssociationEnds(subQuan, whole, part);		
		if(part instanceof Role || part instanceof Phase || part instanceof RoleMixin) subQuan.setIsEssential(false);
		else subQuan.setIsEssential(true);		
		subQuan.setIsImmutablePart(true);
		subQuan.setIsInseparable(false);
		setMultiplicity(ends.get(0), wholeLower, wholeUpper);
		setMultiplicity(ends.get(1), partLower, partUpper);		
		if(name!=null) subQuan.setName(name);
		else subQuan.setName("");
		if(container!=null)container.getPackagedElement().add(subQuan);
		return subQuan;
	}
	
	/** Create a derivation relationship between source and target types */
	public static Derivation createDerivation (Association source, Classifier target, RefOntoUML.Package container)
	{
		Derivation derivation = factory.createDerivation();
		addAssociationEnds(derivation,source,target);
		if(container!=null)container.getPackagedElement().add(derivation);
		source.setIsDerived(true);
		return derivation;
	}

	/** Create a derivation relationship between source and target types */
	public static Derivation createDerivation (Association source, int srcLower, int srcUpper, String name, Classifier target,int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		Derivation derivation = factory.createDerivation();
		List<Property> ends = addAssociationEnds(derivation,source,target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);	
		if(name!=null) derivation.setName(name);
		else derivation.setName("");
		if(container!=null)container.getPackagedElement().add(derivation);
		source.setIsDerived(true);
		return derivation;
	}

	/** Create a structuration relationship between source and target types */
	public static Structuration createStructuration (Classifier source, Classifier target, RefOntoUML.Package container)
	{
		Structuration structuration = factory.createStructuration();
		addAssociationEnds(structuration,source,target);
		if(container!=null)container.getPackagedElement().add(structuration);
		return structuration;
	}
	
	/** Create a structuration relationship between source and target types */
	public static Structuration createStructuration (Classifier source, int srcLower, int srcUpper, String name, Classifier target,int tgtLower, int tgtUpper, RefOntoUML.Package container)
	{
		Structuration structuration = factory.createStructuration();
		List<Property> ends = addAssociationEnds(structuration,source,target);
		setMultiplicity(ends.get(0), srcLower, srcUpper);
		setMultiplicity(ends.get(1), tgtLower, tgtUpper);
		if(name!=null) structuration.setName(name);
		else structuration.setName("");
		if(container!=null)container.getPackagedElement().add(structuration);
		return structuration;
	}
	
	/** Create a kind type  */
	public static Kind createKind(String name, RefOntoUML.Package container)
	{
		Kind kind = factory.createKind();
		if(name!=null) kind.setName(name);
		else kind.setName("");
		if(container!=null)container.getPackagedElement().add(kind);
		return kind;
	}
	
	/** Create a subkind type  */
	public static SubKind createSubKind(String name, RefOntoUML.Package container)
	{
		SubKind subkind = factory.createSubKind();
		if(name!=null) subkind.setName(name);
		else subkind.setName("");
		if(container!=null)container.getPackagedElement().add(subkind);
		return subkind;
	}
	
	/** Create a collective type  */
	public static Collective createCollective(String name, RefOntoUML.Package container)
	{
		Collective collective = factory.createCollective();
		if(name!=null) collective.setName(name);
		else collective.setName("");
		if(container!=null)container.getPackagedElement().add(collective);
		return collective;
	}
	
	/** Create a quantity type  */
	public static Quantity createQuantity(String name, RefOntoUML.Package container)
	{
		Quantity quantity = factory.createQuantity();
		if(name!=null) quantity.setName(name);
		else quantity.setName("");
		if(container!=null)container.getPackagedElement().add(quantity);
		return quantity;
	}
	
	/** Create a role type  */
	public static Role createRole(String name, RefOntoUML.Package container)
	{
		Role role = factory.createRole();
		if(name!=null) role.setName(name);
		else role.setName("");
		if(container!=null)container.getPackagedElement().add(role);
		return role;
	}
	
	/** Create a relator type  */
	public static Relator createRelator(String name, RefOntoUML.Package container)
	{
		Relator relator = factory.createRelator();
		if(name!=null) relator.setName(name);
		else relator.setName("");
		if(container!=null)container.getPackagedElement().add(relator);
		return relator;
	}
	
	/** Create a partition. */
	public static GeneralizationSet createPartition(String name, List<Classifier> specifics, Classifier general, RefOntoUML.Package container)
	{
		List<Generalization> gens = new ArrayList<Generalization>();
		for(Classifier c: specifics) gens.add(createGeneralization(c, general));
		GeneralizationSet gs = createGeneralizationSet(gens,true,true,container);
		if(name!=null)gs.setName(name);
		else gs.setName("");
		return gs;
	}
	
	/** Create a mode type  */
	public static Mode createMode(String name, RefOntoUML.Package container)
	{
		Mode mode = factory.createMode();
		if(name!=null) mode.setName(name);
		else mode.setName("");
		if(container!=null)container.getPackagedElement().add(mode);
		return mode;
	}
	
	/** Create a phase type  */
	public static Phase createPhase(String name, RefOntoUML.Package container)
	{
		Phase phase = factory.createPhase();
		if(name!=null) phase.setName(name);
		else phase.setName("");
		if(container!=null)container.getPackagedElement().add(phase);
		return phase;
	}
	
	/** Create a category type  */
	public static Category createCategory(String name, RefOntoUML.Package container)
	{
		Category category = factory.createCategory();
		if(name!=null) category.setName(name);
		else category.setName("");
		category.setIsAbstract(true);
		if(container!=null)container.getPackagedElement().add(category);
		return category;
	}
	
	/** Create a mixin type  */
	public static Mixin createMixin(String name, RefOntoUML.Package container)
	{
		Mixin mixin = factory.createMixin();
		if(name!=null) mixin.setName(name);
		else mixin.setName("");
		mixin.setIsAbstract(true);
		if(container!=null)container.getPackagedElement().add(mixin);
		return mixin;
	}
	
	/** Create a role mixin type  */
	public static RoleMixin createRoleMixin(String name, RefOntoUML.Package container)
	{
		RoleMixin rolemixin = factory.createRoleMixin();
		if(name!=null) rolemixin.setName(name);
		else rolemixin.setName("");
		rolemixin.setIsAbstract(true);
		if(container!=null)container.getPackagedElement().add(rolemixin);
		return rolemixin;
	}
	
	/** Create a perceivable quality type  */
	public static PerceivableQuality createPerceivableQuality(String name, RefOntoUML.Package container)
	{
		PerceivableQuality quality = factory.createPerceivableQuality();
		if(name!=null) quality.setName(name);
		else quality.setName("");		
		if(container!=null)container.getPackagedElement().add(quality);
		return quality;
	}
	
	/** Create a nonperceivable quality type  */
	public static NonPerceivableQuality createNonPerceivableQuality(String name, RefOntoUML.Package container)
	{
		NonPerceivableQuality quality = factory.createNonPerceivableQuality();
		if(name!=null) quality.setName(name);
		else quality.setName("");		
		if(container!=null)container.getPackagedElement().add(quality);
		return quality;
	}
	
	/** Create a nominal quality type  */
	public static NominalQuality createNominalQuality(String name, RefOntoUML.Package container)
	{
		NominalQuality quality = factory.createNominalQuality();
		if(name!=null) quality.setName(name);
		else quality.setName("");		
		if(container!=null)container.getPackagedElement().add(quality);
		return quality;
	}
	
	/** Create a primitive type  */
	public static PrimitiveType createPrimitiveType(String name, RefOntoUML.Package container)
	{
		PrimitiveType primitive = factory.createPrimitiveType();
		if(name!=null) primitive.setName(name);
		else primitive.setName("");		
		if(container!=null)container.getPackagedElement().add(primitive);
		return primitive;
	}
	
	/** Create a data type  */
	public static DataType createDataType(String name, RefOntoUML.Package container)
	{
		DataType datatype = factory.createDataType();
		if(name!=null) datatype.setName(name);
		else datatype.setName("");		
		if(container!=null)container.getPackagedElement().add(datatype);
		return datatype;
	}	
	
	/** Create a enumeration type  */
	public static Enumeration createEnumeration(String name, Collection<String> values, RefOntoUML.Package container)
	{
		Enumeration datatype = factory.createEnumeration();
		if(name!=null) datatype.setName(name);
		else datatype.setName("");
		for(String literalName: values)
		{
			EnumerationLiteral literal = factory.createEnumerationLiteral();
			literal.setName(literalName);
			datatype.getOwnedLiteral().add(literal);
		}		
		if(container!=null)container.getPackagedElement().add(datatype);
		return datatype;
	}	

	/**
	 * Create an attribute of this given type to this classifier.
	 * Multiplicities are by default 1..1. This is not a derived nor a read only attribute.
	 */
	public static void createAttribute (Classifier classifier, Type type)
	{
		Property attribute = createProperty((Classifier)type, 1, 1, null, false, false, AggregationKind.NONE);
		addAttribute(classifier,attribute);
	}
	
	/** Create this attribute to this association */
	public static void createAttribute (Classifier classifier, Type type, int lower, int upper)
	{
		Property attribute = createProperty((Classifier)type, lower, upper, null, false, false, AggregationKind.NONE);
		addAttribute(classifier,attribute);
	}
		
	/** Create an attribute to this classifier. */
	public static void createAttribute (Classifier classifier, Type type, int lower, int upper, String name, boolean isDerived, boolean isReadOnly, AggregationKind aggreggation)
	{		
		Property attribute = createProperty((Classifier)type, lower, upper, name, isDerived, isReadOnly, aggreggation);
		addAttribute(classifier, attribute);
	}

	/** Create an attribute to this classifier. */
	public static void createAttribute (Classifier classifier, Type type, int lower, int upper, String name, boolean isDerived)
	{		
		Property attribute = createProperty((Classifier)type, lower, upper, name, isDerived, false, AggregationKind.NONE);
		addAttribute(classifier, attribute);
	}
	
	/**
	 * Create a property i.e. an attribute or association end-point. 
	 * with a default name which is the classifier name in lower case 
	 */
	private static Property createProperty(Classifier classifier, int lower, int upper) 
	{
		Property property = factory.createProperty();
		property.setType(classifier);
		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lower);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upper);
		property.setLowerValue(lowerBound);
		property.setUpperValue(upperBound);
		if(classifier!=null && classifier.getName()!=null) property.setName(classifier.getName().trim().toLowerCase());
		else property.setName("");
		return property;
	}

	/** Create a property i.e. an attribute or association end-point. */
	private static Property createProperty(Classifier classifier, int lower, int upper, String name) 
	{
		Property p = createProperty(classifier, lower, upper);
		if(classifier !=null && classifier.getName()!=null && name==null) p.setName(classifier.getName().trim().toLowerCase());
		else if(name!=null) p.setName(name);
		return p;
	}
	
	/** Create a property i.e. an attribute or association end-point. */
	private static Property createProperty(Classifier classifier, int lower, int upper, String name, boolean isDerived, boolean isReadOnly, AggregationKind aggregation)
	{
		Property p = createProperty(classifier, lower, upper, name);
		p.setIsDerived(isDerived);
		p.setIsReadOnly(isReadOnly);        
		if(aggregation==null) aggregation = AggregationKind.NONE;		
		p.setAggregation(aggregation);
		return p;

	}
	
	/** Add this attribute to this classifier */
	private static void addAttribute(Classifier classifier, Property attribute)
	{	
		if (classifier instanceof Class) {
			Class c = (Class) classifier; 
			c.getOwnedAttribute().add(attribute);
		} else if (classifier instanceof DataType) {
			DataType dt = (DataType) classifier;
			dt.getOwnedAttribute().add(attribute);
		}
	}
	
	/** Add the association ends to this association */
	private static void addAssociationEnds(Association association, Property srcProperty, Property tgtProperty) 
	{
		association.getOwnedEnd().add(srcProperty);
		association.getOwnedEnd().add(tgtProperty);
		association.getMemberEnd().add(srcProperty);
		association.getMemberEnd().add(tgtProperty);
		if(association instanceof DirectedBinaryAssociation || association instanceof FormalAssociation || association instanceof MaterialAssociation){
			association.getNavigableOwnedEnd().add(srcProperty);
			association.getNavigableOwnedEnd().add(tgtProperty);
		}else{
			if (srcProperty.getType() instanceof DataType) association.getNavigableOwnedEnd().add(srcProperty);
			if (tgtProperty.getType() instanceof DataType) association.getNavigableOwnedEnd().add(tgtProperty);
		}
	}
	
	/**
	 * Add association end-points from and to these given types in this association.
	 * Multiplicites are 1..1 except for componentOfs which are created by default with 2..*.
	 * Characterization, mediations and derivations are read only at the target side.
	 * Meronymics aggregation kind are shared if they are shareable, otherwise they are composite.
	 */
	private static List<Property> addAssociationEnds(Association association, Classifier source, Classifier target)
	{
		List<Property> properties = addAssociationEnds(association);
		properties.get(0).setType(source);
		properties.get(1).setType(target);
		if(source.getName()!=null) properties.get(0).setName(source.getName().trim().toLowerCase());
		if(target.getName()!=null) properties.get(1).setName(target.getName().trim().toLowerCase());
		return properties;
	}
	
	/**
	 * Create default association end-points for this association.
	 * Multiplicites are 1..1 except for componentOfs which are created by default with 2..*.
	 * Characterization, mediations and derivations are read only at the target side.
	 * Meronymics aggregation kind are shared if they are shareable, otherwise they are composite. 
	 */
	private static List<Property> addAssociationEnds(Association association) 
	{
		List<Property> properties = new ArrayList<Property>();
		//create properties
		Property node1Property, node2Property;
		node1Property = createProperty(null, 1, 1);
		if (association instanceof componentOf) node2Property = createProperty(null, 2, -1);
		else node2Property = createProperty(null, 1, 1);
		//set read only
		if (association instanceof Mediation || association instanceof Characterization	|| association instanceof Derivation){
			node2Property.setIsReadOnly(true);
		}
		//set aggregation
		if (association instanceof Meronymic) 
		{
			if (((Meronymic) association).isIsShareable()) node1Property.setAggregation(AggregationKind.SHARED);
			else node1Property.setAggregation(AggregationKind.COMPOSITE);
		}
		//set name
		String node1Name = new String();
		if (node1Property.getType() != null) 
		{
			node1Name = node1Property.getType().getName();
			if (node1Name == null || node1Name.trim().isEmpty()) node1Name = "source";
			else node1Name = node1Name.trim().toLowerCase();
		}
		String node2Name = new String();
		if (node2Property.getType() != null) 
		{
			node2Name = node2Property.getType().getName();
			if (node2Name == null || node2Name.trim().isEmpty()) node2Name = "target";
			else node2Name = node2Name.trim().toLowerCase();
		}
		node1Property.setName(node1Name);
		node2Property.setName(node2Name);
		//set ends
		addAssociationEnds(association, node1Property, node2Property);
		properties.add(node1Property);
		properties.add(node2Property);
		return properties;
	}
	
	/** Set multiplicity of a association end-point or attribute */
	public static void setMultiplicity(Property property, int lower, int upper)
	{
		LiteralInteger lowerBound = factory.createLiteralInteger();
		lowerBound.setValue(lower);
		LiteralUnlimitedNatural upperBound = factory.createLiteralUnlimitedNatural();
		upperBound.setValue(upper);
		property.setLowerValue(lowerBound);
		property.setUpperValue(upperBound);	
	}
}
