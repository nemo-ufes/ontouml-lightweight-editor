/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;

import br.ufes.inf.nemo.ootos.OntoUML2OWL;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.ootos.util.MappingType;
import br.ufes.inf.nemo.ontouml2simpleowl.OntoUML2SimpleOWL;
import br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.OWLStructure;
import br.ufes.inf.nemo.ontouml2temporalowl.tree.TreeProcessor;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.FileManager;

public class OWLHelper {

	public static OperationResult generateOwl(RefOntoUML.Package model, String ontologyIRI, MappingType mappingType, boolean fileOutput, String filePath, String oclRules)
	{
		//System.out.println(ontologyIRI);
		br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.MappingType mp = null;
		String errors = "";
		if(mappingType != null && !mappingType.equals(MappingType.RULES)){
			mp = br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.MappingType.valueOf(mappingType.toString());
		}
    	try
    	{
    		String owlOutput;
    		if(mappingType == null)
    		{
    			owlOutput = OntoUML2SimpleOWL.Transformation(model, ontologyIRI);
    		}else if(mappingType.equals(MappingType.RULES)){
    			OntoUML2OWL ontoUML2OWL = new OntoUML2OWL();
    			owlOutput = ontoUML2OWL.Transformation(model, ontologyIRI, oclRules);
    			errors = ontoUML2OWL.errors;
    		}else
    		{
    			TreeProcessor tp = new TreeProcessor(model);
        		
    			// mapping the OntoUML-based structure into an OWL-based structure
    			// according to a certain mapping type
    			OWLStructure owl = new OWLStructure(mp);
    			owl.map(tp);
    			owlOutput = owl.verbose(ontologyIRI);
    		}	
			
    		if(owlOutput != null && owlOutput.length() > 0)
    		{
				if(fileOutput && filePath != null)
				{
					String owlFileName = ConfigurationHelper.canon(filePath);
				    	
					// Writing transformed model into owl file
					FileManager fileManager = new FileManager(owlFileName);
					fileManager.write(owlOutput);
					fileManager.done();
					
					return new OperationResult(ResultType.SUCESS, errors + "OWL generated successfully", new Object[] { owlFileName });
				}
				else
				{
					return new OperationResult(ResultType.SUCESS, errors + "OWL generated successfully", new Object[] { owlOutput });
				}
    		}
    		else
    		{
    			return new OperationResult(ResultType.ERROR, errors + "No OWL generated", null);
    		}
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		return new OperationResult(ResultType.ERROR, "Error while generating the OWL for the model. \nDetails: " + ex.getMessage() + errors, null);
    	}
	}
}
