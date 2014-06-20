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
import java.util.List;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import br.ufes.inf.nemo.oled.draw.Connection;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.MoveNodeOperation;
import br.ufes.inf.nemo.oled.draw.MoveOperation;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.draw.TranslateConnectionOperation;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;


/**
 * This class represents the move of one or more diagram allElements.
 * Theoretically, this method simply executes the list of Commands and could
 * also execute anything else. The difference is that this method also
 * notifies the system about an element move.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque
 * @version 1.0
 */
public class MoveElementCommand extends BaseDiagramCommand {

	private static final long serialVersionUID = 2523534899493234371L;
	private MoveOperation[] moveOperations;

	/**
	 * Constructor.
	 * @param aNotification the notification
	 * @param aMoveOperations the move operations
	 */
	public MoveElementCommand(DiagramNotification aNotification, final MoveOperation[] aMoveOperations) {
		this.notification = aNotification;
		moveOperations = new MoveOperation[aMoveOperations.length];
		for (int i = 0; i < aMoveOperations.length; i++) {
			moveOperations[i] = aMoveOperations[i];
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		
		for (MoveOperation moveOperation : moveOperations) {			
			if(moveOperation instanceof MoveNodeOperation){
				moveOperation.run();
				elements.add(((MoveNodeOperation)moveOperation).getNode());				
			}//else if(moveOperation instanceof TranslateConnectionOperation){
//				moveOperation.run();
//				elements.add(((TranslateConnectionOperation)moveOperation).getConnection());				
//			}
		}
		
		//move the connections related to every connection moved
		//this should be done through the MoveOperation and not by reseting points
		for(DiagramElement elem: elements){			
			if (elem instanceof Node){
				Node node = (Node)elem;
				for(Connection c: node.getConnections()){					
					resetRelatedConnectionPoints((DiagramEditor)notification, c);
				}
			}
		}

		DiagramEditor d = ((DiagramEditor)notification);
		//notify
		if (d!=null) {
			d.notifyChange((List<DiagramElement>) elements, ChangeType.ELEMENTS_MOVED, redo ? NotificationType.REDO : NotificationType.DO);			
			UndoableEditEvent event = new UndoableEditEvent(((DiagramEditor)d), this);
			for (UndoableEditListener l : ((DiagramEditor)d).editListeners)  l.undoableEditHappened(event);			
		}
	}

	public void resetRelatedConnectionPoints(DiagramEditor notification, Connection con)
	{
		if(con.getConnections()!=null){
			for(Connection c2: con.getConnections()) { 
				c2.resetPoints();
				c2.invalidate();
			}	
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		
		super.undo();
		
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		
		for (MoveOperation moveOperation : moveOperations) {
			moveOperation.undo();
			if(moveOperation instanceof MoveNodeOperation)
				elements.add(((MoveNodeOperation)moveOperation).getNode());
			else if(moveOperation instanceof TranslateConnectionOperation)
				elements.add(((TranslateConnectionOperation)moveOperation).getConnection());
		}
		
		notification.notifyChange(elements, ChangeType.ELEMENTS_MOVED, NotificationType.UNDO);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		redo = true;
	
		super.redo();
	
		List<DiagramElement> elements = new ArrayList<DiagramElement>();
		
		for (MoveOperation moveOperation : moveOperations) {
			moveOperation.redo();
			if(moveOperation instanceof MoveNodeOperation)
				elements.add(((MoveNodeOperation)moveOperation).getNode());
			else if(moveOperation instanceof TranslateConnectionOperation)
				elements.add(((TranslateConnectionOperation)moveOperation).getConnection());
		}
		
		notification.notifyChange(elements, ChangeType.ELEMENTS_MOVED, NotificationType.REDO);
	}
}
