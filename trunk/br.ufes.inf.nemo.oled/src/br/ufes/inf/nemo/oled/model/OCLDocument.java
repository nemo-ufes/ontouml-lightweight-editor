
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
package br.ufes.inf.nemo.oled.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;

/**
 * This class represents an OCL Model.
 * 
 * @author John Guerson
 */

public class OCLDocument implements Serializable {
		
	private static final long serialVersionUID = 1L;

	/** All OCL Constraints in one single String. This is our "model.*/
	private String oclstring = new String();
	
	/** Absolute path of ocl document. */
	private String oclpath =  new String();	
	
	/** OCL Parser . */
	private TOCLParser oclparser;
		
	private String name = new String("Document");
	
	/**
	 * Creates an empty ocl model.
	 */
	public OCLDocument() {}
	
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	
	/**
	 * Clear this model.
	 */
	public void clear()
	{		
		oclstring="";
		oclpath="";
		oclparser=null;
	}

	/**
	 * Set OCL Constraints from a file Path or string Content.
	 * 
	 * If type="PATH", OCL will be loaded from a Path file, 
	 * else if type="CONTENT", OCL will be loaded from OCL String content.
	 */
	public void setConstraints (String str, String type) throws IOException
	{
		if  (type=="PATH") 
		{
			String content = FileUtil.readFile(str);			
			this.oclstring = content;						
			this.oclpath = str;
			this.name = str.substring(str.lastIndexOf(File.separator),str.lastIndexOf("."));
			
		} else  if (type=="CONTENT") {
			
			String content = str;			
			this.oclstring = content;		
			this.oclpath=null;
		}
	}
	
	/**
	 * Set OCL Parser.
	 * 
	 * @param oclparser
	 */
	public void setParser(TOCLParser oclparser) { this.oclparser = oclparser; }
	public TOCLParser getParser() { return oclparser; }
	
	/** Get OCL single String containing all Constraints. */
	public String getContent() { return oclstring; }
	
	/**
	 * Set OCL single String containing all Constraints.
	 * 
	 * @param text
	 */
	public void setContent(String text) { 
		String content = text;			
		this.oclstring = content;
	}
	
	public void addContent(String text) {
		oclstring += "\n"+text+"\n";
	}
	
	/** Get Absolute path of OCL document.	*/
	public String getPath() { return oclpath; }
	
	/**
	 * Set Absolute path of OCL document.
	 * 
	 * @param path
	 */
	public void setPath (String path) { oclpath = path; }
		
	@Override
	public String toString()
	{
		return name;
	}
}

