package br.ufes.inf.nemo.move.ui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class CheckBoxListScrollPane extends JScrollPane {

	private static final long serialVersionUID = 6743703123244223364L;

	public JPanel checklistPanel;
	public Collection<JCheckBox> checkArrayList;
	
	/**
	 * Constructor.
	 * 
	 * @param checkArrayList
	 */
	public CheckBoxListScrollPane(Collection<JCheckBox> checkArrayList)
	{
		setCheckBoxList(checkArrayList);
	}
	
	public void setCheckBoxList(Collection<JCheckBox> checkArrayList)
	{
		if (checkArrayList.size()<6)
			checklistPanel.setLayout(new GridLayout(6, 1, 0, 0));
		else
			checklistPanel.setLayout(new GridLayout(checkArrayList.size(), 1, 0, 0));
		
		for(JCheckBox ch: checkArrayList)
		{
			this.checkArrayList.add(ch);
			ch.setSize(new Dimension(checklistPanel.getHeight(),50));
			checklistPanel.add(ch);
		}		
	}
	
	public Collection<JCheckBox> getCheckBoxList()
	{
		return checkArrayList;
	}
	
	/**
	 * Constructor.
	 */
	public CheckBoxListScrollPane() 
	{
		checklistPanel = new JPanel();
		checklistPanel.setBackground(Color.WHITE);
		checklistPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		checklistPanel.setPreferredSize(new Dimension(130, 180));
		
		checkArrayList = new ArrayList<JCheckBox>();
		
		setPreferredSize(new Dimension(150, 185));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		setViewportView(checklistPanel);		
	}

}
