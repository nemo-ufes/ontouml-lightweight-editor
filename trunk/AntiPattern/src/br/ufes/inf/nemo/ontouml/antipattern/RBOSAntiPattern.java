package br.ufes.inf.nemo.ontouml.antipattern;

import org.eclipse.emf.common.util.EList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.AssociationEndNameGenerator;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;
import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Type;

public class RBOSAntiPattern {
	private Classifier supertype;
	private Classifier source;
	private Classifier target;
	private Association association;
	
	public RBOSAntiPattern (Association association) throws Exception{
		this.setAssociation(association);
	}
	
	public String generateIrreflexiveOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv irreflexive_"+(association.getName().trim())+" : not (self."+aet_name+"->includes(self))";
	}
	
	public String generateReflexiveOcl(){
		String aet_name = AssociationEndNameGenerator.associationEndName(association.getMemberEnd().get(1));
		return 	"context "+association.getMemberEnd().get(0).getType().getName()+"\n"+
				"inv reflexive_"+(association.getName().trim())+" : self."+aet_name+"->includes(self)";
	}
	
	/*Generates an Alloy predicate that produces model instances in which the related elements are always different*/
	public String generateIrreflexivePredicate(OntoUMLParser mapper){
		String predicate, rules, predicateName;
		
		String associationName;
		associationName=mapper.getName(this.association);
		
		predicateName = "disjointParticipants_"+associationName;
		rules = "some "+associationName+"\n\t";
		rules += "no "+associationName+" & (World->iden)";
				
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}

	/*Generates an Alloy predicate that produces model instances in which the association may have the same participant in both ends*/
	public String generateReflexivePredicate(OntoUMLParser mapper){
		String predicate, rules, predicateName;
		
		String associationName;
		associationName=mapper.getName(this.association);
		
		predicateName = "overlappingParticipants_"+associationName;
		rules = "some "+associationName+"\n\t";
		rules += "some "+associationName+" & (World->iden)";
				
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}
	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) throws Exception {
		EList<Classifier> parentsSource, parentsTarget;

		if(association==null)
			throw new NullPointerException("Association is null");
		if(association.getMemberEnd().size()!=2)
			throw new Exception("The number of memberEnds should be 2");
		if(!(association.getMemberEnd().get(0).getType() instanceof Type))
			throw new Exception("Source type undefined or null");
		if(!(association.getMemberEnd().get(1).getType() instanceof Type))
			throw new Exception("Target type undefined or null");
		
		this.association = association;
		this.source=(Classifier) SourceTargetAssociation.getSourceAlloy(association);
		this.target=(Classifier) SourceTargetAssociation.getTargetAlloy(association);
		
		parentsSource=source.allParents();
		parentsTarget=target.allParents();
		this.supertype=null;
		for (Classifier c : parentsSource) {
			if(parentsTarget.contains(c) || target.equals(c)){
				this.supertype=c;
				break;
			}
		}
		
		for (Classifier c : parentsTarget) {
			if(parentsSource.contains(c) || source.equals(c)){
				this.supertype=c;
				break;
			}
		}
		 
		/*TODO improve the test to guarantee that the provided association characterizes an antipattern*/
		if(supertype==null)
			throw new Exception("No common supertype for the association's related types");
	}
	
	public Classifier getTarget() {
		return target;
	}

	public Classifier getSource() {
		return source;
	}

	public Classifier getSupertype() {
		return supertype;
	}

	@Override
	public String toString() {
		String result;

		result= "Supertype: "+supertype.getName()+"\n";
		result+= this.source.getName()+" - "+association.getName()+" - "+this.target.getName();
		return result;
	}

	
}
