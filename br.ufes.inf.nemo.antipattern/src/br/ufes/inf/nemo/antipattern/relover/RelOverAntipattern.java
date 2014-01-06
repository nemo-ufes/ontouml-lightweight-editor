package br.ufes.inf.nemo.antipattern.relover;

import java.util.ArrayList;
import java.util.Map;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.OverlappingTypesIdentificator;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelOverAntipattern extends Antipattern<RelOverOccurrence> {

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
	
	//@Override
	public ArrayList<RelOverOccurrence> identifyOCL() {
		Map<Classifier, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "relator", "mediatedEnds");
			
		for (Classifier relator : query_result.keySet()) 
		{
			try {
					RelOverOccurrence occurrence = new RelOverOccurrence(relator, query_result.get(relator), this);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<RelOverOccurrence> identify() {
		
		ArrayList<Class> allClasses = new ArrayList<Class>();
		allClasses.addAll(parser.getAllInstances(Class.class));
		int i = 1, total=allClasses.size();
		for (Class c : allClasses) {
			i++;
			if (isRelator(c)){
				
				System.out.println("("+i+" of "+total+") " +parser.getStringRepresentation(c)+": Analyzing...");
				ArrayList<Mediation> mediations = new ArrayList<Mediation>();
				ArrayList<Property> mediatedEnds = new ArrayList<Property>();
				
				try {
					parser.getAllMediations(c, mediations);
					for (Mediation m : mediations) {
						mediatedEnds.add(parser.getMediatedEnd(m));
					}
					
					boolean existsEndWithUnlimitedUpperCardinality = false;
					int upperCardinalituSum = 0;
					
					for (Property end : mediatedEnds) {
					
						if (end.getUpper()==-1)
							existsEndWithUnlimitedUpperCardinality=true;
						else
							upperCardinalituSum+=end.getUpper();
					}
					
				//	System.out.println("\tMediations size: "+mediations.size());
				//	System.out.println("\tCarinality Sum: "+upperCardinalituSum);
				//	System.out.println("\tExists Unlimited: "+existsEndWithUnlimitedUpperCardinality);
					
					if (mediations.size()>1 && (existsEndWithUnlimitedUpperCardinality || upperCardinalituSum>2)){
					
						boolean foundOccurrence = false;
						
						for (Mediation m1 : mediations) {
							for (Mediation m2 : mediations) {
								if(!m1.equals(m2)){
									//System.out.println("M1End: "+parser.getStringRepresentation(parser.getMediated(m1)));
									//System.out.println("M2End: "+parser.getStringRepresentation(parser.getMediated(m2)));
									if(OverlappingTypesIdentificator.isVariation1(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 1!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 1.\n"+e.getMessage());}
										
									}
									else if(OverlappingTypesIdentificator.isVariation2(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 2!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 2.\n"+e.getMessage());}
										
									}
									else if(OverlappingTypesIdentificator.isVariation3(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 3!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 3.\n"+e.getMessage());}
										
									}
									else if(OverlappingTypesIdentificator.isVariation4(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 4!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 4.\n"+e.getMessage());}
										
									}
									else if(OverlappingTypesIdentificator.isVariation5(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 5!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 5.\n"+e.getMessage());}
										
									}
									else if(OverlappingTypesIdentificator.isVariation6(parser.getMediated(m1),parser.getMediated(m2))) {
										try {
											super.occurrence.add(new RelOverOccurrence(c, mediatedEnds, this));
											foundOccurrence = true;
										//	System.out.println("Found Variation 6!");
										} catch (Exception e) { System.out.println("RelOver: Can't create variation 6.\n"+e.getMessage());}
										
									}
								}
								
								if (foundOccurrence) {
									//System.out.println("("+i+" of "+total+")" +parser.getStringRepresentation(c)+": 1 - BREAK IT");
									break;
								}
							}
							
							if (foundOccurrence) {
							//	System.out.println("("+i+" of "+total+")" +parser.getStringRepresentation(c)+": 2 - BREAK IT");
								break;
							}
						}
					}
				} catch (Exception e) {	
					System.out.println("ERROR!! Couldn't get mediations...");
					System.out.println(e.getMessage());	}
			}
		}
		
		return super.getOccurrences();
		
	}
	
	private boolean isRelator (Class c){
		if (c instanceof Relator)
			return true;
		
		for (Classifier parent : c.allParents()) {
			if (parent instanceof Relator)
				return true;
		}
		
		return false;
	}

}
