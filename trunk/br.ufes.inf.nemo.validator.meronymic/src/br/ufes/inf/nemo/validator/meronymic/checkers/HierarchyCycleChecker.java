package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;

public class HierarchyCycleChecker extends Checker<HierarchyCycleError> {
	
	public HierarchyCycleChecker(OntoUMLParser parser) {
		super(parser);
	}
	
	@Override
	public boolean check(){
		
		errors.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and generalizations
		genGraph.createGeneralizationGraph();
		
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes(1);
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths, false);
	
		for (EdgePath cycle : allPaths) {
			errors.add(new HierarchyCycleError(parser, cycle.getNodeIdsOfType(Classifier.class, true), cycle.getEdgeIdsOfType(Generalization.class)));
		}	
		
		if(errors.size()==0)
			return true;
		
		return false;
	}

	@Override
	public String checkerName() {
		return "Hierarchy Cycles";
	}
	
	
	
}
