package br.ufes.inf.nemo.antipattern.asscyc;

import java.util.ArrayList;
import java.util.HashMap;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;
import br.ufes.inf.nemo.common.ontouml2graph.GraphAlgo;
import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;

public class AssCycAntipattern extends Antipattern<AssCycOccurrence> {

	public AssCycAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public AssCycAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final AntipatternInfo info = new AntipatternInfo("Association Cycle", 
			"AssCyc", 
			"This anti-pattern occurs when an arbitrary number of types are connected through the same number of " +
			"relations in a way that composes a cycle (in the same meaning defined in Graph Theory). " +
			"In other words, one can start navigating relations from any type in the cycle and arrive back to the " +
			"starting point without going through the same relation and visiting the same type more than once (except the first/last).",
			null);
	private HashMap<MaterialAssociation, ArrayList<Relator>> mat2RelHash; 
	
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	public AntipatternInfo info(){
		return info;
	}

	public ArrayList<AssCycOccurrence> identifyAlternative() {
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createBidirectionalRelationshipGraph(true,true);
		//get all paths in the graph
		ArrayList<EdgePath> allPaths = genGraph.getAllEdgePathsFromAllNodes(3);
		//only keep paths that are cycles
		Graph.retainCycles(allPaths);
		//remove cycles which contain the very same edges (ignoring the order of the edges)
		Graph.removeDuplicateEdgeCycles(allPaths, true);
	
		//required for method isRelatorPattern to work
		createDerivationHash();
		
		for (EdgePath cycle : allPaths) {
			try {
				ArrayList<Relationship> relCycle = cycle.getEdgeIdsOfType(Relationship.class);
				
				if(relCycle.size()<=2)
					continue;
				
				if(isRelatorPattern(relCycle))
					continue;
				
				occurrence.add(new AssCycOccurrence(cycle.getNodeIdsOfType(RefOntoUML.Class.class, false),relCycle,this));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return occurrence;
	}
	
	private boolean isRelatorPattern(ArrayList<Relationship> relCycle) throws Exception{
		
		if(relCycle.size()!=3)
			return false;
		
		if(mat2RelHash==null)
			createDerivationHash();
		
		MaterialAssociation mat = null;
		Mediation m1 = null, m2 = null;
		
		for (Relationship r : relCycle) {
			if(r instanceof MaterialAssociation){
				if(mat==null)
					mat = (MaterialAssociation) r;
				else
					return false;
			}
			
			if(r instanceof Mediation){
				if(m1==null)
					m1 = (Mediation) r;
				else if (m2==null)
					m2 = (Mediation) r;
			}
		}
		if(mat==null || m1==null || m2==null) 
			return false;
		
		Type 	mediatedM1 = OntoUMLParser.getMediatedType(m1), 
				mediatedM2 =  OntoUMLParser.getMediatedType(m2), 
				relatorM1 = OntoUMLParser.getRelator(m1), 
				relatorM2 = OntoUMLParser.getRelator(m2);
		
		Type 	matSource = mat.getMemberEnd().get(0).getType(),
				matTarget = mat.getMemberEnd().get(1).getType();
		
		if(relatorM1.equals(relatorM2) && !mediatedM1.equals(mediatedM2) && 
				mat2RelHash.containsKey(mat) && mat2RelHash.get(mat).contains(relatorM1) &&
				((matSource.equals(mediatedM1) && matTarget.equals(mediatedM2)) || (matSource.equals(mediatedM2) && matTarget.equals(mediatedM1)) ) )
			return true;
		
		return false;

	}

	private void createDerivationHash(){
		mat2RelHash = new HashMap<MaterialAssociation,ArrayList<Relator>>();
		
		for (Derivation d : parser.getAllInstances(Derivation.class)) {
			MaterialAssociation material = OntoUMLParser.getMaterial(d);			
			Relator relator = OntoUMLParser.getRelator(d);
			
			if(mat2RelHash.containsKey(material)) {	
				mat2RelHash.get(material).add(relator);
			} else{
				ArrayList<Relator> list = new ArrayList<Relator>();
				list.add(relator);
				mat2RelHash.put(material,list);
			}	
		}
	}
	
	@Override
	public ArrayList<AssCycOccurrence> identify() {
			
		int aux[][]; 
		int nodei[], nodej[];
		ArrayList<RefOntoUML.Class> classes = new ArrayList<RefOntoUML.Class>();
		ArrayList<RefOntoUML.Class> cycle = new ArrayList<RefOntoUML.Class>();
		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
		ArrayList<Relationship> cycle_ass = new ArrayList<Relationship>();
		
		aux = OntoUML2Graph.buildGraph(parser, classes, relationships);
		nodei = aux[0];
		nodej = aux[1];
		
		if (relationships.size()<=2) return super.getOccurrences();
		
		int fundcycle[][] = new int [relationships.size()-2][classes.size()];
		GraphAlgo.fundamentalCycles(classes.size()-1, relationships.size()-1, nodei, nodej, fundcycle);
		
		for (int i=1; i<=fundcycle[0][0]; i++) { 
			try{
				cycle = new ArrayList<RefOntoUML.Class>();
				cycle_ass = new ArrayList<Relationship>();
				
				for (int j=1; j<=fundcycle[i][0]; j++)
					cycle.add(classes.get(fundcycle[i][j]));
				
				for (int j = 0; j < cycle.size(); j++) {
					
					int pos1, pos2;
					if(j<cycle.size()-1) {
						pos1 = j;
						pos2 = j+1;
					}
					else {
						pos1=j;
						pos2=0;
					}
					
					for (Relationship r : relationships) {
						if(r instanceof Association){
							Type source, target;
							if (((Association) r).getMemberEnd().size()==2) {
								source = ((Association)r).getMemberEnd().get(0).getType();
								target = ((Association)r).getMemberEnd().get(1).getType();
								if( (source.equals(cycle.get(pos1)) && target.equals(cycle.get(pos2))) || (source.equals(cycle.get(pos2)) && target.equals(cycle.get(pos1))))
									cycle_ass.add(r);
							}
						}
						if (r instanceof Generalization){
							Classifier general, specific;
							general = ((Generalization)r).getGeneral();
							specific = ((Generalization)r).getSpecific();
							if ( (general.equals(cycle.get(pos1)) && specific.equals(cycle.get(pos2))) || (general.equals(cycle.get(pos2)) && specific.equals(cycle.get(pos1))) )
								cycle_ass.add(r);
						}	
					}
				}
				super.occurrence.add(new AssCycOccurrence(cycle,cycle_ass,this));
			}catch (Exception e) {}
		}
		return super.getOccurrences();
	}

}
