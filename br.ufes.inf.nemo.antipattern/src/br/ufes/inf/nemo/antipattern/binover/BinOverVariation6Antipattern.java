package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import RefOntoUML.Association;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation6Antipattern extends Antipattern<BinOverVariation6Occurrence> {

	public BinOverVariation6Antipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	private static final String oclQuery =
			"Association.allInstances() "+
			"->select( a : Association | "+
			"	a.memberEnd->at(1).type.oclIsKindOf(MixinClass) "+
			"	and "+
			"	a.memberEnd->at(2).type.oclIsKindOf(MixinClass) "+
			")->select( a : Association |  "+
			"	a.memberEnd->at(1).type.oclAsType(MixinClass).allChildren()->intersection(a.memberEnd->at(2).type.oclAsType(MixinClass).allChildren())->size()>0 "+
			")";
	
	private static final AntipatternInfo info = new AntipatternInfo("Binary Relation With Overlapping Ends - Variation 4", 
			"BinOver-Var4", 
			"This anti-pattern occurs when...",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<BinOverVariation6Occurrence> identify() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association assoc : query_result) 
		{
			try {
				super.occurrence.add(new BinOverVariation6Occurrence(assoc, super.parser));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}

}
