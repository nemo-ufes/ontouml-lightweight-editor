package br.ufes.inf.nemo.assistant.astah2graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufes.inf.nemo.assistant.graph.Node;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.manager.ManagerPattern;
import br.ufes.inf.nemo.assistant.util.ActionEnum;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IActivity;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IActivityNode;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IFlow;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

import br.ufes.inf.nemo.assistant.window.Action;
import br.ufes.inf.nemo.assistant.window.Alert;
import br.ufes.inf.nemo.assistant.window.NewClass;
import br.ufes.inf.nemo.assistant.window.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.window.NewPhases;
import br.ufes.inf.nemo.assistant.window.NewRelator;
import br.ufes.inf.nemo.assistant.window.Question;

/*
 * 
 * COLOCAR NO NewGeneralizationSet QUE TODOS OS GS SAO DE UM TIPO ESPECIFICO (generalizationSetFilter)
 * e que tem que vir as todas as classes do stereotypes e que elas podem não ter gs
 * 
 * */

/*
 * Criar uma janela que mostre as associacoes de uma classe (usar para o relator, quando tem member-ends < 2)
 * */
public class AstahParser {

	public static void main(String[] args) {
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashTree = doParser("./Patterns.asta");

		GraphAssistant tree = hashTree.get(StereotypeOntoUMLEnum.SUBKIND);

		tree.print(tree.getStart());

		try{
			tree.getStart().run();
		}catch(Exception e){
			e.printStackTrace();
		}			
	}

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
	}

	//Usado para verificar os nodos criados
	private static HashMap<IActivityNode,Node> hashNode;
	private static HashMap<StereotypeOntoUMLEnum,Node> linkNode = new HashMap<>();

	private static GraphAssistant processNodes(IActivityNode[] nodes){
		GraphAssistant tree = new GraphAssistant();
		ManagerPattern pattern = new ManagerPattern();

		tree.setManagerPatern(pattern);

		//Limpa o hashNode a cada diagrama
		hashNode = new HashMap<IActivityNode,Node>();

		for (IActivityNode node : nodes) {
			if(node.getTaggedValue("stereotype").equalsIgnoreCase("START")){
				//Start to instantiate the node's tree	
				for (IFlow flow : node.getOutgoings()) {
					Node startNode = processNode(flow.getTarget(), tree);
					tree.setStart(startNode);
				}
			}
		}	

		return tree;
	}

	/**
	 * Both variables are used to connect a pattern with another one.
	 * */	
	private static Node _lastNode;

	/**
	 * Process node, add to the tree and, recursively, process the next nodes
	 * return node created 
	 * */
	public static Node processNode(IActivityNode aNode, GraphAssistant tree){
		if(aNode.getTaggedValue("stereotype").equalsIgnoreCase("END")){
			return null;
		}

		//To avoid duplicity
		if(hashNode.get(aNode) != null){
			return hashNode.get(aNode);
		}

		Node node = new Node(tree);

		//Process which window is each activity
		String window = aNode.getTaggedValue("window");
		if(window.equalsIgnoreCase("NewClass")){
			NewClass nc = new NewClass();
			String stereotypes = aNode.getTaggedValue("stereotypes");
			nc.setStereotypes(stereotypes.split(","));
			node.setWin(nc);
		}else if(window.equalsIgnoreCase("Action")){
			Action a = new Action();
			a.setAction(ActionEnum.valueOf(aNode.getTaggedValue("action").toUpperCase()));

			if(!aNode.getTaggedValue("connectFilter").isEmpty()){
				//usa o filtro
				a.addFilter(aNode.getTaggedValue("connectFilter"));
			}

			node.setWin(a);
		}else if(window.equalsIgnoreCase("NewGeneralizationSet")){
			NewGeneralizationSet ngs = new NewGeneralizationSet();

			if(aNode.getTaggedValue("getConceptName").equalsIgnoreCase("true")){
				ngs.autoGetConceptName(true);
			}

			String stereotypes = aNode.getTaggedValue("stereotypes");

			if(!aNode.getTaggedValue("generalizationSetFilter").isEmpty()){
				if(aNode.getTaggedValue("generalizationSetFilter").equalsIgnoreCase("_specificsPhase")){
					//usa o filtro
					ngs.setGeneralClasses(tree.getManagerPatern().getGeneralClasses(stereotypes.split(","),aNode.getTaggedValue("generalizationSetFilter")));
				}	
			}else{//Se nao tiver filtro para os valores
				ngs.setGeneralClasses(tree.getManagerPatern().getGeneralClasses(stereotypes.split(",")));
			}
			if(!aNode.getTaggedValue("editableMetaProperties").isEmpty()){
				if(aNode.getTaggedValue("editableMetaProperties").equalsIgnoreCase("false")){
					//usa o filtro
					ngs.setEditableMetaProperties(false);
				}	
			}
			node.setWin(ngs);
		}else if(window.equalsIgnoreCase("Question")){
			Question q = new Question();
			q.setQuestion(aNode.getTaggedValue("question"));
			node.setWin(q);
		}else if(window.equalsIgnoreCase("Alert")){
			Alert a = new Alert();
			a.setAlert(aNode.getTaggedValue("alert"));
			node.setWin(a);
		}else if(window.equalsIgnoreCase("NewPhases")){
			NewPhases np = new NewPhases();
			node.setWin(np);
		}else if(window.equalsIgnoreCase("NewRelator")){
			NewRelator nr = new NewRelator();
			node.setWin(nr);
		}else if(window.equalsIgnoreCase("LinkToPattern")){
			//toUpperCase is used because all enumerations are in upper case
			linkNode.put(StereotypeOntoUMLEnum.valueOf(aNode.getTaggedValue("linkToPattern").toUpperCase()), _lastNode);
		}
		
		//Keep the last node in memory
		_lastNode = node;			

		//To verify duplicity
		hashNode.put(aNode, node);		

		//Process next nodes recursively
		for (IFlow flow : aNode.getOutgoings()) {
			if(!flow.getTaggedValue("stereotype").isEmpty()){
				if(flow.getTaggedValue("value").equalsIgnoreCase("true")){
					node.setGoTrue(processNode(flow.getTarget(),tree));
				}else if(flow.getTaggedValue("value").equalsIgnoreCase("false")){
					node.setGoFalse(processNode(flow.getTarget(),tree));
				}
			}else{
				node.setNext(processNode(flow.getTarget(),tree));
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
