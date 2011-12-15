package br.inf.ufes.nemo.transformation.ontouml2alloy.v3.util;

import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.impl.RefOntoUMLPackageImpl;

public class RefOntoUMLHelper {

	private static RefOntoUMLFactory factory;
	private static boolean initialized = false;

	private RefOntoUMLHelper() {
	}

	public static void initializeHelper() {
		RefOntoUMLPackageImpl.init();
		factory = RefOntoUMLFactory.eINSTANCE;		
		initialized = true;
	}

	public static RefOntoUMLFactory getFactory() {
		if (!initialized) {
			initializeHelper();
		}
		return factory;
	}

}
