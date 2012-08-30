package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDOMUtil {
	
	// Auxiliary function that returns the first direct child of @parent with name @name
	public static Element getChild(Element parent, String name) {
		for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return (Element) child;
			}
		}
		return null;
	}
	
//	public static List<Object> getChildsByType (Element elem, ElementType type) {
//		List<Object> elemList = new ArrayList<Object>();
//		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
//    		if (child instanceof Element && getType(child) == type) {
//				elemList.add((Element)child);
//			}
//		}
//		return elemList;
//	}
	
	public static List<Object> getElementChildsByTagName (Element elem, String TagName) {
		List<Object> elemList = new ArrayList<Object>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element && child.getNodeName().equals(TagName)) {
				elemList.add((Element)child);
			}
		}
		return elemList;
	}
	
	public static List<Object> getElementChilds (Element elem) {
		List<Object> elemList = new ArrayList<Object>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element) {
    			elemList.add((Element)child);
    		}
		}
		return elemList;
	}
}
