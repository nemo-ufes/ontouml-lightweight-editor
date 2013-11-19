package br.ufes.inf.nemo.xmi2ontouml.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ImportXMIDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093867102692070258L;
	
	
	public ImportXMIDialog(JFrame owner, boolean modal)
	{
		super(owner, modal);
		
		initGUI();
		setLocationRelativeTo(owner);
	}
	
	private void initGUI()
	{
		try
		{
			setTitle("Import from XMI");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setPreferredSize(new Dimension(800, 600));
			setBounds(new Rectangle(0, 0, 800, 600));
			{
				JTabbedPane mainTabbedPane = new JTabbedPane();
				JPanel optionsPanel = new JPanel();
				JPanel treePanel = new JPanel();
				{
					JTextField filePathField = new JTextField();
					filePathField.setColumns(60);
					optionsPanel.add(filePathField);
					
					JButton browseBtn = new JButton("Browse");
					optionsPanel.add(browseBtn);
				}
				mainTabbedPane.addTab("Options", optionsPanel);
				mainTabbedPane.addTab("Trees", treePanel);
				
				getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
			}
//			{
//				treeTabbedPane = new JTabbedPane();
//				getContentPane().add(treeTabbedPane, BorderLayout.WEST);
//				{
//					modelTreeScrollPane = new JScrollPane();
//					modelTreeScrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
//					modelTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//					modelTreeScrollPane.setViewportView(modelChckTree);
//				}
//				{
//					diagrTreeScrollPane = new JScrollPane();
//					diagrTreeScrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
//					diagrTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//					diagrTreeScrollPane.setViewportView(diagrChckTree);
//				}
//				treeTabbedPane.addTab("Model", modelTreeScrollPane);
//				treeTabbedPane.addTab("Diagram", diagrTreeScrollPane);
//			}
//			{
//				panel = new JPanel();
//				panel.setPreferredSize(new Dimension(370, 220));
//				getContentPane().add(panel, BorderLayout.EAST);
//				{
//					lblDetails = new JLabel("Details:");
//					lblDetails.setHorizontalTextPosition(SwingConstants.LEADING);
//					lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
//					panel.add(lblDetails);
//				}
//				{
//					horizontalStrut = Box.createHorizontalStrut(20);
//					horizontalStrut.setPreferredSize(new Dimension(320, 0));
//					panel.add(horizontalStrut);
//				}
//				{
//					infoPane = new JTextArea();
//					panel.add(infoPane);
//					infoPane.setPreferredSize(new Dimension(370, 200));
//					infoPane.setEditable(false);
//				}
//				{
//					verfInconsistency = new JCheckBox();
//					panel.add(verfInconsistency);
//					verfInconsistency.setText("Verify inconsistencies when importing");
//				}
//				{
//					importButton = new JButton();
//					panel.add(importButton);
//					importButton.setText("Import Model");
//					importButton.addActionListener(this);
//				}
//			}
			
			setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
