package br.ufes.inf.nemo.oled.pattern;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleter;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.impl.generalization.FOP_GENERALIZATION_CATEGORY;
import br.ufes.inf.nemo.pattern.impl.generalization.FOP_GENERALIZATION_MIXIN;
import br.ufes.inf.nemo.pattern.impl.generalization.FOP_GENERALIZATION_ROLEMIXIN;
import br.ufes.inf.nemo.pattern.impl.other.FOP_GENERAL_RELATOR;
import br.ufes.inf.nemo.pattern.impl.other.FOP_GENERIC_RELATOR;
import br.ufes.inf.nemo.pattern.impl.other.FOP_MULTIPLE_GENERIC_RELATOR;
import br.ufes.inf.nemo.pattern.impl.other.FOP_PRINCIPLE_OF_IDENTITY;
import br.ufes.inf.nemo.pattern.impl.other.FOP_ROLEMIXIN_DEPENDENCE;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_CATEGORY;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_MODE;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_PHASE;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_RELATOR;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_ROLE;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_ROLEMIXIN;
import br.ufes.inf.nemo.pattern.impl.partition.FOP_PARTITION_SUBKIND;
import br.ufes.inf.nemo.pattern.impl.relation.FOP_RELATION_CHARACTERIZATION;
import br.ufes.inf.nemo.pattern.impl.relation.FOP_RELATION_FORMAL;
import br.ufes.inf.nemo.pattern.ui.manager.ModelCompleterManager;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	@SuppressWarnings("incomplete-switch")
	private static Fix tryToRun(DiagramManager diagramManager, ElementType elem,double x,double y){
		AbstractPattern pm = null;

		switch (elem) {
		case FOP_PARTITION_PHASE:
			pm = new FOP_PARTITION_PHASE(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_ROLEMIXIN:
			pm = new FOP_PARTITION_ROLEMIXIN(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_ROLE:
			pm = new FOP_PARTITION_ROLE(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_SUBKIND:
			pm = new FOP_PARTITION_SUBKIND(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_CATEGORY:
			pm = new FOP_PARTITION_CATEGORY(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_RELATOR:
			pm = new FOP_PARTITION_RELATOR(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PARTITION_MODE:
			pm = new FOP_PARTITION_MODE(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_GENERALIZATION_CATEGORY:
			pm = new FOP_GENERALIZATION_CATEGORY(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_GENERALIZATION_ROLEMIXIN:
			pm = new FOP_GENERALIZATION_ROLEMIXIN(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_GENERALIZATION_MIXIN:
			pm = new FOP_GENERALIZATION_MIXIN(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_PRINCIPLE_OF_IDENTITY:
			pm = new FOP_PRINCIPLE_OF_IDENTITY(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_GENERAL_RELATOR:
			pm = new FOP_GENERAL_RELATOR(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_GENERIC_RELATOR:
			pm = new FOP_GENERIC_RELATOR(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_ROLEMIXIN_DEPENDENCE:
			pm = new FOP_ROLEMIXIN_DEPENDENCE(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_MULTIPLE_GENERIC_RELATOR:
			pm = new FOP_MULTIPLE_GENERIC_RELATOR(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_RELATION_CHARACTERIZATION:
			pm = new FOP_RELATION_CHARACTERIZATION(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case FOP_RELATION_FORMAL:
			pm = new FOP_RELATION_FORMAL(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
			
		}

		Fix fix = null;
		if(pm != null){
			pm.runPattern();
			if(pm.canGetFix()){
				fix = pm.getFix();
				diagramManager.updateOLED(fix);
				_runModelCompleter(diagramManager, x, y,false);
			}
		}else{
			if(elem == ElementType.PATTERN_COMPLETER)
				_runModelCompleter(diagramManager, x, y);
		}

		return fix;
	}

	/*
	 * Called when clicked at Toolbar
	 * */
	private static void _runModelCompleter(final DiagramManager diagramManager,final double x, final double y) {
		try{
			ModelCompleter mcw = ModelCompleter.createDialog();
			ModelCompleterManager mcm = new ModelCompleterManager(ProjectBrowser.frame.getProjectBrowser().getParser(), mcw, x, y);

			mcm.runAnalysis();
			if(mcw.isEmpty()){
				mcw.showMessageBox();
				return;
			}

			mcw.open();

			if(mcw.wasClosed())
				return;

			Fix fix = mcw.getFix();
			diagramManager.updateOLED(fix);

			_runModelCompleter(diagramManager, x, y);
		}catch(Exception e){
			//do not show the stacktrace, everything is ok...
			e.printStackTrace();
		}
	}

	private static void _runModelCompleter(final DiagramManager diagramManager,final double x, final double y, boolean showCompleteMessage) {
		try{
			ModelCompleter mcw = ModelCompleter.createDialog(showCompleteMessage);
			ModelCompleterManager mcm = new ModelCompleterManager(ProjectBrowser.frame.getProjectBrowser().getParser(), mcw, x, y);

			mcm.runAnalysis();
			if(mcw.isEmpty())
				return;

			mcw.open();

			if(mcw.wasClosed())
				return;

			Fix fix = mcw.getFix();
			diagramManager.updateOLED(fix);

			_runModelCompleter(diagramManager, x, y, showCompleteMessage);
		}catch(Exception e){
			//do not show the stacktrace, everything is ok...
			e.printStackTrace();
		}
	}

	public static void runPattern(final DiagramManager diagramManager,final ElementType elementType, final double x, final double y) {
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					PatternTool.tryToRun(diagramManager, elementType, x, y);					
				}
			});
		}else{
			PatternTool.tryToRun(diagramManager, elementType, x, y);						
		}
	}
	
	/*
	 * Called when triggered by a new class.
	 * The ModelCompleter feature is ON 
	 * */
	public static void runModelCompleter(final DiagramManager diagramManager, final Classifier elem, final double x, final double y) {
		//Needs to rename the class
		diagramManager.renameElement(elem);

		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					_runModelCompleter(diagramManager, x, y-75.0,false);
				}
			});
		}else{
			_runModelCompleter(diagramManager, x, y-75.0,false);			
		}
	} 
}