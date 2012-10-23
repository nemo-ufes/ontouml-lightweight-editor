package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.Combination;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

public class IAAntiPattern {
	private Association association;
	private Classifier source, target;
	private String associationName, sourceName, targetName;
	private ArrayList<Classifier> sourceChildren, targetChildren;
	private ArrayList<String> sourceChildrenName, targetChildrenName;
	
	public IAAntiPattern(Association a, NamesMapper mapper) throws Exception {
		this.setAssociation(a,mapper);
	}

	public String generateImpreciseAbstractionPredicates() {
		String predicates="", rules, predicateName;
		Combination comb1;
		ArrayList<Object> saida = new ArrayList<>();
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((this.sourceChildrenName.size()>0) && (SourceTargetAssociation.getUpperSourceCardinality(association)==-1 || SourceTargetAssociation.getUpperSourceCardinality(association)>1)) {
			
			comb1 = new Combination(this.sourceChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+this.targetName+"_"+this.associationName;
				rules = "all w:World | #w."+this.targetName+">=1\n\t";
				rules += "some w:World | all x:w."+this.targetName+" | #(w."+this.associationName+").x>1 and ";
				
				for (int n=0;n<this.sourceChildren.size();n++){

		            	if (saida.contains(this.sourceChildren.get(n))){
			            	predicateName += "_"+this.sourceChildren.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "(w."+this.associationName+").x & w."+this.sourceChildren.get(n);
		            	if (n<this.sourceChildren.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((this.targetChildrenName.size()>0) && (SourceTargetAssociation.getUpperTargetCardinality(association)==-1 || SourceTargetAssociation.getUpperTargetCardinality(association)>1)) {
			
			comb1 = new Combination(this.targetChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+sourceName+"_"+this.associationName;
				rules = "all w:World | #w."+sourceName+">=1\n\t";
				rules += "some w:World | all x:w."+sourceName+" | #x.(w."+this.associationName+")>1 and ";
				
				for (int n=0;n<this.targetChildrenName.size();n++){

		            	if (saida.contains(this.targetChildrenName.get(n))){
			            	predicateName += "_"+this.targetChildrenName.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "x.(w."+this.associationName+") & w."+this.targetChildrenName.get(n);
		            	if (n<this.targetChildrenName.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		return predicates;
	}
	
	
	public Association getAssociation() {
		return association;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociation(Association association, NamesMapper mapper) throws Exception {
		/*TODO check if the association characterizes the antipattern*/
		if(association==null)
			throw new NullPointerException("Association can't be null.");
		
		this.association = association;
		this.associationName = mapper.elementsMap.get(association);
		
		if(association.getMemberEnd().size()!=2)
			throw new Exception("The provided association must relate exactly two elements. MemberEnd may be null or undefined");
		
		this.source = (Classifier) SourceTargetAssociation.getSourceAlloy(association);
		
		this.sourceName = mapper.elementsMap.get(this.source);
		
		this.sourceChildren = new ArrayList<>();
		this.sourceChildren.addAll(source.allChildren());
		
		this.sourceChildrenName = new ArrayList<>();
		for (Classifier c : this.sourceChildren) {
			this.sourceChildrenName.add(mapper.elementsMap.get(c));
		}
		
		this.target = (Classifier) SourceTargetAssociation.getTargetAlloy(association);
		
		this.targetName = mapper.elementsMap.get(this.target);
		
		this.targetChildren = new ArrayList<>();
		this.targetChildren.addAll(target.allChildren());
		
		this.targetChildrenName = new ArrayList<>();
		for (Classifier c : this.targetChildren) {
			this.targetChildrenName.add(mapper.elementsMap.get(c));
		}
		
	}

	public Classifier getSource() {
		return source;
	}

	public String getSourceName() {
		return sourceName;
	}

	public ArrayList<Classifier> getSourceChildren() {
		return sourceChildren;
	}

	public Classifier getTarget() {
		return target;
	}

	public String getTargetName() {
		return this.targetName;
	}

	public ArrayList<Classifier> getTargetChildren() {
		return targetChildren;
	}
	
	@Override
	public String toString() {
		String result;
		int i;
		
		result = this.sourceName+" - "+this.associationName+" - "+this.targetName+"\n";
		result += "Source Children: ";
		
		if(sourceChildrenName.size()>0){
			i = 0;
			for (String name : sourceChildrenName) {
				result += name;
				if(i<sourceChildrenName.size()-1)
					result += " : ";
				i++;
			}
		}
		
		if(targetChildrenName.size()>0){
			result += "\nTarget Children: ";		
			i = 0;
			for (String name : targetChildrenName) {
				result += name;
				if(i<targetChildrenName.size()-1)
					result += " : ";
				i++;
			}
		}
		
		return result;
	}

}
