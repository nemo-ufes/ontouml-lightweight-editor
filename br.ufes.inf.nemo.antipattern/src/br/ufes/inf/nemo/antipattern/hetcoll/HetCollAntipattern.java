package br.ufes.inf.nemo.antipattern.hetcoll;
import java.util.ArrayList;
import java.util.Map;

import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class HetCollAntipattern extends Antipattern<HetCollOccurrence> {

	public HetCollAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
		
	}
	
	public HetCollAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final String oclQuery =	
			"let wholeEnds : Bag(Property) = memberOf.allInstances().memberEnd->select( p : Property |  p.aggregation<>AggregationKind::none) "+
			"in  "+
			"	let chosen : Bag(Property) = wholeEnds->select ( c : Property | wholeEnds->select( x |  "+
			"		GeneralizationSet.allInstances()->select(gs : GeneralizationSet| gs.isCovering=true and gs.generalization.general->includes(c.type.oclAsType(Classifier))).generalization.specific->includes(x.type.oclAsType(Classifier)) "+ 
			"		or x.type=c.type  "+
			"		or c.type.oclAsType(Classifier).allParents()->includes(x.type.oclAsType(Classifier)) "+
			"		)->size()>1) "+
			"	in "+
			"		chosen->collect ( x | Tuple {whole : Classifier = x.type.oclAsType(Classifier), memberEnds : Set (Property) = wholeEnds->select( p:Property | p.type=x.type or x.type.oclAsType(Classifier).allParents()->includes(p.type.oclAsType(Classifier))).opposite->asSet()})->asSet()";
	
	
	public static final AntipatternInfo info = new AntipatternInfo("Heterogeneous Collective", 
			"HetColl", 
			"This anti-pattern is characterized by a whole collective type, a «collective» or a «subkind», «role» or «phase» " +
			"which is a subtype of a «collective», which is two or more «memberOf», associations with different types of member parts. ",
			HetCollAntipattern.oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<HetCollOccurrence> identify() {
		Map<Classifier, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "whole", "memberEnds");
			
		for (Classifier whole : query_result.keySet()) 
		{
			try {
					HetCollOccurrence occurrence = new HetCollOccurrence(whole, query_result.get(whole), this.parser);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
}
