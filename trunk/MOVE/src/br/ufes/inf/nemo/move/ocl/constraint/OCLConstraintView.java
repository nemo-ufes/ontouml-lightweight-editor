package br.ufes.inf.nemo.move.ocl.constraint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.move.ocl.editor.OCLEditorPanel;
import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * @author John Guerson
 */

public class OCLConstraintView extends JPanel {
	
	private static final long serialVersionUID = 1347036762872517047L;
	
	@SuppressWarnings("unused")
	private OCLConstraintModel oclConstraintModel;
	
	private TheFrame frame;	
	private JCheckBox cbxEnable;
	private OCLEditorPanel ocltextpanel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboModeList;
	private JPanel endpanel;
	
	/**
	 * Constructor.
	 * 
	 * @param oclConstraintModel
	 * @param frame
	 */
	public OCLConstraintView(OCLConstraintModel oclConstraintModel, TheFrame frame)
	{
		this();
		
		this.frame = frame;
		this.oclConstraintModel = oclConstraintModel;
		
		setConstraint(oclConstraintModel);
		
		validate();
		repaint();
	}	
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OCLConstraintView() 
	{
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setLayout(new BorderLayout(0, 0));
		
		JPanel headpanel = new JPanel();
		headpanel.setPreferredSize(new Dimension(50,50));
		
		add(headpanel, BorderLayout.NORTH);
		
		ocltextpanel = new OCLEditorPanel();
		add(ocltextpanel,BorderLayout.CENTER);
		
		cbxEnable = new JCheckBox("");
		cbxEnable.setHorizontalAlignment(SwingConstants.RIGHT);
		cbxEnable.setSelected(true);
		
		comboModeList = new JComboBox();
		comboModeList.setModel(new DefaultComboBoxModel(new String[] {"FACT", "SIMULATION", "ASSERTION"}));
		
		GroupLayout gl_headpanel = new GroupLayout(headpanel);
		gl_headpanel.setHorizontalGroup(
			gl_headpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headpanel.createSequentialGroup()
					.addGap(18)
					.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxEnable)
					.addContainerGap(260, Short.MAX_VALUE))
		);
		gl_headpanel.setVerticalGroup(
			gl_headpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_headpanel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(gl_headpanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbxEnable)
						.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		headpanel.setLayout(gl_headpanel);
		
		endpanel = new JPanel();
		add(endpanel, BorderLayout.SOUTH);
	}	
	
	public void setConstraint(OCLConstraintModel oclConstraintModel)
	{		
		ocltextpanel.textArea.setText(oclConstraintModel.getOCLString());
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}	
}
