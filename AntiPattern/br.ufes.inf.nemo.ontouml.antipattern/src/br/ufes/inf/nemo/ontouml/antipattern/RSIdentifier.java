package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.Collection;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.util.Tuple;

import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

import RefOntoUML.Association;
import RefOntoUML.Model;


/*Relation Specialization*/
public class RSIdentifier {

	public static String OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second})->select( x | x.a1<>x.a2 and ( ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) ) or ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) )) )";
	
	public static Collection<Tuple<Association, Association>> RSQuery(Model m) throws ParserException 
	{
		return (Collection<Tuple<Association, Association>>)OCLQueryExecuter.executeQuery(OCLQuery, m.eClass(), m);
	}
}
