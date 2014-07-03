package br.ufes.inf.nemo.ontouml2temporalowl.verbose;

public class OWLVerbose
{
	// TODO: every "open" method increases ident, every "close" method decreases ident
	public static int tab = 1;
	
	public static void increaseIdent()
	{
		tab++;
	}
	
	public static void decreaseIdent()
	{
		tab--;
		if (tab < 1) tab = 1;
	}
	
	public static String tab()
	{
		String out = "";
		for (int i = 0; i < tab; i++)
			out += "\t";
		return out;		
	}
	
	public static String openObjectProperty (String about)
	{
		String out = tab() + "<owl:ObjectProperty rdf:about=\"#" + about + "\">\n";
		increaseIdent();
		return out;
	}
	
	public static String openObjectProperty()
	{
		String out = tab() + "<owl:ObjectProperty>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeObjectProperty ()
	{
		decreaseIdent();
		return tab() + "</owl:ObjectProperty>\n";
	}
	
	public static String openCloseObjectProperty (String about)
	{
		return tab() + "<owl:ObjectProperty rdf:about=\"#" + about + "\"/>\n";
	}
	
	public static String openCloseType (String resource)
	{
		return tab() + "<rdf:type rdf:resource=\"&owl;" + resource + "\"/>\n";
	}
	
	public static String openDomain()
	{
		String out = tab() + "<rdfs:domain>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeDomain()
	{
		decreaseIdent();
		return tab() + "</rdfs:domain>\n";
	}
	
	public static String openRange()
	{
		String out = tab() + "<rdfs:range>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeRange()
	{
		decreaseIdent();
		return tab() + "</rdfs:range>\n";
	}

	public static String openCloseDomain (String resource)
	{
		return tab() + "<rdfs:domain rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openCloseRange (String resource)
	{
		if (!resource.contains("&xsd;")) //then it is not a datatype
			resource = "#" + resource;
		return tab() + "<rdfs:range rdf:resource=\"" + resource + "\"/>\n";
	}
	
	public static String openCloseSubPropertyOf (String resource)
	{
		return tab() + "<rdfs:subPropertyOf rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openCloseClass (String about)
	{
		return tab() + "<owl:Class rdf:about=\"#" + about +"\"/>\n";
	}
	
	public static String openClass (String about)
	{
		String out = tab() + "<owl:Class rdf:about=\"#" + about +"\">\n";
		increaseIdent();
		return out;
	}
		
	public static String openClass()
	{
		String out = tab() + "<owl:Class>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeClass()
	{
		decreaseIdent();
		return tab() + "</owl:Class>\n";
	}
	
	public static String openCloseSubClassOf (String resource)
	{
		return tab() + "<rdfs:subClassOf rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openSubClassOf()
	{
		String out = tab() + "<rdfs:subClassOf>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeSubClassOf()
	{
		decreaseIdent();
		return tab() + "</rdfs:subClassOf>\n";
	}
	
	public static String openUnionOf (String parseType)
	{
		String out = tab() + "<owl:unionOf rdf:parseType=\"" + parseType + "\">\n";
		increaseIdent();
		return out;
	}
	
	public static String closeUnionOf()
	{
		decreaseIdent();
		return tab() + "</owl:unionOf>\n";
	}
	
	public static String openCloseDescription (String about)
	{
		if (!about.contains("&xsd;")) //then it is not a datatype
			about = "#" + about;
		return tab() + "<rdf:Description rdf:about=\"" + about + "\"/>\n";
	}
	
	public static String openCloseDisjointWith (String resource)
	{
		return tab() + "<owl:disjointWith rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openRestriction()
	{
		String out = tab() + "<owl:Restriction>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeRestriction()
	{
		decreaseIdent();
		return tab() + "</owl:Restriction>\n";
	}
	
	public static String openCloseOnProperty (String resource)
	{
		return tab() + "<owl:onProperty rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openOnProperty()
	{
		String out = tab() + "<owl:onProperty>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeOnProperty()
	{
		decreaseIdent();
		return tab() + "</owl:onProperty>\n";
	}
	
	public static String openCloseSomeValuesFrom (String resource)
	{
		if (!resource.contains("&xsd;")) //then it is not a datatype
			resource = "#" + resource;
		return tab() + "<owl:someValuesFrom rdf:resource=\"" + resource + "\"/>\n";
	}
	
	public static String openSomeValuesFrom()
	{
		String out = tab() + "<owl:someValuesFrom>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeSomeValuesFrom()
	{
		decreaseIdent();
		return tab() + "</owl:someValuesFrom>\n";
	}
	
	public static String openCloseAllValuesFrom (String resource)
	{
		return tab() + "<owl:allValuesFrom rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openAllValuesFrom()
	{
		String out = tab() + "<owl:allValuesFrom>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeAllValuesFrom()
	{
		decreaseIdent();
		return tab() + "</owl:allValuesFrom>\n";
	}
	
	public static String openCloseOnClass (String resource)
	{
		 return tab() + "<owl:onClass rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openEquivalentClass()
	{
		String out = tab() + "<owl:equivalentClass>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeEquivalentClass()
	{
		decreaseIdent();
		return tab() + "</owl:equivalentClass>\n";
	}
	
	public static String openCloseQualifiedCardinality (String value)
	{
		return tab() + "<owl:qualifiedCardinality rdf:datatype=\"&xsd;nonNegativeInteger\">" + value + "</owl:qualifiedCardinality>\n";
	}
	
	public static String openCloseMinQualifiedCardinality (String value)
	{
		return tab() + "<owl:minQualifiedCardinality rdf:datatype=\"&xsd;nonNegativeInteger\">" + value + "</owl:minQualifiedCardinality>\n";
	}
	
	public static String openCloseMaxQualifiedCardinality (String value)
	{
		return tab() + "<owl:maxQualifiedCardinality rdf:datatype=\"&xsd;nonNegativeInteger\">" + value + "</owl:maxQualifiedCardinality>\n";
	}
	
	public static String openCloseInverseOf (String resource)
	{
		return tab() + "<owl:inverseOf rdf:resource=\"#" + resource + "\"/>\n";
	}
	
	public static String openIntersectionOf (String parseType)
	{
		String out = tab() + "<owl:intersectionOf rdf:parseType=\"" + parseType + "\">\n";
		increaseIdent();
		return out;
	}
	
	public static String closeIntersectionOf()
	{
		decreaseIdent();
		return  tab() + "</owl:intersectionOf>\n";
	}

	public static String openOnClass() 
	{
		String out = tab() + "<owl:onClass>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeOnClass()
	{
		decreaseIdent();
		return tab() + "</owl:onClass>\n";
	}

	public static String openPropChain (String parseType)
	{
		String out = tab() + "<owl:propertyChainAxiom rdf:parseType=\"" + parseType + "\">\n";
		increaseIdent();
		return out;
	}
	
	public static String closePropChain()
	{
		decreaseIdent();
		return tab() + "</owl:propertyChainAxiom>\n";
	}

	public static String openDataTypeProperty(String about)
	{
		String out = tab() + "<owl:DatatypeProperty rdf:about=\"#" + about + "\">\n";
		increaseIdent();
		return out;
	}
	
	public static String openDataTypeProperty()
	{
		String out = tab() + "<owl:DatatypeProperty>\n";
		increaseIdent();
		return out;
	}
	
	public static String closeDataTypeProperty ()
	{
		decreaseIdent();
		return tab() + "</owl:DatatypeProperty>\n";
	}	

}
