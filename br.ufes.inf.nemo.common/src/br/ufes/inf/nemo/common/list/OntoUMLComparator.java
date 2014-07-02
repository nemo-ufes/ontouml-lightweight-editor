package br.ufes.inf.nemo.common.list;

import java.util.Comparator;

import RefOntoUML.NamedElement;

public class OntoUMLComparator<T extends NamedElement> implements Comparator<T>{
	
	@Override
	public int compare(T arg0, T arg1) {
		if(arg0.getName()==null && arg1.getName()==null)
			return 0;
		if(arg0.getName()==null && arg1.getName()!=null)
			return -1;
		if(arg0.getName()!=null && arg1.getName()==null)
			return 1;
	
		return arg0.getName().compareTo(arg1.getName());
	}

}
