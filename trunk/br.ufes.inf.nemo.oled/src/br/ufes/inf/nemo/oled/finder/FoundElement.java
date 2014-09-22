/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.finder;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.NamedElement;
import RefOntoUML.parser.OntoUMLNameHelper;

/**
 * @author John Guerson
 */
public class FoundElement {

	protected EObject eobject;
	protected String name;
	protected String path;
	protected ArrayList<EObject> pathHierarchyList = new ArrayList<EObject>();
	protected String stereotype;
	
	public FoundElement(EObject eobject)
	{
		this.eobject = eobject;
		name = getName(eobject);
		path = getPath(eobject);
		stereotype = OntoUMLNameHelper.getTypeName(eobject);
	}
	
	public String getName() { return name; } 
	public String getType() { return stereotype; }
	public String getPath() { return path; }
	public ArrayList<EObject> getPathHierarchy() { return pathHierarchyList; }
	public EObject getElement() { return eobject; }
	
	private String getPath (EObject e)
	{
		String path = "";				
		if (e.eContainer()!=null) {
			path += getPath((e.eContainer()))+"::";				
		}
		path += getName(((NamedElement)e))+"";
		pathHierarchyList.add(e);
		return path;
	}	
	
	@SuppressWarnings("unused")
	private String getStereotype(EObject e)
	{
		String stereotype = new String();
		stereotype = e.getClass().toString().replaceAll("class RefOntoUML.impl.","");
		stereotype = stereotype.replaceAll("Impl","");
		stereotype = Normalizer.normalize(stereotype, Normalizer.Form.NFD);	
	    if (!stereotype.equalsIgnoreCase("association")) stereotype = stereotype.replace("Association","");
	    return stereotype;
	}
	
	private String getName(EObject eobj)
	{
		if(eobj instanceof NamedElement){
			NamedElement c = (NamedElement)eobj;
			if (c.getName()==null) return "null";
			else if (c.getName().trim().isEmpty()) return"<empty>"; 
			else return c.getName();
		}else{
			return "<unnamed>";
		}
	}
	
	
}
