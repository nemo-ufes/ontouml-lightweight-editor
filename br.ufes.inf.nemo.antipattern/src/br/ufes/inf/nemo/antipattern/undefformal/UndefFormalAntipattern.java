package br.ufes.inf.nemo.antipattern.undefformal;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class UndefFormalAntipattern extends Antipattern {

	public UndefFormalAntipattern(OntoUMLParser parser)
			throws NullPointerException {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	public UndefFormalAntipattern(Package pack) throws NullPointerException {
		super(pack);
		// TODO Auto-generated constructor stub
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

	@Override
	public <T extends Antipattern> ArrayList<T> identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Antipattern> ArrayList<T> getOccurrences() {
		// TODO Auto-generated method stub
		return null;
	}

}
