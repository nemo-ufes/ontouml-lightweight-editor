package br.ufes.inf.nemo.antipattern.homofunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class HomoFuncAntipattern extends Antipattern<HomoFuncOccurrence> {
	
	private HashMap<Classifier,HashSet<Property>> partsHash; 
	private HashMap<Classifier,HashSet<Property>> wholesHash;
	
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
	
	public HomoFuncAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public HomoFuncAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	

	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	public ArrayList<HomoFuncOccurrence> identifyOCL() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association a : query_result) 
		{
			try {
				
				HomoFuncOccurrence occurrence = new HomoFuncOccurrence(a, new ArrayList<Property>(), this);
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
		buildPropertyHash(componentOf.class);
		
		for (Classifier whole : partsHash.keySet()) {
			
			ArrayList<Property> functionalParts = new ArrayList<Property>(partsHash.get(whole));
			
			if(functionalParts.size()==1 && hasDirectPart(whole,functionalParts)){
				try {
					
					ArrayList<Property> functionalWholes = new ArrayList<Property>();
					
					if(wholesHash.containsKey(whole))
						functionalWholes.addAll(wholesHash.get(whole));
					
					//adds supertypes' wholes
					for (Classifier parent : whole.allParents()){
						if(wholesHash.containsKey(parent)){
							functionalWholes.addAll(wholesHash.get(parent));
						}
					}
					
					occurrence.add(new HomoFuncOccurrence(functionalParts.get(0).getAssociation(), functionalWholes, this));
					
				}
				catch (Exception e) {
					System.out.println(info.acronym+": Provided information does not characterize an occurrence of the anti-pattern!");
					System.out.println(e.getMessage());
				}
			}
		}
		
		return occurrence;
	}
	
	public <B extends Meronymic> void buildPropertyHash(Class<B> type){
		partsHash = new HashMap<Classifier,HashSet<Property>>();
		wholesHash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with meronymics that are directly connected to the types
		for (B m : parser.getAllInstances(type)) {
			try{
				
				Property partEnd, wholeEnd; 
				Classifier part = null, whole = null;
				
				partEnd = OntoUMLParser.getPartEnd(m);
				wholeEnd = OntoUMLParser.getWholeEnd(m);
				
				if(wholeEnd!=null)
					whole = (Classifier) wholeEnd.getType();
				if(partEnd!=null)
					part = (Classifier) partEnd.getType();
				
				if(partEnd==null || wholeEnd == null || part==null || whole ==null){
					System.out.println("================================================");
					System.out.println(OntoUMLNameHelper.getCommonName(partEnd));
					System.out.println(OntoUMLNameHelper.getCommonName(part));
					System.out.println(OntoUMLNameHelper.getCommonName(wholeEnd));
					System.out.println(OntoUMLNameHelper.getCommonName(whole));
					System.out.println();
					continue;
				}
				
				if (partsHash.containsKey(whole))
					partsHash.get(whole).add(partEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(partEnd);
					partsHash.put(whole, properties);
				}
				
				if (wholesHash.containsKey(part)){
					wholesHash.get(part).add(wholeEnd);
				}
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(wholeEnd);
					wholesHash.put(part, properties);
				}
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		
		//adds supertypes' parts
		for (Classifier whole : partsHash.keySet()) 
			for (Classifier parent : whole.allParents()) 
				if(partsHash.containsKey(parent))
					partsHash.get(whole).addAll(partsHash.get(parent));
	}
	
	public boolean hasDirectPart(Classifier c, ArrayList<Property> partEnds){
		for (Property p : partEnds) {
			Type wholeType = OntoUMLParser.getWholeEnd(((Meronymic)p.getAssociation())).getType();
			if(wholeType.equals(c))
				return true;
		}
		
		return false;
	}
}
