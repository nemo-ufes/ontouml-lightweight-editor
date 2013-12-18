package br.ufes.inf.nemo.oled.util;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactory;

public class OutcomeFixer {
	
	public DiagramEditor diagramEditor;	
	
	@SuppressWarnings("unused")
	private DiagramManager manager;
	@SuppressWarnings("unused")
	private DiagramElementFactory diagramFactory;
	private UmlProject project;
	private OntoUMLParser refparser;
	
	public OutcomeFixer(DiagramEditor editor)
	{
		this.diagramEditor = editor;
		
		this.project = this.diagramEditor.getDiagram().getProject();
		this.manager = diagramEditor.getDiagramManager();
		this.diagramFactory = this.diagramEditor.getDiagram().getElementFactory();
		this.refparser = ProjectBrowser.getParserFor(project);
	}
	
	/**
	 * Change a model element's stereotype from the model. It does propagate the changes to the diagram. 
	 * 
	 * @param element: elem to be changed
	 * @param stereotype: new stereotype
	 */
	public RefOntoUML.Type changeStereotype(RefOntoUML.Type element, ElementType stereotype)
	{
		//Create new element with given stereotype
		RefOntoUML.Type newElement = ModelHelper.createType(stereotype, ((Classifier)element).getName());

		//Add element to the model and its parser
		element.eContainer().eContents().add(newElement);
		refparser.addElement(newElement);
		
		//Change the references...
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
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);
		DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, project,true,true);
		cmd.deleteFromModel(element);
		
		return newElement;
	}
	
	/**
	 * Change a model element's stereotype from the model. It does propagate the changes to the diagram. 
	 * 
	 * @param element: elem to be changed
	 * @param stereotype: new stereotype
	 */
	public RefOntoUML.Association changeStereotype(RefOntoUML.Association element, RelationType stereotype)
	{		
		//Create new element with given stereotype
		RefOntoUML.Association newElement = (Association)ModelHelper.createRelationship(stereotype, element.getName());

		//Add element to the model and its parser
		element.eContainer().eContents().add(newElement);
		refparser.addElement(newElement);
		
		//Change the references...
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
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);
		DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, project,true,true);
		cmd.deleteFromModel((Relationship)element);
		
		return newElement;
	}
	
	public void changeLinkToSubType(RefOntoUML.Association link, RefOntoUML.Element type, ElementType subtypeStereotype)
	{
		
	}
}
