package br.ufes.inf.nemo.story;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import stories.Link;
import stories.Node;
import stories.Node_state;
import stories.StoriesFactory;
import stories.Story;
import stories.World;
import stories.util.StoriesResourceFactoryImpl;
import RefOntoUML.parser.OntoUMLParser;
import RefOntoUML.util.RefOntoUMLResourceUtil;


public class OntoUMLStoryCrafter {
		
	public OntoUMLParser ontoparser;
	public String dirPath;	
	public String filePath;
	/**
	 * Constructor
	 * @throws IOException 
	 */
	public OntoUMLStoryCrafter (String input) throws IOException//(OntoUMLParser refparser, String fPath) throws IOException 
	{
		//filePath = fPath;
		//dirPath = filePath.substring(0,filePath.lastIndexOf(File.separator)+1);
		//ontoparser = refparser;	

		Resource resource = RefOntoUMLResourceUtil.loadModel(input);

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
	
	private static void teste1() throws IOException{
		final OntoUMLStoryCrafter ont = new OntoUMLStoryCrafter("test_data/input/model_completo.refontouml");
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
		
		
		
		System.out.println(container2.mergeReferences().generatePredicates());
	}
	private static void teste2() throws IOException{
		final OntoUMLStoryCrafter ont = new OntoUMLStoryCrafter("test_data/input/artefato.refontouml");
		//uma interface permite que a pessoa crie instancias e selecione que classes do modelo ela instancia.
		//aqui vou fazer um exemplo de execução dessa interface. O usuário cria duas instancias, dois mundos, uma relação entre elas no segundo mundo.
		
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node j = factory.createNode();
		j.setLabel("John");
		Node m = factory.createNode();
		m.setLabel("Mary");
		m.getDifferent_from().add(j);
		Node doc = factory.createNode();
		doc.setLabel("Doc1");
		Node onto = factory.createNode();
		onto.setLabel("Onto1");
		Node diag1 = factory.createNode();
		diag1.setLabel("Diag1");
		Node diag2 = factory.createNode();
		diag2.setLabel("Diag2");
		
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
		l1.setSource(j);
		l1.setTarget(doc);
		
		Link l2= factory.createLink();
		l2.setSource(m);
		l2.setTarget(doc);
		
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
		container.getElements().add(onto);
		container.getElements().add(doc);
		container.getElements().add(diag1);
		container.getElements().add(diag2);
		
		container.getElements().add(w1);
		container.getElements().add(w2);
		container.getElements().add(w3);
		container.getElements().add(l1);
		container.getElements().add(l2);
		
		for(RefOntoUML.Class c: ont.ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			System.out.println(c.getName());
			if( "Artefato".equals(c.getName())){
				onto.getInstance_of().add(c);
				doc.getInstance_of().add(c);
				diag1.getInstance_of().add(c);
				diag2.getInstance_of().add(c);
			}
			
		}
		
		System.out.println(OntoUMLStoryCrafter.getAuxPredicates());//por enquanto está vazio
		System.out.println(container.generatePredicates());
		Resource res =OntoUMLStoryCrafter.saveStory("artefato_test.xmi", "test_data/output/", container);
		
	}
	private static void bancoStory() throws IOException{
		final OntoUMLStoryCrafter ont = new OntoUMLStoryCrafter("test_data/input/bank.refontouml");
		//uma interface permite que a pessoa crie instancias e selecione que classes do modelo ela instancia.
		//aqui vou fazer um exemplo de execução dessa interface. O usuário cria duas instancias, dois mundos, uma relação entre elas no segundo mundo.
		
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node j = factory.createNode();
		j.setLabel("John");
		Node s = factory.createNode();
		s.setLabel("Santander");
		Node jacc = factory.createNode();
		jacc.setLabel("Johns_Account");
		Node atm = factory.createNode();
		atm.setLabel("UFES_Santander_ATM");
		Node with = factory.createNode();
		with.setLabel("withdraw");
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		
		w1.setLabel("w1");
		w2.setLabel("w2");
		
		s.getPresent_in().add(w1);
		s.getPresent_in().add(w2);
		
		j.getPresent_in().add(w1);		
		j.getPresent_in().add(w2);
		
		//bank maintains account
		Link l1 = factory.createLink();
		l1.setSource(s);
		l1.setTarget(jacc);
				
		l1.getPresent_in().add(w1);
		l1.getPresent_in().add(w2);
		
		//account belongs to client
		Link l2 = factory.createLink();
		l2.setSource(jacc);
		l2.setTarget(j);
				
		l2.getPresent_in().add(w1);
		l2.getPresent_in().add(w2);
		
		//ATM withdraw 
		Link l3 = factory.createLink();
		l3.setSource(atm);
		l3.setTarget(with);
				
		l3.getAbsent_from().add(w1);
		l3.getPresent_in().add(w2);
		
		Link l4 = factory.createLink();
		l4.setSource(with);
		l4.setTarget(jacc);
				
		l4.getAbsent_from().add(w1);
		l4.getPresent_in().add(w2);
		
		Story container = factory.createStory();
		container.getElements().add(j);
		container.getElements().add(s);
		container.getElements().add(jacc);
		container.getElements().add(atm);
		container.getElements().add(with);
		
		container.getElements().add(w1);
		container.getElements().add(w2);
		
		container.getElements().add(l1);
		container.getElements().add(l2);
		container.getElements().add(l3);
		container.getElements().add(l4);
		
		for(RefOntoUML.Class c: ont.ontoparser.getAllInstances(RefOntoUML.Class.class))		
		{
			System.out.println(c.getName());
			if( "Person".equals(c.getName())){
				j.getInstance_of().add(c);
			}
			if( "Account".equals(c.getName())){
				jacc.getInstance_of().add(c);
			}
			if( "Bank".equals(c.getName())){
				s.getInstance_of().add(c);
			}
			if( "ATM".equals(c.getName())){
				atm.getInstance_of().add(c);
			}
		}
		
		System.out.println(OntoUMLStoryCrafter.getAuxPredicates());//por enquanto está vazio
		System.out.println(container.generatePredicates());
		Resource res =OntoUMLStoryCrafter.saveStory("bank_test.xmi", "test_data/output/", container);
	}
	
	
	public static Story alpha(){
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node a = factory.createNode();
		a.setLabel("alpha");
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		a.getAbsent_from().add(w1);
		a.getPresent_in().add(w2);
		
		
		Story container = factory.createStory();
		container.getElements().add(a);
		container.getElements().add(w1);
		container.getElements().add(w2);
		return container;		
	}
	
	public static Story gama(){
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node gama = factory.createNode();
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		gama.getPresent_in().add(w1);
		gama.getAbsent_from().add(w2);
		
		Story container = factory.createStory();
		container.getElements().add(gama);
		container.getElements().add(w1);
		container.getElements().add(w2);
		return container;		
	}
	public static Story delta(){
		StoriesFactory factory = StoriesFactory.eINSTANCE;
		
		Node a = factory.createNode();
		
		Node_state delta = factory.createNode_state();
		a.getStates().add(delta);
		
		World w1 = factory.createWorld();
		World w2 = factory.createWorld();
		a.getPresent_in().add(w1);
		a.getAbsent_from().add(w2);
		
		Story container = factory.createStory();
		container.getElements().add(a);
		container.getElements().add(w1);
		container.getElements().add(w2);
		return container;		
	}		
	public static void main(String[] args) throws IOException {
		//teste1();
		bancoStory();
	}
	
	private static final String getAuxPredicates() {
		return "";//TODO: gerar os predicados auxiliares em associations.als
	}
}
