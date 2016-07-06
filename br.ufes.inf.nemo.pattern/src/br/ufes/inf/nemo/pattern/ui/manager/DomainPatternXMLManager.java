package br.ufes.inf.nemo.pattern.ui.manager;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomainPatternXMLManager {
	
	public static String getDiagramDescription(Document XML, String diagramName){
		try {
			NodeList nList = XML.getElementsByTagName("drop");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getAttribute("name").equalsIgnoreCase(diagramName)){
						return eElement.getElementsByTagName("description").item(0).getTextContent();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static ArrayList<String> getDiagramCompetenceQuestion(Document XML, String diagramName){
		ArrayList<String> cqs = new ArrayList<String>();

		try {
			NodeList nList = XML.getElementsByTagName("drop");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getAttribute("name").equalsIgnoreCase(diagramName)){
						Element ecqs = (Element) eElement.getElementsByTagName("competence-question").item(0);
						for(int i = 0; i < ecqs.getElementsByTagName("cq").getLength(); i++){
							cqs.add(ecqs.getElementsByTagName("cq").item(i).getTextContent());
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cqs;
	}
	
	public static ArrayList<String> getDiagramRules(Document XML, String diagramName){
		ArrayList<String> ocls = new ArrayList<String>();

		try {
			NodeList nList = XML.getElementsByTagName("drop");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getAttribute("name").equalsIgnoreCase(diagramName)){
						Element ecqs = (Element) eElement.getElementsByTagName("rules").item(0);
						if(ecqs == null){
							return ocls;
						}
						for(int i = 0; i < ecqs.getElementsByTagName("ocl").getLength(); i++){
							ocls.add(ecqs.getElementsByTagName("ocl").item(i).getTextContent());
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ocls;
	}
	
}
