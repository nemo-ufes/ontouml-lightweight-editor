package br.ufes.inf.nemo.pattern.ui.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Relator;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleter;
import br.ufes.inf.nemo.pattern.impl.AbstractPattern;
import br.ufes.inf.nemo.pattern.impl.CategoryPattern;
import br.ufes.inf.nemo.pattern.impl.CharacterizationPattern;
import br.ufes.inf.nemo.pattern.impl.GenericMultipleRelator;
import br.ufes.inf.nemo.pattern.impl.MixinPattern;
import br.ufes.inf.nemo.pattern.impl.PhasePartition;
import br.ufes.inf.nemo.pattern.impl.PrincipleOfIdentity;
import br.ufes.inf.nemo.pattern.impl.RoleMixinDependentPattern;
import br.ufes.inf.nemo.pattern.impl.RoleMixinPattern;
import br.ufes.inf.nemo.pattern.impl.RolePartition;

public class ModelCompleterManager {

	private OntoUMLParser parser;
	private ModelCompleter window;
	private double x;
	private double y;
	private ArrayList<TableLine> tableLines;

	public ModelCompleterManager(OntoUMLParser parser, ModelCompleter window, double x, double y) {
		this.parser = parser;
		this.window = window;
		this.x = x;
		this.y = y;
	}

