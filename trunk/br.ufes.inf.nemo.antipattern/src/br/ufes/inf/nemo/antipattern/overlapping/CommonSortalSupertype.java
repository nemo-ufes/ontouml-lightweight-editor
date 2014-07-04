package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Role;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class CommonSortalSupertype extends OverlappingGroup {
	
	boolean hasOverlappingGS;
	Classifier identityProvider,closestSupertpe;
	ArrayList<Classifier> commonSupertypes;
	ArrayList<GeneralizationSet> genSets;
	private ArrayList<Classifier> closestSupertypes;
	
	public CommonSortalSupertype (ArrayList<Property> sortalOverlappingProperties, Antipattern<?> antipattern)throws Exception {
		super(sortalOverlappingProperties, antipattern);
		
		//all types must be subkinds, phases or roles
		for (Classifier type : super.overlappingTypes) {
			if(!(type instanceof SubKind) && !(type instanceof Phase) && !(type instanceof Role))
				throw new Exception("VAR4: All parts must be subkinds, phases or roles.");
		}
		
		//all types must be have the same identity provider or none at all (in case of partial models)
		identityProvider = null;
		boolean hasIdentitylessProperty = false;
		boolean hasIdentifiableProperty = false;
		
		Classifier currentIdentityProvider;
		HashSet<Classifier> allIdentityProviders;
		
		for (Property p : sortalOverlappingProperties) {
			currentIdentityProvider = null;	
			allIdentityProviders = antipattern.getParser().getIdentityProvider((SortalClass) p.getType());
			
			//found single identity provider
			if(allIdentityProviders.size()==1){
				currentIdentityProvider = (Classifier) allIdentityProviders.toArray()[0];
				
				if(hasIdentitylessProperty)
					throw new Exception("VAR4: Either all types have the same identity or none at all.");
				
				if(hasIdentifiableProperty && !identityProvider.equals(currentIdentityProvider))
					throw new Exception("VAR4: Different identity providers.");
				
				hasIdentifiableProperty = true;
				identityProvider = currentIdentityProvider;
			}
			//found multiple identity providers
			else if(allIdentityProviders.size()>1){
				throw new Exception("VAR4: Multiple identity providers.");
			}
			else if(allIdentityProviders.size()==0){
				hasIdentitylessProperty = true;
				
				if(hasIdentifiableProperty)
					throw new Exception("VAR4: Either all types have the same identity or none at all.");
			}
			
		}
		
		this.genSets = new ArrayList<GeneralizationSet>();
		this.commonSupertypes = new ArrayList<Classifier>();
		if (!antipattern.getParser().allTypesOverlap(super.overlappingTypes, commonSupertypes,genSets))
			throw new Exception("VAR: Disjoint by supertypes.");
		
		if(genSets.size()>0)
			hasOverlappingGS = true;
		
		setClosestSupertype();
		
		super.validGroup = true;
	}

	private void setClosestSupertype(){
		OntoUMLParser parser = antipattern.getParser();
		closestSupertypes = new ArrayList<Classifier>(commonSupertypes);
		
		for (Classifier common : commonSupertypes) {
			
			Iterator<Classifier> iterator = closestSupertypes.iterator();
			
			while(iterator.hasNext()){
				Classifier candidate = iterator.next();
				
				if(parser.getAllParents(common).contains(candidate))
					iterator.remove();
			}
			
		}
		
		closestSupertpe = null;
		
		for (Classifier supertype : this.commonSupertypes) {
			if(closestSupertpe == null)
				closestSupertpe = supertype;
			else if (supertype.allParents().contains(closestSupertpe))
				closestSupertpe = supertype;				
		}
	}
	
	
	@Override
	public String toString(){
		String result =	"Overllaping Group: Sortals with Common Identity Provider" +
						"\nIdentity Provider: "+getIdentityProviderName()+
						"\nClosest Common Supertypes: ";
		
		for (Classifier parent : this.closestSupertypes)
			result+="\n\t"+OntoUMLNameHelper.getTypeAndName(parent, true, false);
			
		result += "\nPart Ends: ";
	
		for (Property p : overlappingProperties)
			result+="\n\t"+OntoUMLNameHelper.getNameAndType(p);
		
		return result;
	}


	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> partEnds) {
		
		if(!this.overlappingProperties.containsAll(partEnds))
			return false;
		
		ArrayList<GeneralizationSet> gss = antipattern.getParser().getSubtypesGeneralizationSets(closestSupertpe);
		
		ArrayList<Classifier> subtypes = new ArrayList<> ();
		for (Property property : partEnds) {
			subtypes.add((Classifier) property.getType());
		}
		
		if(gss.size()==0)
			occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes));
		else{
			boolean isFixed = false;
			for (GeneralizationSet gs : gss) {
				ArrayList<Classifier> allGsChildren = new ArrayList<Classifier>();
				
				for (Generalization g : gs.getGeneralization()) {
					allGsChildren.add(g.getSpecific());
					allGsChildren.addAll(antipattern.getParser().allChildrenHash.get(g.getSpecific()));
				}
				
				if (allGsChildren.containsAll(subtypes)){
					gs.setIsDisjoint(true);
					occurrence.getFix().includeModified(gs);
					isFixed = true;
					break;
				}
			}
			
			if(!isFixed)
				occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes));
		}
			
		return true;
	}

	public boolean isHasOverlappingGS() {
		return hasOverlappingGS;
	}

	public Classifier getIdentityProvider() {
		return identityProvider;
	}

	public Classifier getClosestSupertpe() {
		return closestSupertpe;
	}

	public ArrayList<Classifier> getCommonSupertypes() {
		return commonSupertypes;
	}

	public ArrayList<GeneralizationSet> getGenSets() {
		return genSets;
	}
	
	public String getIdentityProviderName(){
		if(identityProvider == null)
			return "None";
		
		if(identityProvider.getName()==null)
			return "Null Name";
		
		if(identityProvider.getName().trim().isEmpty())
			return "Unnamed";
		
		return identityProvider.getName();
	}
	
}
