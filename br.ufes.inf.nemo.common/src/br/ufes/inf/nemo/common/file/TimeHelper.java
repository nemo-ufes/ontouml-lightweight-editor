package br.ufes.inf.nemo.common.file;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

	public static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static String getTime(){
		return dateFormat.format(new Date());
	}

}
