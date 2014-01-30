package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.resource.ResourceUtil;

public class OntoUML2Z3py {

	public static void main(String[]args){
		
		try {
			
			Resource resource = ResourceUtil.loadReferenceOntoUML("models/ExemploZ3.refontouml");
			RefOntoUML.Package root  = (RefOntoUML.Package)resource.getContents().get(0);
			
			System.out.println(root);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
