package br.ufes.inf.nemo.ontouml.antipattern;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.ContentTreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Model;
import RefOntoUML.Package;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.RefOntoUMLPackage;

public class teste {
	public static void main(String[] args) throws Exception {
		String log;
		Package m_copy;		
		Copier copier;
		
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		@SuppressWarnings("unused")
		RefOntoUMLPackage refMetaPack = RefOntoUMLPackage.eINSTANCE;
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// Get the URI of the model file.
		//URI fileURI = URI.createFileURI(new File("models/XML Models/GenericCycle.xmi").getAbsolutePath());
		URI fileURI = URI.createFileURI(new File("models/XML Models/Georreferenciamento.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/allAP.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/Surgery.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOS_regular_and_inverted.refontouml").getAbsolutePath());
		//URI fileURI = URI.createFileURI(new File("models/XML Models/RBOSSimple.xmi").getAbsolutePath());
		// Demand load the resource for this file.
		Resource resource = resourceSet.getResource(fileURI, true);
		
		// Print the contents of the resource to System.out. 
		RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;
		Model m = factory.createModel();
		
		m = (Model) resource.getContents().get(0);
			
		
		try {
    	    
			ArrayList<STRAntiPattern> list = AntiPatternIdentifier.identifySTR(m);
			System.out.println("#STR: "+list.size());
			ArrayList<EObject> selected; 
			
			for (STRAntiPattern str : list) {
				System.out.println("=====\n"+str+"\n");
				
				selected = new ArrayList<>();
				log = new String();
				copier = new Copier();
				
				selected.add(str.getAssociation());
								
				m_copy = Recreator.recreatePackage(selected, log, true, copier);
				System.out.println(log);
				for (EObject o : m_copy.getPackagedElement()) {
					System.out.println(o);
					if (o instanceof Classifier){
						for(Generalization g : ((Classifier) o).getGeneralization())
							System.out.println(g);
					}
				}
			}
		    System.out.println("**************************************************************");
		    
		    ArrayList<RWORAntiPattern> result21 = AntiPatternIdentifier.identifyRWOR(m);
		    System.out.println("#RWOR: "+result21.size()+"\n");
		    for (RWORAntiPattern rwor : result21){
		    	System.out.println("=====\n"+rwor+"\n");
		    	
		    	selected = new ArrayList<>();
				log = new String();
				copier = new Copier();
				
				selected.add(rwor.getRelator());
				selected.addAll(rwor.getMediations().keySet());
				
//				for(Classifier c : rwor.getMediations().values()){
//					selected.add(c);
//					selected.addAll(c.getGeneralization());
//				}
				
				m_copy = Recreator.recreatePackage(selected, log, true, copier);
				System.out.println(log);
				for (EObject o : m_copy.getPackagedElement()) {
					System.out.println(o);
					if (o instanceof Classifier){
						for(Generalization g : ((Classifier) o).getGeneralization())
							System.out.println(g);
					}
				}
				
		    }
		    System.out.println("**************************************************************");
		    
		    
		    ArrayList<RBOSAntiPattern> result3 = AntiPatternIdentifier.identifyRBOS(m);
		    System.out.println("#RBOS: "+result3.size()+"\n");
		    for (RBOSAntiPattern rbos : result3){
		    	System.out.println("=====\n"+rbos+"\n");
		    	
		    	selected = new ArrayList<>();
				log = new String();
				copier = new Copier();
				
				selected.add(rbos.getAssociation());
				m_copy = Recreator.recreatePackage(selected, log, true, copier);
				System.out.println(log);
				
				for (EObject o : m_copy.getPackagedElement()) {
					System.out.println(o);
					if (o instanceof Classifier){
						for(Generalization g : ((Classifier) o).getGeneralization())
							System.out.println(g);
					}
				}
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<RSAntiPattern> result4 = AntiPatternIdentifier.identifyRS(m);
		    System.out.println("#RS: "+result4.size()+"\n");
		    for (RSAntiPattern rs : result4) {
		    	System.out.println("=====\n"+rs+"\n");
		    	
		    	selected = new ArrayList<>();
				log = new String();
				copier = new Copier();
				
				selected.add(rs.getSpecific());
				selected.add(rs.getGeneral());
				
				m_copy = Recreator.recreatePackage(selected, log, true, copier);
				System.out.println(log);
				
				for (EObject o : m_copy.getPackagedElement()) {
					System.out.println(o);
					if (o instanceof Classifier){
						for(Generalization g : ((Classifier) o).getGeneralization())
							System.out.println(g);
					}
				}
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<IAAntiPattern> result5 = AntiPatternIdentifier.identifyIA(m);
		    
		    System.out.println("#Imprecise Abstractions Antipatterns: "+result5.size()+"\n");
		    for (IAAntiPattern ia : result5){
		    	System.out.println(ia+"\n");
		    
		    }
		    System.out.println("**************************************************************");
		    
		    ArrayList<ACAntiPattern> result6 = AntiPatternIdentifier.identifyAC(m);
			System.out.println("#AC: "+result6.size());
			for (ACAntiPattern ac : result6) {
				System.out.println("=====\n"+ac+"\n");
				
				selected = new ArrayList<>();
				log = new String();
				copier = new Copier();
				
				selected.addAll(ac.cycleRelationship);
				
				m_copy = Recreator.recreatePackage(selected, log, true, copier);
				System.out.println(log);
				
				for (EObject o : m_copy.getPackagedElement()) {
					System.out.println(o);
					if (o instanceof Classifier){
						for(Generalization g : ((Classifier) o).getGeneralization())
							System.out.println(g);
					}
				}
				
			
			}
				
		}
			catch (ParserException e) {
		    // record failure to parse
		    //valid = false;
		    System.err.println(e.getLocalizedMessage());
		}
		
	}
		
}
