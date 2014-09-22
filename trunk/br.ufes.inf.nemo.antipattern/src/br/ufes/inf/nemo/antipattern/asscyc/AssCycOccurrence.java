package br.ufes.inf.nemo.antipattern.asscyc;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.antipattern.util.SourceTargetAssociation;

/*Association Cycle Anti-Pattern*/
public class AssCycOccurrence extends AntipatternOccurrence{
	
	ArrayList<Class> classCycle;
	private ArrayList<Class> associationClassList;
	ArrayList<Relationship> relationshipCycle;
	ArrayList<Association> associationList;

	public static int OPEN=0, CLOSED=1;
	
	public AssCycOccurrence(ArrayList<Class> cycle, ArrayList<Relationship> cycleRelationship, AssCycAntipattern ap) throws Exception{
		super(ap);
		
		this.classCycle = new ArrayList<Class>(cycle);
		this.relationshipCycle = new ArrayList<Relationship>(cycleRelationship);
		
		this.associationList = new ArrayList<Association>();
		for (Relationship r : cycleRelationship) {
			if(r instanceof Association)
				associationList.add((Association) r);
		}
		
		if(associationList.size()<=2)
			throw new Exception("AssCyc: Cycle must be composed by 3 or more associations");
		
		associationClassList = new ArrayList<Class>();
		
		Type source = associationList.get(0).getMemberEnd().get(0).getType();
		Type target = associationList.get(0).getMemberEnd().get(1).getType();
		
		getAssociationClassList().add((Class) source);
		getAssociationClassList().add((Class) target);
		
		Type lastTarget = target;
		for (int i = 1; i<associationList.size(); i++) {
			
			source = associationList.get(i).getMemberEnd().get(0).getType();
			target = associationList.get(i).getMemberEnd().get(1).getType();
			
			if(lastTarget.equals(source)){
				getAssociationClassList().add((Class) target);
				lastTarget = target;
			}
			else{
				getAssociationClassList().add((Class) source);
				lastTarget = source;
			}
				
		}
	}
	
	
		
	@Override
	public String toString() {
		String result=	"Cycle Size: "+this.classCycle.size()+"\r\n\r\n" +
						"Classes: ";
		
		ArrayList<Edge> aligned = getAlignedPropertiesFrom(associationList.get(0),associationList.get(0).getMemberEnd().get(0));
		
		for (int i = 0; i < aligned.size(); i++) {	
			result += OntoUMLNameHelper.getTypeAndName(aligned.get(i).getSource(), true, false);
			
			if(i<classCycle.size()-2)
				result+=", ";
			else if(i==classCycle.size()-2)
				result+=" and ";

		}
		result+="\r\nRelationships:";
		
		for (Edge e : aligned) {
			String separator = "";
			if(e.isAssociation())
				separator = "-";
			else
				separator = "->";
			
			result+="\r\n\t"+OntoUMLNameHelper.getTypeAndName(e.getRelationship(), false, false)+
					" ["+OntoUMLNameHelper.getTypeAndName(e.getSource(), true, false)+" "+separator+" "+OntoUMLNameHelper.getTypeAndName(e.getTarget(), true, false)+"]";
				
		}
		
		return result;
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		for (Class c : this.classCycle)
			selection.add(c);
		for (Relationship r : this.relationshipCycle)
			selection.add(r);
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}

	@Override
	public String getShortName() {
		return (OntoUMLNameHelper.getTypeAndName(classCycle.get(0), true, false)+" ... "+OntoUMLNameHelper.getTypeAndName(classCycle.get(classCycle.size()-1), true, false));
	}	
	
	//==================
	// OUTCOMING FIXES
	//==================
	
	public void deriveAssociation(Association assoc) {
		fixPropertiesNames();
		fix.includeRule(generateDerivation(assoc));
	}

	public void forbidCycle() 
	{
		fixPropertiesNames();
		fix.includeRule(generateCycleOcl(associationList.get(0),OPEN));		
	}
	
	public void enforceCycle() 
	{
		fixPropertiesNames();
		fix.includeRule(generateCycleOcl(associationList.get(0),CLOSED));
	}

