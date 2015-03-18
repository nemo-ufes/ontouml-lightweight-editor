package br.ufes.inf.nemo.ontouml2uml;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Reificator {

	public abstract void run();
	public abstract String getLog();
	public abstract HashMap<RefOntoUML.Element, ArrayList<org.eclipse.uml2.uml.Element>> getMap();
	public abstract String getConstraints();
}
