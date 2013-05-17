package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.move.editor.ocl.OCLEditorPanel;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ocl2alloy.OCLParser;

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
	private OCLEditorPanel ocleditor;
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
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

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
		if (path==null && oclmodel !=null){
			//oclbar.textPath.setText("  Location:  Loaded...");
		}else if (path!=null){
			completePath = path;
			//oclbar.textPath.setText("  Location:  "+path);
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
	 * Get the main frame application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame()
	{
		return frame;
	}

}
