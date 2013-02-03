package br.ufes.inf.nemo.oled.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class OLEDResource extends XMIResourceImpl {

	public OLEDResource()
	{
		 super();
	}
	
	public OLEDResource(URI uri)
	{
		 super(uri);
	}
	
	protected boolean useUUIDs() {
        return true;
	}
}