package br.ufes.inf.nemo.antipattern.undefformal;

import java.util.ArrayList;

import RefOntoUML.FormalAssociation;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class UndefFormalAntipattern extends Antipattern<UndefFormalOccurrence> {

	public UndefFormalAntipattern(OntoUMLParser parser)	throws NullPointerException {
		super(parser);
	}

	public UndefFormalAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final String oclQuery =	
			
			"let dataTypeAssociations : Set (Association) = Association.allInstances()->select( a : Association | a.endType->exists( t : Type | t.oclIsTypeOf(DataType))) "+
"			in  "+
"				FormalAssociation.allInstances() "+
"					->select( f | f.memberEnd.type.oclAsType(Classifier) "+
"						->exists( c : Classifier |  "+
"							c.allParents()->including(c).attribute->size()=0 "+ 
"							and  "+
"							dataTypeAssociations->select(a : Association | a.endType->intersection(c.allParents()->including(c).oclAsType(Type) )->size()>0 )->size()=0 "+ 
"						) "+
"					)	  ";
	
	private static final AntipatternInfo info = new AntipatternInfo("Undefined Domain Formal Relation", 
			"UndefFormal", 
			"This anti-pattern is identified in a model when it contains a «formal» association such that the source, " +
			"the target or both of them do no have or inherit properties (attributes or associations) whose types are stereotyped as «datatype» (representing qualities) or «mode». ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}


	public AntipatternInfo info(){
		return info;
	}

	
	@Override
	public ArrayList<UndefFormalOccurrence> identify() {
		ArrayList<FormalAssociation> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, FormalAssociation.class);
		
		for (FormalAssociation formal : query_result) 
		{
			try {
				UndefFormalOccurrence occurrence = new UndefFormalOccurrence(formal, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

}
