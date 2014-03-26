package br.ufes.inf.nemo.xmi2ontouml.framework;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.nemo.xmi2ontouml.util.ElementType;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.XMIParser;

public class XMI2RefDiagram extends XMI2RefNamedElement
{
	private List<XMI2RefDiagramElement> diagElemList = new ArrayList<XMI2RefDiagramElement>();
	
	public XMI2RefDiagram(Object XMIElement, XMIParser mapper)
	{
		super(XMIElement, mapper, null);
		
		XMI2RefModel.getDiagrams().add(this);
	}
	
	@Override
	protected void deal() {}

	@Override
	public void dealReferences() throws Exception {}
	
	@Override
	protected void createSubElements()
	{
		for (Object diagElem : this.Mapper.getElements(XMIElement, ElementType.DIAGRAMELEMENT))
		{
			XMI2RefDiagramElement xmi2refdiagelem = new XMI2RefDiagramElement(diagElem, Mapper);
			diagElemList.add(xmi2refdiagelem);
		}
	}

	public List<XMI2RefDiagramElement> getDiagElemList() {
		return diagElemList;
	}
}
