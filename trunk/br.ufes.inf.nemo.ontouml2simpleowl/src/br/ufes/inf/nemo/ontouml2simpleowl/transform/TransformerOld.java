package br.ufes.inf.nemo.ontouml2simpleowl.transform;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
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
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.DataType;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Model;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.impl.AssociationImpl;
import RefOntoUML.impl.ClassImpl;
import RefOntoUML.impl.ClassifierImpl;
import RefOntoUML.impl.DataTypeImpl;
import RefOntoUML.impl.GeneralizationImpl;
import RefOntoUML.impl.GeneralizationSetImpl;
import RefOntoUML.impl.MomentClassImpl;
import RefOntoUML.impl.NamedElementImpl;
import RefOntoUML.impl.PackageImpl;
import RefOntoUML.impl.PropertyImpl;
import RefOntoUML.impl.SubstanceSortalImpl;

/**
 * Provides transformation of RefOntoUML models into OWL2DL ontologies.
 *  @author Antognoni Albuquerque
 *  @version 0.1
 */
@SuppressWarnings("unchecked")
public class TransformerOld {

	//We could create an OWLOntology object right the way and store the 
	//processed items there but instead we keep track of them separately 
	//in case we need to change them before generating the final Ontology

	private OWLOntologyManager manager;
	private IRI ontologyIRI;
	private Model model;

	//Keep track of the mapped items
	private Map<EObject, Object> owlMappings;
	
	//
	private Map<OWLClass, OWLClassExpression> owlSuperClasses;
	
	//Keep track of the subclasses in order to define necessary and suficient restrictions, when specified
	private Map<OWLClass, OWLClassExpression> owlEquivalents;
	
	//
	private Set<OWLClass> owlClosed;
	
	//Keep track of the Identity Provider Classes as they need to be disjoint from each other
	private Set<OWLClassExpression> owlIdPcs;
	
	//Keep track of the elements' annotations 
	private Map<NamedElement, Set<String>> owlAnnotations;
	
 	public TransformerOld(String ontologyIRI, Model model) {
		
		this.model = model;
		owlMappings = new HashMap<EObject, Object>();
		owlSuperClasses = new HashMap<OWLClass, OWLClassExpression>();
		owlEquivalents = new HashMap<OWLClass, OWLClassExpression>();
		owlClosed = new HashSet<OWLClass>();
		owlIdPcs = new HashSet<OWLClassExpression>();
		owlAnnotations = new HashMap<NamedElement, Set<String>>();
		
		//Simple validation of the passed IRI. If it isn't good, we get a new one.
		if(ontologyIRI.toLowerCase().startsWith("http://") && ontologyIRI.toLowerCase().endsWith(".owl"))
			this.ontologyIRI = IRI.create(ontologyIRI);
		else
			this.ontologyIRI = IRI.create(getDefaultIRI("ontology"));
		
		//This manager is needed in order to work with OWLAPI
		manager = OWLManager.createOWLOntologyManager();
	}
	
