package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.PackageableElement;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.common.list.ArrayListOperations;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;

public class RWORAntiPattern extends Antipattern{
	private Relator relator;
	private Classifier supertype;
	private HashMap<Mediation, Classifier> mediations;
	
	public RWORAntiPattern(Relator relator, OntoUMLParser parser) throws Exception {
		this.setRelator(relator, parser);
	}
	
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
	
	public String generateExclusiveOcl(ArrayList<Classifier> disjointTypes, OntoUMLParser parser){
		String result = "context "+relator.getName()+"\n"+
						"inv: ";
		if (disjointTypes.size()<2)
			return null;
		
		for (Mediation mediation : this.mediations.keySet()) {
			if(mediation.getMemberEnd().get(0).getType().equals(disjointTypes.get(0)))
				result+="self."+parser.getAlias(mediation.getMemberEnd().get(0));
			if(mediation.getMemberEnd().get(1).getType().equals(disjointTypes.get(0)))
				result+="self."+parser.getAlias(mediation.getMemberEnd().get(1));
		}
		System.out.println(disjointTypes.size());
		for (int i = 1; i<disjointTypes.size(); i++){
			Classifier classifier = disjointTypes.get(i);
			for (Mediation mediation : this.mediations.keySet()) {
				if(mediation.getMemberEnd().get(0).getType().equals(classifier))
					result+="->intersection(self."+parser.getAlias(mediation.getMemberEnd().get(0))+")";
				if(mediation.getMemberEnd().get(1).getType().equals(classifier))
					result+="->intersection(self."+parser.getAlias(mediation.getMemberEnd().get(1))+")";
			}
		}
		result += "->size=0";
		
		return result;
	}
	
	public String generateExclusivePredicate(OntoUMLParser parser, int cardinality){
		String predicate, rules, predicateName, relatorName;
		ArrayList<Object> saida, mediations = new ArrayList<>();
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
		ArrayList<Object> saida, mediations = new ArrayList<>();
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
	
/*	public String generateAllMultipleExclusivePredicate(OntoUMLParser parser, int cardinality){
		String predicates="", rules, predicateName, relatorName;
		ArrayList<Object>  mediations = new ArrayList<>(), output = new ArrayList<>(), output2, aux;
		Combination comb1, comb2;
		
		relatorName=parser.getName(relator);

		mediations.addAll(this.mediations.keySet());
		
		comb1 = new Combination(mediations, 2);
		
		// Generate all the combinations of the mediation 2 by 2
        while (comb1.hasNext()) {
            output.add(comb1.next());
        }
        
        //Generates all the combinations of any size with the comb1 result
        comb2 = new Combination(output,0);
        
        while (comb2.hasNext()) {
            output2=(comb2.next());
            
            predicateName = "MultipleExclusiveRole_"+relatorName;
            rules = "#"+relatorName+">="+cardinality;
            rules += "\n\tall w:World | all x:w."+relatorName+" | "; 

            for (int n=0;n<output2.size();n++){
            	predicateName += n;
            	rules+="# (";
            	for (int n2=0; n2<((ArrayList<Object>)(output2).get(n)).size();n2++) {
	            	
            		rules+="x.(w."+parser.getName((PackageableElement)((ArrayList<Object>) output2.get(n)).get(n2))+")";
	            	
	            	if (n2==((ArrayList<Object>)(output2).get(n)).size()-1)
	            		rules+=") = 0";
	            	else
	            		rules+=" & ";
            	}
            	
            	if(n<output2.size()-1)
            		rules += " and ";
            }
            predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
    		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
        }
        
        return predicates;
	}*/
	
	public Classifier getSupertype() {
		return supertype;
	}
	
	public HashMap<Mediation, Classifier> getMediations() {
		return mediations;
	}
	
	public Relator getRelator() {
		return relator;
	}
	
	public void setRelator(Relator relator, OntoUMLParser parser) throws Exception {
		/*ArrayList which will contain all the mediations of the relator and its supertypes*/
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		
		/*List that will be used to identify the common supertype*/
		ArrayList<Classifier> mediatedTypesAllParents = new ArrayList<>();
		
		/*ArrayList which will contain the relator that was the foundation of the anti-pattern with all its supertypes*/
		ArrayList<Classifier> allRelators = new ArrayList<>();
		Relator aux;
		
		if(relator==null)
			throw new NullPointerException("The provided relator is null.");
		if(!(relator instanceof Relator))
			throw new Exception("The provided object is not a Relator.");	
		
		this.relator=relator;
		
		
		//add the input relator to the list
		allRelators.add(relator);
		//add all supertypes to the list
		allRelators.addAll(parser.getAllParents(relator));
			
		/*TODO considers that all mediations are accordingly to the metamodel wrt its source and target. The mediation operation of the metamodel returns the second member end only.*/
		//creates the list that contains all the mediations of the input relator and its supertypes.
		for (Classifier rel : allRelators) {
			aux = (Relator)rel;
			mediations.addAll(parser.retainSelected(aux.mediations()));
		}
		
		//if there are not at least two mediations, the anti-pattern is not characterized
		if(mediations.size()<2)
			throw new Exception("The input relator does not have enough mediations to characterize the Anti-Pattern");
		
		this.mediations = new HashMap<>();
		
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
	
	@Override
	public String toString() {
		String result;
		
		result = "Relator: "+relator.getName()+"\n";
		result += "Supertype: "+supertype.getName()+"\n";
		
		for (Mediation med : mediations.keySet()) {
			result += mediations.get(med).getName() + " - " + med.getName() + "\n";
		}
		
		return result;
	}

	@Override
	public void setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Mediation c : this.mediations.keySet())
			selection.add(c);
		
		selection.add(relator);
		selection.add(supertype);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		
	}
	
}
