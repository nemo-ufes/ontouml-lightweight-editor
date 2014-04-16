package br.ufes.inf.nemo.oled.finder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class FinderPane extends JPanel{

	private static final long serialVersionUID = -3183962658000841153L;
	@SuppressWarnings("unused")
	private UmlProject project;
	private FinderScrollTable finderScrollTable;
	private FinderHeadPane finderHeadPane;
	
	public FinderPane(UmlProject project)
	{
		this();
		this.project = project;
	}
		
	public void setProject(UmlProject project)
	{
		this.project = project;
	}
	
	/**
	 * Constructor.
	 */
	public FinderPane() 
	{
		setLayout(new BorderLayout(0, 0));
		
		finderHeadPane = new FinderHeadPane();
		add(finderHeadPane, BorderLayout.NORTH);
		
		finderScrollTable = new FinderScrollTable();
		add(finderScrollTable, BorderLayout.CENTER);
		
		finderHeadPane.getRunButton().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				find();	
			}
		});
		finderHeadPane.getTextField().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				find();
			}
		});
		repaint(); 
		validate();
	}
	
	public void find() 
	{
		resetResult();		
		// find
		ArrayList<ElementFound> result = ProjectBrowser.frame.getDiagramManager().strictlyFindByName(finderHeadPane.getText());
		finderScrollTable.setData(result);
	}
	
	public void resetResult() { finderScrollTable.reset(); repaint(); validate();}	
}
