package br.ufes.inf.nemo.common.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayListOperations {
	
	 public static <T> List<T> union(List<T> list1, List<T> list2) 
	 {
	        Set<T> set = new HashSet<T>();

	        set.addAll(list1);
	        set.addAll(list2);

	        return new ArrayList<T>(set);
	    }

	   public static <T> List<T> intersection(List<T> list1, List<T> list2) 
	   {
	        List<T> list = new ArrayList<T>();

	        for (T t : list1) {
	            if(list2.contains(t)) {
	                list.add(t);
	            }
	        }

	        return list;
	    }

	public static <T,S> boolean hasIntersection(Collection<T> list1, Collection<S> list2){
		for (T o : list1) {
			if(list2.contains(o))
				return true;
		}
		return false;
	}
}
