package br.ufes.inf.nemo.oled.pattern;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleter;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.impl.MixinPattern;
import br.ufes.inf.nemo.pattern.impl.MixinPatternWithSubkind;
import br.ufes.inf.nemo.pattern.impl.PhasePartition;
import br.ufes.inf.nemo.pattern.impl.RelatorPattern;
import br.ufes.inf.nemo.pattern.impl.RoleMixinPattern;
import br.ufes.inf.nemo.pattern.impl.RolePartition;
import br.ufes.inf.nemo.pattern.impl.SubkindPartition;
import br.ufes.inf.nemo.pattern.impl.SubstanceSortalPartition;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	/**
	 * Public methods 
	 */

	@SuppressWarnings("incomplete-switch")
	public static Fix tryToRun(DiagramManager diagramManager, ElementType elem,double x,double y){
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
		case PATTERN_ROLEMIXIN_PATTERN:
			pm = new RoleMixinPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_ROLE_PARTITION:
			pm = new RolePartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_SUBKIND_PARTITION:
			pm = new SubkindPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		case PATTERN_SUBSTANCE_SORTAL_PARTITION:
			pm = new SubstanceSortalPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		}

		Fix fix = null;
		if(pm != null){
			pm.runPattern();
			if(pm.canGetFix())
				fix = pm.getFix();
		}else{
			fix = runModelCompleter(diagramManager, x, y);
		}
		
		return fix;
	}
	
	private static Fix runModelCompleter(final DiagramManager diagramManager,final double x, final double y) {
		ModelCompleter mci = ModelCompleter.createDialog(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
		mci.open();
		Fix fix = mci.getFix();
		if(fix == null)
			return null;
		diagramManager.updateOLED(fix);
		return runModelCompleter(diagramManager, x, y);
	}

	public static void runPattern(final DiagramManager diagramManager,final ElementType elementType, final double x, final double y) {
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
					Fix fix = PatternTool.tryToRun(diagramManager, elementType, x, y);
					diagramManager.updateOLED(fix);					
				}
			});
		}else{
			Fix fix = PatternTool.tryToRun(diagramManager, elementType, x, y);			
			diagramManager.updateOLED(fix);						
		}
	}
}