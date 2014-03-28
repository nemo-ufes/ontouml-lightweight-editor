package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.util.Tuple;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import br.ufes.inf.nemo.common.list.ArrayListOperations;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AntiPatternIdentifier {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends EObject, S extends EObject> Map<T,ArrayList<S>> runOCLQuery (OntoUMLParser parser, 
			String oclQuery, java.lang.Class<T> queryReturnType1, java.lang.Class<S> queryReturnType2, String tupleField1, String tupleField2){
		
		Map<T,ArrayList<S>>  queryResult = new HashMap<T,ArrayList<S>> ();
		
		Copier copier = new Copier();
		
		//create a new package, copying the selected elements
		Package model = parser.createPackageFromSelections(copier);
		
		try {
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			System.out.println(o);
			if (o instanceof Collection<?>){
				System.out.println("Is collection. Size: "+((Collection)o).size());
				for (Object element : (Collection<?>) o ) {
					
					if (element instanceof Tuple){
						
						Tuple<T,Set<S>> tuple = (Tuple) element;
						
						if (queryReturnType1.isInstance(tuple.getValue(tupleField1))){
							
							T field1Original = (T) AntiPatternUtil.getOriginal((EObject)tuple.getValue(tupleField1), copier);
							
							Set<S> field2 = (Set<S>) tuple.getValue(tupleField2);
							ArrayList<S> field2Original = new ArrayList<>();
							for (S s : field2) {
								field2Original.add((S) AntiPatternUtil.getOriginal((EObject)s, copier));
							}
									
							queryResult.put(field1Original, field2Original);
						}
					}
				}
			}
			
		} catch (ParserException e) {
			System.out.println("Error in OCL query!\n"+e.getMessage());
		}
		
		return queryResult;
	}
		
	@SuppressWarnings("unchecked")
	public static <T extends EObject> ArrayList<T> runOCLQuery (OntoUMLParser parser, String oclQuery, java.lang.Class<T> queryReturnType){
		ArrayList<T> queryResult = new ArrayList<T>();
		
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		try {
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			if (o instanceof Collection<?>){
				
				for (Object element : (Collection<?>) o ) {
					
					if(queryReturnType.isInstance(element) && element instanceof EObject){
						
						T original = (T) AntiPatternUtil.getOriginal((EObject)element, copier);			
						queryResult.add(original);
						
					}
				}
			}
			
		} catch (ParserException e) {
			System.out.println("Error in OCL query!\n"+e.getMessage());
		}
		
		return queryResult;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends EObject, S extends EObject> ArrayList<SimpleTuple<T,S>> runOCLQuerySimpleTuple (OntoUMLParser parser, 
			String oclQuery, java.lang.Class<T> queryReturnType1, java.lang.Class<S> queryReturnType2, String tupleField1, String tupleField2){
		
		ArrayList<SimpleTuple<T,S>> queryResult = new ArrayList<SimpleTuple<T,S>>();
		
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		try {
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			if (o instanceof Collection<?>){
				
				for (Object element : (Collection<?>) o ) {
					
					if (element instanceof Tuple){
						
						Tuple<T,S> tuple = (Tuple) element;
					
						if(queryReturnType1.isInstance(tuple.getValue(tupleField1)) && queryReturnType2.isInstance(tuple.getValue(tupleField2)) 
								&& tuple.getValue(tupleField1) instanceof EObject && tuple.getValue(tupleField2) instanceof EObject){
							
							T original1 = (T) AntiPatternUtil.getOriginal((EObject)tuple.getValue(tupleField1), copier);
							S original2 = (S) AntiPatternUtil.getOriginal((EObject)tuple.getValue(tupleField2), copier);
													
							queryResult.add(new SimpleTuple<T,S>(original1, original2));
						}
					}
				}
			}
		}catch (ParserException e) {
			System.out.println("Error in OCL query!\n"+e.getMessage());
		}
		
		return queryResult;
	}
	
	//Get generalization sets from a classifier's generalization
	public static ArrayList<GeneralizationSet> getGeneralizationSets(Classifier c){
		Set<GeneralizationSet> genSets = new HashSet<GeneralizationSet>();
		
		for (Generalization g : c.getGeneralization()) {
			genSets.addAll(g.getGeneralizationSet());
		}
		
		ArrayList<GeneralizationSet> result = new ArrayList<GeneralizationSet>();
		result.addAll(genSets);
		return result;
	}
	
	//Get generalization sets from a classifier's generalization
	public static ArrayList<GeneralizationSet> getSubtypesGeneralizationSets(Classifier c){
		if(c==null)
			return null;
		
		Set<GeneralizationSet> genSets = new HashSet<GeneralizationSet>();
		for (Classifier child : c.children()) {
			genSets.addAll(getGeneralizationSets(child));
		}
		ArrayList<GeneralizationSet> result = new ArrayList<GeneralizationSet>();
		result.addAll(genSets);
		return result;
	}

	public static ArrayList<Classifier> getCommonSupertypesFromClassifiers(ArrayList<Classifier> types) {
		ArrayList<Classifier> superTypes = new ArrayList<Classifier>();
		
		if(types==null || types.size()==0)
			return superTypes;
		
		superTypes.addAll(types.get(0).allParents());
		
		for (int i = 1; i < types.size(); i++) {
			ArrayList<Classifier> currentSupertypes = new ArrayList<Classifier>();
			currentSupertypes.addAll(types.get(i).allParents());
			superTypes.retainAll(currentSupertypes);
		}
		
		return superTypes;
	}

	public static boolean madeDisjointByGeneralizationSet(	ArrayList<Classifier> types, ArrayList<GeneralizationSet> genSets, ArrayList<Classifier> commonSupertypes) {
		//collect generalizationSets
		ArrayList<GeneralizationSet> generalizationSets = new ArrayList<GeneralizationSet>();
		for (Classifier parent : commonSupertypes) {
			generalizationSets = getSubtypesGeneralizationSets(parent);
		}
		
		//verifies if there is a generalization set which makes the subtypes disjoint
		for (GeneralizationSet gs : generalizationSets) {
			int typesInDifferentGeneralizations = 0;
			
			for (Generalization g1 : gs.getGeneralization())
				if(types.contains(g1.getSpecific()) || ArrayListOperations.hasIntersection(types, g1.getSpecific().allChildren()))
					typesInDifferentGeneralizations++;
			
			if(typesInDifferentGeneralizations>1){
				if(gs.isIsDisjoint())
					return false;
				else
					genSets.add(gs);
			}		
		}
		return true;
	}
}
