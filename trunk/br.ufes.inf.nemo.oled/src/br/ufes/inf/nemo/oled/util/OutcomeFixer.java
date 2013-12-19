package br.ufes.inf.nemo.oled.util;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.CreationHandler;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.LineHandler;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddConnectionCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlNode;

/** 
 * Teste Version!
 * 
 * @author Guerson
 *
 */
public class OutcomeFixer {
	
	public DiagramManager manager;	
	public DiagramEditor diagramEditor;	
	private CreationHandler creationHandler;
	@SuppressWarnings("unused")
	private LineHandler lineHandler;	

	public OutcomeFixer(DiagramManager manager)
	{
		this.manager = manager;
		this.diagramEditor = manager.getCurrentDiagramEditor();
		this.creationHandler = diagramEditor.getCreationHandler();
		this.lineHandler = diagramEditor.getLineHandler();				
	}
	
	/**
	 * Add to model and to the diagram (if necessary)
	 * @param stereotype
	 * @param toBeCloned
	 * @return
	 */
	public UmlNode add (ElementType stereotype, RefOntoUML.Type toBeCloned, DiagramEditor diagramEditor, boolean propagateToDiagram)
	{
		//Create a new element with a given stereotype	
		CreationHandler cHandler = diagramEditor.getCreationHandler();
		UmlNode umlnode = (UmlNode) cHandler.createCloning(stereotype, toBeCloned);
		
		//Add element/node to model/diagram
		if(ModelHelper.getDiagramElement(toBeCloned)!=null && propagateToDiagram){
			AddNodeCommand addCmd = new AddNodeCommand(diagramEditor, diagramEditor.getDiagram(), umlnode.getClassifier(), 10, 10, diagramEditor.getProject(), true, true, null);
			diagramEditor.execute(addCmd);
		}else {
			AddNodeCommand addCmd = new AddNodeCommand(null, null, umlnode.getClassifier(), 10, 10, manager.getCurrentProject(), true, false, null);
			addCmd.addToModel(toBeCloned);
		}
		return umlnode;
	}
	
	/**
	 * Add to model and to the diagram (if necessary)
	 * @param stereotype
	 * @param toBeCloned
	 * @return
	 */
	public UmlNode add (ElementType stereotype, DiagramEditor diagramEditor)
	{
		//Create a new element with a given stereotype	
		CreationHandler cHandler = diagramEditor.getCreationHandler();
		UmlNode umlnode = (UmlNode) cHandler.create(stereotype);
		
		//Add element/node to model/diagram
		if(diagramEditor!=null){
			AddNodeCommand addCmd = new AddNodeCommand(diagramEditor, diagramEditor.getDiagram(), umlnode.getClassifier(), 10, 10, diagramEditor.getProject(), true, true, null);
			diagramEditor.execute(addCmd);		
//		}else{
//			AddNodeCommand addCmd = new AddNodeCommand(null, null, umlnode.getClassifier(), 10, 10, manager.getCurrentProject(), true, false,null);
//			addCmd.addToModel(umlnode.getClassifier());
		}
		return umlnode;
	}
	
	/**
	 * Add to model and to the diagram (if necessary)
	 * @param stereotype
	 * @param toBeCloned
	 * @return
	 */
	public UmlConnection add (RelationType stereotype, RefOntoUML.Relationship toBeCloned, DiagramEditor diagramEditor)
	{		
		//Create a new element with a given stereotype	
		guaranteeUmlNodes(toBeCloned);
		LineHandler lHandler = diagramEditor.getLineHandler();
		UmlConnection conn = (UmlConnection) lHandler.createCloning(stereotype, toBeCloned);
		
		//Add element/node to model/diagram
		 
		if( diagramEditor!=null && (ModelHelper.getDiagramElement(getSource(conn.getRelationship())) != null) && (ModelHelper.getDiagramElement(getTarget(conn.getRelationship()))!=null) ) {
			AddConnectionCommand addCmd = new AddConnectionCommand(diagramEditor, diagramEditor.getDiagram(), conn.getRelationship(), getSource(conn.getRelationship()), getTarget(conn.getRelationship()), manager.getCurrentProject(), true, true);
			diagramEditor.execute(addCmd);
		}else{
			AddConnectionCommand addCmd = new AddConnectionCommand(null, null, conn.getRelationship(), getSource(conn.getRelationship()), getTarget(conn.getRelationship()), manager.getCurrentProject(), true, false);
			addCmd.addToModel(conn.getRelationship());
		}
		return conn;
	}
	
