package br.ufes.inf.nemo.antipattern.relrig;

import java.util.ArrayList;

import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelRigAntipattern extends Antipattern<RelRigOccurrence> {

	public RelRigAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

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
	
	@Override
	public ArrayList<RelRigOccurrence> identify() {
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

}
