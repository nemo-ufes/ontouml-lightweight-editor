package br.ufes.inf.nemo.oled.util;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactory;

public class OutcomeFixer {
	
	public DiagramEditor diagramEditor;	
	public DiagramManager manager;
	public DiagramElementFactory diagramFactory;
	public UmlProject project;
	public OntoUMLParser refparser;
	
	public OutcomeFixer(DiagramEditor editor)
	{
		this.diagramEditor = editor;
		this.project = this.diagramEditor.getDiagram().getProject();
		this.manager = diagramEditor.getDiagramManager();
		this.diagramFactory = this.diagramEditor.getDiagram().getElementFactory();
		this.refparser = ProjectBrowser.getParserFor(project);
	}
	
	/**
	 * Change a model element's stereotype from the model (not from the diagrams). 
	 * 
	 * @param element: elem to be changed
	 * @param stereotype: new stereotype
	 */
	public RefOntoUML.Element changeStereotypeFromModel(RefOntoUML.Element element, ElementType stereotype)
	{
		//Create new element with given stereotype
		RefOntoUML.Type newElement = ModelHelper.createType(stereotype, ((Classifier)element).getName());
		
		//Add element to the model and its parser
		element.eContainer().eContents().add(newElement);
		
		//Make model elements reference the new element
		for(Relationship r: refparser.getAllInstances(RefOntoUML.Relationship.class))
		{
			if(r instanceof Generalization){
				Generalization gen = (Generalization)r;
				if (gen.getSpecific().equals(element)) gen.setSpecific((Classifier)newElement);
				if (gen.getGeneral().equals(element)) gen.setGeneral((Classifier)newElement);
			}	
			if(r instanceof Association){
				Association assoc = (Association)r;
				if (assoc.getMemberEnd().get(0).getType().equals(element)) assoc.getMemberEnd().get(0).setType(newElement);				
				if (assoc.getMemberEnd().get(1).getType().equals(element)) assoc.getMemberEnd().get(1).setType(newElement);	
			}
		}

		//Delete old element with old stereotype of the model
		//manager.deleteFromCurrentModel(element);
		
		return newElement;
	}
	
	public void changeLinkToSubTypeFromModel(RefOntoUML.Association link, RefOntoUML.Element type, ElementType subtypeStereotype)
	{
		
	}
}
