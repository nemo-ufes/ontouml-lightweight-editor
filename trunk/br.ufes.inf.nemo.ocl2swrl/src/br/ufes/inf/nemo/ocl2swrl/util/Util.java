package br.ufes.inf.nemo.ocl2swrl.util;

import org.eclipse.ocl.uml.impl.CallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.ocl.uml.impl.VariableImpl;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.PropertyImpl;
import org.semanticweb.owlapi.model.IRI;

import uk.ac.manchester.cs.owl.owlapi.SWRLLiteralArgumentImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;

public class Util {
	public static String generateVarName(Object expression){
		String varName = "";
		
		if(expression.getClass().equals(PropertyCallExpImpl.class)){
			varName += generateVarName(((CallExpImpl) expression).getSource());
			varName += ".";
			varName += ((PropertyCallExpImpl) expression).getReferredProperty().getName();
		}else if(expression.getClass().equals(VariableExpImpl.class)){
			varName += ((VariableExpImpl) expression).getReferredVariable().getType().getName();
		}else if(expression.getClass().equals(VariableImpl.class)){
			varName += ((VariableImpl) expression).getType().getName();
		}else if(expression.getClass().equals(SWRLLiteralArgumentImpl.class)){
			varName += "_" + ((SWRLLiteralArgumentImpl)expression).getLiteral().getLiteral();
		}else if(expression.getClass().equals(SWRLVariableImpl.class)){
			IRI iri = (IRI)((SWRLVariableImpl)expression).getIRI();
			varName += iri.getFragment();
		}else if(expression.getClass().equals(PropertyImpl.class)){
			varName += generateVarName(((PropertyImpl) expression).getType());
			varName += ".";
			varName += ((PropertyImpl) expression).getName();
			//varName += iri.getFragment();
		}else if(expression.getClass().equals(ClassImpl.class)){
			varName += ((ClassImpl) expression).getName();
			//varName += iri.getFragment();
		}
		
		return varName;
	}
}
