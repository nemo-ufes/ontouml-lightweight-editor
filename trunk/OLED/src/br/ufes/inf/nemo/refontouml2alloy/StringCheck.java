package br.ufes.inf.nemo.refontouml2alloy;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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

import java.text.Normalizer;
import java.util.HashMap;

public class StringCheck {
        
        public HashMap<String, WordCounter> namesCounterMap = new HashMap<String, WordCounter>();
                        
        /* ============================================================================*/
        
        public void printNamesHashMap ()
        {
        	System.out.println("namesCounterMap <String Name,Counter c>");
    		for(String p: namesCounterMap.keySet())
    		{
    			System.out.println("\""+p+"\"->\""+namesCounterMap.get(p).getWord()+" n="+namesCounterMap.get(p).getCounter()+"\"");			
    		}
        }       
        
        /* ============================================================================*/
        
        public String removeSpecialNames(String str, String strClass) 
        {
                int cont=-1;
                
                if(str==null)
                {
                	str = strClass;
                }
                else if(str.equals(""))
                {
                	str = strClass;
                }
                
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
                               
                
                if(namesCounterMap.get(str)==null)
                {
                	namesCounterMap.put(str, new WordCounter(str, 0));
                    cont = 0;
                }else{
                	namesCounterMap.get(str).setCounter(namesCounterMap.get(str).getCounter()+1);
                    cont = namesCounterMap.get(str).getCounter();
                }
                
                if(cont!=0)
                {
                	namesCounterMap.put(str+Integer.toString(cont), new WordCounter(str+Integer.toString(cont), 0));
                    return str+Integer.toString(cont);
                }
                return str;
        }
}