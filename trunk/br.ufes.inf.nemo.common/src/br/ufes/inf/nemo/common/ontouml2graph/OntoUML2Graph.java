package br.ufes.inf.nemo.common.ontouml2graph;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;

public class OntoUML2Graph {
	
	public static int[][] buildGraph (OntoUMLParser parser, ArrayList<Class> classes, ArrayList<Relationship> relationships){
		HashMap<Type,ArrayList<Type>> relatedHash = new HashMap<Type,ArrayList<Type>>();
		
		classes.add(null);
		relationships.add(null);
		
		for (EObject element : parser.getElements()) 
		{
			if(element instanceof Class)
				classes.add((Class) element);

			if(element instanceof Association && !(element instanceof Derivation) )
			{
				Association a = (Association) element;
				if (a.getMemberEnd().size()!=2 || a.isIsDerived())
					continue;
				
				Property sourceEnd = a.getMemberEnd().get(0);
				Property targetEnd = a.getMemberEnd().get(1);	
				Type source = sourceEnd.getType();
				Type target = targetEnd.getType();
				
				if(sourceEnd.isIsDerived() || !(source instanceof Class) || 
						targetEnd.isIsDerived() || !(target instanceof Class) ||
						source.equals(target))
					continue;
				
				if(relatedHash.containsKey(source) && relatedHash.get(source).contains(target) )
					continue;
				
				addToHash(relatedHash,source,target);
				addToHash(relatedHash,target,source);
				relationships.add((Relationship) element);
			}
			
			if(element instanceof Generalization ){
				Generalization g = (Generalization)element;
				Classifier parent = g.getGeneral();
				Classifier child = g.getSpecific();
				
				if(!(parent instanceof Class) || !(child instanceof Class) || child.equals(parent))
					continue;
				
				if(relatedHash.containsKey(child) && relatedHash.get(child).contains(parent) )
					continue;
				
				addToHash(relatedHash,child,parent);
				addToHash(relatedHash,parent,child);
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
	
	public static <T,S> void  addToHash(HashMap<T,ArrayList<S>> hash, T key, S value){
		if(hash.containsKey(key)){
			hash.get(key).add(value);
			return;
		}
		
		ArrayList<S> list = new ArrayList<S>();
		list.add(value);
		hash.put(key, list);
		
	}
	
}
