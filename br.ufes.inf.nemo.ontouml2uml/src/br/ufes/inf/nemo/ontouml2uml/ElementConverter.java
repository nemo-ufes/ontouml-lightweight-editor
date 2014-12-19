package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.uml2.uml.EnumerationLiteral;

/**
 * This class transforms each OntoUML element into the equivalent UML element. 
 * We record each pair (OntoUML element, UML element) into a hash map.
 * We also follow the UML metamodel structure in transformation whenever is possible.
 * 
 * @author John Guerson
 *
 */

public class ElementConverter {
		
	public org.eclipse.uml2.uml.UMLFactory ufactory;	
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
     
	public ElementConverter()
    {
		ufactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
        umap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
    }
	
	public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> getMap()
	{
		return umap;
	}
	
	
	public static void outln(String output) 
    {
    	OntoUML2UML.log += output+"\n";
    }
	
    public static void out(String output) 
    {
    	OntoUML2UML.log += output;
    }
        
    public static void err(String error) 
    {
        OntoUML2UML.log += error+"\n";
    }
    
    
    public RefOntoUML.Element getKeyByValue(org.eclipse.uml2.uml.Element value) 
    {
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : umap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }       
	   
    
    public void RelateElements (RefOntoUML.Element c1, org.eclipse.uml2.uml.Element c2)
    {
        umap.put(c1, c2);
    }
   
    public org.eclipse.uml2.uml.Element GetElement (RefOntoUML.Element e1)
    {
        return umap.get(e1);
    }	
        
