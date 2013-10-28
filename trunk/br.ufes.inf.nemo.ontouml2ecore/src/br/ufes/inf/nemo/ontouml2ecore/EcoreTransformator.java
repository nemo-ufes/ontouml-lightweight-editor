package br.ufes.inf.nemo.ontouml2ecore;

import java.util.HashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class EcoreTransformator {
	
	private OntoUMLParser refparser;
	private EcoreElementConverter econverter;		
	private EPackage ecoreRootModel;
	
	private HashMap <RefOntoUML.Package, EPackage> epackagesMap;
	private boolean ignorePackageHierarchy = false;	
	private boolean ignoreDerivation = true;	 
		
	public EcoreTransformator(OntoUMLParser refparser, boolean ignorePackageHierarchy, boolean ignoreDerivation)  
    { 
		this.refparser = refparser;
		this.ignorePackageHierarchy = ignorePackageHierarchy;
		this.ignoreDerivation = ignoreDerivation;	 
    	econverter = new EcoreElementConverter(this);  
    	epackagesMap = new HashMap<RefOntoUML.Package, EPackage>();
    }

	public OntoUMLParser getOntoUMLParser(){
		return refparser;
	}
	
	public EcoreElementConverter getConverter()
	{
		return econverter;
	}
	
	public EPackage run ()
    {
		EcoreElementConverter.outln("Running ontouml2ecore...");
		
		ecoreRootModel = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEPackage();   
		
        econverter.convertENamedElement((RefOntoUML.NamedElement) refparser.getModel(), (ENamedElement) ecoreRootModel);
        econverter.RelateElements((RefOntoUML.Element) refparser.getModel(), (EModelElement) ecoreRootModel);        
        epackagesMap.put(refparser.getModel(), ecoreRootModel);

        ecoreRootModel.setNsPrefix(ecoreRootModel.getName());
        ecoreRootModel.setNsURI("http://"+ecoreRootModel.getName()+"/1.0");
        
        TransformingPackages(refparser.getModel(),ecoreRootModel);   

        TransformingEnumerations();        

        TransformingDataTypes();        

        TransformingClasses();

        TransformingAttributes();        

        TransformingAssociations();        

        TransformingGeneralizations();        
            
        EcoreElementConverter.outln("Executed.");
        
        return ecoreRootModel;
    }
	
	/** Packages */
    private void TransformingPackages (RefOntoUML.Package refmodel, EPackage ecoremodel)
    {               
        for (EObject obj : refmodel.eContents())
        { 
            if (obj instanceof RefOntoUML.Package && refparser.isSelected(obj))
            {        	
        		EPackage ecorepackage = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEPackage();
        		econverter.convertEPackage((RefOntoUML.Package)obj,ecorepackage);
        		
        		epackagesMap.put((RefOntoUML.Package)obj, ecorepackage);
        		
        		if (!ignorePackageHierarchy) ecoremodel.getESubpackages().add(ecorepackage);        		
        		
        		TransformingPackages((RefOntoUML.Package)obj,ecorepackage);
            }
        }
	}	
	    	
    /** Classes */
	private void TransformingClasses ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			EPackage ecoremodel = epackagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
	            if (elem instanceof RefOntoUML.Class && refparser.isSelected(elem))
	            {	
            		EClass ecoreclass = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass();           		
            		econverter.convertEClass((RefOntoUML.Class) elem, ecoreclass);
            		
	            	if (!ignorePackageHierarchy) ecoremodel.getEClassifiers().add(ecoreclass);
	            	else this.ecoreRootModel.getEClassifiers().add(ecoreclass);
	            }
	        }
		}			        
	}

	/** DataTypes */
	private void TransformingDataTypes ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			EPackage ecoremodel = epackagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if ((elem instanceof RefOntoUML.DataType) && !(elem instanceof RefOntoUML.PrimitiveType) && !(elem instanceof RefOntoUML.Enumeration) && refparser.isSelected(elem))  
	            {
					EClass dt2 = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass();
					econverter.convertEDataType ((RefOntoUML.DataType) elem,dt2);
	                if (!ignorePackageHierarchy) ecoremodel.getEClassifiers().add(dt2);	                 
		           	else this.ecoreRootModel.getEClassifiers().add(dt2);					
	            }
	        }
		}
	}		
	
	/** Enumerations */
	private void TransformingEnumerations ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			EPackage ecoremodel = epackagesMap.get((RefOntoUML.Package)obj);			
	        
			for (EObject elem : refmodel.eContents())
	        {        	
				if (elem instanceof RefOntoUML.Enumeration && refparser.isSelected(elem)) 
	            {					 
					 EEnum dt2 = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEEnum();  
					 econverter.convertEEnum((RefOntoUML.Enumeration) elem,dt2);
					 
					 if (!ignorePackageHierarchy) ecoremodel.getEClassifiers().add(dt2);	                 
		           	 else this.ecoreRootModel.getEClassifiers().add(dt2);           
	            }
	        }
		}
	}	
	
    /** Attributes */
	private void TransformingAttributes ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;
			
			for (EObject elem : refmodel.eContents())
	        {        	
	            if (elem instanceof RefOntoUML.Class && refparser.isSelected(elem))
	            {
	            	EClass ecoreclass = (EClass)econverter.GetElement((RefOntoUML.Class)elem);
	           		econverter.convertEStructuralFeature((RefOntoUML.Class) elem, ecoreclass);	                
	            }
	            if (elem instanceof RefOntoUML.DataType && refparser.isSelected(elem) && (!(elem instanceof RefOntoUML.Enumeration)) && (!(elem instanceof RefOntoUML.PrimitiveType)))
	            {
	            	EClass ecoreclass = (EClass)econverter.GetElement((RefOntoUML.DataType)elem);
	            	econverter.convertEStructuralFeature((RefOntoUML.DataType) elem, ecoreclass);	                
	            }
	        }
		}			        
	}	

	/** Associations */
	private void TransformingAssociations ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;						
	        
			for (EObject elem : refmodel.eContents())
	        {
				if(elem instanceof RefOntoUML.Derivation && refparser.isSelected(elem) && !ignoreDerivation)
				{
					econverter.convertEReference((RefOntoUML.Association) elem);					
					
				} else if(elem instanceof RefOntoUML.Association && !(elem instanceof RefOntoUML.Derivation) && refparser.isSelected(elem))
				{        		
					econverter.convertEReference ((RefOntoUML.Association)elem);
				} 
	        }
        }
	}	
		
	/** Generalizations */
	private void TransformingGeneralizations ()
	{
		for (EObject obj: epackagesMap.keySet())
		{
			RefOntoUML.Package refmodel = (RefOntoUML.Package)obj;						
	        	
			for (EObject elem : refmodel.eContents())
	        {	
				if (elem instanceof RefOntoUML.Classifier && refparser.isSelected(elem))
				{               
			        for (RefOntoUML.Generalization gen : ((RefOntoUML.Classifier)elem).getGeneralization())
			        {
			        	if (refparser.isSelected(gen)) econverter.convertESupertTypes(gen);
			        }
				}
	        }         	
		}
	}
}
