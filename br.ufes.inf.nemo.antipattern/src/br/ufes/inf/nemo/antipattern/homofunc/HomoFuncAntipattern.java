package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class HomoFuncAntipattern extends Antipattern<HomoFuncOccurrence> {

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
	
	public ArrayList<HomoFuncOccurrence> identifyOCL() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association a : query_result) 
		{
			try {
				
				HomoFuncOccurrence occurrence = new HomoFuncOccurrence(a, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

	@Override
	public ArrayList<HomoFuncOccurrence> identify() {
		HashMap<Classifier,HashSet<Property>> hash = buildPropertyHash(componentOf.class);
		
		for (Classifier whole : hash.keySet()) {
			
			ArrayList<Property> functionalParts = new ArrayList<Property>(hash.get(whole));

			if(functionalParts.size()==1 && hasDirectPart(whole,functionalParts)){
				try {
					occurrence.add(new HomoFuncOccurrence(functionalParts.get(0).getAssociation(), this));
				} catch (Exception e) {
					System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
					System.out.println(e.getMessage());
				}
			}
		}
		
		return occurrence;
	}
	
	public <B extends Meronymic> HashMap<Classifier,HashSet<Property>> buildPropertyHash(Class<B> type){
		HashMap<Classifier,HashSet<Property>> hash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with meronymics that are directly connected to the types
		for (B m : parser.getAllInstances(type)) {
			
			try{
				Property partEnd = OntoUMLParser.getPartEnd(m);
				Classifier mainType = (Classifier) partEnd.getOpposite().getType();
				
				if (hash.keySet().contains(mainType))
					hash.get(mainType).add(partEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(partEnd);
					hash.put(mainType, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' parts
		for (Classifier mainType : hash.keySet()) 
			for (Classifier parent : mainType.allParents()) 
				if(hash.keySet().contains(parent))
					hash.get(mainType).addAll(hash.get(parent));
			
		return hash;
	}
	
	public boolean hasDirectPart(Classifier c, ArrayList<Property> partEnds){
		for (Property p : partEnds) {
			if(p.getOpposite().getType().equals(c))
				return true;
		}
		
		return false;
	}
}
