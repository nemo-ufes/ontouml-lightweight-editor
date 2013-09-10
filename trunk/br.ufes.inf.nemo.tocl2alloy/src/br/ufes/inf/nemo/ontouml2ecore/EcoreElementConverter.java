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

public class EcoreElementConverter {

	public org.eclipse.emf.ecore.EcoreFactory efactory;	
    public HashMap <RefOntoUML.Element, EModelElement> emap;
    
    
	public EcoreElementConverter()
    {
		efactory = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE;
        emap = new HashMap<RefOntoUML.Element, EModelElement>();
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
    }
    
	/** EClassifier */	
	public void convertEClassifier (RefOntoUML.Classifier c1, EClassifier c2)
    {
        convertENamedElement (c1, c2);       
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
            if (p1.getType() instanceof PrimitiveType) p2 = efactory.createEAttribute();
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
	
	ArrayList<EReference> list = new ArrayList<EReference>();
		
	/** EReference */	
	public void convertEReference (RefOntoUML.Association a1)
    {
        RefOntoUML.Type source = a1.getMemberEnd().get(0).getType();
        RefOntoUML.Type target = a1.getMemberEnd().get(1).getType();
        
        EClass esource = ((EClass)GetElement(source));
        EClass etarget = ((EClass)GetElement(target));
        
        EReference p2;
        for (int i = 0; i < a1.getMemberEnd().size();i++)
        {
        	RefOntoUML.Property p1 = a1.getMemberEnd().get(i);
        	
        	p2 = efactory.createEReference(); 
        	            
        	convertENamedElement (p1,p2);        	
            convertETypedElement (p1,p2);
                                    
            p2.setDerived(p1.isIsDerived());
                                
            String typeName = new String();
            if (p2.getEType() != null) typeName = p2.getEType().getName();
            else typeName = "null";
            
            if (p1.getAggregation().equals(RefOntoUML.AggregationKind.SHARED) || p1.getAggregation().equals(RefOntoUML.AggregationKind.COMPOSITE))
            {
            	p2.setContainment(true);
            }
            
            outln("Ecore:EReference :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLowerBound()+", upper="+p2.getUpperBound()+", type="+typeName);
            
            if (i==0) { etarget.getEStructuralFeatures().add(p2); }
            else if (i==1) esource.getEStructuralFeatures().add(p2);
            
            list.add(p2);
            
            RelateElements(p1, p2);            
        }        
        
    }

	
	
	
	
	/** EReference */
	public void convertEReference (RefOntoUML.Property p1, EReference p2)
	{        
        //if (p2.getName().isEmpty() || p2.getName()==null) p2.setName(t2.getName().toLowerCase());
        
        RefOntoUML.AggregationKind ak1 = p1.getAggregation();
            
        if (ak1.getValue() == RefOntoUML.AggregationKind.NONE_VALUE)
        {
        	p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL);                     
        }
        else if (ak1.getValue() == RefOntoUML.AggregationKind.SHARED_VALUE)
        {
            p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.SHARED_LITERAL);
        }               
        else if (ak1.getValue() == RefOntoUML.AggregationKind.COMPOSITE_VALUE)
        {
            p2.setAggregation(org.eclipse.uml2.uml.AggregationKind.COMPOSITE_LITERAL);
        }        
    }
	
	/** 
	 * DataType Attributes. 
	 */	
	public void DealAttribute (RefOntoUML.DataType c1, org.eclipse.uml2.uml.DataType c2)
    {	
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            p2 = efactory.createProperty();
            DealProperty(p1, p2);       
            
            String typeName = new String();
            String aggregationName = new String();
            String visibilityName = new String();
            
            if (p2.getType()==null) typeName = "null"; else typeName = p2.getType().getName();
            if (p2.getAggregation()==null) aggregationName = "null"; else aggregationName = p2.getAggregation().getName();
            if (p2.getVisibility()==null) visibilityName = "null"; else visibilityName = p2.getVisibility().getName();
            
            outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
            ", type="+typeName+", aggregationkind="+aggregationName+", visibility="+visibilityName+            
            ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());
            
            c2.getOwnedAttributes().add(p2);
            
