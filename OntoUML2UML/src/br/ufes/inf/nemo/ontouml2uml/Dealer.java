package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author John Guerson
 */

public class Dealer {
		
	/** Creates UML Objects */	
	public org.eclipse.uml2.uml.UMLFactory myfactory;
	
    /** Maps RefOntoUML Elements to UML Elements (auxiliar for Properties, Generalizations and GeneralizationSets) */  	 
    public HashMap <RefOntoUML.Element,org.eclipse.uml2.uml.Element> mymap;
    
    /* ============================================================================*/
    
    public RefOntoUML.Element getKeyByValue(org.eclipse.uml2.uml.Element value) 
    {
        for (Entry<RefOntoUML.Element,org.eclipse.uml2.uml.Element> entry : mymap.entrySet()) 
        {
            if (value.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }
    
    /* ============================================================================*/
    
    /** Constructor. */
	public Dealer()
    {
		myfactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
        mymap = new HashMap<RefOntoUML.Element, org.eclipse.uml2.uml.Element>();
    }
	
	/* ============================================================================*/
	
	/** Method for output. */
    public static void outln(String output) 
    {
    	//System.out.println(output);
    	OntoUML2UML.logDetails += output+"\n";
    }

	/** Method for output. */
    public static void out(String output) 
    {
    	//System.out.print(output);
    	OntoUML2UML.logDetails += output;
    }
    
    /** Method for err output. */
    public static void err(String error) 
    {
        //System.err.println(error);
        OntoUML2UML.logDetails += error+"\n";
    }
	
    /* ============================================================================*/
    
    public void RelateElements (RefOntoUML.Element c1, org.eclipse.uml2.uml.Element c2)
    {
        mymap.put(c1, c2);
    }

    public org.eclipse.uml2.uml.Element GetElement (RefOntoUML.Element e1)
    {
        return mymap.get(e1);
    }
	
    /* ============================================================================*/
    
    /** Named Element. */
	public void DealNamedElement (RefOntoUML.NamedElement ne1, org.eclipse.uml2.uml.NamedElement ne2)
    {    	
    	/* name */
        ne2.setName(ne1.getName());        
        
    	/* visibility */
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
    
	/* ============================================================================*/
	
	/** Property. */	
	public void DealProperty (RefOntoUML.Property p1, org.eclipse.uml2.uml.Property p2)
    {            
        DealNamedElement (p1, p2);
        /* isLeaf (RedefinableElement) */
        p2.setIsLeaf(p1.isIsLeaf());        
                
        /* isStatic (Feature) */
        p2.setIsStatic(p1.isIsStatic());        
        
        /* isReadOnly (StructuralFeature) */
        p2.setIsReadOnly(p1.isIsReadOnly());                      
        
        /* isDerived */
        p2.setIsDerived(p1.isIsDerived());
        
        /* lower, upper (MultiplicityElement) */            
        org.eclipse.uml2.uml.LiteralInteger lowerValue = myfactory.createLiteralInteger();
        org.eclipse.uml2.uml.LiteralUnlimitedNatural upperValue = myfactory.createLiteralUnlimitedNatural();
        lowerValue.setValue(p1.getLower());
        upperValue.setValue(p1.getUpper()); 
            
        p2.setLowerValue(lowerValue);
        p2.setUpperValue(upperValue);
                
        /* Type (TypedElement) */
        org.eclipse.uml2.uml.Type t2 = (org.eclipse.uml2.uml.Type) GetElement(p1.getType());
        p2.setType(t2);              
        
        /* aggregation */
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
	
	/* ============================================================================*/
	
	/** Classifier. */	
	public void DealClassifier (RefOntoUML.Classifier c1, org.eclipse.uml2.uml.Classifier c2)
    {
        DealNamedElement (c1, c2);
        /* Important for Generalization, Property */
        RelateElements (c1, c2);        
        /* Is Abstract */
        c2.setIsAbstract(c1.isIsAbstract());        
    }
	
	/* ============================================================================*/
	
	/** Package */
	public void DealPackage( RefOntoUML.Package p1, org.eclipse.uml2.uml.Package p2)
	{
		DealNamedElement(p1, p2);
		outln("UML:Package :: name="+p2.getName()+", visibility="+p2.getVisibility().getName());
		RelateElements(p1,p2);
	}
	
	/* ============================================================================*/
	
	/** Class. */	
	public void DealClass (RefOntoUML.Class c1, org.eclipse.uml2.uml.Class c2)
    {		
        DealClassifier (c1, c2);
           		
        /* Modified name */
   		//c2.setNameExpression(c2.createNameExpression(refmapper.getName(c1), (org.eclipse.uml2.uml.Type)c2));
   		
        /* print out */
        outln("UML:Class :: name="+c2.getName()+", visibility="+c2.getVisibility().getName()+", isAbstract="+c2.isAbstract());        
        
        /* Attributes */
        org.eclipse.uml2.uml.Property p2;
        for (RefOntoUML.Property p1 : c1.getAttribute())
        {
            p2 = myfactory.createProperty();
            DealProperty(p1, p2);       
            
            /* Modified Name */
            //p2.setNameExpression(p2.createNameExpression(refmapper.getName(p1), p2.getType()));
            
            /* print out */
            outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
            ", type="+p2.getType().getName()+", aggregationkind="+p2.getAggregation().getName()+", visibility="+p2.getVisibility().getName()+            
            ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());
            
            c2.getOwnedAttributes().add(p2);
        }         

    }
			
	/* ============================================================================*/
	
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
	
	/* ============================================================================*/
	
	/** Association. */	
	public org.eclipse.uml2.uml.Association DealAssociation (RefOntoUML.Association a1)
    {
		org.eclipse.uml2.uml.Association a2 = createAssociation(a1);
		
        DealClassifier(a1, a2);
        
        /* isDerived */
        a2.setIsDerived(a1.isIsDerived());        
        
        /* print out */
        outln("UML:Association :: name="+a2.getName()+", visibility="+a2.getVisibility().getName()+", isAbstract="+a2.isAbstract()+
        ", isDerived="+a2.isDerived());
      
        // memberEnds 
        org.eclipse.uml2.uml.Property p2;
        for (int i = 0; i < a1.getMemberEnd().size();i++)
        {
        	RefOntoUML.Property p1 = a1.getMemberEnd().get(i);
        	p2 = a2.getMemberEnds().get(i);
            
            DealNamedElement (p1, p2);
            
            /* isLeaf (RedefinableElement) */
            p2.setIsLeaf(p1.isIsLeaf());        
                    
            /* isStatic (Feature) */
            p2.setIsStatic(p1.isIsStatic());        
            
            /* isReadOnly (StructuralFeature) */
            p2.setIsReadOnly(p1.isIsReadOnly());                      
            
            /* isDerived */
            p2.setIsDerived(p1.isIsDerived());
            outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
            ", type="+p2.getType().getName()+", aggregationkind="+p2.getAggregation().getName()+", visibility="+p2.getVisibility().getName()+            
            ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());            
            
            /* Modified Name */
            //p2.setNameExpression(p2.createNameExpression(refmapper.getName(p1), p2.getType()));
            
            RelateElements(p1, p2);
        }
        
        return a2;
    }
	
	/* ============================================================================*/
	
	/** create Association. */	
	public org.eclipse.uml2.uml.Association createAssociation (RefOntoUML.Association a1)	
    {
    	RefOntoUML.Property src = a1.getMemberEnd().get(0);
		RefOntoUML.Property tgt = a1.getMemberEnd().get(1);
		RefOntoUML.Type source = src.getType();
		RefOntoUML.Type target = tgt.getType();		
		
		// inverse
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
    	    	
    	/* Modified Name */
   		//a2.setNameExpression(a2.createNameExpression(refmapper.getName(a1), (org.eclipse.uml2.uml.Type)a2));
   		
    	return a2;
    }
		
	/* ============================================================================*/
	
	/** Generalization. */	
	public void DealGeneralization (RefOntoUML.Generalization gen1)
    {	
        org.eclipse.uml2.uml.Generalization gen2 = myfactory.createGeneralization();
            
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
	
	/* ============================================================================*/
	
	 /** Process the Generalizations of this Classifier. */	
     public void ProcessGeneralizations (RefOntoUML.Generalization gen)
     {        
        DealGeneralization (gen);               
     }
      
     /* ============================================================================*/
     
     /** PrimitiveType. */     
     public org.eclipse.uml2.uml.PrimitiveType DealPrimitiveType (RefOntoUML.PrimitiveType dt1)
     {
    	 org.eclipse.uml2.uml.PrimitiveType dt2 = null;
         dt2 = myfactory.createPrimitiveType();
            
         DealClassifier (dt1, dt2);
             
         /* print out */                          
         outln("UML:PrimitiveType :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());             
          
         /* Attributes */
         org.eclipse.uml2.uml.Property p2; 
         for (RefOntoUML.Property p1 : dt1.getAttribute())
         {
             p2 = myfactory.createProperty();
             DealProperty(p1, p2);                     
                 
             /* print out */
             outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
             ", type="+p2.getType().getName()+", aggregationkind="+p2.getAggregation().getName()+", visibility="+p2.getVisibility().getName()+            
             ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());
                     
             dt2.getOwnedAttributes().add(p2);
         }                          
         return dt2;
     }
     
     /** Enumeration. */     
     public org.eclipse.uml2.uml.Enumeration DealEnumeration (RefOntoUML.Enumeration dt1)
     {
    	 org.eclipse.uml2.uml.Enumeration dt2 = null;
         dt2 = myfactory.createEnumeration();
            
         DealClassifier (dt1, dt2);
             
         /* print out */                          
         outln("UML:Enumeration :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());             
          
         /* Attributes */
         org.eclipse.uml2.uml.Property p2; 
         for (RefOntoUML.Property p1 : dt1.getAttribute())
         {
             p2 = myfactory.createProperty();
             DealProperty(p1, p2);                     
                 
             /* print out */
             outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
             ", type="+p2.getType().getName()+", aggregationkind="+p2.getAggregation().getName()+", visibility="+p2.getVisibility().getName()+            
             ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());
                     
             dt2.getOwnedAttributes().add(p2);
         }                          
         return dt2;
     }
     
     
     /** DataType. */     
     public org.eclipse.uml2.uml.DataType DealDataType (RefOntoUML.DataType dt1)
     {
         org.eclipse.uml2.uml.DataType dt2 = null;
           
         dt2 = myfactory.createDataType();                          
           
         DealClassifier (dt1, dt2);
         
         /* Modified Name */
       	 //dt2.setNameExpression(dt2.createNameExpression(refmapper.getName(dt1), (org.eclipse.uml2.uml.Type)dt2));
       	 
         /* print out */                          
         outln("UML:DataType :: name="+dt2.getName()+", visibility="+dt2.getVisibility().getName()+", isAbstract="+dt2.isAbstract());
             
         /* Attributes */
         org.eclipse.uml2.uml.Property p2; 
         for (RefOntoUML.Property p1 : dt1.getAttribute())
         {
             p2 = myfactory.createProperty();
             DealProperty(p1, p2);                     
                     
             /* print out */
             outln("UML:Property :: "+"name="+p2.getName()+", isDerived="+p2.isDerived()+", lower="+p2.getLower()+", upper="+p2.getUpper()+
             ", type="+p2.getType().getName()+", aggregationkind="+p2.getAggregation().getName()+", visibility="+p2.getVisibility().getName()+            
             ", isLeaf="+p2.isLeaf()+", isStatic="+p2.isStatic()+", isReadOnly="+p2.isReadOnly());
                     
             dt2.getOwnedAttributes().add(p2);
          }                          
          return dt2;
     }
     
     /* ============================================================================*/
     
     /** GeneralizationSet. */     
     public org.eclipse.uml2.uml.GeneralizationSet DealGeneralizationSet (RefOntoUML.GeneralizationSet gs1)
     {        
        org.eclipse.uml2.uml.GeneralizationSet gs2 = myfactory.createGeneralizationSet();
             
        DealNamedElement(gs1, gs2);
                             
        /* print out */
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
	
     /* ============================================================================*/
     
     /** Dependency. */     
     public org.eclipse.uml2.uml.Dependency DealDependency (RefOntoUML.Dependency d1)
     {             
        org.eclipse.uml2.uml.Dependency d2 = myfactory.createDependency();
          
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
     
     /* ============================================================================*/
     
     /** Comment. */
     /*public void DealComment (RefOntoUML.Comment c1, org.eclipse.uml2.uml.Comment c2)
     {             
        // body 
    	if (c1.isSetBody()) 
    	{    		
    		c2.setBody(c1.getBody());
    	}    	
        // annotatedElements
        for (RefOntoUML.Element a1 : c1.getAnnotatedElement())
        {        	        	
        	org.eclipse.uml2.uml.Element a2 = GetElement(a1);        	
        
        	c2.getAnnotatedElements().add(a2);        
        }             
        RelateElements (c1, c2);
     } */    
     
     /* ============================================================================*/
     
     /** Process RefOntoUML Comments of this Element */
     /*public void ProcessComments (RefOntoUML.Element e1)
     {
    	 org.eclipse.uml2.uml.Element e2 = GetElement (e1);
         
         // ownedComment
    	 org.eclipse.uml2.uml.Comment c2;
         for (RefOntoUML.Comment c1 : e1.getOwnedComment())
         {
             c2 = myfactory.createComment();             
             DealComment (c1, c2);
             
             if (e2!=null) e2.getOwnedComments().add(c2);                          
         }
     }*/
    
}

