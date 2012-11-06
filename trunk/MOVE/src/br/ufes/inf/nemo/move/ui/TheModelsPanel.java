package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * @author John Guerson
 */

public class TheModelsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private RefOntoUML.Package refmodel;
	private OntoUMLParser refparser;
	
	private String oclConstraints;	
	
	private String alsPath;
	private String umlPath;
	
	private JTextField textOntoUML;	
	private JTextField textOCL;
	private JTextField textAlloy;
	private JTextField textUML;
	
	private JButton btnLoadOntoUML;	
	private JButton btnLoadOCL;	
	private JButton btnAlloyOutput;
	private JButton btnUMLOutput;
	
	/**
	 *	Set OntoUML Model and Path. 
	 */
	public void setOntoUMLModel (String modelpath) throws IOException
	{		
		Resource resource = ResourceUtil.loadReferenceOntoUML(modelpath);
		this.refmodel = (RefOntoUML.Package) resource.getContents().get(0);		
		this.refparser = new OntoUMLParser(refmodel);
		
		textOntoUML.setText(modelpath);
	}

	/**
	 * Set OntoUML Model.
	 */
	public void setOntoUMLModel (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;		
		this.refparser = new OntoUMLParser(refmodel);
		
		textOntoUML.setText("Loaded...");
		
		btnLoadOntoUML.setEnabled(false);
	}
	
	/**
	 * Get OntoUML Model.
	 */
	public RefOntoUML.Package getOntoUMLModel ()
	{
		return this.refmodel;
	}
	
	/**
	 * Get OntoUML Parser.
	 * @return
	 */
	public OntoUMLParser getOntoUMLParser ()
	{
		return refparser;
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
		return this.oclConstraints;
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
			btnLoadOCL.setEnabled(false);
			
		}
			
	}
	
	/**
	 *	Set Output Alloy Path. 
	 */
	public void setAlloyPath(String alloyPath)
	{			
		this.alsPath = alloyPath;
		
		textAlloy.setText(alloyPath);
	}

	/**
	 *	Get Output AlloyPath. 
	 */
	public String getAlloyPath()
	{
		return this.alsPath;
	}
	
	/**
	 *	Set Output UML Path. 
	 */
	public void setUMLPath(String umlpath)
	{			
		this.umlPath = umlpath;
		
		textUML.setText(umlpath);
	}

	/**
	 *	Get Output UML Path. 
	 */
	public String getUMLPath()
	{
		return this.umlPath;
	}	
	
	/**
	 * Create the Panel.
	 */
	public TheModelsPanel() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(1074, 83));
		setSize(905,83);
		
		JLabel lblYourOntoumlModel = new JLabel("OntoUML Model:");
		JLabel lblLoadConstraints = new JLabel("Domain Constraints:");
		JLabel lblAlloyOutput = new JLabel("Alloy Output:");
		JLabel lblUmlOutput = new JLabel("UML Output:");
		
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
		textAlloy.setText("*.als");
		textAlloy.setEditable(false);
		textAlloy.setColumns(10);
		textAlloy.setBackground(Color.WHITE);
		
		textUML = new JTextField();
		textUML.setText("*.uml");
		textUML.setEditable(false);
		textUML.setColumns(10);
		textUML.setBackground(Color.WHITE);
		
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
       			AlloyOutputActionPerformed(event);
       		}
       	});		
						
		btnUMLOutput = new JButton("...");
		btnUMLOutput.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			UMLOutputActionPerformed(event);
       		}
       	});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblYourOntoumlModel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblLoadConstraints, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textOntoUML)
						.addComponent(textOCL, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLoadOCL, 0, 0, Short.MAX_VALUE)
						.addComponent(btnLoadOntoUML, GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUmlOutput, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAlloyOutput, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textUML)
						.addComponent(textAlloy, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAlloyOutput, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUMLOutput, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourOntoumlModel)
						.addComponent(textOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadOntoUML)
						.addComponent(lblAlloyOutput)
						.addComponent(btnAlloyOutput)
						.addComponent(textAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoadConstraints)
						.addComponent(btnLoadOCL)
						.addComponent(textOCL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUmlOutput)
						.addComponent(textUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUMLOutput))
					.addGap(14))
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
					
					setAlloyPath(fileChooser.getSelectedFile().getPath().replace(".refontouml", ".als"));
					setUMLPath(fileChooser.getSelectedFile().getPath().replace(".refontouml", ".uml"));
					
					TheFrame.dirPath = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);	
					
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
	 * Changing Alloy Output Location... 
	 */
	public void AlloyOutputActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Alloy Output Location");
		FileNameExtensionFilter alsFilter = new FileNameExtensionFilter("Alloy Specification (*.als)", "als");
		fileChooser.addChoosableFileFilter(alsFilter);
		fileChooser.setFileFilter(alsFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alsFilter) 
			{					
				if (fileChooser.getSelectedFile().getPath().contains(".als"))
					setAlloyPath(fileChooser.getSelectedFile().getPath());
				else
					setAlloyPath(fileChooser.getSelectedFile().getPath()+".als");
			}
		}
	}
	
	/**	
	 * Changing UML Output Location... 
	 */
	public void UMLOutputActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("UML Output Location");
		FileNameExtensionFilter umlFilter = new FileNameExtensionFilter("UML Model (*.uml)", "uml");
		fileChooser.addChoosableFileFilter(umlFilter);
		fileChooser.setFileFilter(umlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == umlFilter) 
			{					
				if (fileChooser.getSelectedFile().getPath().contains(".uml"))
					setUMLPath(fileChooser.getSelectedFile().getPath());
				else
					setUMLPath(fileChooser.getSelectedFile().getPath()+".uml");
			}
		}
	}
}
