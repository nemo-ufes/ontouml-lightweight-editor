package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class RefOntoUMLResourceFactory extends XMIResourceFactoryImpl {
	
	public RefOntoUMLResourceFactory () {
		super();
	}
	
	public Resource createResource (URI uri) {
		return new RefOntoUMLResource(uri);
	}

}
