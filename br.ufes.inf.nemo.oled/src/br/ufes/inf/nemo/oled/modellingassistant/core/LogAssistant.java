package br.ufes.inf.nemo.oled.modellingassistant.core;

import java.util.ArrayList;
import java.util.Calendar;

public class LogAssistant {
	private static LogAssistant instance = new LogAssistant();
	private ArrayList<String> lstLog = new ArrayList<>();

	public static LogAssistant getInstance(){
		return instance;
	}

	public void addLogAction(String action){
		Calendar c = Calendar.getInstance();
		lstLog.add(action+"#"+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND)+"#"+c.get(Calendar.DAY_OF_MONTH)+":"+c.get(Calendar.MONTH)+":"+c.get(Calendar.YEAR));
	}

	public ArrayList<String> getListOfActions(){
		return lstLog;
	}

	@Override
	public String toString() {
		String ret = "";
		for(String s : lstLog){
			ret += s+"\n";
		}
		return ret;
	}
}
