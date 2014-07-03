package br.ufes.inf.nemo.ontouml2temporalowl.auxiliary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2temporalowl.verbose.MainVerbose;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.OWLVerbose;

/***********************************************************
 * General functions for dealing with the owl Object Properties for mapping
 * 
 * Main features: 
 * @param str name: the object property name, 
 * @param <<str>> domains: list of conjunction of disjunction of domain-classes
 * @param <<str>> ranges: list of conjunction of disjunction of range-classes
 * @param byte functionality: whether the property is
 * 						0: none, 1: functional, -1: inv-functional, 2: both
 * @param byte transitivity: whether the property is
 * 						  0: none, 1: transitive
 * @param byte symmetry: whether the property is
 * 						  0: none, 1: symmetric, -1: asymmetric
 * @param byte reflexivity: whether the property is
 * 						  0: none, 1: reflexive, -1: irreflexive
 * @param <str> superProperties: list of super-properties
 * @param <str> invProperties: list of inverse properties
 *  //@param disjointProps: list of disjoint properties
 * @param propChains: list of property-chains 
 */

public class OWLObjectProperty
{
	String name;
	List<List<String>> domains = null; // conjunction of disjunction
	List<List<String>> ranges = null; // conjunction of disjunction
	byte functionality = 0; // whether the property is
	//						0: none, 1: functional, -1: inv-functional, 2: both
	byte transitivity = 0; // whether the property is
	// 						  0: none, 1: transitive
	byte symmetry = 0; // whether the property is
	//						  0: none, 1: symmetric, -1: asymmetric
	byte reflexivity = 0; // whether the property is
	// 						  0: none, 1: reflexive, -1: irreflexive
	List<String> superProperties = null; // list of super-properties
	List<String> invProperties = null; // list of inverse properties
	List<List<String>> propChains = null; // list of inverse properties
	
	// create a simple property, setting a single domain, range and super-property
	public OWLObjectProperty(String propName, String domain, String range, String superProperty, String invProperty)
	{
		name = propName;
		if (domain != null)
		{
			domains = new LinkedList<List<String>>();
			List<String> disjDomains = new LinkedList<String>();
			disjDomains.add(domain);
			domains.add(disjDomains);
		}
		if (range != null)
		{
			ranges = new LinkedList<List<String>>();
			List<String> disjRanges = new LinkedList<String>();
			disjRanges.add(range);
			ranges.add(disjRanges);
		}
		if (superProperty != null)
		{
			superProperties = new LinkedList<String>();
			superProperties.add(superProperty);
		}
		if (invProperty != null)
		{
			invProperties = new LinkedList<String>();
			invProperties.add(invProperty);
		}
	}
	
	// create a simple property
	public OWLObjectProperty(String propName, String domain, String range, 
			String superProperty, String invProperty, 
			byte cfunctionality, byte ctransitivity, byte csymmetry, byte creflexivity)
	{
		name = propName;
		if (domain != null)
		{
			domains = new LinkedList<List<String>>();
			List<String> disjDomains = new LinkedList<String>();
			disjDomains.add(domain);
			domains.add(disjDomains);
		}
		if (range != null)
		{
			ranges = new LinkedList<List<String>>();
			List<String> disjRanges = new LinkedList<String>();
			disjRanges.add(range);
			ranges.add(disjRanges);
		}
		if (superProperty != null)
		{
			superProperties = new LinkedList<String>();
			superProperties.add(superProperty);
		}
		if (invProperty != null)
		{
			invProperties = new LinkedList<String>();
			invProperties.add(invProperty);
		}
		functionality = cfunctionality; 
		transitivity = ctransitivity; 
		symmetry = csymmetry;
		reflexivity = creflexivity;
	}

	// create a property
	public OWLObjectProperty(String propName, List<String> disjDomains, List<String> disjRanges, List<String> lSuperProperties)
	{
		name = propName;
		if (disjDomains != null)
		{
			domains = new LinkedList<List<String>>();
			domains.add(disjDomains);
		}
		if (disjRanges != null)
		{
			ranges = new LinkedList<List<String>>();
			ranges.add(disjRanges);
		}
		superProperties = lSuperProperties;
	}

	public void addDomain(String domain)
	{
		if (domain == null) return;
		if (domains == null)
			domains = new LinkedList<List<String>>();
		List<String> disjDomains = new LinkedList<String>();
		disjDomains.add(domain);
		domains.add(disjDomains);
	}

