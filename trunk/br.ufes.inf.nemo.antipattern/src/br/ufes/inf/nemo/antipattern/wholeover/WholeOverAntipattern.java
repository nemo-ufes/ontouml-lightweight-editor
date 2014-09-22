package br.ufes.inf.nemo.antipattern.wholeover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingAntipattern;

public class WholeOverAntipattern extends OverlappingAntipattern<WholeOverOccurrence> {

	public WholeOverAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public WholeOverAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	@Deprecated
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

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
	public ArrayList<WholeOverOccurrence> identifyOCL() {
		Map<Classifier, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "whole", "parts");
			
		for (Classifier relator : query_result.keySet()) 
		{
			try {
				super.occurrence.add(new WholeOverOccurrence(relator, new HashSet<>(query_result.get(relator)), this));
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<WholeOverOccurrence> identify(){
		
		HashMap<Classifier,HashSet<Property>> hash = super.buildMainTypeAndPropertiesHash(Meronymic.class);
		for (Classifier whole : hash.keySet()) {
			try {
				
				getOccurrences().add(new WholeOverOccurrence(whole, hash.get(whole), this));
			}
			catch (Exception e){}
		}
		
		return getOccurrences();
	}
	
	//returns the property (association end) connected to the part end (Required for supertype method buildMainTypeAndPropertiesHash to work)
	@Override
	public Property getProperty(Association m){
		if(m.getMemberEnd().get(1).getAggregation()!=AggregationKind.NONE && m.getMemberEnd().get(0).getAggregation()==AggregationKind.NONE)
			return m.getMemberEnd().get(0);
		else
			return m.getMemberEnd().get(1);
	}
	
	//returns the whole of the meronymic (Required for supertype method buildMainTypeAndPropertiesHash to work)
	@Override
	public Classifier getMainType(Association m){
		return (Classifier) getProperty(m).getOpposite().getType();
	}	
	
	
}
