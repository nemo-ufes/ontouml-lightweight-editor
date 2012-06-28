package br.ufes.inf.nemo.ontouml.refontouml2alloy.namesaux;

import java.text.Normalizer;
import java.util.HashMap;

import org.eclipse.emf.ecore.EValidator.PatternMatcher;

public class StringCheck {
	
	public int Relator=0,Mode=0,Kind=0,Quantity=0,Collective=0,Phase=0,Role=0,Category=0,RoleMixin=0,Mixin=0;
	public int componentOf=0,subQuantityOf=0,subCollectionOf=0,memberOf=0,Mediation=0,Characterization=0,
			Derivation=0,FomalAssociation=0,MaterialAssociation=0;
	public HashMap<String, WordCounter> names = new HashMap<String, WordCounter>();
	
	public String removeSpecialNames(String str) {
		int cont=-1;
		
		
		String[] keywords  = {"World","abstract","all","and","as","assert","but","check","disj","else","exactly","extends","fact","for","fun","iden","iff","implies","in","Int","let","lone","module","no","none","not","one","open","or","pred","run","set","sig","some","sum","univ"};
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
		
		
		if(names.get(str)==null)
		{
			names.put(str, new WordCounter(str, 0));
			cont = 0;
		}
		else
		{
			names.get(str).setCounter(names.get(str).getCounter()+1);
			cont = names.get(str).getCounter();
		}
		
		if(cont!=0)
		{
			names.put(str+Integer.toString(cont), new WordCounter(str+Integer.toString(cont), 0));
			return str+Integer.toString(cont);
		}
		return str;
	}
}
