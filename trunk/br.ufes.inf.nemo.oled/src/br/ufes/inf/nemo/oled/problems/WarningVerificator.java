package br.ufes.inf.nemo.oled.problems;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.NamedElement;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.problems.ProblemElement.TypeProblem;

public class WarningVerificator {
	
	public OntoUMLParser refparser;
	public ArrayList<WarningElement> warnings = new ArrayList<WarningElement>();
	public double start =0 ;
	public double end =0 ;		
	
	public WarningVerificator(OntoUMLParser ontoparser)
	{
		this.refparser = ontoparser;
	}

	public ArrayList<WarningElement> getWarnings() { return warnings; }
	
	public String getTimingMessage()
	{
		return MessageFormat.format("Model verified in {0} ms, {1} warning(s) found", (end - start),  warnings.size());
	}
	
	public void run()
	{	
		start = System.currentTimeMillis();
		// # Warning : Unnamed name
		for(EObject c: refparser.getElements())
		{			
			if(c instanceof NamedElement){
				NamedElement ne = (NamedElement)c;
				if (ne.getName()==null || ne.getName().trim().isEmpty()) 
				{ 				
					String message = "Unnamed element";
					warnings.add(new WarningElement(c,0,message,TypeProblem.OLED));
									
				}
			}
		}
		end = System.currentTimeMillis();
	}
}	
