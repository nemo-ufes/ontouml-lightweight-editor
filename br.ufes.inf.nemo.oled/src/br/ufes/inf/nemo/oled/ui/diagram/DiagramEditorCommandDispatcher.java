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
package br.ufes.inf.nemo.oled.ui.diagram;

import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.AppMenu;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.dialog.AutoCompletionDialog;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.oled.model.RelationEndType;
import br.ufes.inf.nemo.oled.model.RelationType;
import br.ufes.inf.nemo.oled.util.MethodCall;
import br.ufes.inf.nemo.oled.validator.antipattern.AntiPatternSearchDialog;

/**
 * This class receives BaseEditor related AppCommands and dispatches them to
 * the right places. This offloads editor related commands from the
 * AppFrame object, while AppFrame handles commands on a global level,
 * DiagramEditorCommandDispatcher handles it on the level of the current editor.
 *
 * @author Wei-ju Wu, John Guerson 
 */
public class DiagramEditorCommandDispatcher implements AppCommandListener {
	
	private AppFrame frame;
	private DiagramManager manager;
	private Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();

	/**
	 * Constructor.
	 * @param aFrame the application frame
	 */
	public DiagramEditorCommandDispatcher(DiagramManager manager, AppFrame frame) {
		this.manager = manager;
		initSelectorMap();
		this.frame = frame;
	}

	/**
	 * Initializes the selector map.
	 */
	private void initSelectorMap() {
		try {
			selectorMap.put("SELECT_MODE", new MethodCall(
					DiagramEditor.class.getMethod("setSelectionMode")));

			selectorMap.put("REDO", new MethodCall(
					getClass().getMethod("redo")));

			selectorMap.put("FIND", new MethodCall(
					getClass().getMethod("find")));
			
			selectorMap.put("STATISTICS", new MethodCall(
					getClass().getMethod("collectStatistics")));
			
			selectorMap.put("UNDO", new MethodCall(
					getClass().getMethod("undo")));
			
			selectorMap.put("REDRAW", new MethodCall(
					DiagramEditor.class.getMethod("redraw")));

			selectorMap.put("SELECT_ALL", new MethodCall(
					DiagramEditor.class.getMethod("selectAll")));

			selectorMap.put("BRING_TO_FRONT", new MethodCall(
					DiagramEditor.class.getMethod("bringToFront")));

			selectorMap.put("PUT_TO_BACK", new MethodCall(
					DiagramEditor.class.getMethod("putToBack")));

			selectorMap.put("EDIT_PROPERTIES", new MethodCall(
					DiagramEditor.class.getMethod("editProperties")));

			selectorMap.put("DELETE", new MethodCall(
					DiagramEditor.class.getMethod("deleteSelection")));

			selectorMap.put("EXCLUDE", new MethodCall(
					DiagramEditor.class.getMethod("excludeSelection")));

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
			
			selectorMap.put("CREATE_ENUMERATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.ENUMERATION));

			selectorMap.put("CREATE_PRIMITIVETYPE", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.PRIMITIVETYPE));
			
			selectorMap.put("CREATE_PERCEIVABLEQUALITY", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.PERCEIVABLEQUALITY));
			
			selectorMap.put("CREATE_NONPERCEIVABLEQUALITY", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.NONPERCEIVABLEQUALITY));
			
			selectorMap.put("CREATE_NOMINALQUALITY", new MethodCall(
					DiagramEditor.class.getMethod("setCreationMode", ElementType.class),
					ElementType.NOMINALQUALITY));
			
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

