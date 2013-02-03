package br.ufes.inf.nemo.ontouml2sbvr.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class HtmlHelper
{
	HashMap<String, Integer> divNum;
	
	public HtmlHelper()
	{
		divNum = new HashMap<String, Integer>();
	}
	
	public String StartDocument (String title)
	{
		return  "<!DOCTYPE HTML>\n" +
				"<html>\n\n" +
				"<head>\n" +
				"<title>" + title + "</title>\n\n" +
				"<style type='text/css'>\n" +
				"\tdiv { margin-bottom:2px; margin-top:2px; float:left; }\n" + // border:solid;
				"\tdiv#container { margin:auto; width:85%; float:none; }\n" +
				"\tdiv.headerdiv { clear:left; }\n" + 
				"\tdiv.leftdiv { width:200px; margin-left:25px; clear:left; }\n" + 
				"\tdiv.rightdiv { }\n" +
				"\tdiv.collapsible { clear:left; display:none; padding:10px; margin-left:25px; }\n" +
				"\tdiv.sectiondiv { clear:left; border:3px double black; padding:10px; }\n" +
				"\tdiv.breaker { clear:left; height:25px; }\n" +
				"\tfont { font-family:arial; font-size:medium; }\n" +
				"\tfont.title { font-size:large; }\n" +
				"\tfont.term { color:teal; text-decoration:underline; }\n" +
				"\tfont.verb { color:blue; }\n" +
				"\tfont.keyword { color:#FF6500; }\n" +
				"\tfont.cs { color:teal; }\n" +
				"\tsub { vertical-align: -0.35em; }\n" +
				"\t.double { border-bottom: 3px double teal; line-height: 1.7em;}\n" +
				"\timg { border:none; }\n" +
				"</style>\n\n" +
				"<script type='text/javascript'>\n" +
				"function toggle(name, anchor)\n" +
				"{\n" +			
				"	if (document.getElementById(name).style.display == 'block')\n" +
				"	{\n" +
				"		document.getElementById(name).style.display = 'none';\n" +
				"		anchor.innerHTML = \"<img src='show.png'></img>\" \n" +
				"	}\n" +
				"	else\n" +
				"	{\n" +
				"		document.getElementById(name).style.display = 'block';\n" +
				//"		document.getElementById(name).style.border = '3px double';\n" +
				"		anchor.innerHTML = \"<img src='hide.png'></img>\" \n" +
				"	}\n" +
				"}\n" +
				"</script>\n" +
				"</head>\n\n" +
				"<body>\n" + 
				"<div id='container'>\n";
	}
	
	public String EndDocument ()
	{
		return "</div>\n</body>\n</html>\n";
	}
		
	private String Term (String str)
	{
		return "<font class='term'>" + str + "</font>";
	}
	
	private String TermTitle (String str)
	{
		return "<font class='term title'>" + str + "</font>";
	}

	private String Verb (String str)
	{
		return "<font class='verb'>" + str + "</font>";
	}
	
	private String VerbTitle (String str)
	{
		return "<font class='verb title'>" + str + "</font>";
	}
		
	private String Keyword (String str)
	{
		return "<font class='keyword'>" + str + "</font>";
	}
	
	private String KeywordTitle (String str)
	{
		return "<font class='keyword title'>" + str + "</font>";
	}
	
	private String Entry (String str)
	{
		return "<font>" + str + "</font>";
	}	
	
	private String CS (String str)
	{
		return "<span class='double'><font class='cs'>" + str + "</font></span>";
	}
	
	private String CSTitle (String str)
	{
		return "<span class='double'><font class='cs title'>" + str + "</font></span>";
	}
	
	private String DivHeader (String str)
	{
		return "<div class='headerdiv'>" + str + "</div>\n";
	}
	
	private String DivLeft (String str)
	{
		return "<div class='leftdiv'>" + str + "</div>"; //border:solid; 
	}
	
	private String DivRight (String str)
	{
		return "<div class='rightdiv'>" + str + "</div>"; // border:solid;
	}
	
	public String StartCollapsibleSection(String id)
	{
		Integer index = divNum.get(id);
		String indexStr = "";
		
		if (index != 0)
			indexStr = index.toString();			
		
		return "<div id='" + id + indexStr + "' class='collapsible'>\n";
	}
	
	public String EndCollapsibleSection()
	{
		return "</div>\n";
	}
	
	public String StartSection (String str)
	{
		return "<div id='" + str + "_Section" + "' class='sectiondiv'>\n";
	}
	
	public String EndSection()
	{
		return "</div>\n";
	}
	
	private String Toggle (String id)
	{
		Integer index = divNum.get(id);
		String indexStr = "";
				
		if (index == null)
		{
			divNum.put(id, 0);
		}
		else
		{
			index++;
			divNum.put(id, index);
			indexStr = index.toString();			
		}
				
		return "<a href='javascript:;' onclick=\"toggle('" + id + indexStr + "', this);\">" + "<img src='show.png'></img>" + "</a> ";
	}
	
	private String Anchor (String id)
	{
		return "<a name='" + id + "'></a>";
	}
	
	private String AnchorRef (String id, String str)
	{
		return "<a href='#" + id + "'>" + str + "</a>";
	}
		
	public String NounConcept (String str, boolean toggle, boolean anchor)
	{
		if (toggle)
		{
			// Tree version only
			str = Toggle(str) + TermTitle(str);
			return DivHeader(str);
		}
		
		if (anchor)
		{
			// Serial version only
			str = Anchor(str) + TermTitle(str);
		}
		
		return DivHeader(TermTitle(str));
	}	
	
	public String ConceptType (String str)
	{
		return DivLeft(Entry("Concept Type:")) + DivRight(Term(str)) + LineFeed();
	}
	
	public String GeneralConcept (LinkedList<String> parents, boolean anchor)
	{
		String str = "";
		String current = "";
		
		for (Iterator<String> it = parents.iterator(); it.hasNext();)
		{
			current = it.next();
			
			if (anchor)
				str += AnchorRef(current, Term(current));
			else
				str += Term(current);
			
			if (it.hasNext())
				str += ", ";
		}
		
		return DivLeft(Entry("General Concept:")) + DivRight(str) + LineFeed();
	}
	
	private String Definition (String str)
	{
		return DivLeft(Entry("Definition:")) + DivRight(str) + LineFeed();
	}
	
	private String Necessity (String str)
	{
		return DivLeft(Entry("Necessity:")) + DivRight(str) + LineFeed();
	}
	
	public String VerbConcept (String relata1, String assoc, String relata2, boolean reverse, boolean anchor)
	{
		String sub1 = "";
		String sub2 = "";
		
		if (relata1 == relata2)
		{
			sub1 = KeywordTitle("<sub>1</sub>");
			sub2 = KeywordTitle("<sub>2</sub>");
		}
		
		if (reverse)
		{
			String aux;
			aux = relata1;
			relata1 = relata2;
			relata2 = aux;
			
			aux = sub1;
			sub1 = sub2;
			sub2 = aux;
		}
		
		String part1, part2;
		part1 = TermTitle(relata1);
		part2 = TermTitle(relata2);
		
		if (anchor)
		{
			part1 = AnchorRef(relata1, part1);
			part2 = AnchorRef(relata2, part2);
		}
		
		return DivHeader(part1 + sub1 + " " + VerbTitle(assoc) + " " + part2 + sub2);
	}
	
	private String number (int num)
	{
		return num == 1 ? "one" : num+"";
	}
	
	public String AssociationNecessity (String relata1, String verb, String relata2, int min, int max, boolean reverse)
	{
		if (min == 0 && max == -1) return "";
		
		// Cardinality
		String cardinality = "";
		if (min != max)
		{
			if (min != 0)
			{
				cardinality += "at least " + number(min);
				if (max != -1)
				{
					cardinality += " and ";
				}
			}
						
			if (max != -1)
			{
				cardinality += "at most " + number(max);
			}
		}
		else
		{
			if (min != -1)
				cardinality = number(min);
		}
		cardinality = Keyword(cardinality);
		
		// Relata
		String sub1 = "";
		String sub2 = "";
		
		if (relata1 == relata2)
		{
			sub1 = KeywordTitle("<sub>1</sub>");
			sub2 = KeywordTitle("<sub>2</sub>");
		}
		
		if (reverse)
		{
			String aux;
			aux = relata1;
			relata1 = relata2;
			relata2 = aux;
			
			aux = sub1;
			sub1 = sub2;
			sub2 = aux;
		}
						
		// Necessity
		return Necessity ( Keyword("Each ") + Term(relata1) + sub1 + " " + Verb(verb) + " " + Keyword(cardinality) + " " + Term(relata2) + sub2);	
	}
	
	public String CategorizationScheme (String str)
	{
		return DivHeader(CSTitle(str));
	}
	
	public String CSDefinition (String conceptType, String general)
	{
		return Definition ( Term(conceptType) + " " + Keyword("that") + " " + Verb("is for") + " " + Term(general) );
	}
	
	public String CSNecessity (String csname, LinkedList<String> specifics)
	{
		String mynecessity = CS(csname) + " " + Verb("contains") + " " + Keyword("the") + " " + Term("categories") + " ";
		
		for (Iterator<String> it = specifics.iterator(); it.hasNext();)
		{
			mynecessity += Keyword("'") + Term(it.next()) + Keyword("'");
			if (it.hasNext())
				mynecessity += " " + Keyword("and") + " ";
		}
		
		return Necessity(mynecessity);		
	}
	
	public String IncludedInCs (String str, LinkedList<String> gsets)
	{
		String mynecessity = Keyword("The") +  " " + Term("concept") + " " + Term(str) + " " + Verb("is included in") + " ";
		
		for (Iterator<String> it = gsets.iterator(); it.hasNext();)
		{
			mynecessity += CS(it.next());
			if (it.hasNext())
				mynecessity += " " + Keyword("and") + " ";
		}
		
		return Necessity(mynecessity);
	}
		
	private String LineFeed ()
	{
		return "\n";
	}
	
	public String SectionBreaker ()
	{
		//return "<div class='breaker'>[breaker]</div>\n\n";
		return "<div class='breaker'></div>\n\n";
	}
}
