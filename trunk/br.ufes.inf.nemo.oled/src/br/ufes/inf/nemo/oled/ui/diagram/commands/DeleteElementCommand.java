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
package br.ufes.inf.nemo.oled.ui.diagram.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import org.eclipse.emf.edit.command.DeleteCommand;

import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.Element;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * A command class to remove allElements from a diagram.
 * 
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.1
 */
public class DeleteElementCommand extends BaseDiagramCommand{

	private static final long serialVersionUID = 2456036038567915529L;
	
	private Collection<DiagramElement> diagramElementList = new ArrayList<DiagramElement>();
	private Collection<DiagramElement> diagramDependenceList_L1 = new ArrayList<DiagramElement>(); //level1
	private Collection<DiagramElement> diagramDependenceList_L2 = new ArrayList<DiagramElement>(); //level2 (only true for derivation)
		
	private Collection<Element> elementList= new ArrayList<Element>();
	private Collection<Element> elementDependenceList_L1= new ArrayList<Element>(); // level1
	private Collection<Element> elementDependenceList_L2= new ArrayList<Element>(); // level2 (only true for derivation)
	
	private List<ParentChildRelation> parentChildRelations = new ArrayList<ParentChildRelation>();
	private List<ParentChildRelation> parentChildDependencies_L1 = new ArrayList<ParentChildRelation>();
	private List<ParentChildRelation> parentChildDependencies_L2 = new ArrayList<ParentChildRelation>();
	
	private boolean deleteFromModel;
	private boolean deleteFromDiagram;
	
	/**
	 * A helper class to store the original parent child relation.
	 */
	private static class ParentChildRelation {
		DiagramElement element;
		CompositeNode parent;
		
		/**
		 * Constructor.
		 * 
		 * @param anElement
		 *            the element
		 * @param aParent
		 *            the element's parent
		 */
		public ParentChildRelation(DiagramElement anElement, CompositeNode aParent) {
			parent = aParent;
			element = anElement;
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param aNotification
	 *            the ModelNotification object
	 * @param theElements
	 *            the DiagramElements to remove, each must have a parent
	 */
	public DeleteElementCommand(DiagramNotification aNotification, Collection<Element> theElements, UmlProject project, boolean deleteFromModel, boolean deleteFromDiagram) {
		this.project = project;
		this.notification = aNotification;	
		this.deleteFromDiagram = deleteFromDiagram;
		this.deleteFromModel = deleteFromModel;
		elementList.addAll(theElements);
		diagramElementList.addAll(ModelHelper.getDiagramElements(elementList));		
		
		//Dependencies
		for (Element elem : theElements) 
		{			
			ArrayList<Relationship> depList = ProjectBrowser.getParserFor(project).getSelectedAndNonSelectedRelationshipsOf(elem);
			elementDependenceList_L1.addAll(depList);
			diagramDependenceList_L1.addAll(ModelHelper.getDiagramElements(elementDependenceList_L1));			
			
			//the case in which there is a material in depList. We must include the derivation too.
			for(Relationship r: depList ){ if (r instanceof MaterialAssociation) { 
					Derivation d = ProjectBrowser.getParserFor(project).getDerivation((MaterialAssociation)r);
					if(d!=null){
						elementDependenceList_L2.add(d);
						diagramDependenceList_L2.add(ModelHelper.getDiagramElement(d));
					}
				}
			}			
		}
		
		//ParentChilds
		for (DiagramElement elem : diagramElementList){
			parentChildRelations.add(new ParentChildRelation(elem, elem.getParent()));
		}
		for(DiagramElement elem: diagramDependenceList_L1){
			parentChildDependencies_L1.add(new ParentChildRelation(elem, elem.getParent()));
		}
		for(DiagramElement elem: diagramDependenceList_L2){
			parentChildDependencies_L2.add(new ParentChildRelation(elem, elem.getParent()));
		}
	}
	
	public Collection<DiagramElement> getDiagramElements() {
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
		list.addAll(diagramElementList);
		list.addAll(diagramDependenceList_L1);
		list.addAll(diagramDependenceList_L2);
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		delete(diagramDependenceList_L2,elementDependenceList_L2);
		delete(diagramDependenceList_L1,elementDependenceList_L1);
		delete(diagramElementList, elementList);			
	}
	
	/**
	 * deletion
	 * @param diagramElemList
	 */
	public void delete(Collection<DiagramElement> diagramElemList, Collection<Element> elemList)
	{
		if(deleteFromDiagram) {
			for (DiagramElement element : diagramElemList) {
				deleteFromDiagram(element);
				ModelHelper.removeMapping(element);
			}
		}
		
		if(deleteFromModel){
			//delete first the derivations
			for(Element elem: elemList) {				
				if (elem instanceof RefOntoUML.Derivation) deleteFromModel(elem);				
				if(deleteFromModel) ModelHelper.removeMapping(elem);
			}
			//then the rest of relationships
			for(Element elem: elemList) {				
				if (elem instanceof RefOntoUML.Relationship) deleteFromModel(elem);				
				if(deleteFromModel) ModelHelper.removeMapping(elem);
			}
			//then the classes and datatypes
			for(Element elem: elemList) {
				if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) deleteFromModel(elem);
				if(deleteFromModel) ModelHelper.removeMapping(elem);
			}
		}	
					
		//FIXME - Tiago, you need to fix this part of the code (John).  
		//Removes the inferred elements. After creating the visual objects, use the delete command.			
		ArrayList<Element> inferred = ProjectBrowser.getInferences(project).getInferredElements();			
		OntoUMLParser parser = ProjectBrowser.getParserFor(project);
		for (Element e : inferred) {
			parser.removeElement(e);
		}		
			
		ProjectBrowser.frame.getDiagramManager().updateUI();
	}
	
