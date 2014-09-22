package br.ufes.inf.nemo.antipattern.overlapping;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MixinClass;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.overlapping.CommonMixinSupertypeComposite;

//partEnds with the same type
public class CommonMixinSupertype extends OverlappingGroup {

	boolean hasOverlappingGS;
	Classifier closestSupertpe;
	ArrayList<Classifier> commonSupertypes;
	ArrayList<GeneralizationSet> genSets;
	private ArrayList<Classifier> closestSupertypes;
	
	public CommonMixinSupertype (ArrayList<Property> mixinProperties, Antipattern<?> antipattern) throws Exception {
		super(mixinProperties, antipattern);
		
		//all types must be mixins
		for (Classifier type : super.overlappingTypes) {
			if(!(type instanceof MixinClass))
				throw new Exception("VAR5: All parts must be mixins.");
		}
		
		//at most one mixin type may have sortal subtypes
		int mixinsWithSortalSubtype = 0;
		for (Classifier type : super.overlappingTypes)
			for (Classifier child : antipattern.getParser().allChildrenHash.get(type))
				if(!(child instanceof MixinClass)){
					mixinsWithSortalSubtype++;
					break;
				}
		if(mixinsWithSortalSubtype>1)
			throw new Exception("VAR5: Two part types specialized by sortal.");
		
		this.genSets = new ArrayList<GeneralizationSet>();
		this.commonSupertypes = new ArrayList<Classifier>();
		if (!antipattern.getParser().allTypesOverlap(super.overlappingTypes, commonSupertypes, genSets))
			throw new Exception("VAR5: Disjoint by supertypes.");
		
		if(genSets.size()>0) 
			hasOverlappingGS = true;
		
		getClosestSupertype();
		super.validGroup = true;
	}
	
	private void getClosestSupertype(){
		OntoUMLParser parser = getAntipattern().getParser();
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
		String result =	"Overllaping Group: Mixin Classes with Common Supertype" +
						"\nClosest Common Supertypes: ";
		
		for (Classifier parent : this.closestSupertypes)
			result+="\n\t"+OntoUMLNameHelper.getTypeAndName(parent, true, false);
		
		result += "\nProperties: ";
		
		for (Property p : overlappingProperties)
			result+="\n\t"+OntoUMLNameHelper.getNameAndType(p);
		
		return result;
	}
	
	@Override
	public boolean makeEndsDisjoint(AntipatternOccurrence occurrence, ArrayList<Property> partEnds) {
		
		if(!this.overlappingProperties.containsAll(partEnds))
			return false;
		
//		ArrayList<GeneralizationSet> genSets = occurrence.getParser().getSubtypesGeneralizationSets(closestSupertpe);
		
		ArrayList<Classifier> subtypes = new ArrayList<> ();
		for (Property property : partEnds) {
			subtypes.add((Classifier) property.getType());
		}
		
		if(genSets.size()==0)
			occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes, true, false));
		else{
			boolean isFixed = false;
			for (GeneralizationSet gs : genSets) {
				ArrayList<Classifier> allGsChildren = new ArrayList<Classifier>();
				
				for (Generalization g : gs.getGeneralization()) {
					allGsChildren.add(g.getSpecific());
					allGsChildren.addAll(getAntipattern().getParser().allChildrenHash.get(g.getSpecific()));
				}
				
				if (allGsChildren.containsAll(subtypes)){
					gs.setIsDisjoint(true);
					occurrence.getFix().includeModified(gs);
					isFixed = true;
					break;
				}
			}
			
			if(!isFixed)
				occurrence.getFix().addAll(occurrence.getFixer().createGeneralizationSet(closestSupertpe, subtypes, true, false));
		}
			
		return true;
	}
	
	public boolean isHasOverlappingGS() {
		return hasOverlappingGS;
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
	
	@Override
	public String getType() {
		return "Common Mixin Supertype";
	}

	public ArrayList<Classifier> getClosestSupertypes() {
		return commonSupertypes;
	}
	
	@Override
	public Composite createComposite(Composite parent, int style) {
		return new CommonMixinSupertypeComposite(parent, style, this);
	}	
}
