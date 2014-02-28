/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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

package br.ufes.inf.nemo.oled;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.window.Window;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Constraintx;
import RefOntoUML.Generalization;
import RefOntoUML.NamedElement;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ModelDiagnostician;
import br.ufes.inf.nemo.derivedtypes.DerivedByUnion;
import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlDiagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ClosableTabPanel;
import br.ufes.inf.nemo.oled.ui.DiagramEditorCommandDispatcher;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.ui.Editor;
import br.ufes.inf.nemo.oled.ui.Editor.EditorNature;
import br.ufes.inf.nemo.oled.ui.InstanceVisualizer;
import br.ufes.inf.nemo.oled.ui.ProjectTree;
import br.ufes.inf.nemo.oled.ui.StartPanel;
import br.ufes.inf.nemo.oled.ui.TextEditor;
import br.ufes.inf.nemo.oled.ui.commands.EcoreExporter;
import br.ufes.inf.nemo.oled.ui.commands.PngExporter;
import br.ufes.inf.nemo.oled.ui.commands.ProjectReader;
import br.ufes.inf.nemo.oled.ui.commands.ProjectWriter;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.EditorMouseEvent;
import br.ufes.inf.nemo.oled.ui.diagram.EditorStateListener;
import br.ufes.inf.nemo.oled.ui.diagram.SelectionListener;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddConnectionCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetLabelTextCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.ui.dialog.EcoreSettingDialog;
import br.ufes.inf.nemo.oled.ui.dialog.ImportXMIDialog;
import br.ufes.inf.nemo.oled.ui.dialog.OWLSettingsDialog;
import br.ufes.inf.nemo.oled.ui.dialog.UMLSettingDialog;
import br.ufes.inf.nemo.oled.ui.dialog.VerificationSettingsDialog;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactoryImpl;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.AlloyHelper;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.FileChoosersUtil;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.OLEDResourceFactory;
import br.ufes.inf.nemo.oled.util.OWLHelper;
import br.ufes.inf.nemo.oled.util.OperationResult;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.oled.util.ProjectSettings;
import br.ufes.inf.nemo.oled.util.SBVRHelper;
import br.ufes.inf.nemo.oled.util.SimulationElement;
import br.ufes.inf.nemo.oled.util.VerificationHelper;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ontouml2owl_swrl.util.MappingType;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;
import edu.mit.csail.sdg.alloy4.ConstMap;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * Class responsible for managing and organizing the {@link DiagramEditor}s in tabs.
 */
public class DiagramManager extends JTabbedPane implements SelectionListener, EditorStateListener, IDisposable {

	private static final long serialVersionUID = 5019191384767258996L;
	public final AppFrame frame;
	private DiagramEditorCommandDispatcher editorDispatcher;
	private DiagramElementFactoryImpl elementFactory;

	private UmlProject currentProject;
	private File projectFile;

	public String lastOpenPath = new String();
	public String lastSavePath = new String();
	public String lastImportEAPath = new String();
	public String lastImportEcorePath = new String();
	public String lastExportEcorePath = new String();

	public AppFrame getFrame()
	{
		return frame;
	}

