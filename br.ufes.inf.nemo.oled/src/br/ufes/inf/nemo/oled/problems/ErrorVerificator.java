package br.ufes.inf.nemo.oled.problems;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ErrorVerificator {

	public OntoUMLParser refparser;
	public ArrayList<ErrorElement> errors = new ArrayList<ErrorElement>();
	
	public ErrorVerificator(OntoUMLParser ontoparser)
	{
		this.refparser = ontoparser;
	}

//	public ArrayList<ArrayList<String>> run(OntoUMLParser ontoparser)
//	{	
//		for(RefOntoUML.PackageableElement c: refparser.getAllInstances(RefOntoUML.PackageableElement.class))
//		{			
//			// # Error : Invalid stereotype
//			if (!refparser.isValidStereotype(c)) 
//			{
//				String message = "Invalid stereotype";
//				errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
//			}			
//			// # Error : Name contains an OCL keyword
//			if(!(c instanceof PrimitiveType))
//			{
//				if(refparser.isOCLkeyword(((NamedElement)c).getName())) 
//				{ 				
//					String message = "Name contains an OCL keyword";
//					errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
//				}
//			}
//		}
//				
//		// # Error : Name contains an OCL keyword
//		for(RefOntoUML.Property c: ontoparser.getAllInstances(RefOntoUML.Property.class))
//		{	
//			if (refparser.isOCLkeyword(c.getName())) 
//			{ 				
//				String message = "Name contains an OCL keyword";
//				errors.add(new ErrorElement(c,0,message,TypeProblem.OLED));
//			}
//		}
//		
//		// # Error : Mixin not abstract
//		for(RefOntoUML.Type c: ontoparser.getAllInstances(RefOntoUML.Type.class))
//		{			
//			if((c instanceof MixinClass) && (((MixinClass)c).isIsAbstract()== false)) 
//			{ 				 
//				ArrayList<String> line = new ArrayList<String>();
//				errors++;
//				line.add(String.format("%02d", errors)+". Mixin not abstract");				
//				line.add(getElement(c));
//				line.add(getPath(c));
//				items.add(line);
//			}				
//		}		
//		
//		// # Error : Association end type is null
//		for(RefOntoUML.Property p: ontoparser.getAllInstances(RefOntoUML.Property.class))		
//		{			
//			if (p.getType()==null){
//				ArrayList<String> line = new ArrayList<String>();
//				errors++;
//				line.add(String.format("%02d", errors)+". Association end type is null");				
//				line.add(getElement(p));
//				line.add(getPath(p));
//				items.add(line);
//			}
//		}
//		
//		// # Error : Association hasn't two association ends.
//		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
//		{			
//			if(c.getMemberEnd().size()!=2)			
//			{ 
//				ArrayList<String> line = new ArrayList<String>();
//				errors++;
//				line.add(String.format("%02d", errors)+". Association has not two association ends");				
//				line.add(getElement(c));
//				line.add(getPath(c));
//				items.add(line);							
//			}
//		}
//		
//		// # Error : Source end type is not a Relator
//		// # Error : Mediated end is not read only
//		for(RefOntoUML.Mediation m: ontoparser.getAllInstances(RefOntoUML.Mediation.class))
//		{						
//			if(!(m.relator() instanceof Relator))
//			{ 					
//				ArrayList<String> line = new ArrayList<String>();
//				errors++;
//				line.add(String.format("%02d", errors)+". Source end type must be a Relator");				
//				line.add(getElement(m));
//				line.add(getPath(m));
//				items.add(line);
//			}				
//			if(m.mediatedEnd().isIsReadOnly()==false)
//			{
//				ArrayList<String> line = new ArrayList<String>();
//				errors++;
//				line.add(String.format("%02d", errors)+". Mediated end must be read only");				
//				line.add(getElement(m));
//				line.add(getPath(m));
//				items.add(line);
//			}
//		}
//		
//		// # Error : Whole must have aggregation kind equal to Composite or Shared.
//		// # Error : Part must have aggregation kind equal to None. 
//		for(RefOntoUML.Meronymic m: ontoparser.getAllInstances(RefOntoUML.Meronymic.class))
//		{					
//			if(m.getMemberEnd().size()==2)
//			{
//				if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
//				{
//					ArrayList<String> line = new ArrayList<String>();
//					errors++;
//					line.add(String.format("%02d", errors)+". Whole must have aggregation kind equal to composite or shared");					
//					line.add(getElement(m));
//					line.add(getPath(m));
//					items.add(line);
//				}				
//				if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
//				{
//					ArrayList<String> line = new ArrayList<String>();
//					errors++;
//					line.add(String.format("%02d", errors)+". Part must have aggregation kind equal to none");					
//					line.add(getElement(m));
//					line.add(getPath(m));
//					items.add(line);
//				}
//			}				
//		}	
//		
//		// # Error : Source end type of characterization must be a Mode.
//		// # Error : Characterized end of characterization must be read only.
//		for(RefOntoUML.Characterization m: ontoparser.getAllInstances(RefOntoUML.Characterization.class))
//		{						
//			if(m.getMemberEnd().size()==2)
//			{
//				if(!(m.characterizing() instanceof Mode))
//				{
//					ArrayList<String> line = new ArrayList<String>();
//					errors++;
//					line.add(String.format("%02d", errors)+". Source end type of characterization must be a Mode");					
//					line.add(getElement(m));
//					line.add(getPath(m));
//					items.add(line);
//				}				
//				if(m.characterizedEnd().isIsReadOnly()==false)
//				{
//					ArrayList<String> line = new ArrayList<String>();
//					errors++;
//					line.add(String.format("%02d", errors)+". Characterized end of characterization must be read only");					
//					line.add(getElement(m));
//					line.add(getPath(m));
//					items.add(line);
//				}
//			}
//		}	
//		
//		return items;
//	}
}
