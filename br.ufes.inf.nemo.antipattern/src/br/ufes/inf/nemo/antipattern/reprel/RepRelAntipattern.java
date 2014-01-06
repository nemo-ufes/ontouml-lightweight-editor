package br.ufes.inf.nemo.antipattern.reprel;

import java.util.ArrayList;

import RefOntoUML.Package;
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
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<RepRelOccurrence> identify() {
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

}
