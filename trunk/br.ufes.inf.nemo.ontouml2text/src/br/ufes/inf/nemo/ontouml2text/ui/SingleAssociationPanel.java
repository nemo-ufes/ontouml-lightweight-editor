package br.ufes.inf.nemo.ontouml2text.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import RefOntoUML.Association;

public class SingleAssociationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3079433300422234833L;
	
	private Association assoc;
	private JLabel name, source, target;
	JCheckBox chckbxST, chckbxTS;
	
	public SingleAssociationPanel ()
	{
		initGUI();
	}
	
	public SingleAssociationPanel (Association assoc)
	{
		setAssociation(assoc);
		
		initGUI();
	}
	
	public void setAssociation(Association assoc)
	{
		this.assoc = assoc;
	}
	
	public Association getAssociation()
	{
		return this.assoc;
	}
	
	private void initGUI()
	{
		setPreferredSize(new Dimension(500, 65));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(250, 65));
		{
			name = new JLabel("Association " + assoc.getName());
			source = new JLabel("Source: " + assoc.getMemberEnd().get(0).getType().getName());
			chckbxST = new JCheckBox("Use Source -> Target");
		}
		GroupLayout gl_panel = new GroupLayout(rPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(name))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxST)
								.addComponent(source))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(name)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(source)
					.addGap(3)
					.addComponent(chckbxST)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		rPanel.setLayout(gl_panel);
		
		JPanel lPanel = new JPanel();
		lPanel.setPreferredSize(new Dimension(250, 65));
		{
			chckbxTS = new JCheckBox("Use Target -> Source");
			target = new JLabel("Target: " + assoc.getMemberEnd().get(1).getType().getName());
		}
		GroupLayout gl_panel_1 = new GroupLayout(lPanel);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(target)
						.addComponent(chckbxTS))
					.addGap(55))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addComponent(target)
					.addGap(3)
					.addComponent(chckbxTS)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		lPanel.setLayout(gl_panel_1);
		
		add(rPanel);
		add(lPanel);
	}
	
	public void selectST()
	{
		chckbxST.setSelected(true);
	}
	
	public void clearST()
	{
		chckbxST.setSelected(false);
	}
	
	public void selectTS()
	{
		chckbxTS.setSelected(true);
	}
	
	public void clearTS()
	{
		chckbxTS.setSelected(false);
	}
	
	public void selectBoth()
	{
		selectST();
		selectTS();
	}
	
	public void clearBoth()
	{
		clearST();
		clearTS();
	}
	
	public int getSelectedDirection()
	{
		if (chckbxST.isSelected() && !chckbxTS.isSelected())
		{
			return 1;
		}
		else if (!chckbxST.isSelected() && chckbxTS.isSelected())
		{
			return -1;
		}
		else
			return 0;
	}
}
