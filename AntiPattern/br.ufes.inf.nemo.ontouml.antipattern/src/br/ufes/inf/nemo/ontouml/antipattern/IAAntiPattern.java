package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.Combination;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

public class IAAntiPattern {
	private Association association;
	private Classifier source, target;
	
	private ArrayList<Classifier> sourceChildren, targetChildren;
	
	public IAAntiPattern(Association a) throws Exception {
		this.setAssociation(a);
	}

	public String generateImpreciseAbstractionPredicates(NamesMapper mapper) {
		String predicates="", rules, predicateName;
		Combination comb1;
		ArrayList<Object> saida = new ArrayList<>();
		
		String associationName, sourceName, targetName;
		ArrayList<String> sourceChildrenName, targetChildrenName;
		associationName = mapper.elementsMap.get(association);
		sourceName = mapper.elementsMap.get(this.source);
		targetName = mapper.elementsMap.get(this.target);
		
		sourceChildrenName = new ArrayList<>();
		for (Classifier c : this.sourceChildren) {
			sourceChildrenName.add(mapper.elementsMap.get(c));
		}
		
		targetChildrenName = new ArrayList<>();
		for (Classifier c : this.targetChildren) {
			targetChildrenName.add(mapper.elementsMap.get(c));
		}
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((sourceChildrenName.size()>0) && (SourceTargetAssociation.getUpperSourceCardinality(association)==-1 || SourceTargetAssociation.getUpperSourceCardinality(association)>1)) {
			
			comb1 = new Combination(sourceChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+targetName+"_"+associationName;
				rules = "all w:World | #w."+targetName+">=1\n\t";
				rules += "some w:World | all x:w."+targetName+" | #(w."+associationName+").x>1 and ";
				
				for (int n=0;n<this.sourceChildren.size();n++){

		            	if (saida.contains(this.sourceChildren.get(n))){
			            	predicateName += "_"+this.sourceChildren.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "(w."+associationName+").x & w."+this.sourceChildren.get(n);
		            	if (n<this.sourceChildren.size()-1)
		            		rules += " and ";
            	}
				predicates += AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
        		predicates += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n\n";
			}
		}
		
		//Check if there are specializations on the source of the relation and also if the source upper cardinality is unlimited or greater then 1
		if ((targetChildrenName.size()>0) && (SourceTargetAssociation.getUpperTargetCardinality(association)==-1 || SourceTargetAssociation.getUpperTargetCardinality(association)>1)) {
			
			comb1 = new Combination(targetChildrenName, 0);
			while (comb1.hasNext()) {
				saida=(comb1.next());
				predicateName = "imprecise_abstraction_"+sourceName+"_"+associationName;
				rules = "all w:World | #w."+sourceName+">=1\n\t";
				rules += "some w:World | all x:w."+sourceName+" | #x.(w."+associationName+")>1 and ";
				
				for (int n=0;n<targetChildrenName.size();n++){

		            	if (saida.contains(targetChildrenName.get(n))){
			            	predicateName += "_"+targetChildrenName.get(n);
		            		rules += "some ";
		            	}
		            	else
		            		rules += "no ";
		            	rules += "x.(w."+associationName+") & w."+targetChildrenName.get(n);
		            	if (n<targetChildrenName.size()-1)
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

	public void setAssociation(Association association) throws Exception {
		/*TODO check if the association characterizes the antipattern*/
		if(association==null)
			throw new NullPointerException("Association can't be null.");
		
		this.association = association;
		
		
		if(association.getMemberEnd().size()!=2)
			throw new Exception("The provided association must relate exactly two elements. MemberEnd may be null or undefined");
		
		this.source = (Classifier) SourceTargetAssociation.getSourceAlloy(association);
		
		this.sourceChildren = new ArrayList<>();
		//this.sourceChildren.addAll(source.allChildren());
		this.sourceChildren.addAll(source.children());
		
		this.target = (Classifier) SourceTargetAssociation.getTargetAlloy(association);
		
		this.targetChildren = new ArrayList<>();
		/*If the method get all the children, the number of combinations will be too big*/
		//this.targetChildren.addAll(target.allChildren());
		this.targetChildren.addAll(target.children());
		
	}

	public Classifier getSource() {
		return source;
	}

	public ArrayList<Classifier> getSourceChildren() {
		return sourceChildren;
	}

	public Classifier getTarget() {
		return target;
	}

	public ArrayList<Classifier> getTargetChildren() {
		return targetChildren;
	}
	
	@Override
	public String toString() {
		String result;
		int i;
		
		result = source.getName()+" - "+association.getName()+" - "+target.getName()+"\n";
				
		if(sourceChildren.size()>0){
			result += "Source Children: ";
			i = 0;
			for (Classifier child : sourceChildren) {
				result += child.getName();
				if(i<sourceChildren.size()-1)
					result += " : ";
				i++;
			}
		}
		
		if(targetChildren.size()>0){
			if(sourceChildren.size()>0)
				result += "\n";
			result += "Target Children: ";		
			i = 0;
			for (Classifier child : targetChildren) {
				result += child.getName();
				if(i<targetChildren.size()-1)
					result += " : ";
				i++;
			}
		}
		
		return result;
	}

}
