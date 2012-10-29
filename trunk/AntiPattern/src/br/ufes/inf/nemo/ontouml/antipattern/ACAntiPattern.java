package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.AssociationEndNameGenerator;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.Relationship;
import RefOntoUML.Type;

/*Association Cycle Anti-Pattern*/
public class ACAntiPattern {
	ArrayList<Class> cycle;
	ArrayList<Relationship> cycleRelationship;
	static int OPEN=0, CLOSED=1;
	
	public String generateClosedCyclePredicate(OntoUMLParser mapper, int cardinality) {
		String predicate, rules, name;
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = mapper.getName(this.cycle.get(0));
		
		name = "closedCycle_"+typeName+"_"+mapper.getName(cycle.get(1));
		rules = "all w:World | #w." + typeName + ">=" + cardinality;
		rules += "\n\tall w:World | all x:w."+typeName+" | ";
		
		a = (Association)this.cycleRelationship.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.cycle.get(0)))
			rules += "(x.(w."+mapper.getName(a)+"))";
		
		else {
			rules += "((w."+mapper.getName(a)+").x)";
			last_target = SourceTargetAssociation.getSourceAlloy(a);
			last_source = SourceTargetAssociation.getTargetAlloy(a);
		}
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			 
			if(r instanceof Association){
				Association assoc = (Association)r;
				source = SourceTargetAssociation.getSourceAlloy((Association)r);
				target = SourceTargetAssociation.getTargetAlloy((Association)r);
				r = (Association)r;
				if( (source.equals(last_target)) ){
					rules+=".(w."+mapper.getName(assoc)+")";
					last_source = source;
					last_target = target;
				}
				else {
					rules+=".(~(w."+mapper.getName(assoc)+"))";
					last_target = source;
					last_source = target;
				}
				
				name+="_"+mapper.getName(last_target);
				
			}
		}
		
		rules += " = x";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}
	
	public String generateCycleOcl(int type) {
		String rule, typeName;
		Association a;
		Type last_target;
				
		typeName = this.cycle.get(0).getName();
		
		rule = 	"context "+ typeName + "\n"+
				"inv : self.";
		
		a = (Association)this.cycleRelationship.get(0);
		
		if (a.getMemberEnd().get(0).getType().equals(this.cycle.get(0))) {
			rule += AssociationEndNameGenerator.associationEndName(a.getMemberEnd().get(1));
			last_target = a.getMemberEnd().get(1).getType();
		}
		else{ 
			rule += AssociationEndNameGenerator.associationEndName(a.getMemberEnd().get(0));
			last_target = a.getMemberEnd().get(0).getType();
		}
			
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			 
			if(r instanceof Association){
				
				if( ((Association)r).getMemberEnd().get(0).getType().equals(last_target)) {
					rule += "."+AssociationEndNameGenerator.associationEndName(((Association)r).getMemberEnd().get(1));
					last_target =((Association)r).getMemberEnd().get(1).getType();
				}
				else {
					rule += "."+AssociationEndNameGenerator.associationEndName(((Association)r).getMemberEnd().get(0));
					last_target =((Association)r).getMemberEnd().get(0).getType();
				}
					
			}
		}
		
		if(type==CLOSED)
			rule += " = self";
		else if(type==OPEN)
			rule += " != self";
		else
			return null;
			
		return rule;
	}	
	
	public String generateOpenCyclePredicate(OntoUMLParser mapper, int cardinality) {
		String predicate, predicate_rules, name, function_name = "cycleaux", function_rules="", function_parameters="x:Object, w:World", function_return="Object";
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = mapper.getName(this.cycle.get(0));
		
		name = "openCycle_"+typeName+"_"+mapper.getName(cycle.get(1));
		predicate_rules = "all w:World | #w."+typeName+">="+cardinality+" and all x:w."+typeName+" | some "+function_name+"[x,w] and no "+function_name+"[x,w] & x";
		
		a = (Association)this.cycleRelationship.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.cycle.get(0)))
			function_rules += "(x.(w."+mapper.getName(a)+"))";
		
		else {
			function_rules += "((w."+mapper.getName(a)+").x)";
			last_target = SourceTargetAssociation.getSourceAlloy(a);
			last_source = SourceTargetAssociation.getTargetAlloy(a);
		}
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			
			if(r instanceof Association){
				source = SourceTargetAssociation.getSourceAlloy((Association)r);
				target = SourceTargetAssociation.getTargetAlloy((Association)r);
				Association assoc = (Association)r;
				
				if( (source.equals(last_target)) ){
					function_rules+=".(w."+mapper.getName(assoc)+")";
					last_source = source;
					last_target = target;
				}
				else {
					function_rules+=".(~(w."+mapper.getName(assoc)+"))";
					last_target = source;
					last_source = target;
				}
				
				name+="_"+mapper.getName(last_target);
				
			}
		}
		
		predicate = AlloyConstructor.AlloyFunction(function_name, function_rules, function_parameters, function_return)+"\n";
		predicate += AlloyConstructor.AlloyParagraph(name, predicate_rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}
	
	public ACAntiPattern(ArrayList<Class> cycle, ArrayList<Relationship> cycleRelationship){
		setCycle(cycle);
		setCycleRelationship(cycleRelationship);
	}
	
	public ArrayList<Class> getCycle() {
		return cycle;
	}
	
	private void setCycle(ArrayList<Class> cycle) {
		this.cycle = cycle;
	}
	
	public ArrayList<Relationship> getCycle_relationship() {
		return cycleRelationship;
	}
	
	private void setCycleRelationship(ArrayList<Relationship> cycleRelationships) {
		this.cycleRelationship = cycleRelationships;
	}
	
	@Override
	public String toString() {
		String result="";
		int i=0;
		for (Class a : cycle) {
			result += a.getName();
			
			if(i<cycle.size()-1)
				result+=", ";
			
			i++;
		}
		result+="\n";
		for (Relationship r : cycleRelationship) {
			if (r instanceof Association)
				result += ((Association) r).getMemberEnd().get(0).getType().getName()+" - "+((Association) r).getName()+" - "+ ((Association) r).getMemberEnd().get(1).getType().getName()+"\n";
			
			if (r instanceof Generalization)
				result += ((Generalization) r).getSpecific().getName()+" -> "+ ((Generalization) r).getGeneral().getName()+"\n";
		}
		
		
		return result;
	}
}
