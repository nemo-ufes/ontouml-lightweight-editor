package br.ufes.inf.nemo.common.ontouml2graph;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class OntoUML2Graph {
	
	public static int[][] buildGraph (OntoUMLParser parser, ArrayList<Class> classes, ArrayList<Relationship> relationships, boolean incGen, boolean incMat){
		
		classes.add(null);
		relationships.add(null);
		
		for (EObject element : parser.getElements()) 
		{
			if(element instanceof Class)
				classes.add((Class) element);

			if(element instanceof Association && !(element instanceof Derivation) && ( !(element instanceof MaterialAssociation) || incMat ) )
			{
				if (((Association) element).getMemberEnd().size()==2)
					relationships.add((Relationship) element);
			}
		}
		
		int result[][] = new int[2][];
		int nodei[] = new int[relationships.size()];
		int nodej[] = new int[relationships.size()];
		nodei[0]=0;
		nodej[0]=0;
		
		for (Relationship r : relationships) 
		{
			if (r==null)
				continue;
			
			if (r instanceof Association)
			{
				if (((Association) r).getMemberEnd().size()==2) {
					nodei[relationships.indexOf(r)] = classes.indexOf(((Association)r).getMemberEnd().get(0).getType());
					nodej[relationships.indexOf(r)] = classes.indexOf(((Association)r).getMemberEnd().get(1).getType());
				}
			}
			if (r instanceof Generalization)
			{
				nodei[relationships.indexOf(r)] = classes.indexOf(((Generalization)r).getGeneral());
				nodej[relationships.indexOf(r)] = classes.indexOf(((Generalization)r).getSpecific());
			}
		}
		
		result[0]=nodei;
		result[1]=nodej;
		
		return result;
		
	}
	
}
