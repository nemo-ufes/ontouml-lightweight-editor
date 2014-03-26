package br.ufes.inf.nemo.oled.ui.dialog;

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

public class DialogCaller {

	public static void callGeneralizationSetDialog(JFrame frame, DiagramManager diagramManager, GeneralizationSet element, boolean modal)
	{
		GeneralizationSetDialog dialog = new GeneralizationSetDialog(frame, diagramManager, (GeneralizationSet)element, modal);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}
	
	public static void callGeneralizationDialog(JFrame frame, DiagramManager diagramManager, Generalization element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element,diagramManager.getCurrentDiagramEditor());
		GeneralizationDialog dialog = new GeneralizationDialog(frame,diagramManager,(GeneralizationElement)diagramElement,(RefOntoUML.Generalization)element,true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}
	
	public static void callConstraintxDialog(JFrame frame, DiagramManager diagramManager, Constraintx element, boolean modal)
	{
		RefOntoUML.Element context = ((RefOntoUML.Constraintx)element).getConstrainedElement().get(0);
		if (context instanceof RefOntoUML.Class)
		{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(context,diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement,(RefOntoUML.Classifier)context,true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			dialog.selectTab(2);                			
		}
	}
	
	public static void callCommentDialog(JFrame frame, DiagramManager diagramManager, Comment element, boolean modal)
	{
		if (element.eContainer() instanceof RefOntoUML.Class){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement, (RefOntoUML.Classifier)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(1);
		}
		if (element.eContainer() instanceof RefOntoUML.Association){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)element.eContainer(),diagramManager.getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element.eContainer(), true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);                			 
			if (element instanceof RefOntoUML.Comment) dialog.selectTab(3);
		}
	}
	
	public static void callAssociationDialog(JFrame frame, DiagramManager diagramManager, Association element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,diagramManager.getCurrentDiagramEditor());
		AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}
	
	public static void callPropertyDialog(JFrame frame, DiagramManager diagramManager, Property element, boolean modal)
	{
		Property p = (Property)element;
		if (p.getAssociation()!=null){
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(p.getAssociation(),diagramManager.getCurrentDiagramEditor());
			AssociationDialog dialog = new AssociationDialog(frame,diagramManager, (AssociationElement)diagramElement, (RefOntoUML.Relationship)p.getAssociation(), true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			if(p.getAssociation().getMemberEnd().get(0).equals(p)) dialog.selectTab(1);
			else dialog.selectTab(2);
		}else{
			DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor((RefOntoUML.Element)p.eContainer(),diagramManager.getCurrentDiagramEditor());
			ClassDialog dialog = new ClassDialog(frame,diagramManager,(ClassElement)diagramElement,(RefOntoUML.Classifier)p.eContainer(),true);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			dialog.selectTab(0);
		}
	}
	
	public static void callClassDialog(JFrame frame, DiagramManager diagramManager, Classifier element, boolean modal)
	{
		DiagramElement diagramElement = ModelHelper.getDiagramElementByEditor(element,diagramManager.getCurrentDiagramEditor());
		ClassDialog dialog = new ClassDialog(frame,diagramManager, (ClassElement)diagramElement, (RefOntoUML.Classifier)element, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}
}
