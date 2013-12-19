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

package br.ufes.inf.nemo.oled.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import RefOntoUML.Association;
import RefOntoUML.Derivation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.ComponentOfInference;
import br.ufes.inf.nemo.common.ontoumlparser.MaterialInference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ModelDiagnostician;
import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelChangeListener;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlDiagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.Editor.EditorNature;
import br.ufes.inf.nemo.oled.ui.antipattern.AntiPatternListPane;
import br.ufes.inf.nemo.oled.ui.commands.EcoreExporter;
import br.ufes.inf.nemo.oled.ui.commands.PngExporter;
import br.ufes.inf.nemo.oled.ui.commands.ProjectReader;
import br.ufes.inf.nemo.oled.ui.commands.ProjectWriter;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.EditorMouseEvent;
import br.ufes.inf.nemo.oled.ui.diagram.EditorStateListener;
import br.ufes.inf.nemo.oled.ui.diagram.SelectionListener;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.dialog.ImportXMIDialog;
import br.ufes.inf.nemo.oled.ui.dialog.OWLSettingsDialog;
import br.ufes.inf.nemo.oled.ui.dialog.VerificationSettingsDialog;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
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
import br.ufes.inf.nemo.oled.util.TextDescriptionHelper;
import br.ufes.inf.nemo.oled.util.VerificationHelper;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ontouml2owl_swrl.util.MappingType;
import edu.mit.csail.sdg.alloy4.ConstMap;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;

/**
 * Class responsible for managing and organizing the {@link DiagramEditor}s in tabs.
 */
public class DiagramManager extends JTabbedPane implements SelectionListener, EditorStateListener, IDisposable {