	private void fixPropertiesNames() {
		for(Association a: getOnlyAssociations()){
			Property src = a.getMemberEnd().get(0);
			Property tgt = a.getMemberEnd().get(1);
			fix.addAll(fixer.fixPropertyName(src));
			fix.addAll(fixer.fixPropertyName(tgt));
		}
	}
	
	public String getClassCycleString(){
		String result = "";
		
		for (int i = 0; i < classCycle.size(); i++) {
			
			if (i==classCycle.size()-1){
				result += " and "+OntoUMLNameHelper.getTypeAndName(classCycle.get(i), true, true);
			}
			else if (i!=0){
				result += ", ";
			}
			
			result += OntoUMLNameHelper.getTypeAndName(classCycle.get(i), true, true);
		}
		
		return result;
	}

	public ArrayList<Association> getOnlyAssociations() {
		return associationList;
	}

	public ArrayList<Class> getAssociationClassList() {
		return associationClassList;
	}
	
	public ArrayList<Class> getCycle() {
		return classCycle;
	}
	
	public ArrayList<Relationship> getCycleRelationship() {
		return relationshipCycle;
	}
	
	public String generateDerivation(Association assoc){
		Property source = assoc.getMemberEnd().get(0);
		Property target = assoc.getMemberEnd().get(1);
		
		ArrayList<Edge> aligned = getAlignedPropertiesFrom(assoc, target);
		String result = "context "+addQuotes(source.getType().getName())+"::"+addQuotes(target.getName())+":";
		
		if (target.getUpper()>1 || target.getUpper()==-1)
			result+= "Set("+addQuotes(target.getType().getName())+")";
		else
			result+= addQuotes(target.getType().getName());
		
		result+="\nderive: self";
		
		for (int i = 0; i < aligned.size(); i++) {
			Edge e = aligned.get(i);
			if(e.isAssociation()){
				result+="."+addQuotes(e.p.getName());
			}
			else{
				if(aligned.get((i+1)%aligned.size()).isAssociation())
					result += ".oclAsType("+addQuotes(e.c.getName())+")";
			}
		}
		
		return result;
	}
	
