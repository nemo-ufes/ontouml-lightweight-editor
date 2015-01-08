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

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.NamedElement;
import RefOntoUML.Property;
import RefOntoUML.Relationship;
import RefOntoUML.StringExpression;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import RefOntoUML.parser.SyntacticVerificator;
import RefOntoUML.util.RefOntoUMLResourceUtil;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic.TypeDetail;
import br.ufes.inf.nemo.oled.derivation.DerivedTypesOperations;
import br.ufes.inf.nemo.oled.derivation.ExclusionDerivationOperations;
import br.ufes.inf.nemo.oled.derivation.ExclusionPattern;
import br.ufes.inf.nemo.oled.derivation.IntersectionPattern;
import br.ufes.inf.nemo.oled.derivation.ParticipationDerivationOperations;
import br.ufes.inf.nemo.oled.derivation.ParticipationPatternTypeChoice;
import br.ufes.inf.nemo.oled.derivation.PastSpecializationPattern;
import br.ufes.inf.nemo.oled.derivation.SpecializationPattern;
import br.ufes.inf.nemo.oled.derivation.UnionPattern;
import br.ufes.inf.nemo.oled.dialog.AlloySettingsDialog;
import br.ufes.inf.nemo.oled.dialog.ImportXMIDialog;
import br.ufes.inf.nemo.oled.dialog.OWLSettingsDialog;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.draw.DrawingContextImpl;
import br.ufes.inf.nemo.oled.draw.LineStyle;
import br.ufes.inf.nemo.oled.draw.Node;
import br.ufes.inf.nemo.oled.explorer.CustomOntoUMLElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.explorer.ProjectTree;
import br.ufes.inf.nemo.oled.finder.FoundElement;
import br.ufes.inf.nemo.oled.finder.FoundPane;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.model.UmlDiagram;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.pattern.DomainPatternTool;
import br.ufes.inf.nemo.oled.pattern.PatternTool;
import br.ufes.inf.nemo.oled.problems.ErrorElement;
import br.ufes.inf.nemo.oled.problems.ErrorPane;
import br.ufes.inf.nemo.oled.problems.ErrorVerificator;
import br.ufes.inf.nemo.oled.problems.ProblemElement;
import br.ufes.inf.nemo.oled.problems.ProblemElement.TypeProblem;
import br.ufes.inf.nemo.oled.problems.ProblemPane;
import br.ufes.inf.nemo.oled.problems.WarningPane;
import br.ufes.inf.nemo.oled.problems.WarningVerificator;
import br.ufes.inf.nemo.oled.statistician.StatisticalElement;
import br.ufes.inf.nemo.oled.statistician.StatisticsPane;
import br.ufes.inf.nemo.oled.ui.ClosableTabPanel;
import br.ufes.inf.nemo.oled.ui.StartPanel;
import br.ufes.inf.nemo.oled.ui.commands.EcoreExporter;
import br.ufes.inf.nemo.oled.ui.commands.PngExporter;
import br.ufes.inf.nemo.oled.ui.commands.ProjectReader;
import br.ufes.inf.nemo.oled.ui.commands.ProjectWriter;
import br.ufes.inf.nemo.oled.ui.commands.UMLExporter;
import br.ufes.inf.nemo.oled.ui.diagram.ConstraintEditor;
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
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddGeneralizationSetCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AddNodeCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteElementCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DeleteGeneralizationSetCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.ChangeType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification.NotificationType;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetLabelTextCommand;
import br.ufes.inf.nemo.oled.umldraw.shared.UmlConnection;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.AssociationElement.ReadingDesign;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.umldraw.structure.DiagramElementFactoryImpl;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.ModelHelper;
import br.ufes.inf.nemo.oled.util.OLEDResourceFactory;
import br.ufes.inf.nemo.oled.util.OLEDSettings;
import br.ufes.inf.nemo.oled.util.OWLHelper;
import br.ufes.inf.nemo.oled.util.OperationResult;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.oled.util.ProjectSettings;
import br.ufes.inf.nemo.oled.validator.antipattern.AntiPatternResultDialog;
import br.ufes.inf.nemo.oled.validator.meronymic.ValidationDialog;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;
import br.ufes.inf.nemo.ootos.util.MappingType;
import br.ufes.inf.nemo.ontouml2sbvr.core.OntoUML2SBVR;
import br.ufes.inf.nemo.ontouml2text.ontoUmlGlossary.ui.GlossaryGeneratorUI;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;
import br.ufes.inf.nemo.tocl.tocl2alloy.TOCL2AlloyOption;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

/**
 * Class responsible for managing and organizing the editors in tabs.
 * 
 * @author John Guerson
 */
public class DiagramManager extends JTabbedPane implements SelectionListener, EditorStateListener, IDisposable {

	private static final long serialVersionUID = 5019191384767258996L;
	public final AppFrame frame;
	
	private DiagramEditorCommandDispatcher editorDispatcher;
	private DiagramElementFactoryImpl elementFactory;
	private DrawingContext drawingContext;
	
	private UmlProject currentProject;
	private File projectFile;
	public String lastOpenPath = new String();
	public String lastSavePath = new String();
	public String lastImportEAPath = new String();
	public String lastImportEcorePath = new String();
	public String lastExportEcorePath = new String();
	public String lastExportUMLPath = new String();

	/** Get Frame */
	public AppFrame getFrame() { return frame; }
	/** Get Factory */
	public DiagramElementFactoryImpl getElementFactory() { return elementFactory; }
	/** Get drawing context */
	public DrawingContext getDrawingContext() { return drawingContext; }
	
	/**
	 * Constructor for the DiagramManager class.
	 * @param frame the parent window {@link AppFrame}
	 */
	public DiagramManager(final AppFrame frame) 
	{
		super();
		this.frame = frame;		
		editorDispatcher = new DiagramEditorCommandDispatcher(this,frame);
		elementFactory = new DiagramElementFactoryImpl(); //doesn't have yet any diagram
		drawingContext =  new DrawingContextImpl();
		ModelHelper.initializeHelper();		
		setBorder(new EmptyBorder(0,0,0,0));		
		setBackground(Color.white);
		setMinimumSize(new Dimension(0,0));
	}

	/** Adds a start panel to the manager */
	public StartPanel addStartPanel(JTabbedPane pane, boolean closable)
	{
		StartPanel start = new StartPanel(frame);
		if(closable)addClosable(pane,"Welcome", start);
		else addNonClosable(pane,"Welcome", start);
		return start;
	}

	/** Adds a Finder panel to the manager */
	public FoundPane addFinderPanel(JTabbedPane pane, boolean closable)
	{
		for(Component c: pane.getComponents()) {
			if(c instanceof FoundPane) { pane.setSelectedComponent(c); return (FoundPane)c; }
		}		
		FoundPane finder = new FoundPane(frame.getDiagramManager().getCurrentProject(),true);
		if(closable)addClosable(pane,"Find", finder);
		else addNonClosable(pane,"Find", finder);
		return finder;
	}
	
	/** Adds a Warning panel to the manager */
	public WarningPane addWarningsPanel(JTabbedPane pane, boolean closable)
	{		
		for(Component c: pane.getComponents()) {
			if(c instanceof WarningPane) { pane.setSelectedComponent(c); return (WarningPane)c; }
		}		
		WarningPane warningPane = new WarningPane(frame.getDiagramManager().getCurrentProject());
		if(closable) addClosable(pane,"Warnings", warningPane);
		else addNonClosable(pane,"Warnings", warningPane);
		return warningPane;
	}
	
	/** Adds a Problem panel to the manager */
	public ProblemPane addProblemsPanel(JTabbedPane pane, boolean closable)
	{		
		for(Component c: pane.getComponents()) {
			if(c instanceof ProblemPane) { pane.setSelectedComponent(c); return (ProblemPane)c; }
		}		
		ProblemPane problemsPane = new ProblemPane(frame.getDiagramManager().getCurrentProject());
		if(closable) addClosable(pane,"Problems", problemsPane);
		else addNonClosable(pane,"Problems", problemsPane);
		return problemsPane;
	}
	
	/** Adds a Statistics panel to the manager */
	public StatisticsPane addStatisticsPanel(JTabbedPane pane, boolean closable)
	{
		for(Component c: pane.getComponents()) {
			if(c instanceof StatisticsPane) { pane.setSelectedComponent(c); return (StatisticsPane)c; }
		}		
		StatisticsPane statPanel = new StatisticsPane(frame.getDiagramManager().getCurrentProject());
		if(closable) addClosable(pane,"Statistics", statPanel);
		else addNonClosable(pane,"Statistics", statPanel);
		return statPanel;
	}
	
	/** Sets the dispatcher responsible for routing events of the editor */
	public void setEditorDispatcher(DiagramEditorCommandDispatcher editorDispatcher) 
	{
		this.editorDispatcher = editorDispatcher;
	}

	/** Gets the dispatcher responsible for routing events of the editor */
	public DiagramEditorCommandDispatcher getEditorDispatcher() 
	{
		return editorDispatcher;
	}

	/** Gets the current DiagramEditor (the editor displayed in the focused tab). If there's no suitable editor, returns null. */
	public Editor getCurrentEditor() 
	{
		if(this.getSelectedIndex() != -1){
			Object obj = this.getSelectedComponent();
			if(obj instanceof Editor) return (Editor) obj;	
		}
		return null;
	}

	/** Gets the project being edited */
	public UmlProject getCurrentProject() 
	{
		return currentProject;
	}

	/** Gets the wrapper for the selected DiagramEditor */
	public DiagramEditorWrapper getCurrentWrapper()
	{
		if(this.getSelectedComponent() instanceof DiagramEditorWrapper) return ((DiagramEditorWrapper) this.getSelectedComponent());
		return null;
	}	

