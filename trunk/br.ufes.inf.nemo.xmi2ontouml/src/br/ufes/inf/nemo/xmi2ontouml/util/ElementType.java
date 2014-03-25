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

public enum ElementType
{
	ASSOCIATION,
	ASSOCIATIONCLASS,
	CLASS,
	COMMENT,
	DATATYPE,
	DEPENDENCY,
	DIAGRAM,
	DIAGRAMELEMENT,
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
		else if (type.equalsIgnoreCase("diagram")) 
		{
			return ElementType.DIAGRAM;
		}
		else if (type.equalsIgnoreCase("diagramelement")) 
		{
			return ElementType.DIAGRAMELEMENT;
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
    
    @Override
    public String toString() 
    {
    	if (this.equals(ElementType.ASSOCIATION)) 
		{
			return "Association";
		}
		else if (this.equals(ElementType.ASSOCIATIONCLASS)) 
		{
			return "AssociationClass";
		} 
		else if (this.equals(ElementType.CLASS)) 
		{
			return "Class";
		} 
		else if (this.equals(ElementType.COMMENT)) 
		{
			return "Comment";
		}
		else if (this.equals(ElementType.CONSTRAINT)) 
		{
			return "Constraint";
		}
		else if (this.equals(ElementType.DATATYPE)) 
		{
			return "DataType";
		} 
		else if (this.equals(ElementType.DEPENDENCY)) 
		{
			return "Dependency";
		} 
		else if (this.equals(ElementType.DIAGRAM)) 
		{
			return "Diagram";
		} 
		else if (this.equals(ElementType.DIAGRAMELEMENT)) 
		{
			return "DiagramElement";
		} 
		else if (this.equals(ElementType.ENUMERATION)) 
		{
			return "Enumeration";
		} 
		else if (this.equals(ElementType.ENUMLITERAL)) 
		{
			return "EnumerationLiteral";
		} 
		else if (this.equals(ElementType.GENERALIZATION)) 
		{
			return "Generalization";
		} 
		else if (this.equals(ElementType.GENERALIZATIONSET)) 
		{
			return "GeneralizationSet";
		} 
		else if (this.equals(ElementType.MODEL)) 
		{
			return "Model";
		} 
		else if (this.equals(ElementType.PACKAGE)) 
		{
			return "Package";
		} 
		else if (this.equals(ElementType.PRIMITIVE)) 
		{
			return "PrimitiveType";
		} 
		else if (this.equals(ElementType.PROPERTY)) 
		{
			return "Property";
		} 
		else
			return null;
	}
}
