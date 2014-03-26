/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.umldraw.shared;

import java.io.Serializable;

import RefOntoUML.Element;
import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.oled.draw.LabelSource;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class decorates an UmlProperty with the Label source interface.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class UmlModelElementLabelSource implements LabelSource, Serializable {

  private static final long serialVersionUID = 8535923074605287093L;
  private transient Element namedElement;
  private StructureDiagram diagram;
  private String propertyUUID;
  
  /**
   * Constructor.
   * @param aNamedElement aNamedElement
   */
  public UmlModelElementLabelSource(StructureDiagram diagram, Element aNamedElement) {
    namedElement = aNamedElement;
    this.diagram = diagram;
    propertyUUID = ModelHelper.getUUIDFromElement(namedElement);
    
  }

  /**
   * Gets the source element
   * @return the source named element
   */
  public Element getNamedElement() {
	//In case of deserialization, attempts to retrieve the element from model
	if(namedElement == null && propertyUUID != null)
	{		
		namedElement = (Element) ModelHelper.getElementByUUID(diagram.getModel(), propertyUUID);
	}
			
	return namedElement;
  }

  /**
   * {@inheritDoc}
   */
  public String getLabelText() {
	  getNamedElement();
	  return  UmlLabelFormatter.getLabelTextFor(namedElement);	  
  }

  /**
   * {@inheritDoc}
   */
  public void setLabelText(String aText) { ((NamedElement)namedElement).setName(aText); }
}
