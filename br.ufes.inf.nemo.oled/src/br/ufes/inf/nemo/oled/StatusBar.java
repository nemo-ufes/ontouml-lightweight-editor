package br.ufes.inf.nemo.oled;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.oled.ui.StatusListener;


public class StatusBar extends JPanel implements StatusListener{

	private static final long serialVersionUID = -1470943434794934781L;
	private JLabel statusLabel = new JLabel();
	private JLabel barTextLabel = new JLabel();
	private JLabel zoomLabel = new JLabel();
	//private JLabel coordLabel = new JLabel("    ");
	//private JLabel memLabel = new JLabel("    ");
	private JProgressBar memBar = new JProgressBar();
	private transient Timer timer = new Timer();	
	private final JSeparator separator = new JSeparator();

	public Timer getTimer() {
		return timer;
	}

	public void clearStatus()
	{
		statusLabel.setText("");
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public StatusBar()
	{
		super(new BorderLayout());
		
		setBorder(new EmptyBorder(3, 3, 3, 3));
		
		add(statusLabel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(450,36));
		//BorderLayout thisLayout = new BorderLayout();
		//this.setLayout(thisLayout);
		
		//this.add(coordLabel, BorderLayout.EAST);
		//add(coordLabel, BorderLayout.WEST);
		
		zoomLabel = new JLabel();
		zoomLabel.setText("Zoom: 100%");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
				
		memBar.setMinimum(0);
		memBar.setMaximum((int)Runtime.getRuntime().totalMemory());
		memBar.setValue((int)(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		memBar.setStringPainted(true);
		memBar.setToolTipText(getMemString());	
		barTextLabel.setText("Memory Usage:");
		
		memBar.setSize(new Dimension(50, 20));
		add(panel, BorderLayout.EAST);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(zoomLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barTextLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(memBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(memBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
								.addComponent(zoomLabel)
								.addComponent(barTextLabel))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
						
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
	
	public void reportZoomPercentual(String percentual)
	{
		zoomLabel.setText("Zoom: "+percentual+"%");
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
