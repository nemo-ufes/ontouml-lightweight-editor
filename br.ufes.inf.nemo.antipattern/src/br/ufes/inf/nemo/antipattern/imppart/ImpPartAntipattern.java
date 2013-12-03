package br.ufes.inf.nemo.antipattern.imppart;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ImpPartAntipattern extends Antipattern {

	public ImpPartAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
		// TODO Auto-generated constructor stub
	}

	public ImpPartAntipattern(Package pack) throws NullPointerException {
		super(pack);
		// TODO Auto-generated constructor stub
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
