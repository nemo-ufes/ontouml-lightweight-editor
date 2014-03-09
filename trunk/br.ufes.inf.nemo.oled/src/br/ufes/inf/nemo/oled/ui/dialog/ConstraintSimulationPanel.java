package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOption;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.ProjectBrowser;

/**
 * @author John Guerson
 */

public class ConstraintSimulationPanel extends JPanel {

	private static final long serialVersionUID = 566520388850119106L;

	@SuppressWarnings("unused")
	private OCL2AlloyOption oclOptions;
	
	private AppFrame frame;	
	
	private JPanel ctpanel;
	private JScrollPane scrollPane; 
	private ArrayList<SingleConstraintPanel> singleConstraintsListPanel;
	private JButton btnDisableAll;
	private JButton btnEnableAll;
	private JLabel lblChooseWhichConstraints;
	private JButton btndefault;

	public ConstraintSimulationPanel (OCL2AlloyOption oclOptions,AppFrame frame)
	{		
		this();
	
		this.frame = frame;
		this.oclOptions = oclOptions;
		
		setOCLOptionPane(oclOptions,frame);
	}
	
	public void setOCLOptionPane (OCL2AlloyOption oclOptions, AppFrame frame)
	{
		this.frame=frame;
		ctpanel.removeAll();
		this.oclOptions = oclOptions;
		
		if (oclOptions.getConstraintList().size()<4)
		{
			ctpanel.setLayout(new GridLayout(4, 1, 0, 0));			
		} else {
			ctpanel.setLayout(new GridLayout(oclOptions.getConstraintList().size(), 1, 0, 0));			
		}
		
		for(Constraint ct : oclOptions.getConstraintList())
		{
			SingleConstraintPanel singleConstraint = new SingleConstraintPanel(ct,oclOptions.getConstraintType(ct),
				ProjectBrowser.getOCLModelFor(frame.getDiagramManager().getCurrentProject()).getOCLParser().getUMLEnvironment());			
			singleConstraintsListPanel.add(singleConstraint);
			ctpanel.add(singleConstraint);
		}		
		
		btnEnableAll.setEnabled(true);
		btnDisableAll.setEnabled(true);
		btndefault.setEnabled(true);
		
		invalidate();
	}
		
	/**
	 * Get a list of Scopes from Options View.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getScopesListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.spinCommandScope.getValue());
		}
		return list;
	}
	
	/**
	 * Get a list of bitwidths from Options View.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getBitWidthListSelected()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Integer)singleCt.bitSpinner.getValue());
		}
		return list;
	}
	
	public ArrayList<Boolean> getSelectionList()
	{
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected()) list.add(true);
		}
		return list;		
	}
	
	/**
	 * Get a list of Transformation Types from Options Pane. i.e. if it is "FACT","SIMULATION" OR "ASSERTION"
	 * 
	 * @return
	 */
	public ArrayList<String> getTransformationsTypesListSelected()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((String)singleCt.comboTransformationType.getSelectedItem());
		}
		return list;
	}
	
	/**
	 * Get a list of Constraint from Options Pane.
	 * 
	 * @return
	 */
	public ArrayList<Constraint> getConstraintListSelected()
	{
		ArrayList<Constraint> list = new ArrayList<Constraint>();
		for(SingleConstraintPanel singleCt: singleConstraintsListPanel)
		{
			if (singleCt.checkEnforce.isSelected())
				list.add((Constraint)singleCt.constraint);
		}
		return list;
	}
			
	/**
	 * Create an Empty Pane for OCL Options.
	 */
	public ConstraintSimulationPanel() 
	{
		setBorder(BorderFactory.createTitledBorder("(Temporal) Constraints"));		
		setPreferredSize(new Dimension(470, 260));
		
		ctpanel = new JPanel();		
		ctpanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(ctpanel);
		scrollPane.setPreferredSize(new Dimension(591,179));
				
		singleConstraintsListPanel = new ArrayList<SingleConstraintPanel>();
		
		lblChooseWhichConstraints = new JLabel("Choose which constraints do you want to simulate and check.");
		
		btnEnableAll = new JButton("Enable All");	
		btnEnableAll.setPreferredSize(new Dimension(100, 25));
		btnEnableAll.setEnabled(false);
		
		btnEnableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPanel singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(true);
				}
			}
		});
		btnDisableAll = new JButton("Disable All");
		btnDisableAll.setPreferredSize(new Dimension(100, 25));
		btnDisableAll.setEnabled(false);
		
		btnDisableAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(SingleConstraintPanel singleCt : singleConstraintsListPanel)
				{
					singleCt.checkEnforce.setSelected(false);
				}
			}
		});
		
		btndefault = new JButton("(Default)");
		btndefault.setEnabled(false);
		btndefault.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(99)
							.addComponent(btndefault)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblChooseWhichConstraints, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblChooseWhichConstraints)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnableAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndefault, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(107))
		);
		setLayout(groupLayout);
	}			
	
	/**
	 * Get the main Frame of Application.
	 * 
	 * @return
	 */
	public AppFrame getFrame()
	{
		return frame;
	}

}
