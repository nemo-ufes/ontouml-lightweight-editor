package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorPanel;
import br.ufes.inf.nemo.move.ui.ocl.OCLPathBar;
import br.ufes.inf.nemo.move.ui.ocl.OCLToolBar;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

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
	private OCLPathBar oclbar;
	private OCLEditorPanel ocleditor;
	private OCLToolBar ocltoolbar;
	private JPanel panel_1;
	private String completePath;
		
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
		
		ocleditor.setText("\n-- Write your constraints below... \n-- Press Ctrl+Space too see the options.\n\n");
		ocleditor.setParent(frame);

		validate();
		repaint();
	}
	
	/**
	 * Creates a Empty View for OCL Model.
	 */
	public OCLView() 
	{
		setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(128, 128, 128)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setPreferredSize(new Dimension(40, 50));
		
		oclbar = new OCLPathBar();		
		ocltoolbar = new OCLToolBar();		
		ocltoolbar.setPreferredSize(new Dimension(25, 10));
		
		panel.add(BorderLayout.NORTH,oclbar);
		panel.add(BorderLayout.CENTER,ocltoolbar);
		
		add(BorderLayout.NORTH,panel);	
		
		panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Panel.background"));
		panel_1.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(128, 128, 128)));
		panel_1.setPreferredSize(new Dimension(16, 20));
		
		panel.add(panel_1, BorderLayout.WEST);
		
		ocleditor = new OCLEditorPanel();
		add(BorderLayout.CENTER,ocleditor);
		
	}
	
	public OCLEditorPanel getOcleditor() {
		return ocleditor;
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
			oclbar.textPath.setText("  Loaded...");
		else if (path!=null){
			completePath = path;
			oclbar.textPath.setText("  "+path.substring(path.lastIndexOf(File.separator)+1, path.length()));
		}
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
	public OCLParser parseConstraints() throws ParserException,IOException,Exception
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
	public String getPath() { return completePath; }
		
	/**
	 * Add Open OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addOpenOCLListener(ActionListener actionListener) 
	{
		ocltoolbar.btnOpen.addActionListener(actionListener);
	}
	
	/**
	 * Add Save OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addSaveOCLListener(ActionListener actionListener) 
	{
		ocltoolbar.btnSave.addActionListener(actionListener);
	}
	
	/**
	 * Add New OCL Document Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addNewOCLListener(ActionListener actionListener) 
	{
		ocltoolbar.btnNew.addActionListener(actionListener);
	}
	
	/**
	 * Add Parse OCL Constraints Action Listener.
	 * 
	 * @param actionListener
	 */
	public void addParseOCLListener(ActionListener actionListener) 
	{
		ocltoolbar.btnParse.addActionListener(actionListener);
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
