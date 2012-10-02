package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.Collection;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

import RefOntoUML.Model;
import RefOntoUML.Association;

/*Self Type Relationship*/
public class STRIdentifier {
	
	public static String OCLQuery = "Association.allInstances()->select(x:Association | x.ownedEnd.type->forAll(y1,y2:Type | y1=y2))";
	
	public static Collection<Association> STRQuery (Model m) throws ParserException
	{
		return (Collection<Association>) OCLQueryExecuter.executeQuery(OCLQuery, m.eClass(), m);
	}
}
