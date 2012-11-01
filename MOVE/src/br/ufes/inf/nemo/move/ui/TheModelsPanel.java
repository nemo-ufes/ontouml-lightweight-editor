package br.ufes.inf.nemo.move.ui;

import java.awt.Dimension;
import java.awt.SystemColor;
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
import javax.swing.border.TitledBorder;
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
	
	/* models */
	private RefOntoUML.Package refmodel;
	private String oclConstraints;
	
	/* models paths */
	private JTextField textOntoUML;	
	private JTextField textOCL;		
	private JTextField textAlloy;
	
	/* load path buttons */
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
		setBorder(new TitledBorder(null, "Models", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setBackground(SystemColor.inactiveCaption);
		setPreferredSize(new Dimension(476, 127));
		
		JLabel lblYourOntoumlModel = new JLabel("OntoUML Model :");
		JLabel lblLoadConstraints = new JLabel("Domain Constraints : ");
		JLabel lblAlloyOutput = new JLabel("Alloy Output:");
		
		textOntoUML = new JTextField();
		textOntoUML.setText("*.refontouml");
		textOntoUML.setEditable(false);
		textOntoUML.setColumns(10);
		
		textOCL = new JTextField();
		textOCL.setText("*.ocl");
		textOCL.setEditable(false);
		textOCL.setColumns(10);

		textAlloy = new JTextField();
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
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYourOntoumlModel)
						.addComponent(lblLoadConstraints)
						.addComponent(lblAlloyOutput))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textAlloy, Alignment.LEADING)
						.addComponent(textOCL, Alignment.LEADING)
						.addComponent(textOntoUML, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAlloyOutput, 0, 0, Short.MAX_VALUE)
						.addComponent(btnLoadOCL, 0, 0, Short.MAX_VALUE)
						.addComponent(btnLoadOntoUML, GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourOntoumlModel)
						.addComponent(textOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadOntoUML))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoadConstraints)
						.addComponent(textOCL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadOCL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAlloyOutput)
						.addComponent(btnAlloyOutput))
					.addContainerGap(12, Short.MAX_VALUE))
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
