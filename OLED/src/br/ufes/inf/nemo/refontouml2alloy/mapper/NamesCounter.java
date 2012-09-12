package br.ufes.inf.nemo.refontouml2alloy.mapper;

import java.text.Normalizer;
import java.util.HashMap;

/**
 *	This class is used as a name counter for elements in the model. 
 */

public class NamesCounter {
        
    public HashMap<String, WordCounter> namesCounterMap = new HashMap<String, WordCounter>();
    
    public String treatName (String str, String strClass) 
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
