package br.ufes.inf.nemo.assistant.astah2graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.graph.NodeAction;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.manager.ManagerPattern;
import br.ufes.inf.nemo.assistant.util.ActionEnum;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;
import br.ufes.inf.nemo.assistant.wizard.WizardAssitant;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewClass;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewPhase;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewRelator;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IActivity;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IActivityNode;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IFlow;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

public class SWTAstahParser {

	public static void main(String[] args) {
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashTree = doParser("src/Patterns.asta");

		GraphAssistant graph = hashTree.get(StereotypeOntoUMLEnum.KIND);
		graph.updateNodeList();
		
		System.out.println(graph.getNodeList().size());
		
		WizardDialog wizardDialog = new WizardDialog(new Shell(),new WizardAssitant(graph));
		if (wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
		} else {
			System.out.println("Cancel pressed");
		}
	}

	/*public static HashMap<StereotypeOntoUMLEnum, GraphAssistant> doParser(InputStream astahFile){
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashTree = new HashMap<>();
		try{
			ProjectAccessor prjAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			prjAccessor.open(astahFile);

			// Get a project model
			IModel project = prjAccessor.getProject();

			ArrayList<IActivityDiagram> lst = getActivityDiagram(project);

			//Interate on all activity diagrams
			for (IActivityDiagram activityDiagram : lst) {
				IActivity root = activityDiagram.getActivity();
				IActivityNode[] nodes = root.getActivityNodes();

				//for each diagram, create a tree
				hashTree.put(StereotypeOntoUMLEnum.valueOf(root.getName().toUpperCase()),processNodes(nodes));
			}		

			//For each link to another pattern
			for (Map.Entry<StereotypeOntoUMLEnum, Node> entry : linkNode.entrySet()) {
				//All link node has its next set to the start node from the patter destiny
				entry.getValue().setNext(hashTree.get(entry.getKey()).getStart());
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return hashTree;
	}*/

