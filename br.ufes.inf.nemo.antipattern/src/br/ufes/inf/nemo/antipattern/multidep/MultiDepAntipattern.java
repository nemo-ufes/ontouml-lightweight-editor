package br.ufes.inf.nemo.antipattern.multidep;

import java.util.ArrayList;

import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class MultiDepAntipattern extends Antipattern<MultiDepOccurrence> {

	public MultiDepAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	
	}

	public MultiDepAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

	// do not remove the cases where all mediations are with the same relator
	// String old_query = "ObjectClass.allInstances()->select(c:ObjectClass | Mediation.allInstances()->select(m:Mediation | m.endType->includes(c))->size()>=2)";
	
	private static final String oclQuery =  "ObjectClass.allInstances()->select(c:ObjectClass | "+
		    "let mediations : Set(Mediation) = Mediation.allInstances()->select(m:Mediation | m.endType->includes(c))" +
		    "in mediations->size()>=2 and mediations->collect(m: Mediation | m.relator())->asSet()"+
		    "->size() = mediations->size()"+
			")";
	
	public static final AntipatternInfo info = new AntipatternInfo("Multiple Relational Dependency", 
			"MultDep", 
			"This anti-pattern is identified when an object class is connected to two distinct «relator» types through «mediation» associations.  " +
			"The relators may not specialize one another.",
			oclQuery); 

	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<MultiDepOccurrence> identify() {
		ArrayList<ObjectClass> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, ObjectClass.class);
		
		for (ObjectClass type : query_result) 
		{
			try {
				MultiDepOccurrence occurrence = new MultiDepOccurrence(type, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}

}
