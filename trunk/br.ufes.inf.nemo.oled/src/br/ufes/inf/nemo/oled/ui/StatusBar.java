package br.ufes.inf.nemo.oled.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class StatusBar extends JPanel implements StatusListener{

	private static final long serialVersionUID = -1470943434794934781L;
	private JLabel statusLabel = new JLabel();
	private JLabel barTextLabel = new JLabel();
	//private JLabel coordLabel = new JLabel("    ");
	//private JLabel memLabel = new JLabel("    ");
	private JProgressBar memBar = new JProgressBar();
	private transient Timer timer = new Timer();	

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public StatusBar()
	{
		super(new BorderLayout());
		
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		add(statusLabel, BorderLayout.CENTER);
		
		//BorderLayout thisLayout = new BorderLayout();
		//this.setLayout(thisLayout);
		
		//this.add(coordLabel, BorderLayout.EAST);
		//add(coordLabel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.add(barTextLabel);
		panel.add(memBar);
		
		memBar.setMinimum(0);
		memBar.setMaximum((int)Runtime.getRuntime().totalMemory());
		memBar.setValue((int)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		memBar.setStringPainted(true);
		memBar.setToolTipText(getMemString());	
		barTextLabel.setText("Memory Usage:");
		
		memBar.setSize(new Dimension(50, 20));
		add(panel, BorderLayout.EAST);
						
		scheduleMemTimer();
		
	}

	/**
	 * Shows the inputed text in the status bar
	 * @param the text 
	 */
	public void reportStatus(String status)
	{
		statusLabel.setText(status);
	}
	
	/**
	 * Sets up and starts the timer task.
	 */
	private void scheduleMemTimer() {
		TimerTask task = new TimerTask() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						memBar.setMinimum(0);
						memBar.setMaximum((int)Runtime.getRuntime().totalMemory());
						memBar.setValue((int)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
						memBar.setStringPainted(true);
						memBar.setToolTipText(getMemString());	
						barTextLabel.setText("Memory Usage:");
						//memLabel.setText(getMemString());
					}
				});
			}
		};
		// every 5 seconds
		timer.schedule(task, 2000, 5000);
	}
	
	/**
	 * Creates the memory information string.
	 * @return the memory status string
	 */
	
	private String getMemString() {
		long free = Runtime.getRuntime().freeMemory();
		long total = Runtime.getRuntime().totalMemory();
		long used = total - free;
		used /= (1024 * 1024);
		total /= (1024 * 1024);
		return String.format("Used: %dM Total: %dM   ", used, total);
	}
	
}
