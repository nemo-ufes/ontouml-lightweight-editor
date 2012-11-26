package ontouml2text.core;

import java.util.Set;

import RefOntoUML.*;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;


public class Writer {

	public static String Write (RefOntoUML.Model model)
	{
		String modelDescr = new String();
		
		OntoUMLParser ontoParser = new OntoUMLParser(model);
		
		Set<Generalization> genSet = ontoParser.getGeneralizations();
		
		for (Generalization gen : genSet)
		{
			modelDescr += gen.getSpecific().getName() + " é subtipo de " + gen.getGeneral().getName() + "\n";
		}
		
		return modelDescr;
	}
}
