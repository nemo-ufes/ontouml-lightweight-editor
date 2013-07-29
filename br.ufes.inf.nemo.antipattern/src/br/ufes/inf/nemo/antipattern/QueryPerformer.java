package br.ufes.inf.nemo.antipattern;

import java.io.File;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.ParserException;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Meronymic;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.MomentClass;
import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.SortalClass;
import RefOntoUML.SubKind;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;
import br.ufes.inf.nemo.antipattern.tri.TRIAntiPattern;
import br.ufes.inf.nemo.antipattern.util.AlloyConstructor;
import br.ufes.inf.nemo.common.list.Combination;
import br.ufes.inf.nemo.common.ontoumlparser.ComponentOfInference;
import br.ufes.inf.nemo.common.ontoumlparser.MaterialInference;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

public class QueryPerformer {
	
	public static void main(String[] args) throws Exception {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		// Register the default resource factory -- only needed for stand-alone!
		@SuppressWarnings("unused")
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		//URI fileURI = URI.createFileURI(new File("models/XML Models/ImpreciseAbstraction.xmi").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RoaadTrafficAccident.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/teste_comp_inference.refontouml").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/Modelos/Biodiversity.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/twin_test.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOS_regular_and_inverted.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
		
		OntoUMLParser parser = new OntoUMLParser(m);
		System.out.println("Model loaded: "+ m.getName());
		
		try {
			/*ArrayList<Association> associations = new ArrayList<>();
			associations.addAll(parser.getAllInstances(Association.class));
			associations.removeAll(parser.getAllInstances(Derivation.class));
			
			Combination combinationAssociations = new Combination(associations, 2);
			ArrayList<Association> combination;
			
			while(combinationAssociations.hasNext()){
				combination = combinationAssociations.next();
				Classifier a1Source = (Classifier) combination.get(0).getMemberEnd().get(0).getType();
				Classifier a1Target = (Classifier) combination.get(0).getMemberEnd().get(1).getType();
				Classifier a2Source = (Classifier) combination.get(1).getMemberEnd().get(0).getType();
				Classifier a2Target = (Classifier) combination.get(1).getMemberEnd().get(1).getType();
				
				if((overlap(a1Source,a2Source,parser) && overlap(a1Target,a2Target,parser)) || (overlap(a1Source,a2Target,parser) && overlap(a1Target,a2Source,parser))){
					System.out.println(a1Source.getName() + " : " + combination.get(0).getName() + " : " + a1Target.getName());
					System.out.println(a2Source.getName() + " : " + combination.get(1).getName() + " : " + a2Target.getName());
					System.out.println("-----------");
				}
			}
			
			for (Class c1 : parser.getAllInstances(RefOntoUML.Class.class)) {
				for (Class c2 : parser.getAllInstances(RefOntoUML.Class.class)) {
					
					String over = c1.getName() + " & " + c2.getName() + ": " + overlap(c1,c2,parser);
					System.out.println(over);
				}
			}*/
			
			
			/*
			int i = 0;
			System.out.println("|||||||||||||||||||||||||||||||||||||||||");
			for (Association a : parser.getAllInstances(Association.class)) {
				Type t1,t2;
				
				t1 = a.getMemberEnd().get(0).getType();
				t2 = a.getMemberEnd().get(1).getType();
				
				if(!(t1 instanceof DataType) && !( t2 instanceof DataType) && !(t1.equals(t2)) ){
				String name, predicate_rule, predicate;
				
				name = "overlaps"+parser.getAlias(t1)+parser.getAlias(t2)+i;
				
				predicate_rule = "some w:World | some w."+parser.getAlias(t1)+" & w."+parser.getAlias(t2);
				
				predicate = AlloyConstructor.AlloyParagraph(name, predicate_rule, AlloyConstructor.PRED);
				predicate += AlloyConstructor.RunCheckCommand(name, "10", "1", AlloyConstructor.PRED)+"\n";
				
				System.out.println(predicate+"\n");
				
				i++;
				}
				
				
			}
			System.out.println("|||||||||||||||||||||||||||||||||||||||||");
			
			/*ArrayList<TRIAntiPattern> result01 = AntiPatternIdentifier.identifyTRI(parser);
		    System.out.println("#Twin Relator Instances Antipatterns: "+result01.size()+"\n");
		    
		    for (TRIAntiPattern tri : result01) {
		    	//System.out.println(tri+"\n");
		    	System.out.println(tri.explanation(parser)+"\n\n");
		    	
		    	System.out.println(tri.duplicateInstantiationPattern.description()+"\n\n");
		    	
		    	ArrayList<InstantiationPatternParameter> ippList = new ArrayList<>();
		    	ippList.add(new InstantiationPatternParameter(tri.getRelator(), 2));
		    	
		    	for (Property p : tri.getProblematicMediatedProperties()) {
					ippList.add(new InstantiationPatternParameter(p,1)); 
				}
		    	
		    	System.out.println(tri.duplicateInstantiationPattern.predicate(ippList)+"\n\n");
		    	System.out.println(tri.distinctInstantiationPatter.predicate(ippList)+"\n\n");
		    	//System.out.println(tri.explanation(parser)+"\n");
		    		    	
		    }*/
		   /* System.out.println("**************************************************************");
		    
		    
			ArrayList<RWRTAntiPattern> result00 = AntiPatternIdentifier.identifyRWRT(parser);
		    System.out.println("#Relator with Rigid Type Antipatterns: "+result00.size()+"\n");
		    
		    for (RWRTAntiPattern rwrt : result00) {
		    	System.out.println(rwrt.explanation()+"\n");
		    	
		    	ArrayList<InstantiationPatternParameter> ippList = new ArrayList<>();
		    	ippList.add(new InstantiationPatternParameter(rwrt.getRelator(), 2));
		    	
		    	for (Property p : rwrt.getRigidMediatedProperties()) {
					ippList.add(new InstantiationPatternParameter(p,3)); 
				} 	
		    	
		    	System.out.println(rwrt.changingInstantiationPattern.predicate(ippList, parser));
		    	System.out.println(rwrt.creationalInstantiationPattern.predicate(ippList, parser));
		    	
		    	ippList.remove(0);
		    	
		    	System.out.println(rwrt.changingInstantiationPattern.description(ippList));
		    	System.out.println(rwrt.creationalInstantiationPattern.description(ippList));
		    	
		    	System.out.println("=========================================================");
		    }
		    System.out.println("**************************************************************");
		    
		    /*
		    ArrayList<STRAntiPattern> result = AntiPatternIdentifier.identifySTR(parser);
		    System.out.println("#SelfTypeRelationship Antipatterns: "+result.size()+"\n");
		    
		    for (STRAntiPattern str : result) {
		    	System.out.println(str+"\n");
		    	/*System.out.println(str.generateTransitivePredicate(4,mapper));
		    	System.out.println(str.generateIntransitivePredicate(4,mapper));
		    	System.out.println(str.generateReflexivePredicate(4,mapper));
		    	System.out.println(str.generateIrreflexivePredicate(4,mapper));
		    	System.out.println(str.generateSymmetricPredicate(4,mapper));
		    	System.out.println(str.generateAntisymmetricPredicate(4,mapper));
		    	
		    }
		    System.out.println("**************************************************************");*/
		   
		    /* 
		    ArrayList<RWORAntiPattern> result21 = AntiPatternIdentifier.identifyRWOR(parser);
		    System.out.println("Number of identified RWOR Anti-Patterns: "+result21.size()+"\n");
		    for (RWORAntiPattern rwor : result21){
		    	System.out.println(rwor);
		    	System.out.println(rwor.generateExclusivePredicate(mapper, 1));
		    	System.out.println(rwor.generateOverlappingPredicate(mapper, 1));*/
		    	
		    	/*ArrayList<Classifier> classes = new ArrayList<>();
		    	classes.addAll(rwor.getMediations().values());
		    	System.out.println(rwor.generateExclusiveOcl(classes));
		    	
		    	
		    	System.out.println("#######");*/
		    	
		    	/*	
		    	ArrayList<Mediation> list1, list2;
		    	ArrayList<ArrayList<Mediation>> matrix = new ArrayList<>();
		    	
		    	list1 = new ArrayList<>(); 
		    	list1.addAll(rwor.getMediations().keySet());
		    	list1.remove(0);
		    	matrix.add(list1);
		    	System.out.println(rwor.generateMultipleExclusivePredicate(matrix, mapper, 1));
		    	
		    	list2 = new ArrayList<>();
		    	list2.addAll(rwor.getMediations().keySet());
		    	list2.remove(list2.size()-1);
		    	list2.remove(list2.size()-1);
		    	matrix.add(list2);
		    	System.out.println(rwor.generateMultipleExclusivePredicate(matrix, mapper, 1));
		    	
		    	System.out.println("#######");
		    }
		    System.out.println("**************************************************************");*/
		    
		    /*
		    ArrayList<RBOSAntiPattern> result3 = AntiPatternIdentifier.identifyRBOS(parser);
		    System.out.println("#Relation Between Overlapping Subtypes Antipatterns: "+result3.size()+"\n");
		    for (RBOSAntiPattern rbos : result3){
		    	System.out.println(rbos);
		    	System.out.println(rbos.generateIrreflexiveOcl());
		    	System.out.println(rbos.generateReflexiveOcl());
		    	System.out.println(rbos.generateDisjointPredicate(mapper));
		    	System.out.println(rbos.generateOverlappingPredicate(mapper));
		    }
		    System.out.println("**************************************************************");*/
		    /*
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(parser);
		    System.out.println("#Relation Specialization Antipatterns: "+result4.size()+"\n");
		    for (RSAntiPattern rs : result4) {
		    	System.out.println(rs);
		    	/*System.out.println(rs.generateOcl(RSAntiPattern.SUBSET));
		    	System.out.println(rs.generateOcl(RSAntiPattern.REDEFINE));
		    	System.out.println(rs.generateOcl(RSAntiPattern.NONSUBSET));
		    	System.out.println(rs.generateOcl(RSAntiPattern.DISJOINT));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.SUBSET));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.REDEFINE));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.NONSUBSET));
		    	System.out.println(rs.generatePredicate(mapper, RSAntiPattern.DISJOINT));
		    	System.out.println();
		    }*/
		  /*  System.out.println("**************************************************************");
		    
		    ArrayList<SSRAntiPattern> result5 = AntiPatternIdentifier.identifySSR(parser);
		    
		    System.out.println("#SSR: "+result5.size()+"\n");
		    
		    for (SSRAntiPattern ssr : result5){
		    
		    	System.out.println(ssr+"\n");
		    
		    }
		    System.out.println("**************************************************************");
		    
		    /*
		    ArrayList<ACAntiPattern> result6 = AntiPatternIdentifier.identifyAC(parser);
			System.out.println("# of Generic Cycles Identified: "+result6.size());
			for (ACAntiPattern ac : result6) {
				System.out.println(ac);
				System.out.println(ac.generateCycleOcl(ACAntiPattern.CLOSED));
				System.out.println(ac.generateCycleOcl(ACAntiPattern.OPEN));
				System.out.println(ac.generatePredicate(parser, 2, ACAntiPattern.OPEN));
				System.out.println(ac.generatePredicate(parser, 2, ACAntiPattern.CLOSED));
			}*/
			
		    
		    ComponentOfInference d = new ComponentOfInference(parser);
		    
		    parser = d.infer();
		    System.out.println("Inferred Compositions: "+d.getInferredCompositions().size());
		    for (componentOf c : d.getInferredCompositions()) {
				System.out.println(parser.getStringRepresentation(c));
			}
		    /*
		    System.out.println("***********");
		    ComponentOfInference d2 = new ComponentOfInference(parser);
		    
		    parser = d2.infer();
		    System.out.println("Inferred Compositions: "+d2.getInferredCompositions().size());
		    
		    for (componentOf cp1 : parser.getAllInstances(componentOf.class)) {
		    	for (componentOf cp2 : parser.getAllInstances(componentOf.class)) {
					if (!cp1.equals(cp2) && cp1.whole().equals(cp2.whole()) && cp1.part().equals(cp2.part())){
						System.out.println("DUPLICATE COMPOSITION: ");
						System.out.println("\t"+parser.getStringRepresentation(cp1) + " - W1: " +parser.getStringRepresentation(cp1.whole())+ ", P1: "+parser.getStringRepresentation(cp1.part()) );
						System.out.println("\t"+parser.getStringRepresentation(cp2) + " - W2: " +parser.getStringRepresentation(cp2.whole())+ ", P2: "+parser.getStringRepresentation(cp2.part()) );
					}
				}	
			}
		    
		    /*for (componentOf c : d2.getInferredCompositions()) {
				System.out.println(parser.getStringRepresentation(c));
				
				if(c.getEAnnotations().size()>0)
					System.out.println(c.getEAnnotations().get(0).getSource());
				
				System.out.println("----------------");
			}*/
		    MaterialInference mi = new MaterialInference(parser);
		    parser = mi.infer();
		    
		    for (MaterialAssociation mat : mi.getInferredMaterials()) {
		    
		    	String saida = 	mat.getMemberEnd().get(0).getType().getName()+" ("+mat.getMemberEnd().get(0).getLower()+")..("+mat.getMemberEnd().get(0).getUpper()+") "+
		    					"<Material> "+mat.getName()+
		    					" ("+mat.getMemberEnd().get(1).getLower()+")..("+mat.getMemberEnd().get(1).getUpper()+") "+ mat.getMemberEnd().get(1).getType().getName();
		    	System.out.println(saida);
		    }
				    		    
		} catch (Exception e) {
		    // record failure to parse
		    //valid = false;
			e.printStackTrace();
		}
		
		ResourceUtil.saveReferenceOntoUML("/Users/tiagoprince/Desktop/test.refontouml", m);
		
		
		/*for (EObject k : parser.getAllInstances(AntiRigidSortalClass.class)) {
			System.out.println(parser.getStringRepresentation(k));
		}*/
		
        
	}
	
	
	public static boolean overlap(Classifier c1, Classifier c2, OntoUMLParser parser) throws Exception{
	
		//equal types always overlap
		if (c1.equals(c2))
			return true;
		
		//objects, moments and Datatypes never overlap
		
		char c1Type = ' ', c2Type = ' ';
		
		if (c1 instanceof ObjectClass)
			c1Type = 'o';
		else if (c1 instanceof MomentClass)
			c1Type = 'm';
		else if (c1 instanceof DataType)
			c1Type = 'd';
		else
			return false;
		
		if (c2 instanceof ObjectClass)
			c2Type = 'o';
		else if (c2 instanceof MomentClass)
			c2Type = 'm';
		else if (c2 instanceof DataType)
			c2Type = 'd';
		else
			return false;
		
		if(c1Type!=c2Type)
			return false;
		
			
		if (c1 instanceof ObjectClass && c2 instanceof MomentClass || c1 instanceof Object)
			return false;
		
		//distinct substance sortal types (kinds, quantities, collectives) never overlap
		if(c1 instanceof SubstanceSortal && c2 instanceof SubstanceSortal)
			return false;
		
		//a relator never overlaps with a mode
		if(c1 instanceof Relator && c2 instanceof Mode || c1 instanceof Mode && c2 instanceof Relator)
			return false;
		
		
		//in this point, c1 and c2 are both objectClasses, both Relators, or Both Modes.
		//in all remainder combinations of stereotypes, if one type is an ancestor (direct or indirect supertype), the types overlap.
		//c2 ascendant of c1
		ArrayList<Classifier> c1Parents = new ArrayList<>();
		c1Parents.addAll(c1.allParents());
		if (c1Parents.contains(c2))
			return true;
		
		//c1 ascendant of c2
		ArrayList<Classifier> c2Parents = new ArrayList<>();
		c2Parents.addAll(c2.allParents());
		if (c2Parents.contains(c1))
			return true;
		
		ArrayList<Classifier> commonParents = new ArrayList<>();
		commonParents.addAll(c1Parents);
		commonParents.retainAll(c2Parents);
		
		if(commonParents.size()==0)
			return false;
		
		
		if((c1 instanceof MomentClass && c2 instanceof MomentClass) || (c1 instanceof SortalClass && c2 instanceof SortalClass)) {
			for (GeneralizationSet gs : parser.getAllInstances(GeneralizationSet.class)) {
					
					EList<Generalization> generalizations = gs.getGeneralization();
					
					if(generalizations.size()>0 && commonParents.contains(generalizations.get(0).getGeneral()))
					{
						for (Generalization g1 : generalizations) {
							for (Generalization g2 : generalizations) {
								if(!g1.equals(g2)){
									if((g1.getSpecific().equals(c1) || g1.getSpecific().allChildren().contains(c1)) && (g2.getSpecific().equals(c2) || g2.getSpecific().allChildren().contains(c2)))
										return false;
								}
							}
						}
					}
			}
				return true;
			}
		
		return false;
			
	}
	
	
	public static Class getIdentityProvider (Class c) throws Exception{
		
		if (c instanceof SubKind || c instanceof Role || c instanceof Phase){
			ArrayList<Class> identityProviders = new ArrayList<>();
			
			for (Classifier parent : c.allParents()) {
				if(parent instanceof SubstanceSortal)
					identityProviders.add((Class) parent);
			}
			
			if(identityProviders.size()==1)
				return identityProviders.get(0);
			else if (identityProviders.size()==0)
				return null;
			else
				throw new Exception("Type "+c.getName()+" has two identity providers. Correct the model!");
		}
		
		if (c instanceof SubstanceSortal)
			return (SubstanceSortal) c;
		
		return null;
		
	}
	
	
}