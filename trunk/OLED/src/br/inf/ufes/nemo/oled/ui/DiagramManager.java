/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight Editor).
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

package br.inf.ufes.nemo.oled.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
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
import java.util.LinkedList;
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

import br.inf.ufes.nemo.oled.draw.Label;
import br.inf.ufes.nemo.oled.draw.LabelChangeListener;
import br.inf.ufes.nemo.oled.model.UmlProject;
import br.inf.ufes.nemo.oled.ui.commands.EcoreExporter;
import br.inf.ufes.nemo.oled.ui.commands.OwlExporter;
import br.inf.ufes.nemo.oled.ui.commands.PngExporter;
import br.inf.ufes.nemo.oled.ui.commands.ProjectReader;
import br.inf.ufes.nemo.oled.ui.commands.ProjectWriter;
import br.inf.ufes.nemo.oled.ui.diagram.DiagramEditor;
import br.inf.ufes.nemo.oled.ui.diagram.EditorMouseEvent;
import br.inf.ufes.nemo.oled.ui.diagram.EditorStateListener;
import br.inf.ufes.nemo.oled.ui.diagram.SelectionListener;
import br.inf.ufes.nemo.oled.umldraw.structure.StructureDiagram;
import br.inf.ufes.nemo.oled.util.ApplicationResources;
import br.inf.ufes.nemo.oled.util.ColorPalette;
import br.inf.ufes.nemo.oled.util.ColorPalette.ThemeColor;
import br.inf.ufes.nemo.oled.util.ModelHelper;
import br.inf.ufes.nemo.oled.util.ValidationHelper;
import br.inf.ufes.nemo.oled.util.VerificationHelper;

/**
 * Class responsible for managing and organizing the {@link DiagramEditor}s in tabs.
 */
public class DiagramManager extends JTabbedPane implements SelectionListener, EditorStateListener {

	private static final long serialVersionUID = 5019191384767258996L;
	private final AppFrame frame;
	private EditorCommandDispatcher editorDispatcher;
	List<String> outputFiles;
	
