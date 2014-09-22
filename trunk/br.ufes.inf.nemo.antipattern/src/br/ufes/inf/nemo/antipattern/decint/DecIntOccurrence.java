package br.ufes.inf.nemo.antipattern.decint;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;

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
			for (GeneralizationSet gs : getParser().getSubtypesGeneralizationSets(parent)) {
				
				int generalizationsLeadingToSubtype = 0;
				for (Generalization g : gs.getGeneralization())
					if(g.getSpecific().equals(subtype) || parser.getAllParents(subtype).contains(g.getSpecific()))
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
								
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	public ArrayList<Generalization> getGeneralizationsToFixFromIdentityProvider(Classifier selectedIp){
		ArrayList<Generalization> list = new ArrayList<Generalization>();
		
		for (Classifier ip : identityProviders) {
			if(ip.equals(selectedIp))
				continue;
			for (Classifier child : ip.children()) {
				if(child.equals(subtype) || subtype.allParents().contains(child)){
					for (Generalization g : child.getGeneralization()) {
						if(g.getGeneral().equals(ip))
							list.add(g);
					}
				}
			}
		}
		
		return list;
	}
	
	public void fixIdentityProvider(Classifier identityProvider){
		if(!identityProviders.contains(identityProvider))
			return;
		
		//sets new generals
		for (Generalization gToFix : getGeneralizationsToFixFromIdentityProvider(identityProvider)) {
			gToFix.setGeneral(identityProvider);
			fix.includeModified(gToFix);
		}
		
		//remove duplicate generalizations
		for (Classifier child : identityProvider.children()) {
			
			ArrayList<Generalization> keepList = new ArrayList<Generalization>();
			ArrayList<Generalization> removeList = new ArrayList<Generalization>();
			
			for (Generalization g1 : child.getGeneralization()) {
				for (Generalization keepG : keepList) {
					if(keepG.getSpecific().equals(g1.getSpecific()) && keepG.getGeneral().equals(g1.getGeneral()))
						removeList.add(g1);
					else
						keepList.add(g1);
				}
			}
			
			Iterator<Generalization> iterator = removeList.iterator();
			while (iterator.hasNext()){
				Generalization gToRemove = iterator.next();
				fix.addAll(fixer.deleteElement(gToRemove));
				iterator.remove();
			}
		}
		
		
	}
	
	public void fixGeneralizationSets(ArrayList<GeneralizationSetReplica> replicas){
		for (GeneralizationSet gs : getDisjointGSList()) {
			boolean hasReplica = false;
			for (GeneralizationSetReplica replica : replicas) {
				if(replica.getOriginal().equals(gs)){
					hasReplica = true;
					replica.persistChanges();
					fix.includeModified(gs);
					break;
				}
			}
			if(!hasReplica){
				fix.addAll(fixer.deleteElement(gs));
			}
		}
	}
	
	public void generateIntersectionDerivation(){
		String context = subtype.parents().get(0).getName();
		String invExpr = addQuotes(context)+".allInstances()->forAll ( x | ( ";
		
		for (int i = 0; i < relevantParents.size(); i++) {
			if(i!=0)
				invExpr += " and ";
			invExpr += "x.oclIsTypeOf("+addQuotes(relevantParents.get(i).getName())+")";
		}
		
		invExpr += ") implies x.oclIsTypeOf("+addQuotes(subtype.getName())+") )";
	
		fix.addAll(fixer.generateOCLInvariant(context, "intersection_"+subtype.getName(), invExpr));
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
