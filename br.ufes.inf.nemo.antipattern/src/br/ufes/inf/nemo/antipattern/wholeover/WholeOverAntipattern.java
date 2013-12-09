package br.ufes.inf.nemo.antipattern.wholeover;

import java.util.ArrayList;
import java.util.Map;

import RefOntoUML.AggregationKind;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.OverlappingTypesIdentificator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class WholeOverAntipattern extends Antipattern<WholeOverOccurrence> {

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
				super.occurrence.add(new WholeOverOccurrence(relator, query_result.get(relator), this.parser));
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<WholeOverOccurrence> identify() {
		
		ArrayList<Class> allClasses = new ArrayList<Class>();
		allClasses.addAll(parser.getAllInstances(Class.class));
		int i = 1, total=allClasses.size();
		for (Class c : allClasses) {
			i++;
			
			System.out.println("("+i+" of "+total+") " +parser.getStringRepresentation(c)+": Analyzing...");
			ArrayList<Meronymic> meronymics = new ArrayList<Meronymic>();
			ArrayList<Property> partEnds = new ArrayList<Property>();
				
			parser.getAllMeronymics(c, meronymics);
			for (Meronymic m : meronymics)
				partEnds.add(getPartEnd(m));
			
			boolean existsEndWithUnlimitedUpperCardinality = false;
			int upperCardinalituSum = 0;
			
			for (Property end : partEnds) {
			
				if (end.getUpper()==-1)
					existsEndWithUnlimitedUpperCardinality=true;
				else
					upperCardinalituSum+=end.getUpper();
			}
				
//			System.out.println("\nMemberEnds size: "+meronymics.size());
//			System.out.println("\tCarinality Sum: "+upperCardinalituSum);
//			System.out.println("\tExists Unlimited: "+existsEndWithUnlimitedUpperCardinality);
				
			if (meronymics.size()>1 && (existsEndWithUnlimitedUpperCardinality || upperCardinalituSum>2)){
				
				boolean foundOccurrence = false;
					
				for (Property partEnd1 : partEnds) {
					for (Property partEnd2 : partEnds) {
						if(!partEnd1.equals(partEnd2)){
							System.out.println("M1End: "+parser.getStringRepresentation(partEnd1.getType()));
							System.out.println("M2End: "+parser.getStringRepresentation(partEnd2.getType()));
							if(OverlappingTypesIdentificator.isVariation1((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 1!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 1.\n"+e.getMessage());}
								
							}
							else if(OverlappingTypesIdentificator.isVariation2((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 2!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 2.\n"+e.getMessage());}
								
							}
							else if(OverlappingTypesIdentificator.isVariation3((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 3!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 3.\n"+e.getMessage());}
								
							}
							else if(OverlappingTypesIdentificator.isVariation4((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 4!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 4.\n"+e.getMessage());}
								
							}
							else if(OverlappingTypesIdentificator.isVariation5((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 5!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 5.\n"+e.getMessage());}
								
							}
							else if(OverlappingTypesIdentificator.isVariation6((Classifier)partEnd1.getType(), (Classifier)partEnd2.getType())) {
								try {
									super.occurrence.add(new WholeOverOccurrence(c, partEnds, this.parser));
									foundOccurrence = true;
//									System.out.println("Found Variation 6!");
								} catch (Exception e) { System.out.println("WholeOver: Can't create variation 6.\n"+e.getMessage());}
								
							}
						}
						
						if (foundOccurrence) {
//							System.out.println("("+i+" of "+total+")" +parser.getStringRepresentation(c)+": 1 - BREAK IT");
							break;
						}
					}
						
					if (foundOccurrence) {
//						System.out.println("("+i+" of "+total+")" +parser.getStringRepresentation(c)+": 2 - BREAK IT");
						break;
					}
				}
			}
	
	}
		
		return super.getOccurrences();
		
	}	
	
	private Property getPartEnd(Meronymic m){
		if(m.getMemberEnd().get(1).getAggregation()==AggregationKind.NONE)
			return m.getMemberEnd().get(1);
		else
			return m.getMemberEnd().get(0);
	}
}