	public OWLOntology transform()
	{
		if(model != null && model.getPackagedElement().size() > 0)
		{
			try 
			{
				for (PackageableElement elm : model.getPackagedElement()) {
					process(elm);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			OWLOntology ont = null;
				
			try 
			{
				//Add the processed items to the ontology 
				ont = manager.createOntology(ontologyIRI);
				for (Object obj : owlMappings.values()) {
					if(Collection.class.isInstance(obj))
					{
						for (OWLObject owlObj : (Collection<OWLObject>) obj) {
							addToOntology(ont, owlObj);
						}
					}
					else
					{
						addToOntology(ont, (OWLObject) obj);
					}
				}
				
				//Deal with the subclasses
				for (OWLClass ocls : owlSuperClasses.keySet()) {
					
					OWLAxiom subAxm = manager.getOWLDataFactory().getOWLSubClassOfAxiom(ocls, owlSuperClasses.get(ocls));
					addToOntology(ont, subAxm);

				}
				
				//Deal with the equivalent classes
				for (OWLClass ocls : owlEquivalents.keySet()) {
					
					OWLAxiom equiAxm = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(ocls, owlEquivalents.get(ocls));
					addToOntology(ont, equiAxm);

				}
				
				//Deal with the equivalent classes
				for (OWLClass ocls : owlClosed) {
					
					addClosureAxiom(ont, ocls);

				}
				
				//Make the identity provider classes disjoint
				if(owlIdPcs.size() > 1)
				{
					OWLAxiom disjAxm = manager.getOWLDataFactory().getOWLDisjointClassesAxiom(owlIdPcs);
					addToOntology(ont, disjAxm);
				}
				
			} catch (Exception e) {
				System.err.println(e);
			}
			
			return ont;
		}
		return null;
		
	}
	
	public void transform(OWLOntologyFormat format, OutputStream out)
	{
		try {
			
			OWLOntology ont = transform();
			manager.saveOntology(ont, format, out);
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void addToOntology(OWLOntology ont, OWLObject obj)
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
			AddAxiom addAxiom = new AddAxiom(ont, axm);
			manager.applyChange(addAxiom);
		}
	}
	
	private void addClosureAxiom(OWLOntology ont, OWLClass cls)
	{
		/* Get the class axioms */
        Set<OWLSubClassOfAxiom> axioms = ont.getAxioms(AxiomType.SUBCLASS_OF);

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
            System.out.println("prop: " + prop);
            Set<OWLClassExpression> fillers = restrictions.get(prop);
            for (OWLClassExpression filler : fillers) {
                System.out.println("------> " + filler);
            }

            /* Create a union of the fillers */
            OWLClassExpression union = manager.getOWLDataFactory().getOWLObjectUnionOf(fillers);

            /* Create a universal restriction */
            OWLClassExpression universal = manager.getOWLDataFactory().getOWLObjectAllValuesFrom(prop, union);

            /* Create a new axiom */
            OWLAxiom newAxiom = manager.getOWLDataFactory().getOWLSubClassOfAxiom(cls, universal);

            /* Now add the axiom to the ontology */
            AddAxiom addAxiom = new AddAxiom(ont, newAxiom);
            /* Use the manager to apply the change */
            manager.applyChange(addAxiom);

        }
	}
		
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
		for (PackageableElement elm : pkg.getPackagedElement()) {
			process(elm);
		}
		
		return null;
	}
		
	private Object processClass(RefOntoUML.Class cls)
	{
		IRI clsIRI = IRI.create(ontologyIRI + "#" + cls.getName());
		OWLClass ocls = manager.getOWLDataFactory().getOWLClass(clsIRI);
		
		owlMappings.put(cls, ocls);
		
		//If it's an Identity Provider Class (IDPC) we need to make it disjoint with the other IDPCs  
		if(cls instanceof SubstanceSortalImpl || cls instanceof MomentClassImpl)
			owlIdPcs.add(ocls);
			
		for (Generalization gen : cls.getGeneralization()) {				
			process(gen);
		}
		
		for (Property prp : cls.getOwnedAttribute()) {				
			process(prp);
		}
		
		if(isClosed(cls))
			owlClosed.add(ocls);
		
		return ocls;
	}
	
 	private Object processDatatype(DataType dty)
	{
 		if(getBuiltinType(dty.getName()) == null)
 		{
			IRI dtyIRI = IRI.create(ontologyIRI + "#" + dty.getName());
			OWLClass ocls = manager.getOWLDataFactory().getOWLClass(dtyIRI);
			
			owlMappings.put(dty, ocls);
			
			for (Generalization gen : dty.getGeneralization()) {				
				process(gen);
			}
			
			for (Property prp : dty.getOwnedAttribute()) {				
				process(prp);
			}
			
			return ocls;
 		}
 		
 		return null;
	}
 	
	private Object processProperty(Property prp)
	{
		List<OWLObject> owlObjs = new Vector<OWLObject>();
		
		Classifier clf = (Classifier) prp.eContainer();
		OWLClass cls = (OWLClass) process(clf);
		OWLDatatype dty = getBuiltinType(prp.getType().getName());
			
		IRI propIRI = IRI.create(ontologyIRI + "#" + getOWLName(getOWLName(clf.getName()) + "." + prp.getName()));
		OWLDataProperty oprp  = manager.getOWLDataFactory().getOWLDataProperty(propIRI);
		owlObjs.add(oprp);
		
		OWLDataPropertyDomainAxiom daxPrp = manager.getOWLDataFactory().getOWLDataPropertyDomainAxiom(oprp, cls);
		owlObjs.add(daxPrp);
		
		OWLDataPropertyRangeAxiom raxPrp = manager.getOWLDataFactory().getOWLDataPropertyRangeAxiom(oprp, dty);
		owlObjs.add(raxPrp);
		
		owlMappings.put(prp, owlObjs);

		return owlObjs;
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
		
		return ogen;
	}
			
