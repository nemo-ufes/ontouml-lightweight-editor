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
package br.ufes.inf.nemo.oled.umldraw.shared;

import RefOntoUML.Element;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.impl.PropertyImpl;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * @author John Guerson
 */
public class UmlLabelFormatter {

	public static String getLabelTextFor(Element namedElement)
	{
		if(namedElement instanceof PropertyImpl)
		{
			Property property = (Property) namedElement;
			String type = new String();
			if(property.getType()!=null) type = property.getType().getName();
			return property.getName() + " : " + type+"["+ModelHelper.getMultiplicityString(property)+"]";			
		}
		if(namedElement instanceof Generalization)
		{
			String name = new String();
			int i=0;
			for(GeneralizationSet genSet: ((Generalization)namedElement).getGeneralizationSet()){
				if(i==((Generalization)namedElement).getGeneralizationSet().size()-1) name += genSet.getName();
				i++;
			}
			return name;
		}
		if(namedElement instanceof EnumerationLiteral){
			EnumerationLiteral literal = (EnumerationLiteral) namedElement;			
			return literal.getName();
		}
		/*else if(namedElement instanceof GeneralizationSetImpl)
		{
			GeneralizationSet genSet = (GeneralizationSet) namedElement; 
			
			String params = " {";
			if(genSet.isIsDisjoint())
				params += "disjoint";
			
			if(genSet.isIsCovering() && genSet.isIsDisjoint())
				params += ", ";
			
			if(genSet.isIsCovering())
				params += "covering";
			
			params += "}";
			
			return genSet.getName() + (genSet.isIsCovering() || genSet.isIsDisjoint() ? params : "" );
		}*/
		if(((NamedElement)namedElement)!=null){
			if (((NamedElement)namedElement).getName()==null) return "<null>";
			else return ((NamedElement)namedElement).getName();
		}else{
			return null;
		}
	}

}
