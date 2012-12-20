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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.IDisposable;

import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.draw.LabelChangeListener;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.Editor.EditorNature;
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
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ColorPalette;
import br.ufes.inf.nemo.oled.util.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.OLEDResourceFactory;
import br.ufes.inf.nemo.oled.util.OWLHelper;
import br.ufes.inf.nemo.oled.util.OperationResult;
import br.ufes.inf.nemo.oled.util.TextDescriptionHelper;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.oled.util.ProjectSettings;
import br.ufes.inf.nemo.oled.util.SBVRHelper;
import br.ufes.inf.nemo.oled.util.SimulationElement;
import br.ufes.inf.nemo.oled.util.ValidationHelper;
import br.ufes.inf.nemo.oled.util.VerificationHelper;
import br.ufes.inf.nemo.ontouml.transformation.ontouml2owl.auxiliary.MappingType;
import edu.mit.csail.sdg.alloy4.ConstMap;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;

/**
 * Class responsible for managing and organizing the {@link DiagramEditor}s in tabs.
 */
public class DiagramManager extends JTabbedPane implements SelectionListener, EditorStateListener, IDisposable {

	private static final long serialVersionUID = 5019191384767258996L;
	private final AppFrame frame;
	private DiagramEditorCommandDispatcher editorDispatcher;

	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) {
		super();
		this.frame = frame;

		editorDispatcher = new DiagramEditorCommandDispatcher(this);
		
		//When the user selects a tab show the model tree in the tool manager 
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				DiagramEditor editor = getCurrentDiagramEditor();
				if(editor != null)
					DiagramManager.this.frame.getToolManager().showModelTree(editor.getProject());		
				frame.updateMenuAndToolbars(editor);
			}
		});
		
		ModelHelper.initializeHelper();
	}

	/**
	 * Creates a new project.
	 */
	public void newProject() {
		//if (canCreateNewModel()) {
		UmlProject project = new UmlProject();
		StructureDiagram diagram = new StructureDiagram(project);
		project.addDiagram(diagram);
		//TODO Localize this
		diagram.setLabelText("New Diagram");		
		project.setSaveModelNeeded(true);
		diagram.setSaveNeeded(true);
		createEditor(diagram);
	}
	
	
	/**
	 * Opens an existing project.
	 */
	public void openProject() {
		if (canOpen()) {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
			fileChooser.setDialogTitle(getResourceString("dialog.openmodel.title"));
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fileChooser.getSelectedFile();
					UmlProject model = (UmlProject) ProjectReader.getInstance().readProject(file);				
					createEditor((StructureDiagram) model.getDiagrams().get(0));
					setModelFile(file);
					
					((StructureDiagram) model.getDiagrams().get(0)).setLabelText(file.getName().replace(".oled", ""));
					
					ConfigurationHelper.addRecentProject(file.getCanonicalPath());
					//updateFrameTitle(); FIXME
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.readfile.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Opens an existing project.
	 */
	public void openRecentProject() {
		if (canOpen()) 
		{
			try {
				StartPanel startPanel = (StartPanel) getCurrentEditor();
				if(startPanel != null)
				{
					File file = new File(startPanel.getSelectedRecentFile());
					UmlProject model = (UmlProject) ProjectReader.getInstance().readProject(file);				
					createEditor((StructureDiagram) model.getDiagrams().get(0));
					setModelFile(file);
					
					ConfigurationHelper.addRecentProject(file.getCanonicalPath());
					//updateFrameTitle(); FIXME
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean canOpen() {
		/*if (getCurrentEditor().canUndo()) {
			return JOptionPane.showConfirmDialog(this,
					ApplicationResources.getInstance().getString("confirm.open.message"),
					ApplicationResources.getInstance().getString("confirm.open.title"),
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		}*/
		return true;
	}
	
	
	/**
	 * Writes the current model file. The returned file is different if the input
	 * file does not have the tsm extension.
	 * @param file the file to write
	 * @return the file that was written
	 */
	private File saveProjectFile(File file) {
		File result = null;
		try {

			if(!file.getName().endsWith(".oled"))
			{
				file = new File(file.getCanonicalFile() + ".oled");
			}
			
			result = ProjectWriter.getInstance().writeProject(this, file, getCurrentEditor().getProject());
			getCurrentDiagramEditor().clearUndoManager();
			frame.updateMenuAndToolbars(getCurrentDiagramEditor());
			ConfigurationHelper.addRecentProject(file.getCanonicalPath());
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
			saveProjectAs();
		} else {
			saveProjectFile(getProjectFile());
		}
		
		getCurrentEditor().getProject().setSaveModelNeeded(false);
		getCurrentEditor().getDiagram().setSaveNeeded(false);
		
		updateUI();
	}
	
	/**
	 * Saves the project with a file chooser.
	 */
	public void saveProjectAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.saveas.title"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			setModelFile(saveProjectFile(fileChooser.getSelectedFile()));
			//updateFrameTitle(); FIXME
		}
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
			JFileChooser fileChooser = new JFileChooser();
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
	 * Imports an RefOntoUML model.
	 */
	public void importEcore() {

		JFileChooser fileChooser = new JFileChooser();
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
	public void importXMI() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.importxmi.title"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XMI, XML (*.xmi, *.xml)", "xmi", "xml");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getFileFilter() == filter) {
				File file = fileChooser.getSelectedFile();
				
				ImportXMIDialog inst = new ImportXMIDialog(frame, file, this, true);
	            inst.setLocationRelativeTo(frame);
	            
//		    	frame.getToolManager().setSelectedIndex(1);
			}
        }
	}
	
	/**
	 * Creates an editor for a given Diagram.
	 * @param diagram the diagram to be edited by the editor
	 * */
	public void createEditor(StructureDiagram diagram)
	{
		DiagramEditor editor = new DiagramEditor(frame, this, diagram);
		editor.addEditorStateListener(this);
		editor.addSelectionListener(this);
		editor.addAppCommandListener(editorDispatcher);

		//Add the diagram to the tabbed pane (this), through the wrapper
		DiagramEditorWrapper wrapper = new DiagramEditorWrapper(editor, editorDispatcher);
		final Component comp = this.add(diagram.getLabelText(), wrapper);

		diagram.addNameLabelChangeListener(new LabelChangeListener() {
			/** {@inheritDoc} */
			public void labelTextChanged(Label label) {
				
				//TODO Write a command for this
				DiagramManager.this.setTitleAt(DiagramManager.this.indexOfComponent(comp), label.getNameLabelText());

				DiagramManager.this.updateUI();
			}
		});
	}

	/**
	 * Adds a start panel to the manager
	 */
	public void addStartPanel()
	{
		StartPanel start = new StartPanel(frame);
		this.add("Start", start);
	}
	
	public void openCommunity()
	{
		openLinkWithBrowser("http://nemo.inf.ufes.br/");
	}
	
	public void openLearnOntoUML()
	{
		openLinkWithBrowser("http://nemo.inf.ufes.br/");
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
		Editor editor = getCurrentDiagramEditor();
		if(editor instanceof DiagramEditor)
			return ((DiagramEditor)editor).getProject();
		return null;
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
		if(this.getSelectedIndex() != -1)
			return ((DiagramEditorWrapper) this.getSelectedComponent()).getModelFile();
		return null;
	}

	/**
	 * Sets the file associated with the model.
	 * @param modelFile the model file
	 * */
	public void setModelFile(File modelFile)
	{
		if(this.getSelectedIndex() != -1)
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
		OperationResult result = ValidationHelper.validateModel(project.getModel());
		((DiagramEditorWrapper) this.getSelectedComponent()).showOutputText(result.toString(), true, true);
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
		OperationResult result = VerificationHelper.verifyModel(project.getModel(), simulationElements, project.getTempDir());
		
		if(result.getResultType() != ResultType.ERROR)
		{
			getCurrentWrapper().showOutputText(result.toString(), true, false); 
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
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
		}
	}
	
	@SuppressWarnings("unchecked")
	public void verifyCurrentModelFile() {

		StructureDiagram diagram = (StructureDiagram) getCurrentWrapper().getDiagram();
		UmlProject project = getCurrentProject();
		
		List<SimulationElement> simulationElements = diagram.getSimulationElements();
		OperationResult result = VerificationHelper.verifyModelFromAlloyFile(project.getTempDir());
		
		if(result.getResultType() != ResultType.ERROR)
		{
			getCurrentWrapper().showOutputText(result.toString(), true, false); 
			
			A4Solution solution = (A4Solution) result.getData()[0];
			Module module = (Module) result.getData()[1];
			ConstMap<String, String> alloySources = (ConstMap<String, String>) result.getData()[2];
			
			showModelInstances(diagram, solution, module, alloySources, simulationElements);
		}
		else
		{
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
		}
	}
	
	/**
	 * Shows or hides the output pane, in case the current editor is a DiagramEditor. 
	 */
	public void showOutputPane() {
		((DiagramEditorWrapper) this.getSelectedComponent()).showOrHideOutput();
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
		if(!owlType.equals("SIMPLE"))
			mappingType = MappingType.valueOf(owlType); 
		
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
				getCurrentWrapper().showOutputText(result.toString(), true, false);
				
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
				getCurrentWrapper().showOutputText(result.toString(), true, true);
			}
		}
		else
		{
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
		}
	}
	
	/**
	 * Generates SBVR from the selected model 
	 */
	public void generateSbvr() {
		
		UmlProject project = getCurrentProject();
		OperationResult result = SBVRHelper.generateSBVR(project.getModel(), project.getTempDir());
		
		if(result.getResultType() != ResultType.ERROR)
		{
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
			
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
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
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
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
			
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
		else
		{
			getCurrentWrapper().showOutputText(result.toString(), true, true); 
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
		this.setTabComponentAt(this.getTabCount()-1, new ClosableTab(this));
		this.setSelectedIndex(this.getTabCount()-1);
		return component;
	}
	
	/**
	 * Internal class used to create closable tabs
	 */
	private static class ClosableTab extends JPanel {

		private static final long serialVersionUID = -9007086475434456589L;
		private final JTabbedPane pane;

		/**
		 * Constructor for the ClosableTab class.
		 * @param pane the parent {@link JTabbedPane}
		 */
		public ClosableTab(final JTabbedPane pane) {
			//unset default FlowLayout' gaps
			super(new FlowLayout(FlowLayout.LEFT, 0, 0));
			if (pane == null) {
				throw new NullPointerException("TabbedPane is null");
			}
			this.pane = pane;
			setOpaque(false);

			//make JLabel read titles from JTabbedPane
			JLabel label = new JLabel() {

				private static final long serialVersionUID = -5791363706451298026L;
				public String getText() {
					int i = pane.indexOfTabComponent(ClosableTab.this);
					if (i != -1) {													
						return ((Editor) pane.getComponentAt(i)).isSaveNeeded() ? pane.getTitleAt(i) + "*" : pane.getTitleAt(i);
					}
					return null;
				}
			};

			add(label);
			//add more space between the label and the button
			label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
			//tab button
			JButton button = new TabButton();
			add(button);
			//add more space to the top of the component
			setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		}

		/**
		 * Internal class representing the "x" button in the right side of the tab.
		 */
		private class TabButton extends JButton implements ActionListener {

			private static final long serialVersionUID = -3362039507300806289L;

			/**
			 * Constructor for the TabButton class.
			 * */
			public TabButton() {
				int size = 17;
				setPreferredSize(new Dimension(size, size));
				setToolTipText("Close this tab"); //TODO Localize this

				//Make the button looks the same for all Laf's
				setUI(new BasicButtonUI());
				//Make it transparent
				setContentAreaFilled(false);
				//No need to be focusable
				setFocusable(false);
				//setBorder(BorderFactory.createEtchedBorder());
				setBorderPainted(false);
				//Making nice rollover effect
				//we use the same listener for all buttons
				addMouseListener(buttonMouseListener);
				setRolloverEnabled(true);
				//Close the proper tab by clicking the button
				addActionListener(this);
			}

			/**
			 * Handles the action events, closing the tab.
			 * @param e the triggered {@link ActionEvent}
			 * */
			public void actionPerformed(ActionEvent e) {
				int i = pane.indexOfTabComponent(ClosableTab.this);
				if (i != -1) {
					
					IDisposable disposable = (IDisposable) pane.getComponentAt(i);
					if(disposable != null)
					{
						disposable.dispose();
					}
					pane.remove(i);
				}
			}

			/**
			 * Updates the UI. 
			 * */
			public void updateUI() {
				//we don't want to update UI for this button
			}

			/**
			 * Draws the cross
			 * @param g the {@link Graphics} object used in when rendering 
			 * */
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D) g.create();
				//shift the image for pressed buttons
				if (getModel().isPressed()) {
					g2.translate(1, 1);
				}
				g2.setStroke(new BasicStroke(1,BasicStroke.JOIN_ROUND,BasicStroke.CAP_ROUND));
				g2.setColor(Color.BLACK);
				if (getModel().isRollover()) {
					g2.setColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK));
				}
				int delta = 5;

				g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
				g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);

				g2.dispose();
			}
		}

		private static final MouseListener buttonMouseListener = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(true);
				}
			}

			public void mouseExited(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(false);
				}
			}
		};
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