	public void runAnalysis(){
		tableLines = new ArrayList<TableLine>(); 

		checkIndentityPrinciple();
		checkRoleAndRelatorCompleteness();
		checkPhasePartition();
		checkRoleMixinDependence();
		checkCategoryCompleteness();
		checkMixinCompleteness();
		checkCharacterizationCompleteness();

		Collections.sort(tableLines, new Comparator<TableLine>() {
			@Override
			public int compare(TableLine o1, TableLine o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		for(TableLine tl : tableLines){
			window.addLine(tl.name, tl.stereotype, tl.action, tl.fop);
		}

		window.addTableListener();
	}

	private void checkCharacterizationCompleteness() {
		Set<? extends Classifier> modes = parser.getAllInstances(Mode.class);
		Set<Classifier> list = new HashSet<Classifier>();

		for(Classifier c: modes){
			ArrayList<Association> ass = new ArrayList<>();
			
			//search for direct characterization associations
			for(Association a : parser.getDirectAssociations(c)){
				if(a instanceof Characterization){
					ass.add(a);
				}
			}
					
			//check for indirect characterization associations
			for(Classifier s:parser.getChildren(c)){
				if(s instanceof Mode){
					for(Association a : parser.getDirectAssociations(s)){
						if(a instanceof Characterization){
							ass.add(a);
						}
					}
				}
			}

			if(ass.isEmpty()){
				list.add(c);
			}
		}

		addModeLine(list);
	}

	private void checkMixinCompleteness() {
		Set<? extends Classifier> mixins = parser.getAllInstances(Mixin.class);
		Set<Classifier> list = new HashSet<Classifier>();

		for(Classifier c: mixins){
			boolean isRigid = false;
			boolean isAntiRigid = false;

			if(parser.getSpecializations(c).isEmpty() || parser.getSpecializations(c).size() < 2){
				list.add(c);
			}
			for(Classifier s : parser.getChildren(c)){
				if((s instanceof RigidSortalClass) || (s instanceof RigidMixinClass)){
					isRigid = true;
				}else{
					isAntiRigid = true;
				}
			}

			if(!(isRigid && isAntiRigid)){
				list.add(c);
			}
		}

		addMixinLine(list);
	}

	private void checkCategoryCompleteness() {
		Set<? extends Classifier> categories = parser.getAllInstances(Category.class);
		Set<Classifier> list = new HashSet<Classifier>();

		for(Classifier c: categories){
			if(parser.getSpecializations(c).isEmpty() || parser.getSpecializations(c).size() < 2){
				list.add(c);
			}
		}

		addCategoryLine(list);
	}

	private void checkRoleMixinDependence() {
		Set<Classifier> listRMDependence = new HashSet<Classifier>();
		Set<Classifier> listRM = new HashSet<Classifier>();

		Set<? extends Classifier> rolemixins = parser.getAllInstances(RoleMixin.class);

		for(Classifier c: rolemixins){
			if(parser.getSpecializations(c).isEmpty()){
				listRM.add(c);
			}else{
				//Check size of RM extension
				if(parser.getSpecializations(c).size() < 2){
					listRM.add(c);
				}

				//Checking RoleMixin dependences
				ArrayList<Association> rmAssociations = parser.getDirectAssociations(c);
				rmAssociations.addAll(parser.getIndirectAssociations(c));
				boolean isRMDependent = true;

				for(Association ass : rmAssociations){
					if(ass instanceof Mediation){
						//RM suppress its roles dependences
						isRMDependent = false;
						break;
					}
				}

				if(isRMDependent){
					listRMDependence.add(c);
				}
			}
		}

		addRoleMixinLine(listRM);
		addRoleMixinDependenceLine(listRMDependence);
	}

	private void checkPhasePartition(){
		Set<Classifier> list = new HashSet<Classifier>();
		Set<? extends Classifier> phases = parser.getAllInstances(Phase.class);

		for (Classifier c : phases) {
			if(parser.getGeneralizations(c).isEmpty()){
				list.add(c);
				continue;
			}
			nextClassifier:
				for(Generalization gen : parser.getGeneralizations(c)){
					if(parser.getGeneralizationSet(gen).isEmpty()){
						list.add(c);
						continue;
					}
					for(GeneralizationSet gent : parser.getGeneralizationSet(gen)){
						for(Generalization g : gent.getGeneralization()){
							if(!g.equals(gen)){
								if(g.getSpecific() instanceof Phase){
									break nextClassifier;
								}else{
									list.add(c);
								}
							}
						}
					}	
				}
		}

		addPhasePartitionLine(list);
	}

	/**
	 * Get all classes that does not have its identity principle yet
	 **/
	private void checkIndentityPrinciple(){
		Set<Classifier> list = new HashSet<Classifier>();
		Set<? extends Classifier> set = parser.getAllInstances(Classifier.class);
		for (Classifier c : set) {
			if(c instanceof Phase || c instanceof Role || c instanceof SubKind){
				if(parser.getAllParents(c).isEmpty()){
					list.add(c);
				}
			}
		}

		addPrincipleIdentityLine(list);
	}

	/**
	 * Get all classes that does not have its external dependency suppressed
	 **/
	private void checkRoleAndRelatorCompleteness(){
		Set<Classifier> list = new HashSet<Classifier>();
		Set <? extends Classifier> set = parser.getAllInstances(Role.class);

		for(Classifier c : set){
			//Get all associations of the classifier
			ArrayList<Association> associations = parser.getDirectAssociations(c);
			associations.addAll(parser.getIndirectAssociations(c));

			boolean gambi = true;
			for(Association association : associations){
				if(association instanceof MaterialAssociation || association instanceof Mediation){
					gambi = false;
					break;
				}
			}

			if(gambi || associations.isEmpty()){
				list.add(c);
				continue;
			}
		}

		set = parser.getAllInstances(Relator.class);

		for(Classifier c : set){
			//Get all associations of the classifier
			ArrayList<Association> associations = parser.getDirectAssociations(c);
			associations.addAll(parser.getIndirectAssociations(c));

			boolean gambi = true;
			for(Association association : associations){
				if(association instanceof Mediation){
					gambi = false;
					break;
				}
			}

			if(gambi || associations.isEmpty()){
				list.add(c);
				continue;
			}
		}

		addExternalDependenceLine(list);
	}

	private void addPhasePartitionLine(Set<? extends Classifier> set){
		String apPhasePartition = "Phases must be in a GeneralizationSet\nwith, at least, another Phase.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apPhasePartition, new PhasePartition(parser, c, x, y)));
		}
	}

	private void addPrincipleIdentityLine(Set<? extends Classifier> set){
		String apPrincipleIdentity = "The class must have directly \nor indirectly some Identity Principle.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apPrincipleIdentity, new PrincipleOfIdentity(parser, c, x, y)));
		}
	}

	private void addExternalDependenceLine(Set<? extends Classifier> set){
		String apRelatorPattern = "Relators must have directly \nor indirectly relationed with some Relator class.";
		String apRolePartition = "Roles must have directly \nor indirectly relationed with some Relator class.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRelatorPattern, new GenericMultipleRelator(parser, c, x, y)));
			if(c instanceof Role){
				tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRolePartition, new RolePartition(parser, c, x, y)));	
			}
		}
	}

	private void addRoleMixinLine(Set<? extends Classifier> set){
		String apRoleMixin = "RoleMixins must specialize at least\ntwo Anti-Rigids.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRoleMixin, new RoleMixinPattern(parser, c, x, y)));
		}
	}

	private void addRoleMixinDependenceLine(Set<? extends Classifier> set){
		String apRoleMixinDependence = "RoleMixins must have directly \nor indirectly relationed with some Relator class.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRoleMixinDependence, new RoleMixinDependentPattern(parser, c, x, y)));
		}
	}

	private void addCategoryLine(Set<? extends Classifier> set){
		String apCategory = "Categories must specialize at least\ntwo Rigids.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apCategory, new CategoryPattern(parser, c, x, y)));
		}
	}

	private void addMixinLine(Set<? extends Classifier> set){
		String apMixin = "Mixins must specialize at least\none Rigid and one Anti-Rigid.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apMixin, new MixinPattern(parser, c, x, y)));
		}
	}

	private void addModeLine(Set<? extends Classifier> set){
		String apMode = "Modes must characterizes ar least\none Universal.";

		for (Classifier c : set) {
			tableLines.add(new TableLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apMode, new CharacterizationPattern(parser, c, x, y)));
		}
	}

	class TableLine {
		public String name;
		public String stereotype;
		public String action;
		public AbstractPattern fop;

		TableLine(String name, String stereotype, String action, AbstractPattern fop){
			this.name = name;
			this.stereotype = stereotype;
			this.action = action;
			this.fop = fop;
		}

	}
}
