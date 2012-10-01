package br.ufes.inf.nemo.ontouml.antipattern;

import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;

public class STRAlloyGenerator {
	
	public static String ReflexiveAlloyPredicate (String type, String association, String cardinality) {
		String predicate, rules, name;
		
		name = "reflexive_"+association;
		rules = "#" + association + ">" + cardinality;
		rules += "\n\tall w:World | reflexive[w."+ association +", w."+type+"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String IrreflexiveAlloyPredicate (String type, String association, String cardinality){
		String predicate, rules, name;
		
		name = "irreflexive_"+association;
		
		rules = "#" + association + ">" + cardinality; 
		rules += "\n\tall w:World | irreflexive[w."+ association +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	public static String TransitiveAlloyPredicate (String type, String association, String cardinality){
		String predicate, rules, name;
		
		name = "transitive_"+association;
		
		rules = "#" + association + ">" + cardinality; 
		rules += "\n\tall w:World | transitive[w."+ association +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	public static String IntransitiveAlloyPredicate (String type, String association, String cardinality){
		String predicate, rules, name;
		
		name = "instransitive_"+association;
		rules = "#" + association + ">" + cardinality; 
		rules += "\n\tall w:World | all disj x,y,z: w."+ type +" | (y in x.(w."+ association +") and z in y.(w."+ association +") ) implies z not in x.(w."+ association +")";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
		
		return predicate;
	}
	
	public static String SymmetricAlloyPredicate (String type, String association, String cardinality){
		String predicate, rules, name;
		
		name = "symmetric_"+association;
		
		rules = "#" + association + ">" + cardinality; 
		rules += "\n\tall w:World | symmetric[w."+ association +"]";
				
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n"; 
    	
    	return predicate;
	}
	
	public static String AntisymmetricAlloyPredicate (String type, String association, String cardinality){
		String predicate, rules, name;
		
		name = "antisymmetric_"+association;
		rules = "#" + association + ">" + cardinality;
		rules += "\n\tall w:World | antisymmetric[w."+ association +"]";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
			
		return predicate;
	}
	
	
	
	
}
