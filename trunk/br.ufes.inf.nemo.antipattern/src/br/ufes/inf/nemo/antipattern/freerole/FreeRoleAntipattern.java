package br.ufes.inf.nemo.antipattern.freerole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class FreeRoleAntipattern extends Antipattern<FreeRoleOccurrence> {

	public HashMap<Classifier,HashSet<Property>> relatorHash;
	
	private static final String oclQuery = 	
		"let mediatedProperties : Bag (Property) = "+
		"	Mediation.allInstances().memberEnd "+
		"		->select( p: Property |  "+
		"			not p.type.oclIsTypeOf(Relator) "+ // mediado não é um relator 
		"			and  "+
		"			p.type.oclAsType(Classifier).allParents()->forAll( parent : Classifier | not parent.oclIsTypeOf(Relator))) "+ // mediado não é subtipo de nenhum relator (e.g. phase de relator) 
		"	in mediatedProperties "+ // expressão para agrupar mediados e as propriedades opostas conectas a relators
		"		->collect( p : Property | Tuple { "+
		"			mediated: Classifier=p.type.oclAsType(Classifier),  "+
		"			relatorEnds: Set(Property)=mediatedProperties->select(p2 : Property | p.type=p2.type).opposite->asSet()} "+
		"		)->asSet() "+
		"		->select ( x |  "+
		"			x.mediated.oclIsTypeOf(Role) "+ // mediado é um role
		"			and "+
		"			x.mediated.allChildren() "+
		"			->exists( child: Classifier | "+ // existe pelo menos um subtipo do role que: 
		"				child.oclIsTypeOf(Role) "+ // é instância de um role 
		"				and  "+
		"				mediatedProperties.type->excludes(child.oclAsType(Type)) "+  //não está conectado diretamente a nenhuma mediação
		"				and "+
		"				child.allParents() "+
		"				->forAll( parent: Classifier |  "+
		"					(parent<>x.mediated "+ // todos os demais pais não estão diretamente conectados a uma mediation 
		"					and "+
		"					x.mediated.allParents()->excludes(parent)) "+
		"					implies "+
		"					mediatedProperties.type->excludes(parent.oclAsType(Type)) "+ 
		"				) "+
		"			) "+ 
		"		)";
				
	private static final AntipatternInfo info = new AntipatternInfo("Free Role Specialization", 
			"FreeRole", 
			"This anti-pattern identifies occurrences when a «role» type connected to a «relator» through a «mediation» association, " +
			"is specialized in another «role» type, which in turn is not connected to an additional «mediation» association.",
			oclQuery);

	
	public FreeRoleAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public FreeRoleAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	public AntipatternInfo info(){
		return info;
	}

	public ArrayList<FreeRoleOccurrence> identifyOCL() {
		Map<Classifier, ArrayList<Property>> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Classifier.class, Property.class, "mediated", "relatorEnds");
			
		for (Classifier role : query_result.keySet()) 
		{
			try {
				if (role instanceof Role){
					FreeRoleOccurrence occurrence = new FreeRoleOccurrence((Role) role, query_result.get(role), this);
					super.occurrence.add(occurrence);
				}else throw new Exception();
			} catch (Exception e) {
				System.out.println("FreeRole: Provided information does not characterize an occurrence of the anti-pattern!");
			}
		}
		
		return this.getOccurrences();
	}
	
	@Override
	public ArrayList<FreeRoleOccurrence> identify() {
		buildPropertyHash();
		parser.buildChildrenHashes();
		
		for (Classifier mediated : relatorHash.keySet()) {
			
			if(!(mediated instanceof SortalClass))
				continue;
			
			if(!parser.childHash.containsKey(mediated))
				continue;
			
			for (Classifier child : parser.childHash.get(mediated)) {
				if(!(child instanceof Role))
					continue;
				
				if(relatorHash.containsKey(child))
					continue;
				
				boolean hasDefinedMixinParent = false;
				for(Classifier parent : parser.getParents(child)){
					if(parent instanceof RoleMixin && relatorHash.containsKey(parent)){
						hasDefinedMixinParent = true;
						break;
					}
				}
				
				if(hasDefinedMixinParent)
					continue;
				
				ArrayList<Property> properties = new ArrayList<Property>(relatorHash.get(mediated));
				
				try {
					occurrence.add(new FreeRoleOccurrence(mediated, properties, this));
					break;
				} catch (Exception e) {
					System.out.println("FreeRole: Provided information does not characterize an occurrence of the anti-pattern!");
					System.out.println(e.getMessage());
				}	
			}
		}
		
		return this.getOccurrences();
	}
	
	private void buildPropertyHash(){
		relatorHash = new HashMap<Classifier,HashSet<Property>>();
		
		//builds initial hash, with mediations that are directly connected to the types
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			
			try{
				Property relatorEnd = OntoUMLParser.getRelatorEnd(m);
				Classifier mediated = OntoUMLParser.getMediatedType(m);
				
				if (relatorHash.containsKey(mediated))
					relatorHash.get(mediated).add(relatorEnd);
				else{
					HashSet<Property> properties = new HashSet<Property>();
					properties.add(relatorEnd);
					relatorHash.put(mediated, properties);
				}
			}
			catch(Exception e){ }
		}
		
		//adds supertypes' mediated
		for (Classifier mainType : relatorHash.keySet()) 
			for (Classifier parent : mainType.allParents()) 
				if(relatorHash.containsKey(parent))
					relatorHash.get(mainType).addAll(relatorHash.get(parent));
	}

}
