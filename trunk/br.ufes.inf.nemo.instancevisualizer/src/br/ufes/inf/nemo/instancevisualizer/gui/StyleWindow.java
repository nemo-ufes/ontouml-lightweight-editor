package br.ufes.inf.nemo.instancevisualizer.gui;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private GraphManager graphManager;
	private String typeName;
	private JTextArea textArea;
	private final String mode;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StyleWindow frame = new StyleWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public StyleWindow(String imagePath, String attr, String typeName, GraphManager graphManager, final String mode) {
		this.typeName = typeName;
		this.graphManager = graphManager;
		this.mode = mode;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 240);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("1"+getGraphManager().toString());
				//System.out.println("1"+getTypeName().toString());
				//System.out.println("1"+getTextArea().toString());
				//getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextArea().getText());
				switch(mode) {
				case "node":
					getGraphManager().getLegendManager().getLegendWithType(getTypeName()).setStyle(getTextArea().getText());
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(111, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApply))
				.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addGap(0))
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Basic", null, panel, null);
		
		JLabel lblSize = new JLabel("Size (px):");
		lblSize.setBounds(10, 14, 56, 14);
		
		textField = new JTextField();
		textField.setBounds(74, 11, 44, 20);
		textField.setColumns(10);
		
		JLabel lblBy = new JLabel("by");
		lblBy.setBounds(126, 14, 23, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 11, 44, 20);
		textField_1.setColumns(10);
		
		JLabel lblIcon = new JLabel("Icon:");
		lblIcon.setBounds(10, 48, 32, 14);
		
		JButton button = new JButton("");
		button.setBounds(52, 39, 32, 32);
		button.setIcon(new ImageIcon(imagePath));
		
		JLabel lblVisibility = new JLabel("Visibility:");
		lblVisibility.setBounds(10, 84, 49, 14);
		
		textField_2 = new JTextField();
		textField_2.setBounds(69, 81, 49, 20);
		textField_2.setColumns(10);
		panel.setLayout(null);
		panel.add(lblSize);
		panel.add(textField);
		panel.add(lblVisibility);
		panel.add(textField_2);
		panel.add(lblIcon);
		panel.add(button);
		panel.add(lblBy);
		panel.add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Text", null, scrollPane, null);
		
		textArea = new JTextArea();
		textArea.setText(attr);
		scrollPane.setViewportView(textArea);
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
