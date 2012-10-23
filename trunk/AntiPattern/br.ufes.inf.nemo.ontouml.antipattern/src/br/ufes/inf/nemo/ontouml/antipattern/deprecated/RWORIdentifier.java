package br.ufes.inf.nemo.ontouml.antipattern.deprecated;

import java.util.Collection;

import org.eclipse.ocl.ParserException;

import RefOntoUML.Model;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

/*Relator With Overlapping Roles*/
public class RWORIdentifier {
	/*TODO This query uses the mediations() operation defined in the metamodel, which does not take into account the inverted ones, i.e. mediations with the target as the relator*/ 
	public static String OCLQuery = "Relator.allInstances()->select(r:Relator | r.oclAsType(Classifier).isAbstract=false and ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()>2 or ( r.allParents()->including(r).oclAsType(Relator).mediations()->size()=2 and ( r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(1).mediatedEnd().lower>1 or r.allParents()->including(r).oclAsType(Relator)->asSet().mediations()->asOrderedSet()->at(2).mediatedEnd().lower>1))) and r.allParents()->including(r).oclAsType(Relator).mediated()->exists(t1,t2:Classifier | t1<>t2 and ( (t1.allParents()->includes(t2)) or  (t2.allParents()->includes(t1)) or ( t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)))))";
	
	public static Collection<Relator> RWORQuery (Model m) throws ParserException
	{
		return (Collection<Relator>)OCLQueryExecuter.executeQuery(OCLQuery, m.eClass(), m);
	}
	
	
}
