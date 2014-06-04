package br.ufes.inf.nemo.meronymic_validation.checkers;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.graph.Graph;

public class MeronymicCycleChecker extends Checker<ArrayList<Classifier>>{
	
	public MeronymicCycleChecker(OntoUMLParser parser) {
		super(parser);
	}
	
	@Override
	public boolean check(){
		
		if(errors==null)
			errors = new ArrayList<ArrayList<Classifier>>();
		else
			errors.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph();
		
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes();
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths);
	
		for (EdgePath cycle : allPaths) {
			errors.add(cycle.getNodeIdsOfType(Classifier.class));
		}	
		
		if(errors.size()>0)
			return false;
		
		return true;
	}

	@Override
	public String getErrorDescription(int i){
		if(errors==null)
			check();
		
		if(i>=errors.size())
			return "";
		
		String result = "";
		for (Classifier c : errors.get(i)) {
			result += c.getName()+", ";
		}
		
		return result;
	}

	@Override
	public String getErrorType(int i) {
		return "Part-Whole Cycle";
	}
	
}
