package br.ufes.inf.nemo.move.ontouml;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ontouml.tree.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.ontouml.tree.OntoUMLTreeBar;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.ui.TitleTextField;
import br.ufes.inf.nemo.move.util.ui.TreeScrollPane;

/**
 * @author John Guerson
 */

public class OntoUMLView extends JPanel {

	private static final long serialVersionUID = -8391565085376481547L;

	@SuppressWarnings("unused")
	private OntoUMLModel ontoumlModel;
	
	private TheFrame frame;
	
	private TitleTextField titleTextField;
	private OntoUMLTreeBar ontobar;
	private TreeScrollPane treeScrollPane;
	private CheckboxTree modeltree;
	
	/** Constructor.
	 * 
	 * @param ontoumlModel
	 */
	public OntoUMLView(OntoUMLModel ontoumlModel, TheFrame frame)
	{
		this();
		
		this.ontoumlModel = ontoumlModel;
		this.frame = frame;
		
		setPath(ontoumlModel.getOntoUMLPath(),ontoumlModel.getOntoUMLModelInstance());
		setModelTree(ontoumlModel.getOntoUMLModelInstance());
		
		validate();
		repaint();
	}

	/**
	 * Constructor.
	 */
	public OntoUMLView() 
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new BorderLayout(0, 0));
				
		titleTextField = new TitleTextField();
		titleTextField.setText("OntoUML Conceptual Model");
		panel.add(BorderLayout.NORTH,titleTextField);
		
		ontobar = new OntoUMLTreeBar();
		panel.add(BorderLayout.CENTER,ontobar);
		
		add(BorderLayout.NORTH,panel);
		
		treeScrollPane = new TreeScrollPane();
		add(BorderLayout.CENTER,treeScrollPane);
	}
		
	public void setModelTree(RefOntoUML.Package refmodel)
	{	
		if (refmodel!=null)
		{
			if(modeltree!=null) treeScrollPane.treePanel.remove(modeltree);			
			modeltree = OntoUMLCheckBoxTree.createCheckBoxTree(refmodel);					
			treeScrollPane.treePanel.add(BorderLayout.CENTER,modeltree);
			treeScrollPane.validate();
			treeScrollPane.repaint();
		}
	}
	
	public void setPath(String path, RefOntoUML.Package refmodel)
	{
		if (path==null && refmodel!=null)
			ontobar.textPath.setText("Loaded...");
		else if (path!= null && refmodel !=null)
			ontobar.textPath.setText(path);
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	public void addLoadOntoUMLListener(ActionListener actionListener) 
	{
		ontobar.btnOpen.addActionListener(actionListener);
	}	    
	
	public String getOntoUMLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open OntoUML");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter("Reference OntoUML Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}
}
