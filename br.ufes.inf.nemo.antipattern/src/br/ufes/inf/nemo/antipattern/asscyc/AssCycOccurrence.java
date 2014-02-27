package br.ufes.inf.nemo.antipattern.asscyc;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/*Association Cycle Anti-Pattern*/
public class AssCycOccurrence extends AntipatternOccurrence{
	
	ArrayList<Class> cycle;
	ArrayList<Relationship> cycleRelationship;
	public static int OPEN=0, CLOSED=1;
	
	public ArrayList<Relationship> getCycleRelationship() {
		return cycleRelationship;
	}
	
	public String generateCycleOcl(int type, OntoUMLParser parser) {
		String rule, typeName;
		Association a;
		Type last_target;
				
		typeName = this.cycle.get(0).getName();
				
		rule = 	"\n    self.";
		
		String invName = new String();
		
		if(type==OPEN) invName +=  "openCycle";
		else invName +=  "closedCycle";			
		
		a = (Association)this.cycleRelationship.get(0);
		
		invName += "_"+a.getName();
		
		if (a.getMemberEnd().get(0).getType().equals(this.cycle.get(0))) {
			rule += a.getMemberEnd().get(1).getName();
			last_target = a.getMemberEnd().get(1).getType();
		}
		else{ 
			rule += a.getMemberEnd().get(0).getName();
			last_target = a.getMemberEnd().get(0).getType();
		}
			
		
		for (int i = 1; i<cycleRelationship.size();i++) {
			Relationship r = cycleRelationship.get(i);
			 
			if(r instanceof Association){
				
				invName+= "_"+((Association)r).getName();
				
				if( ((Association)r).getMemberEnd().get(0).getType().equals(last_target)) {
					rule += "."+((Association)r).getMemberEnd().get(1).getName();
					last_target =((Association)r).getMemberEnd().get(1).getType();
				}
				else {
					rule += "."+((Association)r).getMemberEnd().get(0).getName();
					last_target =((Association)r).getMemberEnd().get(0).getType();
				}
					
			}
		}
		
		if(type==CLOSED)
			rule += "->asSet() = self->asSet()";
		else if(type==OPEN)
			rule += "->excludes(self)";
		else
			return null;
			
		String result = "context _'"+ typeName + "'\n"+
						"inv "+invName+ " : " + rule;
				
		return result;
	}	
	
	public String generatePredicate(OntoUMLParser parser, int cardinality, int type) throws Exception {
		String predicate, predicate_rules, name, function_name = "CycleAux", function_rules="", function_parameters="x:univ, w:World", function_return="univ";
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = parser.getAlias(this.cycle.get(0));
		
		if(type==OPEN) {
			name = "openCycle_"+typeName+"_"+parser.getAlias(cycle.get(1));
			function_name = "open"+function_name;
		}
		else if(type==CLOSED) {
			name = "closedCycle_"+typeName+"_"+parser.getAlias(cycle.get(1));
			function_name = "closed"+function_name;
		}
		else
			throw new Exception("There may only be open and closed cycles.");
		
		predicate_rules = 	"#"+typeName+">="+cardinality+"\n\t" +
							"all w:World, x:w."+typeName+" | some "+function_name+"[x,w] ";
		
		
		if (type==OPEN)
			predicate_rules+="and no "+function_name+"[x,w] & x";
		if(type==CLOSED)
			predicate_rules+="implies "+function_name+"[x,w] = x";
		
		a = (Association)this.cycleRelationship.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.cycle.get(0)))
			function_rules += "(x.(w."+parser.getAlias(a)+"))";
		
		else {
			function_rules += "((w."+parser.getAlias(a)+").x)";
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
					function_rules+=".(w."+parser.getAlias(assoc)+")";
					last_source = source;
					last_target = target;
				}
				else {
					function_rules+=".(~(w."+parser.getAlias(assoc)+"))";
					last_target = source;
					last_source = target;
				}
				
				name+="_"+parser.getAlias(last_target);
				
			}
		}
		
		predicate = AlloyConstructor.AlloyFunction(function_name, function_rules, function_parameters, function_return)+"\n";
		predicate += AlloyConstructor.AlloyParagraph(name, predicate_rules, AlloyConstructor.PRED);
		predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
		
		return predicate;
		
	}
	
	public AssCycOccurrence(ArrayList<Class> cycle, ArrayList<Relationship> cycleRelationship, AssCycAntipattern ap){
		super(ap);
		setCycle(cycle);
		setCycleRelationship(cycleRelationship);
	}
	
	public ArrayList<Class> getCycle() {
		return cycle;
	}
	
	private void setCycle(ArrayList<Class> cycle) {
		this.cycle = cycle;
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

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Class c : this.cycle)
			selection.add(c);
		for (Relationship r : this.cycleRelationship)
			selection.add(r);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}

	@Override
	public String getShortName() {
		return (parser.getStringRepresentation(cycle.get(0))+" ... "+parser.getStringRepresentation(cycle.get(cycle.size()-1)));
	}

	/*
	public String generateClosedCyclePredicate(OntoUMLParser mapper, int cardinality) 
	{
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
	}*/
	
	
	//==================
	// OUTCOMING FIXES
	//==================
	
	public void forbidCycle() 
	{
		for(Relationship rel: getCycleRelationship()){
			Property src = ((Association)rel).getMemberEnd().get(0);
			Property tgt = ((Association)rel).getMemberEnd().get(1);
			fixer.fixPropertyName(src);
			fixer.fixPropertyName(tgt);
		}
		fix.includeRule(generateCycleOcl(OPEN, parser));		
	}

	public void deriveAssociation(Association assoc) {
		
	}

	public void enforceCycle() 
	{
		for(Relationship rel: getCycleRelationship()){
			Property src = ((Association)rel).getMemberEnd().get(0);
			Property tgt = ((Association)rel).getMemberEnd().get(1);
			fixer.fixPropertyName(src);
			fixer.fixPropertyName(tgt);
		}
		fix.includeRule(generateCycleOcl(CLOSED, parser));
	}
}
