package br.ufes.inf.nemo.meronymic_validation.derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingWorker;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivedMeronymic.PatternType;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.DerivedTableModel;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.graph.Graph;

public abstract class DerivationTask <T extends Meronymic> extends SwingWorker<Boolean, DerivedMeronymic>{

	protected OntoUMLParser parser;
	protected Set<T> existing;
	protected ArrayList<EdgePath> paths;
	protected ArrayList<DerivedMeronymic> derived;
	protected ArrayList<DerivedMeronymic> forbidden;
	private boolean arePathsSet;
	DerivedTableModel tableModel;
	
	public DerivationTask(OntoUMLParser parser, DerivedTableModel tableModel) {
		this.parser = parser;
		this.tableModel = tableModel;
		
		existing = new HashSet<T>();
		paths = new ArrayList<EdgePath>();
		forbidden = new ArrayList<DerivedMeronymic>();
		derived = new ArrayList<DerivedMeronymic>();
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

	public ArrayList<DerivedMeronymic> getDerived() {
		return derived;
	}

	public DerivedTableModel getTable() {
		return tableModel;
	}

	public ArrayList<DerivedMeronymic> getForbidden() {
		return forbidden;
	}
	
	
	public boolean arePathsSet() {
		return arePathsSet;
	}

	public void setPaths(){
		
		paths.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(existing, false, false);
		
		//get all paths in the graph
		paths = genGraph.getAllEdgePathsFromAllNodes();
		
		arePathsSet = true;
	}
	
	protected DerivedMeronymic createDerivedMeronymic(EdgePath path, Classifier whole,Classifier part, PatternType pattern, Meronymic currentRelation, RelationStereotype stereotype) {
		String prefix = "";
		DerivedMeronymic derived;
		
		if(pattern==PatternType.DIRECT_FUNCTIONAL_PARTHOOD)
			prefix = "dfp_";
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_PATHOOD_TYPE1 || pattern==PatternType.INDIRECT_FUNCTIONAL_PARTHOOD_TYPE2)
			prefix = "ifp_";
		if(pattern==PatternType.DIRECT_SUBCOLLECTION_PARTHOOD)
			prefix = "dscp_";
		
		if(currentRelation!=null)
			derived = new DerivedMeronymic(currentRelation);
		else
			derived = new DerivedMeronymic(stereotype);
		
		derived.setDerivationPath(path.getEdgeIdsOfType(Property.class));
		derived.setDerived(true);
		
		derived.setWhole(whole);
		derived.setPart(part);
				
		derived.setName(prefix+whole.getName().trim()+"_"+part.getName().trim());
		
		derived.setWholeEnd(whole.getName().trim().toLowerCase());
		derived.setPartEnd(part.getName().trim().toLowerCase());
		
		derived.setEndsMetaPropertiesFromPath();
		
		derived.setAllowed(true);
		derived.setPattern(pattern);
		derived.generateOCLRule();
		
		publish(derived);
		
		return derived;
	}
	
	protected <M extends Meronymic> M getDirect(Classifier whole, Classifier part, Class<M> stereotype){
		
		for (Meronymic meronymic : existing){
			if(stereotype.isInstance(meronymic) && OntoUMLParser.getWholeEnd(meronymic).getType().equals(whole) && OntoUMLParser.getPartEnd(meronymic).getType().equals(part))
				return stereotype.cast(meronymic);
		}
						
		return null;
	}

	@Override
	protected void process(final List<DerivedMeronymic> result) {
		System.out.println("PROCESSING!!!");
		tableModel.addRows(result);
	}
	
	@Override
	protected void done() {
		System.out.println("DONE!!!");
	}

}
