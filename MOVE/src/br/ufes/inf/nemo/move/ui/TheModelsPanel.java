package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 * 
 * @author John Guerson
 */

public class TheModelsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private RefOntoUML.Package refmodel;
	private String oclConstraints;
	
	private JTextField textOntoUML;	
	private JTextField textOCL;		
	private JTextField textAlloy;
	
	private JButton btnLoadOntoUML;	
	private JButton btnLoadOCL;	
	private JButton btnAlloyOutput;
	
	/**
	 *	Set OntoUML Model and Path. 
	 */
	public void setOntoUMLModel (String modelpath) throws IOException
	{		
		Resource resource = ResourceUtil.loadReferenceOntoUML(modelpath);
		refmodel = (RefOntoUML.Package) resource.getContents().get(0);
		
		textOntoUML.setText(modelpath);
	}

	/**
	 * Set OntoUML Model.
	 */
	public void setOntoUMLModel (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;
		
		textOntoUML.setText("Loaded...");
	}
	
	/**
	 * Get OntoUML Model.
	 */
	public RefOntoUML.Package getOntoUMLModel ()
	{
		return refmodel;
	}
	
	/**
	 *	Get OntoUML Path. 
	 */
	public String getOntoUMLPath()
	{
		return textOntoUML.getText();
	}
	
	/**
	 *	Get OCL Model. i.e. String Constraints.
	 */
	public String getOCLModel ()	
	{
		return oclConstraints;
	}
	
	/**
	 * Get OCL Path.
	 */
	public String getOCLPath()
	{
		return textOCL.getText();
	}	
	
	/**
	 * Set OCL Model.
	 * 
	 * If option=1, OCL will be loaded from a File, 
	 * else if option=2, OCL will be loaded from String content.
	 */
	public void setOCLModel (String str, int option) throws IOException
	{
		if  (option==1) 
		{
			String content = FileUtil.readFile(str);		
			this.oclConstraints = content;		
			textOCL.setText(str);
			
		} else  if (option==2) {
			
			String content = str;
			this.oclConstraints = content;
			textOCL.setText("Loaded...");
		}
			
	}
	
	/**
	 *	Set Alloy Path. 
	 */
	public void setAlloyPath(String alloyPath)
	{			
		textAlloy.setText(alloyPath);
	}

	/**
	 *	Get AlloyPath. 
	 */
	public String getAlloyPath()
	{
		return textAlloy.getText();
	}	
	
	/**
	 * Create the Panel.
	 */
	public TheModelsPanel() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(905, 83));
		setSize(905,83);
		
		JLabel lblYourOntoumlModel = new JLabel("OntoUML Model");
		JLabel lblLoadConstraints = new JLabel("OCL Domain Constraints");
		JLabel lblAlloyOutput = new JLabel("Alloy Specification Output");
		
		textOntoUML = new JTextField();
		textOntoUML.setBackground(Color.WHITE);
		textOntoUML.setEditable(false);
		textOntoUML.setText("*.refontouml");
		textOntoUML.setColumns(10);
		
		textOCL = new JTextField();
		textOCL.setBackground(Color.WHITE);
		textOCL.setEditable(false);
		textOCL.setText("*.ocl");
		textOCL.setColumns(10);

		textAlloy = new JTextField();
		textAlloy.setBackground(Color.WHITE);
		textAlloy.setEditable(false);
		textAlloy.setText("*.als");
		textAlloy.setColumns(10);
		
		btnLoadOntoUML = new JButton("...");		
		btnLoadOntoUML.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 LoadOntoUMLActionPerformed(event);					
       		}
       	});	
		
		btnLoadOCL = new JButton("...");
		btnLoadOCL.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 LoadOCLActionPerformed(event);					
       		}
       	});
		
		btnAlloyOutput = new JButton("...");		
		btnAlloyOutput.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 SetOutputLocationActionPerformed(event);					
       		}
       	});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textOntoUML, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadOntoUML, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblYourOntoumlModel, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textOCL, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadOCL, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblLoadConstraints, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textAlloy, 237, 237, 237)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlloyOutput, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAlloyOutput, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYourOntoumlModel)
						.addComponent(lblLoadConstraints)
						.addComponent(lblAlloyOutput))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLoadOntoUML))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textOCL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAlloyOutput)
							.addComponent(btnLoadOCL)))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}	

	/**	
	 * Loading  OntoUML Model. 
	 */
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
					
					setOntoUMLModel(fileChooser.getSelectedFile().getPath());
					
					setAlloyPath(fileChooser.getSelectedFile().getPath().replace(".refontouml", "als"));
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the model.\n"+e.getMessage();
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**	
	 * Loading  OCL Model. 
	 */
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
					
					setOCLModel(fileChooser.getSelectedFile().getPath(),1);
					
				} catch (IOException e) {				
					String msg = "An error ocurred while loading the ocl file.\n"+e.getMessage();
					JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**	
	 * Changing Location of Alloy Output. 
	 */
	public void SetOutputLocationActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Output Location");
		FileNameExtensionFilter alloyFilter = new FileNameExtensionFilter("Alloy Specification (*.als)", "als");
		fileChooser.addChoosableFileFilter(alloyFilter);
		fileChooser.setFileFilter(alloyFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alloyFilter) 
			{				
				if(!fileChooser.getSelectedFile().getPath().contains(".als"))
					setAlloyPath(fileChooser.getSelectedFile().getPath()+".als");
				else
					setAlloyPath(fileChooser.getSelectedFile().getPath());
			}
		}
	}

}
