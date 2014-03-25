package br.ufes.inf.nemo.xmi2ontouml.xmiparser;

import java.util.Map;
import java.util.List;

import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;

public interface XMIParser {
	
	public Object getRoot();
	
	public String getStereotype(Object element);
	
	// Retorna uma lista de elementos do tipo 'type' no escopo do elemento 'element'
	public List<Object> getElements(Object element, ElementType type);
	
	public Map<String, Object> getProperties(Object element);
	
}
