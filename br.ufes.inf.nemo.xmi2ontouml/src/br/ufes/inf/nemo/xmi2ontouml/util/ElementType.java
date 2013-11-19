package br.ufes.inf.nemo.xmi2ontouml.util;


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
	
//	ANNOTATION,
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
    PROPERTY,
    CONSTRAINT;
    
    public static ElementType get(String type) 
    {
//    	if (type.equalsIgnoreCase("annotation")) 
//		{
//			return ElementType.ANNOTATION;
//		}
//    	else if (type.equalsIgnoreCase("association")) 
    	if (type.equalsIgnoreCase("association")) 
		{
			return ElementType.ASSOCIATION;
		}
		else if (type.equalsIgnoreCase("associationclass")) 
		{
			return ElementType.ASSOCIATIONCLASS;
		} 
		else if (type.equalsIgnoreCase("class")) 
		{
			return ElementType.CLASS;
		} 
		else if (type.equalsIgnoreCase("comment")) 
		{
			return ElementType.COMMENT;
		}
		else if (type.equalsIgnoreCase("constraint") || type.equalsIgnoreCase("constraintx")) 
		{
			return ElementType.CONSTRAINT;
		}
		else if (type.equalsIgnoreCase("datatype")) 
		{
			return ElementType.DATATYPE;
		} 
		else if (type.equalsIgnoreCase("dependency")) 
		{
			return ElementType.DEPENDENCY;
		} 
		else if (type.equalsIgnoreCase("enumeration") || type.equalsIgnoreCase("enum")) 
		{
			return ElementType.ENUMERATION;
		} 
		else if (type.equalsIgnoreCase("enumerationliteral")) 
		{
			return ElementType.ENUMLITERAL;
		} 
		else if (type.equalsIgnoreCase("generalization")) 
		{
			return ElementType.GENERALIZATION;
		} 
		else if (type.equalsIgnoreCase("generalizationset")) 
		{
			return ElementType.GENERALIZATIONSET;
		} 
		else if (type.equalsIgnoreCase("model")) 
		{
			return ElementType.MODEL;
		} 
		else if (type.equalsIgnoreCase("package")) 
		{
			return ElementType.PACKAGE;
		} 
		else if (type.equalsIgnoreCase("primitive") || type.equalsIgnoreCase("primitivetype")) 
		{
			return ElementType.PRIMITIVE;
		} 
		else if (type.equalsIgnoreCase("property")) 
		{
			return ElementType.PROPERTY;
		} 
		else
			return null;
	}
    
    public static String name(ElementType type) 
    {
//    	if (type.equals(ElementType.ANNOTATION)) 
//		{
//			return "Annotation";
//		}
//    	else if (type.equals(ElementType.ASSOCIATION)) 
    	if (type.equals(ElementType.ASSOCIATION)) 
		{
			return "Association";
		}
		else if (type.equals(ElementType.ASSOCIATIONCLASS)) 
		{
			return "AssociationClass";
		} 
		else if (type.equals(ElementType.CLASS)) 
		{
			return "Class";
		} 
		else if (type.equals(ElementType.COMMENT)) 
		{
			return "Comment";
		}
		else if (type.equals(ElementType.CONSTRAINT)) 
		{
			return "Constraint";
		}
		else if (type.equals(ElementType.DATATYPE)) 
		{
			return "DataType";
		} 
		else if (type.equals(ElementType.DEPENDENCY)) 
		{
			return "Dependency";
		} 
		else if (type.equals(ElementType.ENUMERATION)) 
		{
			return "Enumeration";
		} 
		else if (type.equals(ElementType.ENUMLITERAL)) 
		{
			return "EnumerationLiteral";
		} 
		else if (type.equals(ElementType.GENERALIZATION)) 
		{
			return "Generalization";
		} 
		else if (type.equals(ElementType.GENERALIZATIONSET)) 
		{
			return "GeneralizationSet";
		} 
		else if (type.equals(ElementType.MODEL)) 
		{
			return "Model";
		} 
		else if (type.equals(ElementType.PACKAGE)) 
		{
			return "Package";
		} 
		else if (type.equals(ElementType.PRIMITIVE)) 
		{
			return "PrimitiveType";
		} 
		else if (type.equals(ElementType.PROPERTY)) 
		{
			return "Property";
		} 
		else
			return null;
	}
}
