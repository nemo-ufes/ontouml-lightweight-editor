package br.ufes.inf.nemo.ontouml2temporalowl.auxiliary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import RefOntoUML.MaterialAssociation;
import RefOntoUML.Property;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.NodeBinAssociation;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.NodeClass;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.TreeProcessor;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.NodeBinAssociation.Restriction;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.MainVerbose;

/*********************************************************************
 * Procedures for dealing with the owl elements to be mapped
 * 
 * @param mappingType: indicates the kind of structure that will support the mapping
 * @param OWLClasses: list of classes to be mapped
 * @param OWLObjectProperties: list of object properties to be mapped
 * @param OWLDataTypeProperty: list of data properties to be mapped
 */

public class OWLStructure
{
	public MappingType mappingType = null;
	private List<OWLClass> OWLClasses;
	private List<OWLObjectProperty> OWLObjectProperties;
	private List<OWLDataTypeProperty> OWLDataTypeProperties;

	/*************************************************************
	 * Procedures for creating an OWLElements object	         */
	public OWLStructure (MappingType mt)
	{
		this.OWLClasses = new LinkedList<OWLClass>();
		this.OWLObjectProperties = new LinkedList<OWLObjectProperty>();
		this.OWLDataTypeProperties = new LinkedList<OWLDataTypeProperty>();
		this.mappingType = mt;
		this.initiateBasicStructure();
	}

	public Boolean is4DView()
	{
		return ((mappingType == MappingType.WORM_VIEW_A0) ||
				(mappingType == MappingType.WORM_VIEW_A1) ||
				(mappingType == MappingType.WORM_VIEW_A2));
	}
	
	public Boolean isReificationView()
	{
		return (mappingType == MappingType.REIFICATION);
	}
	
	public Boolean isDynamicView()
	{
		return (is4DView() || isReificationView()) ;
	}

	public Boolean isStaticView()
	{
		return (!isDynamicView());
	}

	/*************************************************************
	 * Procedures for initiating a basic Structure for 
	 * an OWLElements object	         						*/
	public void initiateBasicStructure ()
	{
		/*************************************************************
		/*************************************************************
		// STRUCTURAL CLASSES

		/*************************************************************
		// MAIN CLASSES: */
		// INDIVIDUAL
		if (!is4DView())
		{
			OWLClass ind = addClass("Individual", null);
			String[] s0 = {"Object","Moment"};
			ind.addCompleteClasses(s0);
			if (isReificationView())
				ind.addRestriction("some", "hasTemporalExtent", "TemporalExtent", false, null, null);
		}
		else 
		{
			// INDIVIDUAL CONCEPT
			OWLClass ic = addClass("IndividualConcept", null);
			String[] sic = {"Object","Moment"};
			ic.addCompleteClasses(sic);
			ic.addDisjointClass("TimeSlice");
			ic.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "TimeSlice", true, null, null, null);

			// TIME SLICE
			OWLClass ts = addClass("TimeSlice", null);
			String[] sts = {"FunctionalComplexTS","CollectiveTS","QuantityTS","ModeTS","RelatorTS"};
			ts.addCompleteClasses(sts);
			ts.addDisjointClass("IndividualConcept");
			ts.addRestriction("equivalentClass", "none", "some", null, "hasTemporalExtent", "TemporalExtent", false, null, null, null);
			ts.addRestriction("equivalentClass", "none", "exactly", null, "timeSliceOf", "IndividualConcept", false, null, "1", "1");
		}


		// TEMPORAL EXTENT
		if (isDynamicView())
		{
			OWLClass tem = addClass("TemporalExtent", null);
			if (is4DView())
			{
			  tem.addDisjointClass("IndividualConcept");
			  tem.addDisjointClass("TimeSlice");
			}
			else
			  tem.addDisjointClass("Individual");
		}

		/*************************************************************/

		/*************************************************************
		// SUB-CLASSES OF INDIVIDUAL: */
		// OBJECT
		String[] s1 = {"FunctionalComplex","Collective","Quantity"};
		OWLClass obj;
		if (is4DView())
			obj = addClass("Object", "IndividualConcept", s1, null);
		else
			obj = addClass("Object", "Individual", s1, null);
		obj.addDisjointClass("Moment");
	
