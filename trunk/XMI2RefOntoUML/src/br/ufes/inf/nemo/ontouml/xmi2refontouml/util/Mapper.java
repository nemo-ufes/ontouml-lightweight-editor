package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.Map;
import java.util.List;



public interface Mapper {
	
	public Object getModelElement();
	
	/* GET FUNCTIONS. THESE ARE REFERENT TO ONE ELEMENT */
	public String getID(Object elem);
	public String getName(Object elem);
	public ElementType getType(Object elem);
	public String getStereotype(Object elem);
	
	public Object getElementById(String id);
	
	// Retorna uma lista de elementos do tipo 'type' no escopo do elemento 'element'
	public List<Object> getElements(Object element, ElementType type);
	
	public Map<String, Object> getProperties(Object elem);
	
}
