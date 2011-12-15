package br.inf.ufes.nemo.oled.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class OLEDResourceFactory extends XMIResourceFactoryImpl{

	public OLEDResourceFactory()
	{
		 super();
	}
	
	public Resource createResource(URI uri) {
        return new OLEDResource(uri);
	}
	
}
