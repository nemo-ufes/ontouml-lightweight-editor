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
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingAntipattern;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesIdentificator;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class WholeOverAntipattern extends OverlappingAntipattern<WholeOverOccurrence> {

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
	
	//@Override
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
				WholeOverOccurrence wholeOver = new WholeOverOccurrence(whole, hash.get(whole), this);
				getOccurrences().add(wholeOver);
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
	
//	private HashMap<Classifier,HashSet<Property>> buildWholeAndPropertyPartsHash(){
//		HashMap<Classifier,HashSet<Property>> hash = new HashMap<Classifier,HashSet<Property>>();
//		
//		//builds initial hash, with meronymics that are directly connected to the types
//		for (Meronymic m : parser.getAllInstances(Meronymic.class)) {
//			
//			try{
//				Property wholeEnd = getMainType(m),
//						 partEnd = getProperty(m);
//				Classifier whole = (Classifier) wholeEnd.getType();
//				
//				if (hash.keySet().contains(whole))
//					hash.get(whole).add(partEnd);
//				else{
//					HashSet<Property> partEnds = new HashSet<Property>();
//					partEnds.add(partEnd);
//					hash.put((Classifier) wholeEnd.getType(), partEnds);
//				}
//			}
//			catch(Exception e){ }
//		}
//		
//		//adds supertypes' parts
//		for (Classifier whole : hash.keySet()) 
//			for (Classifier parent : whole.allParents()) 
//				if(hash.keySet().contains(parent))
//					hash.get(whole).addAll(hash.get(parent));
//			
//		return hash;
//	}
	
	
	public ArrayList<WholeOverOccurrence> identifyOLD(){
		HashMap<Classifier,ArrayList<Property>> hash = new HashMap<Classifier,ArrayList<Property>>();
		
		for (Meronymic m : parser.getAllInstances(Meronymic.class)) {
			
			try{
				Property  partEnd = getProperty(m);
				Classifier whole = getMainType(m);
				
				if (hash.keySet().contains(whole))
					hash.get(whole).add(partEnd);
				else{
					ArrayList<Property> partEnds = new ArrayList<Property>();
					partEnds.add(partEnd);
					hash.put(whole, partEnds);
				}
			}
			catch(Exception e){ }
		}
				
		//only checks whole that have at least one type of part directly connected to it
		for (Classifier whole : hash.keySet()) {
			
			ArrayList<Property> typePartEnds = hash.get(whole);
			ArrayList<Property> superPartEnds = new ArrayList<Property>();
			ArrayList<Property> partEnds = new ArrayList<Property>();
			
			//gets parents partEnds
			for (Classifier parent : whole.allParents()) {
				if(hash.keySet().contains(parent))
					superPartEnds.addAll(hash.get(parent));
			}
			
			//merges the parts exclusives to the type with its supertypes parts
			partEnds.addAll(typePartEnds);
			partEnds.addAll(superPartEnds);
			
			//to characterize the occurrence the whole must have at least two parts directly or indirectly connected to it
			if(partEnds.size()>=2) { 
				
				//calculates the upper cardinality sum and verifies if there is any unlimited
				boolean existsEndWithUnlimitedUpperCardinality = false;
				int upperCardinalituSum = 0;
				
				for (Property end : partEnds) {
				
					if (end.getUpper()==-1)
						existsEndWithUnlimitedUpperCardinality=true;
					else
						upperCardinalituSum+=end.getUpper();
				}
				
				if (existsEndWithUnlimitedUpperCardinality || upperCardinalituSum>2){
					
					//goes through every combination of part properties
					Combination comb = new Combination(partEnds, 2);
					boolean overlappingPartsFound = false;
					while(comb.hasNext() && !overlappingPartsFound){
						ArrayList<Property> combination = comb.next();
						Classifier part1 = (Classifier) combination.get(0).getType();
						Classifier part2 = (Classifier) combination.get(1).getType();
						
						if(OverlappingTypesIdentificator.isVariation1(part1, part2)
								|| OverlappingTypesIdentificator.isVariation2(part1, part2)
								|| OverlappingTypesIdentificator.isVariation3(part1, part2)
								|| OverlappingTypesIdentificator.isVariation4(part1, part2)
								|| OverlappingTypesIdentificator.isVariation5(part1, part2)
								|| OverlappingTypesIdentificator.isVariation6(part1, part2)
								) {
							try {
								super.occurrence.add(new WholeOverOccurrence(whole, new HashSet<>(partEnds), this));
								overlappingPartsFound = true;
							} catch (Exception e) { System.out.println("WholeOver: Can't create variation 1.\n"+e.getMessage());}
							
						}
					}
				}
			}
		}
		
		return super.getOccurrences();
		
	}
	
	
}
