package br.ufes.inf.nemo.antipattern.relover;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.RoleMixin;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelOverOccurrence extends AntipatternOccurrence{
	
	private Classifier relator;

	private ArrayList<Property> allMediatedEnds;
	private HashMap<Classifier, ArrayList<Property>> commonSupertypes; //contains the common supertypes and the mediatedEnds connected to the respective subtypes 
	

	private HashMap<Mediation, Classifier> mediations;
	private Classifier supertype;
	
	
	public RelOverOccurrence(Classifier relator, ArrayList<Property> mediatedEnds, RelOverAntipattern ap) throws Exception {
		super(ap);
				
		verifyInput(relator, mediatedEnds);
		
		this.relator=relator;
		this.allMediatedEnds=mediatedEnds;
		this.mediations = new HashMap<>();
		
		for (Property p : mediatedEnds) {
			mediations.put((Mediation)p.getAssociation(), (Classifier)p.getType());
		}
		
		fillSupertypesHash();
		
	}
	
	private void fillSupertypesHash (){
		
		this.commonSupertypes = new HashMap<Classifier, ArrayList<Property>>();
		
		for (Property p1 : allMediatedEnds) {
			for (Property p2 : allMediatedEnds) {
				ArrayList<Classifier> commonSuper = getCommonOverlappingSupertypes((Classifier)p1.getType(), (Classifier)p2.getType());
				if (commonSuper.size()>0){
					for (Classifier supertype : commonSuper) {
						if(commonSupertypes.containsKey(supertype)){
							if(!commonSupertypes.get(supertype).contains(p1))
								commonSupertypes.get(supertype).add(p1);
							if(!commonSupertypes.get(supertype).contains(p2))
								commonSupertypes.get(supertype).add(p2);
						}
						else{
							ArrayList<Property> mediatedEnds = new ArrayList<>();
							mediatedEnds.add(p1);
							mediatedEnds.add(p2);
							commonSupertypes.put(supertype, mediatedEnds);
						}
					}
				}
			}
		}
		
		if (commonSupertypes.keySet().toArray()[0]!=null)
			supertype = (Classifier) commonSupertypes.keySet().toArray()[0];
	}
	
	private ArrayList<Classifier> getCommonOverlappingSupertypes (Classifier c1, Classifier c2){
		ArrayList<Classifier> common = new ArrayList<>();
		ArrayList<Classifier> result = new ArrayList<>();
		
		if(c1.equals(c2))
			return result;
		
		common.addAll(c1.allParents());
		
		//get the common supertypes
		common.retainAll(c2.allParents());
		
		result.addAll(common);
		
		//removes common supertypes which are subtypes of other common supertypes
		for (Classifier parent : common) {
			if(hasIntersection(result, parent.allChildren()))
				result.remove(parent);
			if(parent instanceof Mixin || parent instanceof RoleMixin || parent instanceof Category)
				result.remove(parent);
		}
		
		//removes common supertypes whose generalization sets make the subtypes disjoint
		for (GeneralizationSet gs : parser.getAllInstances(GeneralizationSet.class))
			if(gs.getGeneralization().size()>0 && result.contains(gs.getGeneralization().get(0).getGeneral()) && gs.isIsDisjoint()==true)
				for (Generalization g1 : gs.getGeneralization())
					for (Generalization g2 : gs.getGeneralization())
						if (!g1.equals(g2) && (g1.getSpecific().equals(c1) || g1.getSpecific().allChildren().contains(c1)) && (g2.getSpecific().equals(c2) || g2.getSpecific().allChildren().contains(c2)))
							result.remove(g1.getGeneral());
				
		return result;
	}
	
	private <T,S> boolean hasIntersection(Collection<T> list1, Collection<S> list2){
		for (T o : list1) {
			if(list2.contains(o))
				return true;
		}
		return false;
	}
	
	private void verifyInput(Classifier relator, ArrayList<Property> mediatedEnds) throws Exception {
		if(relator==null)
			throw new NullPointerException("RelOver: The provided relator is null.");
		
		if(mediatedEnds==null)
			throw new NullPointerException("RelOver: mediatedEnds is null.");
		
		if(!(relator instanceof Relator)){
			
			boolean hasRelatorParent = false;
			
			for (Classifier parent : relator.allParents()) {
				if(parent instanceof Relator)
					hasRelatorParent = true;
			}
			
			if(!hasRelatorParent)
				throw new Exception("RelOver: The provided object is not a Relator and neither specializes one.");
		}
		
		boolean existsEndWithUnlimitedUpperCardinality = false;
		int upperCardinalituSum = 0;
		
		for (Property end : mediatedEnds) {
			
			if(!(end.getAssociation() instanceof Mediation))
				throw new Exception("RelOver: All provided properties must refer to Mediations.");
			
			if(!end.getOpposite().getType().equals(relator) && !relator.allParents().contains(end.getOpposite().getType()))
				throw new Exception("RelOver: The type of all opposite ends of the provided properties must be equal to the provided relator, or one of its supertypes.");
			
			if (end.getUpper()==-1)
				existsEndWithUnlimitedUpperCardinality=true;
			else
				upperCardinalituSum+=end.getUpper();
		}
		
		if(!existsEndWithUnlimitedUpperCardinality && upperCardinalituSum<=2 )
			throw new Exception("RelOver: The sum of the mediated ends upper cardinality must be greater than 2.");
	}
	/*
	public void setRelator(Classifier relator) throws Exception {
		//ArrayList which will contain all the mediations of the relator and its supertypes
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		
		//List that will be used to identify the common supertype
		ArrayList<Classifier> mediatedTypesAllParents = new ArrayList<Classifier>();
		
		/*ArrayList which will contain the relator that was the foundation of the anti-pattern with all its supertypes
		ArrayList<Classifier> allRelators = new ArrayList<Classifier>();
				
		if(relator==null)
			throw new NullPointerException("RelOver: The provided relator is null.");
		
		if(!(relator instanceof Relator)){
			
			boolean hasRelatorParent = false;
			
			for (Classifier parent : relator.allParents()) {
				if(parent instanceof Relator)
					hasRelatorParent = true;
			}
			
			if(!hasRelatorParent)
				throw new Exception("RelOver: The provided object is not a Relator and neither specializes one.");
		}
		
		this.relator=relator;
		
		
		//add the input relator to the list
		allRelators.add(relator);
		//add all supertypes to the list
		allRelators.addAll(parser.getAllParents(relator));
			
		for (Classifier rel : allRelators) {
			ArrayList<Mediation> meds = new ArrayList<Mediation>();
			parser.getAllMediations(rel, meds);
			mediations.addAll(parser.retainSelected(meds));
		}
		
		//if there are not at least two mediations, the anti-pattern is not characterized
		if(mediations.size()<2)
			throw new Exception("RelOver: The input relator does not have enough mediations to characterize the Anti-Pattern");
		
		this.mediations = new HashMap<Mediation, Classifier>();
		
		//Adds all supertypes to the ArrayList
		for (Mediation med : mediations)
			mediatedTypesAllParents.addAll(parser.retainSelected(med.mediated().allParents()));
		
		for (Mediation med : mediations) {
			this.mediations.put(med, med.mediated());
			
			ArrayList<Classifier> aux2 = new ArrayList<Classifier>();
			aux2.addAll(parser.retainSelected(med.mediated().allParents()));
			
			if(!ArrayListOperations.intersection(mediatedTypesAllParents, aux2).isEmpty()){
				aux2 = new ArrayList<Classifier>();
				aux2.addAll(parser.retainSelected(med.mediated().allParents()));
				mediatedTypesAllParents.retainAll(aux2);
			}
		}
		
		if(mediatedTypesAllParents.isEmpty())
			throw new NullPointerException("There is no common supertype of the mediated entities");
		
		this.supertype = mediatedTypesAllParents.iterator().next();
		
	}
	
	*/
	public Mediation getKeyByValue(Classifier value) 
	{
	    for (Entry<Mediation,Classifier> entry : mediations.entrySet()) 
	    {
	        if (value.equals(entry.getValue())) 
	        {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	
	public Classifier getSupertype() {
		return supertype;
	}
	
	public HashMap<Mediation, Classifier> getMediations() {
		return mediations;
	}
	
	public Classifier getRelator() {
		return relator;
	}
	
	public ArrayList<Classifier> getMediatedTypes(){
		ArrayList<Classifier> result = new ArrayList<Classifier>();
		for (Property p : this.allMediatedEnds) {
			result.add((Classifier) p.getType());
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result;
		
		result = super.parser.getStringRepresentation(this.relator)+
				"\nMediated Elements: ";
				
		for (Property p : this.allMediatedEnds)
			result+="\n\t"+super.parser.getStringRepresentation(p);
		
	/*	result+="\nSupertypes: ";
		
		int i = 1;
		for (Classifier supertype : this.commonSupertypes.keySet()) {
			result += "\n\tSupertype #"+i+": "+super.parser.getStringRepresentation(supertype);
			for (Property p : this.commonSupertypes.get(supertype)) {
				result += "\n\t\t"+super.parser.getStringRepresentation(p);
			}
			
			i++;
		}
		*/
		return result;
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Property p : this.allMediatedEnds)
			selection.add(p.getAssociation());
		
		selection.add(relator);
		//selection.addAll(commonSupertypes.keySet());
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
		
	}
		
	/*=============================== TO DO ==================================*/
	
	public String generateDisjointPredicate(OntoUMLParser parser, int cardinality)
	{ 
		return ""; 
	}
	public String generateDisjointFromTablePredicate(ArrayList<ArrayList<Mediation>> exclusiveMatrix, OntoUMLParser parser, int cardinality)
	{ 
		return ""; 
	}
	
	public String generateExclusiveOcl(ArrayList<Classifier> disjointTypes, OntoUMLParser parser){
		String result = "context _'"+relator.getName()+"'\n"+
						"inv: ";
		if (disjointTypes.size()<2)
			return null;
		
		for (Mediation mediation : this.mediations.keySet()) 
		{
			if(mediation.getMemberEnd().get(0).getType().equals(disjointTypes.get(0)))
				result+="self."+mediation.getMemberEnd().get(0).getName()+".oclAsType("+
				supertype.getName()+")->asSet()";
				
			if(mediation.getMemberEnd().get(1).getType().equals(disjointTypes.get(0)))
				result+="self."+mediation.getMemberEnd().get(1).getName()+".oclAsType("+
				supertype.getName()+")->asSet()";
		}
		System.out.println(disjointTypes.size());
		
		for (int i = 1; i<disjointTypes.size(); i++)
		{
			Classifier classifier = disjointTypes.get(i);
			for (Mediation mediation : this.mediations.keySet()) 
			{
				if(mediation.getMemberEnd().get(0).getType().equals(classifier))
					result+="->intersection(self."+mediation.getMemberEnd().get(0).getName()+".oclAsType("+
					supertype.getName()+")->asSet())";				
				
				if(mediation.getMemberEnd().get(1).getType().equals(classifier))
					result+="->intersection(self."+mediation.getMemberEnd().get(1).getName()+".oclAsType("+
					supertype.getName()+")->asSet())";
			}
		}
		result += "->size()=0";
		
		return result;
	}
	
	public String generateExclusiveOCLInvariant (ArrayList<ArrayList<Mediation>> exclusiveMatrix, OntoUMLParser parser, int cardinality){
		@SuppressWarnings("unused")
		String invariant="", rules, invariantName, relatorName;
		Combination comb1;
		ArrayList<Mediation> output;
		
		relatorName=parser.getAlias(relator);
		
		invariantName = "MultipleExclusiveRole_"+relator.getName();
		rules = "#"+relatorName+">="+cardinality;
		
		for (ArrayList<Mediation> exclusiveList : exclusiveMatrix){
			if (this.mediations.keySet().containsAll(exclusiveList) && exclusiveList.size()>=2) {
				
				comb1 = new Combination(exclusiveList, 2);
				
				invariantName+="__";
				for (Mediation med : exclusiveList)
					invariantName+="_"+med.getMemberEnd().get(1).getType().getName();
				
				rules += "\n\tall w:World | all x:w."+relatorName+" | ";
				while(comb1.hasNext()){
					output = comb1.next();
					rules+="#(x.(w."+parser.getAlias(output.get(0))+") & x.(w."+parser.getAlias(output.get(1))+")) = 0";
					
					if(comb1.hasNext())
	            		rules += " and ";
				}
			}
		}
			
		invariant = "context _'"+this.getRelator().getName()+"'\n"+
					"inv '"+invariantName+"' :";
		
		return invariant;
		
	}
	
	public String generateExclusivePredicate(OntoUMLParser parser, int cardinality){
		String predicate, rules, predicateName, relatorName;
		ArrayList<Object> saida, mediations = new ArrayList<Object>();
		Combination comb1;
							
		relatorName=parser.getAlias(relator);
		
		predicateName = "exclusiveRole_"+relatorName;
		
		//the number of relators must be always greater or equal to 1;
		if(cardinality<=0)
			rules = "#"+relatorName+">=1";
		else
			rules = "#"+relatorName+">="+cardinality;
		
		rules += "\n\tall w:World | all x:w."+relatorName+" | ";

		// Combinacoes de mediations agrupadas 2 a 2
		mediations.addAll(this.mediations.keySet());
		comb1 = new Combination(mediations, 2);
        
		while (comb1.hasNext()) {
            saida = comb1.next();
            rules+="#(x.(w."+parser.getAlias((PackageableElement)saida.get(0))+") & x.(w."+parser.getAlias((PackageableElement)saida.get(1))+")) = 0";
            
            if(comb1.hasNext())
            	rules+=" and ";
        }
		
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public String generateOverlappingPredicate(OntoUMLParser parser, int cardinality){
		String predicate, rules, predicateName, relatorName;
		ArrayList<Object> saida, mediations = new ArrayList<Object>();
		Combination comb1;
							
		relatorName=parser.getAlias(relator);
		
		predicateName = "nonExclusiveRole_"+relatorName;
		
		//the number of relators must be always greater or equal to 1;
		if(cardinality<=0)
			rules = "#"+relatorName+">=1";
		else
			rules = "#"+relatorName+">="+cardinality;
		
		rules += "\n\tall w:World | all x:w."+relatorName+" | ";

		// Combinacoes de mediations agrupadas 2 a 2
		mediations.addAll(this.mediations.keySet());
		comb1 = new Combination(mediations, 2);
        
		while (comb1.hasNext()) {
            saida = comb1.next();
            rules+="#(x.(w."+parser.getAlias((PackageableElement)saida.get(0))+") & x.(w."+parser.getAlias((PackageableElement)saida.get(1))+")) > 0";
            
            if(comb1.hasNext())
            	rules+=" or ";
        }
		
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public String generateMultipleExclusivePredicate (ArrayList<ArrayList<Mediation>> exclusiveMatrix, OntoUMLParser parser, int cardinality){
		String predicate="", rules, predicateName, relatorName;
		Combination comb1;
		ArrayList<Mediation> output;
		
		relatorName=parser.getAlias(relator);
		
		predicateName = "MultipleExclusiveRole_"+relatorName;
		rules = "#"+relatorName+">="+cardinality;
		
		for (ArrayList<Mediation> exclusiveList : exclusiveMatrix){
			if (this.mediations.keySet().containsAll(exclusiveList) && exclusiveList.size()>=2) {
				
				comb1 = new Combination(exclusiveList, 2);
				
				predicateName+="__";
				for (Mediation med : exclusiveList)
					predicateName+="_"+med.getMemberEnd().get(1).getType().getName();
				
				rules += "\n\tall w:World | all x:w."+relatorName+" | ";
				while(comb1.hasNext()){
					output = comb1.next();
					rules+="#(x.(w."+parser.getAlias(output.get(0))+") & x.(w."+parser.getAlias(output.get(1))+")) = 0";
					
					if(comb1.hasNext())
	            		rules += " and ";
				}
			}
		}
			
		predicate += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
		
		return predicate;
		
	}

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(relator);
	}
	
	
}
