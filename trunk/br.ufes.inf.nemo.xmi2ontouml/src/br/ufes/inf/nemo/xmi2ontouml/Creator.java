package br.ufes.inf.nemo.xmi2ontouml;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.io.File;
import java.util.Map.Entry;

import javax.swing.event.TreeSelectionListener;

import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefAssociation;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefClassifier;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefElement;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefModel;
import br.ufes.inf.nemo.xmi2ontouml.framework.XMI2RefProperty;
import br.ufes.inf.nemo.xmi2ontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2ontouml.mapper.MapperFactory;
import br.ufes.inf.nemo.xmi2ontouml.util.RefOntoUMLUtil;

import RefOntoUML.Model;


public class Creator
{
	// Leitor de arquivo XMI que conhece as tags espec�ficas do programa que exportou o arquivo
	public Mapper mapper;

    public static String warningLog = "";
    
    public void initVariables(String Read_File_Address, boolean importComments, boolean ignoreUnknownStereotypes, 
    		boolean createDefaultElement, boolean ignoreErrorElem, boolean autoGenerateNamesAssoc, 
    		boolean autoGenerateNamesProp, boolean autoGenerateCard) throws Exception
    {
    	warningLog = "";
    	
    	XMI2RefElement.setIgnoreErrorElements(ignoreErrorElem);
    	XMI2RefElement.setImportComments(importComments);
    	
    	if (createDefaultElement)
    		XMI2RefClassifier.setUnknownStereotypeOpt(0);
    	
    	else if (ignoreUnknownStereotypes)
    		XMI2RefClassifier.setUnknownStereotypeOpt(1);
    	
    	else
    		XMI2RefClassifier.setUnknownStereotypeOpt(2);
    	
    	XMI2RefAssociation.setAutoGenerateNames(autoGenerateNamesAssoc);
    	XMI2RefProperty.setAutoGenerateNames(autoGenerateNamesProp);
    	XMI2RefProperty.setAutoGenerateCardinality(autoGenerateCard);
    	
    	//Call the factory to read the Document and decide which Mapper
        //to create, depending on the program/exporter of the XMI
    	MapperFactory mapperFactory = new MapperFactory();
    	mapper = mapperFactory.createMapper(new File(Read_File_Address));
    }
    
    public Model parse(String Read_File_Address, boolean importComments, boolean ignoreUnknownStereotypes, 
    		boolean createDefaultElement, boolean ignoreErrorElem, boolean autoGenerateNamesAssoc, 
    		boolean autoGenerateNamesProp, boolean autoGenerateCard) throws Exception
    {
    	initVariables(Read_File_Address, importComments,
    			ignoreUnknownStereotypes, createDefaultElement, ignoreErrorElem,
    			autoGenerateNamesAssoc, autoGenerateNamesProp, autoGenerateCard);
        
        //Creates the root (model) and all sub elements recursively
    	XMI2RefModel model = new XMI2RefModel(mapper.getModelElement(), mapper);
    	
    	//Deal with the references to other objects
    	for (Entry<String, XMI2RefElement> entry : XMI2RefElement.getElemMap().entrySet())
    	{
    		XMI2RefElement xmi2refelem = entry.getValue();
    		xmi2refelem.dealReferences();
    	}
    	
        return (Model) model.getRefOntoUMLElement();
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