	/**
	 * Deletes a diagram element from the diagram (not from the model instance behind the scenes)
	 * @param elem
	 */
	public void deleteFromDiagram (DiagramElement element)
	{
		if (element instanceof Connection) {			
			detachConnectionFromNodes((Connection) element);				
		}else if (element instanceof Node){
			detachNodeConnections((Node)element);
		}
		
		if(element instanceof BaseConnection || element instanceof ClassElement) 
		{				
			element.getParent().removeChild(element);			
		}
		
		if (notification!=null){
			ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
			list.add(element);
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_REMOVED, redo ? NotificationType.REDO : NotificationType.DO);
			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)notification), this);
			for (UndoableEditListener l : ((DiagramEditor)notification).editListeners) {
				l.undoableEditHappened(event);
			}
		}		
	}
	
	/**
	 * Deletes a relationship from the model instance behind the scenes and updates the application accordingly.
	 * @param elem
	 */
	public void deleteFromModel (RefOntoUML.Element elem)
	{
		DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), elem);
		project.getEditingDomain().getCommandStack().execute(cmd);
		
		//update the application accordingly
		updateApplication(elem);
	}
	
	/** Update the application accordingly */
	public static void updateApplication(RefOntoUML.Element deletedElement)
	{		
		UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
		
		ProjectBrowser.getParserFor(project).removeElement(deletedElement);
		
		//Remove the element from the auto completion of the OCL editor			
		if (deletedElement instanceof RefOntoUML.Association)
		{			
			if (!((RefOntoUML.Association) deletedElement).getMemberEnd().isEmpty())
			{			
				Property source = ((RefOntoUML.Association) deletedElement).getMemberEnd().get(0);
				Property target = ((RefOntoUML.Association) deletedElement).getMemberEnd().get(1);			
				
				ProjectBrowser.getParserFor(project).removeElement(source);
				ProjectBrowser.getParserFor(project).removeElement(target);
			
				ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(source);
				ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(target);
			}
		}else if (deletedElement instanceof RefOntoUML.Class || deletedElement instanceof RefOntoUML.DataType){		
			ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion((Classifier)deletedElement);			
		}		
		
		//FIXME - Do not rebuild the tree, only update it!
		ProjectBrowser.rebuildTree(project);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		redo = true;
		super.redo();
		run();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		super.undo();
		
		for (ParentChildRelation relation : parentChildRelations) {
			if (relation.element instanceof Connection) {
				reattachConnectionToNodes((Connection) relation.element);
			} else if (relation.element instanceof Node) {
				reattachNodeConnections((Node) relation.element);
			}			
			project.getEditingDomain().getCommandStack().undo();
			relation.parent.addChild(relation.element);
		}
		
		for (ParentChildRelation relation : parentChildDependencies_L1) {
			if (relation.element instanceof Connection) {
				reattachConnectionToNodes((Connection) relation.element);
			} else if (relation.element instanceof Node) {
				reattachNodeConnections((Node) relation.element);
			}
			project.getEditingDomain().getCommandStack().undo();
			relation.parent.addChild(relation.element);			
		}
		
		for (ParentChildRelation relation : parentChildDependencies_L2) {
			if (relation.element instanceof Connection) {
				reattachConnectionToNodes((Connection) relation.element);
			} else if (relation.element instanceof Node) {
				reattachNodeConnections((Node) relation.element);
			}
			project.getEditingDomain().getCommandStack().undo();
			relation.parent.addChild(relation.element);			
		}
		
		if(notification!=null){
			ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
			list.addAll(diagramElementList);
			list.addAll(diagramDependenceList_L1);
			list.addAll(diagramDependenceList_L2);
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_REMOVED, NotificationType.UNDO);		
		}
	}

	/**
	 * Called when a node is removed. Detach the connections associated with
	 * this node from the other end nodes and remove them from their parents.
	 * 
	 * @param node
	 *            the node that is removed
	 * @return 
	 */
	private Collection<DiagramElement> detachNodeConnections(Node node) {
		ArrayList<DiagramElement> detachedConnections = new ArrayList<>();
		detachedConnections.addAll(node.getConnections());
		
		for (Connection conn : node.getConnections()) {
			if (conn.getNode1() != node)
				if(conn.getNode1()!=null)
					conn.getNode1().removeConnection(conn);
				else
					conn.getConnection1().removeConnection(conn);
			if (conn.getNode2() != node){
				if(conn.getNode2()!=null)
					conn.getNode2().removeConnection(conn);
				else 
					conn.getConnection2().removeConnection(conn);
			}
			conn.getParent().removeChild(conn);
		}
		
		return detachedConnections;
	}

	/**
	 * Called when a remove node operation is undone. Re-attaches the
	 * connections associated with the specified node to the former other end
	 * nodes and readd them to their parents.
	 * 
	 * @param node
	 *            the node that is readded
	 */
	private void reattachNodeConnections(Node node) {
		for (Connection conn : node.getConnections()) {
			if (conn.getNode1() != node)
				if (conn.getNode1()!=null)
					conn.getNode1().addConnection(conn);
				else
					conn.getConnection1().addConnection(conn);
			if (conn.getNode2() != node){
				if (conn.getNode2()!=null)
					conn.getNode2().addConnection(conn);
				else 
					conn.getConnection2().addConnection(conn);
			}
			conn.getParent().addChild(conn);
		}
	}

	/**
	 * Called when a connection is removed. Detaches the connection from its
	 * associated nodes, but keeps them in the connection to restore them in the
	 * undo operation.
	 * 
	 * @param conn
	 *            the connection that is removed
	 */
	private void detachConnectionFromNodes(Connection conn) {
		if (conn.getNode1()!=null) conn.getNode1().removeConnection(conn);
		else conn.getConnection1().removeConnection(conn);
		if (conn.getNode2()!=null) conn.getNode2().removeConnection(conn);
		else conn.getConnection2().removeConnection(conn);
	}

	/**
	 * Called when a remove connection operation is undone. Reattaches the
	 * connection to its associated nodes.
	 * 
	 * @param conn
	 *            the connection that is readded
	 */
	private void reattachConnectionToNodes(Connection conn) {
		if (conn.getNode1()!=null)
			conn.getNode1().addConnection(conn);
		else
			conn.getConnection1().addConnection(conn);
		if (conn.getNode2()!=null)
			conn.getNode2().addConnection(conn);
		else
			conn.getConnection2().addConnection(conn);
	}
}
