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

import org.eclipse.emf.edit.command.DeleteCommand;

import RefOntoUML.Element;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.draw.CompositeNode;
import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.umldraw.structure.BaseConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * A command class to remove allElements from a diagram.
 * 
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.1
 */
public class DeleteElementCommand extends BaseDiagramCommand{

	private static final long serialVersionUID = 2456036038567915529L;
	private Collection<DiagramElement> elements;

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

	private List<ParentChildRelation> parentChildRelations = new ArrayList<ParentChildRelation>();

	/**
	 * Constructor.
	 * 
	 * @param aNotification
	 *            the ModelNotification object
	 * @param theElements
	 *            the DiagramElements to remove, each must have a parent
	 */
	public DeleteElementCommand(DiagramNotification aNotification, Collection<DiagramElement> theElements, UmlProject project) {
		this.project = project;
		this.notification = aNotification;
		elements = theElements;
		for (DiagramElement elem : elements) {
			parentChildRelations.add(new ParentChildRelation(elem, elem.getParent()));
		}
	}

	public Collection<DiagramElement> getElements() {
		return elements;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {

		for (DiagramElement element : elements) {
			
			
			if (element instanceof Connection) {
				detachConnectionFromNodes((Connection) element);
			}
			
			//FIXME In the execute method whithin the DiagramEditor, previously to deleting a class, it deletes all the nodes connections.
			// So this part of the code is no longer needed.
			/*else if (element instanceof Node) {
				detachNodeConnections((Node) element);
			}*/
			
			//Removes the element from model
			if(element instanceof ClassElement)
			{
				ClassElement classElement = (ClassElement) element;
				
				DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), classElement.getClassifier());
				project.getEditingDomain().getCommandStack().execute(cmd);
				
				//Remove the element from the auto completion of the OCL editor
				ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(classElement.getClassifier());
			}
			
			else if(element instanceof BaseConnection)
			{
				BaseConnection connection = (BaseConnection) element;
				
				DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), connection.getRelationship());
				project.getEditingDomain().getCommandStack().execute(cmd);
				
				//Remove the element from the auto completion of the OCL editor
				Relationship rel = connection.getRelationship();
				if (rel instanceof RefOntoUML.Association){
					Property source = ((RefOntoUML.Association) rel).getMemberEnd().get(0);
					Property target = ((RefOntoUML.Association) rel).getMemberEnd().get(1);					
					ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(source);
					ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(target);
				}
			}
			
			//Removes the element from diagram
			if(element instanceof BaseConnection || element instanceof ClassElement) {				
				element.getParent().removeChild(element);
				notification.notifyChange((List<DiagramElement>) elements, ChangeType.ELEMENTS_REMOVED, redo ? NotificationType.REDO : NotificationType.DO);
				
				
				//FIXME -- Removes the inferred elements. After creating the visual objects, use the delete command.
				
				ArrayList<Element> inferred = ProjectBrowser.getInferences(project).getInferredElements();
				
				OntoUMLParser parser = ProjectBrowser.getParserFor(project);
				for (Element e : inferred) {
					parser.removeElement(e);
				}
				
				// FIXME every modification creates a new tree
				ProjectBrowser.rebuildTree(project);		
			}
		}
		
		
		
		// 
		
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
		
		notification.notifyChange((List<DiagramElement>) elements, ChangeType.ELEMENTS_REMOVED, NotificationType.UNDO);

	}

	/**
	 * Called when a node is removed. Detach the connections associated with
	 * this node from the other end nodes and remove them from their parents.
	 * 
	 * @param node
	 *            the node that is removed
	 * @return TODO
	 */
	@SuppressWarnings("unused")
	private Collection<DiagramElement> detachNodeConnections(Node node) {
		ArrayList<DiagramElement> detachedConnections = new ArrayList<>();
		detachedConnections.addAll(node.getConnections());
		
		for (Connection conn : node.getConnections()) {
			if (conn.getNode1() != node)
				conn.getNode1().removeConnection(conn);
			if (conn.getNode2() != node)
				conn.getNode2().removeConnection(conn);
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
				conn.getNode1().addConnection(conn);
			if (conn.getNode2() != node)
				conn.getNode2().addConnection(conn);
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
		conn.getNode1().removeConnection(conn);
		conn.getNode2().removeConnection(conn);
	}

	/**
	 * Called when a remove connection operation is undone. Reattaches the
	 * connection to its associated nodes.
	 * 
	 * @param conn
	 *            the connection that is readded
	 */
	private void reattachConnectionToNodes(Connection conn) {
		conn.getNode1().addConnection(conn);
		conn.getNode2().addConnection(conn);
	}
}
