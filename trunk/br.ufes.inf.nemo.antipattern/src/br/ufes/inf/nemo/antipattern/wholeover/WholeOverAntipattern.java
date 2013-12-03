package br.ufes.inf.nemo.antipattern.wholeover;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class WholeOverAntipattern extends Antipattern {

	public WholeOverAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public WholeOverAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}

	private static final String oclQuery =	
			"let wholeEnds : Bag(Property) = Meronymic.allInstances().memberEnd->select( p : Property |  p.aggregation<>AggregationKind::none ) "+
			"in "+
			"let chosen : Bag(Property) = wholeEnds->select ( c : Property | wholeEnds->select( x | "+ 
			"		x.type=c.type "+
			"		or c.type.oclAsType(Classifier).allParents()->includes(x.type.oclAsType(Classifier)) "+
			"		)->size()>1) "+
			"	in "+
			"	let generalizationSets : Set (GeneralizationSet) = GeneralizationSet.allInstances()->select(gs : GeneralizationSet | gs.generalization->exists(g | chosen.opposite.type->includes(g.general.oclAsType(Type)))) "+
			"		in	 "+
			"		chosen->collect ( x | Tuple {whole : Type = x.type, parts : Set (Property) = chosen->select( p:Property | p.type=x.type).opposite->asSet()})->asSet() "+
			"		->select( t | 	t.parts "+
			"		->exists(p1,p2:Property | "+ 
			"			p1<>p2  "+
			"			and  "+
			"			p1.type.oclAsType(Classifier).allParents()->including(p1.type.oclAsType(Classifier))->intersection(p2.type.oclAsType(Classifier).allParents()->including(p2.type.oclAsType(Classifier)))->size()>=1 "+
			"			and "+
			"			p1.type.oclAsType(Classifier).allParents()->including(p1.type.oclAsType(Classifier))->intersection(p2.type.oclAsType(Classifier).allParents()->including(p2.type.oclAsType(Classifier)))->exists( x : Classifier | x.oclIsTypeOf(Kind) or x.oclIsTypeOf(Quantity) or x.oclIsTypeOf(Collective)) "+
			"			and "+
			"			generalizationSets->select(gs:GeneralizationSet | gs.generalization "+
			"				->exists(g1,g2:Generalization | "+ 
			"					g1<>g2  "+
			"					and  "+
			"					( g1.specific.allChildren()->includes(p1.type.oclAsType(Classifier)) or g1.specific=p1.type.oclAsType(Classifier)) "+ 
			"					and  "+
			"					(g2.specific.allChildren()->includes(p2.type.oclAsType(Classifier)) or g2.specific=p2.type.oclAsType(Classifier)) "+
			"				) "+
			"			)->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false) "+
			"		) "+
			"	) ";
	 
	
	public static final AntipatternInfo info = new AntipatternInfo("Whole Composed of Overlapping Parts", 
			"WholeOver", 
			"This anti-pattern occurs when a whole is connected through meronymic associations to two or more object types, stereotyped as  «subkind», «role», «phase», whose extensions are possibly overlapping, i.e., the types inherit the same identity principle and are not made explicitly disjoint by"+ 
			"a generalization sets. The sum of the minimum cardinalities on the parts ends must be grater than 2.",
			WholeOverAntipattern.oclQuery); 
		
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
