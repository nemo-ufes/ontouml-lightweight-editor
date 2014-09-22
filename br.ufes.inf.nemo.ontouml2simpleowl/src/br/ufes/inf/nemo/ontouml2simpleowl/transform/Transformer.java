package br.ufes.inf.nemo.ontouml2simpleowl.transform;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectCardinalityRestriction;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLProperty;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.DataType;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.ClassImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.GeneralizationImpl;
import RefOntoUML.impl.GeneralizationSetImpl;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.NamedElementImpl;
import RefOntoUML.impl.PackageImpl;
import RefOntoUML.impl.PropertyImpl;
import RefOntoUML.impl.SubstanceSortalImpl;
import RefOntoUML.parser.OntoUMLParser;

/**
 * Provides transformation of RefOntoUML models into OWL2DL ontologies using the OWLAPI.
 * @author Antognoni Albuquerque
 * @version 0.1
 */
public class Transformer {
	
	private IRI ontologyIRI;
	private OWLOntology ontology;
	private Package model;
	private OWLOntologyManager manager; 
	private Map<Element, Object> owlMappings; 	//Keep track of the mapped items
	private Map<Element, Set<String>> owlAnnotations; 	//Keep track of the elements' annotations 
	private OntoUMLParser ontoParser;
	
	/**
	 * Constructor for the transformer.
	 * @param ontologyIRI - the IRI used to identify uniquely he ontology
	 * @param model - the model to be transformed
	 * @throws OWLOntologyCreationException
	 */
 	public Transformer(String ontologyIRI, Package model) throws OWLOntologyCreationException {
 		
		this.model = model;
		ontoParser = new OntoUMLParser(model);
		owlMappings = new HashMap<Element, Object>();
		owlAnnotations = new HashMap<Element, Set<String>>();
		
		//Simple validate the informed IRI. If it isn't good, we get a new one.
		if(ontologyIRI.toLowerCase().startsWith("http://") && ontologyIRI.toLowerCase().endsWith(".owl"))
			this.ontologyIRI = IRI.create(ontologyIRI.replace(" ", ""));
		else
			this.ontologyIRI = IRI.create(getDefaultIRI("ontology"));
		
		//This manager is needed in order to work with OWLAPI
		manager = OWLManager.createOWLOntologyManager();
		ontology = manager.createOntology(this.ontologyIRI);
	}
	
 	/**
 	 * Transforms the informed model into an OWLOntology
 	 * @return the ontology
 	 */
	public OWLOntology transform()
	{
		if(model != null && model.getPackagedElement().size() > 0)
		{
			process(model);
			
			Set<OWLClassExpression> owlIdPcs = new HashSet<OWLClassExpression>();
			
			for (Element key : owlMappings.keySet()) {
				
				//If it's an Identity Provider Class (IDPC) we need to make it disjoint with the other IDPCs  
				if(key instanceof SubstanceSortalImpl || key instanceof MomentClassImpl)
					owlIdPcs.add((OWLClassExpression)owlMappings.get(key));
				
				if(isClosed(key))
					addClosureAxiom((OWLClass) owlMappings.get(key));
				
				if(isDefined(key))
					convertToDefined((OWLClass) owlMappings.get(key));
			}
			
			//Make the identity provider classes disjoint
			if(owlIdPcs.size() > 1)
			{
				OWLAxiom disjAxm = manager.getOWLDataFactory().getOWLDisjointClassesAxiom(owlIdPcs);
				addToOntology(disjAxm);
			}
		
			return ontology;
		}
		
		return null;
	}
	
