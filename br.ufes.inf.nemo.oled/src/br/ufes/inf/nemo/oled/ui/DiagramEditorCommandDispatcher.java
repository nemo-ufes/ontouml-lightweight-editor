/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui;

import java.util.HashMap;
import java.util.Map;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.OCL2AlloyOptions;
import br.ufes.inf.nemo.oled.draw.Scaling;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationEndType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.dialog.AboutDialog;
import br.ufes.inf.nemo.oled.ui.dialog.AntiPatternListDialog;
import br.ufes.inf.nemo.oled.ui.dialog.AutoCompletionDialog;
import br.ufes.inf.nemo.oled.ui.dialog.LicensesDialog;
import br.ufes.inf.nemo.oled.ui.dialog.SimulationOptionsDialog;
import br.ufes.inf.nemo.oled.util.AppCommandListener;
import br.ufes.inf.nemo.oled.util.MethodCall;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * This class receives BaseEditor related AppCommands and dispatches them to
 * the right places. This offloads editor related commands from the
 * AppFrame object, while AppFrame handles commands on a global level,
 * DiagramEditorCommandDispatcher handles it on the level of the current editor.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class DiagramEditorCommandDispatcher implements AppCommandListener {

	//private AppFrame frame;
	private DiagramManager manager;
	private Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();

	/**
	 * Constructor.
	 * @param aFrame the application frame
	 */
	public DiagramEditorCommandDispatcher(DiagramManager manager) {
		this.manager = manager;
		initSelectorMap();
	}

	/**
	 * Initializes the selector map.
	 */
	private void initSelectorMap() {
		try {
			selectorMap.put("SELECT_MODE", new MethodCall(
					DiagramEditor.class.getMethod("setSelectionMode")));
			
			selectorMap.put("REDO", new MethodCall(
					DiagramEditor.class.getMethod("redo")));
			
			selectorMap.put("UNDO", new MethodCall(
					DiagramEditor.class.getMethod("undo")));
						
			selectorMap.put("REDRAW", new MethodCall(
					DiagramEditor.class.getMethod("redraw")));
			
			selectorMap.put("ZOOM_50", new MethodCall(
					DiagramEditor.class.getMethod("setScaling", Scaling.class),
					Scaling.SCALING_50));
			
			selectorMap.put("ZOOM_75", new MethodCall(
					DiagramEditor.class.getMethod("setScaling", Scaling.class),
					Scaling.SCALING_75));
			
			selectorMap.put("ZOOM_100", new MethodCall(
					DiagramEditor.class.getMethod("setScaling", Scaling.class),
					Scaling.SCALING_100));
			
			selectorMap.put("ZOOM_150", new MethodCall(
					DiagramEditor.class.getMethod("setScaling", Scaling.class),
					Scaling.SCALING_150));
			
			selectorMap.put("BRING_TO_FRONT", new MethodCall(
					DiagramEditor.class.getMethod("bringToFront")));
			
			selectorMap.put("PUT_TO_BACK", new MethodCall(
					DiagramEditor.class.getMethod("putToBack")));
			
			selectorMap.put("EDIT_PROPERTIES", new MethodCall(
					DiagramEditor.class.getMethod("editProperties")));

			selectorMap.put("DELETE", new MethodCall(
					DiagramEditor.class.getMethod("deleteSelection")));
			
			//Commands for creating classes
			selectorMap.put("CREATE_KIND", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.KIND));
			
			selectorMap.put("CREATE_QUANTITY", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.QUANTITY));
			
			selectorMap.put("CREATE_COLLECTIVE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.COLLECTIVE));
			
			selectorMap.put("CREATE_SUBKIND", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.SUBKIND));
		
			selectorMap.put("CREATE_PHASE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.PHASE));
		
			selectorMap.put("CREATE_ROLE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.ROLE));
		
			selectorMap.put("CREATE_CATEGORY", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.CATEGORY));
		
			selectorMap.put("CREATE_ROLEMIXIN", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.ROLEMIXIN));
		
			selectorMap.put("CREATE_MIXIN", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.MIXIN));
		
			selectorMap.put("CREATE_MODE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.MODE));
		
			selectorMap.put("CREATE_RELATOR", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.RELATOR));
			
			selectorMap.put("CREATE_DATATYPE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.DATATYPE));
			
			//Commands for creating relations		
			selectorMap.put("CREATE_GENERALIZATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.GENERALIZATION));
			
			selectorMap.put("CREATE_CHARACTERIZATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.CHARACTERIZATION));
			
			selectorMap.put("CREATE_FORMAL", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.FORMAL));
			
			selectorMap.put("CREATE_MATERIAL", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.MATERIAL));
						
			selectorMap.put("CREATE_MEDIATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.MEDIATION));
			
			selectorMap.put("CREATE_MEMBEROF", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.MEMBEROF));
			
			selectorMap.put("CREATE_SUBQUANTITYOF", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.SUBQUANTITYOF));
			
			selectorMap.put("CREATE_SUBCOLLECTIONOF", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.SUBCOLLECTIONOF));
			
			selectorMap.put("CREATE_COMPONENTOF", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.COMPONENTOF));
			
			selectorMap.put("CREATE_DERIVATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.DERIVATION));

			selectorMap.put("CREATE_ASSOCIATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.ASSOCIATION));
			
			//selectorMap.put("CREATE_CONDITION", new MethodCall(
			//		DiagramEditor.class.getMethod("setCreateConnectionMode",
			//				RelationType.class), RelationType.ASSOCIATION));//Asso
			
			//selectorMap.put("CREATE_DERIVATIONRULE", new MethodCall(
			//		DiagramEditor.class.getMethod("setCreateConnectionMode",
			//				RelationType.class), RelationType.ASSOCIATION));//Asso
			
			//selectorMap.put("CREATE_CONCLUSION", new MethodCall(
			//		DiagramEditor.class.getMethod("setCreateConnectionMode",
			//				RelationType.class), RelationType.ASSOCIATION));//Asso
			
			//selectorMap.put("CREATE_NOTE", new MethodCall(
			//		DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
			//		ElementType.NOTE));
			
			selectorMap.put("CREATE_NOTE_CONNECTION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.NOTE_CONNECTOR));
			
			selectorMap.put("RESET_POINTS", new MethodCall(
					DiagramEditor.class.getMethod("resetConnectionPoints")));
			
			selectorMap.put("RECT_TO_DIRECT", new MethodCall(
					DiagramEditor.class.getMethod("rectilinearToDirect")));
			
			selectorMap.put("DIRECT_TO_RECT", new MethodCall(
					DiagramEditor.class.getMethod("directToRectilinear")));
			
			selectorMap.put("NAVIGABLE_TO_SOURCE", new MethodCall(
					DiagramEditor.class.getMethod("setNavigability", RelationEndType.class),
					RelationEndType.SOURCE));
			
			selectorMap.put("NAVIGABLE_TO_TARGET", new MethodCall(
					DiagramEditor.class.getMethod("setNavigability", RelationEndType.class),
					RelationEndType.TARGET));

			// Self-calls
			selectorMap.put("SHOW_GRID", new MethodCall(
					getClass().getMethod("showGrid")));
			
			selectorMap.put("SNAP_TO_GRID", new MethodCall(
					getClass().getMethod("snapToGrid")));
			
			selectorMap.put("ABOUT", new MethodCall(
					getClass().getMethod("showAbout")));
			
			selectorMap.put("COPYRIGHTS", new MethodCall(
					getClass().getMethod("showCopyrights")));
			
			selectorMap.put("PARSE_OCL", new MethodCall(
					getClass().getMethod("parseOCL")));
			
			selectorMap.put("AUTO_SELECTION", new MethodCall(
					getClass().getMethod("autoComplete")));
			
			selectorMap.put("VALIDATE_MODEL", new MethodCall(
					getClass().getMethod("validateModel")));
			
			selectorMap.put("GENERATE_ALLOY", new MethodCall(
					getClass().getMethod("generatesAlloy")));
			
			selectorMap.put("ANTIPATTERN", new MethodCall(
					getClass().getMethod("manageAntiPatterns")));
			
			selectorMap.put("VERIFY_SETTINGS", new MethodCall(
					getClass().getMethod("verifySettings")));
			
			selectorMap.put("VERIFY_MODEL", new MethodCall(
					getClass().getMethod("verifyModel")));
			
			selectorMap.put("VERIFY_MODEL_FILE", new MethodCall(
					getClass().getMethod("verifyModelFile")));
			
			selectorMap.put("GENERATE_OWL_SETTINGS", new MethodCall(
					getClass().getMethod("generateOwlSettings")));
			
			selectorMap.put("GENERATE_OWL", new MethodCall(
					getClass().getMethod("generateOwl")));
			
			selectorMap.put("GENERATE_SBVR", new MethodCall(
					getClass().getMethod("generateSbvr")));
			
			selectorMap.put("GENERATE_TEXT", new MethodCall(
					getClass().getMethod("generateText")));
			
			selectorMap.put("ERROR", new MethodCall(
					getClass().getMethod("searchErrors")));

			selectorMap.put("WARNING", new MethodCall(
					getClass().getMethod("searchWarnings")));
			
			selectorMap.put("OCLEDITOR", new MethodCall(
					getClass().getMethod("showOclEditor")));
			
			selectorMap.put("OUTPUT", new MethodCall(
					getClass().getMethod("showOutputPane")));

			selectorMap.put("DERIVERELATIONS", new MethodCall(
					getClass().getMethod("deriveRelations")));
			
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void handleCommand(String command) {
		MethodCall methodcall = selectorMap.get(command);
		if (methodcall != null) {
			  Object target = manager.getCurrentDiagramEditor();
			  if(target != null)
			  {
			      // in order to catch the self calling methods
			      if (methodcall.getMethod().getDeclaringClass() == DiagramEditorCommandDispatcher.class) {
			        target = this;
			      }
			      methodcall.call(target);
			  }
		} 
	}

	public void generatesAlloy()
	{	
		manager.parseOCL(false);
		OCL2AlloyOptions oclOptions = ModelTree.getOCLOptionsFor(manager.getCurrentProject());
		
		OntoUMLParser refparser = ModelTree.getParserFor(manager.getCurrentProject());
		OntoUML2AlloyOptions refOptions = ModelTree.getOntoUMLOptionsFor(manager.getCurrentProject());
		
		refOptions.openAnalyzer=true;    	
    	if (!refparser.getElementsWithIdentityMissing().isEmpty()) refOptions.identityPrinciple = false;    		    	
    	if (!refparser.getRelatorsWithInvalidAxiom().isEmpty()) refOptions.relatorAxiom = false;    	
    	if (!refparser.getWholesWithInvalidWeakSupplementation().isEmpty()) refOptions.weakSupplementationAxiom = false;
		
		SimulationOptionsDialog.open(refOptions, oclOptions, manager.getFrame());
	}
	
	public void manageAntiPatterns()
	{
		AntiPatternListDialog.open(manager.getFrame());		
	}
	
	public void parseOCL()
	{
		manager.parseOCL(true);		
	}
	
	public void showOutputPane()
	{
		manager.showOutputPane();
	}

	public void showAbout()
	{		
		AboutDialog.open(manager.getFrame());
	}
	
	public void showCopyrights()
	{		
		LicensesDialog.open(manager.getFrame());
	}
	
	public void searchWarnings()
	{
		manager.searchWarnings();
		manager.getCurrentWrapper().focusOnWarnings();
	}
	
	public void searchErrors()
	{
		manager.searchErrors();
		manager.getCurrentWrapper().focusOnErrors();
	}
	
	public void showOclEditor()
	{
		manager.showOclEditor();
	}
	
	public void autoComplete()
	{
		AutoCompletionDialog.open(manager.getFrame(),manager.getCurrentProject());
	}
	
	public void validateModel() 
	{
		manager.verifyCurrentModel();
	}
	
	public void verifySettings() 
	{
		manager.verificationSettings();
	}
	
	public void deriveRelations() 
	{
		manager.deriveRelations();
	}
	
	public void verifyModel() 
	{
		manager.validateCurrentModel();
	}
	
	public void verifyModelFile()
	{
		manager.verifyCurrentModelFile();
	}
	
	public void generateOwlSettings() 
	{
		manager.generateOwlSettings();
	}
	
	public void generateOwl() 
	{
		manager.generateOwl();
	}
	
	public void generateSbvr()
	{
		manager.generateSbvr();
	}
	
	public void generateText()
	{
		manager.generateText();
	}
	
	/**
	 * Displays the grid depending on the selection state of the menu item.
	 */

	public void showGrid() {
		manager.getCurrentDiagramEditor().showGrid(getMenuManager().isSelected("SHOW_GRID"));
		manager.getCurrentDiagramEditor().redraw();
	}

	/**
	 * Returns the application's menu manager.
	 * @return the menu manager
	 */
	private MainMenu getMenuManager() {
		return manager.getMainMenu();
	}

	/**
	 * Activates snapping depending on the selection state of the menu item.
	 */
	public void snapToGrid() {
		manager.getCurrentDiagramEditor().snapToGrid(getMenuManager().isSelected("SNAP_TO_GRID"));
	}
}
