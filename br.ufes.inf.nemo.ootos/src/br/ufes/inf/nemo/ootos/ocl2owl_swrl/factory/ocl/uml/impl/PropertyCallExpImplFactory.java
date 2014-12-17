package br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.ocl.uml.impl;

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
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLDataPropertyAtom;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLVariable;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.exceptions.Ocl2Owl_SwrlException;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.factory.Factory;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags.Tag;
import br.ufes.inf.nemo.ootos.ocl2owl_swrl.util.Util;

/**
 * @author Freddy Brasileiro Silva {freddybrasileiro@gmail.com}
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

	@Override
	public ArrayList<SWRLDArgument> solve(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies)  throws Ocl2Owl_SwrlException{
		//since the factory is created according to the rule fragment, the fragment is got as a property call fragment
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		//then, the source of the property call is got
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		
		//and a factory is created according to the source class 
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source, this.m_NamedElementImpl);
		int sourceRepeatNumber = repeatNumber;
		//the source is solved and the and the returned arguments from the sourceSolveMethod above are returned 
		//esse trecho foi modificado para que fosse possivel passar o referredArgument para frente e resolver problemas de SameAs com variáveis (regra: 70/71)
		//ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, operatorNot, sourceRepeatNumber, leftSideOfImplies);
		ArrayList<SWRLDArgument> retArgsX = this.sourceFactory.solve(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, referredArgument, operatorNot, sourceRepeatNumber, leftSideOfImplies);
		SWRLDArgument varX = retArgsX.get(retArgsX.size()-1);//get the last
		
		//esse trecho foi comentado para atender o uso de variáveis, o varX era passado como referredArgument e as variáveis a frente ficavam com nome errado (regra: 70/71)
		/*
		//the varX turns the referredArgument if varX is null or if is not about self
		if(referredArgument != null && !initiatedBySelf(source)){
			varX = referredArgument;
		}
		*/
		
		//essa mexida foi feita para correcao da criacao de variaveis temporarias feitas pelo proprio parser
		if(this.isTempVariable(varX)){
			varX = referredArgument;
		}
		
		//then, the referred property of the property call is got
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		Type propertyType = referredProperty.getType();
		
		ArrayList<SWRLDArgument> retArgsY = null;
		if(ctStereotype.equals(Tag.functional.toString())){
			solveFunctional(propertyCallExpImpl, refParser, nameSpace, manager, factory, ontology);			
		}else if(ctStereotype.equals(Tag.inversefunctional.toString())){
			solveInverseFunctional(propertyCallExpImpl, refParser, nameSpace, manager, factory, ontology);			
		}else if(propertyType.getClass().equals(PrimitiveTypeImpl.class) || propertyType.getClass().equals(DataTypeImpl.class)){//if the property is an attirbute
			retArgsY = solveClassAttribute(ctStereotype, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber, leftSideOfImplies);
		}else if(propertyType.getClass().equals(ClassImpl.class)){//if the property is an association
			retArgsY = solveAssociation(ctStereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, varX, operatorNot, repeatNumber, leftSideOfImplies);
		}
		
		if(Tag.isObjectPropertyTag(ctStereotype)){
			return null;
		}
		
		SWRLDArgument varY = retArgsY.get(retArgsY.size()-1);//get the last
		
		//the varX and varY are returned
		ArrayList<SWRLDArgument> retArgsZ = new ArrayList<SWRLDArgument>();
		retArgsZ.add(varX);
		retArgsZ.add(varY);
		/*
		//essa mexida foi feita para correcao da criacao de variaveis temporarias feitas pelo proprio parser
		if(this.isTempVariable(referredArgument) || this.isTempVariable(varX)){
			SWRLSameIndividualAtom same = factory.getSWRLSameIndividualAtom((SWRLVariable)referredArgument, (SWRLVariable)varX);
			this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, same);
		}
		*/
		return retArgsZ;
	}
	
	public void solveInverseFunctional(PropertyCallExpImpl propertyCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException {
		//get the relation
		OWLObjectProperty relation = this.getOWLObjectProperty(propertyCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as transition
		OWLInverseFunctionalObjectPropertyAxiom invFunctional = factory.getOWLInverseFunctionalObjectPropertyAxiom(relation);
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, invFunctional));
		
	}

	public void solveFunctional(PropertyCallExpImpl propertyCallExpImpl, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology) throws Ocl2Owl_SwrlException {
		//get the relation
		OWLObjectProperty relation = this.getOWLObjectProperty(propertyCallExpImpl, nameSpace, refParser, factory);
		
		//set the relation as transition
		OWLFunctionalObjectPropertyAxiom functional = factory.getOWLFunctionalObjectPropertyAxiom(relation);
		//apply changes in the owl manager
		manager.applyChange(new AddAxiom(ontology, functional));
		
	}

	
	//verify if the source is about self
	public Boolean initiatedBySelf(OCLExpressionImpl source){
		if(source.toString().indexOf("self")<0){
			return false;
		}else{
			return true;
		}
		
	}
	
	public ArrayList<SWRLDArgument> solveAssociation(String ctStereotype, OntoUMLParser refParser, String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent, SWRLDArgument referredArgument, Boolean operatorNot, int repeatNumber, Boolean leftSideOfImplies){
		String nameVarX = "";
		String nameVarY = "";
		
		//since the factory is created according to the rule fragment, the fragment is got as a property call fragment
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		//then, the source of the property call is got
		//OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		
		//then, the referred property of the property call is got
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		//get the association of the property
		Association association = referredProperty.getAssociation();
		
		//generate the association name and the IRI
		String iriRelationName = nameSpace + generateAssociationName(refParser, association);;
		IRI iriRelation = IRI.create(iriRelationName);
		//get the OWL object property
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
//		if(relation.getNamedProperty().equals("path")){
//			System.out.println();
//		}
		//get the association end name used in the rule
		String assocEndName = referredProperty.getName();
		//get the association end name of the association with 0 index
		Property assocEnd0 = association.getMemberEnds().get(0);
		String assocEnd0Name = assocEnd0.getName();
		/*
		if(assocEndName.equals(assocEnd0Name)){
			nameVarX = Util.generateVarName(source, referredArgument);
			nameVarY = Util.generateVarName(referredProperty, referredArgument);
		}else{
			nameVarX = Util.generateVarName(referredProperty, referredArgument);
			nameVarY = Util.generateVarName(source, referredArgument);
		}
		*/
		//for an unknown reason, the source and target order (of associations inside of OntoUMLParser) changes when the application is running alone and when is running from OLED
		//the correct order to be used into OLED is the one above
		
		//verifing the correct order of the OWL Object Properties arguments
		if(assocEndName.equals(assocEnd0Name)){
			nameVarX = Util.generateVarName(referredProperty, referredArgument);
			nameVarY = Util.generateVarName(null, referredArgument);
		}else{
			nameVarX = Util.generateVarName(null, referredArgument);
			nameVarY = Util.generateVarName(referredProperty, referredArgument);
		}
		if(repeatNumber>1){
			if(assocEndName.equals(assocEnd0Name)){
				nameVarX+=repeatNumber;
			}else{
				nameVarY+=repeatNumber;
			}			
		}
		
		//variables are created to the source and the target class
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
		
		SWRLObjectPropertyAtom objPropAtom = null;
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varX, varY)); //DifferentFrom(?x,?z)
		//if(ctStereotype.equals(Tag.derive.toString()) && leftSideOfImplies == false){
			//get the SWRL Object Property
			objPropAtom = factory.getSWRLObjectPropertyAtom(relation, varX, varY);
			//consequent.add(); //prop(?x,?Y)
		//}else{
		//	objPropAtom = factory.getSWRLObjectPropertyAtom(relation, varX, varY);
			//the atoms are added to the antecedents atoms
			//antecedent.add(); //prop(?x,?Y)
		//}
		
			//insert the atom on the antecedent or on the consequent
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, objPropAtom);
		
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		
		//return X or Y, depending of the assocEnd0Name 
		if(assocEndName.equals(assocEnd0Name)){
			retArgs.add(varX);
		}else{
			retArgs.add(varY);//se der errado o retorno do VarX, sempre deve retornar VarY			
		}
		
		//retArgs.add(varY);
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
		//since the factory is created according to the rule fragment, the fragment is got as a property call fragment
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		//then, the referred property of the property call is got
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		//get the owner of the attribute (a class)
		//ClassImpl owner = (ClassImpl)referredProperty.getOwner();
		NamedElementImpl owner = (NamedElementImpl)referredProperty.getOwner();
		
		//generate a variable name likes in the OntoUML2OWL_SWRL
		String attrName = owner.getName() + "." + referredProperty.getName();//aqui eh necessario chamar a funcao de criacao de nomes feita pelo victor
		String iriAttrName = nameSpace+attrName;
		iriAttrName = iriAttrName.replace(" ", "_");
		IRI iriAttr = IRI.create(iriAttrName);
		
		//get owl data property
		OWLDataProperty attrDtProp = factory.getOWLDataProperty(iriAttr);
		
		//generates variables involved in the attribute
		String attrVarName = Util.generateVarName(propertyCallExpImpl, referredArgument);
		
		if(repeatNumber>1){
			attrVarName+=repeatNumber;
		}
		
		//generates a swrl variable
		SWRLVariable attrVar = factory.getSWRLVariable(IRI.create(nameSpace+attrVarName));
		
		SWRLDataPropertyAtom dataPropAtom = null;
		//add the atom to the antecedents atoms
		//if(ctStereotype.equals(Tag.derive.toString()) && leftSideOfImplies == false){
			//generate the swrl data property atom
			dataPropAtom = factory.getSWRLDataPropertyAtom(attrDtProp, (SWRLIArgument) referredArgument, attrVar);
			//consequent.add(); //property(?x,?Y)
		//}else{
			//dataPropAtom = factory.getSWRLDataPropertyAtom(attrDtProp, (SWRLIArgument) referredArgument, attrVar);
			//antecedent.add(); //property(?x,?Y)
		//}
		
		//insert the atom on the antecedent or consequent
		this.insertOnAntecedentOrConsequent(ctStereotype, leftSideOfImplies, antecedent, consequent, dataPropAtom);
		
		//the varX and varY are returned
		ArrayList<SWRLDArgument> retArgs = new ArrayList<SWRLDArgument>();
		retArgs.add(attrVar);
		
		return retArgs;
	}
	
	public static RefOntoUML.Association getEquivalentOntoUmlAssociation(OntoUMLParser refParser, Association association){
		//get the association name
		String assocName = association.getName();
		
		//get the association end name (0)
		Property assocMEnd0 = association.getMemberEnds().get(0);
		String assocMEnd0Name = assocMEnd0.getName();
		//and get the class name of the same association end name (0)
		Type assocMEnd0Class = assocMEnd0.getType();
		String assocMEnd0ClassName = assocMEnd0Class.getName();
		
		//now, do the same for index 1
		Property assocMEnd1 = association.getMemberEnds().get(1);
		String assocMEnd1Name = assocMEnd1.getName();
		Type assocMEnd1Class = assocMEnd1.getType();
		String assocMEnd1ClassName = assocMEnd1Class.getName();
		
		//get all instances of OntoUML associations
		Set<RefOntoUML.Association> ontoUmlAssociations = refParser.getAllInstances(RefOntoUML.Association.class);
		Iterator<RefOntoUML.Association> assocIterator = ontoUmlAssociations.iterator();
		while(assocIterator.hasNext()){
			//get the ontoUML association
			RefOntoUML.Association ontoUmlAssoc = assocIterator.next();
			String ontoUmlAssocName = ontoUmlAssoc.getName();
			
			//verify if the ontoUML association and the ocl Association has the same name
			if(hasSameName(assocName, ontoUmlAssocName)){
				//get the association end names from ontoUML at indexes 0and1
				RefOntoUML.Property ontoUmlAssocMEnd0 = ontoUmlAssoc.getMemberEnd().get(0);
				String ontoUmlAssocMEnd0Name = ontoUmlAssocMEnd0.getName();
				RefOntoUML.Property ontoUmlAssocMEnd1 = ontoUmlAssoc.getMemberEnd().get(1);
				String ontoUmlAssocMEnd1Name = ontoUmlAssocMEnd1.getName();
				
				//verify if association ends has the same name at indexes 0and1 at the same time
				if(hasSameName(assocMEnd0Name, ontoUmlAssocMEnd0Name) && hasSameName(assocMEnd1Name, ontoUmlAssocMEnd1Name)){
					//get the class name of the same association end name at indexes 0and1
					RefOntoUML.Type ontoUmlAssocMEnd0Class = ontoUmlAssocMEnd0.getType();
					String ontoUmlAssocMEnd0ClassName = ontoUmlAssocMEnd0Class.getName();
					RefOntoUML.Type ontoUmlAssocMEnd1Class = ontoUmlAssocMEnd1.getType();
					String ontoUmlAssocMEnd1ClassName = ontoUmlAssocMEnd1Class.getName();
					
					//verify if classes of association ends has the same name at indexes 0and1 at the same time
					if(hasSameName(assocMEnd0ClassName, ontoUmlAssocMEnd0ClassName) && hasSameName(assocMEnd1ClassName, ontoUmlAssocMEnd1ClassName)){
						return ontoUmlAssoc;
					}
				}
			}
			
			
		}
		
		return null;
	}
	
	public static String generateAssociationName(OntoUMLParser refParser, Association association){
		//get the equivalent ontoUML association
		RefOntoUML.Association ontoUmlAssociation = getEquivalentOntoUmlAssociation(refParser, association);
		//get the association name
		String propName = ontoUmlAssociation.getClass().getName();
		propName = propName.replace("Impl", "");
		propName = propName.replace("RefOntoUML.impl.", "");
		
		String prop = "";
		//if the association has no name
		if(ontoUmlAssociation.getName()==null || ontoUmlAssociation.getName() == "" || ontoUmlAssociation.getName() == " "){
			prop += propName;
			prop += ".";
			prop += ontoUmlAssociation.getMemberEnd().get(0).getType().getName().replaceAll(" ", "_");
			prop += ".";
			prop += ontoUmlAssociation.getMemberEnd().get(1).getType().getName().replaceAll(" ", "_"); 
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
	public OWLObjectProperty getOWLObjectProperty(OCLExpressionImpl oclExpression, String nameSpace, OntoUMLParser refParser, OWLDataFactory factory) {
		//since the factory is created according to the rule fragment, the fragment is got as a property call fragment
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		//then, the referred property of the property call is got
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		Type refPropType = referredProperty.getType();
		//get the association of the property
		Association association = referredProperty.getAssociation();
		Property memEnd1 = association.getMemberEnds().get(1);
		Type memEnd1Type = memEnd1.getType();
		
		//generate the association name and the IRI
		String iriRelationName = nameSpace;
		if(refPropType != memEnd1Type){
			iriRelationName += "INV.";
		}
		iriRelationName += PropertyCallExpImplFactory.generateAssociationName(refParser, association);
		
		IRI iriRelation = IRI.create(iriRelationName);
		//get the OWL object property
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		return relation;
	}
}