package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.ocl.util.Tuple;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Model;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.Type;

import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontouml2graph.GraphAlgo;
import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;
import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;

public class AntiPatternIdentifier {
	
	/*OCL query for the identification of the Relation Specialization AntiPattern*/
	private static String RS_OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second})->select( x | x.a1<>x.a2 and ( ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) ) or ( x.a1.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(2).oclAsType(Classifier)) and x.a1.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.ownedEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.ownedEnd.type->at(1).oclAsType(Classifier)) )) )";
	
	/*Returns all the Relation Specialization AntiPatterns in the input model m*/ 
	@SuppressWarnings("unchecked")
	public static ArrayList<RSAntiPattern> identifyRS(Model m) throws Exception 
	{
		Collection<Tuple<Association, Association>> query_result;
		ArrayList<RSAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Tuple<Association, Association>>)OCLQueryExecuter.executeQuery(RS_OCLQuery, m.eClass(), m);
		
		for (Tuple<Association, Association> t : query_result) {
			RSAntiPattern rs = new RSAntiPattern((Association)t.getValue("a1"), (Association)t.getValue("a2")); 
			result.add(rs);
		}
		
		return result;
	}
	
	/*OCL query for the identification of the Self-Type Relationship AntiPattern*/
	private static String STR_OCLQuery = "Association.allInstances()->select(x:Association | x.ownedEnd.type->forAll(y1,y2:Type | y1=y2))";
	
	/*Returns all the Self-Type Relationship AntiPatterns in the input model m*/
	@SuppressWarnings("unchecked")
	public static ArrayList<STRAntiPattern> identifySTR (Model m) throws Exception
	{
		Collection<Association> query_result;
		ArrayList<STRAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Association>) OCLQueryExecuter.executeQuery(STR_OCLQuery, m.eClass(), m);
		
		for (Association a : query_result) {
			result.add(new STRAntiPattern(a));
		}
		
		return result;
	}
	
	/*OCL query for the identification of the Relation Between Overlapping Subtypes AntiPattern*/
	private static String RBOS_OCLQuery = "Association.allInstances()-> select(x:Association | x.ownedEnd.type->at(1)<>x.ownedEnd.type->at(2) and ( (x.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.ownedEnd.type->at(2))) or (x.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->includes(x.ownedEnd.type->at(1))) or (x.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.ownedEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.ownedEnd.type->at(1)) or g1.specific=x.ownedEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.ownedEnd.type->at(2)) or g2.specific=x.ownedEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	
	/*Returns all the Relation Between Overlapping Subtypes AntiPatterns in the input model m*/
	@SuppressWarnings("unchecked")
	public static ArrayList<RBOSAntiPattern> identifyRBOS (Model m) throws Exception 
	{
		Collection<Association> query_result;
		ArrayList<RBOSAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Association>)OCLQueryExecuter.executeQuery(RBOS_OCLQuery, m.eClass(), m);
		
		for (Association a : query_result) {
			result.add(new RBOSAntiPattern(a));
		}
		
		return result;
	}
	
	/*TODO This query uses the mediations() operation defined in the metamodel, which does not take into account the inverted ones, i.e. mediations with the target as the relator*/ 
	/*OCL query for the identification of the Relator With Overlapping Roles AntiPattern*/
	public static String RWOROCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and ( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().lower>1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().lower>1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and ( (t1.allParents()->includes(t2)) or  (t2.allParents()->includes(t1)) or ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)))))";
	
	/*Returns all the Relator With Overlapping Roles AntiPatterns in the input model m*/
	@SuppressWarnings("unchecked")
	public static ArrayList<RWORAntiPattern> identifyRWOR (Model m) throws Exception
	{
		
		Collection<Relator> query_result;
		ArrayList<RWORAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Relator>)OCLQueryExecuter.executeQuery(RWOROCLQuery, m.eClass(), m);
		
		for (Relator a : query_result) {
			result.add(new RWORAntiPattern(a));
		}
		
		return result;
	}
	
	/*OCL query for the identification of the Relator With Overlapping Roles AntiPattern*/
	private static String IA_OCLQuery = "Association.allInstances()->select(x:Association | (x.memberEnd.type->at(1).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(1).upper=-1 or x.memberEnd->at(1).upper>1)) or (x.memberEnd.type->at(2).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(2).upper=-1 or x.memberEnd->at(2).upper>1)))";
	
	/*Returns all the Imprecise Abstraction AntiPatterns in the input model m*/
	@SuppressWarnings("unchecked")
	public static ArrayList<IAAntiPattern> identifyIA(Model m) throws Exception 
	{
		Collection<Association> query_result;
		ArrayList<IAAntiPattern> result = new ArrayList<>();
		
		query_result = (Collection<Association>)OCLQueryExecuter.executeQuery(IA_OCLQuery, m.eClass(), m);
		
		for (Association a : query_result) {
			result.add(new IAAntiPattern(a));
		}
		
		return result;
	}
	
	public static ArrayList<ACAntiPattern> identifyAC(Model m){
		
		int aux[][]; 
		int nodei[], nodej[];
		ArrayList<RefOntoUML.Class> classes = new ArrayList<>(),  cycle;
		ArrayList<Relationship> relationships = new ArrayList<>(), cycle_ass;
		ArrayList<ACAntiPattern> result = new ArrayList<>();
		
		aux = OntoUML2Graph.buildGraph(m, classes, relationships, false, false);
		nodei = aux[0];
		nodej = aux[1];
		
		int fundcycle[][] = new int [relationships.size()-2][classes.size()];
		GraphAlgo.fundamentalCycles(classes.size()-1, relationships.size()-1, nodei, nodej, fundcycle);
		
		for (int i=1; i<=fundcycle[0][0]; i++) { 
			
			cycle = new ArrayList<>();
			cycle_ass = new ArrayList<>();
			
			for (int j=1; j<=fundcycle[i][0]; j++)
				cycle.add(classes.get(fundcycle[i][j]));
			
			for (int j = 0; j < cycle.size(); j++) {
				
				int pos1, pos2;
				if(j<cycle.size()-1) {
					pos1 = j;
					pos2 = j+1;
				}
				else {
					pos1=j;
					pos2=0;
				}
					
				
				for (Relationship r : relationships) {
					if(r instanceof Association){
						Type source, target;
						source = ((Association)r).getMemberEnd().get(0).getType();
						target = ((Association)r).getMemberEnd().get(1).getType();
						if( (source.equals(cycle.get(pos1)) && target.equals(cycle.get(pos2))) || (source.equals(cycle.get(pos2)) && target.equals(cycle.get(pos1))))
							cycle_ass.add(r);
					}
					if (r instanceof Generalization){
						Classifier general, specific;
						general = ((Generalization)r).getGeneral();
						specific = ((Generalization)r).getSpecific();
						if ( (general.equals(cycle.get(pos1)) && specific.equals(cycle.get(pos2))) || (general.equals(cycle.get(pos2)) && specific.equals(cycle.get(pos1))) )
							cycle_ass.add(r);
					}
						
							
				}
					
			}
			
			result.add(new ACAntiPattern(cycle,cycle_ass));
		} 
		
		return result;
	}
	
	
	
	
	
}
