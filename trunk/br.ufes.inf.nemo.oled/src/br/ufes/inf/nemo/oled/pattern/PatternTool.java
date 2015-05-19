package br.ufes.inf.nemo.oled.pattern;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleter;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.impl.AntiRigidWeakSupplementation;
import br.ufes.inf.nemo.pattern.impl.CategoryPattern;
import br.ufes.inf.nemo.pattern.impl.CharacterizationPattern;
import br.ufes.inf.nemo.pattern.impl.CollectivePartition;
import br.ufes.inf.nemo.pattern.impl.GenericMultipleRelator;
import br.ufes.inf.nemo.pattern.impl.KindPartition;
import br.ufes.inf.nemo.pattern.impl.MixinPattern;
import br.ufes.inf.nemo.pattern.impl.MixinPatternWithSubkind;
import br.ufes.inf.nemo.pattern.impl.PhasePartition;
import br.ufes.inf.nemo.pattern.impl.QuantityPartition;
import br.ufes.inf.nemo.pattern.impl.RelatorPattern;
import br.ufes.inf.nemo.pattern.impl.RigidWeakSupplementation;
import br.ufes.inf.nemo.pattern.impl.RoleMixinDependentPattern;
import br.ufes.inf.nemo.pattern.impl.RoleMixinPattern;
import br.ufes.inf.nemo.pattern.impl.RolePartition;
import br.ufes.inf.nemo.pattern.impl.SubkindPartition;
import br.ufes.inf.nemo.pattern.ui.manager.ModelCompleterManager;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	@SuppressWarnings("incomplete-switch")
	private static Fix tryToRun(DiagramManager diagramManager, ElementType elem,double x,double y){
		AbstractPattern pm = null;

		switch (elem) {
		case PATTERN_MIXIN_PATTERN:
			pm = new MixinPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y); 
			break;
		case PATTERN_MIXIN_PATTERN_WITH_SUBKIND:
			pm = new MixinPatternWithSubkind(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_PHASE_PARTITION:
			pm = new PhasePartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_RELATOR:
			pm = new RelatorPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_ROLEMIXIN:
			pm = new RoleMixinPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_ROLE_PARTITION:
			pm = new RolePartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_SUBKIND_PARTITION:
			pm = new SubkindPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case KIND_PARTITION:
			pm = new KindPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case COLLECTIVE_PARTITION:
			pm = new CollectivePartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case QUANTITY_PARTITION:
			pm = new QuantityPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case CATEGORY_PATTERN:
			pm = new CategoryPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case DEPENDENT_ROLEMIXIN:
			pm = new RoleMixinDependentPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case GENERIC_RELATOR:
			pm = new GenericMultipleRelator(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case CHARACTERIZATION_PATTERN:
			pm = new CharacterizationPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case RIGID_WEAK_SUPPLEMENTATION:
			pm = new RigidWeakSupplementation(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case ANTIRIGID_WEAK_SUPPLEMENTATION:
			pm = new AntiRigidWeakSupplementation(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
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