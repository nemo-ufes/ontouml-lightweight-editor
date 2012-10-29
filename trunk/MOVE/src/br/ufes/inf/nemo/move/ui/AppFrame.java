package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class AppFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	public InputPanel inputpanel;
	
	/**
	 * Create the frame.
	 */
	public AppFrame() 
	{
		setTitle("OntoUML Model Validation Environment - MOVE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Load OntoUML Model (Ctrl+M)
		JMenuItem miLoadOntoUML = new JMenuItem("Load OntoUML Model");
		mnFile.add(miLoadOntoUML);
		miLoadOntoUML.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		
		miLoadOntoUML.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 LoadOntoUMLActionPerformed(event);					
       		}
       	});		
		
		// Load OCL Model (Ctrl+L)
		JMenuItem miLoadOCL = new JMenuItem("Load OCL Constraints");
		mnFile.add(miLoadOCL);
		miLoadOCL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		
		miLoadOCL.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 LoadOCLActionPerformed(event);					
       		}
       	});	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// InputPanel
		inputpanel = new InputPanel();		
		contentPane.add(BorderLayout.NORTH,inputpanel);
		
		setContentPane(contentPane);
	}
	
	/**	Action Performed for Loading  OntoUML Model. */
	public void LoadOntoUMLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load OntoUML Model");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				try{
					
					inputpanel.setOntoUMLModel(fileChooser.getSelectedFile().getPath());
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the model.\n"+e.getMessage();
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**	Action Performed for Loading  OntoUML Model. */
	public void LoadOCLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load OCL Constraints");
		FileNameExtensionFilter oclFilter = new FileNameExtensionFilter("OCL Constraints (*.ocl)", "ocl");
		fileChooser.addChoosableFileFilter(oclFilter);
		fileChooser.setFileFilter(oclFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == oclFilter) 
			{
				try{
					
					inputpanel.setOCLModel(fileChooser.getSelectedFile().getPath());
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the ocl file.\n"+e.getMessage();
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
