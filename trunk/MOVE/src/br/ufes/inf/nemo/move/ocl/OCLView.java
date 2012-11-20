package br.ufes.inf.nemo.move.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ocl.editor.OCLEditorPanel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.ui.PathPanel;
import br.ufes.inf.nemo.move.util.ui.TitleTextField;

/**
 * @author John Guerson
 */

public class OCLView extends JPanel {

	private static final long serialVersionUID = 174639459637834072L;

	@SuppressWarnings("unused")
	private OCLModel oclmodel;
	
	private TheFrame frame;	
	private TitleTextField titleTextField;
	private PathPanel oclPathPanel;
	private OCLEditorPanel ocleditor;
		
	/**
	 * Constructor.
	 * 
	 * @param oclmodel
	 * @param frame
	 */
	public OCLView(OCLModel oclmodel, TheFrame frame)
	{
		this();
		
		this.oclmodel = oclmodel;
		this.frame = frame;
		
		setPath(oclmodel.getOCLPath(),oclmodel.getOCLString());		
		setConstraints(oclmodel.getOCLString());
		
		validate();
		repaint();
	}
	
	/**
	 * Constructor.
	 */
	public OCLView() 
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new BorderLayout(0, 0));
				
		titleTextField = new TitleTextField();
		titleTextField.setText("OCL Domain Constraints");
		panel.add(BorderLayout.NORTH,titleTextField);
		
		oclPathPanel = new PathPanel();
		oclPathPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		oclPathPanel.btnNew.setEnabled(false);		
		panel.add(BorderLayout.CENTER,oclPathPanel);
		
		add(BorderLayout.NORTH,panel);	

		ocleditor = new OCLEditorPanel();
		
		add(BorderLayout.CENTER,ocleditor);		
	}
	
	/**
	 * Set Path View.
	 * 
	 * @param path
	 * @param oclmodel
	 */
	public void setPath(String path, String oclmodel)
	{
		if (path==null && oclmodel !=null)
			oclPathPanel.textPath.setText("Loaded...");
		else if (path!=null)
			oclPathPanel.textPath.setText(path);
	}	
	
	/**
	 * Set Editor View.
	 * 
	 * @param oclmodel
	 */
	public void setConstraints(String oclmodel)
	{
		ocleditor.setText(oclmodel);
	}			
	
	public String getConstraints()
	{
		return ocleditor.textArea.getText();
	}
	
	public String getOCLPath()
	{
		return oclPathPanel.textPath.getText();
	}
		
	public void addOpenOCLListener(ActionListener actionListener) 
	{
		oclPathPanel.btnOpen.addActionListener(actionListener);
	}
	
	public void addSaveOCLListener(ActionListener actionListener) 
	{
		oclPathPanel.btnSave.addActionListener(actionListener);
	}
	
	public void addNewOCLListener(ActionListener actionListener) 
	{
		oclPathPanel.btnNew.addActionListener(actionListener);
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	public String openOCLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Loading OCL...");
		FileNameExtensionFilter oclFilter = new FileNameExtensionFilter("OCL Constraints (*.ocl)", "ocl");
		fileChooser.addChoosableFileFilter(oclFilter);
		fileChooser.setFileFilter(oclFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == oclFilter) 
			{
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}	
	
	public String saveOCLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Saving OCL...");
		FileNameExtensionFilter oclFilter = new FileNameExtensionFilter("OCL Constraints (*.ocl)", "ocl");
		fileChooser.addChoosableFileFilter(oclFilter);
		fileChooser.setFileFilter(oclFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == oclFilter) 
			{
				String path = fileChooser.getSelectedFile().getPath();
				if (path.contains(".ocl"))
					return fileChooser.getSelectedFile().getPath();
				else
					return fileChooser.getSelectedFile().getPath()+".ocl";
			}
		}
		return null;
	}	
}
