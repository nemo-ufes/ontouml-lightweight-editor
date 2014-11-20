package br.ufes.inf.nemo.oled.pattern;

import java.util.List;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.ElementType;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleterIdentity;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.impl.MixinPattern;
import br.ufes.inf.nemo.pattern.impl.MixinPatternWithSubkind;
import br.ufes.inf.nemo.pattern.impl.PhasePartition;
import br.ufes.inf.nemo.pattern.impl.RelatorPattern;
import br.ufes.inf.nemo.pattern.impl.RoleMixinPattern;
import br.ufes.inf.nemo.pattern.impl.RolePartition;
import br.ufes.inf.nemo.pattern.impl.SubkindPartition;
import br.ufes.inf.nemo.pattern.impl.SubstanceSortalPartition;
import br.ufes.inf.nemo.pattern.ui.manager.ModelCompleterManager;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	/**
	 * Public methods 
	 */

	@SuppressWarnings("incomplete-switch")
	public static Fix tryToRun(ElementType elem,double x,double y){
		AbstractPattern pm = null;
		ModelCompleterIdentity mci = null;

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
		case PATTERN_COMPLETER:
			mci = new ModelCompleterIdentity(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
			break;
		}
		Fix fix = null;
		if(pm != null){
			pm.runPattern();
			fix = pm.getFix();
		}
		
		if(mci != null){
			mci.open();
			fix = mci.getFix();
		}

		return fix;
	}


	@SuppressWarnings("incomplete-switch")
	public static Fix tryToRun(ElementType elem, List<DiagramElement> selectedElements) {
		Fix fix = null;

		switch (elem) {
		case ADDSUBTYPE:
			break;
		case ADDSUPERTYPE:
			break;
		case GENERALIZATIONSPECIALIZATION:
			break;
		case PATTERN_PRINCIPLE_IDENTITY:
			break;
		}

		return fix;
	}

	public static Fix solveIncompleteness() {
		Fix fix = null;
		ModelCompleterIdentity win = ModelCompleterManager.getCompleterIdentityWindow(ProjectBrowser.frame.getProjectBrowser().getParser());
		while(win != null){
			win.open();
			fix = win.getFix();

			if(fix == null)
				break;

			ProjectBrowser.frame.getDiagramManager().updateOLED(fix);
			win = ModelCompleterManager.getCompleterIdentityWindow(ProjectBrowser.frame.getProjectBrowser().getParser());
		}
		return fix;
	}

	//	public static Fix createRelatorPattern(JFrame frame, UmlProject project, double x, double y) {
	//		AbstractPattern pm = new RelatorPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
	//		pm.runPattern();
	//		return pm.getFix();
	//	}
	//
	//	public static Fix createRoleMixinPattern(JFrame frame, UmlProject project, double x, double y) {
	//		RoleMixinPattern roleMixinPattern = new RoleMixinPattern(ProjectBrowser.frame.getProjectBrowser().getParser());
	//		ImagePanel imagePanel = new ImagePanel(PatternType.RoleMixinPattern);
	//
	//		PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, roleMixinPattern, imagePanel);
	//		window.setVisible(true);
	//		window.setLocationRelativeTo(frame);
	//		return window.getFix();
	//	}	
	//
	//	public static Fix principleIdentity(JFrame frame, UmlProject currentProject, double x, double y) {
	//		AbstractPattern pm = new SubkindPartition(ProjectBrowser.frame.getProjectBrowser().getParser(), x, y);
	//		pm.runPattern();
	//		return pm.getFix();
	//	}
	//
	//	public static Fix addSubtype(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
	//		return PatternTool.generalizationAndSpecialization(frame, currentProject, selectedElements);
	//	}
	//
	//	public static Fix generalizationAndSpecialization(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
	//		ImagePanel imagePanel = null;
	//		GeneralizationAndSpecializationPattern pattern = null;
	//		double x, y;
	//
	//		if (selectedElements.size() == 1){
	//			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
	//			x = selectedElement.getAbsoluteX1();
	//			y = selectedElement.getAbsoluteY1();
	//
	//			Classifier selectedClassifier = selectedElement.getClassifier();
	//			if(selectedClassifier instanceof SubKind){
	//				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Sukind);
	//			}else if(selectedClassifier instanceof Role){
	//				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Role);
	//			}else if(selectedClassifier instanceof SubstanceSortal){
	//				imagePanel = new ImagePanel(PatternType.PrincipleIdentity);
	//			}else if(selectedClassifier instanceof Category){
	//				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Category);
	//			}else if(selectedClassifier instanceof RoleMixin){
	//				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_RoleMixin);
	//			}else if(selectedClassifier instanceof MixinClass){
	//				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Mixin);
	//			}else{
	//				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
	//				return null;		
	//			}
	//
	//			pattern = new GeneralizationAndSpecializationPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), selectedClassifier);
	//
	//			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
	//			window.setVisible(true);
	//			window.setLocationRelativeTo(frame);
	//			return window.getFix();
	//		}
	//
	//		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
	//		return null;
	//	}
	//
	//
	//	public static Fix partitionPattern(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
	//		ImagePanel imagePanel = null;
	//		PartitionPattern pattern = null;
	//		double x, y;
	//
	//		if (selectedElements.size() == 1){
	//			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
	//			x = selectedElement.getAbsoluteX1();
	//			y = selectedElement.getAbsoluteY1();
	//
	//			Classifier selectedClassifier = selectedElement.getClassifier();
	//			if(selectedClassifier instanceof RigidSortalClass){
	//				imagePanel = new ImagePanel(PatternType.PartitionPattern_Rigid);
	//			}else if(selectedClassifier instanceof SortalClass){
	//				imagePanel = new ImagePanel(PatternType.PartitionPattern_Sortal);
	//			}else{
	//				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
	//				return null;		
	//			}
	//
	//			pattern = new PartitionPattern(ProjectBrowser.frame.getProjectBrowser().getParser(), selectedClassifier);
	//
	//			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
	//			window.setVisible(true);
	//			window.setLocationRelativeTo(frame);
	//			return window.getFix();
	//		}
	//
	//		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
	//		return null;
	//	}
	//
	//	public static Fix addSupertype(AppFrame frame, UmlProject currentProject,List<DiagramElement> selectedElements) {
	//		ImagePanel imagePanel = null;
	//		ClassSelectionPanel pattern = null;
	//		double x, y;
	//
	//		if (selectedElements.size() == 1){
	//			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
	//			x = selectedElement.getAbsoluteX1();
	//			y = selectedElement.getAbsoluteY1();
	//
	//			Classifier selectedClassifier = selectedElement.getClassifier();
	//			if(selectedClassifier instanceof SubstanceSortal){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_SubstanceSortal);
	//			}else if(selectedClassifier instanceof SubKind){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_Subkind);
	//			}else if(selectedClassifier instanceof Role){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_Role);
	//			}else if(selectedClassifier instanceof Category){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_Category);
	//			}else if(selectedClassifier instanceof Mixin){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_Mixin);
	//			}else if(selectedClassifier instanceof RoleMixin){
	//				imagePanel = new ImagePanel(PatternType.AddSupertype_RoleMixin);
	//			}else{
	//				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
	//				return null;		
	//			}
	//
	//			pattern = new AddSupertype(ProjectBrowser.frame.getProjectBrowser().getParser(), selectedClassifier);
	//
	//			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
	//			window.setVisible(true);
	//			window.setLocationRelativeTo(frame);
	//			return window.getFix();
	//		}
	//
	//		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
	//		return null;
	//	}
}
