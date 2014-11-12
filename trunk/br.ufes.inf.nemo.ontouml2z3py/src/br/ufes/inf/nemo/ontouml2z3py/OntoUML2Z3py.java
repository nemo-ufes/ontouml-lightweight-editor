package br.ufes.inf.nemo.ontouml2z3py;

import java.io.IOException;
import java.io.PrintWriter;

import br.ufes.inf.nemo.z3py.OntoUMLZ3System;

public class OntoUML2Z3py {

	public static void main(String[] args) throws IOException {
		boolean isBranchInTime = true;
		String modelName = "ExemploCompletoSatisfativel";

		Transformer t = new Transformer("models/" + modelName + ".refontouml");
		OntoUMLZ3System system = t.run(isBranchInTime);
		if (isBranchInTime)
			modelName = modelName.concat("BT");
		else
			modelName = modelName.concat("LT");
	
		// System.out.println(system);
		PrintWriter writer = new PrintWriter("models/" + modelName + "2.py","UTF-8");
		writer.println(system);
		//
		writer.close();

	}
}
