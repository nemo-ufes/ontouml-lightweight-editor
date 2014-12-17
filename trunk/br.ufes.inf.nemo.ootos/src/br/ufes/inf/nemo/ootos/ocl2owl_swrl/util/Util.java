package br.ufes.inf.nemo.ootos.ocl2owl_swrl.util;

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
	/**
	 * This method generate a variable name according to the involved expression and a possible referred argument
	 * 
	 * @param expression - contains the expression
	 * @param referredArgument - contains the referred argument (can be null)
	 */
	public static String generateVarName(Object expression, SWRLDArgument referredArgument){
		String varName = "";
		
		//considers the case of non null referred argument
		if(referredArgument != null){
			Class<? extends SWRLDArgument> c = referredArgument.getClass();
			//if the referred argument class is SWRLVariableImpl, its name is added to the resulting variable name
			if(c.equals(SWRLVariableImpl.class)){
				SWRLVariable var = (SWRLVariableImpl) referredArgument;
				IRI iri = var.getIRI();
				
				varName += iri.getFragment();
				
				
			}else if(c.equals(SWRLLiteralArgumentImpl.class)){//if the referred argument class is SWRLLiteralArgumentImpl, its name is added to the resulting variable name
				SWRLLiteralArgument arg = (SWRLLiteralArgumentImpl) referredArgument;
				arg.getLiteral();
			}
		}
		
		if(expression == null){
			return varName;
		}
		
		//increment the variable name according to the expression type
		if(expression.getClass().equals(PropertyCallExpImpl.class)){
			varName += ".";
			varName += ((PropertyCallExpImpl) expression).getReferredProperty().getName();
		}else if(expression.getClass().equals(VariableExpImpl.class)){
			varName = ((VariableExpImpl) expression).getName();
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
		}else if(expression.getClass().equals(ClassImpl.class) && referredArgument == null){
			varName += ((ClassImpl) expression).getName();
		}else if(expression.getClass().equals(TypeExpImpl.class)){
			varName = ((TypeExpImpl) expression).getReferredType().getName();
		}
		
		//replace all spaces with underscores, following the OntoUML2OWL+SWRL transformation
		varName = varName.replace(" ", "_");
		
//		if(varName.toLowerCase().equals("pm_lc_bi_component")){
//			System.out.println();
//		}
		
		return varName;
	}
}
