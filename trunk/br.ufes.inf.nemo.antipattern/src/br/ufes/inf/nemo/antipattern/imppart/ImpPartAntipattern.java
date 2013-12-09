package br.ufes.inf.nemo.antipattern.imppart;

import java.util.ArrayList;

import RefOntoUML.Package;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ImpPartAntipattern extends Antipattern<ImpPartOccurrence> {

	public ImpPartAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public ImpPartAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

	private static final String oclQuery =	
			"Meronymic.allInstances().memberEnd->select( p : Property| p.aggregation=AggregationKind::none and (p.upper > 1 or p.upper=-1) and p.type.oclAsType(Classifier).allChildren()->size()>1)";
	 
	
	private static final AntipatternInfo info = new AntipatternInfo("Imprecise Part Specification", 
			"ImpPart", 
			"This anti-pattern occurs when a whole is connected to only one type of part, through a single meronymic association, and this part has two or more subtypes.",
			ImpPartAntipattern.oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<ImpPartOccurrence> identify() {
		ArrayList<Property> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Property.class);
		
		for (Property partEnd : query_result) 
		{
			try {
				super.occurrence.add(new ImpPartOccurrence(partEnd, super.parser));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

	
}
