package br.ufes.inf.nemo.ontouml.xmi2refontouml.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.mapperImpl.MapperAstah;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.mapperImpl.MapperEA;



public class MapperFactory {
	
	//private final String XMI_NS = "http://schema.omg.org/spec/XMI/2.1";
	
	private File xmiFile;
	
//	//Create a Mapper from a string that represents the program
//	//or the exporter of the XMI
//	public Mapper createMapper(String name) {
//		if (name.toLowerCase().contains("astah") || 
//				name.toLowerCase().contains("jomt") || 
//				name.toLowerCase().contains("jude")) {
//			return new MapperAstah();
//			
//		} else if (name.contains("EA")) {
//			return new MapperEA();
//		}
//		return null;
//	}
//	
//	//Create a Mapper from a DOM Document
//	public Mapper createMapper(Document doc) {
//		return identifyExporter(doc);
//	}
	
	public Mapper createMapper(File file) {
		
		xmiFile = file;
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder docBuilder;
		
			docBuilder = docBuilderFactory.newDocumentBuilder();
        
			Document doc;
			doc = docBuilder.parse(file);
			
			return identifyExporter(doc);
			
		} catch (SAXParseException err) {
            Mediator.errorLog += "** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId();
            Mediator.errorLog += " " + err.getMessage() + "\n";
 
		} catch (SAXException e) {
			Exception x = e.getException();
			Mediator.errorLog += ((x == null) ? e : x).getMessage() + "\n";
			
		} catch (IOException e) {
			Mediator.errorLog += "File " + file.getAbsolutePath() + 
			" does not exist or could not be oppened." + "\n";
			
		} catch (Exception e) {
			Mediator.errorLog += e.getMessage() + "\n";
		
		}
		
		return null;
	}
	
	//Read the DOM Document and look for the program or the exporter
	//of the XMI
	private Mapper identifyExporter(Document doc) throws Exception {
		
		Element root = doc.getDocumentElement();
		NamedNodeMap nnm = root.getAttributes();
		String version = "";
		String exporter = "";
		
		//Get version of XMI document
		for (int i=0; i < nnm.getLength(); i++) {
			if (nnm.item(i).getNodeName().contains("version")) {
				version = nnm.item(i).getNodeValue();
				break;
			}
		}
		
		//For each version, identify the exporter
		if (version.equals("1.1")) {
			exporter = doc.getElementsByTagName("XMI.exporter").item(0).getFirstChild().getNodeValue();
			if (exporter.contains("Jomt")) {
				return new MapperAstah(xmiFile.getAbsolutePath());
			}
			
		} else if (version.equals("2.1")) {
			Node documentation = doc.getElementsByTagName("xmi:Documentation").item(0);
			exporter = ((Element)documentation).getAttribute("exporter");
			if (exporter.contains("Enterprise Architect")) {
				return new MapperEA(xmiFile.getAbsolutePath());
			}
		}

		throw new Exception("Unsuported Exporter or XMI version");
		
	}
}
