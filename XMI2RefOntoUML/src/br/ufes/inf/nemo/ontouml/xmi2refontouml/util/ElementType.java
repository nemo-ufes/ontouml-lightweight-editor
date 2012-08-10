package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

/**
 * This enumeration class is auxiliary to get 
 * all the elements that can be instantiated in 
 * UML. Since the xmi tags are different from
 * program to program, the ElementType can help
 * the communication between the Mapper and the
 * class creating the RefOntoUML XMI.
 * 
 * @author Vinicius
 *
 */

public enum ElementType {
	ASSOCIATION,
	ASSOCIATIONCLASS,
	CLASS,
	COMMENT,
	DATATYPE,
	DEPENDENCY,
	ENUMERATION,
	ENUMLITERAL,
    GENERALIZATION,
    GENERALIZATIONSET,
    MODEL,
    PACKAGE,
	PRIMITIVE,
    PROPERTY;
    
    public static ElementType get(String type) {    	
		if (type.equalsIgnoreCase("association")) {
			return ElementType.ASSOCIATION;
		} else if (type.equalsIgnoreCase("associationclass")) {
			return ElementType.ASSOCIATIONCLASS;
		} else if (type.equalsIgnoreCase("class")) {
			return ElementType.CLASS;
		} else if (type.equalsIgnoreCase("comment")) {
			return ElementType.COMMENT;
		} else if (type.equalsIgnoreCase("datatype")) {
			return ElementType.DATATYPE;
		} else if (type.equalsIgnoreCase("dependency")) {
			return ElementType.DEPENDENCY;
		} else if (type.equalsIgnoreCase("enumeration") ||
				type.equalsIgnoreCase("enum")) {
			return ElementType.ENUMERATION;
		} else if (type.equalsIgnoreCase("enumerationliteral")) {
			return ElementType.ENUMLITERAL;
		} else if (type.equalsIgnoreCase("generalization")) {
			return ElementType.GENERALIZATION;
		} else if (type.equalsIgnoreCase("generalizationset")) {
			return ElementType.GENERALIZATIONSET;
		} else if (type.equalsIgnoreCase("model")) {
			return ElementType.MODEL;
		} else if (type.equalsIgnoreCase("package")) {
			return ElementType.PACKAGE;
		} else if (type.equalsIgnoreCase("primitive") || 
				type.equalsIgnoreCase("primitivetype")) {
			return ElementType.PRIMITIVE;
		} else if (type.equalsIgnoreCase("property")) {
			return ElementType.PROPERTY;
		} else
			return null;
	}
}
