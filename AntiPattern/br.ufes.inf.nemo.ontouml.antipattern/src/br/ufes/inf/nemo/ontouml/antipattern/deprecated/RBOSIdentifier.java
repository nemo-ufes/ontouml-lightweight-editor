package br.ufes.inf.nemo.ontouml.antipattern.deprecated;

import java.util.Collection;

import org.eclipse.ocl.ParserException;
import br.ufes.inf.nemo.ontouml.antipattern.util.OCLQueryExecuter;

import RefOntoUML.Association;
import RefOntoUML.Model;

/*Relation Between Overlapping Subtypes*/
public class RBOSIdentifier {
	
	public static String OCLQuery = "Association.allInstances()-> select(x:Association | x.ownedEnd.type->at(1)<>x.ownedEnd.type->at(2) and ( (x.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->includes(x.ownedEnd.type->at(2))) or (x.ownedEnd.type->at(2).oclAsType(Classifier).allParents()->includes(x.ownedEnd.type->at(1))) or (x.ownedEnd.type->at(1).oclAsType(Classifier).allParents()->intersection(x.ownedEnd.type->at(2).oclAsType(Classifier).allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(x.ownedEnd.type->at(1)) or g1.specific=x.ownedEnd.type->at(1)) and (g2.specific.allChildren()->includes(x.ownedEnd.type->at(2)) or g2.specific=x.ownedEnd.type->at(2))))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false))))";
	
	public static Collection<Association> RBOSQuery(Model m) throws ParserException 
	{
		return (Collection<Association>)OCLQueryExecuter.executeQuery(OCLQuery, m.eClass(), m);
	}
}
