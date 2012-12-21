package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class Transformation {
	
	public Dealer mydealer;	 
	public HashMap <RefOntoUML.Package,org.eclipse.uml2.uml.Package> packagesMap;			
	public OntoUMLParser refparser;
	public org.eclipse.uml2.uml.Package umlmodel; 
	boolean ignorePackageHierarchy = false;
	
	
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
	 * Transforming...
	 */
	public org.eclipse.uml2.uml.Package Transform ()
    {           
		Dealer.outln("Transforming OntoUML model...");
		
        umlmodel = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createModel();
                
        mydealer.DealNamedElement((RefOntoUML.NamedElement) refparser.getModel(), (org.eclipse.uml2.uml.NamedElement) umlmodel);
        mydealer.RelateElements((RefOntoUML.Element) refparser.getModel(), (org.eclipse.uml2.uml.Element) umlmodel);
        
        packagesMap.put(refparser.getModel(), umlmodel);
                
        TransformingPackages(refparser.getModel(),umlmodel);
        
        //VerifyElements();
                    
        TransformingPrimitiveTypes();        
        TransformingEnumerations();        
        TransformingDataTypes();
        
        TransformingClasses();  
        
        TransformingAttributes();
        
        TransformingAssociations();        
        TransformingGeneralizations();        
        TransformingGeneralizationSets();
            
        Dealer.outln("Executed succesfully.");
        
        return umlmodel;
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
            	org.eclipse.uml2.uml.Package umlpackage = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createPackage();
        		mydealer.DealPackage( (RefOntoUML.Package)obj,umlpackage);            	            		
        		
        		if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(umlpackage); 
        		
        		packagesMap.put((RefOntoUML.Package)obj, umlpackage);
        		TransformingPackages((RefOntoUML.Package)obj,umlpackage);        						            	
            }
        }
	}	
	
    /** 
     * Verifying Non Permitted Elements: Non Classifiers, Non GeneralizationSet and Non Dependency 
     */
	public void VerifyElements ()
	{
		for (EObject obj: packagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;						
	        	
			for (EObject elem : refmodel.eContents())
	        {            
				if (!(elem instanceof RefOntoUML.Classifier) &&
					!(elem instanceof RefOntoUML.GeneralizationSet) &&
					!(elem instanceof RefOntoUML.Dependency) &&
					!(elem instanceof RefOntoUML.Package) &&
					!(elem instanceof RefOntoUML.Comment))
				{
					System.err.print("# Unsupported Class: "+elem.getClass().getName());                
				}
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
	          		org.eclipse.uml2.uml.Class umlclass = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createClass();  
	           		mydealer.DealClass( (RefOntoUML.Class) elem, umlclass);	           			           		
	           		
	           		if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(umlclass);
	           		else this.umlmodel.getPackagedElements().add(umlclass);
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
	                 org.eclipse.uml2.uml.PrimitiveType dt2 = mydealer.DealPrimitiveType ((RefOntoUML.PrimitiveType) elem);	                 	                 		           		
	                 
	                 if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(dt2);
		           	 else this.umlmodel.getPackagedElements().add(dt2);	                                                
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
	                 org.eclipse.uml2.uml.Enumeration dt2 = mydealer.DealEnumeration ((RefOntoUML.Enumeration) elem);
	                 
	                 if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(dt2);
		           	 else this.umlmodel.getPackagedElements().add(dt2);                              
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
		           	 else this.umlmodel.getPackagedElements().add(dt2);                               
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
		           	else this.umlmodel.getPackagedElements().add(assoc);					
					
				} else if( elem instanceof RefOntoUML.Association && refparser.isSelected(elem))
				{        		
					org.eclipse.uml2.uml.Association assoc = mydealer.DealAssociation ((RefOntoUML.Association) elem);
					
					if (!ignorePackageHierarchy) umlmodel.getPackagedElements().add(assoc);
		           	else this.umlmodel.getPackagedElements().add(assoc);        		
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
					/* Generalizations */
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
		           	else this.umlmodel.getPackagedElements().add(gs2);					
				}
	        }
		}
	}
}