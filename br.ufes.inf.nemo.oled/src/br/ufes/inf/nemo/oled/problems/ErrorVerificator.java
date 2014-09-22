package br.ufes.inf.nemo.oled.problems;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Meronymic;
import RefOntoUML.MixinClass;
import RefOntoUML.NamedElement;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.problems.ProblemElement.TypeProblem;

public class ErrorVerificator {

	public OntoUMLParser refparser;
	public ArrayList<ErrorElement> errors = new ArrayList<ErrorElement>();
	public double start =0 ;
	public double end =0 ;		
	
	public ErrorVerificator(OntoUMLParser ontoparser)
	{
		this.refparser = ontoparser;
	}

	public ArrayList<ErrorElement> getErrors() { return errors; }
	
	public String getTimingMessage(){
		return MessageFormat.format("Model verified in {0} ms, {1} error(s) found", (end - start),  errors.size());
	}
	
	public void run()
	{	
		start = System.currentTimeMillis();
		for(EObject c: refparser.getElements())
		{			
			if(c instanceof RefOntoUML.Class || c instanceof RefOntoUML.Relationship || c instanceof RefOntoUML.DataType)
			{
				// # Error : Invalid stereotype
				if (!refparser.isValidStereotype(c)) 
				{
					String message = "Invalid stereotype";
					errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
				}			
			}
			// # Error : Name contains an OCL keyword
			if(!(c instanceof PrimitiveType) && (c instanceof NamedElement))
			{
				if(refparser.isOCLkeyword(((NamedElement)c).getName())) 
				{
					String message = "Name contains an OCL keyword";
					errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
				}
			}
			// # Error : Mixin not abstract
			if((c instanceof MixinClass) && (((MixinClass)c).isIsAbstract()== false)) 
			{ 
				String message = "Mixin not abstract";
				errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
			}		
			// #Error : Association has more than two association ends
			if(c instanceof Association){
				if(((Association)c).getMemberEnd().size()!=2)			
				{ 
					String message = "Association has not two association ends";				
					errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
				}
			}
			// # Error : Whole must have aggregation kind equal to Composite or Shared.
			// # Error : Part must have aggregation kind equal to None.
			if(c instanceof Meronymic){
				Meronymic m = (Meronymic)c;
				if(m.getMemberEnd().size()==2)
				{
					if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
					{
						String message = "Whole must have aggregation kind equal to composite or shared";
						errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
					}
					if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
					{
						String message = "Part must have aggregation kind equal to none";
						errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
					}
				}
			}
			// # Error : Property type is null
			if(c instanceof Property){
				if(((Property)c).getType()==null){
					String message = new String();
					if(((Property)c).getAssociation()==null) message = "Attribute type is null";
					else message = "Association end type is null";
					errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
				}
			}

		}
		end = System.currentTimeMillis();
	}
}
