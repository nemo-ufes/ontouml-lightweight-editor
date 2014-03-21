package br.ufes.inf.nemo.common.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class RefOntoUMLResourceFactoryImpl extends XMIResourceFactoryImpl{

	public RefOntoUMLResourceFactoryImpl()
	{
		 super();
	}
	
	public Resource createResource(URI uri) {
        return new RefOntoUMLResource(uri);
	}
	
}