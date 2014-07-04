package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class OverlappingOccurrence extends AntipatternOccurrence{

	Classifier mainType;
	ArrayList<OverlappingGroup> groups;
	HashSet<Property> allProperties;
	HashSet<Classifier> allOverlappingTypes;
	
	public OverlappingOccurrence(Antipattern<?> antipattern, Classifier mainType, HashSet<Property> allProperties) throws Exception {
		super(antipattern);
		
		if(mainType == null)
			throw new Exception("Overlapping: provided input main type is null. Can't create occurrence!");
		if(allProperties == null)
			throw new Exception("Overlapping: provided input properties list is null. Can't create occurrence!");
		
		if(allProperties.size()<2)
			throw new Exception("Overlapping: Can't creat occurrence! At least 2 properties are required");
		
		allOverlappingTypes = new HashSet<Classifier>();
		
		for (Property p : allProperties) {
			
			allOverlappingTypes.add((Classifier) p.getType());
			
			//all association ends in allProperties must have the opposite equals to mainType or one of its supertypes
			if(p.getAssociation()!=null){
				if (!p.getOpposite().getType().equals(mainType) && !mainType.allParents().contains(p.getOpposite().getType())) {
					throw new Exception("Overlapping: All association ends must have the opposite equal to mainType or one of its supertypes.");
				}
			}
			//all attributes must belong to mainType or one of its supertypes
			else {
				if (!p.eContainer().equals(mainType) && !(((Classifier) p.eContainer()).allParents().contains(mainType))) {
					throw new Exception("Overlapping: All attributes must belong to mainType or one of its supertypes.");
				}
			}
		}
		
		this.allProperties = allProperties;
		this.mainType = mainType;
		
		groups = new ArrayList<OverlappingGroup>();
		identifyVariations();
		
		if(groups.size()==0)
			throw new Exception("Overlapping: Could not find any variation in the occurrence");
	}
	
	public void identifyVariations(){
		Combination comb = new Combination(new ArrayList<>(allProperties), 0);
		boolean hasDirectProperty;
		
		while(comb.hasNext()) {
			ArrayList<Property> result = comb.next();
			
			hasDirectProperty = false;
			for (Property p : result) {
				if(p.getOpposite()!=null && getMainType().equals(p.getOpposite().getType())){
					hasDirectProperty = true;
					break;
				}
			}
			
			if(!hasDirectProperty)
				continue;
			
			if(result.size()>1) {
				OverlappingGroup var;
				try {
					var = new SameType(result, antipattern);
					addVariation(var);
					continue;
				}
				catch(Exception e){	}
				try {
					var = new CommonSortalSupertype(result, antipattern);
					addVariation(var);
					continue;
				}
				catch(Exception e){	}
				try {
					var = new CommonMixinSupertype(result, antipattern);
					addVariation(var);
					continue;
				}
				catch(Exception e){	}
				try {
					var = new CommonMixinSubtype(result, antipattern);
					addVariation(var);
					continue;
				}
				catch(Exception e){	}
		}
			
		}
	}
	
	private void addVariation(OverlappingGroup varToAdd){
		
		ArrayList<OverlappingGroup> variationsToRemove = new ArrayList<OverlappingGroup>();
		
		for (OverlappingGroup existingVariation : groups) {
			if(existingVariation.getClass().equals(varToAdd.getClass())){
				
				if(varToAdd.overlappingProperties.containsAll(existingVariation.overlappingProperties)){
					variationsToRemove.add(existingVariation);
				}
				else if(existingVariation.overlappingProperties.containsAll(varToAdd.overlappingProperties)){
					return;
				}
			}
		}
		
		groups.removeAll(variationsToRemove);
		groups.add(varToAdd);
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Property p : getAllProperties()){
			selection.add(p.getType());
			selection.add(p.getAssociation());
		}
		
		selection.add(getMainType());
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}
	
	public ArrayList<OverlappingGroup> getVariations() {
		return groups;
	}

	public HashSet<Property> getAllProperties() {
		return allProperties;
	}

	public HashSet<Classifier> getAllOverlappingTypes() {
		return allOverlappingTypes;
	}
	
	public Classifier getMainType() {
		return mainType; 
	}
	
	//verifies if the sum of the upper bound of the properties' cardinalities is greater or equal to a given threshold
	public boolean verifyMinimumUpperCardinalitySum(int minimumUpperCardinalitySum){
		
		boolean existsEndWithUnlimitedUpperCardinality = false;
		int upperCardinalituSum = 0;
		
		for (Property end : getAllProperties()) {			
			if (end.getUpper()==-1)
				existsEndWithUnlimitedUpperCardinality=true;
			else
				upperCardinalituSum+=end.getUpper();
		}
		
		if(existsEndWithUnlimitedUpperCardinality || upperCardinalituSum>=minimumUpperCardinalitySum )
			return true;
		else
			return false;
	}

	public abstract String getPropertyTypeString();
	
	public abstract String getExclusiveExample();

	public abstract String getBaseClassType();
}
