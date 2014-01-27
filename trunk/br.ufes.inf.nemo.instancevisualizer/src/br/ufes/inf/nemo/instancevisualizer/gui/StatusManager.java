package br.ufes.inf.nemo.instancevisualizer.gui;

public class StatusManager extends Thread {
	private MainWindow mainWindow;
	private String text;
	
	public StatusManager(MainWindow mainWindow, String status) {
		this.mainWindow = mainWindow;
		this.text = status;
		run();
	}
	
	public void run() {
		mainWindow.setStatus(text);
	}
}