	public void addDomain(List<String> disjDomains)
	{
		if (disjDomains == null) return;
		if (domains == null)
			domains = new LinkedList<List<String>>();
		domains.add(disjDomains);
	}

	public void addRange(String range)
	{
		if (range == null) return;
		if (ranges == null)
			ranges = new LinkedList<List<String>>();
		List<String> disjRanges = new LinkedList<String>();
		disjRanges.add(range);
		ranges.add(disjRanges);
	}

	//TODO
	public void addRange(List<String> disjRanges)
	{
		if (disjRanges == null) return;
		if (ranges == null)
			ranges = new LinkedList<List<String>>();
		ranges.add(disjRanges);
	}

	public void addSuperProperty(String superProp)
	{
		if (superProp == null) return;
		if (superProperties == null)
			superProperties = new LinkedList<String>();
		superProperties.add(superProp);
	}

	public void addPropChain(String[] propChain)
	{
		if (propChain == null) return;
		if (propChains == null)
			propChains = new LinkedList<List<String>>();
		propChains.add(Arrays.asList(propChain));
	}

	public void setTransitive()
	{
		transitivity = 1;
	}
	
	public void setSymmetric()
	{
		symmetry = 1;
	}
	
	public void setAsymmetric()
	{
		symmetry = -1;
	}

	public void setReflexive()
	{
		reflexivity = 1;
	}

	public void setIrreflexive()
	{
		reflexivity = -1;
	}

	public void setFunctional()
	{
		if (functionality == -1) 
			functionality = 2;
		else
			functionality = 1;
	}

	public void setInvFunctional()
	{
		if (functionality == 1) 
			functionality = 2;
		else
			functionality = -1;
	}

	public String verbose()
	{
		if (name == null) return "";
		String out = MainVerbose.header(name.replace(" ", "_")) + 
		OWLVerbose.openObjectProperty(name.replace(" ", "_"));
		
		if (functionality == 1 || functionality == 2)
			out += OWLVerbose.openCloseType("FunctionalProperty");

		if (functionality == -1 || functionality == 2)
			out += OWLVerbose.openCloseType("InverseFunctionalProperty");

		if (transitivity == 1)
			out += OWLVerbose.openCloseType("TransitiveProperty");

		if (symmetry == 1)
			out += OWLVerbose.openCloseType("SymmetricProperty");
		else if (symmetry == -1)
			out += OWLVerbose.openCloseType("AsymmetricProperty");
			
		if (reflexivity == 1)
			out += OWLVerbose.openCloseType("ReflexiveProperty");
		else if (reflexivity == -1)
			out += OWLVerbose.openCloseType("IrreflexiveProperty");

    	if (domains != null)
			for (List<String> ldd: domains)
				if (ldd != null)
					if (ldd.size() == 1)
						out += OWLVerbose.openCloseDomain(ldd.get(0).replace(" ", "_"));
					else
					{
						out += OWLVerbose.openDomain() +
							OWLVerbose.openClass() +
							OWLVerbose.openUnionOf("Collection");

						for (String d : ldd)
							out += OWLVerbose.openCloseDescription(d.replace(" ", "_"));

						out += 	OWLVerbose.closeUnionOf() +
							OWLVerbose.closeClass() +
							OWLVerbose.closeDomain();
					}

		if (ranges != null)
			for (List<String> ldr: ranges)
				if (ldr != null)
					if (ldr.size() == 1)
						out += OWLVerbose.openCloseRange(ldr.get(0).replace(" ", "_"));
					else
					{
						out += OWLVerbose.openRange() +
							OWLVerbose.openClass() +
							OWLVerbose.openUnionOf("Collection");

						for (String r : ldr)
							out += OWLVerbose.openCloseDescription(r.replace(" ", "_"));

						out += 	OWLVerbose.closeUnionOf() +
							OWLVerbose.closeClass() +
							OWLVerbose.closeRange();
					}

		if (superProperties != null)
			for (String s: superProperties)
				out += OWLVerbose.openCloseSubPropertyOf(s.replace(" ", "_"));

		if (invProperties != null)
			for (String i: invProperties)
				out += OWLVerbose.openCloseInverseOf(i.replace(" ", "_"));

		if (propChains != null)
			for (List<String> pc: propChains)
				if (pc != null)
					{
						out += OWLVerbose.openPropChain("Collection");

						for (String p : pc)
							out += OWLVerbose.openCloseDescription(p.replace(" ", "_"));

						out += 	OWLVerbose.closePropChain();
					}
		
		out += OWLVerbose.closeObjectProperty() +
		MainVerbose.sectionBreak();

		return out;
	}
}

