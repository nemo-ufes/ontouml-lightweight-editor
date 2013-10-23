package br.ufes.inf.nemo.oled.modellingassistant.core;

import RefOntoUML.RefOntoUMLFactory;

public class Util {

	public static RefOntoUMLFactory factory = RefOntoUMLFactory.eINSTANCE;

	public static RefOntoUML.Class createClass(String stereotype){

		RefOntoUML.Class newclass = null;

		// Criar elementos do tipo Class
		if (stereotype.equalsIgnoreCase("kind")) {
			newclass = factory.createKind();
		} 
		else if (stereotype.equalsIgnoreCase("subkind")) {
			newclass = factory.createSubKind();
		} 
		else if (stereotype.equalsIgnoreCase("role")) {
			newclass = factory.createRole();
		} 
		else if (stereotype.equalsIgnoreCase("phase")) {
			newclass = factory.createPhase();
		}
		else if (stereotype.equalsIgnoreCase("category")) {
			newclass = factory.createCategory();
		}
		else if (stereotype.equalsIgnoreCase("collective")) {
			newclass = factory.createCollective();
		}
		else if (stereotype.equalsIgnoreCase("mixin")) {
			newclass = factory.createMixin();
		}
		else if (stereotype.equalsIgnoreCase("mode")) {
			newclass = factory.createMode();
		}
		else if (stereotype.equalsIgnoreCase("quantity")) {
			newclass = factory.createQuantity();
		}
		else if (stereotype.equalsIgnoreCase("relator")) {
			newclass = factory.createRelator();
		}
		else if (stereotype.equalsIgnoreCase("rolemixin")) {
			newclass = factory.createRoleMixin();
		}
		else
		{
			newclass = factory.createClass();
		}

		return newclass;
	}
}
