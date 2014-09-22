package br.ufes.inf.nemo.antipattern.relover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingAntipattern;

public class RelOverAntipattern extends OverlappingAntipattern<RelOverOccurrence> {

	public RelOverAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public RelOverAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

	//TODO: FIX THE OCL CONSTRAINT USING LET AND PROPERTY TO CONSIDER INVERTED MEDIATIONS. Also fix the query to get Classifier.allInstances() instead of Relator.allInstances()
	private static final String oclQuery =	

		"let relatorEnds : Bag(Property) =  "+
		"	Mediation.allInstances().memberEnd "+
		"	->select(p: Property |  "+
		"		p.type.oclIsTypeOf(Relator) "+
		"		or "+
		"		p.type.oclAsType(Classifier).allParents()	" +
		"		->exists( parent : Classifier | parent.oclIsTypeOf(Relator)) "+
		"	) "+
		"in "+
		"	relatorEnds "+
		"	->collect( p: Property | Tuple {  "+
		"		relator : Classifier = p.type.oclAsType(Classifier), "+ 
		"		mediatedEnds : Set(Property) = relatorEnds "+
		"		->select( p2: Property | "+ 
		"			p2.type=p.type "+
		"			or "+
		"			p.type.oclAsType(Classifier).allParents()->includes(p2.type.oclAsType(Classifier)) "+
		"		).opposite->asSet()} "+ 
		"	)->asSet() "+
		"	->select ( t |  "+
		"		t.relator.isAbstract=false "+
		"		and "+
		"		( "+
		"			t.mediatedEnds->exists( p: Property | p.upper=-1) "+
		"			or "+
		"			t.mediatedEnds.upper->sum()>2 "+
		"		) "+
		"		and  "+
		"		t.mediatedEnds.type.oclAsType(Classifier) "+
		"		->exists(t1,t2:Classifier | "+ 
		"			t1<>t2  "+
		"			and  "+
		"			t1.allParents()->excludes(t2) "+ 
		"			and  "+
		"			t2.allParents()->excludes(t1) "+ 
		"			and  "+
		"			(  "+
		"				t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 "+ 
		"				and  "+
		"				GeneralizationSet.allInstances() "+
		"				->select(gs:GeneralizationSet | gs.generalization "+
		"					->exists(g1,g2:Generalization | "+ 
		"						g1<>g2  "+
		"						and  "+
		"						( "+
		"							g1.specific.allChildren()->includes(t1) "+ 
		"							or  "+
		"							g1.specific=t1 "+
		"						)  "+
		"						and  "+
		"						( "+
		"							g2.specific.allChildren()->includes(t2) "+ 
		"							or  "+
		"							g2.specific=t2 "+
		"						) "+
		"					) "+
		"				)->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false) "+
		"			) "+
		"		) "+
		"	)";
	
	
	
	private static final AntipatternInfo info = new AntipatternInfo("Relator Mediating Overlapping Types", 
			"RelOver", 
			"This anti-pattern occurs when a «relator» is connected through «mediation» associations to two or more object types, stereotype as  «subkind», «role», «phase», whose " +
			"extensions are possibly overlapping, i.e., the types inherit the same identity principle and are not made explicitly disjoint by a generalization sets. ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
	public ArrayList<RelOverOccurrence> identifyOCL() {
		Map<Classifier, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "relator", "mediatedEnds");
			
		for (Classifier relator : query_result.keySet()) 
		{
			try {
					RelOverOccurrence occurrence = new RelOverOccurrence(relator, new HashSet<>(query_result.get(relator)), this);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	
	
	
	@Override
	public ArrayList<RelOverOccurrence> identify(){
		
		HashMap<Classifier,HashSet<Property>> hash = super.buildMainTypeAndPropertiesHash(Mediation.class);
		
		for (Classifier relator : hash.keySet()) {
			try {
				RelOverOccurrence relOver = new RelOverOccurrence(relator, hash.get(relator), this);
				getOccurrences().add(relOver);
			}
			catch (Exception e){}
		}
		
		return getOccurrences();
	}
	
	private boolean isRelator (Type c){
		if (c instanceof Relator)
			return true;
		
		for (Classifier parent : ((Classifier) c).allParents()) {
			if (parent instanceof Relator)
				return true;
		}
		
		return false;
	}

	@Override
	//returns mediatedEnd
	public Property getProperty(Association a) {
		if( isRelator(a.getMemberEnd().get(1).getType()) && !isRelator(a.getMemberEnd().get(0).getType()) )
			return a.getMemberEnd().get(0);
		else
			return a.getMemberEnd().get(1);
	}

	@Override
	//returns relator
	public Classifier getMainType(Association a) {
		return (Classifier) getProperty(a).getOpposite().getType();
	}

}
