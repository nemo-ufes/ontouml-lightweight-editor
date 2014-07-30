package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

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
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes();
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths);
	
		for (EdgePath cycle : allPaths) {
			errors.add(new HierarchyCycleError(parser, cycle.getNodeIdsOfType(Classifier.class), cycle.getEdgeIdsOfType(Generalization.class)));
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
