package br.ufes.inf.nemo.ontouml.antipattern.util;

import RefOntoUML.Property;

public class AssociationEndNameGenerator {
	public static String associationEndName(Property p){
		
		String ae_name = p.getName();
				
		if(ae_name==null)
			ae_name = p.getType().getName().toLowerCase();
		
		return ae_name;
	}
}
