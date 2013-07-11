package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class RBOSAntiPattern extends Antipattern{
	private Classifier supertype;
	private Classifier source;
	private Classifier target;
	private Association association;
	
	public RBOSAntiPattern (Association association) throws Exception{
		this.setAssociation(association);
	}
	
	public String generateIrreflexiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv irreflexive_"+parser.getAlias(association)+" : not (self."+aet_name+"->includes(self))";
	}
	
	public String generateReflexiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv reflexive_"+parser.getAlias(association)+" : self."+aet_name+"->includes(self)";
	}
	
	public String generateSymmetricOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		String aes_name = parser.getAlias(association.getMemberEnd().get(0));
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv symmetric_"+parser.getAlias(association)+"_source : self."+aet_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(1).getType())+" | x."+aes_name+"->includes(self))"+
				
				"context _'"+parser.getAlias(association.getMemberEnd().get(1).getType())+"'\n"+
				"inv symmetric_"+parser.getAlias(association)+"_target : self."+aes_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(0).getType())+" | x."+aet_name+"->includes(self))";
	}
	
	public String generateAntiSymmetricOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));
		String aes_name = parser.getAlias(association.getMemberEnd().get(0));
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv antisymmetric_"+parser.getAlias(association)+"_source : self."+aet_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(1).getType())+" | !(x."+aes_name+"->includes(self)))"+
				
				"context _'"+parser.getAlias(association.getMemberEnd().get(1).getType())+"'\n"+
				"inv antisymmetric_"+parser.getAlias(association)+"_target : self."+aes_name+"->forAll(x : "+parser.getAlias(association.getMemberEnd().get(0).getType())+" | !(x."+aet_name+"->includes(self)))";
	}
	
	public String generateTransitiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));		
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv transitive_" + association.getName() + " : self."+aet_name+"->asSet()->includesAll(self."+aet_name+"."+aet_name+"->asSet())";				
	}
	
	public String generateIntransitiveOcl(OntoUMLParser parser){
		String aet_name = parser.getAlias(association.getMemberEnd().get(1));		
		
		return 	"context _'"+parser.getAlias(association.getMemberEnd().get(0).getType())+"'\n"+
				"inv intransitive_"+association.getName()+ " : self."+aet_name+"->asSet()->excludesAll(self."+aet_name+"."+aet_name+"->asSet())";
				
	}
	
	/*============================= TO DO =====================================*/
	
	public String generateAntisymmetricPredicate(OntoUMLParser parser)
	{ 
		return "";
	}
	public String generateIntransitivePredicate(OntoUMLParser parser)
	{ 
		return "";
	}	
	public String generateSymmetricPredicate(OntoUMLParser parser)
	{ 
		return "";
	}
	public String generateTransitivePredicate(OntoUMLParser parser)
	{ 
		return "";
	}	
	public String generateDisjointPredicate(OntoUMLParser parser)
	{
		return "";
	}
	
	/*Generates an Alloy predicate that produces model instances in which the related elements are always different*/
	public String generateIrreflexivePredicate(OntoUMLParser parser){
		String predicate, rules, predicateName;
		
		String associationName;
		associationName=parser.getAlias(this.association);
		
		predicateName = "disjointParticipants_"+associationName;
		rules = "some "+associationName+"\n\t";
		rules += "no "+associationName+" & (World->iden)";
				
		predicate = AlloyConstructor.AlloyParagraph(predicateName, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(predicateName, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
	}

	/*Generates an Alloy predicate that produces model instances in which the association may have the same participant in both ends*/
	public String generateReflexivePredicate(OntoUMLParser parser){
		String predicate, rules, predicateName;
		
		String associationName;
		associationName=parser.getAlias(this.association);
		
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

	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(source);
		selection.add(target);
		selection.add(association);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
		
	}

	
}
