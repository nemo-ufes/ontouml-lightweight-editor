package br.ufes.inf.nemo.antipattern.depphase;

import java.util.ArrayList;
import java.util.Map;

import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class DepPhaseAntipattern extends Antipattern<DepPhaseOccurrence> {
	
	public DepPhaseAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public DepPhaseAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

	private static final String oclQuery = 
			"let mediatedPhase : Bag (Property) = Mediation.allInstances().memberEnd->select( p : Property | p.type.oclIsTypeOf(Phase)) "+
			"in "+
			"	mediatedPhase->collect ( p : Property | Tuple { "+ 
			"		phase : Phase = p.type.oclAsType(Phase),  "+
			"		relatorEnds : Set (Property) = mediatedPhase->select( p2 : Property | p2.type = p.type).opposite->asSet() }" +
			"	)->asSet() ";
	
	private static final AntipatternInfo info = new AntipatternInfo("Relationally Dependent Phase", 
			"DepPhase", 
			"This anti-pattern occurs when a «phase» type connected to one or more «mediation» associations. ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<DepPhaseOccurrence> identify() {
		
		Map<Phase, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(this.parser, DepPhaseAntipattern.oclQuery, Phase.class, Property.class, "phase", "relatorEnds");
			
		for (Phase phase : query_result.keySet()) 
		{
			try {

				DepPhaseOccurrence depPhase = new DepPhaseOccurrence(phase, query_result.get(phase), this);
				super.occurrence.add(depPhase);
				
			} catch (Exception e) {
//				System.out.println("DepPhase: Provided information does not characterize an occurrence of the anti-pattern!");
			}
		}
		
		return this.getOccurrences();
	}	

}
