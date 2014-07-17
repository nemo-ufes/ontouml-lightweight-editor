package br.ufes.inf.nemo.story;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.ExternalCrossReferencer;
import org.eclipse.emf.ecore.util.EcoreUtil.ProxyCrossReferencer;

import stories.AntiRigidClass;
import stories.Link;
import stories.Node;
import stories.Node_state;
import stories.StoriesFactory;
import stories.Story;
import stories.Story_element;
import stories.World;
import stories.util.StoriesResourceFactoryImpl;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.RefOntoUMLResourceFactoryImpl;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

public class OntoUMLStoryCrafter {
		
	public OntoUMLParser ontoparser;
	public String dirPath;	
	public String filePath;
	/**
	 * Constructor
	 * @throws IOException 
	 */
	public OntoUMLStoryCrafter () throws IOException//(OntoUMLParser refparser, String fPath) throws IOException 
	{
		//filePath = fPath;
		//dirPath = filePath.substring(0,filePath.lastIndexOf(File.separator)+1);
		//ontoparser = refparser;	
		Resource resource = ResourceUtil.loadReferenceOntoUML("test_data/input/model_completo.refontouml");
		RefOntoUML.Package root = (RefOntoUML.Package)resource.getContents().get(0);
		OntoUMLParser gambiparser = new OntoUMLParser(root);
		ontoparser = gambiparser;
	}
	public String transform ()// throws Exception 
	{		
		
		for(RefOntoUML.Class c: this.ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			System.out.println(c.getName());
		}
		return " ";
	}
	
	/**
	 * 
	 * Returns a xmi resource and saves it on a file
	 * @param outputpath
	 * @param container
	 * @return
	 */	
	public static Resource saveStory (String filename, String outputpath, Story container) 
	{
		ResourceSet rset = new ResourceSetImpl();					
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",new StoriesResourceFactoryImpl());
		
		rset.getPackageRegistry().put(stories.StoriesPackage.eNS_URI,	stories.StoriesPackage.eINSTANCE);
		
		URI fileURI = URI.createFileURI(filename);    
				
	    final Resource resource = rset.createResource(fileURI);    	
	    
	    resource.getContents().add(container);    	
	
	    try{
	    	resource.save(new FileOutputStream(outputpath.concat(filename)),Collections.emptyMap());
	    	
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	    return resource;		   	
	}
	/**
	 * 
	 * Returns a xmi resource and saves it on a file
	 * @param outputpath
	 * @param container
	 * @return
	 */
	public static Resource saveStory (String filename, Story container) 
	{
		return saveStory(filename,"",container);
	}
	
	public static Resource loadStory (String filename, String path) throws IOException 
	{
		ResourceSet rset = new ResourceSetImpl();					
		
		rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",new StoriesResourceFactoryImpl());
		
		rset.getPackageRegistry().put(stories.StoriesPackage.eNS_URI,	stories.StoriesPackage.eINSTANCE);
		
		//Essa implementação está dando problemas com os URIs. Resolve mal. O problema é que na hora de resolver ele nao sabe o caminho e se o caminho está na URI dá um outro tipo de problema.
	    File file = new File(filename);
		URI fileURI = URI.createFileURI(filename);		
		Resource resource = rset.createResource(fileURI);		
		
		resource.load(new FileInputStream(path.concat(filename)),Collections.emptyMap());
		
		return resource;		
		
	}
	
	public static void main(String[] args) throws IOException {
		final OntoUMLStoryCrafter ont = new OntoUMLStoryCrafter();
		//uma interface permite que a pessoa crie instancias e selecione que classes do modelo ela instancia.
		//aqui vou fazer um exemplo de execução dessa interface. O usuário cria duas instancias, dois mundos, uma relação entre elas no segundo mundo.
		
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node j = factory.createNode();
		j.setLabel("John");
		Node m = factory.createNode();
		m.setLabel("Mary");
		m.getDifferent_from().add(j);
		Node k = factory.createNode();
		k.setLabel("Kaka");
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		World w3 = factory.createWorld();
		w1.setLabel("w1");
		w2.setLabel("w2");
		w3.setLabel("w3");
		m.getPresent_in().add(w1);
		m.getPresent_in().add(w2);
		j.getPresent_in().add(w1);		
		j.getPresent_in().add(w2);
		m.getAbsent_from().add(w3);
		
		Link l1 = factory.createLink();
		l1.setSource(m);
		l1.setTarget(j);
		
		l1.getAbsent_from().add(w1);
		l1.getPresent_in().add(w2);
		
		
		Node_state ns = factory.createNode_state();
		m.getStates().add(ns);
		ns.getClassified_in().add(w1);
		
		Node_state nsj = factory.createNode_state();
		j.getStates().add(nsj);
		nsj.getNot_classified_in().add(w1);
		
		
		Story container = factory.createStory();
		container.getElements().add(j);
		container.getElements().add(m);
		container.getElements().add(k);
		container.getElements().add(w1);
		container.getElements().add(w2);
		container.getElements().add(w3);
		container.getElements().add(l1);
		
		for(RefOntoUML.Class c: ont.ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			System.out.println(c.getName());
			if( "Pessoa".equals(c.getName())){
				
				
				j.getInstance_of().add(c);
				m.getInstance_of().add(c);
			}
			if( "Homem".equals(c.getName())){
				j.getInstance_of().add(c);
			}
			if( "Mulher".equals(c.getName())){
				m.getInstance_of().add(c);
			}
			if( "Estudante".equals(c.getName())){
				ns.getAntiRigidClasses().add(c);
				nsj.getAntiRigidClasses().add(c);
			}
			if( "Vivo".equals(c.getName())){
				ns.getAntiRigidClasses().add(c);
				nsj.getAntiRigidClasses().add(c);
			}
		}
		
		System.out.println(OntoUMLStoryCrafter.getAuxPredicates());//por enquanto está vazio
		System.out.println(container.generatePredicates());
		Resource res =OntoUMLStoryCrafter.saveStory("storytest.xmi", "test_data/output/", container);
		
		
		
		Story container2 = factory.createStory();
		Node x = factory.createNode();
		x.setLabel("x");
		x.getSame_as().add(j);
		container2.getElements().add(x);
		
		Resource res2 = OntoUMLStoryCrafter.saveStory("combined.xmi", "test_data/output/", container2);
		
		
		
		//System.out.println(container2.mergeReferences().generatePredicates());
		
		
		System.out.println(((Story)(loadStory("combined.xmi","test_data/output/").getContents().get(0))).mergeReferences());
		//System.out.println(((Story)(loadStory("combined.xmi","test_data/output/").getContents().get(0))).mergeReferences());
		
		
		//ont.transform();
		/*
		System.out.println(EcoreUtil.ProxyCrossReferencer.find(res2));
		
		
		TreeIterator<EObject> titer = res2.getAllContents();
		
		System.out.println("-----");
		while(titer.hasNext()){
			EObject abc = titer.next();
			System.out.println(abc);
		}
		System.out.println("-----");
		*/

		 


		
	}
	
	private static final String getAuxPredicates() {
		return "";//TODO: gerar os predicados auxiliares em associations.als
	}
}
