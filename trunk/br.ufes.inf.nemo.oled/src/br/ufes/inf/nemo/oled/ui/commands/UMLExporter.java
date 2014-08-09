package br.ufes.inf.nemo.oled.ui.commands;

import java.io.File;
import java.io.IOException;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLOption;

public class UMLExporter extends FileWriter {

	public void writeUML(DiagramManager manager, File file) throws IOException 
	{
		OntoUMLParser refparser = manager.getFrame().getProjectBrowser().getParser();
		String umlPath = file.getAbsolutePath();
		if(!umlPath.contains(".uml")) umlPath += ".uml";
		OntoUML2UML.convertToUML(refparser,umlPath,new OntoUML2UMLOption(false,false));
	}

	protected String getSuffix() {
		return ".uml";
	}
}