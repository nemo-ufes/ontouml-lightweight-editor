package br.ufes.inf.nemo.ontouml2alloy.names;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.text.Normalizer;
import java.util.HashMap;

/**
 *	This class is used to remove the special characters of a given name in the model and to
 *	store this modified name into a hash map.
 *
 * 	@authors Tiago Sales, John Guerson and Lucas Thom 
 */

public class NamesHandler {
        
	/** 
	 * A hash map containing all the names and their respective word counter. 
	 */
    public HashMap<String, NamesCounter> namesHashMap = new HashMap<String, NamesCounter>();
    
    /** 
     * Remove special characters of the name and store the name into a hash map.
     * 
     * @param str: The name of the element in the model.
     * @param strClass: The class name of the element in the model.
     * @return
     */
    public String treatName (String str, String strClass) 
    {
    	int cont=-1;
                
        if(str==null) str = strClass;        
        else if(str.equals("")) str = strClass;
                        
        String[] keywords  = 
        {
        	"World","abstract","all","and","as","assert","but","check","disj","else","exactly","extends","fact",
            "for","fun","iden","iff","implies","in","Int","let","lone","module","no","none","not","one","open",
            "or","pred","run","set","sig","some","sum","univ","int","Int"
        };
                
        for(int i=0;i<keywords.length;i++)
        {
            if(str==keywords[i])
            {
            	str = "keyword";
                break;
            }
        }
                
        str = str.replaceAll("class RefOntoUML.impl.","");
        str = str.replaceAll("Impl","");
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
                
        str = str.replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll(" ", "");
        str = str.replaceAll(",", "");
        str = str.replaceAll("!", "");
        str = str.replaceAll("@", "");
        str = str.replaceAll("#", "");
        str = str.replaceAll("\\$", "");
        str = str.replaceAll("%", "");
        str = str.replaceAll("\"", "");
        str = str.replaceAll("'", "");
        str = str.replaceAll("&", "");
        str = str.replaceAll("\\*", "");
        str = str.replaceAll("-", "");
        str = str.replaceAll("=", "");
        str = str.replaceAll("\\+", "");
        str = str.replaceAll(";", "");
        str = str.replaceAll(":", "");
        str = str.replaceAll("-", "");
        str = str.replaceAll("<", "");
        str = str.replaceAll(">", "");
        str = str.replaceAll("\\?", "");
        str = str.replaceAll("\\.", "");
        str = str.replaceAll("\\{", "");
        str = str.replaceAll("\\}", "");
        str = str.replaceAll("\\(", "");
        str = str.replaceAll("\\)", "");
        str = str.replaceAll("\\[", "");
        str = str.replaceAll("\\]", "");
        str = str.replaceAll("\\\\", "");
        str = str.replaceAll("/", "");
        str = str.replaceAll("\\|", "");
                
                
        if(namesHashMap.get(str)==null)
        {
        	namesHashMap.put(str, new NamesCounter(str, 0));
            cont = 0;
        }else{
          	namesHashMap.get(str).setCounter(namesHashMap.get(str).getCounter()+1);
            cont = namesHashMap.get(str).getCounter();
        }
                
        if(cont!=0)
        {
        	namesHashMap.put(str+Integer.toString(cont), new NamesCounter(str+Integer.toString(cont), 0));
            return str+Integer.toString(cont);
        }
        return str;
    }
}
