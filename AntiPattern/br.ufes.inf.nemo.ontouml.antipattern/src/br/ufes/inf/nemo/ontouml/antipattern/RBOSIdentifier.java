package br.ufes.inf.nemo.ontouml.antipattern;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import RefOntoUML.Model;
import RefOntoUML.Relator;

public class RBOSIdentifier {
	
	public static String OCLQuery = "Association.allInstances()->select(x:Association | x.ownedEnd.type->isUnique(name) and  x.ownedEnd.type->forAll(y1,y2:Type | y1.oclAsType(Classifier).allParents()->intersection(y2.oclAsType(Classifier).allParents())->intersection(Kind.allInstances().oclAsType(Class)->union(Collective.allInstances().oclAsType(Class))->union(Quantity.allInstances().oclAsType(Class)))->size()>0 ) and GeneralizationSet.allInstances()->select(gs:GeneralizationSet | gs.generalization->exists(y1,y2:Generalization | y1<>y2 and Classifier.allInstances()->select(z1:Classifier | z1.allParents()->includes(y1.specific))->including(y1.specific)->includes(x.ownedEnd.type->at(1)) and Classifier.allInstances()->select(z2:Classifier | z2.allParents()->includes(y2.specific))->including(y2.specific)->includes(x.ownedEnd.type->at(2))) )->forAll(chosen_gs:GeneralizationSet | chosen_gs.isDisjoint=false))";
	public static String OCLQueryRelator = "Relator.allInstances()->select(r:Relator | (r.mediations()->size()>2 or (r.mediations()->size()=2 and (r.mediations()->at(1).mediatedEnd().lower>1 or r.mediations()->at(2).mediatedEnd().lower>1) ) )	and r.mediated()->exists(t1,t2:Classifier |	t1<>t2	and	t1.allParents()->intersection(t2.allParents())->intersection(SubstanceSortal.allInstances())->size()>0 and GeneralizationSet.allInstances()->select(gs:GeneralizationSet |  gs.generalization->exists(g1,g2:Generalization | g1<>g2 and (g1.specific.allChildren()->includes(t1) or g1.specific=t1) and (g2.specific.allChildren()->includes(t2) or g2.specific=t2)))->forAll(chosenGS:GeneralizationSet | chosenGS.isDisjoint=false)))";

	public static Collection<Relator> RBOSRelatorQuery (Model m) throws ParserException
	{
		
		OCLExpression<EClassifier> query = null;
		
		 // create an OCL instance for Ecore
	    OCL<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, org.eclipse.ocl.ecore.Constraint, EClass, EObject> ocl;
	    ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
	    
	    // create an OCL helper object
	    OCLHelper<EClassifier, EOperation, EStructuralFeature, org.eclipse.ocl.ecore.Constraint> helper = ocl.createOCLHelper();
	    
	    // set the OCL context classifier
	    helper.setContext(m.eClass());
	    Query<EClassifier, EClass, EObject> eval;
	    query = helper.createQuery(OCLQueryRelator); 
	    eval = ocl.createQuery(query);
	    return (Collection<Relator>) eval.evaluate(m);
	}
}
