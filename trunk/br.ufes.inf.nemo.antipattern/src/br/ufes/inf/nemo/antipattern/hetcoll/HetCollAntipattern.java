package br.ufes.inf.nemo.antipattern.hetcoll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;


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

	public AntipatternInfo info(){
		return info;
	}

	public ArrayList<HetCollOccurrence> identifyOCL() {
		Map<Classifier, ArrayList<Property>> query_result;
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "whole", "memberEnds");
			
		for (Classifier whole : query_result.keySet()) 
		{
			try {
					HetCollOccurrence occurrence = new HetCollOccurrence(whole, query_result.get(whole), new ArrayList<Property>(), this);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

	@Override
	public ArrayList<HetCollOccurrence> identify() {
		HashMap<Classifier,HashSet<Property>> memberHash = buildPropertyHash(memberOf.class);
		HashMap<Classifier,HashSet<Property>> collectionHash = buildPropertyHash(subCollectionOf.class);
		
		for (Classifier whole : memberHash.keySet()) {
			
			ArrayList<Property> memberProperties = new ArrayList<Property>(memberHash.get(whole));
			ArrayList<Property> collectionProperties = new ArrayList<Property>();

			if(collectionHash.get(whole)!=null)
				collectionProperties.addAll(collectionHash.get(whole));
			
			if(memberProperties.size()>=2 && hasDirectPart(whole,memberProperties)){
				try {
					occurrence.add(new HetCollOccurrence(whole, memberProperties, collectionProperties, this));
				} catch (Exception e) {
					System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
					System.out.println(e.getMessage());
				}
			}
		}
		
		return occurrence;
	}
	
	public <B extends Meronymic> HashMap<Classifier,HashSet<Property>> buildPropertyHash(Class<B> type){
		HashMap<Classifier,HashSet<Property>> hash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with meronymics that are directly connected to the types
		for (B m : parser.getAllInstances(type)) {
			
			try{
				Property partEnd = OntoUMLParser.getPartEnd(m);
				Classifier whole = (Classifier) OntoUMLParser.getWholeEnd(m).getType();
				
				if (hash.containsKey(whole))
					hash.get(whole).add(partEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(partEnd);
					hash.put(whole, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' parts
		for (Classifier mainType : hash.keySet()) 
			for (Classifier parent : mainType.allParents()) 
				if(hash.containsKey(parent))
					hash.get(mainType).addAll(hash.get(parent));
			
		return hash;
	}
	
	public boolean hasDirectPart(Classifier c, ArrayList<Property> partEnds){
		for (Property p : partEnds) {
			if(p.getOpposite().getType().equals(c))
				return true;
		}
		
		return false;
	}
}