	private Object processGeneralizationSet(GeneralizationSet genSet)
	{	
		List<OWLAxiom> axms = new Vector<OWLAxiom>();
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
			axms.add(disjAxm);
		}
		
		//if the generalization set is covering, we add an axiom stating that
		//the general type is equivalent to an union of the specific type
		if(genSet.isIsCovering())
		{
			OWLClassExpression unionOf = manager.getOWLDataFactory().getOWLObjectUnionOf(subTypes);
			//OWLAxiom equiAxm = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(superType, unionOf);
			//axms.add(equiAxm);
			addEquivalentClassAxiom(superType, unionOf);
		}
		
		owlMappings.put(genSet, axms);
		
		return axms;
	}
		
	private Object processAssociation(Association asc)
	{
						
		List<OWLObject> owlObjs = new Vector<OWLObject>();
		Property ept0 = asc.getMemberEnd().get(0);
		Property ept1 = asc.getMemberEnd().get(1);
						
		OWLClass owlSrc = (OWLClass) process(ept0.getType());
		OWLClass owlTrg = (OWLClass) process(ept1.getType());
		
		//Source as domain, target as range ObjectProperty
		OWLObjectProperty dsrcRtrgOP = null; 
		
		//Target as domain, source as target ObjectProperty
		OWLObjectProperty dtrgRsrcOP = null;
		
		//##############################################################################################################
		//Target navigable 
		if(asc.getNavigableOwnedEnd().contains(ept1))
		{
			String name = ept0.getName();
			if(name == null)
				name = asc.getName();
			
			IRI propIRI = IRI.create(ontologyIRI + "#" + getOWLName(name));
			dsrcRtrgOP = manager.getOWLDataFactory().getOWLObjectProperty(propIRI);
			owlObjs.add(dsrcRtrgOP);
			
			OWLObjectPropertyDomainAxiom daxSrc = manager.getOWLDataFactory().getOWLObjectPropertyDomainAxiom(dsrcRtrgOP, owlSrc);
			owlObjs.add(daxSrc);
			
			OWLObjectPropertyRangeAxiom raxTrg = manager.getOWLDataFactory().getOWLObjectPropertyRangeAxiom(dsrcRtrgOP, owlTrg);
			owlObjs.add(raxTrg);
						
			//Deal with cardinality
			OWLClassExpression expr = getCardinalityRestriction(ept1.getLower(), ept1.getUpper(), dsrcRtrgOP, owlSrc, owlTrg);
			//OWLAxiom subAxm = manager.getOWLDataFactory().getOWLEquivalentClassesAxiom(owlSrc, expr); 
			//owlObjs.add(subAxm);
			
			if(isDefined(ept0.getType()))
				addEquivalentClassAxiom(owlSrc, expr);
			else
				addSubClassAxiom(owlSrc, expr);
			
			//If it has a supertype defined in annotation
			/*NamedElement elm = getSuperFromAnnotation(ept0);
			if(elm != null)
			{
				OWLObjectProperty supProp = getObjectProperty(process(elm));
				OWLAxiom subAxm = manager.getOWLDataFactory().getOWLSubObjectPropertyOfAxiom(dsrcRtrgOP, arg1, arg2)
			}*/
				
		}
		
		//##############################################################################################################
		//Source navigable
		if(asc.getNavigableOwnedEnd().contains(ept0))
		{
			String name = ept1.getName();
			if(name == null)
			{
				//Check if it is an inverse prop
				if(dsrcRtrgOP == null)
					name = asc.getName();
				else
					name = "inv" + capitalize(asc.getName());
			}
			
			IRI propIRI = IRI.create(ontologyIRI + "#" + getOWLName(name));
			dtrgRsrcOP = manager.getOWLDataFactory().getOWLObjectProperty(propIRI);
			owlObjs.add(dtrgRsrcOP);
			
			OWLObjectPropertyDomainAxiom daxTrg = manager.getOWLDataFactory().getOWLObjectPropertyDomainAxiom(dtrgRsrcOP, owlTrg);
			owlObjs.add(daxTrg);
			
			OWLObjectPropertyRangeAxiom raxSrc = manager.getOWLDataFactory().getOWLObjectPropertyRangeAxiom(dtrgRsrcOP, owlSrc);
			owlObjs.add(raxSrc);
			
			//Deal with cardinality
			OWLClassExpression expr = getCardinalityRestriction(ept0.getLower(), ept0.getUpper(), dtrgRsrcOP, owlTrg, owlSrc);
			//OWLSubClassOfAxiom subAxm = manager.getOWLDataFactory().getOWLSubClassOfAxiom(owlTrg, expr); 
			//owlObjs.add(subAxm);
			
			if(isDefined(ept1.getType()))
				addEquivalentClassAxiom(owlTrg, expr);
			else
				addSubClassAxiom(owlTrg, expr);
		}
		
		//##############################################################################################################
		//Inverse axiom
		if(dsrcRtrgOP != null && dtrgRsrcOP != null)
		{
			OWLInverseObjectPropertiesAxiom invAxm = manager.getOWLDataFactory().getOWLInverseObjectPropertiesAxiom(dsrcRtrgOP, dtrgRsrcOP);
			owlObjs.add(invAxm);
		}
		
		owlMappings.put(asc, owlObjs);
		
		return owlObjs;
		
	}
			
	@SuppressWarnings("unused")
	private OWLObjectProperty getObjectProperty(List<OWLObject> owlObjs) {
		
		for (OWLObject obj : owlObjs) {
			if(obj instanceof OWLObjectPropertyImpl)
				return (OWLObjectProperty) obj;
		}
		
		return null;
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
	
	private void addSubClassAxiom(OWLClass ocls, OWLClassExpression expr)
	{
		OWLClassExpression exExpr = owlSuperClasses.get(ocls);
		
		//Check whether we already have an EquivalentClass axiom
		if(exExpr == null)
		{
			owlSuperClasses.put(ocls, expr);
		}
		else
		{
			//If we do, than we wrap it with an ObjectIntersectionOf
			OWLClassExpression newExpr = manager.getOWLDataFactory().getOWLObjectIntersectionOf(expr, exExpr);
			owlSuperClasses.put(ocls, newExpr);
		}
	}
	
	private void addEquivalentClassAxiom(OWLClass ocls, OWLClassExpression expr)
	{
		OWLClassExpression exExpr = owlEquivalents.get(ocls);
		
		//Check whether we already have an EquivalentClass axiom
		if(exExpr == null)
		{
			owlEquivalents.put(ocls, expr);
		}
		else
		{
			//If we do, than we wrap it with an ObjectIntersectionOf
			OWLClassExpression newExpr = manager.getOWLDataFactory().getOWLObjectIntersectionOf(expr, exExpr);
			owlEquivalents.put(ocls, newExpr);
		}
	}
	
	private Set<String> getOWLAnnotations(NamedElement elm)
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
	
	private boolean isDefined(NamedElement elm)
	{
		if(elm instanceof RefOntoUML.Class)
			return getOWLAnnotations(elm).contains("defined");
		return false;
	}
	
	private boolean isClosed(NamedElement elm)
	{
		if(elm instanceof RefOntoUML.Class)
			return getOWLAnnotations(elm).contains("closed");
		return false;
	}
	
	@SuppressWarnings("unused")
	private NamedElement getSuperFromAnnotation(NamedElement elm)
	{
		NamedElement ret = null;
		
		for(String ant : getOWLAnnotations(elm))
		{
			if(ant.startsWith("super="))
			{
				ret = getElementByName(model.getPackagedElement(), ant);
			}
		}
		
		return ret;
	}
	
	@SuppressWarnings("unused")
	private NamedElement getElementByName(String name) {
	
		TreeIterator<EObject> it = model.eAllContents();
		while(it.hasNext())
		{
			EObject obj = it.next();
			if(obj instanceof NamedElementImpl)
			{
				String objName = ((NamedElement) obj).getName();
				if(objName.toLowerCase().equals(name.toLowerCase()))
					return (NamedElement) obj;
			}
		}
		
		return null;
	}
	
	private NamedElement getElementByName(EList<? extends EObject> list, String name) {
		
		if(name == null || name.equals(""))
			return null;
		
		for (EObject elm : list) {
			
			if(elm instanceof ClassifierImpl)
				return getElementByName(((Classifier) elm).getAttribute(), name);
			
			if(elm instanceof PackageImpl)
				return getElementByName(((Package) elm).getPackagedElement(), name);
						
			if(elm instanceof NamedElementImpl)
			{
				String elmName = ((NamedElement) elm).getName();
				if(elmName.toLowerCase().equals(name.toLowerCase()))
					return (NamedElement) elm;
			}
		}
		
		return null;
	}

	private String getDefaultIRI(String name)
	{
		String ontologyName = name.replace(" ", "_");
		
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
