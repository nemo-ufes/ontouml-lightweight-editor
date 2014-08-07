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
package br.ufes.inf.nemo.oled;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.problems.ErrorPane;
import br.ufes.inf.nemo.oled.problems.ProblemPane;
import br.ufes.inf.nemo.oled.problems.WarningPane;
import br.ufes.inf.nemo.oled.statistician.StatisticsPane;
import br.ufes.inf.nemo.oled.ui.OutputPane;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.util.ApplicationResources;

/**
 * @author John Guerson
 */
public class InfoManager extends JTabbedPane {

	private static final long serialVersionUID = 1L;	
	public static OutputPane outputPane;	
	public AppFrame frame;
	public UmlProject project;
		
	public void setProject(UmlProject project)
	{
		this.project = project;		
	}
	
	public void eraseProject()
	{
		this.project = null;
		
		outputPane.write("");
		
		for(Component c: getComponents()){
			if(c instanceof ProblemPane) remove(((ProblemPane)c));
		}
		repaint();
		revalidate();
	}
	
	public void selectWarnings()
	{
		for(Component c: getComponents())
		{
			if(c instanceof WarningPane) setSelectedIndex(indexOfComponent(c));	
		}
	}
	
	public void selectProblems()
	{
		for(Component c: getComponents())
		{
			if(c instanceof ProblemPane && !(c instanceof ErrorPane) && !(c instanceof WarningPane)) 
			{
				setSelectedIndex(indexOfComponent(c));				
			}
		}
	}
	
	public void selectStatistic()
	{
		for(Component c: getComponents())
		{
			if(c instanceof StatisticsPane){ setSelectedIndex(indexOfComponent(c)); }
		}
	}
	
	public void selectErrors()
	{
		for(Component c: getComponents())
		{
			if(c instanceof ErrorPane) setSelectedIndex(indexOfComponent(c));	
		}
	}
		
	public void selectConsole()
	{
		for(Component c: getComponents())
		{
			if(c instanceof OutputPane) setSelectedIndex(indexOfComponent(c));	
		}
	}
	
	public InfoManager (final AppFrame frame, final UmlProject project)
	{
		this.frame=frame;
		this.project = project;
				
		outputPane = new OutputPane();
		
		setBorder(null);
		setBackground(UIManager.getColor("Panel.background"));
		setMinimumSize(new Dimension(0,0));
				
		addTab(" Console ",outputPane);	
		setIconAt(indexOfComponent(outputPane),new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/monitor.png")));
						
	}
	
	public OutputPane getOutput(){
		return outputPane;
	}
		
	@SuppressWarnings("unused")
	private String getResourceString(String property) {
	    return ApplicationResources.getInstance().getString(property);
	}

	public void showOutputText(String text, boolean clear, boolean showOutput)
	{		
		if(clear)
			outputPane.write(text);
		else
			outputPane.append(text);
				
		if(showOutput){
			outputPane.setVisible(true);
			frame.selectConsole();
		}		
	}
	
	public UmlProject getProject(){
		return project;
	}

}