	public static HashMap<StereotypeOntoUMLEnum, GraphAssistant> doParser(String astahFile){

		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashTree = new HashMap<>();
		try{
			ProjectAccessor prjAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			prjAccessor.open(astahFile, true, false, true);

			// Get a project model
			IModel project = prjAccessor.getProject();

			ArrayList<IActivityDiagram> lst = getActivityDiagram(project);

			//Interate on all activity diagrams
			for (IActivityDiagram activityDiagram : lst) {
				IActivity root = activityDiagram.getActivity();
				IActivityNode[] nodes = root.getActivityNodes();

				//for each diagram, create a tree
				if(root.getName().equals("Kind"))
					hashTree.put(StereotypeOntoUMLEnum.valueOf(root.getName().toUpperCase()),processNodes(nodes));
			}		

			//For each link to another pattern
			for (Map.Entry<StereotypeOntoUMLEnum, NodeAssistant> entry : linkNode.entrySet()) {
				//All link node has its next set to the start node from the patter destiny
				entry.getValue().setNextNode(hashTree.get(entry.getKey()).getStartNode());
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return hashTree;
	}

	//Usado para verificar os nodos criados
	private static HashMap<IActivityNode,NodeAssistant> hashNode;
	private static HashMap<StereotypeOntoUMLEnum,NodeAssistant> linkNode = new HashMap<>();

	private static GraphAssistant processNodes(IActivityNode[] nodes){
		GraphAssistant graph = new GraphAssistant();
		ManagerPattern pattern = new ManagerPattern();

		graph.setManagerPattern(pattern);

		//Limpa o hashNode a cada diagrama
		hashNode = new HashMap<IActivityNode,NodeAssistant>();

		for (IActivityNode node : nodes) {
			if(node.getTaggedValue("stereotype").equalsIgnoreCase("START")){
				//Start to instantiate the node's tree	
				for (IFlow flow : node.getOutgoings()) {
					NodeAssistant startNode = processNode(flow.getTarget(), graph);
					graph.setStartNode(startNode);
				}
			}
		}	

		return graph;
	}

	/**
	 * Both variables are used to connect a pattern with another one.
	 * */	
	private static NodeAssistant _lastNode;

	/**
	 * Process node, add to the tree and, recursively, process the next nodes
	 * return node created 
	 * */
	public static NodeAssistant processNode(IActivityNode aNode, GraphAssistant graph){
		if(aNode.getTaggedValue("stereotype").equalsIgnoreCase("END")){
			return null;
		}
		
		//To avoid duplicity
		if(hashNode.get(aNode) != null){
			return hashNode.get(aNode);
		}

		NodeAssistant node = new NodeAssistant(graph);

		//Process which window is each activity
		String window = aNode.getTaggedValue("window");
		if(window.equalsIgnoreCase("NewClass")){
			NewClass nc = new NewClass();
			String stereotypes = aNode.getTaggedValue("stereotypes");
			nc.setStereotypes(stereotypes.split(","));
			node.setPage(nc);
		}else if(window.equalsIgnoreCase("Action")){
			NodeAction nodeAction = new NodeAction(graph);
			nodeAction.setAction(ActionEnum.valueOf(aNode.getTaggedValue("action").toUpperCase()));
			node = nodeAction;
		}else if(window.equalsIgnoreCase("Question")){
			Question q = new Question();
			q.setQuestion(aNode.getTaggedValue("question"));
			node.setPage(q);
		}else if(window.equalsIgnoreCase("NewPhases")){
			NewPhase np = new NewPhase();
			node.setPage(np);
		}else if(window.equalsIgnoreCase("NewRelator")){
			NewRelator nr = new NewRelator();
			node.setPage(nr);
		}else if(window.equalsIgnoreCase("LinkToPattern")){
			//toUpperCase is used because all enumerations are in upper case
			linkNode.put(StereotypeOntoUMLEnum.valueOf(aNode.getTaggedValue("linkToPattern").toUpperCase()), _lastNode);
		}else if(window.equalsIgnoreCase("NewGeneralizationSet")){
			NewGeneralizationSet ngs = new NewGeneralizationSet();
			String stereotypes = aNode.getTaggedValue("stereotypes");
			ngs.setStereotypes(stereotypes.split(","));

//			if(!aNode.getTaggedValue("generalizationSetFilter").isEmpty()){
//				if(aNode.getTaggedValue("generalizationSetFilter").equalsIgnoreCase("_specificsPhase")){
//					//usa o filtro
//					ngs.setGeneralClasses(tree.getManagerPatern().getGeneralClasses(stereotypes.split(","),aNode.getTaggedValue("generalizationSetFilter")));
//				}	
//			}else{//Se nao tiver filtro para os valores
//				ngs.setGeneralClasses(tree.getManagerPatern().getGeneralClasses(stereotypes.split(",")));
//			}
//			if(!aNode.getTaggedValue("editableMetaProperties").isEmpty()){
//				if(aNode.getTaggedValue("editableMetaProperties").equalsIgnoreCase("false")){
//					//usa o filtro
//					ngs.setEditableMetaProperties(false);
//				}	
//			}
			node.setPage(ngs);
		}
		if(!node.isAction())
			System.out.println(node.getPage().getName());
		
		//Keep the last node in memory
		_lastNode = node;			

		//To verify duplicity
		hashNode.put(aNode, node);		

		//Process next nodes recursively
		for (IFlow flow : aNode.getOutgoings()) {
			if(!flow.getTaggedValue("stereotype").isEmpty()){
				if(flow.getTaggedValue("value").equalsIgnoreCase("true")){
					node.setTrueNode(processNode(flow.getTarget(),graph));
				}else if(flow.getTaggedValue("value").equalsIgnoreCase("false")){
					node.setFalseNode(processNode(flow.getTarget(),graph));
				}
			}else{
				node.setNextNode(processNode(flow.getTarget(),graph));
			}
		}		
		return node;
	}

	/**
	 * Get all Activity Diagrams
	 * */
	public static ArrayList<IActivityDiagram> getActivityDiagram(IPackage iPackage) {
		ArrayList<IActivityDiagram> activityDiagrams = new ArrayList<IActivityDiagram>();

		IDiagram[] dgms = iPackage.getDiagrams();
		for (int i = 0; i < dgms.length; i++) {
			IDiagram dgm = dgms[i];
			if (dgm instanceof IActivityDiagram 
					&& !((IActivityDiagram )dgm).isFlowChart()) {
				activityDiagrams.add((IActivityDiagram)dgm);
			}
		}
		return activityDiagrams;
	}
}
