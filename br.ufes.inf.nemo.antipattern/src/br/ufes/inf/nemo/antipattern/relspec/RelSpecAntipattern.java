package br.ufes.inf.nemo.antipattern.relspec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.SimpleTuple;

public class RelSpecAntipattern extends Antipattern<RelSpecOccurrence> {

	public RelSpecAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public RelSpecAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	@Deprecated
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

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
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
	
	public <B extends Association> HashMap<Classifier,HashSet<Association>> buildAssociationHash(Class<B> associationType){
		HashMap<Classifier,HashSet<Association>> hash = new HashMap<Classifier,HashSet<Association>>();
		HashSet<Association> sourceSet, targetSet;
		
		for (Association a : parser.getAllInstances(associationType)) {
			
			try{				
				Classifier sourceType = (Classifier) a.getMemberEnd().get(0).getType();
				Classifier targetType = (Classifier) a.getMemberEnd().get(1).getType();
				
				if (!hash.keySet().contains(sourceType)){
					 sourceSet = new HashSet<Association>();
					 hash.put(sourceType, sourceSet);
				}
				else
					sourceSet = hash.get(sourceType);
				
				if (!hash.keySet().contains(targetType)){
					targetSet = new HashSet<Association>();
					hash.put(targetType, targetSet);
				}
				else
					targetSet = hash.get(targetType);
					
				sourceSet.add(a);
				targetSet.add(a);
		
			}
			catch(Exception e){ }
		}
			
		return hash;
	}
	
	@Override
	public ArrayList<RelSpecOccurrence> identify() {
		HashMap<Classifier,HashSet<Association>> hash = buildAssociationHash(Association.class);
		HashMap<Association,HashSet<Association>> visited = new HashMap<Association,HashSet<Association>>();
		
		HashSet<Association> checkedPotentialParents;
		
		for (Classifier baseType : hash.keySet()) {
			
			HashSet<Association> assocSet = new HashSet<Association>(hash.get(baseType));
			for (Classifier parent : parser.getAllParents(baseType)) {
				if(hash.containsKey(parent))
					assocSet.addAll(hash.get(parent));
			}
		
			for (Association potentialChild : assocSet) {
				
				if(visited.keySet().contains(potentialChild))
					checkedPotentialParents = visited.get(potentialChild);
				else{
					checkedPotentialParents = new HashSet<Association>();
					visited.put(potentialChild, checkedPotentialParents);
				}
				
				if(potentialChild.getMemberEnd()==null || potentialChild.getMemberEnd().size()!=2)
					continue;
				
				for (Association potentialParent : assocSet) {
					
					if(checkedPotentialParents.contains(potentialParent))
						continue;
					else
						checkedPotentialParents.add(potentialParent);
					
					if(potentialParent.getMemberEnd()==null || potentialParent.getMemberEnd().size()!=2)
						continue;
					
					if (potentialChild.equals(potentialParent))
						continue;
											
//						boolean isSpecialization = 
//							(sourceA1.equals(sourceA2) || sourceA1.allParents().contains(sourceA2)) &&
//							(targetA1.equals(targetA2) || targetA1.allParents().contains(targetA2)) && 
//							!(sourceEndA1.getSubsettedProperty().contains(sourceEndA2) || sourceEndA1.getRedefinedProperty().contains(sourceEndA2)) &&
//							!(targetEndA1.getSubsettedProperty().contains(targetEndA2) || targetEndA1.getRedefinedProperty().contains(targetEndA2)) &&
//							!(sourceEndA2.getSubsettedProperty().contains(sourceEndA1) || sourceEndA2.getRedefinedProperty().contains(sourceEndA1)) &&
//							!(targetEndA2.getSubsettedProperty().contains(targetEndA1) || targetEndA2.getRedefinedProperty().contains(targetEndA1));
					
//						boolean isReverseSpecialization = 
//							(sourceA1.equals(targetA2) || sourceA1.allParents().contains(targetA2)) &&
//							(targetA1.equals(sourceA2) || targetA1.allParents().contains(sourceA2)) &&
//							!(sourceEndA1.getSubsettedProperty().contains(targetEndA2) || sourceEndA1.getRedefinedProperty().contains(targetEndA2)) &&
//							!(targetEndA1.getSubsettedProperty().contains(sourceEndA2) || targetEndA1.getRedefinedProperty().contains(sourceEndA2)) &&
//							!(sourceEndA2.getSubsettedProperty().contains(targetEndA1) || sourceEndA2.getRedefinedProperty().contains(targetEndA1)) &&
//							!(targetEndA2.getSubsettedProperty().contains(sourceEndA1) || targetEndA2.getRedefinedProperty().contains(sourceEndA1));
					
//					if((isSpecialization(potentialChild, potentialParent) && !isSubsettingOrRedefinition(potentialChild, potentialParent) && !isRedefinition(potentialChild, potentialParent)) )
//						createOccurrence(potentialChild, potentialParent);
					
					if( isSpecialization(potentialChild, potentialParent) && !isSubsettingOrRedefinition(potentialChild, potentialParent))
						createOccurrence(potentialChild, potentialParent);
						 
				}
			}
		}
		
		return this.getOccurrences();
	}

