package gui;

import graph.GraphManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class RelationalMaterialPanel extends JPanel {
	JSlider slider;
	GraphManager graphManager;
	
	public RelationalMaterialPanel(GraphManager graphManager) {
		super();
		this.graphManager = graphManager;
		
		slider = new JSlider(JSlider.VERTICAL, 0, 2, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("FOI");
				getGraphManager().changeSelectedRmLevel(slider.getValue());
			}
		});
		slider.setMinorTickSpacing(1);
		slider.setBounds(10, 11, 31, 116);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		
		/*
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		JLabel a = new JLabel("Stop");
		a.setVisible(true);
	    labelTable.put(0, a);
	    labelTable.put(1, new JLabel("Slow"));
	    labelTable.put(2, new JLabel("Fast"));
	    slider.setLabelTable(labelTable);
	    */
        
		setLayout(null);
		add(slider);
		
		JLabel lblHigh = new JLabel("High");
		lblHigh.setBounds(51, 11, 46, 14);
		add(lblHigh);
		
		JLabel lblMedium = new JLabel("Medium");
		lblMedium.setBounds(51, 61, 46, 14);
		add(lblMedium);
		
		JLabel lblLow = new JLabel("Low");
		lblLow.setBounds(51, 106, 46, 14);
		add(lblLow);
	}
	
	public GraphManager getGraphManager() {
		return graphManager;
	}
}
