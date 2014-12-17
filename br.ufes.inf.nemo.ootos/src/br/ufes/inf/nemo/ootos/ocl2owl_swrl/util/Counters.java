package br.ufes.inf.nemo.ootos.ocl2owl_swrl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import br.ufes.inf.nemo.ootos.ocl2owl_swrl.tags.Tag;

public class Counters{
	public class Counter{
		public int totalCount = 0;
		public int successCount = 0;
		public int unsuccessCount = 0;
		public int warningCount = 0;
		@SuppressWarnings("rawtypes")
		public ArrayList<Class> unsucessReasons = new ArrayList<Class>();
		@SuppressWarnings("rawtypes")
		public ArrayList<Class> warningReasons = new ArrayList<Class>();
		public Counter() {

		}
	}

	public enum ResultStatus {
		Success, Unsuccess, Warning;
	}
	public HashMap<String, Counter> counters = new HashMap<String, Counter>();
	public int total = 0;
	public int totalSuccess = 0;
	public int totalUnsuccess = 0;
	public int totalWarning = 0;

	public void updateCounters(String stereotype, ResultStatus resultStatus, Exception e){
		Counter counter;

		if(this.counters.get(stereotype) == null){
			counter = new Counter();
			this.counters.put(stereotype, counter);
		}

		counter = this.counters.get(stereotype);

		total++;
		counter.totalCount++;
		if(resultStatus.equals(ResultStatus.Success)){
			totalSuccess++;
			counter.successCount++;
		}else if(resultStatus.equals(ResultStatus.Unsuccess)){
			totalUnsuccess++;
			counter.unsuccessCount++;
			if(!counter.unsucessReasons.contains(e.getClass())){
				counter.unsucessReasons.add(e.getClass());	
			}
		}else{
			totalWarning++;
			counter.warningCount++;
			if(!counter.warningReasons.contains(e.getClass())){
				counter.warningReasons.add(e.getClass());	
			}
		}

		//System.out.println();
	}

	public String getReturnMessage(){
		String ret = "";

		ret += "\n\n";
		ret += "Stereotype";
		ret += "\t\t";
		ret += "Total";
		ret += "\t\t";
		ret += "Successfully";
		ret += "\t\t";
		ret += "Warnings";
		ret += "\t\t";
		ret += "Unsuccessfully";
		ret += "\t\t";
		ret += "Warning Reasons";
		ret += "\t\t";
		ret += "Unsuccess Reasons";
		ret += "\n";
		
		int percent = 0;
		
		Iterator<String> countersIterator = this.counters.keySet().iterator();

		while(countersIterator.hasNext()){
			String stereotype = countersIterator.next();
			Counter counter = this.counters.get(stereotype);
			//ret += "Stereotype ";
			if(Tag.isTag(stereotype)){
				stereotype = "(tag) " + stereotype;
			}
			ret += stereotype;
			//ret += ":";
			

			//ret += "\n\t";
			if(stereotype.length() > 12){
				ret += "\t";
			}else{
				ret += "\t\t";
			}			
			//ret += "Total: ";
			ret += counter.totalCount;
			//ret += "\n\t";
			ret += "\t\t";
			//ret += "Successfully transformed: ";
			ret += counter.successCount;
			percent = (int) (((double)(counter.successCount)/(double)(counter.totalCount))*100);
			ret += " (" + percent + "%)";
			/*
			if(percent >= 90){
				ret += " \\o/\\o\\/o/";
			}else if(percent >= 80){
				ret += "\\o/\\o\\";
			}else if(percent >= 70){
				ret += "\\o/";
			}
			*/
			//ret += "\n\t";
			ret += "\t\t";
			ret += counter.warningCount;
			percent = (int) (((double)(counter.warningCount)/(double)(counter.totalCount))*100);
			ret += " (" + percent + "%)";
			
			ret += "\t\t";
			//ret += "Unsuccessfully transformed: ";
			ret += counter.unsuccessCount;
			percent = (int) (((double)(counter.unsuccessCount)/(double)(counter.totalCount))*100);
			ret += " (" + percent + "%)";
			
			/*
			if(counter.unsucessReasons.size() > 0){
				ret += "\n\tReasons:";
			}
			*/
			ret += "\t\t";
			for (@SuppressWarnings("rawtypes") Class reason : counter.warningReasons) {
				//ret += "\n\t\t";
				ret += reason.getName().replace("br.ufes.inf.nemo.ocl2owl_swrl.exceptions.", "");
				if(counter.warningReasons.indexOf(reason) < counter.warningReasons.size()-1){
					ret += ", ";
				}
			}
			
			ret += "\t\t";
			for (@SuppressWarnings("rawtypes") Class reason : counter.unsucessReasons) {
				//ret += "\n\t\t";
				ret += reason.getName().replace("br.ufes.inf.nemo.ocl2owl_swrl.exceptions.", "");
				if(counter.unsucessReasons.indexOf(reason) < counter.unsucessReasons.size()-1){
					ret += ", ";
				}
			}
			
			ret += "\n";
		}
		
		
		//ret += "\n\n";
		ret += "\n\t\t";
		//ret += "Total of rule(s): ";
		ret += this.total;
		//ret += "\n";
		ret += "\t\t";
		//ret += "Rule(s) successfully transformed: ";
		ret += this.totalSuccess;
		percent = (int) (((double)(this.totalSuccess)/(double)(this.total))*100);
		ret += " (" + percent + "%) ";
		/*
		if(percent >= 90){
			ret += " \\o/\\o\\/o/";
		}else if(percent >= 80){
			ret += "\\o/\\o\\";
		}else if(percent >= 70){
			ret += "\\o/";
		}
		*/
		//ret += "\n";
		ret += "\t\t";
		//ret += "Rule(s) unsuccessfully transformed: ";
		ret += this.totalWarning;
		percent = (int) (((double)(this.totalWarning)/(double)(this.total))*100);
		ret += " (" + percent + "%) ";
		
		ret += "\t\t";
		//ret += "Rule(s) unsuccessfully transformed: ";
		ret += this.totalUnsuccess;
		percent = (int) (((double)(this.totalUnsuccess)/(double)(this.total))*100);
		ret += " (" + percent + "%) ";
		ret += "\n";
		

		return ret;
	}

}