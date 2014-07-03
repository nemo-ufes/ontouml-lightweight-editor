package br.ufes.inf.nemo.ontouml2temporalowl.auxiliary;

import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2temporalowl.verbose.MainVerbose;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.OWLVerbose;

/***********************************************************
 * General functions for dealing with the owl DataType Properties for mapping
 * 
 * Main features: 
 * @param str name: the object property name, 
 * @param <<str>> domains: list of conjunction of disjunction of domain-classes
 * @param <<str>> ranges: list of conjunction of disjunction of range-classes
 * @param <str> superProperties: list of super-properties
 * @param byte functionality: whether the property is
 * 						0: none, 1: functional, -1: inv-functional, 2: both
 */

public class OWLDataTypeProperty
{
	String name;
	List<List<String>> domains = null; // conjunction of disjunction
	List<List<String>> ranges = null; // conjunction of disjunction
	byte functionality = 0; // whether the property is
	//						0: none, 1: functional
	
	// create a simple property, setting a single domain, range and super-property
	public OWLDataTypeProperty(String propName, String domain, String range, String superProperty)
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
	}
	
	// create a property
	public OWLDataTypeProperty(String propName, List<String> disjDomains, List<String> disjRanges, List<String> lSuperProperties)
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

	public void setFunctional()
	{
		functionality = 1;
	}

	public String verbose()
	{
		String out = MainVerbose.header(name.replace(" ", "_")) + 
		OWLVerbose.openDataTypeProperty(name.replace(" ", "_"));
		
		if (functionality == 1)
			out += OWLVerbose.openCloseType("FunctionalProperty");


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
						out += OWLVerbose.openDomain() +
							OWLVerbose.openClass() +
							OWLVerbose.openUnionOf("Collection");

						for (String r : ldr)
							out += OWLVerbose.openCloseDescription(r.replace(" ", "_"));

						out += 	OWLVerbose.closeUnionOf() +
							OWLVerbose.closeClass() +
							OWLVerbose.closeDomain();
					}
		
		out += OWLVerbose.closeDataTypeProperty() +
		MainVerbose.sectionBreak();

		return out;
	}
}