	public static boolean isSubsettingOrRedefinition(Association child, Association parent){
		Property 	soParent = parent.getMemberEnd().get(0),
					tgParent = parent.getMemberEnd().get(1),
					soChild = child.getMemberEnd().get(0),
					tgChild = child.getMemberEnd().get(1);
		
		return 	subsetsOrRedefinesProperty(soChild, soParent) || subsetsOrRedefinesProperty(soChild, tgParent) ||
				subsetsOrRedefinesProperty(tgChild, soParent) || subsetsOrRedefinesProperty(tgChild, tgParent) ||
				subsetsOrRedefinesProperty(soParent, soChild) || subsetsOrRedefinesProperty(tgParent, soChild) ||
				subsetsOrRedefinesProperty(soParent, tgChild) || subsetsOrRedefinesProperty(tgParent, tgChild);
	}
	
	public static boolean subsetsOrRedefinesProperty(Property child, Property parent){
		
		for (Property subsetted : child.getSubsettedProperty()) {
			if(subsetted.equals(parent))
				return true;
			if(subsetsOrRedefinesProperty(subsetted, parent))
				return true;	
		}
		
		for (Property redefined : child.getRedefinedProperty()) {
			if(redefined.equals(parent))
				return true;
			if(subsetsOrRedefinesProperty(redefined, parent))
				return true;	
		}
		
		return false;
	}
	
//	private boolean isRedefinition(Association child, Association parent){
//		Property 	soParent = parent.getMemberEnd().get(0),
//					tgParent = parent.getMemberEnd().get(1),
//					soChild = child.getMemberEnd().get(0),
//					tgChild = child.getMemberEnd().get(1);
//		
//		return 	redefinesProperty(soChild, soParent) || redefinesProperty(soChild, tgParent) ||
//				redefinesProperty(tgChild, soParent) || redefinesProperty(tgChild, tgParent) ||
//				redefinesProperty(soParent, soChild) || redefinesProperty(tgParent, soChild) ||
//				redefinesProperty(soParent, tgChild) || redefinesProperty(tgParent, tgChild);
//	}
	
//	private boolean redefinesProperty(Property child, Property parent){
//		
//		for (Property redefined : child.getRedefinedProperty()) {
//			if(redefined.equals(parent))
//				return true;
//			if(redefinesProperty(redefined, parent))
//				return true;	
//		}
//		
//		return false;
//	}
	
	public static boolean isSpecialization(Association child, Association parent) {
		Classifier	sourceChild = (Classifier) child.getMemberEnd().get(0).getType(), 
					targetChild = (Classifier) child.getMemberEnd().get(1).getType(),
					sourceParent = (Classifier) parent.getMemberEnd().get(0).getType(), 
					targetParent = (Classifier) parent.getMemberEnd().get(1).getType();
		
		return 	(
					(sourceChild.equals(sourceParent) || sourceChild.allParents().contains(sourceParent)) &&
					(targetChild.equals(targetParent) || targetChild.allParents().contains(targetParent))
				)
				||
				(
					(sourceChild.equals(targetParent) || sourceChild.allParents().contains(targetParent)) &&
					(targetChild.equals(sourceParent) || targetChild.allParents().contains(sourceParent)) 
				);
	}

