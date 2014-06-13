package br.ufes.inf.nemo.meronymic_validation.forbidden;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.DirectedEdge;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.userinterface.ForbiddenTableModel;

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
		
		System.out.println("####################\nPATHS!");
		
		for (EdgePath path : paths) {
			for (Object c : path.getNodeIds()) {
				System.out.print(OntoUMLNameHelper.getName((EObject) c)+" -> ");
			}
			System.out.println();
		}
		
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
		
		System.out.println("####################\nINDIRECT PATHS!");
		
		for (EdgePath path : indirectPaths) {
			for (Object c : path.getNodeIds()) {
				System.out.print(OntoUMLNameHelper.getName((EObject) c)+" -> ");
			}
			System.out.println();
		}
		
		System.out.println("Intransitive ComponentOf: Identifying alternative paths between functinal parthoods...");
		for (componentOf cp : parser.getAllInstances(componentOf.class)) {
			Type whole = OntoUMLParser.getWholeEnd(cp).getType();
			Type part = OntoUMLParser.getPartEnd(cp).getType();
			
			for (EdgePath path : indirectPaths) {
				Object firstNode = path.getNodeIds().get(0);
				Object lastNode = path.getNodeIds().get(path.nodes()-1);
				
				System.out.println("Whole: "+OntoUMLNameHelper.getName(whole)
						+" Part: "+OntoUMLNameHelper.getName(part)
						+" First: "+OntoUMLNameHelper.getName((EObject) firstNode)
						+" Second: "+OntoUMLNameHelper.getName((EObject) lastNode)
						+" EQUALS: "+(firstNode.equals(whole) && lastNode.equals(part)));
				
				if(firstNode.equals(whole) && lastNode.equals(part)){
					ForbiddenComponentOf fcp = new ForbiddenComponentOf(cp);
					fcp.setPath(path.getEdgeIdsOfType(Property.class));
					fcp.setNodes(path.getNodeIdsOfType(Classifier.class));
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
		setProgress(1);
		System.out.println("Intransitive ComponentOf: Creating meronymic graph...");
		setPaths(true, true);
		setProgress(40);
		System.out.println("Intransitve ComponentOf: Analyzing graph...");
		findForbiddenComponentOf();
		setProgress(100);
		return true;
	}
	
	@Override
	protected void done() {
		System.out.println("Intransitve ComponentOf: Analysis successfully concluded!");
		System.out.println("Intransitve ComponentOf: "+forbidden.size()+" forbidden relations found!");

	}
	
}
