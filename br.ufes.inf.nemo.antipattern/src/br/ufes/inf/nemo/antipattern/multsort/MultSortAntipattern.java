package br.ufes.inf.nemo.antipattern.multsort;

import java.util.ArrayList;

import RefOntoUML.SortalClass;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MultSortAntipattern extends Antipattern<MultSortOccurrence> {

	public MultSortAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	private static final String oclQuery = 
			"SortalClass.allInstances()" +
			"->select( x | " +
			"	x.oclAsType(Classifier).parents()" +
			"	->select( parent : Classifier | " +
			"		parent.oclIsKindOf(SortalClass)" +
			"	)->size()>1" +
			")";

	private static final AntipatternInfo info = new AntipatternInfo("Multiple Sortal Inheritance", 
			"MultSortInh", 
			"This anti-pattern identifies...",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<MultSortOccurrence> identify() {
		ArrayList<SortalClass> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, SortalClass.class);
			
		for (SortalClass subtype : query_result) 
		{
			try {
				
					MultSortOccurrence occurrence = new MultSortOccurrence(subtype, this.parser);
					super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Provided information does not characterize an occurrence of the anti-pattern!");
			}
		}
		
		return this.getOccurrences();
	}

}
