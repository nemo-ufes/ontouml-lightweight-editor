package br.ufes.inf.nemo.xmi2ontouml;

import java.io.File;
import java.util.Map.Entry;

import br.ufes.inf.nemo.xmi2refontouml.framework.XMI2RefElement;
import br.ufes.inf.nemo.xmi2refontouml.framework.XMI2RefModel;
import br.ufes.inf.nemo.xmi2refontouml.mapper.Mapper;
import br.ufes.inf.nemo.xmi2refontouml.mapper.MapperFactory;
import RefOntoUML.Model;

public class Creator
{
	public static String warningLog;
	public static String errorLog;
	
	public Mapper mapper;
	
	public Model parse(String read_file_address) throws Exception
	{
		warningLog = "";
		errorLog = "";
		
		MapperFactory mapperFac = new MapperFactory();
		this.mapper = mapperFac.createMapper(new File(read_file_address));
		if (mapper == null)
			throw new Exception(errorLog);
		
		XMI2RefModel model = new XMI2RefModel(mapper.getModelElement(), mapper);
		for (Entry<String, XMI2RefElement> elemEntry : XMI2RefElement.getElemMap().entrySet())
		{
			XMI2RefElement xmi2refelem = elemEntry.getValue();
			xmi2refelem.dealReferences();
		}
		
		return (Model) model.getRefOntoUMLElement();
	}

}
