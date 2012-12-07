package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.move.mvc.model.OCLModel;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorBar;
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorPanel;
import br.ufes.inf.nemo.move.util.TitleTextField;
import br.ufes.inf.nemo.ocl2alloy.parser.OCLParser;

/**
 * 
 * This class represents a View for OCL Model.
 * 
 * @author John Guerson
 */

public class OCLView extends JPanel {

	private static final long serialVersionUID = 174639459637834072L;

	@SuppressWarnings("unused")
	private OCLModel oclmodel;
	
	private TheFrame frame;	
	private TitleTextField tltxtfldConstraintsOcl;
	private OCLEditorBar oclbar;
	private OCLEditorPanel ocleditor;
		
	/**
	 * Creates a View for OCL Model and the main frame of Application.
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
		
		ocleditor.setText("-- Write your constraints below... Press Ctrl+Space too see the options.\n\n");
		ocleditor.setParent(frame);
		
		validate();
		repaint();
	}
	
	/**
	 * Creates a Empty View for OCL Model.
	 */
	public OCLView() 
	{
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new BorderLayout(0, 0));
				
		tltxtfldConstraintsOcl = new TitleTextField();
		tltxtfldConstraintsOcl.setText("Constraints - OCL");
		panel.add(BorderLayout.NORTH,tltxtfldConstraintsOcl);
		
		oclbar = new OCLEditorBar();
		oclbar.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(BorderLayout.CENTER,oclbar);
		
		add(BorderLayout.NORTH,panel);	

		ocleditor = new OCLEditorPanel();
		
		add(BorderLayout.CENTER,ocleditor);		
	}
	
	/**
	 * Set Path View from a absolute path and the ocl model content.
	 * 
	 * @param path
	 * @param oclmodel
	 */
	public void setPath(String path, String oclmodel)
	{
		if (path==null && oclmodel !=null)
			oclbar.textPath.setText("Loaded...");
		else if (path!=null)
			oclbar.textPath.setText(path);
	}	
	
	/**
	 * Set Editor View from a ocl model content.
	 * 
	 * @param oclmodel
	 */
	public void setConstraints(String oclmodel)
	{
		ocleditor.setText(oclmodel);
	}			
	
	/**
	 * Parse Constraints from the Editor View.
	 * 
	 * @throws ParserException
	 * @throws IOException
	 */
	public OCLParser parseConstraints() throws ParserException,IOException
	{
		return new OCLParser(getConstraints(),frame.getManager().getOntoUMLModel().getOntoUMLParser(),frame.getManager().getUMLModel().getUMLPath());
	}
	
	/**
	 * Get Constraints from the Editor View.
	 * 
	 * @return
	 */
	public String getConstraints() { return ocleditor.textArea.getText(); }
	
	/**
	 * Get OCL Path from View.
	 * 
	 * @return
	 */
	public String getOCLPath() { return oclbar.textPath.getText(); }
		
	/**
	 * Add Open OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addOpenOCLListener(ActionListener actionListener) 
	{
		oclbar.btnOpen.addActionListener(actionListener);
	}
	
	/**
	 * Add Save OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addSaveOCLListener(ActionListener actionListener) 
	{
		oclbar.btnSave.addActionListener(actionListener);
	}
	
	/**
	 * Add New OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addNewOCLListener(ActionListener actionListener) 
	{
		oclbar.btnNew.addActionListener(actionListener);
	}
	
	/**
	 * Add Parse OCL Constraints Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addParseOCLListener(ActionListener actionListener) 
	{
		oclbar.btnParse.addActionListener(actionListener);
	}
	
	/**
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	/**
	 * Open OCL Path Location.
	 * 
	 * @return
	 */
	public String openOCLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open OCL");
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
	
	/**
	 * Save OCL Path Location.
	 * 
	 * @return
	 */
	public String saveOCLPathLocation()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save OCL");
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
