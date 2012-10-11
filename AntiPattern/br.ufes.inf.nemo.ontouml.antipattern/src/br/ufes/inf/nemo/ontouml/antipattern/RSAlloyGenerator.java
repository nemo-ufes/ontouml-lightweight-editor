package br.ufes.inf.nemo.ontouml.antipattern;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

public class RSAlloyGenerator {
	
	public static String subsetAlloyPredicate (Association specificAssociation, Association generealAssociation, NamesMapper mapper) {
		String predicate, rules, name, specificName, generalName;
		
		specificName = mapper.elementsMap.get(specificAssociation);
		generalName = mapper.elementsMap.get(generealAssociation);
				
		name = "subset_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += specificName+" in "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String notSubsetAlloyPredicate (Association specificAssociation, Association generealAssociation, NamesMapper mapper) {
		String predicate, rules, name, specificName, generalName;
		
		specificName = mapper.elementsMap.get(specificAssociation);
		generalName = mapper.elementsMap.get(generealAssociation);
				
		name = "not_subset_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += specificName+" not in "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String disjointAlloyPredicate (Association specificAssociation, Association generealAssociation, NamesMapper mapper) {
		String predicate, rules, name, specificName, generalName;
		
		specificName = mapper.elementsMap.get(specificAssociation);
		generalName = mapper.elementsMap.get(generealAssociation);
				
		name = "disjoint_"+specificName+"_"+generalName;
		rules = "some "+specificName+"\n\t";
		rules += "some "+generalName+"\n\t";
		rules += "no "+specificName+" & "+generalName;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String redefineAlloyPredicate (Association specificAssociation, Association generalAssociation, NamesMapper mapper) {
		String predicate, rules, name, generalName, specificName, typeName;
		Classifier specificSource,specificTarget,generalSource,generalTarget;
		specificName = mapper.elementsMap.get(specificAssociation);
		generalName = mapper.elementsMap.get(generalAssociation);
		
		name = "redefine_"+specificName+"_"+generalName;
				
		specificSource=(Classifier) SourceTargetAssociation.getSourceAlloy(specificAssociation);
		specificTarget=(Classifier) SourceTargetAssociation.getTargetAlloy(specificAssociation);
		generalSource=(Classifier) SourceTargetAssociation.getSourceAlloy(generalAssociation);
		generalTarget=(Classifier) SourceTargetAssociation.getTargetAlloy(generalAssociation);
		
		System.out.println("SpecificSource: "+mapper.elementsMap.get(specificSource));
		System.out.println("SpecificTarget: "+mapper.elementsMap.get(specificTarget));
		System.out.println("GeneralSource: "+mapper.elementsMap.get(generalSource));
		System.out.println("GeneralTarget: "+mapper.elementsMap.get(generalTarget));
		
		if (specificSource.allParents().contains(generalSource) || specificSource.allParents().contains(generalTarget)){
			typeName = mapper.elementsMap.get(specificSource);
			rules = "some "+typeName+"\n\t";
			rules += "all w:World, x:w."+typeName+" | x.(w."+specificName+")=x.(w."+generalName+")";
		}
		else{
			typeName = mapper.elementsMap.get(specificTarget);
			rules = "some "+typeName+"\n\t";
			rules += "all w:World, x:w."+typeName+" | (w."+specificName+").x=(w."+generalName+").x";
		}
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	
}