	//private List<UmlProject> validatingModels = new ArrayList<UmlProject>(); 

	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) {
		super();
		this.frame = frame;
		
		//setFocusable(false);
		
		editorDispatcher = new EditorCommandDispatcher(this);
		//editingDomain = RefOntoUMLHelper.getAdapterEditingDomain();
		
		//When the user selects a tab show the model tree in the tool manager 
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				DiagramEditor editor = getCurrentEditor();
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
		UmlProject model = new UmlProject();
		StructureDiagram diagram = new StructureDiagram(model);
		model.addDiagram(diagram);
		diagram.setLabelText("New Diagram");
		createEditor(diagram);
	}
	
	
	/**
	 * Opens an existing project.
	 */
	public void openProject() {
		if (canOpen()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(getResourceString("dialog.openmodel.title"));
			fileChooser.addChoosableFileFilter(createModelFileFilter());
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					File projectFile = fileChooser.getSelectedFile();
					UmlProject model = (UmlProject) ProjectReader.getInstance().readProject(projectFile);				
					createEditor((StructureDiagram) model.getDiagrams().get(0));
					setModelFile(projectFile);

					//updateFrameTitle(); FIXME
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.readfile.title"),
							JOptionPane.ERROR_MESSAGE);
				}
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
			result = ProjectWriter.getInstance().writeProject(this, file, getCurrentEditor().getProject());
			getCurrentEditor().clearUndoManager();
			frame.updateMenuAndToolbars(getCurrentEditor());
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					getResourceString("error.savefile.title"), JOptionPane.ERROR_MESSAGE);
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
	}
	
	/**
	 * Saves the project with a file chooser.
	 */
	public void saveProjectAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.saveas.title"));
		fileChooser.addChoosableFileFilter(createModelFileFilter());
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
	private FileNameExtensionFilter createModelFileFilter() {
		return new FileNameExtensionFilter(
				"OLED Project (*.oled)", "oled");
	}
	

	/**
	 * Exports graphics as PNG.
	 */
	public void exportGfx() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.exportgfx.title"));
		//FileNameExtensionFilter svgFilter = new FileNameExtensionFilter(
		//  "Scalable Vector Graphics file (*.svg)", "svg");
		FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
				"Portable Network Graphics file (*.png)", "png");
		//fileChooser.addChoosableFileFilter(svgFilter);
		fileChooser.addChoosableFileFilter(pngFilter);
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
			if (fileChooser.getFileFilter() == pngFilter) {
				try {
					PngExporter exporter = new PngExporter();
					exporter.writePNG(getCurrentEditor(), fileChooser.getSelectedFile());
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
		if(getCurrentEditor() != null)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(getResourceString("dialog.exportowl.title"));
			FileNameExtensionFilter owlFilter = new FileNameExtensionFilter(
					"Web Ontology Language file (*.owl)", "owl");
			fileChooser.addChoosableFileFilter(owlFilter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (fileChooser.getFileFilter() == owlFilter) {
					try {
							OwlExporter exporter = new OwlExporter();
							exporter.writeOwl(this, fileChooser.getSelectedFile(), getCurrentEditor().getProject());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								getResourceString("error.exportgfx.title"),
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
	/**
	 * Saves the current model as an Ecore file.
	 * */
	public void exportEcore() {
		if(getCurrentEditor() != null)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(getResourceString("dialog.exportecore.title"));
			FileNameExtensionFilter owlFilter = new FileNameExtensionFilter(
					"Eclipse Ecore Model (*.ecore)", "ecore");
			fileChooser.addChoosableFileFilter(owlFilter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (fileChooser.getFileFilter() == owlFilter) {
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
	 * Imports an Ecore model.
	 * */
	public void importEcore() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.importecore.title"));
		FileNameExtensionFilter owlFilter = new FileNameExtensionFilter(
				"Eclipse Ecore Model (*.ecore)", "ecore");
		fileChooser.addChoosableFileFilter(owlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getFileFilter() == owlFilter) {
				try {
					//Resource resource = RefOntoUMLHelper.createResource();
					//resource.setURI(URI.createFileURI(fileChooser.getSelectedFile().getPath()));
					//resource.getContents().add(getCurrentEditor().getProject().getModel());
					//RefOntoUMLHelper.saveXMI(resource);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("dialog.importecore.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Creates an editor for a given UmlDiagram.
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
				DiagramManager.this.setTitleAt(DiagramManager.this.indexOfComponent(comp),
						label.getNameLabelText());

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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stateChanged(DiagramEditor editor) {
		frame.updateMenuAndToolbars(editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void elementAdded(DiagramEditor editor) {
		frame.selectPaletteDefaultElement();
		frame.updateMenuAndToolbars(editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void elementRemoved(DiagramEditor editor) {
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
	public void setEditorDispatcher(EditorCommandDispatcher editorDispatcher) {
		this.editorDispatcher = editorDispatcher;
	}

	/**
	 * Gets the dispatcher for the editor events
	 * @return EditorCommandDispatcher the dispatcher responsible for routing events
	 * */
	public EditorCommandDispatcher getEditorDispatcher() {
		return editorDispatcher;
	}

	/**
	 * Gets the current DiagramEditor (the editor displayed in the focused tab).
	 * If there's no suitable editor, returns null.
	 * @return DiagramEditor the current focused DiagramEditor
	 * */
	public DiagramEditor getCurrentEditor() {
		if(this.getSelectedIndex() != -1)
		{
			Object obj = this.getSelectedComponent();
			if(obj instanceof DiagramEditorWrapper)
				return ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor();	
		}
		return null;
	}

	/**
	 * Gets the project being edited 
	 * @return {@link UmlProject} the project
	 * */
	public UmlProject getCurrentProject() {
		DiagramEditor editor = getCurrentEditor();
		if(editor != null)
			return editor.getProject();
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
	 * Semantically validate the current model (the model behind the current DiagramEditor).
	 */
	public void validateCurrentModel() {
		UmlProject project = ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor().getProject();
		String validationResult = ValidationHelper.validateModel(project.getModel());
		((DiagramEditorWrapper) this.getSelectedComponent()).showOutputText(validationResult, true);
	}
	
	/**
	 * Verify the current model (the model behind the current DiagramEditor) satisfability using Alloy.
	 */
	public void verifyCurrentModel() {
		UmlProject project = ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor().getProject();
		outputFiles = new LinkedList<String>();
		String verificationResult = VerificationHelper.verifyModel(project.getModel(), outputFiles);
		((DiagramEditorWrapper) this.getSelectedComponent()).showOutputText(verificationResult, true);
	}

	public void verifyCurrentModelFile() {
		outputFiles = new LinkedList<String>();
		String verificationResult = VerificationHelper.verifyModelFromAlloyFile("Simulation.als", outputFiles);
		((DiagramEditorWrapper) this.getSelectedComponent()).showOutputText(verificationResult, true);
	}

	
	public void showOutputPane() {
		((DiagramEditorWrapper) this.getSelectedComponent()).showOrHideOutput();
	}
	
	public void viewOutput() {
		if(outputFiles != null && outputFiles.size() > 0)
			this.add("Verification Output", new InstanceVisualizer(outputFiles));
		else
			((DiagramEditorWrapper) this.getSelectedComponent()).showOutputText("There's no output to be shown", true);
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
						return pane.getTitleAt(i);
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
					pane.remove(i);
					//TODO Check if there nothing else needed in order to close the DiagramManager
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
}
