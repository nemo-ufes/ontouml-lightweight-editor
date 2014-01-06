package br.ufes.inf.nemo.antipattern.impabs;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ImpAbsAntipattern extends Antipattern<ImpAbsOccurrence> {

	public ImpAbsAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public ImpAbsAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final String oclQuery =	
			"Association.allInstances()->select(x:Association | (x.memberEnd.type->at(1).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(1).upper=-1 or x.memberEnd->at(1).upper>1)) or (x.memberEnd.type->at(2).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(2).upper=-1 or x.memberEnd->at(2).upper>1)))";
	
	private static final AntipatternInfo info = new AntipatternInfo("Imprecise Abstraction", 
			"ImpAbs", 
			"This anti-pattern occurs when...",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	@Override
	public ArrayList<ImpAbsOccurrence> identify() {
		ArrayList<Association> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Association.class);
		
		for (Association assoc : query_result) 
		{
			try {
				super.occurrence.add(new ImpAbsOccurrence(assoc, this));
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return this.getOccurrences();
	}

}
