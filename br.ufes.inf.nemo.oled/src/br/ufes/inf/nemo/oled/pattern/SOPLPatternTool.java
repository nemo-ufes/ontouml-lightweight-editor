package br.ufes.inf.nemo.oled.pattern;

import java.util.concurrent.TimeUnit;

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
import br.ufes.inf.nemo.soplpattern.impl.EntryPoint;
import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

/**
 * @author Paulo H. Araujo da Silva
 */
public class SOPLPatternTool {
	
	protected static SOPLPattern pm = null;	
	protected static DiagramManager dm = null;	
	
	public SOPLPatternTool(){
	
	}
	
	public static Fix tryToRun(DiagramManager diagramManager, ElementType elem,double x,double y){
				
		//SOPLPattern pm = null;	
		//DiagramManager dm = null;		
				
		
		switch (elem) {	
		
		case SOFFERING:
			System.out.println("SOFFERING");
			pm = new EntryPoint(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y, 1);
			break;
		case SAGREEMENT:
			System.out.println("SAGREEMENT");
			pm = new EntryPoint(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y, 2);
			break;
		default:
			break;
			
		}
	
		pm.runPattern(diagramManager); 
		Fix fix = null;
//		fix = pm.getFix();
//		diagramManager.updateOLED(fix);		
		
		return fix;

	}	
	
	public static void runPattern(final DiagramManager diagramManager,final ElementType elementType, final double x, final double y)  {
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
				
					SOPLPatternTool.tryToRun(diagramManager, elementType, x, y);
								
				}
			});
		}else{
			SOPLPatternTool.tryToRun(diagramManager, elementType, x, y);						
		}
	}
	
	public static void updateOLED(Fix fix){
		
		//Fix fix = null;		
		dm.updateOLED(fix);
		
		//return fix;
	 }
	
//	public SOPLPattern getPm() {
//		return pm;
//	}
//
//	public void setPm(SOPLPattern pm) {
//		this.pm = pm;
//	}

	public DiagramManager getDm() {
		return dm;
	}

	public static void setDm(DiagramManager diagramManager) {
		dm = diagramManager;
	}
	
	}