package br.ufes.inf.nemo.ontouml.antipattern.util;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Model;
import RefOntoUML.Class;
import RefOntoUML.PackageableElement;
import RefOntoUML.Relationship;

public class RefOntoUML2Graph {
	
	public static int[][] buildGraph (Model model, ArrayList<Class> classes, ArrayList<Relationship> relationships, boolean incGen, boolean incMat){
		
		classes.add(null);
		relationships.add(null);
		
		for (PackageableElement element : model.getPackagedElement()) {
			if(element instanceof Class) {
				classes.add((Class) element);
				if(incGen){
					for (Generalization g : ((Class)element).getGeneralization()) {
						relationships.add(g);
					}
				}
				
			}
			if((element instanceof Association || element instanceof Generalization) && !(element instanceof Derivation) && ( !(element instanceof MaterialAssociation) || incMat ) ){
				relationships.add((Relationship) element);
			}
		}
		
		int result[][] = new int[2][];
		int nodei[] = new int[relationships.size()];
		int nodej[] = new int[relationships.size()];
		nodei[0]=0;
		nodej[0]=0;
		
		for (Relationship r : relationships) {
			if (r==null)
				continue;
			
			if (r instanceof Association) {
				nodei[relationships.indexOf(r)] = classes.indexOf(((Association)r).getMemberEnd().get(0).getType());
				nodej[relationships.indexOf(r)] = classes.indexOf(((Association)r).getMemberEnd().get(1).getType());
			}
			if (r instanceof Generalization){
				nodei[relationships.indexOf(r)] = classes.indexOf(((Generalization)r).getGeneral());
				nodej[relationships.indexOf(r)] = classes.indexOf(((Generalization)r).getSpecific());
			}
		}
		
		result[0]=nodei;
		result[1]=nodej;
		
		return result;
		
	}
	
}
