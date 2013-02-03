package br.ufes.inf.nemo.xmi2refontouml.core;

import java.util.Map;
import java.util.List;

import br.ufes.inf.nemo.xmi2refontouml.util.ElementType;



public interface Mapper {
	
	public Object getModelElement();
	
	/* GET FUNCTIONS. THESE ARE REFERENT TO ONE ELEMENT */
	public String getID(Object element);
	public String getName(Object element);
	public ElementType getType(Object element);
	public String getStereotype(Object element);
	public String getPath(Object element);
	
	public Object getElementById(String id);
	
	// Retorna uma lista de elementos do tipo 'type' no escopo do elemento 'element'
	public List<Object> getElements(Object element, ElementType type);
	
	public Map<String, Object> getProperties(Object element);
	
	public String getRelatorfromMaterial(Object element);
	
	public List<Object> getDiagramList();
	public List<String> getDiagramElements(Object diagram) throws Exception;
	
}
