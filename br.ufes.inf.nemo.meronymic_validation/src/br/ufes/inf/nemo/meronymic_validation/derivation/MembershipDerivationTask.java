package br.ufes.inf.nemo.meronymic_validation.derivation;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.RelationStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivedMeronymic.PatternType;
import br.ufes.inf.nemo.meronymic_validation.derivation.ui.DerivedTableModel;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;

public class MembershipDerivationTask extends DerivationTask<Meronymic>{
	
	
	
	public MembershipDerivationTask(OntoUMLParser parser, DerivedTableModel tableModel) {
		super(parser, tableModel);
		
		existing.addAll(parser.getAllInstances(subCollectionOf.class));
		existing.addAll(parser.getAllInstances(memberOf.class));
	}
	
	
	//path with exclusively subcollections
	public boolean isDerivableSubCollectionOfPath(EdgePath path){
		for (Property p : path.getEdgeIdsOfType(Property.class)) {
			if(!(p.getAssociation() instanceof subCollectionOf))
				return false;
		}
		
		return true;
	}
	
	//path of subcollection ending in memberof
	public boolean isDerivableMemberOfPath(EdgePath path){
		ArrayList<Property> propertyPath = path.getEdgeIdsOfType(Property.class);
		
		for (int i = 0; i < propertyPath.size()-1; i++) {
			if(!(propertyPath.get(i).getAssociation() instanceof subCollectionOf))
				return false;
		}
		
		if(!(propertyPath.get(propertyPath.size()-1).getAssociation() instanceof memberOf))
			return false;
		
		return true;
	}
	
	public void deriveSubCollectionOf(){

		if(!arePathsSet())
			setPaths();
		
		for (EdgePath path : paths) {
			if(path.isCycle() || path.getEdges().size()==1 || !isDerivableSubCollectionOfPath(path))
				continue;
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			derived.add(createDerivedSubCollectionOf(path, whole, part));
		}
	}
	
	public void deriveMemberOf(){

		if(!arePathsSet())
			setPaths();
		
		for (EdgePath path : paths) {
			if(path.isCycle() || path.getEdges().size()==1 || !isDerivableMemberOfPath(path))
				continue;
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			derived.add(createDerivedMemberOf(path, whole, part));
		}
	}

	private DerivedMeronymic createDerivedSubCollectionOf(EdgePath path, Classifier whole, Classifier part) {
		return createDerivedMeronymic(path, whole, part, PatternType.DIRECT_SUBCOLLECTION_PARTHOOD, getDirect(whole, part,subCollectionOf.class), RelationStereotype.SUBCOLLECTIONOF);
	}

	private DerivedMeronymic createDerivedMemberOf(EdgePath path, Classifier whole, Classifier part) {
		return createDerivedMeronymic(path, whole, part, PatternType.DIRECT_MEMBERSHIP, getDirect(whole, part,memberOf.class), RelationStereotype.MEMBEROF);
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		setProgress(1);
		setPaths();
		setProgress(40);
		deriveSubCollectionOf();
		setProgress(70);
		deriveMemberOf();
		setProgress(100);
		return true;
	}
	
	
}
