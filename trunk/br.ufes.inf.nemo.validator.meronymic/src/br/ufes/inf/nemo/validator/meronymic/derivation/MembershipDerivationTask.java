package br.ufes.inf.nemo.validator.meronymic.derivation;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic.PatternType;
import br.ufes.inf.nemo.validator.meronymic.derivation.ui.DerivedTableModel;

public class MembershipDerivationTask extends DerivationTask<Meronymic>{
	
	ArrayList<DerivedMeronymic> memberships;
	ArrayList<DerivedMeronymic> subCollections;
	
	public MembershipDerivationTask(OntoUMLParser parser, DerivedTableModel tableModel) {
		super(parser, tableModel);
		
		memberships = new ArrayList<DerivedMeronymic>();
		subCollections = new ArrayList<DerivedMeronymic>();
		
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
			
			subCollections.add(createDerivedSubCollectionOf(path, whole, part));
		}
		
		derived.addAll(subCollections);
	}
	
	public void deriveMemberOf(){

		if(!arePathsSet())
			setPaths();
		
		for (EdgePath path : paths) {
			if(path.isCycle() || path.getEdges().size()==1 || !isDerivableMemberOfPath(path))
				continue;
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			memberships.add(createDerivedMemberOf(path, whole, part));
		}
		
		derived.addAll(memberships);
	}

	private DerivedMeronymic createDerivedSubCollectionOf(EdgePath path, Classifier whole, Classifier part) {
		return createDerivedMeronymic(path, whole, part, PatternType.DIRECT_SUBCOLLECTION_PARTHOOD, getDirect(whole, part,subCollectionOf.class), RelationStereotype.SUBCOLLECTIONOF);
	}

	private DerivedMeronymic createDerivedMemberOf(EdgePath path, Classifier whole, Classifier part) {
		return createDerivedMeronymic(path, whole, part, PatternType.DIRECT_MEMBERSHIP, getDirect(whole, part,memberOf.class), RelationStereotype.MEMBEROF);
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {		
		System.out.println("Membership Derivation: creating collection and membership graph...");
		setPaths();
		System.out.println("Membership Derivation: deriving subcollections...");
		deriveSubCollectionOf();
		System.out.println("Membership Derivation: "+subCollections.size()+" subcollections derived");
		System.out.println("Membership Derivation: deriving memberships");
		deriveMemberOf();
		System.out.println("Membership Derivation: "+memberships.size()+" memberships derived");
		System.out.println("Membership Derivation: derivation completed!");
		System.out.println("Membership Derivation: a total of "+derived.size()+" relations were derived!");
		setProgress(33);
		return true;
	}
	
}