	/**
	 * Get Source
	 * @param relationship
	 * @return
	 */
	private RefOntoUML.Classifier getSource(Relationship relationship)
	{
		 RefOntoUML.Type sourceType = null;
		 if(relationship instanceof Generalization){
			 sourceType = ((Generalization)relationship).getSpecific();			 
		 }else if (relationship instanceof Association){
			 sourceType = ((Association)relationship).getMemberEnd().get(0).getType();			
		 }
		 return (Classifier) sourceType;
	}
	
	/**
	 * Get Target
	 * @param relationship
	 * @return
	 */
	private RefOntoUML.Classifier getTarget(Relationship relationship)
	{
		 RefOntoUML.Type targetType = null;
		 if(relationship instanceof Generalization){
			 targetType = ((Generalization)relationship).getGeneral();			 
		 }else if (relationship instanceof Association){
			 targetType = ((Association)relationship).getMemberEnd().get(1).getType();			
		 }
		 return (Classifier) targetType;
	}
	
	/**
	 * Guarantee the existence of a UmlNode for source and for target. Note that after this, the UmlNodes are not in the diagram yet.
	 * @param relationship
	 */
	private void guaranteeUmlNodes(RefOntoUML.Relationship relationship)
	{		
		 RefOntoUML.Type sourceType = getSource(relationship);
		 RefOntoUML.Type targetType = getTarget(relationship);		
		 if(sourceType!=null && targetType!=null){			  
			  DiagramElement source = ModelHelper.getDiagramElement(sourceType);
			  DiagramElement target = ModelHelper.getDiagramElement(targetType);		  
			  if(source==null){
				  creationHandler.create(sourceType);
				  source = ModelHelper.getDiagramElement(sourceType);
			  }
			  if(target==null){
				  creationHandler.create(targetType);
				  target = ModelHelper.getDiagramElement(targetType);
			  }
		 }
	}
	
	/**
	 * Change an element's stereotype from the model. It does not completely propagate the changes to the diagram. 
	 * 
	 * @param element: elem to be changed
	 * @param stereotype: new stereotype
	 */
	public RefOntoUML.Type changeStereotype(RefOntoUML.Type element, ElementType stereotype)
	{		
		//Create a new element with a given stereotype	
		UmlNode umlnode = add(stereotype,element, diagramEditor, true);
		
		//Change the references...
		//FIXME: Propagate changes to the diagram!
		RefOntoUML.Type newElement = (Type) umlnode.getClassifier();
		OntoUMLParser refparser = ProjectBrowser.getParserFor(manager.getCurrentProject());
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
	
		//Delete old element/node from model/diagram
		manager.delete(element,diagramEditor);
		
		return newElement;
	}
	
	/**
	 * Change an element's stereotype from the model. It does not completely propagate the changes to the diagram. 
	 * 
	 * @param element: elem to be changed
	 * @param stereotype: new stereotype
	 */
	public RefOntoUML.Relationship changeStereotype(RefOntoUML.Association element, RelationType stereotype)
	{		
		//Create a new element with a given stereotype			
		UmlConnection conn = add(stereotype,element,diagramEditor);
		
		//Change the references...
		//FIXME: Propagate changes to the diagram!
		RefOntoUML.Relationship newElement = (Relationship) conn.getRelationship();
		OntoUMLParser refparser = ProjectBrowser.getParserFor(manager.getCurrentProject());
		for(Relationship r: refparser.getAllInstances(RefOntoUML.Relationship.class))
		{
			if(r instanceof Generalization){
				Generalization gen = (Generalization)r;
				if (gen.getSpecific().equals(element)) gen.setSpecific((Classifier)newElement);
				if (gen.getGeneral().equals(element)) gen.setGeneral((Classifier)newElement);
			}
			if(r instanceof Association){
				Association assoc = (Association)r;
				if (assoc.getMemberEnd().get(0).getType().equals(element)) assoc.getMemberEnd().get(0).setType((RefOntoUML.Type)newElement);				
				if (assoc.getMemberEnd().get(1).getType().equals(element)) assoc.getMemberEnd().get(1).setType((RefOntoUML.Type)newElement);	
			}
		}
	
		//Delete old element/node from model/diagram
		manager.delete(element,diagramEditor);
		
		return newElement;
	}
	
	public void changeLinkToSubType(RefOntoUML.Association link, RefOntoUML.Element type, ElementType subtypeStereotype)
	{
		
		
	}
}
