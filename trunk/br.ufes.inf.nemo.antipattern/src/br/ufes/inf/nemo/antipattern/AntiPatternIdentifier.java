package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.util.Tuple;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontouml2graph.GraphAlgo;
import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AntiPatternIdentifier {
	
	public static EObject getOriginal(EObject copy, Copier copier)
	{
		
		for (EObject element : copier.keySet()) {
			if(copier.get(element).equals(copy))
				return element;
		}
		
		return null;
	}
	
	/**
	 * # QUERY : RS ANTIPATTERN
	 *  
	 * OCL query for the identification of the Relation Specialization AntiPattern.
	 */
	private static String RS_OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second})->select( x | x.a1<>x.a2 and ( ( x.a1.memberEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.memberEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.memberEnd.type->at(1).oclAsType(Classifier)) and x.a1.memberEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.memberEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.memberEnd.type->at(2).oclAsType(Classifier)) ) or ( x.a1.memberEnd.type->at(1).oclAsType(Classifier).allParents()->including(x.a1.memberEnd.type->at(1).oclAsType(Classifier))->includes(x.a2.memberEnd.type->at(2).oclAsType(Classifier)) and x.a1.memberEnd.type->at(2).oclAsType(Classifier).allParents()->including(x.a1.memberEnd.type->at(2).oclAsType(Classifier))->includes(x.a2.memberEnd.type->at(1).oclAsType(Classifier)) )) )";
	
	/**
	 * # IDENTIFY : RS ANTIPATTERN 
	 * 
	 * Returns all the Relation Specialization AntiPatterns in the input model m.
	 *  
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RSAntiPattern> identifyRS(OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Tuple<Association, Association>> query_result;
		ArrayList<RSAntiPattern> result = new ArrayList<RSAntiPattern>();
		
		query_result = (Collection<Tuple<Association, Association>>)OCLQueryExecuter.executeQuery(RS_OCLQuery, (EClassifier)model.eClass(), model);
		
		for (Tuple<Association, Association> t : query_result) {
			
			Association a1 = (Association) AntiPatternIdentifier.getOriginal((Association)t.getValue("a1"), copier);
			Association a2 = (Association) AntiPatternIdentifier.getOriginal((Association)t.getValue("a2"), copier);
			
			RSAntiPattern rs = new RSAntiPattern(a1,a2); 
			result.add(rs);
		}
		
		return result;
	}
	
	/**
	 * # QUERY : STR ANTIPATTERN
	 * 
	 * OCL query for the identification of the Self-Type Relationship AntiPattern.
	 */
	private static String STR_OCLQuery = "Association.allInstances()->select(x:Association | x.memberEnd.type->forAll(y1,y2:Type | y1=y2))";
	
	
	/**
	 * # IDENTIFY : STR ANTIPATTERN
	 * 
	 * Returns all the Self-Type Relationship AntiPatterns in the input model m.
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<STRAntiPattern> identifySTR (OntoUMLParser parser) throws Exception
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Association> query_result;
		ArrayList<STRAntiPattern> result = new ArrayList<STRAntiPattern>();
		
		query_result = (Collection<Association>) OCLQueryExecuter.executeQuery(STR_OCLQuery, (EClassifier)model.eClass(), model);
		
		for (Association a : query_result) {
			Association original = (Association) AntiPatternIdentifier.getOriginal(a, copier);
			
			result.add(new STRAntiPattern(original));
		}
		
		return result;
	}
		
	/**
	 * # QUERY : RBOS ANTIPATTERN
	 * 
	 * OCL query for the identification of the Relation Between Overlapping Subtypes AntiPattern.
	 */
	private static String RBOS_OCLQuery = "Association.allInstances()-> select(x:Association | x.memberEnd.type->at(1)<>x.memberEnd.type->at(2) and ( (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) or (x.memberEnd.type->at(2).oclAsType(Classifier)).allParents()->includes(x.memberEnd.type->at(1).oclAsType(Classifier))) or (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.memberEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.memberEnd.type->at(1).oclAsType(Classifier)) or g1.specific=x.memberEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) or g2.specific=x.memberEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	//private static String RBOS_OCLQuery = "Association.allInstances()-> select(x:Association | x.memberEnd.type->at(1)<>x.memberEnd.type->at(2) and ( (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(2))) or (x.memberEnd.type->at(2).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(1))) or (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.memberEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.memberEnd.type->at(1)) or g1.specific=x.memberEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.memberEnd.type->at(2)) or g2.specific=x.memberEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	
	
	/**
	 * # IDENTIFY : RBOS ANTIPATTERN
	 * 
	 * Returns all the Relation Between Overlapping Subtypes AntiPatterns in the input model m.
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RBOSAntiPattern> identifyRBOS (OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Association> query_result;
		ArrayList<RBOSAntiPattern> result = new ArrayList<RBOSAntiPattern>();
		
		query_result = (Collection<Association>)OCLQueryExecuter.executeQuery(RBOS_OCLQuery, (EClassifier)model.eClass(), model);
		
		for (Association a : query_result) {
			Association original = (Association) AntiPatternIdentifier.getOriginal(a, copier);
			result.add(new RBOSAntiPattern(original));
		}
		
		return result;
	}
	
	/*
	 * TODO 
	 * This query uses the mediations() operation defined in the metamodel, which does not take into account the inverted ones, 
	 * i.e. mediations with the target as the relator
	 * 
	 */ 
	
	/**
	 * # QUERY : RWOR ANTIPATTERN
	 * 
	 * OCL query for the identification of the Relator With Overlapping Roles AntiPattern.
	 */
	public static String RWOROCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and (( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper+r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper)>2 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper=-1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper=-1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and t1.allParents()->excludes(t2) and t2.allParents()->excludes(t1) and ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	//public static String RWOROCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and ( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().lower>1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().lower>1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and ( (t1.allParents()->includes(t2)) or  (t2.allParents()->includes(t1)) or ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)))))";
	
	/*	

	Relator.allInstances()->
	Relator.allInstances()->
	select(r:Relator | 
			r.oclAsType(Classifier).isAbstract=false 
			and 
			( 
				r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 
				or 
				( 
					r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 
					and 
					(
						( 
							r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper
							+
							r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper
						)>2
						or
						r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper=-1
						or
						r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper=-1
					)
				)
			) 
			and 
			r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | 
				t1<>t2
				and 
				t1.allParents()->excludes(t2)
				and 
				t2.allParents()->excludes(t1)
				
				and 
				( 
					t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 
					and 
					GeneralizationSet.allInstances()->select(gs:GeneralizationSet | 
						gs.generalization->exists(g1,g2:Generalization | 
							g1<>g2 
							and 
							(
								g1.specific.allChildren()->includes(t1) 
								or 
								g1.specific=t1
							) 
							and 
							(
								g2.specific.allChildren()->includes(t2) 
								or g2.specific=t2
							)
						)
						)->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)
			
				)
			)
	)
	 */
		
	/**
	 * # IDENTIFY : RWOR ANTIPATTERN
	 * 
	 * Returns all the Relator With Overlapping Roles AntiPatterns in the input model m.
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RWORAntiPattern> identifyRWOR (OntoUMLParser parser) throws Exception
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Relator> query_result;
		ArrayList<RWORAntiPattern> result = new ArrayList<RWORAntiPattern>();
		
		query_result = (Collection<Relator>)OCLQueryExecuter.executeQuery(RWOROCLQuery, (EClassifier)model.eClass(), model);
		
		for (Relator r : query_result) {
			Relator original = (Relator) AntiPatternIdentifier.getOriginal(r, copier);
			result.add(new RWORAntiPattern(original, parser));
		}
		
		return result;
	}
	
	/**
	 * # QUERY : IA ANTIPATTERN
	 * 
	 * OCL query for the identification of the Relator With Overlapping Roles AntiPattern.
	 */
	private static String IA_OCLQuery = "Association.allInstances()->select(x:Association | (x.memberEnd.type->at(1).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(1).upper=-1 or x.memberEnd->at(1).upper>1)) or (x.memberEnd.type->at(2).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(2).upper=-1 or x.memberEnd->at(2).upper>1)))";
	
	/**
	 * # IDENTIFY : IA ANTIPATTERN
	 * 
	 * Returns all the Imprecise Abstraction AntiPatterns in the input model m.
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<IAAntiPattern> identifyIA(OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Association> query_result;
		ArrayList<IAAntiPattern> result = new ArrayList<IAAntiPattern>();
		
		query_result = (Collection<Association>)OCLQueryExecuter.executeQuery(IA_OCLQuery, (EClassifier)model.eClass(), model);
		
		for (Association a : query_result) {
			Association original = (Association) AntiPatternIdentifier.getOriginal(a, copier);
			result.add(new IAAntiPattern(original));
		}
		
		return result;
	}
	
	/**
	 *  # IDENTIFY : AC ANTIPATTERN
	 *  
	 * @param parser
	 * @return
	 */
	public static ArrayList<ACAntiPattern> identifyAC(OntoUMLParser parser){
		
		int aux[][]; 
		int nodei[], nodej[];
		ArrayList<RefOntoUML.Class> classes = new ArrayList<RefOntoUML.Class>();
		ArrayList<RefOntoUML.Class> cycle = new ArrayList<RefOntoUML.Class>();
		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
		ArrayList<Relationship> cycle_ass = new ArrayList<Relationship>();
		ArrayList<ACAntiPattern> result = new ArrayList<ACAntiPattern>();
		
		aux = OntoUML2Graph.buildGraph(parser, classes, relationships, false, false);
		nodei = aux[0];
		nodej = aux[1];
		
		if (relationships.size()<=2) return result;
		
		int fundcycle[][] = new int [relationships.size()-2][classes.size()];
		GraphAlgo.fundamentalCycles(classes.size()-1, relationships.size()-1, nodei, nodej, fundcycle);
		
		for (int i=1; i<=fundcycle[0][0]; i++) { 
			
			cycle = new ArrayList<RefOntoUML.Class>();
			cycle_ass = new ArrayList<Relationship>();
			
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
	
	
	/**
	 * # QUERY : RWRT ANTIPATTERN
	 * 
	 * OCL query for the identification of the Relator With Rigid Types AntiPattern.
	 */
	private static String RWRT_OCLQuery = "Mediation.allInstances()->select(m:Mediation | m.memberEnd->at(2).type.oclIsKindOf(RigidSortalClass) or m.memberEnd->at(1).type.oclIsKindOf(RigidSortalClass) or m.memberEnd->at(2).type.oclIsKindOf(RigidMixinClass) or m.memberEnd->at(1).type.oclIsKindOf(RigidMixinClass))";

	/**
	 * # IDENTIFY : RWRT ANTIPATTERN
	 * 
	 * Returns all the Relator With Rigid Types AntiPatterns in the input model m.
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RWRTAntiPattern> identifyRWRT (OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Mediation> query_result;				
		query_result = (Collection<Mediation>)OCLQueryExecuter.executeQuery(RWRT_OCLQuery, (EClassifier)model.eClass(), model);
		
		ArrayList<RWRTAntiPattern> result = new ArrayList<RWRTAntiPattern>();
		for (Mediation a : query_result) {
			Mediation original = (Mediation) AntiPatternIdentifier.getOriginal(a, copier);
			result.add(new RWRTAntiPattern(original));
		}
		
		return result;
	}
	
}
