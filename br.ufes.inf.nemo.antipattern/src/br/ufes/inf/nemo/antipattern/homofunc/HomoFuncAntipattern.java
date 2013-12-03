package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class HomoFuncAntipattern extends Antipattern {

	public HomoFuncAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public HomoFuncAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	private static final String oclQuery =	
			
			"let wholeEnds : Bag(Property) = componentOf.allInstances().memberEnd" +
				"->select( p : Property |  p.aggregation<>AggregationKind::none and (p.type.oclIsTypeOf(Kind) or p.type.oclIsTypeOf(SubKind) or p.type.oclIsTypeOf(Role) or p.type.oclIsTypeOf(Phase) or p.type.oclIsTypeOf(Category) or p.type.oclIsTypeOf(RoleMixin) or p.type.oclIsTypeOf(Mixin))) " +
			"in wholeEnds->select ( c : Property | wholeEnds->select( x | " +
			"GeneralizationSet.allInstances()->select(gs : GeneralizationSet| gs.isCovering=true and gs.generalization.general->includes(c.type.oclAsType(Classifier))).generalization.specific->includes(x.type.oclAsType(Classifier)) or x.type=c.type " +
			"or c.type.oclAsType(Classifier).allParents()->includes(x.type.oclAsType(Classifier)))->size()=1).association";
	 
	
	private static final AntipatternInfo info = new AntipatternInfo("Homogeneous Functional Complex", 
			"HomoFunc", 
			"This anti-pattern is characterized by a functional complex whole connected to exactly one type of part, through a single «componentOf» relation. "+
			"The whole must be stereotyped as «kind» or it must be stereotyped as «subkind», «phase», «role» and directly or indirectly generalize another type"+
			"stereotyped as «whole».",
			HomoFuncAntipattern.oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<HomoFuncAntipattern> identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HomoFuncAntipattern> getOccurrences() {
		// TODO Auto-generated method stub
		return null;
	}

}
