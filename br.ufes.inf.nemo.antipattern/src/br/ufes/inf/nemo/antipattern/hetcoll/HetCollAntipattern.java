package br.ufes.inf.nemo.antipattern.hetcoll;
import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class HetCollAntipattern extends Antipattern {

	public HetCollAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public HetCollAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	private static final String oclQuery =	
			"let wholeEnds : Bag(Property) = memberOf.allInstances().memberEnd->select( p : Property |  p.aggregation<>AggregationKind::none and (p.type.oclIsTypeOf(Collective) or p.type.oclIsTypeOf(SubKind) or p.type.oclIsTypeOf(Role) or p.type.oclIsTypeOf(Phase) or p.type.oclIsTypeOf(Category) or p.type.oclIsTypeOf(RoleMixin) or p.type.oclIsTypeOf(Mixin)))" +
			"in " +
				"let chosen : Bag(Property) = wholeEnds->select ( c : Property | wholeEnds->select( x | " +
					"GeneralizationSet.allInstances()->select(gs : GeneralizationSet| gs.isCovering=true and gs.generalization.general->includes(c.type.oclAsType(Classifier))).generalization.specific->includes(x.type.oclAsType(Classifier)) " +
					"or x.type=c.type " +
					"or c.type.oclAsType(Classifier).allParents()->includes(x.type.oclAsType(Classifier))" +
					")->size()>1)" +
				"in " +
					"chosen->collect ( x | Tuple {collective : Type = x.type, memberOfs : Set (Association) = chosen->select( p:Property | p.type=x.type).association->asSet()})->asSet()";
	 
	
	public static final AntipatternInfo info = new AntipatternInfo("Heterogeneous Collective", 
			"HetColl", 
			"This anti-pattern is characterized by...",
			HetCollAntipattern.oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<HetCollAntipattern> identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HetCollAntipattern> getOccurrences() {
		// TODO Auto-generated method stub
		return null;
	}

}
