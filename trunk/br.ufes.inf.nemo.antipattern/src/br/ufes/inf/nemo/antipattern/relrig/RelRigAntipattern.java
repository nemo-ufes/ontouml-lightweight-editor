package br.ufes.inf.nemo.antipattern.relrig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class RelRigAntipattern extends Antipattern<RelRigOccurrence> {

	private HashMap<Relator, HashSet<Property>> rigidHash;



	private HashMap<Relator, HashSet<Property>> completeHash;

	public RelRigAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	@Deprecated
	private static final String oclQuery =	
			"Relator.allInstances()" +
			"->select(r: Relator | " +
			"	r.isAbstract=false " +
			"	and " +
			"	Mediation.allInstances()" +
			"	->select(m:Mediation | " +
			"		(m.sourceEnd().type=r and m.sourceEnd().lower>0 and (m.targetEnd().type.oclIsKindOf(RigidSortalClass) or m.targetEnd().type.oclIsKindOf(RigidMixinClass))) " +
			"		or " +
			"		(m.targetEnd().type=r and m.targetEnd().lower>0 and (m.sourceEnd().type.oclIsKindOf(RigidSortalClass) or m.sourceEnd().type.oclIsKindOf(RigidMixinClass))) " +
			"	)->size()>=1 " +
			")";
	
		 
		
	private static final AntipatternInfo info = new AntipatternInfo("Relator Mediating Rigid Types", 
			"RelRig", 
			"This anti-pattern occurs when a «relator» is connected through a «mediation» association to at least one " +
			"rigid object type, stereotyped as «kind», «quantity», «collective», «subkind» or «category».",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
	public ArrayList<RelRigOccurrence> identifyOCL() {
		ArrayList<Relator> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Relator.class);
		
		for (Relator relator : query_result) 
		{
			try {
				RelRigOccurrence occurrence = new RelRigOccurrence(relator, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<RelRigOccurrence> identify() {
		buildPropertyHash();
		
		for (Relator relator : rigidHash.keySet()) {
			try {
				occurrence.add(new RelRigOccurrence(relator,this,completeHash.get(relator),rigidHash.get(relator)));
			}
			catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return occurrence;
	}
	
	public void buildPropertyHash(){
		
		rigidHash = new HashMap<Relator,HashSet<Property>>();
		completeHash = new HashMap<Relator,HashSet<Property>>();
		
		//builds initial hash, with meronymics that are directly connected to the types
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			try{
				
				Property mediatedEnd, relatorEnd; 
				Classifier mediated = null, relator = null;
				
				mediatedEnd = OntoUMLParser.getMediatedEnd(m);
				relatorEnd = OntoUMLParser.getRelatorEnd(m);
				
				if(relatorEnd!=null)
					relator = (Classifier) relatorEnd.getType();
				if(mediatedEnd!=null)
					mediated = (Classifier) mediatedEnd.getType();
				
				if(mediatedEnd==null || relatorEnd == null || mediated==null || relator ==null)
					continue;
				
				boolean isRelator = relator instanceof Relator;
				
				if(!isRelator)
					continue;
				
				if (completeHash.containsKey(relator)){
					completeHash.get(relator).add(mediatedEnd);
				}
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(mediatedEnd);
					completeHash.put((Relator)relator, properties);
				}
				
				boolean isRigid = mediated instanceof RigidSortalClass || mediated instanceof RigidMixinClass;
				boolean isMediatedExistentiallyDependent = relatorEnd.isIsReadOnly();
				
				if(!isRigid || isMediatedExistentiallyDependent)
					continue;
				
				if (rigidHash.containsKey(relator)){
					rigidHash.get(relator).add(mediatedEnd);
				}
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(mediatedEnd);
					rigidHash.put((Relator)relator, properties);
				}
			}
			catch (Exception e){	}
			
		}
		
	}

}
