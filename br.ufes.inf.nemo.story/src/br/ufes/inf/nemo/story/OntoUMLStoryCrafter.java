package br.ufes.inf.nemo.story;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import stories.Link;
import stories.Node;
import stories.StoriesFactory;
import stories.Story;
import stories.Story_element;
import stories.World;
import stories.util.StoriesResourceFactoryImpl;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
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
		Resource resource = ResourceUtil.loadReferenceOntoUML("test_data/input/model.refontouml");
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
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		w1.setLabel("w1");
		w2.setLabel("w2");
		w1.getPresent().add(m);
		w2.getPresent().add(m);
		w1.getPresent().add(j);		
		w2.getPresent().add(j);
		
		
		Link l1 = factory.createLink();
		l1.setSource(m);
		l1.setTarget(j);
		
		w1.getAbsent().add(l1);
		w2.getPresent().add(l1);
		
		Story container = factory.createStory();
		container.getElements().add(j);
		container.getElements().add(m);
		container.getElements().add(w1);
		container.getElements().add(w2);
		container.getElements().add(l1);
		
		RefOntoUML.Class pessoa;
		for(RefOntoUML.Class c: ont.ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			
			if( "Pessoa".equals(c.getName())){
				System.out.println(c.getName());
				pessoa = c;
				j.getInstance_of().add(pessoa);
				m.getInstance_of().add(pessoa);
			}
		}
		
		OntoUMLStoryCrafter.saveStory("storytest.xmi", "test_data/output/", container);
		//para usar várias histórias, precisarei usar essa combinação de xmi... mas vou deixar pra depois
		/*
		Story container2 = factory.createStory();
		Node x = factory.createNode();
		x.setLabel("x");
		x.getSame_as().add(j);
		container2.getElements().add(x);
			
		
		Resource res = OntoUMLStoryCrafter.saveStory("combined.xmi", "test_data/output/", container2);
		//ont.transform();
		Map<EObject, Collection<Setting>> map = EcoreUtil.ExternalCrossReferencer.find(res);
		
		System.out.println(map.toString());
		
		TreeIterator<EObject> titer = res.getAllContents();
		while(titer.hasNext()){
			System.out.println(titer.next());
		}
			
		*/
		System.out.print("pred total[");
		for(Story_element n : container.getElements())		
		{
			System.out.print(n.getLabel()+":");
			System.out.print(n.eClass().getName());
			System.out.print(",");
		}
		System.out.print("]{}");
	}
}
