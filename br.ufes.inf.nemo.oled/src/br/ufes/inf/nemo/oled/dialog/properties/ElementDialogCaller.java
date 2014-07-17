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

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * @author John Guerson
 */
public class ElementDialogCaller {

	public static GeneralizationSetDialog callGeneralizationSetDialog(AppFrame frame, GeneralizationSet element, boolean modal)
	{
		GeneralizationSetDialog dialog = new GeneralizationSetDialog(frame, (GeneralizationSet)element, modal);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static GeneralizationDialog callGeneralizationDialog(AppFrame frame, Generalization element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element,frame.getDiagramManager().getCurrentDiagramEditor());
		GeneralizationDialog dialog = new GeneralizationDialog(frame, (GeneralizationElement)diagramElement,(RefOntoUML.Generalization)element,true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static ClassDialog callConstraintxDialog(AppFrame frame, Constraintx element, boolean modal)
	{
		RefOntoUML.Element context = ((RefOntoUML.Constraintx)element).getConstrainedElement().get(0);
		if (context instanceof RefOntoUML.Class)
		{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(context,frame.getDiagramManager().getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,(ClassElement)diagramElement,(RefOntoUML.Classifier)context,true);
			dialog.setLocationRelativeTo(frame);			
			dialog.selectTab(2); 
			dialog.setVisible(true);
			return dialog;
		}
		return null;
	}
	
	public static JDialog callCommentDialog(AppFrame frame, Comment element, boolean modal)
	{
		if (element.eContainer() instanceof RefOntoUML.Class){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),frame.getDiagramManager().getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,(ClassElement)diagramElement, (RefOntoUML.Classifier)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);			
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(1);
			dialog.setVisible(true);
			return dialog;
		}
		if (element.eContainer() instanceof RefOntoUML.Association){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),frame.getDiagramManager().getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);			                			 
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(3);
			dialog.setVisible(true);
			return dialog;
		}
		return null;
	}
	
	public static AssociationDialog callAssociationDialog(AppFrame frame, Association element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,frame.getDiagramManager().getCurrentDiagramEditor());
		AssociationDialog dialog = new AssociationDialog(frame, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static JDialog callPropertyDialog(AppFrame frame, Property element, boolean modal)
	{
		Property p = (Property)element;
		if (p.getAssociation()!=null){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(p.getAssociation(),frame.getDiagramManager().getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame, (AssociationElement)diagramElement, (RefOntoUML.Relationship)p.getAssociation(), true);
			dialog.setLocationRelativeTo(frame);			
			if(p.getAssociation().getMemberEnd().get(0).equals(p)) dialog.selectTab(1);
			else dialog.selectTab(2);
			dialog.setVisible(true);
			return dialog;
		}else{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)p.eContainer(),frame.getDiagramManager().getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame, (ClassElement)diagramElement,(RefOntoUML.Classifier)p.eContainer(),true);
			dialog.setLocationRelativeTo(frame);			
			dialog.selectTab(0);
			dialog.setVisible(true);
			return dialog;
		}
	}
	
	public static ClassDialog callClassDialog(AppFrame frame, Classifier element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,frame.getDiagramManager().getCurrentDiagramEditor());
		ClassDialog dialog = new ClassDialog(frame, (ClassElement)diagramElement, (RefOntoUML.Classifier)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
		return dialog;
	}
	
	public static void openDialog(RefOntoUML.Element element, AppFrame frame)
	{
		if (element instanceof RefOntoUML.Class)
		{
			ElementDialogCaller.callClassDialog(frame, (RefOntoUML.Classifier)element, true);
		}
		if ((element instanceof RefOntoUML.DataType) || (element instanceof RefOntoUML.Enumeration))
		{
			ElementDialogCaller.callClassDialog(frame, (RefOntoUML.Classifier)element, true);
		}		 
		else if (element instanceof RefOntoUML.Property)
		{
			ElementDialogCaller.callPropertyDialog(frame, (RefOntoUML.Property)element, true);
		} 
		else if (element instanceof RefOntoUML.Association)
		{
			ElementDialogCaller.callAssociationDialog(frame, (RefOntoUML.Association)element, true);        				
		} 
		else if (element instanceof RefOntoUML.Comment)
		{
			ElementDialogCaller.callCommentDialog(frame, (RefOntoUML.Comment)element, true);	            			
		} 
		else if (element instanceof RefOntoUML.Constraintx)
		{
			ElementDialogCaller.callConstraintxDialog(frame, (RefOntoUML.Constraintx)element, true);
		}
		else if (element instanceof RefOntoUML.Generalization)
		{
			ElementDialogCaller.callGeneralizationDialog(frame, (RefOntoUML.Generalization)element, true );
		} 
		else if (element instanceof RefOntoUML.GeneralizationSet)
		{            			
			ElementDialogCaller.callGeneralizationSetDialog(frame, (GeneralizationSet)element, true);
		}
	}
}
