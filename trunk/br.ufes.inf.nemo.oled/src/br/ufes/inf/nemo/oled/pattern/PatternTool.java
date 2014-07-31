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
package br.ufes.inf.nemo.oled.pattern;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.MixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.assistant.pattern.window.ImagePanel;
import br.ufes.inf.nemo.assistant.pattern.window.ImagePanel.PatternType;
import br.ufes.inf.nemo.assistant.pattern.window.PatternAbstractWindowAssistant;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.design.AddSupertype;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.design.RelatorCreation;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.design.RoleMixinPattern;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language.GeneralizationAndSpecializationPattern;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language.PartitionPattern;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.language.PrincipleIdentiy;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	/**
	 * Public methods 
	 */

	public static Fix createRelatorPattern(JFrame frame, UmlProject project, double x, double y) {
		RelatorCreation relatorCreation = new RelatorCreation(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
		ImagePanel imagePanel = new ImagePanel(PatternType.RelatorCreation);

		PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, relatorCreation, imagePanel);
		window.setVisible(true);
		window.setLocationRelativeTo(frame);
		return window.getFix();
	}
	
	public static Fix createRoleMixinPattern(JFrame frame, UmlProject project, double x, double y) {
		RoleMixinPattern roleMixinPattern = new RoleMixinPattern(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
		ImagePanel imagePanel = new ImagePanel(PatternType.RoleMixinPattern);

		PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, roleMixinPattern, imagePanel);
		window.setVisible(true);
		window.setLocationRelativeTo(frame);
		return window.getFix();
	}	
	
	public static Fix principleIdentity(JFrame frame, UmlProject currentProject, double x, double y) {
		PrincipleIdentiy pattern = new PrincipleIdentiy(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
		ImagePanel imagePanel = new ImagePanel(PatternType.PrincipleIdentity);

		PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
		window.setVisible(true);
		window.setLocationRelativeTo(frame);
		return window.getFix();
	}
	
	public static Fix addSubtype(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
		return PatternTool.generalizationAndSpecialization(frame, currentProject, selectedElements);
	}

	public static Fix generalizationAndSpecialization(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
		ImagePanel imagePanel = null;
		GeneralizationAndSpecializationPattern pattern = null;
		double x, y;
		
		if (selectedElements.size() == 1){
			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
			x = selectedElement.getAbsoluteX1();
			y = selectedElement.getAbsoluteY1();
					
			Classifier selectedClassifier = selectedElement.getClassifier();
			if(selectedClassifier instanceof SubKind){
				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Sukind);
			}else if(selectedClassifier instanceof Role){
				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Role);
			}else if(selectedClassifier instanceof SubstanceSortal){
				imagePanel = new ImagePanel(PatternType.PrincipleIdentity);
			}else if(selectedClassifier instanceof Category){
				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Category);
			}else if(selectedClassifier instanceof RoleMixin){
				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_RoleMixin);
			}else if(selectedClassifier instanceof MixinClass){
				imagePanel = new ImagePanel(PatternType.GeneralizationAndSpecialization_Mixin);
			}else{
				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
				return null;		
			}
			
			pattern = new GeneralizationAndSpecializationPattern(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser(), selectedClassifier);
			
			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
			window.setVisible(true);
			window.setLocationRelativeTo(frame);
			return window.getFix();
		}
		
		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
		return null;
	}
	
	
	public static Fix partitionPattern(JFrame frame, UmlProject currentProject, List<DiagramElement> selectedElements) {
		ImagePanel imagePanel = null;
		PartitionPattern pattern = null;
		double x, y;
		
		if (selectedElements.size() == 1){
			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
			x = selectedElement.getAbsoluteX1();
			y = selectedElement.getAbsoluteY1();
					
			Classifier selectedClassifier = selectedElement.getClassifier();
			if(selectedClassifier instanceof RigidSortalClass){
				imagePanel = new ImagePanel(PatternType.PartitionPattern_Rigid);
			}else if(selectedClassifier instanceof SortalClass){
				imagePanel = new ImagePanel(PatternType.PartitionPattern_Sortal);
			}else{
				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
				return null;		
			}
			
			pattern = new PartitionPattern(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser(), selectedClassifier);
			
			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
			window.setVisible(true);
			window.setLocationRelativeTo(frame);
			return window.getFix();
		}
		
		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
		return null;
	}

	public static Fix addSupertype(AppFrame frame, UmlProject currentProject,List<DiagramElement> selectedElements) {
		ImagePanel imagePanel = null;
		ClassSelectionPanel pattern = null;
		double x, y;
		
		if (selectedElements.size() == 1){
			ClassElement selectedElement = (ClassElement) selectedElements.get(0);
			x = selectedElement.getAbsoluteX1();
			y = selectedElement.getAbsoluteY1();
					
			Classifier selectedClassifier = selectedElement.getClassifier();
			if(selectedClassifier instanceof SubstanceSortal){
				imagePanel = new ImagePanel(PatternType.AddSupertype_SubstanceSortal);
			}else if(selectedClassifier instanceof SubKind){
				imagePanel = new ImagePanel(PatternType.AddSupertype_Subkind);
			}else if(selectedClassifier instanceof Role){
				imagePanel = new ImagePanel(PatternType.AddSupertype_Role);
			}else{
				JOptionPane.showMessageDialog(null, "Pattern do not applied to "+UtilAssistant.getStringRepresentationStereotype(selectedClassifier)+" stereotype");
				return null;		
			}
			
			pattern = new AddSupertype(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser(), selectedClassifier);
			
			PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, pattern, imagePanel);
			window.setVisible(true);
			window.setLocationRelativeTo(frame);
			return window.getFix();
		}
		
		JOptionPane.showMessageDialog(null, "Pattern do not applied to multiple selections");
		return null;
	}
}