	public DiagramElementFactoryImpl getElementFactory()
	{
		return elementFactory;
	}

	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) {
		super();
		this.frame = frame;		
		editorDispatcher = new DiagramEditorCommandDispatcher(this,frame);
		elementFactory = new DiagramElementFactoryImpl(null); //doesn't have yet any diagram
		ModelHelper.initializeHelper();		
		setBorder(new EmptyBorder(0,0,0,0));
	}

	/**
	 * Create a current project from a Model
	 * @param project
	 */
	public UmlProject createCurrentProject(RefOntoUML.Package model)
	{		
		currentProject = new UmlProject(model);
		currentProject.setSaveModelNeeded(false);		
		frame.getMainToolBar().enableSaveButton(false);

		frame.getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
		for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
		if(currentProject.getDiagrams().size()==0) newDiagram();

		frame.showInfoManager();	
		return currentProject;
	}

	/**
	 * Create a current project
	 */
	public void createEmptyCurrentProject()
	{
		currentProject = new UmlProject();
		currentProject.setSaveModelNeeded(false);
		frame.getMainToolBar().enableSaveButton(false);

		frame.getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		newDiagram(currentProject);
	}

	/**
	 * Verifies if there is a OLED project opened/loaded.
	 * @return
	 */
	public boolean isProjectLoaded()
	{
		if (getCurrentProject()==null) {
			frame.showInformationMessageDialog("OLED Project", "There is no OLED Project opened");
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Close Project
	 */
	public void closeCurrentProject()
	{
		if (currentProject!=null){

			removeAll();
			frame.getProjectBrowser().eraseProject();
			frame.getInfoManager().eraseProject();
			frame.getStatusBar().clearStatus();			
			currentProject=null;
			addStartPanel();
			frame.hideInfoManager();
			frame.hideToolBox();
		}

		updateUI();
	}

	/**
	 * Creates a new Diagram with in existing Project
	 * @param project
	 */
	public void newDiagram(UmlProject project)
	{
		StructureDiagram diagram = new StructureDiagram(project,elementFactory);
		diagram.setLabelText("New Diagram");
		project.addDiagram(diagram);
		project.setSaveModelNeeded(true);
		frame.getMainToolBar().enableSaveButton(true);
		diagram.setSaveNeeded(true);
		createDiagramEditor(diagram);
		
		//add the diagram from the browser
		ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);
	}

	/**
	 * Creates a new diagram on the current Project
	 */
	public void newDiagram()
	{
		if (currentProject!=null){
			StructureDiagram diagram = new StructureDiagram(getCurrentProject(), elementFactory);
			diagram.setLabelText("New Diagram");
			getCurrentProject().addDiagram(diagram);
			getCurrentProject().setSaveModelNeeded(true);
			frame.getMainToolBar().enableSaveButton(true);
			diagram.setSaveNeeded(true);
			createDiagramEditor(diagram);
			
			//add the diagram from the browser
			ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
			browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);			
		}
	}

	/**
	 * Remove Diagram from Tab. Not from the Project.
	 * 
	 * @param diagram
	 */
	public void removeDiagram(StructureDiagram diagram)
	{
		getCurrentProject().getDiagrams().remove(diagram);
		for(Component c: getComponents()){
			if (c instanceof DiagramEditorWrapper){
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram)) remove(c);
			}
		}		
		
		//remove the diagram from the browser
		ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		browser.getTree().removeCurrentNode();
	}

	/** Add relationship to the model (not to diagrams). */
	public RefOntoUML.Relationship addRelation(RelationType stereotype, EObject eContainer)
	{
		RefOntoUML.Relationship relationship = elementFactory.createRelationship(stereotype);

		// add default properties
		if(relationship instanceof RefOntoUML.Association) elementFactory.createPropertiesByDefault((RefOntoUML.Association)relationship);

		//to add only in the model do exactly as follow				
		if (stereotype==RelationType.GENERALIZATION) {
			AddConnectionCommand cmd = new AddConnectionCommand(null,null,relationship,(RefOntoUML.Classifier)eContainer,null,getCurrentProject(),null);
			cmd.run();
		}else{
			AddConnectionCommand cmd = new AddConnectionCommand(null,null,relationship,null,null,getCurrentProject(),eContainer);
			cmd.run();
		}

		return relationship;
	}

	/** Add comment to the model (not to diagrams) */
	public RefOntoUML.Comment addComment(RefOntoUML.Element eContainer)
	{
		RefOntoUML.Comment comment = elementFactory.createComment();

		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,comment,0,0,getCurrentProject(),eContainer);		
		cmd.run();

		return comment;
	}

	public void addConstraintx(Constraintx cmt, RefOntoUML.Element eContainer) 
	{
		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,cmt,0,0,getCurrentProject(),eContainer);		
		cmd.run();
	}
	
	/** Add comment to the model (not to diagrams) */
	public void addComment(RefOntoUML.Comment c, RefOntoUML.Element eContainer)
	{
		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,c,0,0,getCurrentProject(),eContainer);		
		cmd.run();
	}

	/** Add element to the model (not to diagrams).  */
	public RefOntoUML.PackageableElement addElement(ElementType stereotype, RefOntoUML.Package eContainer)
	{
		RefOntoUML.PackageableElement element = elementFactory.createElement(stereotype);		

		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,element,0,0,getCurrentProject(),eContainer);		
		cmd.run();

		return element;
	}

	/** Rename an element. It updates the application accordingly, including the diagrams in which the element appears */
	public void rename(RefOntoUML.Element element, String text)
	{
		((NamedElement)element).setName(text);
		ArrayList<DiagramEditor> editors = ProjectBrowser.frame.getDiagramManager().getDiagramEditors(element);
		DiagramElement dElem = ModelHelper.getDiagramElement(element);
		if (dElem instanceof ClassElement)
		{
			SetLabelTextCommand cmd = new SetLabelTextCommand((DiagramNotification)editors.get(0),((ClassElement)dElem).getMainLabel(),text,ProjectBrowser.frame.getDiagramManager().getCurrentProject());
			cmd.run();
		}
		frame.getDiagramManager().updateOLEDFromModification(element, false);
	}
	
	/** Delete element from the model and every diagram in each it appears. */
	public void delete(RefOntoUML.Element element)
	{	
		int response = JOptionPane.showConfirmDialog(frame, "Delete selected items from the model and all diagrams? \n\nWARNING: This action cannot be undone.", "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
		if(response==Window.OK)
		{
			ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
			deletionList.add(element);		
			//from diagrams & model
			for(DiagramEditor diagramEditor: getDiagramEditors(element))
			{
				DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true);
				cmd.run();
			}
			// only from model
			if(getDiagramEditors(element).size()==0)
			{		
				DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,false);
				cmd.run();
			}
		}
	}
	
	/** Delete elements from the model and every diagram in each they appear. */
	public void delete(Collection<DiagramElement> diagramElementList)
	{
		int response = JOptionPane.showConfirmDialog(frame, "Delete selected items from the model and all diagrams? \n\nWARNING: This action cannot be undone.\n\n", "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
		if(response==Window.OK)
		{
			ArrayList<RefOntoUML.Element> deletionList = (ArrayList<RefOntoUML.Element>)ModelHelper.getElements(diagramElementList);			
			if(deletionList.size()>0){
				//from diagrams & model
				for(DiagramEditor diagramEditor: getDiagramEditors(deletionList.get(0)))
				{
					DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true);
					cmd.run();
				}
				// only from model
				if(getDiagramEditors(deletionList.get(0)).size()==0)
				{		
					DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,false);
					cmd.run();
				}
			}else{
				System.err.println("Deletion list for selection is empty! Check DiagramManager.delete() method.");
			}
		}
	}
	
	/** Delete element from all diagrams in the project. (not from the model) */
	public void deleteFromDiagrams(RefOntoUML.Element element)
	{
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);

		for(DiagramEditor diagramEditor: getDiagramEditors(element))
		{
			DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),false,true);
			cmd.run();
		}
	}

	/** Delete element from a particular diagram (do not delete it from the model). */
	public void deleteFromDiagram(RefOntoUML.Element element, DiagramEditor diagramEditor)
	{
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);		
		DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),false,true);
		cmd.run();
	}

	/** Re-make element in the diagram . 
	 *  This actually deletes the current diagramElement and creates another diagramElement, including it in the diagram.*/
	public void remakeDiagramElement(RefOntoUML.Element element, DiagramEditor d)
	{
		if(element instanceof RefOntoUML.Relationship){
			deleteFromDiagram(element, d);
			moveToDiagram(element, d); 
		}		
	}

	/** Re-make element in all diagrams it appears. 
	 *  This actually deletes all the diagramElements and creates other diagramElements, including them in their specific diagrams. */
	public void remakeDiagramElement(RefOntoUML.Element element)
	{
		for(DiagramEditor diagramEditor: getDiagramEditors(element))
		{
			remakeDiagramElement(element,diagramEditor);
		}
		// if the element is not in any diagram, redesign it in the diagrams of its types.
		if(getDiagramEditors(element).size()==0)
		{
			if (element instanceof RefOntoUML.Association)
			{
				Type source = ((Association)element).getMemberEnd().get(0).getType();
				Type target = ((Association)element).getMemberEnd().get(1).getType();				
				for(DiagramEditor diagramEditor: getDiagramEditors(source))
				{
					if (getDiagramEditors(target).contains(diagramEditor))
					{						
						remakeDiagramElement(element, diagramEditor);
					}
				}				
			}
			if (element instanceof RefOntoUML.Generalization){
				Type general = ((Generalization)element).getGeneral();
				Type specific = ((Generalization)element).getSpecific();
				for(DiagramEditor diagramEditor: getDiagramEditors(general))
				{
					if (getDiagramEditors(specific).contains(diagramEditor))
					{
						remakeDiagramElement(element, diagramEditor);
					}
				}	
			}			
		}
	}

	/** Refresh element in all diagrams it appears. Just "redraw" the diagramElements. 
	 *  If the element is an association and the end-points are changed, call the remakeDiagramElement() method instead. */
	public void refreshDiagramElement(RefOntoUML.Element element)
	{
		for(DiagramEditor diagramEditor: getDiagramEditors(element))
		{
			refreshDiagramElement(element,diagramEditor);
		}
	}

	/** Refresh a diagram element. Just "redraw" it. 
	 *  If the element is an association and the end-points are changed, call the remakeDiagramElement() method instead. */
	public void refreshDiagramElement (RefOntoUML.Element element, DiagramEditor d)
	{		
		if (d!=null && !d.getDiagram().containsChild(element)) return;
		if (d!=null) {
			DiagramElement diagramElem = ModelHelper.getDiagramElement(element);
			if(diagramElem!=null){				
				ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
				list.add(diagramElem);
				d.notifyChange(list, ChangeType.ELEMENTS_CHANGED, NotificationType.DO);
			}			
		}		
	}

	/** Move element to a Diagram */
	public void moveToDiagram(RefOntoUML.Element element, DiagramEditor d)
	{
		if (d!=null && d.getDiagram().containsChild(element)) return;			
		if (d!=null) {			
			if (element instanceof RefOntoUML.Class) {
				RefOntoUML.Class oClass = (RefOntoUML.Class)element;
				d.setDragElementMode(oClass,oClass.eContainer());				
			}			
			if ((element instanceof RefOntoUML.DataType)&&!(element instanceof RefOntoUML.PrimitiveType)&&!(element instanceof RefOntoUML.Enumeration))
			{
				RefOntoUML.DataType oClass = (RefOntoUML.DataType)element;
				d.setDragElementMode(oClass,oClass.eContainer());				
			}			
			if (element instanceof RefOntoUML.Relationship) {
				RefOntoUML.Relationship rel = (RefOntoUML.Relationship)element;
				d.dragRelation(rel,rel.eContainer());
			}
		}	
	}

	/** Move generalizations of an element to the diagram. 
	 *  It will only move the generalizations in which the general and specific clasifiers are contained in the diagram.
	 */
	public void moveGeneralizationsToDiagram(RefOntoUML.Element element, EObject eContainer, DiagramEditor d)
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		for(RefOntoUML.Generalization gen: refparser.getGeneralizations((RefOntoUML.Classifier)element))
		{
			if (d.getDiagram().containsChild(gen.getGeneral()) && d.getDiagram().containsChild(gen.getSpecific()))
			{	
				d.dragRelation(gen,gen.eContainer());
			}
		}
	}

	/** 
	 * Update the application accordingly to the refontouml instance created
	 * 
	 * @param element: added element on refontouml root instance.
	 */
	public void updatedOLEDFromInclusion(RefOntoUML.Element element)
	{		
		UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
				
		// =================================
		// OntoUML Parser
		// =================================
		ProjectBrowser.getParserFor(project).addElement(element);		
		
		// =================================
		// Project Tree
		// =================================		
		ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
		tree.selectModelElement(element.eContainer());		
		tree.addObject(element);		
		tree.updateUI();
		
		// =================================
		// OCL Completion
		// =================================		
		ProjectBrowser.frame.getInfoManager().getOcleditor().updateCompletion(element);
	}
	
	/**
	 * Update the application accordingly to the refontouml instance created
	 * 
	 * @param element: modified element on the refontouml root instance
	 */
	public void updateOLEDFromModification(RefOntoUML.Element element, boolean redesign)
	{
		updatedOLEDFromInclusion(element);

		// =================================
		// Diagrams
		// =================================		
		if (element instanceof RefOntoUML.Class || element instanceof RefOntoUML.DataType){
			refreshDiagramElement((Classifier)element);			
		}
		if (element instanceof RefOntoUML.Association){
			if (redesign) { remakeDiagramElement((RefOntoUML.Element)element); }
			else refreshDiagramElement((RefOntoUML.Element)element);
		}
		if (element instanceof RefOntoUML.Property){
			Association assoc= ((RefOntoUML.Property)element).getAssociation();								
			if (assoc!=null) {
				if(redesign) remakeDiagramElement((RefOntoUML.Element)assoc);
				else refreshDiagramElement((RefOntoUML.Element)assoc);
			} else {
				refreshDiagramElement((RefOntoUML.Element)(element).eContainer());
			}
		}		
		if (element instanceof RefOntoUML.Generalization){
			if (redesign) { remakeDiagramElement((RefOntoUML.Element)element); }
			else refreshDiagramElement((RefOntoUML.Element)element);
		}
	}
	
	/**
	 * Update the application accordingly to the refontouml instance created
	 * 
	 * @param element: deleted element on the refontouml root instance
	 */
	public void updateOLEDFromDeletion(RefOntoUML.Element deletedElement)
	{		
		UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
		
		// =================================
		// OntoUML Parser
		// =================================
		ProjectBrowser.getParserFor(project).removeElement(deletedElement);
		
		// =================================
		// OCL Completion
		// =================================		
		ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(deletedElement);			
		
		// =================================
		// Project Tree
		// =================================
		ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		browser.getTree().selectModelElement(deletedElement);
		browser.getTree().removeCurrentNode();
		browser.getTree().updateUI();
	}
	
	/** Update OLED according to a Fix.  */
	public void updateOLED (final Fix fix)
	{
		if (fix==null) return;
		for(Object obj: fix.getAdded()) {
			if (obj instanceof RefOntoUML.Class||obj instanceof RefOntoUML.DataType)
				updatedOLEDFromInclusion((RefOntoUML.Element)obj);
		}
		for(Object obj: fix.getAdded()) {
			if (obj instanceof RefOntoUML.Relationship)
				updatedOLEDFromInclusion((RefOntoUML.Element)obj);
		}				
		for(Object obj: fix.getModified()){
			if (obj instanceof RefOntoUML.Property){
				Association assoc= ((RefOntoUML.Property)obj).getAssociation();								
				if (assoc!=null) remakeDiagramElement((RefOntoUML.Element)assoc);
				else refreshDiagramElement((RefOntoUML.Element)((RefOntoUML.Element)obj).eContainer());
			}							
			if (obj instanceof RefOntoUML.Class || obj instanceof RefOntoUML.DataType){
				refreshDiagramElement((Classifier)obj);
			}
		}
		for(Object obj: fix.getDeleted()) {
			if (obj instanceof RefOntoUML.Relationship)
				ProjectBrowser.frame.getDiagramManager().delete((RefOntoUML.Element)obj);			
		}
		for(Object obj: fix.getDeleted()) {
			if (obj instanceof RefOntoUML.Class || obj instanceof RefOntoUML.DataType)
				ProjectBrowser.frame.getDiagramManager().delete((RefOntoUML.Element)obj);			
		}
		for(String str: fix.getAddedRules()){
			getFrame().getInfoManager().addConstraints(str+"\n");
		}
		return ;
	}

	/**
	 * Creates an editor for a given Diagram.
	 * @param diagram the diagram to be edited by the editor
	 * */
	public DiagramEditor createDiagramEditor(StructureDiagram diagram)
	{
		DiagramEditor editor = new DiagramEditor(frame, this, diagram);
		editor.addEditorStateListener(this);
		editor.addSelectionListener(this);
		editor.addAppCommandListener(editorDispatcher);

		// Add all the diagram elements of 'diagram' to the ModelHelper mapping.
		// Keeps trace of mappings between DiagramElement <-> Element.
		ModelHelper.addMapping(editor.getDiagram());

		//Add the diagram to the tabbed pane (this), through the wrapper
		DiagramEditorWrapper wrapper = new DiagramEditorWrapper(editor, editorDispatcher);
		addClosable(diagram.getLabelText(), wrapper);		

		return editor;
	}

	/**
	 * Verifies if this diagram is already opened in a tab.
	 * 
	 * @param diagram
	 * @return
	 */
	public boolean isDiagramOpened (StructureDiagram diagram)
	{
		for(Component c: getComponents()){
			if (c instanceof DiagramEditorWrapper){
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * New OLED Project.
	 */
	public void newProject() 
	{				
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.setDialogTitle("New Project");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setSelectedFile(new File("*.oled"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showDialog(this,"OK") == JFileChooser.APPROVE_OPTION) {
			try {			
				getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	

				closeCurrentProject();				

				File file = fileChooser.getSelectedFile();				
				if (!file.exists()) {

					if(!file.getName().endsWith(".oled"))
					{
						file = new File(file.getCanonicalFile() + ".oled");
					}else
						file = new File(file.getCanonicalFile()+"");
				}				
				setProjectFile(file);				

				createEmptyCurrentProject();	

				saveCurrentProjectToFile(file);				

				frame.setTitle("OLED - "+file.getName()+"");
				frame.showInfoManager();
				frame.showToolBox();				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(),
						getResourceString("error.readfile.title"),
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}	
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}	

	/**
	 * Opens an existing project.
	 */
	public void openProject() 
	{		
		JFileChooser fileChooser = new JFileChooser(lastOpenPath);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.setDialogTitle("Open Project");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);		
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {

				getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				closeCurrentProject();

				File file = fileChooser.getSelectedFile();
				setProjectFile(file);
				lastOpenPath = file.getAbsolutePath();

				currentProject = (UmlProject) ProjectReader.getInstance().readProject(file).get(0);				
				frame.getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
				getCurrentProject().setSaveModelNeeded(false);
				frame.getMainToolBar().enableSaveButton(false);

				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();

				ConfigurationHelper.addRecentProject(file.getCanonicalPath());

				frame.setTitle("OLED - "+file.getName()+"");
				frame.showInfoManager();
				frame.showToolBox();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(),
						getResourceString("error.readfile.title"),
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}

			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}		
	}

	/**
	 * Opens an existing project.
	 */
	public void openRecentProject() {

		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		try {
			StartPanel startPanel = (StartPanel) getCurrentEditor();
			if(startPanel != null)
			{
				closeCurrentProject();

				File file = new File(startPanel.getSelectedRecentFile());
				setProjectFile(file);

				currentProject = (UmlProject) ProjectReader.getInstance().readProject(file).get(0);								
				frame.getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
				getCurrentProject().setSaveModelNeeded(false);
				frame.getMainToolBar().enableSaveButton(false);

				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();

				ConfigurationHelper.addRecentProject(file.getCanonicalPath());

				frame.setTitle("OLED - "+file.getName()+"");
				frame.showInfoManager();
				frame.showToolBox();

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
		}		

		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Writes the current model file. The returned file is different if the input
	 * file does not have the tsm extension.
	 * @param file the file to write
	 * @return the file that was written
	 */
	private File saveCurrentProjectToFile(File file) {
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (file.exists()) file.delete();

		File result = null;
		try {

			if(!file.getName().endsWith(".oled"))
			{
				file = new File(file.getCanonicalFile() + ".oled");
			}

			// copy OCL string in the editor to the OCL Document related to the UmlProject
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());			
			oclmodel.setConstraints(frame.getInfoManager().getConstraints(),"CONTENT");

			result = ProjectWriter.getInstance().writeProject(this, file, getCurrentProject(), oclmodel);

			if (getCurrentDiagramEditor()!=null) {
				getCurrentDiagramEditor().clearUndoManager();
				frame.updateMenuAndToolbars(getCurrentDiagramEditor());
			}

			ConfigurationHelper.addRecentProject(file.getCanonicalPath());

			getCurrentProject().setName(file.getName().replace(".oled",""));
			ProjectBrowser.refreshTree(getCurrentProject());

			getCurrentProject().setSaveModelNeeded(false);
			frame.getMainToolBar().enableSaveButton(false);

			for(UmlDiagram d: getCurrentProject().getDiagrams()) d.setSaveNeeded(false);

			frame.setTitle("OLED - "+file.getName()+"");
			invalidate();

			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.savefile.title"), JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

	/**
	 * Saves immediately if possible.
	 */
	public void saveProject() {

		if (getProjectFile() == null) {
			int option = saveProjectAs();
			if (option!=JFileChooser.APPROVE_OPTION){
				updateUI();
				return;
			}
		} else {
			saveCurrentProjectToFile(getProjectFile());
		}

		updateUI();
	}

	/**
	 * Saves the project with a file chooser.
	 */
	public int saveProjectAs() {
		JFileChooser fileChooser = new JFileChooser(lastSavePath);
		fileChooser.setDialogTitle("Save Project");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int option = fileChooser.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			setProjectFile(saveCurrentProjectToFile(fileChooser.getSelectedFile()));
			File file = fileChooser.getSelectedFile();
			frame.setTitle("OLED - "+file.getName()+"");
			lastSavePath = file.getAbsolutePath();			
		}
		return option;
	}

	/**
	 * Imports a RefOntoUML model.
	 */
	public void importEcore() {

		JFileChooser fileChooser = new JFileChooser(lastImportEcorePath);
		fileChooser.setDialogTitle(getResourceString("dialog.saveas.title"));
		fileChooser.setDialogTitle(getResourceString("dialog.importecore.title"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getFileFilter() == filter) {
				try {

					closeCurrentProject();

					getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new OLEDResourceFactory());
					resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);
					File ecoreFile = new File(fileChooser.getSelectedFile().getPath());					
					org.eclipse.emf.common.util.URI fileURI = org.eclipse.emf.common.util.URI.createFileURI(ecoreFile.getAbsolutePath());		
					Resource resource = resourceSet.createResource(fileURI);		
					resource.load(Collections.emptyMap());

					File projectFile = new File(ecoreFile.getAbsolutePath().replace(".refontouml", ".oled"));
					setProjectFile(projectFile);
					lastOpenPath = projectFile.getAbsolutePath();

					createCurrentProject((RefOntoUML.Package)resource.getContents().get(0));

					saveCurrentProjectToFile(projectFile);

					lastImportEcorePath = fileChooser.getSelectedFile().getAbsolutePath();

					ConfigurationHelper.addRecentProject(projectFile.getCanonicalPath());

					newDiagram();

					frame.setTitle("OLED - "+projectFile.getName()+"");
					frame.showInfoManager();
					frame.showToolBox();

					getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("dialog.importecore.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Imports a model from a XMI file.
	 */
	public void importXMI()
	{		
		new ImportXMIDialog(frame, true, this);
	}

	/**
	 * Exports a Complete OCL document
	 */
	public void exportOCL() 
	{
		try{
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());
			String path = FileChoosersUtil.saveOCLPathLocation(frame,oclmodel.getOCLPath());	    		
			if (path==null) return;		
			oclmodel.setConstraints(frame.getInfoManager().getConstraints(),"CONTENT");
			oclmodel.setOCLPath(path);
			FileUtil.copyStringToFile(frame.getInfoManager().getConstraints(), path);
		}catch(IOException exception){
			String msg = "An error ocurred while saving the constraints to an OCL document.\n"+exception.getMessage();
			frame.showErrorMessageDialog("Saving OCL",msg);		       			
			exception.printStackTrace();
		}
	}

	/**
	 * Get Resource as String
	 * @return
	 */
	private String getResourceString(String property) 
	{
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * Exports graphics as PNG.
	 */
	public void exportGfx() 
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.exportgfx.title"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Network Graphics file (*.png)", "png");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {			
			if (fileChooser.getFileFilter() == filter) {
				try {
					PngExporter exporter = new PngExporter();
					exporter.writePNG(getCurrentDiagramEditor(), fileChooser.getSelectedFile());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.exportgfx.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Saves the current model as an Ecore-based file.
	 * */
	public void exportEcore() 
	{
		if(getCurrentEditor() != null) {
			JFileChooser fileChooser = new JFileChooser(lastExportEcorePath);
			fileChooser.setDialogTitle(getResourceString("dialog.exportecore.title"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (fileChooser.getFileFilter() == filter) {
					try {
						EcoreExporter exporter = new EcoreExporter();
						exporter.writeEcore(this, fileChooser.getSelectedFile(), getCurrentEditor().getProject());
						lastExportEcorePath = fileChooser.getSelectedFile().getAbsolutePath();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								getResourceString("dialog.exportecore.title"),
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	/**
	 * Imports a Complete OCL document
	 */
	public void importOCL() 
	{
		try{
			if (getCurrentProject()==null) newProject();			
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());
			String path = FileChoosersUtil.openOCLPathLocation(frame,oclmodel.getOCLPath());
			if (path==null) return;
			oclmodel.setConstraints(path,"PATH");
			frame.getInfoManager().addConstraints("\n"+oclmodel.getOCLString());
		} catch (IOException exception) {				
			String msg = "An error ocurred while opening the OCL document.\n"+exception.getMessage();
			frame.showErrorMessageDialog("Opening OCL",msg);
			exception.printStackTrace();
		}				
	}

	/**
	 * Adds a start panel to the manager
	 */
	public void addStartPanel()
	{
		StartPanel start = new StartPanel(frame);
		this.addNonClosable("Start", start);
	}

	/**
	 * Adds a new tab.
	 * @param text the tabs text
	 * @param component the component to be added as tab's content
	 * */
	public Component addClosable(String text, Component component)
	{
		if (component==null) component = new JPanel();
		addTab(text, component);
		setTabComponentAt(indexOfComponent(component), new ClosableTabPanel(this));
		setSelectedComponent(component);
		return component;
	}

	/**
	 * Add Non Closable Tab
	 */
	public Component addNonClosable(String text, Component component)
	{
		if (component==null) component = new JPanel();
		addTab(text, component);
		//setTabComponentAt(indexOfComponent(component),null);
		setSelectedComponent(component);
		return component;
	}

	/**
	 * Dispose
	 */
	@Override
	public void dispose() {
		int totalTabs = getTabCount();
		for(int i = 0; i < totalTabs; i++)
		{
			IDisposable disposable = (IDisposable) getComponentAt(i);
			if(disposable != null)
			{
				disposable.dispose();
			}
		}
	}

	/**
	 * Open Isse Report Link
	 */
	public void openIssueReport()
	{
		openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/issues/list");
	}

	/**
	 * Open Link With Browser
	 */
	public void openLinkWithBrowser(String link)
	{
		Desktop desktop = Desktop.getDesktop();

		if( !desktop.isSupported(Desktop.Action.BROWSE)){
			System.err.println( "Desktop doesn't support the browse action (fatal)" );
			return;
		}

		try {
			java.net.URI uri = new java.net.URI(link);
			desktop.browse(uri);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Sets the dispatcher for the editor events
	 * 
	 * @param editorDispatcher the dispatcher responsible for routing events 
	 * */
	public void setEditorDispatcher(DiagramEditorCommandDispatcher editorDispatcher) 
	{
		this.editorDispatcher = editorDispatcher;
	}

	/**
	 * Gets the dispatcher for the editor events
	 * 
	 * @return DiagramEditorCommandDispatcher the dispatcher responsible for routing events
	 * */
	public DiagramEditorCommandDispatcher getEditorDispatcher() 
	{
		return editorDispatcher;
	}

	/**
	 * Gets the current DiagramEditor (the editor displayed in the focused tab).
	 * If there's no suitable editor, returns null.
	 * 
	 * @return DiagramEditor the current focused DiagramEditor
	 * */
	public Editor getCurrentEditor() 
	{
		if(this.getSelectedIndex() != -1)
		{
			Object obj = this.getSelectedComponent();
			if(obj instanceof Editor)
				return (Editor) obj;	
		}
		return null;
	}

	/**
	 * Gets the project being edited 
	 * 
	 * @return {@link UmlProject} the project
	 */
	public UmlProject getCurrentProject() 
	{
		return currentProject;
	}

	/**
	 * Gets the wrapper for the selected DiagramEditor
	 * 
	 * @return {@link UmlProject} the project
	 */
	public DiagramEditorWrapper getCurrentWrapper()
	{
		if(this.getSelectedComponent() instanceof DiagramEditorWrapper)
			return ((DiagramEditorWrapper) this.getSelectedComponent());
		return null;
	}

	/**
	 * Gets the wrapper for the selected DiagramEditor
	 * 
	 * @return {@link UmlProject} the project
	 */
	public DiagramEditor getCurrentDiagramEditor() 
	{		
		if(this.getSelectedComponent() instanceof DiagramEditorWrapper){			
			return ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor();
		}
		return null;
	}

	/**
	 * Gets all DiagramEditors
	 * @return
	 */
	public ArrayList<DiagramEditor> getDiagramEditors()
	{
		ArrayList<DiagramEditor> list = new ArrayList<DiagramEditor>();
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) list.add(((DiagramEditorWrapper)c).getDiagramEditor());
		}
		return list;
	}

	public DiagramEditor getDiagramEditor(StructureDiagram diagram)
	{		
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram))
					return ((DiagramEditorWrapper)c).getDiagramEditor();
			}
		}
		return null;
	}

	/**
	 * Gets all DiagramEditors that contains a given element. 
	 * Some of them might appear opened in the Tab but others don't.
	 */
	public ArrayList<DiagramEditor> getDiagramEditors(RefOntoUML.Element element)
	{
		ArrayList<DiagramEditor> list = new ArrayList<DiagramEditor>();
		for(UmlDiagram d: currentProject.getDiagrams()){
			if(d instanceof StructureDiagram){
				StructureDiagram diagram = (StructureDiagram)d;				
				DiagramElement elem = ModelHelper.getDiagramElement(element);
				if (diagram.containsChild(elem)) {
					DiagramEditor editor = getDiagramEditor(diagram);
					if (editor==null){
						editor = new DiagramEditor(frame, this, diagram);
						editor.addEditorStateListener(this);
						editor.addSelectionListener(this);
						editor.addAppCommandListener(editorDispatcher);						
					}
					list.add(editor);
				}
			}
		}
		return list;
	}

	/**
	 * Gets the file associated with the model.
	 * 
	 * @return File the model file
	 * */
	public File getProjectFile()
	{
		return projectFile;
	}

	/**
	 * Sets the file associated with the model.
	 * 
	 * @param modelFile the model file
	 * */
	public void setProjectFile(File modelFile)
	{
		projectFile = modelFile;
		if((this.getSelectedIndex() != -1)&& !(this.getSelectedComponent() instanceof StartPanel))
			((DiagramEditorWrapper) this.getSelectedComponent()).setModelFile(modelFile);
	}

	/**
	 * Gets the MainMenu from the application frame
	 * 
	 * @return MainMenu the applications' main menu
	 * */
	public MainMenu getMainMenu() 
	{
		return frame.getMainMenu();
	}

	/**
	 * Verify syntactically the current project i.e., reference ontouml model instance
	 */
	public void verifyCurrentProject() {
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		if(currentProject!=null){
			OperationResult result = VerificationHelper.verifyModel(currentProject.getModel());
			frame.getInfoManager().showOutputText(result.toString(), true, true);
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Shows or hides the output pane, in case the current editor is a DiagramEditor. 
	 */
	public void showOutputPane() 
	{
		frame.focusOnOutput();
	}

	/**
	 * Shows or hides the ocl editor pane, in case the current editor is a DiagramEditor. 
	 */
	public void showOclEditor() 
	{		
		frame.focusOnOclEditor();
	}

	/**
	 * Find (Match String) in the Project Browser
	 */
	public void find() 
	{
		String text = frame.getSecondaryBar().getText().toLowerCase();	    		
		if(text!=null)
		{
			ProjectBrowser pbrowser = ProjectBrowser.getProjectBrowserFor(getFrame(), getCurrentProject());
			pbrowser.find(text);		   
		}
	}

	/** Transform model to UML (*.uml) ignoring all stereotypes. */
	public void generateUML() 
	{
		UMLSettingDialog.open(frame, true,ProjectBrowser.getParserFor(currentProject));		
	}

	/** Transform model to Ecore (*.ecore) ignoring all stereotypes. */
	public void generateEcore() 
	{	
		EcoreSettingDialog.open(frame, true, ProjectBrowser.getParserFor(currentProject));
	}

	/**
	 * Search for warnings in the model
	 */
	public void searchWarnings() 
	{	
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());

		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }

		autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,getCurrentProject());

		ModelDiagnostician verificator = new ModelDiagnostician();    	
		frame.getInfoManager().getWarnings().setData(
				verificator.getWarningsMatrixFormat(ProjectBrowser.getParserFor(getCurrentProject())),
				verificator.getWarnings()
				);
		if (verificator.getWarnings()>0)
		{			
			frame.getInfoManager().getWarnings().selectRow(0);
			frame.getInfoManager().setTitleWarning(" Warnings ("+verificator.getWarnings()+")");
			frame.focusOnWarnings();			
		}else frame.getInfoManager().setTitleWarning(" Warnings ");    	

		/*if (verificator.getWarnings()>0) 
			frame.showWarningMessageDialog("Warning", "Your model has warnings. Please, be aware before continue.\n");*/			
	}

	/**
	 * Search for errors 
	 */
	public void searchErrors() 
	{	
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());

		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }

		autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,getCurrentProject());

		ModelDiagnostician verificator = new ModelDiagnostician();    	
		frame.getInfoManager().getErrors().setData(
				verificator.getErrorsMatrixFormat(refparser),
				verificator.getErrors()
				);
		if (verificator.getErrors()>0) 
		{  
			frame.getInfoManager().getErrors().selectRow(0); 
			frame.getInfoManager().setTitleErrors(" Errors ("+verificator.getErrors()+")");
			frame.focusOnErrors(); 
		} else frame.getInfoManager().setTitleErrors(" Errors ");

		/*if (verificator.getErrors()>0) 
		frame.showErrorMessageDialog("Error", "Your model has errors. Please, Fix it before continue.\n");*/			
	}

	/** 
	 * Generate derived relations of the model 
	 */
	@Deprecated
	public void deriveRelations() 
	{
//		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
//		String result = new String();
//
//		ComponentOfInference d = new ComponentOfInference(refparser);
//		refparser = d.infer();
//
//		MaterialInference mi = new MaterialInference(refparser);
//		try {
//			refparser = mi.infer();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		ProjectBrowser.setParserFor(getCurrentProject(), refparser);
//		ProjectBrowser.rebuildTree(getCurrentProject());
//
//		ArrayList<componentOf> generatedCompositions = d.getInferredCompositions();
//		ArrayList<MaterialAssociation> generatedMaterials = mi.getInferredMaterials();
//		ArrayList<Derivation> generatedDerivations = mi.getInferredDerivations();
//
//		ArrayList<Association> allGenerated = new ArrayList<>();
//		allGenerated.addAll(generatedCompositions);
//		allGenerated.addAll(generatedMaterials);
//		allGenerated.addAll(generatedDerivations);
//
//		/*TODO: WE NEED TO KEEP these inferred relations in memory, because if the model is changed, we must deleted all of them and derive them again.
//		 * 		- Figure out where to keep them.
//		 * 		- Implement the method
//		 * */
//
//		if (generatedCompositions.size()>0 || generatedMaterials.size()>0){
//			int size = generatedCompositions.size()+generatedDerivations.size()+generatedMaterials.size();
//
//			result = 	"A total of "+size+" associations were inferred from the model:"+
//					"\n\t"+generatedCompositions.size()+" ComponentOf."+
//					"\n\t"+generatedMaterials.size()+" Materials."+
//					"\n\t"+generatedDerivations.size()+" Derivations."+
//					"\n\nDetails...";
//
//			for (Association a : allGenerated) {
//				result += "\n\t"+refparser.getStringRepresentation(a);
//			}
//		}
//		else result = "No association can be inferred from the model!";
//
//		ProjectBrowser.getInferences(getCurrentProject()).getInferredElements().addAll(allGenerated);
//
//		frame.getInfoManager().showOutputText(result, true, true);
	}

	/**
	 * Auto complete selection in the model
	 */
	public String autoCompleteSelection(int option, UmlProject project)
	{		
		OntoUMLParser refparser = ProjectBrowser.getParserFor(project);
		ProjectBrowser modeltree = ProjectBrowser.getProjectBrowserFor(frame, project);

		if (refparser==null) { return ""; }	

		// Get elements from the tree
		List<EObject> selected = modeltree.getTree().getModelCheckedElements();

		// Get added elements from the auto selection completion
		refparser.selectThisElements((ArrayList<EObject>)selected,true);		
		List<EObject> added = refparser.autoSelectDependencies(option,false);

		// Show which elements were added to selection
		String msg = new String();
		if (added.size()>0) msg = "The following elements were include in selection:\n\n";
		for(EObject o: added)
		{
			msg += ""+refparser.getStringRepresentation(o)+".\n";
		}
		if (added.size()==0) msg += "No elements to include in selection.";		

		// Update tree adding the elements...
		selected.removeAll(added);
		selected.addAll(added);		

		modeltree.getTree().checkModelElements(selected, true);			
		modeltree.getTree().updateUI();    	

		// Create a new model package from selected elements in the model.
		// ontoumlmodel.setOntoUMLPackage(ontoumlmodel.getOntoUMLParser().createPackageFromSelections(new Copier()));

		return msg;
	}

	/**
	 * Parse OCL constraints from OCL editor
	 */
	public void parseOCL(boolean showSuccesfullyMessage)
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());		
		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }		
		autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,getCurrentProject());

		try {
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());			

			// set parser from the editor view.
			String name = ((RefOntoUML.Package)getCurrentProject().getResource().getContents().get(0)).getName();
			if (name==null || name.isEmpty()) name = "model";
			oclmodel.setParser( new OCLParser(refparser,getCurrentProject().getTempDir()+File.separator,name.toLowerCase(),false));

			oclmodel.getParser().parseStandardOCL(frame.getInfoManager().getConstraints());

			// set options from the parser
			ProjectBrowser.setOCLOptionsFor(getCurrentProject(), new OCL2AlloyOptions(oclmodel.getOCLParser()));

			// show Message
			String msg =  "Constraints are syntactically correct.\n";
			if(showSuccesfullyMessage) frame.showSuccessfulMessageDialog("Parsing OCL",msg);

		}catch(SemanticException e2){
			frame.showErrorMessageDialog("OCL Semantic Error",  "OCL Parser : "+e2.getLocalizedMessage());    		
			e2.printStackTrace();	

		}catch(ParserException e1){
			frame.showErrorMessageDialog("OCL Parsing Error", "OCL Parser: "+e1.getLocalizedMessage());    			
			e1.printStackTrace();    	

		}catch(Exception e4){
			frame.showErrorMessageDialog("Unexpected Error", e4.getLocalizedMessage());			
			e4.printStackTrace();
		}		
	}

	/**
	 * Transform model to Alloy
	 */
	public void transformsOntoUMLintoAlloy()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(getCurrentProject());

		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }

		autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,getCurrentProject());

		try {			
			ProjectBrowser.getAlloySpecFor(getCurrentProject()).setAlloyModel(refparser,refOptions);

		} catch (Exception e) {
			frame.showErrorMessageDialog("Transforming OntoUML into Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}	

	public Thread initAlloyAnalyzer(final boolean visible)
	{		
		Thread t = new Thread(new Runnable(){					
			@Override
			public void run() {
				try{
					if(frame.getAlloyAnalyzer()==null){
						String[] args = {""};
						frame.setAlloyAnalyzer(new SimpleGUICustom(args,visible,""));
					}
				}catch(Exception e){
					if(e.getLocalizedMessage().isEmpty())
						frame.showErrorMessageDialog("Creating Alloy Analyzer Instance", "A unexpected error has occurred. Please, report this to developers.");
					else
						frame.showErrorMessageDialog("Creating Alloy Analyzer Instance", e.getLocalizedMessage());
					e.printStackTrace();
				}	
			}
		});
		t.start();
		return t;
	}

	/**
	 * Open Alloy Analyzer
	 */
	public void openAlloyAnalyzer (final AlloySpecification alloymodel, final boolean showAnalyzer, final int cmdIndexToExecute) 
	{
		if (alloymodel.getAlloyPath().isEmpty() || alloymodel.getAlloyPath()==null) return;

		try{
			//open analyer
			if(frame.getAlloyAnalyzer()==null){
				String[] args = {""};
				frame.setAlloyAnalyzer(new SimpleGUICustom(args,true,""));
			}

			final Timer timer = new Timer(100, null);			
			ActionListener listener = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (frame.getAlloyAnalyzer().isInitialized())
					{
						frame.getAlloyAnalyzer().setTheme(alloymodel.getDirectory() + "standart_theme.thm");				
						frame.getAlloyAnalyzer().doOpenFile(alloymodel.getAlloyPath());				
						if (cmdIndexToExecute>=0)frame.getAlloyAnalyzer().doRun(cmdIndexToExecute);						
						timer.stop();
					}
				}
			};
			timer.addActionListener(listener);
			timer.start();					

		}catch(Exception e){
			e.printStackTrace();
			if(e.getLocalizedMessage().isEmpty())
				frame.showErrorMessageDialog("Opening alloy file", "A unexpected error has occurred. please report this to developers.");
			else
				frame.showErrorMessageDialog("Opening alloy file", e.getLocalizedMessage());					
		}
	}

	/**
	 * Transform constraints to Alloy
	 */
	public void transformsOCLintoAlloy ()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());
		OCL2AlloyOptions oclOptions = ProjectBrowser.getOCLOptionsFor(getCurrentProject());
		AlloySpecification alloySpec = ProjectBrowser.getAlloySpecFor(getCurrentProject());

		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }
		if (oclmodel.getOCLParser()==null) { frame.showErrorMessageDialog("Error","It seems that you do not have any OCL constraints."); return; }

		try {						
			// Here the constraints are transformed into Alloy...
			String logMessage = alloySpec.addConstraints(refparser, oclmodel,oclOptions);			

			// show warnings 
			if (!logMessage.isEmpty() && logMessage!=null)
			{				
				frame.showWarningMessageDialog("Transforming OCL into Alloy",logMessage);					
			}

		} catch (Exception e) {			
			frame.showErrorMessageDialog("Transforming OCL into Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}		
	}

	/**
	 * Generates Alloy from OntoUML+OCL model
	 * @throws InterruptedException 
	 */
	public void generatesAlloy()  
	{									
		transformsOntoUMLintoAlloy();
		transformsOCLintoAlloy();
		if (ProjectBrowser.getOntoUMLOptionsFor(getCurrentProject()).openAnalyzer) 
			openAlloyAnalyzer(ProjectBrowser.getAlloySpecFor(getCurrentProject()),true, -1);
		else openAlloyAnalyzer(ProjectBrowser.getAlloySpecFor(getCurrentProject()),false, 0);	
		String umlpath = ProjectBrowser.getAlloySpecFor(getCurrentProject()).getAlloyPath().replace(".als", ".uml");
		File umlfile = new File(umlpath);
		umlfile.deleteOnExit();		
	}

	/**
	 * Shows a dialog for choosing the owl seneration settings 
	 */
	public void generateOwlSettings() {
		OWLSettingsDialog dialog = new OWLSettingsDialog(frame, this, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}	

	/**
	 * Generates OWL from the selected model   
	 */
	public void generateOwl() {

		UmlProject project = getCurrentProject();
		//StructureDiagram diagram = getCurrentEditor().getDiagram();

		String owlType = ProjectSettings.OWL_MAPPING_TYPE.getValue(project);
		MappingType mappingType = null;

		if(!owlType.equals("SIMPLE")){
			mappingType = MappingType.valueOf(owlType); 
		}
		String oclRules = getFrame().getInfoManager().getOcleditor().getText();

		OperationResult result = OWLHelper.generateOwl(project.getModel(), 
				ProjectSettings.OWL_ONTOLOGY_IRI.getValue(project),
				mappingType,
				ProjectSettings.OWL_GENERATE_FILE.getBoolValue(project),
				ProjectSettings.OWL_FILE_PATH.getValue(project),
				oclRules);

		//Model model, String ontologyIRI, String mappingType, boolean fileOutput, String filePath

		if(result.getResultType() != ResultType.ERROR)
		{
			if(!ProjectSettings.OWL_GENERATE_FILE.getBoolValue(project))
			{
				frame.getInfoManager().showOutputText(result.toString(), true, false);

				TextEditor textViz = (TextEditor) getEditorForProject(project, EditorNature.TEXT);

				if(textViz == null)
				{
					textViz = new TextEditor(project);

					//TODO Localize this;
					addClosable("OWL Generated", textViz);
				}
				else
				{
					setSelectedComponent(textViz);
				}
				textViz.setText((String) result.getData()[0]);
			}
			else
			{
				frame.getInfoManager().showOutputText(result.toString(), true, true);
			}
		}
		else
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
	}

	/**
	 * Generates SBVR from the selected model 
	 */
	public void generateSbvr() {

		UmlProject project = getCurrentProject();

		OperationResult result = SBVRHelper.generateSBVR((RefOntoUML.Model)ProjectBrowser.getParserFor(project).getModel(), project.getTempDir());
		//OperationResult result = SBVRHelper.generateSBVR(project.getModel(), project.getTempDir());

		if(result.getResultType() != ResultType.ERROR)
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 

			//HTMLVisualizer htmlViz = (HTMLVisualizer) getEditorForDiagram(diagram, EditorNature.HTML);

			/*if(htmlViz == null)
			{
				htmlViz = new HTMLVisualizer(diagram);

				//TODO Localize this;
				addClosable("SBVR Generated", htmlViz);
			}
			else
			{
				setSelectedComponent(htmlViz);
				frame.setVisible(true); //HACK!!! Needed to force to show the browser
			}*/

			String htmlFilePath = (String) result.getData()[0];
			File file = new File(htmlFilePath);			
			openLinkWithBrowser(file.toURI().toString());

			/*if(!htmlViz.loadLocalPage(htmlFilePath))
			{
				getCurrentWrapper().showOutputText("\n\nCouldn't open the documentation with the internal browser. Trying to open with the system default browser...", false, true);
				openLinkWithBrowser(new File(htmlFilePath).toURI().toString());
			}*/

		}
		else
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
	}

	/**
	 * Generates a text description of the model 
	 */
	public void generateText() {

		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = getCurrentProject();				
				GlossaryGeneratorUI settings = new GlossaryGeneratorUI(ProjectBrowser.getParserFor(project));
				settings.setVisible(true);				
			}
		});
	}

	private Editor getEditorForProject(UmlProject project, EditorNature nature)
	{
		int totalTabs = getTabCount();
		for(int i = 0; i < totalTabs; i++)
		{
			Editor editor = (Editor)getComponentAt(i);

			if(editor.getEditorNature() == nature && editor.getProject() == project)
			{
				return editor;
			}
		}
		return null;
	}

	//===================================== @Inherited =======================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stateChanged(DiagramEditor editor, ChangeType changeType) 
	{
		if(changeType == ChangeType.ELEMENTS_ADDED) frame.selectPaletteDefaultElement();
		frame.updateMenuAndToolbars(editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectionStateChanged() {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseMoved(EditorMouseEvent event) {}

	//===================================== @Older =======================================

	/**
	 * Shows a dialog for choosing the elements to simulate and the style settings 
	 */
	public void verificationSettings() {
		VerificationSettingsDialog dialog = new VerificationSettingsDialog(frame, this, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}	

	/**
	 * Simulate the selected model elements using Alloy.
	 */
	public void verifyCurrentModel() {

		UmlProject project = getCurrentEditor().getProject();
		StructureDiagram diagram = (StructureDiagram) getCurrentEditor().getDiagram();

		List<SimulationElement> simulationElements = diagram.getSimulationElements();
		OperationResult result = AlloyHelper.validateModel(project.getModel(), simulationElements, project.getTempDir());

		if(result.getResultType() != ResultType.ERROR)
		{
			frame.getInfoManager().showOutputText(result.toString(), true, false); 

			//			A4Solution solution = (A4Solution) result.getData()[0];
			//			Module module = (Module) result.getData()[1];
			//			ConstMap<String, String> alloySources = (ConstMap<String, String>) result.getData()[2];
			//
			//			showModelInstances(diagram, solution, module, alloySources, simulationElements);			
		}
		else
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
	}

	@SuppressWarnings("unchecked")
	public void verifyCurrentModelFile() {

		StructureDiagram diagram = (StructureDiagram) getCurrentWrapper().getDiagram();
		UmlProject project = getCurrentProject();

		List<SimulationElement> simulationElements = diagram.getSimulationElements();
		OperationResult result = AlloyHelper.verifyModelFromAlloyFile(project.getTempDir());

		if(result.getResultType() != ResultType.ERROR)
		{
			frame.getInfoManager().showOutputText(result.toString(), true, false); 

			A4Solution solution = (A4Solution) result.getData()[0];
			Module module = (Module) result.getData()[1];
			ConstMap<String, String> alloySources = (ConstMap<String, String>) result.getData()[2];

			showModelInstances(diagram, solution, module, alloySources, simulationElements);
		}
		else
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
	}

	/**
	 * Shows model instances for a given Alloy Solution/Module. 
	 */
	private void showModelInstances(StructureDiagram diagram, A4Solution solution, Module module, ConstMap<String, String> alloySources, List<SimulationElement> simulationElements)
	{
		InstanceVisualizer instanceViz = (InstanceVisualizer) getEditorForDiagram(diagram, EditorNature.INSTANCE_VISUALIZER);
		if(instanceViz == null)
		{
			//TODO Localize this;
			addClosable("Verification Output", new InstanceVisualizer(diagram, solution, module, alloySources, simulationElements)); 
		}
		else
		{
			List<SimulationElement> simElements = ((StructureDiagram) getCurrentWrapper().getDiagram()).getSimulationElements();

			instanceViz.setSolution(solution);
			instanceViz.setModule(module);
			instanceViz.setAlloySources(alloySources);
			instanceViz.loadSolution();
			instanceViz.setSimulationElements(simElements);
			setSelectedComponent(instanceViz);
		}
	}	

	private Editor getEditorForDiagram(StructureDiagram diagram, EditorNature nature)
	{
		int totalTabs = getTabCount();
		for(int i = 0; i < totalTabs; i++)
		{
			Editor editor = (Editor)getComponentAt(i);
			if(editor.getEditorNature() == nature && editor.getDiagram() == diagram)
			{
				return editor;
			}
		}
		return null;
	}

	public void openModellingAssistant(final Classifier elem){
		//VICTOR COMENTAR
//		boolean runAssistant = getFrame().getMainMenu().isAssistantChecked();
//
//		if(runAssistant){
//			if(Main.onMac()){//To work on Mac
//				com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
//					@Override
//					public void run() {
//						Fix fix = ProjectBrowser.getAssistantFor(getCurrentProject()).runPattern(elem);
//						if(fix != null)
//							updateOLED(fix);
//					}
//				});
//			}else{//To work in others
//				final Fix fix = ProjectBrowser.getAssistantFor(getCurrentProject()).runPattern(elem);
//				if(fix != null){
//					SwingUtilities.invokeLater(new Runnable() {						
//						@Override
//						public void run() {
//							updateOLED(fix);
//						}
//					});
//				}					
//			}    		
//		}	
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	public void deriveByUnion() 
	{
		Fix mainfix = new Fix();
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		List<DiagramElement> selected = activeEditor.getSelectedElements();
		
		
		ArrayList<RefOntoUML.Element> refontoList = new ArrayList<RefOntoUML.Element>();
		int j=0;
		for (int i = 0; i < selected.size(); i++) {
			
			if (selected.get(i) instanceof ClassElement) {
				j++;
				ClassElement ce = (ClassElement) selected.get(i);
				refontoList.add(ce.getClassifier());
				//refontoList.add();
			}
			
		}
		if(refontoList.size()==2){
			
			ArrayList<String>stereotypes= DerivedByUnion.getInstance().inferStereotype(refontoList.get(0).eClass().getName(), refontoList.get(1).eClass().getName());
			if(stereotypes.size()<2){
				UmlProject project = getCurrentEditor().getProject();
				OutcomeFixer of = new OutcomeFixer(project.getModel());
				Classifier newElement = (Classifier)of.createClass( of.getClassStereotype(stereotypes.get(0)));
				mainfix.includeAdded(newElement);
				of.copyContainer(((ClassElement) selected.get(0)).getClassifier(), newElement);
				Fix fix=of.createGeneralization((Classifier)refontoList.get(0), newElement);
				Fix fixG2=of.createGeneralization((Classifier)refontoList.get(1), newElement);
				ArrayList<Generalization> generalizations = new ArrayList<Generalization>();
				generalizations.add((Generalization) fix.getAdded().get(0));
				generalizations.add((Generalization) fixG2.getAdded().get(0));
				Fix gs =  of.createGeneralizationSet(generalizations);
				//((GeneralizationSet) gs.getAdded()).setIsCovering(true);
				//((GeneralizationSet) gs.getAdded()).setIsDisjoint(true);
				mainfix.addAll(fixG2);
				mainfix.addAll(fix);
				mainfix.addAll(gs);
				updateOLED(mainfix);
				//fix.includeAllAdded(added);
				//ClassElement c = 
			}
			else{
				
			}
		}
		//refontoList.get(0).get
		//System.out.println("");
		// diagramElem instnaceof AssociationElement ... diagramElement.getRelationship();
		// diagramEleme instanceof ClassElement ... diagramElement.getClassifier();
		
		//eliminate associations and generalizations
		
		//Fix fix = DervideByUnion.run(refontoList);
		
		// double pos = calcular posição ... diagramElement.getAbsoluteX1, X2,
		
		//updateOLED(fix, double pos_new_Elem); // new method: we need to do this.
		
		//String constraint = DerviedByUnion.getConstraints();
		//getFrame().getInfoManager().getOcleditor().addText(constraint);
		
		//System.out.println("derive by union");
		
	}

}
