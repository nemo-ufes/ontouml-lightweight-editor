package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



@SuppressWarnings("unused")
public class DerivedByExclusion extends DerivedType{

	public static DerivedByExclusion instance = new DerivedByExclusion();
	
	public DerivedByExclusion(){
		
		//TABELA 1
		//linha 1
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("SUBKIND");
		tableValues.put("Kind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("SUBKIND");
		tableValues.put("SubKind"+"Kind", value);

	}

	@Override
	public ArrayList<String> inferStereotype(String stereotype_1,
			String stereotype_2) {
		// TODO Auto-generated method stub
		return tableValues.get(stereotype_1+stereotype_2);
	}

	public static DerivedByExclusion  getInstance(){
		return instance;
	}
	
}
