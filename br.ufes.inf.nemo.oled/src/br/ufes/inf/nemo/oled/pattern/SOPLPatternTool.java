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
 * @author Paulo H. Araujo da Silva
 */
public class SOPLPatternTool {
	
//	private SOPLPattern pm = null;	
//	private DiagramManager dm = null;	
	
	public SOPLPatternTool(){
	
	}
	
	public void tryToRun(DiagramManager diagramManager, ElementType elem,double x,double y){
		//AbstractPattern pm = null;
		SOPLPattern pm = null;	
		//DiagramManager dm = null;
		//setDm(diagramManager);
		
		switch (elem) {	
		
		case SODESCRIPTION:
			pm = new SODescription(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		default:
			break;
			
		}
		
		//Fix fix = null;
		if(pm != null){
			//pm.runPattern();
		}

	}	
//	
//	public void returnFix(){
//		
//		Fix fix = null;
//		fix = pm.getFix();
//		dm.updateOLED(fix);
//		
//		//return fix;
//	 }
	
//	public SOPLPattern getPm() {
//		return pm;
//	}
//
//	public void setPm(SOPLPattern pm) {
//		this.pm = pm;
//	}
//
//	public DiagramManager getDm() {
//		return dm;
//	}
//
//	public void setDm(DiagramManager dm) {
//		this.dm = dm;
//	}

	
	}