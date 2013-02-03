package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import RefOntoUML.Association;
import RefOntoUML.PackageableElement;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AssociationsPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7525012929100418024L;
	
	private List<SingleAssociationPanel> singleAssocList = new ArrayList<SingleAssociationPanel>();
	
	private JPanel allAssocPanel;
	
	public AssociationsPanel()
	{
		initGUI();
	}
	
	public AssociationsPanel(OntoUMLParser ontoParser)
	{
		initGUI();
		
		Set<PackageableElement> pelSet = ontoParser.getAllInstances(PackageableElement.class);
		
		for (PackageableElement pel : pelSet)
		{
			if (pel instanceof Association)
			{
				Association assoc = (Association) pel;
		
				SingleAssociationPanel assocPanel = new SingleAssociationPanel(assoc);
				
				singleAssocList.add(assocPanel);
				
				allAssocPanel.add(assocPanel);
			}
		}
	}
	
	private void initGUI()
	{
		setPreferredSize(new Dimension(500, 350));
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		allAssocPanel = new JPanel();
		setViewportView(allAssocPanel);
		allAssocPanel.setLayout(new GridLayout(0, 1, 0, 0));
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
}