	private static final long serialVersionUID = 5019191384767258996L;
	public final AppFrame frame;
	private DiagramEditorCommandDispatcher editorDispatcher;
	
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

	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) {
		super();
		this.frame = frame;		
		editorDispatcher = new DiagramEditorCommandDispatcher(this,frame); 
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
		
		frame.showInfoManager();
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
		StructureDiagram diagram = new StructureDiagram(project);
		diagram.setLabelText("New Diagram");
		project.addDiagram(diagram);
		project.setSaveModelNeeded(true);
		frame.getMainToolBar().enableSaveButton(true);
		diagram.setSaveNeeded(true);
		createDiagramEditor(diagram);
		ProjectBrowser.rebuildTree(project);
	}

	/**
	 * Creates a new diagram on the current Project
	 */
	public void newDiagram()
	{
		if (currentProject!=null){
			StructureDiagram diagram = new StructureDiagram(getCurrentProject());
			diagram.setLabelText("New Diagram");
			getCurrentProject().addDiagram(diagram);
			getCurrentProject().setSaveModelNeeded(true);
			frame.getMainToolBar().enableSaveButton(true);
			diagram.setSaveNeeded(true);
			createDiagramEditor(diagram);
			ProjectBrowser.rebuildTree(getCurrentProject());
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
	}
	
	/**
	 * Delete from model and from the diagram (if necessary). In latter case, you can attribute null to DiagramEditor variable to not delete from the Diagram.
	 * @param element
	 */
	public void delete(RefOntoUML.Element element, DiagramEditor diagramEditor)
	{		
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);		
		if(diagramEditor!=null){
			DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true);
			diagramEditor.execute(cmd);
		}else{
			DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,true);
			cmd.deleteFromModel(element);
		}
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

		// Includes the elements opened in the ModelHelper mapping for future accesses
		for(DiagramElement dElem: editor.getDiagram().getChildren()){
			if (dElem instanceof ClassElement) ModelHelper.addMapping(((ClassElement)dElem).getClassifier(), dElem);
			if (dElem instanceof AssociationElement) ModelHelper.addMapping(((AssociationElement)dElem).getRelationship(), dElem);
		}
		
		//Add the diagram to the tabbed pane (this), through the wrapper
		DiagramEditorWrapper wrapper = new DiagramEditorWrapper(editor, editorDispatcher);
		add(diagram.getLabelText(), wrapper);
						
		diagram.addNameLabelChangeListener(new LabelChangeListener() {
			/** {@inheritDoc} */
			public void labelTextChanged(Label label) {
				// We do not need this anymore... the action is done in the Tab Button (ClosableTabPanel) class...
				//DiagramManager.this.setTitleAt(DiagramManager.this.indexOfComponent(comp), label.getNameLabelText());
				//DiagramManager.this.updateUI();
			}
		});
		
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
			updateUI();
			
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
	@Override
	public Component add(String text, Component component)
	{
		super.add(text, component);
		this.setTabComponentAt(this.getTabCount()-1, new ClosableTabPanel(this));
		this.setSelectedIndex(this.getTabCount()-1);
		return component;
	}
	
	/**
	 * Add Non Closable Tab
	 */
	public Component addNonClosable(String text, Component component)
	{
		super.add(text, component);
		this.setTabComponentAt(this.getTabCount()-1,null);
		this.setSelectedIndex(this.getTabCount()-1);
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

	/**
	 * Gets all DiagramEditors that contains a given element.
	 */
	public ArrayList<DiagramEditor> getDiagramEditors(RefOntoUML.Element element)
	{
		ArrayList<DiagramEditor> list = new ArrayList<DiagramEditor>();
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				DiagramEditor d = ((DiagramEditorWrapper)c).getDiagramEditor();
				for(DiagramElement e: d.getDiagram().getChildren()){
					if (e instanceof ClassElement){
						ClassElement elem = (ClassElement)e;
						if(elem.getClassifier().equals(element)) list.add(((DiagramEditorWrapper)c).getDiagramEditor()); 
					}
					if (e instanceof AssociationElement){
						AssociationElement elem = ((AssociationElement)e);
						if(elem.getRelationship().equals(element)) list.add(((DiagramEditorWrapper)c).getDiagramEditor());
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
	public void deriveRelations() 
	{
		OntoUMLParser refparser = ProjectBrowser.getParserFor(getCurrentProject());
		String result = new String();
		
		ComponentOfInference d = new ComponentOfInference(refparser);
		refparser = d.infer();
		
		MaterialInference mi = new MaterialInference(refparser);
		try {
			refparser = mi.infer();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		ProjectBrowser.setParserFor(getCurrentProject(), refparser);
		ProjectBrowser.rebuildTree(getCurrentProject());
		
		ArrayList<componentOf> generatedCompositions = d.getInferredCompositions();
		ArrayList<MaterialAssociation> generatedMaterials = mi.getInferredMaterials();
		ArrayList<Derivation> generatedDerivations = mi.getInferredDerivations();
		
		ArrayList<Association> allGenerated = new ArrayList<>();
		allGenerated.addAll(generatedCompositions);
		allGenerated.addAll(generatedMaterials);
		allGenerated.addAll(generatedDerivations);
		
		/*TODO: WE NEED TO KEEP these inferred relations in memory, because if the model is changed, we must deleted all of them and derive them again.
		 * 		- Figure out where to keep them.
		 * 		- Implement the method
		 * */
		
		if (generatedCompositions.size()>0 || generatedMaterials.size()>0){
			int size = generatedCompositions.size()+generatedDerivations.size()+generatedMaterials.size();
			
			result = 	"A total of "+size+" associations were inferred from the model:"+
						"\n\t"+generatedCompositions.size()+" ComponentOf."+
						"\n\t"+generatedMaterials.size()+" Materials."+
						"\n\t"+generatedDerivations.size()+" Derivations."+
						"\n\nDetails...";
			
			for (Association a : allGenerated) {
				result += "\n\t"+refparser.getStringRepresentation(a);
			}
		}
		else result = "No association can be inferred from the model!";
		
		ProjectBrowser.getInferences(getCurrentProject()).getInferredElements().addAll(allGenerated);
		
		frame.getInfoManager().showOutputText(result, true, true);
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
			oclmodel.setParser( new OCLParser(refparser,getCurrentProject().getTempDir()+File.separator,name.toLowerCase()));
			
			oclmodel.getParser().parse(frame.getInfoManager().getConstraints());

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

	/**
	 * Open Alloy Analyzer
	 */
	public void openAlloyAnalyzer (AlloySpecification alloymodel, boolean showAnalyzer, int cmdIndexToExecute) 
	{
		if (alloymodel.getAlloyPath().isEmpty() || alloymodel.getAlloyPath()==null) return;

		try {

			frame.getAlloyAnalyzer().setTheme(alloymodel.getDirectory() + "standart_theme.thm");

			frame.getAlloyAnalyzer().setVisible(showAnalyzer);

			frame.getAlloyAnalyzer().doOpenFile(alloymodel.getAlloyPath());

			if (cmdIndexToExecute>=0)frame.getAlloyAnalyzer().doRun(cmdIndexToExecute);

		} catch (Exception e) {			
			frame.showErrorMessageDialog("Open Alloy Analyzer",e.getLocalizedMessage());					
			e.printStackTrace();
		}			
	}

	/**
	 * Open AntiPattern Manager
	 */
	public void openAntiPatternManager() 
	{
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * {@inheritDoc}
			 */
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
					
					AntiPatternListPane antipatternApp = new AntiPatternListPane(ProjectBrowser.getAntiPatternListFor(getCurrentProject()),frame);
					
					antipatternApp.setAntiPatternListModel(ProjectBrowser.getAntiPatternListFor(getCurrentProject()));
					antipatternApp.setVisible(true);
					antipatternApp.setLocationRelativeTo(frame);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

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
					add("OWL Generated", textViz);
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
				add("SBVR Generated", htmlViz);
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

		UmlProject project = getCurrentEditor().getProject();
		OperationResult result = TextDescriptionHelper.generateText(project.getModel(), frame);

		if(result.getResultType() != ResultType.ERROR)
		{
			frame.getInfoManager().showOutputText(result.toString(), true, true); 

			if (!result.getData()[0].equals("CSV"))
			{
				TextEditor textViz = (TextEditor) getEditorForProject(project, EditorNature.TEXT);

				if(textViz == null)
				{
					textViz = new TextEditor(project);

					//TODO Localize this;
					add("Text Description Generated", textViz);
				}
				else
				{
					setSelectedComponent(textViz);
				}
				textViz.setText((String) result.getData()[0]);
			}

		}
		else
		{
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
			this.add("Verification Output", new InstanceVisualizer(diagram, solution, module, alloySources, simulationElements)); 
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


}
