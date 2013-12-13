package br.ufes.inf.nemo.oled.ui.antipattern;

import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactory;

public class OutcomeFixer {
	
	public DiagramEditor diagramEditor;	
	public DiagramElementFactory diagramFactory;
	public UmlProject project;
	
	public OutcomeFixer(DiagramEditor editor)
	{
		this.diagramEditor = editor;
		this.diagramEditor.getDiagram().getProject();
		this.diagramFactory = this.diagramEditor.getDiagram().getElementFactory();
	}
	
	public void changeStereotype(RefOntoUML.Element element, ElementType elementType)
	{
		
	}
}
