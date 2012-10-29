package br.ufes.inf.nemo.move.ui;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.common.resource.ResourceUtil;

/**
 * This Panel was created using the Windows Builder in Eclipse. 
 */

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public RefOntoUML.Package refmodel;
	
	private JTextField textOntoUML;
	
	public String oclConstraints;
	
	private JTextField textOCL;	
		
	public void setOntoUMLModel (String modelpath) throws IOException
	{		
		Resource resource = ResourceUtil.loadReferenceOntoUML(modelpath);
		refmodel = (RefOntoUML.Package) resource.getContents().get(0);
		
		textOntoUML.setText(modelpath);
	}

	public void setOntoUMLModel (RefOntoUML.Package refmodel)
	{
		this.refmodel = refmodel;
		
		textOntoUML.setText("Loaded...");
	}
	
	public RefOntoUML.Package getOntoUMLModel ()
	{
		return refmodel;
	}
	
	public String getOntoUMLPath()
	{
		return textOntoUML.getText();
	}
	
	public String getOCLModel ()	
	{
		return oclConstraints;
	}
	
	public String getOCLPath()
	{
		return textOCL.getText();
	}	
	
	public void setOCLModel (String modelpath) throws IOException
	{
		String content = FileUtil.readFile(modelpath);
		
		this.oclConstraints = content;
		
		textOCL.setText(modelpath);
	}
			
	/**
	 * Create the panel.
	 */
	public InputPanel() 
	{
		setBackground(SystemColor.inactiveCaption);
		setPreferredSize(new Dimension(620, 69));
		
		textOntoUML = new JTextField();
		textOntoUML.setText("*.refontouml");
		textOntoUML.setEditable(false);
		textOntoUML.setColumns(10);
		
		JLabel lblYourOntoumlModel = new JLabel("OntoUML Model :");
		
		textOCL = new JTextField();
		textOCL.setText("*.ocl");
		textOCL.setEditable(false);
		textOCL.setColumns(10);
		
		JLabel lblLoadConstraints = new JLabel("OCL Constraints : ");
		
		JLabel lblctrlm = new JLabel("(Ctrl+M)");
		
		JLabel lblctrll = new JLabel("(Ctrl+L)");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYourOntoumlModel)
						.addComponent(lblLoadConstraints))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textOCL)
						.addComponent(textOntoUML, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblctrlm)
						.addComponent(lblctrll))
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYourOntoumlModel)
						.addComponent(textOntoUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblctrlm))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoadConstraints)
						.addComponent(textOCL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblctrll))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
