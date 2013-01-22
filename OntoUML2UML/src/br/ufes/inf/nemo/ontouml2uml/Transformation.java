package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class Transformation {
	
	public OntoUMLParser refparser;
	
	boolean ignorePackageHierarchy = false;
	
	public Dealer mydealer;	 
	
	public HashMap <RefOntoUML.Package,org.eclipse.uml2.uml.Package> packagesMap;	
	
	public org.eclipse.uml2.uml.Package umlRootModel;	
	
	/** 
	 * Constructor 
	 */
	public Transformation(OntoUMLParser refparser, boolean ignorePackageHierarchy)  
    { 
		this.refparser = refparser;
		this.ignorePackageHierarchy = ignorePackageHierarchy;
    	mydealer = new Dealer();  
    	packagesMap = new HashMap<RefOntoUML.Package,org.eclipse.uml2.uml.Package>(); 
    }
	
	/**
	 * Transforming to UML
	 */
	public org.eclipse.uml2.uml.Package Transform ()
    {           
		Dealer.outln("Transforming OntoUML into UML...");
		
        umlRootModel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createPackage();                
        mydealer.DealNamedElement((RefOntoUML.NamedElement) refparser.getModel(), (org.eclipse.uml2.uml.NamedElement) umlRootModel);
        mydealer.RelateElements((RefOntoUML.Element) refparser.getModel(), (org.eclipse.uml2.uml.Element) umlRootModel);        
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
            
        Dealer.outln("Executed succesfully.");
        
        return umlRootModel;
    }
		
    /** 
     * Transforming Packages. 
     */  
    public void TransformingPackages (RefOntoUML.Package refmodel, org.eclipse.uml2.uml.Package umlmodel)
    {               
        for (EObject obj : refmodel.eContents())
        { 
            if (obj instanceof RefOntoUML.Package && refparser.isSelected(obj))
            {        		
            	org.eclipse.uml2.uml.Package umlpackage;
        		String name = ((RefOntoUML.Package)refmodel).getName();
        		
        		if (!ignorePackageHierarchy) 
        		{        	
        			umlpackage = umlmodel.createNestedPackage(name);        			   	            		
        		}else {
        			umlpackage = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createPackage();
        		}
        		
        		mydealer.DealPackage( (RefOntoUML.Package)obj,umlpackage);         
        		packagesMap.put((RefOntoUML.Package)obj, umlpackage);
        		TransformingPackages((RefOntoUML.Package)obj,umlpackage);
            }
        }
	}	
	    	
    /**
     * Transforming Classes. 
     */
	public void TransformingClasses ()
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
            		
	            	if (!ignorePackageHierarchy) 
	           		{
	            		org.eclipse.uml2.uml.Class umlclass = umlmodel.createOwnedClass(name, isAbstract);
	           			mydealer.DealClass( (RefOntoUML.Class) elem, umlclass);	           			
	           		}
	           		else 
	           		{
	           			org.eclipse.uml2.uml.Class umlclass = this.umlRootModel.createOwnedClass(name, isAbstract);
	           			mydealer.DealClass( (RefOntoUML.Class) elem, umlclass);	           			
	           		}
	            }
	        }
		}			        
	}
	
    /**
     * Transforming Attributes. 
     */
	public void TransformingAttributes ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			
			for (EObject elem : refmodel.eContents())
	        {        	
	            if (elem instanceof RefOntoUML.Class && refparser.isSelected(elem))
	            {
	            	org.eclipse.uml2.uml.Class umlclass = (org.eclipse.uml2.uml.Class)mydealer.GetElement((RefOntoUML.Class)elem);
	           		mydealer.DealAttribute( (RefOntoUML.Class) elem, umlclass);	                
	            }
	            if (elem instanceof RefOntoUML.DataType && refparser.isSelected(elem) && (!(elem instanceof RefOntoUML.Enumeration)) && (!(elem instanceof RefOntoUML.PrimitiveType)))
	            {
	            	org.eclipse.uml2.uml.DataType umlclass = (org.eclipse.uml2.uml.DataType)mydealer.GetElement((RefOntoUML.DataType)elem);
	           		mydealer.DealAttribute( (RefOntoUML.DataType) elem, umlclass);	                
	            }
	        }
		}			        
	}
		
	/**
	 * Transforming PrimitiveTypes. 
	 */
	public void TransformingPrimitiveTypes ()
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
					 
	                 if (!ignorePackageHierarchy) 
	                 {
	                	 org.eclipse.uml2.uml.PrimitiveType dt2 = umlmodel.createOwnedPrimitiveType(name);
	                	 mydealer.DealPrimitiveType(((RefOntoUML.PrimitiveType)elem),dt2);
	                 }
		           	 else 
		           	 {
		           		org.eclipse.uml2.uml.PrimitiveType dt2 = this.umlRootModel.createOwnedPrimitiveType(name);
		           		mydealer.DealPrimitiveType(((RefOntoUML.PrimitiveType)elem),dt2);
		           	 }
	            }
	        }
		}
	}		
	
	/**
	 * Transforming Enumerations. 
	 */
	public void TransformingEnumerations ()
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
	                 	                 
	                 if (!ignorePackageHierarchy)
	                 {
	                	 org.eclipse.uml2.uml.Enumeration dt2 = umlmodel.createOwnedEnumeration(name);
	                	 mydealer.DealEnumeration ((RefOntoUML.Enumeration) elem,dt2);
	                 }
		           	 else 
		           	 {
		           		 org.eclipse.uml2.uml.Enumeration dt2 = this.umlRootModel.createOwnedEnumeration(name);
		           		 mydealer.DealEnumeration ((RefOntoUML.Enumeration) elem,dt2);                              
		           	 }
	            }
	        }
		}
	}
		
	/** 
	 * Transforming DataTypes. 
	 */
	public void TransformingDataTypes ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if ((elem instanceof RefOntoUML.DataType) && !(elem instanceof RefOntoUML.PrimitiveType) && !(elem instanceof RefOntoUML.Enumeration) && refparser.isSelected(elem))  
	            {
	                 org.eclipse.uml2.uml.DataType dt2 = mydealer.DealDataType ((RefOntoUML.DataType) elem);
	                 
	                 if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(dt2);
		           	 else this.umlRootModel.getPackagedElements().add(dt2);                               
	            }
	        }
		}
	}		
		
	/**
	 * Transforming Associations. 
	 */
	public void TransformingAssociations ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if(elem instanceof RefOntoUML.Derivation && refparser.isSelected(elem))
				{
					org.eclipse.uml2.uml.Association assoc = mydealer.DealAssociation ((RefOntoUML.Association) elem);
					
					if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(assoc);
		           	else this.umlRootModel.getPackagedElements().add(assoc);					
					
				} else if( elem instanceof RefOntoUML.Association && refparser.isSelected(elem))
				{        		
					org.eclipse.uml2.uml.Association assoc = mydealer.DealAssociation ((RefOntoUML.Association) elem);
					
					if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(assoc);
		           	else this.umlRootModel.getPackagedElements().add(assoc);        		
				}
	        }
        }
	}	
		
	/**
	 * Transforming Generalizations. 
	 */
	public void TransformingGeneralizations ()
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
			        	if (refparser.isSelected(gen)) mydealer.ProcessGeneralizations(gen);
			        }
				}
	        }         	
		}
	}		
	
	/**
	 * Transforming Generalization Sets. 
	 */
	public void TransformingGeneralizationSets ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			org.eclipse.uml2.uml.Package umlmodel = packagesMap.get((RefOntoUML.Package)obj);			
	        	
			for (EObject elem : refmodel.eContents())
	        {	            
				if (elem instanceof RefOntoUML.GeneralizationSet && refparser.isSelected(elem))
				{
					org.eclipse.uml2.uml.GeneralizationSet gs2 = mydealer.DealGeneralizationSet ((RefOntoUML.GeneralizationSet) elem);        
					
					if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(gs2);
		           	else this.umlRootModel.getPackagedElements().add(gs2);					
				}
	        }
		}
	}
}