		// if it is the reification view, then a restriction can be added for 
		// addressing the "no-propertyless individual" rule
		if (isReificationView())
			obj.addRestriction("some", "inheresIn", "Moment", true, null, null);

		// MOMENT
		OWLClass mom;
		if (!is4DView())
		{
			String[] s2 = {"IntrinsicMoment","Relator"};
			mom = addClass("Moment", "Individual", s2, null);
		}
		else
		{
			String[] s2 = {"Mode","Relator"};
			mom = addClass("Moment", "IndividualConcept", s2, null);
		}
		mom.addDisjointClass("Object");
		/*************************************************************/

		/*************************************************************
		// SUB-CLASSES OF OBJECT: */
		// FUNCTIONAL COMPLEX
		OWLClass fc = addClass("FunctionalComplex", "Object", null, s1);
		
		// COLLECTIVE
		OWLClass col = addClass("Collective", "Object", null, s1);
		
		// QUANTITY
		OWLClass qt = addClass("Quantity", "Object", null, s1);

		if (is4DView())
		{
			//the corresponding classes are added at the TS-level
			// FUNCTIONAL COMPLEX TS
			OWLClass fcts = addClass("FunctionalComplexTS", "TimeSlice");
			fcts.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "FunctionalComplex", false, null, null, null);
			
