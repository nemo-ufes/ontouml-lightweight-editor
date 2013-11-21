package br.ufes.inf.nemo.antipattern;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.TupleType;
import org.eclipse.ocl.util.Tuple;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Relationship;
import RefOntoUML.Relator;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.gsrig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.mm.MMAntiPattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.rbos.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontouml2graph.GraphAlgo;
import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AntiPatternIdentifier {
	
	/**
	 * Auxiliary method. Get original element. 
	 * 
	 * @param copy
	 * @param copier
	 * @return
	 */
	
	
	/** OCL query for RS AntiPattern. */
	private static String RS_OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2Source:Classifier = x.second.memberEnd.type->at(1).oclAsType(Classifier), a2Target:Classifier = x.second.memberEnd.type->at(2).oclAsType(Classifier)})->select( x | x.a1<>x.a2 and ( ( x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Source) and x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Target) ) or ( x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Target) and x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Source))))->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})";
	/*Association.allInstances()->product(Association.allInstances())
	->collect( x | Tuple {a1:Association = x.first, a2:Association = x.second, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2Source:Classifier = x.second.memberEnd.type->at(1).oclAsType(Classifier), a2Target:Classifier = x.second.memberEnd.type->at(2).oclAsType(Classifier)})
		->select( x | 
			x.a1<>x.a2 
			and 
			(
				(
					x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Source) 
					and 
					x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Target) 
				)
				or 
				(
					x.a1Source.allParents()->including(x.a1Source)->includes(x.a2Target) 
					and 
					x.a1Target.allParents()->including(x.a1Target)->includes(x.a2Source) 
				)
			)
		)->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})*/
	
	/**
	 * Identify RS AntiPattern (Relation Specialization).
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

		Collection<Tuple<Association, Association>> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(RS_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Tuple<Association, Association>>)o);
		else if (o instanceof Tuple)
			query_result.add((Tuple<Association, Association>) o);
				
		ArrayList<RSAntiPattern> result = new ArrayList<RSAntiPattern>();
		for (Tuple<Association, Association> t : query_result) 
		{			
			Association a1 = (Association) AntiPatternUtil.getOriginal((Association)t.getValue("a1"), copier);
			Association a2 = (Association) AntiPatternUtil.getOriginal((Association)t.getValue("a2"), copier);			
			RSAntiPattern rs = new RSAntiPattern(a1,a2); 
			result.add(rs);
		}		
		return result;
	}
	
	/** OCL query for STR AntiPattern. */
	private static String STR_OCLQuery = "Association.allInstances()->select(x:Association | x.memberEnd.type->forAll(y1,y2:Type | y1=y2))";
		
	/** 
	 * Identify STR AntiPattern (Self Type Relationship).
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<BinOverAntipattern> identifySTR (OntoUMLParser parser) throws Exception
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Association> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(STR_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Association>)o);
		else if (o instanceof Association)
			query_result.add((Association) o);
		
		ArrayList<BinOverAntipattern> result = new ArrayList<BinOverAntipattern>();
		for (Association a : query_result) 
		{
			try {
			Association original = (Association) AntiPatternUtil.getOriginal(a, copier);			
			result.add(new BinOverAntipattern(original));
			}
			catch (Exception e){
				
			}
		}		
		return result;
	}
		
	/** OCL query for RBOS AntiPattern. */
	private static String RBOS_OCLQuery = "Association.allInstances()-> select(x:Association | x.memberEnd.type->at(1)<>x.memberEnd.type->at(2) and ( (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) or (x.memberEnd.type->at(2).oclAsType(Classifier)).allParents()->includes(x.memberEnd.type->at(1).oclAsType(Classifier))) or (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.memberEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.memberEnd.type->at(1).oclAsType(Classifier)) or g1.specific=x.memberEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.memberEnd.type->at(2).oclAsType(Classifier)) or g2.specific=x.memberEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	
	//private static String RBOS_OCLQuery = "Association.allInstances()-> select(x:Association | x.memberEnd.type->at(1)<>x.memberEnd.type->at(2) and ( (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(2))) or (x.memberEnd.type->at(2).oclAsType(Classifier).allParents()->includes(x.memberEnd.type->at(1))) or (x.memberEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.memberEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.memberEnd.type->at(1)) or g1.specific=x.memberEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.memberEnd.type->at(2)) or g2.specific=x.memberEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
		
	/**
	 * Identify RBOS AntiPattern (Relation Between Overlapping Subtypes).
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
		
		query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(RBOS_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Association>)o);
		else if (o instanceof Association)
			query_result.add((Association) o);
		
		for (Association a : query_result) 
		{
			Association original = (Association) AntiPatternUtil.getOriginal(a, copier);
			result.add(new RBOSAntiPattern(original));
		}		
		return result;
	}
	
	/** OCL query for RWOR AntiPattern. */
	public static String RWOROCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and (( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper+r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper)>2 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().upper=-1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().upper=-1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and t1.allParents()->excludes(t2) and t2.allParents()->excludes(t1) and ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";

	/*
	 * TODO 
	 * This query for RWOR AntiPattern uses the mediations() operation defined in the metamodel, 
	 * which does not take into account the inverted ones, 
	 * i.e. mediations with the target as the relator.
	 */ 
	
	//public static String RWOROCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and ( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().lower>1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().lower>1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and ( (t1.allParents()->includes(t2)) or  (t2.allParents()->includes(t1)) or ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)))))";
	
	/*
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
	 * Identify RWOR AntiPattern (Relator With Overlapping Roles).
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RelOverAntipattern> identifyRWOR (OntoUMLParser parser) throws Exception
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Relator> query_result;
		ArrayList<RelOverAntipattern> result = new ArrayList<RelOverAntipattern>();
		
		query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(RWOROCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Relator>)o);
		else if (o instanceof Relator)
			query_result.add((Relator) o);
		
		for (Relator r : query_result) {
			Relator original = (Relator) AntiPatternUtil.getOriginal(r, copier);
			result.add(new RelOverAntipattern(original, parser));
		}
		
		return result;
	}
	
	/** OCL query for IA AntiPattern. */
	private static String IA_OCLQuery = "Association.allInstances()->select(x:Association | (x.memberEnd.type->at(1).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(1).upper=-1 or x.memberEnd->at(1).upper>1)) or (x.memberEnd.type->at(2).oclAsType(Classifier).allChildren()->size()>1 and (x.memberEnd->at(2).upper=-1 or x.memberEnd->at(2).upper>1)))";
	
	/**
	 * Identify IA AntiPattern (Imprecise Abstraction).
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ImpAbsAntipattern> identifyIA(OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Association> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(IA_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Association>)o);
		else if (o instanceof Association)
			query_result.add((Association) o);
		
		ArrayList<ImpAbsAntipattern> result = new ArrayList<ImpAbsAntipattern>();
		for (Association a : query_result) {
			Association original = (Association) AntiPatternUtil.getOriginal(a, copier);
			result.add(new ImpAbsAntipattern(original));
		}
		
		return result;
	}
	
	/**
	 *  Identify AC AntiPattern (Association Cycle).
	 *  
	 * @param parser
	 * @return
	 */
	public static ArrayList<AssCycAntipattern> identifyAC(OntoUMLParser parser){
		
		int aux[][]; 
		int nodei[], nodej[];
		ArrayList<RefOntoUML.Class> classes = new ArrayList<RefOntoUML.Class>();
		ArrayList<RefOntoUML.Class> cycle = new ArrayList<RefOntoUML.Class>();
		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
		ArrayList<Relationship> cycle_ass = new ArrayList<Relationship>();
		ArrayList<AssCycAntipattern> result = new ArrayList<AssCycAntipattern>();
		
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
						if (((Association) r).getMemberEnd().size()==2) {
							source = ((Association)r).getMemberEnd().get(0).getType();
							target = ((Association)r).getMemberEnd().get(1).getType();
							if( (source.equals(cycle.get(pos1)) && target.equals(cycle.get(pos2))) || (source.equals(cycle.get(pos2)) && target.equals(cycle.get(pos1))))
								cycle_ass.add(r);
						}
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
			
			result.add(new AssCycAntipattern(cycle,cycle_ass));
		} 
		
		return result;
	}
	
	
	/** OCL query for RWRT AntiPattern. */
	private static String RWRT_OCLQuery = "Relator.allInstances()->select(r: Relator | r.isAbstract=false and Mediation.allInstances()->select(m:Mediation | (m.sourceEnd().type=r and m.sourceEnd().lower>0 and (m.targetEnd().type.oclIsKindOf(RigidSortalClass) or m.targetEnd().type.oclIsKindOf(RigidMixinClass)) ) or (m.targetEnd().type=r and m.targetEnd().lower>0 and (m.sourceEnd().type.oclIsKindOf(RigidSortalClass) or m.sourceEnd().type.oclIsKindOf(RigidMixinClass))) )->size()>=1 )";

	/*
	Relator.allInstances()->
		select(r: Relator | r.isAbstract=false and Mediation.allInstances()->
								select(m:Mediation | 
									(m.sourceEnd().type=r and m.sourceEnd().lower>0 and (m.targetEnd().type.oclIsKindOf(RigidSortalClass) or m.targetEnd().type.oclIsKindOf(RigidMixinClass)) )
									or 
									(m.targetEnd().type=r and m.targetEnd().lower>0 and (m.sourceEnd().type.oclIsKindOf(RigidSortalClass) or m.sourceEnd().type.oclIsKindOf(RigidMixinClass)))
								)->size()>=1
				)
	)
	*/
	
	/**
	 * Identify RWRT AntiPattern (Relator With Rigid Types).
	 * 
	 * @param parser
	 * @return
	 * @throws ParserException 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RelRigAntipattern> identifyRWRT (OntoUMLParser parser) throws Exception


	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Relator> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(RWRT_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Relator>)o);
		else if (o instanceof Relator)
			query_result.add((Relator) o);
		
		ArrayList<RelRigAntipattern> result = new ArrayList<RelRigAntipattern>();
		for (Relator a : query_result) 
		{
			Relator original = (Relator) AntiPatternUtil.getOriginal(a, copier);
			
			RelRigAntipattern rwrt;
			try {
				rwrt = new RelRigAntipattern(original, parser);
				result.add(rwrt);
			} catch (Exception e) {
				
			}
			
		}		
		return result;
	}
	
	/** OCL query for TRI AntiPattern. */
	//private static String TRI_OCLQuery = "Relator.allInstances()->select(r: Relator | r.mediations()->select(m:Mediation|m.relatorEnd().upper=-1 or m.relatorEnd().upper>1)->size()>=2)";
	
	private static String TRI_OCLQuery = 
			"Relator.allInstances()->"+
			"select(r: Relator | r.isAbstract=false and Mediation.allInstances()->"+
			"					select(m:Mediation | "+
			"						((m.sourceEnd().type=r or m.sourceEnd().type.oclAsType(Classifier).allParents()->includes(r)) and (m.sourceEnd().upper=-1 or m.sourceEnd().upper>1))"+
			"						or "+
			"						((m.targetEnd().type=r or m.targetEnd().type.oclAsType(Classifier).allParents()->includes(r)) and (m.targetEnd().upper=-1 or m.targetEnd().upper>1) and not m.sourceEnd().type.oclIsKindOf(Relator))"+
			"					)->size()>=2"+
			")";
	
	/*
	 	Relator.allInstances()->
			select(r: Relator | r.isAbstract=false and Mediation.allInstances()->
								select(m:Mediation | 
									((m.sourceEnd().type=r or m.sourceEnd().type.oclAsType(Classifier).allParents()->includes(r)) and (m.sourceEnd().upper=-1 or m.sourceEnd().upper>1))
									or 
									((m.targetEnd().type=r or m.targetEnd().type.oclAsType(Classifier).allParents()->includes(r)) and (m.targetEnd().upper=-1 or m.targetEnd().upper>1) and not m.sourceEnd().type.oclIsKindOf(Relator))
								)->size()>=2
			)
	 */

	/*
	 * TODO 
	 * This query for TRI AntiPattern uses the mediations() operation defined in the metamodel, 
	 * which does not take into account the inverted ones, 
	 * i.e. mediations with the target as the relator.
	 * 
	 * Problem is on relatorEnd() and mediatedEnd()
	 * Implementations should be:
	 * 
	 * relatorEnd(): if self.memberEnd.type->one(t:Type| not t.oclIsKindOf(Relator)) then self.memberEnd->any(p:Property | p.type.oclIsKindOf(Relator)) else self.sourceEnd() endif
	 * mediatedEnd(): if self.memberEnd.type->one(t:Type| not t.oclIsKindOf(Relator)) then self.memberEnd->any(p:Property | not p.type.oclIsKindOf(Relator)) else self.targetEnd() endif
	 * 
	 * For now, let's keep the implementation and verify these inconsistencies on syntax time. 
	 *
	 */
	
	/**
	 * Identify TRI AntiPattern (Twin Relator Instances).
	 * 
	 * @param parser
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RepRelAntipattern> identifyTRI (OntoUMLParser parser) throws Exception 
	{
		Copier copier = new Copier();
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Relator> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(TRI_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Relator>)o);
		else if (o instanceof Relator)
			query_result.add((Relator) o);
		
		ArrayList<RepRelAntipattern> result = new ArrayList<RepRelAntipattern>();
		for (Relator a : query_result) 
		{
			Relator original = (Relator) AntiPatternUtil.getOriginal(a, copier);
			
			RepRelAntipattern tri;
			try {
				tri = new RepRelAntipattern(original,parser);
				result.add(tri);
			} catch (Exception e) {

			}
			
			
		}		
		return result;
	}
	
	/** OCL query for MRBS AntiPattern. */
	private static String MRBS_OCLQuery = "";
	
	/**
	 * Identify MRBS AntiPattern (Multiple Relator Between Sortals).
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<MRBSAntiPattern> identifyMRBS (OntoUMLParser parser) throws Exception 
	{		
		ArrayList<MRBSAntiPattern> result = new ArrayList<MRBSAntiPattern>();
		
		return result;
	}
	
	/** OCL query for SSR AntiPattern. */
	private static String SSR_OCLQuery = "Association.allInstances()->product(Association.allInstances())->collect( x | Tuple {	a1:Association = x.first, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2:Association = x.second})->select( x | x.a1<>x.a2 and ((x.a1.memberEnd->at(2).upper=-1 or x.a1.memberEnd->at(2).upper>1) and x.a1Target.allChildren()->including(x.a1Target)->includesAll(x.a2.endType.oclAsType(Classifier))) or ((x.a1.memberEnd->at(1).upper=-1 or x.a1.memberEnd->at(1).upper>1) and x.a1Source.allChildren()->including(x.a1Source)->includesAll(x.a2.endType.oclAsType(Classifier))))->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})";
	
	/*Association.allInstances()->product(Association.allInstances())->collect( x | 
			Tuple {	a1:Association = x.first, a1Source:Classifier = x.first.memberEnd.type->at(1).oclAsType(Classifier), a1Target:Classifier = x.first.memberEnd.type->at(2).oclAsType(Classifier), a2:Association = x.second})
			->select( x | 
							x.a1<>x.a2 and 
							((x.a1.memberEnd->at(2).upper=-1 or x.a1.memberEnd->at(2).upper>1) and x.a1Target.allChildren()->including(x.a1Target)->includesAll(x.a2.endType.oclAsType(Classifier)))
							or
							((x.a1.memberEnd->at(1).upper=-1 or x.a1.memberEnd->at(1).upper>1) and x.a1Source.allChildren()->including(x.a1Source)->includesAll(x.a2.endType.oclAsType(Classifier)))
					)->collect(x | Tuple { a1:Association = x.a1, a2:Association = x.a2})
	*/
	
	/**
	 * Identify SSR AntiPattern (Super and Sub Relations).
	 * 
	 * @param parser
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<RelCompAntipattern> identifySSR (OntoUMLParser parser) throws Exception 
	{		
		Copier copier = new Copier();		
		
		Package model = parser.createPackageFromSelections(copier);
		
		Collection<Tuple<Association, Association>> query_result = new ArrayList<>();
		Object o = OCLQueryExecuter.executeQuery(SSR_OCLQuery, (EClassifier)model.eClass(), model);
		
		if (o instanceof Collection)
			query_result.addAll((Collection<Tuple<Association, Association>>)o);
		else if (o instanceof Tuple)
			query_result.add((Tuple<Association, Association>) o);
		
		ArrayList<RelCompAntipattern> result = new ArrayList<RelCompAntipattern>();
		for (Tuple<Association, Association> t : query_result) 
		{			
			Association a1 = (Association) AntiPatternUtil.getOriginal((Association)t.getValue("a1"), copier);
			Association a2 = (Association) AntiPatternUtil.getOriginal((Association)t.getValue("a2"), copier);			
			RelCompAntipattern rs = new RelCompAntipattern(a1,a2); 
			result.add(rs);
		}		
		return result;
	}

	public static ArrayList<FreeRoleAntipattern> identifyURS(OntoUMLParser parser) {
		return FreeRoleAntipattern.identify(parser);
	}
	
	public static ArrayList<MultiDepAntipattern> identifyMRD(OntoUMLParser parser) {
		return MultiDepAntipattern.identify(parser);
	}
	
	public static ArrayList<GSRigAntipattern> identifyMRGS(OntoUMLParser parser) {
		return GSRigAntipattern.identify(parser);
	}
	
	public static ArrayList<DepPhaseAntipattern> identifyRDP(OntoUMLParser parser) {
		return DepPhaseAntipattern.identify(parser);
	}
	
	public static ArrayList<MMAntiPattern> identifyMM(OntoUMLParser parser) {
		return MMAntiPattern.identify(parser);
	}
	
	
	
	
}
