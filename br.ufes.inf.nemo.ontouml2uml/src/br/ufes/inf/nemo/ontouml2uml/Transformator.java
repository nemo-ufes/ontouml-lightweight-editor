package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.parser.OntoUMLParser;

/**
 * This class reads every OntoUML element to be transformed and run the UML converter to generate its UML counterpart.
 * 
 * @author John Guerson
 *
 */

public class Transformator {
	
	private OntoUMLParser refparser;
	private ElementConverter uconverter;		
	private org.eclipse.uml2.uml.Package umlRootModel;
		
	private HashMap <RefOntoUML.Package,org.eclipse.uml2.uml.Package> packagesMap;
	private OntoUML2UMLOption options;
		
	public Transformator(OntoUMLParser refparser, OntoUML2UMLOption opt)  
    { 
		this.refparser = refparser;
		this.options=opt;
    	uconverter = new ElementConverter();  
    	packagesMap = new HashMap<RefOntoUML.Package,org.eclipse.uml2.uml.Package>(); 
    }

	public ElementConverter getConverter()
	{
		return uconverter;
	}
	
	public org.eclipse.uml2.uml.Package run ()
    {
		ElementConverter.outln("Translating OntoUML into pure UML...");
		
        umlRootModel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createPackage();                
        uconverter.DealNamedElement((RefOntoUML.NamedElement) refparser.getModel(), (org.eclipse.uml2.uml.NamedElement) umlRootModel);
        uconverter.RelateElements((RefOntoUML.Element) refparser.getModel(), (org.eclipse.uml2.uml.Element) umlRootModel);        
        packagesMap.put(refparser.getModel(), umlRootModel);
        
        TransformingPackages(refparser.getModel(),umlRootModel);   

        TransformingPrimitiveTypes();        

        TransformingEnumerations();        

        TransformingDataTypes();        

        TransformingClasses();

        TransformingAttributes();        

        TransformingAssociations();        

        TransformingGeneralizations();        

        TransformingGeneralizationSets();
            
        ElementConverter.outln("Pure UML translation finished.");
        
        return umlRootModel;
    }
	
	/** Packages */
    private void TransformingPackages (RefOntoUML.Package refmodel, org.eclipse.uml2.uml.Package umlmodel)
    {               
        for (EObject obj : refmodel.eContents())
        { 
            if (obj instanceof RefOntoUML.Package && refparser.isSelected(obj))
            {        		
            	org.eclipse.uml2.uml.Package umlpackage;
        		String name = ((RefOntoUML.Package)refmodel).getName();
        		
        		if (!options.isIgnorePackageHierarchy()) 
        		{        	
        			umlpackage = umlmodel.createNestedPackage(name);        			   	            		
        		}else {
        			umlpackage = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createPackage();
        		}
        		
        		uconverter.DealPackage( (RefOntoUML.Package)obj,umlpackage);         
        		packagesMap.put((RefOntoUML.Package)obj, umlpackage);
        		TransformingPackages((RefOntoUML.Package)obj,umlpackage);
            }
        }
	}	
	    	
