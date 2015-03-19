package br.ufes.inf.nemo.ontouml2uml;

import java.util.HashMap;
import java.util.List;

public abstract class Reificator {

	public abstract void run();
	public abstract String getLog();
	public abstract HashMap<RefOntoUML.Element, List<org.eclipse.uml2.uml.Element>> getMap();
	public abstract String getConstraints();
}
