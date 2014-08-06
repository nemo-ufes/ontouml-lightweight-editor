/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.problems;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import br.ufes.inf.nemo.oled.finder.FoundElement;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;

/**
 * @author John Guerson
 */
public class ProblemPane extends JPanel implements Editor {

	private static final long serialVersionUID = -3183962658000841153L;
	private UmlProject project;
	private ProblemScrollTable problemScrollTable;	
	private JLabel status;

	protected void setProject(UmlProject project)
	{
		this.project = project;
	}

	public ProblemPane(UmlProject project)
	{
		this();
		this.project = project;
		
		repaint(); 
		validate();
	}
		
	public void setData(ArrayList<ProblemElement> list)
	{
		if(problemScrollTable!=null) problemScrollTable.setProblems(list);		
	}
	
	public void resetData() 
	{ 
		problemScrollTable.reset();	
		status.setText("");
		repaint(); 
		validate(); 
	}	
	
	public void setStatus(String text)
	{
		status.setText("  "+text);
	}
	
	/**
	 * Constructor.
	 */
	protected ProblemPane() 
	{
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
				
		String[] columns = {"Type","Description", "Stereotype", "Element","Location"};		
				
		status = new JLabel("");
		status.setBackground(Color.LIGHT_GRAY);
		add(status, BorderLayout.SOUTH);
		status.setPreferredSize(new Dimension(450, 20));
		
		problemScrollTable = new ProblemScrollTable(columns);
		add(problemScrollTable, BorderLayout.CENTER);
	    
		JTableHeader header = problemScrollTable.getTable().getTableHeader() ;		 
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
		    		resetData();
		    		Collections.sort(problemScrollTable.getProblems(),new TypeProblemComparator());
		    		problemScrollTable.setProblems(problemScrollTable.getProblems());
		    	}
		    	if(nColumn==1){
		    		resetData();
		    		Collections.sort(problemScrollTable.getProblems(),new DescriptionComparator());
		    		problemScrollTable.setProblems(problemScrollTable.getProblems());		    		
		    	}
		    	if(nColumn==2){
		    		resetData();
		    		Collections.sort(problemScrollTable.getProblems(),new StereotypeComparator());
		    		problemScrollTable.setProblems(problemScrollTable.getProblems());
		    		
		    	}
		    	if(nColumn==3){
		    		resetData();
		    		Collections.sort(problemScrollTable.getProblems(),new NameComparator());
		    		problemScrollTable.setProblems(problemScrollTable.getProblems());		    		
		    	}
		    	if(nColumn==4){
		    		resetData();
		    		Collections.sort(problemScrollTable.getProblems(),new PathComparator());
		    		problemScrollTable.setProblems(problemScrollTable.getProblems());
		    	}		    	
		    }
		  }
		);
		
		repaint(); 
		validate();
	}
		
	public class NameComparator implements Comparator<FoundElement> 
    {
        @Override
        public int compare(FoundElement o1, FoundElement o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }
	public class StereotypeComparator implements Comparator<FoundElement> 
    {
        @Override
        public int compare(FoundElement o1, FoundElement o2) {
            return o1.getType().compareToIgnoreCase(o2.getType());
        }
    }
	public class PathComparator implements Comparator<FoundElement> 
    {
        @Override
        public int compare(FoundElement o1, FoundElement o2) {
            return o1.getPath().compareToIgnoreCase(o2.getPath());
        }
    }
	
	public class DescriptionComparator implements Comparator<ProblemElement> 
    {
        @Override
        public int compare(ProblemElement o1, ProblemElement o2) {
            return o1.getDescription().compareToIgnoreCase(o2.getDescription());
        }
    }
	
	public class TypeProblemComparator implements Comparator<ProblemElement> 
    {
        @Override
        public int compare(ProblemElement o1, ProblemElement o2) {
            return o1.getTypeProblemString().compareToIgnoreCase(o2.getTypeProblemString());
        }
    }
	
	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.PROBLEMS;
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
		return project;
	}
}
