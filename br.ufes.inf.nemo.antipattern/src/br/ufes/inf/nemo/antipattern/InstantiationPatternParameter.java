package br.ufes.inf.nemo.antipattern;

import RefOntoUML.Element;

public class InstantiationPatternParameter {
	
	private Element element;
	private int cardinality;
	
	public InstantiationPatternParameter(Element element, int cardinality) {
		super();
		this.setElement(element);
		this.setCardinality(cardinality);
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public int getCardinality() {
		return cardinality;
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
	
}
