package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.graph.Graph;

public class HierarchyCycleChecker {

	OntoUMLParser parser;
	ArrayList<ArrayList<Classifier>> hierarchyCycles;
	
	public HierarchyCycleChecker(OntoUMLParser parser) {
		this.parser = parser;
		hierarchyCycles = null;
	}
	
	public boolean check(){
		
		if(hierarchyCycles==null)
			hierarchyCycles = new ArrayList<ArrayList<Classifier>>();
		else
			hierarchyCycles.clear();
		
		Graph genGraph = new Graph();
		//creates directed graph with classes and generalizations
		genGraph.createGeneralizationGraph(parser);
		
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes();
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths);
	
		for (EdgePath cycle : allPaths) {
			hierarchyCycles.add(cycle.getNodeIdsOfType(Classifier.class));
		}	
		
		if(hierarchyCycles.size()==0)
			return false;
		
		return true;
	}
	
	public ArrayList<ArrayList<Classifier>> getCycles(){
		if(hierarchyCycles==null)
			check();
		
		return hierarchyCycles;
	}
	
	public static String errorType(){
		return "Generalization Cycle";
	}
	
	public String getCycleStringById(int i){
		if(hierarchyCycles==null)
			check();
		
		if(i>=hierarchyCycles.size())
			return "";
		
		String result = "";
		for (Classifier c : hierarchyCycles.get(i)) {
			result += c.getName()+", ";
		}
		
		return result;
	}
	
}