			// COLLECTIVE TS
			OWLClass cts = addClass("CollectiveTS", "TimeSlice");
			cts.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "Collective", false, null, null, null);
			
			// QUANTITY TS
			OWLClass qts = addClass("QuantityTS", "TimeSlice");
			qts.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "Quantity", false, null, null, null);

			//Restrictions over the ic-level classes
			fc.addRestriction("subClass", "none", "only", null, "timeSliceOf", "FunctionalComplexTS", true, null, null, null);
			col.addRestriction("subClass", "none", "only", null, "timeSliceOf", "CollectiveTS", true, null, null, null);
			qt.addRestriction("subClass", "none", "only", null, "timeSliceOf", "QuantityTS", true, null, null, null);

		}
	
		/*************************************************************/

		/*************************************************************
		// SUB-CLASSES OF MOMENT: */
		// INTRINSIC MOMENT
		OWLClass imom = null;
		if (!is4DView())
		{
			imom = addClass("IntrinsicMoment", "Moment");
			imom.addDisjointClass("Relator");
			imom.addRestriction("equivalentClass", "none", "some", null, "inheresIn", "Individual", false, null, null, null);
		}

		// RELATOR
		OWLClass rel = addClass("Relator", "Moment");
		if (!is4DView())
			rel.addDisjointClass("IntrinsicMoment");
		else
			rel.addDisjointClass("Mode");

		if (is4DView())
		{
			//the corresponding classes are added at the TS-level
			// RELATOR TS
			OWLClass relts = addClass("RelatorTS", "TimeSlice");
			relts.addDisjointClass("ModeTS");
			relts.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "Relator", false, null, null, null);

			//restrictions over the ic- or ts-level classes depending on the type of 4D view
			if (mappingType == MappingType.WORM_VIEW_A0)
			{
				relts.addRestriction("equivalentClass", "none", "some", null, "mediates", "TimeSlice", false, null, null, null);
				relts.addRestriction("min/max", "mediates", "TimeSlice", false, "2", "-1");
			}
			else if (mappingType == MappingType.WORM_VIEW_A2)
			{ //TODO
				rel.addRestriction("equivalentClass", "none", "some", null, "mediates", "IndividualConcept", false, null, null, null);
				rel.addRestriction("min/max", "mediates", "IndividualConcept", false, "2", "-1");
			}
			rel.addRestriction("subClass", "none", "only", null, "timeSliceOf", "RelatorTS", true, null, null, null);
		}
		else 
		{
			rel.addRestriction("equivalentClass", "none", "some", null, "mediates", "Individual", false, null, null, null);
			rel.addRestriction("min/max", "mediates", "Individual", false, "2", "-1");
			if (isReificationView())
				//a restriction concerning the partOfRelator must be added
				rel.addRestriction("min/max", "partOfRelator", "RelationalQuaIndividual", true, "2", "-1");
		}
		/*************************************************************/

		/*************************************************************
		// SUB-CLASSES OF INTRINSIC MOMENT: */
		// MODE 
		OWLClass mod;
		if (!is4DView())
			mod = addClass("Mode", "IntrinsicMoment");
		else 
			mod = addClass("Mode", "Moment");
		
		if (is4DView())
		{
			//the corresponding classes are added at the TS-level
			// MODE TS
			OWLClass mts = addClass("ModeTS", "TimeSlice");
			mts.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", "Mode", false, null, null, null);

			//restrictions over the ic-level classes
			mod.addRestriction("subClass", "none", "only", null, "timeSliceOf", "ModeTS", true, null, null, null);
		}
		else if (isReificationView())
		{
			// QUALITY
			OWLClass qual = addClass("Quality", "IntrinsicMoment");
			qual.addDisjointClass("Mode");
			qual.addRestriction("min/max", "hasValue", null, false, "1", "-1");
			
			// hasValue
			OWLDataTypeProperty hv = addDTProp("hasValue", "Quality", null, null);
			hv.setFunctional();

			// disjoint class of mode
			mod.addDisjointClass("Quality");

			// complete classes of intrinsic moment
			String[] simom = {"Quality","Mode"};
			imom.addCompleteClasses(simom);
		}
		/*************************************************************/
		
		/*************************************************************
		// SUB-CLASSES OF MODE:*/
		if (isReificationView())
		{
			// QUA INDIVIDUAL
			OWLClass qi = addClass("QuaIndividual", "Mode");
			String[] sq = {"PhasedQuaIndividual","RelationalQuaIndividual"};
			qi.addCompleteClasses(sq);

			// RELATIONAL QUA INDIVIDUAL
			OWLClass rqua = addClass("RelationalQuaIndividual", "QuaIndividual");
			rqua.addRestriction("equivalentClass", "none", "some", null, "partOfRelator", "Relator", false, null, null, null);
			rqua.addDisjointClass("PhasedQuaIndividual");

			// PHASED QUA INDIVIDUAL
			OWLClass pqua = addClass("PhasedQuaIndividual", "QuaIndividual");
			pqua.addDisjointClass("RelationalQuaIndividual");
			
		}
		/*************************************************************/
		
		
		/*************************************************************
		/*************************************************************
		//STRUCTURAL OBJECT PROPERTIES

		/*************************************************************
		// MAIN PROPERTIES:  */
		// EXISTENTIALLY DEPENDENT
		//TODO
		OWLObjectProperty edo;
		if (!is4DView())
			edo = addObjProp("existentiallyDependentOf", "Individual", "Individual", null, "invExistentiallyDependentOf");
		else
			edo = addObjProp("existentiallyDependentOf", null, null, null, "invExistentiallyDependentOf");
		edo.setIrreflexive();
		
		// INVERSE EXISTENTIALLY DEPENDENT
		OWLObjectProperty iedo;
		if (!is4DView())
			iedo = addObjProp("invExistentiallyDependentOf", "Individual", "Individual", null, "existentiallyDependentOf");
		else
			iedo = addObjProp("invExistentiallyDependentOf", null, null, null, "existentiallyDependentOf");
		
		// PART OF 
		//TODO
		OWLObjectProperty partOf;
		if (!is4DView())
			partOf = addObjProp("partOf", "Individual", "Individual", null, null);
		else
		{
			//this relation holds in different levels according to the type of 4D view
			partOf = addObjProp("partOf", null, null, null, null);
			List<String> ls = new LinkedList<String>();
			if ((mappingType == MappingType.WORM_VIEW_A0) || ((mappingType == MappingType.WORM_VIEW_A1)))
				ls.add("TimeSlice");
			if ((mappingType == MappingType.WORM_VIEW_A2) || ((mappingType == MappingType.WORM_VIEW_A1)))
				ls.add("IndividualConcept");
			partOf.addDomain(ls);
			partOf.addRange(ls);
		}
		partOf.setAsymmetric(); partOf.setIrreflexive();
		
		if (isReificationView())
		{
			// HAS TEMPORAL EXTENT
			OWLObjectProperty hte = addObjProp("hasTemporalExtent", "Individual", "TemporalExtent", null, null);
			hte.setFunctional();
		}
		else if (is4DView())
		{
			// HAS TEMPORAL EXTENT
			OWLObjectProperty hte = addObjProp("hasTemporalExtent", "TimeSlice", "TemporalExtent", null, null);
			String[] pc = {"objPropertyTS","hasTemporalExtent"}; 
			hte.addPropChain(pc);
			//the reasoner does not allow a property that is functional and has property chain
			//hte.setFunctional(); 

			// TIME SLICE OF
			OWLObjectProperty tso = addObjProp("timeSliceOf", "TimeSlice", "IndividualConcept", null, null);
			tso.setFunctional();
				
			// OBJECT PROPERTY BETWEEN INDIVIDUAL CONCEPTS
			OWLObjectProperty opic = null;
			if (mappingType != MappingType.WORM_VIEW_A0)
			{
				opic = addObjProp("objPropertyIC", "IndividualConcept", "IndividualConcept", null, null);
				opic.setSymmetric();
			}
				
			// OBJECT PROPERTY BETWEEN TIME SLICES
			OWLObjectProperty opts = addObjProp("objPropertyTS", "TimeSlice", "TimeSlice", null, null);
			opts.setSymmetric(); 
			
			if (mappingType == MappingType.WORM_VIEW_A0)
			// all properties are represented at the TS level
			{
				//all the main properties specializes the obj prop ts
				edo.addSuperProperty("objPropertyTS");
				iedo.addSuperProperty("objPropertyTS");
				partOf.addSuperProperty("objPropertyTS");
			}
			else if (mappingType == MappingType.WORM_VIEW_A1)
			// just the mutual existential dependence relations are represented at the IC level
			{
				//the obj prop between ic's specializes both existencialDependentOf and its inverse properties.
				opic.addSuperProperty("existentiallyDependentOf");
				opic.addSuperProperty("invExistentiallyDependentOf");
			}
			else if (mappingType == MappingType.WORM_VIEW_A2)
			// just the existential dependence relations are represented at the IC level
			{
				//both existencialDependentOf and its inverse properties specialize the obj prop between ic's 
				edo.addSuperProperty("objPropertyIC");
				iedo.addSuperProperty("objPropertyIC");
			}
				
		}
		//*************************************************************/

		
		//*************************************************************/ 
		// SUB-PROPERTIES OF EXISTENTIALLY DEPENDENT:  
		// INHERES
		OWLObjectProperty i;
		if (is4DView())
		{
			//this relation holds in different levels according to the type of 4D view
			if (mappingType == MappingType.WORM_VIEW_A0)
				//ts-level
				i = addObjProp("inheresIn", "ModeTS", "TimeSlice", "existentiallyDependentOf", null);
			else if (mappingType == MappingType.WORM_VIEW_A1)
			{
				//TODO
				//ic- or ts-level
				i = addObjProp("inheresIn", null, null, "existentiallyDependentOf", null);
				List<String> ld = new LinkedList<String>();
				ld.add("IntrinsicMoment"); ld.add("IntrinsicMomentTS");
				i.addDomain(ld);
				List<String> lr = new LinkedList<String>();
				lr.add("IndividualConcept"); lr.add("TimeSlice");
				i.addRange(lr);
			}
			else //if (mappingType == mappingType.WORM_VIEW_A2)
				//ic-level
				i = addObjProp("inheresIn", "IntrinsicMoment", "IndividualConcept", "existentiallyDependentOf", null);
		}
		else
			i = addObjProp("inheresIn", "IntrinsicMoment", "Individual", "existentiallyDependentOf", null);
		i.setFunctional(); i.setAsymmetric(); 

		// MEDIATES
		OWLObjectProperty m;
		if (is4DView())
		{
			//this relation holds in different levels according to the type of 4D view
			if (mappingType == MappingType.WORM_VIEW_A0)
				//ts-level
				m = addObjProp("mediates", "RelatorTS", "TimeSlice", "existentiallyDependentOf", null);
			else if (mappingType == MappingType.WORM_VIEW_A1)
			{
				//ic- or ts-level
				m = addObjProp("mediates", null, null, "existentiallyDependentOf", null);
				List<String> ls = new LinkedList<String>();
				ls.add("Relator");	ls.add("RelatorTS");
				m.addDomain(ls);
				List<String> lr = new LinkedList<String>();
				lr.add("IndividualConcept"); lr.add("TimeSlice");
				m.addRange(lr);
			}
			else //if (mappingType == mappingType.WORM_VIEW_A2)
				//ic-level
				m = addObjProp("mediates", "Relator", "IndividualConcept", "existentiallyDependentOf", null);
		}
		else
			m = addObjProp("mediates", "Relator", "Individual", "existentiallyDependentOf", null);
		m.setAsymmetric(); 
		

		if (isReificationView())
		{
			// EXISTENTIALLY DEPENDENT OF QUA
			OWLObjectProperty por = addObjProp("existentiallyDependentOfQua", "RelationalQuaIndividual", "RelationalQuaIndividual", "existentiallyDependentOf", null);
			por.setSymmetric();
		}
		//*************************************************************/
		
		//*************************************************************/
		// SUB-PROPERTIES OF PART-OF (AND EVENTUALLY OF EXIST-DEP-OF/INV): 
		// INSEPARABLE PART-OF
		//TODO
		OWLObjectProperty ipo = addObjProp("inseparablePartOf", null, null, "partOf", null);
		ipo.addSuperProperty("existentiallyDependentOf");

		// ESSENTIAL PART-OF
		OWLObjectProperty epo = addObjProp("essentialPartOf", null, null, "partOf", null);
		epo.addSuperProperty("invExistentiallyDependentOf");

		// COMPONENT-OF
		List<String> lDomainCOf = new LinkedList<String>();
		List<String> lRangeCOf = new LinkedList<String>();
		lDomainCOf.add("FunctionalComplex");
		lRangeCOf.add("FunctionalComplex");
		if (is4DView())
		{
			//this relation holds also at ts-level
			lDomainCOf.add("FunctionalComplexTS");
			lRangeCOf.add("FunctionalComplexTS");
		}
		addObjProp("componentOf", lDomainCOf, lRangeCOf, "partOf");

		// MEMBER-OF
		List<String> lDomainMOf = new LinkedList<String>();
		List<String> lRangeMOf = new LinkedList<String>();
		lDomainMOf.add("FunctionalComplex");
		lDomainMOf.add("Collective");
		lRangeMOf.add("Collective");
		if (is4DView())
		{
			//this relation holds also at ts-level
			lDomainMOf.add("FunctionalComplexTS");
			lDomainMOf.add("CollectiveTS");
			lRangeMOf.add("CollectiveTS");
		}
		addObjProp("memberOf", lDomainMOf, lRangeMOf, "partOf");
		
		// SUB-COLLECTION-OF
		List<String> lDomainSCOf = new LinkedList<String>();
		List<String> lRangeSCOf = new LinkedList<String>();
		lDomainSCOf.add("Collective");
		lRangeSCOf.add("Collective");
		if (is4DView())
		{
			//this relation holds also at ts-level
			lDomainSCOf.add("CollectiveTS");
			lRangeSCOf.add("CollectiveTS");
		}
		addObjProp("subCollectionOf", lDomainSCOf, lRangeSCOf, "partOf");

		// SUB-QUANTITY-OF
		List<String> lDomainSQOf = new LinkedList<String>();
		List<String> lRangeSQOf = new LinkedList<String>();
		lDomainSQOf.add("Quantity");
		lRangeSQOf.add("Quantity");
		if (is4DView())
		{
			//this relation holds also at ts-level
			lDomainSQOf.add("QuantityTS");
			lRangeSQOf.add("QuantityTS");
		}
		addObjProp("subQuantityOf", lDomainSQOf, lRangeSQOf, "essentialPartOf");

		if (isReificationView())
		{
			// PART-OF-RELATOR
			OWLObjectProperty por = addObjProp("partOfRelator", "RelationalQuaIndividual", "Relator", "inseparablePartOf", null);
			por.addSuperProperty("essentialPartOf");
			por.setFunctional();
		}
		
		/*************************************************************/
	}

	/*************************************************************
 	 * Procedures for adding a Class to the OWL Elements */
	public OWLClass addClass(String name, String superClass)
	{
		OWLClass n = new OWLClass(name, superClass);
		OWLClasses.add(n);
		return n;
	}

	public OWLClass addClass(String name, String superClass, String[] completeClassesNames, String[] disjointClassesNames)
	{
		if (name == null) return null;

		List<String> superClasses = new LinkedList<String>();
		if (superClass != null)
		{
			superClasses = new LinkedList<String>();
			superClasses.add(superClass);
		}

		List<List<String>> llcompleteClasses = null;
		if (completeClassesNames != null)
		{
			llcompleteClasses = new LinkedList<List<String>>();
			llcompleteClasses.add(Arrays.asList(completeClassesNames));
		}
		List<String> ldisjointClasses = null;
		if (disjointClassesNames != null)
		{
			//ldisjointClasses = new LinkedList<String>();
			ldisjointClasses = Arrays.asList(disjointClassesNames);
		}
			
		OWLClass n = new OWLClass(name, superClasses, llcompleteClasses, ldisjointClasses);
		OWLClasses.add(n);
		return n;
	}

	public OWLClass addClass(String name, List<String> superClasses, List<List<String>> completeClassesNames, List<String> disjointClassesNames)
	{
		OWLClass n = new OWLClass(name, superClasses, completeClassesNames, disjointClassesNames);
		OWLClasses.add(n);
		return n;
	}
	
	/*************************************************************
 	 * Procedures for getting a Class to the OWL Elements */
    public OWLClass getOWLClass(String name)
	{
		for (OWLClass c : OWLClasses)
		{
			if (c.name.equals(name)) return c;
		}
		return null;
	}

	/*************************************************************
 	 * Procedures for adding an Object Property to the OWL Elements */
	public OWLObjectProperty addObjProp(String name, String domain, String range, String superProp, String invProp)
	{
		OWLObjectProperty n = new OWLObjectProperty(name, domain, range, superProp, invProp);
		OWLObjectProperties.add(n);
		return n;
	}

	public OWLObjectProperty addObjProp(String name, List<String> disjDomains, List<String> disjRanges, List<String> lSuperProperties)
	{
		OWLObjectProperty n = new OWLObjectProperty(name, disjDomains, disjRanges, lSuperProperties);
		OWLObjectProperties.add(n);
		return n;
	}
	
	public OWLObjectProperty addObjProp(String name, List<String> disjDomains, List<String> disjRanges, String superProperty)
	{
		List<String> lsp = null;
		if (superProperty != null)
		{
			lsp = new LinkedList<String>();
			lsp.add(superProperty);
		}
		OWLObjectProperty n = new OWLObjectProperty(name, disjDomains, disjRanges, lsp);
		OWLObjectProperties.add(n);
		return n;
	}
	
	/*************************************************************
 	 * Procedures for getting an Object Property from the OWL Elements */
	public OWLObjectProperty getOWLObjProp(String name)
	{
		for (OWLObjectProperty p : OWLObjectProperties)
		{
			if (p.name.equals(name)) return p;
		}
		return null;
	}

	/*************************************************************
 	 * Procedures for adding an DataType Property to the OWL Elements */
	public OWLDataTypeProperty addDTProp(String name, String domain, String range, String superProp)
	{
		OWLDataTypeProperty n = new OWLDataTypeProperty(name, domain, range, superProp);
		OWLDataTypeProperties.add(n);
		return n;
	}
	
	/*************************************************************
 	 * Procedures for printing the OWL Elements 	         */
	public String verbose(String modelName)
	{
		String out = "";
		for (OWLObjectProperty p : OWLObjectProperties)
		{
			out += p.verbose();
		}
		for (OWLDataTypeProperty p : OWLDataTypeProperties)
		{
			out += p.verbose();
		}
		for (OWLClass c : OWLClasses)
		{
			out += c.verbose();
		}
		MainVerbose.setModelId(modelName);
		return MainVerbose.initialVerbose() + out + MainVerbose.finalVerbose();
	}
	
	/*************************************************************
 	 * Procedures for mapping the treeProcessor elements
 	 * into OWLStructure 	         */
    public void map(TreeProcessor tp)
    {
		completeOWLStructuralClasses();
		
		for (NodeClass nc : tp.nodes)
		{
			mapNodeClass2OWL(nc);
			mapAttributes2OWL(nc);
		}
		
		for (NodeBinAssociation na : tp.assocNodes)
			mapNodeAssociation2OWL(na);
    }
    
    public void completeOWLStructuralClasses()
    {
		OWLClass oc;

		oc = getOWLClass("FunctionalComplex");
		oc.addCompleteClasses(TreeProcessor.kindsNames);

		oc = getOWLClass("Collective");
		oc.addCompleteClasses(TreeProcessor.collectivesNames);

		oc = getOWLClass("Quantity");
		oc.addCompleteClasses(TreeProcessor.quantitiesNames);

		oc = getOWLClass("Relator");
		oc.addCompleteClasses(TreeProcessor.relatorsNames);

		if (isReificationView())
		{
			if (TreeProcessor.modesNames == null)
				TreeProcessor.modesNames = new LinkedList<String>();
			TreeProcessor.modesNames.add("QuaIndividual");
		}
		oc = getOWLClass("Mode");
		if (oc != null)
			oc.addCompleteClasses(TreeProcessor.modesNames);
		
		oc = getOWLClass("RelationalQuaIndividual");
		if (oc != null)
			oc.addCompleteClasses(TreeProcessor.relationalquasNames);

		oc = getOWLClass("PhasedQuaIndividual");
		if (oc != null)
			oc.addCompleteClasses(TreeProcessor.phasedquasNames);

		oc = getOWLClass("Quality");
		if (oc != null)
			oc.addCompleteClasses(TreeProcessor.qualitiesNames);    	
    }
    
    public void mapNodeClass2OWL(NodeClass n)
    {
		String className; 
		List<String> lparents = null;
		List<List<String>> llchildren = null;
		List<String> lsiblings = null;

		Boolean addTS = is4DView();
		if ((isReificationView()) && (!n.isRigid()))
		{
			className = n.getReifiedName();
			lparents = new LinkedList<String>();
			lparents.add(n.getStructuralParent());
		}
		else
		{
			//if is4DView, the class is created at the ts level
			className = n.getName(addTS);
			lparents = n.getParentsNames(addTS);
		}

		llchildren = n.getCompleteChildren(addTS, is4DView(), isReificationView());
		lsiblings = n.getDisjointSiblingsNames(addTS, isReificationView());
		
		OWLClass oc = addClass(className, lparents, llchildren, lsiblings);
		
		if (is4DView())
		{
			if (n.isRigid())
			{
				addTS = false;
				className = n.getName(addTS);
				lparents = n.getParentsNames(addTS);
				llchildren = n.getCompleteChildren(addTS, is4DView(), isReificationView());
				lsiblings = n.getDisjointSiblingsNames(addTS, isReificationView());
				OWLClass ocic = addClass(className, lparents, llchildren, lsiblings);

				//adding restrictions
				oc.addRestriction("equivalentClass", "none", "some", null, "timeSliceOf", n.getName(), false, null, null, null);
				ocic.addRestriction("subClass", "none", "only", null, "timeSliceOf", n.getTSName(), true, null, null, null);
			}
		}
		
		if ((isReificationView()) && (!n.isRigid()))
		{
			// rigid parents are those in which the qua inheres
			List<String> inhParents = n.getRigidParentsNames(false);
			if (inhParents != null)
				oc.addRestriction("subClass", "none", "some", null, "inheresIn", null, false, inhParents, null, null);
			
			// reified parents are those in which the qua is existentially dependent of
			List<String> reifiedParents = n.getReifiedParentsNames();
			if (reifiedParents != null)
				oc.addRestriction("subClass", "none", "some", null, "existentiallyDependentOf", null, false, reifiedParents, null, null);
		}
    	
    }
    
    public void mapAttributes2OWL(NodeClass n)
    {
		List<Property> properties = n.getRelatedClass().getOwnedAttribute();
		if (properties != null)
			for (Property p : properties)	
			{
				String name = p.getName() + n.getName();
				String domain, range = null;
				
				Integer min = p.lowerBound(),
						max = p.upperBound();
	
				// FIXME: when the Eclipse's bug no more exists
				// Improvising an way of getting datatype from OntoUML model
				// due to a kind of bug of Eclipse on recognizing Primitive Types
				if (p.getType() != null)
				{
					String datatype = p.getType().getName().toLowerCase();
					if ( datatype.equals("string") || datatype.equals("integer") ||
						 datatype.equals("short")  || datatype.equals("long")    ||	
						 datatype.equals("double") || datatype.equals("float")   ||	
						 datatype.equals("date")   || datatype.equals("time")    ||
						 datatype.equals("dateTime") )
					range = "&xsd;" + datatype;
				}
				
				if (isReificationView()) 
				{
					OWLClass qlt = addClass(/*NAME:*/ name, /*SUPERCLASS:*/ "Quality");
	
					qlt.addRestriction("some", "inheresIn", n.getName(), false, null, null);
					qlt.addRestriction("some", "hasValue", range, false, null, null);
					if (TreeProcessor.qualitiesNames == null)
						TreeProcessor.qualitiesNames = new LinkedList<String>();
					TreeProcessor.qualitiesNames.add(name);
						
					//adding an owl cardinality restriction for the class
					//if it is not an immutable attribute, thus the individual
					//can have a number of qualities with different values
					//inhering in it
					if (!p.isIsReadOnly()) 
						max = -1;
					
					if (n.isRigid())
						domain = n.getName();
					else
						domain = n.getReifiedName();
					
					OWLClass oc = getOWLClass(domain);
					oc.addSubClassCardRestriction("inheresIn", name, true, min, max);
					
				}
				else
				{
					
					Boolean addTS = (is4DView()) && ((mappingType == MappingType.WORM_VIEW_A0) 
							                         || !(p.isIsReadOnly() && n.isRigid() && (min > 0)));
					domain = n.getName(addTS);
	
					addDTProp(name, domain, range, /*SUPER-CLASS:*/ null);
	
					if ((min > 0) || (max > 0))
					{
						OWLClass oc = getOWLClass(domain);
						oc.addSubClassCardRestriction(name, "", false, min, max);
					}
				}
			}
	}

    public void mapNodeAssociation2OWL(NodeBinAssociation n)
    {
		if ((n.mappingName != null) &&
				!(isReificationView() && (n.myAssociation instanceof MaterialAssociation)) )
				//create the property
				addObjProp(n.mappingName, n.getDomainList(mappingType), 
										  n.getRangeList(mappingType), 
										  n.getSuperAssocList(mappingType));
			
			n.setRestrictions(mappingType);
			if (n.listRestrictions != null)
				for(Restriction r : n.listRestrictions)
				{
					OWLClass oc = getOWLClass(r.restrictedClass);
					if (oc != null)
					{
						if (r.equivalent)
							oc.addEquivClassCardRestriction(r.propertyName, r.listClasses, r.inverse, r.min, r.max);
						else
							oc.addSubClassCardRestriction(r.propertyName, r.listClasses, r.inverse, r.min, r.max);
					}
				}		    	
    }
}
