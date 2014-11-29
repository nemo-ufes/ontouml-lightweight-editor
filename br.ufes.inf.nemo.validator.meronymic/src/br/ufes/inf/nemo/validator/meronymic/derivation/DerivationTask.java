package br.ufes.inf.nemo.validator.meronymic.derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingWorker;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontouml2directedgraph.Graph;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic.PatternType;
import br.ufes.inf.nemo.validator.meronymic.derivation.ui.DerivedTableModel;

public abstract class DerivationTask <T extends Meronymic> extends SwingWorker<Boolean, DerivedMeronymic>{

	protected OntoUMLParser parser;
	protected Set<T> existing;
	protected ArrayList<EdgePath> paths;
	protected ArrayList<DerivedMeronymic> derived;
	private boolean arePathsSet;
	DerivedTableModel tableModel;
	
	public DerivationTask(OntoUMLParser parser, DerivedTableModel tableModel) {
		this.parser = parser;
		this.tableModel = tableModel;
		
		existing = new HashSet<T>();
		paths = new ArrayList<EdgePath>();
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
	
	public boolean arePathsSet() {
		return arePathsSet;
	}

	public void setPaths(){
		
		paths.clear();
		
		Graph genGraph = new Graph(parser);
		//creates directed graph with classes and meronymics
		genGraph.createMeronymicGraph(existing, false, false);
		
		//get all paths in the graph
		paths = genGraph.getAllEdgePathsFromAllNodes(1);
		
		arePathsSet = true;
	}
	
	protected DerivedMeronymic createDerivedMeronymic(EdgePath path, Classifier whole,Classifier part, PatternType pattern, Meronymic currentRelation, RelationStereotype stereotype) {
		String prefix = "",wholeName,partName;
		DerivedMeronymic derived;
		
		if(pattern==PatternType.DIRECT_FUNCTIONAL)
			prefix = "dfp_";
		if(pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE1 || pattern==PatternType.INDIRECT_FUNCTIONAL_TYPE2)
			prefix = "ifp_";
		if(pattern==PatternType.DIRECT_SUBCOLLECTION_PARTHOOD)
			prefix = "dscp_";
		
		if(currentRelation!=null)
			derived = new DerivedMeronymic(currentRelation, parser);
		else
			derived = new DerivedMeronymic(stereotype, parser);
		
		derived.setPath(path.getEdgeIdsOfType(Property.class));
		derived.setDerived(true);
		
		derived.setWhole(whole);
		derived.setPart(part);

		if(whole.getName()!=null)
			wholeName = "whole";
		else
			wholeName = whole.getName().trim();
		
		if(part.getName()!=null)
			partName = "part";
		else
			partName = part.getName().trim();
		
			derived.setName(prefix+wholeName+"_"+partName);
		
		derived.setWholeEnd(wholeName.toLowerCase());
		derived.setPartEnd(partName.toLowerCase());
		
		derived.setEndsMetaPropertiesFromPath();
		
		derived.setPattern(pattern);
		derived.generateOCLRule(true);
		
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
		tableModel.addRows(result);
	}

}
