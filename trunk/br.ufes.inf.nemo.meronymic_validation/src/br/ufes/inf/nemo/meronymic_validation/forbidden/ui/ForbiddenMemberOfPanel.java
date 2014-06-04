package br.ufes.inf.nemo.meronymic_validation.forbidden.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JProgressBar;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenTask;
import br.ufes.inf.nemo.meronymic_validation.forbidden.IntransitiveMemberOfTask;

public class ForbiddenMemberOfPanel extends JPanel {
	
	private static final long serialVersionUID = -2295763371176331010L;

	private ForbiddenTable table;
	private JProgressBar progressBar;
	private JButton checkButton;
	private JButton stopButton;
	private JButton closeButton;
	
	private OntoUMLParser parser;
	private ForbiddenTask<?> task;

	/**
	 * Create the panel.
	 */
	public ForbiddenMemberOfPanel(OntoUMLParser parser) {
		
		this.parser = parser; 
		
		JLabel lblNewLabel = new JLabel("The following memberOf contain inconsistencies:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		stopButton = new JButton("Stop");
		checkButton = new JButton("Check");
		checkButton.addActionListener(checkAction);
		closeButton = new JButton("Close");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
							.addGap(14)
							.addComponent(checkButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(stopButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(closeButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(closeButton)
							.addComponent(stopButton)
							.addComponent(checkButton))
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		
		table = new ForbiddenTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}
	
	PropertyChangeListener progressListener = new PropertyChangeListener() {			
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if(event.getPropertyName().compareTo("progress")==0){
				Integer value = (Integer) event.getNewValue();
				
				if(value<100){
					checkButton.setEnabled(false);
					progressBar.setIndeterminate(true);
				}
				else{
					checkButton.setEnabled(true);
					progressBar.setIndeterminate(false);					
				}
				progressBar.setValue(value);
			}
		}
	};
	
	ActionListener checkAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setTask();
			getTask().addPropertyChangeListener(progressListener);
			executeTask();
			
		}
	};
	
	
	public void setTask(){
		task = new IntransitiveMemberOfTask(parser, table);
	}
	
	public ForbiddenTask<?> getTask(){
		return task;
	}
	
	public void executeTask(){
		table.getModel().clear();
		
		if(task==null || task.isCancelled() || task.isDone())
			setTask();
		
		task.execute();
	}
	
	public boolean isTableEmpty() {
		return table.getModel().getRowCount()==0;
	}
}
