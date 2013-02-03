package br.ufes.inf.nemo.common.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class RefOntoUMLResource extends XMIResourceImpl {

	public RefOntoUMLResource()
	{
		 super();
	}
	
	public RefOntoUMLResource(URI uri)
	{
		 super(uri);
	}
	
	protected boolean useUUIDs() {
        return true;
	}
}