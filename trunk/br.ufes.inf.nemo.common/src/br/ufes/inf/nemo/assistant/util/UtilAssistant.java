package br.ufes.inf.nemo.assistant.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.NamedElement;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLParser;

public class UtilAssistant {
	public static int cont = 0;
	public static int getCont(){
		return ++cont;
	}
	
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

	/**
	 * Return a String with the Class
	 * e.g.: From Kind Person, returns "Person"
	 * */
	public static String getStringRepresentationClass(Classifier c){
		return ((NamedElement)c).getName();
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From {"Kind Person", "Collective Students"} returns {"Person", "Students"}
	 * */
	public static String[] getStringRepresentationClass(Collection<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationClass(cls);
			i++;
		}
		return s;
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From {"Kind Person", "Collective Students"} returns ["Kind Person", "Collective Students"]
	 * */
	public static String[] getStringRepresentationClassStereotype(Set<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationStereotype(cls) +" - "+ getStringRepresentationClass(cls);
			i++;
		}
		return s;
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From "Collective Students" returns "Collective Students"
	 * */
	public static String getStringRepresentationClassStereotype(Classifier cls){
		return  getStringRepresentationStereotype(cls) +" - "+ getStringRepresentationClass(cls);
	}

	/**
	 * Return a String with the Class
	 * e.g.: From Kind Person, returns "Kind"
	 * */
	public static String getStringRepresentationStereotype(Classifier c){
		String type = c.getClass().toString().replaceAll("class RefOntoUML.impl.","");
		type = type.replaceAll("Impl","");
		type = Normalizer.normalize(type, Normalizer.Form.NFD);

		return type;
	}

	/**
	 * Return a String[] with the Class and its Stereotype of each Classifier in the
	 * ArrayList
	 * e.g.: From {"Kind Person", "Collective Students"} returns {"Kind", "Collective"}
	 * */
	public static String[] getStringRepresentationStereotype(ArrayList<? extends Classifier> set){
		String[] s = new String[set.size()];
		int i = 0;
		for (Classifier cls : set) {
			s[i] = getStringRepresentationStereotype(cls);
		}
		return s;
	}
	
}