	public String generateCycleOcl(Association assoc, int type){
		Property source = assoc.getMemberEnd().get(0);
		ArrayList<Edge> aligned = getAlignedPropertiesFrom(assoc, source);
		
		String invName = "";
		String finalExpression = "";
		String exp = "";
		
		if(type==OPEN){
			invName = "acyclic";
			finalExpression = "->excludes(self)";
		}
		else{
			invName = "cyclic";
			finalExpression = "->includes(self)";
		}

		for (int i = 0; i < aligned.size(); i++) {
			Edge e = aligned.get(i);
			if(e.isAssociation()){
				exp+="."+addQuotes(e.p.getName());
			}
			else{
				if(aligned.get((i+1)%aligned.size()).isAssociation())
					exp += ".oclAsType("+addQuotes(e.c.getName())+")";
			}
		}
	
		return 	"context "+addQuotes(source.getType().getName())+"\n"+
				"inv "+invName+" : self"+exp+finalExpression;
	}
	
//	private int getPrevious(int i){
//		if(i<0 || i>=relationshipCycle.size())
//			return -1;
//		
//		if(i-1 >= 0) return i-1;
//		else return relationshipCycle.size()-1;
//	}
//	
//	public String generateDerivation(Association assoc)
//	{
//		String result = new String();
//				
//		Property firstSourceEnd = assoc.getMemberEnd().get(0);
//		Property firstTargetEnd = assoc.getMemberEnd().get(1);
//		Property source = null, target = null;
//		int index = relationshipCycle.indexOf(assoc);
//		int i = getPrevious(index);
//		
//		
//		if(relationshipCycle.get(getPrevious(index)) instanceof Association){
//			Association a = (Association) relationshipCycle.get(getPrevious(index));
//			Property sourceEnd = a.getMemberEnd().get(0);
//			Property targetEnd = a.getMemberEnd().get(1);
//			
//			if(firstSourceEnd.getType().equals(sourceEnd.getType()) || firstSourceEnd.equals(targetEnd.getType())){
//				source = firstSourceEnd;
//				target = firstTargetEnd;
//			}
//			else{
//				source = firstTargetEnd;
//				target = firstSourceEnd;
//			}
//		}
//		
//		else if(relationshipCycle.get(i) instanceof Generalization){
//			Generalization g = (Generalization) relationshipCycle.get(getPrevious(index));
//			
//			if(firstSourceEnd.getType().equals(g.getSpecific()) || firstSourceEnd.equals(g.getGeneral())){
//				source = firstSourceEnd;
//				target = firstTargetEnd;
//			}else{
//				source = firstTargetEnd;
//				target = firstSourceEnd;
//			}
//		}
//		
//		
//		if (target.getUpper()>1 || target.getUpper()==-1)
//			result+= "context _'"+source.getType().getName()+"'::_'"+target.getName()+"':Set(_'"+target.getType().getName()+"')\n";
//		else
//			result+= "context _'"+source.getType().getName()+"'::_'"+target.getName()+"':_'"+target.getType().getName()+"'\n";
//		
//		result+= "derive: self";
//		Type lastTarget = source.getType(); 
//		
//		while(i!=index){
//			if(relationshipCycle.get(i) instanceof Association){
//				Association a = (Association) relationshipCycle.get(i);
//				Property sourceEnd = a.getMemberEnd().get(0);
//				Property targetEnd = a.getMemberEnd().get(1);
//				
//				if(lastTarget.equals(sourceEnd.getType())){
//					result+="."+addQuotes(targetEnd.getName());
//					lastTarget = targetEnd.getType();
//				}
//				else{
//					result+="."+addQuotes(sourceEnd.getName());
//					lastTarget = sourceEnd.getType();
//				}		
//			}
//			
//			if(relationshipCycle.get(i) instanceof Generalization){
//				Generalization g = (Generalization) relationshipCycle.get(i);
//				
//				if(lastTarget.equals(g.getSpecific())){
//					lastTarget = g.getGeneral();
//				}
//				else{
//					lastTarget = g.getSpecific();
//				}
//				
//				if(relationshipCycle.get(getPrevious(i)) instanceof Association)
//					result+=".oclAsType("+addQuotes(lastTarget.getName())+")";
//			}
//			
//			i = getPrevious(i);
//		}
//	
//		return result;		
//	}
//	
//	public String generateCycleOcl(int type, OntoUMLParser parser) {
//		String rule, typeName;
//		Association a;
//		Type last_target;
//				
//		typeName = this.classCycle.get(0).getName();
//				
//		rule = 	"\n    self.";
//		
//		String invName = new String();
//		
//		if(type==OPEN) invName +=  "openCycle";
//		else invName +=  "closedCycle";			
//		
//		a = (Association)this.relationshipCycle.get(0);
//		
//		invName += "_"+a.getName();
//		
//		if (a.getMemberEnd().get(0).getType().equals(this.classCycle.get(0))) {
//			rule += "_'"+a.getMemberEnd().get(1).getName()+"'";
//			last_target = a.getMemberEnd().get(1).getType();
//		}
//		else{ 
//			rule += "_'"+a.getMemberEnd().get(0).getName()+"'";
//			last_target = a.getMemberEnd().get(0).getType();
//		}
//			
//		
//		for (int i = 1; i<relationshipCycle.size();i++) {
//			Relationship r = relationshipCycle.get(i);
//			 
//			if(r instanceof Association){
//				
//				invName+= "_"+((Association)r).getName();
//				
//				if( ((Association)r).getMemberEnd().get(0).getType().equals(last_target)) {
//					rule += "._'"+((Association)r).getMemberEnd().get(1).getName()+"'";
//					last_target =((Association)r).getMemberEnd().get(1).getType();
//				}
//				else {
//					rule += "._'"+((Association)r).getMemberEnd().get(0).getName()+"'";
//					last_target =((Association)r).getMemberEnd().get(0).getType();
//				}
//					
//			}
//		}
//		
//		if(type==CLOSED)
//			rule += "->asSet() = self->asSet()";
//		else if(type==OPEN)
//			rule += "->excludes(self)";
//		else
//			return null;
//			
//		String result = "context _'"+ typeName + "'\n"+
//						"inv "+invName+ " : " + rule;
//				
//		return result;
//	}	
	
