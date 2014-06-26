/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.dialog.properties;

import javax.swing.JDialog;
import javax.swing.JFrame;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * @author John Guerson
 */
public class DialogCaller {

	public static GeneralizationSetDialog callGeneralizationSetDialog(JFrame frame, DiagramManager diagramManager, GeneralizationSet element, boolean modal)
	{
		GeneralizationSetDialog dialog = new GeneralizationSetDialog(frame, diagramManager, (GeneralizationSet)element, modal);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static GeneralizationDialog callGeneralizationDialog(JFrame frame, DiagramManager diagramManager, Generalization element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element,diagramManager.getCurrentDiagramEditor());
		GeneralizationDialog dialog = new GeneralizationDialog(frame,diagramManager,(GeneralizationElement)diagramElement,(RefOntoUML.Generalization)element,true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static ClassDialog callConstraintxDialog(JFrame frame, DiagramManager diagramManager, Constraintx element, boolean modal)
	{
		RefOntoUML.Element context = ((RefOntoUML.Constraintx)element).getConstrainedElement().get(0);
		if (context instanceof RefOntoUML.Class)
		{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(context,diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement,(RefOntoUML.Classifier)context,true);
			dialog.setLocationRelativeTo(frame);			
			dialog.selectTab(2); 
			dialog.setVisible(true);
			return dialog;
		}
		return null;
	}
	
	public static JDialog callCommentDialog(JFrame frame, DiagramManager diagramManager, Comment element, boolean modal)
	{
		if (element.eContainer() instanceof RefOntoUML.Class){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement, (RefOntoUML.Classifier)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);			
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(1);
			dialog.setVisible(true);
			return dialog;
		}
		if (element.eContainer() instanceof RefOntoUML.Association){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),diagramManager.getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);			                			 
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(3);
			dialog.setVisible(true);
			return dialog;
		}
		return null;
	}
	
	public static AssociationDialog callAssociationDialog(JFrame frame, DiagramManager diagramManager, Association element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,diagramManager.getCurrentDiagramEditor());
		AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static JDialog callPropertyDialog(JFrame frame, DiagramManager diagramManager, Property element, boolean modal)
	{
		Property p = (Property)element;
		if (p.getAssociation()!=null){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(p.getAssociation(),diagramManager.getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)p.getAssociation(), true);
			dialog.setLocationRelativeTo(frame);			
			if(p.getAssociation().getMemberEnd().get(0).equals(p)) dialog.selectTab(1);
			else dialog.selectTab(2);
			dialog.setVisible(true);
			return dialog;
		}else{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)p.eContainer(),diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement,(RefOntoUML.Classifier)p.eContainer(),true);
			dialog.setLocationRelativeTo(frame);			
			dialog.selectTab(0);
			dialog.setVisible(true);
			return dialog;
		}
	}
	
	public static ClassDialog callClassDialog(JFrame frame, DiagramManager diagramManager, Classifier element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,diagramManager.getCurrentDiagramEditor());
		ClassDialog dialog = new ClassDialog(frame,diagramManager, (ClassElement)diagramElement, (RefOntoUML.Classifier)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
}
