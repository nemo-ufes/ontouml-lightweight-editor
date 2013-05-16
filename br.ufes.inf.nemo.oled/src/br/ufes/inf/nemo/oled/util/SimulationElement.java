package br.ufes.inf.nemo.oled.util;

import java.io.Serializable;

import edu.mit.csail.sdg.alloy4graph.DotColor;
import edu.mit.csail.sdg.alloy4graph.DotShape;
import edu.mit.csail.sdg.alloy4graph.DotStyle;

import RefOntoUML.PackageableElement;

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