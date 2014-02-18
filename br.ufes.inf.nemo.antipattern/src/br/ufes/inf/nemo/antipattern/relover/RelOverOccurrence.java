package br.ufes.inf.nemo.antipattern.relover;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RelOverOccurrence extends OverlappingOccurrence{
	
	public RelOverOccurrence(Classifier relator, HashSet<Property> allMediatedEnds, RelOverAntipattern ap) throws Exception {
		super(ap, relator, allMediatedEnds);
		
		//verifies the classifier inputted as relator is a relator or a specialization of one
		if(!(relator instanceof Relator)){
			
			boolean hasRelatorParent = false;
			int i = 0;
			EList<Classifier> allParents = relator.allParents();
			
			while(!hasRelatorParent && i<allParents.size()){
				
				if(allParents.get(i) instanceof Relator)
					hasRelatorParent = true;
				
				i++;
			}
			
			if(!hasRelatorParent)
				throw new Exception(RelOverAntipattern.getAntipatternInfo().getAcronym()+": The provided object is not a Relator and neither specializes one.");
		}
		
		for (Property p : getAllMediatedProperties()) {
			if(!(p.getAssociation() instanceof Mediation))
				throw new Exception(RelOverAntipattern.getAntipatternInfo().getAcronym()+": All provided properties must belong to a mediation.");
		}

		if(!verifyMinimumUpperCardinalitySum(3))
			throw new Exception(RelOverAntipattern.getAntipatternInfo().getAcronym()+": The sum of the upper cardinality is lower than 3.");

	}
		
	public Classifier getRelator() {
		return getMainType();
	}
	
	public HashSet<Property> getAllMediatedProperties() {
		return getAllProperties();
	}
	
	public ArrayList<Mediation> getAllMediations() {
		ArrayList<Mediation> mediations = new ArrayList<Mediation>();
		
		for (Property p : getAllMediatedProperties()) {
			mediations.add((Mediation) p.getAssociation());
		}
		
		return mediations;
	}
	
	public HashSet<Classifier> getAllMediatedTypes(){
		return getAllOverlappingTypes();
	}
	
	@Override
	public String toString() {
		String result;
		
		result = "Relator: "+getParser().getStringRepresentation(getRelator())+
				"\nAll Mediated Types: ";
				
		for (Property p : getAllMediatedProperties())
			result+="\n\t"+getParser().getStringRepresentation(p);
				
		for (OverlappingGroup variation : getVariations()) {
			result+="\n\n"+variation.toString();
		}
		return result;
	}
		
	public String generateExclusivePredicate(OntoUMLParser parser, int cardinality){
		String predicate, rules, predicateName, relatorName;
		ArrayList<Object> saida, mediations = new ArrayList<Object>();
		Combination comb1;
							
		relatorName=parser.getAlias(getRelator());
		
		predicateName = "exclusiveRole_"+relatorName;
		
		//the number of relators must be always greater or equal to 1;
		if(cardinality<=0)
			rules = "#"+relatorName+">=1";
		else
			rules = "#"+relatorName+">="+cardinality;
		
		rules += "\n\tall w:World | all x:w."+relatorName+" | ";

		// Combinacoes de mediations agrupadas 2 a 2
		mediations.addAll(getAllMediations());
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
							
		relatorName=parser.getAlias(getRelator());
		
		predicateName = "nonExclusiveRole_"+relatorName;
		
		//the number of relators must be always greater or equal to 1;
		if(cardinality<=0)
			rules = "#"+relatorName+">=1";
		else
			rules = "#"+relatorName+">="+cardinality;
		
		rules += "\n\tall w:World | all x:w."+relatorName+" | ";

		// Combinacoes de mediations agrupadas 2 a 2
		mediations.addAll(getAllMediations());
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
		
		relatorName=parser.getAlias(getRelator());
		
		predicateName = "MultipleExclusiveRole_"+relatorName;
		rules = "#"+relatorName+">="+cardinality;
		
		for (ArrayList<Mediation> exclusiveList : exclusiveMatrix){
			if (getAllMediations().containsAll(exclusiveList) && exclusiveList.size()>=2) {
				
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
		return "Relator: "+parser.getStringRepresentation(getRelator());
	}
	
	
}
