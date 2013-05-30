package br.ufes.inf.nemo.ontouml.editor.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EcoreResourceFactory extends XMIResourceFactoryImpl{

	public EcoreResourceFactory()
	{
		 super();
	}
	
	public Resource createResource(URI uri) {
        return new EcoreResource(uri);
	}
	
}