            RelateElements(p1, p2);
        }         
    }
	
	/** 
	 * Get Aggregation Kind Method.
	 */
	private org.eclipse.uml2.uml.AggregationKind getAggregationKind (RefOntoUML.Property p1)
    {
    	RefOntoUML.AggregationKind ak1 = p1.getAggregation();
        
    	if (ak1.getValue() == RefOntoUML.AggregationKind.NONE_VALUE)
    	{
    		return org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;                     
    	}
    	else if (ak1.getValue() == RefOntoUML.AggregationKind.SHARED_VALUE)
    	{
    		return org.eclipse.uml2.uml.AggregationKind.SHARED_LITERAL;
    	}               
    	else if (ak1.getValue() == RefOntoUML.AggregationKind.COMPOSITE_VALUE)
    	{
    		return org.eclipse.uml2.uml.AggregationKind.COMPOSITE_LITERAL;
    	}
    	return null;
    }
	/** 
	 * Create Association Method. 
	 */	
	public org.eclipse.uml2.uml.Association createAssociation (RefOntoUML.Association a1)	
    {
		if (a1.getMemberEnd().size()==2)
		{
			RefOntoUML.Property src = a1.getMemberEnd().get(0);
			RefOntoUML.Property tgt = a1.getMemberEnd().get(1);
			RefOntoUML.Type source = src.getType();
			RefOntoUML.Type target = tgt.getType();		
		
			// 	inverse
			org.eclipse.uml2.uml.Type sourceClass= (org.eclipse.uml2.uml.Type) GetElement(target);
			org.eclipse.uml2.uml.Type targetClass = (org.eclipse.uml2.uml.Type)GetElement(source);	
		
			boolean end1IsNavigable = true; //src.isNavigable();
			boolean end2IsNavigable = true; //tgt.isNavigable();
    	
			String end1Name = src.getName();
			String end2Name = tgt.getName();
    	
			int end1Lower = src.getLower();
			int end2Lower = tgt.getLower();
			int end1Upper = src.getUpper();
			int end2Upper = tgt.getUpper();    	
    	
			if((end2Upper==-1) && (end2Lower==-1)) { end2Lower = 0;}
			if((end1Upper==-1) && (end1Lower==-1)) { end1Lower = 0;}
    	
			org.eclipse.uml2.uml.AggregationKind ag1 = getAggregationKind(src);
			org.eclipse.uml2.uml.AggregationKind ag2 = getAggregationKind(tgt);
    	
			/* create association */
			org.eclipse.uml2.uml.Association a2 = sourceClass.createAssociation(
					end1IsNavigable, ag1, end1Name, end1Lower, end1Upper, targetClass, 
					end2IsNavigable, ag2, end2Name, end2Lower, end2Upper
			);        
   		
			return a2;
		}
		return null;
    }
	
	/** 
	 * Generalization. 
	 */	
	public void DealGeneralization (RefOntoUML.Generalization gen1)
    {	
        org.eclipse.uml2.uml.Generalization gen2 = efactory.createGeneralization();
            
        /* source (Specific) */
        RefOntoUML.Classifier e1 = (RefOntoUML.Classifier) gen1.getSpecific();
        org.eclipse.uml2.uml.Classifier e2 = (org.eclipse.uml2.uml.Classifier) GetElement(e1);        
        
        /* Poderia ter setado apenas um dos dois (Generalization::Specific, Classifier::Generalization), ja que sao EOpposites */
        gen2.setSpecific(e2);
        /* O Specific tem posse do generalization */        
        e2.getGeneralizations().add(gen2);

        /* target (General) */
        e1 = (RefOntoUML.Classifier) gen1.getGeneral();
        e2 = (org.eclipse.uml2.uml.Classifier) GetElement(e1);        

        gen2.setGeneral(e2);

        /* print out */
        outln("UML:Generalization :: "+gen2.getSpecific().getName()+"->"+gen2.getGeneral().getName());
        
        /* Important for GeneralizationSet */
        RelateElements (gen1, gen2);
     }
	
	 /** 
	  * Process the Generalizations of this Classifier. 
	  */	
     public void ProcessGeneralizations (RefOntoUML.Generalization gen)
     {        
        DealGeneralization (gen);               
     }
          
     /** 
      * GeneralizationSet. 
      */     
     public org.eclipse.uml2.uml.GeneralizationSet DealGeneralizationSet (RefOntoUML.GeneralizationSet gs1)
     {        
        org.eclipse.uml2.uml.GeneralizationSet gs2 = efactory.createGeneralizationSet();
             
        convertENamedElement(gs1, gs2);
                   
        out("UML:GeneralizationSet :: ");
        		
        /* Add all the generalizations */
        for  (RefOntoUML.Generalization gen1 : gs1.getGeneralization())
        {
        	org.eclipse.uml2.uml.Generalization gen2 = (org.eclipse.uml2.uml.Generalization) GetElement(gen1);
                     
        	/* print out */ 
        	out(gen2.getSpecific().getName()+"->"+gen2.getGeneral().getName()+"  "); 
        	
            /* Poderia ter setado apenas um dos dois (GeneralizationSet::Generalization, Generalization::GeneralizationSet), ja que sao EOpposites */
            gs2.getGeneralizations().add(gen2);
            gen2.getGeneralizationSets().add(gs2);
        }
             
        /* isCovering, isDisjoint */
        gs2.setIsCovering(gs1.isIsCovering());
        gs2.setIsDisjoint(gs1.isIsDisjoint());
       
        /* print out */
        out("isCovering="+gs2.isCovering()+", isDisjoint="+gs2.isDisjoint()+"\n");
        
        /* They are PackageableElements, don't forget it */
        RelateElements (gs1, gs2);
             
        return gs2;
     }     
}
