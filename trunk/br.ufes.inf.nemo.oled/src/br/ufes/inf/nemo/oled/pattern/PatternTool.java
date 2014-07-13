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

import java.util.ArrayList;

import javax.swing.JFrame;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import br.ufes.inf.nemo.assistant.pattern.window.ImagePanel;
import br.ufes.inf.nemo.assistant.pattern.window.ImagePanel.PatternType;
import br.ufes.inf.nemo.assistant.pattern.window.PatternAbstractWindowAssistant;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.RelatorCreation;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.RoleMixinPattern;
import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.SubkindCreation;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;

/**
 * @author Victor Amorim
 */
public class PatternTool {
	
	private static OutcomeFixer outcomeFixer;
	private static Fix fix = new Fix();

	private static final int horizontalDistance = 150;
	private static final int verticalDistance = 330;

	@SuppressWarnings("unused")
	private static Classifier _general;

	/**
	 * Public methods 
	 */

	public static Fix createSubkindPattern(JFrame frame, UmlProject project, double x, double y) {
		SubkindCreation subkindCreation = new SubkindCreation(ProjectBrowser.frame.getBrowserManager().getProjectBrowser().getParser());
		ImagePanel imagePanel = new ImagePanel(PatternType.SubkindCreation);

		PatternAbstractWindowAssistant window = new PatternAbstractWindowAssistant(frame, x, y, subkindCreation, imagePanel);
		window.setVisible(true);
		window.setLocationRelativeTo(frame);
		return window.getFix();
	}

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
	
	
	
	
	
	
	
	
	
	public static Fix createSubkindPartitionPattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);
		createPartition(root, x, y, ClassStereotype.KIND, ClassStereotype.SUBKIND, 2);
		return fix;
	}

	public static Fix createPhasePartitionPattern(JFrame frame,UmlProject project, double x, double y) {
		fix = new Fix();
//		outcomeFixer = new OutcomeFixer(root);
//		createPartition(root, x, y, ClassStereotype.KIND, ClassStereotype.PHASE, 2);
//		outcomeFixer.createAttribute(_general, "attribute", ClassStereotype.PRIMITIVETYPE, "Integer");
		return fix;
	}

	public static Fix createRolePattern(Package root, double x, double y) {
		fix = new Fix();
		outcomeFixer = new OutcomeFixer(root);

		Classifier general = createClassifier(root, ClassStereotype.KIND, "General", x, y);
		Classifier specific = createClassifier(root, ClassStereotype.ROLE, "Specific", x, y+horizontalDistance);

		fix.addAll(outcomeFixer.createGeneralization(specific, general));

		return fix;
	}

	/**
	 * Private methods
	 * */

	private static Classifier createClassifier(Package root, ClassStereotype stereotype, String name, double x, double y){
		RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(stereotype);
		classifier.setName(name);
		root.getPackagedElement().add(classifier);
		fix.includeAdded(classifier,x,y);

		return classifier;
	}

	private static void createPartition(Package root, double x, double y,ClassStereotype generalType, ClassStereotype specificsType, int specificQuant){
		outcomeFixer = new OutcomeFixer(root);
		ArrayList<Generalization> generalizationList = new ArrayList<>();

		Classifier general = createClassifier(root, generalType, "General", (x+(specificQuant/2*verticalDistance)/3)-60, y);
		_general = general;

		for (int i = 0; i < specificQuant; i++) {
			Classifier specific = createClassifier(root, specificsType, "Specific"+i, x+(i*verticalDistance)/3, y+horizontalDistance);
			Fix _fix = outcomeFixer.createGeneralization(specific, general);
			Generalization generalization = (Generalization) _fix.getAdded().get(_fix.getAdded().size()-1);
			generalizationList.add(generalization);

			fix.addAll(_fix);
		}
		fix.addAll(outcomeFixer.createGeneralizationSet(generalizationList, true, true, "partition"));
	}

}
