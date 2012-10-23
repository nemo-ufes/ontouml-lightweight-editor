package br.ufes.inf.nemo.ontouml.antipattern.deprecated;

import java.util.ArrayList;
import java.util.Collection;

import RefOntoUML.Association;
import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.Combination;

/*Relation Between Overlapping Subtypes*/
public class RBOSAlloyGenerator {
	
	public static String DisjointParticipantsAlloyPredicate(Association assoc, NamesMapper mapper){
		String predicate, rules, predicateName, assocName;
		
		assocName = mapper.elementsMap.get(assoc);
		
		predicateName = "disjointParticipants_"+assocName;
		rules = "some "+assocName+"\n\t";
		rules += "no "+assocName+" & (World->iden)";
				
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String OverlappingParticipantsAlloyPredicate(Association assoc, NamesMapper mapper){
		String predicate, rules, predicateName, assocName;
		
		assocName = mapper.elementsMap.get(assoc);
		
		predicateName = "overlappingParticipants_"+assocName;
		rules = "some "+assocName+"\n\t";
		rules += "some "+assocName+" & (World->iden)";
				
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
}
