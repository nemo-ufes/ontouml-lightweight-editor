package br.ufes.inf.nemo.antipattern.reprel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RepRelAntipattern extends Antipattern<RepRelOccurrence> {

	public RepRelAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	public RepRelAntipattern(Package pack) throws NullPointerException {
		super(pack);
		// TODO Auto-generated constructor stub
	}

	private static String oclQuery = 
		"	Relator.allInstances() "+
		"	->select(r: Relator |   "+
		"		Mediation.allInstances() "+
		"		->select(m:Mediation | "+ 
		"			(  "+
		"				(  "+
		"					r=m.memberEnd->at(1).type "+
		"					or   "+
		"					r.oclAsType(Classifier).allParents()->includes(m.memberEnd->at(1).type.oclAsType(Classifier)) "+ 
		"				)   "+
		"				and   "+
		"				(  "+
		"					m.memberEnd->at(1).upper=-1 "+  
		"					or   "+
		"					m.memberEnd->at(1).upper>1 "+ 
		"				) "+ 
		"			) "+
		"			or "+
		"			( "+
		"				(  "+
		"					r=m.memberEnd->at(2).type "+
		"					or   "+
		"					r.oclAsType(Classifier).allParents()->includes(m.memberEnd->at(2).type.oclAsType(Classifier)) "+ 
		"				)   "+
		"				and   "+
		"				(  "+
		"					m.memberEnd->at(2).upper=-1 "+  
		"					or   "+
		"					m.memberEnd->at(2).upper>1 "+ 
		"				) "+ 
		"			) "+
		"		)->size()>=2 "+
		"	)";
	
	private static final AntipatternInfo info = new AntipatternInfo("Repeatable Relator Instances", 
			"RepRel", 
			"This anti-pattern occurs when a «relator» is connected through a «mediation» association to at least one " +
			"rigid object type, stereotyped as «kind», «quantity», «collective», «subkind» or «category».",
			oclQuery); 
	
	public ArrayList<RepRelOccurrence> identifyOCL() {
		ArrayList<Relator> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Relator.class);
		
		for (Relator relator : query_result) 
		{
			try {
				RepRelOccurrence occurrence = new RepRelOccurrence(relator, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();

	}
	
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<RepRelOccurrence> identify() {
		HashMap<Classifier,HashSet<Property>> relatorEndHash = buildMainTypeAndMediatedPropertiesHash();
		
		for (Classifier relator : relatorEndHash.keySet()) {
			
			if(relator instanceof Relator && relatorEndHash.get(relator).size()>=2){
				
				try {
					RepRelOccurrence occurrence = new RepRelOccurrence((Relator) relator, this);
					super.occurrence.add(occurrence);
				} catch (Exception e) {
					System.out.println(info.getAcronym()+": Could not create occurrence!");
					System.out.println(e.getMessage());
				}
			}
		}
		
		return this.getOccurrences(); 
	}
	

	public HashMap<Classifier,HashSet<Property>> buildMainTypeAndMediatedPropertiesHash(){
		HashMap<Classifier,HashSet<Property>> relatorEndHash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with mediation that are directly connected to the types
		for (Property p : setPropertyList(parser.getAllInstances(Mediation.class))) {
			
			try{
				Classifier type = (Classifier) p.getType();
				
				//TODO: does not consider phase, subkind and role of relators
				if(!(type instanceof Relator) || p.getUpper()==0 || p.getUpper()==1)
					continue;
					
				if (relatorEndHash.keySet().contains(type))
					relatorEndHash.get(type).add(p);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(p);
					relatorEndHash.put(type, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' mediated ends
		for (Classifier relator : relatorEndHash.keySet()) 
			for (Classifier parent : relator.allParents()) 
				if(relatorEndHash.keySet().contains(parent))
					relatorEndHash.get(relator).addAll(relatorEndHash.get(parent));
			
		return relatorEndHash;
	}
	
	private <T extends Association> Set<Property> setPropertyList(Set<T> associations){
		Set<Property> allProperties = new HashSet<Property>();
		
		for (T t : associations) {
			if(t.getMemberEnd().size()!=2)
				continue;
			
			Property sourceEnd = t.getMemberEnd().get(0);
			Property targetEnd = t.getMemberEnd().get(1);
			
			if(sourceEnd==null || sourceEnd.getType()==null || targetEnd==null || targetEnd.getType()==null)
				continue;
			
			allProperties.add(t.getMemberEnd().get(0));
			allProperties.add(t.getMemberEnd().get(1));
		}
		
		return allProperties;
	}

}
