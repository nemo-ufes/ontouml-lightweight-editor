package br.ufes.inf.nemo.xmi2ontouml.xmiparser;

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

import br.ufes.inf.nemo.xmi2ontouml.xmiparser.impl.AstahXMIParser;
import br.ufes.inf.nemo.xmi2ontouml.xmiparser.impl.EAXMIParser;


public class XMIParserFactory {
	
	//private final String XMI_NS = "http://schema.omg.org/spec/XMI/2.1";
	
	private File xmiFile;
	
	public XMIParser createMapper(File file) throws Exception
	{
		xmiFile = file;
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder docBuilder;
		
			docBuilder = docBuilderFactory.newDocumentBuilder();
        
			Document doc;
			doc = docBuilder.parse(file);
			
			return identifyExporter(doc);
			
		} catch (SAXParseException err)
		{
			String error = "** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId();
			error += " " + err.getMessage();
			throw new Exception(error);
 
		} catch (SAXException e)
		{
			Exception x = e.getException();
			throw new Exception(((x == null) ? e : x).getMessage());
			
		} catch (IOException e)
		{
			throw new Exception ("File " + file.getAbsolutePath() + 
			" does not exist or could not be oppened.");
		}
	}
	
	//Read the DOM Document and look for the program or the exporter
	//of the XMI
	private XMIParser identifyExporter(Document doc) throws Exception
	{
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
				return new AstahXMIParser(xmiFile.getAbsolutePath());
			}
			
		} else if (version.equals("2.1")) {
			Node documentation = doc.getElementsByTagName("xmi:Documentation").item(0);
			exporter = ((Element)documentation).getAttribute("exporter");
			if (exporter.contains("Enterprise Architect")) {
				return new EAXMIParser(xmiFile.getAbsolutePath());
			}
		}

		throw new Exception("Unsuported Exporter or XMI version");
	}
}
