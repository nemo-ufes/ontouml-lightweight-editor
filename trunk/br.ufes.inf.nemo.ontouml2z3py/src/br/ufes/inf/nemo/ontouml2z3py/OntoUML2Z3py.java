package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import RefOntoUML.Generalization;
import RefOntoUML.Kind;
import RefOntoUML.PrimitiveType;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;
import br.ufes.inf.nemo.z3py.Z3pyFactory;

public class OntoUML2Z3py {
	
	
	//public OntoUMLParser ontoparser;
	//public Z3pyFactory factory;
	
		
	public static void main(String[]args){
		
		
		try {
			
			Resource resource = ResourceUtil.loadReferenceOntoUML("models/Exemplo2.refontouml");
			RefOntoUML.Package root  = (RefOntoUML.Package)resource.getContents().get(0);
			OntoUMLParser ontoparser = new OntoUMLParser(root);
			
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
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
