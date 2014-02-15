package br.ufes.inf.nemo.assistant.util;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;

public class UtilAssistant {

	/**
	 * Return the result of the mapping the RefOntoUML.Classifer to a StereotypeOntoUMLEnum
	 * or return null if the elem is not treat yet.
	 * */
	public static StereotypeOntoUMLEnum getStereotypeFromClassifier(Classifier elem){
		if(elem instanceof Kind){
			return StereotypeOntoUMLEnum.KIND;
		}else if(elem instanceof Relator){
			return StereotypeOntoUMLEnum.RELATOR;
		}else if(elem instanceof Category){
			return StereotypeOntoUMLEnum.CATEGORY;
		}else if(elem instanceof Collective){
			return StereotypeOntoUMLEnum.COLLECTIVE;
		}else if(elem instanceof Mixin){
			return StereotypeOntoUMLEnum.MIXIN;
		}else if(elem instanceof Phase){
			return StereotypeOntoUMLEnum.PHASE;
		}else if(elem instanceof Role){
			return StereotypeOntoUMLEnum.ROLE;
		}else if(elem instanceof RoleMixin){
			return StereotypeOntoUMLEnum.ROLEMIXIN;
		}else if(elem instanceof SubKind){
			return StereotypeOntoUMLEnum.SUBKIND;
		}
		return null;
	}
	
	/**
	 * Print all RefOntoUML structure
	 * */
	public static void printRefOntoUML(Package root){
		OntoUMLParser parser = new OntoUMLParser(root);
		System.out.println(parser.toString());
	}
}
