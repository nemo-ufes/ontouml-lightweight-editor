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
package br.ufes.inf.nemo.oled;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.window.Window;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Constraintx;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageableElement;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.StringExpression;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ModelDiagnostician;
import br.ufes.inf.nemo.common.resource.ResourceUtil;
import br.ufes.inf.nemo.oled.derivation.DerivedTypesOperations;
import br.ufes.inf.nemo.oled.derivation.ExclusionPattern;
import br.ufes.inf.nemo.oled.derivation.IntersectionPattern;
import br.ufes.inf.nemo.oled.derivation.PastSpecializationPattern;
import br.ufes.inf.nemo.oled.derivation.SpecializationPattern;
import br.ufes.inf.nemo.oled.derivation.UnionPattern;
import br.ufes.inf.nemo.oled.dialog.AlloySettingsDialog;
import br.ufes.inf.nemo.oled.dialog.ImportXMIDialog;
import br.ufes.inf.nemo.oled.dialog.OWLSettingsDialog;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.LineStyle;
import br.ufes.inf.nemo.oled.explorer.CustomOntoUMLElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.explorer.ProjectTree;
import br.ufes.inf.nemo.oled.finder.ElementFound;
import br.ufes.inf.nemo.oled.finder.FinderPane;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlDiagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.pattern.PatternTool;
import br.ufes.inf.nemo.oled.ui.ClosableTabPanel;
import br.ufes.inf.nemo.oled.ui.StartPanel;
import br.ufes.inf.nemo.oled.ui.commands.EcoreExporter;
import br.ufes.inf.nemo.oled.ui.commands.PngExporter;
import br.ufes.inf.nemo.oled.ui.commands.ProjectReader;
import br.ufes.inf.nemo.oled.ui.commands.ProjectWriter;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorCommandDispatcher;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;
import br.ufes.inf.nemo.oled.ui.diagram.Editor.EditorNature;
import br.ufes.inf.nemo.oled.ui.diagram.EditorMouseEvent;
import br.ufes.inf.nemo.oled.ui.diagram.EditorStateListener;
import br.ufes.inf.nemo.oled.ui.diagram.SelectionListener;
import br.ufes.inf.nemo.oled.ui.diagram.TextEditor;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddConnectionCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetLabelTextCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDirection;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactoryImpl;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.FileChoosersUtil;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.OLEDResourceFactory;
import br.ufes.inf.nemo.oled.util.OLEDSettings;
import br.ufes.inf.nemo.oled.util.OWLHelper;
import br.ufes.inf.nemo.oled.util.OperationResult;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.oled.util.ProjectSettings;
import br.ufes.inf.nemo.oled.validator.meronymic.ValidationDialog;
import br.ufes.inf.nemo.oled.verifier.VerificationHelper;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ontouml2owl_swrl.util.MappingType;
import br.ufes.inf.nemo.ontouml2sbvr.core.OntoUML2SBVR;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * Class responsible for managing and organizing the {@link DiagramEditor}s in tabs.
 * 
 * @author John Guerson
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

	/** Get Frame */
	public AppFrame getFrame() { return frame; }
	/** Get Factory */
	public DiagramElementFactoryImpl getElementFactory() { return elementFactory; }

	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) 
	{
		super();
		this.frame = frame;		
		editorDispatcher = new DiagramEditorCommandDispatcher(this,frame);
		elementFactory = new DiagramElementFactoryImpl(null); //doesn't have yet any diagram
		ModelHelper.initializeHelper();		
		setBorder(new EmptyBorder(0,0,0,0));		
	}

	/** Tell the application that we need to save the project i.e. the project was modified */
	public void saveProjectNeeded(boolean value)
	{
		currentProject.setSaveModelNeeded(value);
		frame.getMainToolBar().enableSaveButton(value);
	}
	
	/** Tell the application that we need to save the diagram i.e. the diagram was modified */
	public void saveDiagramNeeded(StructureDiagram diagram, boolean value)
	{
		diagram.setSaveNeeded(value);
		saveProjectNeeded(value);
	}
	
	/** Tell the application that we need to save all diagrams i.e. the diagrams were all modified */
	public void saveAllDiagramNeeded(boolean value)
	{
		for(UmlDiagram d: getCurrentProject().getDiagrams()) d.setSaveNeeded(value);
		saveProjectNeeded(value);	
	}
	
	/** Create a project */
	public UmlProject createCurrentProject(RefOntoUML.Package model)
	{		
		currentProject = new UmlProject(model);
		saveProjectNeeded(false);
		frame.getBrowserManager().getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
		for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
		if(currentProject.getDiagrams().size()==0) newDiagram();
		return currentProject;
	}

	/** Create a project */
	public void createEmptyCurrentProject()
	{
		currentProject = new UmlProject();
		saveProjectNeeded(false);
		frame.getBrowserManager().getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		newDiagram(currentProject);
	}

	/** Verifies if there is a project opened/loaded. */
	public boolean isProjectLoaded()
	{
		if (getCurrentProject()==null) {
			frame.showInformationMessageDialog("OLED Project", "There is no OLED Project opened");
			return false;
		}else{
			return true;
		}
	}

	/** Close Project */
	public void closeCurrentProject()
	{
		if (currentProject!=null){
			removeAll();
			frame.getBrowserManager().getProjectBrowser().eraseProject();
			frame.getInfoManager().eraseProject();						
			currentProject=null;
			addStartPanel();
		}
		updateUI();
	}

	/** Creates a new Diagram with in existing Project */
	public void newDiagram(UmlProject project)
	{
		StructureDiagram diagram = new StructureDiagram(project,elementFactory);
		diagram.setLabelText("New Diagram");
		project.addDiagram(diagram);		
		saveDiagramNeeded(diagram,false);
		createDiagramEditor(diagram);		
		//add the diagram from the browser
		ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);
	}

	/** Creates a new diagram on the current Project */
	public void newDiagram()
	{
		if (currentProject!=null)
		{
			StructureDiagram diagram = new StructureDiagram(getCurrentProject(), elementFactory);
			diagram.setLabelText("New Diagram");
			getCurrentProject().addDiagram(diagram);
			saveDiagramNeeded(diagram,true);
			createDiagramEditor(diagram);			
			//add the diagram from the browser
			ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
			browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);			
		}
	}

	/** Remove Diagram from Tab, not from the Project. */
	public void deleteDiagram(StructureDiagram diagram)
	{
		//first remove all the elements in the diagram
		for(DiagramElement dElem: diagram.getChildren()) {
			if(dElem instanceof ClassElement) deleteFromDiagram(((ClassElement)dElem).getClassifier(), getDiagramEditor(diagram));
			if(dElem instanceof AssociationElement) deleteFromDiagram(((AssociationElement)dElem).getRelationship(), getDiagramEditor(diagram));
			if(dElem instanceof GeneralizationElement) deleteFromDiagram(((GeneralizationElement)dElem).getRelationship(), getDiagramEditor(diagram));
		}
		//second deletes the diagram
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
		
	/** Add relationship to the model instance (not to diagram). */
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

	/** Add comment to the model instance (not to diagram) */
	public RefOntoUML.Comment addComment(RefOntoUML.Element eContainer)
	{
		RefOntoUML.Comment comment = elementFactory.createComment();
		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,comment,0,0,getCurrentProject(),eContainer);		
		cmd.run();
		return comment;
	}

	/** Add constraintx to the model instance (not to diagram) */
	public void addConstraintx(Constraintx cmt, RefOntoUML.Element eContainer) 
	{
		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,cmt,0,0,getCurrentProject(),eContainer);		
		cmd.run();
	}
	
	/** Add constraintx to the model instance (not to diagram) */
	public void addConstraintx(String text, RefOntoUML.Element eContainer) 
	{
		RefOntoUML.Constraintx ct = (Constraintx)addElement(ElementType.CONSTRAINT,eContainer);		
		((StringExpression)ct.getSpecification()).setSymbol(text);
	}
	
	/** Add comment to the model instance (not to diagram) */
	public void addComment(RefOntoUML.Comment c, RefOntoUML.Element eContainer)
	{
		//to add only in the model do exactly as follow		
		AddNodeCommand cmd = new AddNodeCommand(null,null,c,0,0,getCurrentProject(),eContainer);		
		cmd.run();
	}

	/** Add element to the model instance (not to diagram).  */
	public RefOntoUML.Element addElement(ElementType stereotype, RefOntoUML.Element eContainer)
	{
		RefOntoUML.Element element = elementFactory.createElement(stereotype);				
		if(element instanceof RefOntoUML.Comment){
			AddNodeCommand cmd = new AddNodeCommand(null,null,(RefOntoUML.Comment)element,0,0,getCurrentProject(),eContainer);		
			cmd.run();
		}else if (element instanceof RefOntoUML.Constraintx){
			AddNodeCommand cmd = new AddNodeCommand(null,null,(RefOntoUML.Constraintx)element,0,0,getCurrentProject(),eContainer);		
			cmd.run();
		}else{
			//to add only in the model do exactly as follow		
			AddNodeCommand cmd = new AddNodeCommand(null,null,element,0,0,getCurrentProject(),eContainer);		
			cmd.run();
		}
		return element;
	}

	/** Rename an element. It updates the application accordingly (including the diagrams in which the element appears). It also shows a input dialog for text entry. */
	public void rename(RefOntoUML.Element element)
	{
		if (element instanceof NamedElement) 
		{
			String value = new String();    						
			value = (String)JOptionPane.showInputDialog(ProjectBrowser.frame,"Please, enter the new name:","Rename Element ",JOptionPane.INFORMATION_MESSAGE,null,null,((NamedElement)element).getName());    						
			if(value!=null)
			{
				((NamedElement)element).setName(value);
				ArrayList<DiagramEditor> editors = ProjectBrowser.frame.getDiagramManager().getDiagramEditors(element);
				ArrayList<DiagramElement> dElemList = ModelHelper.getDiagramElements(element);
				for(DiagramElement dElem: dElemList)
				{
					if (dElem instanceof ClassElement)
					{
						SetLabelTextCommand cmd = new SetLabelTextCommand((DiagramNotification)editors.get(0),((ClassElement)dElem).getMainLabel(),value,ProjectBrowser.frame.getDiagramManager().getCurrentProject());
						cmd.run();
					}
				}
				frame.getDiagramManager().updateOLEDFromModification(element, false);
			}
		}   
	}
	
	public void renameDiagram(final StructureDiagram diagram)
	{
		String text = new String();    						
		text = (String)JOptionPane.showInputDialog(ProjectBrowser.frame,"Please, enter the new name:","Rename Diagram",JOptionPane.INFORMATION_MESSAGE,null,null,diagram.getName());    						
		final String newtext = text;
		if(text!=null)
		{
			SwingUtilities.invokeLater(new Runnable() {				
				@Override
				public void run() {
					diagram.setName(newtext);
					int index = getTabIndex(diagram);					
					setTitleAt(index, newtext);        
			        getCurrentDiagramEditor().getDiagram().setName(newtext);
			        ProjectBrowser.refreshTree(getCurrentProject());	
			        updateUI();
				}
			});				
		}		
	}
	
	/** Delete element from the model and every diagram in each it appears. */
	public void deleteUnsafely(RefOntoUML.Element element)
	{	
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);		
		//from diagrams & model
		for(DiagramEditor diagramEditor: getDiagramEditors(element))
		{
			diagramEditor.execute(new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true));
		}
		// only from model
		if(getDiagramEditors(element).size()==0)
		{		
			DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,false);
			cmd.run();
		}
	}

	/** Change multiplicity and update the connections in diagram */
	public void changeMultiplicity(RefOntoUML.Property property, int lowerValue, int upperValue)
	{
		LiteralInteger lower = getElementFactory().getFactory().createLiteralInteger();
		lower.setValue(lowerValue);
		LiteralUnlimitedNatural upper =  getElementFactory().getFactory().createLiteralUnlimitedNatural();
		upper.setValue(upperValue);				
		property.setLowerValue(lower);			
		property.setUpperValue(upper);
		refreshDiagramElement(property.getAssociation());
		ProjectBrowser.refreshTree(currentProject);
	}
	
	/** Change multiplicity from string and update the connections in diagrams */
	public void changeMultiplicity(RefOntoUML.Property property, String multiplicity) throws ParseException
	{
		ModelHelper.setMultiplicityFromString(property, multiplicity);
		refreshDiagramElement(property.getAssociation());
		ProjectBrowser.refreshTree(currentProject);
	}
	
	/** Delete element from the model and every diagram in each it appears. It shows a message before deletion.*/
	public void delete(RefOntoUML.Element element)
	{	
		int response = JOptionPane.showConfirmDialog(frame, "WARNING: Are you sure you want to delete the selected items from the model \nand all the diagrams they might appear? This action can still be undone.\n", "Delete from OLED", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
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

	/** Delete elements from the model and every diagram in each they appear. It shows a message before deletion. */
	public void delete(Collection<DiagramElement> diagramElementList)
	{
		int response = JOptionPane.showConfirmDialog(frame, "WARNING: Are you sure you want to delete the selected items from the model \nand all the diagrams they might appear? This action can still be undone.\n", "Delete from OLED", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if(response==Window.OK)
		{
			ArrayList<RefOntoUML.Element> deletionList = (ArrayList<RefOntoUML.Element>)ModelHelper.getElements(diagramElementList);			
			if(deletionList.size()>0){
				ArrayList<DiagramEditor> editors = getDiagramEditors(deletionList.get(0));
				//from diagrams & model
				for(DiagramEditor diagramEditor: editors)
				{
					DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true);
					cmd.run();
				}	
				// only from model
				if(editors.size()==0)
				{		
					DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,false);
					cmd.run();
				}
			}else{
				System.err.println("ERROR: The list of diagram elements for deletion is empty! \n" +
				"       Check the DiagramManager.delete(DiagramElementList) method.");
			}
		}
	}

	/** Change a class stereotype */ 
	public void changeClassStereotype(Type type, String stereo) 
	{   
		ArrayList<DiagramElement> diagramElemList = ModelHelper.getDiagramElements(type);		
   		OutcomeFixer fixer = new OutcomeFixer(ProjectBrowser.getParserFor(currentProject).getModel());
   		Fix fix = fixer.changeClassStereotypeTo(type, fixer.getClassStereotype(stereo));   	
   		for(DiagramElement diagramElem: diagramElemList){
	   		if (diagramElem !=null && diagramElem instanceof ClassElement) {
	   			double x = ((ClassElement)diagramElem).getAbsoluteX1();
	   			double y = ((ClassElement)diagramElem).getAbsoluteY1();   	   		
	   	   		fix.setAddedPosition(fix.getAdded().get(0),x,y);
	   		}   		
   		}
   		updateOLED(fix);
	}
	
	/** Change relation stereotype */ 
	public void changeRelationStereotype(Relationship type, String stereo) 
	{	
   		OutcomeFixer fixer = new OutcomeFixer(ProjectBrowser.getParserFor(currentProject).getModel());
   		Fix fix = fixer.changeRelationStereotypeTo(type, fixer.getRelationshipStereotype(stereo));   		
   		updateOLED(fix);   		   		
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
		boolean isRectilinear = false;
		boolean showName = false;
		boolean showOntoUMLStereotype = false;
		boolean showMultiplicities = false;
		boolean showRoles = false;
		ReadingDirection direction = ReadingDirection.UNDEFINED;		
		if(element instanceof Association)
		{
			AssociationElement ae = (AssociationElement) ModelHelper.getDiagramElementByEditor(element, d);
			if(ae!=null)
			{
				isRectilinear = ae.isTreeStyle();			
				showName = ae.showName();
				showOntoUMLStereotype = ae.showOntoUmlStereotype();
				showRoles = ae.showRoles();
				showMultiplicities = ae.showMultiplicities();				
				direction = ae.getNameReadingDirection();
			}
			deleteFromDiagram(element, d);
			moveAssociationToDiagram((Association) element, d, isRectilinear, showName, showOntoUMLStereotype, showMultiplicities, showRoles, direction);
		}			
		else if(element instanceof Generalization)
		{			
			GeneralizationElement ge = (GeneralizationElement) ModelHelper.getDiagramElementByEditor(element, d);
			if (ge!=null) isRectilinear = ge.isTreeStyle();			
			deleteFromDiagram(element, d);
			moveGeneralizationToDiagram((Generalization) element, d, isRectilinear);
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
		if (d!=null) 
		{
			ArrayList<DiagramElement> diagramElemList = ModelHelper.getDiagramElements(element);
			for(DiagramElement diagramElem: diagramElemList)
			{
				if(diagramElem!=null)
				{				
					ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
					list.add(diagramElem);
					d.notifyChange(list, ChangeType.ELEMENTS_CHANGED, NotificationType.DO);
				}			
			}
		}		
	}
	
	/** Move association to a diagram. It creates a diagram element for that refonto instance adding it to the application map. */
	public void moveAssociationToDiagram(Association association, DiagramEditor d, boolean isRectilinear, boolean showName, boolean showOntoUMLStereotype, boolean showMultiplicities, boolean showRoles, ReadingDirection direction)
	{		
		Type src = ((Association)association).getMemberEnd().get(0).getType();
		Type tgt = ((Association)association).getMemberEnd().get(1).getType();				
		if (d.getDiagram().containsChild(src) && d.getDiagram().containsChild(tgt))	
		{			
			AssociationElement conn = (AssociationElement) d.dragRelation(association,association.eContainer());
			if(isRectilinear) d.setLineStyle(conn, LineStyle.RECTILINEAR);
			else d.setLineStyle(conn, LineStyle.DIRECT);
			conn.setShowMultiplicities(showMultiplicities);
			conn.setShowName(showName);
			conn.setShowOntoUmlStereotype(showOntoUMLStereotype);
			conn.setShowRoles(showRoles);
			conn.setNameReadingDirection(direction);
		}
	}
	
	/** Move generalization to a diagram. It creates a diagram element for that refonto instance adding it to the application map. */
	public void moveGeneralizationToDiagram(Generalization gen, DiagramEditor d, boolean isRectilinear)
	{		
		if (d.getDiagram().containsChild(gen.getGeneral()) && d.getDiagram().containsChild(gen.getSpecific()))
		{	
			UmlConnection conn = d.dragRelation(gen,gen.eContainer());			
			if (gen.getGeneralizationSet().size()>0) 
			{
				Classifier general = gen.getGeneral();
				Classifier specific = gen.getSpecific();
				ClassElement generalElem = (ClassElement)ModelHelper.getDiagramElementByEditor(general, d);
				ClassElement specificElem = (ClassElement)ModelHelper.getDiagramElementByEditor(specific, d);
				if (generalElem.getAbsoluteY1() < specificElem.getAbsoluteY1()) d.setLineStyle(conn, LineStyle.TREESTYLE_VERTICAL);
				else d.setLineStyle(conn, LineStyle.TREESTYLE_HORIZONTAL);
			}
			else if (isRectilinear) d.setLineStyle(conn,  LineStyle.RECTILINEAR);
			else d.setLineStyle(conn,  LineStyle.DIRECT);
			((GeneralizationElement)conn).setShowName(false);
		}
	}
	
	/** Move element to a Diagram */
	public void moveToDiagram(RefOntoUML.Element element, DiagramEditor d)
	{
		if (d!=null && d.getDiagram().containsChild(element))
		{
			if (element instanceof NamedElement)
				frame.showInformationMessageDialog("Moving to Diagram", "\""+ModelHelper.getStereotype(element)+" "+((NamedElement)element).getName()+"\" already exists in diagram \""+d.getDiagram().getName()+"\"");
			else if (element instanceof Generalization)
				frame.showInformationMessageDialog("Moving to Diagram", "\"Generalization "+((Generalization)element).getSpecific().getName()+"->"+((Generalization)element).getSpecific().getName()+"\" already exists in diagram \""+d.getDiagram().getName()+"\"");
			return;			
		}
		if (d!=null) 
		{			
			if (element instanceof RefOntoUML.Class) 
			{
				RefOntoUML.Class oClass = (RefOntoUML.Class)element;
				d.setDragElementMode(oClass,oClass.eContainer());				
			}			
			if ((element instanceof RefOntoUML.DataType)&&!(element instanceof RefOntoUML.PrimitiveType)&&!(element instanceof RefOntoUML.Enumeration))
			{
				RefOntoUML.DataType oClass = (RefOntoUML.DataType)element;
				d.setDragElementMode(oClass,oClass.eContainer());				
			}			
			if(element instanceof Association)
			{
				moveAssociationToDiagram((Association) element, d, false, true, true, true, false, ReadingDirection.UNDEFINED);
			}
			if(element instanceof Generalization)
			{
				moveGeneralizationToDiagram((Generalization) element, d, false);
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
	
	/** Create a generalization set from selected diagram elements */
	public GeneralizationSet createGeneralizationSetFrom(Collection<DiagramElement> diagramElementsList) 
	{		
		// retain only generalizations from selected
		ArrayList<Generalization> gens = new ArrayList<Generalization>();
		boolean genSetAlreadyExists = false;
		for(DiagramElement dElem: diagramElementsList){
			if (dElem instanceof GeneralizationElement){
				Generalization gen = ((GeneralizationElement)dElem).getGeneralization();
				if (gen.getGeneralizationSet()!=null && !gen.getGeneralizationSet().isEmpty()) genSetAlreadyExists=true;
				if(gen!=null) gens.add(gen);
			}
		}
		if(gens.size()<=1) return null; 
		if(genSetAlreadyExists){
			int response = JOptionPane.showConfirmDialog(getFrame(), "There is already a generalization set in the selected generalizations.\nAre you sure you want to continue?", "Adding Generalization Set", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			if(response!=JOptionPane.OK_OPTION) return null;
		}
		//create default gen set
		EObject eContainer = null;
		if(gens.size()>1) eContainer = gens.get(0).getSpecific().eContainer();	
		else eContainer = getCurrentProject().getModel();
		PackageableElement newgenset = (PackageableElement)addElement(ElementType.GENERALIZATIONSET, (RefOntoUML.Package)eContainer);
		// init data of generalization set
		((GeneralizationSet)newgenset).setIsCovering(true);
		((GeneralizationSet)newgenset).setIsDisjoint(true);
		((GeneralizationSet)newgenset).setName("gs");
		for(Generalization g: gens){
			((GeneralizationSet)newgenset).getGeneralization().add(g);
			g.getGeneralizationSet().add(((GeneralizationSet)newgenset));
		}
		//update application accordingly
		updateOLEDFromModification(((GeneralizationSet)newgenset),false);		
		return (GeneralizationSet)newgenset;
	}
	
	/** Useful method */
	public boolean contains (ArrayList<CustomOntoUMLElement> list, RefOntoUML.Element elem)
	{
		for(CustomOntoUMLElement coe: list){
			if(coe.getElement().equals(elem)) return true;
		}
		return false;
	}
	
	/** Delete a generalization set from a list of selected diagram elements */
	public void deleteGeneralizationSetFrom(Collection<DiagramElement> diagramElementsList) 
	{	
		// retain only generalization sets from selected
		ArrayList<CustomOntoUMLElement> genSets = new ArrayList<CustomOntoUMLElement>();		
		for(DiagramElement dElem: diagramElementsList){
			if (dElem instanceof GeneralizationElement){
				Generalization gen = ((GeneralizationElement)dElem).getGeneralization();
				if (gen.getGeneralizationSet()!=null && !gen.getGeneralizationSet().isEmpty()) {
					for(GeneralizationSet gs: gen.getGeneralizationSet()) {
						if (!contains(genSets,(RefOntoUML.Element)gs)) genSets.add(new CustomOntoUMLElement(gs,""));				
					}
				}
			}
		}
		if(genSets.size()==0) return;
		if(genSets.size()==1){
			frame.getDiagramManager().delete((RefOntoUML.Element)genSets.get(0).getElement());
		}else{
			CustomOntoUMLElement chosen = (CustomOntoUMLElement) JOptionPane.showInputDialog(getFrame(), 
					"Which generalization set do you want to delete?",
					"Deleting Generalization Set",
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					genSets.toArray(), 
					genSets.toArray()[0]);
			if(chosen!=null){
				frame.getDiagramManager().delete((RefOntoUML.Element)chosen.getElement());
			}		
		}			
	}
	
	/** 
	 * Update the application accordingly to the refontouml instance created. This instance must be already be inserted in its container. 
	 * @param element: added element on refontouml root instance.
	 */
	public void updateOLEDFromInclusion(final RefOntoUML.Element element)
	{		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
				// add to the parser
				ProjectBrowser.getParserFor(project).addElement(element);		
				// add to the tree
				ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, project).getTree();
				boolean found = tree.checkModelElement(element);
				if(!found) {
					if(element.eContainer()!=null) tree.checkModelElement(element.eContainer());
					else tree.checkModelElement(project.getModel());
					tree.addObject(element);			
				} else {
					if(element instanceof Generalization){
						tree.checkModelElement(element);
						tree.removeCurrentNode();
						tree.checkModelElement(element.eContainer());
						tree.addObject(element);
					}
				}
				tree.updateUI();
				//add to ocl completion		
				ProjectBrowser.frame.getInfoManager().getOcleditor().updateCompletion(element);		
			}
		});		
	}

	/** Invert end points of an association. This method switch the current properties of an association. THe source becomes the target and vice-versa. */
	public void invertEndPoints(RefOntoUML.Association association)
	{
		Property source = association.getMemberEnd().get(0);
   		Property target = association.getMemberEnd().get(1);
   		association.getMemberEnd().clear();	
   		association.getMemberEnd().add(target);
   		association.getMemberEnd().add(source);   		
   		ProjectTree tree = ProjectBrowser.getProjectBrowserFor(ProjectBrowser.frame, currentProject).getTree();
   		tree.checkModelElement(source);
   		tree.removeCurrentNode();
   		tree.checkModelElement(association);
   		tree.addObject(source);   		
   		frame.getDiagramManager().updateOLEDFromModification(association, true);
	}
		
	/**
	 * Update the application accordingly to the refontouml instance created
	 * @param element: modified element on the refontouml root instance
	 */
	public void updateOLEDFromModification(final RefOntoUML.Element element, final boolean redesign)
	{
		updateOLEDFromInclusion(element);
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				// update the diagrams
				if (element instanceof RefOntoUML.Class || element instanceof RefOntoUML.DataType)
				{
					refreshDiagramElement((Classifier)element);			
				}
				if (element instanceof RefOntoUML.Association)
				{
					if (redesign) remakeDiagramElement((RefOntoUML.Element)element);
					else refreshDiagramElement((RefOntoUML.Element)element);
				}
				if (element instanceof RefOntoUML.Property)
				{
					Association assoc= ((RefOntoUML.Property)element).getAssociation();								
					if (assoc!=null){
						if(redesign) remakeDiagramElement((RefOntoUML.Element)assoc);
						else refreshDiagramElement((RefOntoUML.Element)assoc);
					}else{
						refreshDiagramElement((RefOntoUML.Element)(element).eContainer());
					}
				}		
				if (element instanceof RefOntoUML.Generalization)
				{
					if (redesign) remakeDiagramElement((RefOntoUML.Element)element); 
					else refreshDiagramElement((RefOntoUML.Element)element);
				}
				if(element instanceof RefOntoUML.GeneralizationSet)
				{
					for(Generalization gen: ((RefOntoUML.GeneralizationSet) element).getGeneralization()) updateOLEDFromModification(gen,false);
				}
			}
		});
	}
	
	/**
	 * Update the application accordingly to the refontouml instance created
	 * @param element: deleted element on the refontouml root instance
	 */
	public void updateOLEDFromDeletion(final RefOntoUML.Element deletedElement)
	{		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
				// delete from the parser
				ProjectBrowser.getParserFor(project).removeElement(deletedElement);
				// delete from ocl completion
				ProjectBrowser.frame.getInfoManager().getOcleditor().removeCompletion(deletedElement);			
				// delete from the tree
				ProjectBrowser browser = ProjectBrowser.getProjectBrowserFor(frame, currentProject);		
				browser.getTree().remove(deletedElement);
			}
		});
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
	
	/** Verifies if this diagram is already opened in a tab. */
	public boolean isDiagramOpened (StructureDiagram diagram)
	{
		for(Component c: getComponents())
			if (c instanceof DiagramEditorWrapper)
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram)) return true;
		return false;
	}

	/** New OLED Project. */
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
				if (!file.exists()){
					if(!file.getName().endsWith(".oled")) {
						file = new File(file.getCanonicalFile() + ".oled");
					}else{
						file = new File(file.getCanonicalFile()+"");
					}
				}				
				setProjectFile(file);
				createEmptyCurrentProject();	
				frame.getInfoManager().getOcleditor().removeAllModelCompletions();
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				saveCurrentProjectToFile(file);
				frame.setTitle("OLED - "+file.getName()+"");							
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}	

	/** Open an existing project. */
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
				frame.getBrowserManager().getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().removeAllModelCompletions();
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
				saveProjectNeeded(false);
				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();
				ConfigurationHelper.addRecentProject(file.getCanonicalPath());
				frame.setTitle("OLED - "+file.getName()+"");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}		
	}

	/** Open an existing project. */
	public void openRecentProject() 
	{
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			StartPanel startPanel = (StartPanel) getCurrentEditor();
			if(startPanel != null)
			{
				closeCurrentProject();
				File file = new File(startPanel.getSelectedRecentFile());
				setProjectFile(file);
				currentProject = (UmlProject) ProjectReader.getInstance().readProject(file).get(0);								
				frame.getBrowserManager().getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().removeAllModelCompletions();
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createDiagramEditor((StructureDiagram)diagram);
				saveProjectNeeded(false);
				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();
				ConfigurationHelper.addRecentProject(file.getCanonicalPath());
				frame.setTitle("OLED - "+file.getName()+"");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
		}		
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/** Save current Project to a file *.oled */
	private File saveCurrentProjectToFile(File file) 
	{
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (file.exists()) file.delete();
		File result = null;
		try {
			if(!file.getName().endsWith(".oled")) {
				file = new File(file.getCanonicalFile() + ".oled");
			}
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());			
			oclmodel.setConstraints(frame.getInfoManager().getConstraints(),"CONTENT");
			result = ProjectWriter.getInstance().writeProject(this, file, getCurrentProject(), oclmodel);			
			ConfigurationHelper.addRecentProject(file.getCanonicalPath());
			getCurrentProject().setName(file.getName().replace(".oled",""));
			ProjectBrowser.refreshTree(getCurrentProject());
			saveAllDiagramNeeded(false);
			frame.setTitle("OLED - "+file.getName()+"");
			invalidate();
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.savefile.title"), JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

	/** Saves immediately if possible. */
	public void saveProject() 
	{
		if (getProjectFile() == null) 
		{
			int option = saveProjectAs();
			if (option!=JFileChooser.APPROVE_OPTION){
				updateUI();
				return;
			}
		}else{
			saveCurrentProjectToFile(getProjectFile());
		}
		updateUI();
	}

	/** Saves the project with a file chooser. */
	public int saveProjectAs() 
	{
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

	/** Open the Text Description settings window */
	public void callGlossary() 
	{
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = getCurrentProject();				
				GlossaryGeneratorUI settings = new GlossaryGeneratorUI(ProjectBrowser.getParserFor(project));
				settings.setVisible(true);
			}
		});
	}
	
	/** Open the Alloy simulation settings window */
	public void openAlloySettings()
	{
		if (isProjectLoaded()==false) return;		
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//parse TOCL
		parseConstraints(false);
		TOCL2AlloyOption oclOptions = ProjectBrowser.getOCLOptionsFor(getCurrentProject());
		//configure a default ontouml2alloy option
		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(getCurrentProject());
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		refOptions.check(refparser);
		// open settings
		AlloySettingsDialog.open(refOptions, oclOptions, getFrame());	
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/** Parse constraints from the editor */
	public void parseConstraints(boolean showSuccesfullyMessage)
	{
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());		
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to create an OLED project first."); return; }		
		try {
			OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());
			// set parser 
			String name = ((RefOntoUML.Package)getCurrentProject().getResource().getContents().get(0)).getName();
			if (name==null || name.isEmpty()) name = "model";
			oclmodel.setParser( new TOCLParser(refparser,getCurrentProject().getTempDir()+File.separator,name.toLowerCase()));
			//parsing...
			oclmodel.getParser().parseTemporalOCL(frame.getInfoManager().getConstraints());
			// set options 
			ProjectBrowser.setOCLOptionsFor(getCurrentProject(), new TOCL2AlloyOption(oclmodel.getOCLParser()));
			// show Message
			String msg =  "Constraints are syntactically correct.\n";
			if(showSuccesfullyMessage) frame.showSuccessfulMessageDialog("Parsing Constraints",msg);			
		}catch(SemanticException e2){
			frame.showErrorMessageDialog("Semantic Error",  "Parser: "+e2.getLocalizedMessage());    		
			e2.printStackTrace();	

		}catch(ParserException e1){
			frame.showErrorMessageDialog("Parsing Error", "Parser: "+e1.getLocalizedMessage());    			
			e1.printStackTrace();    	

		}catch(Exception e4){
			frame.showErrorMessageDialog("Unexpected Error", e4.getLocalizedMessage());			
			e4.printStackTrace();
		}		
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/** Run Transformation to Alloy */
	public void transformToAlloy()  
	{									
		runOntoUML2Alloy();
		runTOCL2Alloy();
		
		if (ProjectBrowser.getOntoUMLOptionsFor(getCurrentProject()).openAnalyzer) openAnalyzer(ProjectBrowser.getAlloySpecFor(getCurrentProject()),true, -1);
		else openAnalyzer(ProjectBrowser.getAlloySpecFor(getCurrentProject()),false, 0);	
		
		String umlpath = ProjectBrowser.getAlloySpecFor(getCurrentProject()).getAlloyPath().replace(".als", ".uml");
		File umlfile = new File(umlpath);
		umlfile.deleteOnExit();		
	}
	
	/** Run transformation from OntoUML into Alloy */
	public void runOntoUML2Alloy()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(getCurrentProject());
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to first create an OLED project."); return; }
		try {			
			// transforming...
			ProjectBrowser.getAlloySpecFor(getCurrentProject()).setAlloyModel(refparser,refOptions);
		} catch (Exception e) {
			frame.showErrorMessageDialog("Transforming OntoUML into Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}
	
	/** Run Transformation from TOCL into Alloy */
	public void runTOCL2Alloy()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		OCLDocument oclmodel = ProjectBrowser.getOCLModelFor(getCurrentProject());
		TOCL2AlloyOption oclOptions = ProjectBrowser.getOCLOptionsFor(getCurrentProject());
		AlloySpecification alloySpec = ProjectBrowser.getAlloySpecFor(getCurrentProject());
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to first create an OLED project."); return; }
		if (oclmodel.getOCLParser()==null) { /*frame.showErrorMessageDialog("Error","Inexistent constraints. You need to first create constraints.");*/  return; }
		try {						
			// transforming...
			String logMessage = alloySpec.addConstraints(refparser, oclmodel,oclOptions);
			// log details 
			if (!logMessage.isEmpty() && logMessage!=null)
			{				
				frame.showWarningMessageDialog("Transforming Temporal OCL into Alloy",logMessage);					
			}
		} catch (Exception e) {			
			frame.showErrorMessageDialog("Transforming Temporal OCL into Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}		
	}
		
	/** Open the alloy specification with the alloy analyzer */
	public void openAnalyzer (final AlloySpecification alloymodel, final boolean showAnalyzer, final int cmdIndexToExecute) 
	{
		if (alloymodel.getAlloyPath().isEmpty() || alloymodel.getAlloyPath()==null) return;
		try{
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
			if(e.getLocalizedMessage().isEmpty()) frame.showErrorMessageDialog("Opening alloy file", "A unexpected error has occurred. please report this to developers.");
			else frame.showErrorMessageDialog("Opening alloy file", e.getLocalizedMessage());					
		}
	}
	
	/** Import a Reference OntoUML model instance. */
	public void importEcore() 
	{
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
					getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),getResourceString("dialog.importecore.title"),JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	

	/** Import a model from a XMI file (from Entreprise Architect). */
	public void importXMI()
	{		
		JFileChooser fileChooser = new JFileChooser(lastImportEAPath);
		fileChooser.setDialogTitle("Import from EA");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XMI, XML (*.xmi, *.xml)", "xmi", "xml");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);		
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			if (fileChooser.getFileFilter() == filter)
			{
				File file = fileChooser.getSelectedFile();
				lastImportEAPath = file.getAbsolutePath();
				new ImportXMIDialog(frame, true, this, lastImportEAPath);
			}
		}		
	}
	
	/** Export the current model as an Ecore instance file (Reference model)*/
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
						JOptionPane.showMessageDialog(this, ex.getMessage(),getResourceString("dialog.exportecore.title"), JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
	/** Open the OWL settings window	 */
	public void openOwlSettings() 
	{
		OWLSettingsDialog dialog = new OWLSettingsDialog(frame, this, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}	
	
	/** 
	 *  Tell the application to work only with the set of elements contained in the diagram.
	 *  
	 *  This method check in the tree all the elements contained in the diagram.
	 *  If something is missing it completes the checking with the missing elements.
	 *  The elements checked  in the tree are then properly selected in the OntoUML parser.
	 *  This then enables all the application to work only with the selected/checked elements in the parser. 
	 */
	public void workingOnlyWith(StructureDiagram diagram)
	{
		// get elements from the diagram
		ArrayList<EObject> elements = new ArrayList<EObject>();
		for(DiagramElement de: diagram.getChildren()){
			if(de instanceof ClassElement) {
				Classifier c = ((ClassElement)de).getClassifier();
				elements.add(c);
				if(c instanceof RefOntoUML.Class) {
					for(Property attr: ((RefOntoUML.Class)c).getOwnedAttribute()) {
						elements.add(attr);
						if(!elements.contains(attr.getType())) elements.add(attr.getType());
					}
				}
				if(c instanceof RefOntoUML.DataType) {
					for(Property attr: ((RefOntoUML.DataType)c).getOwnedAttribute()) {
						elements.add(attr);
						if(!elements.contains(attr.getType())) elements.add(attr.getType());
					}
				}
			}
			if(de instanceof AssociationElement) { 
				Association r = (Association)((AssociationElement)de).getRelationship();
				elements.add(r.getMemberEnd().get(0));
				elements.add(r.getMemberEnd().get(1));
				elements.add(r);								
			}
			if(de instanceof GeneralizationElement) {
				Relationship rel = ((GeneralizationElement)de).getRelationship();
				elements.add(rel);
				elements.addAll(((Generalization)rel).getGeneralizationSet());				 
			}
		}		
		//complete missing/mandatory dependencies on the parser
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());				
		refparser.selectThisElements((ArrayList<EObject>)elements,true);
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);
		elements.removeAll(added);
		elements.addAll(added);
		//check in the tree the selected elements of the parser
		ProjectBrowser modeltree = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		modeltree.getTree().check(elements, true);					
		modeltree.getTree().updateUI();		
		ProjectBrowser.setParserFor(currentProject, refparser);
	}

	/** Tell the application to work only with the elements contained in these diagrams. */
	public void workingOnlyWith(ArrayList<StructureDiagram> diagrams)
	{
		ArrayList<EObject> elements = new ArrayList<EObject>();
		for(StructureDiagram sd: diagrams) {
			for(DiagramElement de: sd.getChildren()){
				if(de instanceof ClassElement) {
					Classifier c = ((ClassElement)de).getClassifier();
					elements.add(c);
					if(c instanceof RefOntoUML.Class) {
						for(Property attr: ((RefOntoUML.Class)c).getOwnedAttribute()) {
							elements.add(attr);
							if(!elements.contains(attr.getType())) elements.add(attr.getType());
						}
					}
					if(c instanceof RefOntoUML.DataType) {
						for(Property attr: ((RefOntoUML.DataType)c).getOwnedAttribute()) {
							elements.add(attr);
							if(!elements.contains(attr.getType())) elements.add(attr.getType());
						}
					}
				}
				if(de instanceof AssociationElement) { 
					Association r = (Association)((AssociationElement)de).getRelationship();
					elements.add(r.getMemberEnd().get(0));
					elements.add(r.getMemberEnd().get(1));
					elements.add(r);								
				}
				if(de instanceof GeneralizationElement) {
					Relationship rel = ((GeneralizationElement)de).getRelationship();
					elements.add(rel);
					elements.addAll(((Generalization)rel).getGeneralizationSet());				 
				}
			}		
		}			
		//complete missing/mandatory dependencies on the parser
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());				
		refparser.selectThisElements((ArrayList<EObject>)elements,true);
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);
		elements.removeAll(added);
		elements.addAll(added);
		//check in the tree the selected elements of the parser
		ProjectBrowser modeltree = ProjectBrowser.getProjectBrowserFor(frame, currentProject);
		modeltree.getTree().check(elements, true);					
		modeltree.getTree().updateUI();		
		ProjectBrowser.setParserFor(currentProject, refparser);
	}
	
	/** 
	 * Tell the application to work only with the checked elements in the tree.
	 * 
	 * This method check if some dependence is missing in the checking, completing it with the missing elements.
	 * The elements checked  in the tree are then properly selected in the OntoUML parser.
	 * This  enables all the application to work only with the checked elements in the parser/tree.
	 */
	public void workingOnlyWithChecked()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(currentProject);
		ProjectBrowser modeltree = ProjectBrowser.getProjectBrowserFor(frame, currentProject);			
		List<EObject> selected = modeltree.getTree().getModelCheckedElements();
		refparser.selectThisElements((ArrayList<EObject>)selected,true);		
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);		
		selected.removeAll(added);
		selected.addAll(added);	
		modeltree.getTree().checkModelElements(selected, true);			
		modeltree.getTree().updateUI();
		ProjectBrowser.setParserFor(currentProject, refparser);
	}
	
	/** 
	 * Tell the application to work with all elements in the model.
	 * 
	 * This method check all the elements of the model in the tree. Then properly select them in the OntoUML parser.
	 * This  enables all the application to work with all the elements in the parser/tree.
	 */
	public void workingWithAll()
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(currentProject);
		ProjectBrowser modeltree = ProjectBrowser.getProjectBrowserFor(frame, currentProject);			
		modeltree.getTree().checkModelElement(currentProject.getModel());
		refparser.selectAllElements();		
		modeltree.getTree().updateUI();
		ProjectBrowser.setParserFor(currentProject, refparser);
	}
	
	/** Open modeling assistant wizard */
	public void openModellingAssistant(final Classifier elem)
	{
		boolean runAssistant = getFrame().getMainMenu().isAssistantChecked();
		if(runAssistant) {
			if(Main.onMac()) {
				com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute(new Runnable(){        	
					@Override
					public void run() {
						Fix fix = ProjectBrowser.getAssistantFor(getCurrentProject()).runPattern(elem);						
						if(fix != null) updateOLED(fix);
					}
				});
			}else{
				final Fix fix = ProjectBrowser.getAssistantFor(getCurrentProject()).runPattern(elem);
				if(fix != null){
					SwingUtilities.invokeLater(new Runnable() {						
						@Override
						public void run() {
							updateOLED(fix);
						}
					});
				}					
			}    		
		}	
	}
	
	/**  Generate SBVR. In order to use the plug-in, we need to store the model into a file before. */
	public void generateSbvr(RefOntoUML.Model refpackage) 
	{
		UmlProject project = getCurrentProject();
		OperationResult result;
		String modelFileName = ConfigurationHelper.getCanonPath(project.getTempDir(), OLEDSettings.MODEL_DEFAULT_FILE.getValue());
		File modelFile = new File(modelFileName);  	
    	modelFile.deleteOnExit();    	
		try {			
			ResourceUtil.saveReferenceOntoUML(modelFileName, refpackage);
			OntoUML2SBVR.Transformation(modelFileName);			
			String docPage = modelFile.getPath().replace(".refontouml", ".html");			
			frame.getInfoManager().showOutputText("SBVR generated successfully", true, true); 
			result = new OperationResult(ResultType.SUCESS, "SBVR generated successfully", new Object[] { docPage });			
		} catch (Exception ex) {
			ex.printStackTrace();
			result = new OperationResult(ResultType.ERROR, "Error while generating the SBVR representaion. Details: " + ex.getMessage(), null);
		}		
		if(result.getResultType() != ResultType.ERROR)
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true);			
			String htmlFilePath = (String) result.getData()[0];
			File file = new File(htmlFilePath);
			openLinkWithBrowser(file.toURI().toString());
		}else{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
	}
	
	/** Update OLED according to a Fix.  */
	public void updateOLED (final Fix fix)
	{
		if (fix==null) return;
		for(Object obj: fix.getAdded()) 
		{			
			if (obj instanceof RefOntoUML.Class||obj instanceof RefOntoUML.DataType) {
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
				// add at specified position...
				if (fix.getAddedPosition(obj).x!=-1 && fix.getAddedPosition(obj).y!=-1) 
				{
					AddNodeCommand cmd = new AddNodeCommand((DiagramNotification)getCurrentDiagramEditor(),getCurrentDiagramEditor().getDiagram(),(RefOntoUML.Element)obj,
						fix.getAddedPosition(obj).x,fix.getAddedPosition(obj).y,
						getCurrentProject(),(RefOntoUML.Element)((EObject)obj).eContainer());		
					cmd.run();
				}
			}			
		}
		for(Object obj: fix.getAdded()) 
		{
			if (obj instanceof RefOntoUML.Relationship) {
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
				moveToDiagram((RefOntoUML.Element)obj, getCurrentDiagramEditor());
			}
			if (obj instanceof RefOntoUML.GeneralizationSet){
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
			}
			if(obj instanceof RefOntoUML.Property){				
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
			}
		}				
//		for(Object obj: fix.getModified())
//		{
//			if (obj instanceof RefOntoUML.Property)
//			{
//				Association assoc= ((RefOntoUML.Property)obj).getAssociation();								
//				if (assoc!=null) remakeDiagramElement((RefOntoUML.Element)assoc);
//				else refreshDiagramElement((RefOntoUML.Element)((RefOntoUML.Element)obj).eContainer());
//			}							
//			if (obj instanceof RefOntoUML.Class || obj instanceof RefOntoUML.DataType)			
//			{
//				refreshDiagramElement((Classifier)obj);
//			}
//			if (obj instanceof Generalization){
//				remakeDiagramElement((Generalization)obj);
//			}
//		}
//		for(Object obj: fix.getDeleted()) 
//		{
//			if (obj instanceof RefOntoUML.GeneralizationSet)
//				ProjectBrowser.frame.getDiagramManager().deleteUnsafely((RefOntoUML.Element)obj);			
//		}
//		for(Object obj: fix.getDeleted()) 
//		{
//			if (obj instanceof RefOntoUML.Relationship)
//				ProjectBrowser.frame.getDiagramManager().deleteUnsafely((RefOntoUML.Element)obj);			
//		}
//		for(Object obj: fix.getDeleted()) 
//		{
//			if (obj instanceof RefOntoUML.Class || obj instanceof RefOntoUML.DataType)
//				ProjectBrowser.frame.getDiagramManager().deleteUnsafely((RefOntoUML.Element)obj);			
//		}
//		for(String str: fix.getAddedRules())
//		{
//			getFrame().getInfoManager().addConstraints(str+"\n");
//		}
		return ;
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
	
	
	/** Set Alloy Analyzer instance */
	@Deprecated
	public Thread createAlloyAnalyzer(final boolean visible)
	{		
		Thread t = new Thread(new Runnable() {					
			@Override
			public void run() {
				try{
					if(frame.getAlloyAnalyzer()==null) { String[] args = {""}; frame.setAlloyAnalyzer(new SimpleGUICustom(args,visible,"")); }
				}catch(Exception e){
					if(e.getLocalizedMessage().isEmpty()) frame.showErrorMessageDialog("Creating Alloy Analyzer Instance", "A unexpected error has occurred. Please, report this to developers.");
					else frame.showErrorMessageDialog("Creating Alloy Analyzer Instance", e.getLocalizedMessage());
					e.printStackTrace();
				}	
			}
		});
		t.start();
		return t;
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
		//StartPanel start = new StartPanel(frame);
		StartPanel start = new StartPanel(frame);
		this.addNonClosable("Start", start);
	}

	public void addFinderPanel()
	{
		for(Component c: getComponents()){
			if(c instanceof FinderPane) { 
				setSelectedComponent(c);
				return;
			}
		}		
		FinderPane finder = new FinderPane(frame.getDiagramManager().getCurrentProject());
		this.addClosable("Find", finder);
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
		ClosableTabPanel tab = new ClosableTabPanel(this);
		if(component instanceof DiagramEditorWrapper){
			Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/tree/diagram.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		if(component instanceof TextEditor){
			Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/editor.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		if(component instanceof FinderPane){
			Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/find.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		setTabComponentAt(indexOfComponent(component),tab);
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

	public void showStatus(DiagramEditor diagramEditor, String status) 
	{
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if(((DiagramEditorWrapper) c).getDiagramEditor().equals(diagramEditor)){
					((DiagramEditorWrapper) c).getStatusBar().reportStatus(status);
				}
			}
		}		
	}

	/** Return all opened diagram editors */
	public ArrayList<DiagramEditor> getDiagramEditors()
	{
		ArrayList<DiagramEditor> list = new ArrayList<DiagramEditor>();
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) list.add(((DiagramEditorWrapper)c).getDiagramEditor());
		}
		return list;
	}

	public void selectEditor(DiagramEditor editor)
	{		
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if(((DiagramEditorWrapper) c).getDiagramEditor().equals(editor)) setSelectedComponent(c);
			}
		}		
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

	public int getTabIndex(StructureDiagram diagram)
	{		
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram))
					return indexOfComponent(c);
			}
		}
		return -1;
	}
	
	/**
	 * Gets all DiagramEditors that contains a given element. 
	 * Some of them might appear opened in the Tab but others don't.
	 */
	public ArrayList<DiagramEditor> getDiagramEditors(RefOntoUML.Element element)
	{
		ArrayList<DiagramEditor> list = new ArrayList<DiagramEditor>();
		for(UmlDiagram d: currentProject.getDiagrams())
		{
			if(d instanceof StructureDiagram)
			{
				StructureDiagram diagram = (StructureDiagram)d;
				ArrayList<DiagramElement> elemList=null;
				if(element instanceof Property) 
					elemList = ModelHelper.getDiagramElements((RefOntoUML.Element)element.eContainer());
				else 
					elemList = ModelHelper.getDiagramElements(element);
				for(DiagramElement elem: elemList){
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
	public AppMenu getMainMenu() 
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
	
	/** Strictly find by name */
	public ArrayList<ElementFound> strictlyFindByName(String text)
	{		
		ArrayList<ElementFound> result = new ArrayList<ElementFound>();
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		if(refparser!=null && text!=null /*&& !text.isEmpty()*/){
			for(EObject eobj: refparser.getAllInstances(EObject.class)){
				if (eobj instanceof NamedElement){
					String name = ((NamedElement)eobj).getName();
					if(name!=null){
						if(text.trim().isEmpty()) result.add(new ElementFound(eobj));
						else {
							if(name.trim().toLowerCase().compareToIgnoreCase(text)==0) result.add(new ElementFound(eobj));
							else if(name.trim().toLowerCase().contains(text.toLowerCase().trim())) result.add(new ElementFound(eobj));
						}
						
					}
				}
			}
		}		
		return result;
	}
	
	/**
	 * Search for warnings in the model
	 */
	public void searchWarnings() 
	{	
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());

		if (refparser==null) { frame.showErrorMessageDialog("Error","It seems that your model is null."); return; }

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

	/** Generates OWL from the selected model */
	public void generateOwl() 
	{
		UmlProject project = getCurrentProject();
		String owlType = ProjectSettings.OWL_MAPPING_TYPE.getValue(project);
		MappingType mappingType = null;
		if(!owlType.equals("SIMPLE")) mappingType = MappingType.valueOf(owlType);		
		String oclRules = getFrame().getInfoManager().getOcleditor().getText();
		RefOntoUML.Package model = ProjectBrowser.getParserFor(project).createPackageFromSelections(new Copier());
		OperationResult result = OWLHelper.generateOwl(model, 
			ProjectSettings.OWL_ONTOLOGY_IRI.getValue(project),
			mappingType,
			ProjectSettings.OWL_GENERATE_FILE.getBoolValue(project),
			ProjectSettings.OWL_FILE_PATH.getValue(project),
			oclRules);
		if(result.getResultType() != ResultType.ERROR)
		{
			if(!ProjectSettings.OWL_GENERATE_FILE.getBoolValue(project))
			{
				frame.getInfoManager().showOutputText(result.toString(), true, false);
				TextEditor textViz = (TextEditor) getEditorForProject(project, EditorNature.TEXT);
				if(textViz == null)
				{
					textViz = new TextEditor(project);
					addClosable("Text Editor", textViz);
				}else{
					setSelectedComponent(textViz);
				}
				textViz.setText((String) result.getData()[0]);
			}else{
				frame.getInfoManager().showOutputText(result.toString(), true, true);
			}
		}else{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 
		}
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
		//frame.updateMenuAndToolbars(editor);
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

	@SuppressWarnings("unused")
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

	@SuppressWarnings({ })
	public void deriveByExclusion() 
	{
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		Fix fix = DerivedTypesOperations.createExclusionDerivation(activeEditor, project, this);
		if(fix!=null) updateOLED(fix);
	}
	
	@SuppressWarnings({ })
	public void deriveByUnion() 
	{
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		Fix fix = DerivedTypesOperations.createUnionDerivation(activeEditor, project,this);
		if(fix!=null) updateOLED(fix);		
	}
	
	public void openDerivedTypePatternUnion(Double x, Double y) {
			
		JDialog dialog = new UnionPattern(this);
		this.setCenterDialog(dialog);
		((UnionPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	public void openDerivedTypePatternExclusion(Double x, Double y) {
		JDialog dialog = new ExclusionPattern(this);
		this.setCenterDialog(dialog);
		((ExclusionPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	
	}
	
	public void setCenterDialog(JDialog dialog){
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		int x_1 = (screenSize.width - dialog.getWidth()) / 2;
		int y_2 = (screenSize.height - dialog.getHeight()) / 2;
		dialog.setLocation(x_1, y_2);
	}

	public void runPattern(ElementType elementType, double x, double y) {
		Fix fix = null;
		
		if(elementType == ElementType.SUBKINDPATTERN){
			fix = PatternTool.createSubkindPattern(frame, getCurrentProject(),x,y);
    	}else if(elementType == ElementType.RELATORPATTERN){
    		fix = PatternTool.createRelatorPattern(frame, getCurrentProject(),x,y);		
    	}if(elementType == ElementType.ROLEMIXIN){
			fix = PatternTool.createRoleMixinPattern(frame, getCurrentProject(),x,y);
    	}else if(elementType == ElementType.SUBKINDPARTITIONPATTERN){
    		fix = PatternTool.createSubkindPattern(frame, getCurrentProject(),x,y);		
    	}else if(elementType == ElementType.PHASEPARTITION){
    		fix = PatternTool.createPhasePartitionPattern(frame, getCurrentProject(),x,y);		
    	}
		
		if(fix != null){
			updateOLED(fix);
		}
	}
	public void openDerivedTypePatternIntersection(Double x, Double y) {
		JDialog dialog = new IntersectionPattern(this);
		this.setCenterDialog(dialog);
		((IntersectionPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	public void deriveByIntersection() {
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		Fix fix = DerivedTypesOperations.createIntersectionDerivation(activeEditor, project,this);
		if(fix!=null)
			updateOLED(fix);
		
	}
	
	public void validatesParthood() {
		ValidationDialog.open(ProjectBrowser.getParserFor(getCurrentProject()), frame);
		
	}
	public void openDerivedTypePatternSpecialization(double x, double y) {
		// TODO Auto-generated method stub
		JDialog dialog = new SpecializationPattern(this);
		this.setCenterDialog(dialog);
		((SpecializationPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	public void deriveBySpecialization() {
		// TODO Auto-generated method stub
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		Fix fix = DerivedTypesOperations.createSpecializationDerivation(activeEditor, project,this);
		if(fix!=null) updateOLED(fix);
	}
	public void openDerivedTypePatternPastSpecialization(double x, double y) {
		// TODO Auto-generated method stub
		JDialog dialog = new PastSpecializationPattern(this);
		this.setCenterDialog(dialog);
		((PastSpecializationPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	public void openDerivedTypePatternParticipation(double x, double y) {
		// TODO Auto-generated method stub
		
	}
	public void deriveByPastSpecialization() {
		// TODO Auto-generated method stub
		
	}
	public void deriveByParticipation() {
		// TODO Auto-generated method stub
		
	}
}
