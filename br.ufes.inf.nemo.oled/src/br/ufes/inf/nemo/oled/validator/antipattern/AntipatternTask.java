package br.ufes.inf.nemo.oled.validator.antipattern;

import java.awt.Font;
import java.util.concurrent.CountDownLatch;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.oled.Main;

public class AntipatternTask extends SwingWorker<Void, Void>{
	private Antipattern<?> antipattern;
	private AntipatternInfo info;
	private JLabel label;
	private JLabel progressBarDescr;
	private JCheckBox checkBox;
	private JProgressBar progressBar;
	private int percentage;
	
	private CountDownLatch latch;
	private CountDownLatch preLatch;
	
	public AntipatternTask(Antipattern<?> antipattern, AntipatternInfo info, JLabel label, JCheckBox checkBox, 
			JProgressBar progressBar, JLabel progressBarDescr, int percentage, CountDownLatch latch, CountDownLatch preLatch) {
		this.antipattern = antipattern;
		this.label = label;
		this.checkBox = checkBox;
		this.progressBar = progressBar;
		this.percentage = percentage;
		this.progressBarDescr = progressBarDescr;
		this.info = info;
		this.latch = latch;
		this.preLatch = preLatch;
	}

	@Override
	protected Void doInBackground() throws Exception {	
		
		preLatch.await();
		
		updateStatus(info.getAcronym()+": Identifying...");
		antipattern.identify();
		
		return null;	
	}
	
	@Override
	protected void done() {
		label.setText("("+antipattern.getOccurrences().size()+")");						
		
		if(antipattern.getOccurrences().size()>0){
			checkBox.setFont(new Font(checkBox.getFont().getFontName(), Font.BOLD,checkBox.getFont().getSize()));
			label.setFont(new Font(label.getFont().getFontName(), Font.BOLD,label.getFont().getSize()));
			progressBar.setValue(progressBar.getValue()+percentage);
			updateStatus(info.getAcronym()+": "+antipattern.getOccurrences().size()+" occurrence(s) found!");
		}
		
		latch.countDown();
	}
	
	private void updateStatus(String s) {
		progressBarDescr.setText(s);
		Main.printOutLine(s);
		
	}

	
	

}
