package br.ufes.inf.nemo.instancevisualizer.gui;

import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StyleWindow extends JFrame {

	private JPanel contentPane;
	
	private GraphManager graphManager;
	private String typeName;
	private final String mode;
	private JTextArea textArea;
	
	
	public StyleWindow(String attr, String typeName, GraphManager graphManager, final String mode) {
		super();
		this.setTitle("Style Editor - "+typeName);
		this.typeName = typeName;
		this.graphManager = graphManager;
		this.mode = mode;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 240);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(mode) {
					case "node":
						//getGraphManager().getLegendManager().getLegendWithType(getTypeName()).setStyle(getTextArea().getText());
						String text = getTextArea().getText();
						int begin = text.indexOf("url(");
						int i = begin;
						System.out.println("TYPE");
						while(text.charAt(i+5) != '\'') {
							System.out.print(text.charAt(i+5));
							i++;
						}
						System.out.print("\n");
						String imagePath = text.substring(begin+5, i+5);
						getGraphManager().getLegendManager().getLegendWithType(getTypeName()).setFillImage(imagePath);
						//AplMainWindow.refreshGraphs();
						break;
					case "edge":
						getGraphManager().getEdgeManager().getEdgeLegendManager().getEdgeTypeLegend(getTypeName()).setStyle(getTextArea().getText());
						break;
				}
				getGraphManager().update();
				dispose();
				setVisible(false);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		
		JButton btnApply = new JButton("Apply");
		
		textArea = new JTextArea();
		textArea.setText("<dynamic>");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApply))
				.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addGap(0))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public GraphManager getGraphManager() {
		return graphManager;
	}

	public String getTypeName() {
		return typeName;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
