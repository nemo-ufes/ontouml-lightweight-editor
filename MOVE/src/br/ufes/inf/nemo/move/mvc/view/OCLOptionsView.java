package br.ufes.inf.nemo.move.mvc.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.move.mvc.model.OCLOptionsModel;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.util.SingleConstraintPanel;

public class OCLOptionsView extends JScrollPane {

	private static final long serialVersionUID = 566520388850119106L;

	@SuppressWarnings("unused")
	private OCLOptionsModel oclOptModel;
	
	private TheFrame frame;	
	private JPanel ctpanel;
	private ArrayList<SingleConstraintPanel> singleConstraintsListPanel;
	
	public OCLOptionsView (OCLOptionsModel oclOptModel,TheFrame frame)
	{
		
		this();
	
		this.frame = frame;
		this.oclOptModel = oclOptModel;
		
		setOCLOptionView(oclOptModel,frame);
	}
	
	/**
	 * Set Option Model.
	 * 
	 * @param optModel
	 */
	public void setOCLOptionView (OCLOptionsModel oclOptModel,TheFrame frame)
	{					
		if (oclOptModel.getOCLOptions().getConstraintList().size()<3)
			ctpanel.setLayout(new GridLayout(3, 1, 0, 0));
		else
			ctpanel.setLayout(new GridLayout(oclOptModel.getOCLOptions().getConstraintList().size(), 1, 0, 0));
		
		for(Constraint ct : oclOptModel.getOCLOptions().getConstraintList())
		{
			SingleConstraintPanel singleConstraint = new SingleConstraintPanel();
			
			singleConstraint.txtConstraintName.setText(ct.getName());
			singleConstraint.txtConstraintType.setText(oclOptModel.getOCLOptions().getConstraintType(ct));	
			
			singleConstraintsListPanel.add(singleConstraint);
		}
	}
	
	/**
	 * Create the panel.
	 */
	public OCLOptionsView() 
	{				
		ctpanel = new JPanel();
		ctpanel.setPreferredSize(new Dimension(412,3*72));
		ctpanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		getVerticalScrollBar().setUnitIncrement(10);
		getHorizontalScrollBar().setUnitIncrement(10);
		setViewportView(ctpanel);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		singleConstraintsListPanel = new ArrayList<SingleConstraintPanel>();				
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}

}
