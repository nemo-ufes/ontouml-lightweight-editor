package br.ufes.inf.nemo.antipattern.relcomp;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.SimpleTuple;

public class RelCompAntipattern extends Antipattern<RelCompOccurrence> {

	public RelCompAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public RelCompAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
//	
//	private static final String oclQuery_unidented =	
//			"Association.allInstances()->product(Association.allInstances())->collect( x | "+
//			"Tuple {	a1:Association = x.first, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2:Association = x.second}) "+
//			"->select( x |  "+
//			"	x.a1<>x.a2 " +
//			"	and  "+
//			"	(" +
//			"		((x.a1.memberEnd->at(2).upper=-1 or x.a1.memberEnd->at(2).upper>1) and x.a1Target.allChildren()->including(x.a1Target)->includesAll(x.a2.endType.oclAsType(Classifier))) "+
//			"		or "+
//			"		((x.a1.memberEnd->at(1).upper=-1 or x.a1.memberEnd->at(1).upper>1) and x.a1Source.allChildren()->including(x.a1Source)->includesAll(x.a2.endType.oclAsType(Classifier))) " +
//			"	)"+
//			")->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})"; 
//	
	@Deprecated
	private static final String oclQuery =	
			"Association.allInstances()->product(Association.allInstances())->collect( x | "+
			"Tuple {	a1:Association = x.first, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2:Association = x.second}) "+
			"->select( x |  "+
			"	x.a1<>x.a2 " +
			"	and  "+
			"	(" +
			"		(" +
			"			(" +
			"				x.a1.memberEnd->at(2).upper=-1 " +
			"				or " +
			"				x.a1.memberEnd->at(2).upper>1" +
			"			) " +
			"			and " +
			"			x.a1Target.allChildren()->including(x.a1Target)->includesAll(x.a2.endType.oclAsType(Classifier))" +
			"		) "+
			"		or "+
			"		((x.a1.memberEnd->at(1).upper=-1 or x.a1.memberEnd->at(1).upper>1) and x.a1Source.allChildren()->including(x.a1Source)->includesAll(x.a2.endType.oclAsType(Classifier))) " +
			"	)"+
			")->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})"; 
		
	
	private static final AntipatternInfo info = new AntipatternInfo("Relation Composition", 
			"RelComp", 
			"Consider two associations, no matter their stereotypes:\n"+
			"\t- A, that connects ASource and ATarget; and\n"+
			"\t- B, that connects BSource and BTarget\n"+
			"For this anti-pattern to occur, one of the possible statements needs to be true: \n"+
			"\t- BSource equals or is a subtype of ATarget and BTarget equals or is a subtype of ATarget.\n"+
			"\t- BSource equals or is a subtype of ASource and BTarget equals or is a subtype of ASource.", 
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
	public ArrayList<RelCompOccurrence> identifyOCL() {
		ArrayList<SimpleTuple<Association, Association>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuerySimpleTuple(parser, oclQuery, Association.class, Association.class, "a1", "a2");
		
		for (SimpleTuple<Association,Association> tuple : query_result) 
		{
			try {
					RelCompOccurrence occurrence = new RelCompOccurrence(tuple.getFirst(), tuple.getSecond(), this);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	
	
	
	@Override
	public ArrayList<RelCompOccurrence> identify() {
		ArrayList<Association> allAssociations = new ArrayList<Association>();
		allAssociations.addAll(parser.getAllInstances(Association.class));
		
//		int current = 1, total = allAssociations.size()*allAssociations.size();
		
		for (Association a1 : allAssociations) {
			for (Association a2 : allAssociations) {
//				current++;
//				System.out.println("("+current+" of "+total+") "+parser.getStringRepresentation(a1)+", "+parser.getStringRepresentation(a2)+": Analyzing...");
				Classifier 	sourceA1 = (Classifier) a1.getMemberEnd().get(0).getType(), 
							targetA1 = (Classifier) a1.getMemberEnd().get(1).getType(), 
							sourceA2 = (Classifier) a2.getMemberEnd().get(0).getType(), 
							targetA2 = (Classifier) a2.getMemberEnd().get(1).getType();
				if (!a1.equals(a2)){
					
					boolean a2DependsOnA1 = 
							(a1.getMemberEnd().get(1).getUpper()==-1 || a1.getMemberEnd().get(1).getUpper()>1)
							&&
							(targetA1.equals(sourceA2) || sourceA2.allParents().contains(targetA1))
							&&
							(targetA1.equals(targetA2) || targetA2.allParents().contains(targetA1));
					
					if(a2DependsOnA1){
							try {
								this.occurrence.add(new RelCompOccurrence(a1, a2, this));
							} catch (Exception e) {
//								System.out.println(info.acronym+"[java]: Provided information does not characterize an occurrence of the anti-pattern!");
//								System.out.println(e.getMessage());
							}
					}
					else { 
						
						boolean a1DependsOnA2 = 
								(a1.getMemberEnd().get(0).getUpper()==-1 || a1.getMemberEnd().get(0).getUpper()>1)
								&&
								(sourceA1.equals(sourceA2) || sourceA2.allParents().contains(sourceA1))
								&&
								(sourceA1.equals(targetA2) || targetA2.allParents().contains(sourceA1));
						
						if(a1DependsOnA2){
						try {
							this.occurrence.add(new RelCompOccurrence(a2, a1, this));
						} catch (Exception e) {
//							System.out.println(info.acronym+"[java]: Provided information does not characterize an occurrence of the anti-pattern!");
//							System.out.println(e.getMessage());
						}
					}
				}
				} 
			}
		}
		return this.getOccurrences();
	}
	
	

}
