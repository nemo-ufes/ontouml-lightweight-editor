package br.ufes.inf.nemo.move.ocl;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.ui.PathPanel;
import br.ufes.inf.nemo.move.util.ui.TitleTextField;
import java.awt.Color;

/**
 * @author John Guerson
 */

public class OCLView extends JPanel {

	private static final long serialVersionUID = 174639459637834072L;

	@SuppressWarnings("unused")
	private OCLModel oclModel;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	
	private TitleTextField titleTextField;
	private PathPanel oclPathPanel;
	
	/**
	 * Constructor.
	 * 
	 * @param oclModel
	 */
	public OCLView(OCLModel oclModel, TheFrame frame)
	{
		this();
		
		this.oclModel = oclModel;
		this.frame = frame;
		
		setPath(oclModel.getOCLPath(),oclModel.getOCLModelInstance());
		
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
		titleTextField.setText("OCL");
		panel.add(BorderLayout.NORTH,titleTextField);
		
		oclPathPanel = new PathPanel();
		panel.add(BorderLayout.CENTER,oclPathPanel);
		
		add(BorderLayout.NORTH,panel);
	}
	
	public void setPath(String path, String oclmodel)
	{
		if (path==null && oclmodel !=null)
			oclPathPanel.textPath.setText("Loaded...");
		else if (path!=null && oclmodel !=null)
			oclPathPanel.textPath.setText(path);
	}
			
	public void addLoadOCLListener(ActionListener actionListener) 
	{
		oclPathPanel.btnLoad.addActionListener(actionListener);
	}
	
	public String getOCLPathLocation()
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
}
