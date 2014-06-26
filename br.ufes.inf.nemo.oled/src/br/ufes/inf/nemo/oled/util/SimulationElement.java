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
package br.ufes.inf.nemo.oled.util;

import java.io.Serializable;

import RefOntoUML.PackageableElement;
import edu.mit.csail.sdg.alloy4graph.DotColor;
import edu.mit.csail.sdg.alloy4graph.DotShape;
import edu.mit.csail.sdg.alloy4graph.DotStyle;

public class SimulationElement implements Serializable
{
	private static final long serialVersionUID = -1180378790065998430L;
	private boolean simulate;
	private String elementUUID;
	private String name;
	private DotColor color;
	private DotShape shape;
	private DotStyle style;
	private transient PackageableElement element;
	
	public boolean isSimulate() {
		return simulate;
	}
	
	public void setSimulate(boolean simulate) {
		this.simulate = simulate;
	}
	
	public DotColor getColor() {
		return color;
	}
	
	public void setColor(DotColor color) {
		this.color = color;
	}
	
	public DotShape getShape() {
		return shape;
	}
	
	public void setShape(DotShape shape) {
		this.shape = shape;
	}
	
	public DotStyle getStyle() {
		return style;
	}
	
	public void setStyle(DotStyle style) {
		this.style = style;
	}
	
	public PackageableElement getElement() {		
		return element;
	}
	
	public void setElement(PackageableElement element) {
		if(element != null)
			elementUUID = element.eResource().getURIFragment(element);
		this.element = element;
	}
	
	public String getElementUUID() {
		return elementUUID;
	}

	public void setName(String name) {
		if(element != null)
			element.setName(name);
		else
			this.name = name;
	}

	public String getName() {
		if(element != null)
			return element.getName().replace(" ", "");
		return name;
	}
}