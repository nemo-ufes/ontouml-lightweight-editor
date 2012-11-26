package br.ufes.inf.nemo.ontouml.xmi2refontouml.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.core.Mapper;

public class XMLDOMUtil {
	
	/**
	 * Auxiliary function that returns the first direct child of parent with the specified name.
	 * @param parent the parent that is the scope of the search.
	 * @param name the name of the Element to be found.
	 * @return the first Element found with the specified name.
	 */
	public static Element getFirstAppearanceOf (Element parent, String name) {
		for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return (Element) child;
			}
		}
		return null;
	}
	
	public static List<Element> getElementChildsByType (Element elem, ElementType type, Mapper typeGetter) {
		List<Element> elemList = new ArrayList<Element>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element && typeGetter.getType(child) == type) {
				elemList.add((Element)child);
			}
		}
		return elemList;
	}
	
	public static List<Element> getElementChildsByTagName (Element elem, String TagName) {
		List<Element> elemList = new ArrayList<Element>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element && child.getNodeName().equals(TagName)) {
				elemList.add((Element)child);
			}
		}
		return elemList;
	}
	
	public static List<Element> getElementChilds (Element elem) {
		List<Element> elemList = new ArrayList<Element>();
		for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
    		if (child instanceof Element) {
    			elemList.add((Element)child);
    		}
		}
		return elemList;
	}
	
}
