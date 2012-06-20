package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

/**
 * This enumeration class is auxiliary to recover 
 * all the elements that can be instantiated in 
 * RefOntoUML. Since the tags are different from
 * program to program, the ElementType can help
 * the communication between the Mapper and the
 * class creating the RefOntoUML XMI.
 * 
 * @author Vinicius
 *
 */

public enum ElementType {
	MODEL,
	PACKAGE,
    CLASS,
    DATATYPE,
    ASSOCIATION,
    PROPERTY,
    GENERALIZATION,
    GENERALIZATIONSET,
    DEPENDENCY,
    COMMENT
}
