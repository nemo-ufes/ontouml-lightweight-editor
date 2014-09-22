package br.ufes.inf.nemo.validator.meronymic.forbidden;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.DirectedEdge;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ui.ForbiddenTableModel;

public class ForbiddenComponentOfTask extends ForbiddenTask<componentOf>{

	public ForbiddenComponentOfTask(OntoUMLParser parser, ForbiddenTableModel tableModel, JTabbedPane tabbedPane) {
		super(parser, tableModel);
		existing.addAll(parser.getAllInstances(componentOf.class));
	}
	
	public void findForbiddenComponentOf(){
		
		//creates a node for each type related by a componentOf and an directed edge from the whole
		//to the part. also creates a node for each child and parent of each part; finally, creates an
		//edge from the whole to the parent and child part type.
		if(!arePathsSet())
			setPaths(true, true);
		
		ArrayList<EdgePath> indirectPaths = new ArrayList<EdgePath>();
		System.out.println("Intransitive ComponentOf: Identifying indirect functional parthood paths...");
		//selects the paths which are indirect (ex: a wholeOf b; b' subtype of b; b' whole of c; path=a->b'->c)
		for (EdgePath path : getPaths()) {
			if(!path.isCycle() && path.edges()>1){
				for (DirectedEdge de : path.getEdges()) {
					if(!de.getTarget().getId().equals(((Property) de.getId()).getType())){
						indirectPaths.add(path);
						break;
					}
				}
			}
		}
		
		System.out.println("Intransitive ComponentOf: Identifying alternative paths between functinal parthoods...");
		for (componentOf cp : parser.getAllInstances(componentOf.class)) {
			Type whole = OntoUMLParser.getWholeEnd(cp).getType();
			Type part = OntoUMLParser.getPartEnd(cp).getType();
			
			for (EdgePath path : indirectPaths) {
				Object firstNode = path.getNodeIds(true).get(0);
				Object lastNode = path.getNodeIds(true).get(path.nodes()-1);
				
				
				if(firstNode.equals(whole) && lastNode.equals(part)){
					ForbiddenComponentOf fcp = new ForbiddenComponentOf(cp, parser);
					fcp.setPath(path.getEdgeIdsOfType(Property.class));
					fcp.setNodes(path.getNodeIdsOfType(Classifier.class, true));
					fcp.classifyPath();
					forbidden.add(fcp);
					publish(fcp);
				}
			}
		}
		
	}
		
	@Override
	protected Boolean doInBackground() throws Exception {
		System.out.println("Intransitive ComponentOf: Analyzing model...");
		System.out.println("Intransitive ComponentOf: Creating meronymic graph...");
		setPaths(true, true);
		System.out.println("Intransitve ComponentOf: Analyzing graph...");
		findForbiddenComponentOf();
		setProgress(50);
		return true;
	}
	
	@Override
	protected void done() {
		System.out.println("Intransitve ComponentOf: Analysis successfully concluded!");
		System.out.println("Intransitve ComponentOf: "+forbidden.size()+" forbidden relations found!");

	}
	
}
