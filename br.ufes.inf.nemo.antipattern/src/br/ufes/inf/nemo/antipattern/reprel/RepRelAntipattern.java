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
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;

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
			"This anti-pattern occurs when a «relator» is connected to two or more «mediation» associations, " +
			"such that the upper bound cardinalities at the relator end are greater than one.",
			oclQuery);

	public AntipatternInfo info(){
		return info;
	}

	private HashMap<Classifier,HashSet<Property>> problematicMediatedHash;

	private HashMap<Classifier, HashSet<Property>> allMediatedHash; 
	
	@Deprecated
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

	

	@Deprecated
	public ArrayList<RepRelOccurrence> identifyOLD() {
		buildProblematicMediatedEndsHash();
		
		for (Classifier relator : problematicMediatedHash.keySet()) {
			
			if(relator instanceof Relator && problematicMediatedHash.get(relator).size()>=2){
				
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
	
	@Override
	public ArrayList<RepRelOccurrence> identify() {
		buildAllHashes();
		
		for (Classifier relator : problematicMediatedHash.keySet()) {
			
//			System.out.println("Relator: "+relator.getName()+", Size: "+problematicMediatedHash.get(relator).size());
			
			if(problematicMediatedHash.get(relator).size()>=2){
				
				try {
					RepRelOccurrence repRel = new RepRelOccurrence((Relator) relator,allMediatedHash.get(relator),problematicMediatedHash.get(relator), this);
					occurrence.add(repRel);
				} catch (Exception e) {
					System.out.println(info.getAcronym()+": Could not create occurrence!");
					System.out.println(e.getMessage());
				}
			}
		}
		
		return this.getOccurrences(); 
	}
	

	public void buildAllHashes(){
		problematicMediatedHash = new HashMap<Classifier,HashSet<Property>>();
		allMediatedHash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with mediation that are directly connected to the types
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			
//			System.out.println("Mediation: "+OntoUMLNameHelper.getCompleteName(m));
			
			try{
				Property relatorEnd = OntoUMLParser.getRelatorEnd(m);
				Property mediatedEnd = OntoUMLParser.getMediatedEnd(m);
				
				Classifier relator = (Classifier) relatorEnd.getType();
				
				boolean isRelator = relator instanceof Relator;
				
				if(!isRelator)
					continue;
				
				if (allMediatedHash.keySet().contains(relator))
					allMediatedHash.get(relator).add(mediatedEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(mediatedEnd);
					allMediatedHash.put(relator, properties);
				}
				
				 if( relatorEnd.getUpper()==0 || relatorEnd.getUpper()==1)
					 continue;
				
				 if (problematicMediatedHash.keySet().contains(relator))
					 problematicMediatedHash.get(relator).add(mediatedEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(mediatedEnd);
					problematicMediatedHash.put(relator, properties);
				}
			}
			catch(Exception e){ 
				e.printStackTrace();
			}
		}
		
		//adds supertypes' mediated ends (if not subsetted or redefined properties)
		for (Classifier relator : problematicMediatedHash.keySet()) {
			
//			System.out.println("Relator: "+relator.getName()+", Size: "+problematicMediatedHash.get(relator).size());
			try{
				for (Classifier parent : parser.getAllParents(relator)) {
					HashSet<Property> subsettedOrRedefined = new HashSet<Property>();
					
					if(!problematicMediatedHash.keySet().contains(parent) || problematicMediatedHash.get(parent).size()>1)
						continue;
					
					for (Property parentProperty : problematicMediatedHash.get(parent)) {
						for (Property childProperty : problematicMediatedHash.get(relator)) {
							if(RelSpecAntipattern.isSubsettingOrRedefinition(childProperty.getAssociation(), parentProperty.getAssociation())){
								subsettedOrRedefined.add(parentProperty);
								break;
							}
						}
					}
					
					for (Property parentProperty : problematicMediatedHash.get(parent))
						if(!subsettedOrRedefined.contains(parentProperty))
							problematicMediatedHash.get(relator).add(parentProperty);
					
					allMediatedHash.get(relator).addAll(allMediatedHash.get(parent));
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Deprecated
	public void buildProblematicMediatedEndsHash(){
		problematicMediatedHash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with mediation that are directly connected to the types
		for (Property p : setPropertyList(parser.getAllInstances(Mediation.class))) {
			
			try{
				Classifier type = (Classifier) p.getType();
				
				if(!(type instanceof Relator) || p.getUpper()==0 || p.getUpper()==1)
					continue;
					
				if (problematicMediatedHash.keySet().contains(type))
					problematicMediatedHash.get(type).add(p);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(p);
					problematicMediatedHash.put(type, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' mediated ends
		for (Classifier relator : problematicMediatedHash.keySet()) 
			for (Classifier parent : relator.allParents()) 
				if(problematicMediatedHash.keySet().contains(parent))
					problematicMediatedHash.get(relator).addAll(problematicMediatedHash.get(parent));
			
	}
	
	@Deprecated
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
