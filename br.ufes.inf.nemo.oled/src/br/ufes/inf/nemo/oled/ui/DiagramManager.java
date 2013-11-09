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
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;

import RefOntoUML.Association;
import RefOntoUML.Derivation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Relationship;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.ontoumlparser.ComponentOfInference;
import br.ufes.inf.nemo.common.ontoumlparser.MaterialInference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontoumlverificator.ModelDiagnostician;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.ocl2alloy.OCL2AlloyOptions;
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
import br.ufes.inf.nemo.oled.ui.diagram.OWLSettingsDialog;
import br.ufes.inf.nemo.oled.ui.diagram.SelectionListener;
import br.ufes.inf.nemo.oled.ui.diagram.VerificationSettingsDialog;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.dialog.ImportXMIDialog;
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
	public void createCurrentProject(RefOntoUML.Model model)
	{		
		currentProject = new UmlProject(model);
		frame.getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		newDiagram(currentProject);
		frame.showInfoManager();		
		frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
	}
	
	/**
	 * Create a current project
	 */
	public void createEmptyCurrentProject()
	{
		currentProject = new UmlProject();
		frame.getProjectBrowser().setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);
		newDiagram(currentProject);
		frame.showInfoManager();
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
		diagram.setSaveNeeded(true);
		createEditor(diagram);
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
			diagram.setSaveNeeded(true);
			createEditor(diagram);
			ProjectBrowser.rebuildTree(getCurrentProject());
		}
	}
	
	/**
	 * Creates an editor for a given Diagram.
	 * @param diagram the diagram to be edited by the editor
	 * */
	public DiagramEditor createEditor(StructureDiagram diagram)
	{
		DiagramEditor editor = new DiagramEditor(frame, this, diagram);
		editor.addEditorStateListener(this);
		editor.addSelectionListener(this);
		editor.addAppCommandListener(editorDispatcher);
		
		//Add the diagram to the tabbed pane (this), through the wrapper
		DiagramEditorWrapper wrapper = new DiagramEditorWrapper(editor, editorDispatcher);			
		final Component comp = add(diagram.getLabelText(), wrapper);
						
		diagram.addNameLabelChangeListener(new LabelChangeListener() {
			/** {@inheritDoc} */
			public void labelTextChanged(Label label) {
				//TODO Write a command for this
				DiagramManager.this.setTitleAt(DiagramManager.this.indexOfComponent(comp), label.getNameLabelText());
				DiagramManager.this.updateUI();
			}
		});		
		
		return editor;
	}
		
	/**
	 * New project.
	 */
	public void newProject() {		
				
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.setDialogTitle("New Project");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setSelectedFile(new File("*.oled"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {			
				closeCurrentProject();
				
				File file = fileChooser.getSelectedFile();	
				if (!file.exists()) {
					file = new File(file.getCanonicalFile() + ".oled");
				}
				
				setProjectFile(file);
				
				createEmptyCurrentProject();
				
				saveCurrentProjectToFile(file);
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(),
						getResourceString("error.readfile.title"),
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}		
	}
	
	/**
	 * Opens an existing project.
	 */
	public void openProject() {
		
		JFileChooser fileChooser = new JFileChooser(lastOpenPath);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.setDialogTitle("Open Project");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);		
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				closeCurrentProject();
				
				File file = fileChooser.getSelectedFile();
				setProjectFile(file);
				lastOpenPath = file.getAbsolutePath();
				
				currentProject = (UmlProject) ProjectReader.getInstance().readProject(file).get(0);					
				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				
				frame.getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createEditor((StructureDiagram)diagram);

				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();
								
				ConfigurationHelper.addRecentProject(file.getCanonicalPath());
				
				//updateFrameTitle(); FIXME
				frame.showInfoManager();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(),
						getResourceString("error.readfile.title"),
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}		
	}

	/**
	 * Opens an existing project.
	 */
	public void openRecentProject() {
		
		try {
			StartPanel startPanel = (StartPanel) getCurrentEditor();
			if(startPanel != null)
			{
				closeCurrentProject();
				
				File file = new File(startPanel.getSelectedRecentFile());
				setProjectFile(file);
				
				currentProject = (UmlProject) ProjectReader.getInstance().readProject(file).get(0);
				String constraints = (String) ProjectReader.getInstance().readProject(file).get(1);
				
				frame.getProjectBrowser().setProject(currentProject);
				frame.getInfoManager().setProject(currentProject);
				frame.getInfoManager().getOcleditor().addCompletions(ProjectBrowser.getParserFor(currentProject));
				for(UmlDiagram diagram: currentProject.getDiagrams()) createEditor((StructureDiagram)diagram);
									
				frame.getInfoManager().getOcleditor().setText(constraints);
				frame.focusOnOclEditor();
								
				ConfigurationHelper.addRecentProject(file.getCanonicalPath());
				
				//updateFrameTitle(); FIXME
				frame.showInfoManager();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
		}		
	}

	/**
	 * Writes the current model file. The returned file is different if the input
	 * file does not have the tsm extension.
	 * @param file the file to write
	 * @return the file that was written
	 */
	private File saveCurrentProjectToFile(File file) {
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
			for(UmlDiagram d: getCurrentProject().getDiagrams()) d.setSaveNeeded(false);
			
			updateUI();
			
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
			//updateFrameTitle(); FIXME
			File file = fileChooser.getSelectedFile();
			lastSavePath = file.getAbsolutePath();			
		}
		return option;
	}

	/**
	 * Deletes the current selection.
	 */
	public void delete() {

	}

	/**
	 * Returns the FileFilter for the TinyUML serialized model files.
	 * @return the FileFilter
	 */
	//private FileNameExtensionFilter createModelFileFilter() {
	//	return new FileNameExtensionFilter(
	//			"OLED Project (*.oled)", "oled");
	//}


	/**
	 * Exports graphics as PNG.
	 */
	public void exportGfx() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.exportgfx.title"));
		//FileNameExtensionFilter svgFilter = new FileNameExtensionFilter(
		//  "Scalable Vector Graphics file (*.svg)", "svg");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Network Graphics file (*.png)", "png");
		//fileChooser.addChoosableFileFilter(svgFilter);
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			//if (fileChooser.getFileFilter() == svgFilter) {
			//  try {
			//    SvgExporter exporter = new SvgExporter();
			//    exporter.writeSVG(getCurrentEditor(), fileChooser.getSelectedFile());
			//  } catch (IOException ex) {
			//    JOptionPane.showMessageDialog(this, ex.getMessage(),
			//      getResourceString("error.exportgfx.title"),
			//      JOptionPane.ERROR_MESSAGE);
			//  }
			//} else 
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

	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * Generates OWL from the current model (the model behind the current DiagramEditor).
	 * */
	public void exportOwl() {
		//CLEANUP
	}

	/**
	 * Saves the current model as an Ecore-based file.
	 * */
	public void exportEcore() {
		if(getCurrentEditor() != null)
		{
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
	 * Imports an RefOntoUML model.
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
					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,new OLEDResourceFactory());
					resourceSet.getPackageRegistry().put(RefOntoUML.RefOntoUMLPackage.eNS_URI, RefOntoUML.RefOntoUMLPackage.eINSTANCE);

					File file = new File(fileChooser.getSelectedFile().getPath());					
					org.eclipse.emf.common.util.URI fileURI = org.eclipse.emf.common.util.URI.createFileURI(file.getAbsolutePath());		
					Resource resource = resourceSet.createResource(fileURI);		

					resource.load(Collections.emptyMap());

					UmlProject project = new UmlProject( (RefOntoUML.Model)resource.getContents().get(0) );
					StructureDiagram diagram = new StructureDiagram(project);
					project.addDiagram(diagram);

					diagram.setLabelText(file.getName().replace(".refontouml", ""));		

					project.setSaveModelNeeded(true);
					diagram.setSaveNeeded(true);
					createEditor(diagram);					

					lastImportEcorePath = fileChooser.getSelectedFile().getAbsolutePath();
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("dialog.importecore.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Imports a model from a XMI file.
	 */
	public void importXMI()
	{
		JFileChooser fileChooser = new JFileChooser(lastImportEAPath);
		fileChooser.setDialogTitle(getResourceString("dialog.importxmi.title"));
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
				ImportXMIDialog inst = new ImportXMIDialog(frame, file, this, true);
				inst.setLocationRelativeTo(frame);
			}
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
	
	public void openIssueReport()
	{
		openLinkWithBrowser("https://code.google.com/p/ontouml-lightweight-editor/issues/list");
	}

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
	 * {@inheritDoc}
	 */
	@Override
	public void stateChanged(DiagramEditor editor, ChangeType changeType) {

		if(changeType == ChangeType.ELEMENTS_ADDED)
			frame.selectPaletteDefaultElement();

		frame.updateMenuAndToolbars(editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectionStateChanged() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseMoved(EditorMouseEvent event) {
	}

	/**
	 * Sets the dispatcher for the editor events
	 * @param editorDispatcher the dispatcher responsible for routing events 
	 * */
	public void setEditorDispatcher(DiagramEditorCommandDispatcher editorDispatcher) {
		this.editorDispatcher = editorDispatcher;
	}

	/**
	 * Gets the dispatcher for the editor events
	 * @return DiagramEditorCommandDispatcher the dispatcher responsible for routing events
	 * */
	public DiagramEditorCommandDispatcher getEditorDispatcher() {
		return editorDispatcher;
	}

	/**
	 * Gets the current DiagramEditor (the editor displayed in the focused tab).
	 * If there's no suitable editor, returns null.
	 * @return DiagramEditor the current focused DiagramEditor
	 * */
	public Editor getCurrentEditor() {
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
	 * @return {@link UmlProject} the project
	 */
	public UmlProject getCurrentProject() {
//		Editor editor = getCurrentDiagramEditor();
//		if(editor instanceof DiagramEditor)
//			return ((DiagramEditor)editor).getProject();
		return currentProject;
	}

	/**
	 * Gets the wrapper for the selected DiagramEditor
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
	 * @return {@link UmlProject} the project
	 */
	public DiagramEditor getCurrentDiagramEditor() 
	{
		if(this.getSelectedComponent() instanceof DiagramEditorWrapper)
			return ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor();
		return null;
	}

	/**
	 * Gets the file associated with the model.
	 * @return File the model file
	 * */
	public File getProjectFile()
	{
		return projectFile;
//		if((this.getSelectedIndex() != -1)&& !(this.getSelectedComponent() instanceof StartPanel))
//			return ((DiagramEditorWrapper) this.getSelectedComponent()).getModelFile();
//		return null;
	}

	/**
	 * Sets the file associated with the model.
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
	 * @return MainMenu the applications' main menu
	 * */
	public MainMenu getMainMenu() {
		return frame.getMainMenu();
	}

	/**
	 * Sintatically validate the current model (the model behind the current DiagramEditor).
	 */
	public void validateCurrentModel() {
		UmlProject project = getCurrentEditor().getProject();
		OperationResult result = VerificationHelper.verifyModel(project.getModel());
		frame.getInfoManager().showOutputText(result.toString(), true, true);
	}

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
			/*
			 * 
			A4Solution solution = (A4Solution) result.getData()[0];
			Module module = (Module) result.getData()[1];
			ConstMap<String, String> alloySources = (ConstMap<String, String>) result.getData()[2];

			showModelInstances(diagram, solution, module, alloySources, simulationElements);
			 */
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
	 * Deletes an element from the UmlPoject. Do not delete of the Diagram.
	 * 
	 * @param elem
	 */
	public void deleteElementOfProject(RefOntoUML.Element elem)
	{
		// Here we delete the element both of the diagram and model project
		if (ModelHelper.getDiagramElement(elem)!=null) {
			DiagramElement diagramElem = ModelHelper.getDiagramElement(elem);
			ArrayList<DiagramElement> elements = new ArrayList<DiagramElement>();					
			elements.add(diagramElem);					
			getCurrentDiagramEditor().execute(new DeleteElementCommand((DiagramNotification)getCurrentDiagramEditor(), elements, frame.getDiagramManager().getCurrentProject()));
		}{
			//Here instead we only delete it from the model
			if (elem instanceof RefOntoUML.Type)
			{
				//delete associations and generalizations of the element 
				ArrayList<Relationship> relList = ProjectBrowser.getParserFor(getCurrentProject()).getSelectedAndNonSelectedRelationshipsOf(elem);
				for(Relationship rel: relList)
				{							
					DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(getCurrentProject().getEditingDomain(), rel);
					getCurrentProject().getEditingDomain().getCommandStack().execute(cmd);
				}
			}
			DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(frame.getDiagramManager().getCurrentProject().getEditingDomain(), elem);
			getCurrentProject().getEditingDomain().getCommandStack().execute(cmd);			
		}
	}
	
	// =============================================================================== 
	// ===============================================================================
	// ===============================================================================
	
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
			// TODO Auto-generated catch block
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
			oclmodel.setParser(					
					new OCLParser(frame.getInfoManager().getConstraints(),refparser,getCurrentProject().getTempDir()+File.separator+name.toLowerCase()+".uml")
					);			

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

		}catch(IOException e3){
			frame.showErrorMessageDialog("IO Error", e3.getLocalizedMessage());						
			e3.printStackTrace();

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

	// ===============================================================================
	// ===============================================================================
	// ===============================================================================
	
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
		OperationResult result = OWLHelper.generateOwl(project.getModel(), 
				ProjectSettings.OWL_ONTOLOGY_IRI.getValue(project),
				mappingType,
				ProjectSettings.OWL_GENERATE_FILE.getBoolValue(project),
				ProjectSettings.OWL_FILE_PATH.getValue(project));

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

	//TODO Remove-me
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
	
	public Component addNonClosable(String text, Component component)
	{
		super.add(text, component);
		this.setTabComponentAt(this.getTabCount()-1,null);
		this.setSelectedIndex(this.getTabCount()-1);
		return component;
	}

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
}