			selectorMap.put("CREATE_STRUCTURATION", new MethodCall(
					DiagramEditor.class.getMethod("setCreateConnectionMode",
							RelationType.class), RelationType.STRUCTURATION));
			
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
					DiagramEditor.class.getMethod("toDirect")));

			selectorMap.put("DIRECT_TO_RECT", new MethodCall(
					DiagramEditor.class.getMethod("toRectilinear")));

			selectorMap.put("TREE_STYLE_VERTICAL", new MethodCall(
					DiagramEditor.class.getMethod("toTreeStyleVertical")));
			
			selectorMap.put("TREE_STYLE_HORIZONTAL", new MethodCall(
					DiagramEditor.class.getMethod("toTreeStyleHorizontal")));
			
			selectorMap.put("NAVIGABLE_TO_SOURCE", new MethodCall(
					DiagramEditor.class.getMethod("setNavigability", RelationEndType.class),
					RelationEndType.SOURCE));

			selectorMap.put("NAVIGABLE_TO_TARGET", new MethodCall(
					DiagramEditor.class.getMethod("setNavigability", RelationEndType.class),
					RelationEndType.TARGET));

			// Self-calls

			selectorMap.put("CREATE_DERIVATION_BY_UNION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationMode")));

			selectorMap.put("CREATE_DERIVATION_BY_EXCLUSION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationModeEx")));
			
			selectorMap.put("CREATE_DERIVATION_BY_SPECIALIZATION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationModeSpecialization")));
			
			selectorMap.put("CREATE_DERIVATION_BY_INTERSECTION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationModeIntersection")));
			
			selectorMap.put("CREATE_DERIVATION_BY_PAST_SPECIALIZATION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationModePastSpecialization")));
			
			selectorMap.put("CREATE_DERIVATION_BY_PARTICIPATION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternCreationModeParticipation")));

			selectorMap.put("SHOW_GRID", new MethodCall(
					getClass().getMethod("showGrid")));

			selectorMap.put("TOOLBOX", new MethodCall(
					getClass().getMethod("showToolbox")));
			
			selectorMap.put("BOTTOMVIEW", new MethodCall(
					getClass().getMethod("showBottomView")));
						
			selectorMap.put("BROWSER", new MethodCall(
					getClass().getMethod("showBrowser")));
			
			selectorMap.put("ZOOM_IN", new MethodCall(
					getClass().getMethod("zoomIn"))
					);

			selectorMap.put("ZOOM_OUT", new MethodCall(
					getClass().getMethod("zoomOut"))
					);
			
			selectorMap.put("SNAP_TO_GRID", new MethodCall(
					getClass().getMethod("snapToGrid")));

			selectorMap.put("PARSE_OCL", new MethodCall(
					getClass().getMethod("parseOCL")));

			selectorMap.put("AUTO_SELECTION", new MethodCall(
					getClass().getMethod("autoComplete")));
			
			selectorMap.put("VERIFY_MODEL", new MethodCall(
					getClass().getMethod("verifyModel")));
			
			selectorMap.put("GENERATE_ALLOY", new MethodCall(
					getClass().getMethod("generateAlloy")));

			selectorMap.put("ANTIPATTERN", new MethodCall(
					getClass().getMethod("manageAntiPatterns")));

			selectorMap.put("PART_WHOLE_VALIDATION", new MethodCall(
					getClass().getMethod("validatesParthood")));
			
			selectorMap.put("GENERATE_OWL_SETTINGS", new MethodCall(
					getClass().getMethod("generateOwl")));

			selectorMap.put("GENERATE_SBVR", new MethodCall(
					getClass().getMethod("generateSbvr")));

			selectorMap.put("GENERATE_TEXT", new MethodCall(
					getClass().getMethod("callGlossary")));

			selectorMap.put("DERIVERELATIONS", new MethodCall(
					getClass().getMethod("deriveRelations")));

			selectorMap.put("DERIVED_BY_UNION", new MethodCall(
					getClass().getMethod("derivedByUnion")));

			selectorMap.put("DERIVED_BY_EXCLUSION", new MethodCall(
					getClass().getMethod("derivedByExclusion")));
			
			selectorMap.put("DERIVED_BY_SPECIALIZATION", new MethodCall(
					getClass().getMethod("derivedBySpecialization")));
			
			selectorMap.put("DERIVED_BY_INTERSECTION", new MethodCall(
					getClass().getMethod("derivedByIntersection")));
			
			selectorMap.put("DERIVED_BY_PAST_SPECIALIZATION", new MethodCall(
					getClass().getMethod("derivedByPastSpecialization")));
			
			selectorMap.put("DERIVED_BY_PARTICIPATION", new MethodCall(
					getClass().getMethod("derivedByParticipation")));

			selectorMap.put("LANGUAGE_GENERALIZATION_SPECIALIZATION", new MethodCall(
					getClass().getMethod("runPatternByMenu",ElementType.class),ElementType.GENERALIZATIONSPECIALIZATION));
			
			selectorMap.put("LANGUAGE_PARTITION_PATTERN", new MethodCall(
					getClass().getMethod("runPatternByMenu",ElementType.class),ElementType.PARTITIONPATTERN));
			
			selectorMap.put("ADD_SUPERTYPE", new MethodCall(
					getClass().getMethod("runPatternByMenu",ElementType.class),ElementType.ADDSUPERTYPE));
			
			selectorMap.put("ADD_SUBTYPE", new MethodCall(
					getClass().getMethod("runPatternByMenu",ElementType.class),ElementType.ADDSUBTYPE));
			
			selectorMap.put("CREATE_GEN_SET", new MethodCall(
					DiagramEditor.class.getMethod("addGeneralizationSet")));
			selectorMap.put("DELETE_GEN_SET", new MethodCall(
					DiagramEditor.class.getMethod("deleteGeneralizationSet")));

			selectorMap.put("PATTERN_MIXIN_PATTERN", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_MIXIN_PATTERN));
			
			selectorMap.put("PATTERN_MIXIN_PATTERN_WITH_SUBKIND", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_MIXIN_PATTERN_WITH_SUBKIND));
			
			selectorMap.put("PATTERN_PHASE_PARTITION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_PHASE_PARTITION));

			selectorMap.put("PATTERN_SUBKIND_PARTITION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_SUBKIND_PARTITION));
			
			selectorMap.put("PATTERN_ROLE_PARTITION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_ROLE_PARTITION));
			
			selectorMap.put("PATTERN_SUBSTANCE_SORTAL_PARTITION", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_SUBSTANCE_SORTAL_PARTITION));
			
			selectorMap.put("PATTERN_COMPLETER", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_COMPLETER));
			
			selectorMap.put("PATTERN_ROLEMIXIN_PATTERN", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_ROLEMIXIN_PATTERN));
			
			selectorMap.put("PATTERN_RELATOR", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.PATTERN_RELATOR));

			selectorMap.put("DOMAIN_PATTERN", new MethodCall(
					DiagramEditor.class.getMethod("setPatternMode",ElementType.class),ElementType.DOMAIN_PATTERN));

		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		}
	}

	public void runPatternByMenu(ElementType type)
	{
		if (manager.isProjectLoaded()==false) return;
		manager.runPattern(type, 0, 0);
	}
	
	@Override
	public void handleCommand(String command) {
		MethodCall methodcall = selectorMap.get(command);
		if (methodcall != null) {
			Object target = manager.getCurrentDiagramEditor();			 
			// in order to catch the self calling methods
			if (methodcall.getMethod().getDeclaringClass() == DiagramEditorCommandDispatcher.class) {
				target = this;
			}
			if(target != null) methodcall.call(target);			  
		} 
	}

	public void undo()
	{		
		if (manager.isProjectLoaded()==false) return;
		if(manager.getCurrentDiagramEditor()!=null){
			if(manager.getCurrentDiagramEditor().canUndo()){
				manager.getCurrentDiagramEditor().undo();
			}else{				
				manager.getFrame().showInformationMessageDialog("Cannot Undo", "No other action to be undone.\n\n");
			}
		}
	}

	public void redo()
	{
		if (manager.isProjectLoaded()==false) return;

		if(manager.getCurrentDiagramEditor()!=null) 
		{			
			if(manager.getCurrentDiagramEditor().canRedo()){
				manager.getCurrentDiagramEditor().redo();
			}else{
				manager.getFrame().showInformationMessageDialog("Cannot Redo", "No other action to be redone.\n\n");
			}
		}
	}

	public void find()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.getFrame().getDiagramManager().addFinderPanel(manager,true);		
	}

	public void collectStatistics()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.getFrame().getDiagramManager().addStatisticsPanel(frame.getInfoManager(),true);
	}
	
	public void showGrid() {
		if (manager.isProjectLoaded()==false) return;

		manager.getCurrentDiagramEditor().showGrid(getMenuManager().isSelected("SHOW_GRID"));
		manager.getCurrentWrapper().getToolBar().update();
	}

	public void showToolbox() {
		manager.getFrame().showToolBox();
	}
	
	public void showBottomView(){
		manager.getFrame().showBottomView();
	}
	
	public void showBrowser() {
		manager.getFrame().showProjectBrowser();
	}
	
	public void zoomOut()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.getCurrentDiagramEditor().zoomOut();
		manager.getCurrentWrapper().getToolBar().update();
	}
	
	public void zoomIn()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.getCurrentDiagramEditor().zoomIn();
		manager.getCurrentWrapper().getToolBar().update();
	}
	
	public void callGlossary()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.workingOnlyWithChecked();
		manager.callGlossary();
	}
	
	public void generateSbvr()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.workingOnlyWithChecked();
		OntoUMLParser refparser = frame.getBrowserManager().getProjectBrowser().getParser();
		manager.generateSbvr((RefOntoUML.Model)refparser.createModelFromSelections(new Copier()));
	}
		
	public void generateAlloy()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.workingOnlyWithChecked();		
		manager.openAlloySettings();
	}

	public void generateOwl() 
	{
		if (manager.isProjectLoaded()==false) return;
		manager.workingOnlyWithChecked();
		manager.openOwlSettings();
	}
	
	public void verifyModel() 
	{
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				if (manager.isProjectLoaded()==false) return;
				manager.verifyModelSyntactically();
				
			}
		});
	}
	
	public void parseOCL()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.parseConstraints(true);		
	}
	public void derivedByUnion()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveByUnion();
	}

	public void derivedByExclusion()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveByExclusion();
	}
	
	public void derivedByIntersection()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveByIntersection();
	}

	public void derivedBySpecialization()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveBySpecialization();
	}

	public void derivedByPastSpecialization()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveByPastSpecialization();
	}

	public void derivedByParticipation()
	{
		if (manager.isProjectLoaded()==false) return;
		manager.deriveByParticipation();
	}


	public void manageAntiPatterns()
	{			
		if (manager.isProjectLoaded()==false) return;
		
		Main.printOutLine("Opening anti-pattern dialog...");
		AntiPatternSearchDialog.open(manager.getFrame());		
	}
	
	public void autoComplete()
	{
		if (manager.isProjectLoaded()==false) return;
		AutoCompletionDialog.open(manager.getFrame(),manager.getCurrentProject());
	}

	public void deriveRelations() 
	{
		if (manager.isProjectLoaded()==false) return;
		//manager.deriveRelations();
	}

	public void validatesParthood()
	{
		if (manager.isProjectLoaded()==false) return;		
		manager.workingOnlyWithChecked();
		manager.validatesParthood();
	}
	
	/**
	 * Returns the application's menu manager.
	 * @return the menu manager
	 */
	private AppMenu getMenuManager() {
		return manager.getMainMenu();
	}

	/**
	 * Activates snapping depending on the selection state of the menu item.
	 */
	public void snapToGrid() {
		manager.getCurrentDiagramEditor().snapToGrid(getMenuManager().isSelected("SNAP_TO_GRID"));
	}
}
