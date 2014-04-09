package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.graph.Graph;

public class MeronymicCycleChecker {

	OntoUMLParser parser;
	ArrayList<ArrayList<Classifier>> meronymicCycles;
	
	public MeronymicCycleChecker(OntoUMLParser parser) {
		this.parser = parser;
		meronymicCycles = null;
	}
	
	public boolean check(){
		
		if(meronymicCycles==null)
			meronymicCycles = new ArrayList<ArrayList<Classifier>>();
		else
			meronymicCycles.clear();
		
		Graph genGraph = new Graph();
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(parser);
		
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes();
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths);
	
		for (EdgePath cycle : allPaths) {
			meronymicCycles.add(cycle.getNodeIdsOfType(Classifier.class));
		}	
		
		if(meronymicCycles.size()==0)
			return false;
		
		return true;
	}
	
	public ArrayList<ArrayList<Classifier>> getCycles(){
		if(meronymicCycles==null)
			check();
		
		return meronymicCycles;
	}
	
}
