package br.ufes.inf.nemo.pattern.ui.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Phase;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.pattern.dynamic.ui.ModelCompleter;
import br.ufes.inf.nemo.pattern.impl.PrincipleOfIdentity;
import br.ufes.inf.nemo.pattern.impl.RelatorPattern;
import br.ufes.inf.nemo.pattern.impl.RolePartition;

public class ModelCompleterManager {
	
	private OntoUMLParser parser;
	private ModelCompleter window;
	private double x;
	private double y;

	public ModelCompleterManager(OntoUMLParser parser, ModelCompleter window, double x, double y) {
		this.parser = parser;
		this.window = window;
		this.x = x;
		this.y = y;
	}
	
	public void runAnalysis(){
		completeForIndentityPrinciple();
		completeForExternalDependency();
	}
	
	/**
	 * Get all classes that does not have its identity principle yet
	 **/
	private void completeForIndentityPrinciple(){
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
	private void completeForExternalDependency(){
		Set<Classifier> list = new HashSet<Classifier>();
		Set <? extends Classifier> set = parser.getAllInstances(Role.class);

		for(Classifier c : set){
			//Get all associations of the classifier
			ArrayList<Association> associations = parser.getDirectAssociations(c);
			associations.addAll(parser.getIndirectAssociations(c));
			
			boolean gambi = true;
			for(Association association : associations){
				if(association instanceof MaterialAssociation){
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
	
	private String apPrincipleIdentity = "The class must have directly \nor indirectly some Identity Principle.";
	private void addPrincipleIdentityLine(Set<? extends Classifier> set){
		for (Classifier c : set) {
			window.addLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apPrincipleIdentity, new PrincipleOfIdentity(parser, c, x, y));
		}
	}
	
	private String apRelatorPattern = "The class must have directly \nor indirectly relationed with some Relator class.";
	private String apRolePartition = "The class must have directly \nor indirectly relationed with some Relator classs.";
	private void addExternalDependenceLine(Set<? extends Classifier> set){
		for (Classifier c : set) {
			window.addLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRelatorPattern, new RelatorPattern(parser, c, x, y));
			if(c instanceof Role){
				window.addLine(UtilAssistant.getStringRepresentationClass(c),UtilAssistant.getStringRepresentationStereotype(c),apRolePartition, new RolePartition(parser, c, x, y));	
			}
		}
	}
	
	
}
