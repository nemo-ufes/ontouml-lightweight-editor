package br.ufes.inf.nemo.ontouml2ecore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;

import RefOntoUML.EnumerationLiteral;
import RefOntoUML.PrimitiveType;

/**
 * This class transforms each OntoUML element into the equivalent Ecore element. 
 * We record each pair (OntoUML element, Ecore element) into a hash map.
 * We also follow the Ecore metamodel structure in transformation whenever is possible.
 * 
 * @author John Guerson
 */

public class EcoreElementConverter {

	public org.eclipse.emf.ecore.EcoreFactory efactory;	
    public HashMap <RefOntoUML.Element, EModelElement> emap;
    public EcoreTransformator etransformer;
    
	public EcoreElementConverter(EcoreTransformator etransformer)
    {
		efactory = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE;
        emap = new HashMap<RefOntoUML.Element, EModelElement>();
        this.etransformer= etransformer; 
    }
	
	public HashMap <RefOntoUML.Element, EModelElement> getMap()
	{
		return emap;
	}
	
	public static void outln(String output) 
    {
    	OntoUML2Ecore.log += output+"\n";
    }
	
    public static void out(String output) 
    {
    	OntoUML2Ecore.log += output;
    }
        
    public static void err(String error) 
    {
    	OntoUML2Ecore.log += error+"\n";
    }
    
    
    public RefOntoUML.Element getKeyByValue(EModelElement value) 
    {
        for (Entry<RefOntoUML.Element, EModelElement> entry : emap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }       
	   
    
    public void RelateElements (RefOntoUML.Element c1, EModelElement c2)
    {
        emap.put(c1, c2);
    }
   
    public EModelElement GetElement (RefOntoUML.Element e1)
    {
        return emap.get(e1);
    }	
    
    /** ENamedElement */
	public void convertENamedElement (RefOntoUML.NamedElement ne1, ENamedElement ne2)
    {    	    	
        ne2.setName(ne1.getName());
        
        if (ne1 instanceof RefOntoUML.Property && (ne1.getName()==null || ne1.getName().isEmpty())) 
        {
        	RefOntoUML.Type type = ((RefOntoUML.Property)ne1).getType();
        	if (type !=null) ne2.setName(type.getName().trim().toLowerCase());
        	else ne2.setName("");
        }      
    }
    
	/** EClassifier */	
	public void convertEClassifier (RefOntoUML.Classifier c1, EClassifier c2)
    {
        convertENamedElement (c1, c2);  c2.getInstanceClassName();     
        RelateElements (c1, c2);                      
    }

	/** EPackage  */
	public void convertEPackage (RefOntoUML.Package p1, EPackage p2)
	{
		convertENamedElement(p1, p2);
		RelateElements(p1,p2);
		
		outln("Ecore:EPackage :: name="+p2.getName());		
	}
			
	/** EClass */	
	public void convertEClass (RefOntoUML.Class c1, EClass c2)
    {		
		convertEClassifier (c1, c2);           		   		

		c2.setAbstract(c1.isIsAbstract());
		
        outln("Ecore:EClass :: name="+c2.getName()+", abstract="+c2.isAbstract());        
    }	
	 
    /** EDataType  */     
    public void convertEDataType (RefOntoUML.DataType dt1, EDataType dt2)
    {     
        convertEClassifier (dt1, dt2);
      	
        dt2.setSerializable(true);
        outln("Ecore:EDataType :: name="+dt2.getName());
    }
    
    /** EDataType */
    public void convertEDataType (RefOntoUML.DataType dt1, EClass dt2)
    {
    	convertEClassifier (dt1, dt2);
    	
    	outln("Ecore:EClass :: name="+dt2.getName()+", abstract="+dt2.isAbstract()); 
    }
    
    /** EEnum */     
    public void convertEEnum (RefOntoUML.Enumeration dt1, EEnum dt2)
    {    	 
    	convertEClassifier (dt1, dt2);
        
    	outln("Ecore:EEnum :: name="+dt2.getName());
    	
    	for (EnumerationLiteral lit: dt1.getOwnedLiteral()) 
    	{
    		EEnumLiteral elit = efactory.createEEnumLiteral();
    		elit.setName(lit.getName());
    		
    		dt2.getELiterals().add(elit);
    		
    		outln("Ecore:EEnumliteral :: name="+elit.getName());
    	}
    }
    
    /** ETypedElement */
    public void convertETypedElement (RefOntoUML.Property p1, ETypedElement p2)
    {
    	p2.setLowerBound(p1.getLower());
    	p2.setUpperBound(p1.getUpper());   
    	
    	EClassifier etype = getEType(p1);  
        p2.setEType(etype);        
    }
    
	/** EStructuralFeature */	
	public void convertEStructuralFeature (RefOntoUML.Classifier c1, EClass c2)
    {   
        EStructuralFeature p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            if (p1.getType() instanceof RefOntoUML.PrimitiveType) p2 = efactory.createEAttribute();
            else { p2 = efactory.createEReference(); }

            c2.getEStructuralFeatures().add(p2);
            
        	convertENamedElement (p1,p2);        	
            convertETypedElement (p1,p2);
                                    
            p2.setDerived(p1.isIsDerived());
            
            String typeName = new String();
            if (p2.getEType() != null) typeName = p2.getEType().getName();
            else typeName = "null";      
            
            if (p2 instanceof EAttribute) 
            	outln("Ecore:EAttribute :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLowerBound()+", upper="+p2.getUpperBound()+", type="+typeName);            
            
            else if (p2 instanceof EReference)
            	outln("Ecore:EReference :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLowerBound()+", upper="+p2.getUpperBound()+", type="+typeName);
                  
            RelateElements(p1, p2);
        }        
    }
		
	/** EType */
	public EClassifier getEType (RefOntoUML.Property p1)
	{
		 EClassifier etype = null;
         
         if (emap.containsKey(p1.getType())) 
         {
         	etype = (EClassifier)GetElement(p1.getType());         	
         } 
         else if (p1.getType() instanceof PrimitiveType) 
         {
         	PrimitiveType pt = (PrimitiveType)p1.getType();
         	
         	if (pt.getName().trim().toLowerCase().equals("int") || pt.getName().trim().toLowerCase().equals("integer")) { etype = EcorePackage.eINSTANCE.getEInt(); }
         	else if (pt.getName().trim().toLowerCase().equals("boolean")) { etype = EcorePackage.eINSTANCE.getEBoolean(); }
         	else if (pt.getName().trim().toLowerCase().equals("char")) { etype = EcorePackage.eINSTANCE.getEChar(); }
         	else if (pt.getName().trim().toLowerCase().equals("float")) { etype = EcorePackage.eINSTANCE.getEFloat(); }
         	else if (pt.getName().trim().toLowerCase().equals("double") || pt.getName().trim().toLowerCase().equals("real") ) { etype = EcorePackage.eINSTANCE.getEDouble(); }            	
         	else if (pt.getName().trim().toLowerCase().equals("string")) { etype = EcorePackage.eINSTANCE.getEString(); }
         }
         return etype;
	}
		
	/** EReference */	
	public void convertEReference (RefOntoUML.Association a1)
    {
        RefOntoUML.Type source = a1.getMemberEnd().get(0).getType();
        RefOntoUML.Type target = a1.getMemberEnd().get(1).getType();

        EClass esource = ((EClass)GetElement(source));
        EClass etarget = ((EClass)GetElement(target));
        
        EReference p2;        
        ArrayList<EReference> ereferenceList = new ArrayList<EReference>();
        for (int i = 0; i < a1.getMemberEnd().size();i++)
        {
        	RefOntoUML.Property p1 = a1.getMemberEnd().get(i);
        	
        	p2 = efactory.createEReference(); 
        	ereferenceList.add(p2);
        	
        	convertENamedElement (p1,p2);        	
            convertETypedElement (p1,p2);
                                    
            p2.setDerived(p1.isIsDerived());
                                
            String typeName = new String();
            if (p2.getEType() != null) typeName = p2.getEType().getName();
            else typeName = "null";
            
            out("Ecore:EReference :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLowerBound()+", upper="+p2.getUpperBound()+", type="+typeName);
            
            if (p1.getAggregation().equals(RefOntoUML.AggregationKind.SHARED) || p1.getAggregation().equals(RefOntoUML.AggregationKind.COMPOSITE))
            {
            	p2.setContainment(true);
            }
            
            outln(", containment="+(p2.isContainment()));
                                    
            RelateElements(p1, p2);            
        }

        // adding to owner class
        etarget.getEStructuralFeatures().add(ereferenceList.get(0)); 
        esource.getEStructuralFeatures().add(ereferenceList.get(1)); 
        
        // setting EOpposites
        ((EReference)ereferenceList.get(1)).setEOpposite(ereferenceList.get(0));    
        ((EReference)ereferenceList.get(0)).setEOpposite(ereferenceList.get(1));
    }
	
	/** ESuperTypes */	
	public void convertESupertTypes (RefOntoUML.Generalization gen1)
    {	
        RefOntoUML.Type general = gen1.getGeneral();
        RefOntoUML.Type specific = gen1.getSpecific();
        
        EClass egeneral = ((EClass)GetElement(general));
        EClass especific = ((EClass)GetElement(specific));
        
        // add to specific super types
        especific.getESuperTypes().add(egeneral);
        
        outln("Ecore:Inheritance :: "+egeneral.getName()+"->"+especific.getName());        
     }	 
}
