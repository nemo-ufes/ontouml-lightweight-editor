package br.ufes.inf.nemo.common.ocl;

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

import RefOntoUML.Package;


public class OCLQueryExecuter {
	
	public static Object executeQuery (String oclQuery, EClassifier context, Package model) throws ParserException
	{
		OCLExpression<EClassifier> query = null;
		
		 // create an OCL instance for Ecore
	    OCL<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, org.eclipse.ocl.ecore.Constraint, EClass, EObject> ocl;
	    ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
	    
	    // create an OCL helper object
	    OCLHelper<EClassifier, EOperation, EStructuralFeature, org.eclipse.ocl.ecore.Constraint> helper = ocl.createOCLHelper();
	    
	    // set the OCL context classifier
	    helper.setContext(context);
	    Query<EClassifier, EClass, EObject> eval;
	    query = helper.createQuery(oclQuery); 
	    eval = ocl.createQuery(query);
	    	    
	    return eval.evaluate(model);
	}
}
