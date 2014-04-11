package br.ufes.inf.nemo.ocl2owl_swrl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import br.ufes.inf.nemo.ocl2owl_swrl.tags.Tag;

public class Counters{
	public class Counter{
		public int totalCount = 0;
		public int successCount = 0;
		public int unsuccessCount = 0;
		public ArrayList<Class> unsucessReasons = new ArrayList<Class>();
		
		public Counter() {
			
		}
	}
	
	public HashMap<String, Counter> counters = new HashMap<String, Counter>();
	public int total = 0;
	public int totalSuccess = 0;
	public int totalUnsuccess = 0;
	
	public void updateCounters(String stereotype, Boolean success, Exception e){
		Counter counter;
		
		if(this.counters.get(stereotype) == null){
			counter = new Counter();
			this.counters.put(stereotype, counter);
		}
		
		counter = this.counters.get(stereotype);
		
		total++;
		counter.totalCount++;
		if(success){
			totalSuccess++;
			counter.successCount++;
		}else{
			totalUnsuccess++;
			counter.unsuccessCount++;
			if(!counter.unsucessReasons.contains(e.getClass())){
				counter.unsucessReasons.add(e.getClass());
			}
		}
		
		System.out.println();
	}
	
	public String getreturnMessage(){
		String ret = "";
		
		ret += "\n\n";
		ret += "Total of rule(s): " + this.total;
		ret += "\n";
		ret += "Rule(s) successfully transformed: " + this.totalSuccess;
		int percent = (int) (((double)(this.totalSuccess)/(double)(this.total))*100);
		ret += " (" + percent + "%)";
		ret += "\n";
		ret += "Rule(s) unsuccessfully transformed: " + this.totalUnsuccess;
		ret += "\n\n";
		
		Iterator<String> countersIterator = this.counters.keySet().iterator();

		while(countersIterator.hasNext()){
		  String stereotype = countersIterator.next();
		  
		  ret += "Stereotype ";
		  if(Tag.isTag(stereotype)){
			  ret += "(tag) ";
		  }
		  ret += stereotype + ":";
		  
		  Counter counter = this.counters.get(stereotype);
		  
		  ret += "\n\t";
		  ret += "Total: " + counter.totalCount;
		  ret += "\n\t";
		  ret += "Successfully transformed: " + counter.successCount;
		  percent = (int) (((double)(counter.successCount)/(double)(counter.totalCount))*100);
		  ret += " (" + percent + "%)";
		  ret += "\n\t";
		  ret += "Unsuccessfully transformed: " + counter.unsuccessCount;
		  ret += "\n";
		  
		}
		
		return ret;
	}
	
}