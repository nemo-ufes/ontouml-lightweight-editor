package br.ufes.inf.nemo.move.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ocl.constraint.OCLConstraintController;
import br.ufes.inf.nemo.move.ocl.constraint.OCLConstraintModel;
import br.ufes.inf.nemo.move.ocl.constraint.OCLConstraintView;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.ui.PathPanel;
import br.ufes.inf.nemo.move.util.ui.TitleTextField;

/**
 * @author John Guerson
 */

public class OCLConstraintListView extends JPanel {

	private static final long serialVersionUID = 174639459637834072L;

	@SuppressWarnings("unused")
	private OCLConstraintListModel oclListModel;
	
	private TheFrame frame;	
	private TitleTextField titleTextField;
	private PathPanel oclPathPanel;
	private JPanel btnPanel;
	private JButton btnAdd;	
	private JScrollPane scrollPane;
	private JPanel constraintsPanel;	
	private JButton btnDisableAll;
	private JButton btnEnableAll;
	
	/**
	 * Constructor.
	 * 
	 * @param oclModel
	 */
	public OCLConstraintListView(OCLConstraintListModel oclListModel, TheFrame frame)
	{
		this();
		
		this.oclListModel = oclListModel;
		this.frame = frame;
		
		setPath(oclListModel.getOCLPath(),oclListModel.getOCLString());		
		setConstraints(oclListModel.getConstraintModelList());
		
		validate();
		repaint();
	}
	
	/**
	 * Constructor.
	 */
	public OCLConstraintListView() 
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
		oclPathPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(BorderLayout.CENTER,oclPathPanel);
		
		btnPanel = new JPanel();
		btnPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btnPanel.setPreferredSize(new Dimension(80, 50));		
		panel.add(BorderLayout.SOUTH,btnPanel);
		
		btnAdd = new JButton("Add");
		
		JButton btnSaveTofile = new JButton("Save As...");
		
		btnDisableAll = new JButton("Disable All");
		
		btnEnableAll = new JButton("Enable All");
		
		GroupLayout gl_btnPanel = new GroupLayout(btnPanel);
		gl_btnPanel.setHorizontalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSaveTofile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDisableAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnableAll)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_btnPanel.setVerticalGroup(
			gl_btnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btnPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_btnPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnSaveTofile)
						.addComponent(btnDisableAll)
						.addComponent(btnEnableAll))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		btnPanel.setLayout(gl_btnPanel);
		
		add(BorderLayout.NORTH,panel);	

		constraintsPanel = new JPanel();
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
		scrollPane.setViewportView(constraintsPanel);
		
		constraintsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		add(BorderLayout.CENTER,scrollPane);		
	}
	
	/**
	 * Set Path.
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
	 * Set Constraints.
	 * 
	 * @param oclListModel
	 */
	public void setConstraints(ArrayList<OCLConstraintModel> oclListModel)
	{
		if (oclListModel.size()==0) return;
		
		constraintsPanel.setLayout(new GridLayout(oclListModel.size(), 0, 0, 0));
		
		for(OCLConstraintModel ctModel: oclListModel)
		{
			OCLConstraintView ctView = new OCLConstraintView(ctModel,frame);
			@SuppressWarnings("unused")
			OCLConstraintController ctController = new OCLConstraintController();
			constraintsPanel.add(ctView);
		}
		
		constraintsPanel.validate();
		constraintsPanel.repaint();
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
