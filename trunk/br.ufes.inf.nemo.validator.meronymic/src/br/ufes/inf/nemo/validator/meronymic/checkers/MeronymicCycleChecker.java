package br.ufes.inf.nemo.validator.meronymic.checkers;

import java.util.ArrayList;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;

public class MeronymicCycleChecker extends Checker<MeronymicCycleError>{
	
	public MeronymicCycleChecker(OntoUMLParser parser) {
		super(parser);
	}
	
	@Override
	public boolean check(){
		
		errors.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(true, true);
		
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes(1);
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths, false);
	
		for (EdgePath cycle : allPaths) {
			errors.add( new MeronymicCycleError(parser, cycle.getEdgeIdsOfType(Property.class)));
		}	
		
		if(errors.size()>0)
			return false;
		
		return true;
	}

	@Override
	public String checkerName() {
		return "Meronymic Cycle";
	}

	
	
}
