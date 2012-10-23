package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.util.Tuple;

import RefOntoUML.Association;
import RefOntoUML.Model;

import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

public class AntiPatternIdentifier {
	
	/*OCL query for the identification of the Relation Specialization AntiPattern*/
	public static String RS_OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second})->select( x | x.a1<>x.a2 and ( ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) ) or ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) )) )";
	
	/*Returns all the Relation Specialization AntiPatterns in the input model m*/ 
	public static ArrayList<RSAntiPattern> identifyRS(Model m, NamesMapper mapper) throws Exception 
	{
		Collection<Tuple<Association, Association>> query_result;
		ArrayList<RSAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Tuple<Association, Association>>)OCLQueryExecuter.executeQuery(RS_OCLQuery, m.eClass(), m);
		
		for (Tuple<Association, Association> t : query_result) {
			RSAntiPattern rs = new RSAntiPattern((Association)t.getValue("a1"), (Association)t.getValue("a2"), mapper); 
			result.add(rs);
		}
		
		return result;
	}
	
	/*OCL query for the identification of the Self-Type Relationship AntiPattern*/
	public static String STR_OCLQuery = "Association.allInstances()->select(x:Association | x.ownedEnd.type->forAll(y1,y2:Type | y1=y2))";
	
	/*Returns all the Self-Type Relationship AntiPatterns in the input model m*/
	public static ArrayList<STRAntiPattern> identifySTR (Model m, NamesMapper mapper) throws Exception
	{
		Collection<Association> query_result;
		ArrayList<STRAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Association>) OCLQueryExecuter.executeQuery(STR_OCLQuery, m.eClass(), m);
		
		for (Association a : query_result) {
			result.add(new STRAntiPattern(a, mapper));
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
