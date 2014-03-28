package br.ufes.inf.nemo.antipattern.decint;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class DecIntOccurrence extends AntipatternOccurrence {

	Class subtype;
	ArrayList<Classifier> relevantParents;
	ArrayList<Classifier> identityProviders;
	ArrayList<GeneralizationSet> disjointGSList;
	
	public DecIntOccurrence(Class subtype, ArrayList<Classifier> relevantParents, DecIntAntipattern ap) throws Exception {
		super(ap);
		
		if(subtype == null)
			throw new Exception(DecIntAntipattern.getAntipatternInfo().acronym+": provided subtype is null. Can't create occurrence!");
		if(relevantParents == null)
			throw new Exception(DecIntAntipattern.getAntipatternInfo().acronym+": provided relevantParents is null. Can't create occurrence!");
		if(relevantParents.size()<2)
			throw new Exception(DecIntAntipattern.getAntipatternInfo().acronym+": there must be 2 or more supertypes. Can't create occurrence!");
		if(!subtype.parents().containsAll(relevantParents))
			throw new Exception(DecIntAntipattern.getAntipatternInfo().acronym+": all classes in the provided list must be a supertype of the provided subtype. Can't create occurrence!");
		
		this.subtype = subtype;
		this.relevantParents = relevantParents;
		this.identityProviders = setIdentityProviders();
		this.disjointGSList = setDisjointGSList();
	}


	private ArrayList<Classifier> setIdentityProviders() {
		ArrayList<Classifier> identityProviders = new ArrayList<Classifier>();
		
		for (Classifier parent :  parser.getAllParents(subtype)) {
			if(parent instanceof SubstanceSortal)
				identityProviders.add(parent);
		}
		
		return identityProviders;
	}
	
		
	private ArrayList<GeneralizationSet> setDisjointGSList() {
		
		ArrayList<GeneralizationSet> disjointGSList = new ArrayList<GeneralizationSet>();
		
		for (Classifier parent : parser.getAllParents(subtype)) {
			for (GeneralizationSet gs : AntiPatternIdentifier.getSubtypesGeneralizationSets(parent)) {
				
				int generalizationsLeadingToSubtype = 0;
				for (Generalization g : gs.getGeneralization())
					if(g.getSpecific().equals(subtype) || parser.getAllChildren(g.getSpecific()).contains(subtype))
						generalizationsLeadingToSubtype++;
				
				if(generalizationsLeadingToSubtype>1 && gs.isIsDisjoint() && !disjointGSList.contains(gs)){
					disjointGSList.add(gs);
				}
			}
		}
		
		return disjointGSList;
	}
	
	public Class getSubtype() {
		return subtype;
	}

	public ArrayList<Classifier> getRelevantParents() {
		return relevantParents;
	}

	public ArrayList<Classifier> getIdentityProviders() {
		return identityProviders;
	}

	public ArrayList<GeneralizationSet> getDisjointGSList() {
		return disjointGSList;
	}


	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(subtype);
		selection.addAll(subtype.allParents());
								
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		
		String result = "Subtype: "+ parser.getStringRepresentation(subtype)+"\n"+
						"Parents: ";
		
		for (Classifier parent : this.relevantParents) {
			result += "\n\t"+parser.getStringRepresentation(parent);
		}
		
		return result;
		
	}
	
	@Override
	public String getShortName() {
		return parser.getStringRepresentation(this.subtype);
	}

}
