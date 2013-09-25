package br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl;

import java.util.Set;

import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
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
import org.semanticweb.owlapi.model.SWRLVariable;

import br.ufes.inf.nemo.ocl2swrl.factory.Factory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.PropertyFactory;
import br.ufes.inf.nemo.ocl2swrl.util.Util;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class PropertyCallExpImplFactory extends NavigationCallExpImplFactory {

	PropertyFactory referredPropertyFactory;
	
	public PropertyCallExpImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent) {
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		
		this.sourceFactory = (OCLExpressionImplFactory) Factory.constructor(source);
		SWRLVariable varX = (SWRLVariable) this.sourceFactory.solve(nameSpace, manager, factory, ontology, antecedent, consequent);
		
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		Type propertyType = referredProperty.getType();
		
		if(propertyType.getClass().equals(PrimitiveTypeImpl.class)){
			solveClassAttribute(nameSpace, manager, factory, ontology, antecedent, consequent);
		}else if(propertyType.getClass().equals(ClassImpl.class)){
			solveAssociation(nameSpace, manager, factory, ontology, antecedent, consequent);
		}
		
		System.out.println();
		return null;
	}
	
	public void solveAssociation(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		String nameVarX = "";
		String nameVarY = "";
		
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		Association association = referredProperty.getAssociation();
		String iriRelationName = nameSpace+association.getName();
		IRI iriRelation = IRI.create(iriRelationName);
		OWLObjectProperty relation = factory.getOWLObjectProperty(iriRelation);
		
		String assocEndName = referredProperty.getName();
		Property assocEnd0 = association.getMemberEnds().get(0);
		String assocEnd0Name = assocEnd0.getName();
		
		if(assocEndName.equals(assocEnd0Name)){
			nameVarX = Util.generateVarName(referredProperty);
			nameVarY = Util.generateVarName(source);
		}else{
			nameVarX = Util.generateVarName(source);
			nameVarY = Util.generateVarName(referredProperty);
		}
		
		//variables are created to the source and the target class
		SWRLVariable varX = factory.getSWRLVariable(IRI.create(nameSpace+nameVarX));
		SWRLVariable varY = factory.getSWRLVariable(IRI.create(nameSpace+nameVarY));
		
		//the atoms are added to the antecedents atoms
		//antecedent.add(this.factory.getSWRLDifferentIndividualsAtom(varX, varY)); //DifferentFrom(?x,?z)
		antecedent.add(factory.getSWRLObjectPropertyAtom(relation, varX, varY)); //prop(?x,?Y)
	}
	
	public void solveClassAttribute(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent){
		PropertyCallExpImpl propertyCallExpImpl = (PropertyCallExpImpl) this.m_NamedElementImpl;
		OCLExpressionImpl source = (OCLExpressionImpl) propertyCallExpImpl.getSource();
		Property referredProperty = propertyCallExpImpl.getReferredProperty();
		
		ClassImpl owner = (ClassImpl)referredProperty.getOwner();
		
		//generate a variable name likes in the OntoUML2OWL_SWRL
		String attrName = owner.getName() + "." + referredProperty.getName();//aqui eh necessario chamar a funcao de criacao de nomes feita pelo victor
		String iriAttrName = nameSpace+attrName;
		IRI iriAttr = IRI.create(iriAttrName);
		OWLDataProperty attrDtProp = factory.getOWLDataProperty(iriAttr);
		
		//generates variables involved in the attribute
		String classVarName = Util.generateVarName(source);
		SWRLVariable classVar = factory.getSWRLVariable(IRI.create(nameSpace+classVarName));
		
		String attrVarName = Util.generateVarName(propertyCallExpImpl);
		SWRLVariable attrVar = factory.getSWRLVariable(IRI.create(nameSpace+attrVarName));
		
		//add the atom to the antecedents atoms
		antecedent.add(factory.getSWRLDataPropertyAtom(attrDtProp, classVar, attrVar)); //property(?x,?Y)
	}
}