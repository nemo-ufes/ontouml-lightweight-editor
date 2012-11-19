package br.ufes.inf.nemo.move.ocl.constraint;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;

/**
 * @author John Guerson
 */

public class OCLConstraintView extends JPanel {
	
	private static final long serialVersionUID = 1347036762872517047L;
	
	@SuppressWarnings("unused")
	private OCLConstraintModel oclConstraintModel;
	
	@SuppressWarnings("unused")
	private TheFrame frame;
	
	private JCheckBox cbxEnable;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboModeList;
	
	private OCLEditorScrollPane editorScrollPane;
	
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
	
	public void setConstraint(OCLConstraintModel oclConstraintModel)
	{		
		editorScrollPane.write(oclConstraintModel.getOCLString());
	}
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OCLConstraintView() 
	{
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		comboModeList = new JComboBox();
		comboModeList.setModel(new DefaultComboBoxModel(new String[] {"FACT", "SIMULATION", "ASSERTION"}));
		
		cbxEnable = new JCheckBox("Enable");
		cbxEnable.setHorizontalAlignment(SwingConstants.RIGHT);
		cbxEnable.setSelected(true);
		
		editorScrollPane = new OCLEditorScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(editorScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxEnable, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboModeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxEnable))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorScrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}	
}