    /** 
     * Named Element (name & visibility).
     * 
     */
	public void DealNamedElement (RefOntoUML.NamedElement ne1, org.eclipse.uml2.uml.NamedElement ne2)
    {    	    	
        ne2.setName(ne1.getName());        
            	
        RefOntoUML.VisibilityKind vk1 = ne1.getVisibility();
        
        if (vk1.getValue() == RefOntoUML.VisibilityKind.PUBLIC_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PRIVATE_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PROTECTED_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL);
        }
        else if (vk1.getValue() == RefOntoUML.VisibilityKind.PACKAGE_VALUE)
        {
        	ne2.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL);
        }                
    }
    
	/** 
	 * Property. 
	 */	
	public void DealProperty (RefOntoUML.Property p1, org.eclipse.uml2.uml.Property p2)
    {            
        DealNamedElement (p1, p2);
               
        p2.setIsLeaf(p1.isIsLeaf());                
        p2.setIsStatic(p1.isIsStatic());       
        p2.setIsReadOnly(p1.isIsReadOnly());        
        p2.setIsDerived(p1.isIsDerived());
                            
        org.eclipse.uml2.uml.LiteralInteger lowerValue = ufactory.createLiteralInteger();
        org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = ufactory.createLiteralUnlimitedNatural();
        lowerValue.setValue(p1.getLower());
        upperValue.setValue(p1.getUpper());            
        p2.setLowerValue(lowerValue);
        p2.setUpperValue(upperValue);
        
        org.eclipse.uml2.uml.Type t2 = (org.eclipse.uml2.uml.Type) GetElement(p1.getType());
        p2.setType(t2);              
        
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
	 * Classifier. 
	 */	
	public void DealClassifier (RefOntoUML.Classifier c1, org.eclipse.uml2.uml.Classifier c2)
    {
        DealNamedElement (c1, c2);       
        RelateElements (c1, c2);
        
        c2.setIsAbstract(c1.isIsAbstract());        
    }
			
	/** 
	 * Package 
	 */
	public void DealPackage( RefOntoUML.Package p1, org.eclipse.uml2.uml.Package p2)
	{
		DealNamedElement(p1, p2);
		RelateElements(p1,p2);
		
		outln("UML:Package :: name="+p2.getName()+", visibility="+p2.getVisibility().getName());		
	}
			
	/** 
	 * Class. 
	 */	
	public void DealClass (RefOntoUML.Class c1, org.eclipse.uml2.uml.Class c2)
    {		
        DealClassifier (c1, c2);           		   		
       
        outln("UML:Class :: name="+c2.getName()+", visibility="+c2.getVisibility().getName()+", isAbstract="+c2.isAbstract());        
        
    }	
	
	/** 
	 * Class Attributes. 
	 */	
	public void DealAttribute (RefOntoUML.Class c1, org.eclipse.uml2.uml.Class c2)
    {	             
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getOwnedAttribute())
        {
            p2 = ufactory.createProperty();
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
	 * DataType Attributes. 
	 */	
	public void DealAttribute (RefOntoUML.DataType c1, org.eclipse.uml2.uml.DataType c2)
    {	
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            p2 = ufactory.createProperty();
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
	 * Association. 
	 */	
	public org.eclipse.uml2.uml.Association DealAssociation (RefOntoUML.Association a1)
    {
		org.eclipse.uml2.uml.Association a2 = createAssociation(a1);		
        if(a2==null) return null;
        DealClassifier(a1, a2);        
        a2.setIsDerived(a1.isIsDerived());        
        
        outln("UML:Association :: name="+a2.getName()+", visibility="+a2.getVisibility().getName()+", isAbstract="+a2.isAbstract()+
        ", isDerived="+a2.isDerived());
      
        // memberEnds 
        org.eclipse.uml2.uml.Property p2;
        for (int i = 0; i < a1.getMemberEnd().size();i++)
        {
        	RefOntoUML.Property p1 = a1.getMemberEnd().get(i);
        	p2 = a2.getMemberEnds().get(i);
            
            DealNamedElement (p1, p2);
            
            p2.setIsLeaf(p1.isIsLeaf());                    
            p2.setIsStatic(p1.isIsStatic());            
            p2.setIsReadOnly(p1.isIsReadOnly());            
            p2.setIsDerived(p1.isIsDerived());
                       
            String typeName = new String();
            String aggregationName = new String();
            String visibilityName = new String();
            
            if (p2.getType()==null) typeName = "null"; else typeName = p2.getType().getName();
            if (p2.getAggregation()==null) aggregationName = "null"; else aggregationName = p2.getAggregation().getName();
            if (p2.getVisibility()==null) visibilityName = "null"; else visibilityName = p2.getVisibility().getName();
            
            outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
            ", type="+typeName+", aggregationkind="+aggregationName+", visibility="+visibilityName+            
            ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());            
            
            RelateElements(p1, p2);
        }
        
        return a2;
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
        org.eclipse.uml2.uml.Generalization gen2 = ufactory.createGeneralization();
            
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
      * PrimitiveType. 
      */     
     public org.eclipse.uml2.uml.PrimitiveType DealPrimitiveType (RefOntoUML.PrimitiveType dt1,	org.eclipse.uml2.uml.PrimitiveType dt2)
     {                
         DealClassifier (dt1, dt2);
                                     
         outln("UML:PrimitiveType :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());             
                  
         return dt2;
     }
     
     /** 
      * Enumeration. 
      */     
     public org.eclipse.uml2.uml.Enumeration DealEnumeration (RefOntoUML.Enumeration dt1,org.eclipse.uml2.uml.Enumeration dt2)
     {    	 
         DealClassifier (dt1, dt2);
                                             
         outln("UML:Enumeration :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());             
          
         for (RefOntoUML.EnumerationLiteral ontoliteral: dt1.getOwnedLiteral())
         {
        	 EnumerationLiteral uliteral = ufactory.createEnumerationLiteral();
        	 uliteral.setName(ontoliteral.getName());
        	 dt2.getOwnedLiterals().add(uliteral);
        	 
        	 outln("UML:EnumerationLiteral :: name="+uliteral.getName());     
         }
         
         return dt2;
     }
     
     
     /** 
      * DataType. 
      */     
     public org.eclipse.uml2.uml.DataType DealDataType (RefOntoUML.DataType dt1)
     {
         org.eclipse.uml2.uml.DataType dt2 = null;
           
         dt2 = ufactory.createDataType();                          
           
         DealClassifier (dt1, dt2);
       	                         
         outln("UML:DataType :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());
             
         return dt2;
     }
     
     /** 
      * GeneralizationSet. 
      */     
     public org.eclipse.uml2.uml.GeneralizationSet DealGeneralizationSet (RefOntoUML.GeneralizationSet gs1)
     {        
        org.eclipse.uml2.uml.GeneralizationSet gs2 = ufactory.createGeneralizationSet();
             
        DealNamedElement(gs1, gs2);
                   
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
	
     /** 
      * Dependency. 
      */     
     public org.eclipse.uml2.uml.Dependency DealDependency (RefOntoUML.Dependency d1)
     {             
        org.eclipse.uml2.uml.Dependency d2 = ufactory.createDependency();
          
        DealNamedElement(d1, d2);
             
        org.eclipse.uml2.uml.NamedElement ne2;        
        
        /* clients */        
        for (RefOntoUML.NamedElement ne1 : d1.getClient())
        {
            ne2 = (org.eclipse.uml2.uml.NamedElement) GetElement(ne1);
            d2.getClients().add(ne2);                
        }         
        
        /* suppliers */        
        for (RefOntoUML.NamedElement ne1 : d1.getSupplier())
        {
            ne2 = (org.eclipse.uml2.uml.NamedElement) GetElement(ne1);
            d2.getSuppliers().add(ne2);             
        }                
        		
        /* They are PackageableElements, don't forget it */
        RelateElements (d1, d2);
             
        return d2;
     }
}

