package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.DataTypeImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.eclipse.uml2.uml.internal.impl.PrimitiveTypeImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLDataPropertyAtom;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.tags.Tag;
import br.ufes.inf.nemo.ocl2swrl.util.Util;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class PropertyCallExpImplFactory extends NavigationCallExpImplFactory {
	Property property;
	
	public PropertyCallExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	public PropertyCallExpImplFactory(NamedElementImpl m_NamedElementImpl, Property property){
		super(m_NamedElementImpl);
		this.property =  property;
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws NonImplemented, NonSupported{
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		//int sourceRepeatNumber = 1;
		int sourceRepeatNumber = repeatNumber;
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, sourceRepeatNumber, leftSideOfImplies);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//pega o ultimo
		
		if(referredArgument != null){
			varX = referredArgument;
		}
		
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		Type propertyType = referredProperty.getType();
		
		ArrayList<SWRLDArgument> retArgsY = null;
		if(propertyType.getClass().equals(PrimitiveTypeImpl.class) || propertyType.getClass().equals(DataTypeImpl.class)){
			retArgsY = solveClassAttribute(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber, leftSideOfImplies);
		}else if(propertyType.getClass().equals(ClassImpl.class)){
			retArgsY = solveAssociation(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber, leftSideOfImplies);
		}
		SWRLDArgument varY = retArgsY.get(retArgsY.size()-1);//pega o ultimo
		
		ArrayList<SWRLDArgument> retArgsZ = new ArrayList<SWRLDArgument>();
		retArgsZ.add(varX);
		retArgsZ.add(varY);
		
		return retArgsZ;
	}
	
	public ArrayList<SWRLDArgument> solveProperty(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber) {
		ArrayList<SWRLDArgument> retArgs = solvePropertyAssociation(refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, repeatNumber);
		
		return retArgs;
	}
	
	public ArrayList<SWRLDArgument> solveAssociation(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies){
		String nameVarX = "";
		String nameVarY = "";
		
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		Association association = referredProperty.getAssociation();
		
		String iriRelationName = nameSpace + generateAssociationName(refParser, association);;
		IRI iriRelation = IRI.create(iriRelationName);
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		String assocEndName = referredProperty.getName();
		Property assocEnd0 = association.getMemberEnds().get(0);
		String assocEnd0Name = assocEnd0.getName();
		
		if(assocEndName.equals(assocEnd0Name)){
			nameVarX = Util.generateVarName(source, referredArgument);
			nameVarY = Util.generateVarName(referredProperty, referredArgument);
		}else{
			nameVarX = Util.generateVarName(referredProperty, referredArgument);
			nameVarY = Util.generateVarName(source, referredArgument);
		}
		
		if(repeatNumber>1){
			//nameVarX+=repeatNumber;
			nameVarY+=repeatNumber;
		}
		
		//variables are created to the source and the target class
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
		
		SWRLObjectPropertyAtom objPropAtom = null;
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varX, varY)); //DifferentFrom(?x,?z)
		if(ctStereotype.equals(Tag.derive.toString()) && leftSideOfImplies == false){
			objPropAtom = factory.getSWRLObjectPropertyAtom(relation, varX, varY);
			//consequent.add(); //prop(?x,?Y)
		}else{
			objPropAtom = factory.getSWRLObjectPropertyAtom(relation, varX, varY);
			//the atoms are added to the antecedents atoms
			//antecedent.add(); //prop(?x,?Y)
		}
		
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, objPropAtom);
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		
		if(assocEndName.equals(assocEnd0Name)){
			retArgs.add(varY);
		}else{
			retArgs.add(varX);
		}
		
		return retArgs;
	}
	
	public ArrayList<SWRLDArgument> solvePropertyAssociation(OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber){
		String nameVarX = "";
		String nameVarY = "";
		
		Association association = property.getAssociation();
		
		String iriRelationName = nameSpace + generateAssociationName(refParser, association);;
		IRI iriRelation = IRI.create(iriRelationName);
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		String assocEndName = property.getName();
		Property assocEnd0 = association.getMemberEnds().get(0);
		String assocEnd0Name = assocEnd0.getName();
		
		if(assocEndName.equals(assocEnd0Name)){
			nameVarX = Util.generateVarName(referredArgument, null);
			nameVarY = Util.generateVarName(property, referredArgument);
		}else{
			nameVarX = Util.generateVarName(property, referredArgument);
			nameVarY = Util.generateVarName(referredArgument, null);
		}
		
		if(repeatNumber>1){
			//nameVarX+=repeatNumber;
			nameVarY+=repeatNumber;
		}
		
		//variables are created to the source and the target class
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
		
		//the atoms are added to the antecedents atoms
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varX, varY)); //DifferentFrom(?x,?z)
		consequent.add(factory.getSWRLObjectPropertyAtom(relation, varX, varY)); //prop(?x,?Y)
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		
		if(assocEndName.equals(assocEnd0Name)){
			retArgs.add(varY);
		}else{
			retArgs.add(varX);
		}
		
		return retArgs;
	}
	
	public ArrayList<SWRLDArgument> solveClassAttribute(String ctStereotype, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies){
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		ClassImpl owner = (ClassImpl)referredProperty.getOwner();
		
		//generate a variable name likes in the OntoUML2OWL_SWRL
		String attrName = owner.getName() + "." + referredProperty.getName();//aqui eh necessario chamar a funcao de criacao de nomes feita pelo victor
		String iriAttrName = nameSpace+attrName;
		iriAttrName = iriAttrName.replace(" ", "_");
		IRI iriAttr = IRI.create(iriAttrName);
		OWLDataProperty attrDtProp = factory.getOWLDataProperty(iriAttr);
		
		//generates variables involved in the attribute
		String attrVarName = Util.generateVarName(propertyCallExpImpl, referredArgument);
		
		if(repeatNumber>1){
			attrVarName+=repeatNumber;
		}
		
		SWRLVariable attrVar = factory.getSWRLVariable(IRI.create(nameSpace+attrVarName));
		
		SWRLDataPropertyAtom dataPropAtom = null;
		//add the atom to the antecedents atoms
		if(ctStereotype.equals(Tag.derive.toString()) && leftSideOfImplies == false){
			dataPropAtom = factory.getSWRLDataPropertyAtom(attrDtProp, (SWRLIArgument) referredArgument, attrVar);
			//consequent.add(); //property(?x,?Y)
		}else{
			dataPropAtom = factory.getSWRLDataPropertyAtom(attrDtProp, (SWRLIArgument) referredArgument, attrVar);
			//antecedent.add(); //property(?x,?Y)
		}
		
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, dataPropAtom);
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(attrVar);
		
		return retArgs;
	}
	
	public static RefOntoUML.Association getEquivalentOntoUmlAssociation(OntoUMLParser refParser, Association association){
		String assocName = association.getName();
		
		Property assocMEnd0 = association.getMemberEnds().get(0);
		String assocMEnd0Name = assocMEnd0.getName();
		Type assocMEnd0Class = assocMEnd0.getType();
		String assocMEnd0ClassName = assocMEnd0Class.getName();
		
		Property assocMEnd1 = association.getMemberEnds().get(1);
		String assocMEnd1Name = assocMEnd1.getName();
		Type assocMEnd1Class = assocMEnd1.getType();
		String assocMEnd1ClassName = assocMEnd1Class.getName();
		
		Set<RefOntoUML.Association> associations = refParser.getAllInstances(RefOntoUML.Association.class);
		Iterator<RefOntoUML.Association> assocIterator = associations.iterator();
		while(assocIterator.hasNext()){
			RefOntoUML.Association ontoUmlAssoc = assocIterator.next();
			String ontoUmlAssocName = ontoUmlAssoc.getName();
			
			if(hasSameName(assocName, ontoUmlAssocName)){
				RefOntoUML.Property ontoUmlAssocMEnd0 = ontoUmlAssoc.getMemberEnd().get(0);
				String ontoUmlAssocMEnd0Name = ontoUmlAssocMEnd0.getName();
				RefOntoUML.Property ontoUmlAssocMEnd1 = ontoUmlAssoc.getMemberEnd().get(1);
				String ontoUmlAssocMEnd1Name = ontoUmlAssocMEnd1.getName();
				
				if(hasSameName(assocMEnd0Name, ontoUmlAssocMEnd0Name) && hasSameName(assocMEnd1Name, ontoUmlAssocMEnd1Name)){
					RefOntoUML.Type ontoUmlAssocMEnd0Class = ontoUmlAssocMEnd0.getType();
					String ontoUmlAssocMEnd0ClassName = ontoUmlAssocMEnd0Class.getName();
					RefOntoUML.Type ontoUmlAssocMEnd1Class = ontoUmlAssocMEnd1.getType();
					String ontoUmlAssocMEnd1ClassName = ontoUmlAssocMEnd1Class.getName();
					
					if(hasSameName(assocMEnd0ClassName, ontoUmlAssocMEnd0ClassName) && hasSameName(assocMEnd1ClassName, ontoUmlAssocMEnd1ClassName)){
						return ontoUmlAssoc;
					}
				}
			}
			
			
		}
		
		return null;
	}
	
	public static String generateAssociationName(OntoUMLParser refParser, Association association){
		RefOntoUML.Association ontoUmlAssociation = getEquivalentOntoUmlAssociation(refParser, association);
		String propName = ontoUmlAssociation.getClass().getName();
		propName = propName.replace("Impl", "");
		propName = propName.replace("RefOntoUML.impl.", "");
		String prop = "";
		if(ontoUmlAssociation.getName()==null || ontoUmlAssociation.getName() == "" || ontoUmlAssociation.getName() == " "){
			prop = propName+"."+ontoUmlAssociation.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_")+"."+ontoUmlAssociation.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_"); 
		}else{
			prop = ontoUmlAssociation.getName().replaceAll(" ", "_");
		}
		return prop;
	}
	
	public static Boolean hasSameName(String assocName, String ontoUmlAssocName){
		if(ontoUmlAssocName == null && assocName == null){
			return true;
		}else if(ontoUmlAssocName == null || assocName == null){
			return false;
		}else if(ontoUmlAssocName.equals(assocName)){
			return true;
		}
		return false;
	}
	
	@Override
	public OWLObjectProperty getOWLObjectProperty(String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) {
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		Association association = referredProperty.getAssociation();
		String iriRelationName = nameSpace + PropertyCallExpImplFactory.generateAssociationName(refParser, association);;
		IRI iriRelation = IRI.create(iriRelationName);
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		return relation;
	}
}