package br.ufes.inf.nemo.pattern.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UtilExperiment implements Serializable{
	private static final long serialVersionUID = 1L;

	public static boolean IS_IN_EXPERIMENT = false;

	private static ArrayList<String> experimentStatus = new ArrayList<>();

	private static long start = 0;

	private static int openCount = 1;
	public static long getStartTime() {
		return start;
	}
	public static void startTimeCount(){
		start = System.currentTimeMillis();
	}

	public static String getTimeElapsedString(){
		long elapsed = System.currentTimeMillis() - start; 

		long hr = TimeUnit.MILLISECONDS.toHours(elapsed);
		long min = TimeUnit.MILLISECONDS.toMinutes(elapsed - TimeUnit.HOURS.toMillis(hr));
		long sec = TimeUnit.MILLISECONDS.toSeconds(elapsed - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));

		return String.format("%02d:%02d:%02d", hr, min, sec);
	}

	public static long getTimeElapsedLong(){
		long elapsed = System.currentTimeMillis() - start; 

		return elapsed;
	}

	public static ArrayList<String> getStatus() {
		return experimentStatus;
	}

	public static void addStatus(String status) {
		experimentStatus.add(status);
	}

	public static void printStatusInfo(){
		int applied = 0, stereotypeChanging = 0, relationChanging = 0;
		HashMap<String, Integer> hashApplied = new HashMap<String, Integer>();
		String sb = new String();

		for (String status : UtilExperiment.getStatus()) {
			if(status.contains("Applied")){
				applied++;
				if(!hashApplied.containsKey(status))
					hashApplied.put(status, 0);
				hashApplied.put(status, hashApplied.get(status)+1);
				sb += "\n"+status;
			}else if(status.contains("Reused") || status.contains("Deactivated")){
				sb += "\n\t"+status;
			}else if(status.contains("Changing the class")){
				stereotypeChanging++;
				sb += "\n"+status;
			}else if(status.contains("Changing the relation")){
				relationChanging++;
				sb += "\n"+status;
			}
		}
		sb += "\n\nTotal of pattern applications: "+applied;
		for(Map.Entry<String, Integer> entry: hashApplied.entrySet()){
			sb += "\n\t"+entry.getKey()+": "+entry.getValue();
		}

		sb += "\n\nTotal of stereotype changes: "+stereotypeChanging;
		
		sb += "\n\nTotal of relation stereotype changes: "+relationChanging;
		
		sb += "\n\nTime elapsed: "+UtilExperiment.getTimeElapsedString();
		
		sb += "\n\nTotal of times that the model was opened: "+openCount;
		
		sb += "\n";
		
		System.out.println(sb);
	}

	public static void setStatus(ArrayList<String> readObject) {
		experimentStatus = readObject;
	}
	public static void setStartTime(long readObject) {
		start = readObject;

	}
	public static void setOpenCount(int i) {
		openCount = i;
		
	}
	public static int getOpenCount() {
		return openCount;
	}


}
