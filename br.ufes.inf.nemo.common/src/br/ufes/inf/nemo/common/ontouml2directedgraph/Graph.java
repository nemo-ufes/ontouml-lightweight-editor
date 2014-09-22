package br.ufes.inf.nemo.common.ontouml2directedgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;

public class Graph {
	
	HashMap<Object, Node> allNodes ;
	OntoUMLParser parser;
	
	public Graph(OntoUMLParser parser) {
		this.allNodes = new HashMap<Object, Node>();
		this.parser = parser;
	}
	
	public Graph() {
		this.allNodes = new HashMap<Object, Node>();
		this.parser = null;
	}
	
	public void createGeneralizationGraph(){
		if(parser==null)
			return;
		
		Set<Classifier> allClassifiers = parser.getAllInstances(Classifier.class);
		
		this.allNodes.clear();
		
		//creates all nodes
		for (Classifier c : allClassifiers) {
			 addNode(new Node(c));
		}
		
		//creates all edges
		for (Classifier c : allClassifiers) {
			 for (Generalization g : parser.retainSelected(c.getGeneralization())) {
				allNodes.get(c).connect(g, allNodes.get(g.getGeneral()));
			}
		}
	}
	
	public void createMeronymicGraph(boolean addPartParents, boolean addPartChildren){
		createMeronymicGraph(parser.getAllInstances(Meronymic.class), addPartParents, addPartChildren);
	}
	
	public void createMeronymicGraph(Set<? extends Meronymic> meronymicList, boolean addPartParents, boolean addPartChildren){
		if(parser==null)
			return;
		
		this.allNodes.clear();
		
		//creates all nodes
		for (Meronymic m : meronymicList) {
			Type whole = OntoUMLParser.getWholeEnd(m).getType();
			Type part = OntoUMLParser.getPartEnd(m).getType();
			
			if(getNode(whole)==null)
				 addNode(new Node(whole));
			if(getNode(part)==null)
				 addNode(new Node(part));
			
			if(addPartParents){
				for (Classifier parent : parser.getAllParents((Classifier)part)) {
					if(getNode(parent)==null)
						 addNode(new Node(parent));
				}
			}
			
			if(addPartChildren){
				for (Classifier child : parser.getAllChildren((Classifier)part)) {
					if(getNode(child)==null)
						 addNode(new Node(child));
				}
			}
		}
		
		//creates all edges
		for (Meronymic m : meronymicList) {
			Type whole = OntoUMLParser.getWholeEnd(m).getType();
			Type part = OntoUMLParser.getPartEnd(m).getType();
			Property partEnd = OntoUMLParser.getPartEnd(m);
			
			getNode(whole).connect(partEnd, getNode(part));
			
			if(addPartParents){
				for (Classifier parent : parser.getAllParents((Classifier)part)) {
					getNode(whole).connect(partEnd, getNode(parent));
				}
			}
			
			if(addPartChildren){
				for (Classifier child : parser.getAllChildren((Classifier)part)) {
					getNode(whole).connect(partEnd, getNode(child));
				}
			}
		}
	}
	
