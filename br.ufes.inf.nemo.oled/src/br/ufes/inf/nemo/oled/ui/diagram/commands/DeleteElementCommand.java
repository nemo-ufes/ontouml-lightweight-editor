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

import RefOntoUML.Derivation;
import RefOntoUML.Element;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Relationship;
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
 * @author Wei-ju Wu, Antognoni Albuquerque, John Guerson
 * @version 1.1
 */
public class DeleteElementCommand extends BaseDiagramCommand{

	private static final long serialVersionUID = 2456036038567915529L;
	
	private Collection<DiagramElement> diagramElemList = new ArrayList<DiagramElement>();
	private Collection<DiagramElement> diagramElemDep1List = new ArrayList<DiagramElement>(); //level1 of dependencies
	private Collection<DiagramElement> diagramElemDep2List = new ArrayList<DiagramElement>(); //level2 of dependencies(only true for derivation)
		
	private Collection<Element> elemList= new ArrayList<Element>();
	private Collection<Element> elemDep1List= new ArrayList<Element>(); // level1 of dependencies
	private Collection<Element> elemDep2List= new ArrayList<Element>(); // level2 of dependencies (only true for derivation)
	
	private List<ParentChildRelation> parentChildRelations = new ArrayList<ParentChildRelation>();
	private List<ParentChildRelation> parentChildRelationsDep1 = new ArrayList<ParentChildRelation>(); // level1 of dependencies
	private List<ParentChildRelation> parentChildRelationsDep2 = new ArrayList<ParentChildRelation>(); // level2 of dependencies 
	
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
	public DeleteElementCommand(DiagramNotification aNotification, Collection<Element> theElements, UmlProject project, boolean deleteFromModel, boolean deleteFromDiagram) 
	{
		this.project = project;
		this.notification = aNotification;	
		this.deleteFromDiagram = deleteFromDiagram;
		this.deleteFromModel = deleteFromModel;
		
		// requested element for deletion
		elemList.addAll(theElements);
		diagramElemList.addAll(ModelHelper.getDiagramElements(elemList));		
		
		// init the dependecies of level 1 and 2.
		for (Element elem : theElements) 
		{			
			// level 1 of dependency
			ArrayList<Relationship> depList = ProjectBrowser.getParserFor(project).getDirectRelationships(elem);			
			elemDep1List.addAll(depList);
			diagramElemDep1List.addAll(ModelHelper.getDiagramElements(elemDep1List));			
			
			// level 2 of dependency
			// the case in which there is a material in depList. We must include the derivation too.
			for(Relationship r: depList )
			{ 
				if (r instanceof MaterialAssociation) 
				{ 
					Derivation d = ProjectBrowser.getParserFor(project).getDerivation((MaterialAssociation)r);
					if(d!=null) {
						elemDep2List.add(d);
						diagramElemDep2List.add(ModelHelper.getDiagramElement(d));
					}
				}
			}			
		}
		
		// Parent children and their dependencies
		for (DiagramElement elem : diagramElemList){
			parentChildRelations.add(new ParentChildRelation(elem, elem.getParent()));
		}
		for(DiagramElement elem: diagramElemDep1List){
			parentChildRelationsDep1.add(new ParentChildRelation(elem, elem.getParent()));
		}
		for(DiagramElement elem: diagramElemDep2List){
			parentChildRelationsDep2.add(new ParentChildRelation(elem, elem.getParent()));
		}
	}
	
	/**
	 * {@inheritDoc} - REDO
	 */
	@Override
	public void redo() {
		redo = true;
		super.redo();
		run();
	}

