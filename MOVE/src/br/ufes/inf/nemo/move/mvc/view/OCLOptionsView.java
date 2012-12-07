package br.ufes.inf.nemo.move.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.SingleConstraintPanel;

/**
 * 
 * This class is a View for OCL Options Model.
 * 
 * @author John Guerson
 */

public class OCLOptionsView extends JPanel {

	private static final long serialVersionUID = 566520388850119106L;

	@SuppressWarnings("unused")
	private OCLOptionsModel oclOptModel;
	
	private TheFrame frame;	
	private JPanel ctpanel;
	private JScrollPane scrollPane; 
	private ArrayList<SingleConstraintPanel> singleConstraintsListPanel;
	private JButton btnDisableAll;
	private JButton btnEnableAll;
	private JLabel lblYouCanAlso;
	
	/**
	 * Creates a View from a OCL Options Model and the main Frame of the application.
	 * 
	 * @param oclOptModel
	 * @param frame
	 */
	public OCLOptionsView (OCLOptionsModel oclOptModel,TheFrame frame)
	{		
		this();
	
		this.frame = frame;
		this.oclOptModel = oclOptModel;
		
		setOCLOptionView(oclOptModel);
	}
	
	/**
	 * Set View from a OCL Options Model.
	 * 
	 * @param optModel
	 */
	public void setOCLOptionView (OCLOptionsModel oclOptModel)
	{
		ctpanel.removeAll();
		this.oclOptModel = oclOptModel;
		
		if (oclOptModel.getOCLOptions().getConstraintList().size()<4)
		{
			ctpanel.setLayout(new GridLayout(4, 1, 0, 0));			
		} else {
			ctpanel.setLayout(new GridLayout(oclOptModel.getOCLOptions().getConstraintList().size(), 1, 0, 0));			
		}
		
		for(Constraint ct : oclOptModel.getOCLOptions().getConstraintList())
		{
			SingleConstraintPanel singleConstraint = new SingleConstraintPanel(ct,oclOptModel.getOCLOptions().getConstraintType(ct));						
			singleConstraintsListPanel.add(singleConstraint);
			ctpanel.add(singleConstraint);
		}		
		validate();
		repaint();
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
	 * Get a list of Transformation Types from Options View. i.e. if it is "FACT","SIMULATION" OR "ASSERTION"
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
	 * Get a list of Constraint from Options View.
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
	 * Create an Empty View for OCL Options Model.
	 */
	public OCLOptionsView() 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setPreferredSize(new Dimension(591,179));
		setLayout(new BorderLayout(0,0));
		
		ctpanel = new JPanel();		
		ctpanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setViewportView(ctpanel);
		scrollPane.setPreferredSize(new Dimension(591,179));
				
		singleConstraintsListPanel = new ArrayList<SingleConstraintPanel>();
		
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(591, 40));
		
		JPanel introPanel = new JPanel();
		introPanel.setBackground(Color.WHITE);
		introPanel.setPreferredSize(new Dimension(591, 30));
		
		add(introPanel,BorderLayout.NORTH);
		
		lblYouCanAlso = new JLabel("You can also check and simulate constraints...");
		lblYouCanAlso.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_introPanel = new GroupLayout(introPanel);
		gl_introPanel.setHorizontalGroup(
			gl_introPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(lblYouCanAlso)
					.addContainerGap(272, Short.MAX_VALUE))
		);
		gl_introPanel.setVerticalGroup(
			gl_introPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYouCanAlso)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		introPanel.setLayout(gl_introPanel);
		add(scrollPane,BorderLayout.CENTER);
		add(btnPanel,BorderLayout.SOUTH);
		
		btnEnableAll = new JButton("Enable All");	
		btnEnableAll.setPreferredSize(new Dimension(100, 25));
		btnDisableAll = new JButton("Disable All");
		btnDisableAll.setPreferredSize(new Dimension(100, 25));
		
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
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		btnPanel.add(btnEnableAll);
		btnPanel.add(btnDisableAll);
	}
	
	/**
	 * Get the main Frame of Application.
	 * 
	 * @return
	 */
	public TheFrame getTheFrame()
	{
		return frame;
	}

}
