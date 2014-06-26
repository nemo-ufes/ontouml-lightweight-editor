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
package br.ufes.inf.nemo.oled.explorer;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;

/** 
 * Custom OntoUML element used for listing ref-onto elements 
 * 
 * @author John Guerson
 * */
public class CustomOntoUMLElement extends OntoUMLElement {

	public CustomOntoUMLElement(EObject refElement, String uniqueName) { super(refElement, uniqueName); }		
	
	@Override
	public String toString() {
		if (this.element instanceof GeneralizationSet) {
			return type + " "+((NamedElement)element).getName();
		}		
		else if (this.element instanceof Generalization)
		{
			String general = new String();
			String specific = new String();
			if (((Generalization)element).getGeneral()==null) general = "null";
			else general = ((Generalization)element).getGeneral().getName();
			if (((Generalization)element).getSpecific()==null) specific = "null";
			else specific = ((Generalization)element).getSpecific().getName();
			return type + " "+specific+"->"+general;
		}		
		else return super.toString();		
	}
}