	public String generatePredicate(OntoUMLParser parser, int cardinality, int type) throws Exception {
		String predicate, predicate_rules, name, function_name = "CycleAux", function_rules="", function_parameters="x:univ, w:World", function_return="univ";
		String typeName;
		Association a;
		Type last_source, last_target, source, target;
				
		typeName = parser.getAlias(this.classCycle.get(0));
		
		if(type==OPEN) {
			name = "openCycle_"+typeName+"_"+parser.getAlias(classCycle.get(1));
			function_name = "open"+function_name;
		}
		else if(type==CLOSED) {
			name = "closedCycle_"+typeName+"_"+parser.getAlias(classCycle.get(1));
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
		
		a = (Association)this.relationshipCycle.get(0);
		last_source = SourceTargetAssociation.getSourceAlloy(a);
		last_target = SourceTargetAssociation.getTargetAlloy(a);
		
		if (last_source.equals(this.classCycle.get(0)))
			function_rules += "(x.(w."+parser.getAlias(a)+"))";
		
		else {
			function_rules += "((w."+parser.getAlias(a)+").x)";
			last_target = SourceTargetAssociation.getSourceAlloy(a);
			last_source = SourceTargetAssociation.getTargetAlloy(a);
		}
		
		for (int i = 1; i<relationshipCycle.size();i++) {
			Relationship r = relationshipCycle.get(i);
			
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
	
	private ArrayList<Edge> getAlignedPropertiesFrom(Association a, Property source){
		
		ArrayList<Edge> alignedList = new ArrayList<Edge>();
		alignedList.add(new Edge(a, source.getOpposite()));
		
		ArrayList<Relationship> visited = new ArrayList<Relationship>();
		ArrayList<Relationship> remainder = new ArrayList<Relationship>(relationshipCycle);
		
		visited.add(a);
		remainder.remove(a);
		
		Type currentNode = source.getOpposite().getType();
		
		while(remainder.size()>0){
			Iterator<Relationship> iterator = remainder.iterator();
			while (iterator.hasNext()) {
				Relationship r = iterator.next();

				if(r instanceof Association){
					Association assoc = (Association) r;
					Property sourceEnd = assoc.getMemberEnd().get(0);
					Property targetEnd = assoc.getMemberEnd().get(1);
					
					if(sourceEnd.getType().equals(currentNode)){
						alignedList.add(new Edge(assoc, targetEnd));
						currentNode = targetEnd.getType();
						visited.add(assoc);
						iterator.remove();
						break;
					}
					if(targetEnd.getType().equals(currentNode)){
						alignedList.add(new Edge(assoc, sourceEnd));
						currentNode = sourceEnd.getType();
						visited.add(assoc);
						iterator.remove();
						break;
					}
				}
				
				if(r instanceof Generalization){
					Generalization gen = (Generalization) r;
					Classifier child = gen.getSpecific();
					Classifier parent = gen.getGeneral();
					
					if(child.equals(currentNode)){
						alignedList.add(new Edge(gen, parent));
						currentNode = parent;
						visited.add(gen);
						iterator.remove();
						break;
					}
					if(parent.equals(currentNode)){
						alignedList.add(new Edge(gen, child));
						currentNode = child;
						visited.add(gen);
						iterator.remove();
						break;
					}
				}
			}	
		}
		return alignedList;
	}
	

}

class Edge {
	Association a;
	Property p;
	Generalization g;
	Classifier c;
	
	public Edge(Association a, Property p){
		this.a = a;
		this.p = p;
	}
	
	public Edge(Generalization g, Classifier c){
		this.g = g;
		this.c = c;
	}
	
	public boolean isAssociation(){
		return a!=null;
	}
	
	public boolean isGeneralization(){
		return g!=null;
	}
	
	public Type getSource(){
		if(isAssociation())
			return p.getOpposite().getType();
		else{
			if(g.getGeneral().equals(c))
				return g.getSpecific();
			else
				return g.getGeneral();
		}
	}
	
	public Type getTarget(){
		if(isAssociation())
			return p.getType();
		
		return c;
	}
	
	public Relationship getRelationship(){
		if(isAssociation())
			return a;
		return g;
	}
}