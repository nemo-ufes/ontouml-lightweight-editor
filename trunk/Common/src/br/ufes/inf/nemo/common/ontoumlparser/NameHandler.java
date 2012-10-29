package br.ufes.inf.nemo.common.ontoumlparser;

import java.text.Normalizer;
import java.util.HashMap;

/**
 *	This class is used to remove the special characters of a given name in the model and to
 *	store this modified name into a hash map.
 */

public class NameHandler {
        
	/** 
	 * A hash map containing all the names and their respective word counter. 
	 */
    public HashMap<String, WordCounter> namesHashMap = new HashMap<String, WordCounter>();
    
    /** 
     * Remove special characters of the name and store the name into a hash map.
     * 
     * @param str: The name of the element in the model.
     * @param strClass: The class name of the element in the model (method getClass()).
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
        	namesHashMap.put(str, new WordCounter(str, 0));
            cont = 0;
        }else{
          	namesHashMap.get(str).setCounter(namesHashMap.get(str).getCounter()+1);
            cont = namesHashMap.get(str).getCounter();
        }
                
        if(cont!=0)
        {
        	namesHashMap.put(str+Integer.toString(cont), new WordCounter(str+Integer.toString(cont), 0));
            return str+Integer.toString(cont);
        }
        return str;
    }
}
