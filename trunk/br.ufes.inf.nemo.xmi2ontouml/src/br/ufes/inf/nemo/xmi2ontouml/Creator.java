package br.ufes.inf.nemo.xmi2ontouml;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.io.File;
import java.util.Map.Entry;

import javax.swing.event.TreeSelectionListener;

import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefElement;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefModel;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.mapper.MapperFactory;
import br.ufes.inf.nemo.xmi2ontouml.util.RefOntoUMLUtil;

import RefOntoUML.Model;


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
    
    public Model parse(String Read_File_Address)
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
    
    public CheckboxTree[] generateModelTrees(Model model, TreeSelectionListener tsl) throws Exception
    {
    	CheckboxTree modelTree = RefOntoUMLUtil.createSelectionTreeFromModel(model);
    	modelTree.getCheckingModel().setCheckingMode(
    			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
    	modelTree.addTreeSelectionListener(tsl);
    	
    	CheckboxTree diagramTree = RefOntoUMLUtil.createSelectionTreeByDiagram(mapper, model);
    	diagramTree.getCheckingModel().setCheckingMode(
    			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
    	diagramTree.addTreeSelectionListener(tsl);
    	
    	return new CheckboxTree[]{modelTree, diagramTree};
    }
}
