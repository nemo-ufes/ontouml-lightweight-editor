package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.Collection;

import org.eclipse.ocl.ParserException;

import RefOntoUML.Association;
import RefOntoUML.Model;
import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

public class ACIdentifier {
	private String OCLQuery (int size){
		String query="";
		
		if (size==0)
			return null;
		
		query += "Association.allInstances()";
				
				
		for (int i=1; i<size; i++)
			query +="->product(Association.allInstances())"; 
		
		return query;
	}
	
	public static void IAQuery(Model m, int size) throws ParserException 
	{
		String query = "Association.allInstances()";
		Collection<Association> result = (Collection<Association>) OCLQueryExecuter.executeQuery(query, m.eClass(), m);
		for (Association a : result) {
			System.out.println(a.getName());
		}
	}
}
