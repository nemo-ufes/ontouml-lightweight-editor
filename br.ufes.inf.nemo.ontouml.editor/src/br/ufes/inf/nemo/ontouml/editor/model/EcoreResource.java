package br.ufes.inf.nemo.ontouml.editor.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class EcoreResource extends XMIResourceImpl {

	public EcoreResource()
	{
		 super();
	}
	
	public EcoreResource(URI uri)
	{
		 super(uri);
	}
	
	protected boolean useUUIDs() {
        return true;
	}
}