    /** Classes */
	private void TransformingClasses ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
	            if (elem instanceof RefOntoUML.Class && refparser.isSelected(elem))
	            {
	            	boolean isAbstract = ((RefOntoUML.Class)elem).isIsAbstract();
            		String name = ((RefOntoUML.Class)elem).getName();
            		
	            	if (!options.isIgnorePackageHierarchy()) 
	           		{
	            		org.eclipse.uml2.uml.Class umlclass = umlmodel.createOwnedClass(name, isAbstract);
	           			uconverter.DealClass( (RefOntoUML.Class) elem, umlclass);	           			
	           		}
	           		else 
	           		{
	           			org.eclipse.uml2.uml.Class umlclass = this.umlRootModel.createOwnedClass(name, isAbstract);
	           			uconverter.DealClass( (RefOntoUML.Class) elem, umlclass);	           			
	           		}
	            }
	        }
		}			        
	}
	
    /** Attributes */
	private void TransformingAttributes ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			
			for (EObject elem : refmodel.eContents())
	        {        	
	            if (elem instanceof RefOntoUML.Class && refparser.isSelected(elem))
	            {
	            	org.eclipse.uml2.uml.Class umlclass = (org.eclipse.uml2.uml.Class)uconverter.GetElement((RefOntoUML.Class)elem);
	           		uconverter.DealAttribute( (RefOntoUML.Class) elem, umlclass);	                
	            }
	            if (elem instanceof RefOntoUML.DataType && refparser.isSelected(elem) && (!(elem instanceof RefOntoUML.Enumeration)) && (!(elem instanceof RefOntoUML.PrimitiveType)))
	            {
	            	org.eclipse.uml2.uml.DataType umlclass = (org.eclipse.uml2.uml.DataType)uconverter.GetElement((RefOntoUML.DataType)elem);
	           		uconverter.DealAttribute( (RefOntoUML.DataType) elem, umlclass);	                
	            }
	        }
		}			        
	}
		
	/** PrimitiveTypes */
	private void TransformingPrimitiveTypes ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if (elem instanceof RefOntoUML.PrimitiveType && refparser.isSelected(elem))
	            {
					 String name = ((RefOntoUML.PrimitiveType)elem).getName();
					 
	                 if (!options.isIgnorePackageHierarchy()) 
	                 {
	                	 org.eclipse.uml2.uml.PrimitiveType dt2 = umlmodel.createOwnedPrimitiveType(name);
	                	 uconverter.DealPrimitiveType(((RefOntoUML.PrimitiveType)elem),dt2);
	                 }
		           	 else 
		           	 {
		           		org.eclipse.uml2.uml.PrimitiveType dt2 = this.umlRootModel.createOwnedPrimitiveType(name);
		           		uconverter.DealPrimitiveType(((RefOntoUML.PrimitiveType)elem),dt2);
		           	 }
	            }
	        }
		}
	}		
	
	/** Enumerations */
	private void TransformingEnumerations ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if (elem instanceof RefOntoUML.Enumeration && refparser.isSelected(elem)) 
	            {
					 String name = ((RefOntoUML.Enumeration)elem).getName();
	                 	                 
	                 if (!options.isIgnorePackageHierarchy())
	                 {
	                	 org.eclipse.uml2.uml.Enumeration dt2 = umlmodel.createOwnedEnumeration(name);
	                	 uconverter.DealEnumeration ((RefOntoUML.Enumeration) elem,dt2);
	                 }
		           	 else 
		           	 {
		           		 org.eclipse.uml2.uml.Enumeration dt2 = this.umlRootModel.createOwnedEnumeration(name);
		           		 uconverter.DealEnumeration ((RefOntoUML.Enumeration) elem,dt2);                              
		           	 }
	            }
	        }
		}
	}
		
	/** DataTypes */
	private void TransformingDataTypes ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if ((elem instanceof RefOntoUML.DataType) && !(elem instanceof RefOntoUML.PrimitiveType) && !(elem instanceof RefOntoUML.Enumeration) && refparser.isSelected(elem))  
	            {
	                 org.eclipse.uml2.uml.DataType dt2 = uconverter.DealDataType ((RefOntoUML.DataType) elem);
	                 
	                 if (!options.isIgnorePackageHierarchy()) umlmodel.getPackagedElements().add(dt2);
		           	 else this.umlRootModel.getPackagedElements().add(dt2);                               
	            }
	        }
		}
	}		
		
	/** Association */
	private void TransformingAssociations ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if(elem instanceof RefOntoUML.Derivation && refparser.isSelected(elem) && !options.isIgnoreDerivation())
				{
					org.eclipse.uml2.uml.Association assoc = uconverter.DealAssociation ((RefOntoUML.Association) elem);					
					if(assoc!=null)
					{
						if (!options.isIgnorePackageHierarchy()) umlmodel.getPackagedElements().add(assoc);
						else this.umlRootModel.getPackagedElements().add(assoc);
					}
					
				} else if( elem instanceof RefOntoUML.Association && refparser.isSelected(elem))
				{        		
					org.eclipse.uml2.uml.Association assoc = uconverter.DealAssociation ((RefOntoUML.Association) elem);
					if(assoc!=null)
					{
						if (!options.isIgnorePackageHierarchy() ) umlmodel.getPackagedElements().add(assoc);
						else this.umlRootModel.getPackagedElements().add(assoc);
					}
				}
	        }
        }
	}	
		
	/** Generalization */
	private void TransformingGeneralizations ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;						
	        	
			for (EObject elem : refmodel.eContents())
	        {	
				if (elem instanceof RefOntoUML.Classifier && refparser.isSelected(elem))
				{               
			        for (RefOntoUML.Generalization gen : ((RefOntoUML.Classifier)elem).getGeneralization())
			        {
			        	if (refparser.isSelected(gen)) uconverter.ProcessGeneralizations(gen);
			        }
				}
	        }         	
		}
	}		
	
	/** Generalization Set */
	private void TransformingGeneralizationSets ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        	
			for (EObject elem : refmodel.eContents())
	        {	            
				if (elem instanceof RefOntoUML.GeneralizationSet && refparser.isSelected(elem))
				{
					org.eclipse.uml2.uml.GeneralizationSet gs2 = uconverter.DealGeneralizationSet ((RefOntoUML.GeneralizationSet) elem);        
					
					if (!options.isIgnorePackageHierarchy()) umlmodel.getPackagedElements().add(gs2);
		           	else this.umlRootModel.getPackagedElements().add(gs2);					
				}
	        }
		}
	}
}