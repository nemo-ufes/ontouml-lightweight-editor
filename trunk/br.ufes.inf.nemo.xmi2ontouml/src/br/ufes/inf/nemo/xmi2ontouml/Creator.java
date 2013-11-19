package br.ufes.inf.nemo.xmi2ontouml;

import java.io.File;
import java.util.Map.Entry;

import RefOntoUML.Model;
import br.ufes.inf.nemo.xmi2refontouml.framework.XMI2RefElement;
import br.ufes.inf.nemo.xmi2refontouml.framework.XMI2RefModel;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.mapper.MapperFactory;


public class Creator
{
	// Leitor de arquivo XMI que conhece as tags espec�ficas do programa que exportou o arquivo
	public Mapper mapper;

    public static String errorLog = "";
    public static String warningLog = "";
    
    public void initVariables(String Read_File_Address) throws Exception
    {
    	errorLog = "";
    	warningLog = "";
    	
    	//Call the factory to read the Document and decide which Mapper
        //to create, depending on the program/exporter of the XMI
    	MapperFactory mapperFactory = new MapperFactory();
    	mapper = mapperFactory.createMapper(new File(Read_File_Address));
    	if (mapper == null)
    	{
    		Exception e = new Exception(errorLog);
        	throw e;
    	}
    }
    
    public Model parse(String Read_File_Address) throws Exception
    {
    	try
    	{
	    	initVariables(Read_File_Address);
	        
	        //Creates the root (model)
	    	XMI2RefModel model = new XMI2RefModel(mapper.getModelElement(), mapper);
	    	
	    	//Deal with the references to other objects
	    	for (Entry<String, XMI2RefElement> entry : XMI2RefElement.getElemMap().entrySet())
	    	{
	    		XMI2RefElement xmi2refelem = entry.getValue();
	    		xmi2refelem.dealReferences();
	    	}
	    	
	        return (Model) model.getRefOntoUMLElement();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
}