	/**
	 * Transforms the informed model into an OWLOntology
	 * @param format - the format used to encode the ontology
	 * @param out - the output stream to serialize the ontology
	 */
	public void transform(OWLOntologyFormat format, OutputStream out)
	{
		try {
			
			OWLOntology ont = transform();
			if(ont != null)
				manager.saveOntology(ont, format, out);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Adds the informed object to the ontology. The object must be either OWLEntity or OWLAxiom
	 * @param obj - the object to be added
	 */
	private void addToOntology(OWLObject obj)
	{
		OWLAxiom axm = null;
			
		if(obj instanceof OWLEntity)
		{
			axm = manager.getOWLDataFactory().getOWLDeclarationAxiom((OWLEntity) obj);
		}
		else if(obj instanceof OWLAxiom)
		{
			axm = (OWLAxiom) obj;
		}
		
		if(axm != null)
		{
			AddAxiom addAxiom = new AddAxiom(ontology, axm);
			manager.applyChange(addAxiom);
		}
	}
	
	/**
	 * Converts the informed class to a defined class by adding an OWLEquivalenClassesAxiom to the ontology
	 * and removing the class super classes
	 * @param cls - the class to be converted
	 */
	private void convertToDefined(OWLClass cls)
	{		
		/* Get the class axioms */
        Set<OWLSubClassOfAxiom> axioms = ontology.getAxioms(AxiomType.SUBCLASS_OF);

        /* Collect those that assert superclasses of the class */
        SubClassCollector collector = new SubClassCollector(cls);

        //Percorre todas as suclasses da ontologia identificando as subclasses da classe informada
        for (OWLClassAxiom axiom : axioms) {
            axiom.accept(collector);
        }
        
        Set<OWLClassExpression> superClasses = new HashSet<OWLClassExpression>();
        
        /* For each axiom.... */
        for (OWLSubClassOfAxiom axiom : collector.getAxioms()) {
            /* Get the superclass */
            OWLClassExpression sup = axiom.getSuperClass();
            superClasses.add(sup);
            
            RemoveAxiom remAxiom = new RemoveAxiom(ontology, axiom);
            manager.applyChange(remAxiom);
        }

        OWLClassExpression intr = manager.getOWLDataFactory().getOWLObjectIntersectionOf(superClasses);
    	OWLAxiom equiAxm = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(cls, intr);
    	AddAxiom addAxiom = new AddAxiom(ontology, equiAxm);	
    	manager.applyChange(addAxiom);
  
	}
	
	/**
	 * Adds a closure axiom for the informed class, allowing dynamic classification.  
	 * @param cls - the class to be closed
	 */
	private void addClosureAxiom(OWLClass cls)
	{
		/* Get the class axioms */
        Set<OWLSubClassOfAxiom> axioms = ontology.getAxioms(AxiomType.SUBCLASS_OF);

        /* Collect those that assert superclasses of the class */
        SubClassCollector collector = new SubClassCollector(cls);

        for (OWLClassAxiom axiom : axioms) {
            axiom.accept(collector);
        }
        
        Map<OWLObjectPropertyExpression, Set<OWLClassExpression>> restrictions = new HashMap<OWLObjectPropertyExpression, Set<OWLClassExpression>>();

        /* For each axiom.... */
        for (OWLSubClassOfAxiom axiom : collector.getAxioms()) {
            /* Get the superclass */
            OWLClassExpression superClass = axiom.getSuperClass();

            /* Collect any existentials */
            ExistentialCollector ec = new ExistentialCollector(restrictions);
            superClass.accept(ec);
        }

        /* For any existentials.... */
        for (OWLObjectPropertyExpression prop : restrictions.keySet()) {
       
            Set<OWLClassExpression> fillers = restrictions.get(prop);
            
            /* Create a union of the fillers */
            OWLClassExpression union = manager.getOWLDataFactory().getOWLObjectUnionOf(fillers);

            /* Create a universal restriction */
            OWLClassExpression universal = manager.getOWLDataFactory().getOWLObjectAllValuesFrom(prop, union);

            /* Create a new axiom */
            OWLAxiom newAxiom = manager.getOWLDataFactory().getOWLSubClassOfAxiom(cls, universal);

            /* Now add the axiom to the ontology */
            AddAxiom addAxiom = new AddAxiom(ontology, newAxiom);
            /* Use the manager to apply the change */
            manager.applyChange(addAxiom);

        }
	}
		
	/**
	 * Coordinates the transformation of the model elements. This method should be called upon
	 * every element to be transformed.
	 * @param src - The model element to be transformed
	 * @return the result of the transformation
	 */
	private Object process(EObject src)
	{			
		Object trg = owlMappings.get(src);
		
		if(trg == null)
		{
			if(src instanceof ClassImpl)
				trg = processClass((Class) src);
			else if(src instanceof AssociationImpl)
				trg = processAssociation((Association) src);
			else if(src instanceof GeneralizationImpl)
				trg = processGeneralization((Generalization) src);
			else if(src instanceof GeneralizationSetImpl)
				trg = processGeneralizationSet((GeneralizationSet) src);
			else if(src instanceof DataTypeImpl)
				trg = processDatatype((DataType) src);
			else if(src instanceof PropertyImpl)
				trg = processProperty((Property) src);
			else if(src instanceof PackageImpl)
				trg = processPackage((RefOntoUML.Package) src);
		}
		
		return trg;
	}
	
	private Object processPackage(RefOntoUML.Package pkg)
	{
		Set<Object> ret = new HashSet<Object>();
		
		for (PackageableElement elm : pkg.getPackagedElement()) {
			ret.add(process(elm));
		}
		
		owlMappings.put(pkg, ret);
		
		return ret;
	}
		
	private Object processClass(RefOntoUML.Class cls)
	{
		String clsName = cls.getName();
		if(clsName != null){
			clsName = clsName.replace(" ", "");
		}
		IRI clsIRI = IRI.create(ontologyIRI + "#" + clsName);
		OWLClass ocls = manager.getOWLDataFactory().getOWLClass(clsIRI);
		
		owlMappings.put(cls, ocls);
		addToOntology(ocls);
				
		if(cls.getGeneralization().size() > 0)
		{
			for (Generalization gen : cls.getGeneralization()) {				
				process(gen);
			}
		}
		//If the class doesn't have any supertypes, we assume that its super type is OWL Thing
		else
		{
			OWLSubClassOfAxiom subAxm = manager.getOWLDataFactory().getOWLSubClassOfAxiom(ocls, manager.getOWLDataFactory().getOWLThing());
			addToOntology(subAxm);
		}
					
		for (Property prp : cls.getOwnedAttribute()) {				
			process(prp);
		}
				
		return ocls;
	}
	
 	private Object processDatatype(DataType dty)
	{
 		//If the DataType has the same name as a a built-in DataType, such as string or int we assume it is a built-in DataType
 		//Otherwise, we treat it as a regular class or a multi-dimensional DataType
 		String dtyName = dty.getName();
 		if(dtyName != null){
 			dtyName = dtyName.replace(" ", "");
 		}
 		OWLDatatype odty = getBuiltinType(dtyName);
 		if(odty == null)
 		{
			IRI dtyIRI = IRI.create(ontologyIRI + "#" + dtyName);
			OWLClass ocls = manager.getOWLDataFactory().getOWLClass(dtyIRI);
			
			owlMappings.put(dty, ocls);
			addToOntology(ocls);
			
			if(dty.getGeneralization().size() > 0)
			{
				for (Generalization gen : dty.getGeneralization()) {				
					process(gen);
				}
			}
			//If the datatype doesn't have any supertypes, we assume that its super type is OWL Thing
			else
			{
				OWLSubClassOfAxiom subAxm = manager.getOWLDataFactory().getOWLSubClassOfAxiom(ocls, manager.getOWLDataFactory().getOWLThing());
				addToOntology(subAxm);
			}
			
			for (Property prp : dty.getOwnedAttribute()) {				
				process(prp);
			}
			
			return ocls;
 		}
 		
 		return odty;
	}
 		
	private Object processGeneralization(Generalization gen)
	{	
		OWLObject sub = (OWLObject) process(gen.getSpecific());
		OWLObject sup = (OWLObject) process(gen.getGeneral());
		
		OWLObject ogen = null;
		
		if(sub instanceof OWLClassImpl && sup instanceof OWLClassImpl)
			ogen = manager.getOWLDataFactory().getOWLSubClassOfAxiom((OWLClass)sub, (OWLClass)sup);
		
		else if(sub instanceof OWLObjectPropertyImpl && sup instanceof OWLObjectPropertyImpl)
			ogen = manager.getOWLDataFactory().getOWLSubObjectPropertyOfAxiom((OWLObjectProperty)sub, (OWLObjectProperty)sup);
			
		else if(sub instanceof OWLDataPropertyImpl && sup instanceof OWLDataPropertyImpl)
			ogen = manager.getOWLDataFactory().getOWLSubDataPropertyOfAxiom((OWLDataProperty)sub, (OWLDataProperty)sup);
				
		owlMappings.put(gen, ogen);
		addToOntology(ogen);
		
		return ogen;
	}
			
	private Object processGeneralizationSet(GeneralizationSet genSet)
	{	
		Set<Object> ret = new HashSet<Object>();
		Classifier general = null;
		OWLClass superType = null;
		Set<OWLClass> subTypes = new HashSet<OWLClass>();
		
		for (Generalization gen : genSet.getGeneralization()) {
			
			//This is not needed, but it is always good to ensure that all the generalizations
			//in a generalization set have the same general type
			
			if(general == null)
				general = gen.getGeneral();
			else
				assert(general == gen.getGeneral());
			
			OWLClass subType = (OWLClass) process(gen.getSpecific());
			subTypes.add(subType);
		}
		
		superType = (OWLClass) process(general);
		
		//If the generalization set is disjoint, we create a disjoint axiom for the ontology
		if(genSet.isIsDisjoint())
		{
			OWLAxiom disjAxm = manager.getOWLDataFactory().getOWLDisjointClassesAxiom(subTypes);
			addToOntology(disjAxm);
			ret.add(disjAxm);
		}
		
		//if the generalization set is covering, we add an axiom stating that
		//the general type is equivalent to an union of the specific type
		if(genSet.isIsCovering())
		{
			OWLClassExpression unionOf = manager.getOWLDataFactory().getOWLObjectUnionOf(subTypes);
			OWLAxiom equiAxm = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(superType, unionOf);
			//axms.add(equiAxm);
			//addEquivalentClassAxiom(superType, unionOf);
			
			addToOntology(equiAxm);
			ret.add(equiAxm);
		}
		
		owlMappings.put(genSet, ret);
		
		return ret;
	}
	 	
	private Object processProperty(Property prp)
	{
		IRI propIRI = null;
		OWLClass dcls = null;
		OWLClass rcls = null;
		OWLDatatype rdty = null;
		
		Classifier clf = (Classifier) prp.eContainer();
		
		//If it has supertypes defined in annotations
		Set<Element> supElm = getSuperFromAnnotation(prp);
		
		//If the property is contained in a class, then it is a class attribute
		if(clf instanceof ClassImpl)
		{
			String propName = prp.getName();
			if(propName != null){
				propName = propName.replace(" ", "");
			}
			propIRI = IRI.create(ontologyIRI + "#" + getOWLName(propName));
			
			//The domain class is the class containing the property
			dcls = (OWLClass) process(clf);
			
			//Check if the range is a DataType
			rdty = getBuiltinType(prp.getType().getName());		
		}
		
		//If the property is contained in an Association its an endpoint of the association
		else if(clf instanceof AssociationImpl)
		{
			String name = prp.getName();
			if(name == null)
			{
				String clfName = clf.getName();
				if(clfName == null || clfName.trim().equals("")){
					clfName = this.ontoParser.getAlias(clf);
				}
				//Check if it is a source or target inverse prop, naming accordingly
				if(prp == ((Association) clf).getMemberEnd().get(0))
					name = clfName;
				else
					name = "inv" + capitalize(clfName);
			}
			if(name != null){
				name = name.replace(" ", "");
			}
			propIRI = IRI.create(ontologyIRI + "#" + getOWLName(name));
			
			//Find the domain and range OWLClasses
			dcls = (OWLClass) process(prp.getType());
			
			//Check if the range is a DataType
			rdty = getBuiltinType(prp.getOpposite().getType().getName());				
		}
		
		//Check if the range is a built in DataType we add a OWLDataProperty.
		//Otherwise, we add an OWLObjectProperty
		if(rdty != null)
		{
			OWLDataProperty oprp  = manager.getOWLDataFactory().getOWLDataProperty(propIRI);
			addToOntology(oprp);
			
			OWLDataPropertyDomainAxiom daxPrp = manager.getOWLDataFactory().getOWLDataPropertyDomainAxiom(oprp, dcls);
			addToOntology(daxPrp);
			
			OWLDataPropertyRangeAxiom raxPrp = manager.getOWLDataFactory().getOWLDataPropertyRangeAxiom(oprp, rdty);
			addToOntology(raxPrp);
					
			owlMappings.put(prp, oprp);
	
			//If the property is annotated with supertype annotations, we include subproperty axioms			
			for (Element elm : supElm) {
				Object supObj = process(elm);
				if(supObj instanceof OWLDataPropertyImpl)
				{
					OWLAxiom subPrpAxm = manager.getOWLDataFactory().getOWLSubDataPropertyOfAxiom(oprp, (OWLDataPropertyExpression) supObj);
					addToOntology(subPrpAxm);
				}
			}
						
			//We don't create subclass axioms (representing anonymous superclasses) or cardinality constraints for these kind 
			//of attributes as it is done with properties of associations.
			
			return oprp;
		}
		else
		{
			OWLObjectProperty oprp = manager.getOWLDataFactory().getOWLObjectProperty(propIRI);
			
			OWLObjectPropertyDomainAxiom daxPrp = manager.getOWLDataFactory().getOWLObjectPropertyDomainAxiom(oprp, dcls);
			addToOntology(daxPrp);
			
			rcls = (OWLClass) process(prp.getOpposite().getType());
			OWLObjectPropertyRangeAxiom raxPrp = manager.getOWLDataFactory().getOWLObjectPropertyRangeAxiom(oprp, rcls);
			addToOntology(raxPrp);
						
			//Deal with cardinality
			OWLClassExpression expr = getCardinalityRestriction(prp.getOpposite().getLower(), prp.getOpposite().getUpper(), oprp, dcls, rcls);
			
			//Create an anonymous superclass for the Object Property
			OWLAxiom subAxm = manager.getOWLDataFactory().getOWLSubClassOfAxiom(dcls, expr); 			
			addToOntology(subAxm);
			
			owlMappings.put(prp, oprp);
			
			//If the property is annotated with supertype annotations, we include subproperty axioms			
			for (Element elm : supElm) {
				Object supObj = process(elm);
				if(supObj instanceof OWLObjectPropertyImpl)
				{
					OWLAxiom subPrpAxm = manager.getOWLDataFactory().getOWLSubObjectPropertyOfAxiom(oprp, (OWLObjectPropertyExpression) supObj);
					addToOntology(subPrpAxm);
				}
			}
			
			//Deal with object Property characteristics from annotations
			
			//Funcional
			if(isFunctional(prp))
			{
				OWLFunctionalObjectPropertyAxiom fncPrpAxm = manager.getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(oprp);
				addToOntology(fncPrpAxm);
			}
			
			//Inverse Functional
			if(isInverseFunctional(prp))
			{
				OWLInverseFunctionalObjectPropertyAxiom infncPrpAxm = manager.getOWLDataFactory().getOWLInverseFunctionalObjectPropertyAxiom(oprp);
				addToOntology(infncPrpAxm);
			}
			
			//Transitive
			if(isTransitive(prp))
			{
				OWLTransitiveObjectPropertyAxiom trsPrpAxm = manager.getOWLDataFactory().getOWLTransitiveObjectPropertyAxiom(oprp);
				addToOntology(trsPrpAxm);
			}
			
			//Symmetric
			if(isSymmetric(prp))
			{
				OWLSymmetricObjectPropertyAxiom symPrpAxm = manager.getOWLDataFactory().getOWLSymmetricObjectPropertyAxiom(oprp);
				addToOntology(symPrpAxm);
			}
				
			//AntiSymmetric
			if(isAntiSymmetric(prp))
			{
				OWLAsymmetricObjectPropertyAxiom asymPrpAxm = manager.getOWLDataFactory().getOWLAsymmetricObjectPropertyAxiom(oprp);
				addToOntology(asymPrpAxm);
			}
			
			//Reflexive
			if(isReflexive(prp))
			{
				OWLReflexiveObjectPropertyAxiom rfxPrpAxm = manager.getOWLDataFactory().getOWLReflexiveObjectPropertyAxiom(oprp);
				addToOntology(rfxPrpAxm);
			}
			
			//Irreflexive
			if(isIrreflexive(prp))
			{
				OWLIrreflexiveObjectPropertyAxiom ifxPrpAxm = manager.getOWLDataFactory().getOWLIrreflexiveObjectPropertyAxiom(oprp);
				addToOntology(ifxPrpAxm);
			}
			
			return oprp;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private Object processAssociation(Association asc)
	{
		Set<Object> ret = new HashSet<Object>();
		
		Property src = asc.getMemberEnd().get(0);
		Property trg = asc.getMemberEnd().get(1);
		
		OWLProperty srcOP = null;
		OWLProperty trgOP = null;
		
		//Target navigable 
//		if(asc.getNavigableOwnedEnd().contains(trg))
//		{
			srcOP = (OWLProperty) process(src); 
			ret.add(srcOP);
//		}
		
		//Source navigable
//		if(asc.getNavigableOwnedEnd().contains(src))
//		{
			trgOP = (OWLProperty) process(trg); 
			ret.add(trgOP);
//		}
		
		//Inverse axiom: no need for inverse axiom in DataType relations
		if((srcOP != null && trgOP != null) 
				&&  (src.getType() instanceof DataTypeImpl == false 
						&& trg.getType() instanceof DataTypeImpl == false ))
		{
			OWLInverseObjectPropertiesAxiom invAxm = manager.getOWLDataFactory().getOWLInverseObjectPropertiesAxiom((OWLObjectProperty) srcOP, (OWLObjectProperty) trgOP);
			addToOntology(invAxm);
		}
			
		return ret;
	}
	
	private OWLClassExpression getCardinalityRestriction(int lower, int upper, OWLObjectProperty objProp, OWLClass owlSrc, OWLClass owlTrg)
	{
		// Multiplicities: 1, 3, 7
		if(lower == upper && lower > 0)
		{
			OWLObjectExactCardinality trgExact = manager.getOWLDataFactory().getOWLObjectExactCardinality(lower, objProp, owlTrg);
			return trgExact;
		}
		// Multiplicities: 1..*, 0..*, *
		else if((lower == -1 || lower == 0 || lower == 1 ) && upper == -1)
		{
			OWLObjectSomeValuesFrom trgSome = manager.getOWLDataFactory().getOWLObjectSomeValuesFrom(objProp, owlTrg);
			return trgSome;
		}
		else
		{
			//Deal with cardinality
			OWLObjectCardinalityRestriction trgLower = null;
			OWLObjectCardinalityRestriction trgUpper = null;
			
			// Multiplicities: 1..3, 0..3, 2..*
			if(lower > 0)
			{
				trgLower = manager.getOWLDataFactory().getOWLObjectMinCardinality(lower, objProp, owlTrg);
			}
			if(upper > 0)
			{
				trgUpper = manager.getOWLDataFactory().getOWLObjectMaxCardinality(upper, objProp, owlTrg);
			}
			
			if(trgLower != null && trgUpper != null)
			{
				OWLObjectIntersectionOf objInt = manager.getOWLDataFactory().getOWLObjectIntersectionOf(trgLower, trgUpper);
				return objInt;
			}
			else if(trgLower != null)
			{
				return trgLower;
			}
			else
			{
				return trgUpper;
			}
		}
	}
			
	private OWLDatatype getBuiltinType(String name)
	{
		String type = name.toLowerCase();
		
		if(type.equals("string"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		}
		else if(type.equals("int"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_INT.getIRI());
		}
		else if(type.equals("integer"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		}
		else if(type.equals("boolean"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_BOOLEAN.getIRI());
		}
		else if(type.equals("datetime") || type.equals("date"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
		}
		else if(type.equals("long"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_LONG.getIRI());
		}
		else if(type.equals("float"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_FLOAT.getIRI());
		}
		else if(type.equals("byte"))
		{
			return manager.getOWLDataFactory().getOWLDatatype(OWL2Datatype.XSD_BYTE.getIRI());
		}
				
		return null;
	}
		
	private Set<String> getOWLAnnotations(Element elm)
	{
		if(!owlAnnotations.containsKey(elm))
		{
			Set<String> ret = new HashSet<String>();
			
			for (Comment ant : elm.getOwnedComment()) {
				String body = ant.getBody().toLowerCase();
				if(body.startsWith("owl:"))
				{
					body = body.substring(4);
					if(body.contains(","))
					{
						String[] args = body.split(",");
						for (String arg : args) {
							ret.add(arg.trim());
						}
					}
					
					ret.add(body.trim());
				}
			}
			
			
			owlAnnotations.put(elm, ret);
		}
		
		return owlAnnotations.get(elm);
	}
	
	private boolean isDefined(Element elm)
	{
		if(elm instanceof ClassImpl)
			return getOWLAnnotations(elm).contains("defined");
		return false;
	}
		
	private boolean isClosed(Element elm)
	{
		if(elm instanceof ClassImpl)
			return getOWLAnnotations(elm).contains("closed");
		return false;
	}
	
	private boolean isFunctional(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("functional");
		return false;
	}
	
	private boolean isInverseFunctional(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("inversefunctional");
		return false;
	}
	
	private boolean isTransitive(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("transitive");
		return false;
	}
	
	private boolean isSymmetric(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("symmetric");
		return false;
	}
	
	private boolean isAntiSymmetric(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("antisymmetric");
		return false;
	}
	
	private boolean isReflexive(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("reflexive");
		return false;
	}
	
	private boolean isIrreflexive(Element elm)
	{
		if(elm instanceof PropertyImpl)
			return getOWLAnnotations(elm).contains("irreflexive");
		return false;
	}
	
	/**
	 * Gets the set containing all the super classifiers for the informed element described by the super annotations. 
	 * @param elm - the annotated element
	 * @return the set containing all the super classifiers
	 */
	private Set<Element> getSuperFromAnnotation(Element elm)
	{
		Set<Element> ret = new HashSet<Element>();
		
		for(String ant : getOWLAnnotations(elm))
		{
			if(ant.startsWith("super="))
			{
				Element supElm = getElementByName(model.getPackagedElement(), ant.substring(ant.indexOf("=") + 1));
				ret.add((Element) supElm);
			}
		}
		
		return ret;
	}
	
	@SuppressWarnings("unused")
	/**
	 * Returns a model element given its name, or null if theres no such element in the model.
	 * @param name - the name of the element
	 * @return the model element
	 */
	private Element getElementByName(String name) {
	
		TreeIterator<EObject> it = model.eAllContents();
		while(it.hasNext())
		{
			EObject obj = it.next();
			if(obj instanceof NamedElementImpl)
			{
				NamedElement namedObj = (NamedElement) obj;
				String objName = namedObj.getName().toLowerCase();
				
				if(objName.equals(name.toLowerCase()))
					return (Element) obj;
			}
		}
		
		return null;
	}
		
	private Element getElementByName(EList<? extends EObject> list, String name) {
		
		if(name == null || name.equals(""))
			return null;
		
		Element ret = null;
		
		for (EObject elm : list) {
			if(elm instanceof NamedElementImpl)
			{
				String elmName = ((NamedElement) elm).getName();
				if(elmName.toLowerCase().equals(name.toLowerCase()))
				{
					ret = (Element) elm;
					break;
				}
			}
			
			//If we didn't find the element we are looking for, keep trying:
			if(ret == null)
			{
				if(elm instanceof ClassImpl)
					ret = getElementByName(((Class) elm).getOwnedAttribute(), name);
				if(elm instanceof AssociationImpl)
					ret = getElementByName(((Association) elm).getMemberEnd(), name);
				else if(elm instanceof PackageImpl)
					ret = getElementByName(((Package) elm).getPackagedElement(), name);
			}		
		}
		
		return ret;
	}

	private String getDefaultIRI(String name)
	{
		String ontologyName = name.replace(" ", "");
		
		if(!ontologyName.endsWith(".owl"))
			ontologyName += ".owl";
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		
		return "http://www.semanticweb.org/ontologies/" + year + "/" + month + "/" + ontologyName;
	}
	
	private String getOWLName(String name)
	{
		if(name != null)
			return name.replace(" ", "");
		return name;
	}
		
	private String capitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
