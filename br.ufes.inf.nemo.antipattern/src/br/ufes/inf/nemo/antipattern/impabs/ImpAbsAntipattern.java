package br.ufes.inf.nemo.antipattern.impabs;

import java.util.ArrayList;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

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
			"This anti-pattern selects associations between which have at least one of the ends meeting the following criteria:" +
			"(i) its upper multiplicity is greater than 1; and (ii) its type (class) has 2 or more subtypes.",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Deprecated
	public ArrayList<ImpAbsOccurrence> identifyOCL() {
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
	
	@Override
	public ArrayList<ImpAbsOccurrence> identify() {
		parser.buildChildrenHashes();
		
		for (Association a : parser.getAllInstances(Association.class)) {
			try{
				Property source = a.getMemberEnd().get(0);
				Property target = a.getMemberEnd().get(1);
				
				Classifier sourceType = (Classifier) source.getType();
				Classifier targetType = (Classifier) target.getType();
				
				HashSet<Classifier> sourceChildren = parser.allChildrenHash.get(sourceType);
				HashSet<Classifier> targetChildren = parser.allChildrenHash.get(targetType);
				
				if(sourceChildren!=null && sourceChildren.size()>=2 && (source.getUpper()==-1 || source.getUpper()>1) || 
						targetChildren!=null && targetChildren.size()>=2 && (target.getUpper()==-1 || target.getUpper()>1) )
					occurrence.add(new ImpAbsOccurrence(a, this));
			}
			catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
				System.out.println(e.getMessage());
			}
		}
		
		return occurrence;
	}

}
