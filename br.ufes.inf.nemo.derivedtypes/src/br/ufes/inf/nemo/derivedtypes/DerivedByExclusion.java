package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class DerivedByExclusion extends DerivedType{

	
	
	public DerivedByExclusion(){
		
		//TABELA 1
		//linha 1
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("SUBKIND");
		tableValues.put("kind"+"Subkind", value);

	}

	
}
