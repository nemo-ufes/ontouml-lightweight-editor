package br.ufes.inf.nemo.ontouml.antipattern;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mode;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

public class RSAlloyGenerator {
	
	public static String subsetAlloyPredicate (Association a1, Association a2, NamesMapper mapper) {
		String predicate, rules, name, a1Name, a2Name;
		
		a1Name = mapper.elementsMap.get(a1);
		a2Name = mapper.elementsMap.get(a2);
				
		name = "subset_"+a1Name+"_"+a2Name;
		rules = "some "+a1Name+"\n\t";
		rules += "some "+a2Name+"\n\t";
		rules += a1Name+" in "+a2Name;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String notSubsetAlloyPredicate (Association a1, Association a2, NamesMapper mapper) {
		String predicate, rules, name, a1Name, a2Name;
		
		a1Name = mapper.elementsMap.get(a1);
		a2Name = mapper.elementsMap.get(a2);
				
		name = "not_subset_"+a1Name+"_"+a2Name;
		rules = "some "+a1Name+"\n\t";
		rules += "some "+a2Name+"\n\t";
		rules += a1Name+" not in "+a2Name;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String disjointAlloyPredicate (Association a1, Association a2, NamesMapper mapper) {
		String predicate, rules, name, a1Name, a2Name;
		
		a1Name = mapper.elementsMap.get(a1);
		a2Name = mapper.elementsMap.get(a2);
				
		name = "disjoint_"+a1Name+"_"+a2Name;
		rules = "some "+a1Name+"\n\t";
		rules += "some "+a2Name+"\n\t";
		rules += "no "+a1Name+" & "+a2Name;
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String redefineAlloyPredicate (Association a1, Association a2, NamesMapper mapper) {
		String predicate, rules, name, a1Name, a2Name, a1SourceName;
		
		a1Name = mapper.elementsMap.get(a1);
		a2Name = mapper.elementsMap.get(a2);
		
		a1SourceName = mapper.elementsMap.get(SourceTargetAssociation.getSourceAlloy(a1));
		
		name = "redefine_"+a1Name+"_"+a2Name;
		rules = "some "+a1SourceName+"\n\t";
		rules += "all w:World, x:w."+a1SourceName+" | x.(w."+a1Name+")=x.(w."+a2Name+")";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	
}
