package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;
import java.util.Hashtable;

import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;
import br.ufes.inf.nemo.z3py.OntoUMLZ3System;

public class OntoUML2Z3py {
	
	
	public static void main(String[]args) throws IOException{
		
		
/*		try {
			
			Resource resource = ResourceUtil.loadReferenceOntoUML("models/Exemplo2.refontouml");
			RefOntoUML.Package root  = (RefOntoUML.Package)resource.getContents().get(0);
			OntoUMLParser ontoparser = new OntoUMLParser(root);
			Z3pyFactory factory = new Z3pyFactoryImpl();
			
			OntoUMLZ3System system = factory.createOntoUMLZ3System();
			system.setFileName("models/Exemplo2.py");
				
			
			System.out.println("Root: " + root);
			
			for(RefOntoUML.Class p: ontoparser.getAllInstances(RefOntoUML.Class.class)){
				System.out.println("Name:" + p.getName() + " - Alias: " + ontoparser.getAlias(p)+ " - Qualif. Name: " + p.getClass().getName());
				if (p instanceof Kind){
					System.out.println("KIND");
							
				}
				
			}
			
					
			for(Generalization p: ontoparser.getAllInstances(Generalization.class)){
				System.out.println("Generalization: "+ p.getGeneral() +" / " +p.getSpecific());
			}
*/
			Transformer t = new Transformer("models/ErroKindHerdaDeSub.refontouml");
			OntoUMLZ3System system = t.run();
			
		
			System.out.println(system);
			
	}
}
