package br.ufes.inf.nemo.validator.meronymic.forbidden;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingWorker;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.DirectedEdge;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Node;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ui.ForbiddenTableModel;

public abstract class ForbiddenTask <T extends Meronymic> extends SwingWorker<Boolean, ForbiddenMeronymic<T>>{

	protected OntoUMLParser parser;
	protected Set<T> existing;
	protected ArrayList<EdgePath> paths;
	protected ArrayList<ForbiddenMeronymic<T>> forbidden;
	private boolean arePathsSet;
	ForbiddenTableModel tableModel;
	
	public ForbiddenTask(OntoUMLParser parser, ForbiddenTableModel tableModel) {
		this.parser = parser;
		this.tableModel = tableModel;
		
		existing = new HashSet<T>();
		paths = new ArrayList<EdgePath>();
		forbidden = new ArrayList<ForbiddenMeronymic<T>>();
		arePathsSet = false;
	}
	
	public OntoUMLParser getParser() {
		return parser;
	}

	public Set<T> getExisting() {
		return existing;
	}

	public ArrayList<EdgePath> getPaths() {
		return paths;
	}

	public ForbiddenTableModel getTable() {
		return tableModel;
	}

	public ArrayList<ForbiddenMeronymic<T>> getForbidden() {
		return forbidden;
	}
	
	
	public boolean arePathsSet() {
		return arePathsSet;
	}

	public void setPaths(boolean addPartChildren, boolean addPartParents){
		
		paths.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(existing, addPartParents, addPartChildren);
		
		for (Node node : genGraph.getAllNodes()) {
			for (DirectedEdge egde : node.getEdges()) {
				System.out.println("(Edge) "+egde.getSource().toString()+" -> "+egde.getTarget().toString());
			}
		}
		//get all paths in the graph
		paths = genGraph.getAllEdgePathsFromAllNodes(1);
		
		arePathsSet = true;
	}
	
	protected <M extends Meronymic> Set<M> getDirect(Classifier whole, Classifier part, Class<M> stereotype){
		
		Set<M> result = new HashSet<M>();
		
		for (Meronymic meronymic : existing){
			if(stereotype.isInstance(meronymic) && OntoUMLParser.getWholeEnd(meronymic).getType().equals(whole) && OntoUMLParser.getPartEnd(meronymic).getType().equals(part))
				result.add(stereotype.cast(meronymic));
		}
						
		return result;
	}

	@Override
	protected void process(final List<ForbiddenMeronymic<T>> result) {
		for (ForbiddenMeronymic<T> forbiddenMeronymic : result) {
			tableModel.addRow(forbiddenMeronymic);
		}
	}
}