	public void createBidirectionalRelationshipGraph(boolean excludeDerivation, boolean excludeDerived) {
		if(parser==null)
			return;
		
		this.allNodes.clear();
		
		//add all classes as nodes of the graph
		for (Class c : parser.getAllInstances(Class.class)) {
			addNode(new Node(c));
		}
		
		//creates all edges in both directions
		for (Relationship r : parser.getAllInstances(Relationship.class)) {
			try{
				if(excludeDerivation && r instanceof Derivation)
					continue;
			
				if(r instanceof Association){
					Association a = (Association) r;
					
					Property sourceEnd = a.getMemberEnd().get(0);
					Type source = sourceEnd.getType();
					
					Property targetEnd = a.getMemberEnd().get(1);
					Type target =  targetEnd.getType();
					
					if(excludeDerived && (a.isIsDerived() || sourceEnd.isIsDerived() || targetEnd.isIsDerived()))
						continue;
					
					getNode(source).connect(a, getNode(target));
					getNode(target).connect(a, getNode(source));
				}
				
				if(r instanceof Generalization){
					Generalization g = (Generalization) r;
					Classifier child = g.getSpecific();
					Classifier parent = g.getGeneral();
					
					getNode(child).connect(g, getNode(parent));
					getNode(parent).connect(g, getNode(child));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	public Node getNode(Object id){
		return allNodes.get(id);
	}
	
	public void addNode(Node node){
		allNodes.put(node.id, node);
	}

	public ArrayList<Node> getAllNodes() {
		return new ArrayList<Node>(allNodes.values());
	}

	public void getAllPathsFrom(Node node, ArrayList<Node> path, ArrayList<ArrayList<Node>> selectedPaths){
		
		if(!path.contains(node)){
			path.add(node);
			for (DirectedEdge edge : node.edges) {
				getAllPathsFrom(edge.target, new ArrayList<Node>(path), selectedPaths);
			}
		}
		else{
			path.add(node);
		}
		
		if(path.size()>2)
			selectedPaths.add(path);
		
	}
	
	public ArrayList<ArrayList<Node>> getAllPathsFromAllNodes(){
		
		ArrayList<ArrayList<Node>> allPaths = new ArrayList<ArrayList<Node>>();

		for (Node node : allNodes.values()) {
			ArrayList<Node> currentPath = new ArrayList<Node>();
			getAllPathsFrom(node,currentPath,allPaths);
		}
		
		return allPaths;
	}
	
	public void removeDuplicateNodeCycles(ArrayList<ArrayList<Node>> allPaths){
		
		int i = 0;
		while(i<allPaths.size()){
			
			ArrayList<Node> basePath = allPaths.get(i);
			
			if(!isNodeCycle(basePath)){
				i++;
				continue;
			}
			
			int j = i+1;
			while(j<allPaths.size()){
				ArrayList<Node> path = allPaths.get(j);
				
				if(!isNodeCycle(path)){
					j++;
					continue;
				}
				
				if(basePath.containsAll(path) && path.containsAll(basePath)){
					allPaths.remove(path);
				}
				else
					j++;
			}
			i++;
		}
	}
	
	public boolean isNodeCycle(ArrayList<Node> path){
		return path!=null && path.size()>0 && path.get(0).equals(path.get(path.size()-1));
	}
	
	
	
	public void getAllEdgePathsFrom(Node node, EdgePath path, ArrayList<EdgePath> selectedPaths, int minPathSize){
		
		if(!path.containsSourceNode(node)){
			for (DirectedEdge edge : node.edges) {
				EdgePath newPath = new EdgePath(path);
				newPath.addEdge(edge);
				getAllEdgePathsFrom(edge.target, newPath, selectedPaths, minPathSize);
			}
		}
		
		if(path.getEdges().size()>=minPathSize)
			selectedPaths.add(path);
	}
	
	public ArrayList<EdgePath> getAllEdgePathsFromAllNodes(int minPathSize){
		
		ArrayList<EdgePath> allPaths = new ArrayList<EdgePath>();

		for (Node node : allNodes.values()) {
			EdgePath currentPath = new EdgePath();
			getAllEdgePathsFrom(node, currentPath, allPaths, minPathSize);
		}
		
		return allPaths;
	}
	
	public static void retainCycles(ArrayList<EdgePath> edgePaths){
		
		Iterator<EdgePath> iterator = edgePaths.iterator();
		while(iterator.hasNext()) {
			EdgePath path = iterator.next();
			if(!path.isCycle())
				iterator.remove();
		}
	}
	
	public static void removeDuplicateEdgeCycles(ArrayList<EdgePath> allPaths, boolean removeBasedOnEdgeId){
		
		int i = 0;
		while(i<allPaths.size()){
			
			EdgePath basePath = allPaths.get(i);
			System.out.println("==================================");
			System.out.print("BASE PATH:");
			printPath(basePath);

			if(!basePath.isCycle()){
				i++;
				continue;
			}
			
			int j = i+1;
			while(j<allPaths.size()){
				EdgePath path = allPaths.get(j);
				
				if(!path.isCycle()){
					j++;
					continue;
				}
				
				//contains the same edges
				if(basePath.containSameEdges(path, removeBasedOnEdgeId)){
					System.out.print("REMOVED: ");
					printPath(path);
					allPaths.remove(path);
				}
				else
					j++;
			}
			i++;
		}
	}

	private static void printPath(EdgePath path) {
		System.out.print("[");
		
		for (DirectedEdge e : path.edges) {
			if(e.id instanceof EObject)
				System.out.print(OntoUMLNameHelper.getTypeAndName((EObject) e.id, true, true)+", ");
		}
		
		System.out.println("]");
	}
	
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		for (Integer i = 0; i <= 6; i++) {
			graph.addNode(new Node(i));
		}
		
		graph.getAllNodes().get(0).connect("0->1",graph.getAllNodes().get(1));
		graph.getAllNodes().get(0).connect("0->2",graph.getAllNodes().get(2));
		graph.getAllNodes().get(1).connect("1->3",graph.getAllNodes().get(3));
		graph.getAllNodes().get(1).connect("1->4",graph.getAllNodes().get(4));
		graph.getAllNodes().get(2).connect("2->5",graph.getAllNodes().get(5));
		graph.getAllNodes().get(5).connect("5->3",graph.getAllNodes().get(3));
		graph.getAllNodes().get(3).connect("3->6",graph.getAllNodes().get(6));
		graph.getAllNodes().get(6).connect("6->0",graph.getAllNodes().get(0));
		
//		ArrayList<ArrayList<ObjectNode>> allPaths = graph.getAllPathsFromAllNodes();
//		System.out.println("#Paths: "+allPaths.size());
//		for (ArrayList<ObjectNode> path : allPaths) {
//			System.out.println(path);
//		}
//		
//		System.out.println("Removing duplicates... ");
//		
//		graph.removeDuplicateCycles(allPaths);
//		System.out.println("#Paths: "+allPaths.size());
//		for (ArrayList<ObjectNode> path : allPaths) {
//			System.out.println(path);
//		}
		
		System.out.println("#Nodes: "+graph.allNodes.values().size());
		
		ArrayList<EdgePath> allEdgePaths = graph.getAllEdgePathsFromAllNodes(1);
		System.out.println("#Paths: "+allEdgePaths.size());
		
		for (EdgePath path : allEdgePaths) {
			System.out.println(path.edges);
		}
		
		retainCycles(allEdgePaths);
		System.out.println("#Cycles: "+allEdgePaths.size());
		
		for (EdgePath cycle : allEdgePaths) {
			System.out.println(cycle.edges);
		}
		
		removeDuplicateEdgeCycles(allEdgePaths, false);
		System.out.println("#No Duplicate Cycles: "+allEdgePaths.size());
		
		for (EdgePath cycle : allEdgePaths) {
			System.out.println(cycle.edges);
		}
	}

	
	
	
	
}
