package br.ufes.inf.nemo.antipattern.relspec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.DirectedBinaryAssociation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.SimpleTuple;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelSpecAntipattern extends Antipattern<RelSpecOccurrence> {

	public RelSpecAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public RelSpecAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final String oclQuery =	
			
			"Association.allInstances()" +
			"->product(Association.allInstances())" +
			"->collect( x | Tuple {" +
			"	a1:Association = x.first, " +
			"	a2:Association = x.second, " +
			"	a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), " +
			"	a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), " +
			"	a2Source:Classifier = x.second.memberEnd.type->at(1).oclAsType(Classifier), " +
			"	a2Target:Classifier = x.second.memberEnd.type->at(2).oclAsType(Classifier)}" +
			")->select( x | " +
			"	x.a1<>x.a2 " +
			"	and " +
			"	( " +
			"		( x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Source) and x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Target)) " +
			"		or " +
			"		( x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Target) and x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Source))" +
			"	)" +
			")->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})"; 
	
	
		
	private static final AntipatternInfo info = new AntipatternInfo("Relation Specialization", 
			"RelSpec", 
			"Consider two distinct associations, regardless of their stereotypes:\n"+
			"\t- A, that connects ASource and ATarget; and\n"+
			"\t- B, that connects BSource and BTarget\n"+
			"For this anti-pattern to occur, one of the possible statements must hold:\n"+
			"\t- ASource is equal or a subtype of BSource and ATarget is equal or a subtype of BTarget\n"+
			"\t- ASource is equal or a subtype of BTarget and ATarget is equal or a subtype of BSource", 
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public ArrayList<RelSpecOccurrence> identifyOCL() {
		ArrayList<SimpleTuple<Association, Association>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuerySimpleTuple(parser, oclQuery, Association.class, Association.class, "a1", "a2");
		
		for (SimpleTuple<Association,Association> tuple : query_result) 
		{
			try {
					RelSpecOccurrence occurrence = new RelSpecOccurrence(tuple.getFirst(), tuple.getSecond(), this);
					super.occurrence.add(occurrence);
				
			} catch (Exception e) {
				System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<RelSpecOccurrence> identify() {
		
		identifyByStereotype(parser.getAllInstances(Characterization.class));
		identifyByStereotype(parser.getAllInstances(Mediation.class));
		identifyByStereotype(parser.getAllInstances(MaterialAssociation.class));
		identifyByStereotype(parser.getAllInstances(FormalAssociation.class));
		identifyByStereotype(parser.getAllInstances(componentOf.class));
		identifyByStereotype(parser.getAllInstances(memberOf.class));
		identifyByStereotype(parser.getAllInstances(subQuantityOf.class));
		identifyByStereotype(parser.getAllInstances(subCollectionOf.class));
		
		Set<Association> remainderAssociations = new HashSet<Association>();
		for (Association a : parser.getAllInstances(Association.class)) {
			if (!(a instanceof DirectedBinaryAssociation) || !(a instanceof MaterialAssociation) || !(a instanceof FormalAssociation)) 
					 remainderAssociations.add(a);
		}
		
		identifyByStereotype(remainderAssociations);
		return this.getOccurrences();
	}
	
	@SuppressWarnings("unused")
	private <T extends Association> void identifyByStereotype( Set<T> associationList){
		int current = 1, total = associationList.size()*associationList.size(); 
		for (Association a1 : associationList) {
			
			if(a1.getMemberEnd()==null || a1.getMemberEnd().size()!=2)
				continue;
			
			Classifier 	sourceA1 = (Classifier) a1.getMemberEnd().get(0).getType(), 
						targetA1 = (Classifier) a1.getMemberEnd().get(1).getType();
			
			for (Association a2 : associationList) {
				current++;
				//System.out.println("("+current+" of "+total+") "+parser.getStringRepresentation(a1)+", "+parser.getStringRepresentation(a2)+": Analyzing...");
				
				if(a2.getMemberEnd()==null || a2.getMemberEnd().size()!=2)
					continue;
				
				Classifier 	sourceA2 = (Classifier) a2.getMemberEnd().get(0).getType(), 
							targetA2 = (Classifier) a2.getMemberEnd().get(1).getType();
				
				if (!a1.equals(a2)){
					
					boolean isSpecialization = 
						(sourceA1.equals(sourceA2) || sourceA1.allParents().contains(sourceA2)) &&
						(targetA1.equals(targetA2) || targetA1.allParents().contains(targetA2));
					boolean isReverseSpecialization = 
						(sourceA1.equals(targetA2) || sourceA1.allParents().contains(targetA2)) &&
						(targetA1.equals(sourceA2) || targetA1.allParents().contains(sourceA2));
					
					if(isSpecialization|| isReverseSpecialization){
							try {
								//verify if there is a similar occurrence created
								boolean exists = false;
								for (RelSpecOccurrence rs : getOccurrences()) {
									if(rs.isEqual(a1, a2))
										exists = true;
								}
								if(!exists)
									this.occurrence.add(new RelSpecOccurrence(a2, a1, this));
							} catch (Exception e) {
								System.out.println(info.acronym+"[java]: Provided information does not characterize an occurrence of the anti-pattern!");
								System.out.println(e.getMessage());
							}
					}
				} 
			}
		}
	}
	
	

}
