package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesIdentificator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation4Antipattern extends
		Antipattern<BinOverVariation4Occurrence> {

	public BinOverVariation4Antipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public BinOverVariation4Antipattern(Package pack) throws NullPointerException {
		this( new OntoUMLParser(pack));
	}

	private static final String oclQuery = 
			"Association.allInstances()" +
			"-> select(x:Association | " +
			"	x.memberEnd.type->at(1)<>x.memberEnd.type->at(2) " +
			"	and " +
			"	( " +
			"		(" +
			"			x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) " +
			"			or " +
			"			x.memberEnd.type->at(2).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(1).oclAsType(Classifier))" +
			"		) " +
			"		or " +
			"		(" +
			"			x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.memberEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 " +
			"			and " +
			"			GeneralizationSet.allInstances()" +
			"			->select(gs:GeneralizationSet | " +
			"				gs.generalization" +
			"				->exists(g1,g2:Generalization | " +
			"					g1<>g2 " +
			"					and " +
			"					(" +
			"						g1.specific.allChildren()->includes(x.memberEnd.type->at(1).oclAsType(Classifier)) " +
			"						or " +
			"						g1.specific=x.memberEnd.type->at(1)" +
			"					) " +
			"					and " +
			"					(" +
			"						g2.specific.allChildren()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) " +
			"						or " +
			"						g2.specific=x.memberEnd.type->at(2)" +
			"					)" +
			"				)" +
			"			)->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)" +
			"		)" +
			"	)" +
			")";
	
	
	private static final AntipatternInfo info = new AntipatternInfo("Binary Relation With Overlapping Ends - Variation 2", 
			"BinOver-Var2", 
			"This anti-pattern occurs when...",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	
	public ArrayList<BinOverVariation4Occurrence> identifyOCL() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association assoc : query_result) 
		{
			try {
				super.occurrence.add(new BinOverVariation4Occurrence(assoc, this));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<BinOverVariation4Occurrence> identify() {
		
		Set<Association> allAssociations = parser.getAllInstances(Association.class);
		
		for (Association a : allAssociations) {
			
			Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
			Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
			
			if(OverlappingTypesIdentificator.isVariation4(source, target)) {
				try { 
					super.occurrence.add(new BinOverVariation4Occurrence(a, this));
				} catch (Exception e) { e.printStackTrace();}
			}
		}
		return this.getOccurrences();
	}

}
