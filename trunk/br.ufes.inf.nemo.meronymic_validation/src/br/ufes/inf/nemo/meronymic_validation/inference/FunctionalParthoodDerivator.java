package br.ufes.inf.nemo.meronymic_validation.inference;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.graph.Graph;

public class FunctionalParthoodDerivator {

	private OntoUMLParser parser;
	private Set<componentOf> existingComponentOf;
	private ArrayList<EdgePath> componentOfPaths;
	private ArrayList<DerivedMeronymic> derivedType1;
	private ArrayList<DerivedMeronymic> derivedType2;
	private ArrayList<DerivedMeronymic> derivedType3;
	
	public FunctionalParthoodDerivator(OntoUMLParser parser) {
		this.parser = parser;
		existingComponentOf = parser.getAllInstances(componentOf.class);
		componentOfPaths = null;
		derivedType1 = new ArrayList<DerivedMeronymic>();
		derivedType2 = new ArrayList<DerivedMeronymic>();
		derivedType3 = new ArrayList<DerivedMeronymic>();
	}
	
	public ArrayList<DerivedMeronymic> getInferedType1() {
		return derivedType1;
	}

	public ArrayList<DerivedMeronymic> getInferedType2() {
		return derivedType2;
	}

	public ArrayList<DerivedMeronymic> getInferedType3() {
		return derivedType3;
	}
	
	public void setPropertyPaths(){
		
		if(componentOfPaths==null)
			componentOfPaths = new ArrayList<EdgePath>();
		else
			componentOfPaths.clear();
		
		Graph genGraph = new Graph();
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(existingComponentOf);
		
		//get all paths in the graph
		componentOfPaths = genGraph.getAllEdgePathsFromAllNodes();
				
	}
	
	public void deriveDirectFunctionalParthoodType1(){

		if(componentOfPaths==null)
			setPropertyPaths();
		
		for (EdgePath path : componentOfPaths) {
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			derivedType1.add(createDerivedComponentOf(path, whole, part));
		}
	}
	
	public void deriveDirectFunctionalParthoodType2(){

		if(componentOfPaths==null)
			setPropertyPaths();
		
		for (EdgePath path : componentOfPaths) {
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			for (Classifier partChild : parser.getAllChildren(part)) {
				derivedType2.add(createDerivedComponentOf(path, whole, partChild));
			}
		}
	}
	
	public void deriveDirectFunctionalParthoodType3(){

		if(componentOfPaths==null)
			setPropertyPaths();
		
		for (EdgePath path : componentOfPaths) {
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			for (Classifier wholeChild : parser.getAllChildren(whole)) {
				derivedType3.add(createDerivedComponentOf(path, wholeChild, part));
			}
		}
	}

	private DerivedMeronymic createDerivedComponentOf(EdgePath path, Classifier whole,	Classifier part) {
		
		DerivedMeronymic derived;
		componentOf existingComponentOf;
		
		existingComponentOf = getDirectFunctionalPartOf(whole, part);
		if(existingComponentOf!=null)
			derived = new DerivedMeronymic(existingComponentOf);
		else{
			existingComponentOf = getDirectFunctionalPartOf(part, whole);
			if(existingComponentOf!=null)
				derived = new DerivedMeronymic(existingComponentOf);
			else
				derived = new DerivedMeronymic(RelationStereotype.COMPONENTOF);
		}
		
		derived.setDerivationPath(path.getEdgeIdsOfType(Property.class));
		derived.setDerived(true);
		
		derived.setWhole(whole);
		derived.setPart(part);
		derived.setName("dfpType1_"+whole.getName().trim()+"_"+part.getName().trim());
		
		derived.setWholeEnd(whole.getName().trim().toLowerCase());
		derived.setPartEnd(part.getName().trim().toLowerCase());
		
		derived.setEndsMetaPropertiesFromPath();
		
		derived.setAllowed(true);
		
		return derived;
	}

	private componentOf getDirectFunctionalPartOf (Classifier whole, Classifier part){
		
		for (componentOf cp : existingComponentOf){
			if (parser.getWholeEnd(cp).getType().equals(whole) && parser.getPartEnd(cp).getType().equals(part))
				return cp;
		}
						
		return null;
	}
	
	
	
	
	
	
}
