package br.inf.ufes.nemo.oled.ui;

/**
 * Interface to be implemented by classes who should listen to the applications status messages
 * 
 * @author Antognoni Albuquerque
 * @version 1.0
 */

public interface StatusListener {
	/**
	 * Reports the listened status
	 * @param status
	 */
	void reportStatus(String status);
}
