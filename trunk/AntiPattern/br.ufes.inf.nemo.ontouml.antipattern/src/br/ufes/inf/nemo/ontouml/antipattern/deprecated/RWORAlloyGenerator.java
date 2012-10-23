package br.ufes.inf.nemo.ontouml.antipattern.deprecated;

import java.util.ArrayList;

import RefOntoUML.Mediation;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.Combination;

/*Relator With Overlapping Roles*/
public class RWORAlloyGenerator {
	
	public static String ExclusiveRolesAlloyPredicate(Relator relator, NamesMapper mapper){
		String predicate, rules, predicateName, relatorName;
		ArrayList<Object> saida;
		ArrayList<Object> mediations = new ArrayList<>();
		Combination comb1;
		
		relatorName = mapper.elementsMap.get(relator);
		
		for (Mediation med : relator.mediations()) {
	        	mediations.add(mapper.elementsMap.get(med));
	    }
		
		comb1 = new Combination(mediations, 2);
		
		predicateName = "exclusiveRole_"+relatorName;
		rules = "some w:World | some x:w."+relatorName+" | ";
		
		// Combinacoes de mediations agrupadas 2 a 2
        while (comb1.hasNext()) {
            saida = comb1.next();
            rules+="# (x.(w."+saida.get(0)+") & x.(w."+saida.get(1)+")) = 0";
            
            if(comb1.hasNext())
            	rules+=" and ";
        }
		
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String NonExclusiveRolesAlloyPredicate(Relator relator, NamesMapper mapper){
		String predicate, rules, predicateName, relatorName;
				
		relatorName = mapper.elementsMap.get(relator);
		predicateName = "nonExclusiveRole_"+relatorName;
		
		rules = "some w:World | some x:w."+relatorName+" | # (";
		
		for (int count=0; count < relator.mediations().size(); count++){
			 rules+="x.(w."+mapper.elementsMap.get(relator.mediations().get(count))+")"; 
			 
			 if (count < relator.mediations().size()-1)
				 rules += " & ";
	    }
			
		rules+=") > 0";
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public static String MultipleExclusiveRolesAlloyPredicate(Relator relator, NamesMapper mapper){
		String predicates="", rules, predicateName, relatorName;
		ArrayList<Object>  mediations = new ArrayList<>(), saida = new ArrayList<>(), saida2, aux;
		Combination comb1, comb2;
		
		relatorName = mapper.elementsMap.get(relator);
		
		for (Mediation med : relator.mediations()) {
	        	mediations.add(mapper.elementsMap.get(med));
	    }
		
		comb1 = new Combination(mediations, 2);
		
		// Combinacoes de mediations agrupadas 2 a 2
        while (comb1.hasNext()) {
            saida.add(comb1.next());
        }
        
        comb2 = new Combination(saida,0);
        
        while (comb2.hasNext()) {
            saida2=(comb2.next());
            
            predicateName = "MultipleExclusiveRole_"+relatorName;
            rules = "some w:World | some x:w."+relatorName+" | "; 

            for (int n=0;n<saida2.size();n++){
            	predicateName += n;
            	rules+="# (";
            	for (int n2=0; n2<((ArrayList<Object>)(saida2).get(n)).size();n2++) {
	            	
            		rules+="x.(w."+((ArrayList<Object>) saida2.get(n)).get(n2)+")";
	            	
	            	if (n2==((ArrayList<Object>)(saida2).get(n)).size()-1)
	            		rules+=") = 0";
	            	else
	            		rules+=" & ";
            	}
            	
            	if(n<saida2.size()-1)
            		rules += " and ";
            }
            predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
    		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
        }
        
        return predicates;
	}

}
