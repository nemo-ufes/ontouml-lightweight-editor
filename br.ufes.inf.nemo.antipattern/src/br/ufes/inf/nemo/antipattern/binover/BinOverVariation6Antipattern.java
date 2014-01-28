package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesIdentificator;
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
	
	public ArrayList<BinOverVariation6Occurrence> identifyOCL() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association assoc : query_result) 
		{
			try {
				super.occurrence.add(new BinOverVariation6Occurrence(assoc, this));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<BinOverVariation6Occurrence> identify() {
		
		Set<Association> allAssociations = parser.getAllInstances(Association.class);
		
		for (Association a : allAssociations) {
			
			Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
			Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
			
			if(OverlappingTypesIdentificator.isVariation6(source, target)) {
				try { 
					super.occurrence.add(new BinOverVariation6Occurrence(a, this));
				} catch (Exception e) { e.printStackTrace();}
			}
		}
		return this.getOccurrences();
	}

}
