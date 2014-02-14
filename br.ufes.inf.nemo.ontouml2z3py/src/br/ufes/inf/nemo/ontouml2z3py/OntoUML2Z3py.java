package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;

import br.ufes.inf.nemo.z3py.OntoUMLZ3System;

public class OntoUML2Z3py {
	
	
	public static void main(String[]args) throws IOException{
					
			Transformer t = new Transformer("models/KindPhaseSpecializationSet.refontouml");
			OntoUMLZ3System system = t.run(true);
			
		
			System.out.println(system);
			
	}
}
