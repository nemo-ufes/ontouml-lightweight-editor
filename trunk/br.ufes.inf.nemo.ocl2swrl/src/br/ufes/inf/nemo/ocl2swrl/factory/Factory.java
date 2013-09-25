package br.ufes.inf.nemo.ocl2swrl.factory;

import java.util.Set;

import org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.ocl.uml.impl.VariableExpImpl;
import org.eclipse.uml2.uml.Property;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;

import br.ufes.inf.nemo.ocl2swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.IntegerLiteralExpImplFactory;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.OperationCallExpImplFactory;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.PropertyCallExpImplFactory;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.VariableExpImplFactory;
import br.ufes.inf.nemo.ocl2swrl.factory.uml2.uml.PropertyFactory;


public class Factory {
	public SWRLDArgument solve(String nameSpace, OWLOntologyManager manager, OWLDataFactory factory, OWLOntology ontology, Set<SWRLAtom> antecedent, Set<SWRLAtom> consequent) {
		throw new NonImplemented("solve()");
	}
	
	public static Object constructor(Object obj){
		Class<? extends Object> c = obj.getClass();
		if(c.equals(PropertyCallExpImpl.class)){
			return new PropertyCallExpImplFactory((PropertyCallExpImpl) obj);
		}else if(c.equals(OperationCallExpImpl.class)){
			return new OperationCallExpImplFactory((OperationCallExpImpl) obj);
		}else if(c.equals(VariableExpImpl.class)){
			return new VariableExpImplFactory((VariableExpImpl) obj);
		}else if(c.equals(Property.class)){
			return new PropertyFactory((Property) obj);
		}else if(c.equals(IntegerLiteralExpImpl.class)){
			return new IntegerLiteralExpImplFactory((IntegerLiteralExpImpl) obj);
		}else{
			throw new NonSupported(c.getName());
		}
	}
}