	/** Gets the selected DiagramEditor */
	public DiagramEditor getCurrentDiagramEditor() 
	{		
		if(this.getSelectedComponent() instanceof DiagramEditorWrapper){			
			return ((DiagramEditorWrapper) this.getSelectedComponent()).getDiagramEditor();
		}
		return null;
	}
	
	public ConstraintEditor getCurrentConstraintEditor()
	{
		if(this.getSelectedComponent() instanceof ConstraintEditor) return ((ConstraintEditor) this.getSelectedComponent());
		return null;
	}
	
	/** Open Link With Browser */
	public void openLinkWithBrowser(String link)
	{
		Desktop desktop = Desktop.getDesktop();
		if( !desktop.isSupported(Desktop.Action.BROWSE)){
			Main.printErrLine( "Desktop doesn't support the browse action (fatal)" );
			return;
		}
		try {
			java.net.URI uri = new java.net.URI(link);
			desktop.browse(uri);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/** Useful method: Verifies if the element is contained in the list */
	public boolean contains (ArrayList<CustomOntoUMLElement> list, RefOntoUML.Element elem)
	{
		for(CustomOntoUMLElement coe: list){
			if(coe.getElement().equals(elem)) return true;
		}
		return false;
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
	
	/** Return all opened constraint editors */
	public ArrayList<ConstraintEditor> getConstraintEditors()
	{
		ArrayList<ConstraintEditor> list = new ArrayList<ConstraintEditor>();
		for(Component c: getComponents()){
			if(c instanceof ConstraintEditor) list.add((ConstraintEditor)c);
		}
		return list;
	}

	/** Select the tab which contains this editor */
	public void select(Editor editor)
	{		
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if(((DiagramEditorWrapper) c).getDiagramEditor().equals(editor)) setSelectedComponent(c);
			}else{
				if(c.equals(editor)) setSelectedComponent(c);
			}
		}		
	}

	/** Select the tab which constains this diagram */
	public void select(StructureDiagram diagram)
	{		
		for(Component c: getComponents()){
			if(c instanceof DiagramEditorWrapper) {
				if(((DiagramEditorWrapper) c).getDiagramEditor().getDiagram().equals(diagram)) setSelectedComponent(c);
			}
		}		
	}
	
	/** Select the tab which contains this ocl document */
	public void select(OCLDocument oclDoc)
	{		
		for(Component c: getComponents()){
			if(c instanceof ConstraintEditor) {
				if(((ConstraintEditor) c).getOCLDocument().equals(oclDoc)) setSelectedComponent(c);
			}
		}		
	}
	
	/** Get the diagram editor which encapsulates this diagram */
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
	
	/** Get the diagram editor which encapsulates this ocl document */
	public ConstraintEditor getConstraintEditor(OCLDocument oclDoc)
	{		
		for(Component c: getComponents()){
			if(c instanceof ConstraintEditor) {
				if (((ConstraintEditor)c).getOCLDocument().equals(oclDoc))
					return (ConstraintEditor)c;
			}
		}
		return null;
	}
	
	/** Report message to the status bar on the respective diagram */
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

	/** Get the tab index of this particular diagram */
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
	
	public int getTabIndex(OCLDocument oclDoc)
	{
		for(Component c: getComponents()){
			if(c instanceof ConstraintEditor) {
				if (((ConstraintEditor)c).getOCLDocument().equals(oclDoc))
					return indexOfComponent(c);
			}
		}
		return -1;		
	}
	
	/** Gets the file associated with the model. */
	public File getProjectFile()
	{
		return projectFile;
	}

	/** Sets the file associated with the model. */
	public void setProjectFile(File modelFile)
	{
		projectFile = modelFile;
		if((this.getSelectedIndex() != -1)&& !(this.getSelectedComponent() instanceof StartPanel))
		{
			((DiagramEditorWrapper) this.getSelectedComponent()).setModelFile(modelFile);
		}
	}

	/** Gets the MainMenu from the application frame */
	public AppMenu getMainMenu() 
	{
		return frame.getMainMenu();
	}
	
	/** Adds a new tab. */
	public static Component addClosable(JTabbedPane pane, String text, Component component)
	{
		if (component==null) component = new JPanel();
		pane.addTab(text, component);		
		if(component instanceof DiagramEditorWrapper){
			ClosableTabPanel tab = new ClosableTabPanel(pane);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/tree/diagram.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}
		if(component instanceof ConstraintEditor){
			ClosableTabPanel tab = new ClosableTabPanel(pane);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/text-editor.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}
		if(component instanceof TextEditor){
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/editor.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}
		if(component instanceof FoundPane){
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/find.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}		
		if(component instanceof ProblemPane){
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/cross_shield.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}
		if(component instanceof StatisticsPane){
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/diagnostic.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);
		}		
		if(component instanceof WarningPane) {
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/exclamation_octagon_fram.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);			
		}
		if(component instanceof ErrorPane) {
			ClosableTabPanel tab = new ClosableTabPanel(pane,false);
			Icon icon = new ImageIcon(pane.getClass().getClassLoader().getResource("resources/icons/x16/cross_octagon.png"));
			tab.getLabel().setIcon(icon);
			tab.getLabel().setIconTextGap(5);
			tab.getLabel().setHorizontalTextPosition(SwingConstants.RIGHT);
			pane.setTabComponentAt(pane.indexOfComponent(component),tab);			
		}
		pane.setSelectedComponent(component);
		return component;
	}

	/** Add Non Closable Tab */
	public static Component addNonClosable(JTabbedPane pane, String text, Component component)
	{
		if (component==null) component = new JPanel();
		pane.addTab(text, component);
		if(component instanceof ProblemPane) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/spellcheck.png")));		
		if(component instanceof StatisticsPane) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/diagnostic.png")));
		if(component instanceof FoundPane) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/find.png")));
		if(component instanceof ConstraintEditor) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/text-editor.png")));
		if(component instanceof DiagramEditorWrapper) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/diagram.png")));
		if(component instanceof WarningPane) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/exclamation_octagon_fram.png")));
		if(component instanceof ErrorPane) pane.setIconAt(pane.indexOfComponent(component),new ImageIcon(pane.getClass().getResource("/resources/icons/x16/cross_octagon.png")));
		//setTabComponentAt(indexOfComponent(component),null);
		pane.setSelectedComponent(component);
		return component;
	}

	/** Dispose */
	@Override
	public void dispose() {
		int totalTabs = getTabCount();
		for(int i = 0; i < totalTabs; i++) {
			IDisposable disposable = (IDisposable) getComponentAt(i);
			if(disposable != null) disposable.dispose();			
		}
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
		
		for(UmlDiagram diagram: currentProject.getDiagrams()) 
			createDiagramEditor((StructureDiagram)diagram);
		
		if(currentProject.getDiagrams().size()==0) 
			newDiagram(currentProject);
		
		newOCLDocument(currentProject, false);
		
		newOCLDocument(currentProject,false);
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
		newOCLDocument(currentProject,false);
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
						
			int response = JOptionPane.showOptionDialog(
					this,
					"Do you really want to close the current project?",
					"Close project?", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					new String[]{"Save and Close", "Close", "Cancel"},
					"default");
			
			if(response==JOptionPane.YES_OPTION){
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				saveProject();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));			
			}
			if(response==JOptionPane.YES_OPTION || response==JOptionPane.NO_OPTION){
				Main.printOutLine("Closing current project");
				removeAll();
				frame.getBrowserManager().getProjectBrowser().clear();
				frame.getInfoManager().eraseProject();										
				currentProject=null;				
				addStartPanel(this,false);
				Main.printOutLine("Current project closed");
			}
			
		}
		repaint();
		revalidate();
	}

	public void setDefaultDiagramSize(StructureDiagram diagram)
	{
		double waste = 0;
		if(frame.isShowBrowser()) waste+=240;
		if(frame.isShowToolBox()) waste+=240;
		diagram.setSize(AppFrame.GetScreenWorkingWidth()-waste+100, AppFrame.GetScreenWorkingHeight()-100);
	}
	
	/** Creates a new Diagram with in existing Project */
	public void newDiagram(UmlProject project)
	{
		StructureDiagram diagram = new StructureDiagram(project,elementFactory,drawingContext);	
		setDefaultDiagramSize(diagram);
		diagram.setLabelText("Diagram"+getCurrentProject().getDiagrams().size());
		getCurrentProject().addDiagram(diagram);
		saveDiagramNeeded(diagram,false);
		createDiagramEditor(diagram);			
		//add the diagram from the browser
		ProjectBrowser browser = frame.getProjectBrowser();
		browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);
	}

	/** Creates a new OCL document with in existing Project */
	public void newOCLDocument(UmlProject project, boolean openInTab)
	{
		OCLDocument oclDoc = new OCLDocument();
		ArrayList<OCLDocument> docs = frame.getBrowserManager().getProjectBrowser().getOCLDocuments();			
		oclDoc.setName("Document"+docs.size());			
		docs.add(oclDoc);
		if(openInTab)createConstraintEditor(oclDoc);		
		//add the ocl document from the browser
		ProjectBrowser browser = frame.getProjectBrowser();
		browser.getTree().addObject(browser.getTree().getConstraintRootNode(),oclDoc);
	}
	
	/** Creates a new diagram on the current Project */
	public void newDiagram()
	{
		if (currentProject!=null)
		{
			StructureDiagram diagram = new StructureDiagram(getCurrentProject(), elementFactory,drawingContext);
			setDefaultDiagramSize(diagram);
			diagram.setLabelText("Diagram"+getCurrentProject().getDiagrams().size());
			getCurrentProject().addDiagram(diagram);						
			saveDiagramNeeded(diagram,true);
			createDiagramEditor(diagram);			
			//add the diagram from the browser
			ProjectBrowser browser = frame.getProjectBrowser();
			browser.getTree().addObject(browser.getTree().getDiagramRootNode(),diagram);			
		}
	}
	
	/** Creates a new ocl document on the current Project */
	public void newOCLDocument(boolean openInTab)
	{
		if (currentProject!=null)
		{
			OCLDocument oclDoc = new OCLDocument();			
			ArrayList<OCLDocument> docs = frame.getBrowserManager().getProjectBrowser().getOCLDocuments();			
			oclDoc.setName("Document"+docs.size());			
			docs.add(oclDoc);
			if(openInTab) createConstraintEditor(oclDoc);				
			//add the ocl document from the browser
			ProjectBrowser browser = frame.getProjectBrowser();
			browser.getTree().addObject(browser.getTree().getConstraintRootNode(),oclDoc);	
		}
	}
	
	/** Close current diagram editor */
	public void closeDiagram()
	{
		Editor editor = getCurrentDiagramEditor();
		if(editor!=null){
			if(editor.isSaveNeeded()){
				int option = JOptionPane.showConfirmDialog(getFrame(), "Your diagram has been modified. Save changes?","Save Project", JOptionPane.YES_NO_CANCEL_OPTION);
				if (option== JOptionPane.YES_OPTION) {saveProject(); }
				else if (option==JOptionPane.CANCEL_OPTION) { return; }
			}			
			DiagramManager.closeTab(getTabIndex(getCurrentDiagramEditor().getDiagram()),this);
		}
	}

	public static void closeAll(JTabbedPane pane)
	{
		 int tabCount = pane.getTabCount();
         
         for (int i = 1; i < tabCount; i++) {
             closeTab(1, pane);
         }
	}
	
	public static void closeOthers(JTabbedPane pane,Component component)
	{	
		int selectedTabIndex = pane.indexOfComponent(component);
		
		 // First remove higher indexes 
        int tabCount = pane.getTabCount();
         
        if (selectedTabIndex < tabCount - 1) {
            for (int i = selectedTabIndex + 1; i < tabCount; i++) {
                closeTab(selectedTabIndex + 1,pane);
            }
        }
         
        if (selectedTabIndex > 0) {
            for (int i = 1; i < selectedTabIndex; i++) {
                closeTab(1,pane);
            }
        }
	}
	
	public static void closeTab(int i, JTabbedPane pane)
	{		
		if (i != -1) {
			IDisposable disposable = (IDisposable) pane.getComponentAt(i);
			if(disposable != null) disposable.dispose();			
			pane.remove(i);
		}
	}

	/** Delete Diagram */
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
		ProjectBrowser browser = frame.getProjectBrowser();
		browser.getTree().removeCurrentNode();
	}

	/** Delete OCL Document */
	public void deleteOCLDocument(OCLDocument doc)
	{		
		ProjectBrowser pb = frame.getBrowserManager().getProjectBrowser();
		pb.getOCLDocuments().remove(doc);
		for(Component c: getComponents()){
			if (c instanceof ConstraintEditor){
				if (((ConstraintEditor)c).getOCLDocument().equals(doc)) remove(c);
			}
		}		
		pb.getTree().removeCurrentNode();
	}
	
	/** Get the names of all diagrams */
	public ArrayList<String> getDiagramNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(UmlDiagram d: currentProject.getDiagrams()){
			result.add(d.getName());			
		}
		return result;
	}
	
	/**Get the names of all ocl documents */
	public ArrayList<String> getOCLDocumentNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(OCLDocument d: frame.getBrowserManager().getProjectBrowser().getOCLDocuments()){
			result.add(d.getName());			
		}
		return result;
	}
	
	/** Rename diagram */
	public void renameDiagram(final StructureDiagram diagram)
	{
		String text = new String();    						
		text = (String)JOptionPane.showInputDialog(ProjectBrowser.frame,"Please, enter the new name:","Rename Diagram",JOptionPane.INFORMATION_MESSAGE,null,null,diagram.getName());    						
		final String newtext = text;		
		if(text!=null)
		{
			if(getDiagramNames().contains(text)){
				//diagram name must be unique
			}else{
				SwingUtilities.invokeLater(new Runnable() {				
					@Override
					public void run() {
						diagram.setName(newtext);
						int index = getTabIndex(diagram);					
						if(index>=0) setTitleAt(index, newtext);			        
						getFrame().getBrowserManager().getProjectBrowser().refreshTree();	
				        updateUI();
					}
				});				
			}
		}		
	}
	
	/** Rename OCL document */
	public void renameOCLDocument(final OCLDocument oclDoc)
	{
		String text = new String();    						
		text = (String)JOptionPane.showInputDialog(ProjectBrowser.frame,"Please, enter the new name:","Rename OCL Document",JOptionPane.INFORMATION_MESSAGE,null,null,oclDoc.getName());    						
		final String newtext = text;
		if(text!=null)
		{
			if(getOCLDocumentNames().contains(text)){
				// ocl document name must be unique
			}else{
				SwingUtilities.invokeLater(new Runnable() {				
					@Override
					public void run() {
						oclDoc.setName(newtext);
						int index = getTabIndex(oclDoc);					
						if(index>=0) setTitleAt(index, newtext);			        
				        getFrame().getBrowserManager().getProjectBrowser().refreshTree();	
				        updateUI();
					}
				});
			}
		}		
	}
	
	/** Verifies if this diagram is already opened in a tab. */
	public boolean isDiagramOpened (StructureDiagram diagram)
	{
		for(Component c: getComponents())
			if (c instanceof DiagramEditorWrapper)
				if (((DiagramEditorWrapper)c).getDiagramEditor().getDiagram().equals(diagram)) return true;
		return false;
	}

	/** Verifies if this ocl document is already opened in a tab. */
	public boolean isOCLDocumentOpened (OCLDocument oclDoc)
	{
		for(Component c: getComponents())
			if (c instanceof ConstraintEditor)
				if (((ConstraintEditor)c).getOCLDocument().equals(oclDoc)) return true;
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
				Main.printOutLine("Creating New project");				
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
				saveCurrentProjectToFile(file);
				frame.setTitle("OLED - "+file.getName()+"");				
				Main.printOutLine("New project succesffully created");
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}	

	/** Open diagrams loaded from the project. It only opens those diagrams saved as opened. */
	public void openDiagrams()
	{
		if(currentProject.isAllClosed() && currentProject.getDiagrams().size()>0){
			Main.printOutLine("Loading diagram \""+currentProject.getDiagrams().get(0).getName()+"\"");	
			createDiagramEditor((StructureDiagram)currentProject.getDiagrams().get(0));
		}else{
			for(UmlDiagram diagram: currentProject.getDiagrams()) {
				if(currentProject.isOpened(diagram)){
					Main.printOutLine("Loading diagram \""+diagram.getName()+"\"");	
					createDiagramEditor((StructureDiagram)diagram);
				}
			}
		}
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
				Main.printOutLine("Opening OLED project...");				
				File file = fileChooser.getSelectedFile();
				setProjectFile(file);
				lastOpenPath = file.getAbsolutePath();
				ArrayList<Object> listFiles = ProjectReader.getInstance().readProject(file);
				currentProject = (UmlProject) listFiles.get(0);
				if(currentProject.getVersion()==null || currentProject.getVersion().trim().isEmpty() || (currentProject.getVersionAsInt()<=934))
				{
					String msg = "This project was originally edited with an older version of OLED (prior to 1.X), hence some changes are required.\nPress \"OK\" to update this file automatically to this new version.\nNotice that saving this file however will make it no longer works in any version of OLED prior to 1.X.";
					String oldversion = new String();					
					if(currentProject.getVersion()==null || currentProject.getVersion().trim().isEmpty()) oldversion = "Unkown";
					else oldversion = currentProject.getVersion();
					int response = JOptionPane.showOptionDialog(this, msg, "Version Incompatibility: "+oldversion+" to "+Main.OLED_VERSION, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, null, "default");					if(response == JOptionPane.OK_OPTION){														
						openListFiles(listFiles);						
						remakeAllAssociationElements();						
					}else{						
						getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}				
				}else{
					openListFiles(listFiles);				
				}
			} catch (Exception ex) {
				Main.printOutLine("Failed to open OLED project!");	
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			Main.printOutLine("OLED project successfully opened!");	
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
				Main.printOutLine("Opening recent project");				
				File file = new File(startPanel.getSelectedRecentFile());
				setProjectFile(file);
				ArrayList<Object> listFiles = ProjectReader.getInstance().readProject(file);
				currentProject = (UmlProject) listFiles.get(0);				
				if(currentProject.getVersion()==null || currentProject.getVersion().trim().isEmpty() || (currentProject.getVersionAsInt()<=934))
				{
					String msg = "This project was originally edited with an older version of OLED (prior to 1.X), hence some changes are required.\nPress \"OK\" to update this file automatically to this new version.\nNotice that saving this file however will make it no longer works in any version of OLED prior to 1.X.";					
					String oldversion = new String();
					if(currentProject.getVersion()==null || currentProject.getVersion().trim().isEmpty()) oldversion = "Unkown";
					else oldversion = currentProject.getVersion();
					int response = JOptionPane.showOptionDialog(this, msg, "Version Incompatibility: "+oldversion+" to "+Main.OLED_VERSION, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, null, "default");		
					if(response == JOptionPane.OK_OPTION) {						
						openListFiles(listFiles);						
						remakeAllAssociationElements();						
					}else{
						getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}
				}else{
					openListFiles(listFiles);					
				}
			}
		} catch (Exception ex) {
			Main.printOutLine("Failed to open OLED project!");	
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Main.printOutLine("OLED project successfully opened!");	
	}

	/** Open OLED project from the list of object read from stream as a result of the oled file serialization */
	private void openListFiles(ArrayList<Object> listFiles) throws IOException
	{
		currentProject = (UmlProject) listFiles.get(0);
		ProjectBrowser pb = frame.getBrowserManager().getProjectBrowser();
		for(int i=1; i<listFiles.size();i++)
		{																
			OCLDocument oclDoc = (OCLDocument)listFiles.get(i);										
			pb.getOCLDocuments().add(oclDoc);					
		}
		pb.setProject(currentProject);
		frame.getInfoManager().setProject(currentProject);	
		openDiagrams();
		saveProjectNeeded(false);				
		ConfigurationHelper.addRecentProject(projectFile.getCanonicalPath());
		frame.setTitle("OLED - "+projectFile.getName()+"");		
	}
	
	/** Save current Project to a file *.oled */
	private File saveCurrentProjectToFile(File file) 
	{
		Main.printOutLine("Saving OLED project...");
		currentProject.setVersion(Main.OLED_VERSION);
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (file.exists()) file.delete();
		File result = null;
		try {
			if(!file.getName().endsWith(".oled")) {
				file = new File(file.getCanonicalFile() + ".oled");
			}						
			for(ConstraintEditor ce: getConstraintEditors()){				
				if(ce!=null) ce.getOCLDocument().setContent(ce.getText());
			}
			currentProject.clearOpenedDiagrams();
			for(DiagramEditor editor: getDiagramEditors()){
				currentProject.saveAsOpened(editor.getDiagram());
			}			
			result = ProjectWriter.getInstance().writeProject(this, file, currentProject, frame.getBrowserManager().getProjectBrowser().getOCLDocuments());		
			ConfigurationHelper.addRecentProject(file.getCanonicalPath());
			getCurrentProject().setName(file.getName().replace(".oled",""));
			getFrame().getBrowserManager().getProjectBrowser().refreshTree();
			saveAllDiagramNeeded(false);
			frame.setTitle("OLED - "+file.getName()+"");
			invalidate();
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception ex) {
			Main.printOutLine("Failed to save OLED project!");
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.savefile.title"), JOptionPane.ERROR_MESSAGE);
		}
		Main.printOutLine("OLED project successfully saved!");
		return result;
	}

	/** Saves immediately if possible. */
	public void saveProject() 
	{
		if (getProjectFile() == null) 
		{
			int option = saveProjectAs();
			if (option!=JFileChooser.APPROVE_OPTION){
				repaint();
				revalidate();				
				return;
			}
		}else{
			saveCurrentProjectToFile(getProjectFile());
		}
		repaint();
		revalidate();
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
	
	/** Export Model as a OLED Pattern **/
	public int exportAsOLEDPattern() 
	{
		JFileChooser fileChooser = new JFileChooser(lastSavePath);
		fileChooser.setDialogTitle("Export as OLED Pattern");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oledpattern)", "oledpattern");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int option = fileChooser.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			setProjectFile(saveCurrentProjectToFile(fileChooser.getSelectedFile()));
			File file = fileChooser.getSelectedFile();
			frame.setTitle("OLED Pattern - "+file.getName()+"");
			lastSavePath = file.getAbsolutePath();			
		}
		return option;
	}
	
	
	/** Creates an editor for a given Diagram. */
	public DiagramEditor createDiagramEditor(StructureDiagram diagram)
	{		
		DiagramEditor editor = new DiagramEditor(frame, this, diagram);
		editor.showGrid(false);	
		editor.addEditorStateListener(this);
		editor.addSelectionListener(this);
		editor.addAppCommandListener(editorDispatcher);
		// Add all the diagram elements of 'diagram' to the ModelHelper mapping.
		// Keeps trace of mappings between DiagramElement <-> Element.
		ModelHelper.addMapping(editor.getDiagram());
		//Add the diagram to the tabbed pane (this), through the wrapper
		DiagramEditorWrapper wrapper = new DiagramEditorWrapper(editor, editorDispatcher);
		editor.setWrapper(wrapper);
		addClosable(this,diagram.getLabelText(), wrapper);		
		return editor;
	}
	
	/** Creates an editor for a given OCL document. */
	public ConstraintEditor createConstraintEditor(OCLDocument oclDoc)
	{		
		ConstraintEditor editor = new ConstraintEditor(frame, oclDoc);
		addClosable(this,oclDoc.getName(), editor);		
		return editor;
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

	/** Export the current model as an UML2 instance file (EMF-implementation of UML)
	 *  This exporting loses all the UML stereotypes that distinguishes OntoUML from UML*/
	public void exportUML() 
	{
		if(getCurrentEditor() != null) {
			JFileChooser fileChooser = new JFileChooser(lastExportUMLPath);
			fileChooser.setDialogTitle("Export as UML");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("UML2 (*.uml)", "uml");
			fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (fileChooser.getFileFilter() == filter) {
					try {
						UMLExporter exporter = new UMLExporter();
						exporter.writeUML(this, fileChooser.getSelectedFile());
						lastExportUMLPath = fileChooser.getSelectedFile().getAbsolutePath();
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(this, ex.getMessage(),"Export as UML", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}	
	
	/** Exports graphics as PNG. */
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
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();				
		refparser.select((ArrayList<EObject>)elements,true);
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);
		elements.removeAll(added);
		elements.addAll(added);
		//check in the tree the selected elements of the parser
		ProjectBrowser pb = frame.getProjectBrowser();
		pb.getTree().check(elements, true);					
		pb.getTree().updateUI();		
		pb.setParser(refparser);
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
		OntoUMLParser refparser = frame.getProjectBrowser().getParser();				
		refparser.select((ArrayList<EObject>)elements,true);
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);
		elements.removeAll(added);
		elements.addAll(added);
		//check in the tree the selected elements of the parser
		ProjectBrowser modeltree = frame.getProjectBrowser();
		modeltree.getTree().check(elements, true);					
		modeltree.getTree().updateUI();		
		ProjectBrowser pb = frame.getBrowserManager().getProjectBrowser();
		pb.setParser(refparser);
	}
	
	/** 
	 * Tell the application to work only with the checked elements in the tree.
	 * 
	 * This method check if some dependence is missing in the checking, completing it with the missing elements.
	 * The elements checked  in the tree are then properly selected in the OntoUML parser.
	 * This  enables all the application to work only with the checked elements in the parser/tree.
	 * @return 
	 */
	public List<EObject> workingOnlyWithChecked()
	{	
		// This method does a lot of processing and updates the UI -- 
		//This causes lag and frozen ui because they take a lot of time on big models.
		//Suggestion: Call this method from a "doInBackground" of a SwingWorker and the commented ones from "done".
		OntoUMLParser refparser = frame.getProjectBrowser().getParser();
		ProjectBrowser modeltree = frame.getProjectBrowser();			
		List<EObject> selected = modeltree.getTree().getModelCheckedElements();
		refparser.select((ArrayList<EObject>)selected,true);		
		List<EObject> added = refparser.autoSelectDependencies(OntoUMLParser.NO_HIERARCHY,false);		
		selected.removeAll(added);
		selected.addAll(added);	
		
//		modeltree.getTree().checkModelElements(selected, true);			
//		modeltree.getTree().updateUI();
		
		ProjectBrowser pb = frame.getBrowserManager().getProjectBrowser();
		pb.setParser(refparser);
		
		return selected;
	}
	
	/** 
	 * Tell the application to work with all elements in the model.
	 * 
	 * This method check all the elements of the model in the tree. Then properly select them in the OntoUML parser.
	 * This  enables all the application to work with all the elements in the parser/tree.
	 */
	public void workingWithAll()
	{
		OntoUMLParser refparser = frame.getProjectBrowser().getParser();
		ProjectBrowser pb = frame.getProjectBrowser();			
		pb.getTree().checkModelElement(currentProject.getModel());
		refparser.selectAll();		
		pb.getTree().updateUI();		
		pb.setParser(refparser);
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
		}else if (element instanceof RefOntoUML.GeneralizationSet){
			AddGeneralizationSetCommand cmd = new AddGeneralizationSetCommand(null,null,(RefOntoUML.GeneralizationSet)element,((RefOntoUML.GeneralizationSet)element).getGeneralization(),getCurrentProject(),eContainer);		
			cmd.run();
		}else{
			//to add only in the model do exactly as follow		
			AddNodeCommand cmd = new AddNodeCommand(null,null,element,0,0,getCurrentProject(),eContainer);		
			cmd.run();
		}
		return element;
	}

	/** Rename an element. It updates the application accordingly (including the diagrams in which the element appears). It also shows a input dialog for text entry. */
	public void renameElement(RefOntoUML.Element element)
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
	
	/** Delete element from the model and every diagram in each it appears. */
	public void deleteFromOLED(RefOntoUML.Element element, boolean showwarning)
	{	
		int response = -1;
		if (showwarning){
			response = JOptionPane.showConfirmDialog(frame, "WARNING: Are you sure you want to delete the selected items from the model \nand all the diagrams they might appear? This action can still be undone.\n", "Delete from OLED", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
		}else{
			response = Window.OK;
		}
		if(response==Window.OK)
		{		
			ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
			deletionList.add(element);
			ArrayList<DiagramEditor> editors = getDiagramEditors(element);
			//from diagrams & model
			for(DiagramEditor diagramEditor: editors)
			{
				if(element instanceof GeneralizationSet){	
					diagramEditor.execute(new DeleteGeneralizationSetCommand(diagramEditor, element, getCurrentProject()));
				}else{
					diagramEditor.execute(new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),true,true));
				}
			}
			// only from model
			if(editors==null || editors.size()==0)
			{		
				if(element instanceof GeneralizationSet){	
					DeleteGeneralizationSetCommand cmd = new DeleteGeneralizationSetCommand(null, element,getCurrentProject());
					cmd.run();
				}else{
					DeleteElementCommand cmd = new DeleteElementCommand(null,deletionList, getCurrentProject(),true,false);
					cmd.run();	
				}				
			}
		}
	}

	/** Delete elements from the model and every diagram in each they appear. It shows a message before deletion. */
	public void deleteFromOLED(Collection<DiagramElement> diagramElementList, boolean showmessage)
	{
		int response =-1;	
		ArrayList<RefOntoUML.Element> deletionList = (ArrayList<RefOntoUML.Element>)ModelHelper.getElements(diagramElementList);			
		if(deletionList.size()>0){		
			if(showmessage) response = JOptionPane.showConfirmDialog(frame, "WARNING: Are you sure you want to delete the selected items from the model \nand all the diagrams they might appear? This action can still be undone.\n", "Delete from OLED", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(response==Window.OK)
			{							
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
					Main.printErrLine("There is a diagram element without a corresponding model instance. Wrong behaviour.");
				}
			}
		}
	}

	/** Create a generalization set from selected diagram elements */
	public GeneralizationSet addGeneralizationSet(DiagramEditor d, Collection<DiagramElement> diagramElementsList) 
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
		RefOntoUML.GeneralizationSet newgenset = (GeneralizationSet)elementFactory.createElement(ElementType.GENERALIZATIONSET);
		((RefOntoUML.Package)eContainer).getPackagedElement().add(newgenset);
		// init data of generalization set
		((GeneralizationSet)newgenset).setIsCovering(true);
		((GeneralizationSet)newgenset).setIsDisjoint(true);
		((GeneralizationSet)newgenset).setName("gs");
		
		d.execute(new AddGeneralizationSetCommand(d, d.getDiagram(), newgenset, gens, getCurrentProject(), getCurrentProject().getModel()));
				
		return (GeneralizationSet)newgenset;
	}
	
	/** Delete a generalization set from a list of selected diagram elements */
	public void deleteGeneralizationSet(DiagramEditor d, Collection<DiagramElement> diagramElementsList) 
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
			frame.getDiagramManager().deleteFromOLED((RefOntoUML.Element)genSets.get(0).getElement(),true);
		}else{
			CustomOntoUMLElement chosen = (CustomOntoUMLElement) JOptionPane.showInputDialog(getFrame(), 
					"Which generalization set do you want to delete?",
					"Deleting Generalization Set",
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					genSets.toArray(), 
					genSets.toArray()[0]);
			if(chosen!=null){
				frame.getDiagramManager().deleteFromOLED((RefOntoUML.Element)chosen.getElement(),true);
			}		
		}			
	}
	
	/** Delete element from all diagrams in the project. (not from the model) */
	public void deleteFromAllDiagrams(RefOntoUML.Element element)
	{	
		if(element instanceof GeneralizationSet) return;
		if(element instanceof Constraintx) return;
		if(element instanceof Comment) return;
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
		if(element instanceof GeneralizationSet) return;
		if(element instanceof Constraintx) return;
		if(element instanceof Comment) return;
		ArrayList<RefOntoUML.Element> deletionList = new ArrayList<RefOntoUML.Element>();
		deletionList.add(element);		
		DeleteElementCommand cmd = new DeleteElementCommand(diagramEditor,deletionList, diagramEditor.getProject(),false,true);
		cmd.run();		
	}
	
	/** Strictly find by name */
	public ArrayList<FoundElement> strictlyFindByName(String text)
	{		
		ArrayList<FoundElement> result = new ArrayList<FoundElement>();
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		if(refparser!=null && text!=null /*&& !text.isEmpty()*/){
			for(EObject eobj: refparser.getAllInstances(EObject.class)){
				if (eobj instanceof NamedElement){
					String name = ((NamedElement)eobj).getName();
					if(name!=null){
						if(text.trim().isEmpty()) result.add(new FoundElement(eobj));
						else {
							if(name.trim().toLowerCase().compareToIgnoreCase(text)==0) result.add(new FoundElement(eobj));
							else if(name.trim().toLowerCase().contains(text.toLowerCase().trim())) result.add(new FoundElement(eobj));
						}
						
					}
				}
			}
		}		
		return result;
	}
	
	private class DescriptionComparator implements Comparator<ProblemElement> 
    {
        @Override
        public int compare(ProblemElement o1, ProblemElement o2) {
            return o1.getDescription().compareToIgnoreCase(o2.getDescription());
        }
    }
	
	/** It runs the syntactical verification of the metamodel, plus the error and warnings verificator of the application */
	public void verifyModelSyntactically() 
	{
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if(currentProject!=null)
		{
			ProblemPane problemsPane = addProblemsPanel(frame.getInfoManager(),true);
			int count=0;
			ArrayList<ProblemElement> problems = new ArrayList<ProblemElement>();			
			double start = System.currentTimeMillis();			
			//loading syntactical problems...			
			SyntacticVerificator verificator = new SyntacticVerificator();
			verificator.run(currentProject.getModel());			
			for(RefOntoUML.Element elem: verificator.getMap().keySet()){
				for(String message: verificator.getMap().get(elem)){					
					problems.add(new ErrorElement(elem,0,message,TypeProblem.SYNTACTIC));
				}
			}						
			//loading application errors...
			ErrorVerificator errorVerificator = new ErrorVerificator(frame.getProjectBrowser().getParser());
			errorVerificator.run();
			problems.addAll(errorVerificator.getErrors());			
			double end = System.currentTimeMillis();			
			Collections.sort(problems,new DescriptionComparator());
			for(ProblemElement pe: problems) { count++; pe.setIdentifier(count); }
			problemsPane.setData(problems);
			problemsPane.setStatus(
				MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (end - start),  problems.size())
			);
			//============================================
			WarningPane warningsPane = addWarningsPanel(frame.getInfoManager(),true);
			count=0;
			ArrayList<ProblemElement> warnings = new ArrayList<ProblemElement>();
			start = System.currentTimeMillis();		
			//loading application warnings...
			WarningVerificator warningVerificator= new WarningVerificator(frame.getProjectBrowser().getParser());
			warningVerificator.run();
			warnings.addAll(warningVerificator.getWarnings());			
			end = System.currentTimeMillis();
			Collections.sort(warnings,new DescriptionComparator());
			for(ProblemElement pe: warnings) { count++; pe.setIdentifier(count); }
			warningsPane.setData(warnings);
			warningsPane.setStatus(warningVerificator.getTimingMessage());	
			if(!frame.isShowBottomView()) { getMainMenu().getBottomViewItem().setSelected(true); frame.showBottomView(); }
			if(problems.size()>0) frame.selectProblems();
			else frame.selectWarnings();
		}
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/** Collect Statistics */
	public ArrayList<StatisticalElement> collectStatistic()
	{
		ArrayList<StatisticalElement> result = new ArrayList<StatisticalElement>();
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		if(refparser!=null){
			OntoUMLModelStatistic diagnostic = new OntoUMLModelStatistic(refparser);
			diagnostic.run();
			
			for (TypeDetail detail : diagnostic.getDetails()) {
				result.add(new StatisticalElement(detail));
			}

		}
		if(!frame.isShowBottomView()) { getMainMenu().getBottomViewItem().setSelected(true); frame.showBottomView(); }
		frame.selectStatistic();
		return result;
	}
	
	//======================================================================
	//
	//             THE CODE ABOVE WAS REVIWED BY JOHN
	//              NOW, I NEED TO REVIEW THESE ONES BELOW....
	//======================================================================
	//======================================================================
	
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
		getFrame().getBrowserManager().getProjectBrowser().refreshTree();
	}
	
	/** Change multiplicity from string and update the connections in diagrams */
	public void changeMultiplicity(RefOntoUML.Property property, String multiplicity) throws ParseException
	{
		ModelHelper.setMultiplicityFromString(property, multiplicity);
		refreshDiagramElement(property.getAssociation());
		getFrame().getBrowserManager().getProjectBrowser().refreshTree();
	}
	
	/** Change a class stereotype */ 
	public void changeClassStereotype(Type type, String stereo) 
	{   
		ArrayList<DiagramElement> diagramElemList = ModelHelper.getDiagramElements(type);		
   		OutcomeFixer fixer = new OutcomeFixer(frame.getBrowserManager().getProjectBrowser().getParser().getModel());
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
   		OutcomeFixer fixer = new OutcomeFixer(frame.getBrowserManager().getProjectBrowser().getParser().getModel());
   		Fix fix = fixer.changeRelationStereotypeTo(type, fixer.getRelationshipStereotype(stereo));   		
   		updateOLED(fix);   		   		
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
		ReadingDesign direction = ReadingDesign.UNDEFINED;		
		if(element instanceof Association)
		{
			AssociationElement ae = (AssociationElement) ModelHelper.getDiagramElementByEditor(element, d);
			if(ae!=null)
			{
				isRectilinear = ae.isTreeStyle();			
				showName = ae.showName();
				showOntoUMLStereotype = ae.showOntoUmlStereotype();
				showRoles = false;//ae.showRoles();
				showMultiplicities = ae.showMultiplicities();				
				direction = ae.getReadingDesign();
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

	/** Re-make all association in all diagrams they appear.
	 *  This actually deletes all the diagramElements and creates other diagramElements, including them in their specific diagrams.
	 */
	public void remakeAllAssociationElements()
	{
		Set<Association> assocList = frame.getProjectBrowser().getParser().getAllInstances(Association.class);
		for(Association assoc: assocList){
			remakeDiagramElement(assoc);
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
					d.notifyChange(list, ChangeType.ELEMENTS_MODIFIED, NotificationType.DO);
				}			
			}
		}		
	}
	
	/** Move association to a diagram. It creates a diagram element for that refonto instance adding it to the application map. */
	public void moveAssociationToDiagram(Association association, DiagramEditor d, boolean isRectilinear, boolean showName, boolean showOntoUMLStereotype, boolean showMultiplicities, boolean showRoles, ReadingDesign direction)
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
			conn.setReadingDesign(direction);
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
				else if (generalElem.getAbsoluteY1() > specificElem.getAbsoluteY1()) d.setLineStyle(conn, LineStyle.TREESTYLE_VERTICAL);
				else d.setLineStyle(conn, LineStyle.TREESTYLE_HORIZONTAL);
			}
			else if (isRectilinear) d.setLineStyle(conn,  LineStyle.RECTILINEAR);
			else d.setLineStyle(conn,  LineStyle.DIRECT);
			((GeneralizationElement)conn).setShowName(false);
		}
	}
	
	/** Bring related elements to diagram */
	public void addAllRelatedElements(DiagramElement diagramElement, DiagramEditor d)
	{
		if(diagramElement instanceof Node){
			ClassElement ce = (ClassElement)diagramElement;
			Classifier element = ce.getClassifier();
			double x = ce.getAbsoluteX2()+30;
			double y = ce.getAbsoluteY1()-30;
			int row = 0;
			int column = 0;

			OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();

			
			HashSet<Type> addedTypes = new HashSet<Type>();
			
			ArrayList<Relationship> relatedAssociations = new ArrayList<Relationship>();
			relatedAssociations.addAll(refparser.getDirectAssociations(element));
			relatedAssociations.addAll(refparser.getDirectGeneralizations(element));
		
			for(Relationship rel: relatedAssociations){
				try{
					if(d.getDiagram().containsChild(rel))
						continue;
					
					Classifier source = null, target = null;
					
					if(rel instanceof Association){
						source = (Classifier)((Association)rel).getMemberEnd().get(0).getType();
						target = (Classifier)((Association)rel).getMemberEnd().get(1).getType();
						addedTypes.add((Association)rel);
					}
					
					if(rel instanceof Generalization){
						source = (Classifier)((Generalization)rel).getGeneral();
						target = (Classifier)((Generalization)rel).getSpecific();
					}
					
					if(source!=null && !d.getDiagram().containsChild(source)) { 
						moveToDiagram(source,x+100*column,y+75*row,d); 
						row++; 
						
						if(row>2) {
							row=0; column++;
						} 
						addedTypes.add(source);
					}
						
					if(target!=null && !d.getDiagram().containsChild(target)) {  
						moveToDiagram(target,x+100*column,y+75*row,d); 
						row++; 
						
						if(row>2) {
							row=0; 
							column++;
						}
						addedTypes.add(target);
					}
					
					if(d.getDiagram().containsChild(source) && d.getDiagram().containsChild(target)) 
						moveToDiagram(rel, d);
					
				}catch(Exception e){}
			}
			
			HashSet<Type> typesInDiagram = new HashSet<Type>();
			for (DiagramElement de : d.getDiagram().getChildren()) {
				if(de instanceof ClassElement)
					typesInDiagram.add(((ClassElement) de).getClassifier());
			}
			
			for (Association a : refparser.getAssociationsBetween(typesInDiagram)) {
				Type source = a.getMemberEnd().get(0).getType();
				Type target = a.getMemberEnd().get(1).getType();
				
				if(!d.getDiagram().containsChild(a) && (addedTypes.contains(source) || addedTypes.contains(target)))
					moveToDiagram(a,d);
			}
			
			for (Generalization g : refparser.getGeneralizationsBetween(typesInDiagram)) {
				RefOntoUML.Type specific = g.getSpecific();
				RefOntoUML.Type general = g.getGeneral();
				
				if(!d.getDiagram().containsChild(g) && (addedTypes.contains(specific) || addedTypes.contains(general)))
					moveToDiagram(g,d);
			}
			
		}
	}
	
	/** Move element to a Diagram */
	public void moveToDiagram(RefOntoUML.Element classifier, double x, double y, DiagramEditor d)
	{
		if((classifier instanceof RefOntoUML.Class) || (classifier instanceof RefOntoUML.DataType)){			
			AddNodeCommand cmd = new AddNodeCommand(d,d.getDiagram(),classifier,x,y,getCurrentProject(),(RefOntoUML.Element)classifier.eContainer());		
			cmd.run();			
		}
	}
	
	/** Move element to a Diagram */
	public void moveToDiagram(RefOntoUML.Element element, DiagramEditor d)
	{
		if (d!=null && d.getDiagram().containsChild(element))
		{
			if (element instanceof NamedElement) frame.showInformationMessageDialog("Moving to Diagram", "\""+ModelHelper.getStereotype(element)+" "+((NamedElement)element).getName()+"\" already exists in the diagram \""+d.getDiagram().getName()+"\"");			
			else if (element instanceof Generalization) frame.showInformationMessageDialog("Moving to Diagram", "\"Generalization "+((Generalization)element).getSpecific().getName()+"->"+((Generalization)element).getSpecific().getName()+"\" already exists in the diagram \""+d.getDiagram().getName()+"\"");
			DiagramElement de = ModelHelper.getDiagramElementByEditor(element, d);
			if(de!=null) d.select(de);
			return;			
		}
		if (d!=null) 
		{			
			if (element instanceof RefOntoUML.Class) 
			{
				RefOntoUML.Class oClass = (RefOntoUML.Class)element;
				d.setDragElementMode(oClass,oClass.eContainer());
			}			
			if (element instanceof RefOntoUML.DataType)
			{
				RefOntoUML.DataType oClass = (RefOntoUML.DataType)element;
				d.setDragElementMode(oClass,oClass.eContainer());				
			}			
			if(element instanceof Association)
			{
				moveAssociationToDiagram((Association) element, d, false, true, true, true, false, ReadingDesign.UNDEFINED);
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
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		for(RefOntoUML.Generalization gen: refparser.getGeneralizations((RefOntoUML.Classifier)element))
		{
			if (d.getDiagram().containsChild(gen.getGeneral()) && d.getDiagram().containsChild(gen.getSpecific()))
			{	
				d.dragRelation(gen,gen.eContainer());
			}
		}
	}
	
	/**FIXME: Adicionei esse método. By Tiago
	 *  Move associations of an element to the diagram. 
	 *  It will only move the association whose other end appears in the diagram
	 */
	public void moveAssociationsToDiagram(RefOntoUML.Element element, EObject eContainer, DiagramEditor d)
	{
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		
		for(RefOntoUML.Association a: refparser.getDirectAssociations((RefOntoUML.Classifier)element))
		{
			try{
				if (d.getDiagram().containsChild(a.getMemberEnd().get(0).getType()) && d.getDiagram().containsChild(a.getMemberEnd().get(1).getType()))
					d.dragRelation(a,a.eContainer());
			}catch (Exception e){}
		}
	}
	
	/** Invert end points of an association. This method switch the current properties of an association. 
	 *  The source becomes the target and vice-versa. */
	public void invertEndPoints(RefOntoUML.Association association)
	{
		Property source = association.getMemberEnd().get(0);
   		Property target = association.getMemberEnd().get(1);
   		association.getMemberEnd().clear();	
   		association.getOwnedEnd().clear();
   		association.getNavigableOwnedEnd().clear();
   		association.getMemberEnd().add(target);
   		association.getMemberEnd().add(source);   	
   		association.getOwnedEnd().add(target);
   		association.getOwnedEnd().add(source);
   		association.getNavigableOwnedEnd().add(target);
   		association.getNavigableOwnedEnd().add(source);
   		ProjectTree tree = frame.getProjectBrowser().getTree();
   		tree.checkModelElement(source);
   		tree.removeCurrentNode();   		
   		tree.checkModelElement(association);
   		tree.addObject(source);  
   		tree.updateUI();
   		frame.getDiagramManager().updateOLEDFromModification(association, true);
	}
	
	/** Invert names of end points of an association. This method switch the current end names of an association. 
	 *  The source end name becomes the target end name and vice-versa. */
	public void invertEndNames(RefOntoUML.Association association)
	{
		Property source = association.getMemberEnd().get(0);
   		Property target = association.getMemberEnd().get(1);
   		String sourceName = source.getName();
   		String targetName = target.getName();
   		source.setName(targetName);
   		target.setName(sourceName);
   		frame.getDiagramManager().updateOLEDFromModification(association, false);
	}
	
	/** Invert multiplicities of end points of an association. This method switch the current multiplicities of an association. 
	 *  The source end multiplicity becomes the target end multiplicity and vice-versa. */
	public void invertEndMultiplicities(RefOntoUML.Association association)
	{
		Property source = association.getMemberEnd().get(0);
   		Property target = association.getMemberEnd().get(1);
   		LiteralInteger sourceLower = getElementFactory().getFactory().createLiteralInteger();
   		LiteralUnlimitedNatural sourceUpper = getElementFactory().getFactory().createLiteralUnlimitedNatural();
   		sourceLower.setValue(target.getLower());
   		sourceUpper.setValue(target.getUpper());   		
   		LiteralInteger targetLower = getElementFactory().getFactory().createLiteralInteger();
   		LiteralUnlimitedNatural targetUpper = getElementFactory().getFactory().createLiteralUnlimitedNatural();
   		targetUpper.setValue(source.getUpper());
   		targetLower.setValue(source.getLower());  	
   		source.setUpperValue(sourceUpper);
   		source.setLowerValue(sourceLower);
   		target.setUpperValue(targetUpper);
   		target.setLowerValue(targetLower);
   		frame.getDiagramManager().updateOLEDFromModification(association, false);
	}
	
	/** Invert types of end points of an association. This method switch the current types of an association. 
	 *  The source end type becomes the target end type and vice-versa. */
	public void invertEndTypes(RefOntoUML.Association association)
	{
		Property source = association.getMemberEnd().get(0);
   		Property target = association.getMemberEnd().get(1);
   		Type sourceType = source.getType();
   		Type targetType = target.getType();
   		source.setType(targetType);
   		target.setType(sourceType);
   		frame.getDiagramManager().updateOLEDFromModification(association, true);
	}
		
	/** 
	 * Update the application accordingly to the refontouml instance created. This instance must be already be inserted in its container.
	 *  
	 * @param element: added element on refontouml root instance.
	 */
	public void updateOLEDFromInclusion(final RefOntoUML.Element addedElement)
	{		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = ProjectBrowser.frame.getDiagramManager().getCurrentProject();
				// add to the parser
				frame.getBrowserManager().getProjectBrowser().getParser().addElement(addedElement);		
				// add to the tree
				ProjectTree tree = frame.getProjectBrowser().getTree();
				boolean found = tree.checkModelElement(addedElement);
				if(!found) {
					if(addedElement.eContainer()!=null) tree.checkModelElement(addedElement.eContainer());
					else if(addedElement instanceof EnumerationLiteral) tree.checkModelElement(((EnumerationLiteral)addedElement).getEnumeration());
					else tree.checkModelElement(project.getModel());					
					tree.addObject(addedElement);					
				} else {
					if(addedElement instanceof Generalization){
						tree.checkModelElement(addedElement);
						tree.removeCurrentNode();
						tree.checkModelElement(addedElement.eContainer());
						tree.addObject(addedElement);
					}
				}
				tree.updateUI();						
			}
		});		
	}
		
	/**
	 * Update the application accordingly to the list of refontouml elements added into the root model instance.
	 *  
	 * @param fix
	 */
	public void updateOLEDFromInclusion(Fix fix)
	{
		//classes and datatypes
		for(Object obj: fix.getAdded()){			
			if (obj instanceof RefOntoUML.Class||obj instanceof RefOntoUML.DataType) {	
				if (fix.getAddedPosition(obj).x!=-1 && fix.getAddedPosition(obj).y!=-1) 
				{						
					AddNodeCommand cmd = new AddNodeCommand((DiagramNotification)getCurrentDiagramEditor(),getCurrentDiagramEditor().getDiagram(),(RefOntoUML.Element)obj,
					fix.getAddedPosition(obj).x,fix.getAddedPosition(obj).y, getCurrentProject(),(RefOntoUML.Element)((EObject)obj).eContainer());		
					cmd.run();
				}
				else {
					AddNodeCommand cmd = new AddNodeCommand(null,null,(RefOntoUML.Element)obj,0,0,getCurrentProject(),(RefOntoUML.Element)((EObject)obj).eContainer());		
					cmd.run();									
				}
			}			
		}
		//relationships and attributes
		for(Object obj: fix.getAdded()) {
			if (obj instanceof RefOntoUML.Relationship && !(obj instanceof RefOntoUML.Derivation)) {
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
				moveToDiagram((RefOntoUML.Element)obj, getCurrentDiagramEditor());
			}
			if(obj instanceof RefOntoUML.Property){		
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
			}
		}	
		//derivations
		for(Object obj: fix.getAdded()) {
			if (obj instanceof RefOntoUML.Derivation) {
				updateOLEDFromInclusion((RefOntoUML.Element)obj);
				moveToDiagram((RefOntoUML.Element)obj, getCurrentDiagramEditor());
			}
		}	
		//generalization sets
		for(Object obj: fix.getAdded()) {
			if (obj instanceof RefOntoUML.GeneralizationSet) 
			{
				AddGeneralizationSetCommand cmd = new AddGeneralizationSetCommand((DiagramNotification)getCurrentDiagramEditor(),getCurrentDiagramEditor().getDiagram(),(RefOntoUML.Element)obj,
				((GeneralizationSet)obj).getGeneralization(),getCurrentProject(),(RefOntoUML.Element)((EObject)obj).eContainer());
				cmd.run(); 
			}
		}
	}	
		
	/**
	 * Update the application accordingly to the refontouml instance that were modified.
	 * 
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
	 * Update the application accordingly to the list of refontouml elements modified into the root model instance.  
	 * 
	 * @param fix
	 */
	public void updateOLEDFromModification(Fix fix)
	{
		for(Object obj: fix.getModified())
		{
			boolean redesign=false;
						
			if (obj instanceof RefOntoUML.Property) {												
				if (((RefOntoUML.Property)obj).getAssociation()!=null) redesign=true;	else redesign=false;		
			}							
			else if (obj instanceof RefOntoUML.Association) {
				redesign=true;
			}
			else if (obj instanceof Generalization) {
				redesign=true;
			}
			else {
				redesign=false;
			}
			updateOLEDFromModification((RefOntoUML.Element)obj,redesign);
		}
	}
	
	/**
	 * Update the application accordingly to the refontouml instance that were deleted from the root refontouml instance
	 * 
	 * @param element: deleted element on the refontouml root instance
	 */
	@SuppressWarnings("unused")
	public void updateOLEDFromDeletion(final RefOntoUML.Element deletedElement)
	{		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				UmlProject project = frame.getDiagramManager().getCurrentProject();
				// delete from the parser
				frame.getBrowserManager().getProjectBrowser().getParser().removeElement(deletedElement);							
				// delete from the tree
				ProjectBrowser browser = frame.getProjectBrowser();		
				browser.getTree().remove(deletedElement);
			}
		});
	}
	
	/** Update OLED according to a Fix.  */
	public void updateOLED (final Fix fix)
	{
		if (fix==null) return;	
		
		updateOLEDFromInclusion(fix);
				
		updateOLEDFromModification(fix);
		
		for(Object obj: fix.getDeleted()) {
			deleteFromOLED((RefOntoUML.Element)obj,false);				
		}
		for(String str: fix.getAddedRules()){
			frame.getBrowserManager().getProjectBrowser().getOCLDocuments().get(0).addContent(str);		
		}
		return ;
	}
	
	/** Open the Text Description settings window */
	public void callGlossary() 
	{
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {								
				GlossaryGeneratorUI settings = new GlossaryGeneratorUI(frame.getBrowserManager().getProjectBrowser().getParser());
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
		TOCL2AlloyOption oclOptions = frame.getProjectBrowser().getOCLOption();
		//configure a default ontouml2alloy option
		OntoUML2AlloyOptions refOptions = frame.getProjectBrowser().getOntoUMLOption();
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		refOptions.check(refparser);
		// open settings
		AlloySettingsDialog.open(refOptions, oclOptions, getFrame());	
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/** Get working constraints */
	public String getWorkingConstraints()
	{
		String result = new String();
		for(OCLDocument oclmodel: frame.getBrowserManager().getProjectBrowser().getOCLDocuments())
		{				
			ConstraintEditor ce = getConstraintEditor(oclmodel);
			if(ce!=null) result+=ce.getText();
			else result+=oclmodel.getContent();
		}
		return result;
	}
	
	/** Parse constraints from the editor */
	public void parseConstraints(boolean showSuccesfullyMessage)
	{
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();		
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to create an OLED project first."); return; }		
		try { 
			String name = ((RefOntoUML.Package)getCurrentProject().getResource().getContents().get(0)).getName();
			if (name==null || name.isEmpty()) name = "model";
			TOCLParser toclparser = new TOCLParser(refparser,getCurrentProject().getTempDir()+File.separator,name.toLowerCase());
			toclparser.parseTemporalOCL(getWorkingConstraints());			
			frame.getProjectBrowser().setOCLOption(new TOCL2AlloyOption(toclparser));
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
		
		if (frame.getProjectBrowser().getOntoUMLOption().openAnalyzer) openAnalyzer(frame.getProjectBrowser().getAlloySpec(),true, -1);
		else openAnalyzer(frame.getProjectBrowser().getAlloySpec(),false, 0);	
		
		String umlpath = frame.getProjectBrowser().getAlloySpec().getAlloyPath().replace(".als", ".uml");
		File umlfile = new File(umlpath);
		umlfile.deleteOnExit();		
	}
	
	/** Run transformation from OntoUML into Alloy */
	public void runOntoUML2Alloy()
	{
		OntoUMLParser refparser = frame.getProjectBrowser().getParser();
		OntoUML2AlloyOptions refOptions = frame.getProjectBrowser().getOntoUMLOption();
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to first create an OLED project."); return; }
		try {			
			// transforming...
			frame.getProjectBrowser().getAlloySpec().setAlloyModel(refparser,refOptions);
		} catch (Exception e) {
			frame.showErrorMessageDialog("Transforming OntoUML into Alloy",e.getLocalizedMessage());					
			e.printStackTrace();
		}
	}
	
	/** Run Transformation from TOCL into Alloy */
	public void runTOCL2Alloy()
	{
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();		
		TOCL2AlloyOption oclOptions = frame.getProjectBrowser().getOCLOption();
		AlloySpecification alloySpec = frame.getProjectBrowser().getAlloySpec();
		if (refparser==null) { frame.showErrorMessageDialog("Error","Inexistent model. You need to first create an OLED project."); return; }
		if (oclOptions.getParser()==null) { /*frame.showErrorMessageDialog("Error","Inexistent constraints. You need to first create constraints.");*/  return; }
		try {						
			// transforming...
			String logMessage = alloySpec.addConstraints(refparser, oclOptions.getParser(),oclOptions);
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
	
	/** Open the OWL settings window	 */
	public void openOwlSettings() 
	{
		OWLSettingsDialog dialog = new OWLSettingsDialog(frame, this, true);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}	
	
	/** Open modeling assistant wizard */
	@Deprecated
//	public void openModellingAssistant(final Classifier elem)
//	{
//		boolean runAssistant = getFrame().getMainMenu().isAssistantChecked();
//		if(runAssistant) {
//			if(Main.onMac()) {
//				com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute(new Runnable(){        	
//					@Override
//					public void run() {
//						Fix fix = frame.getProjectBrowser().getAssistant().runPattern(elem);						
//						if(fix != null) updateOLED(fix);
//					}
//				});
//			}else{
//				final Fix fix = frame.getProjectBrowser().getAssistant().runPattern(elem);
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
//	}
	
	/**  Generate SBVR. In order to use the plug-in, we need to store the model into a file before. */
	public void generateSbvr(RefOntoUML.Model refpackage) 
	{
		UmlProject project = getCurrentProject();
		OperationResult result;
		String modelFileName = ConfigurationHelper.getCanonPath(project.getTempDir(), OLEDSettings.MODEL_DEFAULT_FILE.getValue());
		File modelFile = new File(modelFileName);  	
    	modelFile.deleteOnExit();    	
		try {			
			RefOntoUMLResourceUtil.saveModel(modelFileName, refpackage);
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
	
	/**
	 * Auto complete selection in the model
	 */
	public String autoCompleteSelection(int option, UmlProject project)
	{		
		OntoUMLParser refparser = frame.getProjectBrowser().getParser();
		ProjectBrowser modeltree = frame.getProjectBrowser();

		if (refparser==null) { return ""; }	

		// Get elements from the tree
		List<EObject> selected = modeltree.getTree().getModelCheckedElements();

		// Get added elements from the auto selection completion
		refparser.select((ArrayList<EObject>)selected,true);		
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
//		try{
//			OCLDocument oclmodel = ProjectBrowser.getOCLDocumentFor(getCurrentProject());
//			String path = FileChoosersUtil.saveOCLPathLocation(frame,oclmodel.getPath());	    		
//			if (path==null) return;			
//			if(getCurrentConstraintEditor()!=null){
//				oclmodel.setConstraints(getCurrentConstraintEditor().getText(),"CONTENT");
//				oclmodel.setPath(path);
//				FileUtil.copyStringToFile(getCurrentConstraintEditor().getText(), path);
//			}
//		}catch(IOException exception){
//			String msg = "An error ocurred while saving the constraints to an OCL document.\n"+exception.getMessage();
//			frame.showErrorMessageDialog("Saving OCL",msg);		       			
//			exception.printStackTrace();
//		}
	}

	/**
	 * Get Resource as String
	 * @return
	 */
	public static String getResourceString(String property) 
	{
		return ApplicationResources.getInstance().getString(property);
	}

	/**
	 * Imports a Complete OCL document
	 */
	public void importOCL() 
	{
//		try{
//			if (getCurrentProject()==null) newProject();			
//			OCLDocument oclmodel = ProjectBrowser.getOCLDocumentFor(getCurrentProject());
//			String path = FileChoosersUtil.openOCLPathLocation(frame,oclmodel.getPath());
//			if (path==null) return;
//			oclmodel.setConstraints(path,"PATH");			
//		} catch (IOException exception) {				
//			String msg = "An error ocurred while opening the OCL document.\n"+exception.getMessage();
//			frame.showErrorMessageDialog("Opening OCL",msg);
//			exception.printStackTrace();
//		}				
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
				ArrayList<DiagramElement> elemList=new ArrayList<DiagramElement>();
				if(element instanceof Property){
					elemList = ModelHelper.getDiagramElements((RefOntoUML.Element)element.eContainer());
				}else if(element instanceof EnumerationLiteral){
					elemList = ModelHelper.getDiagramElements(((RefOntoUML.EnumerationLiteral)element).getEnumeration());
				}else if (element instanceof GeneralizationSet){
					for(Generalization gen: ((RefOntoUML.GeneralizationSet)element).getGeneralization()){
						elemList = ModelHelper.getDiagramElements(gen);
					}
				}else{
					elemList = ModelHelper.getDiagramElements(element);
				}
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
		String oclRules = new String();
		oclRules = getWorkingConstraints();				
		RefOntoUML.Package model = frame.getBrowserManager().getProjectBrowser().getParser().createModelFromSelections(new Copier());
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
					addClosable(this,"Text Editor", textViz);
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
		ExclusionDerivationOperations.createExclusionDerivation(activeEditor, project, this, activeEditor.getSelectedElements(), new OutcomeFixer(this.getCurrentProject().getModel()));
		
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

	private Fix _fix;
	public void runPattern(final ElementType elementType, final double x, final double y) {
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					_fix = PatternTool.tryToRun(elementType, x, y);
					if(_fix == null){
						List<DiagramElement> selectedElements = getCurrentDiagramEditor().getSelectedElements();
						_fix = PatternTool.tryToRun(elementType, selectedElements);
					}
					if(_fix != null)
						updateOLED(_fix);
				}
			});
		}else{
			_fix = PatternTool.tryToRun(elementType, x, y);
			if(_fix == null){
				List<DiagramElement> selectedElements = getCurrentDiagramEditor().getSelectedElements();
				_fix = PatternTool.tryToRun(elementType, selectedElements);
			}
			if(_fix != null)
				updateOLED(_fix);
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
		ValidationDialog.open(frame.getBrowserManager().getProjectBrowser().getParser(), frame);
		
	}
	public void openDerivedTypePatternSpecialization(double x, double y) {
		
		JDialog dialog = new SpecializationPattern(this);
		this.setCenterDialog(dialog);
		((SpecializationPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	public void deriveBySpecialization() {
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		Fix fix = DerivedTypesOperations.createSpecializationDerivation(activeEditor, project,this);
		if(fix!=null) updateOLED(fix);
	}
	public void openDerivedTypePatternPastSpecialization(double x, double y) {
		JDialog dialog = new PastSpecializationPattern(this);
		this.setCenterDialog(dialog);
		((PastSpecializationPattern) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
		
	}
	public void openDerivedTypePatternParticipation(double x, double y) {
		
		JDialog dialog = new ParticipationPatternTypeChoice(this);
		this.setCenterDialog(dialog);
		((ParticipationPatternTypeChoice) dialog).setPosition(x, y);
		dialog.setModal(true);
		dialog.setVisible(true);
		
	}
	public void deriveByPastSpecialization() {
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		DerivedTypesOperations.createPastSpecializationDerivation(activeEditor, project,this);
	}
	public void deriveByParticipation() {
		DiagramEditor activeEditor = getCurrentDiagramEditor();
		UmlProject project = getCurrentEditor().getProject();
		ParticipationDerivationOperations participation_derivation = new ParticipationDerivationOperations();
		participation_derivation.createDerivedType(activeEditor, project,this);
	}
	
	/** Save current Pattern Project to a file *.oledpattern */
	@SuppressWarnings("unused")
	private File exportCurrentProjectToFile(File file) 
	{
		Main.printOutLine("Saving OLED Pattern project...");
		currentProject.setVersion(Main.OLED_VERSION);
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (file.exists()) file.delete();
		File result = null;
		try {
			if(!file.getName().endsWith(".oledpattern")) {
				if(file.getName().contains(".oled"))
					file = new File(file.getCanonicalFile().toString().replaceAll(".oled", ".oledpattern"));
				else
					file = new File(file.getCanonicalFile() + ".oledpattern");
			}						
			for(ConstraintEditor ce: getConstraintEditors()){				
				if(ce!=null) ce.getOCLDocument().setContent(ce.getText());
			}
			currentProject.clearOpenedDiagrams();
			for(DiagramEditor editor: getDiagramEditors()){
				currentProject.saveAsOpened(editor.getDiagram());
			}			
			result = ProjectWriter.getInstance().writeProject(this, file, currentProject, frame.getBrowserManager().getProjectBrowser().getOCLDocuments());		
			ConfigurationHelper.addRecentProject(file.getCanonicalPath());
			getCurrentProject().setName(file.getName().replace(".oledpattern",""));
			getFrame().getBrowserManager().getProjectBrowser().refreshTree();
			saveAllDiagramNeeded(false);
			frame.setTitle("OLED Pattern - "+file.getName()+"");
			invalidate();
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception ex) {
			Main.printOutLine("Failed to save OLED Pattern project!");
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.savefile.title"), JOptionPane.ERROR_MESSAGE);
		}
		Main.printOutLine("OLED Pattern project successfully saved!");
		return result;
	}

	//Export as .oledpattern	
	public void exportPattern(){
		DomainPatternTool.exportModelAsPattern(currentProject);
		//call exportCurrentProject
		//call exportAsOLEDPattern
	}
	
	private UmlProject importPatternProjectFile(){
		UmlProject patternProject = null;
		
		JFileChooser fileChooser = new JFileChooser(lastOpenPath);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OLED Project (*.oled)", "oled");
		fileChooser.setDialogTitle("Open OLED Pattern Project");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);		
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Main.printOutLine("Opening OLED project...");				
				File file = fileChooser.getSelectedFile();
				ArrayList<Object> listFiles = ProjectReader.getInstance().readProject(file);
				patternProject = (UmlProject) listFiles.get(0);
			} catch (Exception ex) {
				Main.printOutLine("Failed to open OLED project!");	
				JOptionPane.showMessageDialog(this, ex.getMessage(), getResourceString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			Main.printOutLine("OLED project successfully opened!");	
		}
		
		return patternProject;
	}
	
	public void importPattern(){
		//opening .oledpattern
		UmlProject patternProject = importPatternProjectFile();
		if(patternProject != null){		
			DomainPatternTool.initializeDomainPatternPalette(frame.getToolManager().getPalleteAccordion(), patternProject, editorDispatcher, frame);
		}
	}
		
	public void runDomainPattern(final double x, final double y) {
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					_fix = DomainPatternTool.run(x, y);
					if(_fix != null)
						updateOLED(_fix);
				}
			});
		}else{
			_fix = DomainPatternTool.run(x, y);
			if(_fix != null)
				updateOLED(_fix);
		}
	}
	
}