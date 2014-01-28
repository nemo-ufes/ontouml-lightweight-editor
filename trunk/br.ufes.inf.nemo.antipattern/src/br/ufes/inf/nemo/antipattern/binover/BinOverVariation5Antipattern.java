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

public class BinOverVariation5Antipattern extends Antipattern<BinOverVariation5Occurrence> {

	public BinOverVariation5Antipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	private static final String oclQuery =
			"Association.allInstances() " +
			"->select( a : Association | " +
			"	a.memberEnd->at(1).type.oclIsKindOf(MixinClass) " +
			"	and " +
			"	a.memberEnd->at(2).type.oclIsKindOf(MixinClass) " +
			")->select( a : Association |  " +
			"	a.memberEnd->at(1).type.oclAsType(Classifier).allParents()->intersection(a.memberEnd->at(2).type.oclAsType(Classifier).allParents())->size()>0 " +
			"	and " +
			"	a.memberEnd->at(1).type<>a.memberEnd->at(2).type" +
			"	and " +
			"	a.memberEnd->at(1).type.oclAsType(Classifier).allParents()->excludes(a.memberEnd->at(2).type.oclAsType(Classifier))" +
			"	and " +
			"	a.memberEnd->at(2).type.oclAsType(Classifier).allParents()->excludes(a.memberEnd->at(1).type.oclAsType(Classifier))" +
			"	and " +
			"	a.memberEnd->at(1).type.oclAsType(Classifier).allChildren()->forAll( child : Classifier | not child.oclIsKindOf(SortalClass)) " +
			"	and " +
			"	a.memberEnd->at(2).type.oclAsType(Classifier).allChildren()->forAll( child : Classifier | not child.oclIsKindOf(SortalClass)) " +
			"	and " +
			"	GeneralizationSet.allInstances()" +
			"	->select(gs:GeneralizationSet | " +
			"		gs.generalization" +
			"		->exists(g1,g2:Generalization | " +
			"			g1<>g2 " +
			"			and " +
			"			(" +
			"				g1.specific.allChildren()->includes(a.memberEnd.type->at(1).oclAsType(Classifier)) " +
			"				or " +
			"				g1.specific=a.memberEnd.type->at(1)" +
			"			) " +
			"			and " +
			"			(" +
			"				g2.specific.allChildren()->includes(a.memberEnd.type->at(2).oclAsType(Classifier)) " +
			"				or " +
			"				g2.specific=a.memberEnd.type->at(2)" +
			"			)" +
			"		)" +
			"	)->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)" +
			")";
	
	private static final AntipatternInfo info = new AntipatternInfo("Binary Relation With Overlapping Ends - Variation 3", 
			"BinOver-Var3", 
			"This anti-pattern occurs when...",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	public ArrayList<BinOverVariation5Occurrence> identifyOCL() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association assoc : query_result) 
		{
			try {
				super.occurrence.add(new BinOverVariation5Occurrence(assoc, this));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<BinOverVariation5Occurrence> identify() {
		
		Set<Association> allAssociations = parser.getAllInstances(Association.class);
		
		for (Association a : allAssociations) {
			
			Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
			Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
			
			if(OverlappingTypesIdentificator.isVariation5(source, target)) {
				try { 
					super.occurrence.add(new BinOverVariation5Occurrence(a, this));
				} catch (Exception e) { e.printStackTrace();}
			}
		}
		return this.getOccurrences();
	}


}
