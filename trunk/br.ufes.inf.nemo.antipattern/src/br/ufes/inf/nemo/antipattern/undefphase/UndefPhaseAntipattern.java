package br.ufes.inf.nemo.antipattern.undefphase;

import java.util.ArrayList;

import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class UndefPhaseAntipattern extends Antipattern<UndefPhaseOccurrence> {

	public UndefPhaseAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public UndefPhaseAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

private static final String oclQuery =	
			
			"let dataTypeAssociations : Set (Association) = Association.allInstances()->select( a : Association | a.endType->exists( t : Type | t.oclIsTypeOf(DataType))) "+ 
			"in  "+
			"	GeneralizationSet.allInstances() "+
			"		->select( gs : GeneralizationSet | "+ 
			"			gs.generalization.specific->forAll( c : Classifier | c.oclIsTypeOf(Phase) ) "+
			"			and "+
			"			gs.generalization.general->asOrderedSet()->at(1).allParents()->including(gs.generalization.general->asOrderedSet()->at(1)).attribute->size()=0 "+
			"			and "+
			"			dataTypeAssociations->select(a : Association | a.endType->intersection(gs.generalization.general->asOrderedSet()->at(1).allParents()->including(gs.generalization.general->asOrderedSet()->at(1)).oclAsType(Type) )->size()>0 )->size()=0 "+
			"			and "+
			"			gs.generalization.specific "+
			"				->select( phase : Classifier | not Characterization.allInstances()->exists( charac : Characterization | charac.endType->includes(phase)))->size()>=2 "+
			"		)";
	
	private static final AntipatternInfo info = new AntipatternInfo("Undefined Phase Partition", 
			"UndefPhase", 
			"An occurrence of this anti-pattern is characterized by a partition (generalization set defined as disjoint and complete} of phases in which the supertype of the partition does not " +
			"have or inherit properties (attributes or associations) whose types are stereotyped as «datatype» (representing qualities) or «mode».  ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<UndefPhaseOccurrence> identify() {
		ArrayList<GeneralizationSet> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, GeneralizationSet.class);
		
		for (GeneralizationSet gs : query_result) 
		{
			try {
				UndefPhaseOccurrence occurrence = new UndefPhaseOccurrence(gs, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

}
