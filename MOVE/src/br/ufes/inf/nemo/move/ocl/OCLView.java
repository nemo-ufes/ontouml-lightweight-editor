package br.ufes.inf.nemo.move.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.ui.PathPanel;
import br.ufes.inf.nemo.move.util.ui.TitleTextField;

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
	private JPanel btnPanel;
	private JButton btnAdd;	
	private JScrollPane scrollPane;
	private JPanel constraintsPanel;	
	
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
		
		btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(100, 50));		
		panel.add(BorderLayout.SOUTH,btnPanel);
		
		btnAdd = new JButton("Add Constraint");
		
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdd)
					.addContainerGap(335, Short.MAX_VALUE))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdd)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		btnPanel.setLayout(gl_btnPanel);
		
		add(BorderLayout.NORTH,panel);	

		constraintsPanel = new JPanel();
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
		scrollPane.setViewportView(constraintsPanel);
		
		constraintsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		add(BorderLayout.CENTER,scrollPane);		
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
	
	public void addConstraintListener(ActionListener actionListener)
	{
		btnAdd.addActionListener(actionListener);
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
