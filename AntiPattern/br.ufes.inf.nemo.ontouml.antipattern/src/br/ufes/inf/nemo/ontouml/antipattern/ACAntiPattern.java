package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;

import br.ufes.inf.nemo.ontouml.antipattern.mapper.NamesMapper;
import br.ufes.inf.nemo.ontouml.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.ontouml.antipattern.util.SourceTargetAssociation;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Relationship;
import RefOntoUML.Type;

public class ACAntiPattern {
	ArrayList<Class> cycle;
	ArrayList<Relationship> cycleRelationship;
	
	public String generateClosedCyclePredicate(NamesMapper mapper, int cardinality) {
		String predicate, rules, name;
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = mapper.elementsMap.get(this.cycle.get(0));
		
		name = "closedCycle_"+typeName+"_"+mapper.elementsMap.get(cycle.get(1));
		rules = "all w:World | #w." + typeName + ">=" + cardinality;
		rules += "\n\tall w:World | all x:w."+typeName+" | ";
		
		a = (Association)this.cycleRelationship.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.cycle.get(0)))
			rules += "(x.(w."+mapper.elementsMap.get(a)+"))";
		
		else {
			rules += "((w."+mapper.elementsMap.get(a)+").x)";
			last_target = SourceTargetAssociation.getSourceAlloy(a);
			last_source = SourceTargetAssociation.getTargetAlloy(a);
		}
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			 
			if(r instanceof Association){
				source = SourceTargetAssociation.getSourceAlloy((Association)r);
				target = SourceTargetAssociation.getTargetAlloy((Association)r);
				
				if( (source.equals(last_target)) ){
					rules+=".(w."+mapper.elementsMap.get(r)+")";
					last_source = source;
					last_target = target;
				}
				else {
					rules+=".(~(w."+mapper.elementsMap.get(r)+"))";
					last_target = source;
					last_source = target;
				}
				
				name+="_"+mapper.elementsMap.get(last_target);
				
			}
		}
		
		rules += " = x";
		
		predicate = AlloyConstructor.AlloyParagraph(name, rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}
	
	public String generateOpenCyclePredicate(NamesMapper mapper, int cardinality) {
		String predicate, predicate_rules, name, function_name = "cycleaux", function_rules="", function_parameters="x:Object, w:World", function_return="Object";
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = mapper.elementsMap.get(this.cycle.get(0));
		
		name = "openCycle_"+typeName+"_"+mapper.elementsMap.get(cycle.get(1));
		predicate_rules = "all w:World | #w."+typeName+">="+cardinality+" and all x:w."+typeName+" | some "+function_name+"[x,w] and no "+function_name+"[x,w] & x";
		
		a = (Association)this.cycleRelationship.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.cycle.get(0)))
			function_rules += "(x.(w."+mapper.elementsMap.get(a)+"))";
		
		else {
			function_rules += "((w."+mapper.elementsMap.get(a)+").x)";
			last_target = SourceTargetAssociation.getSourceAlloy(a);
			last_source = SourceTargetAssociation.getTargetAlloy(a);
		}
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			
			if(r instanceof Association){
				source = SourceTargetAssociation.getSourceAlloy((Association)r);
				target = SourceTargetAssociation.getTargetAlloy((Association)r);
				
				if( (source.equals(last_target)) ){
					function_rules+=".(w."+mapper.elementsMap.get(r)+")";
					last_source = source;
					last_target = target;
				}
				else {
					function_rules+=".(~(w."+mapper.elementsMap.get(r)+"))";
					last_target = source;
					last_source = target;
				}
				
				name+="_"+mapper.elementsMap.get(last_target);
				
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
		
		
		return result+"\n";
	}
}
