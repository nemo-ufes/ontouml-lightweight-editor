package gui;

import graph.GraphManager;

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

public class StyleWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	
	private GraphManager graphManager;
	private String typeName;
	
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
	public StyleWindow(String imagePath, String attr, String typeName, GraphManager graphManager) {
		this.typeName = typeName;
		this.graphManager = graphManager;
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
				System.out.println("1"+getGraphManager().toString());
				System.out.println("1"+getTypeName().toString());
				System.out.println("1"+getTextArea().toString());
				getGraphManager().updateNodesStyle(getGraphManager().getSelectedGraph(), getTypeName(), getTextArea().getText());
				getGraphManager().getLegendManager().updateTypeStyle(getTypeName(), getTextArea().getText());
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		
		JButton btnApply = new JButton("Apply");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(111)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApply)
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel)
						.addComponent(btnApply)))
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Text", null, panel_1, null);
		
		textArea = new JTextArea();
		textArea.setText(attr);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
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
