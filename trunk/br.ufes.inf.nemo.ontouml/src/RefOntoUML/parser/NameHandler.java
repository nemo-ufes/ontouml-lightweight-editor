package RefOntoUML.parser;

import java.util.HashMap;

import RefOntoUML.NamedElement;

/**
 *	This class is used to remove the special characters of a given name in the model and to
 *	store this modified name into a hash map.
 */

public class NameHandler {
        
	String[] keywords  = 
        {
        	"World","abstract","all","and","as","assert","but","check","disj","else","exactly","extends","fact",
            "for","fun","iden","iff","implies","in","Int","let","lone","module","no","none","not","one","open",
            "or","pred","run","set","sig","some","sum","univ","int","Int"
        };
	
	/** 
	 * A hash map containing all the names and their respective word counter. 
	 */
    public HashMap<String, WordCounter> namesHashMap = new HashMap<String, WordCounter>();
    
    public boolean contains(String name){
    	return namesHashMap.containsKey(name);
    }
    
    public void remove(String name)
    {
    	namesHashMap.remove(name);
    }
    
    /** 
     * Remove special characters of the name and store the name into a hash map.
     * @param element TODO
     * 
     * @return
     */
    public String treatName (NamedElement element) 
    {
    	String name = element.getName();
    	    	
    	int cont=-1;
                
        if(name==null || name.equals("")){ 
        	name = OntoUMLNameHelper.getTypeName(element);        
        }

        
        for(int i=0;i<keywords.length;i++)
        {
            if(name.compareTo(keywords[i])==0)
            {
            	name = "keyword";
                break;
            }
        }
                       
        name = name.replaceAll("[^\\p{ASCII}]", "");
        name = name.replaceAll(" ", "");
        name = name.replaceAll(",", "");
        name = name.replaceAll("!", "");
        name = name.replaceAll("@", "");
        name = name.replaceAll("#", "");
        name = name.replaceAll("\\$", "");
        name = name.replaceAll("%", "");
        name = name.replaceAll("\"", "");
        name = name.replaceAll("'", "");
        name = name.replaceAll("&", "");
        name = name.replaceAll("\\*", "");
        name = name.replaceAll("-", "");
        name = name.replaceAll("=", "");
        name = name.replaceAll("\\+", "");
        name = name.replaceAll(";", "");
        name = name.replaceAll(":", "");
        name = name.replaceAll("-", "");
        name = name.replaceAll("<", "");
        name = name.replaceAll(">", "");
        name = name.replaceAll("\\?", "");
        name = name.replaceAll("\\.", "");
        name = name.replaceAll("\\{", "");
        name = name.replaceAll("\\}", "");
        name = name.replaceAll("\\(", "");
        name = name.replaceAll("\\)", "");
        name = name.replaceAll("\\[", "");
        name = name.replaceAll("\\]", "");
        name = name.replaceAll("\\\\", "");
        name = name.replaceAll("/", "");
        name = name.replaceAll("\\|", "");
                
                
        if(namesHashMap.get(name)==null)
        {
        	namesHashMap.put(name, new WordCounter(name, 0));
            cont = 0;
        }else{
          	namesHashMap.get(name).setCounter(namesHashMap.get(name).getCounter()+1);
            cont = namesHashMap.get(name).getCounter();
        }
                
        if(cont!=0)
        {
        	namesHashMap.put(name+Integer.toString(cont), new WordCounter(name+Integer.toString(cont), 0));
            return name+Integer.toString(cont);
        }
        
        return name;
    }
}
