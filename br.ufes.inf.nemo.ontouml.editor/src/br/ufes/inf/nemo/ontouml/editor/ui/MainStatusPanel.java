package br.ufes.inf.nemo.ontouml.editor.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.ontouml.editor.util.Resources;

@SuppressWarnings("serial")
public class MainStatusPanel extends JPanel {

	private JLabel statusLabel = new JLabel();
	private JProgressBar memBar = new JProgressBar();
	private Timer timer = new Timer();	
	
	public MainStatusPanel()
	{
		super();
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(3, 3, 3, 3));
		add(statusLabel, BorderLayout.CENTER);
		
		memBar.setSize(new Dimension(50,15));
		memBar.setPreferredSize(new Dimension(50, 15));
				
		add(memBar, BorderLayout.EAST);
		
		updateMemBar();
		scheduleMemTimer();
	}
	
	private void scheduleMemTimer() {
		TimerTask task = new TimerTask() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						updateMemBar();
					}
				});
			}
		};
		
		// every 5 seconds
		timer.schedule(task, 2000, 5000);
	}
	
	private void updateMemBar()
	{
		memBar.setMinimum(0);
		memBar.setMaximum((int)Runtime.getRuntime().totalMemory());
		memBar.setValue((int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		memBar.setToolTipText(getMemString());
	}
	
	private String getMemString() {
		long free = Runtime.getRuntime().freeMemory();
		long total = Runtime.getRuntime().totalMemory();
		long used = total - free;
		used /= (1024 * 1024);
		total /= (1024 * 1024);
		return String.format(Resources.getString("mainstatuspanel.memString"), used, total);
	}

	public void showStatus(String status) {
		statusLabel.setText(status);
	}
}