	/**
	 * {@inheritDoc} - UNDO
	 */
	@Override
	public void undo() 
	{
		super.undo();
		
		//requested
		for (ParentChildRelation relation : parentChildRelations) {
			if (relation.element instanceof Connection) {
				reattachConnectionToNodes((Connection) relation.element);
			} else if (relation.element instanceof Node) {
				reattachNodeConnections((Node) relation.element);
			}			
			project.getEditingDomain().getCommandStack().undo();
			relation.parent.addChild(relation.element);
		}
		
		//dependencies of level1
		for (ParentChildRelation relation : parentChildRelationsDep1) {
			if (relation.element instanceof Connection) {
				reattachConnectionToNodes((Connection) relation.element);
			} else if (relation.element instanceof Node) {
				reattachNodeConnections((Node) relation.element);
			}
			project.getEditingDomain().getCommandStack().undo();
			relation.parent.addChild(relation.element);			
		}
		
		//dependecies of level2
		for (ParentChildRelation relation : parentChildRelationsDep2) {
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
			list.addAll(diagramElemList);
			list.addAll(diagramElemDep1List);
			list.addAll(diagramElemDep2List);
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_REMOVED, NotificationType.UNDO);		
		}
	}
	
	/**
	 * {@inheritDoc} - RUN
	 */
	public void run() 
	{
		// deletes dependencies level2 (derivations)
		delete(diagramElemDep2List,elemDep2List);
		
		// deletes dependencies level1
		delete(diagramElemDep1List,elemDep1List);

		// delete the element requested
		delete(diagramElemList, elemList); 			
	}
	
	public void delete(Collection<DiagramElement> diagramElemList, Collection<Element> elemList)
	{
		if(deleteFromDiagram) deleteFromDiagram(diagramElemList);			
				
		if(deleteFromModel) {
			deleteFromModel(elemList);
			for(RefOntoUML.Element deletedElement: elemList)
			{
				ProjectBrowser.frame.getDiagramManager().updateOLEDFromDeletion(deletedElement);	
			}
		}						
	}
	
	private void deleteFromDiagram(Collection<DiagramElement> diagramElemList)
	{
		for (DiagramElement element : diagramElemList) 
		{
			delete(element);
			ModelHelper.removeMapping(element);
		}
	}
	
	private void deleteFromModel(Collection<Element> elemList)
	{
		//delete first the derivations
		for(Element elem: elemList) {
			if (elem instanceof RefOntoUML.Derivation) delete(elem);				
		}
		//then the comments
		for(Element elem: elemList){
			if (elem instanceof RefOntoUML.Comment) delete(elem);
		}
		
		//then the constraints
		for(Element elem: elemList){
			if (elem instanceof RefOntoUML.Constraintx) delete(elem);
		}
				
		//then the rest of relationships
		for(Element elem: elemList) {				
			if (elem instanceof RefOntoUML.Relationship) delete(elem);
		}
		
		//then the classes and datatypes
		for(Element elem: elemList) {
			if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) delete(elem);			
		}
	}
	
	private void delete (DiagramElement element)
	{
		//detach ends
		if (element instanceof Connection) detachConnectionFromNodes((Connection) element);				
		else if (element instanceof Node) detachNodeConnections((Node)element);
				
		// delete
		if(element instanceof BaseConnection || element instanceof ClassElement) element.getParent().removeChild(element);			
				
		//notify
		if (notification!=null) {
			ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
			list.add(element);
			notification.notifyChange((List<DiagramElement>) list, ChangeType.ELEMENTS_REMOVED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)notification), this);
			for (UndoableEditListener l : ((DiagramEditor)notification).editListeners)  l.undoableEditHappened(event);			
		}		
	}
	
	private void delete (RefOntoUML.Element elem)
	{
		DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), elem);
		project.getEditingDomain().getCommandStack().execute(cmd);		
	}
	
	public Collection<DiagramElement> getDiagramElements() 
	{
		ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
		list.addAll(diagramElemList);
		list.addAll(diagramElemDep1List);
		list.addAll(diagramElemDep2List);
		return list;
	}
	
	/**
	 * Called when a node is removed. Detach the connections associated with
	 * this node from the other end nodes and remove them from their parents.
	 * 
	 * @param node
	 *            the node that is removed
	 * @return 
	 */
	private Collection<DiagramElement> detachNodeConnections(Node node) 
	{
		ArrayList<DiagramElement> detachedConnections = new ArrayList<>();
		detachedConnections.addAll(node.getConnections());
		
		for (Connection conn : node.getConnections()) 
		{
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
	private void reattachNodeConnections(Node node) 
	{
		for (Connection conn : node.getConnections()) 
		{
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
	private void detachConnectionFromNodes(Connection conn) 
	{
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
	private void reattachConnectionToNodes(Connection conn) 
	{
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
