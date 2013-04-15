package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import RefOntoUML.Association;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AssociationsPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7525012929100418024L;
	
	private List<SingleAssociationPanel> singleAssocList = new ArrayList<SingleAssociationPanel>();
	
	private JPanel allAssocPanel, buttonPane;
	private JScrollPane assocScrollPane;
	private JButton selectSTButton, selectTSButton, selectAllButton;
	
	public AssociationsPanel()
	{
		initGUI();
	}
	
	public AssociationsPanel(OntoUMLParser ontoParser)
	{
		initGUI();
		
		Set<Association> assocSet = ontoParser.getAllInstances(Association.class);
		
		for (Association assoc : assocSet)
		{
			SingleAssociationPanel assocPanel = new SingleAssociationPanel(assoc);
			
			singleAssocList.add(assocPanel);
			
			allAssocPanel.add(assocPanel);
		}
	}
	
	private void initGUI()
	{
		setPreferredSize(new Dimension(550, 450));
		{
			assocScrollPane = new JScrollPane();
			assocScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			assocScrollPane.setPreferredSize(new Dimension (500, 350));
			
			allAssocPanel = new JPanel();
			allAssocPanel.setLayout(new GridLayout(0, 1, 0, 0));
			//allAssocPanel.setPreferredSize(new Dimension (500, 350));
			
			assocScrollPane.setViewportView(allAssocPanel);
			add(assocScrollPane, BorderLayout.CENTER);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(0, 3, 0, 0));
			buttonPane.setPreferredSize(new Dimension (500,30));
			
			add(buttonPane, BorderLayout.SOUTH);
			{
				selectSTButton = new JButton("Source->Target only");
				selectSTButton.addActionListener(this);
				selectTSButton = new JButton("Target->Source only");
				selectTSButton.addActionListener(this);
				selectAllButton = new JButton("Select All");
				selectAllButton.addActionListener(this);
			}
			buttonPane.add(selectSTButton);
			buttonPane.add(selectTSButton);
			buttonPane.add(selectAllButton);
		}
	}
	
	public List<SingleAssociationPanel> getAssocPanelList()
	{
		return singleAssocList;
	}
	
	public Map<Association, Integer> getAssDirectionMap()
	{
		Map<Association, Integer> directionsMap = new HashMap<Association, Integer>();
		
		for (SingleAssociationPanel assocPanel : singleAssocList)
		{
			directionsMap.put(assocPanel.getAssociation(), assocPanel.getSelectedDirection());
		}
		
		return directionsMap;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == selectSTButton)
		{
			for (SingleAssociationPanel assocPanel : getAssocPanelList())
			{
				assocPanel.clearTS();
				assocPanel.selectST();
			}
		}
		else if (a.getSource() == selectTSButton)
		{
			for (SingleAssociationPanel assocPanel : getAssocPanelList())
			{
				assocPanel.clearST();
				assocPanel.selectTS();
			}
		}
		else if (a.getSource() == selectAllButton)
		{
			for (SingleAssociationPanel assocPanel : getAssocPanelList())
			{
				assocPanel.selectST();
				assocPanel.selectTS();
			}
		}
	}
}
