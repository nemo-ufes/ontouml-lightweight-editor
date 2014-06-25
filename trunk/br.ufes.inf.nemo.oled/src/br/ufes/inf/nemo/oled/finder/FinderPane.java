package br.ufes.inf.nemo.oled.finder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;

public class FinderPane extends JPanel implements Editor {

	private static final long serialVersionUID = -3183962658000841153L;
	@SuppressWarnings("unused")
	private UmlProject project;
	private FinderScrollTable finderScrollTable;
	private FinderHeadPane finderHeadPane;
	private JLabel status;
	
	public FinderPane(UmlProject project)
	{
		this();
		this.project = project;
	}
		
	public void setProject(UmlProject project)
	{
		this.project = project;
	}
	
	public void requestFindFocus()
	{
		finderHeadPane.getTextField().requestFocus();		
	}
	
	/**
	 * Constructor.
	 */
	public FinderPane() 
	{
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		finderHeadPane = new FinderHeadPane();
		add(finderHeadPane, BorderLayout.NORTH);
		
		finderScrollTable = new FinderScrollTable();
		add(finderScrollTable, BorderLayout.CENTER);
		
		status = new JLabel("");
		status.setBackground(Color.LIGHT_GRAY);
		add(status, BorderLayout.SOUTH);
		status.setPreferredSize(new Dimension(450, 20));
		
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
		
		JTableHeader header = finderScrollTable.getTable().getTableHeader() ;		 
		header.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) 
		    {
		      JTableHeader h = (JTableHeader)e.getSource() ;
		      int nColumn = h.columnAtPoint(e.getPoint());		   
		      if (nColumn != -1) sortColumn(nColumn, h.getTable().getModel());
		    }		 
		    public void sortColumn(int nColumn, TableModel model)
		    {		      
		    	if(nColumn==0){
		    		resetResult();
		    		Collections.sort(finderScrollTable.getResult(),new NameComparator());
		    		finderScrollTable.setData(finderScrollTable.getResult());
		    		
		    	}
		    	if(nColumn==1){
		    		resetResult();
		    		Collections.sort(finderScrollTable.getResult(),new StereotypeComparator());
		    		finderScrollTable.setData(finderScrollTable.getResult());
		    	}
		    	if(nColumn==2){
		    		resetResult();
		    		Collections.sort(finderScrollTable.getResult(),new PathComparator());
		    		finderScrollTable.setData(finderScrollTable.getResult());
		    	}
		    }
		  }
		);
		
		repaint(); 
		validate();
	}
	
	public class NameComparator implements Comparator<ElementFound> 
    {
        @Override
        public int compare(ElementFound o1, ElementFound o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
	public class StereotypeComparator implements Comparator<ElementFound> 
    {
        @Override
        public int compare(ElementFound o1, ElementFound o2) {
            return o1.getType().compareToIgnoreCase(o2.getType());
        }
    }
	public class PathComparator implements Comparator<ElementFound> 
    {
        @Override
        public int compare(ElementFound o1, ElementFound o2) {
            return o1.getPath().compareToIgnoreCase(o2.getPath());
        }
    }
	public void find() 
	{
		resetResult();		
		// find
		ArrayList<ElementFound> result = ProjectBrowser.frame.getDiagramManager().strictlyFindByName(finderHeadPane.getText());
		Collections.sort(result,new StereotypeComparator());
		finderScrollTable.setData(result);
		status.setText("  "+result.size()+" items found.");
	}
	
	public void resetResult() { finderScrollTable.reset(); repaint(); validate(); }
	
	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.FINDER;
	}

	@Override
	public Diagram getDiagram() {
		return null;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public UmlProject getProject() {
		return null;
	}
}