	private void createOccurrence(Association specific, Association general) {
		try {
			for (RelSpecOccurrence rs : occurrence) {
				if(rs.isEqual(specific, general))
					return;
			}
			occurrence.add(new RelSpecOccurrence(specific, general, this));
			
		} catch (Exception e) {
			System.out.println(info.acronym+"[java]: Provided information does not characterize an occurrence of the anti-pattern!");
			System.out.println(e.getMessage());
		}
	}
	

	
	
//	@Override
//	public ArrayList<RelSpecOccurrence> identify() {
//		
//		identifyByStereotype(parser.getAllInstances(Characterization.class));
//		identifyByStereotype(parser.getAllInstances(Mediation.class));
//		identifyByStereotype(parser.getAllInstances(MaterialAssociation.class));
//		identifyByStereotype(parser.getAllInstances(FormalAssociation.class));
//		identifyByStereotype(parser.getAllInstances(componentOf.class));
//		identifyByStereotype(parser.getAllInstances(memberOf.class));
//		identifyByStereotype(parser.getAllInstances(subQuantityOf.class));
//		identifyByStereotype(parser.getAllInstances(subCollectionOf.class));
//		
//		Set<Association> remainderAssociations = new HashSet<Association>();
//		for (Association a : parser.getAllInstances(Association.class)) {
//			if (!(a instanceof DirectedBinaryAssociation) || !(a instanceof MaterialAssociation) || !(a instanceof FormalAssociation)) 
//					 remainderAssociations.add(a);
//		}
//		
//		identifyByStereotype(remainderAssociations);
//		return this.getOccurrences();
//	}
//	
//	private <T extends Association> void identifyByStereotype( Set<T> associationList){
//		for (Association a1 : associationList) {
//			
//			if(a1.getMemberEnd()==null || a1.getMemberEnd().size()!=2)
//				continue;
//			
//			Classifier 	sourceA1 = (Classifier) a1.getMemberEnd().get(0).getType(), 
//						targetA1 = (Classifier) a1.getMemberEnd().get(1).getType();
//			Property sourceEndA1 = a1.getMemberEnd().get(0),
//					 targetEndA1 = a1.getMemberEnd().get(1);
//			
//			for (Association a2 : associationList) {
//								
//				if(a2.getMemberEnd()==null || a2.getMemberEnd().size()!=2)
//					continue;
//				
//				Classifier 	sourceA2 = (Classifier) a2.getMemberEnd().get(0).getType(), 
//							targetA2 = (Classifier) a2.getMemberEnd().get(1).getType();
//				Property sourceEndA2 = a2.getMemberEnd().get(0),
//						 targetEndA2 = a2.getMemberEnd().get(1);
//				
//				if (!a1.equals(a2)){
//					
//					boolean isSpecialization = 
//						(sourceA1.equals(sourceA2) || sourceA1.allParents().contains(sourceA2)) &&
//						(targetA1.equals(targetA2) || targetA1.allParents().contains(targetA2)) && 
//						!(sourceEndA1.getSubsettedProperty().contains(sourceEndA2) || sourceEndA1.getRedefinedProperty().contains(sourceEndA2)) &&
//						!(targetEndA1.getSubsettedProperty().contains(targetEndA2) || targetEndA1.getRedefinedProperty().contains(targetEndA2));
//					boolean isReverseSpecialization = 
//						(sourceA1.equals(targetA2) || sourceA1.allParents().contains(targetA2)) &&
//						(targetA1.equals(sourceA2) || targetA1.allParents().contains(sourceA2)) &&
//						!(sourceEndA1.getSubsettedProperty().contains(targetEndA2) || sourceEndA1.getRedefinedProperty().contains(targetEndA2)) &&
//						!(targetEndA1.getSubsettedProperty().contains(sourceEndA2) || targetEndA1.getRedefinedProperty().contains(sourceEndA2));;
//					
//					if(isSpecialization || isReverseSpecialization){
//							createOccurrence(a1, a2);
//					}
//				} 
//			}
//		}
//	}

	
	
	

}
