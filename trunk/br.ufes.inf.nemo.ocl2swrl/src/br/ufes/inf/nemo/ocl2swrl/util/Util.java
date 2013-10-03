package br.ufes.inf.nemo.ocl2swrl.util;

import org.eclipse.ocl.uml.impl.*;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.PropertyImpl;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLLiteralArgument;
import org.semanticweb.owlapi.model.SWRLVariable;

import uk.ac.manchester.cs.owl.owlapi.SWRLLiteralArgumentImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;

public class Util {
	public static String generateVarName(Object expression, SWRLDArgument referredArgument){
		String varName = "";
		
		if(referredArgument != null){
			Class<? extends SWRLDArgument> c = referredArgument.getClass();
			if(c.equals(SWRLVariableImpl.class)){
				SWRLVariable var = (SWRLVariableImpl) referredArgument;
				IRI iri = var.getIRI();
				
				varName += iri.getFragment();
				
				
			}else if(c.equals(SWRLLiteralArgumentImpl.class)){
				SWRLLiteralArgument arg = (SWRLLiteralArgumentImpl) referredArgument;
				arg.getLiteral();
			}
		}
		
		if(expression.getClass().equals(PropertyCallExpImpl.class)){
			//varName += generateVarName(((CallExpImpl) expression).getSource(), null);
			varName += ".";
			varName += ((PropertyCallExpImpl) expression).getReferredProperty().getName();
		}else if(expression.getClass().equals(VariableExpImpl.class)){
			//varName += ((VariableExpImpl) expression).getReferredVariable().getType().getName();
		}else if(expression.getClass().equals(VariableImpl.class)){
			varName += ((VariableImpl) expression).getType().getName();
		}else if(expression.getClass().equals(SWRLLiteralArgumentImpl.class)){
			varName += "_" + ((SWRLLiteralArgumentImpl)expression).getLiteral().getLiteral();
		}else if(expression.getClass().equals(SWRLVariableImpl.class)){
			IRI iri = (IRI)((SWRLVariableImpl)expression).getIRI();
			varName += iri.getFragment();
		}else if(expression.getClass().equals(PropertyImpl.class)){
			varName += ".";
			varName += ((PropertyImpl) expression).getName();
			//varName += iri.getFragment();
		}else if(expression.getClass().equals(ClassImpl.class) && referredArgument == null){
			varName += ((ClassImpl) expression).getName();
			//varName += iri.getFragment();
		}else if(expression.getClass().equals(TypeExpImpl.class)){
			varName = ((TypeExpImpl) expression).getReferredType().getName();
			//varName += iri.getFragment();
		}
		
		varName = varName.replace(" ", "_");
		
		return varName;
	}
}
