package br.ufes.inf.nemo.antipattern.undefphase;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class UndefPhaseAntipattern extends Antipattern {

	public UndefPhaseAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	public UndefPhaseAntipattern(Package pack) throws NullPointerException {
		super(pack);
		// TODO Auto-generated constructor stub
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
	
	@Override
	public <T extends Antipattern> ArrayList<T> identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Antipattern> ArrayList<T> getOccurrences() {
		// TODO Auto-generated method stub
		return null;
	}

}
