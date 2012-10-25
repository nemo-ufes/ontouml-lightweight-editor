package br.ufes.inf.nemo.ontouml.antipattern.deprecated;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.Combination;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

public class IAAlloyGenerator {

	public static String impreciseAbstractionPredicates(Association a, NamesMapper mapper) {
		Class source = (Class) SourceTargetAssociation.getSourceAlloy(a);
		Class target = (Class) SourceTargetAssociation.getTargetAlloy(a);
		String predicates="", rules, predicateName, sourceName, targetName, assocName=mapper.elementsMap.get(a);
		Combination comb1;
		EList<Classifier> auxChildrenSource = source.children();
		EList<Classifier> auxChildrenTarget = target.children();
		
		ArrayList<Object> childrenSource = new ArrayList<>(), childrenTarget = new ArrayList<>(), saida = new ArrayList<>();
		
		targetName = mapper.elementsMap.get(target);
		sourceName = mapper.elementsMap.get(source);
		
		for (Classifier c : auxChildrenSource)
			childrenSource.add(c);
		for (Classifier c : auxChildrenTarget)
			childrenTarget.add(c);
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((childrenSource.size()>0) && (SourceTargetAssociation.getUpperSourceCardinality(a)==-1 || SourceTargetAssociation.getUpperSourceCardinality(a)>1)) {
			
			comb1 = new Combination(childrenSource, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+targetName+"_"+assocName;
				rules = "all w:World | #w."+targetName+">=1\n\t";
				rules += "some w:World | all x:w."+targetName+" | #(w."+assocName+").x>1 and ";
				
				for (int n=0;n<childrenSource.size();n++){

		            	if (saida.contains(childrenSource.get(n))){
			            	predicateName += "_"+mapper.elementsMap.get(childrenSource.get(n));
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "(w."+assocName+").x & w."+mapper.elementsMap.get(childrenSource.get(n));
		            	if (n<childrenSource.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((childrenTarget.size()>0) && (SourceTargetAssociation.getUpperTargetCardinality(a)==-1 || SourceTargetAssociation.getUpperTargetCardinality(a)>1)) {
			
			comb1 = new Combination(childrenTarget, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+sourceName+"_"+assocName;
				rules = "all w:World | #w."+sourceName+">=1\n\t";
				rules += "some w:World | all x:w."+sourceName+" | #x.(w."+assocName+")>1 and ";
				
				for (int n=0;n<childrenTarget.size();n++){

		            	if (saida.contains(childrenTarget.get(n))){
			            	predicateName += "_"+mapper.elementsMap.get(childrenTarget.get(n));
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "x.(w."+assocName+") & w."+mapper.elementsMap.get(childrenTarget.get(n));
		            	if (n<childrenTarget.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		return predicates;
	}
	
}
